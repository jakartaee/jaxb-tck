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

package com.sun.tgxml.tjtf.processors.parser.impl;

import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import java.io.*;
import java.util.*;
import java.net.URL;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.ext.DeclHandler;

import com.sun.tgxml.tjtf.*;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.tools.Shell;

/** 
 * BaseParserHandlerImpl.java - The Handlers that the SAXparser uses to parse XML SpecFiles into. 
 * a parse tree of ClassInfos. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    BaseParserHandlerImpl 
 * ============================================================================================ 
 */ 
public final class BaseParserHandlerImpl extends DefaultHandler implements ParserHandlerSupport {

    static private boolean           DEBUG = false;

    private TagHandlerTable          m_handlerTable;


    public  Locator                  m_locator;
    private boolean                  m_finishedParsing;
    private int                      m_currentSourceToken;
    private StringBuffer             m_currentBuffer;

    private Shell                    m_shell;
    private XMLObj                   m_root;
    private Stack                    m_stack;
    private StringBuffer             m_parsingFailure;


    private static final int ctInt_ParsingToken_Nothing = 0;
    private static final int ctInt_ParsingToken_Source = 1;
    private static final int ctInt_ParsingToken_Correct = 2;
    private static final int ctInt_ParsingToken_Incorrect = 3;

    private BPH_ErrorHandler m_errorHandler;
    private static final String  str_Separator = System.getProperty("path.separator");
    private static final String  str_Term = System.getProperty("line.separator");

    // Platform Parser exceptions
    //_____________________________________
     public class PlatformParseException extends SAXParseException {
	 public PlatformParseException (String message) {
	     super(message, m_locator);
	 }

	 public PlatformParseException (Exception e) {
	     super(e.getClass().getName(), m_locator.getPublicId (), m_locator.getSystemId (), m_locator.getLineNumber (), m_locator.getColumnNumber (), e);
	     //	     super(message, m_locator);
	 }
	 public PlatformParseException (Exception e, String message) {
	     super(message, m_locator.getPublicId (), m_locator.getSystemId (), m_locator.getLineNumber (), m_locator.getColumnNumber (), e);
	     //	     super(message, m_locator);
	 }
     }

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
   /* 
    * -------------------------------------------------------------------------------------------- 
    *    constructors 
    * -------------------------------------------------------------------------------------------- 
    */ 
 
   /** 
    *   PlatformParser constructor - 
    *       Initialize our internal fields. 
    */ 
    public BaseParserHandlerImpl() {
	init();
	reset();
    }



   /* 
    * -------------------------------------------------------------------------------------------- 
    *    ParserHandlerSupport Interface implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 


   /** 
    *  Get the (XML TD) parser's stack. 
    */ 
    public Stack getStack() {
	return m_stack;
    }

  /**
    *  Set the root of the Java-object parse tree.
    */
    public void setRoot (XMLObj root)  {
	m_root = root;
    }

  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void _throwError (String error)  throws SAXException  {
	throw new PlatformParseException(error);
    }


  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void _throwError (Exception error)  throws SAXException  {
	if (error.getMessage() != null) {
	    SAXException e = new PlatformParseException( error.getMessage());
	    throw ((SAXException) e.fillInStackTrace());
	} else
	    throw new PlatformParseException(error);
    }


  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void _throwError (Exception error, String message)  throws SAXException  {
	throw new PlatformParseException(error, message);
    }


  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void throwError (String error)  throws SAXException  {
	recordError(error);
        SAXException e = new PlatformParseException( m_parsingFailure.toString());
	throw ((SAXException) e.fillInStackTrace());
    }



  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void throwError (Exception error)  throws SAXException  {
	recordError(error.getMessage());
        SAXException e = new PlatformParseException( m_parsingFailure.toString());
	throw ((SAXException) e.fillInStackTrace());
    }

  /**
    *  Report (throw) a semantic error through the parser.
    */
    public void throwError (Exception error, String message)  throws SAXException  {
	recordError(message);
        SAXException e = new PlatformParseException( m_parsingFailure.toString());
	throw ((SAXException) e.fillInStackTrace());
    }


  /**
    *  Set the debug flag.
    */
    public boolean getDebug ()    {
	return 	DEBUG;
    }


  /**
    *  Get the running text stream for a given tag.
    */
    public String getTextStream ()    {
	return 	flushPCDATA ();
    }


  /**
    *  Sets the Shell that owns this parser.
    * <p>
    * @param shellClass The shell that owns this parser.
    */
    public void setShell(Shell shellClass) {
	m_shell = shellClass;
    }

  /**
    *  Gets the Shell that owns this parser.
    * <p>
    * @returns The shell that owns this parser.
    */
    public Shell getShell() {
	return m_shell;
    }

  /**
    *  log a message.
    * <p>
    * @returns The shell that owns this parser.
    */
    public void log(String msg) throws TestFileException {
	m_shell.log(msg);
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    utility methods 
    * -------------------------------------------------------------------------------------------- 
    */


   /** 
    *  initially set up the parser. 
    */ 
    public void init() {
	m_stack = new Stack();
	reset();
	m_errorHandler = new BPH_ErrorHandler();
    }

   /** 
    *  reset the internal fields for reusing the parser. 
    */ 
    public void reset() {
	m_finishedParsing = false;
	m_parsingFailure = null;
	m_root = null;
	m_stack.clear();
    }


   /** 
    *  reset the internal fields for reusing the parser. 
    */ 
    public void setParsingFailure() {
	if (m_parsingFailure == null) {
	    m_parsingFailure = new StringBuffer(LibResHandler.getResStr("xmlbasetool.error.xml.locator", str_Term, 
									m_locator.getPublicId(), str_Term, 
									m_locator.getSystemId(), str_Term, str_Term));
	    m_root = null;
	}
    }

  /**
    *  Returns the fully qualified name of the package.
    */
    public XMLObj getRoot ()  {
	return (XMLObj) m_root;
    }



  /**
    *  Set handler table.
    */
    public void setHandlerTable (TagHandlerTable handlerTable)    {
	m_handlerTable = handlerTable;
    }

  /**
    *  Get the handler table.
    */
    public TagHandlerTable getHandlerTable()    {
	return 	m_handlerTable;
    }


  /**
    *  Set the debug flag.
    */
    public void setDebug (boolean debug)    {
	DEBUG = debug;
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    SAX parser-handler methods 
    * -------------------------------------------------------------------------------------------- 
    */

  /**
    *  Record the locator for diagnostics messages.
    */
    public void setDocumentLocator (Locator l)    {
        // we'd record this if we needed to resolve relative URIs
        // in content or attributes, or wanted to give diagnostics.
	m_locator = l;
    }




  /**
    *  Returns the fully qualified name of the package.
    */
    private String flushPCDATA ()  {	
	String currentData = null;

	if (m_currentBuffer != null) {
	    currentData = m_currentBuffer.toString();
	    m_currentBuffer = null;
	}
	return currentData;
    }




  /**
    *  either create a new buffer with the given string, or append the
    *  string to an existing buffer.
    */
    private void passString (String str) throws SAXException {
	if (m_currentBuffer == null)
	    m_currentBuffer = new StringBuffer(str);
	else
	    m_currentBuffer.append(str);
    }



  /**
    *  Returns the fully qualified name of the package.
    */
    public void characters (char buf [], int offset, int len) throws SAXException {
	if (m_currentBuffer == null)
	    m_currentBuffer = new StringBuffer(new String(buf, offset, len));
	else
	    m_currentBuffer.append(new String(buf, offset, len));
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void ignorableWhitespace (char buf [], int offset, int len)  throws SAXException    {
        // this whitespace ignorable ... so we ignore it!

        // this callback won't be used consistently by all parsers,
        // unless they read the whole DTD.  Validating parsers will
        // use it, and currently most SAX nonvalidating ones will
        // also; but nonvalidating parsers might hardly use it,
        // depending on the DTD structure.
    }


  /**
    *  
    */
    public void processingInstruction (String target, String data)  throws SAXException   {
    }






  /**
    *  Returns the fully qualified name of the package.
    */
    public void startDocument () throws SAXException {
	m_finishedParsing = false;
	m_currentSourceToken = ctInt_ParsingToken_Nothing;
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void endDocument () throws SAXException  {
	m_finishedParsing = true;

         // Throw an exception at the end to terminate parsing.
	if (m_parsingFailure != null) {
	    _throwError(m_parsingFailure.toString());
	}
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void startElement(String namespaceURI, String localName, String qualifiedName, 
			     org.xml.sax.Attributes attrs) throws SAXException {

         // return immediately when there is a parsing failure (let the syntax errors just get logged).
	if (m_parsingFailure != null)
	    return;
				 
        try {
	    // We always turn-off the namespace-prefixes property on the parser,
	    // so the tag name is always coming in on the qualifiedName.
	    ParserTagHandler TH = (ParserTagHandler) m_handlerTable.get(qualifiedName);
	    if (TH == null)
		_throwError(LibResHandler.getResStr("parser.error.taghandlertable.undefTag", qualifiedName));
	    // flush any remaining text from the text-flow 
	    // before parsing a tag.
	    flushPCDATA();
	    TH.startTag(attrs);
	} catch (TestFileException e) {
	    _throwError(e);
	}
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
         // return immediately when there is a parsing failure (let the syntax errors just get logged).
	if (m_parsingFailure != null)
	    return;
				 
        try {
	    // We always turn-off the namespace-prefixes property on the parser,
	    // so the tag name is always coming in on the qualifiedName.
	    ParserTagHandler TH = (ParserTagHandler) m_handlerTable.get(qualifiedName);
	    if (TH == null)
		_throwError(LibResHandler.getResStr("parser.error.taghandlertable.undefTagEnd", qualifiedName));
	    TH.endTag();
	} catch (TestFileException e) {
	    _throwError(e);
	}
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    Error-handler methods 
    * -------------------------------------------------------------------------------------------- 
    */

    protected void recordError(String errMsg) {
	setParsingFailure();
	int col = m_locator.getColumnNumber();
	
	if (col < 0) 
	    m_parsingFailure.append(LibResHandler.getResStr("parser.error.syntax.line", 
							    Integer.toString(m_locator.getLineNumber()), 
							    str_Term, errMsg, str_Term));  
	else
	    m_parsingFailure.append(LibResHandler.getResStr("parser.error.syntax.linecol", 
							    Integer.toString(m_locator.getLineNumber()), 
							    Integer.toString(m_locator.getColumnNumber()), 
							    str_Term, errMsg, str_Term));  
	
	m_parsingFailure.append(str_Term);
    }

    /* 
    * ============================================================================================ 
    *    InnerClasses  
    * ============================================================================================ 
    */ 

    public ErrorHandler getErrorHandler() {
	return m_errorHandler;
    }



    // Error handler to report errors and warnings
    private class BPH_ErrorHandler implements ErrorHandler {

        public BPH_ErrorHandler() {
        }


        // The following methods are standard SAX ErrorHandler methods.
        // See SAX documentation for more info.

        public void warning(SAXParseException spe) throws SAXException {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
	    }

	    recordError(spe.getMessage());
	}
        
        public void error(SAXParseException spe) throws SAXException {
	    recordError(spe.getMessage());
	}

        public void fatalError(SAXParseException spe) throws SAXException {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }

	    recordError(spe.getMessage());

	    _throwError(m_parsingFailure.toString());
        }
    }



}
