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

import java.util.Vector;

/**
 * SwitchChecker - abstract class for checking that element is an option switch.
 * Subclasses should implement the algorithm of checking
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public abstract class SwitchChecker extends Checker {

    String[] keys = null;   

    /**
     * Creates SwitchChecker instance. 
     * @param keys  array of keys used to check elements.
     * @throws  IllegalArgumentException is keys is null or empty
     */
    public SwitchChecker (String[] keys) {
        if (keys == null || keys.length == 0)
             throw new IllegalArgumentException("keys cannot be empty");
        this.keys = keys;
    }

    /**
     * Creates SwitchChecker instance. 
     * @param keys  array of keys used to check elements.
     * @throws  IllegalArgumentException is keys is null or empty
     */
    public SwitchChecker (Vector keys) {
        this((String[])(keys.toArray(new String[0])));
    }
}
