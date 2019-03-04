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

package com.sun.tgxml.tjtf.api.code.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.impl.ExternalSupportClassImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import java.util.ArrayList;
// </importgen>

/**
 * ExternalSupportClass - 
 *
 * <b>ExternalSupportClass</b> is the marker interface to a externally referenced Class (code).
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExternalSupportClassImpl
 * ============================================================================================
 */


public  class ExternalSupportClassImpl extends SupportClassImpl implements ExternalSupportClass {
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
    public ExternalSupportClassImpl() {
	super();
	init();
    }

   public ExternalSupportClassImpl(String langtype, String classID, ArrayList targetSpecs, String sourceName) {
       super(classID, targetSpecs);
       setCodeDelegate(new CodeImpl(langtype));
       try {
	   setSourceName(sourceName);
       } catch (TestFileException e) {

       }
    }

    private void init() {
	setCodeDelegate(new CodeImpl());
	m_sourceName = null;
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

  /**
    *   Get the (fully-qualified) class name for the class.
    *  <p>
    * @return   A string containing the (fully-qualified) class name for the support class.
    * @exception TestFileException if class name is NULL
    * @see #setName
    */
    public String getSourceName() throws TestFileException {
	if (m_sourceName == null || m_sourceName.equals(""))
	    throw new TestFileException("Null source.");
	return m_sourceName;
    }
     
  /**
    *   Set the (fully-qualified) class name for the class.
    *  <p>
    * @param     className The string containing the (fully-qualified) class name for the support class.
    * @exception TestFileException if class name is NULL
    * @see #getName
    */
    public void setSourceName(String sourceName) throws TestFileException {
	if (sourceName == null)
	    throw new TestFileException("Null source name.");

	    m_sourceName = sourceName;
    }


}

