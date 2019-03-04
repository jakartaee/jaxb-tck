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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.LibraryDependency
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * LibraryDependency - 
 *
 * <b>LibraryDependency</b> is the interface for a code dependency on a Library.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    LibraryDependency
 * ============================================================================================
 */


public  interface LibraryDependency extends CodeDependency {

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
   
  /**
    *   Get the Id for the library.
    *  <p>
    * @return   A string containing the name of the library.
    * @throws TestFileException if the ID is unset or invalid.
    * @see #setID
    */
    public String getID() throws TestFileException;
     
  /**
    *   Set the Id for the library.
    *  <p>
    * @param ID the dependencie's ID
    * @throws TestFileException if the ID is invalid.
    * @see #getID
    */
    public void setID(String ID) throws TestFileException;



}
