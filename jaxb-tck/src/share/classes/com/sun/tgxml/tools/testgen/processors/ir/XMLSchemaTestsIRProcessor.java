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

package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.Properties;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.attributes.impl.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.code.impl.*;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.impl.ExternalDataImpl;
import com.sun.tgxml.tjtf.api.documentation.impl.TestGroupDocumentationImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.tests.impl.*;
import com.sun.tgxml.tools.testgen.processors.emitter.XMLSchemaTestEmitter;
import com.sun.tgxml.util.IR;
import java.io.File;
import java.util.*;
import com.sun.tgxml.tools.elgen.ExcludeList;
import com.sun.tgxml.tools.elgen.ExcludeListToolFactory;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.testgen.BundleTestGenFilter;
import com.sun.tgxml.tools.elgen.IncorrectAttributesException;
import java.io.IOException;

public class XMLSchemaTestsIRProcessor extends BasicIRProcessor {
    
    final static ArrayList<CodeDependency> dependencies = new ArrayList<CodeDependency>();
    static {
        try {
            LibraryDependency libDep = new LibraryDependencyImpl();
            libDep.setID("jaxb.j2x");
            dependencies.add(libDep);
        } catch (TestFileException ex) {
            ex.printStackTrace();
        }
    }
    
    public XMLSchemaTestsIRProcessor( Properties props ) {
        super( props );
    }

    public IRObj[] process( IRObj[] irs ) throws TestFileException {
        // generate testcase for checking custom validation (made not via JAXP)
        if( true /* change it to value from the interview */){
            List<TestGroup> l = new ArrayList<TestGroup>();
            for( IRObj ir : irs ){
                if( ir instanceof TestGroup && isXMLSchemaTest( (TestGroup)ir ) ){
                    l.addAll( generateCheckingValidationTest( (TestGroup)ir ) );
                }
            }
            // add this test group into trees
            IRObj[] newTrees = new IRObj[ irs.length + l.size() ];
            l.toArray( newTrees );
            System.arraycopy(irs, 0, newTrees, l.size(), irs.length);
            irs = newTrees;
        }
        return irs;
    }
    
    protected boolean isXMLSchemaTest( TestGroup tg ) {
        TestGroupAttributes attrs = tg.getTGAttributes();
        if( attrs != null ){
            for( AttrElem el : (ArrayList<AttrElem>)attrs.getAttrElems() ){
                if( "testType".equals( el.getName() )  )
                    return "JAXBXMLSchemaTest".equals( el.getValue() );
            }
        }
        return false;
    }
    
    protected TestCase createCheckingValidationTestcase( List<String> schemas, 
    		                                             String xmlFileName ) 
                            throws TestFileException {
        TestCase tc = new TestCaseImpl();
        tc.setID( new File( xmlFileName ).getName() );
        CodeSet cs = new CodeSetImpl();
        
        ArrayList<ExternalSupportClass> escl = new ArrayList<ExternalSupportClass>();
        for (String schema : schemas) {
			ExternalSupportClass esc = new ExternalSupportClassImpl();
			esc.setSourceName(schema);
			escl.add(esc);
		}
        
        cs.setSupportClasses( escl );
        ArrayList<ExternalData> edl = new ArrayList<ExternalData>();
        ExternalData ed = new ExternalDataImpl();
        ed.setSourceName( xmlFileName );
        edl.add( ed );
        cs.setData( edl );
        cs.setDependencies( dependencies );
        tc.setCodeSet(cs);
        return tc;
    }
    
    private String getTestCaseName(List<String> schemas) {
    	for(String fn : schemas ) {
    		if(fn.endsWith(".xsd"))
    			return new File( fn ).getName();
    	}
    	return null;
    }
    
    protected List<TestGroup> generateCheckingValidationTest( TestGroup tg ) throws TestFileException {
        List<TestGroup> result = new ArrayList<TestGroup>();
        String relPath = IR.getAttrElem(IR.relSourcePathAttrElemName, tg);
        String outDir = IR.getAttrElem("OutputDir", tg);
        List<String> schemas = XMLSchemaTestEmitter.findExternalFileNames(tg.getCodeSet(), XMLSchemaTestEmitter.ALLOWED_SCHEMA_EXTESIONS);
        String testCaseName = getTestCaseName(schemas);
        
        if( testCaseName != null && tg.getTestCases() != null ){
        	
            // look for testcase with 'xml' file as a external data
            ir_creation: for( TestCase tc : (List<TestCase>)tg.getTestCases() ){
                // extract 'validity' value
                String validityAttr = IR.getAttrElem(XMLSchemaTestEmitter.IR_ATTR_VALIDITY, tc);
                boolean validity = (validityAttr == null) ? true : validityAttr.equals( "1" );

                if( validity && tc.getCodeSet() != null && tc.getCodeSet().getData() != null){
                    for( Object data : tc.getCodeSet().getData() ){
                        if( data instanceof ExternalData ){
                            String fileName = ((ExternalData)data).getSourceName();
                            if( fileName.endsWith(".xml") && 
                                    ((ExternalData)data).getType().isIOData() ){
                                // xml file is found
                                // create TestGroup
                                TestGroup ntg = new TestGroupImpl();
                                ntg.setID( testCaseName );
                                ntg.setTGDocumentation( new TestGroupDocumentationImpl() );
                                ntg.getTGDocumentation().setTitle( ntg.getID() );
                                ntg.setCodeSet( new CodeSetImpl() );
                                ntg.getCodeSet().setDependencies( dependencies );
                                IR.setAttrElem( ntg, IR.relSourcePathAttrElemName, relPath );
                                IR.setAttrElem( ntg, "OutputDir", outDir );
                                IR.setAttrElem( ntg, IR.SourcePathAttrElemName, IR.getAttrElem( IR.SourcePathAttrElemName, tg ) );
                                ntg.getTGAttributes().getAttrElems().add( new AttrElemImpl( "testType", "JAXBXMLSchemaTest" ) );
                                ntg.setTestCases(new ArrayList());
                                TestCase ntc = createCheckingValidationTestcase( schemas, fileName );
                                ntg.getTestCases().add( ntc );
                                //IR.setAttrElem( ntc, IR.relSourcePathAttrElemName, relPath );
                                //IR.setAttrElem( ntc, "OutputDir", outDir );
                                // set other test group attributes
                                ntg.getTGAttributes().setExecuteClass(
                                        "javasoft.sqe.tests.api.jakarta.xml.bind.JAXBValidationCheckerTest");
                                // IMPORTANT: -TestURL parameter must precede other parameters
                                ntg.getTGAttributes().setExecuteArgs( String.format( "-TestURL $testURL -xml %s -package $package", fileName ) );
                                // selectIfs
                                ArrayList selectIfs = new ArrayList();
                                selectIfs.add("toTestOptionalValidation");
                                ntg.getTGAttributes().setSelectIfs(selectIfs);
                                ArrayList keywords = new ArrayList();
                                keywords.add(XMLSchemaTestEmitter.KEYWORD_VALIDATION_CHECKER);
                                ntg.getTGAttributes().setKeywords(keywords);
                                ntc.setTestGroup(ntg);
                                if (isExcluded(ntg) || isExcluded(ntc)) {
                                    break ir_creation;
                                }
                                // schema parameter will be added later
                                result.add( ntg );
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    public boolean isExcluded(TestItem testItem) throws TestFileException {
        try {
            String jtxFileName = BuildProperties.getString(BundleTestGenFilter.EXLIST_SYSPROP);
            ExcludeList el = ExcludeListToolFactory.getExcludeList(jtxFileName);
            return (el.find(testItem) != null);
        } catch (IncorrectAttributesException ex) {
            throw new TestFileException(ex.getMessage());
        } catch (IOException ex) {
            throw new TestFileException(ex.getMessage());
        }
    }
    
}
