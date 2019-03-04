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
import java.lang.reflect.Method;

/**
 * Wrapper to invoke schema compilation supplied by SUN's Reference
 * Implementation.
 * 
 * @author Leonid Kuskov
 * @version 1.9
 */
public class JavacInvoker extends Invoker {
	
	/**
	 * ctor
	 */
	public JavacInvoker(String[] args) {
		super(args);
	}
	
    /**
     * Process Javac command line arguments.
     */
    @Override public void processArguments() throws Invoker.ArgumentException {
    	super.processArguments();
    	args.clear();
    	args.append(new String[]{"-d", outDir.getAbsolutePath()});
		args.addFileNames(outDir, ".java");
		if(args.size() == 2 ) {
			throw new Invoker.ArgumentException("No java files found in " + outDir); 
		}
    }

    /**
     * Invokes Javac with specific options.
     * 
     * @param out A stream to which to report messages and errors
     * @param err An additional stream to which to write output.
     * @return The result of the command
     */
    @Override public int invoke(final PrintStream out, final PrintStream err) throws Exception {
    	final Integer[] _rc = new Integer[]{FAILED};
        // run all the work in another thread.
        final Throwable[] _ex = new Throwable[1];
        Thread th = new Thread() {
            public void run() {
                try {
                	_rc[0] = _javac(args.getArgs(),err);
                } catch( Throwable e ) {
                    _ex[0]=e;
                }
            }
        };
        th.start();
        th.join();
        if(_ex[0]!=null) {
            // re-throw
            if( _ex[0] instanceof Exception)
                throw (Exception)_ex[0];
            else
                throw (Error)_ex[0];
        }    	
    	return _rc[0];
    }
    
    /**
     * Static wrapper to call javac in a separate thread. 
     */
    private static int _javac(String[] args, PrintStream err)
			throws Exception {
    	int counter = 3;
		Class<?> javacClass = null;
		ClassLoader parentCl = JavacInvoker.class.getClassLoader();
		if (parentCl == null) {
			parentCl = ClassLoader.getSystemClassLoader();
		}
		try {
			if (ToolsJarClassLoader.canLoadToolsJar(parentCl)) {
				javacClass = parentCl.loadClass("com.sun.tools.javac.Main");
			} else {
				ClassLoader classLoader = new ToolsJarClassLoader(parentCl);
				javacClass = classLoader.loadClass("com.sun.tools.javac.Main");
			}

			Method compileMethod = javacClass.getMethod("compile",
					new Class[] { String[].class });
			while(true){
				Object result = compileMethod.invoke(null, new Object[] { args });
				if (result instanceof Integer) {
					if( --counter == 0 || ((Integer) result).intValue() == 0)
						return ((Integer) result).intValue();
					else
						err.println("Since on some platforms sources might be not-ready trying to compile one more time");
				} else {
					throw new Invoker.InvokeException(
							"Unexpected return value from the javac invoker : "
									+ result.toString());
				}
			}
		} catch (Throwable ex) {
			String msg = ex.getMessage();
			throw new Exception("The javac invoker com.sun.tools.javac.Main(" + 
					_toString(args) + ") failed\n" + 
					(msg != null ? " with the message \"" + msg + "\" " : ""));
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
