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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.InlineData
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.data.DataType;
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


public class InlineDataImpl extends DataImpl implements InlineData {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private String m_targetName;
    private String m_data;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    
    public InlineDataImpl() {
	super();
	init();
    }
    
    public InlineDataImpl(String data) {
	super();
	init();
	setData(data);
    }
    
    public InlineDataImpl(String data, String filename, DataType dataType) {
	super(dataType);
	init();
	setData(data);
	setTargetName(filename);
    }


    private void init() {
	m_targetName = null;
	m_data = null;
    }

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the inline-data associated with this data element.
    *  <p>
    *   This data must be either a valid String
    *   or NULL.
    *  <p>
    * @return     The data associated with this data element.
    * @see #setData
    */
    public String getData() {
	return m_data;
    }
     
   /**
    *   Set the inline-data associated with this data element.
    *  <p>
    *   This data must be either a valid String
    *   or NULL.
    *  <p>
    * @param     data The data associated with this data element.
    * @see #getData
    */
    public void setData(String data) {
	m_data = data;
    }


  /**
    *   Get the Filename for the exportable.
    *  <p>
    * @return   A string containing the Filename, or NULL.
    * @see #setTargetName
    */
    public String getTargetName() {
	return m_targetName;
    }

     
  /**
    *   Set the Filename for the exportable.
    *  <p>
    * @param     filename The string containing the filename for the exportable.
    * @see #getTargetFilename
    */
    public void setTargetName(String filename) {
	m_targetName = filename;
    }

     
  /**
    *   Is this item exportable (predicate).
    *  <p>
    */
    public boolean isExport() {
	return (m_targetName != null && ! m_targetName.equals("") && getType() != null);
    }



}
