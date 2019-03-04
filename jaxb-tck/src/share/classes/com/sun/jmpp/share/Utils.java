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
import java.util.Vector;

/**
 * This class contains commonly-used methods. 
 * @author Konstantin S. Bobrovsky
 */
public class Utils {
    public final static String UPPER_DIR = "..";

    /**
     * Constructs 'relative' path for given two paths. For example,
     * for "/aaa/bbb/ccc/ddd" and "/aaa/xxx/yyy" relative path would be
     * "../../../xxx/yyy".
     * @param ref_path reference path
     * @param tst_path path to construct relative form for
     * @return constructed relative path
     */
    public static String getRelativePath(String ref_path, String tst_path) {
        int last_sep_ind = -1;
        int ref_len = ref_path.length();
        int tst_len = tst_path.length();
        int i = 0;

        if (ref_path.charAt(ref_len - 1) == File.separatorChar) {
            ref_len--;
            ref_path = ref_path.substring(0, ref_len);
        }

        for (; i < ref_len && i < tst_len; i++) {
            char ref_ch = ref_path.charAt(i);
            if (ref_ch != tst_path.charAt(i))
                break;
            if (ref_ch == File.separatorChar && i != tst_len - 1) {
                last_sep_ind = i;
            }
        }

        if (i == ref_len && i < tst_len &&
            tst_path.charAt(i) == File.separatorChar) {

            return tst_path.substring(i + 1);
        }

        int sep_left = 0;
        for (int j = i; j < ref_len; j++) {
            if (ref_path.charAt(j) == File.separatorChar)
                sep_left++;
        }

        String res = "";
        for (int j = 0; j <= sep_left; j++) {
            res += UPPER_DIR + File.separator;
        }

        res += tst_path.substring(last_sep_ind + 1);
        return res;
    }

    /**
     * Substitutes all occurences of a substring within a string with given
     * string.
     * @param s    a string to which substitution is applied
     * @param from the substring to be substituted
     * @param to   substitution string
     * @return the result of the substitution
     */
    public static String substitute(String s, String from, String to) {
        if (s == null || from == null || s.equals("") || from.equals(""))
            return s;
        String res = "";
        int ind = s.indexOf(from);
        while (ind >= 0) {
            res += s.substring(0, ind);
            res += to;
            s = s.substring(ind + 1);
            ind = s.indexOf(from);
        }
        res += s;
        return res;
    }

    /**
     * @param s a string to be split
     * @param delim delimiting character
     * @return an array of String objects resulting from 'splittig' the input
     * string at the delimiting character positions (delimiters are not
     * included)
     */
    public static String[] split(String s, char delim) {
        if (s == null)
            return null;
        if (delim == ' ' || delim == '\t')
            return split(s);
        String str = s;
        Vector v = new Vector();
        for(int ind = str.indexOf(delim); ind >= 0 && ind < str.length() - 1;) {
            v.addElement(str.substring(0, ind));
            str = str.substring(ind + 1);
            ind = str.indexOf(delim);
        }
        int len = str.length();
        if (len > 1 || len > 0 && str.charAt(0) != delim)
            v.addElement(str);
        String[] res = new String[v.size()];
        v.copyInto(res);
        return res;
    }

    private static int indexOfWhiteSpace(String s) {
        int[] ind = new int[3];
        ind[0] = s.indexOf(' ');
        ind[1] = s.indexOf('\t');
        ind[2] = s.indexOf('\n');

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < ind.length; i++) {
            if (ind[i] < res && ind[i] >= 0)
                res = ind[i];
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    /**
     * Splits a string into 'words', i.e. treats any sequence of white spaces
     * as delimiters which mark the positions at which splitting is made.
     * @param s the String to be split
     * @return the array of 'words'
     */
    public static String[] split(String s) {
        if (s == null)
            return null;
        String str = s.trim();
        Vector v = new Vector();
        for(int i = indexOfWhiteSpace(str); i >= 0 && i < str.length() - 1;) {
            v.addElement(str.substring(0, i));
            str = str.substring(i + 1).trim();
            i = indexOfWhiteSpace(str);
        }
        if (str.length() > 1)
            v.addElement(str);
        String[] res = new String[v.size()];
        v.copyInto(res);
        return res;
    }

    /**
     * Produces the same result as the Unix command with the same name.
     * @param path a path to calculate basename for
     * @return basename of the path
     */
    public static String basename(String path) {
        int i = path.lastIndexOf(File.separatorChar);
        if (i == -1)
            return path;
        return path.substring(i+1);
    }

    /**
     * Produces the same result as the Unix command with the same name.
     * @param path a path to calculate dirname for
     * @return dirname of the path
     */
    public static String dirname(String path) {
        int i = path.lastIndexOf(File.separatorChar);
        if (i == -1)
            return ".";
        return path.substring(0, i);
    }
}
