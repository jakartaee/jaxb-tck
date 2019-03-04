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
import com.sun.tgxml.tjtf.tools.options.resources.ErrorMessages;


/**
 *  This is an implementation of option with one integer argument.<p>
 *  Use <tt>getIntValue()</tt> to retrieve integer value of parsed option and
 *  <tt>getStringValue()</tt> to retrieve string value.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class IntOption extends StringOption {

    private String patt_intFormatError = 
            ErrorMessages.getPattern("option.ArgumentFormat.Integer");

    /**
     * Creates in-obligatory option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public IntOption(String sw, String usageInfo) {
        super(sw, usageInfo);
    }

    /**
     * Creates option with one specified switch and usageInfo 
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public IntOption(String sw, String usageInfo, boolean isObligatory) {
        super(sw, usageInfo, isObligatory);
    }

    /**
     * Creates option with a set of specified switches and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public IntOption(String[] switches, String usageInfo, 
            boolean isObligatory) {
        super(switches, usageInfo, isObligatory);
    }


    /**
     * Returns ArgChecker that allows argument starts with "-"
     */ 
    public ArgChecker getArgChecker() {
        return new ArgChecker(null, null);
    }

    /**
     * Sets value of option to the the argument of the last found switch
     */
    public void calculateValue(ArrayList values) throws ParseArgumentException {
        super.calculateValue(values);
        if (strValue != null) {
            try {
                setIntValue(Integer.parseInt(strValue));
            } catch (NumberFormatException e) {
                throw new ArgumentFormatException(ErrorMessages.getMessage(
                     patt_intFormatError, this, strValue,e.getMessage()));
            }
        }
    }

    /**
     * Returns value of the parsed option, 0 if option did not occur.
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Sets int value of the option.
     */
    protected void setIntValue(int v) {
        intValue = v;
    }

    protected int intValue = 0;

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if option parameter cannot
     * be converted into int.
     * <p>Example of pattern : 
     * <pre>
     *     "{1}" is illegal parameter of "{0}" option : "{2}"
     * </pre>
     */
    public void setIntegerFormatErrorPattern(String patt) {
        patt_intFormatError = patt;
    }

    /**
     * Returns error message pattern will be printed if option parameter cannot
     * be converted into int.
     */
    public String getIntegerFormatErrorPattern() {
        return patt_intFormatError;
    }

}
