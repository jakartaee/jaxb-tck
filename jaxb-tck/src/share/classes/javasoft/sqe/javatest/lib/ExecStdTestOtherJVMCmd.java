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

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

import com.sun.javatest.Command;
import com.sun.javatest.Status;

/**
 * This is a modification of <code>ProcessCommand</code> suitable
 * for executing standard tests in an separate JVM.  When run in a
 * separate process, these tests report their exit status by calling
 * <code>status.exit()</code>.  
 **/
public class ExecStdTestOtherJVMCmd extends ProcessCommand
{

    /**
     *	Generate a status for the command, based upon the command's exit code
     *  and a status that may have been passed from the command by using 
     *  <code>status.exit()</code>.
     *
     *  @param exitCode		The exit code from the command that was executed.
     *  @param logStatus	If the command that was executed was a test program
     *				and exited by calling <code>status.exit()</code>,
     *				then logStatus will be set to `status'.  Otherwise,
     *				it will be null.  The value of the status is passed
     *				from the command by writing it as the last line to
     *				stdout before exiting the process.   If it is not
     *				received as the last line, the value will be lost.
     *  @return			If <code>logStatus</code> is not null, it will
     *				be used as the result; this will normally correspond
     *				to the status for which the test called 
     *				<code>status.exit();</code>. 
     *				<p> If <code>logStatus</code> is null, that means
     *				that for some reason the test did not successfully
     *				call <code>status.exit()</code> and the test is
     *				deemed to have failed. If the exit code is zero,
     *				a likely possibility is that the test raised an
     *				exception which caused the JVM to dump the stack
     *				and exit. In this case, the result is
     *				<code>Status.failed("exit without status, exception assumed")</code>.
     *				In other cases, the result is simply
     *				<code>Status.failed("exit code" + exitCode)</code>.
     *			
     **/
    protected Status getStatus(int exitCode, Status logStatus) {
	if (logStatus != null)
	    return logStatus;
	else if (exitCode == 0)
	    return Status.failed("exit without status, exception assumed");
	else
	    return Status.failed("exit code " + exitCode);
    }
}
