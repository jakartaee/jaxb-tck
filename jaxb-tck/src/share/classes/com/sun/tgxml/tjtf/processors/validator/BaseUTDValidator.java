/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tjtf.processors.validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.io.File;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.impl.ConstantsImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.api.common.*;
import com.sun.tgxml.tjtf.api.common.impl.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.tests.impl.TestVariantImpl;

import com.sun.tgxml.tjtf.impl.CommonImpl;
import com.sun.tgxml.util.IR;

 
/** 
 * BaseUTDValidator - The generic UTD validator. 
 * <p>
 * A BaseUTDValidator adds method scaffolding to a NominalUTDVisitor to
 * validate commonly specified entities within the UTD.  This visitor
 * serves as a base for other visitors which enforce TCK specific 
 * validation rules beyond the base.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    BaseUTDValidator 
 * ============================================================================================ 
 */ 
public class BaseUTDValidator extends NominalUTDValidator {



   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 

    /** The list of all maps that this validator contains. */
    protected NameValueMap m_maps;

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   BaseUTDValidator constructor - 
    *       Initialize our internal fields. 
    */ 
    public BaseUTDValidator() {
	super();
	init();
    }


   /** 
    *   init internal fields. 
    */ 
    private void init() {
	m_maps = null;

	// setup the extension maps
	setupMaps();
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    Init Extension-Maps
    * -------------------------------------------------------------------------------------------- 
    */ 

       
    private class SourcePathMapping extends Mapping {

	public SourcePathMapping (String elementName) {
	    super(elementName);
	}


	/**
	 * validate a filename (check its existance).
	 */
	public boolean validate(String varValue) {
	    try {
		File f = new File(varValue);
		if (f.exists() &&  ! f.isDirectory())
		    return true;
	    } catch (Exception e) {
		// don't do anything, just return false.
	    }

	    return false;
	}
    }

    /**
     * Add a Map for AttrElems.
     * <p>
     */
    private void setupAttrElemMap() {
	// create a mapping for AttrElems
	NameValueMap aeMap = new NameValueMap(TagsImpl.ctStr_tag_attrelem);

	// Add a mapping for <AttrElem name="SourcePath">
	aeMap.addEntry(new SourcePathMapping(IR.SourcePathAttrElemName));
	aeMap.addEntry(new SourcePathMapping(IR.relSourcePathAttrElemName));

	// ignore missing requested AttrElems
	aeMap.setValidateIfMissing(true);

	// Add the map to the validator
	m_maps.addEntry(aeMap);
    }


    /**
     * Add a Map for DocElems.
     * <p>
     */
    private void setupDocElemMap() {
	// create a map for AttrElems
	NameValueMap deMap = new NameValueMap(TagsImpl.ctStr_tag_docelem);

	// ignore missing requested DocElems
	deMap.setValidateIfMissing(true);

	// Add the map to the validator
	m_maps.addEntry(deMap);
    }


    /**
     * Add a Map for SpecElems.
     * <p>
     */
    private void setupSpecElemMap() {
	// create a map for AttrElems
	NameValueMap seMap = new NameValueMap(TagsImpl.ctStr_tag_specelem);

	// ignore missing requested SpecElems
	seMap.setValidateIfMissing(true);

	// Add the map to the validator
	m_maps.addEntry(seMap);
    }


    /**
     * Add a Map for RequiredResources.
     * <p>
     */
    private void setupRequiredResourcesMap() {
	// create a map for AttrElems
	NameValueMap rrMap = new NameValueMap(TagsImpl.ctStr_tag_requiredresource);

	// ignore missing requested RequiredResources
	rrMap.setValidateIfMissing(true);

	// Add the map to the validator
	m_maps.addEntry(rrMap);
    }

     
      
    /**
     * Setup the map conatining JavaTest keywords.
     * <p>
     */
    private void setupKeywordsMap() {
	NameMap kwMapping = new NameMap(TagsImpl.ctStr_tag_keyword);

	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_compiler);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_runtime);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_positive);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_negative);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_idl_inherit);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_idl_tie);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_interactive);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_jniinvocationapi);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_only_once);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_rmi_iiop);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_rmi_v11);
	kwMapping.addEntry(ConstantsImpl.ctStr_keyword_serial);
	// complain if we are missing a requested keyword.
	kwMapping.setValidateIfMissing(false);

	// Add the map to the validator
	m_maps.addEntry(kwMapping);
    }

     
    /**
     * Setup the map containing JavaTest keywords.
     * <p>
     */
    private void setupSourceLangMap() {
	NameMap slMapping = new NameMap(TagsImpl.ctStr_attr_sourcelang);

	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_java);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_jcod);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_jasm);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_c);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_xml);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_jca);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_jcasm);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_cfg);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_scr);
	slMapping.addEntry(ConstantsImpl.ctStr_attr_langtype_enum_idl);
	// complain if we are missing a requested sourcelang.
	slMapping.setValidateIfMissing(false);

	// Add the map to the validator
	m_maps.addEntry(slMapping);
    }

     
    /**
     * Setup all of the name mappings for this Validator.
     * <p>
     */
    protected void setupMaps() {
	// The map of all maps is a Name-Value enumeration map.
	m_maps = new NameValueMap("all_Maps");
	// complain if we are missing a requested map.
	m_maps.setValidateIfMissing(false);

	setupAttrElemMap();
	setupDocElemMap();
	setupSpecElemMap();
	setupRequiredResourcesMap();
	setupKeywordsMap();
	setupSourceLangMap();
    }

     
    /**
     * Override the setDebug such that we can pass the debug flag
     * on to the maps.
     */
    public void setDebug(boolean debug) {
	super.setDebug(debug);
	m_maps.setDebug(debug);
    }
	    
   /* 
    * -------------------------------------------------------------------------------------------- 
    *    validation utilities
    * -------------------------------------------------------------------------------------------- 
    */ 
   
    /**
     * validate an ID.
     * <p>
     * @param ID an ID string.
     * @return true if the ID string is valid.
     */
    public boolean valid_ID_String(String ID) {
	// until we get naming conventions, all ID formats are valid.
	return true;
    }

    /**
     * validate an VarID.
     * <p>
     * @param VarID an VarID string.
     * @return true if the VarID string is in format: 'VarName':x[.y],
     *         where: VarName - valid ID; x,y are positive integers 
     *         and the value x.y represents a positive decimal value between
     *         00.00 and 99.99
     *
     */
    public boolean valid_VarID_String(String VarID) {
        try {
            // checking VarOrder format:
            TestVariantImpl tv = new TestVariantImpl(VarID);

            // checking VarName format:
            return valid_ID_String(tv.getVarName());
        } catch (TestFileException e) {
            return false;
        }
    }

   
    /**
     * Validates a name-value pair for a specific map.
     * <p>
     * A name-value mapping is the way to semantically extend a test Definition.
     * Extension elements in the IR (such as SpecElem, DocElem, AttrElem, etc)
     * Use maps to validate names and potential values for those names.
     * <p>
     * @param mapName The name of a given map.
     * @param pair A name-value pairing.
     * @return true if the pair is valid in the given map.
     */
    public boolean valid_ExtensionMapping(String mapName, NameValuePair pair) {
	return valid_ExtensionMapping(mapName, pair.getName(), pair.getValue());
    }
   
    /**
     * Validates the existance of a name in a specific map.
     * <p>
     * A name-value mapping is the way to semantically extend a test Definition.
     * Extension elements in the IR (such as SpecElem, DocElem, AttrElem, etc)
     * Use maps to validate names and potential values for those names.
     * <p>
     * @param mapName The name of a given map.
     * @param varName A possible name entry.
     * @return true if the name is valid in the given map.
     */
    public boolean valid_ExtensionMapping(String mapName, String varName) {
	try {
	    if (m_debug) {
		m_shell.log("****  Mapping: (Map: " + mapName + ") Var: \""  + varName + "\"  ****");
	    }
	} catch (TestFileException e) {
	    // do nothing if there is a logging error.
	}

	Mapping map = (Mapping) m_maps.getMapping(mapName);
	if (map == null) {
	    boolean mapValidated = m_maps.validate(mapName);
	    if (m_debug) {
		try {
		    if (mapValidated)
			m_shell.log("****  Missing Map: (Map: " + mapName 
				    + ") Assuming mapping is valid (" + varName + ") ****");
		    else
			m_shell.log("****  Missing Map: (Map: " + mapName 
				    + ") Assuming mapping is invalid (" + varName + ") ****");
		} catch (TestFileException e) {
		    // do nothing if there is a logging error.
		}
	    }
	    return mapValidated;
	}

	boolean varValidated = map.validate(varName);
	if (m_debug) {
	    try {
		if (varValidated)
		    m_shell.log("****  In Map: (Map: " + mapName 
				+ ") VarName (" + varName + ")  is valid ****");
		else
		    m_shell.log("****  In Map: (Map: " + mapName 
				+ ") VarName (" + varName + ")  is invalid ****");
	    } catch (TestFileException e) {
		// do nothing if there is a logging error.
	    }
	}
	return varValidated;
    }
   
    /**
     * Validates a var name and value in a specific map.
     * <p>
     * A name-value mapping is the way to semantically extend a test Definition.
     * Extension elements in the IR (such as SpecElem, DocElem, AttrElem, etc)
     * Use maps to validate names and potential values for those names.
     * <p>
     * @param mapName The name of a given map.
     * @param varName A possible name entry.
     * @param value A value for that name entry.
     * @return true if the name is valid in the given map.
     */
    public boolean valid_ExtensionMapping(String mapName, String varName, String value) {
	try {
	    if (m_debug) {
		m_shell.log("****  Mapping: (Map: " + mapName + ") Var: \""  + varName + "\"  ****");
	    }
	} catch (TestFileException e) {
	    // do nothing if there is a logging error.
	}

	Mapping map = (Mapping) m_maps.getMapping(mapName);
	if (map == null) {
	    boolean mapValidated = m_maps.validate(mapName);
	    if (m_debug) {
		try {
		    if (mapValidated)
			m_shell.log("****  Missing Map: (Map: " + mapName 
				    + ") Assuming mapping is valid (" + varName + ", " + value +") ****");
		    else
			m_shell.log("****  Missing Map: (Map: " + mapName 
				    + ") Assuming mapping is invalid (" + varName + ", " + value +") ****");
		} catch (TestFileException e) {
		    // do nothing if there is a logging error.
		}
	    }
	    return mapValidated;
	}

	if (map instanceof NameValueMap) {
	    boolean pairValidated = ((NameValueMap)map).validate(varName, value);
	    if (m_debug) {
		try {
		    if (pairValidated)
			m_shell.log("****  In Map: (Map: " + mapName 
				    + ") The pair: VarName (" + varName + ")  ValName (" + value + ")  is valid ****");
		    else
			m_shell.log("****  In Map: (Map: " + mapName 
				    + ") The pair: VarName (" + varName + ")  ValName (" + value + ")  is invalid ****");
		} catch (TestFileException e) {
		    // do nothing if there is a logging error.
		}
	    }
	    return pairValidated;
	} 

	// Got the wrong kind of map.  return invalid - and log the descrepancy.
	if (m_debug) {
	    try {
		m_shell.log("****  In Map: (Map: " + mapName + ") the map is not a NameValueMap  (pair is invalid). ****");
	    } catch (TestFileException e) {
		// do nothing if there is a logging error.
	    }
	}

	return false;
	    
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    BaseUTDValidator utilities
    * -------------------------------------------------------------------------------------------- 
    */ 
      
    /**
     * Trim leading and trailing  whitespace from a word. (needs to be implemented).
     * <p>
     * @param str the string.
     */
    protected String trimword(String str) {
	return str;
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    BaseUTDValidator (overrideable) predicate methods
    * -------------------------------------------------------------------------------------------- 
    */ 
      
    /**
     * validate a TestGroup's ID.
     * <p>
     * @param ID a TestGroup's ID.
     */
    public boolean valid_TestGroup_ID(String ID) {
	// A test group must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(ID);
    }

   
    /**
     * validate a TestCase's ID.
     * <p>
     * @param ID a TestCase's ID.
     */
    public boolean valid_TestCase_ID(String ID) {
	// A test case must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(ID);
    }
   
  
    /**
     * validate a TestCase's VarID.
     * <p>
     * @param VarID a TestCase's VarID.
     */
    public boolean valid_TestCase_VarID(String VarID) {
	// A test case must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_VarID_String(VarID);
    }
   
    /**
     * validate a Library's ID.
     * <p>
     * @param ID a Library's ID.
     */
    public boolean valid_Library_ID(String ID)  {
	// A library must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(ID);
    }
  
    /**
     * validate a Library's VarID.
     * <p>
     * @param VarID a Library's VarID.
     */
    public boolean valid_Library_VarID(String VarID) {
	// Alibrary must have a valid VarID (by some definition).
	// Currently use a generic VarID validation method.
	return valid_VarID_String(VarID);
    }

    /**
     * validate a AssertionRef's ID.
     * <p>
     * @param ID a AssertionRef's ID.
     */
    public boolean valid_AssertionRef_ID(String ID)  {
	// An AssertionRef must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(ID);
    }


    /**
     * validate a LibDependency's ID.
     * <p>
     * @param ID a LibDependency's ID.
     */
    public boolean valid_LibDependency_Name(String ID)  {
	// An LibDependency must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(ID);
    }


    /**
     * validate a SourceName's file.
     * <p>
     * @param sourcefilename a SourceFile's name.
     */
    public boolean valid_SourceName_File(String sourcefilename)  {
	// An SourceLang must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(sourcefilename);
    }

    /**
     * validate an Input string.
     * <p>
     * @param inputname a Input's name.
     */
    public boolean valid_Input_Name(String inputname)  {
	// An Input must have a valid ID (by some definition).
	// Currently use a generic ID validation method.
	return valid_ID_String(inputname);
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    BaseUTDValidator (overrideable) validation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
 
   /* 
    * -------------------------------------------------------------------------------------------- 
    *    Duplicate Check scaffolding methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
   
    /**
     * validates duplicate containment issues in an IR tree.
     * <p>
     * Duplicate checking is not done in the leaf-validate methods
     * because the correctness of the leaf elements should be checked before
     * duplicates are checked.
     * <p>
     * @param obj a TestRoot object.
     * @throws ValidatorException if the TestRoot contains duplicate-named variants.
     */
    public void validate_Variants(TestRoot obj) throws ValidatorException {
	_trace("Variants");
	// only TestGroups "contain" variants.
	if (obj instanceof TestGroup) {
	    TestGroup tg = (TestGroup) obj;
	    
	    // Check for duplicate Libraries
	    ArrayList libs = tg.getLibraries();
	    if (libs != null) {
		TreeMap cklib = new TreeMap();
		Iterator libit = libs.iterator();
		while (libit.hasNext()) {
		    Library lib = (Library) libit.next();
		    String ID = null;;
		    try {
			ID = lib.getID();
		    } catch (TestFileException e) {
			// this shouldn't happen.
		    }
		    String VarID = lib.getVarID();
		    String key;
		    if (VarID == null || VarID.equals(""))
			key = ID;
		    else
			key = ID + ":" + VarID;
		    Library dup = (Library) cklib.get(key);
		    if (dup != null) {
			if (VarID == null)
			    VarID = "";
			raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.variant.duplicate", 
									TagsImpl.ctStr_tag_library, ID, VarID));
		    }
		    
		    cklib.put(key, lib);
		}
	    }
	    
	    
	    // Check for duplicate TestCases
	    ArrayList tcs = tg.getTestCases();
	    if (tcs != null) {
		TreeMap cktcs = new TreeMap();
		Iterator tcit = tcs.iterator();
		while (tcit.hasNext()) {
		    TestCase tc = (TestCase) tcit.next();
		    String ID = null;
		    try {
			ID = tc.getID();
		    } catch (TestFileException e) {
			// this shouldn't happen.
		    }
		    String VarID = tc.getVarID();
		    String key;
		    if (VarID == null || VarID.equals(""))
			key = ID;
		    else
			key = ID + ":" + VarID;
		    TestCase dup = (TestCase) cktcs.get(key);
		    
		    if (dup != null) {
			if (VarID == null)
			    VarID = "";
			raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.variant.duplicate", 
									TagsImpl.ctStr_tag_testcase, ID, VarID));
		    }
		    
		    cktcs.put(key, tc);
		}
	    }
	}
    }


  
    /**
     * validate a TestGroup.
     * <p>
     * @param obj a TestGroup object.
     * @throws ValidatorException if the TestGroup is invalid.
     */
    public void validate_TestGroup(TestGroup obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testgroup);

	try {
	    String ID = obj.getID();
	    if (! valid_TestGroup_ID(ID))
		raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.invalid", TagsImpl.ctStr_tag_testgroup, ID));

	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.null", TagsImpl.ctStr_tag_testgroup));
	}
    }


    /**
     * validate a TestCase.
     * <p>
     * @param obj a TestCase object.
     * @throws ValidatorException if the TestCase is invalid.
     */
    public void validate_TestCase(TestCase obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testcase);

	try {
	    String ID = obj.getID();
	    if (! valid_TestCase_ID(ID))
		raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.invalid", TagsImpl.ctStr_tag_testcase, ID));

	    String VarID = obj.getVarID();
	    if (VarID != null && ! VarID.equals("") && ! valid_TestCase_VarID(VarID))
		raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.varid.invalid", TagsImpl.ctStr_tag_testcase, VarID));

	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.null", TagsImpl.ctStr_tag_testcase));
	}
    }


    /**
     * validate a Library.
     * <p>
     * @param obj a Library object.
     * @throws ValidatorException if the Library is invalid.
     */
    public void validate_Library(Library obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_library);

	try {
	    String ID = obj.getID();
	    if (! valid_Library_ID(ID))
		raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.invalid", TagsImpl.ctStr_tag_library, ID));


	    String VarID = obj.getVarID();
	    if (VarID != null && ! VarID.equals("") && ! valid_Library_VarID(VarID))
		raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.varid.invalid", TagsImpl.ctStr_tag_library, VarID));

	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.id.null", TagsImpl.ctStr_tag_library));
	}
    }


    /**
     * validate a TestGroupDocumentation.
     * <p>
     * @param obj a TestGroupDocumentation object.
     * @throws ValidatorException if the TestGroupDocumentation is invalid.
     */
    public void validate_TestGroupDocumentation(TestGroupDocumentation obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testgroupdocumentation);


    }

    /**
     * validate a LibDocumentation.
     * <p>
     * @param obj a LibDocumentation object.
     * @throws ValidatorException if the LibDocumentation is invalid.
     */
    public void validate_LibDocumentation(LibDocumentation obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_librarydocumentation);


    }

    /**
     * validate a TestCaseDocumentation.
     * <p>
     * @param obj a TestCaseDocumentation object.
     * @throws ValidatorException if the TestCaseDocumentation is invalid.
     */
    public void validate_TestCaseDocumentation(TestCaseDocumentation obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testcasedocumentation);


    }

    /**
     * validate a TestGroupAttributes.
     * <p>
     * @param obj a TestGroupAttributes object.
     * @throws ValidatorException if the TestGroupAttributes is invalid.
     */
    public void validate_TestGroupAttributes(TestGroupAttributes obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testgroupattributes);


    }

    /**
     * validate a LibAttributes.
     * <p>
     * @param obj a LibAttributes object.
     * @throws ValidatorException if the LibAttributes is invalid.
     */
    public void validate_LibAttributes(LibAttributes obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_libraryattributes);


    }

    /**
     * validate a TestCaseAttributes.
     * <p>
     * @param obj a TestCaseAttributes object.
     * @throws ValidatorException if the TestCaseAttributes is invalid.
     */
    public void validate_TestCaseAttributes(TestCaseAttributes obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testcaseattributes);


    }

    /**
     * validate a CodeSet.
     * <p>
     * @param obj a CodeSet object.
     * @throws ValidatorException if the CodeSet is invalid.
     */
    public void validate_CodeSet(CodeSet obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_codeset);


    }

    /**
     * validate a LibraryDependency.
     * <p>
     * @param obj a LibraryDependency object.
     * @throws ValidatorException if the LibraryDependency is invalid.
     */
    public void validate_LibraryDependency(LibraryDependency obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_lib);

	// Assertion LD1.
	String depName = null;
	try {
	    depName = obj.getID();
	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.libdep.ID.null"));
	}
	if (! valid_LibDependency_Name(depName))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.libdep.ID.invalid", depName));
    }

    /**
     * validate a Import.
     * <p>
     * @param importStr a Import object.
     * @throws ValidatorException if the Import is invalid.
     */
    public void validate_Import(String importStr) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_import);

	// Assertion IM1.
	if (! ClassUtils.validImport(importStr))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.import.name.invalid", importStr));

    }

    /**
     * validate a Export.
     * <p>
     * @param exportStr a Export object.
     * @throws ValidatorException if the Export is invalid.
     */
    public void validate_Export(String exportStr) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_export);

	// Assertion IM1.
	if (! ClassUtils.validExport(exportStr))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.export.name.invalid", exportStr));

    }

    /**
     * validate a BaseClass.
     * <p>
     * @param obj a BaseClass object.
     * @throws ValidatorException if the BaseClass is invalid.
     */
    public void validate_BaseClass(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_baseclass);

	// Assertion BC1.
	if (! ClassUtils.validExtClassname(obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.baseclass.name.invalid", obj));

    }


    /**
     * validate a TestCode.
     * <p>
     * @param obj a TestCode object.
     * @throws ValidatorException if the TestCode is invalid.
     */
    public void validate_TestCode(TestCode obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testcode);

	// Assertion TEC1.
	String sourcelang = obj.getSourceLang();
	if (! valid_ExtensionMapping(TagsImpl.ctStr_attr_sourcelang, sourcelang))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcelang.invalid", 
					 sourcelang));
    }


    /**
     * validate a SupportCode.
     * <p>
     * @param obj a SupportCode object.
     * @throws ValidatorException if the SupportCode is invalid.
     */
    public void validate_SupportCode(SupportCode obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_supportcode);

	// Assertion SUC1.
	String sourcelang = obj.getSourceLang();
	if (! valid_ExtensionMapping(TagsImpl.ctStr_attr_sourcelang, sourcelang))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcelang.invalid", sourcelang));
    }

    /**
     * validate a ExternalSupportClass.
     * <p>
     * @param obj a ExternalSupportClass object.
     * @throws ValidatorException if the ExternalSupportClass is invalid.
     */
    public void validate_ExternalSupportClass(ExternalSupportClass obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_externalsupportclass);

	// Assertion ESC1.
	String sourcename = null;
	try {
	    sourcename = obj.getSourceName();
	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcename.null"));
	}
	if (! valid_SourceName_File(sourcename))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcename.invalid",  sourcename));

	// Assertion ESC2.
	String sourcelang = obj.getSourceLang();
	if (! valid_ExtensionMapping(TagsImpl.ctStr_attr_sourcelang, sourcelang))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcelang.invalid",  sourcelang));

    }

    /**
     * validate a InlineSupportClass.
     * <p>
     * @param obj a InlineSupportClass object.
     * @throws ValidatorException if the InlineSupportClass is invalid.
     */
    public void validate_InlineSupportClass(InlineSupportClass obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_inlinesupportclass);

	// Assertion ISC1.
	String sourcelang = obj.getSourceLang();
	if (! valid_ExtensionMapping(TagsImpl.ctStr_attr_sourcelang, sourcelang))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcelang.invalid",  sourcelang));
    }

    /**
     * validate a CodeSource.
     * <p>
     * @param obj a CodeSource object.
     * @throws ValidatorException if the CodeSource is invalid.
     */
    public void validate_CodeSource(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_codesource);


    }


    /**
     * validate a ExternalData.
     * <p>
     * @param obj a ExternalData object.
     * @throws ValidatorException if the ExternalData is invalid.
     */
    public void validate_ExternalData(ExternalData obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_externaldata);

	// Assertion ED1.
	String sourcename = null;
	try {
	    sourcename = obj.getSourceName();
	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcename.null"));
	}
	if (! valid_SourceName_File(sourcename))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.sourcename.invalid",  sourcename));
    }

    /**
     * validate a InlineData.
     * <p>
     * @param obj a InlineData object.
     * @throws ValidatorException if the InlineData is invalid.
     */
    public void validate_InlineData(InlineData obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_inlinedata);


    }

    /**
     * validate a Title.
     * <p>
     * @param obj a Title object.
     * @throws ValidatorException if the Title is invalid.
     */
    public void validate_Title(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_title);


    }

    /**
     * validate a Description.
     * <p>
     * @param obj a Description object.
     * @throws ValidatorException if the Description is invalid.
     */
    public void validate_Description(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_description);

	
    }

    /**
     * validate a AssertionRef.
     * <p>
     * @param obj a AssertionRef object.
     * @throws ValidatorException if the AssertionRef is invalid.
     */
    public void validate_AssertionRef(AssertionRef obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_assertionref);

	String ref = obj.getRef();
	if (! valid_AssertionRef_ID(ref))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.assertionref.id.invalid", 
								 ref));
    }

   

    /**
     * validate a InlineAssertion.
     * <p>
     * @param obj a InlineAssertion object.
     * @throws ValidatorException if the InlineAssertion is invalid.
     */
    public void validate_InlineAssertion(InlineAssertion obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_inlineassertion);

	
    }

    /**
     * validate a TestedPackage.
     * <p>
     * @param obj a TestedPackage object.
     * @throws ValidatorException if the TestedPackage is invalid.
     */
    public void validate_TestedPackage(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testedpackage);

	// The package name should be a (well-formed) java identifier.
	if (! ClassUtils.validExtPackagename(obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.testedpackage.invalidjavaident", obj));	     
    }

    /**
     * validate a TestedClass.
     * <p>
     * @param obj a TestedClass object.
     * @throws ValidatorException if the TestedClass is invalid.
     */
    public void validate_TestedClass(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testedclass);

	// The class name should be a (well-formed) java identifier.
	if (! ClassUtils.validExtClassname(obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.testedclass.invalidjavaident", obj));	     
    }

    /**
     * validate a DocElem.
     * <p>
     * @param obj a DocElem object.
     * @throws ValidatorException if the DocElem is invalid.
     */
    public void validate_DocElem(DocElem obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_docelem);

	if (!valid_ExtensionMapping(TagsImpl.ctStr_tag_docelem, obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.mapping.invalid", 
								 TagsImpl.ctStr_tag_docelem,
								 obj.getName(), obj.getValue()));	     
    }

    /**
     * validate a Author.
     * <p>
     * @param obj a Author object.
     * @throws ValidatorException if the Author is invalid.
     */
    public void validate_Author(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_author);

	
    }

    /**
     * validate a TestCaseSpec.
     * <p>
     * @param obj a TestCaseSpec object.
     * @throws ValidatorException if the TestCaseSpec is invalid.
     */
    public void validate_TestCaseSpec(TestCaseSpec obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testcasespec);

	
    }

    /**
     * validate a TestTechnique.
     * <p>
     * @param obj a TestTechnique object.
     * @throws ValidatorException if the TestTechnique is invalid.
     */
    public void validate_TestTechnique(TestTechnique obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_testtechnique);

	
    }

    /**
     * validate a MemberSig.
     * <p>
     * @param obj a MemberSig object.
     * @throws ValidatorException if the MemberSig is invalid.
     */
    public void validate_MemberSig(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_membersig);

	
    }

    /**
     * validate a Input.
     * <p>
     * @param obj a Input object.
     * @throws ValidatorException if the Input is invalid.
     */
    public void validate_Input(Input obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_input);

	// Assertion IN1.
	if (! valid_Input_Name(obj.getName()))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.input.name.invalid", obj.getName()));
    }

    /**
     * validate a Precondition.
     * <p>
     * @param obj a Precondition object.
     * @throws ValidatorException if the Precondition is invalid.
     */
    public void validate_Precondition(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_precondition);

	
    }

    /**
     * validate a ExpectedResultValue.
     * <p>
     * @param obj a ExpectedResultValue object.
     * @throws ValidatorException if the ExpectedResultValue is invalid.
     */
    public void validate_ExpectedResultValue(ExpectedResultValue obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_expectedresultvalue);

	
    }

    /**
     * validate a ExpectedResultSideEffect.
     * <p>
     * @param obj a ExpectedResultSideEffect object.
     * @throws ValidatorException if the ExpectedResultSideEffect is invalid.
     */
    public void validate_ExpectedResultSideEffect(ExpectedResultSideEffect obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_expectedresultsideeffect);

	
    }

    /**
     * validate a ExpectedResultException.
     * <p>
     * @param obj a ExpectedResultException object.
     * @throws ValidatorException if the ExpectedResultException is invalid.
     */
    public void validate_ExpectedResultException(ExpectedResultException obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_expectedresultexception);

	
    }

    /**
     * validate a SpecElem.
     * <p>
     * @param obj a SpecElem object.
     * @throws ValidatorException if the SpecElem is invalid.
     */
    public void validate_SpecElem(SpecElem obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_specelem);

	if (!valid_ExtensionMapping(TagsImpl.ctStr_tag_specelem, obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.mapping.invalid", 
								 TagsImpl.ctStr_tag_specelem,
								 obj.getName(), obj.getValue()));	     
    }

    /**
     * validate a RequiredResource.
     * <p>
     * @param obj a RequiredResource object.
     * @throws ValidatorException if the RequiredResource is invalid.
     */
    public void validate_RequiredResource(RequiredResource obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_requiredresource);

	if (!valid_ExtensionMapping(TagsImpl.ctStr_tag_requiredresource, obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.mapping.invalid", 
								 TagsImpl.ctStr_tag_requiredresource,
								 obj.getName(), obj.getValue()));	     
    }

    /**
     * validate a AttrElem.
     * <p>
     * @param obj a AttrElem object.
     * @throws ValidatorException if the AttrElem is invalid.
     */
    public void validate_AttrElem(AttrElem obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_attrelem);

	if (!valid_ExtensionMapping(TagsImpl.ctStr_tag_attrelem, obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.mapping.invalid", 
								 TagsImpl.ctStr_tag_attrelem,
								 obj.getName(), obj.getValue()));	     

    }

    /**
     * validate a TargetSpec.
     * <p>
     * @param obj a TargetSpec object.
     * @throws ValidatorException if the TargetSpec is invalid.
     */
    public void validate_TargetSpec(TargetSpec obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_targetspec);

	
    }

    /**
     * validate a TargetSpec.
     * <p>
     * @param obj a TargetSpec object.
     * @throws ValidatorException if the TargetSpec is invalid.
     */
    public void validate_TargetSpecElem(TargetSpecElem obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_targetspecelem);

	if (!valid_ExtensionMapping(TagsImpl.ctStr_tag_targetspecelem, obj))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.mapping.invalid", 
								 TagsImpl.ctStr_tag_targetspecelem,
								 obj.getName(), obj.getValue()));	     

	
    }

    /**
     * validate a Keyword.
     * <p>
     * @param obj a Keyword object.
     * @throws ValidatorException if the Keyword is invalid.
     */
    public void validate_Keyword(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_keyword);
	
	// Assertion KW1.
	
	// Make sure keyword is a single token
	String kwd = null;
	try {
	    kwd = CommonImpl.getSingleToken(obj);
	} catch (TestFileException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.keyword.invalid", obj));
	}

	// make sure keyword has a value
	if (kwd == null || kwd.equals(""))
    	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.keyword.invalid", obj));

	// normalize the keyword to lower case, and check the maps
	kwd = kwd.toLowerCase();

	if (! valid_ExtensionMapping(TagsImpl.ctStr_tag_keyword, kwd))
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.keyword.invalid", obj));
    }

    /**
     * validate a Context.
     * <p>
     * @param obj a Context object.
     * @throws ValidatorException if the Context is invalid.
     */
    public void validate_Context(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_context);

	
    }

    /**
     * validate a ExecuteClass.
     * <p>
     * @param obj a ExecuteClass object.
     * @throws ValidatorException if the ExecuteClass is invalid.
     */
    public void validate_ExecuteClass(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_executeclass);

	
    }

    /**
     * validate a ExecuteArgs.
     * <p>
     * @param obj a ExecuteArgs object.
     * @throws ValidatorException if the ExecuteArgs is invalid.
     */
    public void validate_ExecuteArgs(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_executeargs);

	
    }

    /**
     * validate a ExecuteNative.
     * <p>
     * @param obj a ExecuteNative object.
     * @throws ValidatorException if the ExecuteNative is invalid.
     */
    public void validate_ExecuteNative(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_executenative);

	
    }

    /**
     * validate a Remote.
     * <p>
     * @param obj a Remote object.
     * @throws ValidatorException if the Remote is invalid.
     */
    public void validate_Remote(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_remote);

	
    }

    /**
     * validate a RMICClasses.
     * <p>
     * @param obj a RMICClasses object.
     * @throws ValidatorException if the RMICClasses is invalid.
     */
    public void validate_RMICClasses(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_rmicclasses);

	
    }

    /**
     * validate a SelectIf.
     * <p>
     * @param obj a SelectIf object.
     * @throws ValidatorException if the SelectIf is invalid.
     */
    public void validate_SelectIf(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_selectif);

	
    }

    /**
     * validate a Timeout.
     * <p>
     * @param obj a Timeout object.
     * @throws ValidatorException if the Timeout is invalid.
     */
    public void validate_Timeout(String obj) throws ValidatorException {
	_trace(TagsImpl.ctStr_tag_timeout);

	// Assertion TO1.
	String timeout = trimword(obj);
	try {
	    Integer.parseInt(timeout);
	} catch (NumberFormatException e) {
	    raiseValidatorException(LibResHandler.getResStr("baseutdvalidator.error.timeout.invalid", obj));
	}
    }


}
