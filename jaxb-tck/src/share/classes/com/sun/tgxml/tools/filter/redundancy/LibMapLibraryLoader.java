/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.xml.sax.SAXException;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.processors.parser.ParserFactory;
import com.sun.tgxml.tjtf.processors.parser.XMLParser;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser;

/**
 * The implementation of LibraryLoader, which loads information about external 
 * library files from a InputStream in the following format:
 * <ul>
 *   <li> each line contains description about all external libraries withe the same ID.
 *   <li> stream may have arbitrary order and duplicates.
 *   <li> Each line must be in following format:
 *    <p>
 *   &lt;library&nbsp;id&gt;&nbsp;&lt;repository&nbsp;location&gt;:&lt;relative&nbsp;library&nbsp;file&gt;[&nbsp;&lt;repository&nbsp;location&gt;:&lt;relative&nbsp;library&nbsp;file&gt;&nbsp;...]
 * </ul>
 * The external library files are parsed by 
 * <code>com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser</code>
 */
public class LibMapLibraryLoader implements LibraryLoader {
    
    private HashMap list = new HashMap();
    private MiddleWareXMLParser parser = new MiddleWareXMLParser();
    
    /*  
     * 32 is enough, because this cache is required only during processing 
     * of the library by TestItem selector. 
     * When the library is processed by TestItemSelector, the Library is cached
     * by TestItemSelector and there are no needs to store list of external 
     * libraries here.
     */
    private Map cache = Collections.synchronizedMap(new WeakHashMap());
/* commented to be compatible with jdk 1.3.0 new CasheMap(32); */
    /**
     * creates instance which loads library map from the resource with
     * the given name. The resource should be accessible via
     * <code>ClassLoader.getResourceAsStream()</code>.
     * @param name name of the resource.
     */
    public LibMapLibraryLoader(String name) {
        loadResource(openResource(name));
    }
    
    private InputStream openResource(String name) {
        ClassLoader loader = this.getClass().getClassLoader();
        loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
        InputStream inRes = loader.getResourceAsStream(name);
        if (inRes == null) {
            throw new RuntimeException("Can not load library map list from "
                                       + name);
        } else {
            return inRes;
        }
    }

    /**
     * creates instance which loads library map from the given InputStream.
     */
    public LibMapLibraryLoader(InputStream in) {
        loadResource(in);
    }
    
    /**
     * parses lib map file
     */
    private void loadResource(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                int pos = line.indexOf(' ');
                if ((pos > 0) && (pos < line.length() - 1))  {
                    list.put(line.substring(0, pos).trim(), 
                             line.substring(pos + 1).trim());
                }
            }
        } catch (IOException e) {
             e.printStackTrace();
             throw new IllegalArgumentException("Can not read from InputStream: " + in);
        }
    }
    
    private Library parseLibrary(String name) 
        throws TestFileException, SAXException, IOException {
        String sourceRoot = BuildProperties.getString("tck.source.dir");
        int pos = name.indexOf(':');
        if (pos >= 0) {
            sourceRoot = name.substring(0, pos);
        }
        return (Library) parser.parse(new File(name.replace(':', File.separatorChar)), sourceRoot);
    }
    
    /**
     * obtains list of the required external library files, parses these
     * files and returns ArrayList with Library instances.
     */
    public ArrayList loadLibraryVariants(String id) {
        ArrayList retVal = (ArrayList) cache.get(id);
        if (retVal != null) {
            return retVal;
        }
        String value = (String)list.get(id);
        if (value == null) {
            throw new ItemNotFoundException(id);
        }
        retVal = new ArrayList();
        try {
            int pos;
            while ((pos = value.indexOf(' ')) > 0) {
                retVal.add(parseLibrary(value.substring(0, pos)));
                value = (pos < value.length() - 1) ? value.substring(pos + 1) : "";
            }
            if ((value != null) && !value.equals("")) {
                retVal.add(parseLibrary(value));
            }
            return retVal;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemNotFoundException(id);
        }
    }
}
