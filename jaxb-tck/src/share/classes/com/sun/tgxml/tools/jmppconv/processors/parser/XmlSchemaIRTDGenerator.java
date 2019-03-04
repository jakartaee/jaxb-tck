/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.util.ArrayList;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.File;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.documentation.DocumentationFactory;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.tests.TestFactory;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.CodeFactory;
import com.sun.tgxml.tjtf.api.code.ExternalSupportClass;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.DataFactory;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler; 
import com.sun.tgxml.tools.jmppconv.processors.parser.IRTDGenerator;
import com.sun.tgxml.tools.jmppconv.processors.parser.JmppLibXMLSchema;
import com.sun.jmpp.JmppException;
import com.sun.jmpp.lib.TDGenerator;
import com.sun.jmpp.lib.JmppLibTest;


/**  
 * Class for generation a test description basing on JmppLibTest variable set.
 * The descriptions are generated as IR objects.
 */

class XmlSchemaIRTDGenerator extends IRTDGenerator {

   public static final String TG_ATTR_ELEM_XMLSCHEMA_TESTYPE_VALUE = "XMLSchemaTest";
   public static final String TC_ATTR_ELEM_VALIDITY_NAME = "validity";
   public static final String VALIDITY_VALID_VALUE = "1";
   public static final String VALIDITY_INVALID_VALUE = "0";
   public static final boolean VALID_SCHEMA = true;
   public static final boolean INVALID_SCHEMA = false;
   public static final boolean VALID_XML_DOC = true;
   public static final boolean INVALID_XML_DOC = false;
   
    public void makeTD(JmppLibTest lib){
        try {
            if (((JmppLibXMLSchema)lib).singleSchema) {
                processSingleSchema((JmppLibXMLSchema)lib);
            } else if (((JmppLibXMLSchema)lib).biSchema) {
                processBiSchema((JmppLibXMLSchema)lib);
            } else {
                processSimpleSchema(((JmppLibXMLSchema)lib));
            }
        } catch (TestFileException tfe) {
            throw new JmppException(tfe);
        }
    }

    public void processSingleSchema(JmppLibXMLSchema lib) throws TestFileException {
        TestGroup tg = null;
        ArrayList tgs = getRoots();
        if (tgs != null) {
            if (tgs.size() == 1){
                tg = (TestGroup)tgs.get(0);
                createTG(tg, lib);
                return;
            } else if (tgs.size() > 1){
                throw new TestFileException("singleSchema mode, but more than 1 TG created");
            }
        }
        tg = createTG(null, lib);
        storeRoot(tg);
        return;
    }

    public void processSimpleSchema(JmppLibXMLSchema lib) throws TestFileException {
        TestGroup tg = createTG(null, lib);
        storeRoot(tg);
    }    

    public void processBiSchema(JmppLibXMLSchema lib) throws TestFileException {
        ArrayList tgs = createBiSchemaTGs(lib);
        for (int i = 0; i < tgs.size(); ++i){
            storeRoot((TestRoot)tgs.get(i));
        }
        return;
    }
    
    public TestGroup createTG(TestGroup tg, JmppLibXMLSchema lib) throws TestFileException {
        if (tg == null){
            if (lib.isValidSchema == true) {
                tg = _createTG(lib, (lib.singleSchema ? lib.sharedName() : lib.test), VALID_SCHEMA);
                tg.addTestCase(createSchemaTC(VALID_SCHEMA, lib, tg));
	    } else {
                tg = _createTG(lib, (lib.singleSchema ? lib.sharedName() : lib.test), INVALID_SCHEMA);
	        tg.addTestCase(createSchemaTC(INVALID_SCHEMA, lib, tg));
	        return tg;
	    }
        } else if (lib.isValidSchema == false) {
	    return tg;
        }
        if (lib.createPositiveCase){
             tg.addTestCase(createTC(VALID_XML_DOC, lib, tg));
        }
        if (lib.createNegativeCase){
             tg.addTestCase(createTC(INVALID_XML_DOC, lib, tg));
        }
        return tg;
    }

    public ArrayList createBiSchemaTGs(JmppLibXMLSchema lib)  throws TestFileException  {
        ArrayList al = new ArrayList();
	if (lib.createPositiveCase){
	    TestGroup tg = _createTG(lib, lib.test + "_p", VALID_SCHEMA);
	    tg.addTestCase(createSchemaTC(VALID_SCHEMA, lib, tg));
            tg.addTestCase(createTC(VALID_XML_DOC, lib, tg));
            al.add(tg);
	}
	if (lib.createNegativeCase){
	    TestGroup tg = _createTG(lib, lib.test + "_n", INVALID_SCHEMA);
            tg.addTestCase(createSchemaTC(INVALID_SCHEMA, lib, tg));
            al.add(tg);
	}
        return al;
    }

    public TestGroup _createTG(JmppLibXMLSchema lib, String tgID, boolean valid_schema) throws TestFileException {
        TestGroup tg = TestFactory.createTestGroup();
        tg.setID(tgID);
        tg.setTGAttributes(createTGAttributes(lib, valid_schema));
        tg.setTGDocumentation(createTGDocumentation(lib, valid_schema));
        tg.setCodeSet(createTGCodeSet(lib, tgID));
        return tg;
    }

    protected TestGroupDocumentation createTGDocumentation(JmppLibXMLSchema lib, boolean valid_schema) throws TestFileException {
        TestGroupDocumentation tgDoc = createTGDocumentation(lib);
        tgDoc.setTitle(lib.title + (valid_schema ? " (valid schema)" : " (invalid schema)"));
        tgDoc.setDescription(lib.description);
        return tgDoc;
    }

    protected TestGroupAttributes createTGAttributes(JmppLibXMLSchema lib, boolean valid_schema) throws TestFileException {
        TestGroupAttributes tgAttrs = AttributesFactory.createTestGroupAttributes();
        if (lib.properties != null && !lib.properties.trim().equals("")) {
            tgAttrs.setContext(lib.properties);
        }
        if (lib.testTimeout > 0){
            tgAttrs.setTimeout(Integer.toString(lib.testTimeout));
        }
        tgAttrs.setTargetSpecs(createTGTargetSpecs(lib, valid_schema));
        tgAttrs.setAttrElems(createTGAttrElems(lib));
        return tgAttrs;
    }

    protected ArrayList createTGTargetSpecs(JmppLibXMLSchema lib, boolean valid_schema) throws TestFileException {
        String ts = null;
        if (lib.singleSchema) {
            ts = lib.getAttr(lib.TARGET_SPEC_SINGLE_SCHEMA_STR);
        } else if (lib.biSchema) {
            if (valid_schema) {
                ts = lib.getAttr(lib.TARGET_SPEC_BISCHEMA_POS_CASE_STR);
            } else {
                ts = lib.getAttr(lib.TARGET_SPEC_BISCHEMA_NEG_CASE_STR);
            }
        } else {
            ts = lib.getAttr(lib.TARGET_SPEC_SIMPLE_SCHEMA_STR);
        }
        return createTargetSpecs(lib, ts);
    }

    protected ArrayList createTCTargetSpecs(JmppLibXMLSchema lib, boolean positive) throws TestFileException {
        String ts = null;
        if (lib.biDoc) {
            if (positive) {
                ts = lib.getAttr(lib.TARGET_SPEC_BIDOC_POS_CASE_STR);
            } else {
                ts = lib.getAttr(lib.TARGET_SPEC_BIDOC_NEG_CASE_STR);
            }
        } else {
            ts = lib.getAttr(lib.TARGET_SPEC_SINGLE_DOC_STR);
        }
        return createTargetSpecs(lib, ts);
    }

    protected CodeSet createTGCodeSet(JmppLibXMLSchema lib, String schemaName) throws TestFileException {
        CodeSet tgCodeSet = CodeFactory.createCodeSet();
        ExternalSupportClass esc = CodeFactory.createExternalSupportClass();
        esc.setSourceName(schemaName + ".xsd");
        esc.setSourceLang("xml");
        ArrayList al = new ArrayList();
        al.add(esc);
        tgCodeSet.setSupportClasses(al);
        tgCodeSet.setData(createTGData(lib, schemaName));
        return tgCodeSet;
    }

    protected ArrayList createTGData(JmppLibXMLSchema lib, String schemaName) throws TestFileException {
        String sources[] = new String[lib.sourceFiles.size()];
        schemaName += ".xsd";
        if (lib.sourceList!=null) {
            sources=lib.sourceList;
            lib.sortFileArrayAccordingToSuppliedByTemplateSourceList();
        } else {
            lib.sourceFiles.copyInto(sources);
        }
        ArrayList externalDataList = null;
        for (int i = 0; i < sources.length; ++i){
            if (!schemaName.equals(sources[i]) && !sources[i].endsWith(".xml")) {
                externalDataList = (externalDataList == null ? new ArrayList() : externalDataList);
                ExternalData externalData = DataFactory.createExternalData();
                externalData.setSourceName(sources[i]);
                externalData.setType(DataFactory.createDataType("iodata"));
                externalDataList.add(externalData);
            }
        }
        return externalDataList;
    }

    protected ArrayList createTGAttrElems(JmppLibTest lib) throws TestFileException {
        ArrayList attrElems = new ArrayList();
        AttrElem outputDir = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_OUTPUTDIR_NAME, getTestPath(lib));
        AttrElem testType = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_TESTYPE_NAME, TG_ATTR_ELEM_XMLSCHEMA_TESTYPE_VALUE);
        AttrElem scInfo = AttributesFactory.createAttrElem
                (TG_ATTR_ELEM_SCINFO_NAME, lib.sccs);
        attrElems.add(outputDir);
        attrElems.add(testType);
        attrElems.add(scInfo);
        return attrElems;
    }

    public TestCase createSchemaTC(boolean validSchema, JmppLibXMLSchema lib, TestGroup tg) throws TestFileException {
        TestCase tc = TestFactory.createTestCase();
        tc.setID(tg.getID());
        TestCaseAttributes tcAttrs = AttributesFactory.createTestCaseAttributes();
        tcAttrs.setAttrElems(createTCAttrElems(validSchema, lib));
        tc.setTCAttributes(tcAttrs);
        tc.setTestGroup(tg);
        return tc;
    }

    public TestCase createTC(boolean positive, JmppLibXMLSchema lib, TestGroup tg) throws TestFileException {
        TestCase tc = TestFactory.createTestCase();
        TestCaseAttributes tcAttrs = AttributesFactory.createTestCaseAttributes();
        String pos_ID = lib.singleSchema ? lib.test + "_p" : "Positive";
        String neg_ID = lib.singleSchema ? lib.test + "_n" : "Negative";
        tc.setID(positive ? pos_ID : neg_ID);
        tc.setTCDocumentation(createTCDocumentation(positive, lib));
        tc.setCodeSet(createTCCodeSet(positive, lib));
        tcAttrs.setAttrElems(createTCAttrElems(positive, lib));
        tcAttrs.setTargetSpecs(createTCTargetSpecs(lib, positive));
        tc.setTCAttributes(tcAttrs);
        tc.setTestGroup(tg);
        return tc;
    }
    
    public ArrayList createTCAttrElems(boolean validity, JmppLibXMLSchema lib) throws TestFileException {
        ArrayList attrElems = new ArrayList();
        AttrElem valid = AttributesFactory.createAttrElem
                (TC_ATTR_ELEM_VALIDITY_NAME, validity ? VALIDITY_VALID_VALUE : VALIDITY_INVALID_VALUE);
        attrElems.add(valid);
        return attrElems;
    }

    protected TestCaseDocumentation createTCDocumentation(boolean positive, JmppLibXMLSchema lib) throws TestFileException {
        TestCaseDocumentation tcDoc = DocumentationFactory.createTestCaseDocumentation();
        tcDoc.setTitle(lib.title + (positive ? " (valid document)" : " (invalid document)"));
//        tcDoc.setDescription(lib.description);
        tcDoc.setAuthors(createAuthors(lib));
        return tcDoc;
    }

    protected CodeSet createTCCodeSet(boolean positive, JmppLibXMLSchema lib) throws TestFileException {
        CodeSet tcCodeSet = CodeFactory.createCodeSet();
        tcCodeSet.setData(createData(positive, lib));
        tcCodeSet.setDependencies(createDependencies(lib));
        return tcCodeSet;
    }

    protected ArrayList createData(boolean positive, JmppLibXMLSchema lib) throws TestFileException {
    	String sfx = positive ? "_p" : "_n";
	String xml = (lib.biDoc ? sfx : "")  +".xml";
        ArrayList data = new ArrayList();
        Data d = DataFactory.createExternalData(lib.test + xml);
        d.setType(DataFactory.createDataType("iodata"));
        data.add(d);
        return data;
    }

    protected String getTestPath(JmppLibTest lib){
        return lib.getOutputDir() + File.separatorChar + ((JmppLibXMLSchema)lib).sharedName();
    }

    //Code duplicated from IRTDGenerator class to avoid simple bug in case of empty 
    //targetSpec entity, i.e. ", ID:version...".
    protected ArrayList createTargetSpecs(JmppLibXMLSchema lib, String ts) throws TestFileException {
        ArrayList targetSpecs = new ArrayList();
        if (ts == null || ts.trim().length() == 0){
            return targetSpecs;
        }
        // lib.targetSpec should be "ID:version, ID:version,..."
        StringTokenizer st = new StringTokenizer(ts, ",");
        while (st.hasMoreTokens()) {
            // targetSpecEntry should be ID:version
            String targetSpecEntry = st.nextToken().trim();
            // ", ID:version..." IRTDGenerator bug fix:
            if (targetSpecEntry.equals("")){
                continue;
            }
            StringTokenizer stcolumn = new StringTokenizer(targetSpecEntry, ":");
            if (stcolumn.countTokens() != 2){
                throw new TestFileException(LibResHandler.getResStr("jmppconv.error.targetspec", targetSpecEntry));
            }
            targetSpecs.add(AttributesFactory.createTargetSpec(stcolumn.nextToken(), stcolumn.nextToken()));
        }
        return targetSpecs;
    }
}

