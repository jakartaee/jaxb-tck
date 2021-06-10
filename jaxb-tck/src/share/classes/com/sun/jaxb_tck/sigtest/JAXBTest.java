/*
 * Copyright (c) 2005, 2021 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jaxb_tck.sigtest;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sun.tdk.signaturetest.SignatureTest;
/**
 * @test
 */

public class JAXBTest implements com.sun.javatest.Test {

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


    /**
     * Run the test using command-line; return status via numeric exit code.
     */
    public static void main(String[] args) {
        JAXBTest t = new JAXBTest();
        t.run(args, new PrintWriter(System.err, true),
                new PrintWriter(System.out, true)).exit();
    }


    /**
     * This is the gate to run the test with the JavaTest application.
     *
     * @param log This log-file is used for error messages.
     * @param ref This reference-file is ignored here.
     */
    public com.sun.javatest.Status run(String[] args, PrintWriter log, PrintWriter ref) {
        SignatureTest t = new SignatureTest();
       
        List command = new ArrayList();

        for(int i=0; i < args.length; i++){
          command.add(args[i]);
        }

        for(String jdkClassName:excludeJdkClasses) {
          command.add(EXCLUDE_JDK_CLASS_FLAG);
          command.add(jdkClassName);
        }

        String[] sigTestArgs = ((String[]) command.toArray(new String[command.size()]));

        t.run(sigTestArgs, log, ref);
        return com.sun.javatest.Status.parse(t.toString().substring(7));
    }

}
