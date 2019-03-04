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
 * ExecJCKTestSameJVMCmd executes a JCK test in the same Java Virtual 
 * Machine as the caller of this class.
 *
 * <p> JCK tests may be "standard" tests, which implement the Test interface,
 * and which return a result via the value returned from the run method,
 * or they may be a "simple" which implements a standard simple method
 * which an integer return code.
 *
 * <p> It can use either a private class loader or the system class loader. 
 * A private class loader will be created if the -loadDir option is given; 
 * otherwise the system class loader will be used.  A private class
 * loader minimises the interference between tests, but you may be 
 * restricted from using private class loaders if you are running the 
 * harness inside a web browser.
 *
 * @see com.sun.jck.lib.ExecJCKTestSameJVMCmd
 * @see com.sun.jck.lib.ExecJCKTestOtherJVMCmd
 * @see javasoft.sqe.javatest.lib.ExecStdTestSameJVMCmd
 *
 * @author Jonathan J Gibbons
 * @version 10/15/97
 */
public class ExecJCKTestSameJVMCmd extends com.sun.jck.lib.ExecJCKTestSameJVMCmd
{
}
