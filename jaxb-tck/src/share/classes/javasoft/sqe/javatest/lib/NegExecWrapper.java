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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A wrapper class used by JCKScript for negative runtime tests.
 * Args:
 * <pre>
 *   [-expectedException exception-class-name] executeClass executeArgs
 * </pre>
 * It tries to load executeClass and call the run method defined on it.
 * It is deemed to pass if an expected exception is thrown. The expected
 * exception can be sepcified as an option or defaults to LinkageError.
 *
 * <p> The wrapper uses the standard protocol for JCK VM tests: 
 * 95: passed, 97: failed.
 */

public class NegExecWrapper {

    public static void main(String args[]) {
	System.exit(run(args, System.err) + 95/*STATUS_TEMP*/);
    }

    public static int run(String args[], PrintStream out) {
	String executeClassName = null;
	String[] executeArgs = null;
	String expectedExceptionName = "java.lang.LinkageError";
	Class expectedException = null;

	/*
	 * Decode the arguments, looking for
	 * - optional expected exception name
	 * - execute class name
	 * - execute args
	 */
	for (int i = 0; i < args.length; i++) {
	    if (args[i].equals("-expectedException")) {
		expectedExceptionName = args[++i];
	    }
	    // else check for other options
	    // ...
	    // else check for executeClass and executeArgs
	    else if (!args[i].startsWith("-")) {
		executeClassName = args[i++];
		executeArgs = new String[args.length - i];
		System.arraycopy(args, i, executeArgs, 0, executeArgs.length);
		break;
	    }
	    else
		return failed(out, "Unrecognized argument: " + args[i]);
	}
	

	/*
	 * Validate arguments
	 */
	if (executeClassName == null)
	    return failed(out, "No execute class specified");

	try {
	    expectedException = Class.forName(expectedExceptionName);
	    if (!Throwable.class.isAssignableFrom(expectedException))
		return failed(out, "Expected exception class is not a subtype of Throwable");
	}
	catch (ClassNotFoundException e) {
	    return failed(out, "Expected exception class not found");
	}


	/*
	 * Start the real work of the test ...
	 * Try loading the executeClass
	 * ... if we get an expected exception, the test passes.
	 */
	Class executeClass = null;

	try {
	    executeClass = Class.forName(executeClassName);
	}
	catch (ThreadDeath e) {
	    throw e;
	}
	catch (Throwable e) {
	    if (ClassNotFoundException.class.isInstance(e))
		return passed(out, "Received valid exception while loading test: " + e);
	    else
		return failed(out, "Received unexpected exception while loading test: " + e);
	}


	/*
	 * The class loaded OK. This either means that the verifier
	 * has failed to detect an error in the class, or that the
	 * class has an error which is not required to be detected
	 * during loading. See JVM spec, p126, "Pass 4".
	 *
	 * To force the error, we try and invoke the following method:
	 *	public static int run(String[], PrintStream method)
	 * via reflection, which first requires we get the corresponding 
	 * Method.
	 */

	Class[] runArgTypes = {String[].class, PrintStream.class};
	Method runMethod = null;

	try {
	    runMethod = executeClass.getMethod("run", runArgTypes);
	} 
	catch (SecurityException e) {
	    return failed(out, "Security exception accessing run method in the test class: " + e);
	}
	catch (NoSuchMethodException e) {
	    return failed(out, "Test method `run(String[],PrintStream)' not found in the test class: " + e);
	} 
	catch (ThreadDeath e) {
	    throw e;
	}
	catch (Throwable e) {
	    return failed(out, "Unexpected exception on executeClass.getMethod() invocation: " + e);
	}


	/*
	 * Assuming we got a handle on the run method, we try and invoke it.
	 * ... if we get an expected exception, the test passes.
	 */
	Object[] invokeArgs = {executeArgs, out};

	try {
	    Object ignore = runMethod.invoke(null, invokeArgs);
	    // ignore the result; if it returns, the test must have failed
	} 
	catch (NullPointerException e) {
	    return failed(out, "Test method `run(String[],PrintStream)' must be static in the test class");
	} 
	catch (IllegalAccessException e) {
	    return failed(out, "Test method `run(String[],PrintStream)' is not accessible in the test class");
	} 
	catch (InvocationTargetException e) {
	    Throwable ee = e.getTargetException();
	    if (expectedException.isInstance(ee))
		return passed(out, "Received valid exception while running test: " + e);
	    else 
		return failed(out, "Received unexpected exception while running test: " + e);
	}
	catch (ThreadDeath e) {
	    throw e;
	}
	catch (Throwable e) {
	    return failed(out, "Unexpected exception invoking test method `run(String[],PrintStream)': " + e);
	}

	return failed(out, "Test class unexpectedly loaded and executed");
    }

    private static int passed(PrintStream out, String reason) {
	// The prefix will be recognized by JavaTest, and will make the reason
	// appear in the status
	out.println("STATUS:Passed. " + reason);
	return 0/*STATUS_PASSED*/;

    }

    private static int failed(PrintStream out, String reason) {
	// The prefix will be recognized by JavaTest, and will make the reason
	// appear in the status
	out.println("STATUS:Failed. " + reason);
	return 2/*STATUS_FAILED*/;
    }
}
