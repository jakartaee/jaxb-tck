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
import java.util.Stack;

import com.sun.tgxml.tjtf.tools.*;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.tests.*;

 
/** 
 * NominalUTDValidator - The vanilla visitor for a UTD validator. 
 * <p>
 * A NominalUTDValidator adds method scaffolding for sub-classes to validate
 * the various components of a UTD. A NominalUTDValidator internally implements a UTDVisitorBase
 * to walk the IR tree for a UTD IR.  Sub-classes override the method scaffolding to add
 * validation content for any given IR component.
 * <p>
 * @version 	1.1, 10/28/02 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    NominalUTDValidator 
 * ============================================================================================ 
 */ 
public class NominalUTDValidator extends NullValidator {


   /* 
    * ============================================================================================ 
    *    InnerClass 
    * ============================================================================================ 
    */ 


     /*
     * --------------------------------------------------------------------------------------------
     *    ValVisitor
     * --------------------------------------------------------------------------------------------
     */
    
    private class ValVisitor extends UTDVisitorBase {
	/*
	 * --------------------------------------------------------------------------------------------
	 *    constructors
	 * --------------------------------------------------------------------------------------------
	 */
	
	public ValVisitor () {
	    super();
	}      
	
	
	//------------------------------------------------------------------------------
	//  handler functions
	//------------------------------------------------------------------------------
	
	protected void visit_TestGroup(TestGroup obj) throws TestFileException {
	    validate_TestGroup(obj);
	    super.visit_TestGroup(obj);
	}

	protected void visit_TestCase(TestCase obj) throws TestFileException {
	    validate_TestCase(obj);
	    super.visit_TestCase(obj);
	}

	protected void visit_Library(Library obj) throws TestFileException {
	    validate_Library(obj);
	    super.visit_Library(obj);
	}

	protected void visit_TestGroupDocumentation(TestGroupDocumentation obj) throws TestFileException {
	    validate_TestGroupDocumentation(obj);
	    super.visit_TestGroupDocumentation(obj);
	}
    
	protected void visit_LibDocumentation(LibDocumentation ld) throws TestFileException {
	    validate_LibDocumentation(ld);
	    super.visit_LibDocumentation(ld);
	}
    
	protected void visit_TestCaseDocumentation(TestCaseDocumentation obj) throws TestFileException {
	    validate_TestCaseDocumentation(obj);
	    super.visit_TestCaseDocumentation(obj);
	}

	protected void visit_TestGroupAttributes(TestGroupAttributes obj) throws TestFileException {
	    validate_TestGroupAttributes(obj);
	    super.visit_TestGroupAttributes(obj);
	}

	protected void visit_LibAttributes(LibAttributes la) throws TestFileException {
	    validate_LibAttributes(la);
	    super.visit_LibAttributes(la);
	}

	protected void visit_TestCaseAttributes(TestCaseAttributes obj) throws TestFileException {
	    validate_TestCaseAttributes(obj);
	    super.visit_TestCaseAttributes(obj);
	}

	protected void visit_CodeSet(CodeSet obj) throws TestFileException {
	    validate_CodeSet(obj);
	    super.visit_CodeSet(obj);
	}



	protected void visit_LibraryDependency(LibraryDependency obj) throws TestFileException {
	    validate_LibraryDependency(obj);
	}


	protected void visit_Import(String importStr) throws TestFileException {
	    validate_Import(importStr);
	}


	protected void visit_BaseClass(String baseClass) throws TestFileException {
	    validate_BaseClass(baseClass);
	}

	protected void visit_Export(String exportStr) throws TestFileException {
	    validate_Export(exportStr);
	}


	protected void visit_TestCode(TestCode tc) throws TestFileException {
	    validate_TestCode(tc);
	}


	protected void visit_SupportCode(SupportCode sc) throws TestFileException {
	    validate_SupportCode(sc);
	}


        protected void visit_ExternalSupportClass(ExternalSupportClass esc) throws TestFileException {
	    validate_ExternalSupportClass(esc);
	    super.visit_ExternalSupportClass(esc);
	}

        protected void visit_InlineSupportClass(InlineSupportClass isc) throws TestFileException {
	    validate_InlineSupportClass(isc);
	    super.visit_InlineSupportClass(isc);
	}


	protected void visit_CodeSource(String codeSource) throws TestFileException {
	    validate_CodeSource(codeSource);
	}


	protected void visit_ExternalData(ExternalData ed) throws TestFileException {
	    validate_ExternalData(ed);
	}


	protected void visit_InlineData(InlineData id) throws TestFileException {
	    validate_InlineData(id);
	}


	protected void visit_Title(String title) throws TestFileException {
	    validate_Title(title);
	}


	protected void visit_Description(String description) throws TestFileException {
	    validate_Description(description);
	}


	protected void visit_AssertionRef(AssertionRef ar) throws TestFileException {
	    validate_AssertionRef(ar);
	}


	protected void visit_InlineAssertion(InlineAssertion ia) throws TestFileException {
	    validate_InlineAssertion(ia);
	}


	protected void visit_TestedPackage(String tp) throws TestFileException {
	    validate_TestedPackage(tp);
	}


	protected void visit_TestedClass(String tc) throws TestFileException {
	    validate_TestedClass(tc);
	}

	protected void visit_DocElem(DocElem de) throws TestFileException {
	    validate_DocElem(de);
	}


	protected void visit_Author(String author) throws TestFileException {
	    validate_Author(author);
	}


        protected void visit_TestCaseSpec(TestCaseSpec tcs) throws TestFileException {
	    validate_TestCaseSpec(tcs);
	    super.visit_TestCaseSpec(tcs);
	}



	protected void visit_TestTechnique(TestTechnique tt) throws TestFileException {
	    validate_TestTechnique(tt);
	}


	protected void visit_MemberSig(String sig) throws TestFileException {
	    validate_MemberSig(sig);
	}


	protected void visit_Input(Input input) throws TestFileException {
	    validate_Input(input);
	}


	protected void visit_Precondition(String precondition) throws TestFileException {
	    validate_Precondition(precondition);
	}


	protected void visit_ExpectedResultValue(ExpectedResultValue erv) throws TestFileException {
	    validate_ExpectedResultValue(erv);
	}


	protected void visit_ExpectedResultSideEffect(ExpectedResultSideEffect erse) throws TestFileException {
	    validate_ExpectedResultSideEffect(erse);
	}


	protected void visit_ExpectedResultException(ExpectedResultException ere) throws TestFileException {
	    validate_ExpectedResultException(ere);
	}


	protected void visit_SpecElem(SpecElem se) throws TestFileException {
	    validate_SpecElem(se);
	}


	protected void visit_RequiredResource(RequiredResource rr) throws TestFileException {
	    validate_RequiredResource(rr);
	}


	protected void visit_AttrElem(AttrElem ae) throws TestFileException {
	    validate_AttrElem(ae);
	}


	protected void visit_TargetSpec(TargetSpec ts) throws TestFileException {
	    validate_TargetSpec(ts);
	    super.visit_TargetSpec(ts);
	}


	protected void visit_TargetSpecElem(TargetSpecElem tse) throws TestFileException {
	    validate_TargetSpecElem(tse);
	}


	protected void visit_Keyword(String keyword) throws TestFileException {
	    validate_Keyword(keyword);
	}


	protected void visit_Context(String context) throws TestFileException {
	    validate_Context(context);
	}


	protected void visit_ExecuteClass(String execargs) throws TestFileException {
	    validate_ExecuteClass(execargs);
	}

	protected void visit_ExecuteArgs(String execargs) throws TestFileException {
	    validate_ExecuteArgs(execargs);
	}


	protected void visit_ExecuteNative(String execNative) throws TestFileException {
	    validate_ExecuteNative(execNative);
	}

	protected void visit_Remote(String remote) throws TestFileException {
	    validate_Remote(remote);
	}


	protected void visit_RMICClasses(String rmicclasses) throws TestFileException {
	    validate_RMICClasses(rmicclasses);
	}


	protected void visit_SelectIf(String selectIf) throws TestFileException {
	    validate_SelectIf(selectIf);
	}

	protected void visit_Timeout(String timeout) throws TestFileException {
	    validate_Timeout(timeout);
	}

    }



   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 
    protected ValVisitor m_visitor;


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   NominalUTDValidator constructor - 
    *       Initialize our internal fields. 
    */ 
    public NominalUTDValidator() {
	super();
	init();
    }


   /** 
    *   init internal fields. 
    */ 
    private void init() {
	m_visitor = new ValVisitor();
    }


   /** 
    *  Get the context stack.  The Context stack
    *  is a stack of UTD IR objects that represent 
    *  the owners of any given IR Object at a given point
    *  of the tree traversal.
    * <p>
    * The internal visitor maintains its own stack, so this method
    * just provides access to that stack.
    * <p>
    * @return java.util.Stack containing the context of UTD IR objects.
    */ 
    protected Stack getContext() {
	return m_visitor.getContextStack();
    }

  
  
   /** 
    *  Trace the given tag (prints diagnostics).
    * <p>
    */ 
    protected void _trace(String tag) {
	if (m_debug) {
	    try {
		m_shell.log("******  Validating: " + tag + " ********");
	    } catch (TestFileException e) {
		// Don't do anything if logging fails.
	    }
	}
    }


   /** 
    *  Get a string describing the (owning) context
    * of the currently visited tag.
    * <p>
    * @return The context string.
    */ 
    protected String getContextString() {
	String contextStr = "";
	Stack contextStack = getContext();
	int stsize = contextStack.size();
	
	for (int j = 0 ; j < stsize; j++) {
	    Object obj = contextStack.elementAt(j);
	    
	    if (obj instanceof TestGroup) {
		TestGroup tg = (TestGroup) obj;
		String id = "<null ID>";
		try {
		    id ="\"" +  tg.getID() + "\"";
		} catch (TestFileException e) {
		}
		contextStr = "TestGroup (ID=" + id + "); ";
	    } else if (obj instanceof Library) {
		Library lib = (Library) obj;
		String id = "<null ID>";
		try {
		    id = "\"" + lib.getID() + "\"";
		} catch (TestFileException e) {
		}
		contextStr = "Library (ID=" + id + "); ";
	    } else if (obj instanceof TestCase) {
		TestCase tc = (TestCase) obj;
		String id = "<null ID>";
		try {
		    id = "\"" + tc.getID() + "\"";
		} catch (TestFileException e) {
		}
		contextStr += "TestCase (ID=" + id + "); ";
	    } else {
		String objName = obj.getClass().getName();
		objName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
		objName = objName.substring(0, objName.lastIndexOf("Impl") );
		contextStr += objName + "; ";
	    }
	}

	return contextStr;
    }
  
  

   /** 
    *  Throw a validation exception.
    * <p>
    * This method takes the incoming validation error message, and 
    * tacks-on the current contect.
    * <p>
    * @throws ValidatorException always.
    */ 
    protected void raiseValidatorException(String message) throws ValidatorException {
	throw new ValidatorException(getContextString() + " " + message);
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    NullValidator implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 


  /**
    * Describes the type of IRObj tree roots that this validator implementation knows how to handle.
    * <p>
    *  This particular implementation describes that UTD IR Object trees are accepted.
    *  Particulartly, TestRoot objects (TestGroup, Library) are accepted.
    * <p>
    * @param objTree The IR object tree to be validated.
    * @return false if the validator doesn't validate the given IRObj type.
    */
   
    public boolean accepts(IRObj objTree) {
	if (objTree instanceof TestRoot)
	    return true;

	else
	    return false;
    }



  /**
    *  Primary Validation override.
    * <p>
    *  Sub-classes do not override this method further. Sub-classes
    * need only modify the scaffold validate_ methods.
    * <p>
    * This method dispatches the tree to the internal visitor to
    * decode the tree.
    * <p>
    * @param objTree The IR object tree to be validated.
    * @exception ValidatorException if there is a violation.
    */   
    final public void _validate (IRObj objTree) throws ValidatorException {
	TestRoot root = (TestRoot) objTree;

	try {
	    m_visitor.visit(root);
	    validate_Variants(root);
	} catch (ValidatorException e) {
	    // rethrow caught validation exceptions
	    throw e;
	} catch (TestFileException e) {
	    // if it is some other form of TestFileException,
	    // rethrow it as a ValidationException
	    throw new ValidatorException(e.getMessage());
	}
    }



   /* 
    * -------------------------------------------------------------------------------------------- 
    *    Duplicate Check scaffolding methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
   
    /**
     * validates duplicate containment issues in an IR tree.
     * Duplicate checking is not done in the leaf-validate methods
     * because the correctness of the leaf elements should be checked before
     * duplicates are checked.
     * <p>
     * @param obj a TestGroup object.
     * @throws ValidatorException if the TestGroup is invalid.
     */
    public void validate_Variants(TestRoot obj) throws ValidatorException {

    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    NominalUTDValidator scaffolding methods 
    * -------------------------------------------------------------------------------------------- 
    */ 
   
    /**
     * validate a TestGroup.
     * <p>
     * @param obj a TestGroup object.
     * @throws ValidatorException if the TestGroup is invalid.
     */
    public void validate_TestGroup(TestGroup obj) throws ValidatorException {

    }

    /**
     * validate a TestCase.
     * <p>
     * @param obj a TestCase object.
     * @throws ValidatorException if the TestCase is invalid.
     */
    public void validate_TestCase(TestCase obj) throws ValidatorException {

    }

    /**
     * validate a Library.
     * <p>
     * @param obj a Library object.
     * @throws ValidatorException if the Library is invalid.
     */
    public void validate_Library(Library obj) throws ValidatorException {

    }

    /**
     * validate a TestGroupDocumentation.
     * <p>
     * @param obj a TestGroupDocumentation object.
     * @throws ValidatorException if the TestGroupDocumentation is invalid.
     */
    public void validate_TestGroupDocumentation(TestGroupDocumentation obj) throws ValidatorException {

    }

    /**
     * validate a LibDocumentation.
     * <p>
     * @param obj a LibDocumentation object.
     * @throws ValidatorException if the LibDocumentation is invalid.
     */
    public void validate_LibDocumentation(LibDocumentation obj) throws ValidatorException {

    }

    /**
     * validate a TestCaseDocumentation.
     * <p>
     * @param obj a TestCaseDocumentation object.
     * @throws ValidatorException if the TestCaseDocumentation is invalid.
     */
    public void validate_TestCaseDocumentation(TestCaseDocumentation obj) throws ValidatorException {

    }

    /**
     * validate a TestGroupAttributes.
     * <p>
     * @param obj a TestGroupAttributes object.
     * @throws ValidatorException if the TestGroupAttributes is invalid.
     */
    public void validate_TestGroupAttributes(TestGroupAttributes obj) throws ValidatorException {

    }

    /**
     * validate a LibAttributes.
     * <p>
     * @param obj a LibAttributes object.
     * @throws ValidatorException if the LibAttributes is invalid.
     */
    public void validate_LibAttributes(LibAttributes obj) throws ValidatorException {

    }

    /**
     * validate a TestCaseAttributes.
     * <p>
     * @param obj a TestCaseAttributes object.
     * @throws ValidatorException if the TestCaseAttributes is invalid.
     */
    public void validate_TestCaseAttributes(TestCaseAttributes obj) throws ValidatorException {

    }

    /**
     * validate a CodeSet.
     * <p>
     * @param obj a CodeSet object.
     * @throws ValidatorException if the CodeSet is invalid.
     */
    public void validate_CodeSet(CodeSet obj) throws ValidatorException {

    }

    /**
     * validate a LibraryDependency.
     * <p>
     * @param obj a LibraryDependency object.
     * @throws ValidatorException if the LibraryDependency is invalid.
     */
    public void validate_LibraryDependency(LibraryDependency obj) throws ValidatorException {

    }

    /**
     * validate a Import.
     * <p>
     * @param obj a Import object.
     * @throws ValidatorException if the Import is invalid.
     */
    public void validate_Import(String obj) throws ValidatorException {

    }

    /**
     * validate a BaseClass.
     * <p>
     * @param obj a BaseClass object.
     * @throws ValidatorException if the BaseClass is invalid.
     */
    public void validate_BaseClass(String obj) throws ValidatorException {

    }

    /**
     * validate a Export.
     * <p>
     * @param obj a Export object.
     * @throws ValidatorException if the Export is invalid.
     */
    public void validate_Export(String obj) throws ValidatorException {

    }


    /**
     * validate a TestCode.
     * <p>
     * @param obj a TestCode object.
     * @throws ValidatorException if the TestCode is invalid.
     */
    public void validate_TestCode(TestCode obj) throws ValidatorException {

    }


    /**
     * validate a SupportCode.
     * <p>
     * @param obj a SupportCode object.
     * @throws ValidatorException if the SupportCode is invalid.
     */
    public void validate_SupportCode(SupportCode obj) throws ValidatorException {

    }

    /**
     * validate a ExternalSupportClass.
     * <p>
     * @param obj a ExternalSupportClass object.
     * @throws ValidatorException if the ExternalSupportClass is invalid.
     */
    public void validate_ExternalSupportClass(ExternalSupportClass obj) throws ValidatorException {

    }

    /**
     * validate a InlineSupportClass.
     * <p>
     * @param obj a InlineSupportClass object.
     * @throws ValidatorException if the InlineSupportClass is invalid.
     */
    public void validate_InlineSupportClass(InlineSupportClass obj) throws ValidatorException {

    }

    /**
     * validate a CodeSource.
     * <p>
     * @param obj a CodeSource object.
     * @throws ValidatorException if the CodeSource is invalid.
     */
    public void validate_CodeSource(String obj) throws ValidatorException {

    }


    /**
     * validate a ExternalData.
     * <p>
     * @param obj a ExternalData object.
     * @throws ValidatorException if the ExternalData is invalid.
     */
    public void validate_ExternalData(ExternalData obj) throws ValidatorException {

    }

    /**
     * validate a InlineData.
     * <p>
     * @param obj a InlineData object.
     * @throws ValidatorException if the InlineData is invalid.
     */
    public void validate_InlineData(InlineData obj) throws ValidatorException {

    }

    /**
     * validate a Title.
     * <p>
     * @param obj a Title object.
     * @throws ValidatorException if the Title is invalid.
     */
    public void validate_Title(String obj) throws ValidatorException {

    }

    /**
     * validate a Description.
     * <p>
     * @param obj a Description object.
     * @throws ValidatorException if the Description is invalid.
     */
    public void validate_Description(String obj) throws ValidatorException {
	
    }

    /**
     * validate a AssertionRef.
     * <p>
     * @param obj a AssertionRef object.
     * @throws ValidatorException if the AssertionRef is invalid.
     */
    public void validate_AssertionRef(AssertionRef obj) throws ValidatorException {
	
    }

    /**
     * validate a InlineAssertion.
     * <p>
     * @param obj a InlineAssertion object.
     * @throws ValidatorException if the InlineAssertion is invalid.
     */
    public void validate_InlineAssertion(InlineAssertion obj) throws ValidatorException {
	
    }

    /**
     * validate a TestedPackage.
     * <p>
     * @param obj a TestedPackage object.
     * @throws ValidatorException if the TestedPackage is invalid.
     */
    public void validate_TestedPackage(String obj) throws ValidatorException {
	
    }

    /**
     * validate a TestedClass.
     * <p>
     * @param obj a TestedClass object.
     * @throws ValidatorException if the TestedClass is invalid.
     */
    public void validate_TestedClass(String obj) throws ValidatorException {
	
    }

    /**
     * validate a DocElem.
     * <p>
     * @param obj a DocElem object.
     * @throws ValidatorException if the DocElem is invalid.
     */
    public void validate_DocElem(DocElem obj) throws ValidatorException {
	
    }

    /**
     * validate a Author.
     * <p>
     * @param obj a Author object.
     * @throws ValidatorException if the Author is invalid.
     */
    public void validate_Author(String obj) throws ValidatorException {
	
    }

    /**
     * validate a TestCaseSpec.
     * <p>
     * @param obj a TestCaseSpec object.
     * @throws ValidatorException if the TestCaseSpec is invalid.
     */
    public void validate_TestCaseSpec(TestCaseSpec obj) throws ValidatorException {
	
    }

    /**
     * validate a TestTechnique.
     * <p>
     * @param obj a TestTechnique object.
     * @throws ValidatorException if the TestTechnique is invalid.
     */
    public void validate_TestTechnique(TestTechnique obj) throws ValidatorException {
	
    }

    /**
     * validate a MemberSig.
     * <p>
     * @param obj a MemberSig object.
     * @throws ValidatorException if the MemberSig is invalid.
     */
    public void validate_MemberSig(String obj) throws ValidatorException {
	
    }

    /**
     * validate a Input.
     * <p>
     * @param obj a Input object.
     * @throws ValidatorException if the Input is invalid.
     */
    public void validate_Input(Input obj) throws ValidatorException {
	
    }

    /**
     * validate a Precondition.
     * <p>
     * @param obj a Precondition object.
     * @throws ValidatorException if the Precondition is invalid.
     */
    public void validate_Precondition(String obj) throws ValidatorException {
	
    }

    /**
     * validate a ExpectedResultValue.
     * <p>
     * @param obj a ExpectedResultValue object.
     * @throws ValidatorException if the ExpectedResultValue is invalid.
     */
    public void validate_ExpectedResultValue(ExpectedResultValue obj) throws ValidatorException {
	
    }

    /**
     * validate a ExpectedResultSideEffect.
     * <p>
     * @param obj a ExpectedResultSideEffect object.
     * @throws ValidatorException if the ExpectedResultSideEffect is invalid.
     */
    public void validate_ExpectedResultSideEffect(ExpectedResultSideEffect obj) throws ValidatorException {
	
    }

    /**
     * validate a ExpectedResultException.
     * <p>
     * @param obj a ExpectedResultException object.
     * @throws ValidatorException if the ExpectedResultException is invalid.
     */
    public void validate_ExpectedResultException(ExpectedResultException obj) throws ValidatorException {
	
    }

    /**
     * validate a SpecElem.
     * <p>
     * @param obj a SpecElem object.
     * @throws ValidatorException if the SpecElem is invalid.
     */
    public void validate_SpecElem(SpecElem obj) throws ValidatorException {
	
    }

    /**
     * validate a RequiredResource.
     * <p>
     * @param obj a RequiredResource object.
     * @throws ValidatorException if the RequiredResource is invalid.
     */
    public void validate_RequiredResource(RequiredResource obj) throws ValidatorException {
	
    }

    /**
     * validate a AttrElem.
     * <p>
     * @param obj a AttrElem object.
     * @throws ValidatorException if the AttrElem is invalid.
     */
    public void validate_AttrElem(AttrElem obj) throws ValidatorException {
	
    }

    /**
     * validate a TargetSpec.
     * <p>
     * @param obj a TargetSpec object.
     * @throws ValidatorException if the TargetSpec is invalid.
     */
    public void validate_TargetSpec(TargetSpec obj) throws ValidatorException {
	
    }

    /**
     * validate a TargetSpec.
     * <p>
     * @param obj a TargetSpec object.
     * @throws ValidatorException if the TargetSpec is invalid.
     */
    public void validate_TargetSpecElem(TargetSpecElem obj) throws ValidatorException {
	
    }

    /**
     * validate a Keyword.
     * <p>
     * @param obj a Keyword object.
     * @throws ValidatorException if the Keyword is invalid.
     */
    public void validate_Keyword(String obj) throws ValidatorException {
	
    }

    /**
     * validate a Context.
     * <p>
     * @param obj a Context object.
     * @throws ValidatorException if the Context is invalid.
     */
    public void validate_Context(String obj) throws ValidatorException {
	
    }

    /**
     * validate a ExecuteClass.
     * <p>
     * @param obj a ExecuteClass object.
     * @throws ValidatorException if the ExecuteClass is invalid.
     */
    public void validate_ExecuteClass(String obj) throws ValidatorException {
	
    }

    /**
     * validate a ExecuteArgs.
     * <p>
     * @param obj a ExecuteArgs object.
     * @throws ValidatorException if the ExecuteArgs is invalid.
     */
    public void validate_ExecuteArgs(String obj) throws ValidatorException {
	
    }

    /**
     * validate a ExecuteNative.
     * <p>
     * @param obj a ExecuteNative object.
     * @throws ValidatorException if the ExecuteNative is invalid.
     */
    public void validate_ExecuteNative(String obj) throws ValidatorException {
	
    }

    /**
     * validate a Remote.
     * <p>
     * @param obj a Remote object.
     * @throws ValidatorException if the Remote is invalid.
     */
    public void validate_Remote(String obj) throws ValidatorException {
	
    }

    /**
     * validate a RMICClasses.
     * <p>
     * @param obj a RMICClasses object.
     * @throws ValidatorException if the RMICClasses is invalid.
     */
    public void validate_RMICClasses(String obj) throws ValidatorException {
	
    }

    /**
     * validate a SelectIf.
     * <p>
     * @param obj a SelectIf object.
     * @throws ValidatorException if the SelectIf is invalid.
     */
    public void validate_SelectIf(String obj) throws ValidatorException {
	
    }

    /**
     * validate a Timeout.
     * <p>
     * @param obj a Timeout object.
     * @throws ValidatorException if the Timeout is invalid.
     */
    public void validate_Timeout(String obj) throws ValidatorException {
	
    }


}
