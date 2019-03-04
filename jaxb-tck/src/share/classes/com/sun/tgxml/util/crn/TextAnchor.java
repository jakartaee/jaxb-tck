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

package com.sun.tgxml.util.crn;

import java.io.PrintStream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a text anchor - a pair of anchor pattern and anchor
 * value. It is able to search for anchor's pattern in arbitrary text, parse
 * any macros met in the matching text, replace macros found in anchor's
 * value with concrete strings.
 *
 * @author Konstantin S. Bobrovsky
 */
public class TextAnchor implements CRNConstants {
    /**
     * Used to indicate an error.
     */
    public static class Fault extends Exception {
        public Fault() {}

        public Fault(String msg) {
            super(msg);
        }

        public Fault(String msg, String text) {
            super(msg + ", text: <" + text + ">");
        }
    }

    private static Object lock = new Object();

    private static HashMap id2macro = new HashMap();

    private String id;
    private String pattern;
    private String value;
    private Pattern patternImpl;
    private Pattern valueImpl;

    /**
     * Default constructor
     *
     * @param id anchor id
     */
    public TextAnchor(String id) {
        this.id = id;
    }

    /**
     * Constructor
     *
     * @param pattern anchor's pattern
     * @param value   anchor's value
     */
    public TextAnchor(String pattern, String value) throws Fault {
        setPattern(pattern);
        setValue(value);
    }

    /**
     * Constructor
     *
     * @param id      anchor id
     * @param pattern anchor's pattern
     * @param value   anchor's value
     */
    public TextAnchor(String id, String pattern, String value) throws Fault {
        this.id = id;
        setPattern(pattern);
        setValue(value);
    }

    /**
     * @return anchor's id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets new pattern for the anchor
     *
     * @param pattern the pattern
     */
    public void setPattern(String pattern) throws Fault {
        this.pattern = pattern;
        try {
            patternImpl = new Pattern(pattern, id2macro.values());
        } catch (Pattern.Fault f) {
            throw new Fault(f.getMessage() + ", anchor: " + getId());
        }
        checkMacrosAreSubset(valueImpl, patternImpl);
    }

    /**
     * Sets new value for the anchor
     *
     * @param value the value
     */
    public void setValue(String value) throws Fault {
        this.value = value;
        try {
            valueImpl = new Pattern(value, id2macro.values());
        } catch (Pattern.Fault f) {
            throw new Fault(f.getMessage() + ", anchor: " + getId());
        }
        checkMacrosAreSubset(valueImpl, patternImpl);
    }

    /**
     * Tries to match anchor's pattern with given text
     *
     * @param text
     *     the text
     * @return 
     *     a {@link Pattern.Match} object filled with necessary info about
     *     the match coordinates, macros, etc, or <code>null</code>
     *     if matching failed
     */
    public Pattern.Match match(String text) {
        return match(text, 0, text.length());
    }

    /**
     * Tries to match anchor's pattern with given piece of text.<br>
     * <b>IMPORTANT</b>: the algorithm used in this method does not
     * handle a situation with "joint" macros, for example:<br>
     * ...<code>$MACRO1$$MACRO2$</code>...
     *
     * @param text 
     *     the text
     * @param start_pos
     *     start position of the piece in the text
     * @param len 
     *     the length of the piece in characters
     * @return 
     *     a {@link Pattern.Match} object filled with necessary info about
     *     the match coordinates, macros, etc, or <code>null</code>
     *     if matching failed
     */
    public Pattern.Match match(String text, int start_pos, int len) {
        if (patternImpl == null) {
            throw new NullPointerException(getId() + " anchor pattern has not been set");
        }
        return clonePattern(patternImpl).match(text, start_pos, len);
    }

    /**
     * Expands all macros in the anchor value whose names
     * are found in the match.
     *
     * @param m the match
     * @return the anchor value with all macros expanded.
     * @throws Fault
     *     if the set of anchor value macros is not a subset of
     *     the set of macros found in the match
     */
    public String expandValue(Pattern.Match m) throws Fault {
        Pattern value_clone = getParsedValue();
        checkMacrosAreSubset(value_clone, m.getParent());
        String[] macro_names = m.getMacroNames();

        for (int i = 0; i < macro_names.length; i++) {
            String macro_name = macro_names[i];
            value_clone.expandMacro(macro_name, m.getMacroValue(macro_name));
        }
        return value_clone.toString();
    }

    /**
     * @return anchor's current pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @return the Pattern object corresponding to the anchor value.
     */
    public Pattern getParsedValue() {
        if (valueImpl == null) {
            throw new NullPointerException(getId() + " anchor value has not been set");
        }
        return clonePattern(valueImpl);
    }

    /**
     * Adds a known macro, which can be present in pattern/value text.
     *
     * @param m the macro to add.
     */
    public static void addKnownMacro(Macro m) {
        synchronized (lock) {
            id2macro.put(m.getName(), m);
        }
    }

    /**
     * @param name macro name
     * @return known macro with this name or <code>null</code>
     */
    public static Macro getKnownMacro(String name) {
        Macro m = (Macro)id2macro.get(name);
        return m == null ? null : (Macro)m.clone();
    }

    /**
     * @return the map (name =&gt; macro object) of all known macros
     */
    public static HashMap getKnownMacros() {
        synchronized (lock) {
            return (HashMap)id2macro.clone();
        }
    }

    private Pattern clonePattern(Pattern p) {
        return (Pattern)p.clone();
    }

    private static void checkMacrosAreSubset(Pattern p, Pattern set) throws Fault {
        if (p == null || set == null) {
            return;
        }
        Set subset   = p.getMacroNamesAsSet();
        Set wholeset = set.getMacroNamesAsSet();

        if (!wholeset.containsAll(subset)) {
            String s = "anchor's value macro set exceeds that of its pattern\n";
            s += "macro names missing in anchor's value:\n";
            Iterator i = subset.iterator();

            while (i.hasNext()) {
                String name = (String)i.next();

                if (!wholeset.contains(name)) {
                    s += " " + name;
                }
            }
            throw new Fault(s);
        }
    }

    /**
     * Print this object to a stream
     *
     * @param out the stream
     */
    public void dbgPrint(PrintStream out) {
        out.println(">>> Anchor: " + getId());
        out.println("-- Source pattern:");
        out.println("<" + pattern + ">");
        out.println("-- Source value:");
        out.println("<" + value + ">");
        out.println("-- Pattern:");
        if (patternImpl != null) {
            patternImpl.dbgPrint(out);
        } else {
            out.println("    NULL");
        }
        out.println("-- Value:");
        if (valueImpl != null) {
            valueImpl.dbgPrint(out);
        } else {
            out.println("    NULL");
        }
        out.println("<<< Anchor: " + getId());
    }
}
