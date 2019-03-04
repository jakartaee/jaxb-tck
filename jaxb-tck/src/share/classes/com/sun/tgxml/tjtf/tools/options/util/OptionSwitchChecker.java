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
 * OptionSwitchChecker - class for checking that element is one of specified
 * option switches
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class OptionSwitchChecker extends SwitchChecker {


    /**
     * Creates OptionSwitchChecker instance. 
     * @param keys  array of option switches
     * @throws  IllegalArgumentException is keys is null or empty
     */
    public OptionSwitchChecker(String[] keys) {
        super(keys);
    }

    /**
     * Creates OptionSwitchChecker instance. 
     * @param keys  vector of option switches
     * @throws  IllegalArgumentException is keys is null or empty
     */
    public OptionSwitchChecker(Vector keys) {
        super(keys);
    }



    /**
     * Checks whether <code>elem</code> is one of specified option switches<p>
     *
     * @return <code>true</code> if elem is an option switch
     *          <code>false</code> otherwise
     */
    public boolean checkElement(String elem) {
        for (int i = 0; i < keys.length; i++) {
            if (elem.equals(keys[i]))
                return true;
        }
        return false;
    }

}
