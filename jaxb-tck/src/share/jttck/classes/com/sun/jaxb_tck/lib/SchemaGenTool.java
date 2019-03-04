/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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
import java.io.PrintStream;

/**
 * Interface to invoke a schema generator in sameJVM mode.
 * 
 * @author   Leonid Kuskov
 * @version  1.5
 */
public interface SchemaGenTool {
	/**
	 * @param javaFiles array of string containing java source files 
	 * @param outDir output directory where xml schema file(s) will be generated
	 * @param out output stream for logging
	 * @param err error stream for logging
	 * @return  0 if xml schema file(s) generated successfully
	 */
	int generate(String[] javaFiles, File outDir, 
			final PrintStream out, final PrintStream err) throws Exception;
}
