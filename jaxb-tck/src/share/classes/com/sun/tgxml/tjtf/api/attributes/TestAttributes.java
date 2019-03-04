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

package com.sun.tgxml.tjtf.api.attributes;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.TestAttributes
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * TestAttributes - 
 *
 * <b>TestAttributes</b> is the typed-attribute interface for describing the attributes
 * associated with a TestCase.
 * <p>
 * <b>TestAttributes</b> contains the following (from Attributes):<br><br>
 * <ul>
 *     <li> RequiredResources
 *     <li> AttrElems
 *     <li> TargetSpecs
 *  </ul> <br>
 *
 * <b>TestAttributes</b> additionally contain (as Strings or ArrayLists of Strings):<br><br>
 * <ul>
 *     <li> Timeout
 *  </ul> <br>
 *
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestAttributes
 * ============================================================================================
 */


public  interface TestAttributes extends Attributes  {

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
    *   times (integer), or NULL.
    * <p>
    * Timeout is a string encoding a timeout integer. A value specified in 
    *  seconds used to override the default time-out used for TCK tests. 
    *  <p>
    * @return     The timeout (String) value.
    * @see #setTimeout
    * @see java.lang.String
    */
    public String getTimeout();
     
   /**
    *   Set the timeout associated with this test.
    *  <p>
    *   The Timeout is a string containing "timeout"
    *   times (integer), or NULL.
    * <p>
    * Timeout is a string encoding a timeout integer. A value specified in 
    *  seconds used to override the default time-out used for TCK tests. 
    *  <p>
    * @param     timeout The timeout value.
    * @throws     TestFileException if timeout is less than zero, or not an integer.
    * @see #getTimeout
    * @see java.lang.String
    */
    public void setTimeout(String timeout) throws TestFileException;

   /**
    *   Set the timeout associated with this test.
    *  <p>
    *   The Timeout is an integer containing "timeout"
    *   times (integer, greater than 0).
    * <p>
    * Timeout is a timeout integer. A value specified in 
    *  seconds used to override the default time-out used for TCK tests. 
    *  <p>
    * @param     timeout The timeout value.
    * @throws     TestFileException if timeout is less than zero, or not an integer.
    * @see #getTimeout
    * @see java.lang.String
    */
    public void setTimeout(int timeout) throws TestFileException;


}
