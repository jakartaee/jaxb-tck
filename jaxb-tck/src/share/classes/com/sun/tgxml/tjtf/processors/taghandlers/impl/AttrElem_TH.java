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
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * AttrElem_TH - The tag-handler for a AttrElem tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    AttrElem_TH 
 * ============================================================================================ 
 */ 
public class AttrElem_TH extends NameValueTagHandler  {


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
    *   AttrElem_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public AttrElem_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_attrelem;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String name, String value) throws SAXException {
	try {
	    super.endTag(name, value);
	    
	    Stack testItemStack = m_ParserHandler.getStack();
	    Object testitem = testItemStack.peek();
	    
	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
	    
	    if (! (testitem instanceof com.sun.tgxml.tjtf.api.attributes.Attributes)  )
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext3", getTagName(), 
							TagsImpl.ctStr_tag_testgroupattributes,
							TagsImpl.ctStr_tag_testcaseattributes,
							TagsImpl.ctStr_tag_libraryattributes));
	    
	    //  Nothing is pushed onto the stack
	    com.sun.tgxml.tjtf.api.attributes.Attributes attrs = (com.sun.tgxml.tjtf.api.attributes.Attributes) testitem;
	    
	    ArrayList attrelems = attrs.getAttrElems();
	    if (attrelems == null) {
		attrelems = new ArrayList();
		attrs.setAttrElems(attrelems);
	    }
	    
	    AttrElem ae = AttributesFactory.createAttrElem(name, value);
	    // validated the import name
	    // validateAttrElem(ae);
	    attrelems.add(ae);
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}
          
    }

 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         

    //  NameValueTagHandler handles emit code
}
