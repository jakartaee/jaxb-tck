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

package com.sun.jmpp.lib;


/**
 * This interface represents a name value pair
 */
public interface TargetSpecElem {


   /**
    * Get the name associated with this pair.
    */
    public String getName();
     
   /**
    * Set the name associated with this pair.
    */
    public void setName(String name);


   /**
    * Get the value associated with this pair.
    */
    public String getValue();
     
   /**
    * Set the value associated with this pair.
    */
    public void setValue(String value);

}

