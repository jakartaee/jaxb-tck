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

package com.sun.tgxml.tjtf.api.common;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.descriptions.NameValuePair
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
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
 *    NameValuePair
 * ============================================================================================
 */


public interface NameValuePair  {


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   


  /**
    *   Get the name associated with this rsde.
    * Value can not be a null value (i.e. "");
    *  <p>
    * @return     The name associated with this rsde
    * @see #setName
    */
    public String getName();
     
   /**
    *   Set the name associated with this rsde.
    * Value can not be set to a null value (i.e. "");
    *  <p>
    * @param     name The name of the rsde.
    * @see #getName
    */
    public void setName(String name) throws TestFileException;


  /**
    *   Get the value associated with this rsde.
    * Value can be a null value (i.e. "");
    *  <p>
    * @return     The value associated with this rsde
    * @see #setValue
    */
    public String getValue();
     
   /**
    *   Set the value associated with this rsde.
    * Value can be a null value (i.e. "");
    *  <p>
    * @param     value The value of the rsde.
    * @see #getValue
    */
    public void setValue(String value);


    


}
