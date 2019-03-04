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

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Simple implementation of LibSelectionInfo interface
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class LibsInfo implements LibSelectionInfo {

     private ArrayList accepted = null;
     private ArrayList rejected = null;

     /**
      * Creates LibsInfo with empty lists of accepted
      * and rejected libraries.
      */
     public LibsInfo() {
         accepted = new ArrayList();
         rejected = new ArrayList();
     }

     /**
      * Adds libID to the list of accepted libraries
      */
     public void addAccepted(String libID) {
         if (libID != null && !accepted.contains(libID)) {
             accepted.add(libID);
         }
     }

     /**
      * Adds libID to the list of rejected libraries
      */
     public void addRejected(String libID) {
         if (libID != null && !rejected.contains(libID)) {
             rejected.add(libID);
         }
     }


     /**
      * Returns the list of accepted libIDs
      */
     public ArrayList accepted() {
         return accepted;
     }

     /**
      * Returns the list of rejected libIDs
      */
     public ArrayList rejected() {
         return rejected;
     }

     /**     
      * Returns string representation of LibsInfo
      */
     public String toString() {
         StringBuffer sb = new StringBuffer();
         for (Iterator it = accepted.iterator(); it.hasNext();) {
             sb.append("++ " + it.next() + "\n");
         }
         for (Iterator it = rejected.iterator(); it.hasNext();) {
             sb.append("-- " + it.next() + "\n");
         }
         sb.append("Total accepted/rejected: ");
         sb.append(accepted.size() + " / " + rejected.size() + "\n");
         return sb.toString();
     }

}
