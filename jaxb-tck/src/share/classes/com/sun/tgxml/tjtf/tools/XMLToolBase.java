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
import com.sun.tgxml.tjtf.processors.parser.impl.XMLParserImpl;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.processors.emitter.impl.XMLEmitterImpl;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

// </importgen>


/**
 * XMLToolBase - 
 *
 * This class adds to the base class by handling standard params for a
 * tool to do XML processing.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */

/*
 * ============================================================================================
 *    XMLToolBase
 * ============================================================================================
 */


public  class XMLToolBase extends StandardToolBase {
    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    private static final String CtStr_ToolName = LibResHandler.getResStr("xmlbasetool.name");
    
      
            /** Print Strings   */
    private static final String str_Term = System.getProperty("line.separator");
    

    protected StringOption fileOption = new StringOption(LibResHandler.getResStr("xmlbasetool.filearg.mnem"), 
         LibResHandler.getResStr("xmlbasetool.filearg.desc"),
         OBLIGATORY);


    private TagHandlerTable     m_tagHandlerTable;

    private boolean             m_diagnoseParser;
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
    *  This replaces the main in ToolBase (sub-classes XMLToolBase).
    * <p>
     * @param args The command line arguments to  this tool.
    */
    public static void main(String args[]) {
        XMLToolBase c = new XMLToolBase(System.out, System.err);
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
    public XMLToolBase( PrintStream out, PrintStream err) {
	this(out, err, CtStr_ToolName);
    }

    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out  The print stream for writing program information.
     * @param err  The print stream for error diagnostics.
     * @param name The name of the tool.
     *
     * @see java.io.PrintStream 
     */
    public XMLToolBase( PrintStream out, PrintStream err, String name) {
	super(out, err, name);
	m_diagnoseParser = false;
    }


 
   /* 
    * -------------------------------------------------------------------
    *    Getters/Setters 
    * -------------------------------------------------------------------
    */ 



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


  /**
    * Setup the XML environment for the processors.
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    * @throws TestFileException If there is a problem with  the DTD or TagHandlers.
    */
    public void setupXMLEnvironment() throws TestFileException {
	m_tagHandlerTable = TagHandlerFactory.getDefaultHandlerTable();
    }




  /**
    * Create a default parser (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public IRParser createParser() throws TestFileException {
	IRParser parser =  ParserFactory.createXMLParser();
	parser.setShell(this);
	return parser;
    }


  /**
    * Create a default parser (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public void setupParser()  throws TestFileException {
	try {
	    XMLParserImpl parser = (XMLParserImpl) m_parser;
	    TagHandlerFactory.setParserSupport(m_tagHandlerTable, parser.getParserHandlerSupport());
	    parser.setupParser(m_tagHandlerTable);
	    // by default, set the parser to be validating.
	    parser.setXMLValidation(true);
	} catch (SAXException e) {
	    throw new EmbeddedTFException (e);
	}
    }

 


  /**
    * Create a default emitter (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public IREmitter createEmitter() throws TestFileException {
	IREmitter emitter =  EmitterFactory.createXMLEmitter();
	emitter.setShell(this);
	return emitter;
    }


  /**
    * Create a default parser (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public void setupEmitter()  throws TestFileException {
	XMLEmitterImpl emitter = (XMLEmitterImpl) m_emitter;
	TagHandlerFactory.setEmitterSupport(m_tagHandlerTable, emitter.getEmitterHandlerSupport());
	emitter.setupEmitter(m_tagHandlerTable);
    }

 
   /* 
    * -------------------------------------------------------------------
    *    
    * -------------------------------------------------------------------
    */ 


 
    
    /** 
     *  
     *  Primary tool interface.
     * <p>
     * This method sets up a parser and emitter, and processes the arguments.
     * It calls the methods: processArgs() and executeTool().
     * <p>
     * XMLToolBase.startTool() fully overrides the implementation in StandardToolBase,
     * and does not call the parent's implementation.  This implementation also catches
     * SAXExceptions (which are embedded inside off TestFileException (EmbeddedTFException).
     * This method allows SAXExceptions to pass thru the generic contracts (that only
     * specify throwing TestFileException.
     * <p>
     *  Exceptions caught in this method setup
     *  return-codes:  Return Code (int) - 0 = normal termination, > 0 abnormal termination.
     */
    public void startTool() {
	int returnCode = ctInt_ErrorCode_NoError;

        try {
	    processArgs();
	    executeTool();
	} catch (EmbeddedTFException excpt) {
	    // The general contract only specifies TestFileException and IOException
	    //   (Since other types of parsers don't care about SAX exceptions).
	    // So to handle SAX exceptions, they are embedded into EmbeddedTFException,
	    // (a form of TestFileException).
	    

	    // Embedded exceptions need to be unwrapped and handled.
	    Exception embedded = excpt.getException();
	    if (embedded instanceof SAXParseException) {
		SAXParseException e  = (SAXParseException) embedded;
		// IMPORTANT:
		//   The SAX parser tends to wrap exceptions within it's
		//   own exceptions.  If you print a StackTrace, it will
		//   reflect the location where the parser rethrows the 
		//   exception, and not where the original exception occurrs.
		//
		//   To get the correct StackTrace, you must get the embedded
		//   exception, and perform printStackTrace on that exception.
		String message = e.getMessage();
		// the problem is in the XML file


		if (m_debug) {
		    Exception wrappedException = e.getException();
		    if (wrappedException != null)
			wrappedException.printStackTrace();
		    else
			e.printStackTrace();
		} else
		    // The ErrorHandler (in the parser) should have already located the file/DTD 
		    reportErrorMsg(LibResHandler.getResStr("xmlbasetool.error.xml", str_Term, str_Term, message));

		setResultCode(ctInt_ErrorCode_Error);
	    } else if (embedded instanceof SAXException) {
		SAXException e  = (SAXException) embedded;
		String message = e.getMessage();
		String filename = "<none>";
		if (getInputFile() != null)
		    filename = getInputFile().getAbsolutePath();
		
		if (m_debug) {
		    Exception wrappedException = e.getException();
		    if (wrappedException != null)
			wrappedException.printStackTrace();
		    else
			e.printStackTrace();
		} else
		    reportErrorMsg(LibResHandler.getResStr("xmlbasetool.error.xmlparse.line", filename, str_Term, message, str_Term));

		setResultCode(ctInt_ErrorCode_Error);
	    } else {
		// if it is not a SAXException, I don't know what it is!!
	    }
	} catch (TestFileException e) {
	    if (m_debug)
		e.printStackTrace();
	    else
		reportErrorMsg(e.getMessage());


	    setResultCode(ctInt_ErrorCode_Error);
	} catch (IOException e) {
	    String filename = "<none>";
	    if (getInputFile() != null)
		filename = getInputFile().getAbsolutePath();
	    if (m_debug)
		e.printStackTrace();
	    else
		reportErrorMsg(LibResHandler.getResStr("file.error.ioerror", filename));
	    setResultCode(ctInt_ErrorCode_Error);
	}
    }


   /**
    * Handles EmbeddedTFException, SAXException and IOException .
    * Other exception are handled by super
    */
    protected void handleShellException(Exception excpt) {
	// Handle TestFile Exception (general)
	if (excpt instanceof EmbeddedTFException) {
	    // The general contract only specifies TestFileException and IOException
	    //   (Since other types of parsers don't care about SAX exceptions).
	    // So to handle SAX exceptions, they are embedded into EmbeddedTFException,
	    // (a form of TestFileException).
	    

	    // Embedded exceptions need to be unwrapped and handled.
	    Exception embedded = ((EmbeddedTFException)excpt).getException();
	    if (embedded instanceof SAXParseException) {
		SAXParseException e  = (SAXParseException) embedded;
		// IMPORTANT:
		//   The SAX parser tends to wrap exceptions within it's
		//   own exceptions.  If you print a StackTrace, it will
		//   reflect the location where the parser rethrows the 
		//   exception, and not where the original exception occurrs.
		//
		//   To get the correct StackTrace, you must get the embedded
		//   exception, and perform printStackTrace on that exception.
		String message = e.getMessage();
		// the problem is in the XML file


		if (m_debug) {
		    Exception wrappedException = e.getException();
		    if (wrappedException != null)
			wrappedException.printStackTrace();
		    else
			e.printStackTrace();
		} else
		    // The ErrorHandler (in the parser) should have already located the file/DTD 
		    reportErrorMsg(LibResHandler.getResStr("xmlbasetool.error.xml", str_Term, str_Term, message));

		setResultCode(ctInt_ErrorCode_Error);
	    } else if (embedded instanceof SAXException) {
		SAXException e  = (SAXException) embedded;
		String message = e.getMessage();
		String filename = "<none>";
		if (getInputFile() != null)
		    filename = getInputFile().getAbsolutePath();
		
		if (m_debug) {
		    Exception wrappedException = e.getException();
		    if (wrappedException != null)
			wrappedException.printStackTrace();
		    else
			e.printStackTrace();
		} else
		    reportErrorMsg(LibResHandler.getResStr("xmlbasetool.error.xmlparse.line", filename, str_Term, message, str_Term));

		setResultCode(ctInt_ErrorCode_Error);
	    } else {
		// if it is not a SAXException, I don't know what it is!!
	    }
	} else if (excpt instanceof IOException) {
	    String filename = "<none>";
	    if (getInputFile() != null)
		filename = getInputFile().getAbsolutePath();
	    if (m_debug)
		excpt.printStackTrace();
	    else
		reportErrorMsg(LibResHandler.getResStr("file.error.ioerror", filename));
	    setResultCode(ctInt_ErrorCode_Error);
	} else
	    // let the super handle the exception
	    super.handleShellException(excpt);
    }


   
    /** 
     *  
     *  Primary tool (set-up) interface.
     * <p>
     * This method creates/sets-up a parser and emitter (calls the create/setup methods).
     * <p>
     *  The XMLToolBase.setupTool() implementation completely overrides the implementation
     * in StandardToolBase.  This implementation allows another method, setupXMLEnvironment()
     * to get called before the parser and emitter are created/setup.  This implementation also
     * catches SAXExceptions.
     * <p>.
     *  Exceptions caught in this method setup
     *  return-codes:  Return Code (int) - 0 = normal termination, > 0 abnormal termination.
     */
    public void setupTool() throws TestFileException, IOException {
	setupXMLEnvironment();
	super.setupTool();
    }





    
    /** 
     *  
     *  Parse a single file, process the Parse-Tree, and emit the processed tree.
     * <p>
     * @throws TestFileException If there is a problem with the IR parse tree.
     * @throws IOException if there is an IO problem.
     */
    public void executeTool() throws TestFileException, IOException {
	// This particular Tool processes a single XML file.
	//
	// It parses an XML file, processes the TD object tree,
	// and then emits the tree.
	//
	// other tools may follow a different scheme - 
	//   for example, they may parse an XML file, then
	//   emit a list of attributes in html.
	//
	// These tools can simply override StartTool to do
	// some variant of this.
	File files[] = new File[1];
	files[0] = getInputFile();
	OutputStream[] outputs = new OutputStream[1];
	outputs[0] = getStandardOut();

	IRObj[] results = m_parser.parse(files);
	process(results[0]);
	m_emitter.emit(results, outputs);

    }

    
    /** 
     *  
     *  Tool-specific implementation code. All sub-classes should override this method.
     * <p>
     * Process an IR tree.
     * <p>
     * @param ir A tree of Internal Rep objects.
     * @throws TestFileException when there is a problem processing an IR tree.
     */
    public void process(IRObj ir) throws TestFileException {

    }

    
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    /**
     * Registers -file option, parser and emitter options
     */
    public void registerOptions() {
         super.registerOptions();
         addOption(fileOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (fileOption.isSet()) {
            m_inputfileString = fileOption.getStringValue();

            if (! m_inputfileString.endsWith(".xml")) {
                throw new ParseArgumentException (LibResHandler.getResStr("xmlbasetool.filearg.error.notxml", getProgramName(),  m_inputfileString));
            }
    
            File file = new File(m_inputfileString);
    
            if (! file.exists()) {
                throw new ParseArgumentException(LibResHandler.getResStr("xmlbasetool.filearg.error.notpresent", getProgramName(),  m_inputfileString));
            }
    
            if (file.isDirectory()) {
                throw new ParseArgumentException(LibResHandler.getResStr("xmlbasetool.filearg.error.isdir", getProgramName(),  m_inputfileString));
            }
    
            setInputFile(file);
        }

        super.applyOptionsValues();
    }


}
