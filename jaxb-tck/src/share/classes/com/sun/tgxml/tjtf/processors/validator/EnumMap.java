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
 * EnumMap - Name-Value map for Extension tags in the IR. 
 * <p>
 * An EnumMap is a type of mapping that enumerates a 
 * set of var-names that a given map may contain.  Validation
 * for an EnumMap means that the enumeration contains a var-name entry
 * for a given value.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ExtensionMap 
 * ============================================================================================ 
 */ 
public class EnumMap extends Mapping {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 

    /** The enumeration */
    protected TreeMap m_hash;

    /** Flag that determines if the validation function should
        return true if a value is not present (i.e. don't care). */
    protected boolean m_validateIfMissing;


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   EnumMap constructor - 
    *       Initialize our internal fields. 
    */ 
    public EnumMap(String name) {
	super(name);
	m_hash = new TreeMap();
	
	// by default:
	// if the map is missing the requested variable - 
	//  just accept the var-value (i.e. ignore it).
	m_validateIfMissing = true;
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    ExtensionMap implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */    


  /**
    * Set the validateIfMissing flag.
    * <p>
    * @param validateIfMissing The value to set the flag to.
    */
   
    public void setValidateIfMissing(boolean validateIfMissing) {
	m_validateIfMissing =  validateIfMissing;
    }




  /**
    * Validate the var-val mapping.
    * <p>
    *  This is the method that does the lookup and validation.
    * <p>
    * @param val The value to be looked up in the enumeration-set.
    * @return true if the value validates (or fn doesn't care), false otherwise.
    */
   
    public boolean validate(String val) {
	if (m_hash.containsKey(val))
	    return true;

	// otherwise
	if (m_debug) {
	    if (m_validateIfMissing)
		System.out.println("     ****  Missing Var: (Var: " + val 
				   + ") Assuming mapping is valid (" + val + ") ****");
	    else
		System.out.println("     ****  Missing Var: (Var: " + val 
				   + ") Assuming mapping is invalid (" + val + ") ****");
	}

	return m_validateIfMissing;
    }

}
