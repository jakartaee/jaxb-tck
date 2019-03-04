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
 * Wrapper to invoke a schema(s) generation in sameJVM mode. 
 *
 * @author  Leonid Kuskov
 * @version 1.11
 */
public class SchemaGenInvoker extends Invoker {

	//	 SUN implementation : "com.sun.jaxb_tck.lib.SchemaGen"
    protected String schemaGenClassName  = null; 
    
    // Collection of java files to be processed
    protected Arguments javaFiles = new Arguments();
    
    /**
     * ctor
     */
    public SchemaGenInvoker(String[] args){
    	super(args);
    }
    
     /**
     * Process SchemaGen's command line arguments.
     *
     */
    @Override 
    public void processArguments()  throws Invoker.ArgumentException {
    	
        schemaGenClassName = args.getValue("-jxc");

        if(schemaGenClassName == null) {
            throw new Invoker.ArgumentException("No class name specified after the option '-jxc'.");    
        }
        args.removeArgs("-jxc",2);
        args.setArgs(args.getTail("-", false));
        super.processArguments();
        args.removeArgs("-d",2);

        int n = args.size();
        if ( n == 0 ) {
        	throw new Invoker.ArgumentException("Error executing the schema generator. No java file(s) specified.");
        }
        // Splits patches of java files that could be merged into one argument.
        for (int i = 0; i < n; i++) {
        	javaFiles.append(args.get(i).split("\u0085"));
		}
        // KLEO: remove this method use mechanism that is used in XJC 
        adjustArgs();
    }

    /**
     * Invokes SchemaGen with specific options to perform schema generation.
     *
     * @param out   A stream to which to report messages and errors
     * @param err   An additional stream to which to write output.
     * @return      The result of the command
     */
    @Override 
    public int invoke(PrintStream out, PrintStream err) throws Exception {
		Class<?> schemaGenClass = Class.forName(schemaGenClassName);
		SchemaGenTool schemaGenTool = SchemaGenTool.class.cast(schemaGenClass
				.newInstance());
		return schemaGenTool.generate(javaFiles.getArgs(), outDir, out, err);
	}
    
    /**
     * Replaces a final file separator(s) in a java file names with appropriate
     * to OS.
     * 
     * Transforms the found defect as follows:
     * R:\jck\tests\java2schema\CustomizedMapping\classes\XmlType\namespace\NameSpace012s/package-info.java
     * to
     * R:\jck\tests\java2schema\CustomizedMapping\classes\XmlType\namespace\NameSpace012s\package-info.java
     */
    private void adjustArgs() {
        for (int i = args.size() - 1; i > 1; i--) {
            String fname = args.get(i);
            if (fname.endsWith(".java")) {
                int ui = fname.indexOf('/');
                int wi = fname.indexOf('\\');
                if (ui == -1 || wi == -1)
                    continue;
                args.set(i, fname.replace(wi < ui ? '/' : '\\', 
                        wi < ui ? '\\': '/') );
            }
        }      
    }
}
