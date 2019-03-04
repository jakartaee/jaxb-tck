/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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
 */

package com.sun.jaxb_tck.lib;

import java.io.PrintWriter;
import java.io.File;
import java.io.StringWriter;
import java.util.StringTokenizer;

import com.sun.javatest.Command;
import com.sun.javatest.Status;

import java.lang.reflect.Method;

/**
 * ExecStdTestSameJVMCmd executes a test (one that implements the Test
 * interface) in the same Java Virtual Machine as the caller.
 *
 * @author Evgueni M. Astigueevitch
 * @version 1.9
 */
public class ExecTestSameJVMCmd extends Command {

    private static final int INVOCATION_LIMIT = 50;

    private static volatile Counter counter = new Counter();

    /**
     * The method that that does the work of the command.
     *
     * @param args
     *            [-loadDirs <em>dirs</em>]
     *            <em>executeClass</em> <em>executeArgs</em>
     * @param log
     *            A stream to which to report messages and errors
     * @param ref
     *            A stream to which to write reference output
     * @return The result of the command
     */
    public Status run(String[] args, PrintWriter log, PrintWriter ref) {
        String className = null;
        String[] executeArgs = {};
        ClassLoader loader = null;

        int i = 0;
        for (; i < args.length && args[i].startsWith("-"); i++) {
            if ("-loadDirs".equals(args[i]) && i + 1 < args.length) {
                loader = DirsClassLoader.newInstance(split(args[++i]),
                        ExecTestSameJVMCmd.class.getClassLoader());
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

        Class c = null;
        try {
            c = Class.forName(className, true, loader);

            // Try to pass classloader into JAXB tests using Thread context
            // to avoid adding ClassLoader paremeter into a lot of methods
            Thread.currentThread().setContextClassLoader(loader);

            // execute test
            Method m = c.getMethod("run", new Class[] { String[].class,
                    PrintWriter.class, PrintWriter.class });
            Object result = m.invoke(c.newInstance(), executeArgs, log, ref);
            if (result instanceof Status) {
                return (Status) result;
            }

            // get Status
            Method m1 = result.getClass().getMethod("getType");
            Method m2 = result.getClass().getMethod("getReason");
            return new Status(((Integer) m1.invoke(result)).intValue(),
                    (String) m2.invoke(result));

        } catch (NoSuchMethodException e) {
        	e.printStackTrace(log);
            return Status.failed("Can't load test: " + e);
        } catch (java.lang.reflect.InvocationTargetException e) {
        	e.printStackTrace(log);
            return Status.failed("Can't load test: " + e.getCause());
        } catch (ClassCastException e) {
        	e.printStackTrace(log);
            return Status.failed("Can't load test: " + e);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace(log);
            return Status.failed("Can't load test: " + e);
        } catch (InstantiationException e) {
        	e.printStackTrace(log);
            return Status.failed("Can't instantiate test: " + e);
        } catch (IllegalAccessException e) {
        	e.printStackTrace(log);
            return Status.failed("Illegal access to test: " + e);
        } catch (VerifyError e) {
        	e.printStackTrace(log);
            return Status
                    .failed("Class verification error while trying to load test class `"
                            + className + "': " + e);
        } catch (LinkageError e) {
        	e.printStackTrace(log);
            return Status
                    .failed("Class linking error while trying to load test class `"
                            + className + "': " + e);
        } finally {
            className = null;
            executeArgs = null;
            c = null;
            loader = null;
            if (counter.getValue() >= INVOCATION_LIMIT) {
                counter.reset();
                System.runFinalization();
                System.gc();
            }
        }
    }

    private static File[] split(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s, File.pathSeparator);
        int n = tokenizer.countTokens();
        File[] path = new File[n];
        for (int i = 0; i < n; i++) {
            path[i] = new File(tokenizer.nextToken());
        }

        tokenizer = null; // GC help

        return path;
    }
}
