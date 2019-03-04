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

import java.io.PrintStream;

/**
 * Wrapper to generate a schema
 * 
 * @author Leonid Kuskov
 * @version 1.6
 */
public class Jxc extends CompositeInvoker {

	private boolean isSetIOAllowed;
	
	{
		SecurityManager sm = System.getSecurityManager();
		if (sm == null) {
			isSetIOAllowed = true;
		} else {
			try {
				sm.checkPermission(new RuntimePermission("setIO"));

				isSetIOAllowed = true;
			} catch (SecurityException e) {
				isSetIOAllowed = false;
			}
		}
	}

	/**
	 * ctor
	 */
	public Jxc(String[] args) {
	    super(args);
	}

	/**
	 * Processes command line arguments.
	 */
	@Override
	public void processArguments() throws Invoker.ArgumentException {
		add(new SchemaGenInvoker(args));
	}

	/**
	 * Performs the schema generation
	 */
	@Override
	protected int execute(PrintStream out, PrintStream err) throws Exception {
		PrintStream oldSystemOut = System.out;
		PrintStream oldSystemErr = System.err;
		try {
			if (isSetIOAllowed) {
				System.setOut(out);
				System.setErr(err);
			}
			return super.execute(out, err);
		} finally {
			err.flush();
			out.flush();

			if (isSetIOAllowed) {
				System.setOut(oldSystemOut);
				System.setErr(oldSystemErr);
				oldSystemOut = null;
				oldSystemErr = null;
			}
		}
	}
}
