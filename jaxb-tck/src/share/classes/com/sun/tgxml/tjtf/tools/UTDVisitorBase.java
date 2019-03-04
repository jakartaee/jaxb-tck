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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.Testgroup
import java.util.Stack;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * UTDVisitorBase - 
 *
 * <b>UTDVisitorBase</b> encodes a visitor pattern. A visitor pattern
 * allows code to recursively descend a tree of Internal-Representation
 * objects.
 * <p>
 *  This visitor may be sub-classed in a specific file, or it may be
 *  annonymously sub-classed on the fly to fulfill a specific function.
 *  The code below uses a visitor to print out authors of TestCases.  
 *<p>
 * <code><pre>
 *	UTDVisitorBase myVisitor = new UTDVisitorBase() {
 *
 *
 *
 *	    public void visit_Author(String author) throws TestFileException {
 *		Stack stk = getContextStack();
 *		Object top = stk.peek();
 *
 *		if (top instanceof TestCaseDocumentation) {
 *
 *		    int topindex = stk.size() - 1;
 *		    int nextElemIndex = topindex - 1;
 *
 *		    Object nextElem = stk.elementAt(nextElemIndex);
 *		    TestCase tc = (TestCase) nextElem;
 *		    m_OutputFile.println("*** TestCase: ID=\"" + tc.getID() + "\"   Author: " + author + ".");
 *		}
 *	    }
 *
 *
 *	};
 *
 *	// visit the clone
 *	myVisitor.visit(theTree);
 *
 * </pre></code>
 * <p>
 *  The visit method for each non-leaf node in the IR tree calls a method to 
 * visit the nodes that it owns.  To continue this behavior in visit methods that you
 * override, you should call that methods super.visit_xxx() method.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    UTDVisitorBase
 * ============================================================================================
 */


public  class UTDVisitorBase {


    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    
    private Stack m_ContextStack;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   
    /*
     * --------------------------------------------------------------------------------------------
     *    constructors
     * --------------------------------------------------------------------------------------------
     */
    
    public UTDVisitorBase () {
	m_ContextStack = new Stack();
    }

    /*
     * --------------------------------------------------------------------------------------------
     *    accessors
     * --------------------------------------------------------------------------------------------
     */

   /** 
    *  Get the context stack.
    * <p>
    * The context stack contains the parent trace of
    * all enclosing parent objects in a visit.
    * <p>
    * @return The context stack.
    */ 
    public final Stack getContextStack() {
	return m_ContextStack;
    }

    /*
     * --------------------------------------------------------------------------------------------
     *    constructors
     * --------------------------------------------------------------------------------------------
     */
    

 
   /** 
    *  visitor entry point. 
    * <p>
    * Classes that wish to start visiting a generic object start from this method.
    * <p>
    * @param tdObject An object in the TGXML TD JavaAPI.
    * @throws TestFileException if the tdObject is not in the API, or some user defined error condition.
    */ 
    public final void visit(Object tdObject) throws TestFileException {
	// call the private visitor (predefined set)
	_visit(tdObject);
    }


   /** 
    *   Visit method override.  Programmers can override this method
    *   If they want to add other objects to visit.
    * <p>
    *   Must return true if the object was visited.
    * @param tdObject a potential object to visit.
    * @return true if the object was visited.
    * @exception TestFileException for user-defined conditions. 
    */ 
    public  boolean visitOverride(Object tdObject) throws TestFileException {
	// call the private visitor (predefined set)
	return false;
    }

 
   /** 
    *  reset the internal fields for reusing the parser. 
    */ 
    private void _visit(Object tdObject) throws TestFileException {
	if (tdObject == null)
	    throw new TestFileException(LibResHandler.getResStr("visitor.nullObj"));

	if (tdObject instanceof TestGroup) {
	    m_ContextStack.push(tdObject);
	    visit_TestGroup((TestGroup) tdObject);
	    m_ContextStack.pop();
	}


	else if (tdObject instanceof TestCase) {
	    m_ContextStack.push(tdObject);
	    visit_TestCase((TestCase) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof Library) {
	    m_ContextStack.push(tdObject);
	    visit_Library((Library) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestGroupDocumentation) {
	    m_ContextStack.push(tdObject);
	    visit_TestGroupDocumentation((TestGroupDocumentation) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof LibDocumentation) {
	    m_ContextStack.push(tdObject);
	    visit_LibDocumentation((LibDocumentation) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestCaseDocumentation) {
	    m_ContextStack.push(tdObject);
	    visit_TestCaseDocumentation((TestCaseDocumentation) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestGroupAttributes) {
	    m_ContextStack.push(tdObject);
	    visit_TestGroupAttributes((TestGroupAttributes) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof LibAttributes) {
	    m_ContextStack.push(tdObject);
	    visit_LibAttributes((LibAttributes) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestCaseAttributes) {
	    m_ContextStack.push(tdObject);
	    visit_TestCaseAttributes((TestCaseAttributes) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof CodeSet) {
	    m_ContextStack.push(tdObject);
	    visit_CodeSet((CodeSet) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof LibraryDependency) {
	    m_ContextStack.push(tdObject);
	    visit_LibraryDependency((LibraryDependency) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestCode) {
	    m_ContextStack.push(tdObject);
	    visit_TestCode((TestCode) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof SupportCode) {
	    m_ContextStack.push(tdObject);
	    visit_SupportCode((SupportCode) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof ExternalSupportClass) {
	    m_ContextStack.push(tdObject);
	    visit_ExternalSupportClass((ExternalSupportClass) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof InlineSupportClass) {
	    m_ContextStack.push(tdObject);
	    visit_InlineSupportClass((InlineSupportClass) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof ExternalData) {
	    m_ContextStack.push(tdObject);
	    visit_ExternalData((ExternalData) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof InlineData) {
	    m_ContextStack.push(tdObject);
	    visit_InlineData((InlineData) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof AssertionRef) {
	    m_ContextStack.push(tdObject);
	    visit_AssertionRef((AssertionRef) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof InlineAssertion) {
	    m_ContextStack.push(tdObject);
	    visit_InlineAssertion((InlineAssertion) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof DocElem) {
	    m_ContextStack.push(tdObject);
	    visit_DocElem((DocElem) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestCaseSpec) {
	    m_ContextStack.push(tdObject);
	    visit_TestCaseSpec((TestCaseSpec) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TestTechnique) {
	    m_ContextStack.push(tdObject);
	    visit_TestTechnique((TestTechnique) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof Input) {
	    m_ContextStack.push(tdObject);
	    visit_Input((Input) tdObject);
	    m_ContextStack.pop();
	}


	else if (tdObject instanceof ExpectedResultValue) {
	    m_ContextStack.push(tdObject);
	    visit_ExpectedResultValue((ExpectedResultValue) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof ExpectedResultSideEffect) {
	    m_ContextStack.push(tdObject);
	    visit_ExpectedResultSideEffect((ExpectedResultSideEffect) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof ExpectedResultException) {
	    m_ContextStack.push(tdObject);
	    visit_ExpectedResultException((ExpectedResultException) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof SpecElem) {
	    m_ContextStack.push(tdObject);
	    visit_SpecElem((SpecElem) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof RequiredResource) {
	    m_ContextStack.push(tdObject);
	    visit_RequiredResource((RequiredResource) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof AttrElem) {
	    m_ContextStack.push(tdObject);
	    visit_AttrElem((AttrElem) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TargetSpec) {
	    m_ContextStack.push(tdObject);
	    visit_TargetSpec((TargetSpec) tdObject);
	    m_ContextStack.pop();
	}

	else if (tdObject instanceof TargetSpecElem) {
	    m_ContextStack.push(tdObject);
	    visit_TargetSpecElem((TargetSpecElem) tdObject);
	    m_ContextStack.pop();
	}


	else if (visitOverride(tdObject))
		; //do nothing - the case is handled
	else
	    throw new TestFileException(LibResHandler.getResStr("visitor.illObj",
								tdObject.getClass().getName()));
    }




    //------------------------------------------------------------------------------
    //  handler functions
    //------------------------------------------------------------------------------

    /**
     * visit a TestGroup object.
     * <p>
     * @param obj a TestGroup.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestGroup(TestGroup obj) throws TestFileException {
	
	_visit_TestGroup_comp(obj);
    }

    private void _visit_TestGroup_comp(TestGroup tg) throws TestFileException {
	TestGroupDocumentation tgd = tg.getTGDocumentation();
	if (tgd != null){
	    m_ContextStack.push(tgd);
	    visit_TestGroupDocumentation(tgd);
	    m_ContextStack.pop();
	}

	TestGroupAttributes tga = tg.getTGAttributes();
	if (tga != null){
	    m_ContextStack.push(tga);
	    visit_TestGroupAttributes(tga);
	    m_ContextStack.pop();
	}

	ArrayList librarys = tg.getLibraries();
	if (librarys != null) {
	    Iterator it = librarys.iterator();
	    Library lib = null;

	    while (it.hasNext()) {
		lib = (Library) it.next();
		m_ContextStack.push(lib);
		visit_Library(lib);
		m_ContextStack.pop();
	    }
	}	

	CodeSet cs = tg.getCodeSet();
	if (cs != null){
	    m_ContextStack.push(cs);
	    visit_CodeSet(cs);
	    m_ContextStack.pop();
	}

	ArrayList testCases = tg.getTestCases();
	if (testCases != null) {
	    Iterator it = testCases.iterator();
	    TestCase tc = null;

	    while (it.hasNext()) {
		tc = (TestCase) it.next();
		m_ContextStack.push(tc);
		visit_TestCase(tc);
		m_ContextStack.pop();
	    }
	}	

    }

    /**
     * visit a TestCase object.
     * <p>
     * @param obj a TestCase.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestCase(TestCase obj) throws TestFileException {
	
	_visit_TestCase_comp(obj);
    }

    private void _visit_TestCase_comp(TestCase tc) throws TestFileException {

	TestCaseDocumentation tcd = tc.getTCDocumentation();
	if (tcd != null){
	    m_ContextStack.push(tcd);
	    visit_TestCaseDocumentation(tcd);
	    m_ContextStack.pop();
	}

	TestCaseAttributes tca = tc.getTCAttributes();
	if (tca != null){
	    m_ContextStack.push(tca);
	    visit_TestCaseAttributes(tca);
	    m_ContextStack.pop();
	}

	CodeSet cs = tc.getCodeSet();
	if (cs != null){
	    m_ContextStack.push(cs);
	    visit_CodeSet(cs);
	    m_ContextStack.pop();
	}

	TestCode tcode = tc.getTestCode();
	if (tcode != null){
	    m_ContextStack.push(tcode);
	    visit_TestCode(tcode);
	    m_ContextStack.pop();
	}
    }

    /**
     * visit a Library object.
     * <p>
     * @param obj a Library.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Library(Library obj) throws TestFileException {
	
	_visit_Library_comp(obj);
    }

    private void _visit_Library_comp(Library lib) throws TestFileException {
	LibDocumentation libd = lib.getLibDocumentation();
	if (libd != null){
	    m_ContextStack.push(libd);
	    visit_LibDocumentation(libd);
	    m_ContextStack.pop();
	}

	LibAttributes liba = lib.getLibAttributes();
	if (liba != null){
	    m_ContextStack.push(liba);
	    visit_LibAttributes(liba);
	    m_ContextStack.pop();
	}

	CodeSet cs = lib.getCodeSet();
	if (cs != null){
	    m_ContextStack.push(cs);
	    visit_CodeSet(cs);
	    m_ContextStack.pop();
	}
    }

    /**
     * visit a TestGroupDocumentation object.
     * <p>
     * @param obj a TestGroupDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestGroupDocumentation(TestGroupDocumentation obj) throws TestFileException {
	
	_visit_TestGroupDocumentation_comp(obj);
    }
    
    private void _visit_TestGroupDocumentation_comp(TestGroupDocumentation tgd) throws TestFileException {
	String title = tgd.getTitle();
	visit_Title(title);

	String description = tgd.getDescription();
	if (description != null)
	    visit_Description(description);

	ArrayList assertions = tgd.getAssertions();
	if (assertions != null) {
	    Iterator it = assertions.iterator();
	    Assertion assertion = null;

	    while (it.hasNext()) {
		assertion = (Assertion) it.next();

		if (assertion instanceof AssertionRef) {
		    m_ContextStack.push(assertion);
		    visit_AssertionRef((AssertionRef)assertion);
		    m_ContextStack.pop();
		} else if (assertion instanceof InlineAssertion) {
		    m_ContextStack.push(assertion);
		    visit_InlineAssertion((InlineAssertion)assertion);
		    m_ContextStack.pop();
		}
	    }
	}

	String testedpackage = tgd.getTestedPackage();
	if (testedpackage != null)
	    visit_TestedPackage(testedpackage);

	String testedclass = tgd.getTestedClass();
	if (testedclass != null)
	    visit_TestedClass(testedclass);

	String membersig = tgd.getMemberSig();
	if (membersig != null)
	    visit_MemberSig(membersig);

	ArrayList docelems = tgd.getDocElems();
	if (docelems != null) {
	    Iterator it1 = docelems.iterator();

	    while (it1.hasNext()) {
		DocElem de = (DocElem)it1.next();
		m_ContextStack.push(de);
		visit_DocElem(de);
		m_ContextStack.pop();
	    }
	}


	ArrayList authors = tgd.getAuthors();
	if (authors != null) {
	    Iterator it2 = authors.iterator();

	    while (it2.hasNext()) {
		visit_Author((String) it2.next());
	    }
	}
    }

    /**
     * visit a LibraryDocumentation object.
     * <p>
     * @param ld a LibraryDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_LibDocumentation(LibDocumentation ld) throws TestFileException {
	
	_visit_LibDocumentation_comp(ld);
    }
    
    private void _visit_LibDocumentation_comp(LibDocumentation libd) throws TestFileException {
	String title = libd.getTitle();
	if (title != null)
	    visit_Title(title);

	String description = libd.getDescription();
	if (description != null)
	    visit_Description(description);

	ArrayList authors = libd.getAuthors();
	if (authors != null) {
	    Iterator it2 = authors.iterator();

	    while (it2.hasNext()) {
		visit_Author((String) it2.next());
	    }
	}
    }

    /**
     * visit a TestCaseDocumentation object.
     * <p>
     * @param obj a TestCaseDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestCaseDocumentation(TestCaseDocumentation obj) throws TestFileException {
	
	_visit_TestCaseDocumentation_comp(obj);
    }

    private void _visit_TestCaseDocumentation_comp(TestCaseDocumentation tcd) throws TestFileException {
	m_ContextStack.push(tcd);

	String title = tcd.getTitle();
	if (title != null)
	    visit_Title(title);

	String description = tcd.getDescription();
	if (description != null)
	    visit_Description(description);



	ArrayList authors = tcd.getAuthors();
	if (authors != null) {
	    Iterator it2 = authors.iterator();

	    while (it2.hasNext()) {
		visit_Author((String) it2.next());
	    }
	}

	ArrayList tcspecs = tcd.getTestCaseSpecs();
	if (tcspecs != null) {
	    Iterator it3 = tcspecs.iterator();

	    while (it3.hasNext()) {
		TestCaseSpec tcs = (TestCaseSpec) it3.next();
		m_ContextStack.push(tcs);
		visit_TestCaseSpec(tcs);
		m_ContextStack.pop();
	    }
	}
	
	m_ContextStack.pop();	
    }

    /**
     * visit a TestGroupAttributes object.
     * <p>
     * @param obj a TestGroupAttributes.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestGroupAttributes(TestGroupAttributes obj) throws TestFileException {
	
	_visit_TestGroupAttributes_comp(obj);
    }

    private void _visit_TestGroupAttributes_comp(TestGroupAttributes tga) throws TestFileException {
	ArrayList reqrecs = tga.getRequiredResources();
	if (reqrecs != null) {
	    Iterator it = reqrecs.iterator();

	    while (it.hasNext()) {
		RequiredResource rr = (RequiredResource) it.next();
		m_ContextStack.push(rr);
		visit_RequiredResource(rr);
		m_ContextStack.pop();
	    }
	}

	ArrayList attrelems = tga.getAttrElems();
	if (attrelems != null) {
	    Iterator it1 = attrelems.iterator();

	    while (it1.hasNext()) {
		AttrElem ae = (AttrElem) it1.next();
		m_ContextStack.push(ae);
		visit_AttrElem(ae);
		m_ContextStack.pop();
	    }
	}


	ArrayList targetspecs = tga.getTargetSpecs();
	if (targetspecs != null) {
	    Iterator it2 = targetspecs.iterator();

	    while (it2.hasNext()) {
		TargetSpec ts = (TargetSpec) it2.next();
		m_ContextStack.push(ts);
		visit_TargetSpec(ts);
		m_ContextStack.pop();
	    }
	}

	ArrayList keywords = tga.getKeywords();
	if (keywords != null) {
	    Iterator it3 = keywords.iterator();

	    while (it3.hasNext()) {
		visit_Keyword((String) it3.next());
	    }
	}

	String context = tga.getContext();
	if (context != null)
	    visit_Context(context);

	String execclass = tga.getExecuteClass();
	if (execclass != null)
	    visit_ExecuteClass(execclass);

	String execargs = tga.getExecuteArgs();
	if (execargs != null)
	    visit_ExecuteArgs(execargs);


	String execnative = tga.getExecuteNative();
	if (execnative != null)
	    visit_ExecuteNative(execnative);



	ArrayList remotes = tga.getRemotes();
	if (remotes != null) {
	    Iterator it4 = remotes.iterator();

	    while (it4.hasNext()) {
		visit_Remote((String) it4.next());
	    }
	}

	String rmic = tga.getRMICClasses();
	if (rmic != null)
	    visit_RMICClasses(rmic);



	ArrayList selectifs = tga.getSelectIfs();
	if (selectifs != null) {
	    Iterator it5 = selectifs.iterator();

	    while (it5.hasNext()) {
		visit_SelectIf((String) it5.next());
	    }
	}

	String timeout = tga.getTimeout();
	if (timeout != null)
	    visit_Timeout((String) timeout);
    }

    /**
     * visit a LibraryAttributes object.
     * <p>
     * @param la a LibraryAttributes.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_LibAttributes(LibAttributes la) throws TestFileException {
	
	_visit_LibAttributes_comp(la);
    }

    private void _visit_LibAttributes_comp(LibAttributes liba) throws TestFileException {
	ArrayList reqrecs = liba.getRequiredResources();
	if (reqrecs != null) {
	    Iterator it = reqrecs.iterator();

	    while (it.hasNext()) {
		RequiredResource rr = (RequiredResource) it.next();
		m_ContextStack.push(rr);
		visit_RequiredResource(rr);
		m_ContextStack.pop();
	    }
	}

	ArrayList attrelems = liba.getAttrElems();
	if (attrelems != null) {
	    Iterator it1 = attrelems.iterator();

	    while (it1.hasNext()) {
		AttrElem ae = (AttrElem) it1.next();
		m_ContextStack.push(ae);
		visit_AttrElem(ae);
		m_ContextStack.pop();
	    }
	}


	ArrayList targetspecs = liba.getTargetSpecs();
	if (targetspecs != null) {
	    Iterator it2 = targetspecs.iterator();

	    while (it2.hasNext()) {
		TargetSpec ts = (TargetSpec) it2.next();
		m_ContextStack.push(ts);
		visit_TargetSpec(ts);
		m_ContextStack.pop();
	    }
	}
    }

    /**
     * visit a TestCaseAttributes object.
     * <p>
     * @param obj a TestCaseAttributes.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestCaseAttributes(TestCaseAttributes obj) throws TestFileException {
	
	_visit_TestCaseAttributes_comp(obj);
    }

    private void _visit_TestCaseAttributes_comp(TestCaseAttributes tca) throws TestFileException {
	m_ContextStack.push(tca);

	ArrayList reqrecs = tca.getRequiredResources();
	if (reqrecs != null) {
	    Iterator it = reqrecs.iterator();

	    while (it.hasNext()) {
		RequiredResource rr = (RequiredResource) it.next();
		m_ContextStack.push(rr);
		visit_RequiredResource(rr);
		m_ContextStack.pop();
	    }
	}

	ArrayList attrelems = tca.getAttrElems();
	if (attrelems != null) {
	    Iterator it1 = attrelems.iterator();

	    while (it1.hasNext()) {
		AttrElem ae = (AttrElem) it1.next();
		m_ContextStack.push(ae);
		visit_AttrElem(ae);
		m_ContextStack.pop();
	    }
	}


	ArrayList targetspecs = tca.getTargetSpecs();
	if (targetspecs != null) {
	    Iterator it2 = targetspecs.iterator();

	    while (it2.hasNext()) {
		TargetSpec ts = (TargetSpec) it2.next();
		m_ContextStack.push(ts);
		visit_TargetSpec(ts);
		m_ContextStack.pop();
	    }
	}


	String timeout = tca.getTimeout();
	if (timeout != null)
	    visit_Timeout(timeout);
    }

    /**
     * visit a CodeSet object.
     * <p>
     * @param obj a CodeSet.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_CodeSet(CodeSet obj) throws TestFileException {
	_visit_CodeSet_comp(obj);
    }

    private void _visit_CodeSet_comp(CodeSet cs) throws TestFileException {
	ArrayList dependencies = cs.getDependencies();
	if (dependencies != null && dependencies.size() > 0) {
	    Iterator it0 = dependencies.iterator();

	    while (it0.hasNext()) {
		CodeDependency dep = (CodeDependency) it0.next();

		if (dep instanceof LibraryDependency) {
		    LibraryDependency ld = (LibraryDependency) dep;
		    m_ContextStack.push(ld);
		    visit_LibraryDependency(ld);
		    m_ContextStack.pop();
		}
	    }
	}

	ArrayList imports = cs.getImports();
	if (imports != null) {
	    Iterator it1 = imports.iterator();

	    while (it1.hasNext()) {
		visit_Import((String) it1.next());
	    }
	}

	String executeArgs = cs.getExecuteArgs();
	if (executeArgs != null)
	    visit_ExecuteArgs(executeArgs);

	String context = cs.getContext();
	if (context != null)
	    visit_Context(context);


	String baseclass = cs.getBaseClass();
	if (baseclass != null)
	    visit_BaseClass(baseclass);


	ArrayList exports = cs.getExports();
	if (exports != null) {
	    Iterator it1 = exports.iterator();

	    while (it1.hasNext()) {
		visit_Export((String) it1.next());
	    }
	}


	SupportCode supportcode = cs.getSupportCode();
	if (supportcode != null) {
	    m_ContextStack.push(supportcode);
	    visit_SupportCode(supportcode);
	    m_ContextStack.pop();
	}

	ArrayList supportclasses = cs.getSupportClasses();
	if (supportclasses != null) {
	    Iterator it2 = supportclasses.iterator();
	    SupportClass sc = null;

	    while (it2.hasNext()) {
		sc = (SupportClass) it2.next();

		if (sc instanceof ExternalSupportClass) {
		    ExternalSupportClass esc = (ExternalSupportClass) sc;
		    m_ContextStack.push(esc);
		    visit_ExternalSupportClass(esc);
		    m_ContextStack.pop();
		} else if (sc instanceof InlineSupportClass) {
		    InlineSupportClass isc = (InlineSupportClass) sc;
		    m_ContextStack.push(isc);
		    visit_InlineSupportClass(isc);
		    m_ContextStack.pop();
		}
	    }
	}



	ArrayList data = cs.getData();
	if (data != null) {
	    Iterator it3 = data.iterator();
	    Data datum = null;

	    while (it3.hasNext()) {
		datum = (Data) it3.next();

		if (datum instanceof ExternalData) {
		    ExternalData ed = (ExternalData) datum;
		    m_ContextStack.push(ed);
		    visit_ExternalData(ed);
		    m_ContextStack.pop();
		} else if (datum instanceof InlineData) {
		    InlineData id = (InlineData) datum;
		    m_ContextStack.push(id);
		    visit_InlineData(id);
		    m_ContextStack.pop();
		}
	    }
	}
    }


    /**
     * visit a LibraryDependency object.
     * <p>
     * @param obj a LibraryDependency.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_LibraryDependency(LibraryDependency obj) throws TestFileException {
	
    }


    /**
     * visit a Import object.
     * <p>
     * @param importStr a Import.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Import(String importStr) throws TestFileException {
	
    }


    /**
     * visit a BaseClass object.
     * <p>
     * @param baseClass a BaseClass.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_BaseClass(String baseClass) throws TestFileException {
	
    }

    /**
     * visit a Export object.
     * <p>
     * @param exportStr a Export.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Export(String exportStr) throws TestFileException {
	
    }


    /**
     * visit a TestCode object.
     * <p>
     * @param tc a TestCode.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestCode(TestCode tc) throws TestFileException {
	
    }


    /**
     * visit a SupportCode object.
     * <p>
     * @param sc a SupportCode.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_SupportCode(SupportCode sc) throws TestFileException {
	
    }


    /**
     * visit a ExternalSupportClass object.
     * <p>
     * @param esc a ExternalSupportClass.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExternalSupportClass(ExternalSupportClass esc) throws TestFileException {
	_visit_ExternalSupportClass_comp(esc);

    }

    private void _visit_ExternalSupportClass_comp(ExternalSupportClass esc) throws TestFileException {
    }

    /**
     * visit a InlineSupportClass object.
     * <p>
     * @param isc a InlineSupportClass.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_InlineSupportClass(InlineSupportClass isc) throws TestFileException {
	_visit_InlineSupportClass_comp(isc);

    }

    private void _visit_InlineSupportClass_comp(InlineSupportClass isc) throws TestFileException {
	visit_CodeSource(isc.getSource());
    }

    /**
     * visit a CodeSource object.
     * <p>
     * @param codeSource a CodeSource.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_CodeSource(String codeSource) throws TestFileException {
	
    }


    /**
     * visit a ExternalData object.
     * <p>
     * @param ed a ExternalData.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExternalData(ExternalData ed) throws TestFileException {
	
    }


    /**
     * visit a InlineData object.
     * <p>
     * @param id a InlineData.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_InlineData(InlineData id) throws TestFileException {
	
    }


    /**
     * visit a Title object.
     * <p>
     * @param title a Title.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Title(String title) throws TestFileException {
	
    }


    /**
     * visit a Description object.
     * <p>
     * @param description a Description.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Description(String description) throws TestFileException {
	
    }


    /**
     * visit a AssertionRef object.
     * <p>
     * @param ar a AssertionRef.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_AssertionRef(AssertionRef ar) throws TestFileException {
	
    }


    /**
     * visit a InlineAssertion object.
     * <p>
     * @param ia a InlineAssertion.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_InlineAssertion(InlineAssertion ia) throws TestFileException {
	
    }


    /**
     * visit a TestedPackage object.
     * <p>
     * @param tp a TestedPackage.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestedPackage(String tp) throws TestFileException {
	
    }


    /**
     * visit a TestedClass object.
     * <p>
     * @param tc a TestedClass.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestedClass(String tc) throws TestFileException {
	
    }

    /**
     * visit a DocElem object.
     * <p>
     * @param de a DocElem.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_DocElem(DocElem de) throws TestFileException {
	
    }


    /**
     * visit a Author object.
     * <p>
     * @param author a Author.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Author(String author) throws TestFileException {
	
    }


    /**
     * visit a TestCaseSpec object.
     * <p>
     * @param tcs a TestCaseSpec.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestCaseSpec(TestCaseSpec tcs) throws TestFileException {
	
	_visit_TestCaseSpec_comp(tcs);
    }

    private void _visit_TestCaseSpec_comp(TestCaseSpec tcs) throws TestFileException {
	ArrayList assertions = tcs.getAssertions();
	if (assertions != null) {
	    Iterator it = assertions.iterator();
	    Assertion assertion = null;

	    while (it.hasNext()) {
		assertion = (Assertion) it.next();

		if (assertion instanceof AssertionRef) {
		    m_ContextStack.push(assertion);
		    visit_AssertionRef((AssertionRef)assertion);
		    m_ContextStack.pop();
		} else if (assertion instanceof InlineAssertion) {
		    m_ContextStack.push(assertion);
		    visit_InlineAssertion((InlineAssertion)assertion);
		    m_ContextStack.pop();
		}
	    }
	}

	TestTechnique tt = tcs.getTestTechnique();
	m_ContextStack.push(tt);
	visit_TestTechnique(tt);
	m_ContextStack.pop();

	String membersig = tcs.getMemberSig();
	visit_MemberSig(membersig);

	ArrayList inputs = tcs.getInputs();
	if (inputs != null) {
	    Iterator it1 = inputs.iterator();

	    while (it1.hasNext()) {
		Input inp = (Input) it1.next();
		m_ContextStack.push(inp);
		visit_Input(inp);
		m_ContextStack.pop();
	    }
	}

	ArrayList preconditions = tcs.getPreconditions();
	if (preconditions != null) {
	    Iterator it2 = preconditions.iterator();

	    while (it2.hasNext()) {
		visit_Precondition((String) it2.next());
	    }
	}

	ExpectedResultValue erv = tcs.getExpectedResultValue();
	if (erv != null) {
	    m_ContextStack.push(erv);
	    visit_ExpectedResultValue(erv);
	    m_ContextStack.pop();
	}

	ArrayList erses = tcs.getExpectedResultSideEffects();
	if (erses != null) {
	    Iterator it3 = erses.iterator();

	    while (it3.hasNext()) {
		ExpectedResultSideEffect erse = (ExpectedResultSideEffect) it3.next();
		m_ContextStack.push(erse);
		visit_ExpectedResultSideEffect(erse);
		m_ContextStack.pop();
	    }
	}

	ArrayList eres = tcs.getExpectedResultExceptions();
	if (eres != null) {
	    Iterator it4 = eres.iterator();

	    while (it4.hasNext()) {
		ExpectedResultException ere = (ExpectedResultException) it4.next();
		m_ContextStack.push(ere);
		visit_ExpectedResultException(ere);
		m_ContextStack.pop();
	    }
	}

	ArrayList specelems = tcs.getSpecElems();
	if (specelems != null) {
	    Iterator it5 = specelems.iterator();

	    while (it5.hasNext()) {
		SpecElem se = (SpecElem) it5.next();
		m_ContextStack.push(se);
		visit_SpecElem(se);
		m_ContextStack.pop();
	    }
	}
    }

    /**
     * visit a TestTechnique object.
     * <p>
     * @param tt a TestTechnique.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TestTechnique(TestTechnique tt) throws TestFileException {
	
    }


    /**
     * visit a MemberSig object.
     * <p>
     * @param sig a MemberSig.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_MemberSig(String sig) throws TestFileException {
	
    }


    /**
     * visit a Input object.
     * <p>
     * @param input a Input.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Input(Input input) throws TestFileException {
	
    }


    /**
     * visit a Precondition object.
     * <p>
     * @param precondition a Precondition.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Precondition(String precondition) throws TestFileException {
	
    }


    /**
     * visit a ExpectedResultValue object.
     * <p>
     * @param erv a ExpectedResultValue.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExpectedResultValue(ExpectedResultValue erv) throws TestFileException {
	
    }


    /**
     * visit a ExpectedResultSideEffect object.
     * <p>
     * @param erse a ExpectedResultSideEffect.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExpectedResultSideEffect(ExpectedResultSideEffect erse) throws TestFileException {
	
    }


    /**
     * visit a ExpectedResultException object.
     * <p>
     * @param ere a ExpectedResultException.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExpectedResultException(ExpectedResultException ere) throws TestFileException {
	
    }


    /**
     * visit a SpecElem object.
     * <p>
     * @param se a SpecElem.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_SpecElem(SpecElem se) throws TestFileException {
	
    }


    /**
     * visit a RequiredResource object.
     * <p>
     * @param rr a RequiredResource.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_RequiredResource(RequiredResource rr) throws TestFileException {
	
    }


    /**
     * visit a AttrElem object.
     * <p>
     * @param ae a AttrElem.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_AttrElem(AttrElem ae) throws TestFileException {
	
    }


    /**
     * visit a TargetSpec object.
     * <p>
     * @param ts a TargetSpec.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TargetSpec(TargetSpec ts) throws TestFileException {
	
	_visit_TargetSpec_comp(ts);
    }

    private void _visit_TargetSpec_comp(TargetSpec ts) throws TestFileException {

	ArrayList tses = ts.getTargetSpecElems();
	if (tses != null) {
	    Iterator it = tses.iterator();
	    TargetSpecElem tse = null;

	    while (it.hasNext()) {
		tse = (TargetSpecElem) it.next();
		m_ContextStack.push(tse);
		visit_TargetSpecElem(tse);
		m_ContextStack.pop();
	    }
	}	

    }



    /**
     * visit a TargetSpecElem object.
     * <p>
     * @param tse a TargetSpecElem.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_TargetSpecElem(TargetSpecElem tse) throws TestFileException {

    }


    /**
     * visit a Keyword object.
     * <p>
     * @param keyword a Keyword.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Keyword(String keyword) throws TestFileException {
	
    }


    /**
     * visit a Context object.
     * <p>
     * @param context a Context.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Context(String context) throws TestFileException {
	
    }


    /**
     * visit a ExecuteClass object.
     * <p>
     * @param execargs a ExecuteClass.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExecuteClass(String execargs) throws TestFileException {
	
    }

    /**
     * visit a ExecuteArgs object.
     * <p>
     * @param execargs a ExecuteArgs.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExecuteArgs(String execargs) throws TestFileException {
	
    }


    /**
     * visit a ExecuteNative object.
     * <p>
     * @param execNative a ExecuteNative.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_ExecuteNative(String execNative) throws TestFileException {
	
    }

    /**
     * visit a Remote object.
     * <p>
     * @param remote a Remote.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_Remote(String remote) throws TestFileException {
	
    }


    /**
     * visit a RMICClasses object.
     * <p>
     * @param rmicclasses a RMICClasses.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_RMICClasses(String rmicclasses) throws TestFileException {
	
    }


    /**
     * visit a SelectIf object.
     * <p>
     * @param selectIf a SelectIf.
     * @throws TestFileException for some user-defined error.
     */
    protected void visit_SelectIf(String selectIf) throws TestFileException {
	
    }

    /**
     * visit a Timeout object.
     * <p>
     * @param timeout a Timeout.
     * @throws TestFileException for some user-defined error.     
     */
    protected void visit_Timeout(String timeout) throws TestFileException {
	
    }

}
