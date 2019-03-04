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

package com.sun.tgxml.tjtf.processors.taghandlers.impl;
import java.io.IOException;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;


 
/** 
 * TagHandlerImpl - The tag-handler abstraction. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    AttrElem_TH 
 * ============================================================================================ 
 */ 
public class TagHandlerImpl implements ParserTagHandler, EmitterTagHandler  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 


    /**  The handler support (SAXParser handler) for this tag-handler. */
    protected ParserHandlerSupport m_ParserHandler;

    /**  The handler support (SAXParser handler) for this tag-handler. */
    protected EmitterHandlerSupport m_EmitterHandler;



   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   TagHandlerImpl constructor - 
    *       Initialize our internal fields. 
    */ 
    public TagHandlerImpl() {

    }

    //------------------------------------------------------------------------------
    //  getters/setters
    //------------------------------------------------------------------------------


    public final void setParserHandler(ParserHandlerSupport parserHandler) {
	m_ParserHandler = parserHandler;
    }

    public final ParserHandlerSupport getParserHandler() {
	return m_ParserHandler;
    }


    public final void setEmitterHandler(EmitterHandlerSupport emitterHandler) {
	m_EmitterHandler = emitterHandler;
    }


    public final EmitterHandlerSupport getEmitterHandler() {
	return m_EmitterHandler;
    }

    //------------------------------------------------------------------------------
    //  ParserHandlers
    //------------------------------------------------------------------------------


  /**
    *   Get the name associated with this tag.
    *  <p>
    * Sub-classes must override this method
    * @see #endTag
    */
     public String getTagName() { return null; }

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void startTag(org.xml.sax.Attributes attrs) throws SAXException {
	try {
	    if (m_ParserHandler.getDebug())
		m_ParserHandler.log("xxxxx Starting to parse an <" + getTagName() + ">   xxx");
	} catch (TestFileException e) {
	    throw new SAXException(e);
	}
    }


  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag() throws SAXException {
	try {
	    if (m_ParserHandler.getDebug())
		m_ParserHandler.log("xxxxx Ending <" + getTagName() + ">   xxx");
	} catch (TestFileException e) {
	    throw new SAXException(e);
	}
    }
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         

  /**
    *   emit a tag (general function).
    *  <p>
    * @see #endTag
    */
    public void emit(Object tdObject) throws TestFileException, IOException {
	m_EmitterHandler.indent();
	emitStartTag(tdObject);
	m_EmitterHandler.newline();
	m_EmitterHandler.incrementIndentation();
	emitComponents(tdObject);
	m_EmitterHandler.decrementIndentation();
	m_EmitterHandler.indent();
	emitEndTag();
	m_EmitterHandler.newline();
    }
          

  /**
    *   emit a start tag (general function).
    *  <p>
    * @see #endTag
    */
    public void emitStartTag(Object tdObject) throws TestFileException, IOException {
	m_EmitterHandler.emitHeadBeginTag(getTagName());
	emitAttributes(tdObject);
	m_EmitterHandler.emitHeadEndTag();
    }
          
  /**
    *   emit a tags attributes (general function).
    *  <p>
    * @see #endTag
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
    }

          
  /**
    *   emit a tags attributes (general function).
    *  <p>
    * @see #endTag
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
    }
          

  /**
    *   emit a start tag (general function).
    *  <p>
    * @see #endTag
    */
    public void emitEndTag() throws TestFileException, IOException {
	m_EmitterHandler.emitTailTag(getTagName());
    }

}
