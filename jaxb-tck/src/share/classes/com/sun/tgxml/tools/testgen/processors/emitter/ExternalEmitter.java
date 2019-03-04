/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

import com.sun.tgxml.tjtf.*;
import com.sun.tgxml.tjtf.api.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.util.CopyrightManager;

import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.api.*;
import com.sun.tgxml.tools.elgen.*;
import com.sun.tgxml.tools.indexgen.api.TestSuite;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.util.IR;


public class ExternalEmitter
    extends StandardOptionHandler
    implements Generator, CopyrightManager.Constants, EmitterManager.HtmlConflictModeReplace
{
   
    protected ExcludeListCollector collector = null;    
    protected Properties m_properties;
    protected Hashtable testGroup_testIR = new Hashtable();
    protected Hashtable testGroup_htmlName = new Hashtable();
    protected String copyrightLink = null;
    protected Hashtable hash = new Hashtable();
	
    /**
    * set of conflict fiels
    */
    protected HashSet conflictFiles = new HashSet();


    public static final String EXTERNAL_TEST = "ExternalTest";
    public static final String EXTERNAL_MULTITEST = "ExternalMultiTest";

    public ExternalEmitter() {
    }

    
    public void setExcludeListCollector(ExcludeListCollector collector){
        this.collector = collector;
    }
    
    public void setProperties(Properties props){        
        m_properties = props;        
        copyrightLink = props == null ? null :
                props.getProperty(EmitterManager.TESTGEN_COPYRIGHT_LINK_PROP);
    }

    public Shell getShell() {
        return null;
    }

    public void setShell(Shell shell){}



    /**
     *  Emit the IR's into a set of File(s).
     */
    public void generate(IRObj[] trees) throws TestFileException {
        int numIRs = trees.length;
        
        hash = new Hashtable();
        HashMap testSuites = new HashMap();
        ArrayList tgList = new ArrayList();
        for (int i=0; i < numIRs; i++) {
            if (trees[i] instanceof TestGroup) {
                tgList.add(trees[i]);
            } else if (trees[i] instanceof TestSuite) {
                TestSuite ts = (TestSuite)trees[i];
                testSuites.put(ts.getID(), ts);
            } else {
                throw new TestFileException(LibResHandler.getResStr(
                        "testgen.error.unknownIR", trees[i].toString()));
            }
        }

        for (Iterator it = tgList.iterator(); it.hasNext();) {
            generateTestGroup((TestGroup)it.next(), testSuites);
        }

        writeHtmls();
        notifyExcludeListGenerator();
        
    }
       
    public void generate (IRObj root) throws TestFileException  {
         generate(new IRObj[]{root});
    }

    protected void generateTestGroup(TestGroup tg, HashMap testSuites) 
            throws TestFileException  {

        // Keeps mappings TestGroup[i] -> TestCase[i]
        // where i corresponds to test case number of the test group
        // to be split
        HashMap tg2tc = new HashMap();
        ResourceInstaller ri = new ResourceInstaller();
        
        TestGroup[] groups = splitTestGroup(tg, tg2tc);
        for (int i = 0; i < groups.length; i++) {        

            HtmlIR html = getHtmlIR(groups[i], testSuites);
            html.addTestedClass(
                    groups[i].getTGDocumentation().getTestedClass());
            HtmlTestIR testIR = getHtmlTestIR(groups[i], (TestCase)tg2tc.get(groups[i]));

            testGroup_testIR.put(groups[i], testIR);
            testGroup_htmlName.put(groups[i], html.getFileName());

            testIR.setAnchorRequired(anchorGenerationMode(tg));

            html.addHtmlTestIR(testIR);
            String sc_info = IR.getAttrElem("scInfo", tg);

            if (sc_info != null && !sc_info.equals("")) {
                html.addComment("File : " + sc_info);
            } else {
                String msg = "no scInfo attribute. File: " + html.getFileName();
                CopyrightManager.error("(ExternalEmitter) " + msg);
            }
            try {
                String html_crn = CopyrightManager.getCopyright(
                      // anchor ID
                    CRN_HTML_SHORT,
                      // macros to expand
                    new String[][] {
                        { MACRO_SC_INFO,       sc_info                 },
                        { MACRO_FULL_CRN_LINK, html.getCopyrightLink() }
                    }
                );
                if (html_crn != null) {
                    html.setCopyright(html_crn);
                    CopyrightManager.logCrnOk("(ExternalEmitter): " + html.getFileName());
                }
            } catch (CopyrightManager.Fault f) {
                String s = "(ExternalEmitter) " + f.getMessage();
                CopyrightManager.errorSkipCRN(s, html.getFileName());
            }
            InlineSupportUtil.generateInlines(
                groups[i],
                IR.getAttrElem("OutputDir", groups[i]),
                sc_info,
                BuildProperties.getPrefixString(
                    "testgen",
                    "test.copyrightNotice"
                ),
                detectTestPackageName(groups[i]),
                null //no imports by default
            );
            
            ri.installResources(groups[i]);
        }
    }

    /**
     * Splits testGroup by testcases or return array of the testGroup itself
     */
    protected TestGroup[] splitTestGroup(TestGroup tg, HashMap tg2tc)
            throws TestFileException {
        if (EXTERNAL_MULTITEST.equals(LibUtils.getTestType(tg))) {
            return new TestGroup[] {tg};
        } else {
            return splitTestGroupByTestcases(tg, tg2tc);
        }
    }

    /**
     * Return array of TestGroups. Split testgroup by TestCases.
     * Each testcase is treated as TestGroup
     * If TestGroup has no testcases returns array with one element:
     * this TestGroup.
     */
    protected TestGroup[] splitTestGroupByTestcases(TestGroup tg, HashMap tg2tc)
                throws TestFileException {
        ArrayList splitted = new ArrayList();

        if (tg.getTestCases() == null) {
             throw new TestFileException("TestGroup: " + tg.getID() 
                 + " has no testcases");
        }
        Iterator tcs = tg.getTestCases().iterator();
        if (!tcs.hasNext()) {
            splitted.add(tg);         
        } else {
            while (tcs.hasNext()) {
                TestCase  cur_tc = (TestCase)tcs.next();
                TestGroup cur_tg = testcase2TestGroup(cur_tc, tg);
                splitted.add(cur_tg);
                tg2tc.put(cur_tg, cur_tc);
            }
        }

        return (TestGroup[])splitted.toArray(new TestGroup[0]);
    }

    protected TestGroup testcase2TestGroup(TestCase tc, TestGroup tg)
                throws TestFileException {
        String id = tc.getID();

        TestGroupDocumentation tgDoc = tcDoc2tgDoc(
            tc.getTCDocumentation(), tg.getTGDocumentation());

        TestGroupAttributes tgAttr = tcAttr2tgAttr(
            tc.getTCAttributes(), tg.getTGAttributes());

        CodeSet codeSet = tc.getCodeSet();

        ArrayList testCases = new ArrayList();

        return TestFactory.createTestGroup
                (id, tgDoc, tgAttr, codeSet, testCases);

    }

    protected TestGroupDocumentation tcDoc2tgDoc(
            TestCaseDocumentation tcDoc, TestGroupDocumentation tgDoc)
                throws TestFileException {

          TestGroupDocumentation result = null;
          if (tgDoc != null)
               result = DocumentationFactory.cloneTestGroupDocumentation(tgDoc);
          else 
               result = DocumentationFactory.createTestGroupDocumentation();

          if (tcDoc != null) {
               String title = tcDoc.getTitle();
               String descr = tcDoc.getDescription();
               ArrayList authors = tcDoc.getAuthors();

               if (title != null)
                    result.setTitle(title);

               if (descr != null)
                    result.setDescription(descr);

               if (authors != null)
                    result.setAuthors(authors);               
          }
          return result;
    }


    protected TestGroupAttributes tcAttr2tgAttr(
            TestCaseAttributes tcAttr, TestGroupAttributes tgAttr) 
                throws TestFileException {

          if (tgAttr == null && tcAttr == null)
               return null;


          TestGroupAttributes result = null;
          if (tgAttr != null)
              result = AttributesFactory.cloneTestGroupAttributes(tgAttr); 
          else 
              result = AttributesFactory.createTestGroupAttributes(); 

          if (tcAttr != null) {
               String timeout = tcAttr.getTimeout();

               ArrayList attrElems = tcAttr.getAttrElems();
               attrElems.addAll(tgAttr.getAttrElems());

               ArrayList reqResources = tcAttr.getRequiredResources();
               reqResources.addAll(tgAttr.getRequiredResources());

               ArrayList targetSpecs = tcAttr.getTargetSpecs();
               targetSpecs.addAll(tgAttr.getTargetSpecs());

               if (timeout != null)
                    result.setTimeout(timeout);

               result.setAttrElems(attrElems);          
               result.setRequiredResources(reqResources);          
               result.setTargetSpecs(targetSpecs);          
          }
          return result;
    }


    /** 
     * Returns true if anchor for the testgroup is always required.
     * false - if anchor is not required when there is only one TestGroup in 
     * one html file and this TestGroup has no testcases.
     * Returned value depends on "testType" and "anchorRequired" AttrElems
     * For ExternalMultiTest returns true if "anchorRequired" is not
     * specified or has value "yes".
     * For ExternalTest returns false if "anchorRequired" is not
     * specified or has value "no".
     */
    protected boolean anchorGenerationMode(TestGroup tg) {

        String attrElemValue = IR.getAttrElem("anchorRequired", tg);

        if (EXTERNAL_MULTITEST.equals(LibUtils.getTestType(tg))) {
            if (attrElemValue == null || attrElemValue.equals("yes")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (attrElemValue == null || attrElemValue.equals("no")) {
                return false;
            } else {
                return true;
            }
        }
    }


    /**
     * Returns HtmlTestIR corresponding the passed TestGroup
     */
    protected HtmlTestIR getHtmlTestIR(TestGroup tg, TestCase tc)
            throws TestFileException {

        TestDescriptionIR tdIR = constructTestDescriptionIR(tg, tc);
        String tdDescription = getTestGroupDescription(tg, tc);
        TestGroupDocumentation tgDoc = tg.getTGDocumentation();
        String id = tg.getID();
        String authors = getAuthors(tgDoc);

        TestGroupIR testGroupIR = new TestGroupIR(id);
        testGroupIR.setDescription(tdDescription);
        testGroupIR.setComments(authors);
        testGroupIR.setTestDescriptionIR(tdIR);
        
        if (tg.getTestCases() != null) {
            for (Iterator it = tg.getTestCases().iterator(); it.hasNext();) {
                testGroupIR.addTestCase((TestCase)it.next());
            }
        }

        return testGroupIR;
    }

    /**
     * Constructs TestDescriptionIR for the passed TestGroup and TestCase.
     * It can be overridden by subclasses if needed.
     * The current implementation uses 
     * constructTestDescriptionIR(id, title, tgAttr, codeSet, tc) method
     * to create TestDescriptionIR.
     */
    protected TestDescriptionIR constructTestDescriptionIR(
            TestGroup tg, TestCase tc) throws TestFileException {

        TestGroupDocumentation tgDoc = tg.getTGDocumentation();

        String id = tg.getID();
        String title = tgDoc.getTitle().trim();
        String context = getTestGroupContext(tg);
        String executeArgs = getTestGroupExecuteArgs(tg);


        TestGroupAttributes tgAttr = tg.getTGAttributes();
        CodeSet codeSet = tg.getCodeSet();        

        TestDescriptionIR tdIR =
            constructTestDescriptionIR(id, title, tgAttr, codeSet, tc,
            context, executeArgs);
        TDEntriesCollector tdCollector = createTDEntriesCollector();
        if (EXTERNAL_MULTITEST.equals(LibUtils.getTestType(tg))) {
            tdCollector.processTestItem(tg);
        } else {
            tdCollector.processTestItem(tc);
        }
        tdCollector.putIntoTD(tdIR);
        return tdIR;
    }

    /**
     * @deprecated  replaced by <code>TestDescriptionIR
     * constructTestDescriptionIR(String id, String title,
     * TestGroupAttributes tgAttr, CodeSet codeSet, TestCase tc,
     * String context, String executeArgs)()</code>.
     * It does not support variable ExecuteArgs and Context.
     */
    protected TestDescriptionIR constructTestDescriptionIR(
             String id,
             String title,
             TestGroupAttributes tgAttr,
             CodeSet codeSet,
             TestCase tc)
                 throws TestFileException {

         return constructTestDescriptionIR(id, title, tgAttr, codeSet, tc, 
              tgAttr.getContext(), tgAttr.getExecuteArgs());
    }

    /**
     * Constructs TestDescriptionIR by: id, title, tgAttr, CodeSet.
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


        ArrayList sources = findSourceFileNames(codeSet, tc);

        TestDescriptionIR tdIR = new TestDescriptionIR();
       
        tdIR.add(TestDescriptionIR.TITLE, id + " - " + title);
        tdIR.add(TestDescriptionIR.NAME, id);
        tdIR.add(TestDescriptionIR.CLASS, tgAttr.getExecuteClass());
        tdIR.add(TestDescriptionIR.KEYWORDS, tgAttr.getKeywords());
        tdIR.add(TestDescriptionIR.CONTEXT, context);
        tdIR.add(TestDescriptionIR.EXECUTE_ARGS, executeArgs);
        tdIR.add(TestDescriptionIR.EXECUTE_NATIVE, tgAttr.getExecuteNative());
        tdIR.add(TestDescriptionIR.REMOTE, tgAttr.getRemotes());
        tdIR.add(TestDescriptionIR.RMIC_CLASSES, tgAttr.getRMICClasses());
        tdIR.add(TestDescriptionIR.SELECT_IF, tgAttr.getSelectIfs());
        tdIR.add(TestDescriptionIR.TIMEOUT, tgAttr.getTimeout());
        tdIR.addRef(TestDescriptionIR.SOURCE, sources);

        return tdIR;
    }

    protected String getTestGroupDescription(TestGroup tg, TestCase tc)
             throws TestFileException {

        TestGroupDocumentation tgDoc = tg.getTGDocumentation();

        String id = tg.getID();
        String title = tgDoc.getTitle();
        String descr = tgDoc.getDescription();
        String comments = getTestGroupComments(tg); 
        String files = filesToString(findExternalFileNames(tg.getCodeSet(), tc));

        return constructTDDescription(id, title, descr, comments, files);
    }


    protected String constructTDDescription(String id, String title, 
            String descr, String comments, String files) {

        StringBuffer sb = new StringBuffer();
        sb.append("<b><big>TEST " + id + "</big></b> - " + title);


        if ( descr != null) {
            sb.append("\n<P><h3>DESCRIPTION</h3>\n");
            sb.append(descr.trim());
        }

        if ( comments != null) {
            sb.append("\n<P><h3>COMMENTS</h3>\n");
            sb.append(comments.trim());
        }
        if ( files != null) {
            sb.append("\n<P><h3>FILES</h3>\n");
            sb.append(files.trim());
        }
        return sb.toString();
    }

    protected TDEntriesCollector createTDEntriesCollector() {
        return new TDEntriesCollector();
    }                        

    protected String filesToString(ArrayList files) {
        if (files == null || files.size() == 0)
            return null;
        StringBuffer sb = new StringBuffer();

        sb.append("<ul>\n");
        for (Iterator it = files.iterator() ; it.hasNext() ;) {
            String file = (String)it.next();
            sb.append("  <li><a href=\"" + file + "\">" + file + "</a>\n");
        }
        sb.append("</ul>\n");
        return sb.toString();
    }

    protected String getTestGroupComments(TestGroup tg) {
        ArrayList docelems = tg.getTGDocumentation().getDocElems();
        if (docelems != null && docelems.size() > 0) {
            for (int i = 0; i < docelems.size(); i++) {
                DocElem  de = (DocElem)docelems.get(i);
                if ("comments".equals(de.getName())) {
                    return de.getValue();
                }
            }
        }
        return null;
    }

    protected String getAuthors(Documentation doc) {
        if (doc == null)
            return null;
        ArrayList authors = doc.getAuthors();
        if (authors != null && authors.size() > 0) {
            Iterator it = authors.iterator();
            StringBuffer sb = new StringBuffer("Authors: " + it.next());
            while (it.hasNext()) {
                sb.append(", ");
                sb.append(it.next());
            }
            return sb.toString();
        } else {
             return null;
        }       
    }

    protected String detectTestPackageName(TestGroup tg) {
         if (tg == null)
             return null;

         TestGroupAttributes tgAttr = tg.getTGAttributes();
         if (tgAttr == null)
             return null;

         String className = tgAttr.getExecuteClass();
         int index = (className == null ? -1 : className.lastIndexOf("."));
         return index < 0 ? null : className.substring(0,index);
    }

    protected String getTestGroupContext(TestGroup tg) {
        return new TGVisitor(tg).getContext();
    }

    protected String getTestGroupExecuteArgs(TestGroup tg) {
        return new TGVisitor(tg).getExecuteArgs();
    }

    ArrayList findSourceFileNames(CodeSet cs, TestCase tc) throws TestFileException {
        ArrayList res = new ArrayList();
        if(cs == null) {
            return res;
        }

        ArrayList classes = cs.getSupportClasses();
        if(classes != null) {
            for (int i = 0; i < classes.size(); i++) {
                SupportClass sc = (SupportClass)classes.get(i);
                if (sc instanceof ExternalSupportClass) {
                    String var_name = tc == null ? null : tc.getVarName();
                    String src = ((ExternalSupportClass)sc).getSourceName();
                    res.add(LibUtils.removeVariantNameFromSource(src, var_name));
                } else if (sc instanceof InlineSupportClass) {
                    InlineSupportClass isc = (InlineSupportClass)sc;
                    if (isc.isExport() && (isc.getSourceLang() == null
                            || "java".equals(isc.getSourceLang()))) {
                        res.add(isc.getTargetName());
                    }
                }
            }
        }
        return res;
    }

    ArrayList findExternalFileNames(CodeSet cs, TestCase tc) throws TestFileException {
        ArrayList res = findSourceFileNames(cs, tc);
        if(cs == null) {
            return res;
        }
        ArrayList data = cs.getData();
        if(data != null) {
            for (int i = 0; i < data.size(); i++) {
                Data ed = (Data)data.get(i);
                if (ed instanceof ExternalData) {
                    res.add(((ExternalData)ed).getSourceName());
                } else  {
                    res.add(((InlineData)ed).getTargetName());
                }
            }
        }

        return res;
    }


    protected HtmlIR getHtmlIR(TestGroup tg, HashMap testSuites)
            throws TestFileException {      

        String tdFile = LibUtils.getTDFile(tg);
        String indexName = (tdFile == null) ? tg.getID() : tdFile;
        String outDir = IR.getAttrElem("OutputDir", tg);
        String fileName =  ((outDir == null) ? "" : outDir) + File.separator 
            + indexName + ".html";
    
        if (hash.containsKey(fileName)) {
            return (HtmlIR)(hash.get(fileName));
        } else {
            MyHtmlIR html = new MyHtmlIR();
            html.setFileName(fileName);
            html.setCopyrightLink(copyrightLink);
			html.setIndexName(indexName);
            TestSuite ts = (TestSuite)testSuites.get(indexName);
            if (ts != null) {               
                html.setHeader(ts.getDescription());
                html.setTitle(ts.getTitle());
            }
            hash.put(fileName, html);
            return html;
        }
    }


    /**
    *  overriden version implements html conflict mode
    */
    protected void writeHtmls()
    		throws TestFileException {
        Enumeration e = hash.keys();
    	while (e.hasMoreElements()) {
            String fileName = (String) e.nextElement();
            MyHtmlIR html = (MyHtmlIR) hash.get(fileName);
            try {
                File outputFile = new File(fileName);
                MiscUtils.mkdirs(outputFile.getParentFile());
                /* Conflict mode case */
                if (isInConflictMode(html.getIndexName()) && outputFile.exists()) {
                    // replace   in original html file
                    StringBuffer outBuffer = new StringBuffer();
                    BufferedReader br = new BufferedReader(new FileReader(outputFile));
                    boolean changed = false;
                    while (br.ready()) {
                        String line = br.readLine();
                        if (EmitterManager.HtmlConflictModeIntf.AFTER_TOPLIST.equals(line)) {
                            changed = true;
                            outBuffer.append(html.testTable());
                        } else if (EmitterManager.HtmlConflictModeIntf.AFTER_TABLE.equals(line)) {
                            changed = true;
                            outBuffer.append(html.testToString());
                        } else
                            outBuffer.append(line + "\n");
                    }
                    if (!changed) System.err.println("WARNING: Html conflict mode is on but nothing has been changed!");
                    br.close();
                    FileWriter os = new FileWriter(outputFile);
                    os.write(outBuffer.toString());
                    os.close();
                } else {
                    FileWriter os = new FileWriter(outputFile);
                    os.write(html.toString());
                    os.close();
                }
            } catch (IOException ioe) {
                throw new TestFileException(ioe.toString());
            }
        }
    }

    /** 
     * notifies ExcludeListGenerator 
     */
    protected void notifyExcludeListGenerator()
             throws TestFileException {
        Enumeration testGroups = testGroup_testIR.keys();
        while (testGroups.hasMoreElements()) {
            TestGroup tg = (TestGroup)testGroups.nextElement();
            HtmlTestIR testIR = (HtmlTestIR)testGroup_testIR.get(tg);
            String htmlName = (String)testGroup_htmlName.get(tg);

            if (!testIR.isAnchorRequired()) {
                notifyExcludeListGenerator(tg, htmlName);
            } else {
                String tdName = htmlName + "#" + testIR.id();            
                notifyExcludeListGenerator(tg, tdName);
                ArrayList testCases = tg.getTestCases();
                if (testCases != null && testCases.size() > 0) {
                    for (Iterator it = testCases.iterator(); it.hasNext();) {
                        TestCase tc = (TestCase)it.next();
                        notifyExcludeListGenerator(tc, tdName);
                    }
                }
            }
        }
    }


    protected void notifyExcludeListGenerator(TestItem ti, String fileName)
             throws TestFileException {
        // set testURL     
        IR.setAttrElem(ti, ExcludeListUtils.TestDescriptionURLAttrElemName, fileName);        
        try {
            if (ti instanceof TestCase) {
                // set testCaseName
                IR.setAttrElem(ti, ExcludeListUtils.TestCaseNameAttrElemName, ti.getID());
                collector.addEntry((TestCase) ti);    
            } else if (ti instanceof TestGroup) {
                collector.addEntry((TestGroup) ti);    
            }        
        } catch (IncorrectAttributesException e) {
            throw new TestFileException (e.toString());
        } catch (IOException e) {
            throw new TestFileException (e.toString());
        }
    }
	
	/**
	*  Adds conflict files or clear file set if parameter is null
	*/    
    public void setConflictFiles(HashSet files) {
    	if (files != null) 
	    	conflictFiles.addAll(files);
		else 
			conflictFiles.clear();    
    }
	
    /**
	*	Checks whenever file is in conflict set
	*/	
    public boolean isInConflictMode(String file) {
		return conflictFiles.contains(file);
    }
	
    /**
     * Access to protected memebers
     * Incapsulates index file (tdFile) information
     */
    class MyHtmlIR extends HtmlIR {
        private String indexName;

        public String testToString() {
            return super.testToString();
        }

        public String testTable() {
            return super.testTable();
        }
		
		/**
		* Helper method 
		*/
        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }
		
		/**
		* Helper method
		*/
        public String getIndexName() {
            return indexName;
        }
    }


}
