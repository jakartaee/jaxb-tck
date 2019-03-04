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
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.api.common.impl.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.tests.*;

 
/** 
 * JCKValidator - The full validator for a JCK test format. 
 * <p>
 * A JCKValidator adds implementation to a BaseUTDValidator to validate the 
 * following JCK assertions:
 * <p>
 * <b> TestGroup</b><br>
 * <ul>
 * <li> TG1.) TestGroupDocumentation must be present.</li>
 * <li> TG4.) At least one TestCase must be present.</li>
 * <li> TG5.) ID must be validated (undefined format).</li>
 * </ul>
 * <p>
 * The following assertions have been removed for TestGroup:
 * <ul>
 * <li> TG2.) TestGroupAttributes must be present.</li> 
 * <li> TG3.) CodeSet must be present.</li>
 * </ul>
 * <p>
 * <b> Library</b><br>
 * <ul>
 * <li> Lib1.) LibDocumentation must be present.</li>
 * <li> Lib2.) LibAttributes must be present.</li>
 * <li> Lib3.) CodeSet must be present.</li>
 * <li> Lib4.) ID must be validated (undefined format).</li>
 * </ul>
 * <p>
 * <b> TestCase</b><br>
 * <ul>
 * <li> TC1.) TestCaseDocumentation must be present.</li>
 * <li> TC2.) TestCaseAttributes must be present.</li>
 * <li> TC3.) CodeSet must be present.</li>
 * <li> TC4.) ID must be validated (undefined format).</li>
 * </ul>
 * <p>
 * The following assertions have been removed for TestGroup:
 * <ul>
 * <li> TC2.) TestCaseAttributes must be present.</li>
 * <li> TC3.) CodeSet must be present.</li>
 * </ul>
 * <p>
 * <b> TestGroupDocumentation</b><br>
 * <ul>
 * <li> TGD1.) TestedPackage must be present.</li>
 * <li> TGD2.) TestedClass must be present.</li>
 * <li> TGD3.) Assertions must be all (only) AssertionRefs.</li>
 * </ul>
 * <p>
 * <b> LibraryDocumentation</b><br>
 * (nothing)<br>
 * <p>
 * <b> TestCaseDocumentation</b><br>
 * <ul>
 * <li> TCD1.) At least one TestCaseSpec must be present.</li>
 * </ul>
 * <p>
 * <b> TestCaseSpec</b><br>
 * (nothing)<br>
 * <p>
 * <b> Title</b><br>
 * (nothing)<br>
 * <p>
 * <b> AssertionRef</b><br>
 * <ul>
 * <li> AR1.) ID must be validated (undefined format).</li>
 * </ul>
 * <p>
 * <b> InlineAssertion</b><br>
 * (nothing)<br>
 * <p>
 * <b> Description</b><br>
 * (nothing)<br>
 * <p>
 * <b> TestedPackage</b><br>
 * <ul>
 * <li> TP1.) Name must be valid Java Identifier (default - handled in BaseUTDValidator).</li>
 * </ul>
 * <p>
 * <b> TestedClass</b><br>
 * <ul>
 * <li> TCl1.) Name must be valid Java Identifier (default - handled in BaseUTDValidator).</li>
 * </ul>
 * <p>
 * <b> DocElem</b><br>
 * <ul>
 * <li> DE1.) SourceFile - Must contain valid file name (exists on FS).</li>
 * </ul>
 * <p>
 * <b> Author</b><br>
 * (nothing)<br>
 * <p>
 * <b> TestTechnique</b><br>
 * (nothing)<br>
 * <p>
 * <b> MemberSig</b><br>
 * (nothing)<br>
 * <p>
 * <b> Input</b><br>
 * <ul>
 * <li> I1.) Input name must be valid (undefined).</li>
 * </ul>
 * <p>
 * <b> Precondition</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExpectedResultValue</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExpectedResultSideEffect</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExpectedResultException</b><br>
 * (nothing)<br>
 * <p>
 * <b> SpecElem</b><br>
 * (nothing)<br>
 * <p>
 * <b> TestGroupAttributes</b><br>
 * (nothing)<br>
 * <p>
 * <b> LibraryAttributes</b><br>
 * (nothing)<br>
 * <p>
 * <b> TestCaseAttributes</b><br>
 * (nothing)<br>
 * <p>
 * <b> RequiredResource</b><br>
 * (nothing)<br>
 * <p>
 * <b> AttrElem</b><br>
 * (nothing)<br>
 * <p>
 * <b> TargetSpec</b><br>
 * <ul>
 * <li> TS1.) ID must be valid (undefined).</li>
 * <li> TS2.) Version string must follow the prescribed grammar (default - impl in BaseUTDValidator).</li>
 * </ul>
 * <p>
 * <b> Keyword</b><br>
 * <ul>
 * <li> KW1.) Keyword must be in the map (default map contains JavaTest keywords) (robust - handle extra whitespace, capitalization).</li>
 * </ul>
 * <p>
 * <b> Context</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExecuteClass</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExecuteArgs</b><br>
 * (nothing)<br>
 * <p>
 * <b> ExecuteNative</b><br>
 * (nothing)<br>
 * <p>
 * <b> Remote</b><br>
 * (nothing)<br>
 * <p>
 * <b> RMICClasses</b><br>
 * (nothing)<br>
 * <p>
 * <b> SelectIf</b><br>
 * (nothing)<br>
 * <p>
 * <b> Timeout</b><br>
 * <ul>
 * <li> TO1.) Value must be a valid integer.</li>
 * </ul>
 * <p>
 * <b> CodeSet</b><br>
 * (nothing)<br>
 * <p>
 * <b> Import</b><br>
 * <ul>
 * <li> IM1.) Value must be a valid import name (undefined).</li>
 * </ul>
 * <p>
 * <b> BaseClass</b><br>
 * <ul>
 * <li> BC1.)  Name must be valid Java Identifier (default - handled in BaseUTDValidator).</li>
 * </ul>
 * <p>
 * <b> Export</b><br>
 * <ul>
 * <li> IM1.) Value must be a valid import name (undefined).</li>
 * </ul>
 * <p>
 * <b> Dependency</b><br>
 * (nothing)<br>
 * <p>
 * <b> Lib</b><br>
 * <ul>
 * <li> LD1.)  Name must be valid (undefined).</li>
 * </ul>
 * <p>
 * <b> ExternalData</b><br>
 * <ul>
 * <li> ED1.)  SourceName must point to an existing file.</li>
 * </ul>
 * <p>
 * <b> InlineData</b><br>
 * <ul>
 * <li> ID1.)  If TargetName is present, Type must be present (and vice-versa)</li>
 * </ul>
 * <p>
 * <b> ExternalSupportClass</b><br>
 * <ul>
 * <li> ESC1.)  SourceName must point to an existing file.</li>
 * <li> ESC2.)  SourceLang is one of the pre-defined enumerated types.</li>
 * </ul>
 * <p>
 * <b> InlineSupportClass</b><br>
 * <ul>
 * <li> ISC1.)  SourceLang is one of the pre-defined enumerated types.</li>
 * </ul>
 * <p>
 * <b> CodeSource</b><br>
 * (nothing)<br>
 * <p>
 * <b> SupportCode</b><br>
 * <ul>
 * <li> SUC1.)  SourceLang is one of the pre-defined enumerated types.</li>
 * </ul>
 * <p>
 * <b> TestCode</b><br>
 * <ul>
 * <li> TEC1.)  SourceLang is one of the pre-defined enumerated types.</li>
 * </ul>
 * <p>
 * The JCKValidator (or one of it's parent classes) must handle these assertions.
 * <p>
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    JCKValidator 
 * ============================================================================================ 
 */ 
public class JCKValidator extends BaseUTDValidator {
   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 
    /** The name of the TCK. */
    protected String m_TCKName;


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   BaseUTDValidator constructor - 
    *       Initialize our internal fields. 
    */ 
    public JCKValidator() {
	super();
	init();
    }


   /** 
    *   init internal fields. 
    */ 
    private void init() {
	m_TCKName = "JCK";
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    validation utilities
    * -------------------------------------------------------------------------------------------- 
    */ 
   

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    BaseUTDValidator implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
   
    /**
     * validate a TestGroup.
     * <p>
     * @param obj a TestGroup object.
     * @throws ValidatorException if the TestGroup is invalid.
     */
    public void validate_TestGroup(TestGroup obj) throws ValidatorException {
	// the super validates ID's (TG5)
	super.validate_TestGroup(obj);

	// Assertion TG1.
	TestGroupDocumentation tgd = obj.getTGDocumentation();
	if (tgd == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroup, TagsImpl.ctStr_tag_testgroupdocumentation));
	/*  
	 * These assertions have been removed (1/14/02)
	 *
	 */

	/*
	// Assertion TG2.
	TestGroupAttributes tga = obj.getTGAttributes();
	if (tga == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroup, TagsImpl.ctStr_tag_testgroupattributes));
	    
	    
	// Assertion TG3.
	CodeSet cs = obj.getCodeSet();
	if (cs == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroup, TagsImpl.ctStr_tag_codeset));
        */

	    
	// Assertion TG4.
	ArrayList cases = obj.getTestCases();
	if (cases == null || (cases.size() < 1))
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.needs1comp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroup, TagsImpl.ctStr_tag_testcase));

	    
    }


    /**
     * validate a TestCase.
     * <p>
     * @param obj a TestCase object.
     * @throws ValidatorException if the TestCase is invalid.
     */
    public void validate_TestCase(TestCase obj) throws ValidatorException {
	// the super validates ID's (TC4)
	super.validate_TestCase(obj);

	// Assertion TC1.
	TestCaseDocumentation tcd = obj.getTCDocumentation();
	if (tcd == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testcase, TagsImpl.ctStr_tag_testcasedocumentation));
	    
	/*  
	 * These assertions have been removed (1/14/02)
	 *
	 */

	/*
	// Assertion TC2.
	TestCaseAttributes tca = obj.getTCAttributes();
	if (tca == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testcase, TagsImpl.ctStr_tag_testcaseattributes));
	    
	    
	// Assertion TC3.
	CodeSet cs = obj.getCodeSet();
	if (cs == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testcase, TagsImpl.ctStr_tag_codeset));
         */
    }


    /**
     * validate a Library.
     * <p>
     * @param obj a Library object.
     * @throws ValidatorException if the Library is invalid.
     */
    public void validate_Library(Library obj) throws ValidatorException {
	// the super validates ID's (Lib4)
	super.validate_Library(obj);

	// Assertion Lib1.
	LibDocumentation ld = obj.getLibDocumentation();
	if (ld == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_library, TagsImpl.ctStr_tag_librarydocumentation));
	    
	// Assertion Lib2.
	LibAttributes la = obj.getLibAttributes();
	if (la == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_library, TagsImpl.ctStr_tag_libraryattributes));
	    
	    
	// Assertion Lib3.
	CodeSet cs = obj.getCodeSet();
	if (cs == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_library, TagsImpl.ctStr_tag_codeset));
    }



    /**
     * validate a TestGroupDocumentation.
     * <p>
     * @param obj a TestGroupDocumentation object.
     * @throws ValidatorException if the TestGroupDocumentation is invalid.
     */
    public void validate_TestGroupDocumentation(TestGroupDocumentation obj) throws ValidatorException {
	// Assertion TGD1.
	String testedpck = obj.getTestedPackage();
	if (testedpck == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroupdocumentation, TagsImpl.ctStr_tag_testedpackage));
	    
	// Assertion TGD2.
	String testedcls = obj.getTestedClass();
	if (testedcls == null)
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.missingcomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroupdocumentation, TagsImpl.ctStr_tag_testedclass));
	    
	    
	// Assertion TGD3.
	ArrayList assertions = obj.getAssertions();
	if (assertions != null) {
	    Iterator it = assertions.iterator();
	    while (it.hasNext()) {
		Assertion current = (Assertion) it.next();
		if (! (current instanceof AssertionRef)) 
		    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.needsonlycomp", 
					 m_TCKName, TagsImpl.ctStr_tag_testgroupdocumentation, TagsImpl.ctStr_tag_assertionref));
	    }
	}

    }


    /**
     * validate a TestCaseDocumentation.
     * <p>
     * @param obj a TestCaseDocumentation object.
     * @throws ValidatorException if the TestCaseDocumentation is invalid.
     */
    public void validate_TestCaseDocumentation(TestCaseDocumentation obj) throws ValidatorException {
	// Assertion TCD1.
	ArrayList specs = obj.getTestCaseSpecs();
	if (specs == null || (specs.size() < 1))
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.needs1comp", 
					 m_TCKName, TagsImpl.ctStr_tag_testcasedocumentation, TagsImpl.ctStr_tag_testcasespec));
	    
    }


    /**
     * validate a InlineData.
     * <p>
     * @param obj a InlineData object.
     * @throws ValidatorException if the InlineData is invalid.
     */
    public void validate_InlineData(InlineData obj) throws ValidatorException {
	// Assertion ID1.
	String targetname = obj.getTargetName();
	DataType type = obj.getType();
	if ( (targetname != null && type == null) ||
	     (targetname == null && type != null))
	    raiseValidatorException(LibResHandler.getResStr("jckvalidator.error.inlinedata.targtype"));
    }
}
