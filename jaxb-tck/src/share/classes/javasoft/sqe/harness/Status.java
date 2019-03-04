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

package javasoft.sqe.harness;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * A class to embody the result of a test: a status-code and a related message.
 *
 * @author Jonathan J Gibbons
 * @version @(#)Status.java	1.23 02/01/03
 * @deprecated
 * @see javasoft.sqe.javatest.Status
 */

public class Status
{
    /**
     * Create a Status that represents the successful outcome of a test.
     */
    public static Status passed(String reason) {
	return new Status(PASSED, reason);
    }

    /**
     * Create a Status that represents the unsuccessful outcome of a test.
     */
    public static Status failed(String reason) {
	return new Status(FAILED, reason);
    }

    /**
     * Create a Status that represents that the test completed, but that further
     * analysis of the output of the test against reference files is required.
     */
    public static Status checkFile(String reason) {
	return new Status(CHECK_FILE, reason);
    }

    /**
     * Create a Status that represents that the test was not run because under
     * the conditions given it was not applicable.
     */
    public static Status notApplicable(String reason) {
	return new Status(NOT_APPLICABLE, reason);
    }

    /**
     * Create a Status that represents that the test has not yet been run
     */
    public static Status notRun(String reason) {
	return new Status(NOT_RUN, reason);
    }

    /**
     * A return code indicating that the test was successful.
     * @see #passed
     * @see #getType
     */
    public static final int PASSED = 0;

    /**
     * A return code indicating that the test failed.
     * @see #failed
     * @see #getType
     */
    public static final int FAILED = 1;

    /**
     * A return code indicating that the test did not itself determine whether it 
     * succeeded or failed;  instead, the output the test generated must be compared 
     * against a known correct version (a `golden file') to determine if it passed or not.
     * @see #checkFile
     * @see #getType
     */
    public static final int CHECK_FILE = 2;

    /**
     * A return code indicating that the test was not run because the test description 
     * was not applicable for the current run of the harness. 
     * For example, this might occur if the test involved native code for one type of 
     * machine and the harness was trying to run it on another.
     * @see #getType
     */
    public static final int NOT_APPLICABLE = 3;

    /**
     * A return code indicating that the test has not yet been run in this context.  
     * (More specifically, no status file has been recorded for this test in the 
     * current work directory.)
     * @see #getType
     */
    public static final int NOT_RUN = 4;

    /**
     * Get a type code indicating the type of Status message this is.
     * @see #PASSED
     * @see #FAILED
     * @see #CHECK_FILE
     * @see #NOT_APPLICABLE
     * @see #NOT_RUN
     */
    public int getType() {
	return type;
    }

    /**
     * Get the message given when the status was created.
     */
    public String getReason() {
	return reason;
    }

    /**
     * Return a new Status object with a possibly augmented reason field
     */
    public Status augment(Status aux) {
	if (aux.reason == null || aux.reason.length() == 0)
	    return this;
	else 
	    return new Status(type, (reason + " [" + aux.reason + "]"));
    }


    /**
     * Write the status to a stream.
     * @see #read
     */
    public void write(PrintStream out) {
	out.println(toString());
    }

    /**
     * Read a status message from a file.
     * @see #write
     */
    public static Status read(DataInputStream in) throws IOException {
	String s = in.readLine();
	for (int i = 0; i < texts.length; i++)
	    if (s.startsWith(texts[i]))
		return new Status(i, s.substring(texts[i].length()).trim());
	return null;
    }

    /**
     * Standard routine.
     */
    public String toString() {
	if (reason == null || reason.length() == 0)
	    return texts[type];	
	else
	    return texts[type] + " " + reason;
    }

    /**
     * Convenience exit() function for the main() of tests to exit in such a 
     * way that the status passes up across process boundaries without losing
     * information (ie exit codes don't give the associated text of the status
     * and return codes when exceptions are thrown could cause unintended 
     * results). <p>
     *
     * An identifying marker is written to the error stream, which the script
     * running the test watches for as the last output before returning, then
     * the standard pickling is used (ie read()/write()) for the actual status.
     *
     * The method does not return.
     */
    public void exit() {
	PrintStream strm = System.err;
	strm.print(marker);
	write(strm);
	strm.flush();
	System.exit(exitCodes[type]);
    }


    //-----internal routines------------------------------------------------------

    public Status(int type, String reason) { 
	this.type = type; 
	this.reason = reason; 
    }

    //----------Data members---------------------------------------------------------

    private int type;
    private String reason;

    private static final String marker = "STATUS:";

    private static String[] texts = {  
	// correspond to PASSED, FAILED, CHECK_FILE, NOT_APPLICABLE, NOT_RUN
	"Passed.", 
        "Failed.", 
	"Completed--check results.",
	"Not applicable.",
	"Not run."
    };

    /**
     * Exit codes used by Status.exit corresponding to
     * PASSED, FAILED, CHECK_FILE, NOT_APPLICABLE, NOT_RUN.
     * The only values that should normally be returned from a test
     * are the first three; the other two values are provided
     * for completeness.
     * <small> Note: The assignment is historical and cannot easily be changed. </small>
     */
    public static final int[] exitCodes = { 95, 97, 96, 98, 99 };
}
