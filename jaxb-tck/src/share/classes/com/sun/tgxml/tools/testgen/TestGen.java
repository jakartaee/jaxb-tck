/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

import com.sun.tgxml.tjtf.tools.StandardToolBase;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.processors.emitter.IREmitter;
import com.sun.tgxml.tjtf.processors.parser.IRParser;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.PropertyOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;
import com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser;
import com.sun.tgxml.tools.testgen.processors.emitter.EmitterManager;
import com.sun.tgxml.tools.testgen.processors.emitter.Generator;
import com.sun.tgxml.tools.elgen.ExcludeListCollector;
import com.sun.tgxml.tools.elgen.ExcludeListToolFactory;
import java.util.Properties;

/**
 * Jmpp to XML converter tool
 */

public class TestGen extends StandardToolBase {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "TestGen";


    protected Properties emitterProperties = new Properties();

    private File[]              m_inputFiles;
    private ArrayList           m_inputFileNames;
    
    private String              m_elFileName;
    
    protected Generator         m_generator;

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
        TestGen c = new TestGen(System.out, System.err);
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
    public TestGen( PrintStream out, PrintStream err) {
        super(out, err, CtStr_ToolName);
        m_generator = null;
        m_needsCommandLineArguments = true;
        m_inputFileNames = new ArrayList();
    }
    
    protected String getTCKPropertiesFileName() {
        return BuildProperties.TCK_PROPERTIES_FILE_NAME;
    }
    

  /**
    * Create a (generic processor).
    * <p>
    *  Sub-classes may override this to install
    *  different types of parsers.
    * <p> 
    * @return a (generic) IRParser.
    * @throws TestFileException if there is a problem creating a parser.
    */
    public IRParser createParser() throws TestFileException {
	    return new MiddleWareXMLParser();
    }

    /**
     * Create a (generic) emitter.
     * <p>
     *  Sub-classes may override this to install
     *  different types of emitters.
     * <p> 
     * @return a (generic) IREmitter.
     * @throws TestFileException if there is a problem creating an emitter.
     */
    public Generator createGenerator() throws TestFileException {
        emitterProperties.setProperty(EmitterManager.GEN_TYPE_PROPERTY, 
                                      getGenType());
        EmitterManager retVal = new EmitterManager();
        retVal.setProperties(emitterProperties);
	    return retVal;
    }


    public IREmitter createEmitter() throws TestFileException {
	    return null;
    }

    /** 
     *  Parse inputs, process the IR, emit outputs.
     * <p>
     *  Tools should override this, this function determines
     *  if something is parsed - how many things are parsed at a given time,
     *  how an IR is processed (or multiple IRs),
     *  if something is emitted - how many things are emitted at a given time.
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void executeTool() throws TestFileException, IOException {
        File[] files = getInputFiles();
        IRObj[] trees = m_parser.parse(files);
        m_generator.generate(trees);
    }

    public String getGenType() {
        return "testgen";
    }
    
    /**
     * Verify that input files are valid files. Invokes setInputFiles() if files are OK.
     *
     * @return false the additional arguments don't verify.
     */
   
    public String chkInputFiles() {    
	if (m_inputFileNames.isEmpty()) 
	    return "Error: No input files are specified";

        File[] inputFiles = new File[m_inputFileNames.size()];
        String m_fileName;
        
        Iterator it = m_inputFileNames.iterator();
        
	for (int i=0; i<inputFiles.length; i++) {
	    m_fileName = it.next().toString().trim();
	    if (! m_fileName.endsWith(".xml"))
		return "Error: No .xml extension for the file : " + m_fileName;

	    inputFiles[i] = new File(m_fileName);

	    if (! inputFiles[i].exists() || inputFiles[i].isDirectory()) {
		return "Error: Bad file : " + m_fileName;
            }
        }
	    
        setInputFiles(inputFiles);
        
        return null;
    }

    /** 
     *  
     * Sub-classes override this method when they wish to process any arguments
     * before running the "executeTool" method. (Called by startTool()).
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void processArgs() throws TestFileException, IOException {
        if (m_elFileName != null) {
            ExcludeListCollector elCollector = 
                        ExcludeListToolFactory.createELCollector(m_elFileName);
            m_generator.setExcludeListCollector(elCollector);
        }
        m_generator.setProperties(emitterProperties);
    }
    
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption elOption = new StringOption("-el", 
         "  -el <exludelist>  exclude list file");

    PropertyOption emitterOptions = new PropertyOption("-emitter.", 
         "  -emitter.XXX=YYY  emitter option");

    /**
     * Registers -el options
     */
    public void registerOptions() {         
         super.registerOptions();
         addOption(elOption);
         addOption(emitterOptions);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (elOption.isSet()) {
            m_elFileName = elOption.getStringValue();
        }

        if (emitterOptions.isSet()) {
            emitterProperties.putAll(emitterOptions.getProperties());
        }

        // check operands (input files)
        File[] inputFiles = new File[operands.length];
        for (int i = 0; i < inputFiles.length; i++) {        
            inputFiles[i] = new File(operands[i]);
            if (! inputFiles[i].exists() || inputFiles[i].isDirectory()) {
                throw new ParseArgumentException("file does not exist or is a directory: " + operands[i]);
            }
        }
	    
        setInputFiles(inputFiles);

        super.applyOptionsValues();
    }

    /** 
     * Sets OperandsValidator thats validates that at least one operand
     * is passed, operands end with ".xml" and operands do not start with "-"
     */
    protected void setOperandsValidator() {
        String[] operandsUsageLines = {
            "Operands: ",
            "  file1.xml file2.xml ... "
        };
        operandsValidator = new DefaultOperandsValidator(1, Integer.MAX_VALUE, 
            "-", ".xml", operandsUsageLines);
    }
    
    /**
     * Sets TestGen usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = 
            "Usage: " + getProgramName() + " [<options>] [<fileName>.xml [<fileName>.xml]]\n" + 
            "where options include:";
    }

   /* 
    * ----------------------------------------------------------------------
    * 
    * ----------------------------------------------------------------------
    */
    
    /**
     * get this tool's input file.
     * <p> 
     * @return a String with the tool name.
     * @see #setInputFiles
     */
    public final File[] getInputFiles() {
	    return m_inputFiles;
    }
    

    /**
     * Set this tool's input file.
     * <p> 
     * @param input A String with the tool name.
     * @see #getInputFiles
     */
    public final void setInputFiles(File[] input) {
        m_inputFiles = input;
    }
    
    /** 
     *  
     *  Primary tool (set-up) interface.
     * <p>
     * This method creates/sets-up a parser and emitter (calls the create/setup methods).
     * <p>
     *  Exceptions caught in this method setup
     *  return-codes:  Return Code (int) - 0 = normal termination, > 0 abnormal termination.
     */
    public void setupTool() throws TestFileException, IOException {
        super.setupTool();
        m_generator = createGenerator();
    }

    public void setLibMapFile(String fileName) {
        if (fileName == null || m_generator == null
            || (emitterProperties.getProperty("libmap") != null)) {
            return;
        }

        emitterProperties.setProperty("libmap", fileName);
        m_generator.setProperties(emitterProperties);
    }
}
