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

import javasoft.sqe.javatest.Status;

/**
 * This is a modification of <code>ProcessCommand</code> suitable
 * for tests that use the "simple" invocation protocol. When run
 * in a separate process, these tests just use the exit code
 * to identify the success or otherwise of the test. Values are chosen
 * to minimise the chance of failures within the process machinery
 * or the remote JVM accidentally using these same values.
 */
public class ExecSimpTestOtherJVMCmd extends ProcessCommand
{
    /**
     * Generate a status for the command, based upon the command's exit code.
     * @param exitCode		The exit code from the command that was executed.
     *				<table>
     *				<tr><td>95    <td>passed
     *				<tr><td>96    <td>compare the output written to the 
     *						reference stream against a reference 
     *						file
     *				<tr><td>97    <td>failed (inside test)
     *				<tr><td>other <td>other failure
     *				</table>
     * @param logStatus		This parameter is ignored.
     * @return			A status object appropriate to the exitCode.
     */ 
    public Status getStatus(int exitCode, Status logStatus) {
	// logStatus will always be null for these tests
	switch (exitCode) {
	case 95:
	    return passed;
	case 97:
	    return failed;
	default:
	    return Status.failed("unexpected exit code: exit code " + exitCode);
	}
    }

    private static final Status passed = Status.passed("OK");
    private static final Status failed = Status.failed("test failed");
}
