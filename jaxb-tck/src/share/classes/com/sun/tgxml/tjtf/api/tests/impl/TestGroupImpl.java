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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.impl.TestGroupImpl
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * TestGroup - 
 *
 * <b>TestGroup</b> is the entity that describes a grouping (or class) of atomic tests. 
 * <b>TestGroup</b> are owned by <b>Test</b>s.  
 * <p>
 * <b>TestGroup</b>es derive from <b>CommonTestProperties</b>,
 * and contain a <b>Test</b> as their parent. These properties describe
 * the comments, assertions, and descriptions that this class contains.
 * <p>
 * <b>TestGroup</b>es also derive from <b>SourceContainer</b>, which
 * describe the source code that this class contains.
 *<p>
 * <b>TestGroup</b>es contain the following fields:
 * <p>
 * <ul>
 *     <li> librarys
 *     <li> test cases
 *  </ul> <br>
 * <p>
 *
 * @version 	1.1, 10/23/02
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestGroupImpl
 * ============================================================================================
 */


public  class TestGroupImpl extends TestItemImpl implements TestGroup {


    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList          m_testCases;
    private ArrayList          m_librarys;
    private boolean            isGloballyExcluded;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestGroupImpl() {
	super();
	init();
    }

    public TestGroupImpl(String ID, TestGroupDocumentation documentation, TestGroupAttributes attributes, 
			 CodeSet codeset, ArrayList testCases ) {
        super(ID, documentation, attributes, codeset);
        setTestCases(testCases);
    }

    public TestGroupImpl(String ID, TestGroupDocumentation documentation, TestGroupAttributes attributes, 
			 CodeSet codeset, ArrayList testCases, ArrayList librarys ) {
        super(ID, documentation, attributes, codeset);
        setTestCases(testCases);
        setLibraries(librarys);
    }

    private void init() {
	m_testCases = null;
	m_librarys = null;
    }

   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the Documentation associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #setTGDocumentation
    */
    public TestGroupDocumentation getTGDocumentation() {
	return (TestGroupDocumentation) getDocumentation();
    }
     
  /**
    *   Set the Documentation associated with this TestItem.
    *  <p>
    * @param     doc The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #getTGDocumentation
    */
    public void setTGDocumentation(TestGroupDocumentation doc) {
	_setDocumentation(doc);
    }




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    * @see #setTGAttributes
    */
    public TestGroupAttributes getTGAttributes() {
	return (TestGroupAttributes) getAttributes();
    }
     
  /**
    *   Set the Attributes associated with this TestItem.
    *  <p>
    * @param     attrs The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    * @see #getTGAttributes
    */
    public void setTGAttributes(TestGroupAttributes attrs) {
	_setAttributes(attrs);
    }


  /**

    *   Get the (ArrayList) list of Libraries that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of Libraries.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #setTestCases
    */
    public ArrayList getLibraries() {
	return m_librarys;
    }
     

     
  /**
    *   Set the (ArrayList) list of Libraries that this TestGroup owns.
    *  <p>
    * @param     librarys The (ArrayList) list of librarys for this TestGroup, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #getTestCases
    */
    public void setLibraries(ArrayList librarys) {
        m_librarys = librarys;
	/* only do this if we need to establish reverse ownership
        if (librarys != null) {
            Iterator it = librarys.iterator();                               
            while (it.hasNext()) {
                ((Library) it.next()).setTestGroup(this);
            }
        }
	*/
    }

  /**
    *   Adds the Library to this TestGroup.   
    *   The owner for TestCase is set to be this TestGroup.
    *  <p>
    * @throws TestFileException if a library with the same ID and VarID exists;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see #setTestCases
    */
    public void addLibrary(Library library) throws TestFileException {        
        if (library != null) {

	    Library lib1 = getLibrary(library.getID(), library.getVarID());
	    if (lib1 != null) 
		throw new TestFileException(LibResHandler.getResStr("api.tg.adddup.lib", library.getID(), library.getVarID()));


	    //            library.setTestGroup(this);
            if (m_librarys == null) {
                m_librarys = new ArrayList();
            }
            m_librarys.add(library);
        }
    }
     


  /**
    *   Get the Library(s) that have a specific ID.
    *  <p>
    * @return   An (ArrayList) list of Libraries, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #setLibraries
    */
    public ArrayList getLibrary(String ID)  throws TestFileException {
	if (ID == null) 
	    throw new TestFileException(LibResHandler.getResStr("api.tg.libid.null"));

        if (m_librarys == null)
	    return null;

	ArrayList libs = null;
	Iterator libIter = m_librarys.iterator();
	while (libIter.hasNext()) {
	    Library lib = (Library) libIter.next();
	    String libID = lib.getID();
	    if (ID.equals(libID)) {
		if (libs == null)
		    libs = new ArrayList();
		libs.add(lib);
	    }
	}

	return libs;
    }




  /**
    *   Get the Library that has a specific ID and Variant ID.
    *  <p>
    * @return   A Library, or null.
    * @throws TestFileException if ID or VarID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setLibraries
    */
    public Library getLibrary(String ID, String VarID)  throws TestFileException {
	if (ID == null || ID.equals("")) 
	    throw new TestFileException(LibResHandler.getResStr("api.tg.libid.null"));

        if (m_librarys == null)
	    return null;

	Iterator libIter = m_librarys.iterator();
	while (libIter.hasNext()) {
	    Library lib = (Library) libIter.next();
	    String libID = lib.getID();
	    String libVarID = lib.getVarID();
	    if (ID.equals(libID)) {
		if ((VarID == null && libVarID == null) ||
		    (VarID != null && libVarID != null && VarID.equals(libVarID))) {
		    return lib;
		}
	    }
	}

	return null;
    }







  /**

    *   Get the (ArrayList) list of TestCases that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of TestCases.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setTestCases
    */
    public ArrayList getTestCases() {
	return m_testCases;
    }
     

     
  /**
    *   Set the (ArrayList) list of TestCases that this TestGroup owns.
    *   Each of testcases in the ArrayList is updated with the new owner TestGroup.
    *  <p>
    * @param     testcases The (ArrayList) list of imports for this TestGroup, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #getTestCases
    */
    public void setTestCases(ArrayList testcases) {
        m_testCases = testcases;
        if (testcases != null) {
            Iterator it = testcases.iterator();                               
            while (it.hasNext()) {
                ((TestCase) it.next()).setTestGroup(this);
            }
        }
    }

  /**
    *   Adds the TestCase to this TestGroup.   
    *   The owner for TestCase is set to be this TestGroup.
    *  <p>
    * @throws TestFileException if a TestCase with the same ID and VarID exists;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see #setTestCases
    */
    public void addTestCase(TestCase tcase) throws TestFileException {        
        if (tcase != null) {

	    TestCase tc1 = getTestCase(tcase.getID(), tcase.getVarID());
	    if (tc1 != null) 
		throw new TestFileException(LibResHandler.getResStr("api.tg.adddup.tc", tcase.getID(), tcase.getVarID()));

            tcase.setTestGroup(this);
            if (m_testCases == null) {
                m_testCases = new ArrayList();
            }
            m_testCases.add(tcase);
        }
    }
     


  /**
    *   Get the TestCase(s) that have a specific ID.
    *  <p>
    * @return   An (ArrayList) list of TestCases, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setTestCases
    */
    public ArrayList getTestCase(String ID)  throws TestFileException {
	if (ID == null) 
	    throw new TestFileException(LibResHandler.getResStr("api.tg.tcid.null"));

        if (m_testCases == null)
	    return null;

	ArrayList tcs = null;
	Iterator tcIter = m_testCases.iterator();
	while (tcIter.hasNext()) {
	    TestCase tc = (TestCase) tcIter.next();
	    String tcID = tc.getID();
	    if (ID.equals(tcID)) {
		if (tcs == null)
		    tcs = new ArrayList();
		tcs.add(tc);
	    }
	}

	return tcs;
    }


 /**
    *   Get the TestCase that has a specific ID and VarID.
    *  <p>
    * @return   A TestCase, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.TestCase
    * @see #setTestCases
    */
    public TestCase getTestCase(String ID, String VarID)  throws TestFileException {
	if (ID == null) 
	    throw new TestFileException(LibResHandler.getResStr("api.tg.tcid.null"));

        if (m_testCases == null)
	    return null;

	Iterator tcIter = m_testCases.iterator();
	while (tcIter.hasNext()) {
	    TestCase tc = (TestCase) tcIter.next();
	    String tcID = tc.getID();
	    String tcVarID = tc.getVarID();
	    if (ID.equals(tcID)) {
		if ((VarID == null && tcVarID == null) ||
		    (VarID != null && tcVarID != null && VarID.equals(tcVarID))) {
		    return tc;
		}
	    }
	}

	return null;
    }


  /**
    *   Get the (ArrayList) list of TestCase IDs that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of TestCase IDs.
    * @see #setTestCases
    */
    public ArrayList getTestCaseIDs() {
        if (m_testCases == null) 
            return null;

        ArrayList idList = new ArrayList();	
	for (Iterator tcIter = m_testCases.iterator(); tcIter.hasNext();) {
	    TestCase tc = (TestCase) tcIter.next();
            try {
		String tcID = tc.getID();
		if (!idList.contains(tcID)) {
		    idList.add(tcID);
                }
	    } catch (TestFileException e) {
            }
	}

	return idList;        
    }


  /**
    *   Get the (ArrayList) list of Library IDs that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of Library IDs.
    * @see #setLibraries
    */
    public ArrayList getLibraryIDs() {
        if (m_librarys == null) 
            return null;

        ArrayList idList = new ArrayList();	
	for (Iterator libIter = m_librarys.iterator(); libIter.hasNext();) {
	    Library lib = (Library)libIter.next();
            try {
		String libID = lib.getID();
		if (!idList.contains(libID)) {
		    idList.add(libID);
                }
	    } catch (TestFileException e) {
            }
	}

	return idList;        
    }

   /**
     * Returns true it the test is globally permanently excluded,
     * false otherwise
     */
    public boolean isDeleted() {
        return isGloballyExcluded;
    }

   /**
     * Marks the test as globally permanently excluded if the passed
     * argument is true.
     */
    public void setDeleted(boolean v) {
        isGloballyExcluded = v;
    }
}
