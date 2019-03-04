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

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.tjtf.resources.LibResHandler;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * TestVariantImpl - 
 *
 * <b>TestVariantImpl</b> is the implementation of the TestVariant interface. 
 * 
 * <p>
 *
 * @author  Dmitry Fazunenko
 */


/*
 * ======================================================================
 *    TestVariantImpl
 * ====================================================================== 
 */


public class TestVariantImpl implements TestVariant {

    private String            varName;
    private String            varOrder;
    private int               order;


    /*
     * ====================================================================== 
     *    Ctors
     * ====================================================================== 
     */

     public TestVariantImpl() {
         init();
     }

     public TestVariantImpl(String VarID) throws TestFileException {
         setVarID(VarID);
     }

     private void init() {
         varName = null;
         varOrder = null;
         order = 0;
     }

    /*
     * ====================================================================== 
     *    Methods
     * ====================================================================== 
     */

   /**
    * Gets the variant identifier associated with the TestCase or Library.
    *  <p>
    * @return  A string containing the identifier in format VarName:VarOrder
    * @see #setVarID
    */
    public String getVarID() {
        if (varName != null && varName.length() > 0) {
            return varName + ":" + varOrder;
        } else {
            return null;
        }
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
        if (varID != null) {
            int colon = varID.lastIndexOf(':');
            if (colon < 0) {
                throw new TestFileException(LibResHandler.getResStr(
                   "api.varid.missedcolon", varID));
            } else {
                setVarName(varID.substring(0, colon));
                setVarOrder(varID.substring(colon + 1));
            }
        } else {
            init();
        }
    }

   /**
    * Sets variant name for the variant
    * @see #getVarName
    */
   public void setVarName(String value) {
       varName = value;
   }


   /**
    * Returns the variant name if VarID is set, and null if VarID is not set.
    * @see #setVarName
    */
   public String getVarName() {
       return varName;
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
       order = parseVarOrder(value);
       varOrder = orderToString(order);
   }


   /**
    * Returns VarOrder as String
    * @see #setVarOrder
    */
   public String getVarOrder() {
       return varOrder;
   }


   /**
    * Returns VarOrder as int.
    * Returned order must be a value between 0 - 9999 (inclusive)
    * and represents a decimal that is right-shifted by two places.
    */
   public int order() {
       return order;
   }

   
   private static final int MAX_FRACTION = 2;
   private static final String MIN_VALUE = "0.0";
   private static final String MAX_VALUE = "99.99";

   private int parseVarOrder(String vo) throws TestFileException {
       if (vo == null) {
           throw new TestFileException(LibResHandler.getResStr(
                  "api.varid.null"));
       }

       try {
           BigDecimal bd = new BigDecimal(vo);
           if (bd.compareTo(new BigDecimal(MIN_VALUE)) < 0) 
               throw new TestFileException(LibResHandler.getResStr(
                      "api.varid.toosmall", vo, MIN_VALUE));
           if (bd.compareTo(new BigDecimal(MAX_VALUE)) > 0) 
               throw new TestFileException(LibResHandler.getResStr(
                      "api.varid.toolarge", vo, MAX_VALUE));
           if (bd.scale() > MAX_FRACTION) 
               throw new TestFileException(LibResHandler.getResStr(
                      "api.varid.toomanydigits", vo, "" + MAX_FRACTION));
           return bd.movePointRight(MAX_FRACTION).intValue(); 
       } catch (NumberFormatException e) {
           throw new TestFileException(LibResHandler.getResStr(
                  "api.varid.cannotparse", vo));
       }
   }

   private String orderToString(int order) {
       try {
           BigDecimal bd = new BigDecimal("" + order);
           String str = bd.movePointLeft(MAX_FRACTION).toString(); 
           // removes zeros from the end 
           int i = str.length() - 1;
           while (i >= 0 && str.charAt(i) == '0')
               i--;

           // removes '.' sign from the end 
           if (i >= 0 && str.charAt(i) == '.')
               i--;
           return str.substring(0, i+1);
           
       } catch (NumberFormatException e) {
           // should never arise
           return "";
       }
   }


}
