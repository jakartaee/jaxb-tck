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
import java.util.Properties;
import java.util.Enumeration;
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;
import com.sun.tgxml.tjtf.tools.options.resources.ErrorMessages;


/**
 *  This is an implementation of prefix option like <code>-Pkey=value</code>
 *  <p> where: <code>-P</code> - prefix of the option
 *  Use <tt>getValue(String key)</tt> to retrieve value of key.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class PropertyOption extends PrefixOption {

    private String patt_propertyFormatError = 
            ErrorMessages.getPattern("option.ArgumentFormat.Property");

    /**
     * Creates with one specified switch prefix and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or sw is null
     */
    public PropertyOption(String swPrefix, String usageInfo) {
        super(swPrefix, usageInfo);
    }


    /**
     * Creates option with a set of specified switch prefixes and usageInfo
     *
     * @throws IllegalArgumentException if usageInfo is null, or array of
     * switches is empty or contains null.
     */    
    public PropertyOption(String[] swPrefixes, String usageInfo) {
        super(swPrefixes, usageInfo);
    }


    /**
     * Sets values for all found keys
     */
    protected void processSwitches() throws ParseArgumentException {
        String sw[] = (String[])found.toArray(new String[0]);
        for (int i = 0; i < sw.length; i++ ) {
            String keyValue = removePrefix(sw[i]);
            int ind = keyValue.indexOf('=');
            if (ind < 0) 
                throw new ArgumentFormatException(ErrorMessages.getMessage(
                     patt_propertyFormatError, sw));
            properties.put(keyValue.substring(0, ind), keyValue.substring(ind+1));
        }
        
    }

    /**
     * Returns value of the key, null if key did not specified.
     */
    public String getValue(String key) {
        return (String)properties.get(key);
    }


    /**
     * Sets value of the key
     */
    protected void setValue(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Returns properties.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Returns properties.
     */
    protected void setProperties(Properties prop) {
        properties = prop;
    }

    private Properties properties = new Properties();

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if option is not in
     * property option format.
     * <p>Example of pattern : 
     * <pre>
     *     Illegal property option format <prefix><key>=<value>: "{0}"
     * </pre>
     */
    public void setPropertyFormatErrorPattern(String patt) {
        patt_propertyFormatError = patt;
    }

    /**
     * Returns error message pattern will be printed if option is not in
     * property option format.
     */
    public String setPropertyFormatErrorPattern() {
        return patt_propertyFormatError;
    }


}
