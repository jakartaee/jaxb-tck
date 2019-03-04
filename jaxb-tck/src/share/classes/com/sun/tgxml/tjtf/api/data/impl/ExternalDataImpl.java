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

package com.sun.tgxml.tjtf.api.data.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.impl.ExternalDataImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.data.*;
import java.io.PrintStream;
// </importgen>

/**
 * ExternalDataImpl - 
 *
 * <b>ExternalDataImpl</b> describes the data that a test needs to be able to
 * execute.
 *<p>
 * <b>ExternalData</b> contains either a URL to a data file, or inline text
 * that represents data.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExternalDataImpl
 * ============================================================================================
 */


public  class ExternalDataImpl extends DataImpl implements ExternalData  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private String m_sourceName;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    
    public ExternalDataImpl() {

    }

    public ExternalDataImpl(String fileName) {
	m_sourceName = fileName;
    }

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
    public String getSourceName() throws TestFileException {
	if (m_sourceName == null)
	    throw new TestFileException("Null filename.");

	return m_sourceName;
    }
     
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
    public void setSourceName(String filename)  throws TestFileException {
	if (filename == null)
	    throw new TestFileException("Null filename.");
	m_sourceName = filename;
    }


}
