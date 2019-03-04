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
import java.util.Enumeration;
import com.sun.tgxml.tjtf.tools.options.util.ArgReader;
import com.sun.tgxml.tjtf.tools.options.util.ArgChecker;
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;


/**
 *  This is an implementation of option with one string argument.<p>
 *  Use <tt>getStringValue()</tt> to retrieve value of parsed option.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class StringOption extends BasicOption {

    /**
     * Creates in-obligatory option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public StringOption(String sw, String usageInfo) {
        super(sw, usageInfo);
    }

    /**
     * Creates option with one specified switch and usageInfo 
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public StringOption(String sw, String usageInfo, boolean isObligatory) {
        super(sw, usageInfo, isObligatory);
    }

    /**
     * Creates option with a set of specified switches and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public StringOption(String[] switches, String usageInfo, 
            boolean isObligatory) {
        super(switches, usageInfo, isObligatory);
    }


    /**
     * Returns ArgReader that expects one argument
     */ 
    public ArgReader getArgReader() {
        return new ArgReader(1, 1, getArgChecker());
    }

    /**
     * Sets value of option to the the argument of the last found switch
     */
    public void calculateValue(ArrayList values) throws ParseArgumentException {
        ParsedOption val = (ParsedOption)values.get(values.size()-1);
        setStringValue( (String)val.getArguments().get(0));
    }

    /**
     * Returns value of the parsed option, null if option did not occur.
     */
    public String getStringValue() {
        return strValue;
    }

    /**
     * Sets value of the option
     */
    protected void setStringValue(String str) {
        strValue = str;
    }

    protected String strValue = null;
}
