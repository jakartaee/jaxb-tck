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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.TestCase
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.TestCode;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * TestCase - 
 *
 * <b>TestCase</b> is the entity that describes an atomic test. 
 * <b>TestCase</b>s are owned by <b>TestGroup</b>s.  
 * <p>
 * <b>TestCase</b>s derive from <b>TestSourceItem</b>,
 * and contain a <b>TestGroup</b> as their parent. These properties describe
 * the comments, assertions, and descriptions that this class contains.
 * <p>
 * <b>TestCase</b>s contain the following fields:
 * <p>
 * <ul>
 *     <li> test code
 *  </ul> <br>
 * <p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestCaseImpl
 * ============================================================================================
 */


public  class TestCaseImpl extends TestItemImpl implements TestCase  {



    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private TestCode          m_testcode;
    private TestGroup         m_group;
    private TestVariantImpl   m_TestVar;
    private boolean           isGloballyExcluded;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestCaseImpl() {
	super();
	init();
    }

    public TestCaseImpl(String ID, TestCaseDocumentation documentation, TestCaseAttributes attributes, 
			 CodeSet codeset, TestCode testcode, TestGroup group ) {
	super(ID, documentation, attributes, codeset);
	m_testcode = testcode;
	m_group = group;
    }

    public TestCaseImpl(String ID, String VarID, TestCaseDocumentation documentation, TestCaseAttributes attributes, 
			 CodeSet codeset, TestCode testcode, TestGroup group )
            throws TestFileException {
	super(ID, documentation, attributes, codeset);
	m_TestVar = new TestVariantImpl(VarID);
	m_testcode = testcode;
	m_group = group;
    }

    private void init() {
	m_TestVar = new TestVariantImpl();
	m_testcode = null;
	m_group = null;
    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    * Gets the variant identifier associated with the TestCase or Library.
    *  <p>
    * @return  A string containing the identifier in format VarName:VarOrder
    * @see #setVarID
    */
    public String getVarID() {
        return m_TestVar.getVarID();
    }

     
  /**
    * Sets the variant identifier associated with the TestCase or Library.
    * <p>
    * @param  varID The string containing the identifier in format
    *         VarName:VarOrder
    * @throws TestFileException if passed varID is in illegal format
    * @see #getVarID
    * @see #setVarName
    * @see #setVarOrder
    */
    public void setVarID(String varID) throws TestFileException {
       m_TestVar.setVarID(varID);
   }

   /**
    * Sets VarOrder for the variant
    * @params value the string is of the form: "x[.y]",
    *         where x,y are positive integers 
    *         and the value x.y represents a positive decimal value between
    *         00.00 and 99.99.
    * @throws TestFileException if the passed value is in illegal format
    * @see #getVarOrder
    */
   public void setVarOrder(String value) throws TestFileException {
       m_TestVar.setVarOrder(value);
   }

   /**
    * Returns VarOrder as String
    * @see #setVarOrder
    */
   public String getVarOrder() {
        return m_TestVar.getVarOrder();
   }

   /**
    * Returns VarOrder as int.
    *   order must be a value between 0 - 9999 (inclusive)
    * and represents a decimal that is right-shifted by two places.
    */
   public int order() {
        return m_TestVar.order();
   }


   /**
    * Sets variant name for the variant
    * @see #getVarName
    */
   public void setVarName(String value) {
       m_TestVar.setVarName(value);
   }


   /**
    * Returns the variant name if VarID is set, and null if VarID is not set.
    * @see #setVarName
    */
   public String getVarName() {
        return m_TestVar.getVarName();
   }




  /**
    *   Get the Documentation associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    * @see #setTCDocumentation
    */
    public TestCaseDocumentation getTCDocumentation() {
	return (TestCaseDocumentation) getDocumentation();
    }
     
  /**
    *   Set the Documentation associated with this TestItem.
    *  <p>
    * @param     doc The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #getTCDocumentation
    */
    public void setTCDocumentation(TestCaseDocumentation doc) {
	_setDocumentation(doc);
    }




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    * @see #setTCAttributes
    */
    public TestCaseAttributes getTCAttributes() {
	return (TestCaseAttributes) getAttributes();
    } 
     
  /**
    *   Set the Attributes associated with this TestItem.
    *  <p>
    * @param     attrs The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    * @see #getTCAttributes
    */
    public void setTCAttributes(TestCaseAttributes attrs) {
	_setAttributes(attrs);
    }




 /**
    *   Get the (TestCode) code that this TestCase owns.
    *  <p>
    * @return   A (TestCode) code for this TestCase.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    * @see #setTestCode
    */
    public TestCode getTestCode() {
	return m_testcode;
    }
     

     
  /**
    *   Set the (TestCode) code that this TestCase owns.
    *  <p>
    * @param     testcode The (TestCode) code for this TestCase.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    * @see #getTestCode
    */
    public void setTestCode(TestCode testcode) {
	m_testcode = testcode;
    }

     


 /**
    *   Get the (TestGroup) parent of this TestCase.
    *  <p>
    * @return   A (TestGroup) parent for this TestCase, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    * @see #setTestGroup
    */
    public TestGroup getTestGroup() {
	return m_group;
    }
     

     
  /**
    *   Set the (TestGroup) parent of this TestCase.
    *  <p>
    * @param group   A (TestGroup) parent for this TestCase, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    * @see #getTestGroup
    */
    public void setTestGroup(TestGroup group) {
	m_group = group;
    }


   /**
     * Returns true it the test is globally permanently excluded,
     * false otherwise
     */
    public boolean isDeleted() {
        return isGloballyExcluded;
    }

   /**
     * Marks the test as globally permanently excluded if passed
     * argument is true.
     */
    public void setDeleted(boolean v) {
        isGloballyExcluded = v;
    }

}
