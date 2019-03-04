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

/**
 * Indicates an error encountered by JmppReader during parsing a jmpp source
 * file.
 * @see JmppReader
 */
public class BadSyntaxException extends RuntimeException {
    /**
     * A number of jmpp source file line at which the error encountered.
     */
    protected int lineNum = -1;

    /**
     * Jmpp source file line containing the error.
     */
    protected String line;

    /**
     * Creates new BadSyntaxException instance with the specified error message.
     * @param s the error message
     */
    public BadSyntaxException(String s) {
        super(s);
    }

    /**
     * Creates new BadSyntaxException instance with the specified error message
     * and line number.
     * @param s the error message
     * @param line_num the line number
     */
    public BadSyntaxException(String s, int line_num) {
        super(s);
        lineNum = line_num;
    }

    /**
     * Creates new BadSyntaxException instance with the specified error message,
     * line number and jmpp source line.
     * @param s the error message
     * @param line_num the line number
     * @param line the line itself
     */
    public BadSyntaxException(String s, int line_num, String line) {
        this(s, line_num);
        this.line = line;
    }

    /**
     * @return the number of line at which this error was encountered
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * @return the source code line containing this error or null if it is
     *         unknown
     */
    public String getLine() {
        return line;
    }
}

