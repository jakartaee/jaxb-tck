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

package com.sun.tgxml.tools.filter.libutil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.api.tests.Library;

/**
 * An object that maps libID to ArrayList of Variants IR
 *
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class VariantsMap {

     HashMap map = null;

     /**
      * Creates an empty map
      */
     public VariantsMap() {
         map = new HashMap();
     }

     /**
      * Creates a map for the passed variants, where libID maps to
      * variants with the same libID.
      */
     public VariantsMap(Library[] libs) {
         this();
         for (int i = 0; i < libs.length; i++) {
             addVariant(libs[i]);
         }
     }


    /**
     * Adds var to the variant list of var.getID()
     * If passed var is null this method just returns.
     */
     public void addVariant(Library var) {
         if (var == null) 
             return;

         String libID = IR.getID(var);
         addVariant(libID, var);
     }

    /**
     * Adds var to the variant list of libID. libID may be different 
     * from var.getID()
     * If either passed var or libID is null this method just returns.
     */
     public void addVariant(String libID, Library var) {
         if (var == null || libID == null) 
             return;

         ArrayList vars = variants(libID);
         if (vars == null) {
             vars = new ArrayList();
             vars.add(var);
             map.put(libID, vars);
         } else {
             if (!vars.contains(var))
                 vars.add(var);
         }
     }


    /**
     * Adds libID that maps to an empty ArrayList
     * If passed libID is null or already exists this method just returns.
     */
     public void addLibID(String libID) {
         if (libID == null || map.containsKey(libID)) 
             return;
         map.put(libID, new ArrayList());
     }




    /**
     * Removes var from the variant list of var.getID()
     * If passed var is null this method returns false.
     * @return  true if variant has been removed, false otherwise
     */
     public boolean removeVariant(Library var) {
         if (var == null) 
             return false;

         String libID = IR.getID(var);
         return removeVariant(libID, var);
     }

    /**
     * Removes var from the variant list of libID. libID may be different 
     * from var.getID()
     * If either passed var or libID is null this method just returns false
     * @return  true if variant has been removed, false otherwise
     */
     public boolean removeVariant(String libID, Library var) {
         if (var == null || libID == null) 
             return false;

         ArrayList vars = variants(libID);
         if (vars == null) {
             return false;
         } else {
             if (vars.contains(var)) {
                 vars.remove(var);
                 return true;
             } else {
                 return false;
             }
         }
     }


    /**
     * Removes libID from the map.
     * If passed libID is null or this method just returns false.
     * @return  true if libID has been removed, false otherwise
     */
     public boolean removeLibID(String libID) {
         if (libID == null || !map.containsKey(libID)) 
             return false;
         map.remove(libID);
         return true;
     }



    /**
     * Returns the list of all variants by libID
     */
     public ArrayList variants(String libID) {
         return (ArrayList)(map.get(libID));
     }

    /**
     * Returns the iterator over the all libIDs 
     */
     public Iterator libIDs() {
         return map.keySet().iterator();
     }

    /**
     * Returns the String representation of VariantsMap if format:
     * libID_1: VarID1 VarID2 ...
     * libID_2: VarID1 VarID2 ...
     */
     public String toString() {
         StringBuffer sb = new StringBuffer();
         for (Iterator it = map.keySet().iterator(); it.hasNext();) {
             String libID = (String)it.next();
             sb.append(libID + ": ");
             for (Iterator varIt = variants(libID).iterator();varIt.hasNext();){
                 String variantID = ((Library)varIt.next()).getVarID();
                 sb.append(variantID == null ? "<NoVarID>" : variantID);
                 sb.append("  ");
             }
             sb.append("\n");
         }
         return sb.toString();
     }


}
