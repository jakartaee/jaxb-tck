/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.testgen.processors.emitter;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Enumeration;
import com.sun.tgxml.tools.testgen.LibUtils;

public class EmitterFactory {
    private static Hashtable classTable;
    private static Hashtable instanceTable = new Hashtable();

    /**
     * Specify the key name for the default testgen generator
     */
    public static final String DEFAULT_KEY = "Default";

    /**
     * Returns generator correspnding to the given key or null
     */
    public static Generator getGenerator(IRObj obj) throws TestFileException{
        String key = null;
        if (obj instanceof TestGroup) {   
            key = "TestGroup." + detectKey((TestGroup)obj);
        } else if (obj instanceof Library) {
            key = "Library";
        } else {
            return null;
        }
        String className = getGeneratorClassName(key);
        Generator result = null;
        if (className != null) {
            if (instanceTable.containsKey(className)) {
                return (Generator)instanceTable.get(className);
            }
            
            result = createGenerator(className);
            if (result != null) instanceTable.put(className, result);
        }
        return result; 
    }
    

    /**
     * Returns generator by the classname
     */
    public static Generator createGenerator(String className) throws TestFileException {
        Generator result = null;
        if (className != null) {
            try {
                Class cl = Class.forName(className);
                result = (Generator)cl.newInstance();
            } catch (Exception e) {
                throw new TestFileException(LibResHandler.getResStr("testgen.error.creategenerator", e.getMessage()));
            }
        }
        return result;
    }
    
    /**
     * Initializes table of the known generators
     */
    protected static void initClassTable() {
        if (classTable == null) classTable = new Hashtable();
        String propPrefix = "testgen.emitter.";
        Enumeration keys = BuildProperties.propertyNames(propPrefix);

        while (keys.hasMoreElements()) {
             String key = (String)keys.nextElement();
             String className = BuildProperties.getString(key);
             if (className != null && className.trim().length() > 0) 
                 classTable.put(key.substring(propPrefix.length()), className);
        }
    }


    /**
     * Returns the class name of the generator.
     * Returns null if key is null or unknown
     * (the table of the known generators does not contains this key)
     *
     */
    protected static String getGeneratorClassName(String key) {
        if (key == null)
             return null;
        if (classTable == null) 
            initClassTable();
        return (String)classTable.get(key);
    }

    /**
     * Detects generator's key for the specified TestGroup.
     * Returns value of "testType" AttrElem, and DEFAULT_KEY
     * if "testType" not specified for the test.
     */
     protected static String detectKey(TestGroup test) {
         String key = LibUtils.getTestType(test);
         if (key != null) {
             return key;
         } else {
             return DEFAULT_KEY;
         }
     }


}
        
