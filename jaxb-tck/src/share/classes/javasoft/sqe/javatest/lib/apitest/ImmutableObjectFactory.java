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

/**
 * <P>
 * This class implements the Factory interface for all the primitive
 * types and some non primitive types of Java. They are
 * </P>
 * <OL>
 * <LI> <CODE>int</CODE>
 * <LI> <CODE>long</CODE>
 * <LI> <CODE>float</CODE>
 * <LI> <CODE>double</CODE>
 * <LI> <CODE>char</CODE>
 * <LI> <CODE>boolean</CODE>
 * <LI> <CODE>string</CODE>
 *</OL>
 *<P>
 * Usage: A Factory of integers can be created as follows:
 *</P>
 *<PRE>
 * int a[] = {100, 10, 9, 18, -7, -0, 0};
 * ImmutableObjectFactory param1 = ImmutableObjectFactory.createIntegerFactory(a);
 *</PRE>
 *
 */

public class ImmutableObjectFactory implements Factory {
  

  /** 
   * The Default Constructor for the Primitive Factory
   */
  public ImmutableObjectFactory() {
    names = new Vector();
    values = new Vector();
  }

  /**
   * Create a Factory object with the initialCapacity specified
   * in the method parameter set. If the second parameter to this
   * method is true, then include "null" as one of the Factory
   * elements.
   *
   * @param initialCapacity size of the Factory
   * @param includeNull     specifies whether null should be included or not
   *
   */
  public ImmutableObjectFactory(int initialCapacity, boolean includeNull) {
    names = new Vector(initialCapacity);
    values = new Vector (initialCapacity);
    if (includeNull) {
      names.addElement ("null");
      values.addElement (null);
    }
  }

  /**
   * <P>
   * The method is used for adding an element into the factory. The method is
   * particularly useful when the test developer would like to give a special
   * comment or special name to the object being added into the factory.
   * The static createXXX methods in this class will assign <CODE>String.ValueOf()</CODE>
   * as the name for the values.
   * </P>
   * @param name The name or comment associated with the factory element
   * @param value the value of the factory element.
   */
  public void add(String name, Object value) {
    names.addElement(name);
    values.addElement(value);
  }


  /**
   * This method returns the size of the Factory
   */
  public int getSize() { 
    return names.size();
  }

  /**
   * This method returns an element from the factory that is
   * located at index i.
   *
   * @param i index of the element
   * @exception AssertionTest.Fault is raised when an error occurs while 
   * creating the element.
   */
  public Object createElement(int i) throws AssertionTest.Fault {
    if (i > values.size()) 
      throw new AssertionTest.Fault ("Error: test point index > factory length"); 
    return values.elementAt(i);
  }

  /**
   * <P>
   * This method disposes the element that is created. Since
   * this factory represents primitive types this method is
   * empty.
   * </P>
   * @param obj Object that needs to be disposed.
   */
  public void disposeElement(Object obj) {
  }

  /**
   * This method returns the name of the element from the factory
   * that is at index i.
   *
   * @param i index of the element.
   *
   */
  public String getName(int i) { 
    return (String) names.elementAt(i);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of doubles.
   * 
   * @param values an array of doubles used for creating the factory.
   */
  public static ImmutableObjectFactory createDoubleFactory(double[] values) { 
    return createDoubleFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of doubles and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of doubles used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createDoubleFactory(double[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Double (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of floats.
   * 
   * @param values an array of floats used for creating the factory.
   */
  public static ImmutableObjectFactory createFloatFactory(float[] values) { 
    return createFloatFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of floats and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of floats used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createFloatFactory(float[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Float (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of integers.
   * 
   * @param values an array of integers used for creating the factory.
   */
  public static ImmutableObjectFactory createIntegerFactory(int[] values) { 
    return createIntegerFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of integers and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of integers used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createIntegerFactory(int[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Integer (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of longs.
   * 
   * @param values an array of longs used for creating the factory.
   */
  public static ImmutableObjectFactory createLongFactory(long[] values) { 
    return createLongFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of longs and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of longs used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createLongFactory(long[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Long (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of chars.
   * 
   * @param values an array of chars used for creating the factory.
   */
  public static ImmutableObjectFactory createCharacterFactory(char[] values) { 
    return createCharacterFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of char and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of char used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createCharacterFactory(char[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Character (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of Strings.
   * 
   * @param values an array of Strings used for creating the factory.
   */
  public static ImmutableObjectFactory createStringFactory(String[] values) { 
    return createStringFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of Strings and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of Strings used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createStringFactory(String[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(values[i], values[i]);
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of booleans.
   * 
   * @param values an array of booleans used for creating the factory.
   */
  public static ImmutableObjectFactory createBooleanFactory(boolean[] values) { 
    return createBooleanFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of booleans and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   *
   * @param values an array of booleans used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createBooleanFactory(boolean[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory(values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), new Boolean (values[i]));
    return df; 
  }

 /**
   * This method creates a ImmutableObjectFactory from an array of objects.
   * <B>NOTE</B>: This works only for immutable objects
   *
   * @param values an array of objects used for creating the factory.
   */
  public static ImmutableObjectFactory createObjectFactory(Object[] values) { 
    return createObjectFactory(values, false);
  }

  /**
   * This method creates a ImmutableObjectFactory from an array of objectss and 
   * a boolean flag that signals whether this factory should include 
   * "null" or not.
   * <B>NOTE</B> This works only for immutable objects
   *
   * @param values an array of objectss used for creating the factory.
   * @param includeNull a boolean flag to specify the inclusion of null. 
   */
  public static ImmutableObjectFactory createObjectFactory(Object[] values, boolean includeNull) { 
    ImmutableObjectFactory df = new ImmutableObjectFactory (values.length, includeNull);
    for (int i = 0; i < values.length; ++i) 
      df.add(String.valueOf(values[i]), values[i]);
    return df; 
  }

  /*--------------- Private Data Members -----------------------*/
  private int initialCapacity;
  private Vector names;
  private Vector values;
}
