/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.indexgen.processors.taghandlers.impl;

import java.io.IOException;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tjtf.processors.taghandlers.impl.TagHandlerImpl;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.api.impl.TestSuiteImpl;
 
/** 
 * TestSuite_TH - The tag-handler for a TestSuite tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TestSuite_TH 
 * ============================================================================================ 
 */ 
public class TestSuite_TH extends TagHandlerImpl  {


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
    *   TestSuite_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TestSuite_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TestSuiteTagsImpl.ctStr_tag_testsuite;
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

		String ID = null;
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength (); i++) {
				// Get the ID
				if ((attrs.getQName(i)).equals(TagsImpl.ctStr_attr_id)) {
					ID = attrs.getValue (i);
				}
				// unknown attribute
				else { 
					// Unknown spec attribute
					getParserHandler().throwError(LibResHandler.getResStr("parser.error.unknownTDAttr", attrs.getQName(i)));
                }
			}
		}
		if (getParserHandler().getRoot() != null) {
			getParserHandler().throwError(LibResHandler.getResStr("parser.error.multipleRoot"));
		}

		TestSuite tg = new TestSuiteImpl();
        tg.setID(ID);

		getParserHandler().setRoot(tg);
    		
		Stack testItemStack = getParserHandler().getStack();
		if (! testItemStack.empty() )
			getParserHandler().throwError(LibResHandler.getResStr("parser.error.tg.nonemptystack"));
		
		testItemStack.push(tg);
	}
      

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #startTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = getParserHandler().getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		getParserHandler().throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof TestSuite))
		getParserHandler().throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	    if (! testItemStack.empty() )
		getParserHandler().throwError(LibResHandler.getResStr("parser.error.nonemptystack"));
	} catch (EmptyStackException e) {
	    getParserHandler().throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
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
		EmitterHandlerSupport eh = getEmitterHandler();
		
		if (! (tdObject instanceof TestSuite))
			throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"TestSuite", tdObject.getClass().getName()));

		TestSuite tgd = (TestSuite) tdObject;

        String title = tgd.getTitle();
        eh.emit(TestSuiteTagsImpl.ctStr_tag_title, title);

        String description = tgd.getDescription();
        if (description != null)
            eh.emit(TestSuiteTagsImpl.ctStr_tag_description, description);


        String format = tgd.getContentsFormat();
        if (format != null)
            eh.emit(TestSuiteTagsImpl.ctStr_tag_contents, format);


        String comments = tgd.getComments();
        if (comments != null)
            eh.emit(TestSuiteTagsImpl.ctStr_tag_comments, comments);
            
            
        ArrayList attrelems = tgd.getAttrElems();
    	if (attrelems != null) {
	        Iterator it1 = attrelems.iterator();
    	    while (it1.hasNext()) {
	        	eh.emit(TagsImpl.ctStr_tag_attrelem, it1.next());
	        }
    	}
            
            
    }



  /**
    *   emit a tags attributes.
    *  <p>
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
        EmitterHandlerSupport eh = getEmitterHandler();

        if (! (tdObject instanceof TestSuite)) {
            throw new TestFileException(LibResHandler.getResStr(
					   "emitter.error.invObj", "TestSuite", 
					   tdObject.getClass().getName()));
		}
        TestSuite tg = (TestSuite) tdObject;
        // ID is currently not included in testsuite.dtd 
		//if (tg.getID() != null) {
		//	eh.emitAttribute(TagsImpl.ctStr_attr_id, tg.getID());
		//}
	}

}
