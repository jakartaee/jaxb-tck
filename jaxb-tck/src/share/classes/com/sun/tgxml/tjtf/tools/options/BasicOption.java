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
import java.util.Vector;
import java.util.Enumeration;
import com.sun.tgxml.tjtf.tools.options.util.ArgReader;
import com.sun.tgxml.tjtf.tools.options.util.OptionReader;
import com.sun.tgxml.tjtf.tools.options.util.ArgChecker;
import com.sun.tgxml.tjtf.tools.options.util.SwitchChecker;
import com.sun.tgxml.tjtf.tools.options.util.OptionSwitchChecker;


/**
 *  This is the base class for most commonly used options. Each option 
 *  is responsible for extracing itself from a command line.
 *  To implement parsing subclasses need provide implementaistions only for 
 *  the following methods:<p>
 *  <tt>getArgReader()</tt><p>
 *  <tt>calculateValue(ArrayList parsedOption)</tt><p>
 *  Subclasses should also provide get methods for retrieving value of 
 *  the option
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public abstract class BasicOption extends StandardOption {

    /**
     * set of option switches
     */
    protected Vector keys = null;

    private String patt_missedOptionParameter = null;
    
    /**
     * Creates in-obligatory option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public BasicOption(String sw, String usageInfo) {
        this(new String[]{sw}, usageInfo, false);
    }

    /**
     * Creates option with one specified switch and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public BasicOption(String sw, String usageInfo, boolean isObligatory) {
        this(new String[]{sw}, usageInfo, isObligatory);
    }

    /**
     * Creates option with a set of specified switches and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public BasicOption(String[] switches, String usageInfo, 
            boolean isObligatory) {
        if (usageInfo == null) 
             throw new IllegalArgumentException("usageInfo should not be null");
        if (switches == null || switches.length == 0) 
             throw new IllegalArgumentException("switches should not be empty");

        keys = new Vector(switches.length);
        for (int i = 0; i < switches.length; i++) 
            addSwitch(switches[i]);

        setUsageInfo(usageInfo);
        setObligatory(isObligatory);        
    }

    /**
     * Add option switch to the set of specified switches
     *
     * @throws IllegalArgumentException if <code>sw</code> is null
     */ 
    public void addSwitch(String sw) {
        if (sw == null)
            throw new IllegalArgumentException("switch cannot be null");
        keys.add(sw);
    }



    /**
     * Returns ArgReader used to extact option arguments from the list
     * If option has not arguments (flag) returns null
     */ 
    public abstract ArgReader getArgReader();

    /**
     * Returns ArgChecker checking that an argument does not start with "-"
     */ 
    public ArgChecker getArgChecker() {
        return new ArgChecker("-", null);
    }

    /**
     * Returns OptionReader used to extact option with arguments from the list
     */ 
    public OptionReader getOptionReader() {
         ArgReader ar = getArgReader();
         if (ar != null) {
             ar.setOptionName(this.toString());
             if (patt_missedOptionParameter != null) {
                 ar.setOptionParameterMissedPattern(patt_missedOptionParameter);
             }
         }
         return new OptionReader(getSwitchChecker(), ar);
    }

    /**
     * Returns SwitchChecker checking that an element is one of specified
     * option switches
     */ 
    public SwitchChecker getSwitchChecker() {
        return new OptionSwitchChecker(keys);
    }


    /** 
     * Returns name of the option (the first specified option switch)
     */
    public String toString() {
         return (String)keys.firstElement();
    }

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if option parameter
     * is missed.
     * <p>Example of pattern : 
     * <pre>
     *     "{0}" option expects {1} parameter(s), passed only {2}
     * </pre>
     */
    public void setOptionParameterMissedPattern(String patt) {
        patt_missedOptionParameter = patt;
    }

    /**
     * Returns error message pattern will be printed if option parameter
     * is missed.
     */
    public String getOptionParameterMissedPattern() {
        return patt_missedOptionParameter;
    }

}
