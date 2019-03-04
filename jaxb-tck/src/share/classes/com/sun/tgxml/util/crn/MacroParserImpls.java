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

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.sun.tgxml.util.MiscUtils;

/**
 * A collection of commonly used macro parsers.
 *
 * @author Konstantin S. Bobrovsky
 */
public interface MacroParserImpls {
    /**
     * Defines a family of parsers which try to match a concrete text.
     */
    public static interface PlainTextParser extends MacroParser {
        /**
         * @return a text which this parser if assumed to parse (match).
         */
        String getText();
    }

    /**
     * Parses a value of the 'Z' SCCS macro.
     */
    MacroParser zParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseZ(s, start_pos);
            }

            public String toString() {
                return "zParser";
            }
        };

    /**
     * Parses a value of the 'M' SCCS macro.
     */
    MacroParser mParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseM(s, start_pos);
            }

            public String toString() {
                return "mParser";
            }
        };

    /**
     * Parses a value of the 'I' SCCS macro.
     */
    MacroParser iParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseI(s, start_pos);
            }

            public String toString() {
                return "iParser";
            }
        };

    /**
     * Parses a value of the 'W' SCCS macro.
     */
    MacroParser wParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseW(s, start_pos);
            }

            public String toString() {
                return "wParser";
            }
        };

    /**
     * Parses a value of the 'E' SCCS macro.
     */
    MacroParser eParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseE(s, start_pos);
            }

            public String toString() {
                return "eParser";
            }
        };

    /**
     * Parses a value of the 'T' SCCS macro.
     */
    MacroParser tParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseT(s, start_pos);
            }

            public String toString() {
                return "tParser";
            }
        };

    /**
     * Parses a four-digit number.
     */
    MacroParser yearParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseYear(s, start_pos);
            }

            public String toString() {
                return "yearParser";
            }
        };

    /**
     * Parses a relative path to the master TCK copyright file,
     * used in index html TCK files.
     */
    MacroParser crnLinkParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseFullCrnLink(s, start_pos);
            }

            public String toString() {
                return "crnLinkParser";
            }
        };

    /**
     * Parses an author name.<br>
     * example0: "John W. Ivanov" <br>
     * example1: "John Ivanov" <br>
     */
    MacroParser authorParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseAuthor(s, start_pos);
            }

            public String toString() {
                return "authorParser";
            }
        };

    /**
     * Parses a comma-separated list of author names.<br>
     * example0: "John W. Ivanov\n" <br>
     * example1: "John Ivanov, Ivan B. Johnson\n" <br>
     */
    MacroParser authorListParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseAuthorList(s, start_pos);
            }

            public String toString() {
                return "authorListParser";
            }
        };

    /**
     * Parses a sequence of space or tab characters.
     */
    MacroParser spacesParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                return MacroParseAlgorithms.parseSpaces(s, start_pos);
            }

            public String toString() {
                return "spacesParser";
            }
        };

    /**
     * Parses 'W' and 'E' sccs macro sequence, separated by
     * spaces or tabs.
     */
    MacroParser scInfoParser = new CompositeValueParser
        (new MacroParser[] {
            wParser,
            spacesParser,
            eParser
        })
    {
        public String toString() {
            return "scInfoParser";
        }
    };
 
    /**
     * Parses arbitrary text till the end of line.
     */
   MacroParser textTillEOLParser = new MacroParser() {
            public int parseValue(String s, int start_pos) {
                if (start_pos >= s.length()) {
                    return -1;
                }
                int ind = MacroParseAlgorithms.indexOfAnyChar(
                    s, "\n\r\f", start_pos
                );
                ind = ind < 0 ? s.length() : ind;
                s = s.substring(start_pos, ind);
                return s.toLowerCase().indexOf("copyright", start_pos) >= 0 ? -1 : ind;
            }

            public String toString() {
                return "textTillEOLParser";
            }
        };

    /**
     * Parses (matches) a concrete string.
     */
    public static class StringParser implements PlainTextParser {
        private String pattern;

        public StringParser(String pattern) {
            this.pattern = pattern;
        }

        public int parseValue(String s, int start_pos) {
            return s.startsWith(pattern, start_pos) ?
                start_pos + pattern.length() :
                -1;
        }

        public String toString() {
            return "StringParser: <" + pattern + ">";
        }

        public String getText() {
            return pattern;
        }
    }

    /**
     * Parses a sequence of macro values.
     */
    public static class CompositeValueParser implements MacroParser {
        private ArrayList values = new ArrayList();
        protected MacroParser[] parsers;

        public CompositeValueParser(MacroParser[] parsers) {
            this.parsers = parsers;
        }

        public CompositeValueParser(ArrayList parsers) {
            MacroParser[] arr = new MacroParser[parsers.size()];
            this.parsers = (MacroParser[])parsers.toArray(arr);
        }

        public MacroParser getParser(int i) {
            return parsers[i];
        }

        public int getParserCount() {
            return parsers == null ? 0 : parsers.length;
        }

        public int parseValue(String s, int start_pos) {
            values.clear();
            return MacroParseAlgorithms.parseMacroSequence(
                s,
                start_pos,
                parsers,
                values
            );
        }

        public String[] getValues() {
            return (String[])values.toArray(new String[values.size()]);
        }

        public String toString() {
            int len = parsers.length;
            String res = "CompositeValueParser: { ";

            for (int i = 0; i < len; i++) {
                res += (i == 0 ? "" : ", ") + parsers[i];
            }
            return res += " }";
        }
    }

    /**
     * Parses a single macro value using alternative parsers.
     * If more than one alternative parser successfully parsed
     * a value, the one which returned the biggest end index of
     * the matched substring is chosen.
     */
    public static class VariantValueParser implements MacroParser {
        private int lastMatchedParserInd;
        private ArrayList parsers;

        public VariantValueParser(ArrayList parsers) {
            this.parsers = (ArrayList)parsers.clone();
        }

        public VariantValueParser(MacroParser[] parsers) {
            this.parsers = new ArrayList();

            for (int i = 0; i < parsers.length; i++) { 
                this.parsers.add(parsers[i]);
            }
        }

        public VariantValueParser(MacroParser p1, MacroParser p2) {
            this(new MacroParser[] { p1, p2 });
        }

        /**
         * @return -1 in case of failure, otherwise - the biggest index returned
         *     by child parsers.
         */
        public int parseValue(String s, int start_index) {
            int max_ind = -1;
            lastMatchedParserInd = -1;

            for (int i = 0; i < parsers.size(); i++) {
                MacroParser parser = (MacroParser)parsers.get(i);
                int ind = parser.parseValue(s, start_index);

                if (ind >= 0) {
                    if (ind < start_index) {
                        throw new IndexOutOfBoundsException();
                    }
                    if (max_ind < ind) {
                        max_ind = ind;
                    }
                    lastMatchedParserInd = i;
                }
            }
            return max_ind;
        }

        public int getLastMatchedParserInd() {
            return lastMatchedParserInd;
        }

        public String toString() {
            String res = "VariantValueParser: { ";

            for (int i = 0; i < parsers.size(); i++) {
                res += (i == 0 ? "" : ", ") + parsers.get(i);
            }
            return res += " }";
        }
    }

    /**
     * Matches (parses) a sequens of space or tab characters
     * o given length.
     */
    public static class CharSequenceParser implements MacroParser {
        private String okChars;
        private int min;
        private int max;

        public CharSequenceParser(String chars) {
            this(chars, 1, -1);
        }

        public CharSequenceParser(String chars, int min, int max) {
            okChars = chars;
            this.min = min;
            this.max = max < 0 ? Integer.MAX_VALUE : max;
        }

        public int parseValue(String s, int start_index) {
            if (start_index >= s.length()) {
                return -1;
            }
            int max_ind = start_index + max;
            int ind = start_index;

            for (; ind < s.length() && ind < max_ind; ind++) {
                if (okChars.indexOf(s.charAt(ind)) < 0) {
                    break;
                }
            }
            return (ind - start_index < min ? -1 : ind);
        }
    }

    /**
     * Cyclically applies the same set of parsers to
     * match (parse) given text.
     */
    public static class CyclicParser implements MacroParser {
        private ArrayList parsers;
        private int matchCount;
        private int min;
        private int max;

        public CyclicParser(ArrayList parsers, int min, int max) {
            setMatchCountRange(min, max);
            this.parsers = (ArrayList)parsers.clone();
        }

        public CyclicParser(MacroParser[] parsers, int min, int max) {
            setMatchCountRange(min, max);
            this.parsers = new ArrayList();

            for (int i = 0; i < parsers.length; i++) { 
                this.parsers.add(parsers[i]);
            }
        }

        public CyclicParser(MacroParser p, int min, int max) {
            this(new MacroParser[] { p }, min, max);
        }

        private void setMatchCountRange(int min, int max) {
            this.min = min;
            this.max = max < 0 ? Integer.MAX_VALUE : max;
        }

        /**
         * @return -1 in case of failure, otherwise - position in the
         *     string, up to which the matching succeded.
         */
        public int parseValue(String s, int start_index) {
            int match_count = 0;

            for (match_count = 0; match_count <= max; match_count++) {
                boolean match_failed = false;
                int ind = start_index;

                for (int i = 0; i < parsers.size(); i++) {
                    int last_ind = ((MacroParser)parsers.get(i)).parseValue(s, ind);

                    if (last_ind < 0) {
                        match_failed = true;
                        break;
                    } else {
                        if (last_ind < ind) {
                            throw new IndexOutOfBoundsException();
                        }
                    }
                    ind = last_ind;
                }
                if (match_failed) {
                    break;
                }
                start_index = ind;
            }
            matchCount = match_count;
            return (match_count >= min ? start_index : -1);
        }

        public int getLastMatchCount() {
            return matchCount;
        }

        public String toString() {
            String res = "CyclicParser: (" + min + ", " + max + ") {\n";

            for (int i = 0; i < parsers.size(); i++) {
                res += (i == 0 ? "" : ", ") + parsers.get(i);
            }
            return res += "\n}";
        }
    }

    /**
     * Parses zero to four repetitions of the ornamental "*\n" strings
     * in a CRN block.
     */
    MacroParser starsParser = new MacroParser() {
            private MacroParser indentParser = new CharSequenceParser(" \t", 0, 4);
            private MacroParser parser = new CyclicParser(
                new MacroParser[] {
                    indentParser,
                    new StringParser("*"),
                    spacesParser,
                    new StringParser("\n")
                },
                0,
                5
            );

            public int parseValue(String s, int start_index) {
                return parser.parseValue(s, start_index);
            }
        };
}

/**
 * Implements author name parsing logic.
 *
 * @author Konstantin S. Bobrovsky
 */
class AuthorScanner {
    static class Fault extends Exception {}
    private String lastToken = null;
    private int prevEndPos;
    private int curEndPos;
    private StringTokenizer t;
    private final static String DELIMS = " \t\n\r\f,";
    private static final String END_TAG = "<__THE_END__>" + AuthorScanner.class;

    int scanAuthor(String s, int start_pos) throws Fault {
        if (start_pos >= s.length()) {
            throw new Fault();
        }
        if (t == null) {
            t = new StringTokenizer(s.substring(start_pos), DELIMS, true);
            curEndPos = start_pos;
        }
        expectName(false);
        expectName(true);

        if (lastToken.endsWith(".") ||
            lastToken.length() == 1 && lastToken.equals(lastToken.toUpperCase()))
        {
            expectName(false);
        }
        return curEndPos;
    }

    int scanAuthorList(String s, int start_pos) throws Fault {
        t = new StringTokenizer(s.substring(start_pos), DELIMS, true);
        curEndPos = start_pos;

        do {
            scanAuthor(s, curEndPos);
        } while (nextToken(true).equals(","));

        return prevEndPos;
    }

    private void next() {
        if (!t.hasMoreTokens()) {
            lastToken = END_TAG;
        } else {
            lastToken = t.nextToken();
            curEndPos += lastToken.length();
        }
    }

    private String nextToken(boolean eof_allowed) throws Fault {
        prevEndPos = curEndPos;

        do {
            next();
        } while (isDelim(lastToken));

        if (eof_allowed && lastToken == END_TAG) {
            throw new Fault();
        } 
        return lastToken;
    }

    private void expectName(boolean may_end_with_dot) throws Fault {
        nextToken(false);
        String pref = lastToken.substring(0, 1);
        String suff = lastToken.substring(1);

        if (!(suff.equals(suff.toLowerCase())) ||
            !(pref.equals(pref.toUpperCase())))
        {
            throw new Fault();
        }
        String tmp = lastToken;

        if (may_end_with_dot && tmp.charAt(tmp.length() - 1) == '.') {
            tmp = tmp.substring(0, tmp.length() - 1);
        }
        if (!MacroParseAlgorithms.allAsciiLetters(tmp) || tmp.length() <= 0) {
            throw new Fault();
        }
    }

    private static boolean isDelim(String s) {
        return s.length() == 1 && Character.isWhitespace(s.charAt(0));
    }
}

/**
 * A collection of macro parsing algorithms.
 *
 * @author Konstantin S. Bobrovsky
 */
class MacroParseAlgorithms {
    private static class Fault extends Exception {}

    static int parseFileName(String s, int start_pos) {
        if (start_pos >= s.length()) {
            return -1;
        }
        String s1 = s.substring(start_pos);

        if (s1.trim().length() == 0) {
            return -1;
        }
        int i = indexOfAnyChar(s1, DELIMS);

        if (i == 0) {
            return -1;
        }
        return i < 0 ? s.length() : start_pos + i;
    }

    static int parseZ(String s, int start_pos) {
        // example: '@(#)'
        if (start_pos >= s.length()) {
            return -1;
        }
        final String TOKEN = "@(#)";

        if (s.startsWith(TOKEN, start_pos)) {
            return start_pos + TOKEN.length();
        }
        return -1;
    }

    static int parseM(String s, int start_pos) {
        return parseFileName(s, start_pos);
    }

    private static class VersionScanner {
        private String lastToken;
        private int curEndPos;
        private StringTokenizer t;

        int scan(String s, int start_pos) throws Fault {
            curEndPos = start_pos;
            t = new StringTokenizer(s.substring(start_pos), " \t\n\r\f.", true);
            expectNum();
            expectDot();
            expectNum();

            boolean had_tokens = false;

            while (t.hasMoreTokens()) {
                if (!nextToken().equals(".")) {
                    had_tokens = true;
                    break;
                }
                expectNum();
            }
            return had_tokens ? curEndPos - lastToken.length() : curEndPos;
        }

        private String nextToken() {
            lastToken = t.nextToken();
            curEndPos += lastToken.length();
            return lastToken;
        }

        private void expectDot() throws Fault {
            if (!".".equals(nextToken())) {
                throw new Fault();
            }
        }

        private void expectNum() throws Fault {
            if (!allAsciiDigits(nextToken())) {
                throw new Fault();
            }
        }
    }

    static int parseI(String s, int start_pos) {
        // example: '1.14.1.2'
        try {
            return new VersionScanner().scan(s, start_pos);
        } catch (Fault f) {
            return -1;
        }
    }

    static int parseW(String s, int start_pos) {
        // example: '@(#)jckjni.h	1.14'
        int ind = parseZ(s, start_pos);

        if (ind < 0) {
            return -1;
        }
        if ((ind = parseM(s, ind)) < 0) {
            return -1;
        }
        if (ind >= s.length()) {
            return -1;
        }
        int ind1 = parseSpaces(s, ind);

        if (ind1 < 0 || ind1 - ind > 8) {
            return -1;
        }
        return parseI(s, ind1);
    }

    private static int parseNumberTripple(String s, int start_pos, char delim) {
        final int NUM_LEN = 2;
        final int DELIM_COUNT = 2;
        final int TOKEN_LEN = (NUM_LEN + 1)*DELIM_COUNT + DELIM_COUNT;
        int cur = start_pos;
        int len = s.length();

        if (start_pos + TOKEN_LEN > len) {
            return -1;
        }
        for (int i = 0; i < DELIM_COUNT; i++) {
            String num = s.substring(cur, cur + NUM_LEN);
            
            if (!allAsciiDigits(num)) {
                return -1;
            }
            cur += NUM_LEN;

            if (s.charAt(cur) != delim) {
                return -1;
            }
            cur++;
        }
        String num = s.substring(cur, cur + NUM_LEN);
        cur += NUM_LEN;
        boolean term_met =
            cur == len ||
            !MiscUtils.isAsciiDigit(s.charAt(cur)) &&
            s.charAt(cur) != delim;

        if (allAsciiDigits(num) && term_met) {
            return cur;
        } else {
            return -1;
        }
    }

    static int parseE(String s, int start_pos) {
        // example: 03/09/22
        return parseNumberTripple(s, start_pos, '/');
    }

    static int parseT(String s, int start_pos) {
        // example: 15:10:01
        return parseNumberTripple(s, start_pos, ':');
    }

    static int parseYear(String s, int start_pos) {
        // example: '2060'
        if (start_pos >= s.length()) {
            return -1;
        }
        final int DIGIT_COUNT = 4;
        int i = start_pos;

        for (; i < start_pos + DIGIT_COUNT && i < s.length(); i++) {
            if (!MiscUtils.isAsciiDigit(s.charAt(i))) {
                return -1;
            }
        }
        if (i >= s.length()) {
            return i;
        }
        return Character.isWhitespace(s.charAt(i)) ? i : -1;
    }

    static int parseChar(String s, int start_pos, char ch) {
        if (start_pos >= s.length()) {
            return -1;
        }
        return s.charAt(start_pos) == ch ? ++start_pos : -1;
    }

    static int parseSpaces(String s, int start_pos) {
        if (start_pos >= s.length()) {
            return -1;
        }
        for (; start_pos < s.length(); start_pos++) {
            char ch = s.charAt(start_pos);

            if (!Character.isWhitespace(ch) || ch == '\n' || ch == '\r') {
                break;
            }
        }
        return start_pos;
    }

    static int parseMacroSequence(String s,
                                  int start_pos,
                                  MacroParser[] parsers,
                                  ArrayList res)
    {
        int cur_pos = start_pos;

        for (int i = 0; i < parsers.length; i++) {
            if ((cur_pos = parsers[i].parseValue(s, start_pos)) < 0) {
                return -1;
            }
            if (res != null) {
                res.add(s.substring(start_pos, cur_pos));
            }
            start_pos = cur_pos;
        }
        return start_pos;
    }

    static int parseFullCrnLink(String s, int start_pos) {
        // example: '../../../filename.html'
        if (start_pos >= s.length()) {
            return -1;
        }
        final String UP = "../";
        String s1 = s.substring(start_pos);

        while (s1.startsWith(UP)) {
            start_pos += UP.length();
            s1 = s1.substring(UP.length());
        }
        return parseFileName(s, start_pos);
    }

    static int parseAuthor(String s, int start_pos) {
        try {
            return new AuthorScanner().scanAuthor(s, start_pos);
        } catch (AuthorScanner.Fault f) {
            return -1;
        }
    }

    static int parseAuthorList(String s, int start_pos) {
        try {
            return new AuthorScanner().scanAuthorList(s, start_pos);
        } catch (AuthorScanner.Fault f) {
            return -1;
        }
    }

    private final static String DELIMS = " \t\n\r()<>{}[]^&?@\"";

    static boolean allAsciiLetters(String s) {
        return allAsciiLetters(s, 0, s.length() - 1);
    }

    static boolean allAsciiDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!MiscUtils.isAsciiDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean allAsciiLetters(String s, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (!MiscUtils.isAsciiLetter(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFilenamePart(char ch) {
        if (ch < '!' /* 33 */ || ch > '~' /*126*/) {
            return false;
        }
        return DELIMS.indexOf(ch) < 0;
    }

    public static int indexOfAnyChar(String s, String chars) {
        return indexOfAnyChar(s, chars, 0);
    }

    public static int indexOfAnyChar(String s, String chars, int start_pos) {
        int min_ind = Integer.MAX_VALUE;

        for (int i = 0; i < chars.length(); i++) {
            int ind = s.indexOf(chars.charAt(i), start_pos);

            if (ind >= 0 && ind < min_ind) {
                min_ind = ind;
            }
        }
        return min_ind == Integer.MAX_VALUE ? -1 : min_ind;
    }
}
