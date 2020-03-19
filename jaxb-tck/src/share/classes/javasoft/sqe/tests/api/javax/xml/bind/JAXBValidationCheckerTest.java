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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.jck.lib.*;
import javasoft.sqe.tests.api.jakarta.xml.bind.SchemaGenTestExt.IFileResolver;
import javasoft.sqe.tests.api.jakarta.xml.bind.SchemaGenTestExt.SchemaParameterValue;
import javasoft.sqe.tests.api.jakarta.xml.bind.SchemaGenTestExt.XMLDocumentParameterValue;
import jakarta.xml.bind.*;
import javax.xml.validation.Schema;

public class JAXBValidationCheckerTest extends MultiTestExt {
	
    protected IFileResolver fr = new IFileResolver() {
    	public File[] getFile(String... names) {
        	int len = names.length;
        	File[] files = new File[len];
        	for(int i = 0; i<len; i++) {
        		files[i] = new File(new File( testURL.get().getPath() ).getParentFile(), names[i]);
        	}
            return files;
		}
    };
    
	/**
     * get packageName.
     */
    @Override
    protected String getPackageName() {
        return packageName.get();
    }
    
    public URIValue.Parameter testURL = new URIValue.Parameter( "TestURL" );
    public SchemaParameterValue schema = new SchemaParameterValue( "schema", fr );
    public XMLDocumentParameterValue xml = new XMLDocumentParameterValue( "xml", fr );
    public StringValue.Parameter packageName = new StringValue.Parameter( "package" );
    
    public Status test() {
        boolean ignoreSchema;
        try {
            JAXBContext jc = getJAXBContext();
            
            Unmarshaller u = jc.createUnmarshaller();
            Marshaller   m = jc.createMarshaller();
            try {
                Schema s     = schema.getAsSchema();
                ignoreSchema = null == s;
                // check unmarshal validation
                u.setSchema(s);
                Object element = u.unmarshal( xml.get() );
                // check marshal validation
                try {
                    m.setSchema(s);
                    m.marshal( element, new OutputStream() { public void write(int b) throws IOException {} } );
                } catch( Exception x ) {
                    x.printStackTrace( ref );
                    return Status.failed( String.format( "marshal operation failed (%s)", x.getMessage() ) );
                }
            } catch( Exception x ){
                x.printStackTrace( ref );
                return Status.failed( String.format( "unmarshal operation failed (%s)", x.getMessage() ) );
            }
        } catch( Exception x ){
            x.printStackTrace( ref );
            throw new TestFailureException( x );
        }
        return Status.passed( ignoreSchema ? "OK. [Cannot load schemas. The test passed without XML schema validation ]"  : 
                                             "OK"  );
    }
    
    public static void main( String[] args ) {
        new JAXBValidationCheckerTest().run( args, System.err, System.out).exit();
    }
}
