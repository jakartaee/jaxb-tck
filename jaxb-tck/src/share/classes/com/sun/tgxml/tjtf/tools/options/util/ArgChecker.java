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
 * ArgChecker - class for checking that element may be an option argument
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class ArgChecker extends Checker {

    String invalidPrefix = null;   
    String validPrefix = null;   

    /**
     * Creates ArgChecker instance. 
     * @param invalidPrefix  is set, elements starting with this 
     *                       prefix will be treated as non-acceptable
     * @param validPrefix    is set, elements not starting with this 
     *                       prefix will be treated as non-acceptable
     */
    public ArgChecker (String invalidPrefix, String validPrefix) {
         this.invalidPrefix = invalidPrefix;
         this.validPrefix = validPrefix;
    }


    /**
     * Checks whether argument is acceptable or not.<p>
     * if <code>invalidPrefix</code> is set, argument starting with this 
     *        prefix is non-acceptable<p>
     * if <code>validPrefix</code> is set argument not starting with this 
     *        prefix is non-acceptable<p>
     * Subclasses may override this method to implement own algorithm of 
     * checking.
     *
     * @return <code>true</code> if argument is acceptable
     *          <code>false</code> otherwise
     */
    public boolean checkElement(String elem) {
        if (invalidPrefix != null && elem.startsWith(invalidPrefix))
             return false;
        if (validPrefix != null && !elem.startsWith(validPrefix))
             return false;

        return true;
    }

}
