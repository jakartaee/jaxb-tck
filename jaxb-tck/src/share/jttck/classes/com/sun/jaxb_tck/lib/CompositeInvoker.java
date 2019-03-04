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

import java.util.ArrayList;
import java.io.PrintStream;

/**
 * Composite invocation.
 * 
 * @author Leonid Kuskov
 * @version 1.5
 */
public abstract class CompositeInvoker extends Invoker {

	//Arguments.
	protected 	String[] args;

	/**
	 * ctor
	 */
	public CompositeInvoker(String[] args) {
		super();
	    this.args = args;
	}
	
	
	// Components container
	private ArrayList<Invoker> container = new ArrayList<Invoker>();

	/**
	 * Walks through the list of Invoker(s) placed in container, performs the
	 * invocation.
	 */
	@Override
	protected final int invoke(PrintStream out, PrintStream err) throws Exception {
		
	  synchronized (this) {
		  int rc = PASSED;
			for (Invoker i : container) {
			  try {
			    rc = i.execute(out, err);
			  } catch(InterruptedException ex) {
			    
			  }
				if (rc != PASSED)
					break;
			}
			try {
				Thread.sleep(500);
			} catch(InterruptedException ex){/*ignore*/}
			return rc;
		}
	  
	}

	/**
	 * Engages the invokers list.
	 * 
	 * @param i The invoker to be used in the list.
	 */
	public void add(Invoker i) {
		container.add(i);
	}
}
