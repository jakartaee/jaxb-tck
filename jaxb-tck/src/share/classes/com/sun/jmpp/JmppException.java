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

import java.io.PrintStream;

/**
 * Thrown if an error occured during parsing <code>.jmpp</code>
 * file.
 */
public class JmppException extends RuntimeException {
    public final static int UNKNOWN_ERROR = -1;
    public final static int WRAPPED_ERROR = 0;
    public final static int SYSTEM_ERROR = 1;
    public final static int SYNTAX_ERROR = 2;
    public final static int COMPILATION_ERROR = 3;

    protected int kind = -1;
    protected Throwable wrappedException;

    /**
     * Constructs a <code>JmppException</code> with no detail message.
     */
    public JmppException() {
        super();
    }

    public JmppException(Throwable e) {
        kind = WRAPPED_ERROR;
        wrappedException = e;
    }

    /**
     * Constructs a <code>JmppException</code> with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public JmppException(String s) {
        super(s);
    }

    public JmppException(int kind, String s) {
        super(s);
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public String getMessage() {
        switch (kind) {
        case WRAPPED_ERROR:
            String s = (wrappedException == null) ?
                super.getMessage() :
                wrappedException + ": " + wrappedException.getMessage();
            return "An error occurred: " + s;
        case SYSTEM_ERROR:
            return "A system error occurred: " + super.getMessage();
        case SYNTAX_ERROR:
            return "Syntax errors found in jmpp source file:" + " \n" + 
                   super.getMessage();
        case COMPILATION_ERROR:
            return "Compilation of the intermediate program (" +
                   super.getMessage() + ") failed";
        default:
            return super.getMessage();
        }
    }

    public void printStackTrace(PrintStream out) {
        if (kind != WRAPPED_ERROR) {
            super.printStackTrace(out);
        } else {
            out.println(getClass().getName() + ".");
            if (wrappedException == null)
                super.printStackTrace(out);
            else
                wrappedException.printStackTrace(out);
        }
    }

    public void printStackTrace() {
        printStackTrace(System.out);
    }
}
