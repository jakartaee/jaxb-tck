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

import java.util.Vector;
import javasoft.sqe.javatest.*;
import javasoft.sqe.javatest.lib.MultiStatus;
import javasoft.sqe.javatest.lib.apitest.AssertionTest;
import javasoft.sqe.javatest.lib.apitest.Factory;
import javasoft.sqe.javatest.lib.apitest.Generator;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * <P>
 * This class is an implementation of the Generator interface that is used
 * for executing test cases for a specific testpoint. The best use of this
 * class is as follows. First execute a test case using AssertionTest's 
 * default Generator viz the GridGenerator. While executing this test use
 * the default Reporter viz. the PrintReporter. This test will execute and
 * if there are any failure the reporter will print out a list of failures.
 * The report will contain a line <I>Index of Factory Object</I> followed
 * by a colon seperated list of numbers. These numbers correspond to the
 * Factory index in the array of Factory objects present in the test case
 * that was used for identifying the object under test and execution 
 * parameters used for executing this specific test point. These are the
 * test points where the tests failed. When the test developer/product
 * developer wants to rerun the test case all grid points may not be of
 * great interest (also if the number of grid points run into thousands
 * the test may take a longer time to execute). To simply rerun the test
 * points that failed (only), use the following Syntax. For example if
 * test points 2:2 and 2:3 failed then
 * </P>
 * <CODE>
 * java TestCaseName javasoft.sqe.javatest.lib.apitest.SingletonGenerator
 * -testPoint 2:2 2:3 -end
 * </CODE>
 *
 * @author Rampalli Narasimhan
 * @see    Generator
 * @see    AssertionTest
 */

public class SingletonGenerator implements Generator {

  /**
   * <P>
   * This method implements the interface Generator for the SingletonGenerator.
   * This method copies the value of the AssertionTest object that called this
   * Generator (which will be used for callback) and the array of Factory 
   * objects that represents the data used for creating the testpoints.
   * </P>
   * @param testObject the AssertionTest object 
   * @param paramFactory the data factory
   */
  public Status run (AssertionTest testObject, Factory[] paramFactory) {
    this.testObject = testObject;
    factory = paramFactory;
    overallStatus = new MultiStatus();
    try {
      createTestData (testPointVector);
      executeTestCases (testData);
    } catch (AssertionTest.Fault f) {
      return Status.failed (f.getMessage());
    }
    return overallStatus.getStatus();
  }

  /**
   * <P>
   * This method processes the arguments that was passed at runtime (the list of
   * arguments that belongs to the Generator) to AssertionTest.
   * </P>
   * @param args the arguments passed at runtime to AssertionTest that belongs to
   *             the generator.
   * @exception AssertionTest.Fault raised when there is an error in processing the
   *                                generator arguments.
   */
  public void init (String[] args) throws AssertionTest.Fault {
    try {
      if (args != null) {
	for (int i = 0; i < args.length; ++i) {
	  if (args[i].equals("-reporter")) {
	    throw  new AssertionTest.Fault ("There was no -end at the end of the argument list for Generator");
	  } else if (args[i].equals("-testPoint")) {
	    i += 1;
	    while (i < args.length) {
	      testPointVector.addElement (args[i]);
	      i += 1;
	    }
	  }
	}
      }
    } catch (ArrayIndexOutOfBoundsException ae) {
      throw new AssertionTest.Fault (ae.getMessage() + " raised in generator's init() method");
    }
  }

  /*
   * A private method that creates the testdata and passes the data to the executeTestCase()
   * method to execute a soecific test point.
   */
  private void createTestData (Vector v) {
    int index;
    for (int i = 0; i < v.size(); ++i) {
      int[] param = new int[factory.length];
      String elem = (String) v.elementAt(i);
      StringTokenizer st = new StringTokenizer (elem, ":");
      index = 0;
      while (st.hasMoreElements()) {
	Integer val = new Integer ((String) st.nextElement());
	param[index] = val.intValue();
	index += 1;
      }
      testData.addElement(param);
    }
  }

  /**
   * Executes a specific test point
   */
  private void executeTestCases (Vector v) throws AssertionTest.Fault {
    String testID = "";
    try {
    for (int j = 0; j < v.size(); ++j) {
      testID = "";
      int[] p = new int[factory.length];
      p = (int []) v.elementAt(j);
      Object[] exeParameters = new Object[p.length - 1];
      Object objectUnderTest = null;
      for (int i = 0; i < p.length; ++i) {
	testID = testID + String.valueOf(p[i]) + ":";
	if (i == 0) 
	  objectUnderTest = factory[i].createElement(p[i]);
	else 
	  exeParameters[i - 1] = factory[i].createElement (p[i]);
      }
      Status testStatus = testObject.runTest (objectUnderTest, exeParameters, p);
      overallStatus.add (testID, testStatus);
      factory[0].disposeElement (objectUnderTest);
    }
    } catch (AssertionTest.Fault f) {
      throw new AssertionTest.Fault (f.getMessage() + " Execution Signature at this point: " + testID);
    }
  }


  /*--------------- Private Data Member for this class -------------------*/

  private AssertionTest testObject;
  private Factory[] factory;
  private MultiStatus overallStatus;
  private Vector testPointVector = new Vector();
  private Vector testData = new Vector();

 
}
