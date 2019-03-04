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
import java.util.Iterator;
import java.util.Enumeration;
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;
import com.sun.tgxml.tjtf.tools.options.util.OptionReader;
import com.sun.tgxml.tjtf.tools.options.util.SwitchChecker;
import com.sun.tgxml.tjtf.tools.options.util.PrefixOptionChecker;


/**
 *  This is the base class for prefix options. Each option 
 *  is responsible for extracing itself from a command line.
 *  To implement parsing subclasses need provide implementaistions only for 
 *  the following method:<p>
 *  <tt>processSwitches()</tt><p>
 *  Subclasses should also provide get methods for retrieving value of 
 *  the option
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class PrefixOption extends StandardOption {

    /**
     * set of option switch prefixes
     */
    protected Vector keys = null;

    /**
     * set of found switches
     */
    protected ArrayList found = null;

    
    /**
     * Creates option with one specified switch prefix and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public PrefixOption(String swPrefix, String usageInfo) {
        this(new String[]{swPrefix}, usageInfo);
    }

    /**
     * Creates option with a set of specified switch prefixes and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switch prefixes is empty or contains null.
     */    
    public PrefixOption(String[] swPrfixes, String usageInfo) {
        if (usageInfo == null) 
             throw new IllegalArgumentException("usageInfo should not be null");
        if (swPrfixes == null || swPrfixes.length == 0) 
             throw new IllegalArgumentException("swPrfixes should not be empty");

        keys = new Vector(swPrfixes.length);
        for (int i = 0; i < swPrfixes.length; i++) 
            addSwitchPrefix(swPrfixes[i]);

        setUsageInfo(usageInfo);
        setObligatory(false);
    }

    /**
     * Add option switch prefix to the set of specified switch prefixes
     *
     * @throws IllegalArgumentException if <code>sw</code> is null
     */ 
    public void addSwitchPrefix(String sw) {
        if (sw == null)
            throw new IllegalArgumentException("switch prefix cannot be null");
        keys.add(sw);
    }


    /**
     * Returns OptionReader used to extact option with arguments from the list
     */ 
    public OptionReader getOptionReader() {
         return new OptionReader(getSwitchChecker(), null);
    }

    /**
     * Returns SwitchChecker checking that an element starts with one 
     * of specified option switch prefixes
     */ 
    public SwitchChecker getSwitchChecker() {
        return new PrefixOptionChecker(keys);
    }

    /**
     * Calculates values for all found keys
     */
    public final void calculateValue(ArrayList values) 
            throws ParseArgumentException {
        found = new ArrayList(values.size());
        Iterator list = values.iterator();
        while (list.hasNext())
            found.add(((ParsedOption)list.next()).getSwitch());
        processSwitches();
        
    }

    /**
     * Returns found switches
     */
    public ArrayList foundSwitches() {
        return found;
    }

    protected void processSwitches() throws ParseArgumentException {
    }

    /** 
     * remove option prefix from the string
     * <pre> Example: 
     *     if option has several prefixes: "-P", "-Parser"
     *     the longer of possible prefixes will be removed:
     * removePrefix("-PX=1") will return "X=1"
     * removePrefix("-ParsX=1") will return "arsX=1"
     * removePrefix("-ParserX=1") will return "X=1"
     * </pre>
     */
    protected String removePrefix(String sw) {
        String prefix = "";
        Enumeration list = keys.elements();
        while (list.hasMoreElements()) {
            String p = (String)list.nextElement();
            if (sw.startsWith(p) && p.length() > prefix.length()) 
                prefix = p;
        }
        return sw.substring(prefix.length());
    }


    /** 
     * Returns name of the option (the first specified option switch prefix)
     */
    public String toString() {
         return (String)keys.firstElement();
    }
}
