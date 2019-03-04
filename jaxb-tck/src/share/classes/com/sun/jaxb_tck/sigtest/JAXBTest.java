/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

import com.sun.tdk.signaturetest.SignatureTest;
/**
 * @test
 */

public class JAXBTest implements com.sun.javatest.Test {

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
        t.run(args, log, ref);
        return com.sun.javatest.Status.parse(t.toString().substring(7));
    }

}
