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

package com.sun.tgxml.tools.jmppconv;

import com.sun.tgxml.tjtf.tools.StandardToolBase;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.exceptions.ValidatorException;
import com.sun.tgxml.tjtf.processors.emitter.EmitterFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.processors.emitter.IREmitter;
import com.sun.tgxml.tjtf.processors.parser.IRParser;
import com.sun.tgxml.tjtf.processors.parser.XMLParser;
import com.sun.tgxml.tjtf.processors.parser.ParserFactory;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tools.jmppconv.processors.emitter.MiddleWareXMLEmitter;
import com.sun.tgxml.tools.jmppconv.processors.parser.JmppTDParser;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tjtf.resources.LibResHandler; 
import com.sun.tgxml.util.MiscUtils;


/**
 * Jmpp to XML converter tool
 */

public class JmppConverterTool extends StandardToolBase {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "JmppConverterTool";
    
    private static final String str_Term = System.getProperty("line.separator");
    private static final String str_FileSep = System.getProperty("file.separator");
    
    private File                m_InputFile;
    private String              m_inputfileString;

    private IRValidator validator = null;

    /*
     * =========================================================================
     *    Methods
     * =========================================================================
     */
    
    /**
     * Program entry
     *
     * This replaces the main in StandardToolBase.
     * @param args The command line arguments to  this tool.
     */
    public static void main(String args[]) {
        JmppConverterTool c = new JmppConverterTool(System.out, System.err);
        com.sun.jmpp.lib.JmppLibTest.setDefaultBPProvider(
            new JmppBuildPropertiesProvider());
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
    public JmppConverterTool( PrintStream out, PrintStream err) {
        super(out, err, CtStr_ToolName);
        m_parser = null;
        m_emitter = null;
        m_needsCommandLineArguments = true;
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
	    return new JmppTDParser();
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
    public IREmitter createEmitter() throws TestFileException {
	    return new MiddleWareXMLEmitter();
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
    public void executeTool() 
            throws TestFileException, IOException, ValidatorException {
        File[] files = {getInputFile()};
        IRObj[] trees = m_parser.parse(files);
        for (int i=0; i<trees.length; i++) {
            //TODO:temporary hack
            String outputFileName = null;
            ArrayList attrElems = null;
            if (trees[i] instanceof TestRoot) {
                TestItem ti = (TestItem)trees[i];
                attrElems = ti.getAttributes().getAttrElems();
                outputFileName = getOutputDir(attrElems) + ti.getID() + ".test.xml";
            } else if (trees[i] instanceof TestSuite) {
                TestSuite ts = (TestSuite)trees[i];
                attrElems = ts.getAttrElems();
                outputFileName = getOutputDir(attrElems) + ts.getID() + ".tdoc.xml";
            }
            if (outputFileName != null) {                
                File       outputFile = new File(outputFileName);
                createTestDir(outputFile);
                File[]     outputFiles = {outputFile};
                IRObj[]    outputObjects = {trees[i]};
                m_emitter.emit(outputObjects, outputFiles);
                if (validator != null) 
                    validateXML(outputFiles);
            }
        }
    }
    
    private String getOutputDir(ArrayList attrElems) {
        if (attrElems != null) {
            Iterator it = attrElems.iterator();
            while (it.hasNext()) {
                AttrElem el = (AttrElem)it.next();
                if (el.getName().equals("OutputDir")) {
                    String result = el.getValue();
                    return result.endsWith(str_FileSep) ? result : result + str_FileSep;
                }
            }
        }
        return "";
    }


   /**
    *  Creates subdir for outputFile
    *  @throws TestFileException if cannot create subdir
    */	
    protected void createTestDir(File outputFile) throws TestFileException {
        File dir = outputFile.getParentFile();
        if ((dir != null) && (!dir.exists())) {
            if (!MiscUtils.mkdirs(dir)) {
                throw new TestFileException(LibResHandler.getResStr("jmppconv.error.createdir", dir.toString()));
            }
        }         
    }

    /**
     * get this tool's input file.
     * <p> 
     * @return a String with the tool name.
     * @see #setInputFile
     */
    public final File getInputFile() {
	    return m_InputFile;
    }

    /**
     * Set this tool's input file.
     * <p> 
     * @param input A String with the tool name.
     * @see #getInputFile
     */
    public final void setInputFile(File input) {
        m_InputFile = input;
    }




   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    /**
     * Registers parser option handler
     */
    public void registerOptions() {
         decoder.addOptionHandler(m_parser);
         super.registerOptions();
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {	   
        m_inputfileString = operands[0];
        File file = new File(m_inputfileString);
        if (!file.exists() || file.isDirectory()) {
            throw new ParseArgumentException("file does not exist or is a directory: " + file);
        }
	    
        setInputFile(file);

        super.applyOptionsValues();
    }

    /** 
     * Sets OperandsValidator that validates that only one operand
     * is passed, operand ends with ".jmpp" and does not start with "-"
     */
    protected void setOperandsValidator() {
        String[] operandsUsageLines = {
            "Operands: ",
            "  <fileName>.jmpp"
        };
        operandsValidator = new DefaultOperandsValidator(1, 1, 
            "-", ".jmpp", operandsUsageLines);
    }
    
    /**
     * Sets jmppconv usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = 
            "Usage: " + getProgramName() + " [<options>] <fileName>.jmpp\n" + 
            "where options include:";
    }

   /* 
    * ----------------------------------------------------------------------
    *    
    * ----------------------------------------------------------------------
    */

    /** 
     *  setup validator
     */
    public void setupTool() throws TestFileException, IOException {
         super.setupTool();
         initValidator();
    }

    private void initValidator() throws TestFileException {
        try {
            String validatorName = System.getProperty("jmpp.conv.vld");
            if (validatorName != null)
                validator = (IRValidator)
                        Class.forName(validatorName).newInstance();
            else
                validator = null;    
        } catch (Exception e) {
             throw new TestFileException("Cannot initialize validator: " + e);
        }
    }


    private void validateXML(File[] emittedFiles) 
             throws ValidatorException, TestFileException, IOException { 
        XMLParser parser = ParserFactory.createDefaultXMLParser();
        for (int i = 0; i < emittedFiles.length; i++) {
            try {
                 parser.setXMLValidation(true);
                 parser.setValidator(validator);
                 parser.parse(emittedFiles[i]);
            } catch (Exception e) {
                throw new ValidatorException(emittedFiles[i] + " is not valid: " + e);
            }
        }
    }
}
