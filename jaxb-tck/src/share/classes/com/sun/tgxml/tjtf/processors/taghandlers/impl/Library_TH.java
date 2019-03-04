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

import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.LibDocumentation;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * Library_TH - The tag-handler for a Library tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    Library_TH 
 * ============================================================================================ 
 */ 
public class Library_TH extends TagHandlerImpl  {


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
    *   Library_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public Library_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_library;
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
	    String VarID = null;
            boolean isInline = false;
	    if (attrs != null) {
		for (int i = 0; i < attrs.getLength (); i++) {
		    // Get the ID
		    if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_id)) {
			ID = attrs.getValue (i);
		    }
		    // Get the ID
		    else if ((attrs.getQName (i)).equals(TagsImpl.ctStr_attr_varid)) {
			VarID = attrs.getValue (i);		    
		    } 
                    else if ((attrs.getQName (i)).equals
                           (TagsImpl.ctStr_attr_inline)) {
			String inlineValue = attrs.getValue (i);
                        isInline = TagsImpl.isTrueOrFalse(inlineValue);
                    }
		    // unknown attribute
		    else 
			// Unknown spec attribute
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.unknownTDAttr", attrs.getQName (i)));
		}

		// A library can now be embedded in a TG, 
		// so the multi-root assertion is only true when there is something on the stack.
		Stack testItemStack = getParserHandler().getStack();
		Object tgo = testItemStack.empty() ? null : testItemStack.peek();

		if (! testItemStack.empty() && !(tgo instanceof TestGroup)) 
		    // Library is inlined in something other than a TestGroup
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testgroup));

		if ( testItemStack.isEmpty() && getParserHandler().getRoot() != null)
		    // the Stack is not consistent with the parser (parser already had a root)
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.multipleRoot"));
		    		
		Library lib = null;

		if (tgo == null) {
                    lib = TestFactory.createLibrary();
		    m_ParserHandler.setRoot(lib);
		} else {
		    TestGroup tg = (TestGroup) tgo;
                    lib = TestFactory.createInlineLibrary();
                    ((InlineLibrary)lib).setTestGroup(tg);
		    ArrayList libs = tg.getLibraries();
		    if (libs == null) {
			libs = new ArrayList();
			tg.setLibraries(libs);
		    }
		    
		    Library other = tg.getLibrary(ID, VarID);
		    if (other != null)
			m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.tg.library.dupvariant", ID, VarID));

		    libs.add(lib);
		}

		lib.setID(ID);
		lib.setVarID(VarID);
		lib.setInline(isInline);

		testItemStack.push(lib);

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
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = m_ParserHandler.getStack();

	    if (testItemStack.isEmpty()) 
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    Object testitem = testItemStack.pop();
	    if (! (testitem instanceof Library))
		 m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	    // the item on the stack is either NULL (Root Library), or TestGroup (InlineLibrary)
	    if (!testItemStack.isEmpty()) {
		// the Library may be inline.
		Object tgo = testItemStack.peek();
		if (! (tgo instanceof TestGroup))
		    // Library is not a Root, and it is not inline (from a TestGroup).
		    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", TagsImpl.ctStr_tag_testgroup));
	    }

	} catch (EmptyStackException e) {
	     m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}
    }
     
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
          
  /**
    *   emit a tags attributes.
    *  <p>
    */
    public void emitAttributes(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof Library))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"Library", tdObject.getClass().getName()));

	Library lib = (Library) tdObject;
	m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_id, lib.getID());
	String varID = lib.getVarID();
	if (varID != null && ! varID.equals(""))
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_varid, varID);
	if (lib.isInline() && !(lib instanceof InlineLibrary))
	    m_EmitterHandler.emitAttribute(TagsImpl.ctStr_attr_inline, "true");
    }

          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof Library))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
								"Library", tdObject.getClass().getName()));

	Library lib = (Library) tdObject;

	LibDocumentation libd = lib.getLibDocumentation();
	if (libd != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_librarydocumentation, libd);

	LibAttributes liba = lib.getLibAttributes();
	if (liba != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_libraryattributes, liba);

	CodeSet cs = lib.getCodeSet();
	if (cs != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_codeset, cs);
    }

}
