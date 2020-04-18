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

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.testgen.api.TestDescriptionIR;
import com.sun.tgxml.tools.testgen.processors.emitter.ExternalEmitter;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.util.IR;

/**
 * Emitter for java-to-schema tests. Java-to-schema tests consist of java file and set of xml files. Each xml file is 
 * considered as a separate TestCase.
 */
public class J2XTestEmitter extends ExternalEmitter {

    /** Attribute denotes if the test positive or negative */
    public static final String ATTR_POS_NEG = "pos_neg";
    /** "Positive" value of ATTR_POS_NEG attribute */
    public static final String VALUE_POS = "positive";
    /** "Negative" value of ATTR_POS_NEG attribute */
    public static final String VALUE_NEG = "negative";
    /** Default value of executeClass property */
    public final static String PARAMETER_EXECUTE_CLASS = "javasoft.sqe.tests.api.jakarta.xml.bind.SchemaGenTest";
    /** Name of property from tck.properties to define executeClass */
    public final static String PROPERTY_PARAMETER_EXECUTE_CLASS = "testgen.emitter.TestGroup.J2XTest.executeClass";
    /** Test Group keyword value */
    public static final String KWD_NEGATIVE = "negative";
    /** parameters of test description table */
    public final static String PARAMETER_PACKAGE = "package";
    
    private String javaSource;
    protected ArrayList javaSourceArray;
    
    protected StringBuffer createExecuteArgsBuffer() {
        StringBuffer buffer = new StringBuffer();
        return buffer;
    }

    /**
     * To include into generated test description executeClass and executeArgs.
     * executeArgs consists of '-n' flag if the test is a negative and
     * name of xml file (if there are several xml files then the files are delimited with ':')
     */
    protected TestDescriptionIR constructTestDescriptionIR(
             String id,
             String title,
             TestGroupAttributes tgAttr,
             CodeSet codeSet,
             TestCase tc,
             String context, 
             String executeArgs)
                 throws TestFileException {
        StringBuffer buffer = createExecuteArgsBuffer();
        buffer.append("-j " + javaSource + " ");
        ArrayList tdSource = new ArrayList();
        tdSource.addAll(javaSourceArray);
        if (tc != null) {
            try {
                // get array of external xml files and check is there is just a schema compiling
                String[] files = getExternalXml(tc);
                tdSource.addAll(Arrays.asList(files));
                if (!isPositive(tc)) {
                    buffer.append("-n ");
                }
                buffer.append("-d ");
                buffer.append(inlineArray(files));
            } catch (NoXmlException ex) {
                // that's ok, test is just schema compiling
            }
        }
        buffer.append(" -TestURL $testURL ");
        executeArgs = buffer.toString();
        
        if ( tgAttr.getKeywords() != null && !tgAttr.getKeywords().contains(J2XRuntimeEmitter.KWD_RTGEN) ) {
            ArrayList selectIfs = new ArrayList();
            selectIfs.add("toTestOptionalSchemagen");
            tgAttr.setSelectIfs(selectIfs);
        }
        
        String executeClass = BuildProperties.getString(PROPERTY_PARAMETER_EXECUTE_CLASS, PARAMETER_EXECUTE_CLASS);
        TestDescriptionIR td;
        
        td = super.constructTestDescriptionIR(id,title,tgAttr,codeSet,tc,context, executeArgs);
        td.add(TestDescriptionIR.CLASS, executeClass);
        td.addRef(TestDescriptionIR.SOURCE, tdSource);
        
        return td;
    }
    
    /**
     * Check if the TestCase is positive
     */
    protected boolean isPositive(TestCase tc) throws TestFileException {
            TestCaseAttributes attributes = tc.getTCAttributes();
            ArrayList attrs = attributes.getAttrElems();
            for (Iterator it = attrs.iterator(); it.hasNext(); ) {
                AttrElem attr = (AttrElem)it.next();
                String name = attr.getName();
                String value = attr.getValue();
                if (ATTR_POS_NEG.equals(name)) {
                    if (VALUE_NEG.equals(value)) {
                        return false;
                    }
                    if (VALUE_POS.equals(value)) {
                        return true;
                    }
                }
            }
            throw new TestFileException("no " + ATTR_POS_NEG + " attribute in TestCase " + tc.getID());
    }
    
    /**
     * Get string array of external xml source files. There is may be no xml sources in that case
     * empty array is returned.
     */
    protected String[] getExternalXml(TestCase tc) throws TestFileException, NoXmlException {
            ArrayList list = new ArrayList();
            CodeSet cs = tc.getCodeSet();
            // no CodeSet in TestCase
            if (cs == null) throw new NoXmlException();
            ArrayList datas = cs.getData();
            if (datas == null) throw new NoXmlException();
            for (Iterator it = datas.iterator(); it.hasNext(); ) {
                Data data = (Data)it.next();
                if (data instanceof ExternalData) {
                    try {
                        String file = ((ExternalData)data).getSourceName();
                        list.add(file);
                    } catch (TestFileException ex) {
                        //ok
                    }
                }
            }
            return (String[])list.toArray(new String[0]);
    }

    /**
     * Get the name of java source file and package name from TestGroup before splitting TestGroup
     */
    protected TestGroup[] splitTestGroup(TestGroup tg, HashMap tg2tc)
            throws TestFileException {
        CodeSet cs = tg.getCodeSet();
        ArrayList list = cs.getSupportClasses();
        ArrayList javaFiles = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object sc = it.next();
            if (sc instanceof ExternalSupportClass) {
                javaFiles.add(((ExternalSupportClass)sc).getSourceName());
            }
        }
        javaSourceArray = javaFiles;
        javaSource = inlineArray((String[])javaFiles.toArray(new String[0])).toString();
        fixTDFile(tg);
        

        TestGroupAttributes tgAttr = tg.getTGAttributes();
        ArrayList keywords = tgAttr.getKeywords();
        if (keywords != null) {
            for (Iterator it = keywords.iterator(); it.hasNext();) {
                if (KWD_NEGATIVE.equals(it.next())) {
                    return new TestGroup[] {tg};
                }
            }
        }
        
        return super.splitTestGroup(tg, tg2tc);
    }
    
    /**
     * Represents string array as a string if arguments,
     * current format is arr[0]:arr[1]:....
     */
    private StringBuffer inlineArray(String[] arr) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0 ; i < arr.length; i++) {
            if (i > 0) {
                buffer.append(':');
            }
            buffer.append(arr[i]);
        }
        return buffer;
    }
    
    /**
     * The exception is thrown if not test cases were specified and test
     * consists of only xml schema compiling
     */
    private class NoXmlException extends Exception {
    }
    
    protected void fixTDFile(TestGroup tg) {
        /* - always reset tdFile!
        String tdFile = LibUtils.getTDFile(tg);
        if (tdFile != null) return;
         */
        TestGroupAttributes attrs = tg.getTGAttributes();
        try {
            IR.setAttrElem(attrs, "tdFile", tg.getID());
        } catch (com.sun.tgxml.tjtf.api.exceptions.TestFileException ex) {
            ex.printStackTrace();
        }
    }
    
}
