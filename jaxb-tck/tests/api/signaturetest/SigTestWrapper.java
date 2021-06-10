/*
 * Copyright (c) 2003, 2021 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.signaturetest;

import com.sun.javatest.Test;
import com.sun.javatest.Status;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class wraps the signature test.
 * It prepares the arguments and invokes the signature test.
 * It uses the property 'java.version' in order to choose the signature file.
 *
 * @author  Evgueni Astigueevitch
 * @version 1.2
 */
public class SigTestWrapper implements Test {

    private static String FILE_SEPARATOR =
        System.getProperty("file.separator");


    private static String[] excludeJdkClasses = {
          "java.util.Map",
          "java.lang.Object",
          "java.io.ByteArrayInputStream",
          "java.io.InputStream",
          "java.lang.Deprecated",
          "java.io.Writer",
          "java.io.OutputStream",
          "java.util.List",
          "java.util.Collection",
          "java.lang.instrument.IllegalClassFormatException",
          "javax.transaction.xa.XAException",
          "javax.xml.transform.sax",
          "java.lang.annotation.Repeatable",
          "java.lang.InterruptedException",
          "java.lang.CloneNotSupportedException",
          "java.lang.Throwable",
          "java.lang.Thread",
          "java.lang.Enum",
          "org.xml.sax"
    };

    private static final String EXCLUDE_JDK_CLASS_FLAG = "-IgnoreJDKClass";

    private static final String FILENAME_FLAG = "-FileName";



    /**
     * Implementation of method com.sun.javatest.Test.run()
     */
    public Status run(String [] args, PrintWriter err, PrintWriter out) {

        List command = new ArrayList();

        for(int i=0; i < args.length; i++){
          command.add(args[i]);
        }

        String fileName = "sig" + FILE_SEPARATOR, testedPackage, sigTestArgs[];

        testedPackage = getTestedPackage(args);
        if (testedPackage == null) {
            return Status.failed("Package not specified.");
        }
        fileName += testedPackage + ".sig";

        command.add(FILENAME_FLAG);
        command.add(fileName);
        
        for(String jdkClassName:excludeJdkClasses) {
          command.add(EXCLUDE_JDK_CLASS_FLAG);
          command.add(jdkClassName);
        }

        sigTestArgs = ((String[]) command.toArray(new String[command.size()]));
       
        out.println("debug info:");
        out.println("  java.version: " + System.getProperty("java.version"));
        out.println("  sigtest args: ");
        for (int i = 0; i < sigTestArgs.length; ++i) {
                out.println("    " + sigTestArgs[i]);
        }

        return runSignatureTest(sigTestArgs, err, out);
    }

    
    private String getTestedPackage(String args[]) {
        int i;

        for (i = 0; i < args.length && !args[i].startsWith("-Package"); i++);
        return (i == args.length) ? null : args[i+1];
    }

    private Status runSignatureTest(String args[], PrintWriter err,
                                    PrintWriter out) {
        com.sun.tdk.signaturetest.Test test = new com.sun.tdk.signaturetest.Test();
        return test.run(args, err, out);
    }

    public static void main(String [] args) {
        PrintWriter err = new PrintWriter(System.err, true);
        PrintWriter out = new PrintWriter(System.out, true);
        Status status = new SigTestWrapper().run(args, err, out);
        err.flush();
        out.flush();
        status.exit();
    }
}
