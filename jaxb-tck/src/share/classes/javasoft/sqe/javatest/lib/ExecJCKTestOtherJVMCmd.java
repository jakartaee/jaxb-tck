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

/**
 * This is a modification of <code>ProcessCommand</code> suitable
 * for executing JCK tests in an separate JVM. JCK tests may be "standard"
 * tests which implement the Test interface and which report their exit 
 * status by calling <code>status.exit()</code>, or they may be
 * "simple" tests which report their exit status by an exit code alone.
 *
 * @deprecated
 * @see com.sun.jck.lib.ExecJCKTestOtherJVMCmd
 * @see com.sun.jck.lib.ExecJCKTestSameJVMCmd
 * @see javasoft.sqe.javatest.lib.ExecStdTestOtherJVMCmd
 *
 * @author Jonathan J Gibbons
 * @version 10/15/97
 **/
public class ExecJCKTestOtherJVMCmd extends com.sun.jck.lib.ExecJCKTestOtherJVMCmd
{
}
