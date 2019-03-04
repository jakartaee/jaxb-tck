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

package com.sun.tgxml.tools.elgen;

import java.io.IOException;
import java.util.Hashtable;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

public class ExcludeListToolFactory {

    public static final String EL_CHANGE_PROPNAME = "elChangedDynamically";

    /**
     * Hash that contains pairs: "jtx file name" <--> ExcludeList
     */
    protected static Hashtable jtxHash = new Hashtable();

    /** 
     * óreate a default ExcludeListCollector, that will put all exclude
     *  entries into the collectorFileName.
     */
    public static ExcludeListCollector createELCollector(String outFileName) 
            throws TestFileException {

        String propName = "ExcludeListCollector";
        Object obj = getInstance(propName);
        if (obj == null) {
            return new DefaultExcludeListCollector(outFileName);
        } else if (obj instanceof ExcludeListCollector) {
             ExcludeListCollector collector = (ExcludeListCollector)obj;
             collector.setExcludeListFileName(outFileName);
             return collector;
        } else {
             throw new TestFileException(propName + " should specify " +
                 "instance of: com.sun.tgxml.tools.elgen." + propName); 
        }
    }

    /** 
     * óreate a default ExcludeListMarker, that will get exclude entries 
     *  from excludeListFileName
     */
    public static ExcludeListMarker createELMarker(String excludeListFileName) 
            throws IOException, TestFileException {

        String propName = "ExcludeListMarker";
        Object obj = getInstance(propName);
        if (obj == null) {
            return new DefaultExcludeListMarker(excludeListFileName);
        } else if (obj instanceof ExcludeListMarker) {
             ExcludeListMarker marker = (ExcludeListMarker)obj;
             marker.init(excludeListFileName);
             return marker;
        } else {
             throw new TestFileException(propName + " should specify " +
                 "instance of: com.sun.tgxml.tools.elgen." + propName); 
        }


    }

    /**
     * Returns instance of class specified by "elgen." + propName
     * property or null if this property is not specified.
     * @throws TestFileException if property is specified
     * but instance cannot be created
     */
    protected static Object getInstance(String propName)
             throws TestFileException {
        String className = 
                BuildProperties.getPrefixString("elgen", propName);
        if (className == null) {
            return null;
        }

        try {
             Class cl = Class.forName(className);
             return cl.newInstance();
        } catch (ClassNotFoundException e) {
             throw new TestFileException("Cannot find: " + propName 
                 + " class: " + e);
        } catch (Exception e) {
             throw new TestFileException("Cannot create instance of: " 
                 + propName + " class: " + e);
        }
         
    }

    /**
     * Returns true if contents of .jtx files may be changed during 
     * the build time, false otherwise.
     * This method should be invoked from getExcludeList() method,
     * if .jtx files may not be changed during the build time parsed
     * ExcludeList will be stored in the hash.
     * This method looks up for 'elgen.elChangedDynamically' build 
     * property. If the property is set to "false" or unset then 
     * 'false' is returned.
     */
    public static boolean elChangedDynamically() {
        String s = BuildProperties.getPrefixString("elgen", EL_CHANGE_PROPNAME);
        if (s == null || s.equalsIgnoreCase("false")) {
            return false;
        } else {
            return true;
        }
    }

    /** 
     * Returns an ExcludeList which gets entries from the specified jtx file.
     * This method attempts to resolve multiple requests with the
     * same filename.
     * If contents of .jtx files may be changed during the build time
     * or in case of the first request for specified jxt file new 
     * ExcludeList instance is created. In case of posterior request
     * for specified jxt file previosly created instance is returned.
     */
    public static synchronized ExcludeList getExcludeList(String jtxFileName)
            throws IOException {
        ExcludeList el = (ExcludeList)(jtxHash.get(jtxFileName));
        if (el == null) {
            el = new ExcludeList(jtxFileName);
            if (!elChangedDynamically()) {
                jtxHash.put(jtxFileName, el);
            }
        }
        return el;
    }

} 

