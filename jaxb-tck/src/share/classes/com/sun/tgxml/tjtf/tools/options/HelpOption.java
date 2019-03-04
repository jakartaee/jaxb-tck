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


/**
 *  This is an implementation of help option<p>
 *  If one of help switches is present in the argument list
 *  HelpOptionException will be thrown from <tt>parse(ArrayList list)</tt>
 *  method
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class HelpOption extends FlagOption {

    /**
     * Creates in-obligatory option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public HelpOption(String sw, String usageInfo) {
        super(sw, usageInfo);
    }

    /**
     * Creates option with one specified switch and usageInfo 
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public HelpOption(String sw, String usageInfo, boolean isObligatory) {
        super(sw, usageInfo, isObligatory);
    }

    /**
     * Creates option with a set of specified switches and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public HelpOption(String[] switches, String usageInfo, 
            boolean isObligatory) {
        super(switches, usageInfo, isObligatory);
    }


    /**
     * Throws HelpOptionException
     */
    public void calculateValue(ArrayList values) throws ParseArgumentException {
        throw new HelpOptionException("");
    }

}
