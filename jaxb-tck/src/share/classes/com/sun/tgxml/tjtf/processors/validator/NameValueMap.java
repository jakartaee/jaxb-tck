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
 * NameValueMap - Name-Value mapping. 
 * <p>
 * A NameValueMap is a concrete implementation of an EnumMap, that
 * associates a Mapping to a Name-entry in the enumeration.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    NameValueMap 
 * ============================================================================================ 
 */ 
public class NameValueMap extends EnumMap {
   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   NameMapImpl constructor - 
    *       Initialize our internal fields. 
    */ 
    public NameValueMap(String name) {
	super(name);
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    ExtensionMap implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 

 
   /** 
    *   Add a Mapping entry into this enumeration-set. 
    *  The name is already stored in the mapping.
    * <p>
    * @param map The mapping to be added.
    */ 
    public void addEntry(Mapping map) {
	m_hash.put(map.getName(), map);
    }

 
   /** 
    *  Validate a Name and value.  This form of
    *  validation looks for a Mapping in the enumeration
    *  with the given name.  If the mapping exists, the
    *  given value is validated by that mapping.
    * <p> 
    * @param name The potential name of a Mapping entry.
    * @param val  A potential value the Mapping can validate.
    * @return true If the value validates, or if the map doesn't care.
    */ 
    public boolean validate(String name, String val) {

	if (m_hash.containsKey(name)) {
	    Mapping map = (Mapping) m_hash.get(name);
	    return map.validate(val);
	} else {
	    return m_validateIfMissing;
	}
    }

 
   /** 
    *   Fetch a mapping of a given name in the enumeration set. 
    * <p>
    * @param name The name of a potential mapping.
    * @return A mapping, or null.
    */ 
    public Mapping getMapping(String name) {

	if (m_hash.containsKey(name))
	    return (Mapping) m_hash.get(name);
	else
	    return null;
    }


  /**
    * Set the debug flag (recursively for all maps in the enumeration-set).
    * <p>
    * @param debug The value the flag will be set to.
    */
    public void setDebug(boolean debug) {
	super.setDebug(debug);
	Iterator it = m_hash.values().iterator();
	while (it.hasNext()) {
	    Mapping map = (Mapping) it.next();
	    map.setDebug(debug);
	}
    }

}


