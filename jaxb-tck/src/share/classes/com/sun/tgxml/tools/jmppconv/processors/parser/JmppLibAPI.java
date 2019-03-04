/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import java.io.*;
import java.util.*;
import com.sun.jmpp.lib.LibAPIException;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.DataFactory;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.api.impl.TestSuiteImpl;
import com.sun.tgxml.util.IR;

public class JmppLibAPI extends com.sun.jmpp.lib.JmppLibAPI implements IRGenerator {
    
    Hashtable methodsHashtable = null;
    String storedRCfiles = "";
    TestGroup currentTGroup;
    TestCase currentTCase;
    ArrayList currentTCSpecs;
    
    ArrayList testCases;
    ArrayList testRoots;
    
    private AttrElem outputDirAttrElem = null;
    
    /**
     * Generates the set of TestRoot objects
     */
    public ArrayList generate(File inputFile) throws LibAPIException {
        inFile = inputFile;
        templatePackage = IRTDGenerator.getTemplatePackageName(inputFile);
        inName = inputFile.getName();
		if(inputFile.getParent() != null) {
			inDir = inputFile.getParent() + File.separator;
		}

        try {
            generate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LibAPIException(e.getMessage());
        }
        
        return ((JmppLibAPI)stepTwoLib).getRoots();
    }
    
    public void makeOut(){
        //loadDefaults();
        //outBuffer = new StringBuffer();
        makeTest();
        //testclose();
        //outBuffer = null;
    }
    
    public ArrayList getRoots() {
        return testRoots;
    }
        
    protected void runXLib(Class generatorClass) throws Exception {
        stepTwoLib = (JmppLibAPI)generatorClass.newInstance();
        passDataTo(stepTwoLib);
        stepTwoLib.makeOut();
    }

    /**
     * Overriden superclass's method.
     * @return system property name in which the path to TCK classes directory
     * should be specified - tck.classes.dir;
     */
     protected String getTCKDstDirVarName(){
         return "tck.classes.dir";
     }
    
   
    public void store() {
        checkVars(CHECK_VARS_STORE);
        
        //create testcase element
        ////
        if (currentTCase == null) {
            currentTCase = TestFactory.createTestCase();
            try {
                currentTCase.setID(testCaseID);
            } catch (Exception e) {
                throw new LibAPIException(e.getMessage());
            }

            TestCaseDocumentation tcDocumentation = 
                    DocumentationFactory.createTestCaseDocumentation();
            currentTCSpecs = new ArrayList();
            tcDocumentation.setTestCaseSpecs(currentTCSpecs);
            currentTCase.setTCDocumentation(tcDocumentation);
        }
        //create testcase spec
        ////
        TestCaseSpec tcSpec = createTestCaseSpec(testTechnique,
                method, 
                values,
                precondition,
                expected,
                testclass);
        TestCaseDocumentation tcDocumentation = currentTCase.getTCDocumentation();
        tcDocumentation.getTestCaseSpecs().add(tcSpec);
        nullTestcaseVars(CHECK_VARS_STORE);
    }
    
    
    /**
     * Creates test case specification element using
     * <code>method</code>, <code>values</code>, <code>precondition</code>, 
     * <code>expected</code> and <code>testTechnique</code> variable values.
     */
    protected TestCaseSpec createTestCaseSpec(int testTechnique,
                                                String method_sig, 
                                                String values,
                                                String precondition,
                                                String expected,
                                                String testedclass) {
        TestCaseSpec spec = DocumentationFactory.createTestCaseSpec();
        
        
        try {
        
            //process method signature;
            ////
            Method method = createMethod(method_sig, testedclass);
            if (method != null) {
                spec.setMemberSig(method.getSignature());
            }
            if (testTechnique != JmppLibAPI.ASSERTION_TESTING) {
				//process test technique
				////
				spec.setTestTechnique(new LibAPITestTechnique(testTechnique));

                ArrayList inputts = createInputs(method, values);
                if(inputts != null) {
                    spec.setInputs(inputts);
                }

                ExpectedResultValue outputValue = createExpectedResultValue (method, values);
                if(outputValue != null) {
                    spec.setExpectedResultValue(outputValue);
                }
            } else {
                ArrayList assertion = new ArrayList();
                assertion = createAssertions(values);
                if (assertion != null) {
                    spec.setAssertions(assertion);
                }
            }
            
            //process preconditions
            ////
            ArrayList preconditions = createPreconditions(precondition);
            if (preconditions != null) {
                spec.setPreconditions(preconditions);
            }
        
            //process expected result
            ////
            ArrayList expectedVal = createExpectedResultSideEffect(expected);
            if (expectedVal != null) {
                spec.setExpectedResultSideEffects(expectedVal);
            }
            
        } catch (Exception e) {
			e.printStackTrace();
            throw new LibAPIException(e.getMessage());
        }
       
        return spec;
    }
        
    public void gen() {
        checkVars(CHECK_VARS_GEN);
        addAuthor();
        store();
        TestCode tcCode = null;
        TestCaseAttributes tcAttr = null;
        try {
            String trimCode = trimBraces(code);
            tcCode = CodeFactory.createTestCode(Code.ctStr_langType_java, trimCode);
            currentTCase.setTestCode(tcCode);

            ArrayList tcTargetSpecs = 
                    IRTDGenerator.convertTargetSpecList(testcaseTargetSpecs);
            ArrayList tcRequiredResources = IRTDGenerator.
                    convertRequiredResources(getTestcaseRequiredResources());
            ArrayList tcAttrElems = IRTDGenerator.
                    convertAttrElems(getTestcaseAttrElems());
            if (tcTargetSpecs != null || tcRequiredResources != null
                    || tcAttrElems != null) {
                tcAttr = AttributesFactory.createTestCaseAttributes();
                tcAttr.setTargetSpecs(tcTargetSpecs);
                tcAttr.setRequiredResources(tcRequiredResources);
                tcAttr.setAttrElems(tcAttrElems);
                currentTCase.setTCAttributes(tcAttr);
            }

        } catch (Exception e) {
            throw new LibAPIException(e.getMessage());
        }
        if (testCases == null) testCases = new ArrayList();
        testCases.add(currentTCase);
        currentTCase = null;
        nullTestcaseVars();
    }
   
    protected ArrayList createInputs(Method method, String values) {
        Hashtable resValues = parseValues(values);
        Enumeration en = resValues.keys();
        ArrayList inputs = new ArrayList();
        while (en.hasMoreElements()) {
            Object key = en.nextElement();
            if(method.getParams() != null && method.getParams().contains(key)) {
                try {
                    Input input = DocumentationFactory.createInput(key.toString(), 
                            resValues.get(key).toString());
                    inputs.add(input);
                } catch (Exception e) {
                    throw new LibAPIException(e.getMessage());
                }
            } 
        }
        
        return inputs.isEmpty()?null:inputs;
    }
    

	protected ExpectedResultValue createExpectedResultValue(Method method, String values) {
		ExpectedResultValue output = null;
		if (method.type != null 
			    && !method.type.trim().equals("void")
			    && !method.isField) {
			Hashtable resValues = parseValues(values);

			if (resValues.containsKey("output")) {
                try {
					output = DocumentationFactory.createExpectedResultValue((String)resValues.get("output"));
               } catch (Exception e) {
                    throw new LibAPIException(e.getMessage());
			   }
			}
		}
		return output;	
	}

    protected ArrayList createAssertions(String value) {
        if (stringContentsCheck(value)) {
            ArrayList assertions = new ArrayList();
            try {
                assertions.add(DocumentationFactory.createInlineAssertion(value));
            } catch (Exception e) {
                throw new LibAPIException(e.getMessage());
            }
            return assertions;
        } else {
            return null;
        }
    }
    
    protected ArrayList createPreconditions(String value) {
        if (stringContentsCheck(value)) {
            ArrayList preconditions = new ArrayList();
            preconditions.add(value);
            return preconditions;
        } else {
            return null;
        }
    }
    
    protected ArrayList createExpectedResultSideEffect(String value) {
        ArrayList expected = new ArrayList();
        if (stringContentsCheck(value)) {
            ExpectedResultSideEffect result = null;
            try {
                result = DocumentationFactory.createExpectedResultSideEffect(value.trim());
				expected.add(result);

            } catch (Exception e) {
                throw new LibAPIException(e.getMessage());
            }
            return expected;
        } else {
            return null;
        }
    }
    
    protected Method createMethod(String method, String testedClass) {
        String key = strRemovedBlankSpaces(method);
        Method result = null;
        if (methodsHashtable != null
                && methodsHashtable.contains(key)) {
            result = (Method)methodsHashtable.get(key);
        } else {
            result = new Method(method, testedClass);
            try {
                result.init();
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            if (result != null) {
                if (methodsHashtable == null) methodsHashtable = new Hashtable();
                methodsHashtable.put(key, result);
            }
        }
        return result;
    }
    
    /**
     * Parses <code>values</code> public variable value in order 
     * to construct a hashtable with method parameter names as keys and their 
     * delimited with ":" values as values. 
     */
    protected Hashtable parseValues(String value) {
       Hashtable values = new Hashtable();
       StringTokenizer st = new StringTokenizer(value, "\n");
       while (st.hasMoreTokens()){
           String entity = st.nextToken();
           if (entity.indexOf(':') < 0) {
               //TODO: insert proper exception
               throw new LibAPIException("Incorrect values variable format");
           } else {
               int index = entity.indexOf(':');
               String key = entity.substring(0,index).trim();
               String val = entity.substring(index+1).trim();
               values.put(key, val);
           }
       }
       return values;
    }   
    
    public void testcreate() {
        super.testcreate();

        // output dir 
        if (outputDir != null && !outputDir.equals("")) {   
            outputDir += File.separator + getShortTestClassName();
        }
        
        outputDirAttrElem = AttributesFactory.createAttrElem();
    	try {
	        outputDirAttrElem.setName("OutputDir");
    	} catch (TestFileException e) {
	        // this should never happen
    	}
        outputDirAttrElem.setValue((outputDir!=null) ? outputDir : "");        
        
        testRoots = new ArrayList();
        
        // TestSuite
        TestSuite ts = new TestSuiteImpl();
        ts.setID(htmlindex);
        ArrayList attrElems = new ArrayList();
        attrElems.add(outputDirAttrElem);
        ts.setAttrElems(attrElems);
        try {
            ts.setTitle("Tests for " + testclass);
        } catch (TestFileException e) {
        }    
        
        ts.setDescription(index);
        testRoots.add(ts);
    }
    
    public void javaclose() {
        checkVars(CHECK_VARS_JAVACLOSE);
        currentTGroup = TestFactory.createTestGroup();
        try {
            currentTGroup.setID(file);
        } catch (Exception e) {
            throw new LibAPIException(e.getMessage());
        }

        if (testCases == null) {
            throw new LibAPIException("No test cases for current test root");
        }
        
        
        //process classinclude/uses variables
        ////
        ArrayList supportClasses = 
                createSupportClasses(uses, classinclude);
            
        //process codeinclue variable
        ////
        SupportCode supportCode =
                createSupportCode(codeinclude);
        
        //process imports variable
        ////
        ArrayList testCodeImports = createImports(imports);
        testCodeImports.addAll(createImports(getImportForTestClass(testpackage, testclass)));
        
        ArrayList testCodeDependencies = 
                createDependencies(dependencies, baseClass);
        
        // Check that imports do not duplicate dependencies, 
        // remove duplicates if any
        if ((testCodeImports != null) && (testCodeDependencies!=null))  {
            for (int i=0; i<testCodeImports.size(); i++){
                for (int j=0; j<testCodeDependencies.size(); j++) {
                    String imp = (String)testCodeImports.get(i);
                    String dep = null;
                    try {
                        dep = ((LibraryDependency)testCodeDependencies.get(j)).getID();
                    } catch (TestFileException e) {
                        e.printStackTrace();
                        throw new LibAPIException(e.getMessage());
                    }
                    if (imp.endsWith(dep)) {
                        testCodeImports.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }    
        
        ArrayList testCodeIOData = createIOData(rcfiles, iodata);
        
        CodeSet codeSet = createCodeSet(testCodeDependencies,
                                        testCodeImports,
                                        baseClass,
                                        supportCode,
                                        supportClasses,
                                        testCodeIOData);
        
        currentTGroup.setCodeSet(codeSet);
        
        //process test group documentation
        ////
        TestGroupDocumentation tgDocumentation =
            createTestGroupDocumentation(filetitle, 
                                         getAuthor(file), 
                                         testpackage,
                                         testclass);
        
        currentTGroup.setTGDocumentation(tgDocumentation);
        
        //process test group attributes
        ////
       
        if (keywords == null) {
            keywords = "";
        }          
        if (getDefaultKeywords() != null) {
            keywords = ((rmicClass == null) ? getDefaultRmicKeywords() : getDefaultKeywords()) + " " + keywords;
        }
        
        TestGroupAttributes tgAttributes = 
            createTestGroupAttributes(rcfiles,
                                         keywords, 
                                         executeArgs,
                                         remote,
                                         rmicClass,
                                         selectIf,
					                     context, 
                                         outputDir,
                                         testGroupTargetSpecs,
                                         getTestGroupAttrElems(),
                                         getTestGroupRequiredResources());
        if (tgAttributes != null) {
            currentTGroup.setTGAttributes(tgAttributes);
        }
            
        currentTGroup.setTestCases(testCases);
        testCases = null;
        testRoots.add(currentTGroup);
        currentTGroup = null;
        nullTestcaseVars();
        nullFileVariables();
        
    }
    
    
    /**
     * creates TestGroupAttributes entity based on <code>rcfiles</code>,
     * <code>keywords</code>, <code>executeArgs</code>, <code>remote</code>,
     * <code>rmicClass</code> and <code>selectIf</code> variables values
     */
    protected TestGroupAttributes createTestGroupAttributes(String rcfiles,
                                         String keywords, 
                                         String executeArgs,
                                         String remote,
                                         String rmicClass,
                                         String selectIf,
                                         String context,
                                         String outputDir,
                                         ArrayList testGroupTargetSpecs,
                                         ArrayList testGroupAttrElems,
                                         Enumeration tgRequiredResources) {
        
        TestGroupAttributes result = null;
        
        ArrayList resKeywordsList = null;
        String    resExecuteArgs  = null;
        ArrayList resRemotesList  = null;
        String    resRMICClasses  = null;
        ArrayList resSelectIfs    = null;
        String     resContext    = null;
        ArrayList attrElems      = new ArrayList();
        ArrayList resTargetSpecs  = null;
        ArrayList reqResources  = null;
        ArrayList tgAttrElems  = null;

        // source code info
        IR.setAttrElem(attrElems, "scInfo", scInfo == null ? "" : scInfo);

        // input filename
        String relPath = null;
        String path = getTestDescriptionName();
        String rootPath = BuildProperties.getString("tck.source.dir");
        if (rootPath != null && !rootPath.equals("")) {
            File f = new File(rootPath);
            try {
                rootPath = f.getCanonicalPath();
                if (path.startsWith(rootPath)) {
                    relPath = path.substring(rootPath.length() + 1);
                }
            } catch (IOException e) {
            }
        }
        if (relPath != null) {
            IR.setAttrElem(attrElems, IR.relSourcePathAttrElemName, relPath);
        }

        // tdFile
        IR.setAttrElem(attrElems, "tdFile", htmlindex);
        
        // output dir 
        attrElems.add(outputDirAttrElem);

        
        if(stringContentsCheck(keywords)) {
            resKeywordsList = new ArrayList();
            StringTokenizer st = new StringTokenizer(keywords);
            while(st.hasMoreTokens()) {
                resKeywordsList.add(st.nextToken().trim());
            }
        }
        
        if(stringContentsCheck(executeArgs)) {
            resExecuteArgs = executeArgs.trim();
        }
        
        if(stringContentsCheck(remote)) {
            resRemotesList = new ArrayList();
            StringTokenizer st = new StringTokenizer(remote, "\n");
            while(st.hasMoreTokens()) {
                resRemotesList.add(st.nextToken().trim());
            }
        }
        
        if(stringContentsCheck(rmicClass)) {
            resRMICClasses = rmicClass.trim();
        }
        
        if(stringContentsCheck(context)) {
            resContext = context.trim();
        }
 
        if(stringContentsCheck(selectIf)) {
            resSelectIfs = new ArrayList();
            StringTokenizer st = new StringTokenizer(selectIf, "\n");
            while(st.hasMoreTokens()) {
                resSelectIfs.add(st.nextToken().trim());
            }
        }

        resTargetSpecs = 
                IRTDGenerator.convertTargetSpecList(testGroupTargetSpecs);
        reqResources = 
                IRTDGenerator.convertRequiredResources(tgRequiredResources);
        tgAttrElems = 
                IRTDGenerator.convertAttrElems(testGroupAttrElems);
        if (tgAttrElems != null)
             attrElems.addAll(tgAttrElems);

        if (resKeywordsList != null
                || resExecuteArgs  != null
                || resRemotesList  != null
                || resRMICClasses  != null
                || resSelectIfs    != null
                || resContext      != null
                || attrElems    != null
                || resTargetSpecs  != null
                || reqResources  != null) {
            result = AttributesFactory.createTestGroupAttributes();
            result.setAttrElems(attrElems);
            if (resKeywordsList != null) result.setKeywords(resKeywordsList);
            if (resExecuteArgs  != null) result.setExecuteArgs(resExecuteArgs);
            if (resRemotesList  != null) result.setRemotes(resRemotesList);
            if (resRMICClasses  != null) result.setRMICClasses(resRMICClasses);
            if (resSelectIfs    != null) result.setSelectIfs(resSelectIfs);
            if (resContext    != null)   result.setContext(resContext);
            if (resTargetSpecs  != null) result.setTargetSpecs(resTargetSpecs);
            if (reqResources != null) result.setRequiredResources(reqResources);
        }
        return result;
    }
            

    /**
     * creates TestGroupDocumentation entity based on <code>filetitle</code>,
     * <code>author</code>, <code>testpackage</code>, <code>testclass</code>
     * variables
     */
    protected TestGroupDocumentation createTestGroupDocumentation(String fileTitle, 
                                         String authors,
                                         String testpackage,
                                         String testclass) {
        TestGroupDocumentation result = DocumentationFactory.createTestGroupDocumentation();
        if (stringContentsCheck(fileTitle)) {
            try {   
                result.setTitle(fileTitle);
            } catch (Exception e) {
                throw new LibAPIException(e.getMessage());
            }
        }
        
        if (stringContentsCheck(testpackage)) {
             result.setTestedPackage(testpackage);
        }
        
        if (stringContentsCheck(testclass)) {
            result.setTestedClass(testclass);
        }
        
        if (stringContentsCheck(authors)) {
            ArrayList authorList = new ArrayList();
            StringTokenizer st = new StringTokenizer(authors, ",");
            while(st.hasMoreTokens()) {
                authorList.add(st.nextToken().trim());
            }
            result.setAuthors(authorList);
        }
        
        return result;
    }
                
    /**
     * Creates code set entry for current test group based on
     */
    protected CodeSet createCodeSet(ArrayList dependencies,
                                    ArrayList imports,
                                    String baseClassName,
                                    SupportCode supportCode,
                                    ArrayList supportClasses,
                                    ArrayList externalData) {
        CodeSet result = null;
        try {
            result = CodeFactory.createCodeSet(dependencies, imports, baseClassName,
                                    supportCode, supportClasses, externalData);
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new LibAPIException(e.getMessage());
        }
        
        return result;
    }
    /**
     * Creates support classes entry based on the "uses" and "classinclude"
     * variables
     */
    protected ArrayList createSupportClasses(String uses, String classinclude) {
        ArrayList result = new ArrayList();
        if (stringContentsCheck(uses)) {
            StringTokenizer st = new StringTokenizer(uses);
            while(st.hasMoreTokens()) {
                String sourceName = st.nextToken().trim();
                File usesFile = new File(sourceName);
                if (usesFile.exists()) {
                    try {
                        copy(usesFile, getDestinationDir(usesFile));
                    } catch (FileNotFoundException fnfe) {
                        throw new LibAPIException("Copy operation "
                                + "failed: Can't find file: + usesFile");
                    } catch (IOException ioe) {
                        throw new LibAPIException("Copy operation "
                                + "failed: Can't open file: + usesFile");
                    }
                }
                try {
                    ExternalSupportClass esc = CodeFactory.createExternalSupportClass();
                    esc.setSourceLang(Code.ctStr_langType_java);
                    esc.setSourceName(sourceName);
                    result.add(esc);
                } catch (Exception e) {
                    throw new LibAPIException(e.getMessage());
                }
            }
        }
        
        if(stringContentsCheck(classinclude)) {
            InlineSupportClass isc = CodeFactory.createInlineSupportClass();
            isc.setSourceLang(Code.ctStr_langType_java);
            isc.setSource(classinclude);
            result.add(isc);
        }
        return result.isEmpty()?null:result;
    }
    
    /**
     * creates SupportCode entity based on th <code>codeinclude</code> variable
     */
    protected SupportCode createSupportCode(String codeInclude) {
        SupportCode result = null;
        if (stringContentsCheck(codeInclude)) {
            try {
                result = CodeFactory.createSupportCode(Code.ctStr_langType_java, 
                                                                    codeInclude);
            } catch (Exception e) {
                throw new LibAPIException(e.getMessage());
            }
        }
        return result;
    }
    
    /**
     * Creates imports entry based on the imports variable
     */
    protected ArrayList createImports(String imports) {
        ArrayList result = new ArrayList();
        if (stringContentsCheck(imports)) {            
            StringTokenizer st = new StringTokenizer(imports, ";");
            while(st.hasMoreTokens()) {
				String str = st.nextToken();
                if (str != null && str.indexOf("import") >= 0) {
			        // remove "import" string
			        str=str.substring(str.indexOf("import")+6).trim();
                    if (str.startsWith("javasoft.sqe.tests.api." + getTestPackageName(testpackage, testclass)) ||
                            !(str.startsWith("javasoft.sqe.jck.lib")
                                        || str.startsWith("javasoft.sqe.tests.api"))) {
                        result.add(str.trim());
                    }
                }
            }
        }
        
        return result;
    }

    /**
     * Creates dependencies. BaseClass is automatically added since this is 
     * dependence.
     */
    protected ArrayList createDependencies(String dependencies, String baseClass) {
        ArrayList result = new ArrayList(); 
        try {       
            if (stringContentsCheck(baseClass)) {
                int index = baseClass.lastIndexOf(".");
                if (index > -1) {
                    // Cut package name from base class here
                    if (baseClass.endsWith(".")) {
                        throw new LibAPIException("Invalid base class : " + baseClass);
                    }
                    baseClass = baseClass.substring(index + 1, baseClass.length() - 1);                        
                }
                if (baseClass.equals("XmlMultiTest")) {
                    result.add(CodeFactory.createLibraryDependency("xml.XmlMultiTest"));
                } else if (!baseClass.equals("MultiTest")) {
                    result.add(CodeFactory.createLibraryDependency(baseClass));
                }
            } 

            if (stringContentsCheck(dependencies)) {
                StringTokenizer st = new StringTokenizer(dependencies, " ");
                while(st.hasMoreTokens()) {
                    String lib = st.nextToken().trim();
                    if ((!lib.equals("")) && (!lib.equals(baseClass))) {
                        result.add(CodeFactory.createLibraryDependency(lib));
                    }
                }
            }        
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new LibAPIException(e.getMessage());
        }            
        return result.isEmpty() ? null : result;
    }

    /**
     * Creates iodatas. 
     */
    protected ArrayList createIOData(String rcfiles, String iodata) {    
        ArrayList result = null;
        
        if (rcfiles == null) rcfiles = "";
        if (stringContentsCheck(rcfiles + storedRCfiles)) {
            result = new ArrayList();
            result.addAll(processRCFiles(rcfiles + storedRCfiles));            
        }
                
        if (stringContentsCheck(iodata)) {
            if (result == null)
                result = new ArrayList();
            StringTokenizer st = new StringTokenizer(iodata, " ");
            while(st.hasMoreTokens()) {
                String dataName = st.nextToken().trim();
                File dataFile = new File(dataName);
                if (dataFile.exists()) {
                    try {
                        copy(dataFile, getDestinationDir(dataFile));
                    } catch (FileNotFoundException fnfe) {
                        throw new LibAPIException("Copy operation "
                                + "failed: Can't find file: + dataFile");
                    } catch (IOException ioe) {
                        throw new LibAPIException("Copy operation "
                                + "failed: Can't open file: + dataFile");
                    }
                }
                try {
                    ExternalData ed = DataFactory.createExternalData(dataName);
                    ed.setType(DataFactory.createDataType("iodata")); 
                    result.add(ed);
                } catch (TestFileException e) {
                    e.printStackTrace();
                    throw new LibAPIException(e.getMessage());
                }     
            }
        }        
        return result;
    }

    
    /**
     * Retrieves <code>-o</code> option and saves this value into
     * <code>outputDir</code> variable. This value will be used 
     * to put into TestRoot <code>outputDir</code> attr element at
     * <code>javaclose</code> call;
     */
    public void setProperties(Hashtable hash) {
        if (hash.get("-o") != null) {
            super.outputDir = hash.get("-o").toString();
        } else {
            super.outputDir = "";
        }
        
        if (hash.get("-w") != null) {
            setWorkDir(hash.get("-w").toString().trim());
        }
    }
    
    protected void installRCFiles(){
        storedRCfiles = stringContentsCheck(rcfiles)?" " + rcfiles:"";
    }
    
    protected ArrayList processRCFiles(String rcFiles){
        StringTokenizer st = new StringTokenizer(rcFiles);
        ArrayList result = new ArrayList();
        while (st.hasMoreTokens()){
    	    String rcFileName = st.nextToken().trim();
            //Add atribute
            ////
            ExternalData data = DataFactory.createExternalData();
            File rcFile = null;
            File rcLegacyFormat = new File(rcFileName + rcFileExt);
            File rcNonLegacyFormat = new File(rcFileName);
            try {
                if (rcNonLegacyFormat.exists()) {
                    data.setSourceName(rcFileName);
                }
                else {
                    data.setSourceName(rcFileName + rcFileExt);
                }
                data.setType(DataFactory.createDataType("resource"));
                rcFile = new File(data.getSourceName());
            } catch (TestFileException e) {
            }
            result.add(data);
            if (rcFile.exists()) {
                try {
                    copy(rcFile, getDestinationDir(rcFile));
                } catch (FileNotFoundException fnfe) {
                    throw new LibAPIException("Copy operation "
                            + "failed: Can't find file: + rcFile");
                } catch (IOException ioe) {
                    throw new LibAPIException("Copy operation "
                            + "failed: Can't open file: + rcFile");
                }
            }
        }
        return result.isEmpty()?null:result;
    }
    
    //utility methods
    ////
    private static boolean stringContentsCheck(String value) {
        return value != null && !value.trim().equals("");
    }
    
    private File getDestinationDir(File source) {
        String inFilePath = source.getAbsolutePath();
        File tmpInFile = new File(inFile.getAbsolutePath());
        
        String currentPath = tmpInFile.getParentFile().getAbsolutePath();
        
        if (inFilePath.startsWith(currentPath)) {
            File outputDirFile = new File(outputDir);
            File destFile = new File(outputDir, inFilePath.substring(currentPath.length()));
            return destFile.getParentFile();
        } else {
            return null;
        }
    }

    
    private static void copy(File source, File destDir) 
            throws FileNotFoundException, IOException {
        File destination = null;
        if (MiscUtils.mkdirs(destDir)
                || destDir.exists()) {
            destination = new File(destDir, source.getName());
            FileReader in = new FileReader(source);
            FileWriter out = new FileWriter(destination);
            int c;

            while ((c = in.read()) != -1)
                out.write(c);

            in.close();
            out.close();
        }
    }
	private static String trimBraces(String code) {
		String result = code.trim();
		if(result.startsWith("{")) {
			result = result.substring(1);
		}

		if(result.endsWith("}")) {
			result = result.substring(0,result.length() - 1);
		}
		return result;

	}
        


    //=====================internal classes===================================
    ////
    
    public class Method {
        Vector modifiers = new Vector();
        String type = null;
        String name = null;
        boolean isField = false;
        boolean isConstructor = false;
        String signature = null;
        Vector params = null;
        String testedClass = null;
        
    	/**
         * Constructor
         */
        
        public Method(String signature, String testedClass){
        	this.signature   = signature;
    		this.testedClass = testedClass;
    		
    	}
        
        public void init() throws Exception {
            String cashedToken;
            String currentToken;
            
    		//substring () contents
            ////
            
            int start = signature.indexOf('(');
            int end   = signature.indexOf(')');
            
            if(signature.lastIndexOf(')') != end 
                    || signature.lastIndexOf('(') != start
                    || start > end
                    || start*end < 0) {
                throw new Exception("Incorrect method signature: " + signature);
            }
            
            isField = start < 0;
            
            if (!isField) {
                String formalVals = signature.substring(start+1,end).trim();
                params = parseFormalVals(formalVals);
            }
            
            String shortSignature = signature.substring(0, isField?signature.length():start);
            StringTokenizer st = new StringTokenizer(shortSignature);
            if(st.countTokens() < 2) {
                throw new Exception("Incorrect method signature");
            }
            cashedToken = st.nextToken();
            while(st.hasMoreTokens()) {
                currentToken = st.nextToken();
                if (st.hasMoreTokens()) {
                    modifiers.add(cashedToken);
                    cashedToken = currentToken;
                } else {
                    name = currentToken;
                    isConstructor = name.trim().equals(testedClass.trim());
                    if (isConstructor) {
                        modifiers.add(cashedToken);
                    } else type = cashedToken;
                }
            }
        }
        
        
        //parse formal parameters
        ////
        private Vector parseFormalVals(String value) {
            Vector result = new Vector();
            if(JmppLibAPI.stringContentsCheck(value)) {
                StringTokenizer st = new StringTokenizer(value, ",");
                while(st.hasMoreTokens()) {
                    result.add(lastToken(st.nextToken()));
                }
            }
            
            return result.isEmpty()?null:result;
        }
    
        //returns last token
        ////
        private String lastToken(String value) {
            StringTokenizer st = new StringTokenizer(value);
            String result = null;
            while(st.hasMoreTokens()) result = st.nextToken();
            return result;
        }
        
        public Vector getParams() {
            return params;
        }
        
        public String getSignature() {
            return signature;
        }
        
        public boolean isConstructor() {
            return isConstructor;
        }
        
        public boolean isField() {
            return isField;
        }
    }
    
    public class LibAPITestTechnique implements TestTechnique {
        int technique = JmppLibAPI.ASSERTION_TESTING;
        public LibAPITestTechnique(int technique) {
            if (technique < 3
                    && technique > 0) {
                this.technique = technique;
            }
        }
        
        public boolean isAssertion() {
            return technique == JmppLibAPI.ASSERTION_TESTING;
        }
        
        public boolean isBoundary() {
            return technique == JmppLibAPI.BOUNDARY_VALUE_ANALYSIS;
        }
        
        public boolean isEqClass() {
            return technique == JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING;
        }
    }
}
