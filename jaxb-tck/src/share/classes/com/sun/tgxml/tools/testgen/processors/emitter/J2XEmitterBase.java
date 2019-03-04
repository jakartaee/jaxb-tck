/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.testgen.processors.emitter;

import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.nio.charset.Charset;
import java.io.*;
import java.util.*;
import javax.tools.*;
import javax.annotation.processing.AbstractProcessor;

public class J2XEmitterBase extends MultiTestWriter {

    protected static String toStr( Object[] objs ){
        String b = "";
        for( int i = 0; i < objs.length; i++){
            b = b + ", " + objs[i];
        }
        return "[" + b.substring( 2 ) + "]";
    }

    protected interface StringReceiver {
        void push( String str );
    }

    protected static void enumSupportClasses( CodeSet cs, StringReceiver r ) throws TestFileException {
        if( cs == null || cs.getSupportClasses() == null )
            return;
        for( Iterator it = cs.getSupportClasses().iterator(); it.hasNext(); ){
            Object obj = it.next();
            if (obj instanceof ExternalSupportClass)
                r.push(((ExternalSupportClass)obj).getSourceName());
        }
    }

    protected static void enumExternalData( CodeSet cs, StringReceiver r ) throws TestFileException {
        if( cs == null || cs.getData() == null )
            return;
        for( Iterator it = cs.getData().iterator(); it.hasNext(); ){
            Object obj = it.next();
            if (obj instanceof ExternalData)
                r.push( ((ExternalData)obj).getSourceName() );
        }
    }

    protected static Set extractSupportClasses( CodeSet cs ) throws TestFileException {
        final Set supportClasses = new HashSet();
        enumSupportClasses( cs,
            new StringReceiver(){
                public void push( String fileName ){
                    supportClasses.add( fileName );
                }
            });
        return supportClasses;
    }

    public static Iterable<String> doCompile( Iterable<String> sourceFiles,
                                                String outDir ) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = 
            compiler.getStandardFileManager(diagnostics, (Locale) null, (Charset) null);
        J2XProcessor processor = new J2XProcessor();
        synchronized( fileManager ) {
            Iterable<String> options = Arrays.asList( "-target", "1.8" );
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromStrings( sourceFiles );
            new File( outDir ).mkdirs();
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                    Collections.singleton( new File( outDir ) ));
            JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(System.err),
                    fileManager, null, options, null, compilationUnits);
            List<AbstractProcessor> processors = new ArrayList<AbstractProcessor>();
            processors.add( processor );
            task.setProcessors( processors );
            
            if ( !task.call() ) {
                StringBuffer compMessage = new StringBuffer();
                for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                    compMessage.append(String.format("Error on line %d in %s: %s.\n",
                             diagnostic.getLineNumber(),
                             ((JavaFileObject)diagnostic.getSource()).toUri(),
                             diagnostic.getMessage( Locale.getDefault() )));
                }
                throw new RuntimeException(compMessage.toString());
            }
        }        
        return processor.getClassNames();
    }

}
