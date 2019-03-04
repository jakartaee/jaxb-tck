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

package com.sun.tgxml.tjtf.api.tests;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.Testgroup
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.LibDocumentation;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.TreeMap;
// </importgen>

/**
 * Library - 
 *
 * <b>Library</b> is a (root) entity that describes a library of functionality (classes, auxiliary classes, etc)
 * that a code entity may depend on.  
 * <p>
 * A <b>TestGroup</b> or <b>TestCase</b>s <b>CodeSet</b> may specify a direct dependency on
 * a  <b>Library</b>.
 * <p>
 * <b>Library</b>s derive from <b>TestItem</b>. and contain all of the
 * descriptions, attributes, and code that is commonly shared by the code grouping.
 * <p>
 * <b>Library</b>s contain the following fields:
 * <p>
 * <ul>
 *     <li> ID
 *     <li> VarID
 *     <li> LibraryDocumentation
 *     <li> LibraryAttributes
 *     <li> CodeSet
 *  </ul> <br>
 * <p>
 *
 * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
 * @version 	1.1, 10/23/02
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Library
 * ============================================================================================
 */


public  interface Library extends TestRoot, VariableTestItem {

    /*
    * ============================================================================================
    *    Member Fields
    * ============================================================================================
    */


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

  /**
    *   Get the Documentation associated with this Library.
    *  <p>
    * @return   The Documentation associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    * @see #setLibDocumentation
    */
    public LibDocumentation getLibDocumentation();
     
  /**
    *   Set the Documentation associated with this Library.
    *  <p>
    * @param     doc The Documentation associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.documentation.LibDocumentation
    * @see #getLibDocumentation
    */
    public void setLibDocumentation(LibDocumentation doc);




  /**
    *   Get the Attributes associated with this Library.
    *  <p>
    * @return   The Attributes associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    * @see #setLibAttributes
    */
    public LibAttributes getLibAttributes();
     
  /**
    *   Set the Attributes associated with this Library.
    *  <p>
    * @param     attrs The Attributes associated with the Library, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    * @see #getLibAttributes
    */
    public void setLibAttributes(LibAttributes attrs);

  /**
    *  Returns true if library is Inline.
    *  For InlineLibrary instances it always returns true.
    *  For external libraries the value of inline flag associated with 
    *  this Library (false by default).
    *
    * @see #setInline
    */
    public boolean isInline();
     
  /**
    *  Sets the inline flag associated with this Library.
    *  (For InlineLibrary instances does nothing)
    *  <p>
    * @param     isInline  the inline flag value.
    * @see #isInline
    */
    public void setInline(boolean isInline);

}
