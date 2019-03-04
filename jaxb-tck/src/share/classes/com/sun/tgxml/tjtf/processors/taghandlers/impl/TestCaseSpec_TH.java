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

import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * TestCaseSpec_TH - The tag-handler for a TestCaseSpec tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestCaseSpec_TH 
 * ============================================================================================ 
 */ 
public class TestCaseSpec_TH extends TagHandlerImpl  {


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
    *   TestCaseSpec_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestCaseSpec_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_testcasespec;
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
	Stack testItemStack = m_ParserHandler.getStack();
	Object testitem = testItemStack.peek();
	if (! (testitem instanceof TestCaseDocumentation))
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testcasedocumentation));

	TestCaseSpec tcs = DocumentationFactory.createTestCaseSpec();
	TestCaseDocumentation tcd = (TestCaseDocumentation) testitem;

	ArrayList specs = tcd.getTestCaseSpecs();
	if (specs == null) {
	    specs = new ArrayList();
	    tcd.setTestCaseSpecs(specs);
	}

	specs.add(tcs);

	testItemStack.push(tcs);

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

	    if (! (testitem instanceof TestCaseSpec))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}
    }
     
     
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
          
          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TestCaseSpec))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestCaseSpec", tdObject.getClass().getName()));

	TestCaseSpec tcs = (TestCaseSpec) tdObject;


	ArrayList assertions = tcs.getAssertions();
	if (assertions != null) {
	    Iterator it = assertions.iterator();
	    Assertion assertion = null;

	    while (it.hasNext()) {
		assertion = (Assertion) it.next();

		if (assertion instanceof AssertionRef)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_assertionref, assertion);
		else if (assertion instanceof InlineAssertion)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_inlineassertion, assertion);
	    }
	}

	TestTechnique tt = tcs.getTestTechnique();
	if (tt != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_testtechnique, tt);

	String membersig = tcs.getMemberSig();
	if (membersig != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_membersig, membersig);

	ArrayList inputs = tcs.getInputs();
	if (inputs != null) {
	    Iterator it1 = inputs.iterator();

	    while (it1.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_input, it1.next());
	    }
	}


	ArrayList preconditions = tcs.getPreconditions();
	if (preconditions != null) {
	    Iterator it2 = preconditions.iterator();

	    while (it2.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_precondition, it2.next());
	    }
	}

	ExpectedResultValue erv = tcs.getExpectedResultValue();
	if (erv != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_expectedresultvalue, erv);

	ArrayList erses = tcs.getExpectedResultSideEffects();
	if (erses != null) {
	    Iterator it3 = erses.iterator();

	    while (it3.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_expectedresultsideeffect, it3.next());
	    }
	}

	ArrayList eres = tcs.getExpectedResultExceptions();
	if (eres != null) {
	    Iterator it4 = eres.iterator();

	    while (it4.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_expectedresultexception, it4.next());
	    }
	}

	ArrayList specelems = tcs.getSpecElems();
	if (specelems != null) {
	    Iterator it5 = specelems.iterator();

	    while (it5.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_specelem, it5.next());
	    }
	}


    }

}
