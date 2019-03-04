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
import java.util.Vector;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.Test;

/**
 * Test case library that executes multiple test classes.
 * This test case is used to group existing test classes together into
 * one large overall test case.  You can think of it as acting
 * as a collection, or <EM>umbrella</EM> for existing test classes.
 *
 * <P>You will need to be aware of the following Test Description
 * fields as you create the HTML documentation for an Umbrella
 * test class:
 * <UL>
 * <LI><STRONG>executeClass</STRONG>
 * <LI><STRONG>source</STRONG>
 * <LI><STRONG>executeArgs</STRONG>
 * </UL>
 *
 * <P>The value for the <STRONG>executeClass</STRONG> Test Description 
 * will always be <EM>javasoft.sqe.tests.lib.UmbrellaTest</EM>.  It is
 * this class that will execute the individual test classes you specify.
 *
 * <P>The <STRONG>source</STRONG> Test Description field should list
 * <STRONG>ALL</STRONG> of the source files that will be needed by the
 * tests to be executed by Umbrella Test, except the Umbrella Test 
 * source code file itself.  That file should have already been compiled 
 * when you received the JCK.
 *
 * <P>The <STRONG>executeArgs</STRONG> field
 * <DL>
 * <DT><B>-TestClasses</B>
 * <DD>A white space separated list of execute class names.  To make this
 *     simpler, you can use the <STRONG>-PackageName</STRONG> argument to
 *     specify a common package name for the test classes.
 *
 * <DT><B>-PackageName</B> <EM>optional</EM>
 * <DD>The common package name of all of the test classes.  This is
 *     optional, but very useful (do you really want to type 
 *     "javasoft.sqe.tests.api.java.lang.Object" ten times?).
 *
 * </DL>
 *
 * The first execute argument that follows the list of test classes must start
 * with a <EM>-</EM>, otherwise it will also be added to the list of 
 * test classes.  All other values in the executeArgs field are passed to 
 * <STRONG>ALL</STRONG> of the individual tests that are being executed.
 *
 * @see javasoft.sqe.javatest.Test
 *
 * @author Kevin A. Smith
 * @version 1.13
 */
public class UmbrellaTest implements Test {

	/**
	 * Array of class names to be executed.
	 * Each class named in this array must implement javasoft.sqe.javatest.Test
	 * This variable is assigned in the init method.
	 *
	 * @see #init
	 */
	protected String testClasses[] = null;

	/**
	 * Array of additional execute arguments.
	 * Array of execute arguments that were not used to configure
	 * Umbrella Test.  It is assumed that any execute argument
	 * that is not needed by Umbrella Test is needed by <STRONG>ALL</STRONG>
	 * test classes that Umbrella Test executes.  This variable is assigned
	 * in the init method.
	 *
	 * @see #init
	 */
	protected String executeArgs[] = new String[0];

	/**
	 * Output to be logged to result file.  
	 * Output to this PrintWriter is not used during golden file comparison.
	 * Also used to output the Status from each individual test case.
	 */
    protected PrintWriter log;

	/**
	 * Output that can be used as reference.
	 * Output to this PrintWriter is used during golden file comparison.
	 */
    protected PrintWriter ref;


	/**
	 * Default constructor
	 */
	public UmbrellaTest( ) {}

	/**
	 * Other JVM test interface
	 * main method called when this test is run in a separate JVM.
	 *
	 * @param argv execute arguments passed in from the command line
	 *             
	 */
	public static void main( String argv[] ) {
		UmbrellaTest test = new UmbrellaTest( );
		Status status = test.run( argv, new PrintWriter(System.err), new PrintWriter(System.out) );
		status.exit( );
	}

	/**
	 * Same JVM test interface.
	 * Implementation of Test interface.  Called by either main or by JavaTest
	 * Harness to execute the test.
	 *
	 * @see javasoft.sqe.javatest.Status
	 *
	 * @param argv Execute arguments passed in from either the 
	 *             command line or the execution harness.
	 * @param log Output stream for general messages from the tests.
	 *            Is assigned to this.log.
	 * @param ref Output stream for reference output from the tests.
	 *            Is assigned to this.ref.
	 * @return Overall status of running all of the test cases.
     *
	 */
	public final Status run( String argv[], PrintWriter log, PrintWriter ref ) {
		Status status[];

		// assign streams.
		this.ref = ref;
		this.log = log;

		// Initialize Umbrella test
		Status s = init( argv );
		if( s.getType() != Status.PASSED ) {
			return s;
		}

		// loop through tests
		status = new Status[ testClasses.length ];
		for( int i = 0; i < testClasses.length; ++i ) {

			/* belt and suspenders here, I should never have a null or 
			 * empty value for tests[i].
			 */
			if( testClasses[i] == null ||	testClasses[i].equals( "" ) ) {
				status[i] = Status.failed( "Invalid test class name" );
				continue;
			}

			/* Attempt to create an instance of tests[i], cast it to 
			 * javasoft.sqe.javatest.Test, and call Test.run().
			 */
			try {
				Test test = (Test) Class.forName( testClasses[i] ).newInstance( );
				status[i] = test.run( executeArgs, log, ref );
			}
			catch( ClassNotFoundException e ) {
				status[i] = Status.failed( e.toString() );
			}
			catch( InstantiationException e ) {
				status[i] = Status.failed( e.toString() );
			}
			catch( IllegalAccessException e ) {
				status[i] = Status.failed( e.toString() );
			}
			catch( Throwable t ) {
				status[i] = Status.failed( "Unexpected Throwable: " +
										   t.toString() );
			}

		}

		return MultiStatus.overallStatus( testClasses, status, ref );
	}

	/**
	 * Parses execute arguments and assigns values to testClasses and executeArgs.
	 * This method processes the execute arguments for Umbrella Test and assigns
	 * values to the testClases and executeArgs variables.  It recognizes two specific 
	 * command line options:
	 * <DL>
	 * <DT><B>-TestClasses</B>
	 * <DD>A white space separated list of execute class names.  To make this
	 *     simpler, you can use the <STRONG>-PackageName</STRONG> argument to
	 *     specify a common package name for the test classes.
	 *
	 * <DT><B>-PackageName</B> <EM>optional</EM>
	 * <DD>The common package name of all of the test classes.  This is
	 *     optional, but very useful (do you really want to type 
	 *     "javasoft.sqe.tests.api.java.lang.Object" ten times?).
	 *
	 * </DL>
	 *
	 * <P>If a packageName is defined, it is prepended to the test classes specified by 
	 * the -TestClasses command line argument.  This method assumes that <STRONG>all</STRONG> 
	 * other command line arguments are to be passed to the test classes that Umbrella Test 
	 * executes.  It assigns these values to the executeArgs variable.
	 *
	 * @param argv execute arguments from the test harness or from the 
	 *             command line
	 *
	 * @return Status.passed("") if initialization was successful, or 
	 *         Status.failed( ) with an informative message if 
	 *         initialization was not successful.
	 *
	 * @see #testClasses
	 * @see #executeArgs
	 */
	protected Status init( String argv[] ) {
		int i;
		String packageName = null;
		boolean gettingClasses = false;
		Vector execArgs = new Vector();
		Vector tests = new Vector();

		for( i = 0; i < argv.length; ++i ) {

			/* Check for Package Name */
			if( argv[i].equals( "-PackageName" ) &&
				( argv.length > i + 1 ) ) {
				packageName = argv[++i];
				if( ! packageName.endsWith( "." ) ) {
					packageName = packageName + ".";
				}
			}

			/* Check for Test Classes */
			else if( argv[i].equals( "-TestClasses" ) &&
					 (argv.length > i + 1) ) {
				gettingClasses = true;
			}

			/* If the previous argument keyword was -TestClasses 
			 * and this argument does not start with a -
			 * add value to class names.
			 */
			else if( gettingClasses &&
					 ( ! argv[i].startsWith( "-" ) ) ) {
				tests.addElement( argv[i] );

			}

			/* Add everything else to executeArgs list */
			else {
				gettingClasses = false;
				execArgs.addElement( argv[i] );
			}
				
		}

		/* Check if tests has been defined */
		if( tests.size( ) < 1 ) {
			return Status.failed( "No test classes defined" );
		}

		/* Convert tests to testClasses */
		testClasses = new String[ tests.size() ];
		tests.copyInto( testClasses );

		/* Add package name if defined */
		if( packageName != null ) {
			for( i = 0; i < testClasses.length; ++i ) {
				testClasses[i] = packageName + testClasses[i];
			}
		}

		/* Convert execArgs to executeArgs array */
		executeArgs = new String[ execArgs.size() ];
		execArgs.copyInto( executeArgs );
		
		return Status.passed( "" );

	}

}





