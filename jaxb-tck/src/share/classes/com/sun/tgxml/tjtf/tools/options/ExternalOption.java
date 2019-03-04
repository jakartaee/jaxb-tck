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
import java.util.StringTokenizer;
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;


/**
 *  This is an implementation of prefix option. All options
 *  with specified prefixes will be passed to the extenal OptionHandler
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class ExternalOption extends PrefixOption {

    OptionHandler optionHandler = null;


    /**
     * Creates with one specified switch prefix, usageInfo and external
     * OptionHandler
     *
     * @throws IllegalArgumentException if either usageInfo is null 
     *         or sw is null, or handler is null
     */
    public ExternalOption(String swPrefix, String usageInfo, 
            OptionHandler oHandler) {
        this(new String[]{swPrefix}, usageInfo, oHandler);
    }


    /**
     * Creates option with a set of specified switch prefixes, usageInfo
     * and external OptionHandler
     *
     * @throws IllegalArgumentException if either usageInfo is null 
     *          or array of switches is empty or contains null
     *          or handler is null
     */    
    public ExternalOption(String[] swPrefixes, String usageInfo,
            OptionHandler oHandler) {
        super(swPrefixes, usageInfo);
        
        this.optionHandler = oHandler;
    }

    /**
     * Calls external handler to parse options
     */
    protected void processSwitches() throws ParseArgumentException {
        int size = found.size();
        ArrayList extArgs = new ArrayList(size*2);
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(removePrefix((String)found.get(i)));
            while (st.hasMoreElements()) 
                extArgs.add(st.nextToken());
        }

        if (optionHandler != null) {
            extArgs = optionHandler.parseArguments(extArgs);
            if (extArgs != null && extArgs.size() > 0) {
                StringBuffer sb = new StringBuffer((String)extArgs.get(0));
                for (int i = 1; i < extArgs.size(); i++) {
                    sb.append(", ");
                    sb.append((String)extArgs.get(i));
                }
                throw new ParseArgumentException(optionHandler 
                    + " cannot process arguments: " + sb.toString());
            }
        }        
    }


}
