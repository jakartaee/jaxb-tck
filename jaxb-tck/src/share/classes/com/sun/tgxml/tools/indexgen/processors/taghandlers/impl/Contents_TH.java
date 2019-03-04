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

import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tjtf.processors.taghandlers.impl.TagHandlerImpl;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
 
 
/** 
 * Contents_TH - The tag-handler for a Lib tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    Contents_TH 
 * ============================================================================================ 
 */ 
public class Contents_TH extends TagHandlerImpl  {


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
    *   Contents_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public Contents_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TestSuiteTagsImpl.ctStr_tag_contents;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------
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
			String FORMAT = null;
			if (attrs != null) {
				for (int i = 0; i < attrs.getLength (); i++) {
					// Get the langtype
					if ((attrs.getQName(i)).equals(TestSuiteTagsImpl.ctStr_attr_format)) {
						FORMAT = attrs.getValue (i);
					} else {
						// Unknown spec attribute
						getParserHandler().throwError(LibResHandler.getResStr("parser.error.unknownSpecAttr", attrs.getQName(i)));
					}
				}
				
				Stack testItemStack = getParserHandler().getStack();
				Object testitem = testItemStack.peek();
				if (testitem == null)
					getParserHandler().throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
				
				if (! (testitem instanceof TestSuite)  )
					getParserHandler().throwError(LibResHandler.getResStr("parser.error.invcontext3", getTagName(), TestSuiteTagsImpl.ctStr_tag_testsuite));
				
				//  Nothing is pushed onto the stack
				TestSuite doc = (TestSuite) testitem;
				
				// validated the dependency name
				// validateDescription(text);
				doc.setContentsFormat(FORMAT);
			}
		} catch (TestFileException e) {
			getParserHandler().throwError(e.getMessage());
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
		EmitterHandlerSupport eh = getEmitterHandler();
		if (! (tdObject instanceof String)) {
			throw new TestFileException(LibResHandler.getResStr(
                   "emitter.error.invObj", "String",
                   tdObject.getClass().getName()));
		}
  	   
		String format = (String)tdObject;
		if (format != null && ! format.equals("")) {
			eh.emitAttribute(TestSuiteTagsImpl.ctStr_attr_format, format);
		}

    }
                   
}
