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

import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * InlineData_TH - The tag-handler for a Lib tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    InlineData_TH 
 * ============================================================================================ 
 */ 
public class InlineData_TH extends TextStreamTagHandler  {


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
    *   InlineData_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public InlineData_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_inlinedata;
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
	    String targetname = null;
	    String type = null;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the targetname
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_targetname)) {
			targetname = attrs.getValue (i);
			// validateTargetName(targetname);
		    }
		    // Get the type
		    else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_type)) {
			type = attrs.getValue (i);
			// validateLangType(lang);
		    }

		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownSpecAttr", attrs.getQName (i)));
		}

		Stack testItemStack = getParserHandler().getStack();
		Object tco = testItemStack.peek();
		if (! (tco instanceof CodeSet))
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_codeset));

		CodeSet cs = (CodeSet) tco;
		ArrayList datalist = cs.getData();
		if (datalist == null) {
		    datalist = new ArrayList();
		    cs.setData(datalist);
		}

		InlineData id = DataFactory.createInlineData();
		if (targetname != null)
		    id.setTargetName(targetname);
		
		// set the data type if it is specified
		if (type != null) { 
		    
		    if (! (type.equals(TagsImpl.ctStr_attr_extdata_enum_resource) || 
			   type.equals(TagsImpl.ctStr_attr_extdata_enum_iodata)))
			    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.attribute.enum.illval",
									     TagsImpl.ctStr_attr_type, type));

		    DataType dt = DataFactory.createDataType(type);
		    id.setType(dt);
		}
		
		datalist.add(id);
		
		// push the TestCase to the top of the stack
		testItemStack.push(id);

	    }
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}
    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String text) throws SAXException {
	super.endTag(text);

	try {
	    Stack testItemStack = m_ParserHandler.getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof InlineData))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	    InlineData id = (InlineData) testitem;

	    // validated the inline-data name
	    //   If the tag is parsed in, it can not have null text.
	    if (text == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	
	    StringTokenizer tknzr = new StringTokenizer(text, " \t\n\r");
	    if (! tknzr.hasMoreTokens())
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	    id.setData(text);

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
	if (! (tdObject instanceof InlineData))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"InlineData", tdObject.getClass().getName()));

	InlineData data = (InlineData) tdObject;

	String targetname = data.getTargetName();
	if (targetname != null && ! targetname.equals(""))
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_targetname, targetname);

	DataType dt = data.getType();

	if (dt != null) {
	    if (dt.isResource()) {
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_type, TagsImpl.ctStr_attr_extdata_enum_resource);
	    } else if (dt.isIOData()) {
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_type, TagsImpl.ctStr_attr_extdata_enum_iodata);
	    }
	}
    }

  /**
    *   emit the Ref text.
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof InlineData))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"InlineData", tdObject.getClass().getName()));

	InlineData id = (InlineData) tdObject;
	String source = id.getData();
	//   If the tag is parsed in, it can not have null text.
	if (source == null)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));
	
	StringTokenizer tknzr = new StringTokenizer(source, " \t\n\r");
	if (! tknzr.hasMoreTokens())
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));

	m_EmitterHandler.emitText(source);

    }

}
