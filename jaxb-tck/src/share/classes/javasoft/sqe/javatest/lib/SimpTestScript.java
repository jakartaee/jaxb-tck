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
import com.sun.javatest.Script;
import com.sun.javatest.Status;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;

/**
 * A Script to compile/execute a test using the "simple" invocation protocol.
 *
 * @author Jonathan J Gibbons
 * @version @(#)SimpTestScript.java	1.17 02/01/03
 */
public class SimpTestScript extends Script
{
    /**
     * Compile and/or execute a test.
     * The sources will be compiled if `-compile', `-compileTogether' or
     * `-compileIndividually' is given;  the test will be executed if
     * `-execute' is given. Either one or both of the compile options
     * and the execute option must be given.  `-compile' is a equivalent to
     * `-compileTogether'.
     *
     * By default, the compilation and execution are both expected to succeed.
     * However, if `-expectFail' is given as well as `-execute', the execution
     * of the test will be expected to fail, in which case the test as a whole
     * will be deemed to have passed.)
     * If `-expectFail' is given but the test is simply compiled, and not executed,
     * the expectation of failure applies to the compilation.
     *
     * If `-useWrapper' is set, a wrapper class will be called. This is typically
     * necessary when running simple tests in the same JVM when reflection is not
     * an option (i.e. JDK 1.0.2 or equivalent.)
     *
     * If `-executeSimple' is set, the class will be executed via the executeSimple
     * command property; this will typically be necessary because the test should be
     * executed in the same JVM, via the the SimpleExecuteCommand.  This command
     * uses reflection, and does not need a separate wrapper class for each test.
     *
     * @see com.sun.javatest.Script#compileTogether
     * @see com.sun.javatest.Script#compileIndividually
     * @see com.sun.javatest.Script#execute
     *
     */
    public Status run(String[] args, TestDescription td, TestEnvironment env) {
	boolean expectFail = false;
	boolean compile = false;
	boolean compileIndividually = false;
	boolean execute = false;

	for (int i = 0; i < args.length; i++) {
	    String arg = args[i];
	    if (arg.equals("-compile") || arg.equals("-compileTogether") )
		compile = true;
	    else if (arg.equals("-compileIndividually"))
		compile = compileIndividually = true;
	    else if (arg.equals("-execute"))
		execute = true;
	    else if (arg.equals("-expectFail"))
		expectFail = true;
	    else if (arg.equals("-useWrapper"))
		useWrapper = true;
	    else if (arg.equals("-executeSimple"))
		execute = executeSimple = true;
	    else
		return Status.failed("bad arg for script: `" + arg + "'");
	}

	if (compile) {
	    File[] srcs = td.getSourceFiles();

	    Status compileStatus;
	    if (compileIndividually)
		compileStatus = compileIndividually(srcs);
	    else
		compileStatus = compileTogether(srcs);

	    // if compilation so far is OK, and if a wrapper class is required, compile one
	    if ((compileStatus.getType() == Status.PASSED) && useWrapper) {
		String c = td.getParameter("executeClass");
		if (c == null)
		    return error_noExecuteClass;
		int lastDot = c.lastIndexOf('.');
		String w = (lastDot == -1 ? c : c.substring(lastDot+1)) + "t.java";
		File wf = new File(td.getDir(), w);
		compileStatus = compileOne(wf);
	    }

	    // if we're not going to execute the test, this is the end of the task
	    if (!execute) {
		if (expectFail) {
		    if (compileStatus.getType() == Status.FAILED) {
			return pass_compFailExp.augment(compileStatus);
		    } else
			return fail_compSuccUnexp.augment(compileStatus);
		} else
		    return compileStatus;
	    } else {
		// if we want to execute the test, but the compilation failed, we can't go on
		if (compileStatus.getType() == Status.FAILED)
		    return fail_compFailUnexp.augment(compileStatus);
	    }
	}

	if (execute) {
	    String executeClass = td.getParameter("executeClass");
	    if (executeClass == null)
		return error_noExecuteClass;

	    if (useWrapper)
		executeClass += "t";  // standard suffix for wrapper class

	    Status executeStatus = execute(executeClass, td.getParameter("executeArgs"));

	    if (expectFail) {
		if (executeStatus.getType() == Status.FAILED) {
		    return pass_execFailExp.augment(executeStatus);
		} else
		    return fail_execSuccUnexp.augment(executeStatus);
	    } else
		return executeStatus;
	}

	return error_noActionSpecified;
    }

    protected Status execute(String executeClass, String[] executeArgs) {
	env.put("testExecuteClass", executeClass);
	env.put("testExecuteArgs", executeArgs);
	String key = (executeSimple ? "executeSimple" : "execute");
	return invokeCommand(key);
    }

    private boolean executeSimple;
    private boolean useWrapper;

}
