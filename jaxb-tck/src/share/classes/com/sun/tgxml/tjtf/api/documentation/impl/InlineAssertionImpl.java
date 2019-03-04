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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.InlineAssertionImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.InlineAssertion;
import com.sun.tgxml.tjtf.resources.LibResHandler;
// </importgen>

/**
 * InlineAssertion - 
 *
 * <b>InlineAssertion</b> is a (non-specification) assertion that a given test
 * is testing.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineAssertionImpl
 * ============================================================================================
 */


public class InlineAssertionImpl implements InlineAssertion {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
     private String m_inlineString;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------


  /**
    *   InlineAssertion Constructor (annon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @param     value  The InlineAssertion value
    * @see com.sun.tgxml.tjtf.api.documentation.Assertion
    */
    public InlineAssertionImpl(){
    }

  /**
    *   InlineAssertion Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @param     value  The InlineAssertion value
    * @see com.sun.tgxml.tjtf.api.documentation.Assertion
    */
    public InlineAssertionImpl(String value){
	m_inlineString = value;
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the value associated with this rsde.
    *  <p>
    * @return     The value associated with this rsde
    * @see #setValue
    */
    public String getValue() {
	return m_inlineString; 
    }
     
   /**
    *   Set the value associated with this rsde.
    *  <p>
    * @param     value The value of the rsde.
    * @see #getValue
    */
    public void setValue(String value) throws TestFileException {
	if (value == null || value.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.doc.inlineassertion.value.null") );

	m_inlineString = value;
    }

}
