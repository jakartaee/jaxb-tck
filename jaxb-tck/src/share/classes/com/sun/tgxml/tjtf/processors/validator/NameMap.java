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
 * NameMap - a mapping of a set of names.
 * <p>
 * NameMap is a concrete implementation of an EnumMap.  It
 * provides an <em>addEntry()</em>.  You can create a NameMap
 * when you wish to have an enumerated map.
 * <p>
 * For example, <em>Keywords</em> is a NameMap.  Keywords 
 * are a set of named Attributes that a JCK UTD may contain.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    NameMap 
 * ============================================================================================ 
 */ 
public class NameMap extends EnumMap {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 

   /* 
    * ============================================================================================ 
    *    InnerClass 
    * ============================================================================================ 
    */ 


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   NameMap constructor - 
    *       Initialize our internal fields. 
    */ 
    public NameMap(String name) {
	super(name);
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    ExtensionMap implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 

 
   /** 
    *   Add a name entry into the enumerated-set.
    * <p>
    * @param name A name to add into the enumerated-set. 
    */ 
    public void addEntry(String name) {
	m_hash.put(name, name);
    }


}
