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
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiStatus;
import java.io.PrintWriter;

/**
 * <P>
 * GridGenerator.java: This class is an implementation of the Generator 
 * interface. This is a type of data provider. In this data provider 
 * class, a grid of execution parameters are generated from the factory
 * objects. 
 * </P>
 * @author Rampalli Narasimhan, Jonathan Gibbons, Kevin A Smith
 * @see    Generator
 * @see    AssertionTest
 *
 */

public class GridGenerator implements Generator {


  /**
   * run(): Implements the interface Generator
   *
   * @param testObject   AssertionTest object that calls the generator
   * @param paramFactory Factory of objects that represent the ObjectUnderTest
   *                     and execution parameters.
   */
  public Status run (AssertionTest testObject, Factory[] paramFactory) {
    this.testObject = testObject;
    factory = paramFactory;
    overallStatus = new MultiStatus();
    try {
      iterate (paramFactory);
    } catch (AssertionTest.Fault f) {
      return Status.failed (f.getMessage());
    }
    return overallStatus.getStatus();
  }

  /**
   * <P>
   * init() implements the interface Generator and is used for initializing
   * any runtime arguments passed to the Generator. In the case of a Grid
   * Generator, since no arguments are required/processed this method is empty
   * </P>
   * @param args arguments passed to the Grid Generator at runtime.
   * @exception AssertionTest.Fault is raised when there is no -end to the GridGenerator
   *                             argument list.
   */
  public void init (String[] args) throws AssertionTest.Fault {
    if (args != null) {
      for (int i = 0; i < args.length; ++i) {
	if (args[i].equals("-reporter")) 
	    throw  new AssertionTest.Fault ("There was no -end at the end of the argument list for Generator");
      }
    }
  }

  //
  // This method generates the grid points and as soon as one data point
  // is reached, it executes the test case.
  //
  private void iterate (Factory[] pFactory) throws AssertionTest.Fault {
    int[] fact = new int[pFactory.length];
    for (int j = 0; j < pFactory.length; ++j) {
      fact[j] = (pFactory[j]).getSize();
    }
    int factory_length = fact.length;
    int[] points = new int[fact.length];
    int i = 0;    
    while (true) {
      if (points[i] < fact[i]) {
	if (i == factory_length - 1) {
	  executeTestCase (points);
	} else {
	  points[++i] = 0;
	  continue;
	} 
      } else if (--i < 0) 
	break;
      points[i]++;
    }
  }

  //
  // This method executes the test case by calling the AssertionTest object
  // with the execution information.
  //
  private void executeTestCase (int[] p) throws AssertionTest.Fault {
    Object[] exeParameters = new Object[p.length - 1];
    Object objectUnderTest = null;
    String testID = String.valueOf(p[0]);
    try {
      objectUnderTest = factory[0].createElement(p[0]);
    for (int i = 1; i < p.length; ++i) {
      testID = testID + ":" + String.valueOf(p[i]);
      exeParameters[i - 1] = factory[i].createElement (p[i]);
    }
    Status testStatus = testObject.runTest (objectUnderTest, exeParameters, p);
    overallStatus.add (testID, testStatus);
    factory[0].disposeElement (objectUnderTest);
    for (int i = 1; i < factory.length; ++i) 
      factory[i].disposeElement (exeParameters[i - 1]);
    } catch (AssertionTest.Fault f) {
      throw new AssertionTest.Fault (f.getMessage() + " Execution Signature at this point: " + testID);
    }
  }


  /*--------------- Private Data Member for this class -------------------*/

  private AssertionTest testObject;
  private Factory[] factory;
  private MultiStatus overallStatus;

 
}
