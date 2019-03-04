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

package com.sun.tgxml.tjtf.api.documentation.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.impl.LibDocumentationImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.LibDocumentation;
import java.util.ArrayList;
// </importgen>

/**
 * LibDocumentation - 
 *
 * <b>LibDocumentation</b> is the basic interface for describing a JavaTest test description
 * associated with a Library.
 * <p>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    LibDocumentationImpl
 * ============================================================================================
 */


public  class LibDocumentationImpl extends DocumentationImpl implements LibDocumentation  {

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
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public LibDocumentationImpl() {
    }

    public LibDocumentationImpl(String title, String description, ArrayList authors) {
	super(title, description, authors);
    }
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the title associated with this documentation.
    *  <p>
    *   This title must be a valid String (for a TestGroup and Library)
    *   and not NULL.  For a TestCase, the title may be either a
    *   valid string or NULL.
    *  <p>
    *   Overrides Documentation.getTitle()
    * <p>
    * @return     The title associated with this documentation
    * @exception TestFileException if the title is NULL
    * @see #setTitle
    */
    public String getTitle()  throws TestFileException {
	String title = super.getTitle();

	if (title == null || title.equals(""))
	    throw new TestFileException("Null title.");

	return title;
    }
     
   /**
    *   Set the title associated with this documentation.
    *  <p>
    *   This title must be a valid String (for a TestGroup and Library)
    *   and not NULL.  For a TestCase, the title may be either a
    *   valid string or NULL.
    *  <p>
    *   Overrides Documentation.setTitle()
    * <p>
    * @param     title The title of the documentation.
    * @exception TestFileException if the title is NULL
    * @see #getTitle
    */
    public void setTitle(String title)  throws TestFileException {
	if (title == null || title.equals(""))
	    throw new TestFileException("Null title.");

	super.setTitle(title);
    }


}
