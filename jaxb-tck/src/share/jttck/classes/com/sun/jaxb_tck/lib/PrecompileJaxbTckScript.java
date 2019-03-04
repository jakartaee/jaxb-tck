/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.io.File;

import com.sun.javatest.Status;
import com.sun.javatest.util.StringArray;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;

/**
 * Script to precompile JAXB tests.
 *
 * @author Evgueni M. Astigueevitch
 * @version 1.31
 */
public class PrecompileJaxbTckScript extends JaxbTckScript {

    protected boolean needCompileStage() {
        return isRuntime;
    }

    protected boolean needExecuteStage() {
        return false;
    }

    protected Status compile() {
        File[] sources = td.getSourceFiles();
        if( debug ) System.out.println( "PrecompileJaxbTckScript.compile: " + java.util.Arrays.toString( sources ) );
        if (sources == null || sources.length == 0) {
            return error_noSource;
        }

        env.put("testClassDir", testSuiteRootDir + File.separator + "classes");

        // check the 'testSource' item existence
        String testSource = td.getParameter( "testSource" );
        if( testSource != null ){
            String[] srcs = StringArray.split(testSource);

            // copied from TestDescription class
            String dir = td.getFile().getParent();
            File[] sourceFiles = new File[srcs.length];
            String userCurrDir = System.getProperty("user.dir") + File.separator;
            for (int i = 0; i < srcs.length; i++) {
                File f = new File(dir, srcs[i].replace('/', File.separatorChar));
                String s = f.getPath();
                if (s.startsWith(userCurrDir)) {
                    s = s.substring(userCurrDir.length());
                    sourceFiles[i] = new File(s);
                } else
                    sourceFiles[i] = f;
            }
            // end of copy

            sources = sourceFiles;
        if( sources.length == 0 )
            return Status.passed( "OK" );
        }

        if (isSchema || isDocument) {
        	if ( onlyJavaSourcesPresented(sources) )
        		return compileTogether(sources);
        	return compileIndividually(sources);
        }
        return compileTogether(sources);
    }

    protected Status compileIndividually(File[] sources) {
        if( debug ) System.out.println( "PrecompileJaxbTckScript.compileIndividually: " + java.util.Arrays.toString( sources ) );
        Status status = null;
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].getName().endsWith(".xsd")) {
                status = super.compile();
            } else if (sources[i].getName().endsWith(".java")) {
                status = super.compileOne(sources[i]);
            } else {
                status = Status.error("Don't know how to compile: "
                        + sources[i]);
            }

            if (!status.isPassed()) {
                return status;
            }
        }

        return status;
    }

    protected Status execute() {
        return Status.passed("OK");
    }

    protected void initTestPackage() {
        if ((isSchema && isPositive) || isDocument) {
            super.initTestPackage();
        }
    }

    protected void initSchema() {
        if ((isSchema && isPositive) || isDocument) {
            super.initSchema();
        }
    }

    protected void initOutputDir() throws InitializationException {
        if ((isSchema && isPositive) || isDocument) {
            super.initOutputDir();
        }
    }

    /**
     * do not precompile java-to-schema tests
     */
    protected Status generate() {
        return Status.passed("OK");
    }
    
    private boolean onlyJavaSourcesPresented(File[] sources) {
        for (int i = 0; i < sources.length; i++) {
             if (!sources[i].getName().endsWith(".java")) {
                 return false;
             }
        }
        return true;
    }

    @Override
    public Status run(String[] args, TestDescription td, TestEnvironment env) {
        if( debug ) System.out.println( "PrecompileJaxbTckScript.run " + td.getId() );
        initKeywords(td);
        if (!isRuntime || isRtgen)
            return Status.passed("OK. Precompilation isn't required.");
        return super.run(args, td, env);
    }
}
