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




package javasoft.sqe.javatest.lib.apitest;

import java.lang.reflect.*;
import javasoft.sqe.javatest.Status;
import java.io.PrintWriter;

/**
 * <P>
 * The PrintReporter is the default implementation of the Reporter interface.
 * This report generator generates a detailed report of all the tests that failed.
 * In addition to this, it also generates information about the test header (the
 * name of the method/constructor under test) and the test summary.
 * </P>
 *
 * @author Jonathan Gibbons, Kevin A Smith, Rampalli Narasimhan
 *
 */

public class PrintReporter implements Reporter {

  /**
   * <P>
   * This method initializes the PrintWriters log and ref, arguments passed
   * to the PrintWriter.
   * </P>
   * @param log the log information is sent here
   * @param ref the test reference information is sent here
   * @param args arguments passed to the generator
   * @exception AssertionTest.Fault is raised when there is an error.
   */
  public void init (String[] args, PrintWriter log, PrintWriter ref) throws AssertionTest.Fault {
    this.log = log;
    this.ref = ref;
    if (args != null) {
    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("-verbose")) {
	verbose = true;
      }
      else if (args[i].equals ("-showpoint")) {
	  // Use this rarely - when there are large test points and if the test seems to hang
	  // somewhere 
	  shouldShowPoint = true;
      }
      if (args[i].equals("-generator")) { 
	throw new AssertionTest.Fault("There was no -end at the end of Argument list for the Reporter");
      }
    }
    }
  }

 /**
   * <P>
   * This method reports the start of a test by printing the Method name and
   * Class name.
   * </P>
   * @param methodName the name of the method being tested.
   * @param dataFactories the data used in this test case.
   */
  public void reportTestStart(Method methodName, Factory[] f) {
    dataFactories = f;
    ref.println ("----------------------------------------------------------------");
    ref.println ("               AssertionTest Report                             ");
    ref.println ("----------------------------------------------------------------");
    ref.println (" Method Name     : " + methodName.toString());
    ref.println (" Class  Name     : " + (methodName.getDeclaringClass()).toString());
    ref.println ("----------------------------------------------------------------");
    ref.flush();
  }

 /**
   * <P>
   * This method reports the start of a test by printing the Constructor name and
   * Class name.
   * </P>
   * @param constructorName the name of the constructor being tested.
   * @param dataFactories the data used in this test case.
   */
  public void reportTestStart (Constructor constructorName, Factory[] f) {
    dataFactories = f;
    ref.println ("----------------------------------------------------------------");
    ref.println ("               AssertionTest Report                             ");
    ref.println ("----------------------------------------------------------------");
    ref.println (" Method Name     : " + constructorName.toString());
    ref.println (" Class  Name     : " + (constructorName.getDeclaringClass()).toString());
    ref.println ("----------------------------------------------------------------");
    ref.flush();

  }

  /**
   * <P>
   * This method is called when an assertion is checked and the details of the assertions are
   * passed to the reporter.
   * </P>
   * @param s the comment associated with this Assertion
   * @param b the result of the assertion
   */
  public void reportAssertion (String s, boolean b) {
    if (!b) {
      if (firstTime) {
	firstTime = false;
	printTestData();
	ref.println (" List of Assertion Failures:");
      }
      ref.println ("  - " + s);
    }
  }

  /**
   * <P>
   * This method is called when a test point is concluded and the test result ought to
   * be reported.
   * </P>
   * @param result The information returned when the method/constructor under test was executed
   * @param s   the final status of this test point
   */
  public void reportTestResult (Object res, Status s) {
    result = res;
    if (s.getType() == Status.PASSED) {
      pass += 1; 
    } else {
      printTestData();
      fail += 1;
      if (verbose) {
	ref.println (" Test Returned             : " + result);
      }
      ref.println (" Test Status               : " + s.getReason());
      ref.println ("--------------------------------------------------------------");
    }
    ref.flush();
  }

  /**
   * <P>
   * This method is called when an exception is raised and it should be reported.
   * </P>
   * @param t the throwable object representing the exception raised.
   */
  public void reportException (Throwable t) {
    if (firstTime) {
      firstTime = false;
      printTestData();
    }
    ref.println ("Exception Raised during Testing : ");
    t.printStackTrace (ref);
    ref.flush();
  }

 /**
   * <P>
   * This method is called when a test starts for a specific test point. A specific test point
   * contains information on the object under test and the execution parameters.
   * </P>
   * @param objectUnderTest the instance of the class being tested.
   * @param exeParams the method/constructor execution parameters.
   * @param exeSignature this is used by the reporter to get the name associated with the factory
   *                     object used in this test point.
   */
  public void reportTestData (Object objectUnderTest, Object[] exeParameters, int[] exeSignature) {
    printData = true;
    firstTime = true;
    this.objectUnderTest = objectUnderTest;
    this.exeParameters = exeParameters;
    this.exeSignature = exeSignature;
    if (shouldShowPoint) {
      log.print ("Excecution Signature at this point: ");
      for (int i = 0; i < exeSignature.length; ++i) {
	log.print (exeSignature[i]);
      }
      log.println ("");
    }
    
  }
 
  /**
   * <P>
   * This method is called when all the test points are executed and the tests conclude.
   * This usually generates the overall test summary
   * </P>
   * @param overallStatus the overall status - PASSED if and only if all tests pass
   */
  public void reportTestDone (Status overallStatus) {
    String status = new String();
    if (overallStatus.getType() == Status.PASSED) {
      status = "Passed";
    } else {
      status = "Failed";
    }
    ref.println ("--------------------------------------------------------------------");   
    ref.println ("               Overall AssertionTest Result Summary                 ");
    ref.println ("--------------------------------------------------------------------");
    ref.println (" Overall Test Status        : " + status);
    ref.println (" Total Number of Tests      : " + (pass + fail));
    ref.println (" Total Tests Passed         : " + pass);
    ref.println (" Total Tests Failed         : " + fail);
    ref.println ("--------------------------------------------------------------------");
    ref.flush(); 
  }

  //
  // Either writeAssertion or writeException or writeTestResult will call this
  //
  private void printTestData() {
    if (printData) {
      printData = false;
      ref.println ("--------------------------------------------------------------");
      if (verbose) {
	ref.print   (" Parameter Passed          : ");
	for (int i = 0; i < exeParameters.length; ++i) {
	  ref.print (exeParameters[i]+ " ");
	}
	ref.println ("");
	ref.println (" Object Under Test         : " + objectUnderTest );
      }
      ref.print (" Index of factory objects  : ");
      for (int i = 0; i < exeSignature.length; ++i) {
	if (i == exeSignature.length - 1) 
	  ref.print (exeSignature[i]);
	else 
	  ref.print (exeSignature[i] + ":");
      }
      ref.println ("");
    }
  }

  private PrintWriter ref;
  private PrintWriter log;
  private int pass = 0;
  private int fail = 0;
  private Factory[] dataFactories;
  private Object objectUnderTest;
  private Object[] exeParameters;
  private Object result;
  private boolean verbose = false;
  private int[] exeSignature;
  private boolean printData;
  private boolean firstTime = true;
  private boolean shouldShowPoint = false;
}





