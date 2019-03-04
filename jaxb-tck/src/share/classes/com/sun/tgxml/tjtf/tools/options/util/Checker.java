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

package com.sun.tgxml.tjtf.tools.options.util;

import java.util.ArrayList;

/**
 * Checker has methods for check whether element with
 * specified index in the array list is acceptable or not.
 * Subclasses should implement the algorithm of checking
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public abstract class Checker {


    /**
     * Takes the element from array with the specified index, 
     * converts it to string and checks it with checkElement method.
     * Returns false if index is out of bounds
     */
    public final boolean check(ArrayList arr, int index) {
        try {
             Object obj = arr.get(index);
             return checkElement((String)obj);
        } catch (IndexOutOfBoundsException e) {
             return false;
        }        
    }

    /**
     * Checks whether <code>elem</code> is acceptable or not.<p>
     * Subclasses should override this method to implement own algorith of 
     * checking.
     *
     * @param elem the string under check
     * @return <code>true</code> if argument is acceptable
     *          <code>false</code> otherwise
     */
    public abstract boolean checkElement(String elem);
}
