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
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

/** 
 * CodeSet_TH - The tag-handler for a CodeSet tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    CodeSet_TH 
 * ============================================================================================ 
 */ 
public class CodeSet_TH extends TagHandlerImpl  {


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
    *   CodeSet_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public CodeSet_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_codeset;
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
	CodeSet cs = null;

	super.startTag(attrs);
	Stack testItemStack = m_ParserHandler.getStack();
	Object testitem = testItemStack.peek();
	if (! (testitem instanceof TestItem)  )
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.testitem.invcontext", getTagName()));

	cs = CodeFactory.createCodeSet();
	TestItem ti = (TestItem) testitem;
	ti.setCodeSet(cs);
	testItemStack.push(cs);
    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = getParserHandler().getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof CodeSet))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
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
	if (! (tdObject instanceof CodeSet))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"CodeSet", tdObject.getClass().getName()));

	CodeSet cs = (CodeSet) tdObject;


	ArrayList dependencies = cs.getDependencies();
	if (dependencies != null && dependencies.size() > 0)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_dependency, dependencies);



	ArrayList imports = cs.getImports();
	if (imports != null) {
	    Iterator it1 = imports.iterator();

	    while (it1.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_import, it1.next());
	    }
	}

	String executeargs = cs.getExecuteArgs();
	if (executeargs != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_executeargs, executeargs);


	String context = cs.getContext();
	if (context != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_context, context);


	String baseclass = cs.getBaseClass();
	if (baseclass != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_baseclass, baseclass);


	ArrayList exports = cs.getExports();
	if (exports != null) {
	    Iterator it1 = exports.iterator();

	    while (it1.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_export, it1.next());
	    }
	}


	SupportCode supportcode = cs.getSupportCode();
	if (supportcode != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_supportcode, supportcode);


	ArrayList supportclasses = cs.getSupportClasses();
	if (supportclasses != null) {
	    Iterator it2 = supportclasses.iterator();
	    SupportClass sc = null;

	    while (it2.hasNext()) {
		sc = (SupportClass) it2.next();

		if (sc instanceof ExternalSupportClass)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_externalsupportclass, sc);
		else if (sc instanceof InlineSupportClass)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_inlinesupportclass, sc);
	    }
	}



	ArrayList data = cs.getData();
	if (data != null) {
	    Iterator it3 = data.iterator();
	    Data datum = null;

	    while (it3.hasNext()) {
		datum = (Data) it3.next();

		if (datum instanceof ExternalData)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_externaldata, datum);
		else if (datum instanceof InlineData)
		    m_EmitterHandler.emit(TagsImpl.ctStr_tag_inlinedata, datum);
	    }
	}


    }

}
