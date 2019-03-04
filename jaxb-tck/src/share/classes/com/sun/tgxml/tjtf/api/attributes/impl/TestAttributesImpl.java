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

package com.sun.tgxml.tjtf.api.attributes.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.impl.TestAttributesImpl
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.TestAttributes;
import com.sun.tgxml.tjtf.impl.CommonImpl;
import java.util.ArrayList;
// </importgen>

/**
 * TestAttributes - 
 *
 * <b>TestAttributes</b> is the basic interface for describing the attributes
 * associated with a TestCase.
 * <p>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestAttributesImpl
 * ============================================================================================
 */


public  class TestAttributesImpl  extends AttributesImpl implements TestAttributes {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */

    private String    m_Timeout;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

    public TestAttributesImpl() {
	super();
	init();
    }


    public TestAttributesImpl(ArrayList reqResources, ArrayList attrElems, ArrayList targetSpecs, String timeout) {
	super(reqResources, attrElems, targetSpecs);
	init();

	try {
	    setTimeout(timeout);
	} catch (TestFileException e) {
	    init();
	}
    }



    private void init() {
	m_Timeout = null;
    }

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the timeout associated with this test.
    *  <p>
    *   The Timeout is a string containing "timeout"
    *   times (integer (int, 32-bits, msecs, default )), or NULL.
    *  <p>
    * @return     The timeout (String) value.
    * @see #setTimeout
    */
    public String getTimeout(){
	return m_Timeout;
    }
     
   /**
    *   Set the timeout associated with this test.
    *  <p>
    *   The Timeout is a string containing "timeout"
    *   times (integer (int, 32-bits, msecs, default )), or NULL.
    *  <p>
    * @param     timeout The list of timeouts.
    * @see #getTimeout
    */
    public void setTimeout(int timeout) throws TestFileException {
	if (timeout < 0)
	    throw new TestFileException(LibResHandler.getResStr("api.attributes.testattributes.timeout.neg", 
								    Integer.toString(timeout)));

	m_Timeout = Integer.toString(timeout);
    }
     
   /**
    *   Set the timeout associated with this test.
    *  <p>
    *   The Timeout is a string containing "timeout"
    *   times (integer (int, 32-bits, msecs, default )), or NULL.
    *  <p>
    * @param     timeout The list of timeouts.
    * @see #getTimeout
    */
    public void setTimeout(String timeout) throws TestFileException {
	String ptimeout = CommonImpl.getSingleToken(timeout);
	if (ptimeout == null) {
	    m_Timeout = null;
	    return;
	}

	try {
	    int val = Integer.parseInt(ptimeout);
	    setTimeout(val);
	} catch (NumberFormatException e) {
	    throw new TestFileException(LibResHandler.getResStr("api.attributes.testattributes.timeout.bad", ptimeout));
	}
    }

}
