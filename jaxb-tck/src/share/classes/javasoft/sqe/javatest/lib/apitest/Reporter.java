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
import java.util.Vector;
import java.io.PrintWriter;

/**
 * <P>
 * This interface represents the methods required by any Reporter
 * object. The default Reporter object called ReportGenerator 
 * implements this interface. However, a test developer can write
 * his/her own report generator class by implementing this interface.
 * <P>
 * 
 * @author Rampalli Narasimhan, Jonathan Gibbons, Kevin A Smith
 */

public interface Reporter {

  /**
   * <P>
   * This method initializes the PrintWriters log and ref
   * </P>
   *
   * @param args the list of arguments passed to the Reporter
   * @param log the log information is sent here
   * @param ref the test reference information is sent here
   * @exception AssertionTest.Fault is raised when this method runs into an error.
   */
   void init (String[] args, PrintWriter log, PrintWriter ref) throws AssertionTest.Fault;

  /**
   * <P>
   * This method reports the start of a test by printing the Method name and
   * Class name.
   * </P>
   * @param methodName the name of the method being tested.
   * @param dataFactories the data used in this test case.
   */
   void reportTestStart (Method methodName, Factory[] dataFactories);
 
 /**
   * <P>
   * This method reports the start of a test by printing the Constructor name and
   * Class name.
   * </P>
   * @param constructorName the name of the constructor being tested.
   * @param dataFactories the data used in this test case.
   */
   void reportTestStart (Constructor constructorName, Factory[] dataFactories);

  /**
   * <P>
   * This method is called when a test point is concluded and the test result ought to
   * be reported.
   * </P>
   * @param result The information returned when the method/constructor under test was executed
   * @param finalStatus   the final status of this test point
   */
   void reportTestResult (Object result, Status finalStatus);

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
   void reportTestData (Object objectUnderTest, Object[] exeParams, int[] exeSignature);

  /**
   * <P>
   * This method is called when an exception is raised and it should be reported.
   * </P>
   * @param exceptionThrown the throwable object representing the exception raised.
   */
   void reportException (Throwable exceptionThrown);

  /**
   * <P>
   * This method is called when an assertion is checked and the details of the assertions are
   * passed to the reporter.
   * </P>
   * @param exceptionString the comment associated with this Assertion
   * @param booleanExpressionTested the result of the assertion tested by the test developer
   */
   void reportAssertion (String exceptionString, boolean booleanExpressionTested);

  /**
   * <P>
   * This method is called when all the test points are executed and the tests conclude.
   * This usually generates the overall test summary
   * </P>
   * @param overallStatus the overall status - PASSED if and only if all tests pass
   */
   void reportTestDone (Status overallStatus);
}





