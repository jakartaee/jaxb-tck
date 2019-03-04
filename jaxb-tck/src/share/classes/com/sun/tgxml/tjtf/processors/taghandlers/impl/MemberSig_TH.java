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
 * MemberSig_TH - The tag-handler for a MemberSig tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    MemberSig_TH 
 * ============================================================================================ 
 */ 
public class MemberSig_TH extends TextStreamTagHandler  {


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
    *   MemberSig_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public MemberSig_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_membersig;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------


  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String text) throws SAXException {
	super.endTag(text);
	Stack testItemStack = m_ParserHandler.getStack();
	Object testitem = testItemStack.peek();
	
	if (testitem == null)
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
	
	// validated the member sig name
	//   If the tag is parsed in, it can not have null text.
	if (text == null)
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));

	StringTokenizer tknzr = new StringTokenizer(text, " \t\n\r");
	if (! tknzr.hasMoreTokens())
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));

	
	if (testitem instanceof TestGroupDocumentation) {
	    //  Nothing is pushed onto the stack
	    TestGroupDocumentation tgd = (TestGroupDocumentation) testitem;
	    
	    tgd.setMemberSig(text);
	} else if (testitem instanceof TestCaseSpec) {
	    //  Nothing is pushed onto the stack
	    TestCaseSpec tcs = (TestCaseSpec) testitem;
	    
	    tcs.setMemberSig(text);
	    
	} else 
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext2", getTagName(), 
							     TagsImpl.ctStr_tag_testgroupdocumentation,
							     TagsImpl.ctStr_tag_testcasespec));
	    
    }
     
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
         

  /**
    *   emit the Ref text.
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof String))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"String", tdObject.getClass().getName()));

	String sig = (String) tdObject;

	m_EmitterHandler.emitText(sig);

    }
     

}
