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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.TestItem
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.documentation.Documentation;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import java.util.ArrayList;
// </importgen>

/**
 * TestVariant - 
 *
 * <b>TestVariant</b> is the interface that describes that this object may have variants
 * (i.e. two items with the same ID).  TestGroup and Libraries have variants
 * <p>
 *  A variant typically contains a unique VarID descriptor.
 * 
 * <p>
 *
 * @version 	1.1, 10/22/2002
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestVariant  (marker interface)
 * ============================================================================================
 */


public  interface TestVariant {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

  /**
    * Gets the variant identifier associated with the TestCase or Library.
    *  <p>
    * @return  A string containing the identifier in format VarName:VarOrder
    * @see #setVarID
    */
    public String getVarID();

     
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
    public void setVarID(String varID) throws TestFileException;


   /**
    * Sets VarOrder for the variant
    * @param value the string is of the form: "x[.y]",
    *         where x,y are positive integers 
    *         and the value x.y represents a positive decimal value between
    *         00.00 and 99.99.
    * @throws TestFileException if passed value is in illegal format
    * @see #getVarOrder
    */
   public void setVarOrder(String value) throws TestFileException;


   /**
    * Returns VarOrder as String
    * @see #setVarOrder
    */
   public String getVarOrder();


   /**
    * Returns VarOrder as int.
    * Returned order must be a value between 0 - 9999 (inclusive)
    * and represents a decimal that is right-shifted by two places.
    */
   public int order();



   /**
    * Sets variant name for the variant
    * @see #getVarName
    */
   public void setVarName(String value);


   /**
    * Returns the variant name if VarID is set, and null if VarID is not set.
    * @see #setVarName
    */
   public String getVarName();

}
