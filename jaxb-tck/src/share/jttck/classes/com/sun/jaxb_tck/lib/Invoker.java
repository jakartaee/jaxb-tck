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

/**
 * Represents the element in the composition @see {@link CompositeInvoker.java}
 *
 * @author  Leonid Kuskov
 * @version 1.3
 */
public abstract class Invoker {
    
    public static class ArgumentException extends Exception {
		private static final long serialVersionUID = -2080407318175799071L;

		public ArgumentException(String message) {
            super(message);
        }
    }

    public static class InvokeException extends Exception {
		private static final long serialVersionUID = 7732323011436329862L;

		public InvokeException(String message) {
            super(message);
        }
    }
    
    public static final String EMPTY_OUT_PARAMETER = "-empty_output";
    
    // Predefined return codes
    protected final static int PASSED = 0;
    protected final static int FAILED = -1;
    
    // Command line argument(s) holder.
    protected Arguments args;
    
    //Output directory.
    protected File outDir;
    
	/**
	 * ctor
	 */
	public Invoker(String[] args) {
	    this.args = new Arguments(args);
	}
    
    
    public Invoker() {}

	protected int execute(PrintStream out, PrintStream err) 
                  throws Exception
    {
        int rc;
        processArguments();
        rc = invoke(out, err);
        return rc;
    }
    
    /**
     * Processes command line arguments.
     */
    protected  void processArguments() throws ArgumentException {
    	String outDirStr = null;
        // Verify output directory.
    	outDirStr = args.getValue("-d");
        if(outDirStr == null) {
            args.removeArgs("-d", 1);
            args.addArgs("-d", ".");
            outDirStr = ".";
        } 
        outDir = new File(outDirStr);
        // remove EMPTY_OUT_PARAMETER parameter.
        if(args.contains(EMPTY_OUT_PARAMETER)) {
            args.removeArgs(EMPTY_OUT_PARAMETER);
        }        
        
    }
    
    /**
     * Invokes command.
     * @throws Exception 
     */
    protected abstract int invoke(PrintStream out, PrintStream err) throws Exception;
}
