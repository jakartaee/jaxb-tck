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

package com.sun.tgxml.tools.filter;

import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.Iterator;
import java.util.ArrayList;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.tools.XMLValToolBase;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;

import com.sun.tgxml.tools.dependence.LibraryDependency;
import com.sun.tgxml.tools.dependence.LibraryDependencies;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.FilteringException;

import com.sun.tgxml.tools.filter.libutil.LibMap;
import com.sun.tgxml.tools.filter.libutil.LibMapFile;
import com.sun.tgxml.tools.filter.libutil.VariantsMap;
import com.sun.tgxml.tools.filter.libutil.LibFilterFactrory;
import com.sun.tgxml.tools.filter.libutil.LibAttributesFilter;
import com.sun.tgxml.tools.filter.libutil.LibDependencyFilter;

/**
 * Provides filtering of whole bundle of external libraries.
 * It gets list of lib.xml files to filter, filter plugin class name
 * and output directory for storage intermediate xml files.
 * It provides attributes filtering and filtering by dependencies,
 * emits selected and cleaned Libraries in UTD XML format into
 * output directory, creates libMap file (containing information
 * about accepted/rejected libraries), creates libDepTree file
 * (in DependencyAnalyzer format) if needed.
 * <p>
 * This tool is a wrapper tool. It does the following:
 * <ul>
 * <li>parses command line options</li>
 * <li>parses all passed Libraries (default XML parser inherited from 
 *     XMLValToolBase is used)</li>
 * <li>creates an instance of LibFilterFactrory, that provides 
 *     LibAttributesFilter, LibDependencyFilter, LibMapFile implementations.
 *     The LibFilterFactrory class name can be specified via 
 *     "libfilter.LibFilterFactrory" build property. If not specified
 *     "com.sun.tgxml.tools.filter.libutil.DefaultLibFilterFactrory" 
 *     will be used<li> 
 * <li>sequentially applies LibAttributesFilter and LibDependencyFilter</li>
 * <li>applies LibMapFile to write resulting map file</li>
 * <li>emits selected and cleaned Libraries into output directory
 *     (default XML emitter inherited from XMLValToolBase is used)</li>
 * <li>creates libDepTree file, if needed</li>
 * </ul>
 *
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */
public class ExternalLibFilter extends XMLValToolBase {

    protected String pluginName;
    protected String libXmlListFile;
    protected String libmapFileName;
    protected String outputDir;
    protected String deptreeFileName;

    public ExternalLibFilter(PrintStream out, PrintStream err) {
	super(out, err, "ExternalLibFilter");
    }

    public static void main(String args[]) {
        ExternalLibFilter tool = new ExternalLibFilter(System.out, System.err);
        System.exit(tool.run(args));
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    /**
     * specifies library filter plugin class name
     */
    protected StringOption pluginOption = new StringOption(
        "-plugin",
        "  -plugin <pluginname>   plugin name (obligatory)",
        OBLIGATORY);


    /**
     * specifies list of lib.xml files in all repositories.
     */
    protected StringOption libXmlListOption = new StringOption(
        "-libxmllist",
        "  -libxmllist <filename> list of lib.xml files in all repositories",
        OBLIGATORY);

    /**
     * specifies the name of libmap file to generate
     */
    protected StringOption libMapOption = new StringOption(
        "-libmap",
        "  -libmap <filename> name of libmap file for filtered libraries",
        OBLIGATORY);

    /**
     * specifies the name of lib dependencies tree file to generate.
     */
    protected StringOption libDepTreeOption = new StringOption(
        "-libdeptree",
        "  -libdeptree <filename> name of lib dependencies tree file",
        OBLIGATORY);


    /**
     * specifies the directory name for storage intermediate 
     * lib.xml files for filtered libs.
     */
    protected StringOption outputOption = new StringOption(
        "-o",
        "  -o <filtereddir> output dir for storage filtered libs"
        );


    /**
     * Registers options
     */
    public void registerOptions() {         
        super.registerOptions();
        removeOption(fileOption);

        addOption(pluginOption);
        addOption(libXmlListOption);
        addOption(libMapOption);
        addOption(libDepTreeOption);
        addOption(outputOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        pluginName = pluginOption.getStringValue();
        libXmlListFile = libXmlListOption.getStringValue();
        libmapFileName = libMapOption.getStringValue();

        if (outputOption.isSet()) {
            outputDir = outputOption.getStringValue();
            if (!outputDir.endsWith(File.separator)) {
                outputDir += File.separator;
            }
        } else {
            outputDir = "." + File.separator;
        }

        if (libDepTreeOption.isSet()) {
            deptreeFileName = libDepTreeOption.getStringValue();
        } else {
            deptreeFileName = null;
        }


        super.applyOptionsValues();
    }

    /**
     * Unsets OperandValidator (no operads required for this tool)
     */
    protected void setOperandsValidator() {
    }

   /* 
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */


    protected String   tckSourceDir = null;
    protected String[] repositories = null;
    protected String[] relativePaths = null;
    /**
     * Parses libXmlListFile file that contains list of .lib.xml files.
     * Returns array list of xml file names from the file
     * @throws TestFileException when one of lib.xml file either not found 
     *         or have not .lib.xml suffix
     * @throws IOException if there is some type of IO problem with reading
     *         libXmlListFile
     */
    protected File[] libXmlFromFile(String libXmlListFile)
             throws IOException, TestFileException {

        tckSourceDir =  BuildProperties.getString("tck.source.dir");
        if (tckSourceDir != null && tckSourceDir.trim().length() == 0) {
            tckSourceDir = null; // tck.source.dir is not set
        }

        ArrayList names = MiscUtils.parseTextFile(libXmlListFile, true);

        if (tckSourceDir == null) {
            // possible multiple repository
            repositories = new String[names.size()];
            relativePaths = new String[names.size()];
            return multiReposFiles(names);
        } else {
            return singleReposFiles(names, tckSourceDir);
        }
    }

    protected File[] multiReposFiles(ArrayList names)
            throws IOException, TestFileException {

        File[] xmls = new File[names.size()];
        StringBuffer badFiles = new StringBuffer();
        for (int i = 0; i < xmls.length; i++) {
            String name = (String)names.get(i);
            int index = name.lastIndexOf(':');
            if (index > 0) {
                repositories[i] = name.substring(0, index);
                relativePaths[i] = name.substring(index+1);
                xmls[i] = new File(repositories[i] + File.separator +
                        relativePaths[i]);
                if (!xmls[i].isFile()
                        || !name.endsWith(".lib.xml")) {
                    badFiles.append("    " + xmls[i] + "\n");
                }
            } else {
                badFiles.append("    " + name + "\n");
            }
        }
        if (badFiles.length() == 0) 
            return xmls;
        else
            throw new TestFileException("The following files:\n" + badFiles + 
                "either not found " +
                "or have not .lib.xml suffix " +
                "or do not belong to any repository");
    }

    protected File[] singleReposFiles(ArrayList names, String reposName)
            throws IOException, TestFileException {

        File[] xmls = new File[names.size()];
        StringBuffer badFiles = new StringBuffer();
        for (int i = 0; i < xmls.length; i++) {
            String name = (String)names.get(i);
            int index = name.lastIndexOf(':');
            if (index > 0) {
                String rep = name.substring(0, index);           
                String relPath = name.substring(index+1);
                if (!reposName.equals(rep)) {
                    badFiles.append("    " + name + "\n");
                    continue;
                }
                xmls[i] = new File(rep + File.separator + relPath);
            } else {
                xmls[i] = new File(name);
            }
            if (!xmls[i].isFile() || !name.endsWith(".lib.xml")) {
                badFiles.append("    " + xmls[i] + "\n");
            }
        }
        if (badFiles.length() == 0) 
            return xmls;
        else
            throw new TestFileException("The following files:\n" + badFiles + 
                "either not found " +
                "or have not .lib.xml suffix " +
                "or do not belong to the specified repository: " + reposName);
    }

    /** 
     * Parses lib.xml files to Library[].
     * @throws TestFileException if xml file cannot be parsed or not a Library.
     * @throws IOException if there is an IO problem.
     */
    protected Library[] parseXMLs(String libXmlListFile) 
            throws TestFileException, IOException {

        IRObj[] irs;
        File[] libXmls = libXmlFromFile(libXmlListFile);
        irs = m_parser.parse(libXmls);
        Library[] allLibs = new Library[irs.length];
        for (int i = 0; i < allLibs.length; i++) {
            try {
                allLibs[i] = (Library)irs[i];
                if (tckSourceDir == null) {
                    // parser does not set AttrElems
                    IR.setAttrElem(allLibs[i], IR.SourcePathAttrElemName, 
                        repositories[i] + File.separator + relativePaths[i]);
                    IR.setAttrElem(allLibs[i], IR.relSourcePathAttrElemName,
                        relativePaths[i]);
                }
            } catch (ClassCastException e) {
                throw new TestFileException(libXmls[i] + " is not a library");
            }
        }
        return allLibs;
    }

    /** 
     * Emits filtered Libraries. The name of generated xml file are calculated
     * as outputDir + "relSourcePath".
     * @throws TestFileException Library cannot be emitted
     * @throws IOException if there is an IO problem.
     */
    protected void emitXMLs(LibMap libMap)
            throws TestFileException, IOException {

        for (Iterator it = libMap.accepted().iterator(); it.hasNext();) {
            String libID = (String)it.next();
            Library lib = libMap.get(libID);
            emitXML(lib);
        }
         
    }

    /** 
     * Emits single Library
     */
    protected void emitXML(Library lib) throws TestFileException, IOException {
        String relPath = IR.getAttrElem(IR.relSourcePathAttrElemName, lib);
        if (relPath == null) {
            throw new TestFileException("Cannot get relativePath for: " + 
                    IR.getID(lib));
        }
        String xmlFileName = outputDir + relPath;
        OutputStream output = MiscUtils.createOutputStream(xmlFileName);
        ((XMLEmitter)m_emitter).emit(lib, output);
        output.close();
    }


    /** 
     * Creates file with library dependency tree in format of
     * DependencyAnalyzerTool
     */
    protected void createLibDepTree(LibMap libMap, String deptreeFileName)
            throws TestFileException, IOException {

        LibraryDependencies depTree = new LibraryDependencies();
        for (Iterator it = libMap.accepted().iterator(); it.hasNext();) {
            String libID = (String)it.next();
            Library lib = libMap.get(libID);
            ArrayList depList = IR.getDependentLibs(lib);
            if (depList.size() > 0) {
                 LibraryDependency dep = new LibraryDependency(libID);
                 for (Iterator it2 = depList.iterator(); it2.hasNext();) {
                     dep.addString((String)it2.next());
                 }
                 depTree.put(dep);
            }
        }

        OutputStream output = MiscUtils.createOutputStream(deptreeFileName);
        depTree.write(output);

    }

   /* 
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */


    /** 
     * Selects and parses input files, filter the Library array, 
     * and emit the cleaned tree.
     * 
     * @throws TestFileException If there is a problem with the IR parse tree.
     * @throws IOException if there is an IO problem.
     */
    public void executeTool() throws TestFileException, IOException {

        // create factory
        LibFilterFactrory factory = FilterUtil.
                createLibFilterFactrory("libfilter");


        // parse files
        Library[] libs = parseXMLs(libXmlListFile);

        // filter libraries
        LibMap libMap = null;
        try {            
            libMap = FilterUtil.filterLibBundle(libs, pluginName, factory,null);
        } catch (FilteringException fe) {
            throw new TestFileException(
                "Libraries filtering failed: " + fe);
        }

        // write libmap file
        LibMapFile libMapFile = factory.createLibMapFile();
        libMapFile.setLibMap(libMap);
        libMapFile.setLibDir(outputDir);
        libMapFile.setFileName(libmapFileName);
        libMapFile.write();

        
        // emit filtered libs
        emitXMLs(libMap);

        // create library dependecy tree file (for DepenecyAnalyzerTool)
        // if needed
        if (deptreeFileName != null)
            createLibDepTree(libMap, deptreeFileName);

    }


}
