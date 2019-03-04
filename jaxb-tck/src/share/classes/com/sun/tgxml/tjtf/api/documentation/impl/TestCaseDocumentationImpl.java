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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.TestCaseDocumentationImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import java.util.ArrayList;
// </importgen>

/**
 * TestCaseDocumentation - 
 *
 * <b>TestCaseDocumentation</b> is the basic interface for describing a JavaTest test description
 * associated with a TestCase.
 * <p>
 * <b>TestCaseDocumentation</b> contains the following:<br><br>
 * <ul>
 *     <li> TestSpecs
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
 *    TestCaseDocumentationImpl
 * ============================================================================================
 */


public  class TestCaseDocumentationImpl extends DocumentationImpl implements TestCaseDocumentation {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList   m_specs;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestCaseDocumentationImpl() {
	super();
	init();
    }

    public TestCaseDocumentationImpl(String title, String description, ArrayList authors, ArrayList testCaseSpecs) {
	super(title, description, authors);
	if (testCaseSpecs == null)
	    init();
	else
	    m_specs = testCaseSpecs;
    }

    private void init() {
        m_specs = new ArrayList();
    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------   




  /**
    *   Get the TestCaseSpecs associated with this documentation.
    *  <p>
    *   The TestCaseSpecs list is always defined, and may contain zero or more entries.
    *  <p>
    * @return     The Assertion list associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    * @see #setTestCaseSpecs
    */
    public ArrayList getTestCaseSpecs() {
	return m_specs;
    }

     
 /**
    *   Set the TestCaseSpecs associated with this documentation.
    *  <p>
    *   The TestCaseSpecs list is always defined, and may contain zero or more entries.
    *  <p>
    * @param     assertions The ArrayList of TestCaseSpec entries.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    * @see #getTestCaseSpecs
    */
    public void setTestCaseSpecs(ArrayList tcspecs) {
	if (tcspecs == null)
	    m_specs.clear();
	else
	    m_specs = tcspecs;
    }



}
