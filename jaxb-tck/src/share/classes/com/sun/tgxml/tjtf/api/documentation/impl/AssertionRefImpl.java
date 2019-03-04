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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.AssertionRefImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.AssertionRef;
import com.sun.tgxml.tjtf.resources.LibResHandler;
// </importgen>

/**
 * AssertionRef - 
 *
 * <b>AssertionRef</b> is a (non-specification) assertion that a given test
 * is testing.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    AssertionRefImpl
 * ============================================================================================
 */


public class AssertionRefImpl implements AssertionRef {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
     private String m_ID;


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   

  /**
    *   Validator.
    *  <p>
    *   Validates that the string is a properly formed reference ID.
    *  <p>
    * @param     id  The AssertionRef ID value
    */
    public  boolean valid (String id) {
	// default
	return true;
    }

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------


  /**
    *   AssertionRef Constructor (annon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @param     id  The AssertionRef value
    */
    public AssertionRefImpl(){
    }

  /**
    *   AssertionRef Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @param     id  The AssertionRef value
    */
    public AssertionRefImpl(String id){
	m_ID = id;
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the value associated with this rsde.
    *  <p>
    * @return     The value associated with this rsde
    * @see #setRef
    */
    public String getRef() {
	return m_ID; 
    }
     
   /**
    *   Set the reference ID for this Assertion.
    *  <p>
    * @param     id The reference ID for this Assertion.
    * @see #getRef
    */
    public void setRef(String id) throws TestFileException {
	if (id == null || id.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.doc.assertionref.id.null") );


	if (! valid(id))
	    throw new TestFileException(LibResHandler.getResStr("api.doc.assertionref.id.invalid", id) );

	m_ID = id;
    }

}
