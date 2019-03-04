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

/**
 * This interface abstracts the basic functionality provided
 * by all type of Factories. 
 *
 * @author     Jonathan Gibbons, Rampalli Narasimhan, Kevin A Smith
 * @see        ImmutableObjectFactory
 * @see        AssertionTest
 * @see        GridGenerator
 *
 */

public interface Factory {

  /**
   * This method returns the size of the factory.
   */
  int getSize();

  /**
   * This method returns the name of the element located at the index
   * i.
   * 
   * @param i index of the element.
   */ 
   String getName (int i);

  /**
   * This method returns the element in the factory that is located at
   * index i.
   *
   * @param i index of the element
   * @exception AssertionTest.Fault is raised when an error occurs while trying
   *           to create the element in the factory.
   */
   Object createElement(int i) throws AssertionTest.Fault;

  /**
   * This method disposes the element created by the Factory
   *
   * @param obj Object that should be disposed.
   *
   */
   void disposeElement (Object obj);

}
