/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.redundancy;

import java.lang.reflect.Constructor;

import com.sun.tgxml.tjtf.tools.BuildProperties;

/**
 * The class defines static helper methods.
 */
public class Utils {
    
    private static final String SPLIT_REGEX = "[\n\t ]+";

    /**
     * creates and instantiates the instance. The build property
     * value associated with given key is split into words list list
     * using [\n\t&nbsp;]+ regular expression. The first element is parsed
     * as class name. The rest of the list is arguments list. If the
     * argument list is empty, the constructor without parameter is
     * invoked to instantiate the instance, otherwise the constructor
     * with single <code>String[]</code> parameter is used.
     * @param key given property name
     * @param allowsNull flags how the null value is processed. If it is true
     *        the null is returned in this case, otherwise the
     *        <code>IllegalArgumentException</code> is thrown.
     */
    public static Object createAndInitObject(String key, boolean allowsNull) {
        String value = BuildProperties.getString(key);
        if (value == null) {
            if (allowsNull) {
                return null;
            } else {
                throw new IllegalArgumentException("Property " + key + " is not defined");
            }
        }
        try {
            String[] items = value.split(SPLIT_REGEX);
            Class cl = Class.forName(items[0]);
            if (items.length > 1) {
                String[] args = new String[items.length - 1];
                System.arraycopy(items, 1, args, 0, args.length);
                Constructor ctor = cl.getDeclaredConstructor(new Class[] {args.getClass()});
                return ctor.newInstance(new Object[] {args});
            } else {
                return cl.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can not instantinate or initialize:" + value);
        }
    }
}
