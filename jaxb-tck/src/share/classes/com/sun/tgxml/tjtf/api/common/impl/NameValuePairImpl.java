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

package com.sun.tgxml.tjtf.api.common.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.common.impl.NameValuePairImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.NameValuePair;
import com.sun.tgxml.tjtf.resources.LibResHandler;
// </importgen>

/**
 * NameValuePair - 
 *
 * <b>NameValuePair</b>  is the name value pair for some description item.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    NameValuePairImpl
 * ============================================================================================
 */


public class NameValuePairImpl implements NameValuePair  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    
    /** The entity Name */
    private String m_name;
    
    /** The entity Value */
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
    *   Get the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedPackage associated with this documentation
    */
    public NameValuePairImpl() {
    }

  /**
    *   Get the TestedPackage associated with this documentation.
    *  <p>
    *   This TestedPackage is an optional field (required for JCK tests).
    *  <p>
    * @return     The TestedPackage associated with this documentation
    */
    public NameValuePairImpl(String name, String value) throws TestFileException {
	    setName(name);
	    setValue(value);
    }

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

  /**
    *   Get the name associated with this rsde.
    *  <p>
    * @return     The name associated with this rsde
    * @see #setName
    */
    public String getName()  {
	return m_name; 
    }
     
   /**
    *   Set the name associated with this rsde.
    *  <p>
    * @param     name The name of the rsde.
    * @see #getName
    */
    public void setName(String name)  throws TestFileException {
	if (name == null || name.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.namevalpair.name.null"));
	m_name = name;
    }



  /**
    *   Get the value associated with this rsde.
    *  <p>
    * @return     The value associated with this rsde
    * @see #setValue
    */
    public String getValue()  {
	return m_value; 
    }
     
   /**
    *   Set the value associated with this rsde.
    *  <p>
    * @param     value The value of the rsde.
    * @see #getValue
    */
    public void setValue(String value) {
	if (value == null)
	    value = "";
	m_value = value;
    }




    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------

     

     


}
