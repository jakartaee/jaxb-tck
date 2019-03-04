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
 */

package com.sun.jmpp;

import javax.tools.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * Wrapper for the compiler provided with this platform.
 * It shares file manager across compilation tasks that speed up the process.
 * This class is created to be used by jmpp for the compilation of the intermediate program.
 */

public class CompilerAPIJavac {

    private static PrintStream err = System.err;
    private static StandardJavaFileManager manager = null;
    private static JavaCompiler compiler = null;
    private static int counter = 0;
    /**
     * Limit of compilations per shared compiler and their file manager.
     */
    private static int compilationLimit = -1;

    static {
        String compilationLimitSt = System.getProperty("jmpp.compiler.api.compilationLimit");
        if (compilationLimitSt != null) {
            compilationLimit = Integer.valueOf(compilationLimitSt);
        }
    }

    /**
     * Runs compiler via JSR 199 using shared compiler and java file manager
     */
    public boolean compile(String[] args) {
        decodeAllArgs(args);
        boolean result = false;
        try {
            if (compiler == null) {
                compiler = getCompiler();
            }
            if (manager == null) {
                manager = compiler.getStandardFileManager((DiagnosticListener) null,
                        (Locale) null, (Charset) null);
            }
            result = doCompile(compiler, manager);
            count();
        } catch (Throwable ex) {
            ex.printStackTrace(err);
            return false;
        }
        return result;
    }

    /**
     * Returns compiler tool to be tested.
     */
    protected JavaCompiler getCompiler() {
        JavaCompiler compiler =
                ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException("Default compiler is not available");
        }
        return compiler;
    }

    void decodeAllArgs(Object[] args) {
        String[] sargs = (String[]) args;
        int i = 0;
        while (i < sargs.length) {
            int elementsConsumed = decodeArg(sargs, i);
            if (elementsConsumed == 0) {
                // The argument was not recognized.
                throw new IllegalArgumentException("Could not recognize " +
                        "argument: " + sargs[i]);
            }
            i += elementsConsumed;
        }
    }

    private String classPath;
    private String outDir;
    private String[] testSource;
    private List<? extends File> cpList;

    int decodeArg(String[] args, int index) {
        if (args[index].equals("-cp") || args[index].equals("-classpath") && index + 1 < args.length) {
            classPath = args[index + 1];
            cpList = splitLocation(classPath, File.pathSeparator);
            return 2;
        } else if (args[index].equals("-d") && index + 1 < args.length) {
            outDir = args[index + 1];
            return 2;
        } else {
            testSource = new String[args.length - index];
            System.arraycopy(args, index, testSource, 0, args.length - index);
            return args.length - index;
        }
    }

    /**
     * Converts passed classpath to list of files.
     */
    static private List<? extends File> splitLocation(String location,
                                                      String sep) {
        ArrayList<File> result = new ArrayList<File>();
        StringTokenizer tok = new StringTokenizer(location, sep);
        while (tok.hasMoreElements()) {
            result.add(new File((String) tok.nextElement()));
        }
        return result;
    }

    /**
     * Compiler using selected comiler and fileManager
     */
    protected boolean doCompile(JavaCompiler compiler,
                                StandardJavaFileManager fileManager/*, PrintWriter err*/)
            throws IOException {
        synchronized (fileManager) {
            Iterable<? extends JavaFileObject> compilationUnits = null;
            Iterable<String> classes = null;
            compilationUnits =
                    fileManager.getJavaFileObjectsFromStrings(Arrays.asList(testSource));
            if (cpList != null) {
                fileManager.setLocation(StandardLocation.CLASS_PATH, cpList);
            }
            if (outDir != null) {
                fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                        Collections.singleton(new File(outDir)));
            }
            JavaCompiler.CompilationTask task = compiler.getTask(null,
                    fileManager, null, null, classes, compilationUnits);
            return task.call();
        }
    }

    /**
     * Increments compilations counter of given compiler. If number of compilations
     * greater then limit, then the compiler will be nullified.
     * This means that the compiler instance will no longer be used for compilation.
     */
    private void count() throws IOException {
        if (compilationLimit != -1) {
            if (counter++ >= compilationLimit) {
                try {
                    //clear file manager resources
                    manager.close();
                    compiler = null;
                } finally {
                    counter = 0;
                }
            }
        }
    }
}
