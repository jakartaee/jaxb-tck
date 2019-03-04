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

package com.sun.tgxml.tjtf.processors.emitter.impl;

import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterTagHandler;
import com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerTable;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import java.io.*;
import java.util.*;
import java.net.URL;

import com.sun.tgxml.tjtf.*;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;

/** 
 * BaseEmitterHandlerImpl.java - The Handlers that the SAXparser uses to parse XML SpecFiles into. 
 * a parse tree of ClassInfos. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    BaseEmitterHandlerImpl 
 * ============================================================================================ 
 */ 
public final class BaseEmitterHandlerImpl implements EmitterHandlerSupport {

    static private boolean           DEBUG = false;

    private TagHandlerTable          m_handlerTable;

    private PrintStream              m_OutputStream;
    private Shell                    m_shell;

    private int                      m_IndentLevel;
    static private int               ctInt_Tab  = 4;



    private static final String  str_Separator = System.getProperty("path.separator");
    private static final String  str_Term = System.getProperty("line.separator");

    private static final String  str_BeginDel = "<";
    private static final String  str_EndDel = ">";
    private static final String  str_EndTagBegDel = "</";
    private static final String  str_EndSingltn = "/>";
    private static final String  str_BegAttr= "=\"";
    private static final String  str_EndAttr= "\"";
    private static final String  str_Space= " ";


    protected static final String  str_Beg_CDATA = "<![CDATA[";
    protected static final String  str_End_CDATA = "]]>";

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
    *   PlatformEmitter constructor - 
    *       Initialize our internal fields. 
    */ 
    public BaseEmitterHandlerImpl() {
	init();
	reset();
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
    * @param msg The smessage to log.
    */
    public void log(String msg) throws TestFileException {
	m_shell.log(msg);
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    EmitterHandlerSupport Interface implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitHeadBeginTag(String tdTagType) throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.print(str_BeginDel);
	ps.print(tdTagType);
    }

   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitHeadEndTag() throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.print(str_EndDel);
    }
          
   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitTailTag(String tdTagType) throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.print(str_EndTagBegDel);
	ps.print(tdTagType);
	ps.print(str_EndDel);
    }
 
   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitSingletonBeginTag(String tdTagType) throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.print(str_BeginDel);
	ps.print(tdTagType);
    }

   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitSingletonEndTag() throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.print(str_EndSingltn);
    }

   /** 
    *   test XML emitter string - if  there are any XML pre-defined entities. 
    */ 
    private boolean containsXMLPredefinedEntities(String text)  {
	boolean returnval = false;

	if (text == null || text.equals(""))
	    return false;

	if (text.indexOf("<") >= 0)
	    return true;

	if (text.indexOf(">") >= 0)
	    return true;

	if (text.indexOf("&") >= 0)
	    return true;

	return false;
    }

   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitText(String text) throws TestFileException, IOException {
	PrintStream ps = getStream();
       
	// Wrap source code within CDATA blocks if required.
	if (containsXMLPredefinedEntities(text)) {
	    ps.print(str_Beg_CDATA);
	    ps.print(text);
	    ps.print(str_End_CDATA);
	} else {
	    ps.print(text);
	}
    }

   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void emitAttribute(String name, String value) throws TestFileException, IOException {
	PrintStream ps = getStream();

	ps.print(str_Space);
	ps.print(name);
	ps.print(str_BegAttr);
	ps.print(value);
	ps.print(str_EndAttr);
    }
          
   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void incrementIndentation() {
	m_IndentLevel++;
    }

   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void decrementIndentation() {
	if (m_IndentLevel > 0)
	    m_IndentLevel--;
    }


   /** 
    *   The handler dispatches a tag to emit. 
    */ 
    public void newline() throws TestFileException, IOException {
	PrintStream ps = getStream();
	ps.println();
    }


  /**
    *  Set the debug flag.
    */
    public boolean getDebug ()    {
	return 	DEBUG;
    }


  /**
    *  Hashes the correct TagHandler.
    */
    public void emit(String tdTagType, Object tdObj) throws TestFileException, IOException {
	    EmitterTagHandler TH = (EmitterTagHandler) m_handlerTable.get(tdTagType);
	    if (TH == null)
		throw new TestFileException(LibResHandler.getResStr("emitter.error.taghandlertable.undefTag", tdTagType));
	    TH.emit(tdObj);
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
	reset();
    }

   /** 
    *  reset the internal fields for reusing the parser. 
    */ 
    public void reset() {
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
    *  Set handler table.
    */
    public void setStream (OutputStream stream)    {
	if (stream == null)
	    m_OutputStream = null;
	else
	    m_OutputStream = new PrintStream(stream);
    }

  /**
    *  Get the handler table.
    */
    public PrintStream getStream() throws TestFileException   {

	if (m_OutputStream == null)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.outstream.null"));

	return 	m_OutputStream;
    }


  /**
    *  Set the debug flag.
    */
    public void setDebug (boolean debug)    {
	DEBUG = debug;
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void startDocument (XMLObj root) throws TestFileException, IOException {
	String rootTagName = getHandlerTable().getRootTag(root);
	String docName = getHandlerTable().getDocName();

	if (rootTagName == null || rootTagName.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.generic.notroot", root.getClass().getName()));

	startDocumentHeader(rootTagName, docName);
	emit(rootTagName, root);
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void startDocumentHeader (String docRoot, String dtdName) throws TestFileException, IOException {
	PrintStream ps = getStream();

	ps.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
	ps.println("<!DOCTYPE " + docRoot + " SYSTEM \"" + dtdName + "\">");
	ps.println();
    }


  /**
    *  Returns the fully qualified name of the package.
    */
    public void endDocument ()   {

    }



   /** 
    *  initially set up the parser. 
    */ 
    public void indent() throws TestFileException, IOException {
	int tabAmount = ctInt_Tab * m_IndentLevel;

	if (tabAmount > 0) {
	    PrintStream ps = getStream();
	    StringBuffer sb = new StringBuffer();
	    
	    for (int i = tabAmount; i > 0; i--) 
		sb.append(str_Space);
	    
	    ps.print(sb.toString());
	}
    }


}
