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
 *
 * */

package javasoft.sqe.javatest.lib;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This class should take the hit for all deprecated methods used in this
 * package.  
 *
 * @author Iris A Garcia
 * @version @(#)Deprecated.java	1.6 02/01/03
 */
class Deprecated
{
    /**
     * This method is for use in place of calls to the deprecated constructors
     * for PrintStream().  It is necessary to keep using PrintStreams for
     * java.lang.System.setOut(), java.lang.System.setErr(), or calls to things
     * in sun.tools that have no alternative entry points that do not use
     * PrintStreams.
     *
     * @param out  The output stream to which values and objects will be
     *             printed.
     * @return     A PrintStream.
     */
    static PrintStream createPrintStream(OutputStream out) {
	return new PrintStream(out);
    }
}
