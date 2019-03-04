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

package com.sun.tgxml.tools.dependence;

import java.util.ArrayList;

/**
 * The <code>StringArray</code> class adds two methods <i>addString</i> and 
 * <i>getString</i> to the <code>ArrayList</code>.
 */
public class StringArray extends ArrayList {
    
    /**
     * Appends the specified string to the end of this list if the string
     * is new.
     * @param string 
     *           string to be appended to this list.
     * @return 
     *    <strong>true</strong>   if string is new and it is appended to this list.
     *    <strong>false</strong>  if string is found in the list.
     */
    public boolean addString(String string) {
        if (-1 == indexOf(string)) {
            add(string);
            return true;
        }
        return false;
    }

    /**
     * Returns the string at the specified position in this list.
     * @param index 
     *           index of string to return.
     * @return the string at the specified position in this list.
     * @throws IndexOutOfBoundsException 
     *         if index is out of range 
     *           <code>(index < 0 || index >= size())</code>.
     */
    public String getString(int index) {
        return (String)get(index);
    }
}
