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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.ArrayList;
// </importgen>

/**
 * TestGroupDocumentation - 
 *
 * <b>TestGroupDocumentation</b> is the typed-documentation interface for describing the documentation
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
 *    TestGroupDocumentation
 * ============================================================================================
 */


public  interface TestGroupDocumentation extends Documentation  {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



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
    public ArrayList getAssertions();

     
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
    public void setAssertions(ArrayList assertions);




  /**
    *   Get the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedPackage associated with this documentation
    * @see #setTestedPackage
    */
    public String getTestedPackage();
     
   /**
    *   Set the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @param     testedPackage The name of the TestedPackage.
    * @see #getTestedPackage
    */
    public void setTestedPackage(String testedPackage);




  /**
    *   Get the TestedClass associated with this documentation.
    *  <p>
    *   This TestedClass is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedClass associated with this documentation
    * @see #setTestedClass
    */
    public String getTestedClass();
     
   /**
    *   Set the TestedClass associated with this documentation.
    *  <p>
    *   This TestedClass is an optional field (required for JCK tests).
    *  <p>
    * @param     testedClass The name of the TestedClass.
    * @see #getTestedClass
    */
    public void setTestedClass(String testedClass);




  /**
    *   Get the member signature associated with this documentation.
    *  <p>
    *   This MemberSig is an optional field (required for JCK tests).
    *  <p>
    * @return     The MemberSig associated with this documentation
    * @see #setMemberSig
    */
    public String getMemberSig();
     
   /**
    *   Set the member signature associated with this documentation.
    *  <p>
    *   This MemberSig is an optional field (required for JCK tests).
    *  <p>
    * @param     sig The member signature.
    * @see #getMemberSig
    */
    public void setMemberSig(String sig);


  /**
    *   Get the (Repository Specific) description-entities associated with this documentation.
    *  <p>
    *   The DocElems list is always defined, and may contain zero or more entries.
    *  <p>
    * @return     The DocElem list associated with this documentation
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    * @see #setDocElems
    */
    public ArrayList getDocElems();

     
 /**
    *   Set the (Repository Specific) description-entities associated with this documentation.
    *  <p>
    *   The DocElem list is always defined, and may contain zero or more entries.
    *  <p>
    * @param     docelems The ArrayList of DocElem entries.
    * @see com.sun.tgxml.tjtf.api.documentation.DocElem
    * @see #getDocElems
    */
    public void setDocElems(ArrayList docelems);



}
