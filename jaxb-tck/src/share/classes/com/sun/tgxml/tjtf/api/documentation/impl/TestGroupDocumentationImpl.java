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

package com.sun.tgxml.tjtf.api.documentation.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.TestGroupDocumentationImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import java.util.ArrayList;
// </importgen>

/**
 * TestGroupDocumentation - 
 *
 * <b>TestGroupDocumentation</b> is the basic interface for describing a JavaTest test description
 * associated with a TestGroup.
 * <p>
 * <b>TestGroupDocumentation</b> contains the following:<br><br>
 * <ul>
 *     <li> TestedPackage
 *     <li> TestedClass
 *     <li> DocElems (Repository Specific Description Entities)
 *  </ul> <br>
 *
 * <p>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestGroupDocumentationImpl
 * ============================================================================================
 */


public  class TestGroupDocumentationImpl extends DocumentationImpl implements TestGroupDocumentation {


    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList   m_assertions;
    private ArrayList   m_docElems;
    private String      m_testedPackage;
    private String      m_testedClass;
    private String      m_memberSig;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestGroupDocumentationImpl() {
	super();
	init();
    }

    public TestGroupDocumentationImpl(String title, String description, ArrayList authors, 
				      ArrayList assertions, String testedPackage, String testedClass,
				      String memberSig, ArrayList docElems ) {
	super(title, description, authors);
	if (assertions == null)
	    m_assertions = new ArrayList();
	else
	    m_assertions = assertions;

	if (docElems == null)
	    m_docElems = new ArrayList();
	else
	    m_docElems = docElems;

	m_testedPackage = testedPackage;
	m_testedClass = testedClass;
	m_memberSig = memberSig;

    }

    private void init() {
        m_assertions = new ArrayList();
        m_docElems = new ArrayList();
	m_testedPackage = null;
	m_testedClass = null;
	m_memberSig = null;
    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the title associated with this documentation.
    *  <p>
    *   This title must be a valid String (for a TestGroup and Library)
    *   and not NULL.  For a TestCase, the title may be either a
    *   valid string or NULL.
    *  <p>
    *   Overrides Documentation.getTitle()
    * <p>
    * @return     The title associated with this documentation
    * @exception TestFileException if the title is NULL
    * @see #setTitle
    */
    public String getTitle()  throws TestFileException {
	String title = super.getTitle();

	if (title == null || title.equals(""))
	    throw new TestFileException("Null title.");

	return title;
    }
     
   /**
    *   Set the title associated with this documentation.
    *  <p>
    *   This title must be a valid String (for a TestGroup and Library)
    *   and not NULL.  For a TestCase, the title may be either a
    *   valid string or NULL.
    *  <p>
    *   Overrides Documentation.setTitle()
    * <p>
    * @param     title The title of the documentation.
    * @exception TestFileException if the title is NULL
    * @see #getTitle
    */
    public void setTitle(String title)  throws TestFileException {
	if (title == null || title.equals(""))
	    throw new TestFileException("Null title.");

	super.setTitle(title);
    }


  /**
    *   Get the Assertions associated with this documentation.
    *  <p>
    *   The Assertions list is always defined, and may contain zero or more entries.
    *   JCK test will not allow InlineAssertion elements
    *  <p>
    * @return     The Assertion list associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.Assertion
    * @see #setAssertions
    */
    public ArrayList getAssertions() {
	return m_assertions;
    }

     
 /**
    *   Set the Assertions associated with this documentation.
    *  <p>
    *   The Assertions list is always defined, and may contain zero or more entries.
    *   JCK test will not allow InlineAssertion elements
    *  <p>
    * @param     assertions The ArrayList of Assertion entries.
    * @see com.sun.tgxml.tjtf.api.documentation.Assertion
    * @see #getAssertions
    */
    public void setAssertions(ArrayList assertions) {
	if (assertions == null)
	    m_assertions.clear();

	m_assertions = assertions;
    }




  /**
    *   Get the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedPackage associated with this documentation
    * @see #setTestedPackage
    */
    public String getTestedPackage() {
	return m_testedPackage;
    }
     
   /**
    *   Set the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @param     testedPackage The name of the TestedPackage.
    * @see #getTestedPackage
    */
    public void setTestedPackage(String testedPackage) {
	m_testedPackage = testedPackage;
    }




  /**
    *   Get the TestedClass associated with this documentation.
    *  <p>
    *   This TestedClass is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedClass associated with this documentation
    * @see #setTestedClass
    */
    public String getTestedClass() {
	return m_testedClass;
    }
     
   /**
    *   Set the TestedClass associated with this documentation.
    *  <p>
    *   This TestedClass is an optional field (required for JCK tests).
    *  <p>
    * @param     testedClass The name of the TestedClass.
    * @see #getTestedClass
    */
    public void setTestedClass(String testedClass) {
	m_testedClass = testedClass;
    }




  /**
    *   Get the member signature associated with this documentation.
    *  <p>
    *   This MemberSig is an optional field (required for JCK tests).
    *  <p>
    * @return     The MemberSig associated with this documentation
    * @see #setMemberSig
    */
    public String getMemberSig() {
	return m_memberSig;
    }
     
   /**
    *   Set the member signature associated with this documentation.
    *  <p>
    *   This MemberSig is an optional field (required for JCK tests).
    *  <p>
    * @param     sig The member signature.
    * @see #getMemberSig
    */
    public void setMemberSig(String sig) {
	m_memberSig = sig;
    }


  /**
    *   Get the (Repository Specific) description-entities associated with this documentation.
    *  <p>
    *   The DocElems list is always defined, and may contain zero or more entries.
    *  <p>
    * @return     The DocElem list associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    * @see #setDocElems
    */
    public ArrayList getDocElems() {
	return m_docElems;
    }

     
 /**
    *   Set the (Repository Specific) description-entities associated with this documentation.
    *  <p>
    *   The DocElem list is always defined, and may contain zero or more entries.
    *  <p>
    * @param     docelems The ArrayList of DocElem entries.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    * @see #getDocElems
    */
    public void setDocElems(ArrayList docelems) {
	if (docelems == null)
	    m_docElems.clear();
	else
	    m_docElems = docelems;
    }



}
