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

import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;


import com.sun.tgxml.tjtf.processors.taghandlers.impl.TextStreamTagHandler;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
 
/** 
 * Comments_TH - The tag-handler for a Lib tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    Comments_TH 
 * ============================================================================================ 
 */ 
public class Comments_TH extends TextStreamTagHandler  {


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
    *   Comments_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public Comments_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TestSuiteTagsImpl.ctStr_tag_comments;
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
	    Stack testItemStack = getParserHandler().getStack();
	    Object testitem = testItemStack.peek();
	    
	    if (testitem == null)
		getParserHandler().throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
	    
	    if (! (testitem instanceof TestSuite)  )
		getParserHandler().throwError(LibResHandler.getResStr("parser.error.invcontext3", getTagName(),
							TestSuiteTagsImpl.ctStr_tag_testsuite));
	    
	    //  Nothing is pushed onto the stack
	    TestSuite doc = (TestSuite) testitem;
	    
	    // validated the dependency name
	    // validateComments(text);
	    doc.setComments(text);
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
	EmitterHandlerSupport eh = getEmitterHandler();

	if (! (tdObject instanceof String))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"String", tdObject.getClass().getName()));

	String title = (String) tdObject;

	eh.emitText(title);

    }

}
