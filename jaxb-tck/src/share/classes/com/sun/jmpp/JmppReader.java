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

package com.sun.jmpp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**  
 * Macro preprocessor reader to read jmpp sources with conversion into 
 * Java code. That output Java code is to have further compilation 
 * and execution in order to generate final output of the 
 * preprocessor.<br>
 * <br>
 * <b>Brief syntax description:</b><br><br>
 * All text started with <code>#</code> character is further called 
 * <em>meta-text</em>, all other text is called <em>object-level 
 * text</em>.<br>
 * All meta-text go into intermediate Java program, which generates 
 * final output.<br>
 * All object-level text goes into the final output with all contained 
 * macros expanded.<br><br>
 * Whatever enclosed in double quotes object-level text of input file 
 * is called string context, everything else is called text context.<br>
 * Macro substitution syntax differs in both contexts the following 
 * way:<br>
 * in text context, macro is marked with @ symbol and is substituted 
 * as is.<br>
 * in string context, macro is marked with sequence \@ and substituted 
 * with dynamic screening of double quotes and backslashes with 
 * backslashes. This is implemented in such a manner that such macros 
 * are expanded to call of method of class <tt>JmppLib</tt> called 
 * <tt>screenString()</tt>.<br><br>
 * Macro syntax is: after <code>\@</code> or <code>@</code> one may 
 * use literal or any Java expression.<br>
 * If Java expression follows, it must be wrapped in round brackets 
 * '<code>(</code>' and '<code>)</code>'.<br>
 * No wrapping is allowed for simple identifiers or method invocations 
 * (so-called parameterized macros).<br>
 * Literal syntax is: one may write <code>@-"-</code> and this will 
 * be expanded to <code>"</code> in final output.<br>
 * It is possible to use as a delimiter (instead of <code>-</code> 
 * sign here) any character that is neither left round bracket 
 * '<code>(</code>' symbol, nor a character which can serve as a first
 * character for regular Java identifier name.<br><br>
 * <br>
 *
 * @author Oleg V. Ulyankin
 * @version @(#)JmppReader.java	1.16 04/04/23
 */

public class JmppReader extends BufferedReader {

    static final char macroToken = '@';
    static final char macroLeft = '(';
    static final char macroRite = ')';
    static final String commentToken = "##";
    static final String noWrapToken = "#";
    static final String openingMacro = "\"+";
    static final String closingMacro = "+\"";
    static final String wrapLeft = "L(\"";
    static final String wrapRite = "\");";
    static final String wrapS        = "\"";
    static final String wrapTabS     = "\t\"";
    static final String wrapRiteSMid = "\"+";
    static final String stringContextScreenMethod =
        JmppLib.stringContextScreenMethod;
    static final String errHead = "JmppReader: ";
    static final String msgNoQuote = "no closing double quote found for" +
                                     " object-level text";
    static final String msgNoMacroEnd = "no closing token found for '" +
                                        macroToken + "'";
    private String nextLine = null;
    private int lineNumber = 0;
    private boolean stringContext = false;
    private boolean wrapAsString = false;
    private ParsedMacro macro;
    private PrintWriter log = new PrintWriter(System.err, true);
    private boolean oldTextBlockMacro = false;
    
    public void processOldMacroInTextBlocks(boolean state) {
        oldTextBlockMacro = state;
    }
    
    public JmppReader(Reader in) {
        super(in);
    }
    
    protected static String returnLine(StringBuffer s) {
        return (s.append("\n").toString());
    }
    
    /**
     *  Process line of non-meta code from the jmpp source
     */
    protected StringBuffer screenQuotesAndBackslashesAndParseMacros(String line)
        throws BadSyntaxException {

        StringBuffer newString = new StringBuffer(line.length() + 100);
        int lineLen = line.length();
        int i = 0;
        
        if (macro != null) {
            macro.resumeParsing(line);
			
            newString.append((Object) macro.body);
            i = macro.length;
            
            if (macro.closed) {
                if (stringContext && !macro.wereBrackets)
                    newString.append(')');
                newString.append(closingMacro);
                macro = null;
            } 
            else if (i < lineLen) { // assert
                String msg = "INTERNAL: !macro.closed && chars left";
                throw new BadSyntaxException(msg, lineNumber, line);
            }
        }
        
        for (; i < lineLen; i++) {
            char c = line.charAt(i);
            if (c == macroToken && stringContext) {
                newString.append(c);
                continue;
            }
            switch (c) {
            case '"':
                stringContext=!stringContext;
                newString.append("\\\"");
                continue;
            case '\\':
                if (++i < lineLen) {
                    c = line.charAt(i);
                    // escaped " do not switch stringContext
                    if (c == '\\' || c == '"') {
                        newString.append("\\\\\\").append(c);
                        continue;
                    }
                }
                if (c != macroToken) {
                    newString.append("\\\\");
                    i--;
                    continue;
                }
                
                // Use \@ out of stringContext in text blocks (wrapAsString) 
                // as macroToken for backward compatibility
                if (stringContext || (wrapAsString && oldTextBlockMacro))
                    ; // do nothing & go to  case macroToken:
                else {
                    newString.append(macroToken); // replace \@ with @ symbol
                    continue;
                }
                // no break in order to fall thru to parse a macro in string
                // context
            case macroToken:
                try {
                    macro = new ParsedMacro(line, i);
                } catch (BadSyntaxException e) {
                    String msg = e.getMessage();
                    throw new BadSyntaxException(msg, lineNumber, line);
                }
                
                i += macro.length; // points to last processed char
                if (macro.wasLiteral) {
                    newString.append((Object) macro.body);
                    macro = null;
                    continue;
                }
                newString.append(openingMacro);
                if (stringContext) {
                    newString.append(stringContextScreenMethod);
                    if (!macro.wereBrackets)
                        newString.append('(');
                }
                
                newString.append((Object) macro.body);
                
                if (macro.closed) {
                    if (stringContext && !macro.wereBrackets)
                        newString.append(')');
                    newString.append(closingMacro);
                    macro = null;
                } 
                continue;
            }
            newString.append(c);
        }
        return newString;
    }

    protected boolean foundStartOfTextBlock(String line, int i) {
        boolean sContext = false;
        int lineLen = line.length();

        for (; i < lineLen; i++) {
            char c = line.charAt(i);
            if (c == '\\')
                i++;
            else if (c == '"')
                sContext = !sContext;
        }
        return sContext;
    }

    protected int findEndOfTextBlock(String line) throws BadSyntaxException {
        int lineLen = line.length();

        for (int i = 1; i < lineLen; i++) {
            char c = line.charAt(i);
            if (c == '\\')
                i++;
            else if (c == '"')
                return i;
        }
        throw new BadSyntaxException(msgNoQuote, lineNumber, line);
    }

    /**
     *	Read a line of text, filtering it with preprocessor rules
     *	@return	filtered string
     *	@exception  IOException  If an I/O error occurs
     */
    public String readLine() throws IOException {
        String line = "";
        while (nextLine != null || (line = super.readLine()) != null) {
            lineNumber++;
            
            if (nextLine != null) {
                line = nextLine;
                nextLine = null;
            }
            // check for comment token
            if (line.startsWith(commentToken))
                continue;
            
            StringBuffer buf;

            if (line.startsWith(noWrapToken)) {	// is a line of metacode
                stringContext = false;
                if (macro != null)
                    throw new BadSyntaxException(msgNoMacroEnd,
                                                 lineNumber,
                                                 line);
                buf = new StringBuffer(line.length() + 5);
                buf.append(line).deleteCharAt(0);
                int pos = 0;
                if (wrapAsString) {
                    wrapAsString = false;
                    pos = findEndOfTextBlock(line);
                    buf.insert(0, wrapS);
                }
                if (foundStartOfTextBlock(line, pos + 1)) {
                    wrapAsString = true;
                    buf.append(wrapRiteSMid);
                }
                return returnLine(buf);
            }
            boolean wasMacro = macro != null;
            buf = screenQuotesAndBackslashesAndParseMacros(line);
            boolean isMacro  = macro != null;
            
            if (!wrapAsString) {
                if (!wasMacro)
                    buf.insert(0, wrapLeft);
                if (!isMacro)
                    buf.append(wrapRite);
                return returnLine(buf);
            }
            // wrap strings in TextBlock
            if (line.startsWith("\t")) 
                buf.deleteCharAt(0);
            if ( (nextLine = super.readLine()) == null )
                throw new BadSyntaxException(msgNoQuote, lineNumber, line);
            
            if (!wasMacro)
                buf.insert(0, wrapTabS);
            if (!isMacro) {
                 // next line lays in this TextBlock too
                if (!nextLine.startsWith(noWrapToken))
                    if (line.endsWith("\\")) {
                        int l = buf.length();
                        // two last chars : escaped \ in buf
                        buf.delete(l - 2, l);
                    } else
                        buf.append("\\n");
                buf.append(wrapRiteSMid);
            }
            return returnLine(buf);
        }
        if (macro != null)
            throw new BadSyntaxException(msgNoMacroEnd, lineNumber, line);
        return null;
    }

    public static void main (String[] argv) {
        while (argv != null && argv.length > 0 && argv.length < 3) {
            try {
                String tname = argv[0];
                boolean bc = tname.equals("-bc");
                if (bc) {
                    if (argv.length == 1)
                        break;	// go to usage
                    tname = argv[1];
                }
                JmppReader p = new JmppReader(new FileReader(tname));
                p.processOldMacroInTextBlocks(bc);
                String nm = tname + ".java";
                PrintWriter out = new PrintWriter(new FileWriter(nm), true);
                String s;
                while ((s = p.readLine()) != null)
                    out.print(s);
                out.close();
                return;
            } catch(Throwable e) {
                System.err.println(errHead + "transformation failure: " + e);
                return;
            }
        }
        // usage :
        String usage =
            "Wrong usage. Usage:\n" +
            "\njava com.sun.jmpp.JmppReader [-bc] file.jmpp\n" +
            "\t-bc\t treat '\\@' in text block as macro start symbol";

        System.err.println(usage);
    }

    public void setLog(PrintWriter log) {
        this.log = log;
    }

    public PrintWriter getLog() {
        return log;
    }
}

/**
 * Class to parse all kinds of macro.
 * Used in screenQuotesAndBackslashesAndParseMacros()
 */
class ParsedMacro {
	static final char macroLeft = JmppReader.macroLeft;
	static final char macroRite = JmppReader.macroRite;

	boolean wasLiteral = false;
	boolean wereBrackets;
	boolean closed;
	private int depth = 0;

	int length; // length of the macro construct without '@' sign
	StringBuffer body = new StringBuffer(40);

    /**
     * Parse a macro or a literal, starting at the given position within the
     * given string.
     * @param s	string containing the macro to extract
     * @param i	position of the macroToken
     * @return		extracted macro
     * @exception	BadSyntaxException on syntax error occurred
     */

    ParsedMacro(String s, int i) throws BadSyntaxException {
        int sLen = s.length();
        int k = i+1;
        if (k >= sLen)
            throw new BadSyntaxException("no macro body found");
        char ch = s.charAt(k);

        closed = true;
        wereBrackets = ch == macroLeft;
        if (!wereBrackets) {
            if (!Character.isJavaIdentifierStart(ch)) {	// it's macro literal.
                char delimiter = ch;
                i += 2;	// i == index of char after opening delimiter
                ch = s.charAt(i);
                if (ch == '"' || ch == '\\')
                    body.append('\\');
                k = s.indexOf(delimiter, i); // k == index of closing delimiter
                if (k == -1) {
                    String msg = "no closing delimiter '" + delimiter +
                                 "' is found for literal";
                    throw new BadSyntaxException(msg);
                }
                body.append(s.substring(i, k));
                length = k - i + 2;	// length of the macro without '@' sign
                wasLiteral = true;
                return;
            }
            while (++k < sLen && Character.isJavaIdentifierPart(s.charAt(k)));
            body.append(s.substring(++i, k));
            length = k - i;	// length of the macro name without '@' sign

            if (k == sLen || s.charAt(k) != macroLeft)
                return;		// it's a simple macro variable
            i = k-1;
        }
        // length is inited with 0 if wereBrackets
        closed = false;
        depth = 0;
        appendTillClosingToken(s, i);
    }


    void resumeParsing(String line) throws BadSyntaxException {
        body = new StringBuffer(line.length());
        length = 0;
        appendTillClosingToken(line, -1);
    }

    // i points to char before macro body
    void appendTillClosingToken(String line, int i) throws BadSyntaxException {
        boolean sContext = false;		// flag : in string
        int lineLen = line.length();

        while (++i < lineLen) {
            char ch = line.charAt(i);
            body.append(ch);
            length++;

            if (ch == '\\' && ++i < lineLen) {
                body.append(line.charAt(i));
                length++;
                continue;
            }
            if (ch == '"') {
                sContext =! sContext;
            }
            else if (!sContext) {
                if (ch == macroLeft)
                    depth++;
                else if (ch == macroRite && --depth == 0) {
                    closed = true;
                    return;
                }
            }
        }
        if (sContext)
            throw new BadSyntaxException("no closing double quote found");
    }
}
