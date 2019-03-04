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

package com.sun.tgxml.tjtf.api.tests.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.impl.TestItemImpl
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.documentation.Documentation;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import java.util.ArrayList;
// </importgen>

/**
 * TestItem - 
 *
 * <b>TestItem</b> is the marker interface to a class structure that describes the root of a test description.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestItemImpl  (marker interface)
 * ============================================================================================
 */


public  class TestItemImpl implements TestItem {


    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private String             m_ID;
    private Documentation      m_documentation;
    private Attributes         m_attributes;
    private CodeSet            m_codeset;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestItemImpl() {
	init();
    }

    public TestItemImpl(String ID, Documentation documentation, Attributes attributes, CodeSet codeset ) {
	m_ID = ID;
	m_documentation = documentation;
	m_attributes = attributes;
	m_codeset = codeset;
    }

    private void init() {
	m_ID = null;
	m_documentation = null;
	m_attributes = null;
	m_codeset = null;
    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


    /**
     *   Get the identifier associated with the TestGroup, TestClass, or Library.
     *  <p>
     * @exception   TestFileException if ID is unset.
     * @return   A string containing the identifier.
     * @see #setID
     */
    public String getID() throws TestFileException {
	if (m_ID == null || m_ID.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.tg.id.null"));
	return m_ID;
    }

     
    /**
     *   Set the identifier associated with the TestGroup, TestClass, or Library.
     *  <p>
     * @param     id The string containing the identifier.
     * @exception   TestFileException if id is null.
     * @see #getID
     */
    public void setID(String id) throws TestFileException{
	if (id == null || id.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.tg.id.null"));
	m_ID = id;
    }



  /**
    *   Get the Documentation associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.Documentation
    */
    public Documentation getDocumentation() {
	return m_documentation;
    }
     


  /**
    *   Set the Documentation associated with this TestItem (SPI).
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.Documentation
    */
    public void _setDocumentation(Documentation doc) {
	m_documentation = doc;
    }
     




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.Attributes
    */
    public Attributes getAttributes() {
	return m_attributes;
    }
     


  /**
    *   Set the Attributes associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.Documentation
    */
    public void _setAttributes(Attributes attrs) {
	m_attributes = attrs;
    }
     


  /**

    *   Get the CodeSet for this TestItem.
    *  <p>
    * @return   A CodeSet object (or NULL).
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    * @see #setCodeSet
    */
    public CodeSet getCodeSet() {
	return m_codeset;
    }
     

     
  /**
    *   Set the CodeSet for this TestItem.
    *  <p>
    * @param     codeset The CodeSet object (or NULL).
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    * @see #getCodeSet
    */
    public void setCodeSet(CodeSet codeset) {
	m_codeset = codeset;
    }




}
