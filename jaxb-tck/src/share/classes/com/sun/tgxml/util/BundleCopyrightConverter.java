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

package com.sun.tgxml.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This utility is used to insert valid CRNs into a set of files.
 *
 * @author Konstantin S. Bobrovsky
 */
public class BundleCopyrightConverter {
    static {
        CopyrightManager.loadProperties();
        CopyrightManager.init();
    }

    private String srcDir;
    private String dstDir;

    public final static String OPT_SRC   = "-src";
    public final static String OPT_DST   = "-dst";
    public final static String OPT_LST   = "-lst";
    public final static String OPT_STDIN = "-";

    /**
     * Constructor.
     *
     * @param src_dir source directory. Non-absolute source file names
     *     are treated to be relative to this directory.
     * @param dst_dir 
     *     destination directory, where to put files with valid CRNs inserted.
     */
    public BundleCopyrightConverter(String src_dir, String dst_dir) {
        srcDir = src_dir;
        dstDir = dst_dir;
    }

    /**
     * Entry point. Synopsis:<br>
     * <blockquote>
     * <code>BundleCopyrightConverter <i>options</i><br>
     * </blockquote>
     * where all <i>options</i> below are obligatory:<br>
     * <ul>
     * <li><b>-src</b> <i>directory</i> source directory</li>
     * <li><b>-dst</b> <i>directory</i> destination directory</li>
     * <li><b>-lst</b> <i>file</i><br> a file with a list of filenames
     *     to be processed</li>
     * </ul>
     */
    public static void main(String[] args) {
        String src = null;
        String dst = null;
        String lst = null;
        boolean stdin = false;
        int i = 0;

        try {
            for (; i < args.length; i++) {
                if (OPT_SRC.equals(args[i])) {
                    i++;
                    if (src != null) {
                        error(OPT_SRC + " option duplicated");
                    }
                    src = args[i];
                } else if (OPT_DST.equals(args[i])) {
                    i++;
                    if (dst != null) {
                        error(OPT_DST + " option duplicated");
                    }
                    dst = args[i];
                } else if (OPT_LST.equals(args[i])) {
                    i++;
                    if (lst != null) {
                        error(OPT_LST + " option duplicated");
                    }
                    lst = args[i];
                } else if (OPT_STDIN.equals(args[i])) {
                    // read file list from stdin
                    if (stdin) {
                        error("'" + OPT_STDIN + "' option duplicated");
                    }
                    stdin = true;
                } else {
                    error("bad argument: " + args[i]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            error("invalid command line");
        }
        if (src == null) {
            error("source directory not specified");
        }
        if (dst == null) {
            error("destination directory not specified");
        }
        File src_dir = new File(src);

        if (!src_dir.exists() || !src_dir.isDirectory()) {
            error("directory " + src + " does not exist");
        }
        File dst_dir = new File(dst);

        if (!dst_dir.exists() || !dst_dir.isDirectory()) {
            error("directory " + dst + " does not exist");
        }
        if (lst == null && !stdin) {
            error("no files to convert");
        }
        File f = null;

        if (lst != null) {
            if (!((f = new File(lst)).exists())) {
                error("list file " + lst + " does not exist");
            }
        }
        try {
            String src_path = src_dir.getAbsolutePath();
            String dst_path = dst_dir.getAbsolutePath();
            ArrayList file_list = new ArrayList();

            if (f != null) {
                readList(new BufferedReader(new FileReader(f)), file_list);
            }
            if (stdin) {
                readList(new BufferedReader(new InputStreamReader(System.in)), file_list);
            }
            new BundleCopyrightConverter(src_path, dst_path).convertFiles(file_list);
        } catch (IOException e) {
            error(e.getClass().getName() + " occurred: " + e.getMessage());
        }
    }

    private static void readList(BufferedReader r, ArrayList list)
        throws IOException
    {
        String line;

        while ( (line = r.readLine()) != null ) {
            String s = line.trim();
            if (!s.equals("")) {
                list.add(s);
            }
        }
        r.close();
    }

    private void convertFiles(ArrayList l) {
        for (int i = 0; i < l.size(); i++) {
            convertFile((String)l.get(i));
        }
    }

    private void convertFile(String name) {
        String dst_path = null;
        String src_path = null;

        if (!name.startsWith(srcDir)) {
            if (name.startsWith(File.separator)) {
                warning("absolute name beyond src dir (skipped): " + name);
                return;
            }
            dst_path = name;
            src_path = srcDir + File.separator + name;
        } else {
            dst_path = name.substring(srcDir.length());
            src_path = name;
        }
        dst_path = dstDir + File.separator + dst_path;
        File dst = new File(dst_path);
        File src = new File(src_path);
    
        if (!src.exists()) {
            warning("file does not exist: " + name);
            return;
        }
        try {
            CopyrightManager.updateCopyright(src, dst, null);
        } catch (IOException e) {
            warning("could not update file " + dst_path + ": " + e);
        }
    }

    final static String class_name = BundleCopyrightConverter.class.getName();

    public static void error(String msg) {
        System.err.println("*** CRN ERROR (" + class_name + "):  " + msg);
        System.exit(1);
    }

    public static void warning(String msg) {
        System.out.println("*** CRN WARNING (" + class_name + "): " + msg);
    }
}
