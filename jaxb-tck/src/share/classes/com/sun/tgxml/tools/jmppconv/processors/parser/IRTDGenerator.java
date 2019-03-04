/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.documentation.DocumentationFactory;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.impl.RequiredResourceImpl;
import com.sun.tgxml.tjtf.api.attributes.impl.AttrElemImpl;
import com.sun.tgxml.tjtf.api.tests.TestFactory;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.CodeFactory;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.jmpp.lib.JmppLibTest;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.util.IR;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import com.sun.jmpp.JmppException;
import com.sun.jmpp.lib.TDGenerator;
import com.sun.jmpp.JmppLib;
import com.sun.tgxml.tjtf.resources.LibResHandler; 


/**  
 * Class for generation a test description basing on JmppLibTest variable set.
 * The descriptions are generated as IR objects.
 */

public class IRTDGenerator implements TDGenerator {

/**
 * TestGroupDocumentation DocElem's name string "comments".
 */
    public static final String TG_COMMENTS_DOC_ELEM_NAME = "comments";

/**
 * TestGroup AttrElem's name string "OutputDir".
 */
    public static final String TG_ATTR_ELEM_OUTPUTDIR_NAME = "OutputDir";

/**
 * TestGroup AttrElem's name string "testType".
 */
    public static final String TG_ATTR_ELEM_TESTYPE_NAME = "testType";

/**
 * TestGroup "testType" AttrElem's value.
 */
    public static final String TG_ATTR_ELEM_TESTYPE_VALUE = "ExternalTest";

/**
 * TestGroup "scInfo" AttrElem's name.
 */
    public static final String TG_ATTR_ELEM_SCINFO_NAME = "scInfo";


/**
 * The method which is the entry point for test description generation.
 * @param lib JmppLibTest instance, test descriptions are generated
 * basing on JmppLibTest variable set. Each method invocation creates
 * an IR object - TestGroup, and stores it. One can use getRoots() to
 * get stored TestGroups.
 * @exception  (runtime exception) com.sun.jmpp.JmppException if IR object creation fails.
 */
    public void makeTD(JmppLibTest lib){
        try {
            TestRoot ir = createTG(lib);
            storeRoot(ir);
            cleanupTestGroupAttributes(lib);
        } catch (TestFileException tfe) {
            throw new JmppException(tfe);
        }
    }

/**
 * The method returns an array of created and stored TestGroup objects.
 * @return an ArrayList of TestRoot objects which are generated TestGroups.
 */

    public ArrayList getRoots(){
        return irStorage;
    }

/**
 * The method creates TestGroup object - TestGroupDocumentation, TestGroupAttributes,
 * ArrayList of TestCases are created and set for the created TestGroup. JmppLibTest.test
 * variable value is used to set TestGroup ID.
 * @param lib JmppLibTest instance, whose variable set is used to create a TestGroup.
 * @return created TestGroup.
 * @exception  TestFileException if TestGroup object creation fails.
 */
    protected TestGroup createTG(JmppLibTest lib) throws TestFileException {
        TestGroupDocumentation tgDoc = createTGDocumentation(lib);
        TestGroupAttributes tgAttrs = createTGAttributes(lib);
        TestGroup tg = TestFactory.createTestGroup();
        tg.setID(lib.test);
        tg.setTGAttributes(tgAttrs);
        tg.setTGDocumentation(tgDoc);
        ArrayList tgTestCases = createTGTestCases(lib, tg);
        tg.setTestCases(tgTestCases);
        return tg;
    }
   
/**
 * The method creates TestGroupDocumentation object. JmppLibTest.title
 * variable value is used to set TestGroupDocumentation title.
 * JmppLibTest.testComments if not null and not an empty string, is used to set TestGroupDocumentation's 
 * ArrayList containing a single DocElem whose name is "comments" and the value 
 * is JmppLibTest.testComments.
 * @param lib JmppLibTest instance, whose variable set is used to create a TestGroupDocumentation.
 * @return created TestGroupDocumentation.
 * @exception  TestFileException if TestGroupDocumentation object creation fails.
 */
    protected TestGroupDocumentation createTGDocumentation(JmppLibTest lib) throws TestFileException {
        TestGroupDocumentation tgDoc = DocumentationFactory.createTestGroupDocumentation();
        tgDoc.setTitle(lib.title);
        if (lib.testComments != null && !lib.testComments.trim().equals("")){
            ArrayList docElems = new ArrayList();
            docElems.add(DocumentationFactory.createDocElem(TG_COMMENTS_DOC_ELEM_NAME, lib.testComments));
            tgDoc.setDocElems(docElems);
        }
        return tgDoc;
    }

/**
 * The method creates TestGroupAttributes object. 
 * JmppLibTest.properties if not null and not an empty string, is used to set TestGroupAttributes' context.
 * JmppLibTest.executeArgs if not null and not an empty string, is used to set TestGroupAttributes' executeArgs.
 * JmppLibTest.executeClass is used to set TestGroupAttributes' executeClass.
 * JmppLibTest.testTimeout if &gt; 0, is used to set TestGroupAttributes' Timeout.
 * ArrayLists of keywords, TargetSpecs, AttrElems are created and set for the created TestGroupAttributes.
 * @param lib JmppLibTest instance, whose variable set is used to create a TestGroupAttributes.
 * @return created TestGroupAttributes.
 * @exception  TestFileException if TestGroupAttributes object creation fails.
 */
    protected TestGroupAttributes createTGAttributes(JmppLibTest lib) 
            throws TestFileException {

        TestGroupAttributes tgAttrs =
                AttributesFactory.createTestGroupAttributes();
        if (lib.properties != null && !lib.properties.trim().equals("")) {
            tgAttrs.setContext(lib.properties);
        }
        if (lib.executeArgs != null && !lib.executeArgs.trim().equals("")) {
            tgAttrs.setExecuteArgs(lib.executeArgs);
        }
        tgAttrs.setExecuteClass(lib.executeClass);
        if (lib.testTimeout > 0){
            tgAttrs.setTimeout(Integer.toString(lib.testTimeout));
        }
        tgAttrs.setKeywords(createKeywords(lib));
        tgAttrs.setTargetSpecs(createTargetSpecs(lib));

        ArrayList reqResources =
                convertRequiredResources(lib.getTestGroupRequiredResources());
        if (reqResources != null) {
            tgAttrs.setRequiredResources(reqResources);
        }

        ArrayList targetSpecs =
                convertTargetSpecList(lib.getTestGroupTargetSpec());
        if (targetSpecs != null) {
            tgAttrs.setTargetSpecs(targetSpecs);
        }

        ArrayList attrElems = createTGAttrElems(lib);
        ArrayList more_attr_elems = convertAttrElems(lib.getTestGroupAttrElems());
        if (more_attr_elems != null) {
            // need this to retain attr elems created in createTGAttrElems
            attrElems.addAll(more_attr_elems);
        }
        tgAttrs.setAttrElems(attrElems);
        return tgAttrs;
    }

/**
 * The method creates ArrayList of TestGroup's AttrElems.
 * The created ArrayList contains the AttrElem object whose name is "outputDir",
 * the value is the full path to the test - the directory
 * returned by getTestPath(JmppLibTest); and the AttrElem object whose name 
 * is "testType", the value is "ExternalTest".
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of AttrElems.
 * @return created ArrayList of AttrElems.
 * @exception  TestFileException if AttrElem object creation fails.
 */
    protected ArrayList createTGAttrElems(JmppLibTest lib) throws TestFileException {
        ArrayList attrElems = new ArrayList();
        AttrElem outputDir = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_OUTPUTDIR_NAME, getTestPath(lib));
        AttrElem testType = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_TESTYPE_NAME, TG_ATTR_ELEM_TESTYPE_VALUE);
        AttrElem scInfo = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_SCINFO_NAME, lib.sccs);
        AttrElem auto_gen = AttributesFactory.createAttrElem
                (IR.ATTR_ELEM_AUTO_GENERATED, "true");
        attrElems.add(outputDir);
        attrElems.add(testType);
        attrElems.add(scInfo);
        attrElems.add(auto_gen);
        return attrElems;
    }

/**
 * The method creates ArrayList of TestGroup's TestCases.
 * The created ArrayList contains the only TestCase object.
 * TestCaseDocumentation and CodeSet are created and set for the created TestCase.
 * JmppLibTest.test variable value is used to set TestCase ID, the passed as argument TestGroup
 * is used to set TestCase's TestGroup.
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of TestCases.
 * @param tg TestGroup instance, used to set TestCase's TestGroup.
 * @return created ArrayList of TestCases.
 * @exception  TestFileException if TestCase object creation fails.
 */
    protected ArrayList createTGTestCases(JmppLibTest lib, TestGroup tg) 
            throws TestFileException {

        ArrayList tgTestCases = new ArrayList();
        TestCaseDocumentation tcDoc = createTCDocumentation(lib);
        CodeSet tcCodeSet = createTCCodeSet(lib);

        ArrayList specs = convertTargetSpecList(lib.getTestcaseTargetSpec());
        ArrayList req_r = convertRequiredResources(lib.getTestcaseRequiredResources());
        ArrayList attrs = convertAttrElems(lib.getTestcaseAttrElems());

        TestCaseAttributes tcAttrs = AttributesFactory.createTestCaseAttributes();

            if (specs != null) {
                tcAttrs.setTargetSpecs(specs);
            }
            if (req_r != null) {
                tcAttrs.setRequiredResources(req_r);
            }
            if (attrs != null) {
                tcAttrs.setAttrElems(attrs);
            }
        IR.setAutoGeneratedAttrElem(tcAttrs, "true");
        TestCase tgTC = TestFactory.createTestCase(lib.test, tcDoc, tcAttrs, 
                tcCodeSet, null, tg);
        tgTestCases.add(tgTC);
        cleanupTestCaseAttributes(lib);
        return tgTestCases;
    }

/**
 * The method creates TestCaseDocumentation object.
 * TestCaseDocumentation and CodeSet are created and set for the created TestCase.
 * JmppLibTest.title variable value is used to set TestCaseDocumentation title.
 * JmppLibTest.description is used to set description.
 * Created ArrayList of authors is used to set Authors.
 * @param lib JmppLibTest instance, whose variable set is used to create TestCaseDocumentation.
 * @return created TestCaseDocumentation object.
 * @exception  TestFileException if TestCaseDocumentation object creation fails.
 */
    protected TestCaseDocumentation createTCDocumentation(JmppLibTest lib) throws TestFileException {
        TestCaseDocumentation tcDoc = DocumentationFactory.createTestCaseDocumentation();
        tcDoc.setTitle(lib.title);
        tcDoc.setDescription(lib.description);
        tcDoc.setAuthors(createAuthors(lib));
        return tcDoc;
    }

/**
 * The method creates TestCase's CodeSet object.
 * Created ArrayList of ExternalSupportClasses is used to set SupportClasses.
 * Created ArrayList of Dependencies is used to set Dependencies.
 * @param lib JmppLibTest instance, whose variable set is used to create CodeSet.
 * @return created CodeSet object.
 * @exception  TestFileException if CodeSet object creation fails.
 */
    protected CodeSet createTCCodeSet(JmppLibTest lib) throws TestFileException {
        CodeSet tcCodeSet = CodeFactory.createCodeSet();
        tcCodeSet.setSupportClasses(createExternalSupportClasses(lib));
        tcCodeSet.setDependencies(createDependencies(lib));
        return tcCodeSet;
    }

/**
 * The method creates ArrayList of ExternalSupportClasses objects.
 * JmppLibTest.sourceFiles or JmppLibTest.sourceList list of source files is used
 * to create ArrayList of ExternalSupportClass objects. For each source in the list
 * ExternalSupportClass object is created, its source's extension is ExternalSupportClass's SourceLang.
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of ExternalSupportClass.
 * @return created ArrayList object.
 * @exception  TestFileException if ExternalSupportClass object creation fails.
 */
    protected ArrayList createExternalSupportClasses(JmppLibTest lib) throws TestFileException {
        String sources[] = new String[lib.sourceFiles.size()];
        if (lib.sourceList!=null) {
            sources=lib.sourceList;
            lib.sortFileArrayAccordingToSuppliedByTemplateSourceList();
        } else {
            lib.sourceFiles.copyInto(sources);
        }
        ArrayList externalClasses = new ArrayList();
        for (int i = 0; i < sources.length; ++i){
            ExternalSupportClass externalClass = CodeFactory.createExternalSupportClass();
            externalClass.setSourceName(sources[i]);
            int extensionIndex = sources[i].lastIndexOf(".");
            String extension = extensionIndex >= 0 ? sources[i].substring(extensionIndex + 1):"java";
            externalClass.setSourceLang(extension);
            externalClasses.add(externalClass);
        }
        return externalClasses;
    }
    
/**
 * The method creates ArrayList of LibraryDependency objects.
 * JmppLibTest.dependsOn variable value is used to create LibraryDependency objects.
 * JmppLibTest.dependsOn contains blank-space-separated list of library IDs.
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of LibraryDependency objects.
 * @return created ArrayList of LibraryDependency object.
 * @exception  TestFileException if LibraryDependency object creation fails.
 */
    protected ArrayList createDependencies(JmppLibTest lib) throws TestFileException {
        if (lib.dependsOn == null || lib.dependsOn.trim().length() == 0){
            return null;
        }
        ArrayList dependencies = new ArrayList();
        StringTokenizer st = new StringTokenizer(lib.dependsOn);
        while (st.hasMoreTokens()){
            dependencies.add(CodeFactory.createLibraryDependency(st.nextToken()));
        }
        return dependencies;
    }

/**
 * The method creates ArrayList of String objects - test authors.
 * JmppLibTest.testDeveloper variable value is used.
 * It contains a comma-separated list of test authors.
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of Strings.
 * @return created ArrayList of String objects - test authors.
 */
    protected ArrayList createAuthors(JmppLibTest lib){
        ArrayList authors = new ArrayList();
        if (lib.testDeveloper != null && lib.testDeveloper.trim().length() != 0){
            StringTokenizer st = new StringTokenizer(lib.testDeveloper, ",");
            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if (!token.equals("")){
                    authors.add(token);
                }
            }
        }
        return authors;
    }

/**
 * The method creates ArrayList of TargetSpec objects.
 * JmppLibTest.targetSpec variable value is used.
 * It contains a comma-separated list of specs which the test complies to, for example:
 * <pre> 
 * "JDK:-1.3, J2ME:1.4-"
 * </pre>
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of TargetSpec objects.
 * @return created ArrayList of TargetSpec objects.
 */
    protected ArrayList createTargetSpecs(JmppLibTest lib) throws TestFileException {
        ArrayList targetSpecs = new ArrayList();
        if (lib.targetSpec == null || lib.targetSpec.trim().length() == 0){
            return targetSpecs;
        }
        // lib.targetSpec should be "ID:version, ID:version,..."
        StringTokenizer st = new StringTokenizer(lib.targetSpec, ",");
        while (st.hasMoreTokens()) {
            // targetSpecEntry should be ID:version
            String targetSpecEntry = st.nextToken().trim();
            StringTokenizer stcolumn = new StringTokenizer(targetSpecEntry, ":");
            if (stcolumn.countTokens() != 2){
                throw new TestFileException(LibResHandler.getResStr("jmppconv.error.targetspec", targetSpecEntry));
            }
            targetSpecs.add(AttributesFactory.createTargetSpec(stcolumn.nextToken(), stcolumn.nextToken()));
        }
        return targetSpecs;
    }
    
/**
 * The method creates ArrayList of String objects - test keywords.
 * JmppLibTest.keywords variable value is used.
 * It contains a blank-space-separated list of test keywords.
 * @param lib JmppLibTest instance, whose variable set is used to create ArrayList of Strings.
 * @return created ArrayList of String objects - test keywords.
 */
    protected ArrayList createKeywords(JmppLibTest lib){
        ArrayList keywords = new ArrayList();
        if (lib.keywords != null && lib.keywords.trim().length() != 0){
            StringTokenizer st = new StringTokenizer(lib.keywords);
            while (st.hasMoreTokens()) {
                keywords.add(st.nextToken());
            }
        }
        return keywords;
    }


    /** 
     * Converts a list of attributes each denoted by &lt;name, value&gt; pair into
     * a list of AttrElem objects
     *
     * @param elems input list of pairs
     * @return the list of constructed AttrElem objects
     */
    public static ArrayList convertAttrElems(ArrayList elems) {
        if (elems == null || elems.size() < 1) {
            return null;
        }
        ArrayList list = new ArrayList();

        for (int i = 0; i < elems.size(); i++) {
            JmppLib.Pair p = (JmppLib.Pair)elems.get(i);
            try {
                 list.add(new AttrElemImpl(p.getName(), p.getValue()));
            } catch (TestFileException e) {
                // should never occur, but who knows?..
                throw new IllegalArgumentException(
                    "cannot create AttrElemImpl from " + 
                    p.getName() + ", " + p.getValue() + " because of: " + e);
            }
        }
        return list;
    }

    /** 
     * converts enumeration of "resourceName", "resourceValue" pairs into
     * list of RequiredResource
     */
    public static ArrayList convertRequiredResources(Enumeration en) {
        if (en == null || !en.hasMoreElements())
            return null;

        
        ArrayList list = new ArrayList();

        while(en.hasMoreElements()) {
            JmppLib.Pair p = (JmppLib.Pair)(en.nextElement());
            try {
                list.add(new RequiredResourceImpl(p.getName(), p.getValue()));
            } catch (TestFileException e) {
                // should never occur
            }
        }
        return list;
    }


    /** 
     * converts list of com.sun.jmpp.lib.TargetSpec into
     * list of com.sun.tgxml.tjtf.api.attributes.TargetSpec
     */
    public static ArrayList convertTargetSpecList(ArrayList list) {
        if (list == null || list.size() == 0)
            return null;

        ArrayList newList = new ArrayList(list.size());

        com.sun.jmpp.lib.TargetSpec  jmppTS = null;
        com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecImpl tjtfTS = null;

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            jmppTS = (com.sun.jmpp.lib.TargetSpec)it.next();
            tjtfTS = new com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecImpl(
                 jmppTS.getId(), jmppTS.getVersion());

            ArrayList specelems = jmppTS.getTargetSpecElems();
            if (specelems != null && specelems.size() > 0) {
                tjtfTS.setTargetSpecElems(
                        convertTargetSpecElemList(specelems));
            }

            newList.add(tjtfTS);
        }
        return newList;
    }

    /** 
     * converts list of com.sun.jmpp.lib.TargetSpecElem into
     * list of com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
     */
    public static ArrayList convertTargetSpecElemList(ArrayList list) {
        if (list == null || list.size() == 0)
            return null;

        ArrayList newList = new ArrayList(list.size());

        com.sun.jmpp.lib.TargetSpecElem jmppTSE = null;
        com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecElemImpl tjtfTSE =null;

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            jmppTSE = (com.sun.jmpp.lib.TargetSpecElem)it.next();
            try { 
                tjtfTSE = new 
                    com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecElemImpl(
                    jmppTSE.getName(), jmppTSE.getValue());
            } catch (TestFileException e) {
                throw new IllegalArgumentException(e + ": Cannot create: " +
                   "com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecElemImpl");
            }
            newList.add(tjtfTSE);
        }
        return newList;

    }


/**
 * The method returns the full path to the test, i.e. "-o directory" + "test_name".
 * JmppLibTest.test variable value is used.
 * @param lib JmppLibTest instance, whose variable set is used to construct the path to the test.
 * @return String - full path to the test.
 */
    protected String getTestPath(JmppLibTest lib){
        String dir = lib.getOutputDir();
        if (lib.createDirs && lib.test != null && lib.test.trim().length() > 0){
            dir += File.separator + lib.test;
        }
        return dir;
    }

/**
 * The method stores the given TestRoot object.
 * @param ir a TestRoot object to store.
 */
    protected void storeRoot(TestRoot ir){
         irStorage.add(ir);
    }

/**
 * ArrayList of TestRoot objects created during tests generation.
 */
    private ArrayList irStorage = new ArrayList();



    private static String sourceRootDir = "";
    private static int sourceRootDirLen = 0;
    private static final String DEFAULT_TEMPLATE_PACKAGE = 
            "com.sun.tgxml.tools.jmppconv.processors.parser";

    static {
        String rootPath = BuildProperties.getString("tck.source.dir");
        if (rootPath != null && !rootPath.equals("")) {
            File f = new File(rootPath);
            try {
                rootPath = f.getCanonicalPath();
                sourceRootDir = rootPath;
                sourceRootDirLen = sourceRootDir.length();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Calculates templatePackage name for the input jmpp file.
     * If absolute path of the input file starts with "tck.source.dir"
     * build property value, then relative path translated into valid package
     * name added to DEFAULT_TEMPLATE_PACKAGE will be returned,
     * otherwise just DEFAULT_TEMPLATE_PACKAGE will be returned.
     */
    public static String getTemplatePackageName(File inputFile) {
        StringBuffer pack = new StringBuffer(DEFAULT_TEMPLATE_PACKAGE);

        String path = null;
        try {
            path = new File(inputFile.getCanonicalPath()).getParent();
        } catch (IOException e) {
        }
        if(path != null && path.startsWith(sourceRootDir)) {
            String relPath = path.substring(sourceRootDirLen);
            for (int i = 0; i < relPath.length(); i++) {
                char c = relPath.charAt(i);
                if (Character.isJavaIdentifierPart(c)) {
                    pack.append(c);
                } else if (c == File.separatorChar) {
                    pack.append("._");
                } else {
                    pack.append("_");
                }
                
            }
            pack.append("._" + Integer.toHexString(relPath.hashCode()));
        } else {
        }
        return pack.toString();
    }

    /**
     * Clears all attribute data accumulated by the underlying
     * Jmpp library for current test case.
     */
    protected void cleanupTestCaseAttributes(JmppLibTest lib) {
        lib.cleanTestcaseTargetSpecs();
        lib.cleanTestcaseRequiredResource();
        lib.cleanTestcaseAttrElems();
        if (lib instanceof com.sun.jmpp.lib.JmppLibVM) {
            ((com.sun.jmpp.lib.JmppLibVM)lib).clearTemplateAttributes();
        }
    }

    /**
     * Clears all attribute data accumulated by the underlying
     * Jmpp library for current test group.
     */
    protected void cleanupTestGroupAttributes(JmppLibTest lib) {
        lib.cleanTestGroupTargetSpecs();
        lib.cleanTestGroupRequiredResource();
        lib.cleanTestGroupAttrElems();
        if (lib instanceof com.sun.jmpp.lib.JmppLibVM) {
            ((com.sun.jmpp.lib.JmppLibVM)lib).clearTemplateAttributes();
        }
    }
}
