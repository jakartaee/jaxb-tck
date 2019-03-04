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

package com.sun.tgxml.tools.dependence;

import java.io.PrintStream;
import java.io.FileInputStream;
import java.util.TreeSet;
import java.util.Stack;
import java.util.Iterator;

import com.sun.tgxml.tools.dependence.processors.DependenceAnalyzer;
import com.sun.tgxml.tjtf.tools.ToolBase;
import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;

// import com.sun.tgxml.tools.dependence.LibIDList;
// import com.sun.tgxml.tools.dependence.LibraryDependencies;
// import com.sun.tgxml.tools.dependence.LibraryDependency;

/**  
 * A processing dependency tool. The tool is to provide a command
 * line user interface to the <i>DependenceAnalyzer</i> component.
 * <br>
 *
 * @author Evgueni Rouban
 * @version @(#)DependenceAnalyzerTool.java	1.12 05/04/08
 * @see com.sun.tgxml.tools.dependence.processors.DependenceAnalyzer
 */

public class DependenceAnalyzerTool extends ToolBase {
    
    private static final String CtStr_ToolName = "DependenceAnalyzerTool";
    
    protected String dependenciesFileName = null;
    protected String libraryListFile = null;
    
    protected LibraryDependencies libraryDependencies = null;
    protected LibIDList libIDList = new LibIDList();

    protected static final String str_Term = System.getProperty("line.separator");

    protected String getUsageMsgTitle() {
        return LibResHandler.getResStr("dependencednalyzertool.title1", getProgramName()) + str_Term
             + LibResHandler.getResStr("dependencednalyzertool.title2")  + str_Term
             + LibResHandler.getResStr("dependencednalyzertool.title3")  + str_Term
             + LibResHandler.getResStr("dependencednalyzertool.title4")  + str_Term
             + LibResHandler.getResStr("dependencednalyzertool.title5")  + str_Term
             + LibResHandler.getResStr("dependencednalyzertool.title6");
    }


   /** ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption dOption = new StringOption(LibResHandler.getResStr("dependencednalyzertool.option.d.mnem"), 
         LibResHandler.getResStr("dependencednalyzertool.option.d.desc"),
         OBLIGATORY);

    StringOption lOption = new StringOption(LibResHandler.getResStr("dependencednalyzertool.option.l.mnem"),
         LibResHandler.getResStr("dependencednalyzertool.option.l.desc"),
         OPTIONAL);


    /**
     * Registers -d, -l options
     */
    public void registerOptions() {
         dOption.setOptionParameterMissedPattern(LibResHandler.getResStr("dependencednalyzertool.option.missed.0"));
         lOption.setOptionParameterMissedPattern(LibResHandler.getResStr("dependencednalyzertool.option.missed.1"));
         super.registerOptions();
         addOption(dOption);
         addOption(lOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        dependenciesFileName = dOption.getStringValue();
        if (lOption.isSet())
            libraryListFile = lOption.getStringValue();
 
        // check operands
        for (int i = 0; i < operands.length; i++) {        
            libIDList.add(operands[i]);
        }
	    

        if (libraryListFile == null && libIDList.isEmpty()) {
            throw new ParseArgumentException (LibResHandler.getResStr("dependencednalyzertool.error.args.library.not.specified"));
        }

        try {
            libraryDependencies = new LibraryDependencies();
            libraryDependencies.read(new FileInputStream(dependenciesFileName));

            if (libraryListFile != null) {
                libIDList.read(new FileInputStream(libraryListFile));
            }
        } catch (java.io.IOException e) {
            throw new ParseArgumentException (e.toString());
        }

        super.applyOptionsValues();
    }

    /** 
     * Sets OperandsValidator that validates that allows any operands
     */
    protected void setOperandsValidator() {
        String[] operandsUsageLines = {
            LibResHandler.getResStr("dependencednalyzertool.operands.0"),
            LibResHandler.getResStr("dependencednalyzertool.operands.1")
        };
        operandsValidator = new DefaultOperandsValidator(0, Integer.MAX_VALUE, 
            null, null, operandsUsageLines);
    }
    
    /**
     * Sets usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = LibResHandler.getResStr("dependencednalyzertool.usage.header", getProgramName(), str_Term);
    }

    /** 
     *  
     *  Primary tool interface.
     * <p>
     * This method creates the DependenceAnalyzer and calls its method analyze.
     * Then prints the result to the output.
     */
    public void startTool() {

        DependenceAnalyzer dependenceAnalyzer = new DependenceAnalyzer(getStandardErr());
        LibIDList requiredLibraries = dependenceAnalyzer.analyze(libIDList, libraryDependencies);
        
        if (requiredLibraries != null) {
            requiredLibraries.write(getStandardOut());
        }
    }

    public DependenceAnalyzerTool(PrintStream out, PrintStream err, String name) {
        super(out, err, name);
        m_needsCommandLineArguments = true;
    }

    /**
     * Implements command line user interface.
     * <pre> usage: 
     *   DependenceAnalyzerTool -d dependencies_file_name -l library_list_file
     *   DependenceAnalyzerTool -d dependencies_file_name LibID ...
     * </pre>
     * <ul>
     * <li>library_list_file - text file containing list of library 
     *     identifiers referred by test groups. This file is produced 
     *     by TestGroup filtering. 
     * <li>LibID - a library identifier 
     * <li>dependencies_file_name - text file containing library dependencies. 
     *     The file format must be acceptable by the method 
     *     LibraryDependencies.read(InputStream)
     * </ul>
     *
     * @param args
     *                 command line parameters.
     * @see com.sun.tgxml.tools.dependence.LibraryDependencies#read(java.io.InputStream)
     */
    public static void main(String args[]) {
        DependenceAnalyzerTool dependenceAnalyzerTool =
            new DependenceAnalyzerTool(System.out, System.err, CtStr_ToolName);
        System.exit(dependenceAnalyzerTool.run(args));
    }

}
