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
import com.sun.javatest.util.StringArray;

/**
 * A Script to compile/rmic/execute an RMI test.
 *
 * @author Jonathan J Gibbons
 * @version @(#)RmicTestScript.java	1.21 02/01/03
 */
public class RmicTestScript extends Script
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

	    if (compileStatus.getType() == Status.PASSED) {
		String rmiClasses = td.getParameter("rmicClasses");
		// backwards compatability
		if (rmiClasses == null)
		    rmiClasses = td.getParameter("rmicClass");
		if (rmiClasses == null)
		    return fail_noRmicClasses;
		compileStatus = rmicompile(StringArray.split(rmiClasses));
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

	    Status executeStatus = execute(executeClass, td.getParameter("executeArgs"));

	    if (expectFail) {
		if (executeStatus.getType() == Status.FAILED) 
		    return pass_execFailExp.augment(executeStatus);
		else
		    return fail_execSuccUnexp.augment(executeStatus);
	    } else
		return executeStatus;
	}

	return error_noActionSpecified;
    }


    /**
     * RMI Compile the given class files.  The compiler and arguments to be used
     * is identified by the `<code>env.<em>env</em>.command.rmic</code>'
     * property in the script's environment, where <em>env</em>
     * is the name of the environment specified to the GUI.
     * The name of the classes to be compiled by rmic is obtained from the
     * test description.
     * @param classes   The names of the classes to be compiled by rmic.
     * @return          The status of the compilation: passed or failed.
     * @see #init
     * @see #invoke
     */
    private Status rmicompile(String[] classes) {
        try {
            String[] classDir = env.lookup("testClassDir");
            if (classDir == null || classDir.length != 1)
                return Status.failed("classDir not a file URL");
            File f = new File(classDir[0]);
            if (!f.exists())
                f.mkdirs();
        }
        catch (TestEnvironment.Fault e) {
            return Status.failed("problem finding testClassDir");
        }

	env.put("testRmicClasses", classes);
	// backwards compatability
	env.put("testRmicClass", classes);
        return invokeCommand("rmic");
    }

    private static final Status fail_noRmicClasses = Status.failed("no rmicClasses specified");
}
