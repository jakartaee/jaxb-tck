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

package com.sun.tgxml.tjtf.api.tests.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.impl.LibraryImpl
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.LibDocumentation;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * Library - 
 *
 * <b>Library</b> is the entity that describes a library that TestCodes depend on.  
 * <p>
 * <b>Library</b>s derive from <b>TestItem</b>. These properties describe
 * the comments, assertions, and descriptions that this class contains.
 * <p>
 *
 * @version 	1.1, 10/28/02
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    LibraryImpl
 * ============================================================================================
 */


public  class LibraryImpl extends TestItemImpl implements Library  {


    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private TestVariantImpl            m_TestVar;
    private boolean                    inlineFlag;
   

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public LibraryImpl() {
	super();
	init();
    }

    public LibraryImpl(String ID, LibDocumentation documentation, LibAttributes attributes, 
			 CodeSet codeset ) {
	super(ID, documentation, attributes, codeset);
	init();
    }

    public LibraryImpl(String ID, String VarID, LibDocumentation documentation, LibAttributes attributes, 
			 CodeSet codeset ) throws TestFileException {
	super(ID, documentation, attributes, codeset);
	m_TestVar = new TestVariantImpl(VarID);
    }

 
    private void init() {
	m_TestVar = new TestVariantImpl();
    }
  

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

  /**
    * Gets the variant identifier associated with the TestCase or Library.
    *  <p>
    * @return  A string containing the identifier in format VarName:VarOrder
    * @see #setVarID
    */
    public String getVarID() {
        return m_TestVar.getVarID();
    }

     
  /**
    * Sets the variant identifier associated with the TestCase or Library.
    * <p>
    * @param  varID The string containing the identifier in format
    *         VarName:VarOrder
    * @throws TestFileException if passed varID is in illegal format
    * @see #getVarID
    * @see #setVarName
    * @see #setVarOrder
    */
    public void setVarID(String varID) throws TestFileException {
       m_TestVar.setVarID(varID);
   }

   /**
    * Sets VarOrder for the variant
    * @params value the string is of the form: "x[.y]",
    *         where x,y are positive integers 
    *         and the value x.y represents a positive decimal value between
    *         00.00 and 99.99.
    * @throws TestFileException if passed value is in illegal format
    * @see #getVarOrder
    */
   public void setVarOrder(String value) throws TestFileException {
       m_TestVar.setVarOrder(value);
   }

   /**
    * Returns VarOrder as String
    * @see #setVarOrder
    */
   public String getVarOrder() {
        return m_TestVar.getVarOrder();
   }

   /**
    * Returns VarOrder as int.
    * Returned order must be a value between 0 - 9999 (inclusive)
    * and represents a decimal that is right-shifted by two places.
    */
   public int order() {
        return m_TestVar.order();
   }


   /**
    * Sets variant name for the variant
    * @see #getVarName
    */
   public void setVarName(String value) {
       m_TestVar.setVarName(value);
   }


   /**
    * Returns the variant name if VarID is set, and null if VarID is not set.
    * @see #setVarName
    */
   public String getVarName() {
        return m_TestVar.getVarName();
   }


  /**
    *   Get the Documentation associated with this Library.
    *  <p>
    * @return   The Documentation associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    * @see #setLibDocumentation
    */
    public LibDocumentation getLibDocumentation() {
	return (LibDocumentation) getDocumentation();
    }
     
  /**
    *   Set the Documentation associated with this Library.
    *  <p>
    * @param     doc The Documentation associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    * @see #getLibDocumentation
    */
    public void setLibDocumentation(LibDocumentation doc) {
	_setDocumentation(doc);
    }




  /**
    *   Get the Attributes associated with this Library.
    *  <p>
    * @return   The Attributes associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    * @see #setLibAttributes
    */
    public LibAttributes getLibAttributes() {
	return (LibAttributes) getAttributes();
    }
     
  /**
    *   Set the Attributes associated with this Library.
    *  <p>
    * @param     attrs The Attributes associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    * @see #getLibAttributes
    */
    public void setLibAttributes(LibAttributes attrs) {
	_setAttributes(attrs);
    }

  /**
    *  Returns the value of inline flag associated with 
    *  this Library (false by default).
    *
    * @see #setInclude
    */
    public boolean isInline() {
        return inlineFlag;
    }
     
  /**
    *  Sets the inline flag associated with this Library.
    *  <p>
    * @param     isInline  the inline flag value.
    * @see #isInclude
    */
    public void setInline(boolean isInline) {
        inlineFlag = isInline;
    }


     


}
