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

package com.sun.jmpp.share;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This classloader makes it possible to reload a class
 * from disk even if it has already been loaded by the JVM,
 * and to load classes from beyond the classpath.
 * @author Konstantin S. Bobrovsky
 */
public class DirClassLoader extends ClassLoader {
    /**
     * A list of directories where to search for class files.
     */
    protected String[] searchDirs;

    /**
     * If non-null, only classes from these packages will be loaded
     * by this class loader - other classes will be loaded via
     * the system class loader.
     */
    protected String[] packages;

    /**
     * Buffer for a class file binary data.
     */
    protected byte[] buf = new byte[BUF_SIZE];

    /**
     * Buffer length.
     */
    protected static final int BUF_SIZE = 65536;

    /**
     * Constructs a new DirClassLoader.
     * @param searchDirs a list of directories where to search for class files
     * @param packages a list of packages to be loaded by this class loader
     * @see #searchDirs
     * @see #packages
     */
    public DirClassLoader(String[] searchDirs, String[] packages) {
        this.searchDirs = searchDirs;
        this.packages    = packages;
    }

    /**
     * Equivalent of DirClassLoader(new String[] { searchPath },
     *                              new String[] { pckg })
     * @see #DirClassLoader(String[], String[])
     */
    public DirClassLoader(String searchPath, String pckg) {
        this(new String[] { searchPath }, new String[] { pckg });
    }

    /**
     * Equivalent of DirClassLoader(new String[] { searchPath }, null)
     * @see #DirClassLoader(String[], String[])
     */
    public DirClassLoader(String searchPath) {
        this(new String[] { searchPath }, null);
    }

    /**
     * Tries to find a class file data for a class with the specified name
     * first in the system classpath path, then (if not found) in the search
     * directories. If the data is found, returns the result of
     * java.lang.ClassLoader.defineClass call for the data, otherwise throws
     * ClassNotFoundException. For more information see Java API docs.
     * @param name class name
     * @return a Class object for the class with given name
     * @throws ClassNotFoundException 
     */
    protected Class findClass(String name) throws ClassNotFoundException {
        String class_file_name = name.replace('.', File.separatorChar);
        class_file_name = class_file_name + ".class";
        int len = 0;
        try {
            InputStream in = getSystemResourceAsStream(class_file_name);
            if (in == null) {
                for (int i = 0;
                     searchDirs != null && i < searchDirs.length;
                     i++) {

                    try {
                        in = new FileInputStream(searchDirs[i] +
                                                 File.separator +
                                                 class_file_name);
                        break;
                    } catch (IOException e) {}
                }
            }
            if (in == null) {
                return null;
            }

            while (true) {
                int res = in.read();
                if (res == -1)
                    break;
                buf[len] = (byte)res;
                len++;
                if (len == buf.length){
                    byte[] buf_tmp = buf;
                    buf = new byte[BUF_SIZE + buf_tmp.length];
                    System.arraycopy(buf_tmp, 0, buf, 0, buf_tmp.length);
                }
            }
            in.close();
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(null, buf, 0, len);
    }

    /**
     * Loads a class.
     * @param name    class name 
     * @param resolve indicates whether the class should be resolved
     *                (via ClassLoader.resolveClass)
     * @return a Class object for the class
     * @throws ClassNotFoundException 
     */
    protected Class loadClass(String name, boolean resolve)
        throws ClassNotFoundException {
        Class c = null;
        boolean needs_reloading = needsReloading(name);
        if (needs_reloading) {
            c = findClass(name);
        }
        if (c == null) {
            if ((c = findLoadedClass(name)) != null) {
                return c;
            }
            try {
                c = findSystemClass(name);
            } catch (ClassNotFoundException e) {
                if (!needs_reloading){
                    c = findClass(name);
                }
            }
        }
        if (c == null) {
            throw new ClassNotFoundException(name);
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    /**
     * @param class_name a class name
     * @return true if the class should be loaded via this class loader
     *         (i.e. the class name matches the package filter), false
     *         otherwise
     * @see #packages
     */
    protected boolean needsReloading(String class_name) {
        if (class_name.startsWith("java."))
            return false;
        if (packages != null && packages.length == 0)
            return true;
        for (int i = 0; packages != null && i < packages.length; i++) {
            if (class_name.startsWith(packages[i]))
                return true;
        }
        return false;
    }
}
