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

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.Test;
import com.sun.javatest.util.WriterStream;

/**
 * A handler for the set of test cases in a test.
 * Test cases are those methods with no args that return a 
 * {@link javasoft.sqe.javatest.Status status}.
 * Test cases can be explicitly selected into or excluded from the 
 * set.
 */
public class TestCases {
    /**
     * Exception used to report internal errors.
     */
    public static class Fault extends Exception {
	/**
	 * Construct a new Fault object that signals failure
	 * with a corresponding message.
	 * 
	 * @param s the string containing a comment
	 */
	public Fault(String s) {
	    super(s);
	}
    }

    /**
     * Create an object to handle the test cases of the given test.
     */
    public TestCases(Test t, PrintWriter log) {
	test = t;
	this.log = log;
	testClass = t.getClass();
    }

    /**
     * Explicitly select a set of test cases by name. Subsequent calls
     * are cumulative; if no selections are made, the default is all
     * test cases are selected. Excluded tests will be excluded from the
     * selection; the order of select and exclude calls does not matter.
     * @param testCaseNames a comma-separated list of test cases names.
     * Each name must identify a method in the test object, that takes
     * no arguments and returns a {@link javasoft.sqe.javatest.Status status}.
     * @throws Fault if any of the test case names are invalid.
     */
    public void select(String testCaseNames) throws Fault {
	select(split(testCaseNames));
    }


    /**
     * Explicitly select a set of test cases by name. Subsequent calls
     * are cumulative; if no selections are made, the default is all
     * test cases are selected. Excluded tests will be excluded from the
     * selection; the order of select and exclude calls does not matter.
     * @param testCaseNames an array of test cases names.
     * Each name must identify a method in the test object, that takes
     * no arguments and returns a {@link javasoft.sqe.javatest.Status status}.
     * @throws Fault if any of the test case names are invalid.
     */
    public void select(String[] testCaseNames) throws Fault  {
	for (int i = 0; i < testCaseNames.length; i++) {
	    String t = testCaseNames[i];
	    selectedCases.put(t, getTestCase(t));
	}
    }


    /**
     * Explicitly exclude a set of test cases by name. Subsequent calls
     * are cumulative; by default, no test cases are excluded.
     * @param testCaseNames a comma-separated list of test cases names.
     * Each name must identify a method in the test object, that takes
     * no arguments and returns a {@link javasoft.sqe.javatest.Status status}.
     * @throws Fault if any of the test case names are invalid.
     */
    public void exclude(String testCaseNames) throws Fault  {
	exclude(split(testCaseNames));
    }


    /**
     * Explicitly exclude a set of test cases by name. Subsequent calls
     * are cumulative; by default, no test cases are excluded.
     * @param testCaseNames an array of test cases names.
     * Each name must identify a method in the test object, that takes
     * no arguments and returns a {@link javasoft.sqe.javatest.Status status}.
     * @throws Fault if any of the test case names are invalid.
     */
    public void exclude(String[] testCaseNames) throws Fault  {
	for (int i = 0; i < testCaseNames.length; i++) {
	    String t = testCaseNames[i];
	    excludedCases.put(t, getTestCase(t));
	}
    }


    /**
     * Return an enumeration of the selected test cases, based on the
     * select and exclude calls that have been made, if any.
     */
    public Enumeration enumerate() {
	Vector v = new Vector();
	if (selectedCases.isEmpty()) {
	    Method[] methods = testClass.getMethods();
	    for (int i = 0; i < methods.length; i++) {
		Method m = methods[i];
		if (excludedCases.get(m.getName()) == null) {
		    Class[] paramTypes = m.getParameterTypes();
		    Class returnType = m.getReturnType();
		    if ((paramTypes.length == 0) && Status.class.isAssignableFrom(returnType))
			v.addElement(m);
		}
	    }
	}
	else {
	    for (Enumeration e = selectedCases.elements(); e.hasMoreElements(); ) {
		Method m = (Method)(e.nextElement());
		if (excludedCases.get(m.getName()) == null)
		    v.addElement(m);
	    }
	}
	return v.elements();	
    }

    
    /**
     * Invoke each of the selected test cases, based upon the select and exclude
     * calls that have been made, if any.
     * If the test object provides a public method 
     * {@link javasoft.sqe.javatest.Status}invokeTestCase({@link java.lang.reflect.Method})
     * that method will be called to invoke the test cases; otherwise, the test
     * cases will be invoked directly.
     * It is an error if no test cases are selected, (or if they have all been excluded.)
     */
    public Status invokeTestCases() {
	// see if test object provides  Status invokeTestCase(Method m)
	Method invoker;
	try {
	    invoker = testClass.getMethod("invokeTestCase", new Class[] {Method.class});
	    if (!Status.class.isAssignableFrom(invoker.getReturnType()))
		invoker = null;
	}
	catch (NoSuchMethodException e) {
	    invoker = null;
	}

	MultiStatus ms = new MultiStatus(log);
	for (Enumeration e = enumerate(); e.hasMoreElements(); ) {
	    Method m = (Method)(e.nextElement());
	    Status s;
	    try {
		if (invoker != null)
		    s = (Status)invoker.invoke(test, new Object[] {m});
		else
		    s = (Status)m.invoke(test, noArgs);
	    }
	    catch (IllegalAccessException ex) {
		s = Status.failed("Could not access test case: " + m.getName());
	    }
	    catch (InvocationTargetException ex) {
		printStackTrace(ex.getTargetException());
		s = Status.failed("Exception from test case: " +
				       ex.getTargetException().toString());
	    }
	    catch (ThreadDeath t) {
		printStackTrace(t);
		throw t;
	    }
	    catch (Throwable t) {
		printStackTrace(t);
		s = Status.failed("Unexpected Throwable: " + t);
	    }

	    ms.add(m.getName(), s);
	}
	if (ms.getTestCount() == 0)
	    return Status.error("No test cases executed.");
	else
	    return ms.getStatus();
    }

    /**
     * Print a stack trace for an exception to the log.
     */
    protected void printStackTrace(Throwable t) {
	if (log != null) {
	    PrintStream ps = new PrintStream(new WriterStream(log));
	    t.printStackTrace(ps);
	    ps.close();
	}
    }

    /**
     * Look up a test case in the test object.
     * @param name the name of the test case; it must identify a method
     *		Status name()
     * @returns the selected method
     * @throws Fault if the name does not identify an appropriate method.
     */
    private Method getTestCase(String name) throws Fault {
	try {
	    Method m = testClass.getMethod(name, noArgTypes);
	    if (!Status.class.isAssignableFrom(m.getReturnType()))
		throw new Fault("Method for test case '" + name + "' has wrong return type" ); 
	    return m;
	} 
	catch (NoSuchMethodException e) {
	    throw new Fault("Could not find test case: " + name);
	} 
	catch (SecurityException e) {
	    throw new Fault(e.toString());
	}
    }

    private String[] split(String s) {
	Vector v = new Vector();
	int start = 0;
	for (int i = s.indexOf(','); i != -1; i = s.indexOf(',', start)) {
	    v.addElement(s.substring(start, i));
	    start = i + 1;
	}
	if (start != s.length())
	    v.addElement(s.substring(start));
	String[] ss = new String[v.size()];
	v.copyInto(ss);
	return ss;
    }

    private Object test;
    private Class testClass;
    private Hashtable selectedCases = new Hashtable();
    private Hashtable excludedCases = new Hashtable();
    private PrintWriter log;

    private static final Object[] noArgs = { };
    private static final Class[] noArgTypes = { };
}
