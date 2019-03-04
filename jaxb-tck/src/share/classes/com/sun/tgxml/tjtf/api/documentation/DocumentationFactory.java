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

package com.sun.tgxml.tjtf.api.documentation;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.DocumentationFactory
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.impl.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
// </importgen>

/**
 * DocumentationFactory - 
 *
 * <b>DocumentationFactory</b> is a static factory class for creating documentation objects (TestGroupDocumentation,
 * TestCaseDocumentation, LibDocumentation, AssertionRef, InlineAssertion, ExpectedResultException, ExpectedResultSideEffect,
 * ExpectedResultValue, SpecElem, TestCaseSpec, TestTechnique). 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    DocumentationFactory
 * ============================================================================================
 */


public  final class DocumentationFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private DocumentationFactory () {

    }

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
    //  Factories
    //------------------------------------------------------------------------------


  /**
    *   Create an (uninitialized) TestGroupDocumentation object.
    *  <p>
    * @return   a new TestGroupDocumentation.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    */
    static public TestGroupDocumentation  createTestGroupDocumentation() {
	return new TestGroupDocumentationImpl();
    }
     

  /**
    *   Create an (cannon) TestGroupDocumentation object.
    *  <p>
    * @param title The TestGroupDocumentation's title
    * @param description The TestGroupDocumentation's description
    * @param authors The TestGroupDocumentation's authors
    * @param assertions The TestGroupDocumentation's assertions
    * @param testedPackage The TestGroupDocumentation's testedPackage
    * @param testedClass The TestGroupDocumentation's testedClass
    * @param memberSig The TestGroupDocumentation's memberSig
    * @param docElems The TestGroupDocumentation's docElems
    * @return   a new TestGroupDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    */
    static public TestGroupDocumentation  createTestGroupDocumentation (String title, String description, ArrayList authors, 
				      ArrayList assertions, String testedPackage, String testedClass,
				      String memberSig, ArrayList docElems ) throws TestFileException {
	TestGroupDocumentation tgd = createTestGroupDocumentation();
	tgd.setTitle(title);
	tgd.setDescription(description);
	tgd.setAuthors(authors);
	tgd.setAssertions(assertions);
	tgd.setTestedPackage(testedPackage);
	tgd.setTestedClass(testedClass);
	tgd.setMemberSig(memberSig);
	tgd.setDocElems(docElems);

	return tgd;
    }
     


  /**
    *   Create an (clone) TestGroupDocumentation object.
    *  <p>
    * @param origTGD The TestGroupDocumentation to be cloned
    * @return   a new (cloned) TestGroupDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    */
    static public TestGroupDocumentation  cloneTestGroupDocumentation (TestGroupDocumentation origTGD ) 
	throws TestFileException {
	if (origTGD == null)
	    return null;

	TestGroupDocumentation cloneTGD = createTestGroupDocumentation();

	// let sub-classes override their clone fields
	cloneOverrideTestGroupDocumentation(origTGD, cloneTGD);


	cloneTGD.setTitle(origTGD.getTitle());
	cloneTGD.setDescription(origTGD.getDescription());

	ArrayList origAuth = origTGD.getAuthors();
	if (origAuth != null) {
	    ArrayList cloneAuth = new ArrayList();
	    Iterator it = origAuth.iterator();
	    while (it.hasNext()) {
		cloneAuth.add(it.next());
	    }
	    cloneTGD.setAuthors(cloneAuth);
	}

	ArrayList origAsserts = origTGD.getAssertions();
	if (origAsserts != null) {
	    ArrayList cloneAsserts = new ArrayList();
	    Iterator it2 = origAsserts.iterator();
	    while (it2.hasNext()) {
		Assertion assertion = (Assertion) it2.next();
		if (assertion instanceof AssertionRef)
		    cloneAsserts.add(cloneAssertionRef((AssertionRef) assertion));
		else if (assertion instanceof InlineAssertion)
		    cloneAsserts.add(cloneInlineAssertion((InlineAssertion) assertion));
	    }
	    cloneTGD.setAssertions(cloneAsserts);
	}

	cloneTGD.setTestedPackage(origTGD.getTestedPackage());
	cloneTGD.setTestedClass((origTGD.getTestedClass()));
	cloneTGD.setMemberSig((origTGD.getMemberSig()));

	ArrayList origDE = origTGD.getDocElems();
	if (origDE != null) {
	    ArrayList cloneDE = new ArrayList();
	    Iterator it3 = origDE.iterator();
	    while (it3.hasNext()) {
		cloneDE.add(it3.next());
	    }
	    cloneTGD.setDocElems(cloneDE);
	}

	return cloneTGD;
    }
     



  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origTGD The TestGroupDocumentation to be cloned
    * @param cloneTGD The TestGroupDocumentation clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    */
    static public void  cloneOverrideTestGroupDocumentation (TestGroupDocumentation origTGD,
							     TestGroupDocumentation cloneTGD ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) TestCaseDocumentation object.
    *  <p>
    * @return   a new TestCaseDocumentation.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    */
    static public TestCaseDocumentation  createTestCaseDocumentation() {
	return new TestCaseDocumentationImpl();
    }
     

  /**
    *   Create an (cannon) TestCaseDocumentation object.
    *  <p>
    * @param title The TestCaseDocumentation's title
    * @param description The TestCaseDocumentation's description
    * @param authors The TestCaseDocumentation's authors
    * @param testCaseSpecs The TestCaseDocumentation's test-case Specs
    * @return   a new TestCaseDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    */
    static public TestCaseDocumentation  createTestCaseDocumentation (String title, String description, ArrayList authors, 
								      ArrayList testCaseSpecs ) throws TestFileException {
	TestCaseDocumentation tcd = createTestCaseDocumentation();
	tcd.setTitle(title);
	tcd.setDescription(description);
	tcd.setAuthors(authors);
	tcd.setTestCaseSpecs(testCaseSpecs);

	return tcd;
    }



  /**
    *   Create an (clone) TestCaseDocumentation object.
    *  <p>
    * @param origTCD The TestCaseDocumentation to be cloned
    * @return   a new (cloned) TestCaseDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    */
    static public TestCaseDocumentation  cloneTestCaseDocumentation (TestCaseDocumentation origTCD ) 
	throws TestFileException {
	if (origTCD == null)
	    return null;
	TestCaseDocumentation cloneTCD = createTestCaseDocumentation();

	// let sub-classes override their clone fields
	cloneOverrideTestCaseDocumentation(origTCD, cloneTCD);


	cloneTCD.setTitle(origTCD.getTitle());
	cloneTCD.setDescription(origTCD.getDescription());

	ArrayList origAuth = origTCD.getAuthors();
	if (origAuth != null) {
	    ArrayList cloneAuth = new ArrayList();
	    Iterator it = origAuth.iterator();
	    while (it.hasNext()) {
		cloneAuth.add(it.next());
	    }
	    cloneTCD.setAuthors(cloneAuth);
	}


	ArrayList origTCSpecs = origTCD.getTestCaseSpecs();
	if (origTCSpecs != null) {
	    ArrayList cloneTCSpecs = new ArrayList();
	    Iterator it1 = origTCSpecs.iterator();
	    while (it1.hasNext()) {
		cloneTCSpecs.add(it1.next());
	    }
	    cloneTCD.setTestCaseSpecs(cloneTCSpecs);
	}


	return cloneTCD;
    }
     


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origTCD The TestCaseDocumentation to be cloned
    * @param cloneTCD The TestCaseDocumentation clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    */
    static public void  cloneOverrideTestCaseDocumentation (TestCaseDocumentation origTCD,
							     TestCaseDocumentation cloneTCD ) throws TestFileException {

    }






  /**
    *   Create an (uninitialized) TestCaseSpec object.
    *  <p>
    * @return   a new TestCaseSpec.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    */
    static public TestCaseSpec  createTestCaseSpec() {
	return new TestCaseSpecImpl();
    }
     

  /**
    *   Create an (cannon) TestCaseSpec object.
    *  <p>
    * @param assertions The TestCaseSpec's assertions
    * @param technique The TestCaseSpec's test-technique
    * @param memberSig The TestCaseSpec's tested member signature
    * @param inputs The TestCaseSpec's described inputs
    * @param preconditions The TestCaseSpec's described preconditions
    * @param expValue The TestCaseSpec's described expected result values
    * @param sideeffects The TestCaseSpec's described expected result side-effects
    * @param exceptions The TestCaseSpec's described expected exceptions
    * @param specElems The TestCaseSpec's spec-elems
    * @return   a new TestCaseSpec.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    */
    static public TestCaseSpec  createTestCaseSpec (ArrayList assertions, TestTechnique technique,
			    String memberSig, ArrayList inputs,  ArrayList preconditions,
			    ExpectedResultValue expValue, ArrayList sideeffects,  
			    ArrayList exceptions,  ArrayList specElems ) throws TestFileException {
	TestCaseSpec tcs = createTestCaseSpec();
	tcs.setAssertions(assertions);
	tcs.setTestTechnique(technique);
	tcs.setMemberSig(memberSig);
	tcs.setInputs(inputs);
	tcs.setPreconditions(preconditions);
	tcs.setExpectedResultValue(expValue);
	tcs.setExpectedResultSideEffects(sideeffects);
	tcs.setExpectedResultExceptions(exceptions);
	tcs.setSpecElems(specElems);

	return tcs;
    }



  /**
    *   Create an (clone) TestCaseSpec object.
    *  <p>
    * @param origTCS The TestCaseSpec to be cloned
    * @return   a new (cloned) TestCaseSpec.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    */
    static public TestCaseSpec  cloneTestCaseSpec (TestCaseSpec origTCS ) 
	throws TestFileException {
	if (origTCS == null)
	    return null;
	TestCaseSpec cloneTCS = createTestCaseSpec();

	// let sub-classes override their clone fields
	cloneOverrideTestCaseSpec(origTCS, cloneTCS);


	ArrayList origAsserts = origTCS.getAssertions();
	if (origAsserts != null) {
	    ArrayList cloneAsserts = new ArrayList();
	    Iterator it = origAsserts.iterator();
	    while (it.hasNext()) {
		Assertion assertion = (Assertion) it.next();
		if (assertion instanceof AssertionRef)
		    cloneAsserts.add(cloneAssertionRef((AssertionRef) assertion));
		else if (assertion instanceof InlineAssertion)
		    cloneAsserts.add(cloneInlineAssertion((InlineAssertion) assertion));
	    }
	    cloneTCS.setAssertions(cloneAsserts);
	}




	cloneTCS.setTestTechnique(origTCS.getTestTechnique());
	cloneTCS.setMemberSig(origTCS.getMemberSig());

	ArrayList origInputs = origTCS.getInputs();
	if (origInputs != null) {
	    ArrayList cloneInputs = new ArrayList();
	    Iterator it1 = origInputs.iterator();
	    while (it1.hasNext()) {
		Input origInput = (Input) it1.next();
		Input cloneInput = cloneInput(origInput);
		cloneInputs.add(cloneInput);
	    }
	    cloneTCS.setInputs(cloneInputs);
	}


	ArrayList origPres = origTCS.getPreconditions();
	if (origPres != null) {
	    ArrayList clonePres = new ArrayList();
	    Iterator it2 = origPres.iterator();
	    while (it2.hasNext()) {
		clonePres.add(it2.next());
	    }
	    cloneTCS.setInputs(clonePres);
	}

	cloneTCS.setExpectedResultValue(cloneExpectedResultValue(origTCS.getExpectedResultValue()));

	ArrayList origSEs = origTCS.getExpectedResultSideEffects();
	if (origSEs != null) {
	    ArrayList cloneSEs = new ArrayList();
	    Iterator it3 = origSEs.iterator();
	    while (it3.hasNext()) {
		ExpectedResultSideEffect origSE = (ExpectedResultSideEffect) it3.next();
		ExpectedResultSideEffect cloneSE = cloneExpectedResultSideEffect(origSE);
		cloneSEs.add(cloneSE);
	    }
	    cloneTCS.setExpectedResultSideEffects(cloneSEs);
	}


	ArrayList origEs = origTCS.getExpectedResultExceptions();
	if (origEs != null) {
	    ArrayList cloneEs = new ArrayList();
	    Iterator it4 = origEs.iterator();
	    while (it4.hasNext()) {
		ExpectedResultException origE = (ExpectedResultException) it4.next();
		ExpectedResultException cloneE = cloneExpectedResultException(origE);
		cloneEs.add(cloneE);
	    }
	    cloneTCS.setExpectedResultExceptions(cloneEs);
	}

	ArrayList origSpecElems = origTCS.getSpecElems();
	if (origSpecElems != null) {
	    ArrayList cloneSpecElems = new ArrayList();
	    Iterator it5 = origEs.iterator();
	    while (it5.hasNext()) {
		SpecElem origSE = (SpecElem) it5.next();
		SpecElem cloneSE = cloneSpecElem(origSE);
		cloneSpecElems.add(cloneSE);
	    }
	    cloneTCS.setSpecElems(cloneSpecElems);
	}



	return cloneTCS;
    }
     


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origTCS The TestCaseSpec to be cloned
    * @param cloneTCS The TestCaseSpec clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseSpec
    */
    static public void  cloneOverrideTestCaseSpec (TestCaseSpec origTCS,
							     TestCaseSpec cloneTCS ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) LibDocumentation object.
    *  <p>
    * @return   a new LibDocumentation.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    */
    static public LibDocumentation  createLibDocumentation() {
	return new LibDocumentationImpl();
    }
     

  /**
    *   Create an (cannon) LibDocumentation object.
    *  <p>
    * @param title The LibDocumentation's title
    * @param description The LibDocumentation's description
    * @param authors The LibDocumentation's authors
    * @return   a new LibDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    */
    static public LibDocumentation  createLibDocumentation (String title, String description, 
								      ArrayList authors ) throws TestFileException {
	LibDocumentation libd = createLibDocumentation();
	libd.setTitle(title);
	libd.setDescription(description);
	libd.setAuthors(authors);

	return libd;
    }



  /**
    *   Create an (clone) LibDocumentation object.
    *  <p>
    * @param origLD The TestCaseDocumentation to be cloned
    * @return   a new (cloned) TestCaseDocumentation.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation
    */
    static public LibDocumentation  cloneLibDocumentation (LibDocumentation origLD ) 
	throws TestFileException {
	if (origLD == null)
	    return null;

	LibDocumentation cloneLD = createLibDocumentation();

	// let sub-classes override their clone fields
	cloneOverrideLibDocumentation(origLD, cloneLD);


	cloneLD.setTitle(origLD.getTitle());
	cloneLD.setDescription(origLD.getDescription());

	ArrayList origAuth = origLD.getAuthors();
	if (origAuth != null) {
	    ArrayList cloneAuth = new ArrayList();
	    Iterator it = origAuth.iterator();
	    while (it.hasNext()) {
		cloneAuth.add(it.next());
	    }
	    cloneLD.setAuthors(cloneAuth);
	}

	return cloneLD;
    }
     


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origLD The LibDocumentation to be cloned
    * @param cloneLD The LibDocumentation clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    */
    static public void  cloneOverrideLibDocumentation (LibDocumentation origLD,
							     LibDocumentation cloneLD ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) AssertionRef object.
    *  <p>
    * @return   a new AssertionRef.
    * @see com.sun.tgxml.tjtf.api.documentation.AssertionRef
    */
    static public AssertionRef  createAssertionRef() {
	return new AssertionRefImpl();
    }
     

  /**
    *   Create an (cannon) AssertionRef object.
    *  <p>
    * @param id The AssertionRef's ID
    * @return   a new AssertionRef.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.AssertionRef
    */
    static public AssertionRef  createAssertionRef (String id ) throws TestFileException {
	AssertionRef ar= createAssertionRef();
	ar.setRef(id);
	return ar;
    }


  /**
    *   Create an (clone) AssertionRef object.
    *  <p>
    * @param origAR The AssertionRef to be cloned
    * @return   a new (cloned) AssertionRef.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.AssertionRef
    */
    static public AssertionRef  cloneAssertionRef (AssertionRef origAR ) 
	throws TestFileException {
	if (origAR == null)
	    return null;

	AssertionRef cloneAR = createAssertionRef();

	// let sub-classes override their clone fields
	cloneOverrideAssertionRef(origAR, cloneAR);


	cloneAR.setRef(origAR.getRef());

	return cloneAR;
    }
     


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origAR The AssertionRef to be cloned
    * @param cloneAR The AssertionRef clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.AssertionRef
    */
    static public void  cloneOverrideAssertionRef (AssertionRef origAR,
							     AssertionRef cloneAR ) throws TestFileException {

    }


     
  /**
    *   Create an (uninitialized) InlineAssertion object.
    *  <p>
    * @return   a new InlineAssertion.
    * @see com.sun.tgxml.tjtf.api.documentation.InlineAssertion
    */
    static public InlineAssertion  createInlineAssertion() {
	return new InlineAssertionImpl();
    }
     

  /**
    *   Create an (cannon) InlineAssertion object.
    *  <p>
    * @param value The InlineAssertion's assertion text (value)
    * @return   a new InlineAssertion.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.InlineAssertion
    */
    static public InlineAssertion  createInlineAssertion (String value ) throws TestFileException {
	InlineAssertion ia= createInlineAssertion();
	ia.setValue(value);
	return ia;
    }


  /**
    *   Create an (clone) InlineAssertion object.
    *  <p>
    * @param origIA The InlineAssertion to be cloned
    * @return   a new (cloned) InlineAssertion.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.InlineAssertion
    */
    static public InlineAssertion  cloneInlineAssertion (InlineAssertion origIA ) 
	throws TestFileException {
	if (origIA == null)
	    return null;

	InlineAssertion cloneIA = createInlineAssertion();

	// let sub-classes override their clone fields
	cloneOverrideInlineAssertion(origIA, cloneIA);


	cloneIA.setValue(origIA.getValue());

	return cloneIA;
    }
     


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origIA The InlineAssertion to be cloned
    * @param cloneIA The InlineAssertion clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.InlineAssertion
    */
    static public void  cloneOverrideInlineAssertion (InlineAssertion origIA,
							     InlineAssertion cloneIA ) throws TestFileException {

    }
     
  /**
    *   Create an (uninitialized) ExpectedResultException object.
    *  <p>
    * @return   a new ExpectedResultException.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    */
    static public ExpectedResultException  createExpectedResultException() {
	return new ExpectedResultExceptionImpl();
    }


     

  /**
    *   Create an (cannon) ExpectedResultException object.
    *  <p>
    * @param excep The ExpectedResultException's exception string
    * @return   a new ExpectedResultException.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    */
    static public ExpectedResultException  createExpectedResultException (String excep ) throws TestFileException {
	ExpectedResultException ere = createExpectedResultException();
	ere.setException(excep);
	return ere;
    }

  /**
    *   Create an (clone) ExpectedResultException object.
    *  <p>
    * @param origERE The ExpectedResultException to be cloned
    * @return   a new (cloned) ExpectedResultException.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    */
    static public ExpectedResultException  cloneExpectedResultException (ExpectedResultException origERE ) 
	throws TestFileException {
	if (origERE == null)
	    return null;

	ExpectedResultException cloneERE = createExpectedResultException();

	// let sub-classes override their clone fields
	cloneOverrideExpectedResultException(origERE, cloneERE);


	cloneERE.setException(origERE.getException());

	return cloneERE;
    }



  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origERE The ExpectedResultException to be cloned
    * @param cloneERE The ExpectedResultException clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    */
    static public void  cloneOverrideExpectedResultException (ExpectedResultException origERE,
							     ExpectedResultException cloneERE ) throws TestFileException {

    }


     
  /**
    *   Create an (uninitialized) ExpectedResultSideEffect object.
    *  <p>
    * @return   a new ExpectedResultSideEffect.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultException
    */
    static public ExpectedResultSideEffect  createExpectedResultSideEffect() {
	return new ExpectedResultSideEffectImpl();
    }

  /**
    *   Create an (cannon) ExpectedResultSideEffect object.
    *  <p>
    * @param sideEffect The ExpectedResultSideEffect's exception string
    * @return   a new ExpectedResultSideEffect.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect
    */
    static public ExpectedResultSideEffect  createExpectedResultSideEffect (String sideEffect ) throws TestFileException {
	ExpectedResultSideEffect erse = createExpectedResultSideEffect();
	erse.setSideEffect(sideEffect);
	return erse;
    }

  /**
    *   Create an (clone) ExpectedResultSideEffect object.
    *  <p>
    * @param origERSE The ExpectedResultSideEffect to be cloned
    * @return   a new (cloned) ExpectedResultSideEffect.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect
    */
    static public ExpectedResultSideEffect  cloneExpectedResultSideEffect (ExpectedResultSideEffect origERSE ) 
	throws TestFileException {
	if (origERSE == null)
	    return null;

	ExpectedResultSideEffect cloneERSE = createExpectedResultSideEffect();

	// let sub-classes override their clone fields
	cloneOverrideExpectedResultSideEffect(origERSE, cloneERSE);


	cloneERSE.setSideEffect(origERSE.getSideEffect());

	return cloneERSE;
    }

  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origERSE The ExpectedResultSideEffect to be cloned
    * @param cloneERSE The ExpectedResultSideEffect clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect
    */
    static public void  cloneOverrideExpectedResultSideEffect (ExpectedResultSideEffect origERSE,
							     ExpectedResultSideEffect cloneERSE ) throws TestFileException {

    }
     

     
  /**
    *   Create an (uninitialized) ExpectedResultValue object.
    *  <p>
    * @return   a new ExpectedResultValue.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    */
    static public ExpectedResultValue  createExpectedResultValue() {
	return new ExpectedResultValueImpl();
    }
     

  /**
    *   Create an (cannon) ExpectedResultValue object.
    *  <p>
    * @param value The ExpectedResultValue's value string
    * @return   a new ExpectedResultValue.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    */
    static public ExpectedResultValue  createExpectedResultValue (String value ) throws TestFileException {
	ExpectedResultValue erv = createExpectedResultValue();
	erv.setValue(value);
	return erv;
    }


  /**
    *   Create an (clone) ExpectedResultValue object.
    *  <p>
    * @param origERV The ExpectedResultValue to be cloned
    * @return   a new (cloned) ExpectedResultValue.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    */
    static public ExpectedResultValue  cloneExpectedResultValue (ExpectedResultValue origERV ) 
	throws TestFileException {
	if (origERV == null)
	    return null;

	ExpectedResultValue cloneERV = createExpectedResultValue();

	// let sub-classes override their clone fields
	cloneOverrideExpectedResultValue(origERV, cloneERV);


	cloneERV.setValue(origERV.getValue());

	return cloneERV;
    }


  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origERV The ExpectedResultValue to be cloned
    * @param cloneERV The ExpectedResultValue clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue
    */
    static public void  cloneOverrideExpectedResultValue (ExpectedResultValue origERV,
							     ExpectedResultValue cloneERV ) throws TestFileException {

    }
      
  /**
    *   Create an (uninitialized) DocElem object.
    *  <p>
    * @return   a new DocElem.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    */
    static public DocElem  createDocElem() {
	// Fill in with blank name/value (some other code should set name/values)
	try {
	    return new DocElemImpl(" ", "");
	} catch (TestFileException e) {
	    return null;
	}

    }
     

  /**
    *   Create an (cannon) DocElem object.
    *  <p>
    * @param name The DocElem's name string
    * @param value The DocElem's value string
    * @return   a new DocElem.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    */
    static public DocElem  createDocElem (String name, String value ) throws TestFileException {
	DocElem de = createDocElem();
	de.setName(name);
	de.setValue(value);
	return de;
    }



  /**
    *   Create an (clone) DocElem object.
    *  <p>
    * @param origDE The DocElem to be cloned
    * @return   a new (cloned) DocElem.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    */
    static public DocElem  cloneDocElem (DocElem origDE ) 
	throws TestFileException {

	if (origDE == null)
	    return null;

	DocElem cloneDE = createDocElem();

	// let sub-classes override their clone fields
	cloneOverrideDocElem(origDE, cloneDE);


	cloneDE.setValue(origDE.getValue());

	return cloneDE;
    }

  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origDE The DocElem to be cloned
    * @param cloneDE The DocElem clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    */
    static public void  cloneOverrideDocElem (DocElem origDE,  DocElem cloneDE ) throws TestFileException {

    }
      
      
  /**
    *   Create an (uninitialized) Input object.
    *  <p>
    * @return   a new Input.
    * @see com.sun.tgxml.tjtf.api.documentation.Input
    */
    static public Input  createInput() {
	// Fill in with blank name/value (some other code should set name/values)
	try {
	    return new InputImpl(" ", "");
	} catch (TestFileException e) {
	    return null;
	}

    }
     

  /**
    *   Create an (cannon) Input object.
    *  <p>
    * @param name The Input's name string
    * @param value The Input's value string
    * @return   a new Input.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.Input
    */
    static public Input  createInput (String name, String value ) throws TestFileException {
	Input inp = createInput();
	inp.setName(name);
	inp.setValue(value);
	return inp;
    }




  /**
    *   Create an (clone) Input object.
    *  <p>
    * @param origInput The Input to be cloned
    * @return   a new (cloned) Input.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.Input
    */
    static public Input  cloneInput (Input origInput ) 
	throws TestFileException {

	if (origInput == null)
	    return null;

	Input cloneInput = createInput();

	// let sub-classes override their clone fields
	cloneOverrideInput(origInput, cloneInput);


	cloneInput.setName(origInput.getName());
	cloneInput.setValue(origInput.getValue());

	return cloneInput;
    }

  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origInput The Input to be cloned
    * @param cloneInput The Input clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.Input
    */
    static public void  cloneOverrideInput (Input origInput, Input cloneInput ) throws TestFileException {

    }
      
      
  /**
    *   Create an (uninitialized) SpecElem object.
    *  <p>
    * @return   a new SpecElem.
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    */
    static public SpecElem  createSpecElem() {
	// Fill in with blank name/value (some other code should set name/values)
	try {
	    return new SpecElemImpl(" ", "");
	} catch (TestFileException e) {
	    return null;
	}
    }
     

  /**
    *   Create an (cannon) SpecElem object.
    *  <p>
    * @param name The SpecElem's name string
    * @param value The SpecElem's value string
    * @return   a new SpecElem.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    */
    static public SpecElem  createSpecElem (String name, String value ) throws TestFileException {
	SpecElem se = createSpecElem();
	se.setName(name);
	se.setValue(value);
	return se;
    }




  /**
    *   Create an (clone) SpecElem object.
    *  <p>
    * @param origSE The SpecElem to be cloned
    * @return   a new (cloned) SpecElem.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    */
    static public SpecElem  cloneSpecElem (SpecElem origSE ) 
	throws TestFileException {
	if (origSE == null)
	    return null;

	SpecElem cloneSE = createSpecElem();

	// let sub-classes override their clone fields
	cloneOverrideSpecElem(origSE, cloneSE);


	cloneSE.setValue(origSE.getValue());

	return cloneSE;
    }

  /**
    *   Override this method to allow sub-classes to clone their extra fields.
    *  <p>
    * @param origSE The SpecElem to be cloned
    * @param cloneSE The SpecElem clone
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.documentation.SpecElem
    */
    static public void  cloneOverrideSpecElem (SpecElem origSE, SpecElem cloneSE ) throws TestFileException {

    }
      



  /**
    *   Factory TestTechnique Constructor (EqClass).
    *  <p>
    * @return     The EqClass TestTechnique associated with this documentation
    */
    static public TestTechnique createEqClassTestTechnique(){
	return TestTechniqueImpl.createEqClass();
    }
     

  /**
    *   Factory TestTechnique Constructor (Boundary).
    *  <p>
    * @return     The EqClass TestTechnique associated with this documentation
    */
    static public TestTechnique createBoundaryTestTechnique(){
	return  TestTechniqueImpl.createBoundary();
    }
     



    // TestTechniques are enums, so there is no need for a clone
}
