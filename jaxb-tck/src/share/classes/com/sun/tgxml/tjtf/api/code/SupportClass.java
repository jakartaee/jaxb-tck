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

package com.sun.tgxml.tjtf.api.code;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.SupportClass
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.ArrayList;
// </importgen>

/**
 * SupportClass - 
 *
 * <b>SupportClass</b> is the marker interface to a Class (code).
 * <b>SupportClass</b> describes an auxiliary class that a test uses to compile or execute. 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    SupportClass 
 * ============================================================================================
 */


public  interface SupportClass extends Code {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

  /**
    *   Get the ClassID string.
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setClassID
    */
    public String getClassID();
     
  /**
    *   Set the ClassID string.
    *  <p>
    * @param     classID The string containing the class ID.
    * @see #getClassID
    */
    public void setClassID(String classID);

}

