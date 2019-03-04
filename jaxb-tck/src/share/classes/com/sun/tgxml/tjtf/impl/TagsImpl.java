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

package com.sun.tgxml.tjtf.impl;

// <importgen> Generated imports for class: com.sun.specinfo.emitter.XMLPlatformEmitter
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * TagsImpl - 
 *
 * <b>TagsImpl</b> contains the the string names for all XML entity tags, attributes, and
 * enumerated values.  These are used for both parsing and emitting.
 * <p>
 *
 *
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TagsImpl
 * ============================================================================================
 */


public  class TagsImpl  {
   /*
    * ============================================================================================
    *    Member Fields
    * ============================================================================================
    */
    



    //---------------------------------------------------------------------------------
    // Entity (tag) identifiers
    //---------------------------------------------------------------------------------
    public static final String  ctStr_tag_testgroup                = "TestGroup";
    public static final String  ctStr_tag_testcase                 = "TestCase";
    public static final String  ctStr_tag_library                  = "Library";


    public static final String  ctStr_tag_testgroupdocumentation   = "TestGroupDocumentation";
    public static final String  ctStr_tag_testcasedocumentation    = "TestCaseDocumentation";
    public static final String  ctStr_tag_librarydocumentation     = "LibraryDocumentation";

    public static final String  ctStr_tag_testgroupattributes      = "TestGroupAttributes";
    public static final String  ctStr_tag_testcaseattributes       = "TestCaseAttributes";
    public static final String  ctStr_tag_libraryattributes        = "LibraryAttributes";

    public static final String  ctStr_tag_codeset                  = "CodeSet";
    public static final String  ctStr_tag_dependency               = "Dependency";
    public static final String  ctStr_tag_lib                      = "Lib";
    public static final String  ctStr_tag_import                   = "Import";
    public static final String  ctStr_tag_baseclass                = "BaseClass";
    public static final String  ctStr_tag_export                   = "Export";
    public static final String  ctStr_tag_testcode                 = "TestCode";
    public static final String  ctStr_tag_supportcode              = "SupportCode";
    public static final String  ctStr_tag_externalsupportclass     = "ExternalSupportClass";
    public static final String  ctStr_tag_inlinesupportclass       = "InlineSupportClass";
    public static final String  ctStr_tag_externaldata             = "ExternalData";
    public static final String  ctStr_tag_inlinedata               = "InlineData";
    public static final String  ctStr_tag_codesource               = "CodeSource";


    public static final String  ctStr_tag_title                    = "Title";
    public static final String  ctStr_tag_description              = "Description";
    public static final String  ctStr_tag_assertionref             = "AssertionRef";
    public static final String  ctStr_tag_inlineassertion          = "InlineAssertion";
    public static final String  ctStr_tag_testedpackage            = "TestedPackage";
    public static final String  ctStr_tag_testedclass              = "TestedClass";
    public static final String  ctStr_tag_docelem                  = "DocElem";
    public static final String  ctStr_tag_author                   = "Author";
    public static final String  ctStr_tag_testcasespec             = "TestCaseSpec";
    public static final String  ctStr_tag_testtechnique            = "TestTechnique";
    public static final String  ctStr_tag_membersig                = "MemberSig";
    public static final String  ctStr_tag_input                    = "Input";
    public static final String  ctStr_tag_precondition             = "Precondition";
    public static final String  ctStr_tag_expectedresultvalue      = "ExpectedResultValue";
    public static final String  ctStr_tag_expectedresultsideeffect = "ExpectedResultSideEffect";
    public static final String  ctStr_tag_expectedresultexception  = "ExpectedResultException";
    public static final String  ctStr_tag_specelem                 = "SpecElem";


    public static final String  ctStr_tag_requiredresource         = "RequiredResource";
    public static final String  ctStr_tag_attrelem                 = "AttrElem";
    public static final String  ctStr_tag_targetspec               = "TargetSpec";
    public static final String  ctStr_tag_targetspecelem           = "TargetSpecElem";
    public static final String  ctStr_tag_keyword                  = "Keyword";
    public static final String  ctStr_tag_context                  = "Context";
    public static final String  ctStr_tag_executeclass             = "ExecuteClass";
    public static final String  ctStr_tag_executeargs              = "ExecuteArgs";
    public static final String  ctStr_tag_executenative            = "ExecuteNative";
    public static final String  ctStr_tag_remote                   = "Remote";
    public static final String  ctStr_tag_rmicclasses              = "RMICClasses";
    public static final String  ctStr_tag_selectif                 = "SelectIf";
    public static final String  ctStr_tag_timeout                  = "Timeout";




    //---------------------------------------------------------------------------------
    //  Enumerated attribute values
    //---------------------------------------------------------------------------------
    public static final String  ctStr_attr_id                         = "ID";
    public static final String  ctStr_attr_varid                      = "VarID";
    public static final String  ctStr_attr_classid                    = "ClassID";
    public static final String  ctStr_attr_sourcelang                 = "SourceLang";
    public static final String  ctStr_attr_name                       = "Name";
    public static final String  ctStr_attr_sourcename                 = "SourceName";
    public static final String  ctStr_attr_targetname                 = "TargetName";
    public static final String  ctStr_attr_value                      = "Value";
    public static final String  ctStr_attr_version                    = "Version";
    public static final String  ctStr_attr_type                       = "Type";
    public static final String  ctStr_attr_deleted                    = "Deleted";
    public static final String  ctStr_attr_inline                     = "Inline";

    public static final String  ctStr_attr_testtech_enum_eqclass      = "EqClass";
    public static final String  ctStr_attr_testtech_enum_boundary     = "Boundary";
 
    public static final String  ctStr_attr_extdata_enum_resource      = "resource";
    public static final String  ctStr_attr_extdata_enum_iodata        = "iodata";

    public static final String  ctStr_attr_enum_true                  = "true";
    public static final String  ctStr_attr_enum_false                 = "false";

    // An AttrElem that the parser can add
    public static final String  ctStr_attr_sourcepath                 = "SourcePath";


   /*
    * ============================================================================================
    *    Methods
    * ============================================================================================
    */
 
  /**
    *   Predicate - determines if the enumerated attr value is a true or false flag.
    *  <p>
    * @param      keywords The int representing an keywords-flag.
    * @exception  TestFileException if the string is NULL or not "true" or "false".
    * @return     true or false according to string value.
    */
    static public boolean isTrueOrFalse(String trueOrFalseString) throws TestFileException {
	if (trueOrFalseString == null || trueOrFalseString.equals(""))
	    throw new TestFileException("Null boolean attribute string-value.");
	if (trueOrFalseString.equals(ctStr_attr_enum_true))
	    return true;
	if (trueOrFalseString.equals(ctStr_attr_enum_false))
	    return false;

	throw new TestFileException("Non-boolean attribute string-value: " + trueOrFalseString + ".");

    }


}
