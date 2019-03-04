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

package com.sun.tgxml.tjtf.api.data;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.ExternalData
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.io.PrintStream;
// </importgen>

/**
 * ExternalData - 
 *
 * <b>ExternalData</b> describes the data that a test needs to be able to
 * execute.
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExternalData (marker interface)
 * ============================================================================================
 */


public  interface ExternalData extends Data  {


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------




  /**
    *   Get the filename associated with this data.
    *  <p>
    *   This filename must be either a valid String.
    *  <p>
    * @return     The filename associated with this data.
    * @exception TestFileException if the filename is null.
    * @see #setSourceName
    */
    public String getSourceName() throws TestFileException;
     
   /**
    *   Set the filename associated with this data.
    *  <p>
    *   This filename must be either a valid String
    *   or NULL.
    *  <p>
    * @param     filename The  filename associated with this data.
    * @exception TestFileException if the filename is null.
    * @see #getSourceName
    */
    public void setSourceName(String filename)  throws TestFileException;



}
