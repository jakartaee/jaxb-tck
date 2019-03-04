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

import java.util.Vector;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * CompoundOption has a set of registred options.
 * <tt>parse()</tt> method consequently call <tt>parse()</tt> method
 * for all registred options
 * <tt>getUsageInfo()</tt> combined info for all registred options
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class CompoundOption implements Option {

    /**
     * array of registred options
     */   
    protected Vector options = null;

    /**
     * flag indicating whether option is set or non
     */   
    protected boolean isSet = false;

    /**
     * Creates CompoundOption with specified set of options
     */
    public CompoundOption(Option[] optionList) {
         if (optionList != null) {
             options = new Vector(optionList.length);
             for (int i = 0; i < optionList.length; i++) {
                 options.add(optionList[i]);
             }
         } else {
             options = new Vector();
         }
    }

    /** 
     * Adds new option to the list
     */
    public void addOption(Option op) {
        if (op != null && !options.contains(op))
            options.add(op);
    }


    /** 
     * Removes option from the list
     */
    public void removeOption(Option op) {
        if (op != null)
            options.remove(op);
    }


    /**
     * parses all options in the specified order
     * @return new list of unparsed arguments (without this option)
     * @throws ParseArgumentException if option is required but missed
     *        or option format is incorrect
     *
     */
    public ArrayList parse(ArrayList unparsed) throws ParseArgumentException {
        int oldSize = unparsed.size();
        Enumeration list = options.elements();
        while (list.hasMoreElements())
            unparsed = ((Option)list.nextElement()).parse(unparsed);
        if (unparsed.size() != oldSize) {
            isSet = true;
        }
        return unparsed;
    }

    /**
     * Returns true if option value has been set
     */
    public boolean isSet() {
        return isSet;
    }

    /**
     * Marks option as unset
     */
    public void reset() {
        isSet = false;
    }

    /**
     * Returns string with usage info for this option
     */
    public String getUsageInfo() {
        if (usageInfo != null) 
            return usageInfo;
        StringBuffer sb = new StringBuffer();
        Enumeration list = options.elements();
        while (list.hasMoreElements()) {
            sb.append(((Option)list.nextElement()).getUsageInfo());
            if (list.hasMoreElements()) 
                sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Sets new usage info.
     */
    public void setUsageInfo(String info) {
        usageInfo = info;
    }

    /**
     * if usageInfo == null getUsageInfo will calculate usageInfo.
     */
    private String usageInfo = null;


}
