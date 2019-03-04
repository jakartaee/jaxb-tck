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
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * TestGroup - 
 *
 * <b>TestGroup</b> is the entity that describes a grouping of <b>TestCase</b>s. 
 * <p>
 * <b>TestGroup</b> is a root-node for an Test. A <b>TestGroup</b> can contain 1 
 * or more <b>TestCase</b>s, or contains enough information for some
 * processor to generate 1 or more TestCases.
 * <p>
 * <b>TestGroup</b>s derive from <b>TestItem</b>s, and contain all of the
 * descriptions, attributes, and code that is commonly shared by all <b>TestCase</b>s
 * that this group contains.
 * <p>
 * <b>TestGroup</b>s contain the following fields:
 * <p>
 * <ul>
 *     <li> ID
 *     <li> TestGroupDocumentation
 *     <li> TestGroupAttributes
 *     <li> CodeSet
 *     <li> Libraries
 *     <li> TestCases
 *  </ul> <br>
 * <p>
 *
 * @version 	1.1, 10/23/02
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestGroup
 * ============================================================================================
 */


public  interface TestGroup extends TestRoot, TestItem {

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
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #setTGDocumentation
    */
    public TestGroupDocumentation getTGDocumentation();
     
  /**
    *   Set the Documentation associated with this TestItem.
    *  <p>
    * @param     doc The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
    * @see #getTGDocumentation
    */
    public void setTGDocumentation(TestGroupDocumentation doc);




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    * @see #setTGAttributes
    */
    public TestGroupAttributes getTGAttributes();
     
  /**
    *   Set the Attributes associated with this TestItem.
    *  <p>
    * @param     attrs The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    * @see #getTGAttributes
    */
    public void setTGAttributes(TestGroupAttributes attrs);




  /**

    *   Get the (ArrayList) list of Libraries that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of Libraries.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #setLibraries
    */
    public ArrayList getLibraries();
     

     
  /**
    *   Set the (ArrayList) list of Libraries that this TestGroup owns.
    *  <p>
    * @param     librarys The (ArrayList) list of librarys for this TestGroup, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #getLibraries
    */
    public void setLibraries(ArrayList librarys);



  /**
    *   Adds the Library to this TestGroup.   
    *  <p>
    * @throws TestFileException if a library with the same ID and VarID exists;
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see #setLibraries
    */
    public void addLibrary(Library library) throws TestFileException;


  /**
    *   Get the Library(s) that have a specific ID.
    *  <p>
    * @return   An (ArrayList) list of Libraries, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.Library
    * @see java.util.ArrayList
    * @see #setLibraries
    */
    public ArrayList getLibrary(String ID) throws TestFileException;


  /**
    *   Get the Library that has a specific ID and Variant ID.
    *  <p>
    * @return   A Library, or null.
    * @throws TestFileException if ID or VarID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setLibraries
    */
    public Library getLibrary(String ID, String VarID) throws TestFileException;

     

  /**

    *   Get the (ArrayList) list of TestCases that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of TestCases.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setTestCases
    */
    public ArrayList getTestCases();
     

     
  /**
    *   Set the (ArrayList) list of TestCases that this TestGroup owns.
    *  Each of testcases in the ArrayList is updated with the new owner TestGroup.
    *  <p>
    * @param     testcases The (ArrayList) list of imports for this TestGroup, or NULL.
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #getTestCases
    */
    public void setTestCases(ArrayList testcases);



  /**
    *   Adds the TestCase to this TestGroup.   
    *  <p>
    * @throws TestFileException if a library with the same ID and VarID exists;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see #setTestCases
    */
    public void addTestCase(TestCase tcase) throws TestFileException;


  /**
    *   Get the TestCase(s) that have a specific ID.
    *  <p>
    * @return   An (ArrayList) list of TestCases, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see java.util.ArrayList
    * @see #setTestCases
    */
    public ArrayList getTestCase(String ID) throws TestFileException;


  /**
    *   Get the TestCase that has a specific ID and VarID.
    *  <p>
    * @return   A TestCase, or null.
    * @throws TestFileException if ID is null;
    * @see com.sun.tgxml.tjtf.api.tests.TestCase
    * @see #setTestCases
    */
    public TestCase getTestCase(String ID, String VarID) throws TestFileException;

     
  /**
    *   Get the (ArrayList) list of TestCase IDs that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of TestCase IDs.
    * @see #setTestCases
    */
    public ArrayList getTestCaseIDs();


  /**
    *   Get the (ArrayList) list of Library IDs that this TestGroup owns.
    *  <p>
    * @return   An (ArrayList) list of Library IDs.
    * @see #setLibraries
    */
    public ArrayList getLibraryIDs();

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
