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
import com.sun.tgxml.tjtf.api.tests.impl.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
// </importgen>

/**
 * TestFactory - 
 *
 * <b>TestFactory</b> is a static factory class for creating Test objects (TestGroup, TestCase, Library). 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestFactory
 * ============================================================================================
 */


public  class TestFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private TestFactory () {

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
    *   Create an (clone) of a TestRoot object.
    *  <p>
    * @param origRoot   the original TD root object.
    * @return   a cloned TD root object.
    * @throws TestFileException if origRoot is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public TestRoot  cloneTestRoot (TestRoot origRoot )  throws TestFileException {
	if (origRoot == null)
	    return null;

	if (origRoot instanceof TestGroup)
	    return cloneTestGroup((TestGroup) origRoot);
	else if (origRoot instanceof Library)
	    return cloneLibrary((Library) origRoot);

	return null;
    }


  /**
    *   Create an (uninitialized) TestGroup object.
    *  <p>
    * @return   a new TestGroup.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    */
    static public TestGroup  createTestGroup() {
	return new TestGroupImpl();
    }
     

  /**
    *   Create an (cannon) TestGroup object.
    *  <p>
    * @param ID The TestGroup's ID
    * @param documentation The TestGroup's documentation
    * @param attributes The TestGroup's attributes
    * @param codeset The TestGroup's CodeSet
    * @param testCases The TestGroup's TestCases
    * @return   a new TestGroup.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    */
    static public TestGroup  createTestGroup (String ID, TestGroupDocumentation documentation, TestGroupAttributes attributes, 
					      CodeSet codeset, ArrayList testCases )  throws TestFileException {
	TestGroup tg = createTestGroup();
	tg.setID(ID);
	tg.setTGDocumentation(documentation);
	tg.setTGAttributes(attributes);
	tg.setCodeSet(codeset);
	tg.setTestCases(testCases);

	return tg;
    }
     




  /**
    *   Create an (clone) of a TestGroup object.
    *  <p>
    * @param origTG   the original TestGroup root object.
    * @return   a cloned TestGroup root object.
    * @throws TestFileException if a origTG is null.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    */
    static public TestGroup  cloneTestGroup (TestGroup origTG )  throws TestFileException {
	if (origTG == null)
	    return null;

	TestGroup clonetg = createTestGroup();

	// let sub-classes override their clone fields
	cloneOverrideTestGroup(origTG, clonetg);

	clonetg.setID(origTG.getID());
	clonetg.setTGDocumentation(DocumentationFactory.cloneTestGroupDocumentation(origTG.getTGDocumentation()));
	clonetg.setTGAttributes(AttributesFactory.cloneTestGroupAttributes(origTG.getTGAttributes()));
	clonetg.setCodeSet(CodeFactory.cloneCodeSet(origTG.getCodeSet()));

	ArrayList origLibs = origTG.getLibraries();
	if (origLibs != null) {
	    ArrayList cloneLibs = new ArrayList();

	    Iterator it = origLibs.iterator();
	    while (it.hasNext()) {
		Library origLib = (Library) it.next();
		Library cloneLib = cloneLibrary(origLib);
		// cloneLib.setTestGroup(clonetg);
		cloneLibs.add(cloneLib);
	    }
	    clonetg.setLibraries(cloneLibs);
	}


	ArrayList origTestCases = origTG.getTestCases();
	if (origTestCases != null) {
	    ArrayList cloneTestCases = new ArrayList();

	    Iterator it = origTestCases.iterator();
	    while (it.hasNext()) {
		TestCase origTC = (TestCase) it.next();
		TestCase cloneTC = cloneTestCase(origTC);
		cloneTC.setTestGroup(clonetg);
		cloneTestCases.add(cloneTC);
	    }
	    clonetg.setTestCases(cloneTestCases);
	}

	return clonetg;
    }
     

  /**
    *   Override this method for sub-classes to clone their fields.
    *  <p>
    * @param origTG   the original TestGroup root object.
    * @param cloneTG   the TestGroup clone.
    * @throws TestFileException if a origTG is null.
    * @see com.sun.tgxml.tjtf.api.tests.TestGroup
    */
    static public void  cloneOverrideTestGroup (TestGroup origTG, TestGroup cloneTG ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) TestCase object.
    *  <p>
    * @return   a new TestCase.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    */
    static public TestCase  createTestCase() {
	return new TestCaseImpl();
    }
     

  /**
    *   Create an (cannon) TestCase object.
    *  <p>
    * @param ID The TestCase's ID
    * @param documentation The TestCase's documentation
    * @param attributes The TestCase's attributes
    * @param codeset The TestCase's CodeSet
    * @param testcode The TestCase's test code
    * @param testgroup The TestCase's test group
    * @return   a new TestCase.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    */
    static public TestCase  createTestCase (String ID, TestCaseDocumentation documentation, TestCaseAttributes attributes, 
					      CodeSet codeset, TestCode testcode, TestGroup testgroup )  throws TestFileException {
	TestCase tc = createTestCase();
	tc.setID(ID);
	tc.setTCDocumentation(documentation);
	tc.setTCAttributes(attributes);
	tc.setCodeSet(codeset);
	tc.setTestCode(testcode);
	tc.setTestGroup(testgroup);

	return tc;
    }

  /**
    *   Create an (cannon) TestCase object.
    *  <p>
    * @param ID The TestCase's ID
    * @param documentation The TestCase's documentation
    * @param attributes The TestCase's attributes
    * @param codeset The TestCase's CodeSet
    * @param testcode The TestCase's test code
    * @param testgroup The TestCase's test group
    * @return   a new TestCase.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    */
    static public TestCase  createTestCase (String ID, String VarID, TestCaseDocumentation documentation, TestCaseAttributes attributes, 
					      CodeSet codeset, TestCode testcode, TestGroup testgroup )  throws TestFileException {
	TestCase tc = createTestCase(ID, documentation, attributes, codeset, testcode, testgroup);
	tc.setVarID(VarID);

	return tc;
    }
     
     

  /**
    *   Create an (clone) TestCase object.
    *  <p>
    * @param origTC The original TestCase to be cloned.
    * @return   a new TestGroup.
    * @throws TestFileException if a field in origTC is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    */
    static public TestCase  cloneTestCase (TestCase origTC )  throws TestFileException {
	if (origTC == null)
	    return null;

	TestCase clonetc = createTestCase();

	// let sub-classes override their clone fields
	cloneOverrideTestCase(origTC, clonetc);

	clonetc.setID(origTC.getID());
	clonetc.setVarID(origTC.getVarID());
	clonetc.setTCDocumentation(DocumentationFactory.cloneTestCaseDocumentation(origTC.getTCDocumentation()));
	clonetc.setTCAttributes(AttributesFactory.cloneTestCaseAttributes(origTC.getTCAttributes()));
	clonetc.setCodeSet(CodeFactory.cloneCodeSet(origTC.getCodeSet()));
	clonetc.setTestCode(CodeFactory.cloneTestCode(origTC.getTestCode()));

	return clonetc;
    }
     
  /**
    *   Override this method for sub-classes to clone their fields.
    *  <p>
    * @param origTC   the original TestCase root object.
    * @param cloneTC   the TestCase clone.
    * @throws TestFileException if a origTG is null.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    */
    static public void  cloneOverrideTestCase (TestCase origTC, TestCase cloneTC ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) Library object.
    *  <p>
    * @return   a new Library.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public Library  createLibrary() {
	return new LibraryImpl();
    }
     
  /**
    *   Create an (uninitialized) InlineLibrary object.
    *  <p>
    * @return   a new InlineLibrary.
    * @see com.sun.tgxml.tjtf.api.tests.InlineLibrary
    */
    static public InlineLibrary  createInlineLibrary() {
	return new InlineLibraryImpl();
    }
     

  /**
    *   Create an (cannon) Library object.
    *  <p>
    * @param ID The Library's ID
    * @param documentation The Library's documentation
    * @param attributes The Library's attributes
    * @param codeset The Library's CodeSet
    * @return   a new Library.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public Library  createLibrary (String ID, LibDocumentation documentation, LibAttributes attributes, 
					      CodeSet codeset ) throws TestFileException {
	Library lib = createLibrary();
        initLibrary(lib, ID, documentation, attributes, codeset);
	return lib;
    }
     
  /**
    *   Create an (cannon) InlineLibrary object.
    *  <p>
    * @param ID The Library's ID
    * @param documentation The Library's documentation
    * @param attributes The Library's attributes
    * @param codeset The Library's CodeSet
    * @return   a new Library.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.InlineLibrary
    */
    static public InlineLibrary  createInlineLibrary (String ID, LibDocumentation documentation, LibAttributes attributes, 
					      CodeSet codeset ) throws TestFileException {
	InlineLibrary lib = createInlineLibrary();
        initLibrary(lib, ID, documentation, attributes, codeset);
	return lib;
    }
     


  /**
    *   Create an (cannon) Library object.
    *  <p>
    * @param ID The Library's ID
    * @param documentation The Library's documentation
    * @param attributes The Library's attributes
    * @param codeset The Library's CodeSet
    * @return   a new Library.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public Library  createLibrary (String ID, String VarID, LibDocumentation documentation, LibAttributes attributes, 
					      CodeSet codeset ) throws TestFileException {
	Library lib = createLibrary(ID, documentation, attributes, codeset);
	lib.setVarID(VarID);

	return lib;
    }
     

  /**
    *   Create an (cannon) InlineLibrary object.
    *  <p>
    * @param ID The Library's ID
    * @param documentation The Library's documentation
    * @param attributes The Library's attributes
    * @param codeset The Library's CodeSet
    * @return   a new Library.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.InlineLibrary
    */
    static public InlineLibrary  createInlineLibrary (String ID, String VarID, LibDocumentation documentation, LibAttributes attributes, 
					      CodeSet codeset ) throws TestFileException {
	InlineLibrary lib = createInlineLibrary(ID, documentation, attributes, codeset);
	lib.setVarID(VarID);

	return lib;
    }
     
  /**
    *   Create an (cannon) InlineLibrary object.
    *  <p>
    * @param extLib The Library's ID
    * @return   a new Library.
    * @throws TestFileException if a parameter is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.InlineLibrary
    */
    static public InlineLibrary  createInlineLibrary (Library extLib) 
            throws TestFileException {
	InlineLibrary lib = createInlineLibrary(
           extLib.getID(), 
           extLib.getLibDocumentation(), 
           extLib.getLibAttributes(), 
           extLib.getCodeSet());

	lib.setVarID(extLib.getVarID());
	return lib;
    }
     


  /**
    *   Create an (clone) Library object.
    *  <p>
    * @param origLib The Library to be cloned.
    * @return   a new Library.
    * @throws TestFileException if a field in origLib is invalid.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public Library  cloneLibrary (Library origLib ) throws TestFileException {
	if (origLib == null)
	    return null;

	Library cloneLib = null;
        if (origLib instanceof InlineLibrary) {
            InlineLibrary inlineLib = createInlineLibrary();
            inlineLib.setTestGroup(((InlineLibrary)origLib).getTestGroup());
            cloneLib = inlineLib;
        } else {
            cloneLib = createLibrary();
        }

	// let sub-classes override their clone fields
	cloneOverrideLib(origLib, cloneLib);
	cloneLib.setID(origLib.getID());
	cloneLib.setVarID(origLib.getVarID());
	cloneLib.setLibDocumentation(DocumentationFactory.cloneLibDocumentation(origLib.getLibDocumentation()));
	cloneLib.setLibAttributes(AttributesFactory.cloneLibAttributes(origLib.getLibAttributes()));
	cloneLib.setCodeSet(CodeFactory.cloneCodeSet(origLib.getCodeSet()));

	return cloneLib;
    }

  /**
    *   Override this method for sub-classes to clone their fields.
    *  <p>
    * @param origLib   the original Library root object.
    * @param cloneLib   the Library clone.
    * @throws TestFileException if a origLib is null.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    */
    static public void  cloneOverrideLib (Library origLib, Library cloneLib ) throws TestFileException {

    }

    static  void  initLibrary (Library lib, 
            String ID, LibDocumentation documentation, 
            LibAttributes attributes, CodeSet codeset ) 
                throws TestFileException 
    {
	lib.setID(ID);
	lib.setLibDocumentation(documentation);
	lib.setLibAttributes(attributes);
	lib.setCodeSet(codeset);

    }


}
