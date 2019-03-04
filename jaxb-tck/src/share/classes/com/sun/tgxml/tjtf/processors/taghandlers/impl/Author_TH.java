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
 * Author_TH - The tag-handler for a Description tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    Author_TH 
 * ============================================================================================ 
 */ 
public class Author_TH extends TextStreamTagHandler  {


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
    *   Author_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public Author_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_author;
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

	if (! (testitem instanceof Documentation)  )
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext3", getTagName(),
						 TagsImpl.ctStr_tag_testgroupdocumentation,
						 TagsImpl.ctStr_tag_testcasedocumentation,
						 TagsImpl.ctStr_tag_librarydocumentation));
	
	//  Nothing is pushed onto the stack
	Documentation doc = (Documentation) testitem;

	ArrayList authors = doc.getAuthors();
	if (authors == null) {
	    authors = new ArrayList();
	    doc.setAuthors(authors);
	}

	// validated the author name
	if (text == null || text.equals(""))
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.text.null", getTagName()));

	authors.add(text);
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

	String author = (String) tdObject;
	// validated the author name
	if (author == null || author.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));

	m_EmitterHandler.emitText(author);

    }

}
