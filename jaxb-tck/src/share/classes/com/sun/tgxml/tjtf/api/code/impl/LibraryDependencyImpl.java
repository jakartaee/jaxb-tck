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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.impl.LibraryDependencyImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.LibraryDependency;
import com.sun.tgxml.tjtf.resources.LibResHandler;
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
 *    LibraryDependencyImpl
 * ============================================================================================
 */


public  class LibraryDependencyImpl implements LibraryDependency {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */


    private String m_ID;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public LibraryDependencyImpl() {
	init();
    }

   public LibraryDependencyImpl(String id) {
	m_ID = id;
    }

    private void init() {
	m_ID = "";
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


   
  /**
    *   Get the Id for the library.
    *  <p>
    * @return   A string containing the name of the library.
    * @see #setID
    */
    public String getID() throws TestFileException {
	if (m_ID == null || m_ID.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.code.libdep.id.null") );

	return m_ID;
    }
     
  /**
    *   Set the Id for the library.
    *  <p>
    * @param     sourcelang The string containing the source language.
    * @see #getID
    */
    public void setID(String ID) throws TestFileException {
	if (ID == null || ID.equals(""))
	    throw new TestFileException(LibResHandler.getResStr("api.code.libdep.id.null") );

	m_ID = ID;
    }

}

