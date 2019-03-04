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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Iterator;

import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.util.crn.CRNConstants;
import com.sun.tgxml.util.crn.Macro;
import com.sun.tgxml.util.crn.MacroParser;
import com.sun.tgxml.util.crn.MacroParserImpls;
import com.sun.tgxml.util.crn.Pattern;
import com.sun.tgxml.util.crn.TextAnchor;

/**
 * An utility responsible for finding proper copyright notices
 * for TCK source files, depending on their type (extension).
 * Its functionality also includes ability to:
 * <ul>
 * <li> find copyright anchors in a text and replace it with
 *      corresponding copyright notice
 * </li>
 * <li> for a given anchor ID return its value
 * </li>
 * <li> load copyright-related settings (anchors and copyrights)
 *      from build properties.
 * </li>
 * </ul>
 *
 * @author Konstantin Bobrovsky
 */
public class CopyrightManager implements CRNConstants, MacroParserImpls {

    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~
    // Inner Classes
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~

    /**
     * Convinency interface to be implemented by external classes.
     */
    public static interface Constants extends CRNConstants {}

    /**
     * Used to signal an error.
     */
    public static class Fault extends Exception {
        public Fault() {}

        public Fault(String msg) {
            super(msg);
        }
    }

    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~
    // Fields
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~

    /**
     * System property name which cancels auto-crn insertion when set.
     */
    public final static String PROP_CRN_SKIP = "crn.skip";

    private static ArrayList allAnchors = new ArrayList();
    private static HashMap ext2anchors = new HashMap();
    private static HashMap expAnchors = new HashMap();
    private static GregorianCalendar calendar = new GregorianCalendar();
    private static int curYear  = calendar.get(Calendar.YEAR);
    private static int curMonth = calendar.get(Calendar.MONTH);
    private static int curDate  = calendar.get(Calendar.DATE);


    private final static String PROP_PATTERN = "crn.anchor.pattern.";
    private final static String PROP_VALUE   = "crn.anchor.value.";
    private final static String PROP_TEXT    = "crn.anchor.text.";
    private final static String PROP_MACRO   = "crn.anchor.macro.";

    private static boolean initDone = false;
    private static boolean idleMode = false;

    private final static int IND_M = 0;
    private final static int IND_I = 1;
    private final static int IND_E = 2;

    private final static int IND_NAME = 0;
    private final static int IND_VAL  = 1;

    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~
    // Initializers
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~

    static {
        init();
    }

    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~
    // Interface methods
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~

    /**
     * Command line interface. Accepts two args: source file and
     * destination file, then invokes
     * {@link #updateCopyright(java.lang.String, java.lang.String)}
     * passing the arguments to it.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            error(CopyrightManager.class.getName() + ": bad arguments");
        }
        loadProperties();
        init();

        try {
            updateCopyright(args[0], args[1]);
        } catch (IOException e) {
            error("CRN update failed for " + args[0] + ": " + e);
        }
    }

    //-----------------------------------------------------
    // getCopyright methods
    //-----------------------------------------------------

    /**
     * Shortcut to getCopyright(anchor_id, macros, null).
     * See {@link #getCopyright(String,String[][],String)}.
     */
    public static String getCopyright(String anchor_id,
                                      String[][] macros)
        throws Fault
    {
        return getCopyright(anchor_id, macros, null);
    }

    /**
     * Returns CRN text for an anchor, expanding given macros.
     *
     * @param anchor_id anchor id
     * @param macros    pairs &lt;macro name, macro value&gt;
     * @param path      the path for which the copyright is being got. Used in warning reporting.
     * @return
     *     the CRN text with macros expanded.
     * @throws Fault
     *     if either given anchor id is unknown or some macros could
     *     not be expanded (were not present in the array of
     *     &lt;name, value&gt; pairs).
     */
    public static String getCopyright(String anchor_id,
                                      String[][] macros,
                                      String path)
        throws Fault
    {
        return idleMode ?
            null :
            getCopyright(anchor_id, MiscUtils.pairArrayToMap(macros), path);
    }

    /**
     * Shortcut to getCopyright(anchor_id, sc_info, null).
     * See {@link #getCopyright(String,String,String)}.
     */
    public static String getCopyright(String anchor_id,
                                      String sc_info)
        throws Fault
    {
        return getCopyright(anchor_id, sc_info, null);
    }

    /**
     * Returns CRN text for an anchor, expanding W, E and I macros, whose
     * values are extracted from given source code control information.
     *
     * @param anchor_id anchor id
     * @param sc_info   the source code control information
     * @param path      the path for which the copyright is being got. Used in warning reporting.
     * @return
     *     the CRN text with macros expanded.
     * @throws Fault
     *     if either given anchor id is unknown or some macros could
     *     not be expanded (the CRN text contained macros other than
     *     those which could be extracted from the sc info).
     */
    public static String getCopyright(String anchor_id,
                                      String sc_info,
                                      String path)
        throws Fault
    {
        String[][] macros = new String[][] {{ MACRO_SC_INFO, sc_info}};
        return idleMode ? null : getCopyright(anchor_id, macros, path);
    }

    /**
     * Returns a CRN text specific for Jmpp-generated sources.
     * The specifics is that such CRNs contain extra macro, corresponding
     * to Jmpp template name from which the source was generated.
     *
     * @param sc_info
     *     sc info, filename part is treated as Jmpp template name
     * @param file_name
     *     the name of the generated source
     * @return
     *     the CRN text with all CRN macros expanded.
     */
    public static String getJmppCopyright(String sc_info, String file_name) {
        return getJmppCopyright(sc_info, file_name, CRN_JMPP_GEN);
    }

    /**
     * Returns a CRN text specific for Jmpp-generated sources.
     * The specifics is that such CRNs contain extra macro, corresponding
     * to Jmpp template name from which the source was generated.
     *
     * @param sc_info
     *     sc info, filename part is treated as Jmpp template name
     * @param file_name
     *     the name of the generated source
     * @param anchor_id
     *     CRN anchor id (must be Jmpp-specific, that is, its value
     *     must contain
     *     {@link com.sun.tgxml.util.crn.CRNConstants#MACRO_FILE} and
     *     {@link com.sun.tgxml.util.crn.CRNConstants#MACRO_TEMPLATE} macros
     * @return
     *     the CRN text with all CRN macros expanded.
     */
    public static String getJmppCopyright(String sc_info,
                                          String file_name,
                                          String anchor_id)
    {
        if (idleMode) {
            return null;
        }
        try {
            String[][] sc_tags = parseScInfo(sc_info);
            String year = "" + extractYear(sc_tags[IND_E][IND_VAL], file_name);
            String m_macro = sc_tags[IND_M][IND_VAL];
            String i_macro = sc_tags[IND_I][IND_VAL];
            String w_macro = "@(#)" + m_macro + (i_macro == null ? "" : "\t" + i_macro);
            HashMap map = MiscUtils.pairArrayToMap(sc_tags);
            map.put(MACRO_FILE,     MiscUtils.basename(file_name));
            map.put(MACRO_TEMPLATE, m_macro);
            map.put(MACRO_YEAR,     year);
            map.put(MACRO_W,        w_macro);
            logCrnOk(file_name);
            return getCopyright(anchor_id, map, file_name);
        } catch (Fault f) {
            errorSkipCRN("(getJmppCopyright) " + f.getMessage(), file_name);
            return null;
        }
    }

    //-----------------------------------------------------
    // insertCopyright methods
    //-----------------------------------------------------

    /**
     * Short-cut method to
     * {@link #insertCopyright(java.lang.String,
     *                         com.sun.tgxml.util.crn.TextAnchor[],
     *                         int,
     *                         String)}
     * method. The set of applicable CRN anchors is determined basing on
     * the extension of given filename.
     *
     * @param text source file contents
     * @param file source file
     * @param year CRN year
     * @return
     *     the original text with valid CRN inserted.
     */
    public static String insertCopyright(String text, File file, int year) {
        if (idleMode) {
            return text;
        }
        ArrayList l = (ArrayList)ext2anchors.get(MiscUtils.getFileExtension(file));

        if (l == null) {
            l = allAnchors;
        }
        String path = file.getAbsolutePath();
        TextAnchor[] arr = (TextAnchor[])l.toArray(new TextAnchor[l.size()]);
        try {
            String res = insertCopyright(text, arr, year, path);

            if (res != text) {
                logCrnOk(path);
            } else {
                warnAnchorNotFound("(insertCopyright) " + path);
            }
            return res;
        } catch (Fault f) {
            errorSkipCRN("(insertCopyright) " + f.getMessage(), path);
        }
        return text;
    }

    /**
     * Short-cut method to
     * {@link #insertCopyright(java.lang.String,
     *                         java.io.File,
     *                         int)}
     * method. The value of the year parameter is extracted from
     * given source code control information.
     *
     * @param text
     *     the text
     * @param file
     *     a file from which the text was read. Necessary to determine
     *     a set of possible anchors which may present in the text.
     * @param sc_info the source code control information. Must have the format
     *     <code>@(#)<i>filename  delta</i></code> <i>yy/mm/dd ... </i>. For example: <i>@(#)CopyrightManager.java	1.4 04/04/28 1.4</i>.
     * @return
     *     the original text with valid CRN inserted.
     */
    public static String insertCopyright(String text,
                                         File file,
                                         String sc_info)
    {
        if (idleMode) {
            return text;
        }
        String path = file.getAbsolutePath();
        try {
            String year = sc_info == null ?
                null :
                parseScInfo(sc_info)[IND_E][IND_VAL];
            return insertCopyright(text, file, extractYear(year, path));
        } catch (Fault f) {
            error(f.getMessage() + ", file: " + path);
        }
        return text;
    }

    /**
     * Short-cut method to
     * {@link #insertCopyright(java.lang.String,
     *                         java.io.File,
     *                         int)}
     * method. The value of the 3-rd parameter (year) is set to zero. This means
     * that actual copyright year will be extracted from the matched piece
     * of text.
     *
     * @param text
     *     the text
     * @param file
     *     a file from which the text was read. Necessary to determine
     *     a set of possible anchors which may present in the text.
     * @return
     *     the original text with valid CRN inserted.
     */
    public static String insertCopyright(String text, File file) {
        return insertCopyright(text, file, 0);
    }

    /**
     * Finds first CRN anchor whose pattern matchces given text and replaces
     * the matched part of the text with the anchor's value, expanding found
     * CRN macros. CRN macro values are taken from the matched piece of text.
     *
     * @param text
     *     the text
     * @param anchors
     *     the set of anchors to search for in the text
     * @param year
     *     copyright year. If not zero, used as the value of the
     *     {@link com.sun.tgxml.util.crn.CRNConstants#MACRO_YEAR} macro
     * @param file
     *     (can be null) appears in error messages.
     * @return
     *     the original text object if no anchor patterns matched. Otherwise,
     *     the original text with matched part replaced with anchor value.
     */
    public static String insertCopyright(String text,
                                         TextAnchor[] anchors,
                                         int year,
                                         String file)
        throws Fault
    {
        if (idleMode) {
            return text;
        }
        if (year != 0) {
            // protection against invalid input
            year = (year > curYear || year < 1950) ? curYear : year;
        }
        for (int i = 0; i < anchors.length; i++) {
            TextAnchor a = anchors[i];
            Pattern.Match m = a.match(text);

            if (m == null) {
                continue;
            }
            if (year == 0) {
                // check if there was an sc info matched -
                // try to extract CRN year from it then
                String s = m.getMacroValue(MACRO_E);

                if (s == null) {
                    s = m.getMacroValue(MACRO_SC_INFO);
                    s = s == null ? s : parseScInfo(s)[IND_E][IND_VAL];
                }
                year = extractYear(s, file);
            }
            if (year != 0) {
                m.expandMacro(MACRO_YEAR, "" + year);
            }
            String res = text.substring(0, m.getStartPos());
            try {
                res += a.expandValue(m);
            } catch (TextAnchor.Fault f) {
                throw new Fault(f.getMessage());
            }
            res += text.substring(m.getEndPos() + 1);
            return res;
        }
        return text;
    }

    /**
     * Short-cut method to
     * {@link #insertCopyright(java.lang.String,
     *                         com.sun.tgxml.util.crn.TextAnchor[],
     *                         int,
     *                         String)}
     * method. The 3-rd parameter (year) is set to zero, the 4-th - to <code>null</null>.
     *
     * @param text
     *     the text
     * @param anchors
     *     the set of anchors to search for in the text
     * @return
     *     the original text with valid CRN inserted.
     */
    public static String insertCopyright(String text,
                                         TextAnchor[] anchors)
        throws Fault
    {
        return idleMode ? text : insertCopyright(text, anchors, 0, null);
    }

    //-----------------------------------------------------
    // updateCopyright methods
    //-----------------------------------------------------

    /**
     * Short-cut method to
     * {@link #updateCopyright(String, String, String)}
     * method. The 3-rd parameter (sc info) is set to <code>null</code>.
     *
     * @param src_path
     *     the path of the source file
     * @param dst_path
     *     the path of the destination file
     * @throws IOException
     *     if an I/O error occurred when reading/writing files
     */
    public static void updateCopyright(String src_path,
                                       String dst_path)
        throws IOException
    {
        updateCopyright(src_path, dst_path, null);
    }

    /**
     * Short-cut method to
     * {@link #updateCopyright(java.io.File, java.io.File, String)}
     * method. The 3-rd parameter (sc info) is set to <code>null</code>.
     *
     * @param src
     *     source file
     * @param dst
     *     destination file
     * @throws IOException
     *     if an I/O error occurred when reading/writing files
     */
    public static void updateCopyright(File src, File dst) throws IOException {
        updateCopyright(src, dst, null);
    }

    /**
     * Short-cut method to
     * {@link #updateCopyright(java.io.File, java.io.File, String)}
     * method. Necessary <code>java.io.File</code> objects are constructed
     * from the source and destination paths.
     *
     * @param src_path
     *     the path of the source file
     * @param dst_path
     *     the path of the destination file
     * @param sc_info
     *     a source code control information
     * @throws IOException
     *     if an I/O error occurred when reading/writing files
     */
    public static void updateCopyright(String src_path,
                                       String dst_path,
                                       String sc_info)
        throws IOException
    {
        updateCopyright(new File(src_path), new File(dst_path), sc_info);
    }

    /**
     * Performs insertiion of valid CRN into a file. Essentially,
     * this method reads given source file into a String, invokes the
     * {@link #insertCopyright(String, java.io.File, String)} method
     * with appropriate parameters and writes the result to the destination.
     * If the source file is not a text file (as determined by
     * {@link MiscUtils#isTextFile(java.io.File)}) - simply copies the
     * source to the destination in binary mode.
     *
     * @param src
     *     source file
     * @param dst
     *     destination file
     * @param sc_info
     *     source code control information, if non-null, used to extract
     *     actual copyright year.
     * @throws IOException
     *     if an I/O error occurred when reading/writing files.
     *
     * @see #insertCopyright(String, java.io.File, String)
     */
    public static void updateCopyright(File src,
                                       File dst,
                                       String sc_info)
        throws IOException
    {
        if (idleMode || !MiscUtils.isTextFile(src)) {
            MiscUtils.copyFile(src, dst);
            return;
        }
        byte[] data = MiscUtils.readBinaryFile(src);
        String text = new String(data);
        String new_text = null;

        try {
            String year = sc_info == null ?
                null :
                parseScInfo(sc_info)[IND_E][IND_VAL];
            new_text = insertCopyright(text, dst, extractYear(year, dst.getAbsolutePath()));
        } catch (Fault f) {
            error(f.getMessage() + ", src: " + src + ", dst: " + dst);
            new_text = text;
        }
        if (text == new_text) {
            MiscUtils.writeBinaryFile(dst, data);
        } else {
            MiscUtils.writeTextFile(dst, new_text);
        }
    }

    //-----------------------------------------------------
    // Misc error/warning reporting methods
    //-----------------------------------------------------

    public static void error(String msg) {
        if (idleMode) {
            return;
        }
        //Thread.currentThread().dumpStack();
        System.err.println("*** CRN ERROR: " + msg);
    }

    public static void fatalError(String msg) {
        Thread.currentThread().dumpStack();
        System.err.println("*** CRN ERROR: " + msg);
        System.exit(1);
    }

    public static void fatalError(String msg, Throwable t) {
        t.printStackTrace();
        System.err.println("*** CRN ERROR: " + msg);
        System.exit(1);
    }

    public static void warning(String msg) {
        if (idleMode) {
            return;
        }
        System.out.println("*** CRN WARNING: " + msg);
    }

    public static void warnAnchorNotFound(String path) {
        if (idleMode) {
            return;
        }
        warning("no CRN anchor found in: " + path);
    }

    public static void errorSkipCRN(String msg, String path) {
        if (idleMode) {
            return;
        }
        error(msg + ", CRN not inserted, file: " + path);
    }

    public static void log(String msg) {
        //System.out.println("*** CRN LOG: " + msg);
    }

    public static void logCrnOk(String msg) {
        //System.out.println("*** CRN OK: " + msg);
    }

    public static void dbg(Class c, String msg, File f) {
        System.out.println("*** CRN debug: " + c.getName() + ", " + msg + ", " + f);
    }

    public static void dbg(Class c, String msg, File src, File dst) {
        System.out.println("*** CRN debug: " + c.getName() + ", " + msg + "\n\t" +
                           "SRC: " + src + ", DST: " + dst);
    }

    //-----------------------------------------------------
    // Initialization methods
    //-----------------------------------------------------

    /**
     * Simply invokes {@link com.sun.tgxml.tjtf.tools.BuildProperties#loadProperties()}.
     * Useful when the class is used separately from the B&I fromework (directly).
     * Before using methods of this class, one has to call {@link #loadProperties()} and
     * {@link #init()} methods (in this order).
     */
    public static void loadProperties() {
            try {
                log("loading properties");
                BuildProperties.loadProperties();
                initDone = false;
            } catch (IOException e) {
                e.printStackTrace();
                fatalError("could not load build props: " + e);
            }

    }

    /**
     * Performs basic static initialization.
     */
    public static void init() {
        if (initDone) {
            return;
        }
        if (System.getProperty(PROP_CRN_SKIP) != null) {
            idleMode = true;
            return;
        }
        try {
            // in this order!
            loadKnownMacros();
            loadMacros();
            loadAnchors();
            initDone = true;
        } catch (TextAnchor.Fault f1) {
            String s = f1.getMessage();
            fatalError("failed to load crn anchors/macros from property file: " + s, f1);
        } catch (Pattern.Fault f2) {
            String s = f2.getMessage();
            fatalError("failed to load crn anchors/macros from property file: " + s, f2);
        }
    }

    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~
    // Other methods
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~

    //-----------------------------------------------------
    // Helper methods
    //-----------------------------------------------------

    private static String getCopyright(String anchor_id, HashMap macros, String path)
        throws Fault
    {
        TextAnchor a = (TextAnchor)expAnchors.get(anchor_id);

        if (a == null) {
            throw new Fault("unknown anchor id: " + anchor_id);
        }
        Pattern val = a.getParsedValue();
        String sc_info = (String)macros.get(MACRO_SC_INFO);
        String year = (String)macros.get(MACRO_YEAR);

        if (sc_info != null) {
            String[][] sc_tags = parseScInfo(sc_info);

            for (int i = 0; i < sc_tags.length; i++) {
                if (macros.get(sc_tags[i][IND_NAME]) != null) {
                    macros.put(sc_tags[i][IND_NAME], sc_tags[i][IND_VAL]);
                }
            }
            if (year == null) {
                year = "" + extractYear(sc_tags[IND_E][IND_VAL], path);
                macros.put(MACRO_YEAR, year);
            }
            if (macros.get(MACRO_W) == null) {
                String m = sc_tags[IND_M][IND_VAL];
                String i = sc_tags[IND_I][IND_VAL];
                String w = "@(#)" + m + "\t" + (i == null ? "" : i);
                macros.put(MACRO_W, w);
            }
            if (macros.get(MACRO_E) == null) {
                macros.put(MACRO_E, sc_tags[IND_E][IND_VAL]);
            }
        }
        val.expandMacros(macros);
        // +++ assign current year to MACRO_YEAR if it is not expanded
        if (val.getMacroCount(MACRO_YEAR) > 0 &&
            val.getMacroValue(MACRO_YEAR) == null)
        {
            warning("copyright year unavailable, file: " + path);
            val.expandMacro(MACRO_YEAR, "" + curYear);
        }
        // --
        val.expandMacroDefaults();
        ArrayList l = val.getUnexpandedMacroNames();

        if (l.size() > 0) {
            String list = "";
            for (int j = 0; j < l.size(); list += " " + l.get(j++));
            throw new Fault("unexpanded macros left: " + list);
        }
        return val.toString();
    }

    private static String[][] parseScInfo(String sc_info) throws Fault {
        if (sc_info == null) {
            return null;
        }
        Macro m_copy = (Macro)TextAnchor.getKnownMacro(MACRO_M).clone();
        Macro i_copy = (Macro)TextAnchor.getKnownMacro(MACRO_I).clone();
        Macro e_copy = (Macro)TextAnchor.getKnownMacro(MACRO_E).clone();

        MacroParser[] arr = new MacroParser[] {
            new VariantValueParser(
                new StringParser[] {
                    new StringParser("Ident:"),
                    new StringParser("File:"),
                    new StringParser("")
                }
            ),
            spacesParser,
            zParser,
            spacesParser,
            m_copy,
            spacesParser,
            new VariantValueParser(
                i_copy,
                new StringParser("")
            ),
            spacesParser,
            e_copy
        };
        CompositeValueParser p = new CompositeValueParser(arr);

        if (p.parseValue(sc_info, 0) < 0) {
            throw new Fault("bad sc info: " + sc_info);
        }
        String[][] res = new String[3 /* total number of sub-macros */][2];
        res[IND_M][IND_NAME] = MACRO_M; res[IND_M][IND_VAL] = m_copy.getValue();
        res[IND_I][IND_NAME] = MACRO_I; res[IND_I][IND_VAL] = i_copy.getValue();
        res[IND_E][IND_NAME] = MACRO_E; res[IND_E][IND_VAL] = e_copy.getValue();
        return res;
    }

    private static int parse2Digits(String s) throws Fault {
        try {
            if (s.charAt(0) == '0') {
                s = s.substring(1);
            }
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new Fault("bad sccs E macro part (not a number): " + s);
        }
    }

    private static int year2to4digits(int year2digits) {
        return (year2digits < 50 ? 2000 : 1900) + year2digits;
    }

    private static int extractYear(String e_macro, String path) throws Fault {
        // parsing E sccs macro, e.g. "04/01/23"
        if (e_macro == null) {
            return 0;
        }
        if (e_macro.length() != 8) {
            throw new Fault("bad sccs E macro (" + e_macro + ")");
        }
        int p1 = parse2Digits(e_macro.substring(0, 2));
        int p2 = parse2Digits(e_macro.substring(3, 5));
        int p3 = parse2Digits(e_macro.substring(6, 8));

        // check if this was really E macro (yy/mm/dd),
        // and not G or H macro (mm/dd/yy)

        int year = year2to4digits(p1);

        if (year > curYear ||
            (year == curYear && p2 > curMonth + 1) ||
            (year == curYear && p2 == curMonth + 1 && p3 > curDate)) {

            if (path == null) {
                Thread.currentThread().dumpStack();
            }
            System.out.println("year: " + year + " (cur is " + curYear + ")," +
                               " month: " + p2 + " (cur is " + curMonth + ")," +
                               " date: " + p3 + " (cur is " + curDate + ")");
            warning("invalid sccs E macro (" + e_macro + "), file: " + path);
            year = year2to4digits(p3);
        }

        // final year check

        if (year < 1950 || year > curYear) {
            if (path == null) {
                Thread.currentThread().dumpStack();
            }
            warning("invalid year (" + year + "), file: " + path);
            year = curYear;
        }
        return year;
    }

    //-----------------------------------------------------
    // Init and build property loading/processing methods
    //-----------------------------------------------------

    /**
     * Loads a set of build properties whose names start with given
     * prefix.
     *
     * @param prefix the prefix
     * @return a map of &lt;property name =&gt; property value&gt;
     *     translations
     */
    private static HashMap loadPropertyFamily(String prefix) {
        HashMap res = new HashMap();
        Enumeration e = BuildProperties.propertyNames(prefix);

        while (e.hasMoreElements()) {
            String prop = (String)e.nextElement();
            String suff = prop.substring(prefix.length());
            res.put(suff, BuildProperties.getString(prop));
        }
        return res;
    }

    /**
     * Loads a set of macro definitions from the build properties
     * constructs corresponding macro objects and adds them to the
     * set of known macros.
     *
     * @throws com.sun.tgxml.util.crn.TextAnchor.Fault
     * @throws com.sun.tgxml.util.crn.Pattern.Fault
     */
    private static void loadMacros() throws TextAnchor.Fault, Pattern.Fault {
        HashMap all = loadPropertyFamily(PROP_MACRO);
        Iterator iter = all.keySet().iterator();
        HashMap parsers   = new HashMap();
        HashMap defaults = new HashMap();

        while (iter.hasNext()) {
            String s = (String)iter.next();
            int i = s.indexOf('.');

            if (i <= 0) {
                // should contain a dot
                fatalError("bad CRN macro in properties: " + s);
            }
            String name = s.substring(0, i);
            String suff = s.substring(i + 1);
            String prop = (String)all.get(s);

            if (suff.equals("default")) {
                defaults.put(name, prop);
            } else {
                ArrayList l = (ArrayList)parsers.get(name);

                if (l == null) {
                    l = new ArrayList();
                    parsers.put(name, l);
                }
                l.add(getValueParser(prop));
            }
        }
        iter = parsers.keySet().iterator();
        ArrayList known_macros = new ArrayList();

        while (iter.hasNext()) {
            String name = (String)iter.next();
            ArrayList l = (ArrayList)parsers.get(name);

            if (l == null) {
                fatalError("no value for CRN macro: " + name);
            }
            // construct a value parser for the macro
            MacroParser parser = l.size() > 1 ?
                new VariantValueParser(l) :
                (MacroParser)l.get(0);
            // create the macro and store in additional "known macros" list
            Macro m = new Macro(name, parser);
            m.setDefaultValue((String)defaults.get(name));
            known_macros.add(m);
        }
        // make additional "known macros" list elements
        // really known macros, parsable by TextAnchors
        for (int i = 0; i < known_macros.size(); i++) {
            TextAnchor.addKnownMacro((Macro)known_macros.get(i));
        }
    }

    /**
     * Loads all CRN anchor definitions from the build properties,
     * constructs and internally stores corresponding anchor objects.
     */
    private static void loadAnchors() {
        HashMap patterns = loadPropertyFamily(PROP_PATTERN);
        HashMap values   = loadPropertyFamily(PROP_VALUE);
        HashMap texts    = loadPropertyFamily(PROP_TEXT);

        Iterator iter = values.keySet().iterator();

        if (!values.keySet().containsAll(patterns.keySet())) {
            fatalError("the set of all anchor pattern ids must be a subset " +
                       "of the set of all anchor value ids");
        }
        while (iter.hasNext()) {
            String anchor_id = (String)iter.next();
            String pattern_text_id = (String)patterns.get(anchor_id);
            String value_text_id   = (String)values.get(anchor_id);
            String value = (String)texts.get(value_text_id);

            try {
                if (pattern_text_id != null) {
                    // this is a "full" anchor which have both pattern and value -
                    // such anchors are used to really replace anchors with
                    // crn text.
                    String pattern = (String)texts.get(pattern_text_id);
                    TextAnchor anchor = new TextAnchor(anchor_id, pattern, value);
                    allAnchors.add(anchor);
                    //anchor.dbgPrint(System.out);
                } else {
                    // this is an "expansion" anchor used just to
                    // construct a crn text with optional macro
                    // expansion (without matching anchor pattern)
                    TextAnchor anchor = new TextAnchor(anchor_id);
                    anchor.setValue(value);
                    expAnchors.put(anchor_id, anchor);
                    //anchor.dbgPrint(System.out);
                }
            } catch (TextAnchor.Fault f) {
                fatalError("anchor " + anchor_id + ": " + f.getMessage(), f);
            }
        }
    }

    /**
     * Constructs a macro parser for given pattern. The pattern may
     * include primordially known macros (those defined in
     * {@link com.sun.tgxml.util.crn.CRNConstants} rather than in the
     * build properties.)
     *
     * @param pattern
     * @return
     * @throws Pattern.Fault
     */
    private static MacroParser getValueParser(String pattern)
        throws Pattern.Fault
    {
        if (pattern.length() == 0) {
            return new StringParser("");
        }
        ArrayList l = Pattern.splitPattern(pattern, TextAnchor.getKnownMacros());
        ArrayList parsers = new ArrayList();

        for (int i = 0; i < l.size(); i++) {
            Object elem = l.get(i);

            if (elem instanceof Macro) {
                // is a Macro
                Macro m = (Macro)elem;
                parsers.add(m.getValueParser());
            } else {
                // is a String
                parsers.add(new StringParser((String)elem));
            }
        }
        return parsers.size() > 1 ?
            new CompositeValueParser(parsers) :
            (MacroParser)parsers.get(0);
    }

    /**
     * Fill the set of "known" macros by adding new macros via
     * {@link com.sun.tgxml.util.crn.TextAnchor#addKnownMacro(com.sun.tgxml.util.crn.Macro)}
     */
    private static void loadKnownMacros() {
        TextAnchor.addKnownMacro(new Macro(MACRO_Z, zParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_M, mParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_I, iParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_W, wParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_E, eParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_T, tParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_YEAR, yearParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_SC_INFO, scInfoParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_FULL_CRN_LINK, crnLinkParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_AUTHOR, authorParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_AUTHOR_LIST, authorListParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_TEXT_TILL_EOL, textTillEOLParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_FILE));
        TextAnchor.addKnownMacro(new Macro(MACRO_TEMPLATE));
        TextAnchor.addKnownMacro(new Macro(MACRO_SPACES, spacesParser));
        TextAnchor.addKnownMacro(new Macro(MACRO_STARS, starsParser));
    }
}
