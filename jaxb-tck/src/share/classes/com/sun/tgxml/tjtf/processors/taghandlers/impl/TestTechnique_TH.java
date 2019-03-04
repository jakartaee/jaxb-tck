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
 * TestTechnique_TH - The tag-handler for a TestTechnique tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestTechnique_TH 
 * ============================================================================================ 
 */ 
public class TestTechnique_TH extends SingletonTagHandler  {


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
    *   TestTechnique_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestTechnique_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_testtechnique;
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
	if (! (testitem instanceof TestCaseSpec))
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testcasespec));
	
	TestCaseSpec tcs = (TestCaseSpec) testitem;
	TestTechnique  tt = null;
	
	if (attrs != null) {
	    for (int i = 0; i < attrs.getLength (); i++) {
		// Get the value
		if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_value)) {
		    String value = attrs.getValue (i);
		    // do something with the value
		    if (value.equals(TagsImpl.ctStr_attr_testtech_enum_eqclass)) 
			tt = DocumentationFactory.createEqClassTestTechnique();
		    
		    else if (value.equals(TagsImpl.ctStr_attr_testtech_enum_boundary)) 
			tt = DocumentationFactory.createBoundaryTestTechnique();
		    
		    // Unknown testtechnique
		    else
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.attribute.enum.illval",
									   TagsImpl.ctStr_attr_value, value));
		    
		}
		// unknown attribute
		else 
		    // Unknown spec attribute
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownSpecAttr", attrs.getQName (i)));
	    }
	}
	
	tcs.setTestTechnique(tt);
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
	if (! (tdObject instanceof TestTechnique))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TestTechnique", tdObject.getClass().getName()));

	TestTechnique tt = (TestTechnique) tdObject;

	if (tt.isBoundary())
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_value, TagsImpl.ctStr_attr_testtech_enum_boundary);

	else if (tt.isEqClass())
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_value, TagsImpl.ctStr_attr_testtech_enum_eqclass);

	
    }
}
