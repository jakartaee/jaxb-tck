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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.ExpectedResultSideEffect
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect;
import com.sun.tgxml.tjtf.resources.LibResHandler;
// </importgen>

/**
 * ExpectedResultSideEffect - 
 *
 * <b>ExpectedResultSideEffect</b> is a side-effect that is expected as the
 * outcome of a test.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExpectedResultSideEffectImpl
 * ============================================================================================
 */


public class ExpectedResultSideEffectImpl implements ExpectedResultSideEffect  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
     private String m_sideeffect;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------


  /**
    *   ExpectedResultSideEffect Constructor (annon).
    *  <p>
    *   Value is a non-null string.
    *  <p>
    * @param     se  The ExpectedResultSideEffect value
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    */
    public ExpectedResultSideEffectImpl(){
    }

  /**
    *   ExpectedResultSideEffect Constructor (cannon).
    *  <p>
    *   Value is a non-null string.
    *  <p>
    * @param     se  The ExpectedResultSideEffect value
    * @see com.sun.tgxml.tjtf.api.documentation.ExpectedResult
    */
    public ExpectedResultSideEffectImpl(String se){
	m_sideeffect = se;
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the side-effect associated with this ExpectedResult.
    *  <p>
    * @return     The side-effect associated with this ExpectedResult
    * @see #setSideEffect
    */
    public String getSideEffect() {
	return m_sideeffect; 
    }
     
   /**
    *   Set the side-effect associated with this ExpectedResult.
    *  <p>
    * @param     sideEffect The value for this ExpectedResult.
    * @see #getSideEffect
    */
    public void setSideEffect(String sideEffect) throws TestFileException {
	if (sideEffect == null || sideEffect.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.doc.expectressideeffect.value.null") );

	m_sideeffect = sideEffect;
    }

}
