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
import java.io.PrintWriter;
import com.sun.javatest.Command;
import com.sun.javatest.Status;
import com.sun.javatest.util.DirectoryClassLoader;
// NOTE: this class also uses
//	javasoft.sqe.javatest.Status
//	javasoft.sqe.javatest.Test


/**
 * ExecStdTestSameJVMCmd executes a standard test (one that implements
 * the Test interface) in the same Java Virtual Machine as JavaTest.
 *
 * It can use either a private class loader or the system class loader.
 * A private class loader will be created if the -loadDir option is given;
 * otherwise the system class loader will be used.  A private class
 * loader minimises the interference between tests, but you may be
 * restricted from using private class loaders if you are running the
 * harness inside a web browser.
 *
 * <p> If the the <code>-repeat</code> option is provided, then the test will be
 * run multiple times in the same JVM.  <code>Status.error()</code> will be
 * returned (and the remainder of the iterations will not be performed) if any
 * repetition of the test returns an error, or if the status return type changes
 * between iterations.  The returned status after each iteration will be
 * included in the log. If this option is not given, the test will be run once.
 *
 * @see javasoft.sqe.javatest.lib.ExecStdTestOtherJVMCmd
 */
public class ExecStdTestSameJVMCmd extends Command
{
    /**
     * The method that that does the work of the command.
     * @param args	[-loadDir <em>dir</em>] [-saveProps] <em>executeClass</em> <em>executeArgs</em>
     * @param log	A stream to which to report messages and errors
     * @param ref	A stream to which to write reference output
     * @return		The result of the command
     */
    public Status run(String[] args, PrintWriter log, PrintWriter ref) {
	int repeat = 1;
	String className = null;
	String[] executeArgs = { };
	ClassLoader loader = getClassLoader();

	int i = 0;

	for (; i < args.length && args[i].startsWith("-"); i++) {
	    if ("-loadDir".equals(args[i]) && i+1 < args.length) {
		// -loadDir is optional; if given, a new class loader will be created
		// to load the class to execute;  if not given, the system class loader
		// will be used.
		loader = new DirectoryClassLoader(new File(args[++i]));
	    } else if ("-repeat".equals(args[i]) && i+1 < args.length) {
		// -repeat is optional; if given, the test will be run that
		// number of times (in the same JVM)
		try {
		    if ((repeat = Integer.parseInt(args[++i])) < 1)
			return Status.error("Unexpected number of repetitions: " + repeat);
		}
		catch (NumberFormatException e) {
		    return Status.error("Unrecognized number of repetitions: " + repeat);
		}
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

	Status status = null;
	try {
	    Class c;
	    if (loader == null)
		c = Class.forName(className);
	    else
		c = loader.loadClass(className);

	    Status prevStatus = null;
	    for (int j = 0; j < repeat; j++) {
		if (repeat > 1)
		    log.println("iteration: " + (j+1));

		javasoft.sqe.javatest.Test t = (javasoft.sqe.javatest.Test)(c.newInstance());
		javasoft.sqe.javatest.Status s = t.run(executeArgs, log, ref);
		status = new Status(s.getType(), s.getReason());

		if (repeat > 1)
		    log.println("   " + status);

		if ((prevStatus != null) && status.getType() != prevStatus.getType())
		    status = Status.error("Return status type changed at repetition: " + (j+1));

		if (status.isError())
		    return status;
		else
		    prevStatus = status;
	    }
	}
        catch (ClassCastException e) {
	    status = Status.failed("Can't load test: required interface not found");
	}
	catch (ClassNotFoundException e) {
	    status = Status.failed("Can't load test: " + e);
	}
	catch (InstantiationException e) {
	    status = Status.failed("Can't instantiate test: " + e);
	}
	catch (IllegalAccessException e) {
	    status = Status.failed("Illegal access to test: " + e);
	}
	catch (VerifyError e) {
	    return Status.failed("Class verification error while trying to load test class `" + className + "': " + e);
	}
	catch (LinkageError e) {
	    return Status.failed("Class linking error while trying to load test class `" + className + "': " + e);
	}
	return status;
    }
}

