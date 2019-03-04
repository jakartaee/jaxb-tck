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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.SpecElemImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.SpecElem;
import com.sun.tgxml.tjtf.api.common.impl.NameValuePairImpl;
// </importgen>

/**
 * SpecElem - 
 *
 * <b>SpecElem</b> (Repository Test-Specific Entities) is the name value pair for
 * a description item that may be defined in a given repository for test-specific description entities.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    SpecElemImpl
 * ============================================================================================
 */


public class SpecElemImpl extends NameValuePairImpl implements SpecElem {

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
    *   SpecElem Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @return     The SpecElem associated with this documentation
    * @see com.sun.tgxml.tjtf.api.common.NameValuePair
    */
    public SpecElemImpl(String name, String value) throws TestFileException {
	super(name, value);
    }


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------

  /**
    *   Determine if two SpecElem's are equal.
    *  <p>
    * @return     true if the SpecElems are equal
    */
    public boolean equals(SpecElem other) {
	return (getName().equals(other.getName()) && getValue().equals(other.getValue())); 
    }
     

     


}
