/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

//
// This is the ordered list class
//
// Author: Kevin A Smith & Rampalli Narasimhan
//  

package com.sun.jck.utils.jckfilecheck;

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;

public class OrderedList {

  private Vector list;    
  private int msgLevel = 1;
  private PrintStream err;

  //
  // The default constructor creates a node of 256 elements.
  // 
  public OrderedList () {
     this (256, System.err);
  }

  //
  // Or else one can create an ordered list by specifying the
  // size. If size is less than 1 then there is something wrong.
  //
  public OrderedList (int size) {
   
    if (size >= 1) {
     list = new Vector (size);
     this.err = System.err;
    }
    else {
     throw new IllegalArgumentException ("Ordered List must be atleast 1 unit size");
    }
  }

  public OrderedList (int size, PrintStream err) {
    if (size < 1) {
       throw new IllegalArgumentException ("Ordered List must be atleast 1 unit size");
    }
    list = new Vector (size);
    this.err = err;
  }

  // 
  // Some utilities to this class
  //
  public void setMsgLevel (int msgLevel) {
         this.msgLevel = msgLevel;
  }

  public int size () {
      return list.size();
  }

  public Enumeration getIterator() {
      return list.elements();
  }

  //
  // The most important function in this class is the addNode function.
  //

  public void addNode (Comparable obj) {

      int left;
      int right;
      int x = 0;  
      
      if (list.isEmpty()) {
              list.addElement(obj);
              return;
      }

      if (obj.compareTo ((Comparable) list.lastElement()) > 0) {
              list.addElement(obj);
              return;
      }
      
      if (msgLevel == 2) {
          err.println ("Searching for object: " + obj);
      }

      //
      // Start the searching process
      //
      left = 0;
      right = list.size();

      while (right >= 0) {
            x = (left + right)/ 2;
            if (msgLevel == 2) {
                err.println ("Left: " + left + "Right: " + right + "Index: " + x);
	    }
	    if (x < list.size()) {
	      if (obj.compareTo ((Comparable) list.elementAt (x)) < 0) {
                      right = x - 1;
	      }
	      else {
                      left = x + 1;
	      }
	    }
	    else {
              right = -1;
	    }
	    if (left > right) {
              x = left;
              right = -1;
	    }
      }

      try {
           list.insertElementAt (obj, x);
      }
      catch (OutOfMemoryError e) {
         err.println ("Error: Out of memory while adding object: " + obj);
      }
  }
}     



