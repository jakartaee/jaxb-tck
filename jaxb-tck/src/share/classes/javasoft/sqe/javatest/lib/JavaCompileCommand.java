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
 * Invoke a Java compiler via reflection.
 * The compiler is assumed to have a constructor and compile method
 * matching the following signature:
 *<pre>
 * public class COMPILER {
 *     public COMPILER(java.io.OutputStream out, String compilerName);
 *     boolean compile(String[] args);
 * }
 *</pre>
 * or
 *<pre>
 * public class COMPILER {
 *     public COMPILER();
 *     int compile(String[] args);
 * }
 *</pre>
 * This means the command is suitable for (but not limited to) the
 * compiler javac suplied with JDK. (Note that this uses an internal
 * API of javac which is not documented and is not guaranteed to exist
 * in any specific release of JDK.)
 * @see com.sun.javatest.lib.JavaCompileCommand
 *
 * @author Jonathan J Gibbons
 * @version 10/15/97
 */
public class JavaCompileCommand extends com.sun.javatest.lib.JavaCompileCommand
{
}
