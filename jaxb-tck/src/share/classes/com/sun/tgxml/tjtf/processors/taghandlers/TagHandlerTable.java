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

package com.sun.tgxml.tjtf.processors.taghandlers;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.XMLObj;


 
/** 
 * TagHandlerTable - The tag-handler table is a collection
 * of TagHandler objects.  The table manages them on behalf
 * of a processor (such as a parser or emitter).
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TagHandlerTable 
 * ============================================================================================ 
 */ 
public class TagHandlerTable   {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 

    /**  The (internal) collection of tag-handlers. */
    private TreeMap m_table;

    /**  The docname of the dtd. */
    private String  m_docName;

    /**  The (URI) path to the dtd. */
    private String  m_docURI;

    /**  The map of root-object names to tag-handler names. */
    private Hashtable m_roots;



   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   TagHandlerTable constructor - 
    *       Initialize our internal fields. 
    */ 
    public TagHandlerTable() {
	m_table = new TreeMap();
	m_roots = new Hashtable();
    }


    //------------------------------------------------------------------------------
    //  Table maintenance
    //------------------------------------------------------------------------------

   /** 
    *   Add a root-association to the Table.
    * <p>
    *   A root-handler is an association between a specific
    *   class (XMLObj.class) and it's external tagname.
    *<p>
    *  For example, in the TGXML TestDescription, you would add
    *  two roots: <br>
    * <code><pre>
    *    addRootHandler(TestGroup.class, "TestGroup");
    *    addRootHandler(Library.class, "Library");
    * <p>
    *   @param rootClass
    *   @param handlerTag 
    *   @see #getDocName()
    */ 
    public void addRootAssociation(Class rootClass, String handlerTag) throws TestFileException {
	if (rootClass == null)
	    throw new TestFileException(LibResHandler.getResStr("tht.error.rootclass.null"));

	if (handlerTag == null || handlerTag.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("tht.error.roottag.null"));

	m_roots.put(rootClass, handlerTag);
    }


   /** 
    *   Get the tag associated with the root object
    * <p>
    *   @param root The root object.
    *   @return The string containing the name of the tag-handler for that root.
    *   @throws TestFileException if the root is null.
    */ 
    public String getRootTag(XMLObj root) throws TestFileException {
	if (root == null)
	    throw new TestFileException(LibResHandler.getResStr("tht.error.root.null"));


	Iterator it = m_roots.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry entry = (Map.Entry) it.next();
	    Class cl = (Class) entry.getKey();
	    if (cl.isInstance(root)) 
		return (String) entry.getValue();
	}

	return null;
    }


   /** 
    *   Set the name of an (XML) document that this table handles.
    * <p>
    *   @param docname The name of the (XML) document.
    *   @see #getDocName
    */ 
    public void setDocName(String docname) {
	m_docName = docname;
    }



   /** 
    *   Set the name of an (XML) document that this table handles.
    * <p>
    *   @return The name of the (XML) document.
    * @throws TestFileException if the docname has not been set.
    *   @see #setDocName
    */ 
    public String getDocName() throws TestFileException {
	if (m_docName == null || m_docName.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("tht.error.docname.null"));
	return m_docName;
    }


   /** 
    *   Set the location (URI) of an (XML) document that this table handles.
    * <p>
    *   @param docURI The location of the (XML) document.
    *   @see #getDocURI
    */ 
    public void setDocURI(String docURI) {
	m_docURI = docURI;
    }



   /** 
    *   Set the name of an (XML) document that this table handles.
    * <p>
    *   @return The name of the (XML) document.
    * @throws TestFileException if the docname has not been set.
    *   @see #setDocName
    */ 
    public String getDocURI() throws TestFileException {
	if (m_docURI == null || m_docURI.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("tht.error.docuri.null"));
	return m_docURI;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   put a TagHandler into the table.
    *  <p>
    * @param th A tag-handler to be entered into the table.
    * @throws TestFileException if the TagHandler is null or a duplicate.
    * @see #get
    */
    public void put(TagHandler th) throws TestFileException {
	if (th == null) {
	    throw new TestFileException(LibResHandler.getResStr("parser.error.taghandlertable.nullentry"));
	}

	String tagName = th.getTagName();
	if (tagName == null || tagName.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("parser.error.taghandlertable.nullname"));

	TagHandler current = (TagHandler) m_table.get(tagName);
	if (current != null)
	    throw new TestFileException(LibResHandler.getResStr("parser.error.taghandlertable.dupentry", tagName));

	m_table.put(tagName, th);
    }



  /**
    *   Get a TagHandler with the given name.
    *  <p>
    * @param tagName The name of  the TagHandler being fetched.
    * @return A TagHandler, or null if non exists with the given name.
    * @throws TestFileException if the tagName is NULL.
    * @see #put
    */
    public TagHandler get(String tagName) throws TestFileException {
	if (tagName == null || tagName.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("parser.error.taghandlertable.nullfetch"));

	return (TagHandler)m_table.get(tagName);
    }


         
  /**
    *   Get a TagHandler with the given name.
    *  <p>
    * @return An iterator over the TagHandlers.
    * @see #put
    */
    public Iterator iterator() {
	return m_table.values().iterator();
    }

}
