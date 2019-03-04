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
 * ExternalData_TH - The tag-handler for a Lib tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ExternalData_TH 
 * ============================================================================================ 
 */ 
public class ExternalData_TH extends SingletonTagHandler  {


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
    *   ExternalData_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public ExternalData_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_externaldata;
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
	    String sourcename = null;
	    String type = null;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the sourcename
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_sourcename)) {
			sourcename = attrs.getValue (i);
			// validateSourceName(sourcename);
		    }
		    // Get the type
		    else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_type)) {
			type = attrs.getValue (i);
			// validateType(type);
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

		ExternalData m_ExternalData = DataFactory.createExternalData();
		if (sourcename != null)
		    m_ExternalData.setSourceName(sourcename);
		
		
		// set the data type if it is specified
		if (type != null) { 
		    
		    if (! (type.equals(TagsImpl.ctStr_attr_extdata_enum_resource) || 
			   type.equals(TagsImpl.ctStr_attr_extdata_enum_iodata)))
			    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.attribute.enum.illval",
									     TagsImpl.ctStr_attr_type, type));

		    DataType dt = DataFactory.createDataType(type);
		    m_ExternalData.setType(dt);
		}
		
		datalist.add(m_ExternalData);
		
	    }
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
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
	if (! (tdObject instanceof ExternalData))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"ExternalData", tdObject.getClass().getName()));

	ExternalData data = (ExternalData) tdObject;

	String sourcename = data.getSourceName();
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_sourcename, sourcename);

	DataType dt = data.getType();

	if (dt != null) {
	    if (dt.isResource()) {
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_type, TagsImpl.ctStr_attr_extdata_enum_resource);
	    } else if (dt.isIOData()) {
		m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_type, TagsImpl.ctStr_attr_extdata_enum_iodata);
	    }
	}

	
    }


     

}
