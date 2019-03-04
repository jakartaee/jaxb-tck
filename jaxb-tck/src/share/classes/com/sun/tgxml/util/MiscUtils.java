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

package com.sun.tgxml.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *  Miscellaneous utils
 */
public class MiscUtils {
    /** 
     *   Tries to create directory with specified path
     *
     */
    public static boolean mkdirs(String dir) {
	return mkdirs(new File(dir));
    }

    /** 
     *   Tries to create directory with path specified by File object
     *
     */
    public static boolean mkdirs(File dir) {
	if (dir.exists())
	    return dir.isDirectory();

	File f = dir.isAbsolute()? dir
				 : dir.getAbsoluteFile();

	File p = f.getParentFile();
	if (!mkdirs(p))
	    return false;

	return f.mkdir() || f.isDirectory() ;
    }

    /**
     * Returns OutputStream to write to the specified file.
     * This method creates parent directory for the file if needed.
     * @param fileName name of file to write to
     */
    public static OutputStream createOutputStream(String fileName) 
            throws IOException {

        File file = new File(fileName);
        File dir = file.getParentFile();
        if (dir != null && !mkdirs(dir)){
            throw new IOException("Cannot create dir: " + dir);
        }

        return new FileOutputStream(file);
    }

    /**
     * Returns lines of passed text file as ArrayList of String
     * @param fileName name of file to be parsed
     * @param unique - if true skip duplicate lines
     * @return line list
     */
    public static ArrayList parseTextFile(String fileName, boolean unique)
             throws IOException {

        ArrayList list = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                if (!unique || !list.contains(str))
                    list.add(str);
            }
            line = reader.readLine();
        }
        reader.close();
        return list;
    }


    /**
     * Reads a text file and returns its contents as a String.
     * @param file file to be read
     * @return file contents
     */
    public static String readTextFile(File file) throws IOException {
        BufferedReader reader = null;
        IOException e = null;
        char[] buf = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            int len = (int)file.length();
            buf = new char[len];
            int off = 0;
            int n = 0;

            while (off < len && (n = reader.read(buf, off, len - off)) > 0) {
                off += n;
            }
        } catch (IOException ioe) {
            e = ioe;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        if (e != null) {
            throw e;
        }
        return new String(buf);
    }

    /**
     * Writes given text into a file.
     *
     * @param file the file to write to
     * @param text the text to be written
     */
    public static void writeTextFile(File file, String text) throws IOException {
        IOException e = null;
        FileWriter writer = null;

        try {
            mkdirs(file.getParentFile());
            writer = new FileWriter(file);
            writer.write(text, 0, text.length());
        } catch (IOException ioe) {
            e = ioe;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        if (e != null) {
            throw e;
        }
    }

    /**
     * Reads a file as an array of bytes.
     *
     * @param file the file
     * @return the bytes read
     * @throws IOException 
     *     in case of I/O errors when reading the file
     */
    public static byte[] readBinaryFile(File file) throws IOException {
        FileInputStream in = null;
        IOException e = null;
        byte[] res = null;

        try {
            in = new FileInputStream(file);
            int len = in.available();
            res = new byte[len];
            int off = 0;

            while (len > 0) {
                int cnt = in.read(res, off, len);
                off += cnt;
                len -= cnt;
            }
        } catch (IOException ioe) {
            e = ioe;
        } finally {
            if (in != null) {
                in.close();
            }
        }
        if (e != null) {
            throw e;
        }
        return res;
    }

    /**
     * Writes an array of bytes to a file.
     *
     * @param file the file
     * @param data the bytes
     * @throws IOException 
     *     in case of I/O errors when writing the file
     */
    public static void writeBinaryFile(File file, byte[] data) throws IOException {
        FileOutputStream out = null;
        IOException e = null;

        try {
            mkdirs(file.getParentFile());
            out = new FileOutputStream(file);
            out.write(data);
        } catch (IOException ioe) {
            e = ioe;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        if (e != null) {
            throw e;
        }
    }

    /**
     * Copies a file using an array of bytes as a buffer.
     *
     * @param src
     *     source file
     * @param dst
     *     defines the destination
     * @throws IOException 
     *     in case of I/O errors when reading/writing files
     */
    public static void copyFile(File src, File dst) throws IOException {
        writeBinaryFile(dst, readBinaryFile(src));
    }

    /**
     * Finds an object in a table using object's equals method.
     *
     * @param arr    the table of objects.
     * @param elem   search key
     * @param column column to be searched
     * @return
     *     -1 if nothing was found, otherwise - the row number where
     *     the object was found.
     */
    public static int find(Object[][] arr, Object elem, int column) {
        for (int i = 0; i < arr.length; i++) {
            if (elem.equals(arr[i][column])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds (linearly) an object in an array using object's equals method.
     *
     * @param arr  the array
     * @param elem search key
     * @return
     *     -1 or array index of the found object.
     */
    public static int find(Object[] arr, Object elem) {
        for (int i = 0; i < arr.length; i++) {
            if (elem.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Coverts an array of pairs &lt;X, Y&gt; into a map of
     * &lt;X =&gt; Y&gt; translations.
     *
     * @param arr the array of pairs
     * @return
     *     the map.
     */
    public static HashMap pairArrayToMap(Object[][] arr) {
        HashMap res = new HashMap(arr.length);

        for (int i = 0; i < arr.length; i++) {
            Object[] sub_arr = arr[i];

            if (sub_arr.length != 2) {
                throw new IndexOutOfBoundsException("" + sub_arr.length);
            }
            res.put(sub_arr[0], sub_arr[1]);
        }
        return res;
    }

    public static boolean isAsciiLetter(char ch) {
        return ('A' <= ch) && (ch <= 'Z') || ('a' <= ch) && (ch <= 'z');
    }

    public static boolean isAsciiDigit(char ch) {
        return ('0' <= ch) && (ch <= '9');
    }

    public static String getFileExtension(File f) {
        String path = f.getAbsolutePath();
        path = path.substring(path.lastIndexOf(File.separatorChar) + 1);
        int i = path.lastIndexOf('.', path.length());
        return i < 0 ? "" : path.substring(i + 1);
    }

    public static String basename(String path) {
        // String.lastIndexOf is obscure
        int ind = -1;

        while (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1);
        }
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == File.separatorChar) {
                ind = i;
            }
        }
        return ind < 0 ? path : path.substring(ind + 1);
    }

    public static String dirname(String path) {
        // String.lastIndexOf is obscure
        int ind = -1;

        while (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1);
        }
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == File.separatorChar) {
                ind = i;
            }
        }
        return ind < 0 ? "." : path.substring(0, ind);
    }

    public static boolean isTextFile(File f) {
        final String[] TEXT_EXTS = {
            "txt",
            "java",
            "idl",
            "c",
            "h",
            "html",
            "htm",
            "pl",
            "pm",
            "xml",
            "xsd",
            "asm",
            "jasm",
            "jcod",
            "jdis",
            "jdec",
            "sh",
            "ksh",
            "mk",
            "gmk",
            "bat"
        };
        String ext = getFileExtension(f);
        return (find(TEXT_EXTS, ext) >= 0);
    }
    
    public static String getParentPackage( String pkg ) {
        String res = "";
	if (pkg == null) {
	    return res;
	}
        int lastDotLoc = pkg.lastIndexOf(".");
        if ( lastDotLoc == -1 ) {
            return res;
        }
        
        return pkg.substring(0,lastDotLoc);
    }
    

}
