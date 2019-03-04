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

package javasoft.sqe.javatest;

/**
 * A class to embody the result of a test: a status-code and a related message.
 *
 * @author Jonathan J Gibbons
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
    public static Status error(String reason) {
	return new Status(ERROR, reason);
    }

    /**
     * Create a Status that represents that the test was not run because under
     * the conditions given it was not applicable.  This method is retained
     * for backwards compatability only; the resultant object is of FAILED
     * type.
     * @deprecated
     */
    public static Status notApplicable(String reason) {
	return new Status(FAILED, "Not Applicable: " + reason);
    }

    /**
     * Create a Status that represents that the test has not yet been run
     */
    static Status notRun(String reason) {
	return new Status(NOT_RUN, reason);
    }

    /**
     * Return true if the type code of the status is PASSED.
     * @see #passed
     * @see #getType
     * @see #PASSED
     */
    public boolean isPassed() {
	return (type == PASSED);
    }

    /**
     * Return true if the type code of the status is FAILED.
     * @see #failed
     * @see #getType
     * @see #FAILED
     */
    public boolean isFailed() {
	return (type == FAILED);
    }

    /**
     * Return true if the type code of the status is ERROR.
     * @see #error
     * @see #getType
     * @see #ERROR
     */
    public boolean isError() {
	return (type == ERROR);
    }

    /**
     * A return code indicating that the test was executed and was successful.
     * @see #passed
     * @see #getType
     */
    public static final int PASSED = 0;

    /**
     * A return code indicating that the test was executed but the test
     * reported that it failed.
     * @see #failed
     * @see #getType
     */
    public static final int FAILED = 1;

    /**
     * A return code indicating that the test was not run because some error
     * occurred before the test could even be attempted. This is generally
     * a more serious error than FAILED.
     * @see #getType
     */
    public static final int ERROR = 2;

    /**
     * A return code indicating that the test has not yet been run in this context.  
     * (More specifically, no status file has been recorded for this test in the 
     * current work directory.)  This is for the internal use of the harness only.
     * @see #getType
     */
    public static final int NOT_RUN = 3;

    /**
     * Number of states which are predefined as "constants".
     */
    public static final int NUM_STATES = 4;

    /**
     * Get a type code indicating the type of Status message this is.
     * @see #PASSED
     * @see #FAILED
     * @see #ERROR
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
    public Status augment(String aux) {
	if (aux == null || aux.length() == 0)
	    return this;
	else 
	    return new Status(type, (reason + " [" + aux + "]"));
    }

    /**
     * Return a new Status object with a possibly augmented reason field
     */
    public Status augment(Status aux) {
	return (aux == null ? this : augment(aux.reason));
    }

    /**
     * Parse a string-form of a Status.
     * @see #exit
     */
    public static Status parse(String s) {
	try {
	    return new Status(s);
	}
	catch (IllegalArgumentException e) {
	    return null;
	}
    }

    private Status(String s) {
	for (int t = 0; t < texts.length; t++) {
	    if (s.startsWith(texts[t])) {
		int l = texts[t].length();
		String r;
		if (l < s.length()) {
		    if (s.charAt(l) == ' ')
			r = s.substring(l + 1);
		    else
			r = s.substring(l);
		}
		else 
		    r = "";
		type = t;
		reason = r;
		return;
	    }
	}
	throw new IllegalArgumentException();
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
     * Translate the type number to a descriptive string.
     * For example, type 0 corresponds to the "Passed." string.
     *
     * @param typeNum A number between zero and NUM_STATES
     * @return Will be null if the given integer was out of range.
     */
    public static String typeToString(int typeNum) {
	if (typeNum < NUM_STATES)
	    return texts[typeNum];
	else
	    return null;
    }

    /**
     * Convenience exit() function for the main() of tests to exit in such a 
     * way that the status passes up across process boundaries without losing
     * information (ie exit codes don't give the associated text of the status
     * and return codes when exceptions are thrown could cause unintended 
     * results). <p>
     *
     * An identifying marker is written to the error stream, which the script
     * running the test watches for as the last output before returning, 
     * followed by the type and reason
     *
     * The method does not return.  It calls System.exit with a value
     * dependent on the type.
     */
    public void exit() {
	if (System.err != null) {
	    System.err.print(EXIT_PREFIX);
	    System.err.print(texts[type]);
	    System.err.println(reason);
	    System.err.flush();
	}
	System.exit(exitCodes[type]);
    }


    //-----internal routines------------------------------------------------------

    public Status(int type, String reason) { 
	// if we find any bad characters in the reason string (e.g. newline)
	// we rewrite the string replacing all such characters with a space.
	for (int i = 0; i < reason.length(); i++) {
	    if (!isPrintable(reason.charAt(i))) {
		StringBuffer r = new StringBuffer(reason.length());
		for (int j = 0; j < reason.length(); j++) {
		    char c = reason.charAt(j);
		    r.append(isPrintable(c) ? c : ' ');
		}
		reason = r.toString();
		break;
	    }
	}

	this.type = type; 
	this.reason = reason.trim(); 
    }

    private static final boolean isPrintable(char c) {
	return (32 <= c && c < 127);
    }

    //----------Data members---------------------------------------------------------

    private /*final*/ int type;
    private /*final*/ String reason;

    public static final String EXIT_PREFIX = "STATUS:";

    private static String[] texts = {  
	// correspond to PASSED, FAILED, ERROR, NOT_RUN
	"Passed.", 
        "Failed.", 
	"Error.",
	"Not run."
    };

    /**
     * Exit codes used by Status.exit corresponding to
     * PASSED, FAILED, ERROR, NOT_RUN.
     * The only values that should normally be returned from a test
     * are the first three; the other value is provided for completeness.
     * <small> Note: The assignment is historical and cannot easily be changed. </small>
     */
    public static final int[] exitCodes = { 95, 97, 98, 99 };
}
