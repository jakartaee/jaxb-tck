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
import javasoft.sqe.javatest.Status;

/**
 * <P>
 * The interface generator defines a set of methods that should be 
 * present in all types of generators used in AssertionTest. The
 * task of the generator is to get the Factory of objects created
 * by the test developer and manipulate the factory to get some
 * meaningful data. For example, the GridGenerator generates a 
 * grid of possible data combinations using which either the 
 * method or the constructor under test can be executed.
 * </P>
 * 
 * @author Rampalli Narasimhan, Jonathan Gibbons, Kevin A Smith
 * @see    GridGenerator
 * @see    Factory
 * @see    AssertionTest
 *
 */

public interface Generator {
  /**
   * <P>
   * This method takes the Factory[] given by the test developer and
   * manipulates this data to get some meaningful set of data using
   * which AssertionTest will execute either the method or the 
   * constructor under test.
   * </P>
   *
   * @param assertionTestObject the assertion test instance 
   * @param f data factories supplied by the test developer
   */
  Status run(AssertionTest assertionTestObject, Factory[] f);

  /**
   * <P>
   * This method is used for initializing the arguments of the Generator
   * object. The arguments are passed at runtime and are process by the
   * AssertionTest base class.
   * </P>
   * 
   * @param arguments arguments passed to the generator
   * @exception AssertionTest.Fault is raised when there is an error in decoding
   *                                the arguments.
   */
  void init (String[] arguments) throws AssertionTest.Fault;

}



