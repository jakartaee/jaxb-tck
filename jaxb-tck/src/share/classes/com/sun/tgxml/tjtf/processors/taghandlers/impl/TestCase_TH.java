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
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.TestCode;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * TestCase_TH - The tag-handler for a TestCase tag. 
 * 
 * 
 * @version 	1.1, 10/24/02 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestCase_TH 
 * ============================================================================================ 
 */ 
public class TestCase_TH extends TagHandlerImpl  {


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
    *   TestCase_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestCase_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_testcase;
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
	    String VarID = null;
            boolean isDeleted = false;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the ID
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_id)) {
			ID = attrs.getValue (i);
		    }
		    // Get the VarID
		    else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_varid)) {
			VarID = attrs.getValue (i);
		    // Get the Deleted
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

		Stack testItemStack = getParserHandler().getStack();
		Object tgo = testItemStack.peek();
		if (! (tgo instanceof TestGroup))
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testgroup));

		TestGroup tg = (TestGroup) tgo;
		TestCase tc  = TestFactory.createTestCase();
		tc.setID(ID);
		tc.setVarID(VarID);
		tc.setDeleted(isDeleted);
		tc.setTestGroup(tg);

		ArrayList cases = tg.getTestCases();
		if (cases == null) {
		    cases = new ArrayList();
		    tg.setTestCases(cases);
		}
		
		TestCase other = tg.getTestCase(ID, VarID);
		if (other != null)
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.tg.tc.dupvariant", ID, VarID));
		
		cases.add(tc);

		// push the TestCase to the top of the stack
		testItemStack.push(tc);

	    }
	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.push"));
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}

    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = m_ParserHandler.getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof TestCase))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack"));
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
	if (! (tdObject instanceof TestCase))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestCase", tdObject.getClass().getName()));

	TestCase tc = (TestCase) tdObject;
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_id, tc.getID());
	String varID = tc.getVarID();
	if (varID != null && ! varID.equals(""))
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_varid, varID);
        if (tc.isDeleted()) 
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_deleted, "true");
    }

          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestCase))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestCase", tdObject.getClass().getName()));

	TestCase tc = (TestCase) tdObject;

	TestCaseDocumentation tcd = tc.getTCDocumentation();
	if (tcd != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testcasedocumentation, tcd);

	TestCaseAttributes tca = tc.getTCAttributes();
	if (tca != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testcaseattributes, tca);

	CodeSet cs = tc.getCodeSet();
	if (cs != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_codeset, cs);

	TestCode tcode = tc.getTestCode();
	if (tcode != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testcode, tcode);

    }

}
