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
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.NameValuePair;


 
/** 
 * NameValueTagHandler - The tag-handler for a tag with unformatted text data. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    NameValueTagHandler 
 * ============================================================================================ 
 */ 
public class NameValueTagHandler extends TextStreamTagHandler   {

   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 
    private String  m_Name;

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   NameValueTagHandler constructor - 
    *       Initialize our internal fields. 
    */ 
    public NameValueTagHandler() {
	super();
	 
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------



  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public final void startTag(org.xml.sax.Attributes attrs) throws SAXException {
	super.startTag(attrs);
	if (attrs != null) {
	    for (int i = 0; i < attrs.getLength (); i++) {
		// Get the ID
		if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_name)) {
		    m_Name = attrs.getValue (i);
		}
		// unknown attribute
		else 
		    // Unknown spec attribute
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownTDAttr", attrs.getQName (i)));
	    }
	}

    }



  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public final void endTag(String text) throws SAXException {
	super.endTag(text);

	endTag(m_Name, text);
    }
          

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String name, String value) throws SAXException {
    }
          
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         

  /**
    *   emit a tag (general function).
    *  <p>
    * @see #endTag
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof NameValuePair))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.notnamevalue", getTagName()));

	NameValuePair nv = (NameValuePair) tdObject;

	String name = nv.getName();
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_name, name);
    }

         

  /**
    *   emit a tag (general function).
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof NameValuePair))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"NameValuePair", tdObject.getClass().getName()));

	NameValuePair nv = (NameValuePair) tdObject;

	String value = nv.getValue();
	m_EmitterHandler.emitText(value);
    }

          

}
