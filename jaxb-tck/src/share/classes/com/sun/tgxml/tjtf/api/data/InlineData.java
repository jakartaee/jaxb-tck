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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.InlineData
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.Export;
import java.io.PrintStream;
// </importgen>

/**
 * InlineData - 
 *
 * <b>InlineData</b> describes the data that a test needs to be able to
 * execute.
 *<p>
 * <b>InlineData</b> contains either a URL to a data file, or inline text
 * that represents data.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineData (marker interface)
 * ============================================================================================
 */


public interface InlineData extends Data, Export {


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the inline-data (String) associated with this data element.
    *  <p>
    *   This data must be either a valid String
    *   or NULL.
    *  <p>
    * @return     The data associated with this data element.
    * @see #setData
    */
    public String getData();
     
   /**
    *   Set the inline-data associated with this data element.
    *  <p>
    *   This data must be either a valid String
    *   or NULL.
    *  <p>
    * @param     data The data associated with this data element.
    * @see #getData
    */
    public void setData(String data);


}
