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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.TestItem
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.Documentation;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import java.util.ArrayList;
// </importgen>

/**
 * TestItem - 
 *
 * <b>TestItem</b> is the interface that describes that this object is a 
 * TestEntity (TestGroup, TestCase, or TestLibrary).
 * <b>TestItem</b> describes all of the common member functions of a TestEntity.
 * <p>
 *  A test Item typically contains some Documentation entity, an Attributes entity,
 *  and a CodeSet (common-shared code parts for an entity).
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestItem  (marker interface)
 * ============================================================================================
 */


public  interface TestItem {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

  /**
    *   Get the identifier associated with the TestGroup, TestClass, or Library.
    *  <p>
    * @exception   TestFileException if ID is unset.
    * @return   A string containing the identifier.
    * @see #setID
    */
    public String getID() throws TestFileException;

     
  /**
    *   Set the identifier associated with the TestGroup, TestClass, or Library.
    *  <p>
    * @param     id The string containing the identifier.
    * @exception   TestFileException if id is null.
    * @see #getID
    */
    public void setID(String id) throws TestFileException;



  /**
    *   Get the Documentation associated with this TestItem.
    *  <p>
    * @return   The Documentation associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.Documentation
    */
    public Documentation getDocumentation();
     




  /**
    *   Get the Attributes associated with this TestItem.
    *  <p>
    * @return   The Attributes associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.Attributes
    */
    public Attributes getAttributes();
     


  /**

    *   Get the CodeSet for this TestItem.
    *  <p>
    * @return   A CodeSet object (or NULL).
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    * @see #setCodeSet
    */
    public CodeSet getCodeSet();
     

     
  /**
    *   Set the CodeSet for this TestItem.
    *  <p>
    * @param     codeset The CodeSet object (or NULL).
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    * @see #getCodeSet
    */
    public void setCodeSet(CodeSet codeset);




}

