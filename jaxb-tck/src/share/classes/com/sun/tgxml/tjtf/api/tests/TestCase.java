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

package com.sun.tgxml.tjtf.api.tests;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.Testgroup
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.code.TestCode;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * TestCase - 
 *
 * <b>TestCase</b> is the entity that describes a single (atomic) test case. 
 * <b>TestCase</b>s are owned by <b>TestGroup</b>s.  
 * <p>
 * <b>TestCase</b>s derive from <b>TestItem</b>,and contain all of the
 * descriptions, attributes, and code that is relevant to this <b>TestCase</b>.
 * <p>
 * <b>TestCase</b>s contain the following fields:
 * <p>
 * <ul>
 *     <li> ID
 *     <li> VarID
 *     <li> TestCaseDocumentation
 *     <li> TestCaseAttributes
 *     <li> CodeSet
 *     <li> TestCode
 *  </ul> <br>
 * <p>
 *
 * @version 	1.1, 10/23/02
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestCase
 * ============================================================================================
 */


public  interface TestCase extends VariableTestItem, TestGroupComponent {

    /*
    * ============================================================================================
    *    Member Fields
    * ============================================================================================
    */


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

  /**
    *   Get the Documentation associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    * @see #setTCDocumentation
    */
    public TestCaseDocumentation getTCDocumentation();
     
  /**
    *   Set the Documentation associated with this TestItem.
    *  <p>
    * @param     doc The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #getTCDocumentation
    */
    public void setTCDocumentation(TestCaseDocumentation doc);




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    * @see #setTCAttributes
    */
    public TestCaseAttributes getTCAttributes();
     
  /**
    *   Set the Attributes associated with this TestItem.
    *  <p>
    * @param     attrs The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    * @see #getTCAttributes
    */
    public void setTCAttributes(TestCaseAttributes attrs);




 /**
    *   Get the (TestCode) code that this TestCase owns.
    *  <p>
    * @return   A (TestCode) code for this TestCase, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    * @see #setTestCode
    */
    public TestCode getTestCode();
     

     
  /**
    *   Set the (TestCode) code that this TestCase owns.
    *  <p>
    * @param     testcode The (TestCode) code for this TestCase, or NULL.
    * @throws TestFileException if testcode is invalid.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    * @see #getTestCode
    */
    public void setTestCode(TestCode testcode) throws TestFileException;
     
   /**
     * Returns true it the test is globally permanently excluded,
     * false otherwise
     */
    public boolean isDeleted();

   /**
     * Marks the test as globally permanently excluded if the passed
     * argument is true.
     */
    public void setDeleted(boolean v);
}
