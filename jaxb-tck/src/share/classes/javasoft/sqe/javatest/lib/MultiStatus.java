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

import java.io.PrintWriter;
import javasoft.sqe.javatest.Status;

/**
 * When executing multiple test cases in the same test class, it is usually 
 * easier for each test case to return a Status object representing whether that 
 * individual test case passed or failed.  However, combining those individual
 * Status objects into one Status object to represent the overall Status of the
 * tests executed can be difficult.  This test library is designed to solve the 
 * problem of generating an aggregate or overall Status from multiple Status 
 * objects.  The static method overallStatus is designed to take an array of 
 * Status objects from individual test cases and generate a Status object that 
 * correctly reflects the overall status of all the individual the test 
 * cases executed.
 *
 * <P>The rule for how MultiStatus calculates the overall Status of an array of 
 * Status objects is based on the following precedence:
 * <BLOCKQUOTE>
 * If any of the test cases return a Status.FAILED, then the overall status is 
 * Status.FAILED.<BR>
 * If all test cases return Status.PASSED, then the overall status is 
 * Status.PASSED.<BR>
 * If at least one test case returns either a null Status or some other Status, 
 * the overall status is Status.FAILED.
 * </BLOCKQUOTE>
 *
 * <P>For an example of how to use this library see the UmbrellaTest library or the 
 * JCK test case: <TT>tests/api/java_lang/Double/SerializeTests.html</TT>.
 *
 * @author Kevin A. Smith
 * @author Jonathan J. Gibbons
 */
public class MultiStatus {

    /**
     * Default constructor
     */
    public MultiStatus() {
    }

    /**
     *
     * @param out    A stream to which the report the outcome of the tests.
     *			 If the stream is null, no reporting is done.
     */
    public MultiStatus(PrintWriter out) {
	this.out = out;
    }

    /**
     * Get the number of tests that have been added.
     */
    public int getTestCount() {
	return iTestCases;
    }

    /** 
     * Add another test into the set for consideration.
     *
     * @param testID A name for this test case
     * @param status The outcome of this test case
     */
    public void add(String testID, Status status) {
	if (out != null) {
	    out.println(testID + ": " + status);
	}
	
	++iTestCases;
	
	if (status != null) {
	    switch (status.getType()) {
	    case Status.PASSED:
		++iPassed;
		return;
		
	    case Status.FAILED:
		if (iFail == 0 && iBad == 0) {
		    firstTest = testID;
		}
		++iFail;
		return;
		
	    default:
	    }
	}

	if (iBad == 0) {
	    firstTest = testID;
	}
	++iBad;
    }
    
    /**
     * Get the aggregate outcome of all the outcomes passed to "add".
     */
    public Status getStatus() {

	// If stream not null, flush it
	if( out != null ) {
	    out.flush();
	}

	String summary;
	if (iTestCases == 0)
	    summary = "No tests cases found (or all test cases excluded.)";
	else {
	    summary = "test cases: " + iTestCases;
	    if (iPassed > 0) {
		if (iPassed == iTestCases)
		    summary += "; all passed";
		else
		    summary += "; passed: " + iPassed;
	    }

	    if (iFail > 0) {
		if (iFail == iTestCases)
		    summary += "; all failed";
		else
		    summary += "; failed: " + iFail;
	    }
		
	    if (iBad > 0) {
		if (iBad == iTestCases)
		    summary += "; all have bad status";
		else
		    summary += "; bad status: " + iBad;
	    }
	}

	/* Return a status object that reflects the aggregate of the various test cases. */

        /* At least one test case was bad */
	if (iBad > 0) {
	    return Status.failed(summary + "; first bad test case found: " + firstTest);
	}
        /* At least one test case failed */
	else if (iFail > 0) {
	    return Status.failed(summary + "; first test case failure: " + firstTest);
	}
	/* All test cases passed */
	else {
	    return Status.passed(summary);
	}
    }


    /**
     * Generates a Status object that reflects an array of Status objects.
     * Uses the algorithm above to generate an overall status from an array of 
     * Status objects.  This method prints out the individual Status values from 
     * each test case to the PrintWriter supplied.  If the PrintWriter is null,
     * no output is generated.
     *
     * @param testIDs an array of names used to identify the individual test cases.
     * @param status an array of Status objects giving the outcomes of the individual test cases.
     * @param out a PrintWriter that can be used to output the individual test case
     *            status values. If null, no output is generated.	
     * @return the aggregate status of the array of Status objects.
     */
    public static Status overallStatus(String testIDs[], Status status[], PrintWriter out) {

	/* Check the number of tests against the number of statuses */
	if( testIDs.length != status.length ) {
	    return Status.failed( "mismatched array sizes; test cases: " + testIDs.length +
				  " statuses: " + status.length );
	}

        /* Loop through status objects, check types,
         * increment appropriate counters, and identify the
         * first test that should be listed in the aggregate status.
         */
	
	MultiStatus ms = new MultiStatus(out);
        for( int i = 0; i < status.length; ++i ) {
	    ms.add(testIDs[i], status[i]);
	}

	return ms.getStatus();
    }


    /**
     * Generates a Status object that reflects an array of Status objects.
     * Uses the algorithm above to generate an overall status from an array of 
     * Status objects.  This method does not output any information
     *
     * @param testIDs an array of names used to identify the individual test cases.
     * @param status an array of Status objects giving the outcomes of the individual test cases.
     * @return overall status of the specified array of Status objects.
     */
    public static Status overallStatus(String testIDs[], Status status[]) {
	return MultiStatus.overallStatus(testIDs, status, null);
    }

    // These values accumulate the aggregate outcome, without requiring
    // the individual outcomes be stored
    private int iTestCases = 0;
    private int iPassed = 0;
    private int iFail = 0;
    private int iBad = 0;
    private String firstTest = "";

    private PrintWriter out = null;
}

