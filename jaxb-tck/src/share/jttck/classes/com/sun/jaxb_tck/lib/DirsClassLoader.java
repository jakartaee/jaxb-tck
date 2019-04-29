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

package com.sun.jaxb_tck.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A class loader for loading classes from a path of directories.
 * 
 * @author   Evgueni M. Astigueevitch
 * @version  1.8
 */

public class DirsClassLoader extends ClassLoader {

//    private static DirsClassLoader loader;
    public static final String LOGGER_NAME = "jaxb_tck.lib.dirs_class_loader";
    final static Logger logger = Logger.getLogger(LOGGER_NAME);
    
    private File[] dirs;


    /**
     * Create a DirsClassLoader, specifying a path.
     */
    protected DirsClassLoader(File[] dirs, ClassLoader parent) {
        this(parent);
        this.dirs = dirs;
    }

    protected DirsClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static synchronized DirsClassLoader newInstance(File[] dirs, ClassLoader parent) {
    	DirsClassLoader loader = new DirsClassLoader(parent);
        logger.info(Thread.currentThread().toString());
        loader.dirs = dirs;
        return loader;
    }

    protected synchronized Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class cl = findLoadedClass(name);

        if (cl == null) {
            try {
                cl = findClass(name);
            } catch (ClassNotFoundException e) {
                ClassLoader parent = getParent();
                if (parent != null) {
					cl = parent.loadClass(name);
                } else {
                	ClassNotFoundException clnf = new ClassNotFoundException(name); 
                    throw clnf;
                }
            }
        }

        if (resolve)
            resolveClass(cl);

        return cl;
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        String fileName = name.replace('.', File.separatorChar) + ".class";
        for (int i = 0; i < dirs.length; i++) {
            File file = new File(dirs[i], fileName);
            if (file.exists()) {
                int packageIndex = name.lastIndexOf('.');
                if (packageIndex > 0) {
                    String pkgname = name.substring(0,name.lastIndexOf('.'));
                    Package pkg = getPackage(pkgname);
                    if (pkg == null) {
                        definePackage(pkgname, null, null, null, null, null, null, null);
                    }
                }
                byte[] data = loadClassData(file);
                return defineClass(name, data, 0, data.length);
            }
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] loadClassData(File classFile) 
            throws ClassNotFoundException {
        try {
            int size = (int)classFile.length();
            byte[] data = new byte[size];
            FileInputStream in = new FileInputStream(classFile);
            try {
                for (int total = 0; total < size; ) {
                    total += in.read(data, total, size - total);
                }
            } finally {
                in.close();
            }

            return data;
        } catch (IOException e) {
            throw new ClassNotFoundException("", e);
        }

    }

    public URL findResource(final String name) {
        for (int i = 0; i < dirs.length; i++) {
            File file = new File(dirs[i], name);
            if (file.exists()) {
                try {
                    return file.toURL();
                } catch (MalformedURLException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
