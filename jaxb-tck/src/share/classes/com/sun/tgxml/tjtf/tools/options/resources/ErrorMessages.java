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

package com.sun.tgxml.tjtf.tools.options.resources; 
 
import java.util.ResourceBundle; 
import java.util.MissingResourceException;
import java.text.MessageFormat;

  
/** 
 * ErrorMessages - functionality for access to internationalized 
 * error messages
 * 
 * 
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 */ 
 
 
public class  ErrorMessages { 

    private static String bundleName =
         "com.sun.tgxml.tjtf.tools.options.resources.ErrorsBundle";

    private static ResourceBundle bundle = loadBundle();

    private static ResourceBundle loadBundle() {
        try {
            return ResourceBundle.getBundle(bundleName);
        } catch (MissingResourceException e) {
            System.err.println("Failed to load resource bundle: " + bundleName);
            e.printStackTrace(System.err);
            System.exit(1);
        }
        return null;
    }


    /**
     * Returns pattern for error message by the key
     * @return found pattern, or an empty string if not found
     */
    public static String getPattern(String key) {
        return getPattern(key, "");
    }

    /**
     * Returns pattern for error message by the key
     * @return found pattern, or passed defautl value if not found
     */
    public static String getPattern(String key, String def) {
        if (bundle == null)
            return def;
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return def;
        }
    }

    /**
     * Returns formatted message
     */
    public static String getMessage(String pattern, Object[] args) {
        return MessageFormat.format(pattern, args);
    }



    /**
     * Returns formatted message with no arguments
     */
    public static String getMessage(String pattern) {
        return getMessage(pattern, new Object[]{});
    }

    /**
     * Returns formatted message with one argument
     */
    public static String getMessage(String pattern, Object arg1) {
        return getMessage(pattern, new Object[]{arg1});
    }

    /**
     * Returns formatted message with one argument
     */
    public static String getMessage(String pattern, int arg1) {
        return getMessage(pattern, new Object[]{new Integer(arg1)});
    }

    /**
     * Returns formatted message with two arguments
     */
    public static String getMessage(String pattern, Object arg1, Object arg2) {
        return getMessage(pattern, new Object[]{arg1, arg2});
    }

    /**
     * Returns formatted message with two arguments
     */
    public static String getMessage(String pattern, Object arg1, int arg2) {
        return getMessage(pattern, new Object[]{arg1, new Integer(arg2)});
    }

    /**
     * Returns formatted message with two arguments
     */
    public static String getMessage(String pattern, int arg1, int arg2) {
        return getMessage(pattern, 
            new Object[]{new Integer(arg1), new Integer(arg2)});
    }

    /**
     * Returns formatted message with three arguments
     */
    public static String getMessage(String pattern, 
            Object arg1, Object arg2, Object arg3) {
        return getMessage(pattern, new Object[]{arg1, arg2, arg3});
    }

    /**
     * Returns formatted message with three arguments
     */
    public static String getMessage(String pattern, 
            Object arg1, int arg2, int arg3) {
        return getMessage(pattern,
                new Object[]{arg1, new Integer(arg2), new Integer(arg3)});
    }
}

