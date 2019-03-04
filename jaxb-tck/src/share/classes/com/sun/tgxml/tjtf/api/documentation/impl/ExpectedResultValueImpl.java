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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.ExpectedResultValueImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.ExpectedResultValue;
import com.sun.tgxml.tjtf.resources.LibResHandler;
// </importgen>

/**
 * ExpectedResultValue - 
 *
 * <b>ExpectedResultValue</b> is a value that is the expected result of a tests
 * outcome.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExpectedResultValueImpl
 * ============================================================================================
 */


public class ExpectedResultValueImpl implements ExpectedResultValue  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
     private String m_value;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

  /**
    *   ExpectedResultValue Constructor (annon).
    *  <p>
    *   Value is a non-null string.
    *  <p>
    * @param     value  The ExpectedResultValue value
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    */
    public ExpectedResultValueImpl() {

    }


  /**
    *   ExpectedResultValue Constructor (cannon).
    *  <p>
    *   Value is a non-null string.
    *  <p>
    * @param     value  The ExpectedResultValue value
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    */
    public ExpectedResultValueImpl(String value){
	m_value = value;
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the value associated with this ExpectedResult.
    *  <p>
    * @return     The value associated with this ExpectedResult
    * @see #setValue
    */
    public String getValue() {
	return m_value; 
    }
     
   /**
    *   Set the value associated with this ExpectedResult.
    *  <p>
    * @param     value The value for this ExpectedResult.
    * @see #getValue
    */
    public void setValue(String value) throws TestFileException {
	if (value == null || value.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.doc.expectresval.value.null") );

	m_value = value;
    }

}
