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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.sun.tgxml.util.MiscUtils;

/**
 * represents a text pattern - a piece of text containing macros,
 * i.e. ascii-only identifiers bounded by the {@link #MACRO_CHAR}
 * character.
 *
 * @author Konstantin S. Bobrovsky
 */
public class Pattern implements MacroParserImpls {
    /**
     * Used to indicate an error.
     */
    public static class Fault extends Exception {
        public Fault() {}

        public Fault(String msg) {
            super("CRN Pattern error: " + msg);
        }
    }

    /**
     * A character bounding macro names in the text.
     */
    public final static char MACRO_CHAR = '$';

    /**
     * Represents a match of a text pattern with a piece of text.
     */
    public static class Match {
        private Pattern parent;
        private int begPos;
        private int endPos;

        /**
         * Constructor.
         *
         * @param pattern
         *     the pattern which matched
         * @param beg_pos
         *     start position of the matched substring in the text
         * @param end_pos 
         *     end position of the matched substring in the text
         */
        Match(Pattern pattern, int beg_pos, int end_pos) {
            parent = pattern;
            begPos = beg_pos;
            endPos = end_pos;
        }

        /**
         * @return the start position of the matched text piece in the text
         */
        public int getStartPos() {
            return begPos;
        }

        /**
         * @return the end position of the matched text piece in the text
         */
        public int getEndPos() {
            return endPos;
        }

        /**
         * @return an array of strings. Each string is a macro name found in the
         *     matched piece of text
         */
        public String[] getMacroNames() {
            return parent.getMacroNames();
        }

        /**
         * Retrieves a (matched) value of a macro with given name
         *
         * @param macro_name
         *     macro name
         * @return 
         *     macro value or <code>null</code> if macro with this
         *     name was not found
         */
        public String getMacroValue(String macro_name) {
            return parent.getMacroValue(macro_name);
        }

        /**
         * Sets a macro value to given string
         *
         * @param macro_name  macro name
         * @param macro_value the string
         * @return <code>true</code> if macro with this name was found,
         *     <code>false</code> otherwise.
         */
        public boolean expandMacro(String macro_name, String macro_value) {
            return parent.expandMacro(macro_name, macro_value);
        }

        /**
         * @return the parent Pattern object
         */
        Pattern getParent() {
            return parent;
        }
    }

    protected HashMap macros = new HashMap();
    protected HashMap knownMacros = new HashMap();

    protected CompositeValueParser parser;

    /**
     * Default constructor (non-public).
     */
    protected Pattern() {}

    /**
     * Constructor.
     *
     * @param text
     *     pattern text
     * @param known_macros
     *     a set of known macros
     * @throws Fault
     *     if the text contains unknown macro names
     */
    public Pattern(String text, Collection known_macros) throws Fault {
        setKnownMacros(known_macros);
        parseMacros(text);
    }

    /**
     * Constructor.
     *
     * @param text 
     *     pattern text
     * @throws Fault 
     *     if the text contains unknown macro names
     */
    public Pattern(String text) throws Fault {
        parseMacros(text);
    }

    /**
     * @return full copy of this object
     */
    public Object clone() {
        Pattern copy = new Pattern();
        copy.setKnownMacros(knownMacros.values());
        ArrayList l = new ArrayList();

        for (int i = 0; i < parser.getParserCount(); i++) {
            Macro m = (Macro)parser.getParser(i);
            MacroParser m_parser = m.getValueParser();
            Macro m_clone = (Macro)m.clone();

            if (m_parser instanceof PlainTextParser) {
                // such macro is not subject to macro operations on the
                // pattern (e.g. expansion) - so it is not added to the
                // main macro hash. Instead, its value is copied.
                m_clone.setValue(((PlainTextParser)m_parser).getText());
            } else {
                // regular macro, defined in the pattern's text -
                // add it to the main hash of the pattern copy
                copy.addMacro(m_clone);
            }
            l.add(m_clone);
        }
        copy.parser = new CompositeValueParser(l);
        return copy;
    }

    /**
     * Clones a {@link MacroParserImpls.CompositeValueParser} object.
     *
     * @param parser the parser to clone
     * @return the cloned object.
     */
    private static CompositeValueParser cloneParser(CompositeValueParser parser) {
        ArrayList l = new ArrayList();

        for (int i = 0; i < parser.getParserCount(); i++) {
            l.add(((Macro)parser.getParser(i)).clone());
        }
        return new CompositeValueParser(l);
    }

    /**
     * Sets known macros. Only these macros are allowed to
     * be present in the pattern text.
     *
     * @param c the set of known macros.
     */
    public void setKnownMacros(Collection c) {
        if (c == null) {
            knownMacros.clear();
            return;
        }
        Iterator i = c.iterator();

        while (i.hasNext()) {
            Macro m = (Macro)i.next();
            knownMacros.put(m.getName(), m);
        }
    }

    /**
     * @return an array of macro names found in the text
     */
    public String[] getMacroNames() {
        return (String[])macros.keySet().toArray(new String[macros.size()]);
    }

    /**
     * @return a set of macro names found in the text
     */
    public Set getMacroNamesAsSet() {
        return macros.keySet();
    }

    /**
     * Retrieves a value of an (expanded) macro with given name
     *
     * @param macro_name
     *     macro name
     * @return 
     *     macro value or <code>null</code> if macro with this
     *     name was not found or has not been expanded yet
     */
    public String getMacroValue(String macro_name) {
        Macro m = getMacro(macro_name);
        return (m == null ? null : m.getValue());
    }

    /**
     * Expands a macro with given name (sets it to given value).
     *
     * @param macro_name
     *     macro name
     * @param macro_value
     *     macor value
     * @return <code>true</code> if expansion succeeded, <code>false</code> otherwise
     *     (if a macro with given name was not found in the text)
     */
    public boolean expandMacro(String macro_name, String macro_value) {
        int count = getMacroCount(macro_name);

        for (int i = 0; i < count; i++) {
            getMacro(macro_name, i).setValue(macro_value);
        }
        return count > 0 ? true : false;
    }

    /**
     * Expands macros present in the pattern according
     * to the supplied map.
     *
     * @param name2val a map of &lt;macro name => &gt; macro value&gt;
     *     translations
     * @return
     *     the number of macros expanded.
     */
    public int expandMacros(HashMap name2val) {
        Iterator i = name2val.keySet().iterator();
        int cnt = 0;

        while (i.hasNext()) {
            String name = (String)i.next();
            String val  = (String)name2val.get(name);

            if (expandMacro(name, val)) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * Expands macros present in the pattern according
     * to the supplied array of &lt;macro name, macro value&gt; pairs.
     *
     * @param name_val_pairs the array of pairs
     * @return 
     *     the number of macros expanded.
     */
    public int expandMacros(String[][] name_val_pairs) {
        int cnt = 0;

        for (int i = 0; i < name_val_pairs.length; i++) {
            if (expandMacro(name_val_pairs[i][0], name_val_pairs[i][1])) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * Sets pattern text macros to their default values.
     */
    public void expandMacroDefaults() {
        ArrayList l = getUnexpandedMacroNames();

        for (int i = 0; i < l.size(); i++) {
            String name = (String)l.get(i);
            String val = getMacro(name).getDefaultValue();

            if (val != null) {
                expandMacro(name, val);
            }
        }
    }

    /**
     * @param macro_name macro name
     * @return the number of occurances of the macro in the pattern text
     */
    public int getMacroCount(String macro_name) {
        ArrayList l = getMacros(macro_name);
        return l == null ? 0 : l.size();
    }

    /**
     * @param macro_name macro name
     * @param ind        index
     * @return
     *     macro object corresponding to the ind'th occurance of the
     *     macro name in the pattern text or <code>null</code>
     */
    public Macro getMacro(String macro_name, int ind) {
        ArrayList l = getMacros(macro_name);
        return l == null ? null : (Macro)l.get(ind);
    }

    /**
     * @param macro_name macro name
     * @return 
     *     macro object corresponding to the 0'th occurance of the
     *     macro name in the pattern text or <code>null</code>
     */
    public Macro getMacro(String macro_name) {
        return getMacro(macro_name, 0);
    }

    /**
     * @param macro_name  macro name
     * @return 
     *     macro objects corresponding to all occurances of the
     *     macro name in the pattern text.
     */
    protected ArrayList getMacros(String macro_name) {
        return (ArrayList)macros.get(macro_name);
    }

    /**
     * @return a list of macro names which are still unexpanded
     */
    public ArrayList getUnexpandedMacroNames() {
        ArrayList l = new ArrayList();
        Iterator i = macros.keySet().iterator();

        while (i.hasNext()) {
            String name = (String)i.next();
            Macro m = getMacro(name);

            if (m.getValue() == null) {
                l.add(name);
            }
        }
        return l;
    }

    /**
     * @return a number of macros which are still unexpanded
     */
    public int countUnexpandedMacros() {
        int count = 0;
        Iterator i = macros.keySet().iterator();

        while (i.hasNext()) {
            Macro m = getMacro((String)i.next());

            if (m.getValue() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return string representation of the pattern
     */
    public String toString() {
        String res = "";

        for (int i = 0; i < parser.getParserCount(); i++) {
            Macro m = (Macro)parser.getParser(i);
            res += m.getValue();
        }
        return res;
    }

    /**
     * Adds a macro to the internal map of parsed macros.
     *
     * @param m the macro to add
     */
    protected void addMacro(Macro m) {
        String name = m.getName();
        ArrayList l = (ArrayList)macros.get(name);

        if (l == null) {
            l = new ArrayList();
            macros.put(name, l);
        }
        l.add(m);
    }

    /**
     * Splits a text into the list of macros and strings.
     * Split points are macro name occurances. Strings
     * reluted from the split are added to the resulting list
     * as is, macro names are converted to macros using the
     * known macros collection.
     *
     *
     * @param text         the text
     * @param known_macros the map of known macros (macro name =&gt; macro object)
     * @return
     *     the list of objects resulted from the split
     * @throws Fault
     *     if unknown macro names were met in the text
     */
    public static ArrayList splitPattern(String text,
                                         HashMap known_macros)
        throws Fault
    {
        int beg = 0;
        int end = 0;
        ArrayList res = new ArrayList();

        while (true) {
            // cycle thru all macros
            beg = text.indexOf(MACRO_CHAR);
            end = text.indexOf(MACRO_CHAR, beg + 1);
            String macro_name = parseMacroName(beg, end, text);

            if (macro_name == null) {
                // no macros left - exit
                break;
            }
            String s = text.substring(0, beg);

            if (s.length() > 0) {
                res.add(s);
            }
            Macro macro;

            // check if known macros set
            if (known_macros != null && known_macros.size() > 0) {
                // throw error if the macro just read is unknown
                // otherwise - initialize its value parser
                String base_name = new Macro(macro_name).getBaseName();
                Macro known_macro = (Macro)known_macros.get(base_name);

                if (known_macro == null) {
                    throw new Fault("unknown macro: " + base_name);
                }
                macro = (Macro)known_macro.clone();
            } else {
                macro = new Macro(macro_name);
            }
            res.add(macro);
            // advance past the boundary of the last parsed macro
            text = text.substring(end + 1);
        }
        if (text.length() > 0) {
            res.add(text);
        }
        return res;
    }

    /**
     * Parses the text and costructs top-level parser
     * of the pattern.
     *
     * @param text pattern text
     * @throws Fault
     *     if unknown macro names were met in the text
     */
    protected void parseMacros(String text) throws Fault {
        ArrayList l = splitPattern(text, knownMacros);
        ArrayList macro_list = new ArrayList();

        for (int i = 0; i < l.size(); i++) {
            Object elem = l.get(i);
            Macro m = null;

            if (elem instanceof Macro) {
                if (i == 0) {
                    // a pattern must not start with a macro
                    throw new Fault("pattern starts with a macro:\n" + text);
                }
                m = (Macro)elem;
                addMacro(m);
            } else {
                String s = (String)elem;
                m = new Macro(s, new StringParser(s));
                m.setValue(s);
            }
            macro_list.add(m);
        }
        parser = new CompositeValueParser(macro_list);
    }

    /**
     * Parses a macro name, taking into account that macro names
     * may have suffixes (.xxx)
     *
     * @param beg  the index of the starting macro name designator character
     *     ({@link #MACRO_CHAR}) in the text
     * @param end 
     *     the index of the ending macro name designator character
     *     ({@link #MACRO_CHAR}) in the text
     * @param text the text
     * @return
     *     macro name
     */
    private static String parseMacroName(int beg, int end, String text)
        throws Fault
    {
        if (beg < 0) {
            return null;
        }
        if (end < 0) {
            end = text.indexOf(' ', beg);
            end = end < 0 ? text.length() : end;
            throw new Fault("macro not terminated: " + text.substring(beg, end));
        }
        String res = text.substring(beg + 1, end);

        if (res.length() == 0) {
            throw new Fault("empty macro name");
        }
        if (!MiscUtils.isAsciiLetter(res.charAt(0))) {
            throw new Fault("bad macro name start: <" + res + ">");
        }
        boolean dot_met = false;

        for (int i = 1; i < res.length(); i++) {
            char ch = res.charAt(i);

            if (ch == '.') {
                if (dot_met) {
                    // only one dot allowed
                    throw new Fault("bad macro name: <" + res + ">");
                }
                dot_met = true;
                continue;
            }

            if (!(MiscUtils.isAsciiLetter(ch) ||
                  MiscUtils.isAsciiDigit(ch) ||
                  ch == '_'))
            {
                throw new Fault("bad macro name: <" + res + ">");
            }
        }
        return res;
    }

    /**
     * Tries to match a portion of a text with this pattern.
     *
     * @param text      the text
     * @param start_pos starting position of the piece of text to be matched
     * @param len       the length if the piece of text to be matched
     * @return
     *     a Match instance if matching succeeded, <code>null</code> otherwise.
     */
    public Match match(String text, int start_pos, int len) {
        text = text.substring(0, start_pos + len);
        Macro m = (Macro)parser.getParser(0);
        String prefix = ((StringParser)m.getValueParser()).getText();

        // cyclically match the pattern prefix (which is not a macro)
        // against the text, each time advancing prefix.length()
        // character positions ahead

        for (; start_pos < text.length(); start_pos += prefix.length()) {
            if ((start_pos = text.indexOf(prefix, start_pos)) < 0) {
                return null;
            }
            //System.out.println("### MATCHING at " + start_pos);
            int end_pos = parser.parseValue(text, start_pos);

            if (end_pos >= 0) {
                return new Match(this, start_pos, end_pos - 1);
            }
        }
        return null;
    }

    /**
     * Prints out this object to a stream.
     *
     * @param out the stream.
     */
    public void dbgPrint(PrintStream out) {
        for (int i = 0; i < parser.getParserCount(); i++) {
            Macro m = (Macro)parser.getParser(i);
            out.println("" + i + ": " + m);
        }
    }
}

