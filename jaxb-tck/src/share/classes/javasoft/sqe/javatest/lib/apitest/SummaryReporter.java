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
 * SummaryReporter is an implementation of the Reporter interface that
 * prints the summary of the test only. Unlike the PrintReporter that
 * prints detailed information about each test point failure, the 
 * Summary reporter just prints the overall test summary that includes
 * the total number of tests, the total number of tests that passed,
 * the total number of tests that failed, the method/constructor under
 * test and the class name.
 * </P>
 * @author Rampalli Narasimhan
 * @see    Reporter
 * @see    AssertionTest
 */
public class SummaryReporter implements Reporter {

  /**
   * <P>
   * This method implements the Reporter Interface.
   * This method initializes the PrintWriter objects log and ref where
   * the log information and the reference information will be printed.
   * The SummaryReporter does not take any arguments and hence any arguments
   * passed to this reporter will be simply ignored.
   * </P>
   * @param args List of arguments taken by SummaryReporter
   * @param log the PrintWriter object where the log information is written.
   * @param ref the PrintWriter object where the reference information is written.
   * @exception AssertionTest.Fault is raised when there is an error.
   */
  public void init (String[] args, PrintWriter log, PrintWriter ref) throws AssertionTest.Fault {
    this.log = log;
    this.ref = ref;
  }

  /**
   * <P>
   * This method implements the Reporter interface. Since the SummaryReporter
   * just prints the overall test summary, this method ignores the data factory
   * passed as a parameter. The purpose of this method is to capture the name
   * of the method under test.
   * </P>
   * @param methodName the name of the method under test
   * @param f the data factories
   */
  public void reportTestStart(Method methodName, Factory[] f) {
    method = methodName;
  }

 /**
   * <P>
   * This method implements the Reporter interface. Since the SummaryReporter
   * just prints the overall test summary, this method ignores the data factory
   * passed as a parameter. The purpose of this method is to capture the name
   * of the constructor under test.
   * </P>
   * @param constructorName the name of the constructor under test
   * @param f the data factories
   */
  public void reportTestStart (Constructor constructorName, Factory[] f) {
    constructor = constructorName;
  }

  /**
   * <P>
   * This method implements the Reporter interface. The SummaryReporter does not
   * report any assertion information. So this method is empty.
   * </P>
   * @param s the comment associated with the assertion
   * @paran b the boolean assertion
   */
  public void reportAssertion (String s, boolean b) {
  }

  /**
   * <P>
   * This method implements the Reporter interface. The SummaryReporter does not
   * report any test result information. So this method is empty.
   * </P>
   * @param s the status of the test (at this test point)
   * @paran res the result returned by the method or constructor under test.
   */
  public void reportTestResult (Object res, Status s) {
    if (s.getType() == Status.PASSED) {
      pass += 1; 
    } else {
      fail += 1;
    }
  }

  /**
   * <P>
   * This method implements the Reporter interface. The SummaryReporter does not
   * report any exception information. So this method is empty.
   * </P>
   * @param t The throwable object that represents the exception raised.
   */
  public void reportException (Throwable t) {
  }

  /**
   * <P>
   * This method implements the Reporter interface. The SummaryReporter does not
   * report any test data  information. So this method is empty.
   * </P>
   * @param objectUnderTest the object under test
   * @param exeParameters the execution parameter set
   * @param exeSignature the index of the objects from the factory array
   */
  public void reportTestData (Object objectUnderTest, Object[] exeParameters, int[] exeSignature) {
  }
 

  /**
   * <P>
   * This method implements the Reporter interface. This is the most important method
   * of the SummaryReporter as this method reports the overall test summary.
   * </P>
   * @param overallStatus the overall status of the test. Passed if and only if all test points pass.
   */
  public void reportTestDone (Status overallStatus) {
    String status = new String();
    if (overallStatus.getType() == Status.PASSED) 
      status = "Passed.";
    else 
      status = "Failed.";
    ref.println ("--------------------------------------------------------------------");   
    ref.println ("               Overall AssertionTest Result Summary                 ");
    ref.println ("--------------------------------------------------------------------");
    if (method != null) {
      ref.println (" Method Name                : " + method.toString());
      ref.println (" Class  Name                : " + (method.getDeclaringClass()).toString());
    } else if (constructor != null) {
      ref.println (" Method Name                : " + constructor.toString());
      ref.println (" Class  Name                : " + (constructor.getDeclaringClass()).toString());
    }
    ref.println (" Overall Test Status        : " + status);
    ref.println (" Total Number of Tests      : " + (pass + fail));
    ref.println (" Total Tests Passed         : " + pass);
    ref.println (" Total Tests Failed         : " + fail);
    ref.println ("--------------------------------------------------------------------");
    ref.flush(); 
  }
 
  /*------------------------- Private Memebers of this class --------------------------*/
  private PrintWriter ref;
  private PrintWriter log;
  private int pass = 0;
  private int fail = 0;
  private Method method;
  private Constructor constructor;
}





