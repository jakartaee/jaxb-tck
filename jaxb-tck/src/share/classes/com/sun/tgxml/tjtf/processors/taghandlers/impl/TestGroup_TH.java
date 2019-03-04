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
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * TestGroup_TH - The tag-handler for a TestGroup tag. 
 * 
 * 
 * @version 	1.1, 10/24/02 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestGroup_TH 
 * ============================================================================================ 
 */ 
public class TestGroup_TH extends TagHandlerImpl  {


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
    *   TestGroup_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestGroup_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_testgroup;
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
	    String ID = "";
            boolean isDeleted = false;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the ID
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_id)) {
			ID = attrs.getValue (i);
		    } else if ((attrs.getQName (i)).equals
                           (TagsImpl.ctStr_attr_deleted)) {
			String deletedValue = attrs.getValue (i);
                        isDeleted = TagsImpl.isTrueOrFalse(deletedValue);
		    }
		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownTDAttr", attrs.getQName (i)));
		}

		if (m_ParserHandler.getRoot() != null)
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.multipleRoot"));

		TestGroup tg = TestFactory.createTestGroup();
		m_ParserHandler.setRoot(tg);
		tg.setID(ID);
		tg.setDeleted(isDeleted);

		Stack testItemStack = m_ParserHandler.getStack();
		if (! testItemStack.empty() )
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.tg.nonemptystack"));

		testItemStack.push(tg);

	    }
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}

    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #startTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = m_ParserHandler.getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof TestGroup))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	    if (! testItemStack.empty() )
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nonemptystack"));
	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}

    }

 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
          
  /**
    *   emit a tags attributes.
    *  <p>
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestGroup))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"TestGroup", tdObject.getClass().getName()));

	TestGroup tg = (TestGroup) tdObject;
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_id, tg.getID());
        if (tg.isDeleted()) 
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_deleted, "true");
    }

          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestGroup))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"TestGroup", tdObject.getClass().getName()));

	TestGroup tg = (TestGroup) tdObject;

	TestGroupDocumentation tgd = tg.getTGDocumentation();
	if (tgd != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testgroupdocumentation, tgd);

	TestGroupAttributes tga = tg.getTGAttributes();
	if (tga != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testgroupattributes, tga);

	CodeSet cs = tg.getCodeSet();
	if (cs != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_codeset, cs);

	ArrayList librarys = tg.getLibraries();
	if (librarys != null) {
	    Iterator it = librarys.iterator();
	    Library lib = null;

	    while (it.hasNext()) {
		lib = (Library) it.next();
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_library, lib);
	    }
	}

	ArrayList testCases = tg.getTestCases();
	if (testCases != null) {
	    Iterator it = testCases.iterator();
	    TestCase tc = null;

	    while (it.hasNext()) {
		tc = (TestCase) it.next();
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_testcase, tc);
	    }
	}


    }
               

}
