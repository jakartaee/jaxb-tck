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

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.Test;
import javasoft.sqe.javatest.Command;
import com.sun.javatest.util.DirectoryClassLoader;
import com.sun.javatest.util.WriterStream;

/**
 * ExecSimpTestSameJVMCmd executes a test class, using either a private class loader or
 * the system class loader. A private class loader will be created if the -loadDir
 * option is given; otherwise the system class loader will be used.  A private class
 * loader minimises the interference between tests, but you may be restricted from
 * using private class loaders if you are running the harness inside a web browser.
 *
 * The test class must support a method
 * <code>
 *		static int run(String[], java.io.PrintStream)
 * </code>
 *
 * @deprecated use ExecJCKTestSameJVMCmd
 */
public class ExecSimpTestSameJVMCmd extends Command
{
    /**
     * The method that that does the work of the command.
     * @param args	[-loadDir <em>dir</em>] [-saveProps] <em>executeClass</em> <em>executeArgs</em>
     * @param log	A stream to which to report messages and errors
     * @param ref	A stream to which to write reference output
     * @return		The result of the command
     */
    public Status run(String[] args, PrintWriter log, PrintWriter ref) {
	ClassLoader loader = getClassLoader();
	boolean saveProps = false;
	String className = null;
	String[] executeArgs = { };

	int i = 0;

	for (; i < args.length && args[i].startsWith("-"); i++) {
	    if ("-loadDir".equals(args[i]) && i+1 < args.length) {
		// -loadDir is optional; if given, a new class loader will be created
		// to load the class to execute;  if not given, the system class loader
		// will be used.
		loader = new DirectoryClassLoader(new File(args[++i]));
	    }
	}

	// Next must come the executeClass
	if (i < args.length) {
	    className = args[i];
	    i++;
	} else
	    return Status.failed("No executeClass specified");

	// Finally, any optional args
	if (i < args.length) {
	    executeArgs = new String[args.length - i];
	    System.arraycopy(args, i, executeArgs, 0, executeArgs.length);
	}

	try {
	    Class c;
	    if (loader == null)
		c = Class.forName(className);
	    else
		c = loader.loadClass(className);

	    Class[] runParamTypes = {String[].class, PrintStream.class};
	    Method[] mm = c.getDeclaredMethods();

	    if (false) {
		log.println("class: " + c);
		for (int ii = 0;ii < mm.length;ii++)
		    log.println("method " + ii + ": " + mm[ii]);
		log.println("runParamTypes[0]: " + runParamTypes[0]);
		log.println("runParamTypes[1]: " + runParamTypes[1]);
	    }

	    Method runMethod = c.getMethod("run", runParamTypes);

	    if (false)
		log.println("method: " + runMethod);

	    //Object testObj = c.newInstance();
	    //log.println("testObj: " + testObj);
	    Object[] runArgs = {executeArgs, Deprecated.createPrintStream(new WriterStream(log))};
	    Object result = runMethod.invoke(null, runArgs);

	    if (false)
		log.println("result: " + result);

	    switch (((Integer)result).intValue()) {
	    case 0:
		return Status.passed("");
	    case 2:
		return Status.failed("");
	    default:
		return Status.failed("Unexpected exit code: " + result);
	    }
	}
        catch (ClassCastException e) {
	    return Status.failed("run() did not return an integer result");
	}
	catch (ClassNotFoundException e) {
	    return Status.failed("Can't load test: " + e);
	}
	//catch (InstantiationException e) {
	//    return Status.failed("Can't instantiate test: " + e);
	//}
        catch (InvocationTargetException e) {
	    return Status.failed("Problem invoking \"run\" method: " + e);
	}
	catch (IllegalAccessException e) {
	    return Status.failed("Illegal access to test: " + e);
	}
        catch (NoSuchMethodException e) {
	    return Status.failed("Cannot find \"run\" method: " + e);
	}
        catch (NoSuchMethodError e) {
	    return Status.failed("Cannot find \"run\" method: " + e);
	}
	catch (VerifyError e) {
	    return Status.failed("Class verification error while trying to load test class `" + className + "': " + e);
	}
	catch (LinkageError e) {
	    return Status.failed("Class linking error while trying to load test class `" + className + "': " + e);
	}
    }
}
