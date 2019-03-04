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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.impl.RequiredResourceImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.RequiredResource;
import com.sun.tgxml.tjtf.api.common.impl.NameValuePairImpl;
// </importgen>

/**
 * RequiredResource - 
 *
 * <b>RequiredResource</b>  is the name value pair for
 * an attribute that may be defined in a given repository.  These
 * resources tend to be physical requirements that a repository must have
 * to build or run.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    RequiredResourceImpl
 * ============================================================================================
 */


public class RequiredResourceImpl extends NameValuePairImpl implements RequiredResource {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

  /**
    *   RequiredResourceImpl Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @return     The RequiredResource associated with this documentation
    * @see com.sun.tgxml.tjtf.api.common.NameValuePair
    */
    public RequiredResourceImpl(String name, String value) throws TestFileException {
	super(name, value);
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------

  /**
    *   Determine if two RequiredResource's are equal.
    *  <p>
    * @return     true if the RequiredResources are equal
    */
    public boolean equals(RequiredResource other) {
	return (getName().equals(other.getName()) && getValue().equals(other.getValue())); 
    }
     

}     
