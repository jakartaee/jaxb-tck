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

package com.sun.tgxml.tools.testgen;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileInputStream;

import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Properties;

import com.sun.tgxml.util.IR;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.processors.parser.IRParser;

import com.sun.tgxml.tools.filter.FilterUtil;
import com.sun.tgxml.tools.filter.libutil.LibMap;
import com.sun.tgxml.tools.filter.libutil.LibMapFile;
import com.sun.tgxml.tools.filter.libutil.XmlFileNameMap;
import com.sun.tgxml.tools.filter.libutil.LibFilterFactrory;

import com.sun.tgxml.tools.dependence.LibIDList;
import com.sun.tgxml.tools.dependence.LibraryDependencies;
import com.sun.tgxml.tools.dependence.processors.DependenceAnalyzer;

import com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser;

/**
 * Wrapper tool including TestGen and DependencyAnalyzerTool.
 */

public class ExternalLibGen extends TestGen {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "ExternalLibGen";
    

    String reqLibsFile = null;
    ArrayList requiredLibIDs = null;

    String libmapFileName = null;
    String deptreeFileName = null;
    String changeRuleFileName = null;
    Changer relPathChanger;

    String tckRootDir = null;

    PrintStream out = null;
    /*
     * =========================================================================
     *    Methods
     * =========================================================================
     */

    
    /**
     * Program entry
     *
     * This replaces the main in ToolBase.
     * @param args The command line arguments to  this tool.
     */
    public static void main(String args[]) {
			//System.err.println(">>>> Start main()");
        ExternalLibGen c = new ExternalLibGen(System.out, System.err);
        System.exit(c.run(args));
    }
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public ExternalLibGen( PrintStream out, PrintStream err) {
	super(out, err);
        setProgramName(CtStr_ToolName);
			//System.err.println(">>>> super ctor ends");
	
    }
    
       
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */


    /**
     * specifies the name of libmap file to generate
     */
    protected StringOption libMapOption = new StringOption(
        "-libmap",
        "  -libmap <filename> name of libmap file for filtered libraries",
        OBLIGATORY);


    /**
     * specifies the file containing list of libID required for tests
     */
    protected StringOption reqLibsOption = new StringOption(
        "-libs",
        "  -libs <filename> libID list of libraries required for tests"
                + "  (obligatory)",
        OBLIGATORY);

    /**
     * specifies the name of lib dependencies tree file to generate.
     * If not specified such file will not generated.
     */
    protected StringOption libDepTreeOption = new StringOption(
        "-libdeptree",
        "  -libdeptree <filename> name of lib dependencies tree file",
        OBLIGATORY);

    /**
     * specifies list of lib.xml files in all repositories,
     * if not specified the tool will look up for them.
     */
    protected StringOption changeRuleOption = new StringOption(
        "-changeRule",
        "  -changeRule <ruleFile> file that contains relative path " + 
                "change rules"
        );

    /**
     * specifies list of lib.xml files in all repositories,
     * if not specified the tool will look up for them.
     */
    protected StringOption outputOption = new StringOption(
        "-o",
        "  -o <tckrootdir> output dir (should be tck root dir)"
        );


    /**
     * Registers options
     */
    public void registerOptions() {         
        super.registerOptions();
        removeOption(elOption);
        addOption(libMapOption);
        addOption(reqLibsOption);
        addOption(libDepTreeOption);
        addOption(changeRuleOption);
        addOption(outputOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        libmapFileName = libMapOption.getStringValue();
        reqLibsFile = reqLibsOption.getStringValue();
        deptreeFileName = libDepTreeOption.getStringValue();


        if (changeRuleOption.isSet()) {
            changeRuleFileName = changeRuleOption.getStringValue();
        } else {
            changeRuleFileName = null;
        }

        if (outputOption.isSet()) {
            tckRootDir = outputOption.getStringValue();
            if (!tckRootDir.endsWith(File.separator)) {
                tckRootDir += File.separator;
            }
        } else {
            tckRootDir = "." + File.separator;
        }

        super.applyOptionsValues();
    }

    /**
     * Unsets OperandValidator (no operads required for this tool)
     */
    protected void setOperandsValidator() {
    }

    /**
     * Sets ExternalLibGen usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = 
            "Usage: " + getProgramName() + " [<options>] \n" + 
            "where options include:";
    }

   /* 
    * ----------------------------------------------------------------------
    *    
    * ----------------------------------------------------------------------
    */


    /** 
     *  Sets out field.
     */
    public void processArgs() throws TestFileException, IOException {
        out = getStandardOut();
        if (out == null)
            out = System.out;

        if (changeRuleFileName != null) {
            relPathChanger = new Changer(changeRuleFileName);
        }
    }

    /**
     * Provides dependency analysis
     * Returns list of libID to generate
     */
    protected LibIDList analyze(String dependenciesFileName,
            String libraryListFile) throws TestFileException, IOException {

        LibraryDependencies libraryDependencies = new LibraryDependencies();
        libraryDependencies.read(new FileInputStream(dependenciesFileName));

        LibIDList libIDList = new LibIDList();
        libIDList.read(new FileInputStream(libraryListFile));

        DependenceAnalyzer dependenceAnalyzer = 
            new DependenceAnalyzer(getStandardErr());

        return dependenceAnalyzer.analyze(libIDList, libraryDependencies);
    }

    /**
     * Returns array of lib.xml files for libraries from libIDList.
     * @param libIDList  required libID list
     * @param map        mapping: libID <--> file name
     * @return   File array of the same size as libIDList, or an empty
     *            array if libIDList is null
     * @exception TestFileException if some libID does not maps to 
     *            existing .lib.xml file
     */
    protected File[] libXmlFiles(LibIDList libIDList, XmlFileNameMap map)
            throws TestFileException {

        if (libIDList == null)
            return new File[0];

        StringBuffer badFiles = new StringBuffer();
        File[] xmls = new File[libIDList.size()];
        for (int i = 0; i < xmls.length; i++) {
             String libID = libIDList.getString(i);
             String fileName = map.xmlFileName(libID);
             if (fileName == null) {                 
                 badFiles.append("   " + libID 
                         + ": cannot find .lib.xml file name for this libID\n");
             } else {
                 xmls[i] = new File(fileName);
                 if (!xmls[i].isFile()) {
                     badFiles.append("    " + xmls[i] + " does not exist\n");
                 } else if (!fileName.endsWith(".lib.xml")) {
                     badFiles.append("    " + xmls[i] + " has a bad suffix\n");
                 }
             }
        }

        if (badFiles.length() == 0) {
            return xmls;
        } else {
            throw new TestFileException(
                "The following errors found:\n" + badFiles);
        }
    }



    /** 
     * Parses lib.xml files to Library[].
     * @throws TestFileException if one xml file cannot be parsed or not 
     *         a Library.
     */
    protected Library[] parseXMLs(File[] libXmls) throws TestFileException {
        IRObj[] irs;
        try {
            irs = m_parser.parse(libXmls);
        } catch (IOException ioe){
            throw new TestFileException("Cannot parse libraries: " + ioe);
        }
        Library[] allLibs = new Library[irs.length];
        for (int i = 0; i < allLibs.length; i++) {
            try {
                allLibs[i] = (Library)irs[i];
            } catch (ClassCastException e) {
                throw new TestFileException(libXmls[i] + " is not a library");
            }
        }
        return allLibs;
    }

    /** 
     * Returns prepared for generation Library array.
     * @param libIDList   required libID list
     * @param libMapFile  mapping: libID <--> file name | Library
     *        if libMapFile is an instance of XmlFileNameMap then 
     *        own parser will be used.
     *        Otherwise, libMapFile.getLibMap() should return a map,
     *        that provides an Library IR by libID.
     * @throws TestFileException if impossible to get all required library IRs.
     */
    protected Library[] libsToGenerate(LibIDList libIDList,  
            LibMapFile libMapFile) throws TestFileException, IOException {

        Library[] libs = null;
        if (libMapFile instanceof XmlFileNameMap) {
            XmlFileNameMap map = (XmlFileNameMap)libMapFile;
            libs = parseXMLs(libXmlFiles(libIDList, map));
        } else {
            LibMap libMap = libMapFile.getLibMap();
            if (libMap == null) {
                throw new TestFileException("LibMapFile object is not suitable:"
                        + " getLibMap() returns null and XmlFileNameMap " 
                        + " interface is not implemented");
            }

            libs = new Library[libIDList.size()];
            for (int i = 0; i < libs.length; i++) {
                 libs[i] = libMap.get(libIDList.getString(i));
            }
        }
        // skip external inline libraries
        ArrayList toGen = new ArrayList(libs.length);
        for (int i = 0; i < libs.length; i++) {
            if (!libs[i].isInline()) {
                 toGen.add(libs[i]);
            }
        }
        return (Library[])(toGen.toArray(new Library[0]));
    }

    /** 
     * Provides library cross dependency analysis.
     * Generates all required libraries.
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void executeTool() throws TestFileException, IOException {

        // create list of all required libID
        LibIDList libIDList = analyze(deptreeFileName, reqLibsFile);
        if (libIDList == null || libIDList.size() == 0) {
            return; // nothing to generate
        }

        // create LibMapFile object
        LibFilterFactrory factory = FilterUtil.
                createLibFilterFactrory("libgen");

        LibMapFile libMapFile = factory.createLibMapFile();
        libMapFile.setFileName(libmapFileName);
        libMapFile.read();

        Library[] toGenerate = libsToGenerate(libIDList, libMapFile);

        out.println("Generating: " + toGenerate.length + " libraries");
        setLibMapFile(libmapFileName);
        m_generator.generate(toGenerate);
    }

    /** 
     * Overrides TestGen method.
     */
    public IRParser createParser() throws TestFileException {
	return new LibMiddleWareXMLParser();
    }

    /**
     * Returns output directory for the passed Library.
     * It detects the relative path of srcFile within the repository.
     * The output dir for this file will be calculated as 
     * "tck root dir" + "relative path".
     * This method is called from parser to set OutputDir AttrElem
     */
    protected String getLibOutputDir(Library lib) {
        String relativePath = IR.getAttrElem(IR.relSourcePathAttrElemName,lib);

        if (relativePath != null) {
            if (relPathChanger != null) {
                relativePath = relPathChanger.change(relativePath);
            }
            return tckRootDir + new File(relativePath).getParent();
        } else {
            return tckRootDir;
        }
    }

    public String getGenType() {
    	return "libgen";
    }
    
    class LibMiddleWareXMLParser extends MiddleWareXMLParser {

        public LibMiddleWareXMLParser() {
            super();
        }

        // no options required
        public void registerOptions() {
        }

        // no options required
        public void applyOptionsValues() throws ParseArgumentException {	   
        }

        protected void setOutputDir(IRObj tree, File srcFile) {
            if (tree instanceof Library) {
                setOutputDir((Library)tree, getLibOutputDir((Library)tree)); 
            }
        }
    }
}

class Changer {

    Properties rules = null;
    String[] pattern = null;
    int count = 0;

    public Changer(String ruleFile) throws IOException {
        rules = new Properties();
        rules.load(new FileInputStream(new File(ruleFile)));
        pattern = (String[])rules.keySet().toArray(new String[0]);
        java.util.Arrays.sort(pattern);
        count = pattern.length;
    }


    public String change(String str) {
        if (str == null) 
            return null;
        for (int i = count - 1; i >= 0; i--) {
            String p = pattern[i];
            if (str.startsWith(p)) {
                 return rules.getProperty(p) + str.substring(p.length());
            }
        }
        return str; // does not match any pattern
    }
}
