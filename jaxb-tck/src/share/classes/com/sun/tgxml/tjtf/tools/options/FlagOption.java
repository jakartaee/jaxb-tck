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

package com.sun.tgxml.tjtf.tools.options;

import java.util.ArrayList;
import com.sun.tgxml.tjtf.tools.options.util.ArgReader;
import com.sun.tgxml.tjtf.tools.options.util.ArgChecker;
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;


/**
 *  This is an implementation of option without arguments.<p>
 *  Use <tt>isSet()</tt> to know whether options is set or not.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class FlagOption extends BasicOption {

    /**
     * Creates in-obligatory option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public FlagOption(String sw, String usageInfo) {
        super(sw, usageInfo);
    }

    /**
     * Creates option with one specified switch and usageInfo 
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public FlagOption(String sw, String usageInfo, boolean isObligatory) {
        super(sw, usageInfo, isObligatory);
    }

    /**
     * Creates option with a set of specified switches and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public FlagOption(String[] switches, String usageInfo, 
            boolean isObligatory) {
        super(switches, usageInfo, isObligatory);
    }


    /**
     * Returns null (flag option has no arguments)
     */ 
    public ArgReader getArgReader() {
        return null;
    }

    /**
     * Does nothing
     */
    public void calculateValue(ArrayList values) throws ParseArgumentException {
        return;
    }

}
