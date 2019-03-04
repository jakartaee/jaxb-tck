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
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Wrapper to compile schema(s) in sameJVM mode. It invokes the schema generate
 * method that is supplied by SUN's reference implementation.
 * 
 * @author Leonid Kuskov
 * @version 1.6
 */

public class SchemaCompiler implements SchemaCompilerTool {

	public int compile(String[] xsdFiles, String packageName, File outDir, 
			           final PrintStream out, final PrintStream err) throws Exception {

		final ArrayList<String> schemaCompilerArgs = new ArrayList<String>();
		schemaCompilerArgs.add("-d");
		schemaCompilerArgs.add(outDir.getAbsolutePath());

		schemaCompilerArgs.add("-p");
		schemaCompilerArgs.add(packageName);

		if (xsdFiles != null) {
			for (String xsdFile : xsdFiles) {
				schemaCompilerArgs.add(xsdFile);
			}
		}
		final Integer[] _rc = new Integer[] { -1 };
		// run all the work in another thread.
		final Throwable[] _ex = new Throwable[1];
		Thread th = new Thread() {
			public void run() {
				try {
					_rc[0] = _xjc(schemaCompilerArgs.toArray(new String[]{}), out, err);
				} catch (Throwable e) {
					_ex[0] = e;
				}
			}
		};
		th.start();
		th.join();
		if (_ex[0] != null) {
			// re-throw
			if (_ex[0] instanceof Exception)
				throw (Exception) _ex[0];
			else
				throw (Error) _ex[0];
		}
		return _rc[0];
	}
	
	private static int _xjc(String[] args, PrintStream out, PrintStream err) throws Exception{
		try {
			return com.sun.tools.xjc.Driver.run(args, out, err);
		} catch (Throwable ex) {
			String msg = ex.getMessage();
			throw new Exception("The xjc invoker Driver.run(" + 
					_toString(args) + ") failed\n" 
					+ (msg != null ? " with the message \"" + msg + "\" " : ""));
		}
	}
	
	static private String _toString(String[] a){
		StringBuilder sb = new StringBuilder(" ");
		for(String s : a){
				sb.append(s + ", ");
		}
		sb.deleteCharAt(sb.length()-2);
		return sb.toString();
	}    
}
