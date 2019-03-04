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

package com.sun.tgxml.tjtf.processors.validator;
import java.util.TreeMap;
import java.util.Hashtable;
import java.util.Iterator;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.tests.*;

 
/** 
 * Mapping - Abstract definition of a Name-Value mapping. 
 * <p>
 * A Mapping is defined by a specific name, and a validate function
 * which validates potential values associated with a given mapping.
 * <p>  
 * A mapping could be defined over an enumerated set of values, or it
 * could even be a function which calculates to a certain value.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    Mapping 
 * ============================================================================================ 
 */ 
abstract public class Mapping  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 
    /** The name of this mapping. */
    protected String  m_name;

    /** The debug flag.  */
    protected boolean m_debug;

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    Mapping  methods 
    * -------------------------------------------------------------------------------------------- 
    */ 

 
   /** 
    *   Mapping constructor - 
    *       Initialize our internal fields. 
    */ 
    public Mapping(String name) {
	m_name = name;
	m_debug = false;
    }


  /**
    * Get the name of this mapping.
    * <p>
    * @return The Mapping name.
    */
    public String getName() {
	return m_name;
    }

  /**
    * Set the debug flag.
    * <p>
    * @param debug The value to set the debug flag to.
    */
    public void setDebug(boolean debug) {
	m_debug = debug;
    }

  /**
    * Validate a value for this mapping.
    * <p>
    * @param val The value string to be validated.
    * @return true If the value is valid.
    */
    abstract public boolean validate(String val);

}
