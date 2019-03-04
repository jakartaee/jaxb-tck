/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tjtf.tools;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Enumeration;
import java.util.Collections;
import java.util.HashSet;


/**
 * Build properties reader
 *
 * Final class that allows to read tck build properties. <br>
 *
 * Build properties may be specified in two ways:<br>
 *   (1) in a property file <br>
 *   (2) using a command line, through JVM system property (-DXXX=YYY) <br>
 * The (2) supersedes the (1). 
 *
 * The property file has a format specified in java.util.Properties class <br>
 * 
 * The name of the properties file is specified via system property with 
 * key "tck.build.propfile" (stored in the constant
 * <code>BuildProperties.TCK_PROPERTIES_FILE_NAME)</code>.
 * 
 * Note: to make properties specified in file readable method loadProperties()
 * should be invoked before. This method may called only once. The next 
 * trying to invoke it will be ignored.
 * 
 * If no file is specified or loadProperties() method has not been invoked
 * then all properties are treated as not specified except
 * properties explicitly specified  via system properties<br>
 * 
 */


public final class BuildProperties {

    /**
     * The name of system property specifying the build properties file name
     */
    public static final String TCK_PROPERTIES_FILE_NAME = "tck.build.propfile";

    private static Properties props = null;

    /**
     * Loads build properties from the configuration file specified by
     * <code>tck.build.propfile</code> system property.<BR>
     *
     * If no file is specified all properties are treated as not 
     * specified except properties explicitly specified
     * via system properties<br>
     *
     * This method can be called at most once in a given Java Virtual
     * Machine. All next calls will be ignored.
     *
     * @see   BuildProperties#TCK_PROPERTIES_FILE_NAME
     * @exception IOException if an I/O exception occurs
     */
    public static void loadProperties() throws IOException {
        loadProperties(System.getProperty(TCK_PROPERTIES_FILE_NAME));
    }


    /**
     * Loads build properties from the configuration file<BR>
     *
     * If no file is specified all properties are treated as not 
     * specified except properties explicitly specified
     * via system properties<br>
     *
     * This method can be called at most once in a given Java Virtual
     * Machine. All next calls will be ignored.
     *
     * @param   propertyFileName the name of build properties file
     * @exception IOException if an I/O exception occurs
     */
    static void loadProperties(String propertyFileName) 
            throws IOException {
        if (props != null) {
            // ignore: properties are already loaded
            return;
        }

        props = new Properties();        
        if (propertyFileName != null && propertyFileName.trim().length() > 0) {
            FileInputStream is = new FileInputStream(propertyFileName);
            props.load(is);
        }
    }
 
    /**
     * Returns an enumeration of all the build properties keys
     */
    public static Enumeration propertyNames() {
        return propertyNames("");
    }

    /**
     * Returns an enumeration of all the build properties keys starting 
     * with specified prefix
     */
    public static Enumeration propertyNames(String prefix) {
        HashSet set = new HashSet();
        Enumeration sysKeys = System.getProperties().propertyNames();
        while (sysKeys.hasMoreElements()) {
            String key = (String)sysKeys.nextElement();
            if (key != null && key.startsWith(prefix))
                set.add(key);
        }

        if (props != null) {
            Enumeration fileKeys = props.propertyNames();
            while (fileKeys.hasMoreElements()) {
                String key = (String)fileKeys.nextElement();
                if (key != null && key.startsWith(prefix))
                    set.add(key);
            }
        }
       
        // converting Collection -> Enumeration
        return Collections.enumeration(set);

    }

    /**
     * Searches for the property with the specified key.
     * The method returns <code>null</code> if the property is not found.
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static String getString(String key) {
        return getPrefixString(null, key, null);
    }


    /**
     * Searches for the property with the specified key.
     * The method returns the default value argument if the property 
     * is not found.
     *
     * @param   key            the hashtable key.
     * @param   defaultValue   a default value.
     *
     * @return  the value in this property list with the specified key value.
     */
    public static String getString(String key, String defaultValue) {
        return getPrefixString(null, key, defaultValue);
    }


    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns <code>null</code> if the property is not found.
     *
     * @param   prefix         the prefix of property.
     * @param   key            the hashtable key.
     *
     * @return  the value in this property list with the specified key value.
     */
    public static String getPrefixString(String prefix, String key) {
        return getPrefixString(prefix, key, null);
    }


    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns the default value argument if the property 
     * is not found.
     *
     * @param   prefix         the prefix of property.
     * @param   key            the hashtable key.
     * @param   defaultValue   a default value.
     *
     * @return  the value in this property list with the specified key value.
     */
    public static String getPrefixString(String prefix, String key, 
            String defaultValue) {

        if  (prefix != null && prefix.trim().length() > 0) 
            prefix = prefix.trim();
        else
            prefix = null;


        String val = null;
        if (prefix != null) 
            val = get(prefix + "." + key);            
        
        if (val != null)
            return val;

        val = get(key);
        if (val != null)
            return val;

        return defaultValue;
    }


    /**
     * Searches for the property with the specified key.
     * The method returns integer value of property.
     * If the property is not found or cannot be converted into int
     * this method returns <code>0</code>
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static int getInt(String key) {
         return getPrefixInt(null, key, 0);
    }


    /**
     * Searches for the property with the specified key.
     * The method returns integer value of property.
     * If the property is not found or cannot be converted into int
     * this method returns the default value argument
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static int getInt(String key, int defaultValue) {
        return getPrefixInt(null, key, defaultValue);
    }


    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns integer value of property.
     * If the property is not found or cannot be converted into int
     * this method returns <code>0</code>
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */

    public static int getPrefixInt(String prefix, String key) {
         return getPrefixInt(prefix, key, 0);
    }

    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns integer value of property.
     * If the property is not found or cannot be converted into int
     * this method returns the default value argument
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */

    public static int getPrefixInt(String prefix, String key, int defaultValue)
    {
        String val = getPrefixString(prefix, key, null);
        try {
            return Integer.parseInt(val);
        } catch (NullPointerException e) {
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * Searches for the property with the specified key.
     * The method returns float value of property.
     * If the property is not found or cannot be converted into float
     * this method returns <code>0.0</code>
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static float getFloat(String key) {
         return getPrefixFloat(null, key, 0);
    }


    /**
     * Searches for the property with the specified key.
     * The method returns float value of property.
     * If the property is not found or cannot be converted into float
     * this method returns the default value argument
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */
    public static float getFloat(String key, float defaultValue) {
        return getPrefixFloat(null, key, defaultValue);
    }


    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns float value of property.
     * If the property is not found or cannot be converted into float
     * this method returns <code>0.0</code>
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */

    public static float getPrefixFloat(String prefix, String key) {
         return getPrefixFloat(prefix, key, 0);
    }

    /**
     * Searches for the property prefix + "." + key.
     * If not found searches for the property with the specified key.
     * The method returns float value of property.
     * If the property is not found or cannot be converted into float
     * this method returns the default value argument
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     */

    public static float getPrefixFloat(String prefix, String key, 
            float defaultValue)
    {
        String val = getPrefixString(prefix, key, null);
        try {
            return Float.parseFloat(val);
        } catch (NullPointerException e) {
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private static String get(String key) {
        if (key == null)
            return null;
        String value = System.getProperty(key);
        if (props == null)
            return  value;
        return  value == null ? props.getProperty(key) : value;
    }

}


