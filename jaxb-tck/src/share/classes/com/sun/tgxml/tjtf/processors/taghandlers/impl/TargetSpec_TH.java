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

import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * TargetSpec_TH - The tag-handler for a TargetSpec tag. 
 * 
 * 
 * @version 	1.1, 10/28/02 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TargetSpec_TH 
 * ============================================================================================ 
 */ 
public class TargetSpec_TH extends TagHandlerImpl  {


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
    *   TargetSpec_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public TargetSpec_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_targetspec;
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
	    String version = "";
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the ID
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_id)) {
			ID = attrs.getValue (i);
		    } else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_version)) {
			version = attrs.getValue (i);
		    }
		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownTDAttr", attrs.getQName (i)));
		}

		Stack testItemStack = getParserHandler().getStack();
		Object testitem = testItemStack.peek();
		
		if (testitem == null)
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

		ArrayList tsps = null;

		
		if (testitem instanceof com.sun.tgxml.tjtf.api.attributes.Attributes) {
		    //  Nothing is pushed onto the stack
		    com.sun.tgxml.tjtf.api.attributes.Attributes attribs = 
			(com.sun.tgxml.tjtf.api.attributes.Attributes) testitem;
		    
		    tsps = attribs.getTargetSpecs();
		    if (tsps == null) {
			tsps = new ArrayList();
			attribs.setTargetSpecs(tsps);
		    }
		} else
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext5", getTagName(), 
							 TagsImpl.ctStr_tag_testgroupattributes,
							 TagsImpl.ctStr_tag_testcaseattributes,
							 TagsImpl.ctStr_tag_libraryattributes,
							 TagsImpl.ctStr_tag_externalsupportclass,
							 TagsImpl.ctStr_tag_inlinesupportclass));

		    
		TargetSpec ts = AttributesFactory.createTargetSpec(ID, version);
		// validated the name and version
		// validateTargetSpec(ts);
		tsps.add(ts);
		testItemStack.push(ts);
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

	    if (! (testitem instanceof TargetSpec))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
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
	if (! (tdObject instanceof TargetSpec))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"TargetSpec", tdObject.getClass().getName()));

	TargetSpec spec = (TargetSpec) tdObject;

	String ID = spec.getID();
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_id, ID);

	String version = spec.getVersion();
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_version, version);
	
    }

                   
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof TargetSpec))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"TargetSpec", tdObject.getClass().getName()));

	TargetSpec ts = (TargetSpec) tdObject;
	ArrayList tseList = ts.getTargetSpecElems();

	if (tseList != null) {
	    Iterator it1 = tseList.iterator();

	    while (it1.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_targetspecelem, it1.next());
	    }
	}
    }

}
