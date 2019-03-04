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

package com.sun.tgxml.tjtf.api.documentation;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.Documentation
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.ArrayList;
// </importgen>

/**
 * Documentation - 
 *
 * <b>Documentation</b> is the basic interface for describing documentation
 * associated with a test.
 * <p>
 * <b>Documentation</b> contains the following:<br><br>
 * <ul>
 *     <li> title
 *     <li> description
 *     <li> authors
 *  </ul> <br>
 *
 * <p>
 * A <b>documentation</b> is registered with a <b>TestGroup</b>, <b>Library</b>, and <b>TestCase</b> 
 *  elements.  
 * <p> 
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Documentation
 * ============================================================================================
 */


public  interface Documentation  {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

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
    * @return     The title associated with this documentation
    * @exception TestFileException if the title is NULL
    * @see #setTitle
    */
    public String getTitle()  throws TestFileException;
     
   /**
    *   Set the title associated with this documentation.
    *  <p>
    *   This title must be a valid String (for a TestGroup and Library)
    *   and not NULL.  For a TestCase, the title may be either a
    *   valid string or NULL.
    *  <p>
    * @param     title The title of the documentation.
    * @exception TestFileException if the title is NULL
    * @see #getTitle
    */
    public void setTitle(String title)  throws TestFileException;




  /**
    *   Get the description associated with this documentation.
    *  <p>
    *   This description may be a valid String or Null (for a TestGroup and Library).
    *   For a TestCase, the description must be a valid string.
    *  <p>
    * @return     The description associated with this documentation.
    * @see #setDescription
    */
    public String getDescription();
     
   /**
    *   Set the description associated with this documentation.
    *  <p>
    *   This description may be a valid String or Null (for a TestGroup and Library).
    *   For a TestCase, the description must be a valid string.
    *  <p>
    * @param     description The title of the documentation.
    * @see #getDescription
    */
    public void setDescription(String description);




  /**
    *   Get the authors associated with this documentation.
    *  <p>
    *   The authors list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each author entry is a string.
    *  <p>
    * @return     The authors associated with this documentation
    * @see #setAuthors
    */
    public ArrayList getAuthors();

     
 /**
    *   Set the authors associated with this documentation.
    *  <p>
    *   The authors list is always defined, and may contain zero or more entries.
    *  <p>
    *   Each author entry is a string.
    *  <p>
    * @param     authors The ArratList of author entries.
    * @see #getAuthors
    */
    public void setAuthors(ArrayList authors);



}
