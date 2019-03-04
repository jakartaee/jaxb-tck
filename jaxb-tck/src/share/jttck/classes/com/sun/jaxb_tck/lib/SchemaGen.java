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
import java.util.LinkedList;


/**
 * Wrapper to generate schema in sameJVM mode. It invokes the schema 
 * generate method that is supplied by SUN's reference implementation.  
 *
 * @author  Leonid Kuskov
 * @version 1.9
 */
public class SchemaGen implements SchemaGenTool {
    
    /**
     * Invokes  the SUN implemented internal tool to perform schema generation.
     *
     * @param args  SchemaGen-specific options and arguments
     * @param out   A stream to which to report messages and errors
     * @param err   An additional stream to which to write output.
     * @return      The result of the command
     */
    public int generate(String[] javaFiles, File outDir, 
    		final PrintStream out, final PrintStream err) throws Exception {
        LinkedList<String> schemaGenArgs = new LinkedList<String>();
        
        schemaGenArgs.add("-d");
        schemaGenArgs.add(outDir.getAbsolutePath());        
        
        if ( javaFiles == null || javaFiles.length == 0 ) {
        	throw new Invoker.ArgumentException("No java file(s) specified.");
        }
        
        for (String javaFile : javaFiles)
            schemaGenArgs.add(javaFile);

        String[] args = (String[])schemaGenArgs.toArray( new String[schemaGenArgs.size()]); 
        
        try {
        	/* Invoke Standalone RI */
        	return com.sun.tools.jxc.SchemaGenerator.run(args);
        } catch (Throwable ex) {
            String msg = ex.getMessage();
			throw new Exception("The schemagen invoker SchemaGenerator.run(" + 
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


