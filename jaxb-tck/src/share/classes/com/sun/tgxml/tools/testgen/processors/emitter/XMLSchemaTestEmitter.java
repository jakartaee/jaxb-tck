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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


import com.sun.javatest.ExcludeList;
import com.sun.javatest.util.*;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.code.Code;
import com.sun.tgxml.tjtf.api.code.CodeFactory;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.tjtf.api.code.InlineSupportClass;
import com.sun.tgxml.tjtf.api.code.LibraryDependency;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.code.SupportCode;
import com.sun.tgxml.tjtf.api.code.TestCode;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.documentation.impl.TestCaseDocumentationImpl;
import com.sun.tgxml.tjtf.api.exceptions.EmbeddedTFException;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestFactory;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.UTDVisitorBase;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.api.impl.TestSuiteImpl;
import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.api.TestDescriptionIR;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;
import java.util.List;

/*
 * A class that is to convert JAXB TCK UTDs into html test descriptions. All
 * those UTDs have test group attribute 'testType' set to "XMLSchemaTest".
 */
public class XMLSchemaTestEmitter extends ExternalEmitter {


    // File extension that used in test suite to identify xml schema files. 
    public static final String[] ALLOWED_SCHEMA_EXTESIONS = { 
    	".xsd",
    	".inc",
    	".imp",
    	".imp2",
    	".red"
    };


    /* The type ID of XMLSchemaTest tests */
    public static final String XML_SCHEMA_TEST = "XMLSchemaTest";

    /* parameters of test description table */
    public final static String PARAMETER_PACKAGE = "package";

    public final static String PARAMETER_SIGNATURE = "signature";

    public final static String PARAMETER_DOCUMENT = "document";

    public final static String PARAMETER_BINDINFO = "bindinfo";
    
    public final static String PARAMETER_JBXCONTEXT = "jb-context";

    public static final String SCHEMA_NAME = "schemaName";

    /* property prefix */
    public final static String PROPERTY_PREFIX = 
    	"testgen.emitter.TestGroup.JAXBXMLSchemaTest.";

    /*
     * default value of the executeClass parameter in schema test description
     * table
     */
    public final static String PARAMETER_SCHEMA_EXECUTE_CLASS = 
    	"com.sun.tdk.signaturetest.Test";

    /*
     * default value of the executeArgs parameter in schema test description
     * table
     */
    public final static String PARAMETER_SCHEMA_EXECUTE_ARGS = 
    	"-TestURL $testURL -Plugin com.sun.jaxb_tck.sigtest.JaxbPlugin -FileName $sigfile -PackageWithoutSubpackages $package";

    /**
     * the property is used to define value of the executeClass parameter in
     * schema test description table
     */
    public final static String PROPERTY_PARAMETER_SCHEMA_EXECUTE_CLASS = PROPERTY_PREFIX
            + "parameter.schema.executeClass";

    /**
     * the property is used to define value of the executeArgs parameter in
     * schema test description table. The following macros are substituted:
     * $sigfile for schema tests, $document for document tests, $package for all
     * kind of tests.
     */
    public final static String PROPERTY_PARAMETER_SCHEMA_EXECUTE_ARGS = PROPERTY_PREFIX
            + "parameter.schema.executeArgs";

    /*
     * default value of the executeClass parameter in document test description
     * table
     */
    public final static String PARAMETER_DOCUMENT_EXECUTE_CLASS = 
	"javasoft.sqe.tests.api.jakarta.xml.bind.JAXBTest";

    /*
     * default value of the executeArgs parameter in document test description
     * table
     */
    public final static String PARAMETER_DOCUMENT_EXECUTE_ARGS = 
    	"-TestURL $testURL -package $package -out :ref -document $document -pcontent $pcontent";

    /**
     * the property is used to define value of the executeClass parameter in
     * document test description table
     */
    public final static String PROPERTY_PARAMETER_DOCUMENT_EXECUTE_CLASS = PROPERTY_PREFIX
            + "parameter.document.executeClass";

    /**
     * the property is used to define value of the executeArgs parameter in
     * document test description table. The following macros are substituted:
     * $sigfile for schema tests, $document for document tests, $package for all
     * kind of tests.
     */
    public final static String PROPERTY_PARAMETER_DOCUMENT_EXECUTE_ARGS = PROPERTY_PREFIX
            + "parameter.document.executeArgs";

    /* keywords */
    public final static String KEYWORD_POSITIVE = "positive";

    public final static String KEYWORD_NEGATIVE = "negative";

    public final static String KEYWORD_SCHEMA = "schema";

    public final static String KEYWORD_DOCUMENT = "document";

    public final static String KEYWORD_RUNTIME = "runtime";

    public final static String KEYWORD_BINDINFO = "bindinfo";

    public final static String KEYWORD_JAXB_NOT_REQUIRED  = "jaxb_not_required";

    public final static String KEYWORD_VALIDATION_CHECKER = "validation_checker";
    
    public final static String KEYWORD_EMPTY_OUPUT        = "empty_output";

    /* the keyword list */
    public static final String RECOGNIZED_KEYWORDS = 
                            KEYWORD_POSITIVE + " " + 
                            KEYWORD_NEGATIVE + " " + 
                            KEYWORD_SCHEMA + " " + 
                            KEYWORD_DOCUMENT + " " + 
                            KEYWORD_RUNTIME + " " + 
                            KEYWORD_BINDINFO + " " + 
                            KEYWORD_JAXB_NOT_REQUIRED + " " + 
                            KEYWORD_VALIDATION_CHECKER + " " +
                            KEYWORD_EMPTY_OUPUT;

    public final static String PROPERTY_RECOGNIZED_KEYWORDS = PROPERTY_PREFIX
            + "recognized.keywords";

    /* the list of the recognized keywords */
    public ArrayList<String> recognizedKeywords = new ArrayList<String>();
    {
        String allKeywords = BuildProperties.getString(
                PROPERTY_RECOGNIZED_KEYWORDS, RECOGNIZED_KEYWORDS);
        StringTokenizer tkn = new StringTokenizer(allKeywords);
        while (tkn.hasMoreTokens()) {
            recognizedKeywords.add(tkn.nextToken());
        }
    }

    /* IR attribute validity */
    public final static String IR_ATTR_VALIDITY = "validity";

    /* IR attribute tdFile */
    public final static String IR_ATTR_TDFILE = "tdFile";

    /* IR attribute group, which is needed to group testcases into one CT test */
    public final static String IR_ATTR_GROUP = "group";
    
    /*
     * Extended test description IR is to represent various test description
     * parameters. The default list is: TITLE, NAME, SOURCE, PARAMETER_PACKAGE,
     * PARAMETER_SIGNATURE, PARAMETER_DOCUMENT, PARAMETER_JBXCONTEXT, CLASS, 
     * KEYWORDS, CONTEXT, EXECUTE_ARGS, SELECT_IF, TIMEOUT.
     */
    public static class XMLSchemaTestDescriptionIR extends TestDescriptionIR {

        /* default list of test description parameters in html output order */
        public static final String[] PARAMETER_LIST = { 
													TITLE, 
													NAME, 
													SOURCE,
													SCHEMA_NAME, 
													CLASS, 
													EXECUTE_ARGS, 
													KEYWORDS, 
													CONTEXT, 
													SELECT_IF, 
													TIMEOUT 
//															PARAMETER_BINDINFO,
//															PARAMETER_PACKAGE, 
//															PARAMETER_SIGNATURE, 
//															PARAMETER_DOCUMENT,
//															PARAMETER_JBXCONTEXT,
        											};

        public void add(String key, String value) {
            if (value != null) {
                if (key.equals(PARAMETER_DOCUMENT)
                		|| key.equals(PARAMETER_JBXCONTEXT)
                        || key.equals(PARAMETER_SIGNATURE)
                        || key.equals(PARAMETER_BINDINFO)) {
                    value = "<a href=\"" + value + "\">" + value + "</a>";
                }
                super.add(key, value);
            }
        }

        public XMLSchemaTestDescriptionIR() {
            setFields(PARAMETER_LIST);
        }
    }

    /*
     * Finds the first testsuite with a given ID in a given array of testsuites.
     */
    protected TestSuite findFirstTestSuite(String tsID, IRObj[] trees) {
        for (int i = 0; i < trees.length; ++i) {
            if (trees[i] instanceof TestSuite) {
                if (tsID.equals(((TestSuite) trees[i]).getID())) {
                    return (TestSuite) trees[i];
                }
            }
        }
        return null; // not found
    }

    /**
     * Emits the IR's into a set of File(s).
     */
    public void generate(IRObj[] trees) throws TestFileException {
        trees = addTestSuites(trees);

        joinTestCases(trees);

        super.generate(trees);
    }

    protected void generateExportFiles(IRObj[] trees) throws TestFileException {
        for (int i = 0; i < trees.length; ++i) {
            if (trees[i] instanceof TestGroup) {
                generateInlines((TestGroup) trees[i]);
            }
        }
    }

    /*
     * Adds tdFile attribute to all groups to make them be generated in html
     * file which contains all testcases' test descriptions (adds new TestSuites
     * if needed).
     */
    protected IRObj[] addTestSuites(IRObj[] trees) throws TestFileException {
        ArrayList<TestSuite> newTSList = new ArrayList<TestSuite>();
        // set tdFile if not set
        for (int i = 0; i < trees.length; ++i) {
            if (trees[i] instanceof TestGroup) {
                TestGroup tg = (TestGroup) trees[i];
                String tdFile = LibUtils.getTDFile(tg);
                if (tdFile == null) {
                    tdFile = tg.getID();
                    IR.setAttrElem(tg, IR_ATTR_TDFILE, tdFile);
                }

                // if no testsuite is set, then for the tg create one testsuite
                // which is to handle header and title
                if (findFirstTestSuite(tdFile, trees) == null) {
                    TestGroupDocumentation tgDoc = tg.getTGDocumentation();
                    if (tgDoc != null) {
                        String title = tgDoc.getTitle();
                        String descr = PreQuote(tgDoc.getDescription());
                        tgDoc.setDescription(null);

                        TestSuite ts = new TestSuiteImpl(title,
                                descr == null ? "" : descr,
                                TestSuiteImpl.LIST_FORMAT, null);
                        ts.setID(tdFile);
                        newTSList.add(ts);
                    }
                }
            }
        }

        // add all newly created TestSuites to IRObj
        if (newTSList.size() > 0) {
            IRObj[] newtrees = new IRObj[trees.length + newTSList.size()];
            System.arraycopy(trees, 0, newtrees, 0, trees.length);
            for (int i = 0; i < newTSList.size(); ++i) {
                newtrees[trees.length + i] = (IRObj) (newTSList.get(i));
            }
            trees = newtrees;
        }

        return trees;
    }

    public final static String PROPERTY_TEST_DEFAULT_GROUP = PROPERTY_PREFIX
            + "test.default.group";

    public final static String TEST_DEFAULT_GROUP = "CTTests";

    public final static String PROPERTY_TEST_DEFAULT_BASE_CLASS = PROPERTY_PREFIX
            + "test.default.baseclass";

    public final static String TEST_DEFAULT_BASE_CLASS = "CTTest";

    public final static String PROPERTY_TEST_STATUS_CLASS = PROPERTY_PREFIX
            + "test.status.class";

    public final static String TEST_STATUS_CLASS = "javasoft.sqe.javatest.Status";

    /**
     * In one testgroup all testcases that have identical values of attr. elem.
     * named IR_ATTR_GROUP are joint into one testcase which is to be one
     * multitest class.
     */
    @SuppressWarnings("unchecked")
    protected void joinTestCases(IRObj[] trees) throws TestFileException {
        for (int j = 0; j < trees.length; ++j) {
            if (trees[j] instanceof TestGroup) {

                TestGroup tg = (TestGroup) trees[j];
                HashMap<String,TestCase> newTestCases = new HashMap<String,TestCase>();
                ArrayList<TestCase> removeTestCases = new ArrayList<TestCase>();
                ArrayList<TestCase> testCases = tg.getTestCases();
                boolean hasNoSchemaOrDocTestCases = true;

                for (int i = 0; i < testCases.size(); ++i) {
                    TestCase tc = (TestCase) testCases.get(i);
                    if (tc.getTestCode() == null) {
                        hasNoSchemaOrDocTestCases = false;
                    } else {

                        // remember to remove the testcase from the testgroup
                        removeTestCases.add(tc);

                        // get testcase group as tgID + group_attr
                        String tcGroup = IR.getAttrElem(IR_ATTR_GROUP, tc);
                        if (tcGroup == null) {
                            tcGroup = BuildProperties.getString(
                                    PROPERTY_TEST_DEFAULT_GROUP,
                                    TEST_DEFAULT_GROUP);
                        }
                        tcGroup = tg.getID() + tcGroup;

                        // create newTestCase or check existent
                        TestCase newTC = (TestCase) newTestCases.get(tcGroup);
                        if (newTC != null) {

                            // move all needed information from the testcase to
                            // the newTC:
                            // ID - unchanged
                            // TestCaseDocumentation - add title and description
                            // to the table
                            // TestCaseAttributes - add all attributes, report
                            // any collision
                            // CodeSet - join all code
                            // TestCode - wrap the TestCode into public Status
                            // id() { ... }
                            // and append to newTC.TestCode

                            addTCDocumentation(newTC, tc);

                            addTCAttributes(newTC, tc.getTCAttributes());

                            newTC.setCodeSet(joinCSs(newTC.getCodeSet(), tc
                                    .getCodeSet()));

                            addTestCode(newTC, tc);

                        } else {
                            newTC = TestFactory.cloneTestCase(tc);
                            newTestCases.put(tcGroup, newTC);

                            // change all needed information from the testcase
                            // to the newTC:
                            // ID - set to tcGroup, remember id to
                            // changeTestCode
                            // TestCaseDocumentation - create a table: title -
                            // description
                            // TestCaseAttributes - set group
                            // CodeSet - unchanged
                            // TestCode - wrap the TestCode into public Status
                            // id() { ... }

                            // change ID
                            String methodID = newTC.getID();
                            newTC.setID(tcGroup);

                            // change documentation
                            changeTCDocumentation(newTC, methodID);

                            // set group attribute
                            IR.setAttrElem(newTC, IR_ATTR_GROUP, tcGroup);

                            // change TestCode
                            changeTestCode(newTC, methodID);
                        }

                    }
                }

                // remove all processed testcases and add new ones
                for (int i = 0; i < removeTestCases.size(); ++i) {
                    testCases.remove(testCases.indexOf(removeTestCases.get(i)));
                }

                if (hasNoSchemaOrDocTestCases && newTestCases.size() == 1
                        && tg.getTGDocumentation() != null
                        && tg.getTGDocumentation().getTitle() != null) {
                    // set testcase title
                    TestCase[] tCs = (TestCase[]) (newTestCases.values()
                            .toArray(new TestCase[0]));
                    tCs[0].getTCDocumentation().setTitle(
                            tg.getTGDocumentation().getTitle());
                }

                testCases.addAll(newTestCases.values());

            }
        }
    }

    protected static final String TABLE_BOTTOM_LINE = "<!-- bottom of the testcase table -->";

    /**
     * Appends testcase documentation to the documentation of the general
     * testcase. General testcase documentation contains a table with titles and
     * documentation of sub testcases.
     */
    protected void addTCDocumentation(TestCase generalTC, TestCase tc)
            throws TestFileException {
        TestCaseDocumentation doc = tc.getTCDocumentation();
        TestCaseDocumentation tcDoc = generalTC.getTCDocumentation();
        String newLine = ("<tr><td>"
                + (doc.getDescription() == null ? "" : doc.getDescription())
                + "</td>\n" + "<td>" + tc.getID() + "</td></tr>\n" + TABLE_BOTTOM_LINE)
                .replaceAll("\\$", "\\\\\\$");
        String newDescr = tcDoc.getDescription().replaceAll(TABLE_BOTTOM_LINE,
                newLine);
        tcDoc.setDescription(newDescr);
        tcDoc.setTitle("several testcases");
        if (doc.getAuthors() != null) {
            if (tcDoc.getAuthors() == null) {
                tcDoc.setAuthors(doc.getAuthors());
            } else {
                tcDoc.getAuthors().addAll(doc.getAuthors());
            }
        }
    }

    /**
     * Converts documentation of the testcase into table with one row: title,
     * description.
     */
    protected void changeTCDocumentation(TestCase generalTC, String tcID)
            throws TestFileException {
        TestCaseDocumentation tcDoc = generalTC.getTCDocumentation();
        if( tcDoc == null ){
            tcDoc = new TestCaseDocumentationImpl();
            generalTC.setTCDocumentation( tcDoc );
        }
        String newDescr = "The test <a href=\""
                + generalTC.getID()
                + ".java\">"
                + generalTC.getID()
                + ".java</a> contains the following test cases:<p>"
                + "<table border=\"1\">\n"
                + "<tr><th>Description</th><th>TestCase</th></tr>\n"
                + "<tr><td>"
                + (tcDoc.getDescription() == null ? "" : tcDoc.getDescription())
                + "</td>\n" + "<td>" + tcID + "</td></tr>\n"
                + TABLE_BOTTOM_LINE + "</table>";
        tcDoc.setDescription(newDescr);
    }

    /**
     * Adds all attributes of sub testcase to general testcase and reports any
     * collision (differences of attribute values).
     */
    protected void addTCAttributes(TestCase generalTC, TestCaseAttributes attrs)
            throws TestFileException {
        if (attrs == null) {
            return;
        }

        TestCaseAttributes tcAttributes = generalTC.getTCAttributes();
        if (tcAttributes == null) {
            generalTC.setTCAttributes(attrs);
            return;
        }

        // Concern the following:
        // TimeOut, RequiredResources, TargetSpecs and AttrElems

        // TimeOut
        String timeOutG = tcAttributes.getTimeout();
        String timeOut = attrs.getTimeout();
        if (timeOutG == null) {
            tcAttributes.setTimeout(timeOutG);
        } else {
            if (timeOut != null) {
                int toG = Integer.parseInt(timeOutG);
                int to = Integer.parseInt(timeOut);
                tcAttributes.setTimeout("" + (toG + to));
            }
        }

        // RequiredResources
        ArrayList reqResG = tcAttributes.getRequiredResources();
        ArrayList reqRes = attrs.getRequiredResources();
        if (reqResG == null) {
            tcAttributes.setRequiredResources(reqRes);
        } else {
            if (reqRes != null) {
                // add all resources from reqRes to reqResG
                for (int i = 0; i < reqRes.size(); ++i) {
                    if (!reqResG.contains(reqRes.get(i))) {
                        reqResG.add(reqRes.get(i));
                    }
                }
            }
        }

        // TargetSpecs: probably, it is not needed
        ArrayList tsG = tcAttributes.getTargetSpecs();
        ArrayList ts = attrs.getTargetSpecs();
        if (tsG == null) {
            tcAttributes.setTargetSpecs(ts);
        } else {
            if (ts != null) {
                // add all target specs from ts to tsG
                for (int i = 0; i < ts.size(); ++i) {
                    if (!tsG.contains(ts.get(i))) {
                        tsG.add(ts.get(i));
                    }
                }
            }
        }

        // AttrElems: take care of collisions
        ArrayList aeG = tcAttributes.getAttrElems();
        ArrayList ae = attrs.getAttrElems();
        if (aeG == null) {
            tcAttributes.setAttrElems(ae);
        } else {
            if (ae != null) {
                // add all AttrElems from ae to aeG
                for (int i = 0; i < ae.size(); ++i) {
                    AttrElem elem = (AttrElem) ae.get(i);
                    String value = IR.getAttrElem(elem.getName(), aeG);
                    if (value == null) {
                        aeG.add(elem);
                    } else {
                        if (!value.equals(elem.getValue())) {
                            // report attribute collision
                            throw new TestFileException(
                                    "test case attributes \""
                                            + elem.getName()
                                            + "\" must have identical values: found "
                                            + elem.getValue() + ", expected "
                                            + value);
                        }
                    }
                }
            }
        }
    }

    private static boolean isValidJavaIdentifier( String methodID ){
        boolean valid = true;
        for( int i = 1; valid && i < methodID.length(); i++)
            valid = Character.isJavaIdentifierPart( methodID.charAt( i ) );
        return valid && Character.isJavaIdentifierStart( methodID.charAt( 0 ) );
    }

    protected static String createTestCodeString(TestCase tc, String methodID) {
        // methodName must be a java identifier
        // assert( isValidJavaIdentifier( methodID ) );
        return
            "    public Status " + methodID + "( ) {\n" +
            "        " + tc.getTestCode().getSource().trim() + "\n" +
            "    }\n\n";
    }

    /**
     * Wraps TestCode of sub testcase into public Status id() { ... } and
     * appends it to TestCode of the general testcase.
     */
    protected void addTestCode(TestCase generalTC, TestCase testCase)
            throws TestFileException {
        String src = generalTC.getTestCode().getSource() +
                        createTestCodeString(testCase, testCase.getID());
        generalTC.getTestCode().setSource(src);
    }

    /**
     * Wraps TestCode of the testcase into public Status id() { ... }.
     */
    protected void changeTestCode(TestCase generalTC, String methodID)
            throws TestFileException {
        generalTC.getTestCode().setSource(createTestCodeString(generalTC, methodID));
    }

    /*
     * Converts testgroup with testcase to one testgroup without testcase.
     * CodeSet of the new testgroup contains codes from both testgroup and
     * testcase. Also it contains InlineSupportClass generated from the testcase
     * TestCode. If original ID's of the testgroup and the testcase are equal,
     * keyword 'schema' is added to the resulting testgroup.
     */
    protected TestGroup testcase2TestGroup(TestCase tc, TestGroup tg)
            throws TestFileException {

        String tgID = tg.getID();

        CodeSet newCS = joinCSs(tg.getCodeSet(), tc.getCodeSet());
        TestGroup resultTG = super.testcase2TestGroup(tc, tg);
        resultTG.setCodeSet(newCS);

        // generate InlineSupportClass from the testcase TestCode
        exportTestCode(tc, resultTG);

        // add KEYWORD_SCHEMA if needed
        try {
            if (resultTG.getID().equals(tgID)) {
                ArrayList keys = resultTG.getTGAttributes().getKeywords();
                if (!keys.contains(KEYWORD_SCHEMA)) {
                    keys.add(KEYWORD_SCHEMA);
                }
            }
        } catch (NullPointerException e) {
            throw new TestFileException("cannot add keyword '" + KEYWORD_SCHEMA
                    + "' to " + tgID + "#" + tg.getID());
        }

        return resultTG;
    }

    /**
     * the property is used to define a default value of the executeArgs
     * parameter for a particular base class. Use IR attribute "executeArgs" to
     * override the value.
     */
    public final static String PROPERTY_PREFFIX_BASE_CLASS_EXECUTE_ARGS = PROPERTY_PREFIX
            + "execute.args.";

    /*
     * default value of the executeArgs parameter in document test description
     * table
     */
    public final static String BASE_CLASS_EXECUTE_ARGS = "-TestURL $testURL -package $package";

    /**
     * Gets inline support classes that are not exported.
     */
    protected String getInlineSupportCode(CodeSet cs) {
        StringBuffer sb = new StringBuffer();
        ArrayList classes;

        if (cs != null && (classes = cs.getSupportClasses()) != null) {
            for (int i = 0; i < classes.size(); ++i) {
                if (classes.get(i) instanceof InlineSupportClass) {
                    InlineSupportClass isc = (InlineSupportClass) classes
                            .get(i);
                    if (!isc.isExport()) {
                        sb.append(isc.getSource().trim() + "\n\n");
                    }
                }
            }
        }

        return sb.toString();
    }

    /*
     * Converts TestCase.TestCode to InlineSupportClass and set executeClass.
     */
    protected void exportTestCode(TestCase tc, TestGroup tg)
            throws TestFileException {
        TestCode testCode = tc.getTestCode();
        if (testCode == null) {
            return;
        }

        if (!testCode.getSourceLang().equals(Code.ctStr_langType_java)) {
            throw new TestFileException("cannot generate testcase of type "
                    + testCode.getSourceLang());
        }
        String sPackage = getTestPackage(tg, tc);
        String baseClass = getTestBaseClass(tg, tc);
        String sImports = getTestImports(tg, tc, baseClass);
        String sClass = getTestClass(tg, tc);
        String sSupportCode = getSupportCode(tg, sClass);
        String sSupportClasses = getInlineSupportCode(tg.getCodeSet());

        // set executeClass
        tg.getTGAttributes().setExecuteClass(sPackage + "." + sClass);

        // set executeArgs
        String executeArgs = IR.getAttrElem("executeArgs", tg);
        if (executeArgs == null) {
            executeArgs = BuildProperties.getString(
                    PROPERTY_PREFFIX_BASE_CLASS_EXECUTE_ARGS + baseClass,
                    BASE_CLASS_EXECUTE_ARGS);
        }
        tg.getTGAttributes().setExecuteArgs(executeArgs);

        // TBD
        String source = /*
                         * "package " + sPackage + ";\n\n" +
                         */sImports + "public class " + sClass + " extends " + baseClass
                + " {\n\n" + sSupportCode + testCode.getSource() + "\n"
                + "}\n\n" + sSupportClasses;

        InlineSupportClass isc = CodeFactory.createInlineSupportClass(testCode
                .getSourceLang(), source, sClass,/* new ArrayList(), */sClass
                + "." + testCode.getSourceLang());

        CodeSet tgCS = tg.getCodeSet();

        ArrayList supportClasses = tgCS.getSupportClasses();
        if (supportClasses == null) {
            supportClasses = new ArrayList();
            tgCS.setSupportClasses(supportClasses);
        }

        supportClasses.add(isc);
    }

    /*
     * Collects all codes into one CodeSet. Removes duplicates. Detects
     * collisions. Assumes, that cs1 has no duplicates and collisions.
     */
    protected CodeSet joinCSs(CodeSet cs1, CodeSet cs2)
            throws TestFileException {

        if (cs1 == null) {
            return CodeFactory.cloneCodeSet(cs2);
        }

        if (cs2 == null) {
            return CodeFactory.cloneCodeSet(cs1);
        }

        CodeSet resultCS = CodeFactory.createCodeSet();

        // Concern the following:
        // Dependency?,
        // Import*,
        // BaseClass?,
        // SupportCode?,
        // (ExternalSupportClass | InlineSupportClass)*,
        // (ExternalData | InlineData)*

        // BaseClass
        if (cs1.getBaseClass() != null) {
            resultCS.setBaseClass(cs1.getBaseClass());
            if (cs2.getBaseClass() != null
                    && !cs1.getBaseClass().equals(cs2.getBaseClass())) {
                throw new TestFileException("base class collision: found "
                        + cs1.getBaseClass() + ", expected "
                        + cs2.getBaseClass());
            }
        } else if (cs2.getBaseClass() != null) {
            resultCS.setBaseClass(cs2.getBaseClass());
        }

        // support code: leave unchanged if equal, otherwise append
        if (cs1.getSupportCode() != null) {
            if (cs2.getSupportCode() != null) {
                SupportCode sc1 = cs1.getSupportCode();
                SupportCode sc2 = cs2.getSupportCode();
                if (!sc1.getSourceLang().equals(sc2.getSourceLang())) {
                    throw new TestFileException(
                            "support codes must be of the same type: found "
                                    + sc2.getSourceLang() + ", expected "
                                    + sc1.getSourceLang());
                }

                if (sc1.getSource().equals(sc2.getSource())) {
                    resultCS.setSupportCode(sc1);
                } else {
                    resultCS.setSupportCode(CodeFactory.createSupportCode(sc1
                            .getSourceLang(), sc1.getSource().trim()
                            + "\n\n    " + sc2.getSource().trim()));

                }
            } else {
                resultCS.setSupportCode(cs1.getSupportCode());
            }
        } else if (cs2.getSupportCode() != null) {
            resultCS.setSupportCode(cs2.getSupportCode());
        }

        // join Data
        ArrayList list = new ArrayList();
        ArrayList list1 = cs1.getData();
        ArrayList list2 = cs2.getData();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            for (int i = 0; i < list2.size(); ++i) {
                Data d2 = (Data) list2.get(i);
                boolean duplicate = false;
                for (int j = 0; j < list.size(); ++j) {
                    Data d1 = (Data) list.get(j);
                    if (d1 instanceof InlineData) {
                        if (d2 instanceof InlineData) {
                            String targetName1 = ((InlineData) d1)
                                    .getTargetName();
                            String targetName2 = ((InlineData) d2)
                                    .getTargetName();
                            String dt1 = ((InlineData) d1).getData();
                            String dt2 = ((InlineData) d2).getData();
                            if (targetName1 != null && targetName2 != null
                                    && targetName1.equals(targetName2)
                                    && dt2 != null && dt1 != null) {
                                if (!dt1.equals(dt2)) {
                                    throw new TestFileException(
                                            "different data are exported to the file "
                                                    + targetName1);
                                }
                                duplicate = true;
                                break;
                            }
                        }
                    } else { // external data
                        if (d2 instanceof ExternalData) {
                            String targetName1 = ((ExternalData) d1)
                                    .getSourceName();
                            String targetName2 = ((ExternalData) d2)
                                    .getSourceName();
                            if (targetName1 != null && targetName2 != null
                                    && targetName1.equals(targetName2)) {
                                duplicate = true;
                                break;
                            }
                        }
                    }
                }
                if (!duplicate) {
                    list.add(d2);
                }
            }
        }
        resultCS.setData(list);

        // join Dependencies
        list = new ArrayList();
        list1 = cs1.getDependencies();
        list2 = cs2.getDependencies();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            for (int i = 0; i < list2.size(); ++i) {
                Object d2 = list2.get(i);
                boolean duplicate = false;
                if (d2 instanceof LibraryDependency) {
                    String id2 = ((LibraryDependency) d2).getID();
                    for (int j = 0; j < list.size(); ++j) {
                        Object d1 = list.get(j);
                        if (d1 instanceof LibraryDependency) {
                            String id1 = ((LibraryDependency) d1).getID();
                            if (id1.equals(id2)) {
                                duplicate = true;
                                break;
                            }
                        }
                    }
                }
                if (!duplicate) {
                    list.add(d2);
                }
            }
        }
        resultCS.setDependencies(list);

        // join Imports
        list = new ArrayList();
        list1 = cs1.getImports();
        list2 = cs2.getImports();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            for (int i = 0; i < list2.size(); ++i) {
                if (-1 == list.indexOf(list2.get(i))) {
                    list.add(list2.get(i));
                }
            }
        }
        resultCS.setImports(list);

        // join SupportClasses
        list = new ArrayList();
        list1 = cs1.getSupportClasses();
        list2 = cs2.getSupportClasses();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            for (int i = 0; i < list2.size(); ++i) {
                SupportClass sc2 = (SupportClass) list2.get(i);
                boolean duplicate = false;
                for (int j = 0; j < list.size(); ++j) {
                    SupportClass sc = (SupportClass) list.get(j);
                    if (sc instanceof InlineSupportClass) {
                        if (sc2 instanceof InlineSupportClass) {
                            InlineSupportClass isc = (InlineSupportClass) sc;
                            InlineSupportClass isc2 = (InlineSupportClass) sc2;
                            String src = isc.getSource();
                            String src2 = isc2.getSource();
                            if (isc.isExport() == isc2.isExport()) {
                                if (isc.isExport()) {
                                    String targetName = isc.getTargetName();
                                    String targetName2 = isc2.getTargetName();
                                    if (targetName != null
                                            && targetName2 != null
                                            && targetName.equals(targetName2)
                                            && src != null && src2 != null) {
                                        if (!src.equals(src2)) {
                                            throw new TestFileException(
                                                    "different source codes are exported to the file "
                                                            + targetName);
                                        }
                                        duplicate = true;
                                        break;
                                    }
                                } else {
                                    if (src != null && src2 != null
                                            && src.equals(src2)) {
                                        duplicate = true;
                                        break;
                                    }
                                }
                            }
                        }
                    } else { // external support class
                        if (sc2 instanceof ExternalSupportClass) {
                            String targetName = ((ExternalSupportClass) sc)
                                    .getSourceName();
                            String targetName2 = ((ExternalSupportClass) sc2)
                                    .getSourceName();
                            if (targetName != null && targetName2 != null
                                    && targetName.equals(targetName2)) {
                                duplicate = true;
                                break;
                            }
                        }
                    }
                }
                if (!duplicate) {
                    list.add(sc2);
                }
            }
        }
        resultCS.setSupportClasses(list);

        return resultCS;
    }

    /**
     * extension of bindinfo files ".bind.xml"
     */
    public static final String[] EXTENSION_BINDINFO = { ".bind.xml" };

    /**
     * Constructs TestDescriptionIR by: id, title, tgAttr, CodeSet.
     */
    protected TestDescriptionIR constructTestDescriptionIR(String id,
                                                             String title, 
                                                             TestGroupAttributes tgAttr, 
                                                             CodeSet codeSet)
            throws TestFileException {
        TestDescriptionIR tdIR = new XMLSchemaTestDescriptionIR();
        tdIR.add(TestDescriptionIR.TITLE, id + " - " + title);
        tdIR.add(TestDescriptionIR.NAME, id);
        tdIR.add(TestDescriptionIR.CLASS, getExecuteClass(id, tgAttr, codeSet));
        tdIR.add(TestDescriptionIR.KEYWORDS, getKeywords(id, tgAttr, codeSet));
        tdIR.add(TestDescriptionIR.CONTEXT, tgAttr.getContext());
        tdIR.add(TestDescriptionIR.EXECUTE_ARGS, getExecuteArgs(id, tgAttr, codeSet));
        tdIR.add(TestDescriptionIR.SELECT_IF, tgAttr.getSelectIfs());
        tdIR.add(TestDescriptionIR.TIMEOUT, tgAttr.getTimeout());
        tdIR.addRef(TestDescriptionIR.SOURCE, findSourceFileNames(codeSet, (String[])null));
        
        tdIR.add(SCHEMA_NAME, getSchemaListAsString(codeSet, tgAttr));
        
        tdIR.add(PARAMETER_BINDINFO, findExternalFileNames(codeSet, EXTENSION_BINDINFO));
        tdIR.add(PARAMETER_PACKAGE, getPackage(id, tgAttr, codeSet));
        tdIR.add(PARAMETER_SIGNATURE, getSignature(id, tgAttr, codeSet));
        tdIR.add(PARAMETER_DOCUMENT, getDocument(id, tgAttr, codeSet));
        tdIR.add(PARAMETER_JBXCONTEXT, getJBContext(id, tgAttr, codeSet ));

        return tdIR;
    }

    private String getJBContext(String id, 
                                 TestGroupAttributes tgAttr,
                                 CodeSet codeSet) throws TestFileException {
    	if (getTCType(id, tgAttr, codeSet).equals(KEYWORD_DOCUMENT)) {
    		List<String> jbxFileList = findExternalFileNames(codeSet, new String[]{".jbx" });
    		int n = jbxFileList.size();
    		if (n > 1) {
    			String msg = jbxFileList.get(0);
    			for( int i=1; i<n; i++){
    				msg = ", " + jbxFileList.get(i);
    			}
    			throw new TestFileException("TestGroup " + 
    					id + 
    					" contains more than one jb content file: " + 
    					msg + ".");
    		}	else if(n==1) {	
    			return jbxFileList.get(0);
    		}
    		return IR.getAttrElem(PARAMETER_JBXCONTEXT, tgAttr);
    	}
    	return null;
	}
        
    protected String getSignature(	String id, 
						    		TestGroupAttributes tgAttr,
						    		CodeSet codeSet) throws TestFileException {
    	if (getTCType(id, tgAttr, codeSet).equals(KEYWORD_SCHEMA)) {
    		List<String> sigFileList = findExternalFileNames(codeSet, new String[]{".sig"});
    		int n = sigFileList.size();
    		if (n > 1) {
    			String msg = sigFileList.get(0);
    			for( int i=1; i<n; i++){
    				msg = ", " + sigFileList.get(i);
    			}
    			throw new TestFileException("TestGroup " + 
    					id + 
    					" contains more than one signature file: " + 
    					msg);
    		}	else if(n==1) {	
    			return sigFileList.get(0);
    		}
    		return IR.getAttrElem(PARAMETER_SIGNATURE, tgAttr);
    	}
    	return null;	
    }

    protected String getDocument(String id, 
					    		  TestGroupAttributes tgAttr,
						    	  CodeSet codeSet) throws TestFileException {
    	if (getTCType(id, tgAttr, codeSet).equals(KEYWORD_DOCUMENT)) {
    		List<String> xmlFileList = findExternalFileNames(codeSet, new String[]{".xml"});
    		int n = xmlFileList.size();
    		if (n > 1) {
    			String msg = xmlFileList.get(0);
    			for( int i=1; i<n; i++){
    				msg = ", " + xmlFileList.get(i);
    			}
    			throw new TestFileException("More than document are defined for document " +
    					                     " tescase " + id + 
    					                     " : " + msg);
    		}	else if(n==1) {	
    			return xmlFileList.get(0);
    		}
    		return IR.getAttrElem(PARAMETER_DOCUMENT, tgAttr);
    	}
    	return null;
    }

	protected String getMainSchema(CodeSet codeSet, TestGroupAttributes tgAttr) 
													 throws TestFileException {
        List<String> schemaList = findSourceFileNames(codeSet, ALLOWED_SCHEMA_EXTESIONS);
        if (schemaList.size() > 0) {
        	// The first "ExternalSupportClass" schema is main and must be used 
        	// to build package. 
        	return schemaList.get(0);
        }
        return StringArray.split(IR.getAttrElem(SCHEMA_NAME, tgAttr))[0];
    }

	protected String getSchemaListAsString(CodeSet codeSet, TestGroupAttributes tgAttr)
													 throws TestFileException {
		List<String> schemaList = findSourceFileNames(codeSet, ALLOWED_SCHEMA_EXTESIONS);
		if( schemaList.size() > 0 ) {
			return StringArray.join(schemaList.toArray(new String[schemaList.size()]));
        }
        return IR.getAttrElem(SCHEMA_NAME, tgAttr);
    }

    /**
     * Constructs TestDescriptionIR by: id, title, tgAttr, CodeSet. Added for
     * compatibility with BTools 1.3
     */
    protected TestDescriptionIR constructTestDescriptionIR(	
				    		String id,
				            String title, 
				            TestGroupAttributes tgAttr, 
				            CodeSet codeSet,
				            TestCase tc, 
				            String context, 
				            String executeArgs)     throws TestFileException {
        return constructTestDescriptionIR(id, title, tgAttr, codeSet);
    }

    protected void addUnique(ArrayList al, Object elem) {
        if (!al.contains(elem)) {
            al.add(elem);
        }
    }

    /* target specification ID for JAXB not required features */
    public static final String TARGET_SPEC_ID_JAXB_NOT_REQUIRED = "JAXB_NOT_REQUIRED";

    protected String getKeywords(String id, TestGroupAttributes tgAttr,
            CodeSet codeSet) throws TestFileException {
        ArrayList keywords = tgAttr.getKeywords();
        if (keywords == null) {
            keywords = new ArrayList();
        } else {
            keywords = (ArrayList) keywords.clone();
        }
        
        boolean isValidationTest = keywords.contains(KEYWORD_VALIDATION_CHECKER);

        // leave only recognized keywords
        for (int i = keywords.size(); --i >= 0;) {
            if (!recognizedKeywords.contains(keywords.get(i))) {
                keywords.remove(i);
            }
        }

        // if targetSpecs contains JAXB_NOT_REQUIRED
        // add jaxb_not_required keyword and change positive to negative
        // in schema tests, document tests will get -invalid option
        // in executeArgs
        boolean switchPositiveToNegative = false;
        ArrayList targetSpecs = tgAttr.getTargetSpecs();
        if (targetSpecs != null && targetSpecs.size() > 0) {
            for (int i = targetSpecs.size(); --i >= 0;) {
                if (((TargetSpec) targetSpecs.get(i)).getID().equals(
                        TARGET_SPEC_ID_JAXB_NOT_REQUIRED)) {
                    addUnique(keywords, KEYWORD_JAXB_NOT_REQUIRED);
                    switchPositiveToNegative = true;
                    break;
                }
            }
        }

        String type = getTCType(id, tgAttr, codeSet);
        if (type.equals(KEYWORD_SCHEMA)) {
            String validity = IR.getAttrElem(IR_ATTR_VALIDITY, tgAttr);
            if (validity != null && validity.equals("0")) {
                addUnique(keywords, KEYWORD_NEGATIVE);
            } else {
                addUnique(keywords, KEYWORD_POSITIVE);
            }
            addUnique(keywords, KEYWORD_SCHEMA);
            if (switchPositiveToNegative) {
                int index = keywords.indexOf(KEYWORD_POSITIVE);
                if (index != -1) {
                    keywords.set(index, KEYWORD_NEGATIVE);
                }
            }
        } else {
            // invalid documents are marked with -invalid option in execute args
            addUnique(keywords, KEYWORD_POSITIVE);
            addUnique(keywords, KEYWORD_DOCUMENT);
            if (!type.equals(KEYWORD_DOCUMENT) && !isValidationTest) {
                addUnique(keywords, KEYWORD_RUNTIME);
            }
        }

        if (findExternalFileNames(codeSet, EXTENSION_BINDINFO).size() != 0) {
            addUnique(keywords, KEYWORD_BINDINFO);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < keywords.size(); ++i) {
            String keyword = (String) keywords.get(i);
            sb.append(keyword + " ");
        }

        return sb.toString().trim();
    }

    protected String getTCType( String id, 
    		                    TestGroupAttributes tgAttr,
                                CodeSet codeSet ) throws TestFileException {
        ArrayList keys = tgAttr.getKeywords();
        if (keys != null && keys.contains(KEYWORD_SCHEMA)) {
            return KEYWORD_SCHEMA; // schema test
        }

        String executeClass = IR.getAttrElem(TestDescriptionIR.CLASS, tgAttr);
        if (executeClass != null || tgAttr.getExecuteClass() != null) {
            return KEYWORD_RUNTIME;
        }

        return KEYWORD_DOCUMENT; // xml document test
    }

    /**
     * the property is used to define Emitter Exclude lists (to exclude some
     * MultiTest testcases by adding to executeArgs "-exclude
     * case1,case2,..,case3")
     */
    public final static String PROPERTY_PARAMETER_E_LISTS = PROPERTY_PREFIX
            + "e-lists";

    protected HashMap excludeMap = new HashMap();

    /*
     * Exclude list entries marked with this keyword are testcases of CTtest.
     * Those are not represented by TestCases in test.xml. To exclude such
     * testcases the option "-exclude testcaselist" should be added to the
     * executeArgs in the test description table.
     */
    public static final String EXCLUDE_LIST_KEYWORD_JAXB_TEST = "jaxb_test";

    /*
     * Exclude list entries marked with this keyword are caused by a bug in the
     * test.
     */
    public static final String EXCLUDE_LIST_KEYWORD_TEST_BUG = "test_bug";

    public XMLSchemaTestEmitter() {
        String eLists = BuildProperties.getString(PROPERTY_PARAMETER_E_LISTS);
        ExcludeList excludeList = null;
        if (eLists != null && eLists.trim().length() > 0) {
            String[] fileNames = eLists.split(" ");
            try {
                File[] files = new File[fileNames.length];
                for (int i = 0; i < fileNames.length; ++i) {
                    files[i] = new File(fileNames[i]);
                }
                excludeList = new ExcludeList(files);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("cannot read exclude lists: "
                        + eLists, e);
            } catch (IOException ioe) {
                throw new RuntimeException("cannot read exclude lists: "
                        + eLists, ioe);
            } catch (ExcludeList.Fault elf) {
                throw new RuntimeException("cannot read exclude lists: "
                        + eLists, elf);
            }
        }

        if (excludeList != null) {
            for (Iterator it = excludeList.getIterator(false); it.hasNext();) {
                ExcludeList.Entry ele = (ExcludeList.Entry) it.next();
                String synopsis = ele.getSynopsis();
                // account for CTTests only
                if (synopsis != null
                        && synopsis.indexOf(EXCLUDE_LIST_KEYWORD_JAXB_TEST) != -1
                        && synopsis.indexOf(EXCLUDE_LIST_KEYWORD_TEST_BUG) != -1) {
                    String test = ele.getRelativeURL();
                    String testCases = (String) excludeMap.get(test);
                    if (testCases == null) {
                        testCases = ele.getTestCases();
                    } else {
                        testCases += "," + ele.getTestCases();
                    }

                    excludeMap.put(test, testCases);
                }
            }
        }

    }

    public static String PROPERTY_TCK_DEST_DIR = "tck.dest.dir";

    protected String getRelativeTestURL(String id, TestGroupAttributes tgAttr)
            throws TestFileException {
        String tdFile = LibUtils.getTDFile(tgAttr);
        String htmlName = (tdFile == null) ? id : tdFile;
        String outDir = IR.getAttrElem("OutputDir", tgAttr);
        if (outDir == null) {
            outDir = "";
        }
        String tckTestBaseDir = BuildProperties.getString(PROPERTY_TCK_DEST_DIR);
        if (tckTestBaseDir != null) {
            if (outDir.startsWith(tckTestBaseDir)) {
                if (outDir.length() == tckTestBaseDir.length()) {
                    outDir = "";
                } else {
                    outDir = outDir.substring(tckTestBaseDir.length() + 1); // +1
                    // removes
                    // File.separator
                }
            } else {
                throw new TestFileException("output dir (" + outDir
                        + ") must start with TCK test dir (" + tckTestBaseDir
                        + ")");
            }
        }

        if (outDir.length() != 0 && !outDir.endsWith(File.separator)) {
            outDir += File.separator;
        }

        return outDir + htmlName + ".html#" + id;
    }

    protected String getExecuteArgs(String id, TestGroupAttributes tgAttr,
            CodeSet codeSet) throws TestFileException {
        String  args = null;
        String  tcType = getTCType(id, tgAttr, codeSet);
        boolean isEmptyOut       = getKeywords(id, tgAttr, codeSet).indexOf(KEYWORD_NEGATIVE) != -1;
        boolean isValidationTest = getKeywords(id, tgAttr, codeSet).indexOf(KEYWORD_VALIDATION_CHECKER) != -1;
        
        if (tcType.equals(KEYWORD_SCHEMA)
                && getKeywords(id, tgAttr, codeSet).indexOf(KEYWORD_NEGATIVE) == -1) {
// keywords: schema 
            args = BuildProperties.getString( PROPERTY_PARAMETER_SCHEMA_EXECUTE_ARGS, PARAMETER_SCHEMA_EXECUTE_ARGS);
            if ( isEmptyOut ) {
                args = " -empty_output ";
            } else {           
                String signature = getSignature(id, tgAttr, codeSet);
                if (signature != null) {
                    args = args.replaceAll("\\$sigfile", signature.replaceAll("\\$", "\\\\\\$") );
                } else {
                    args = args.replaceAll("\\$sigfile", "skip" );
                }
            }
        } else if (tcType.equals(KEYWORD_DOCUMENT)) {
// keywords: document
            args = BuildProperties.getString( PROPERTY_PARAMETER_DOCUMENT_EXECUTE_ARGS,
                    PARAMETER_DOCUMENT_EXECUTE_ARGS);
            
            String doc = getDocument(id, tgAttr, codeSet);
            if (doc == null) {
                throw new TestFileException("No document is defined for document testcase " + id);
            }
            if(isEmptyOut) {
                throw new TestFileException("Testcate " + id + 
                                            " has empty_output keyword and contains a document for testing."); 
            }
            
            args = args.replaceAll("\\$document", doc.replaceAll("\\$", "\\\\\\$"));
          
            String jbx = getJBContext(id, tgAttr, codeSet);
            
            if ( jbx != null ) {
                args = args.replaceAll("\\$pcontent", jbx.replaceAll("\\$", "\\\\\\$") );
            } else {
            	args = args.replaceAll("\\$pcontent", "skip" );
            }

            // if the document is invalid.
            // The xml document becomes invalid if it is marked with
            // JAXB_NOT_REQUIRED target specification
            boolean jaxbNotRequired = false;
            ArrayList targetSpecs = tgAttr.getTargetSpecs();
            if (targetSpecs != null && targetSpecs.size() > 0) {
                for (int i = targetSpecs.size(); --i >= 0;) {
                    if (((TargetSpec) targetSpecs.get(i)).getID().equals(
                            TARGET_SPEC_ID_JAXB_NOT_REQUIRED)) {
                        jaxbNotRequired = true;
                        break;
                    }
                }
            }

            String validity = IR.getAttrElem(IR_ATTR_VALIDITY, tgAttr);
            if ((validity != null && validity.equals("0")) || jaxbNotRequired) {
                args = "-invalid " + args;
            }
        } else if ( tcType.equals(KEYWORD_RUNTIME) )  {
// keywords: runtime    
            if(isEmptyOut) {
                throw new TestFileException("\"Runtime\" testcate " + id + " cannot have an empty_output keyword."); 
            }
           args = tgAttr.getExecuteArgs();
           // See JaxbTckScript.java.execute.if (isDocument && !isRuntime ){
            if( args != null && !isValidationTest) {
            	args += " -schema "  + getSchemaListAsString(codeSet, tgAttr);
            }
        }

        if (excludeMap.size() > 0) {
            String test = getRelativeTestURL(id, tgAttr);
            Object testCases = excludeMap.get(test);
            if (testCases != null) {
                args += " -exclude " + testCases;
            } else if (excludeMap.containsKey(test)) {
                throw new IllegalArgumentException(
                        "the emitter cannon exclude all testcases,"
                                + " enumerate all testcases of " + test
                                + " in the emitter exclude list");
            }
        }
        return args;
    }

    protected String getExecuteClass(String id, TestGroupAttributes tgAttr,
            CodeSet codeSet) throws TestFileException {
        String tcType = getTCType(id, tgAttr, codeSet);
        if (tcType.equals(KEYWORD_SCHEMA)) {
            if (getKeywords(id, tgAttr, codeSet).indexOf(KEYWORD_NEGATIVE) == -1) {
                return BuildProperties.getString(
                        PROPERTY_PARAMETER_SCHEMA_EXECUTE_CLASS,
                        PARAMETER_SCHEMA_EXECUTE_CLASS);
            }
            return null; // negative schema test has no execute class
        } else if (tcType.equals(KEYWORD_DOCUMENT)) {
            return BuildProperties.getString(
                    PROPERTY_PARAMETER_DOCUMENT_EXECUTE_CLASS,
                    PARAMETER_DOCUMENT_EXECUTE_CLASS);
        }

        String attr = IR.getAttrElem(TestDescriptionIR.CLASS, tgAttr);
        if (attr == null || attr.equals("")) {
            attr = tgAttr.getExecuteClass();
        }
        return attr;
    }

    /* default value of the CT tests package name prefix */
    public final static String PACKAGE_NAME_PREFFIX = "javasoft.sqe";

    /**
     * the property is used to define package name prefix for CT tests package
     * name
     */
    public final static String PROPERTY_PACKAGE_NAME_PREFFIX = PROPERTY_PREFIX
            + "package.name.preffix";

    protected String getTestPackage(TestGroup tg, TestCase tc)
            throws TestFileException {
        // test classes must have the same package as schema interfaces
        // to access them without imports
        return getPackage(tg.getID(), tg.getTGAttributes(), tg.getCodeSet());
    }

    protected String getTestClass(TestGroup tg, TestCase tc)
            throws TestFileException {
        return IR.getAttrElem(IR_ATTR_GROUP, tg);
    }

    /* default value of the CTTest class imports */
    public final static String TEST_IMPORTS = 
	"javasoft.sqe.tests.api.jakarta.xml.bind.CTTest";

    /**
     * the property is used to define imports for a base class
     */
    public final static String PROPERTY_PREFIX_TEST_IMPORTS = PROPERTY_PREFIX
            + "test.imports.";

    public final static String PRINT_WRITER_CLASS = java.io.PrintWriter.class.getName();

    protected String getTestImports(TestGroup tg, TestCase tc, String baseClass)
            throws TestFileException {
        CodeSet cs = tg.getCodeSet();
        ArrayList imports = null;
        if (cs != null) {
            imports = cs.getImports();
        }
        if (imports == null) {
            imports = new ArrayList();
        }

        StringBuffer sb = new StringBuffer();

        // add import javasoft.sqe.javatest.Status;
        String statusClass = BuildProperties.getString(
                PROPERTY_TEST_STATUS_CLASS, TEST_STATUS_CLASS);
        if (imports.indexOf(statusClass) == -1) {
            imports.add(statusClass);
        }

        // add import java.io.PrintWriter;
        if (imports.indexOf(PRINT_WRITER_CLASS) == -1) {
            imports.add(PRINT_WRITER_CLASS);
        }

        String baseClassImports = BuildProperties.getString(
                PROPERTY_PREFIX_TEST_IMPORTS + baseClass, TEST_IMPORTS);
        if (baseClassImports != null) {
            StringTokenizer tokenizer = new StringTokenizer(baseClassImports);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (imports.indexOf(token) == -1) {
                    imports.add(token);
                }
            }
        }

        for (int i = 0; i < imports.size(); ++i) {
            sb.append("import " + imports.get(i) + ";\n");
        }
        if (sb.length() > 0) {
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * generate command line started and append SupportCode.
     */
    protected String getSupportCode(TestGroup tg, String className) {
        return "    /**\n"
                + "     * Command line starter.\n"
                + "     */\n"
                + "    public static void main(String[] args) {\n"
                + "        "
                + className
                + " test = new "
                + className
                + "();\n"
                + "        PrintWriter err = new PrintWriter(System.err, true);\n"
                + "        PrintWriter out = new PrintWriter(System.out, true);\n"
                + "        Status status = test.run(args, err, out);\n"
                + "        err.flush();\n"
                + "        out.flush();\n"
                + "        status.exit();\n"
                + "    }\n\n"
                + ((tg.getCodeSet().getSupportCode() == null) ? "" : "    "
                        + tg.getCodeSet().getSupportCode().getSource().trim()
                        + "\n\n");
    }

    protected String getTestBaseClass(TestGroup tg, TestCase tc) {
        String baseClass = tg.getCodeSet().getBaseClass();

        if (baseClass == null) {
            baseClass = BuildProperties.getString(
                    PROPERTY_TEST_DEFAULT_BASE_CLASS, TEST_DEFAULT_BASE_CLASS);
        }

        return baseClass;
    }

    /**
     * Java keyword list.
     */
    protected static final List<String> javaKeywords = Arrays.asList( 
    		"abstract", "default",
            "if", "private", "this", "boolean", "do", "implements",
            "protected", "throw", "break", "double", "import", "public",
            "throws", "byte", "else", "instanceof", "return", "transient",
            "case", "extends", "int", "short", "try", "catch", "final",
            "interface", "static", "void", "char", "finally", "long",
            "strictfp", "volatile", "class", "float", "native", "super",
            "while", "const", "for", "new", "switch", "continue", "goto",
            "package", "synchronized");

	private String fixJavaKeywords(String pkg) {
		int i = 0;
		StringBuffer result = new StringBuffer();
		for (String str : pkg.split("\\.")) {
			if (i > 1) {
				if (javaKeywords.contains(str)) {
					str = "_" + str;
                    }
				result.append((result.length() > 0 ? "." : "") + str);
                }
			i++;
            }
		// TODO: It's needed to convert a long package Name to shortest. 
		return result.toString();
        }


    protected String getPackage(String id, 
    							 TestGroupAttributes tgAttr,
            					 CodeSet codeSet) throws TestFileException {

        String prefix = BuildProperties.getString(	PROPERTY_PACKAGE_NAME_PREFFIX, 
        											PACKAGE_NAME_PREFFIX);

		try {
        	String schemaPath = getMainSchema(codeSet, tgAttr);
			String path = IR.getAttrElem(IR.relSourcePathAttrElemName, tgAttr).replaceAll("(.*)/.*", "$1/");
			/*
			 * Hack: Remove last path component for api tests 
			 * to avoid regeneration of api tests code
			 */
			URI testUri = path.startsWith("api") ? new URI(path.replaceAll("(.*)/.*/", "$1/")): new URI(path);
			URI schemaUri = testUri.resolve(schemaPath);
			/* 
			 * 1. toLowerCase
			 * 2. Remove .xsd extension
			 * 3. Remove .bind 
			 * 4. Replace package names started with digits to _digit 
			 * 5. Replace all slashes to dots 
			 * 6. Replace all non java identifier symbols to '_'
			 */
			String packageName = schemaUri.toString().toLowerCase()
				.replaceAll("\\.xsd$", "")
								 .replaceAll("\\.bind$", "")
				.replaceAll("[\\\\/]+", ".")
				.replaceAll("\\.([0-9])", "._$1")
				.replaceAll("[^a-z0-9.]", "_");
			/*
			 * 1. Remove first two parts of the path, 
			 * 2. prepends java keywords with _,
			 * 3. add prefix 
			 */
			packageName = (prefix + (prefix.length() == 0 ? "" : ".")) + 
			              fixJavaKeywords(packageName);
			return packageName;
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
        return null;
    }
        }

    /**
     * NIST tests need their description be in <pre>tag.</pre>
     */
    protected String PreQuote(String descr) {
        if (descr != null && descr.indexOf("File: ") != -1) {
            descr = descr.replaceFirst("File: ", "<pre>File: ").replaceFirst(
                    "\\s*\\z", "</pre>\n");
        }
        return descr;
    }

    /*
     * 1. change description for nist tests (insert it into the <pre> tag) 
     * 2. files section is constructed calling findExternalFileNames
     */
    protected String getTestGroupDescription(TestGroup tg)
            throws TestFileException {

        TestGroupDocumentation tgDoc = tg.getTGDocumentation();

        String id = tg.getID();
        String title = tgDoc.getTitle();
        String descr = PreQuote(tgDoc.getDescription());
        String comments = getTestGroupComments(tg);
        String files = filesToString(findExternalFileNames(tg.getCodeSet(), (String[])null));

        return constructTDDescription(id, title, descr, comments, files);
    }

    /*
     * added to support BTools 1.3
     *
     * @see com.sun.tgxml.tools.testgen.processors.emitter.ExternalEmitter#getTestGroupDescription(com.sun.tgxml.tjtf.api.tests.TestGroup,
     *      com.sun.tgxml.tjtf.api.tests.TestCase)
     */
    protected String getTestGroupDescription(TestGroup tg, TestCase tc)
            throws TestFileException {
        return getTestGroupDescription(tg);
    }

    public static ArrayList<String> findExternalFileNames(CodeSet cs,
			String[] extensions) throws TestFileException {
		ArrayList<String> res = findSourceFileNames(cs, extensions);
		if (cs == null) {
			return res;
		}

		ArrayList<?> data = cs.getData();
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				Data datum = (Data) data.get(i);
				String sourceName = null;
				if (datum instanceof ExternalData) {
					ExternalData edt = (ExternalData) datum;
					sourceName = edt.getSourceName();
				} else if (datum instanceof InlineData) {
					InlineData idt = (InlineData) datum;
					sourceName = idt.getTargetName();
				}
				if (sourceName != null) {
					if (extensions == null) {
						res.add(sourceName);
					} else {
						for (String ext : extensions) {
							if (sourceName.endsWith(ext)) {
								res.add(sourceName);
								break;
							}
						}
					}
				}
			}
		}
		return res;
	}

    public static ArrayList<String> findSourceFileNames(CodeSet cs, String[] extensions)
            throws TestFileException {
        ArrayList<String> res = new ArrayList<String>();
        if (cs == null) {
            return res;
        }

        ArrayList<?> classes = cs.getSupportClasses();
        if (classes != null) {
            for (int i = 0; i < classes.size(); i++) {
                SupportClass sc = (SupportClass) classes.get(i);
                String sourceName = null;
                if (sc instanceof ExternalSupportClass) {
                    ExternalSupportClass esc = (ExternalSupportClass) sc;
                    sourceName = esc.getSourceName();
                } else if (sc instanceof InlineSupportClass) {
                    InlineSupportClass isc = (InlineSupportClass) sc;
                    sourceName = isc.getTargetName();
                }
				if (sourceName != null) {
					if (extensions == null) {
						res.add(sourceName);
					} else {
						for (String ext : extensions) {
							if (sourceName.endsWith(ext)) {
								res.add(sourceName);
								break;
							}
						}
					}
				}
            }
        }
        return res;
    }

    static class XMLSchemaTestInlineGenerator extends UTDVisitorBase {

        TestGroup rootTG;

        File outputDir;

        String scInfo;

        public XMLSchemaTestInlineGenerator(TestGroup tg)
                throws TestFileException {
            rootTG = tg;

            String out = IR.getAttrElem("OutputDir", tg);
            if (out == null) {
                throw new TestFileException(LibResHandler.getResStr(
                        "testgen.error.no_outputdir", tg.getID()));
            } else {
                outputDir = new File(out);
            }
            scInfo = IR.getAttrElem("scInfo", tg);
        }

        public void visit_InlineSupportClass(InlineSupportClass isc)
                throws TestFileException {
            if (isc.isExport()) {
                generateFile(isc.getTargetName(), isc.getSource());
            }
            super.visit_InlineSupportClass(isc);
        }

        public void visit_InlineData(InlineData id) throws TestFileException {
            if (id.isExport()) {
                generateFile(id.getTargetName(), id.getData());
            }
            super.visit_InlineData(id);
        }

        public void generateFile(String fileName, String fileData)
                throws TestFileException {
            try {
                char[] buffer = new char[fileData.length() > 1024 * 32 ? 1024 * 32
                        : fileData.length()];
                File file = new File(outputDir, fileName);
                Reader reader = new StringReader(fileData);
                MiscUtils.mkdirs(file.getParentFile());
                FileWriter fw = new FileWriter(file);
                if (fileName.endsWith(".java")) {
                    // add copyright to java source
                    String copyright = BuildProperties.getPrefixString(
                            "testgen", "test.copyrightNotice");
                    if (copyright != null) {
                        fw.write("/*\n * "
                                + (scInfo == null ? fileName : scInfo)
                                + "\n *\n * "
                                + copyright.replaceAll("\n", "\n * ")
                                + "\n *\n */\n\n");
                    }
                }
                for (int i = reader.read(buffer); i >= 0; i = reader
                        .read(buffer)) {
                    fw.write(buffer, 0, i);
                }
                reader.close();
                fw.close();
            } catch (IOException ioe) {
                throw new EmbeddedTFException(ioe);
            }
        }
    }

    public void generateInlines(TestGroup tg) throws TestFileException {
        UTDVisitorBase myVisitor = new XMLSchemaTestInlineGenerator(tg);
        myVisitor.visit(tg);
    }

    protected boolean anchorGenerationMode(TestGroup tg) {
        return true;
    }

    /**
     * Splits testGroup by testcases or return array of the testGroup itself
     */
    protected TestGroup[] splitTestGroup(TestGroup tg) throws TestFileException {
        /*
         * we have to provide unnecessary second parameter because method
         * signature of super.splitTestGroup was changed in tools 1.3
         */
        TestGroup[] testGroups = super.splitTestGroup(tg, new HashMap());
        generateExportFiles(testGroups);
        return testGroups;
    }

    /*
     * added to support BTools 1.3
     *
     * @see com.sun.tgxml.tools.testgen.processors.emitter.ExternalEmitter#splitTestGroup(com.sun.tgxml.tjtf.api.tests.TestGroup,
     *      java.until.HashMap)
     */
    protected TestGroup[] splitTestGroup(TestGroup tg, HashMap tg2tc)
            throws TestFileException {
        return splitTestGroup(tg);
    }
}
