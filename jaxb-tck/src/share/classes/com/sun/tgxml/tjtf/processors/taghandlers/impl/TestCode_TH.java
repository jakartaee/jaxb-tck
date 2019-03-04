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
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.ConstantsImpl;
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * TestCode_TH - The tag-handler for a TestCode tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestCode_TH 
 * ============================================================================================ 
 */ 
public class TestCode_TH extends TextStreamTagHandler  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 


    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   TestCode_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestCode_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_testcode;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void startTag(org.xml.sax.Attributes attrs) throws SAXException {
	super.startTag(attrs);
	try {
	    String lang = "";
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the langtype
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_sourcelang)) {
			lang = attrs.getValue (i);
			// do something with the lang
			// validateLangType(lang);
		    }
		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownSpecAttr", attrs.getQName (i)));
		}

		Stack testItemStack = getParserHandler().getStack();
		Object tca = testItemStack.peek();
		if (! (tca instanceof TestCase))
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.codeset.invcontext"));

		TestCase tc = (TestCase) tca;
		TestCode tcode = CodeFactory.createTestCode();
		tc.setTestCode(tcode);
		tcode.setSourceLang(lang);

		// push the TestCase to the top of the stack
		testItemStack.push(tcode);

	    }
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}
    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String text) throws SAXException {
	super.endTag(text);

	try {
	    Stack testItemStack = m_ParserHandler.getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof TestCode))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	    TestCode tc = (TestCode) testitem;

	    // validated the code
	    //   If the tag is parsed in, it can not have null text.
	    if (text == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	    
	    StringTokenizer tknzr = new StringTokenizer(text, " \t\n\r");
	    if (! tknzr.hasMoreTokens())
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	    tc.setSource(text);

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}

    }
     

 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
  /**
    *   emit a tags attributes (general function).
    *  <p>
    * @see #endTag
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestCode))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestCode", tdObject.getClass().getName()));

	TestCode tc = (TestCode) tdObject;

	String lang = tc.getSourceLang();
	if (lang != null && ! lang.equals("")) {
	    // Don't output the lang if it is equal to the default (java)
	    if (lang != ConstantsImpl.ctStr_attr_langtype_enum_java) 
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_sourcelang, lang);
	}

    }

         

  /**
    *   emit the Ref text.
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestCode))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestCode", tdObject.getClass().getName()));

	TestCode tc = (TestCode) tdObject;
	String source = tc.getSource();
	//   If the tag is parsed in, it can not have null text.
	if (source == null)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));
	
	StringTokenizer tknzr = new StringTokenizer(source, " \t\n\r");
	if (! tknzr.hasMoreTokens())
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));
       
	m_EmitterHandler.emitText(source);
    }



}
