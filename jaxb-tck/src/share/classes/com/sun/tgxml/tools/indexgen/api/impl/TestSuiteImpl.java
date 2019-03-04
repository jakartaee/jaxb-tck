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

package com.sun.tgxml.tools.indexgen.api.impl;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.impl.TestItemImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler; 
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * TestSuite - 
 *
 * <b>TestSuite</b> is a root element of doc.xml file 
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
 * @version
 * @author  Artem A. Aliev
 */



/*
 * ============================================================================================
 *    TestSuiteImpl
 * ============================================================================================
 */


public  class TestSuiteImpl extends TestItemImpl implements TestSuite {


    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private String          title;
    private String          description;
    private String          comments;
    private String          format;
    private String          id;

    private ArrayList       attrElems;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TestSuiteImpl() {
		init();
    }

    public TestSuiteImpl(String Title, String Description, String Format, String Comments) {
		title = Title;
		description = Description;
		format = Format;
		comments = Comments;
    }

    private void init() {
    }

   

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
    public String getTitle()  throws TestFileException {
		if(title == null) {
			throw new TestFileException(LibResHandler.getResStr("indexgen.error.nulltitle"));
		}
		return title;
	}
     
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
    public void setTitle(String title)  throws TestFileException{
		if(title == null) {
			throw new TestFileException(LibResHandler.getResStr("indexgen.error.nulltitle"));
		}
		this.title = title;
	}




  /**
    *   Get the description associated with this documentation.
    *  <p>
    *   This description must be a valid String.
    *  <p>
    * @return     The description associated with this documentation.
    * @see #setDescription
    */
    public String getDescription(){
		return description;
	}
   /**
    *   Set the description associated with this documentation.
    *  <p>
    *   This description must be a valid string.
    *  <p>
    * @param     description The title of the documentation.
    * @see #getDescription
    */
    public void setDescription(String description) {
		this.description = description;
	}


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
    public String getContentsFormat() {
		return format;

    } 
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
	public void setContentsFormat(String format) throws TestFileException {
		if (!TABLE_FORMAT.equals(format) && !LIST_FORMAT.equals(format)) {
			throw new TestFileException(LibResHandler.getResStr("indexgen.error.contentformat"));
		}
		this.format = format;
	}



  /**
    *   Get the comments associated with this documentation.
    *  <p>
    *   This comments may be a valid String or Null.
    *  <p>
    * @return     The comments associated with this documentation.
    * @see #setComments
    */
    public String getComments() {
		return comments;
	}
   /**
    *   Set the comments associated with this documentation.
    *  <p>
    *   This comments may be a valid String or Null.
    *  <p>
    * @param The comments  of the documentation.
    * @see #getComments
    */
    public void setComments(String comments) {
		this.comments = comments;
	}

   /**
	*   Get the ID of the documentation.
    *  <p>
    *   This ID may be a valid String or Null.
    *  <p>
    * @return     The ID associated with this documentation.
    * @see #setID
    */
    public String getID(){
		return id;
	}
   /**
    *   Set the ID of this documentation.
    *  <p>
    *   This ID may be a valid String or Null.
    *  <p>
    * @param The ID  of the documentation.
    * @see #getID
    */
    public void setID(String id){
		this.id = id;
	}

  /**
    *   Get the repository-specific attributes associated with this entity.
    *  <p>
    * @return     The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    * @see #setAttrElems
    */
    public ArrayList getAttrElems() {
        return attrElems;
    }
     
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
    public void setAttrElems(ArrayList attrElems) {
        this.attrElems = attrElems;
    }

}
