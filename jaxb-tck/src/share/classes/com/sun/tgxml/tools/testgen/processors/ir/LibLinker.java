/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.tgxml.tools.testgen.processors.ir;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.LibraryDependency;
import com.sun.tgxml.tjtf.api.code.CodeFactory;
import com.sun.tgxml.tjtf.api.tests.TestFactory;
import com.sun.tgxml.tjtf.tools.BuildProperties;

import com.sun.tgxml.tools.filter.FilterUtil;
import com.sun.tgxml.tools.filter.libutil.LibMap;
import com.sun.tgxml.tools.filter.libutil.LibMapFile;
import com.sun.tgxml.tools.filter.libutil.XmlFileNameMap;
import com.sun.tgxml.tools.filter.libutil.LibFilterFactrory;

import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser;
import com.sun.tgxml.tools.testgen.processors.emitter.TGVisitor;
import com.sun.tgxml.tjtf.processors.parser.XMLParser;
import com.sun.tgxml.util.IR;

/**
 * <b>LibLinker</b> - default IRProcessor.
 * <p>
 * LibLinker is designed to absolve TestGen
 * emitters from dependency processing.
 * <p>
 * LibLinker extracts Exports from dependent external libraries. For
 * TestGroup it also responsible for including ExternalInline libraries
 * TestGroup depends on (directly or not).
 * <p>
 * To find external library by id libMap file name should be set
 * via "libmap" property. If libMap is not set, then imports for
 * dependent libraries are detected in the old manner (taken from
 * <i>library.id.libID</i> build property) and ExternalInline libraries
 * are not added.
 * <p>
 * The list of classes provided by the library can be specified
 * in two ways: via library Export and via <i>library.id.libID</i>
 * build property. The first way is more preferable. (The second
 * way is currently supported for the sake of backward compatibly)
 * <p>
 * If provided classes are not specified explicitly, it's assumed
 * that library provides the whole package:
 * <br>
 * <code>[libraryPackagePrefix].[libID].*</code>, where
 * libraryPackagePrefix is value of <i>lib.packagePrefix</i> property
 * <p>
 * If provided classes are specified in both places, then LibLinker
 * checks that library "Exports" do not conflict with build properties.
 * In case when build property defines class missed in "Exports",
 * TestFileException will be thrown.
 * <p>
 * If TestGroup or Library directly depends on libID, LibLinker
 * detects list of provided classes and adds all of them to the TestGroup
 * or Library "Imports"
 * <p>
 * During TestGroup processing LibLinker recursively detected all
 * ExternalInline libraries and adds them to own inline libraries.
 * So libraries ExternalInline depends on are treated as TestGroup
 * directly dependent.
 * <p>
 * LibLinker caches all preloaded libraries. So external library is
 * parsed only by the first request. On the next request library with
 * the same libID the library will be taken from cache.
 *
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko
 *
 */

public class LibLinker extends One2OneProcessor {
    
    private boolean isInitialized = false;
    protected LazyMap lazyMap = null;
    protected static final int EXEC_ARGS = 0;
    protected static final int CONTEXT = 1;
    protected static final int BASECLASS = 2;
    public LibLinker() {
        super();
    }
    
    public LibLinker(Properties props) {
        super(props);
    }
    
    /**
     * Adds required imports for external libraries.
     * For inline library does nothing.
     */
    public Library process(Library lib) throws TestFileException {
        if (lib == null || lib.isInline())
            return lib;
        initialize();
        
        ArrayList directDeps = IR.getDependentLibs(lib);
        if (directDeps == null || directDeps.size() == 0) {
            return lib;
        }
        
        ArrayList newImports = new ArrayList();
        for (Iterator it = removeDups(directDeps).iterator(); it.hasNext();) {
            String libID = (String)it.next();
            newImports.addAll(detectExports(libID));
        }
        
        addLibImports(lib, newImports);
        
        return lib;
        
    }
    
    /**
     * Adds required imports and ExternalInline libraries.
     */
    public TestGroup process(TestGroup tg) throws TestFileException {
        if (tg == null)
            return tg;
        initialize();
        
        /*
          step #1:
          Detect the list of library ids which TestGroup
          (and all testcases and inline libraries) direclty depends on.
         
          step #2:
          Detect External Inline libraries which TestGroup
          recursively depends on and include them into TestGroup
         */
        
        ArrayList externalDeps = includeExternalInline(tg);
        
        if (isMultiTest(tg)) {
            processExternalExports(tg, externalDeps);
        }
        
        HashMap baseClassExecArgs = new HashMap();
        processBaseClasses(tg, externalDeps, baseClassExecArgs);
        
        
        if (canGetExternalLibrary()) {
            processExternalExecuteArgs(tg, externalDeps, baseClassExecArgs);
            processExternalContext(tg, externalDeps);
        }
        
        return tg;
    }
    
    protected void processBaseClasses(TestGroup tg, ArrayList externalDeps, HashMap baseClassExecArgs) throws TestFileException {
        ArrayList tiList = getAllTestItems(tg);
        ArrayList baseClasses = new ArrayList();
        Library bcLib = null;
        collectBaseClasses(tiList.iterator(),baseClasses);
        if (canGetExternalLibrary()) {
            collectBaseClasses(externalDeps.iterator(),baseClasses);
        }
        Iterator i = baseClasses.iterator();
        while (i.hasNext()) {
            String execArgs = "";
            String propExecArgs = "";
            String bcID = (String)i.next();
            String bcLibID = BuildProperties.getString("test.baseClass."+bcID+".className");
            if (bcLibID == null || bcLibID.trim().equals("") ) {
                bcLibID = bcID;
            }
            
            propExecArgs = BuildProperties.getString("test.baseClass."+bcID+".executeArgs");
            if (propExecArgs == null) {
                propExecArgs = "";
            }
            propExecArgs.trim();
            
            try {
                bcLib = getExternalLibrary(bcLibID);
            }
            catch (TestFileException tfe) {
                bcLib=null;
            }
            if (bcLib == null || bcLib.getCodeSet() == null ) {
                baseClassExecArgs.put(bcLibID, propExecArgs);
                continue;
            }
            execArgs = bcLib.getCodeSet().getExecuteArgs();
            if (execArgs == null) {
                execArgs = "";
            }
            execArgs.trim();
            
            if (!execArgs.equals("") && !propExecArgs.equals("")) {
                throw new TestFileException("ExecuteArgs Definition Conflict! Execute Args found in build properties file for base class " +
                                        bcID + " as well as within UTD definition for " + bcID + ".\n");
            }
            
            if  (execArgs.equals("")) {
                baseClassExecArgs.put(bcLibID, propExecArgs);
            }
            else {
                baseClassExecArgs.put(bcLibID, execArgs);
            }
        }
    }
    
    private void collectBaseClasses(Iterator i, ArrayList res) throws TestFileException {
       CodeSet cs = null;
       while (i.hasNext()) {
            Object item = i.next();
            if (item instanceof TestItem) {
                cs = ((TestItem)item).getCodeSet();
            }
            if (item instanceof String) {
                String libDep = (String)item;
                try {
                    cs = getExternalLibrary(libDep).getCodeSet();
                }
                catch (TestFileException tfe) {
                    cs = null;
                }
            }
            if (cs == null) {
                continue;
            }
            String bc = cs.getBaseClass();
            if (bc == null || bc.trim().equals("")) {
                continue;
            }
            res.add(bc.trim());
        } 
    }
    
    /**
     * Detects the list of library ids which TestGroup
     * (and all testcases and inline libraries) direclty depends on.
     * <br>
     * Inlcudes all required External Inline libraries into TestGroup.
     * <br>
     * Returns list of external libraries TestGroups directly depends on.
     */
    protected ArrayList includeExternalInline(TestGroup tg)
    throws TestFileException {
        
        /* step #1:
          Detect the list of library ids which TestGroup
          (and all testcases and inline libraries) direclty depends on.
         */
        
        // libraries which TestGroup direclty depends on.
        ArrayList directDeps = new ArrayList();
        
        // list of ID of processed libraries
        ArrayList processedLibs = new ArrayList();
        
        directDeps.addAll(IR.getDependentLibs(tg));
        
        ArrayList testCases = tg.getTestCases();
        if (testCases != null) {
            for (Iterator it = testCases.iterator(); it.hasNext();) {
                directDeps.addAll(IR.getDependentLibs((TestCase)it.next()));
            }
        }
        
        ArrayList inlineLibs = tg.getLibraries();
        ArrayList inlineLibIDs = new ArrayList();
        if (inlineLibs != null) {
            for (Iterator it = inlineLibs.iterator(); it.hasNext();) {
                Library lib = (Library)(it.next());
                directDeps.addAll(IR.getDependentLibs(lib));
                processedLibs.add(IR.getID(lib));
                inlineLibIDs.add(IR.getID(lib));
            }
        } else {
            inlineLibs = new ArrayList();
        }
        
        /* step #2:
          Detect External Inline libraries which TestGroup
          recursively depends on.
         */
        if (canGetExternalLibrary()) {
            ArrayList newInlineLibs = new ArrayList();
            ArrayList toProcess = (ArrayList)directDeps.clone();
            while(toProcess.size() > 0) {
                String libID = (String)toProcess.remove(0);
                if (processedLibs.contains(libID)) {
                    continue;
                }
                processedLibs.add(libID);
                
                Library lib = getExternalLibrary(libID);
                if (lib.isInline()) {
                    newInlineLibs.add(TestFactory.createInlineLibrary(lib));
                }
                toProcess.addAll(IR.getDependentLibs(lib));
        
            }
            inlineLibs.addAll(newInlineLibs);
            tg.setLibraries(inlineLibs);
            
            // add newInlineLibs dependencies to the direct dependencies list
            for (Iterator it = newInlineLibs.iterator(); it.hasNext();) {
                Library lib = (Library)(it.next());
                directDeps.addAll(IR.getDependentLibs(lib));
                inlineLibIDs.add(IR.getID(lib));
            }
            
        }
        directDeps.removeAll(inlineLibIDs);
        return removeDups(directDeps);
    }
    
    /**
     * Adds imports required for External Libraries usage
     */
    protected void processExternalExports(TestGroup tg,
            ArrayList externalDeps) throws TestFileException {
        
        ArrayList newImports = new ArrayList();
        for (Iterator it = externalDeps.iterator(); it.hasNext();) {
            String libID = (String)it.next();
            newImports.addAll(detectExports(libID));
        }
        
        addTGImports(tg, newImports);
    }
    
    /**
     * Sets "externalLibsExecuteArgs" AttrElem as summary of
     * "ExecuteArgs" defined in the external libraries TestGroup depends on.
     */
    protected void processExternalExecuteArgs(TestGroup tg,
            ArrayList externalDeps, HashMap bassClassExecArgs) throws TestFileException {
        
        HashSet processedTestitems = new HashSet();
        StringBuffer execArgsBuf = new StringBuffer();
        
        for (Iterator it = externalDeps.iterator(); it.hasNext();) {
            String libID = (String)it.next();
            getAllExecuteArgs(getExternalLibrary(libID),processedTestitems,execArgsBuf, bassClassExecArgs);
        }
       
        
        //set final value execArgs in IR
        if (!execArgsBuf.toString().trim().equals("")) {
            IR.setAttrElem(tg, TGVisitor.EXE_ARGS_ATTRELEM, execArgsBuf.toString().trim());
        }
    }
    
    /**
     * Returns TestItem list TestGroup has. (TestGroup itself, TestCases
     * and InlineLibraries.
     */
    protected ArrayList getAllTestItems(TestGroup testGroup) {
         ArrayList list = new ArrayList();
         list.add(testGroup);
         ArrayList testcases = testGroup.getTestCases();
         if (testcases != null) {
             list.addAll(testcases);
         }
         ArrayList libs = testGroup.getLibraries();
         if (libs != null) {
             list.addAll(libs);
         }
         return list;
    }
    
    /**
     * Sets "externalLibsContext" AttrElem as summary of
     * "Context" defined in the external libraries TestGroup depends on.
     */
    protected void processExternalContext(TestGroup tg,
            ArrayList externalDeps) throws TestFileException {
        
        StringBuffer contextsBuf = new StringBuffer();
        HashMap processedLibraries = new HashMap();
        for (Iterator it = externalDeps.iterator(); it.hasNext();) {
            String libID = (String)it.next();
            String value = getContext(libID);
            getAllContexts(getExternalLibrary(libID),null,contextsBuf);
        }
        if (!contextsBuf.toString().trim().equals("")) {
            IR.setAttrElem(tg, TGVisitor.CONTEXT_ATTRELEM, contextsBuf.toString());
        }
    }
    
    
    /**
     * Returns ExecuteArgs defined in external library, or null if not defined.
     */
    protected String getExecuteArgs(String libID) throws TestFileException {
        return IR.getExecuteArgs(getExternalLibrary(libID));
    }
    

    
    protected void getCodeSetParms(TestItem ti, HashSet processedTestitems, StringBuffer args, int type, HashMap values) throws TestFileException {
        
        if (processedTestitems == null) {
            processedTestitems = new HashSet();
        }
        
        if (args == null) {
            args = new StringBuffer();
        }
        
        if (ti == null) {
            return;
        }
        if (processedTestitems.contains(ti.getID())){
            return;
        }
        if (values == null) {
            values = new HashMap();
        }
        processedTestitems.add(ti.getID());
        CodeSet cs = ti.getCodeSet();
        if (cs != null) {
            String IRParmArgs = null;
            switch(type) {
                case EXEC_ARGS:
                    if (values.containsKey(ti.getID())) {
                        IRParmArgs = (String) values.get(ti.getID());
                    }
                    else {
                        IRParmArgs = IR.getExecuteArgs(ti);
                    }
                    break;
                case CONTEXT:
                    IRParmArgs = IR.getContext(ti);
                    break;
                default:
                    throw new TestFileException("getCodeSetParams Unsupported type: " + type + ".");
            }
            

            
            if (IRParmArgs != null) {
                if(args.length() > 0) {
                    args.append(" ");
                }
                args.append(IRParmArgs);
            }
            
            ArrayList dep = cs.getDependencies();
            if (dep != null) {
                Iterator iter = dep.iterator();
                while (iter.hasNext()) {
                    LibraryDependency ld = (LibraryDependency)iter.next();
                    String newlibID = ld.getID();
                    if (newlibID != null) {
                        getCodeSetParms(getExternalLibrary(newlibID),processedTestitems, args, type, values);
                    }
                }
            }
        }
    }
    
    protected void getAllExecuteArgs(TestItem ti, HashSet processedTestitems, StringBuffer args, HashMap baseClassExecArgs) throws TestFileException {
        getCodeSetParms(ti, processedTestitems, args, EXEC_ARGS, baseClassExecArgs);
    }
    protected void getAllContexts(TestItem ti, HashSet processedTestitems, StringBuffer contexts) throws TestFileException{
        getCodeSetParms(ti, processedTestitems, contexts, CONTEXT, null);
    }
    
    /**
     * Returns Context defined in external library, or null if not defined.
     */
    protected String getContext(String libID) throws TestFileException {
        return IR.getContext(getExternalLibrary(libID));
    }
    
    
    /**
     * Detects classes list required required to be imported by libID.
     */
    protected ArrayList detectExports(String libID) throws TestFileException {
        ArrayList exports = new ArrayList();
        if (canGetExternalLibrary()) {
            Library lib = getExternalLibrary(libID);
            if (!lib.isInline()) {
                ArrayList libExports = getLibExports(lib);
                if (libExports != null) {
                    exports.addAll(libExports);
                } else {
                    return exports; // an empty list
                    // Library does not provide any exports
                }
            } else {
                return exports; // an empty list
                // InlineLibrary cannot provide any exports
            }
        }
        
        ArrayList bpExports = getBuildPropsExports(libID);
        if (bpExports.size() == 0 && exports.size() == 0) {
            // provided classes are no specified explicitly
            return getDefaultExports(libID);
        } else if (bpExports.size() == 0) {
            return exports;
        } else if (exports.size() == 0) {
            return bpExports;
        }
        
        // provided classes are specified in two ways: via Export and
        // via build properties.
        checkConflict(exports, bpExports, libID);
        return exports;
    }
    
    /**
     * Returns Export list defined in the passed library.
     * If no Export is defined for library an empty list will be returned.
     * If there is an Export defined as "NONE" the method returns null.
     */
    protected static ArrayList getLibExports(Library lib) throws TestFileException {
        try {
            boolean noneExportsFound = false;
            ArrayList exports = lib.getCodeSet().getExports();
            if (exports == null) {
                return new ArrayList();
            }
            for (Iterator it = exports.iterator(); it.hasNext();) {
                if (LibUtils.isNoExports((String)it.next())) {
                    noneExportsFound = true;
                }
            }
            if (noneExportsFound) {
                if (exports.size() > 1) {
                    throw new TestFileException("Library " + lib.getID() +
                            " defines one or more exports while also setting export to NONE");
                }
                return null;
            }
            return exports;
        } catch (NullPointerException e) {
            return new ArrayList();
        }
    }
    
    /**
     * Returns Library imports from build properties
     */
    protected static ArrayList getBuildPropsExports(String libID) {
        ArrayList result = new ArrayList();
        String classes = BuildProperties.getString("library.id." + libID);
        if (classes != null && classes.trim().length() > 0) {
            // use user-defined mapping, import all classes defined
            StringTokenizer tok = new StringTokenizer(classes);
            while (tok.hasMoreTokens()) {
                result.add(tok.nextToken());
            }
        }
        return result;
    }
    
    /**
     * Returns default Library imports
     */
    protected static ArrayList getDefaultExports(String libID) {
        ArrayList result = new ArrayList(1);
        result.add(LibUtils.getLibraryPackage(libID) +"." + LibUtils.getLibraryClassName(libID));
        return result;
    }
    
    /**
     * Adds new imports to the passed TestGroup.
     */
    protected void addTGImports(TestGroup tg, ArrayList newImports) {
        if (newImports == null || newImports.size() == 0)
            return;
        
        CodeSet cs = tg.getCodeSet();
        if (cs == null)
            cs = CodeFactory.createCodeSet();
        tg.setCodeSet(cs);
        ArrayList oldImports = cs.getImports();
        if (oldImports == null) {
            oldImports = new ArrayList();
        }
        for (Iterator it = newImports.iterator(); it.hasNext();) {
            String imp = (String)it.next();
            if (imp != null && !imp.equals("") && !oldImports.contains(imp)
            && !isRedundantImport(imp)) {
                oldImports.add(imp);
            }
        }
        cs.setImports(oldImports);
    }
    
    /**
     * Adds new imports to the passed TestGroup.
     */
    protected void addLibImports(Library lib, ArrayList newImports) {
        if (newImports == null || newImports.size() == 0)
            return;
        
        CodeSet cs = lib.getCodeSet();
        if (cs == null)
            cs = CodeFactory.createCodeSet();
        lib.setCodeSet(cs);
        ArrayList oldImports = cs.getImports();
        if (oldImports == null) {
            oldImports = new ArrayList();
        }
        for (Iterator it = newImports.iterator(); it.hasNext();) {
            String imp = (String)it.next();
            if (imp != null && !imp.equals("") && !oldImports.contains(imp)) {
                oldImports.add(imp);
            }
        }
        cs.setImports(oldImports);
    }
    
    
    /**
     * Returns true if the passed import is redundant
     */
    protected boolean isRedundantImport(String imp) {
        // !!!!
        // This is a temporary solution for
        // serial tests : dependency imports are not added.
        if (imp.startsWith("javasoft.sqe.serial")) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks that list of classes provided by library defined
     * via build properties does not conflict with list of library Exports.
     * If case of no confict method just returns.
     * @param libExports list of library Exports
     * @param bpExports  list of classes provided by library defined
     *                   via build properties
     * @exception TestFileException in case of conflict.
     */
    protected void checkConflict(ArrayList libExports, ArrayList bpExports,
            String libID) throws TestFileException {
        StringBuffer conflicts = new StringBuffer();
        for (Iterator it = bpExports.iterator(); it.hasNext();) {
            Object bpImport = it.next();
            if (!libExports.contains(bpImport)) {
                conflicts.append("   ");
                conflicts.append(bpImport);
                conflicts.append("\n");
            }
        }
        if (conflicts.length() > 0) {
            throw new TestFileException("The following classes:\n" + conflicts
                    + " are specified in build properties as provided by"
                    + " library '" + libID + "', but missed in library exports");
            
        }
    }
    
    /**
     * returns true if passed TestGroup is MultiTest test
     */
    protected static boolean isMultiTest(TestGroup tg) {
        String type = LibUtils.getTestType(tg);
        return type == null || type.equals("MultiTest");
    }
    
    /**
     * initializes LibLinker if not initializes yet.
     */
    protected void initialize() throws TestFileException {
        if (isInitialized)
            return;
        isInitialized = true;
        String libmapFileName = getProperty("libmap");
        if (libmapFileName == null) {
            System.err.println("WARNING! libmap file is not specified!");
            lazyMap = null;
        } else {
            try {
                LibFilterFactrory factory = FilterUtil.
                        createLibFilterFactrory("libgen");
                
                LibMapFile libMapFile = factory.createLibMapFile();
                libMapFile.setFileName(libmapFileName);
                libMapFile.read();
                lazyMap = new LazyMap(libMapFile);
            } catch (IOException e) {
                throw new TestFileException("cannot read libmap: "
                        + libmapFileName + ": " + e);
            }
        }
    }
    
    /**
     * Returns true if LibLinker is initialized to retrieve
     * external library IR by libID.
     */
    public boolean canGetExternalLibrary() {
        return lazyMap != null;
    }
    
    /**
     * Returns Library IR by libID, or null if library cannot be
     * retrieved.
     */
    public Library getExternalLibrary(String libID) throws TestFileException {
        if (lazyMap != null) {
            return lazyMap.getExternalLibrary(libID);
        } else {
            return null;
        }
    }
    
    /**
     * Returns list that contains only unique elements of the original list
     */
    protected static ArrayList removeDups(ArrayList list) {
        ArrayList newList = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object obj = it.next();
            if (!newList.contains(obj)) {
                newList.add(obj);
            }
        }
        return newList;
    }
    
    class LazyMap {
        LibParser lib_parser = new LibParser();
        LibMapFile libMapFile;
        HashMap cashedLibs = new HashMap();
        XmlFileNameMap xmlMap = null;
        LibMap libMap = null;
        
        LazyMap(LibMapFile libMapFile) throws TestFileException {
            this.libMapFile = libMapFile;
            if (libMapFile instanceof XmlFileNameMap) {
                xmlMap = (XmlFileNameMap)libMapFile;
            } else {
                LibMap libMap = libMapFile.getLibMap();
                if (libMap == null) {
                    throw new TestFileException("LibMapFile object is not suitable:"
                            + " getLibMap() returns null and XmlFileNameMap "
                            + " interface is not implemented");
                }
            }
        }
        
        Library getExternalLibrary(String libID) throws TestFileException {
            Library lib = (Library)cashedLibs.get(libID);
            if (lib != null)
                return lib;
            
            if (xmlMap != null) {
                String xml = xmlMap.xmlFileName(libID);
                if (xml == null) {
                    throw new TestFileException("Cannot find library: "+libID);
                }
                
                try {
                    lib = lib_parser.parseLibrary(xml);
                } catch (IOException ioe){
                    throw new TestFileException("Cannot parse library: "
                            + libID + ": " + ioe);
                }
            } else {
                lib = libMap.get(libID);
            }
            cashedLibs.put(libID, lib);
            return lib;
        }
    }
    
    class LibParser extends MiddleWareXMLParser {
        
        LibParser() {
            super();
        }
        
        Library parseLibrary(String fileName) throws
                TestFileException, IOException {
            File f = new File(fileName);
            XMLParser parser = getParser(getKey(f));
            parser.setSourceFileMode(false);
            
            try {
                return (Library)parser.parse(f);
            } catch (org.xml.sax.SAXException saxe) {
                saxe.printStackTrace();
                throw new TestFileException("Cannot parse library: file "
                        + fileName);
            } catch (ClassCastException e) {
                throw new TestFileException("Cannot parse library: file "
                        + fileName + " does not contain Library");
            }
        }
    }
}

