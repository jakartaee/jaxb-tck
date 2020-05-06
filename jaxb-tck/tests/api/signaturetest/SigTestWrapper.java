/*
 * Copyright (c) 2003, 2020 Oracle and/or its affiliates. All rights reserved.
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

/**
 * This class wraps the signature test.
 * It prepares the arguments and invokes the signature test.
 * It uses the property 'java.version' in order to choose the signature file.
 *
 * @author  Evgueni Astigueevitch
 * @version 1.2
 */
public class SigTestWrapper implements Test {

    private final static int JAVA_5_0 = 50;
    private final static int JAVA_6_0 = 60;
    private final static int JAVA_7_0 = 70;
    private final static int JAVA_8_0 = 80;
    private final static int JAVA_9_0 = 90;
    private final static int UNSUPPORTED_JAVA = -1;

    private static String FILE_SEPARATOR =
        System.getProperty("file.separator");

    private static String ROOT_DIR = "../../../tempTestStorage/JAXB-TCK/tests/api/signaturetest";

    /**
     * Implementation of method com.sun.javatest.Test.run()
     */
    public Status run(String [] args, PrintWriter err, PrintWriter out) {
        String fileName = ROOT_DIR + FILE_SEPARATOR + "sig" + FILE_SEPARATOR, testedPackage, sigTestArgs[];

        switch (getJavaVersion()) {

        case JAVA_5_0:
            fileName += "5.0";
            break;
        case JAVA_6_0:
            fileName += "6.0";
            break;
        case JAVA_7_0:
            fileName += "7.0";
            break;
        case JAVA_8_0:
            fileName += "8.0";
            break;
        case JAVA_9_0:
            fileName += "9.0";
            break;
        default:
            return Status.failed("Unsupported Java version: "
                                 + System.getProperty("java.version"));
        }

        fileName += FILE_SEPARATOR;
        testedPackage = getTestedPackage(args);
        if (testedPackage == null) {
            return Status.failed("Package not specified.");
        }
        fileName += testedPackage + ".sig";

        sigTestArgs = new String[args.length + 2];
        System.arraycopy(args, 0, sigTestArgs, 0, args.length);
        sigTestArgs[args.length] = "-FileName";
        sigTestArgs[args.length + 1] = fileName;

        out.println("debug info:");
        out.println("  java.version: " + System.getProperty("java.version"));
        out.println("  sigtest args: ");
        for (int i = 0; i < sigTestArgs.length; ++i) {
                out.println("    " + sigTestArgs[i]);
        }

        return runSignatureTest(sigTestArgs, err, out);
    }

    private int getJavaVersion() {
        String javaVersion = System.getProperty("java.version");

        if (javaVersion.startsWith("1.5") || javaVersion.startsWith("5.0")) {
            return JAVA_5_0;
        } else if (javaVersion.startsWith("1.6.0") || javaVersion.startsWith("6.0")) {
            return JAVA_6_0;
        } else if (javaVersion.startsWith("1.7.0") || javaVersion.startsWith("7.0")) {
            return JAVA_7_0;
        } else if (javaVersion.startsWith("1.8.0") || javaVersion.startsWith("8.0")) {
            return JAVA_8_0;
        } else if (javaVersion.startsWith("1.9") || javaVersion.startsWith("9")) {
            return JAVA_9_0;
        } else {
            return UNSUPPORTED_JAVA;
        }
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

