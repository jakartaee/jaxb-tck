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

package com.sun.tdk.signaturetest;

import java.io.PrintWriter;

import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.Test;

/**
 * Wraper to execute Sigtest Setup in sameJVM mode.
 *
 * @author   Evgueni M. Astigueevitch
 * @version  1.6
 */
public class SigtestSetupRun implements Test {

    public Status run(String[] args, PrintWriter err, PrintWriter out) {
        Setup setupRun = new Setup();
        setupRun.run(args, err, out);
        String status = setupRun.toString();
        return (setupRun.isPassed()) ? Status.passed(status) : Status.failed(status);
    }
    public static void main(String[] args) {
        SigtestSetupRun sr = new SigtestSetupRun();
        Status status = sr.run(args, null, null);
        System.out.println(status.getReason());
    }
}
