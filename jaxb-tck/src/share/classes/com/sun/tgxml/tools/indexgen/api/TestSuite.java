/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.indexgen.api;


import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.XMLObj;
import java.util.ArrayList;

/**
 * TestSuite - 
 *
 * <b>TestSuite</b> is the basic interface for root element of doc.xml file 
 * <p>
 * <b>TestSuite</b> contains the following:<br><br>
 * <ul>
 *     <li> title
 *     <li> description
 *     <li> content format ( list or table)
 *     <li> comments
 *  </ul> <br>
 *
 * <p>
 * @author  Artem A. Aliev
 */


/*
 * ============================================================================================
 *    TestSuite
 * ============================================================================================
 */


public  interface TestSuite  extends XMLObj {


	public static final String LIST_FORMAT = "list";
	public static final String TABLE_FORMAT = "table";



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
    *   This title must be a valid String.
    *  <p>
    * @return     The title associated with this documentation
    * @exception TestFileException if the title is NULL
    * @see #setTitle
    */
    public String getTitle()  throws TestFileException;
     
   /**
    *   Set the title associated with this documentation.
    *  <p>
    *   This title must be a valid String
    *   and not NULL. 
    *  <p>
    * @param     title The title of the documentation.
    * @exception TestFileException if the title is NULL
    * @see #getTitle
    */
    public void setTitle(String title)  throws TestFileException;




  /**
    *   Get the description associated with this documentation.
    *  <p>
    *   This description must be a valid String.
    *  <p>
    * @return     The description associated with this documentation.
    * @see #setDescription
    */
    public String getDescription();
     
   /**
    *   Set the description associated with this documentation.
    *  <p>
    *   This description must be a valid string.
    *  <p>
    * @param     description The title of the documentation.
    * @see #getDescription
    */
    public void setDescription(String description);




  /**
    *   Get the content format of this documentation.
    *  <p>
	* 
    *  <p>
    *   There are two format <code>TestSuite.LIST_FORMAT </code> and 
    * <code>TestSuite.TABLE_FORMAT </code>
    *  <p>
    * @return content format of this documentation.
    * @see #setContentsFormat
    * @see #LIST_FORMAT
    * @see #TABLE_FORMAT
    */
    public String getContentsFormat();

     
  /**
    *   Set the content format of this documentation.
    *  <p>
	* 
    *  <p>
    *   There are to format <code>TestSuite.LIST_FORMAT </code> and 
    * <code>TestSuite.TABLE_FORMAT </code>
    *  <p>
    * @exception TestFileException if the format is not a LIST_FORMAT or TABLE_FORMAT
    * @see #getContentsFormat
    * @see #LIST_FORMAT
    * @see #TABLE_FORMAT
    */
	public void setContentsFormat(String format) throws TestFileException;



  /**
    *   Get the comments associated with this documentation.
    *  <p>
    *   This comments may be a valid String or Null.
    *  <p>
    * @return     The comments associated with this documentation.
    * @see #setComments
    */
    public String getComments();
     
   /**
    *   Set the comments associated with this documentation.
    *  <p>
    *   This comments may be a valid String or Null.
    *  <p>
    * @param comments The comments of the documentation.
    * @see #getComments
    */
    public void setComments(String comments);


  /**
    *   Get the ID of the documentation.
    *  <p>
    *   This ID may be a valid String or Null.
    *  <p>
    * @return     The ID associated with this documentation.
    * @see #setID
    */
    public String getID();
     
   /**
    *   Set the ID of this documentation.
    *  <p>
    *   This ID may be a valid String or Null.
    *  <p>
    * @param id The ID of the documentation.
    * @see #getID
    */
    public void setID(String id);

    
  /**
    *   Get the repository-specific attributes associated with this entity.
    *  <p>
    *   The AttrElems is always an ArrayList with zero or
    *   more AttrElem objects.
    *  <p>
    * @return     The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    * @see #setAttrElems
    */
    public ArrayList getAttrElems();
     
   /**
    *   Set the repository-specific attributes associated with this entity.
    *  <p>
    *   The AttrElems is always an ArrayList with zero or
    *   more AttrElem objects.
    *  <p>
    * @param     attrelems The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    * @see #getAttrElems
    */
    public void setAttrElems(ArrayList attrelems);

}
