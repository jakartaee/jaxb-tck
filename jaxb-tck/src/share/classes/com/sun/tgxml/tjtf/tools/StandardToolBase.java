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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.XMLToolBase
import org.xml.sax.*;
import com.sun.tgxml.tjtf.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.parser.*;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.tools.options.FlagOption;
import com.sun.tgxml.tjtf.tools.options.ExternalOption;
import com.sun.tgxml.tjtf.tools.options.OptionHandler;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

// </importgen>


/**
 * StandardToolBase - 
 *
 * The StandardToolBase class defines a simple form of a tool requiring 
 * parser and an emitter (generic - IRParser and IREmitter) Processors.  StandardToolBase
 * provides overrideable methods for creating and initializing these processors, such
 * that they can be done in an agnostic way.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */

/*
 * ============================================================================================
 *    XMLToolBase
 * ============================================================================================
 */


public  class StandardToolBase extends ToolBase {
    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    private static final String CtStr_ToolName = LibResHandler.getResStr("standardtoolbase.name");

    //-------------------------------------------------------
    // CommandLine options
    private static final String ctStr_arg_debug = LibResHandler.getResStr("standardtoolbase.debugarg.mnem"); 

    protected FlagOption debugOption = new FlagOption(ctStr_arg_debug, LibResHandler.getResStr("standardtoolbase.debugarg.desc"));

          
            /** Put this tool in a debug mode.   */
    protected static boolean m_debug = false;


            /** A (generic) parser.   */
    protected IRParser         m_parser;

            /** A (generic) emitter.   */
    protected IREmitter        m_emitter;

    private File                m_InputFile;
    private String              m_inputfileString;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
    
    
    
    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Program Entry
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
  /**
    *  Program entry
    *
    *  This replaces the main in ToolBase.
     * @param args The command line arguments to  this tool.
    */
    public static void main(String args[]) {
        StandardToolBase c = new StandardToolBase(System.out, System.err);
        System.exit(c.run(args));
    }
    

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Public Methods (outside world can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public StandardToolBase( PrintStream out, PrintStream err) {
	this(out, err, CtStr_ToolName);
    }

    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     * @param name The name of the tool.
     *
     * @see java.io.PrintStream 
     */
    public StandardToolBase( PrintStream out, PrintStream err, String name) {
	super(out, err, name);
	m_parser = null;
	m_emitter = null;
    }


 
   /* 
    * -------------------------------------------------------------------
    *    Getters/Setters 
    * -------------------------------------------------------------------
    */ 





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
	throw new TestFileException(LibResHandler.getResStr("standardtoolbase.error.createparser.nooverride"));
    }


  /**
    * Setup a parser.
    * <p>
    *  Sub-classes may override this to initialize
    *  a parser.
    * <p> 
    * @throws TestFileException if there is a problem setting-up a parser.
    */
    public void setupParser()  throws TestFileException {
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
	throw new TestFileException(LibResHandler.getResStr("standardtoolbase.error.createemitter.nooverride"));
    }


  /**
    * Setup an emitter.
    * <p>
    *  Sub-classes may override this initialize the emitter.
    * <p> 
    * @throws TestFileException if there is a problem setting-up an emitter.
    */
    public void setupEmitter()  throws TestFileException {
    }

 
   /* 
    * -------------------------------------------------------------------
    *    
    * -------------------------------------------------------------------
    */ 

   /**
    * Handles TestFileException and IOException.
    * Other exception are handled by super
    */
    protected void handleShellException(Exception e) {
	// Handle TestFile Exception (general)
	if (e instanceof TestFileException) {
            reportErrorMsg(e.getMessage());
	    if (m_debug)
		e.printStackTrace();
	    setResultCode(ctInt_ErrorCode_Error);
        // Handle IOException
	} else if (e instanceof IOException) {
            reportErrorMsg(e.getMessage());
	    if (m_debug)
		e.printStackTrace();
	    setResultCode(ctInt_ErrorCode_Error);

	} else
	    // let the super handle the exception
	    super.handleShellException(e);
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
	m_parser = createParser();
	setupParser();
	m_emitter = createEmitter();
	setupEmitter();
    }


 
    
    /** 
     *  
     *  Primary tool interface.
     * <p>
     * This method sets up a parser and emitter, and processes the arguments.
     * It calls the methods: processArgs() and executeTool().
     * <p>
     *  Exceptions caught in this method setup
     *  return-codes:  Return Code (int) - 0 = normal termination, > 0 abnormal termination.
     */
    public void startTool() {
	int returnCode = ctInt_ErrorCode_NoError;

        try {
	    processArgs();
	    executeTool();
	} catch  (TestFileException e) {
            reportErrorMsg(e.getMessage());
	    if (m_debug)
		e.printStackTrace();
	    setResultCode(ctInt_ErrorCode_Error);
	}  catch (IOException e) {
            reportErrorMsg(e.getMessage());
	    if (m_debug)
		e.printStackTrace();
	    setResultCode(ctInt_ErrorCode_Error);
	} 
    }





    
    /** 
     *  
     *  Sub-classes override this method when they wish to process any arguments
     *  before running the "executeTool" method. (Called by startTool()).
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void processArgs() throws TestFileException, IOException {
    }
    
    /** 
     *  
     *  Parse inputs, process the IR, emit outputs.
     * <p>
     *  Tools should override this, this function determines
     *  if something is parsed - how many things are parsed at a given time,
     *  how an IR is processed (or multiple IRs),
     *  if something is emitted - how many things are emitted at a given time.
     * <p>
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void executeTool() throws TestFileException, IOException {

    }


    
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */


    /**
     * Registers -debug option, parser and emitter option handlers
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(debugOption);
        decoder.addOptionHandler(m_parser);
        decoder.addOptionHandler(m_emitter);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (debugOption.isSet()) {
            m_debug = true;
        }

        super.applyOptionsValues();
    }

}
