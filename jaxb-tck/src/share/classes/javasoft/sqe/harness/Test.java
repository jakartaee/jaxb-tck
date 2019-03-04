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
 * */

package javasoft.sqe.harness;

import java.io.PrintStream;

/**
 * This interface is implemented by tests to be run under the test harness
 * of javasoft.sqe.harness. Information about the test is normally contained
 * in the parameters of a special applet in an HTML file that is read by the
 * test harness.
 *
 * A test should also define `main' as follows:
 * <pre>
 * <code>
 * 	public static void main(String[] args) {
 * 	    Test t = new <em>test-class-name</em>();
 * 	    Status s = t.run(args, System.err, System.out);
 * 	    s.exit();
 * 	}
 * </code>
 * </pre>
 * Defining `main' like this means that the test can also be run standalone, 
 * independent of the harness.
 *
 * @author Jonathan J Gibbons
 * @version @(#)Test.java	1.13 02/01/03
 * @deprecated 
 * @see javasoft.sqe.javatest.Test
 */
public interface Test
{
    /**
     * Runs the test embodied by the implementation.
     * @param args 	These are supplied from the `executeArgs'
     *		   	values in the corresponding test description
     *             	and permit an implementation to be used for a variety of tests.
     * @param log  	A stream to which to report messages and errors.
     * @param ref  	A stream to which to write reference output.
     *			The file may subsequently be used to determine if the test 
     *			succeeded by comparing the contents against a golden file.
     */
    public Status run(String[] args, PrintStream log, PrintStream ref);

}
