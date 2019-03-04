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

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.util.IR;
import java.io.File;
import java.io.IOException;

import java.util.*;

public class J2XTestWTBEmitter extends MultiTestWriter {

    final public String SCHEMAGENMODE_PROPERTY = "testgen.emitter.TestGroup.J2XTestWTB.SchemaGenMode";
    
    public static final String KWD_RTGEN = "rtgen";
    public static final String KWD_SCHEMAGEN_REQUIRED = "java_to_schema";

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

    protected static void addSupportClassesTo( CodeSet cs, final Set/*<String>*/ supportClasses )
                                throws TestFileException {
        enumSupportClasses( cs,
            new StringReceiver(){
                public void push( String fileName ){
                    supportClasses.add( fileName );
                }
            });
    }

    //protected Map testGroupExecuteClass = new HashMap();
    
    final static public <T> T noNull( T obj, T def ){
        return (obj != null)? obj : def;
    }

    public void generate( IRObj[] trees ) throws TestFileException {
        for( int i = 0; i < trees.length; i++){
            if( trees[ i ] instanceof TestGroup ){
                TestGroup tg = (TestGroup)trees[ i ];
                Set<String> sourceClasses = new HashSet<String>();
                addSupportClassesTo( tg.getCodeSet(), sourceClasses );
                Iterator it = tg.getTestCases().iterator();
                while( it.hasNext() ){
                    TestCase tc = (TestCase)it.next();
                    addSupportClassesTo( tc.getCodeSet(), sourceClasses );
                }
                StringBuilder args = new StringBuilder( noNull( IR.getExecuteArgs( tg ), "" ) );
                // process mode
                if( !"true".equals( System.getProperty( SCHEMAGENMODE_PROPERTY, "false" ) ) ){
                    // compile java sources
                    String srcDir = IR.getAttrElem(IR.SourcePathAttrElemName, tg);
                    srcDir = srcDir.substring(0, srcDir.lastIndexOf(File.separatorChar) + 1);
                    List<String> sources = new ArrayList<String>();
                    it = sourceClasses.iterator();
                    while( it.hasNext() ){
                        sources.add( srcDir + it.next() );
                    }
                    System.out.println("Compile sources for DefaultMapping java2schema test:\n" );
                    Iterable<String> classNames;
                    try {
                        classNames = J2XEmitterBase.doCompile( sources, System.getProperty("tck.classes.dir") );
                    } catch( IOException x ){
                        throw new TestFileException( x.getMessage() );
                    }
                    // create -classes parameter
                    StringBuilder b = new StringBuilder();
                    it = classNames.iterator();
                    while( it.hasNext() ) b.append( ":" + it.next() );
                    args.append( " -classes " + b.toString().substring( 1 ) + " " );
                    // add 'rtgen' keyword (JaxbTckScript.KWD_RTGEN)
                    // addKeyword( tg , KWD_RTGEN );
                } else {
                    // create -j parameter (for com.sun.jaxb_tck.lib.JaxbTckScript class)
                    StringBuilder b = new StringBuilder();
                    it = sourceClasses.iterator();
                    while( it.hasNext() ) b.append( ":" + it.next() );
                    args.append( " -j " + b.toString().substring( 1 ) + " " );
                    // add 'java_to_schema' keyword (JaxbTckScript.KWD_SCHEMAGEN_REQUIRED)
                    addKeyword( tg , KWD_SCHEMAGEN_REQUIRED );
                }
                
                tg.getTGAttributes().setExecuteArgs( args.toString() );
                //
                IR.setAttrElem( tg, "testSource", getClassFileName( tg.getID() ) + ".java" );

                // set dummy parameters
                tg.getTGDocumentation().setTestedClass( "DefaultMapping" );
                tg.getTGDocumentation().setTestedPackage( "JAXB" );
            }
        }

        super.generate( trees );
    }
    private void addKeyword(final TestGroup tg, final String keyword) {
        ArrayList<String> kwds = tg.getTGAttributes().getKeywords();
        if( kwds == null ){
            kwds = new ArrayList<String>();
            tg.getTGAttributes().setKeywords(kwds);
        }
        kwds.add( keyword );
    }
}
