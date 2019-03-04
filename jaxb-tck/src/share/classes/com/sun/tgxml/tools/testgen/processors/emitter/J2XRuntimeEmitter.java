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

package com.sun.tgxml.tools.testgen.processors.emitter;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Locale;
import java.nio.charset.Charset;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.util.IR;
import java.util.List;
import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaCompiler;
import javax.tools.StandardLocation;
import javax.tools.DiagnosticCollector;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


/**
 * Emitter for java-to-schema tests with generating schemas at runtime. 
 * Java-to-schema tests consist of java file and set of xml files. Each xml file is 
 * considered as a separate TestCase.
 */
public class J2XRuntimeEmitter extends J2XTestEmitter {
    
    public static final String KWD_RTGEN = "rtgen";
    public static final String KWD_EMPTY_OUT = "empty_output";
    public static final String KWD_SCHEMAGEN_REQUIRED = "java_to_schema";

    protected String compileDir;
    protected String outputDir;
    protected String srcDir;
    protected boolean isNegativeGen;
    protected boolean isEmptyOut; // KWD_EMPTY_OUT = "empty_output"
    protected Iterable<String> classNames;
    
    /**
     * execute args always contain
     *   -srcdir -- directory name with precompiled java sources for the test
     *   -neggen -- if schema generation is expected to fail
     */
    protected StringBuffer createExecuteArgsBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(" -out $testWorkDir ");
        if (isNegativeGen) {
            buffer.append(" -neggen ");
        }
        
        if ( isEmptyOut ) {
            buffer.append(" -" + KWD_EMPTY_OUT + " ");
        }
        
        if (classNames != null) {
            for (String className : classNames) {
                buffer.append(" -class ");
                buffer.append(className);
                buffer.append(" ");
            }
        }
        return buffer;
    }
    protected TestGroup[] splitTestGroup(TestGroup tg, HashMap tg2tc)
            throws TestFileException {
        outputDir = IR.getAttrElem("OutputDir", tg);
        srcDir = IR.getAttrElem(IR.SourcePathAttrElemName, tg);
        srcDir = srcDir.substring(0, srcDir.lastIndexOf(File.separatorChar));
        TestGroupAttributes tgAttr = tg.getTGAttributes();
        // append keyword to recognize runtime j2x test
        ArrayList<String> keywords = tgAttr.getKeywords();
        keywords.add(KWD_RTGEN);
        tgAttr.setKeywords(keywords);
        tg.setTGAttributes(tgAttr);
        
        isNegativeGen = keywords.contains(KWD_NEGATIVE);
        isEmptyOut = keywords.contains(KWD_EMPTY_OUT); 
        
        keywords.remove(KWD_NEGATIVE);
        //Leonid Kuskov(lk157052): 
        //Since schema(s) were already compiled during precompile stage 
        //schemagen is not needed. 
        keywords.remove(KWD_SCHEMAGEN_REQUIRED);
        
        TestGroup[] testGroups = super.splitTestGroup(tg, tg2tc);
        try {
            ArrayList<String> javaList = new ArrayList<String>();
            for (ListIterator it = javaSourceArray.listIterator(); it.hasNext();) {
                Object fileName = it.next();
                javaList.add(srcDir + File.separator + fileName);
            }
            classNames = J2XEmitterBase.doCompile( javaList, System.getProperty("tck.classes.dir") );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return testGroups;
    }
    
    private Iterable<String> getCompilerOptions() {
        ArrayList<String> opts = new ArrayList<String>();
        opts.add("-sourcepath");
        opts.add(srcDir);
        //opts.add("-proc:only");
        return opts;
    }
    
    private void _doCompile() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager
                = compiler.getStandardFileManager(diagnostics,
                (Locale) null, (Charset) null);
        synchronized (fileManager) {
            ArrayList<String> javaList = new ArrayList<String>();
            for (ListIterator it = javaSourceArray.listIterator(); it.hasNext();) {
                Object fileName = it.next();
                javaList.add(srcDir + File.separator + fileName);
            }
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromStrings(javaList);
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                    Collections.singleton(new File(System.getProperty("tck.classes.dir"))));
            JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(System.err),
                    fileManager, null, getCompilerOptions(), null, compilationUnits);
            List<AbstractProcessor> processors = new ArrayList<AbstractProcessor>();
            J2XProcessor processor = new J2XProcessor();
            processors.add(processor);
            task.setProcessors(processors);
            Boolean result = task.call();
            classNames = processor.getClassNames();
            
            if (result != Boolean.TRUE) {
                StringBuffer compMessage = new StringBuffer();
                for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                    compMessage.append(String.format("Error on line %d in %d%n. ",
                             diagnostic.getLineNumber(),
                             ((JavaFileObject)diagnostic.getSource()).toUri()));
                }
                RuntimeException compFailure = new RuntimeException(compMessage.toString());
                throw compFailure;
            }
            
        }        
    }
    
}
