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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.TestCaseSpecImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.TestCaseSpec;
import com.sun.tgxml.tjtf.api.documentation.TestTechnique;
import com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue;
import com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect;
import com.sun.tgxml.tjtf.api.documentation.ExpectedResultException;
import java.util.ArrayList;
// </importgen>

/**
 * TestCaseSpec - 
 *
 * <b>TestCaseSpec</b> is the basic interface for describing a JCK API test specification.
 * <p>
 * <b>TestCaseSpec</b> contains the following:<br><br>
 * <ul>
 *     <li> assertions (only AssertionRefs)
 *     <li> test-technique
 *     <li> member-signature
 *     <li> values
 *     <li> precondition
 *     <li> expectedresult
 *     <li> SpecElems (Repository Test Specific Entities)
 *  </ul> <br>
 *
 * <p>
 * A <b>TestCaseSpec</b> is registered in the <b>TestCaseDocumentation</b>of a <b>TestCase</b> 
 *  element.  
 * <p> 
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestCaseSpecImpl
 * ============================================================================================
 */


public  class TestCaseSpecImpl implements TestCaseSpec  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList          m_assertions;
    private ArrayList          m_inputs;
    private ArrayList          m_specElems;
    private String             m_testedPackage;
    private String             m_memberSig;
    private ArrayList          m_preconditions;
    private TestTechnique      m_testTechnique;
    private ExpectedResultValue     m_expValue;
    private ArrayList          m_expSideEffects;
    private ArrayList          m_expExceptions;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestCaseSpecImpl() {
	init();
    }

    public TestCaseSpecImpl(ArrayList assertions, TestTechnique technique,
			    String memberSig, ArrayList inputs,  ArrayList preconditions,
			    ExpectedResultValue expValue, ArrayList sideeffects,  
			    ArrayList exceptions,  ArrayList specElems ) {
	if (assertions == null)
	    m_assertions = new ArrayList();
	else
	    m_assertions = assertions;

	if (inputs == null)
	    m_inputs = new ArrayList();
	else
	    m_inputs = inputs;

	if (specElems == null)
	    m_specElems = new ArrayList();
	else
	    m_specElems = specElems;

	if (sideeffects == null)
	    m_expSideEffects = new ArrayList();
	else
	    m_expSideEffects = sideeffects;

	if (exceptions == null)
	    m_expExceptions = new ArrayList();
	else
	    m_expExceptions = exceptions;

	if (preconditions == null)
	    m_preconditions = new ArrayList();
	else
	    m_preconditions = preconditions;

	m_testTechnique = technique;
	m_memberSig = memberSig;
	m_expValue = expValue;

    }

    private void init() {
	m_assertions = new ArrayList();
	m_inputs = new ArrayList();
	m_specElems = new ArrayList();
	m_preconditions = new ArrayList();

	m_testTechnique = null;
	m_memberSig = null;
	m_preconditions = null;
	m_expValue = null;
	m_expSideEffects = new ArrayList();
	m_expExceptions = new ArrayList();

    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the assertions associated with this TestCaseSpec.
    *   An Assertion is a specification-assertion  that a given test
    *   verifies.
    *  <p>
    *   The assertion list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each author entry is an Assertion.
    *  <p>
    * @return     The assertions associated with this TestCaseSpec
    * @see #setAssertions
    * @see com.sun.tgxml.tjtf.api.documentation.Assertion
    */
    public ArrayList getAssertions() {
	return m_assertions;
    }

     
 /**
    *   Set the assertions associated with this TestCaseSpec.
    *   An Assertion is a specification-assertion  that a given test
    *   verifies.
    *  <p>
    *   The assertion list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each author entry is an Assertion.
    *  <p>
    * @param     Assertions The ArrayList of assertion entries.
    * @see #getAssertions
     * @see com.sun.tgxml.tjtf.api.documentation.Assertion
   */
    public void setAssertions(ArrayList assertions) {
	if (assertions == null)
	    m_assertions.clear();
	else
	    m_assertions = assertions;
    }






  /**
    *   Get the TestTechnique associated with this documentation.
    *  <p>
    *   This TestTechnique may be NULL.  
    *  <p>
    * @return     The TestTechnique associated with this TestCaseSpec
    * @see com.sun.tgxml.tjtf.api.documentation.TestTechnique
    * @see #setTestTechnique
    */
    public TestTechnique getTestTechnique() {
	return m_testTechnique;
    }
     
   /**
    *   Set the TestTechnique associated with this documentation.
    *  <p>
    *   This TestTechnique may be NULL.  
    *  <p>
    * @param     technique The TestTechnique of the TestCaseSpec.
    * @see com.sun.tgxml.tjtf.api.documentation.TestTechnique
    * @see #getTestTechnique
    */
    public void setTestTechnique(TestTechnique technique) {
	m_testTechnique = technique;
    }



  /**
    *   Get the MemberSig associated with this TestCaseSpec.
    *  <p>
    *   This MemberSig may be a valid String
    *   or NULL.  
    *  <p>
    * @return     The MemberSig associated with this TestCaseSpec
    * @see #setMemberSig
    */
    public String getMemberSig() {
	return m_memberSig;
    }
     
   /**
    *   Set the MemberSig associated with this TestCaseSpec.
    *  <p>
    *   This MemberSig may be a valid String
    *   or NULL.  
    *  <p>
    * @param     membersig The MemberSig of the TestCaseSpec.
    * @see #getMemberSig
    */
    public void setMemberSig(String membersig)  {
	m_memberSig = membersig;
    }




  /**
    *   Get the list of values of test input for a given test.
    *  <p>
    *   The input list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each entry is a String.
    *  <p>
    * @return     The test-input values associated with this TestCaseSpec
    * @see #setInputs
    */
    public ArrayList getInputs() {
	return m_inputs;
    }

     
 /**
    *   Set the list of values of test input for a given test.
    *  <p>
    *   The input list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each entry is a String.
    *  <p>
    * @param     inputs The ArrayList of input entries.
    * @see #getInputs
   */
    public void setInputs(ArrayList inputs) {
	if (inputs == null)
	    m_inputs.clear();
	else
	    m_inputs = inputs;
    }





  /**
    *   Get the list of Precondition values associated with this documentation.
    *   A Precondition is the expected state of the system before
    *   a test is executed.
    *  <p>
    *   The Precondition list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each entry is a String.
    *  <p>
    * @return     The list of Preconditions associated with this TestCaseSpec
    * @see #setPreconditions
    */
    public ArrayList getPreconditions() {
	return m_preconditions;
    }
     
   /**
    *   Set the list of Precondition values associated with this documentation.
    *   A Precondition is the expected state of the system before
    *   a test is executed.
    *  <p>
    *   The Precondition list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each entry is a String.
    *  <p>
    * @param     preconditions The list of Preconditions of the TestCaseSpec.
    * @see #getPreconditions
    */
    public void setPreconditions(ArrayList preconditions) {
	if (preconditions == null)
	    m_preconditions.clear();
	else
	    m_preconditions = preconditions;
    }





  /**
    *   Get the ExpectedResultValue associated with this TestCaseSpec.
    *   The ExpectedResults is the expected state of the system after
    *   a test is executed.
    *  <p>
    * @return     The ExpectedResult associated with this TestCaseSpec or NULL
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    * @see #setExpectedResultValue
    */
    public ExpectedResultValue getExpectedResultValue() {
	return m_expValue;
    }
     
   /**
    *   Set the ExpectedResultValue associated with this TestCaseSpec.
    *   The ExpectedResults is the expected state of the system after
    *   a test is executed.
    *  <p>
    * @param     expectedresult The ExpectedResults of the TestCaseSpec.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    * @see #getExpectedResultValue
    */
    public void setExpectedResultValue(ExpectedResultValue expectedresult) {
	m_expValue = expectedresult;
    }


  /**
    *   Get the expected side-effects.
    *  <p>
    * @return     The SpecElems associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    * @see #setExpectedResultSideEffects
    */
    public ArrayList getExpectedResultSideEffects() {
	return m_expSideEffects;
    }

     
 /**
    *   Set the expected side-effects.
    *  <p>
    * @param     sideeffects The ArrayList of expected side-effects.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    * @see #getExpectedResultSideEffects
    */
    public void setExpectedResultSideEffects(ArrayList sideeffects) {
	if (sideeffects == null)
	    m_expSideEffects.clear();
	else
	    m_expSideEffects = sideeffects;
    }


  /**
    *   Get the expected exceptions.
    *  <p>
    * @return     The SpecElems associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    * @see #setExpectedResultExceptions
    */
    public ArrayList getExpectedResultExceptions() {
	return m_expExceptions;
    }

     
 /**
    *   Set the expected exceptions.
    *  <p>
    * @param     exceptions The ArrayList of expected exceptions.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    * @see #getExpectedResultExceptions
    */
    public void setExpectedResultExceptions(ArrayList exceptions) {
	if (exceptions == null)
	    m_expExceptions.clear();
	else
	    m_expExceptions = exceptions;
    }


  /**
    *   Get the SpecElems associated with this documentation.
    *  <p>
    *   The SpecElems list is always defined, and may contain zero or more entries.
    *  <p>
    * @return     The SpecElems associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    * @see #setSpecElems
    */
    public ArrayList getSpecElems() {
	return m_specElems;
    }

     
 /**
    *   Set the SpecElems associated with this documentation.
    *  <p>
    *   The SpecElems list is always defined, and may contain zero or more entries.
    *  <p>
    * @param     specelems The ArratList of author entries.
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    * @see #getSpecElems
    */
    public void setSpecElems(ArrayList specelems) {
	if (specelems == null)
	    m_specElems.clear();
	else
	    m_specElems = specelems;
    }



}
