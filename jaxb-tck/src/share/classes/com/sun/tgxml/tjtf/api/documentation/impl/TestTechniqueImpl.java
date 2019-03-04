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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.TestTechniqueImpl
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.documentation.TestTechnique;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * TestTechniqueImpl - 
 *
 * <b>TestTechniqueImpl</b>  is the enumeration for a method of testing a given API.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestTechnique
 * ============================================================================================
 */


public class TestTechniqueImpl implements TestTechnique  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    final static private String cStr_EqClass = TagsImpl.ctStr_attr_testtech_enum_eqclass;
    final static private String cStr_Boundary = TagsImpl.ctStr_attr_testtech_enum_boundary;

    final static private TestTechnique EqClass = new TestTechniqueImpl(cStr_EqClass);
    final static private TestTechnique Boundary = new TestTechniqueImpl(cStr_Boundary);

    private String m_type;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------


  /**
    *   TestTechnique Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @return     The TestTechnique associated with this documentation
    * @see com.sun.tgxml.tjtf.api.common.NameValuePair
    */
    private TestTechniqueImpl(String value){
	m_type = value;
    }


    //------------------------------------------------------------------------------
    //  Factory methods
    //------------------------------------------------------------------------------


  /**
    *   Factory TestTechnique Constructor (EqClass).
    *  <p>
    * @return     The EqClass TestTechnique associated with this documentation
    */
    static public TestTechnique createEqClass(){
	return EqClass;
    }
     

  /**
    *   Factory TestTechnique Constructor (Boundary).
    *  <p>
    * @return     The EqClass TestTechnique associated with this documentation
    */
    static public TestTechnique createBoundary(){
	return Boundary;
    }
      

    //------------------------------------------------------------------------------
    //  Predicate methods
    //------------------------------------------------------------------------------


  /**
    *   Is this an EqClass technique.
    *  <p>
    * @return     The true if this technique is EqClass.
    */
    public boolean isEqClass(){
	return (this == EqClass);
    }
     

  /**
    *   Is this an Boundary technique.
    *  <p>
    * @return     The true if this technique is Boundary.
    */
    public boolean isBoundary(){
	return (this == Boundary);
    }
     
     

    //------------------------------------------------------------------------------
    //  Factory methods
    //------------------------------------------------------------------------------


  /**
    *  return the string value of the TestTechnique.
    *  <p>
    * @return     The TestTechnique value.
    */
    public String toString(){
	return m_type;
    }
     

}
