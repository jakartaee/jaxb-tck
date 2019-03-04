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
import java.util.Map;
import java.util.TreeMap;
import java.util.Hashtable;
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
 * UTDEquivVisitorBase - 
 *
 * <b>UTDEquivVisitorBase</b> encodes a visitor pattern. A visitor pattern
 * allows code to recursively descend a tree of Internal-Representation
 * objects.
 * <p>
 *  This visitor may be sub-classed in a specific file, or it may be
 *  annonymously sub-classed on the fly to fulfill a specific function.
 *  The code below uses a visitor to print out authors of TestCases.  
 *<p>
 * <code><pre>
 *	UTDEquivVisitorBase myVisitor = new UTDEquivVisitorBase() {
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
 *    UTDEquivVisitorBase
 * ============================================================================================
 */


public  class UTDEquivVisitorBase {


    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    
    static private final String STR_TERM = System.getProperty("line.separator");

    protected Stack m_ContextStack;

    private boolean  m_debug;
    private StringBuffer  m_buffer;
    private boolean  m_bufferAllErrors;


    // This exception is thrown when items are not equivalent
    public class NonEquivException extends TestFileException {
	
	public NonEquivException (String message) {
	    super(message);
	}
    }

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
    
    public UTDEquivVisitorBase () {
	m_ContextStack = new Stack();
	m_debug = false;
	m_bufferAllErrors = false;
	m_buffer = new StringBuffer();
    }

    /*
     * --------------------------------------------------------------------------------------------
     *    accessors
     * --------------------------------------------------------------------------------------------
     */

   /** 
    *  Set the visitors debug mode (prints diagnostics).
    * <p>
    * @return The context stack.
    */ 
    public void setDebug(boolean debug) {
	 m_debug = debug;
    }

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

    public void setBufferErrors(boolean bufferItems) {
	m_bufferAllErrors = bufferItems;
    }

    public String dumpBuffer() {
	return m_buffer.toString();
    }

    /*
     * --------------------------------------------------------------------------------------------
     *    constructors
     * --------------------------------------------------------------------------------------------
     */
  
   /** 
    *  Set the visitors debug mode (prints diagnostics).
    * <p>
    * @return The context stack.
    */ 
    protected String getContextString() {
	String contextStr = "";
	int stsize = m_ContextStack.size();
	String _indentVal = "   ";
	String indent = "";
	
	for (int j = 0 ; j < stsize; j++) {
	    if (j > 0)
		contextStr += STR_TERM;

	    Object obj = m_ContextStack.elementAt(j);
	    
	    if (obj instanceof TestGroup) {
		TestGroup tg = (TestGroup) obj;
		String id = "<null ID>";
		try {
		    id ="\"" +  tg.getID() + "\"";
		} catch (TestFileException e) {
		}
		contextStr = indent + "TestGroup (ID=" + id + ");";
	    } else if (obj instanceof Library) {
		Library lib = (Library) obj;
		String id = "<null ID>";
		try {
		    id = "\"" + lib.getID() + "\"";
		} catch (TestFileException e) {
		}
	 	contextStr = indent + "Library (ID=" + id + "); ";
	    } else if (obj instanceof TestCase) {
		TestCase tc = (TestCase) obj;
		String id = "<null ID>";
		try {
		    id = "\"" + tc.getID() + "\"";
		} catch (TestFileException e) {
		}
	 	contextStr += indent + "TestCase (ID=" + id + "); ";
	    } else {
		String objName = obj.getClass().getName();
		objName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
		objName = objName.substring(0, objName.lastIndexOf("Impl") );
	 	contextStr += indent + objName + "; ";
	    }

	    indent += _indentVal;
	}

	return contextStr;
    }
  

   /** 
    *  Set the visitors debug mode (prints diagnostics).
    * <p>
    * @return The context stack.
    * @throws TestFileException always.
    */ 
    protected void raiseEquivException(String message) throws TestFileException {
	String finalMesg = getContextString() + " " + message;
	if (m_bufferAllErrors) {
	    m_buffer.append(finalMesg + STR_TERM);
	} else
	    throw new NonEquivException(finalMesg);
    }


 
   /** 
    *  visitor entry point. 
    * <p>
    * Classes that wish to start visiting a generic object start from this method.
    * <p>
    * @param utdObject1 An object in the TGXML TD JavaAPI.
    */ 
    public final boolean equivP(Object utdObject1, Object utdObject2) {
	boolean retval = true;
	try {
	    _equiv(utdObject1, utdObject2);
	} catch (TestFileException e) {
	    retval = false;
	}
	return retval;
	 
    }


   /** 
    *  visitor entry point. 
    * <p>
    * Classes that wish to start visiting a generic object start from this method.
    * <p>
    * @param utdObject1 An object in the TGXML TD JavaAPI.
    * @throws TestFileException if the tdObject is not in the API, or some user defined error condition.
    */ 
    protected void equiv(Object utdObject1, Object utdObject2) throws TestFileException{
	_equiv(utdObject1, utdObject2);	 
    }


 
   /** 
    *  reset the internal fields for reusing the parser. 
    */ 
    private void _equiv(Object utdObject1, Object utdObject2) throws TestFileException {
	// Eq objects are equiv
	if (utdObject1 == utdObject2) 
	    return;

	if (! _equiv_Objs(utdObject1, utdObject2, "<unknown>"))
	    return;


	if (utdObject1 instanceof TestGroup && utdObject2 instanceof TestGroup)
	    equiv_TestGroup((TestGroup) utdObject1, (TestGroup) utdObject2 );


	else if (utdObject1 instanceof TestCase && utdObject2 instanceof TestCase)
	    equiv_TestCase((TestCase) utdObject1, (TestCase) utdObject2 );


	else if (utdObject1 instanceof Library && utdObject2 instanceof Library)
	    equiv_Library((Library) utdObject1, (Library) utdObject2 );


	else if (utdObject1 instanceof TestGroupDocumentation && utdObject2 instanceof TestGroupDocumentation)
	    equiv_TestGroupDocumentation((TestGroupDocumentation) utdObject1, (TestGroupDocumentation) utdObject2 );


	else if (utdObject1 instanceof LibDocumentation && utdObject2 instanceof LibDocumentation)
	    equiv_LibDocumentation((LibDocumentation) utdObject1, (LibDocumentation) utdObject2 );


	else if (utdObject1 instanceof TestCaseDocumentation && utdObject2 instanceof TestCaseDocumentation)
	    equiv_TestCaseDocumentation((TestCaseDocumentation) utdObject1, (TestCaseDocumentation) utdObject2 );


	else if (utdObject1 instanceof TestGroupAttributes && utdObject2 instanceof TestGroupAttributes)
	    equiv_TestGroupAttributes((TestGroupAttributes) utdObject1, (TestGroupAttributes) utdObject2 );


	else if (utdObject1 instanceof LibAttributes && utdObject2 instanceof LibAttributes)
	    equiv_LibAttributes((LibAttributes) utdObject1, (LibAttributes) utdObject2 );


	else if (utdObject1 instanceof TestCaseAttributes && utdObject2 instanceof TestCaseAttributes)
	    equiv_TestCaseAttributes((TestCaseAttributes) utdObject1, (TestCaseAttributes) utdObject2 );


	else if (utdObject1 instanceof CodeSet && utdObject2 instanceof CodeSet)
	    equiv_CodeSet((CodeSet) utdObject1, (CodeSet) utdObject2 );


	else if (utdObject1 instanceof LibraryDependency && utdObject2 instanceof LibraryDependency)
	    equiv_LibraryDependency((LibraryDependency) utdObject1, (LibraryDependency) utdObject2 );


	else if (utdObject1 instanceof TestCode && utdObject2 instanceof TestCode)
	    equiv_TestCode((TestCode) utdObject1, (TestCode) utdObject2 );


	else if (utdObject1 instanceof SupportCode && utdObject2 instanceof SupportCode)
	    equiv_SupportCode((SupportCode) utdObject1, (SupportCode) utdObject2 );


	else if (utdObject1 instanceof ExternalSupportClass && utdObject2 instanceof ExternalSupportClass)
	    equiv_ExternalSupportClass((ExternalSupportClass) utdObject1, (ExternalSupportClass) utdObject2 );


	else if (utdObject1 instanceof InlineSupportClass && utdObject2 instanceof InlineSupportClass)
	    equiv_InlineSupportClass((InlineSupportClass) utdObject1, (InlineSupportClass) utdObject2 );


	else if (utdObject1 instanceof ExternalData && utdObject2 instanceof ExternalData)
	    equiv_ExternalData((ExternalData) utdObject1, (ExternalData) utdObject2 );


	else if (utdObject1 instanceof InlineData && utdObject2 instanceof InlineData)
	    equiv_InlineData((InlineData) utdObject1, (InlineData) utdObject2 );


	else if (utdObject1 instanceof AssertionRef && utdObject2 instanceof AssertionRef)
	    equiv_AssertionRef((AssertionRef) utdObject1, (AssertionRef) utdObject2 );


	else if (utdObject1 instanceof InlineAssertion && utdObject2 instanceof InlineAssertion)
	    equiv_InlineAssertion((InlineAssertion) utdObject1, (InlineAssertion) utdObject2 );


	else if (utdObject1 instanceof DocElem && utdObject2 instanceof DocElem)
	    equiv_DocElem((DocElem) utdObject1, (DocElem) utdObject2 );


	else if (utdObject1 instanceof TestCaseSpec && utdObject2 instanceof TestCaseSpec)
	    equiv_TestCaseSpec((TestCaseSpec) utdObject1, (TestCaseSpec) utdObject2 );


	else if (utdObject1 instanceof Input && utdObject2 instanceof Input)
	    equiv_Input((Input) utdObject1, (Input) utdObject2 );

	else if (utdObject1 instanceof TestTechnique && utdObject2 instanceof TestTechnique)
	    equiv_TestTechnique((TestTechnique) utdObject1, (TestTechnique) utdObject2 );



	else if (utdObject1 instanceof ExpectedResultValue && utdObject2 instanceof ExpectedResultValue)
	    equiv_ExpectedResultValue((ExpectedResultValue) utdObject1, (ExpectedResultValue) utdObject2 );


	else if (utdObject1 instanceof ExpectedResultSideEffect && utdObject2 instanceof ExpectedResultSideEffect)
	    equiv_ExpectedResultSideEffect((ExpectedResultSideEffect) utdObject1, (ExpectedResultSideEffect) utdObject2 );


	else if (utdObject1 instanceof ExpectedResultException && utdObject2 instanceof ExpectedResultException)
	    equiv_ExpectedResultException((ExpectedResultException) utdObject1, (ExpectedResultException) utdObject2 );


	else if (utdObject1 instanceof SpecElem && utdObject2 instanceof SpecElem)
	    equiv_SpecElem((SpecElem) utdObject1, (SpecElem) utdObject2 );


	else if (utdObject1 instanceof RequiredResource && utdObject2 instanceof RequiredResource)
	    equiv_RequiredResource((RequiredResource) utdObject1, (RequiredResource) utdObject2 );


	else if (utdObject1 instanceof AttrElem && utdObject2 instanceof AttrElem)
	    equiv_AttrElem((AttrElem) utdObject1, (AttrElem) utdObject2 );


	else if (utdObject1 instanceof TargetSpec && utdObject2 instanceof TargetSpec)
	    equiv_TargetSpec((TargetSpec) utdObject1, (TargetSpec) utdObject2 );


	else
	    // The two objs are not instances of equivalent utd objs.
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.type.uneq", 
								utdObject1.getClass().getName(),
								utdObject2.getClass().getName()));
    }




    //------------------------------------------------------------------------------
    //  handler functions
    //------------------------------------------------------------------------------
  
    private boolean _equiv_Objs(Object obj1, Object obj2, String attrName) throws TestFileException {
	if (obj1 == null && obj2 == null)
	    return false;
	if (obj1 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genobj.uneq", attrName, "null", attrName));
	if (obj2 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genobj.uneq", attrName, attrName, "null"));

	if (m_debug)
	    System.out.println("**  Comparing " + getContextString() + "(" + attrName + "1 to " + attrName + "2).  **");

	return true;
    }

   
    private boolean _equiv_List(ArrayList al1, ArrayList al2, String attrName) throws TestFileException {
	if (al1 == null && al2 == null)
	    return false;
	if (al1 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genlist.uneq", attrName, "null", attrName+"[]"));
	if (al2 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genlist.uneq", attrName, attrName+"[]", "null"));

	// Fail fast if the lengths are not the same.
	if (al1.size() != al2.size())
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genlist.len", attrName, attrName,
								Integer.toString(al1.size()), attrName, 
								Integer.toString(al2.size())));

	return true;
	
    }

    /**
     * compare TestGroup objects.
     * <p>
     * @param obj1 a TestGroup.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_IDs(TestItem obj1, TestItem obj2, String grouping) throws TestFileException {
	// Compare the fields
	String id1 = obj1.getID();
	String id2 = obj2.getID();

	if (id1 == null && id2 == null)
	    return;

	if (id1 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.id.uneq", grouping, "null", id2));

	else if (id2 == null)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.id.uneq", grouping, id1, "null"));

	if (!id1.equals(id2))	
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.id.uneq", grouping, id1, id2));


    }


    //------------------------------------------------------------------------------
    //  "Test" Equivalency methods
    //------------------------------------------------------------------------------


    /**
     * compare TestGroup objects.
     * <p>
     * @param tg1 a TestGroup.
     * @param tg2 a TestGroup.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestGroup(TestGroup tg1, TestGroup tg2) throws TestFileException {
	if (! _equiv_Objs(tg1, tg2, "TestGroup"))
	    return;

	m_ContextStack.push(tg1);

	// Compare the fields
	equiv_IDs(tg1, tg2, "TestGroup");


	equiv_TestGroupDocumentation(tg1.getTGDocumentation(), tg2.getTGDocumentation());
	equiv_TestGroupAttributes(tg1.getTGAttributes(), tg2.getTGAttributes());
	equiv_CodeSet(tg1.getCodeSet(), tg2.getCodeSet());
	equiv_InlineLibrariesList(tg1.getLibraries(), tg2.getLibraries());
	equiv_TestCasesList(tg1.getTestCases(), tg2.getTestCases());

	m_ContextStack.pop();

    }


    public void equiv_TestCasesList(ArrayList testCases1, ArrayList testCases2) throws TestFileException {

	//  Compare TestCases
	if (! _equiv_List(testCases1, testCases2, "TestCases"))
	    return;

	// Build a name-testcase hashmap for the first tg.
	Iterator it1 = testCases1.iterator();
	Hashtable tcNames1 = new Hashtable();
	while ( it1.hasNext()) {
	    TestCase tc = (TestCase) it1.next();
	    String ID = tc.getID();
	    String VarID = tc.getVarID();
	    if (VarID == null)
		VarID = "";
	    // No two TestCases should have the same ID and VarID.
	    String key = ID + ":" + VarID;
	    tcNames1.put(key, tc);
	}
		
	// Build a name-testcase hashmap for the second tg.
	Iterator it2 = testCases2.iterator();
	Hashtable tcNames2 = new Hashtable();
	while ( it2.hasNext()) {
	    TestCase tc = (TestCase) it2.next();
	    String ID = tc.getID();
	    String VarID = tc.getVarID();
	    if (VarID == null)
		VarID = "";
	    String key = ID + ":" + VarID;
	    // Fail immediately if we have TC's in 2 that aren't in 1.
	    if (! tcNames1.containsKey(key))
		raiseEquivException("Unequal TestCases:  TestCases1: missing TestCase (ID: " + ID + ", VarID: " + VarID + ").");
	    tcNames2.put(key, tc);
	}
		
	// Look for tcs that are in 1 that are not in 2.
	Iterator it3 = tcNames1.entrySet().iterator();
	while ( it3.hasNext()) {
	    Map.Entry entry = (Map.Entry) it3.next();
	    String name1 = (String) entry.getKey();
	    TestCase tc1 = (TestCase) entry.getValue();
	    // Fail immediately if we have TC's in 2 that aren't in 1.
	    if (! tcNames2.containsKey(name1)) {
		String ID = tc1.getID();
		String VarID = tc1.getVarID();
		if (VarID == null)
		    VarID = "";

		raiseEquivException("Unequal TestCases:  TestCases2: missing TestCase (ID: " + ID + ", VarID: " + VarID + ").");
	    }
	}
		
	// Both lists have the same names.
	//
	//  Now, try to find matching cases
		
	// Look for tcs that are in 1 that are not in 2.
	Iterator it4 = tcNames1.entrySet().iterator();
	while ( it4.hasNext()) {
	    
	    Map.Entry entry1 = (Map.Entry) it4.next();
	    String name1 = (String) entry1.getKey();
	    TestCase tcs1 = (TestCase) entry1.getValue();
	    TestCase tcs2 =  (TestCase) tcNames2.get(name1);

	    if (tcs1 != null && tcs2 != null) 
		equiv_TestCase(tcs1, tcs2);
	}
    }


    public void equiv_InlineLibrariesList(ArrayList librarys1, ArrayList librarys2) throws TestFileException {

	//  Compare TestCases
	if (! _equiv_List(librarys1, librarys2, "Libraries"))
	    return;

	// Build a name-library hashmap for the first tg.
	Iterator it1 = librarys1.iterator();
	Hashtable libNames1 = new Hashtable();
	while ( it1.hasNext()) {
	    Library lib = (Library) it1.next();
	    String ID = lib.getID();
	    String VarID = lib.getVarID();
	    if (VarID == null)
		VarID = "";
	    // No two Libraries should have the same ID and VarID.
	    String key = ID + ":" + VarID;
	    libNames1.put(key, lib);
	}
		
	// Build a name-teslibase hashmap for the second tg.
	Iterator it2 = librarys2.iterator();
	Hashtable libNames2 = new Hashtable();
	while ( it2.hasNext()) {
	    Library lib = (Library) it2.next();
	    String ID = lib.getID();
	    String VarID = lib.getVarID();
	    if (VarID == null)
		VarID = "";
	    String key = ID + ":" + VarID;
	    // Fail immediately if we have LIB's in 2 that aren't in 1.
	    if (! libNames1.containsKey(key))
		raiseEquivException("Unequal Libraries:  Libraries1: missing Library (ID: " + ID + ", VarID: " + VarID + ").");
	    libNames2.put(key, lib);
	}
		
	// Look for libs that are in 1 that are not in 2.
	Iterator it3 = libNames1.entrySet().iterator();
	while ( it3.hasNext()) {
	    Map.Entry entry = (Map.Entry) it3.next();
	    String name1 = (String) entry.getKey();
	    Library lib1 = (Library) entry.getValue();
	    // Fail immediately if we have LIB's in 2 that aren't in 1.
	    if (! libNames2.containsKey(name1)) {
		String ID = lib1.getID();
		String VarID = lib1.getVarID();
		if (VarID == null)
		    VarID = "";

		raiseEquivException("Unequal Libraries:  Libraries2: missing Library (ID: " + ID + ", VarID: " + VarID + ").");
	    }
	}
		
	// Both lists have the same names.
	//
	//  Now, try to find matching cases
		
	// Look for tcs that are in 1 that are not in 2.
	Iterator it4 = libNames1.entrySet().iterator();
	while ( it4.hasNext()) {
	    
	    Map.Entry entry1 = (Map.Entry) it4.next();
	    String name1 = (String) entry1.getKey();
	    Library lib1 = (Library) entry1.getValue();
	    Library lib2 =  (Library) libNames2.get(name1);

	    if (lib1 != null && lib2 != null) 
		equiv_Library(lib1, lib2);
	}
    }


    /**
     * compare TestCase objects.
     * <p>
     * @param tc1 a TestCase.
     * @param tc2 a TestCase.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestCase(TestCase tc1, TestCase tc2) throws TestFileException {
	if (! _equiv_Objs(tc1, tc2, "TestCase"))
	    return;

	m_ContextStack.push(tc1);

	// Compare the fields
	equiv_IDs(tc1, tc2, "TestCase");

	equiv_TestCaseDocumentation(tc1.getTCDocumentation(), tc2.getTCDocumentation());
	equiv_TestCaseAttributes(tc1.getTCAttributes(), tc2.getTCAttributes());
	equiv_CodeSet(tc1.getCodeSet(), tc2.getCodeSet());
	equiv_TestCode(tc1.getTestCode(), tc2.getTestCode());
	
	m_ContextStack.pop();
    }

    /**
     * compare Library objects.
     * <p>
     * @param lib1 a Library.
     * @param lib2 a Library.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_Library(Library lib1, Library lib2) throws TestFileException {
	if (! _equiv_Objs(lib1, lib2, "Library"))
	    return;

	m_ContextStack.push(lib1);

	// Compare the fields
	equiv_IDs(lib1, lib2, "Library");

	equiv_LibDocumentation(lib1.getLibDocumentation(), lib2.getLibDocumentation());
	equiv_LibAttributes(lib1.getLibAttributes(), lib2.getLibAttributes());
	equiv_CodeSet(lib1.getCodeSet(), lib2.getCodeSet());

	m_ContextStack.pop();
    }

    //------------------------------------------------------------------------------
    //  "Documentation" Equivalency methods
    //------------------------------------------------------------------------------

    /**
     * compare TestGroupDocumentation objects.
     * <p>
     * @param tgd1 a TestGroupDocumentation.
     * @param tgd2 a TestGroupDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestGroupDocumentation(TestGroupDocumentation tgd1, TestGroupDocumentation tgd2) throws TestFileException {
	if (! _equiv_Objs(tgd1, tgd2, "TestGroupDocumentation"))
	    return;

	m_ContextStack.push(tgd1);

	equiv_Title(tgd1.getTitle(), tgd2.getTitle());
	equiv_Description(tgd1.getDescription(), tgd2.getDescription());
	equiv_TestedPackage(tgd1.getTestedPackage(), tgd2.getTestedPackage());
	equiv_TestedClass(tgd1.getTestedClass(), tgd2.getTestedClass());
	equiv_MemberSig(tgd1.getMemberSig(), tgd2.getMemberSig());

	equiv_AssertionsList(tgd1.getAssertions(), tgd2.getAssertions());
	equiv_DocElemsList(tgd1.getDocElems(), tgd2.getDocElems());
	equiv_AuthorsList(tgd1.getAuthors(), tgd2.getAuthors());
	
	m_ContextStack.pop();
    }
    

    /**
     * compare LibraryDocumentation objects.
     * <p>
     * @param libd1 a LibraryDocumentation.
     * @param libd2 a LibraryDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_LibDocumentation(LibDocumentation libd1, LibDocumentation libd2) throws TestFileException {
	if (! _equiv_Objs(libd1, libd2, "LibDocumentation"))
	    return;

	m_ContextStack.push(libd1);

	equiv_Title(libd1.getTitle(), libd2.getTitle());
	equiv_Description(libd1.getDescription(), libd2.getDescription());
	equiv_AuthorsList(libd1.getAuthors(), libd2.getAuthors());
	
	m_ContextStack.pop();	
    }

    /**
     * compare TestCaseDocumentation objects.
     * <p>
     * @param tcd1 a TestCaseDocumentation.
     * @param tcd2 a TestCaseDocumentation.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestCaseDocumentation(TestCaseDocumentation tcd1, TestCaseDocumentation tcd2) throws TestFileException {
	if (! _equiv_Objs(tcd1, tcd2, "TestCaseDocumentation"))
	    return;

	m_ContextStack.push(tcd1);

	equiv_Title(tcd1.getTitle(), tcd2.getTitle());
	equiv_Description(tcd1.getDescription(), tcd2.getDescription());
	equiv_AuthorsList(tcd1.getAuthors(), tcd2.getAuthors());
	equiv_TestCaseSpecList(tcd1.getTestCaseSpecs(), tcd2.getTestCaseSpecs());
	
	m_ContextStack.pop();	
    }


    /**
     * compare TestCaseSpec objects.
     * <p>
     * @param tcs1 a TestCaseSpec.
     * @param tcs2 a TestCaseSpec.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestCaseSpec(TestCaseSpec tcs1, TestCaseSpec tcs2) throws TestFileException {
	if (! _equiv_Objs(tcs1, tcs2, "TestCaseSpec"))
	    return;

	m_ContextStack.push(tcs1);

	equiv_AssertionsList(tcs1.getAssertions(), tcs2.getAssertions());
	equiv_TestTechnique(tcs1.getTestTechnique(), tcs2.getTestTechnique());
	equiv_MemberSig(tcs1.getMemberSig(), tcs2.getMemberSig());
	equiv_InputList(tcs1.getInputs(), tcs2.getInputs());
	equiv_PreconditionsList(tcs1.getPreconditions(), tcs2.getPreconditions());
	equiv_ExpectedResultValue(tcs1.getExpectedResultValue(), tcs2.getExpectedResultValue());
	equiv_ExpectedResultSideEffectList(tcs1.getExpectedResultSideEffects(), tcs2.getExpectedResultSideEffects());
	equiv_ExpectedResultExceptionList(tcs1.getExpectedResultExceptions(), tcs2.getExpectedResultExceptions());
	equiv_SpecElemList(tcs1.getSpecElems(), tcs2.getSpecElems());

	m_ContextStack.pop();	
    }


    /**
     * Compare Title objects.
     * <p>
     * @param title1 a Title.
     * @param title2 a Title.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Title(String title1, String title2) throws TestFileException {
	if (! _equiv_Objs(title1, title2, "Title"))
	    return;

	if (! title1.equals(title2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Title", title1, title2));
    }


    /**
     * Compare Description objects.
     * <p>
     * @param desc1 a Description.
     * @param desc2 a Description.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Description(String desc1, String desc2) throws TestFileException {
	if (! _equiv_Objs(desc1, desc2, "Description"))
	    return;

	if (! desc1.equals(desc2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Description", desc1, desc2));
    }


    /**
     * Compare TestedPackage objects.
     * <p>
     * @param tp1 a TestedPackage.
     * @param tp2 a TestedPackage.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_TestedPackage(String tp1, String tp2) throws TestFileException {
	if (! _equiv_Objs(tp1, tp2, "TestedPackage"))
	    return;

	if (! tp1.equals(tp2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TestedPackage", tp1, tp2));
    }


    /**
     * Compare TestedClass objects.
     * <p>
     * @param tc1 a TestedClass.
     * @param tc2 a TestedClass.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_TestedClass(String tc1, String tc2) throws TestFileException {
	if (! _equiv_Objs(tc1, tc2, "TestedClass"))
	    return;

	if (! tc1.equals(tc2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TestedClass", tc1, tc2));
    }



    /**
     * Compare MemberSig objects.
     * <p>
     * @param ms1 a MemberSig.
     * @param ms2 a MemberSig.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_MemberSig(String ms1, String ms2) throws TestFileException {
	if (! _equiv_Objs(ms1, ms2, "MemberSig"))
	    return;

	if (! ms1.equals(ms2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "MemberSig", ms1, ms2));
    }




    /**
     * Compare TestTechnique objects.
     * <p>
     * @param tt1 a TestTechnique.
     * @param tt2 a TestTechnique.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_TestTechnique(TestTechnique tt1, TestTechnique tt2) throws TestFileException {
	// TestTechniques are Enum types
	if (tt1 != tt2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TestTechnique", tt1.toString(), tt2.toString()));
    }




    /**
     * Compare Precondition objects.
     * <p>
     * @param pr1 a Precondition.
     * @param pr2 a Precondition.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Precondition(String pr1, String pr2) throws TestFileException {
	if (! _equiv_Objs(pr1, pr2, "Precondition"))
	    return;

	if (! pr1.equals(pr2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Precondition", pr1, pr2));
    }


    /**
     * Compare Input objects.
     * <p>
     * @param in1 a Input.
     * @param in2 a Input.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Input(Input in1, Input in2) throws TestFileException {
	if (! _equiv_Objs(in1, in2, "Input"))
	    return;

	String name1 = in1.getName();
	String name2 = in2.getName();

	if (! _equiv_Objs(name1, name2, "Input-name"))
	    return;

	if (! name1.equals(name2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Input-name", name1, name2));

	String val1 = in1.getValue();
	String val2 = in2.getValue();

	if (! _equiv_Objs(val1, val2, "Input-value"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Input-value", val1, val2));
    }


    /**
     * Compare ExpectedResultValue objects.
     * <p>
     * @param erv1 a ExpectedResultValue.
     * @param erv2 a ExpectedResultValue.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExpectedResultValue(ExpectedResultValue erv1, ExpectedResultValue erv2) throws TestFileException {
	if (! _equiv_Objs(erv1, erv2, "ExpectedResultValue"))
	    return;

	String val1 = erv1.getValue();
	String val2 = erv2.getValue();

	if (! _equiv_Objs(val1, val2, "ExpectedResultValue"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExpectedResultValue", val1, val2));
    }



    /**
     * Compare ExpectedResultSideEffect objects.
     * <p>
     * @param erse1 a ExpectedResultSideEffect.
     * @param erse2 a ExpectedResultSideEffect.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExpectedResultSideEffect(ExpectedResultSideEffect erse1, ExpectedResultSideEffect erse2) throws TestFileException {
	if (! _equiv_Objs(erse1, erse2, "ExpectedResultSideEffect"))
	    return;

	String val1 = erse1.getSideEffect();
	String val2 = erse2.getSideEffect();

	if (! _equiv_Objs(val1, val2, "ExpectedResultSideEffect"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExpectedResultSideEffect", val1, val2));
    }





    /**
     * Compare ExpectedResultException objects.
     * <p>
     * @param ere1 a ExpectedResultException.
     * @param ere2 a ExpectedResultException.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExpectedResultException(ExpectedResultException ere1, ExpectedResultException ere2) throws TestFileException {
	if (! _equiv_Objs(ere1, ere2, "ExpectedResultException"))
	    return;

	String val1 = ere1.getException();
	String val2 = ere2.getException();

	if (! _equiv_Objs(val1, val2, "ExpectedResultException"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExpectedResultException", val1, val2));
    }





    /**
     * Compare DocElem objects.
     * <p>
     * @param de1 a DocElem.
     * @param de2 a DocElem.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_DocElem(DocElem de1, DocElem de2) throws TestFileException {
	if (! _equiv_Objs(de1, de2, "DocElem"))
	    return;

	if (! de1.equals(de2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "DocElem", de1.getName(), de2.getName()));
    }




    /**
     * Compare SpecElem objects.
     * <p>
     * @param se1 a SpecElem.
     * @param se2 a SpecElem.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_SpecElem(SpecElem se1, SpecElem se2) throws TestFileException {
	if (! _equiv_Objs(se1, se2, "SpecElem"))
	    return;

	if (! se1.equals(se2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "SpecElem", se1.getName(), se2.getName()));
    }



    /**
     * Compare Author objects.
     * <p>
     * @param au1 a Author.
     * @param au2 a Author.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Author(String au1, String au2) throws TestFileException {
	if (! _equiv_Objs(au1, au2, "Author"))
	    return;

	if (! au1.equals(au2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Author", au1, au2));
    }



    /**
     * Compare AssertionRef objects.
     * <p>
     * @param ar1 a AssertionRef.
     * @param ar2 a AssertionRef.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_AssertionRef(AssertionRef ar1, AssertionRef ar2) throws TestFileException {
	if (! _equiv_Objs(ar1, ar2, "AssertionRef"))
	    return;

	String val1 = ar1.getRef();
	String val2 = ar2.getRef();

	if (! _equiv_Objs(val1, val2, "AssertionRef"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "AssertionRef", val1, val2));
    }


    /**
     * Compare InlineAssertion objects.
     * <p>
     * @param ia1 a InlineAssertion.
     * @param ia2 a InlineAssertion.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_InlineAssertion(InlineAssertion ia1, InlineAssertion ia2) throws TestFileException {
	if (! _equiv_Objs(ia1, ia2, "InlineAssertion"))
	    return;

	String val1 = ia1.getValue();
	String val2 = ia2.getValue();

	if (! _equiv_Objs(val1, val2, "InlineAssertion"))
	    return;

	if (! val1.equals(val2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineAssertion", val1, val2));
    }



    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 
    //  "List" Equivalency methods
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 


    public void equiv_AssertionsList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (! _equiv_List(al1, al2, "Assertions"))
	    return;
	
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    Assertion as1 = (Assertion) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameAssertion = false;
	    boolean isAssertionRef1 = false;
	    if (as1 instanceof AssertionRef)
		isAssertionRef1 = true;

	    while (it2.hasNext()) {
		Assertion as2 = (Assertion) it2.next();
		boolean isAssertionRef2 = false;
		if (as2 instanceof AssertionRef)
		    isAssertionRef2 = true;

		try {
		    if (isAssertionRef1 && isAssertionRef2) {
			equiv_AssertionRef((AssertionRef) as1, (AssertionRef) as2);
			sameAssertion = true;
			break;
		    } else  if (! isAssertionRef1 &&  !isAssertionRef2) {
			equiv_InlineAssertion((InlineAssertion) as1, (InlineAssertion) as2);
			sameAssertion = true;
			break;
		    }
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}

	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameAssertion)
		raiseEquivException("Unequal Assertion List:  missing assertion1: " + as1 + ".");
	}
    }


    
    private void equiv_DocElemsList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "DocElems"))
	    return;
	
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    DocElem de1 = (DocElem) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameDocElem = false;

	    while (it2.hasNext()) {
		DocElem de2 = (DocElem) it2.next();
		try {
		    equiv_DocElem(de1, de2);
		    sameDocElem = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameDocElem)
		raiseEquivException("Unequal DocElem List:  missing docelem1: " + de1 + ".");
	}
    }


    
    private void equiv_AuthorsList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Authors"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String au1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameAuthor = false;

	    while (it2.hasNext()) {
		String au2 = (String) it2.next();
		try {
		    equiv_Author(au1, au2);
		    sameAuthor = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameAuthor)
		raiseEquivException("Unequal Author List:  missing author1: " + au1 + ".");
	}
    }


    
    private void equiv_InputList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Inputs"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    Input in1 = (Input) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameInput = false;

	    while (it2.hasNext()) {
		Input in2 = (Input) it2.next();
		try {
		    equiv_Input(in1, in2);
		    sameInput = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameInput)
		raiseEquivException("Unequal Input List:  missing input1: " + in1 + ".");
	}
    }


    
    private void equiv_PreconditionsList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Preconditions"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String pr1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean samePre = false;

	    while (it2.hasNext()) {
		String pr2 = (String) it2.next();
		try {
		    equiv_Precondition(pr1, pr2);
		    samePre = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! samePre)
		raiseEquivException("Unequal Preconditions List:  missing precondition1: " + pr1 + ".");
	}
    }




    
    private void equiv_ExpectedResultSideEffectList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "ExpectedResultSideEffect"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    ExpectedResultSideEffect se1 = (ExpectedResultSideEffect) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameExpectedResultSideEffect = false;

	    while (it2.hasNext()) {
		ExpectedResultSideEffect se2 = (ExpectedResultSideEffect) it2.next();
		try {
		    equiv_ExpectedResultSideEffect(se1, se2);
		    sameExpectedResultSideEffect = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameExpectedResultSideEffect)
		raiseEquivException("Unequal ExpectedResultSideEffect List:  missing ExpectedResultSideEffect1: " + se1 + ".");
	}
    }


    
    private void equiv_ExpectedResultExceptionList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "ExpectedResultException"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    ExpectedResultException ex1 = (ExpectedResultException) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameExpectedResultException = false;

	    while (it2.hasNext()) {
		ExpectedResultException ex2 = (ExpectedResultException) it2.next();
		try {
		    equiv_ExpectedResultException(ex1, ex2);
		    sameExpectedResultException = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameExpectedResultException)
		raiseEquivException("Unequal ExpectedResultException List:  missing ExpectedResultException1: " + ex1 + ".");
	}
    }


    
    private void equiv_SpecElemList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "SpecElem"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    SpecElem se1 = (SpecElem) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameSpecElem = false;

	    while (it2.hasNext()) {
		SpecElem se2 = (SpecElem) it2.next();
		try {
		    equiv_SpecElem(se1, se2);
		    sameSpecElem = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameSpecElem)
		raiseEquivException("Unequal SpecElem List:  missing SpecElem1: " + se1 + ".");
	}
    }

    
    private void equiv_TestCaseSpecList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "TestCaseSpec"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    TestCaseSpec tcs1 = (TestCaseSpec) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameTestCaseSpec = false;

	    while (it2.hasNext()) {
		TestCaseSpec tcs2 = (TestCaseSpec) it2.next();
		try {
		    equiv_TestCaseSpec(tcs1, tcs2);
		    sameTestCaseSpec = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameTestCaseSpec)
		raiseEquivException("Unequal TestCaseSpec List:  missing TestCaseSpec1: " + tcs1 + ".");
	}
    }


    //------------------------------------------------------------------------------
    //  "Attributes" Equivalency methods
    //------------------------------------------------------------------------------

    /**
     * compare TestGroupAttributes objects.
     * <p>
     * @param tga1 a TestGroupAttributes.
     * @param tga2 a TestGroupAttributes.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestGroupAttributes(TestGroupAttributes tga1, TestGroupAttributes tga2) throws TestFileException {
	// handle null objs.
	if (! _equiv_Objs(tga1, tga2, "TestGroupAttributes"))
	    return;

	m_ContextStack.push(tga1);

	equiv_Context(tga1.getContext(), tga2.getContext());
	equiv_ExecuteClass(tga1.getExecuteClass(), tga2.getExecuteClass());
	equiv_ExecuteArgs(tga1.getExecuteArgs(), tga2.getExecuteArgs());
	equiv_ExecuteNative(tga1.getExecuteNative(), tga2.getExecuteNative());
	equiv_RMICClasses(tga1.getRMICClasses(), tga2.getRMICClasses());
	equiv_Timeout(tga1.getTimeout(), tga2.getTimeout());
	equiv_RequiredResourceList(tga1.getRequiredResources(), tga2.getRequiredResources());
	equiv_AttrElemList(tga1.getAttrElems(), tga2.getAttrElems());
	equiv_TargetSpecList(tga1.getTargetSpecs(), tga2.getTargetSpecs());
	equiv_KeywordList(tga1.getKeywords(), tga2.getKeywords());
	equiv_RemoteList(tga1.getRemotes(), tga2.getRemotes());
	equiv_SelectIfList(tga1.getSelectIfs(), tga2.getSelectIfs());
	
	m_ContextStack.pop();	
    }



    /**
     * compare LibraryAttributes objects.
     * <p>
     * @param liba1 a LibraryAttributes.
     * @param liba2 a LibraryAttributes.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_LibAttributes(LibAttributes liba1, LibAttributes liba2) throws TestFileException {
	// handle null objs.
	if (! _equiv_Objs(liba1, liba2, "LibAttributes"))
	    return;

	m_ContextStack.push(liba1);
	equiv_RequiredResourceList(liba1.getRequiredResources(), liba2.getRequiredResources());
	equiv_AttrElemList(liba1.getAttrElems(), liba2.getAttrElems());
	equiv_TargetSpecList(liba1.getTargetSpecs(), liba2.getTargetSpecs());
	m_ContextStack.pop();	
    }

    /**
     * compare TestCaseAttributes objects.
     * <p>
     * @param tca1 a TestCaseAttributes.
     * @param tca2 a TestCaseAttributes.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_TestCaseAttributes(TestCaseAttributes tca1, TestCaseAttributes tca2) throws TestFileException {
	// handle null objs.
	if (! _equiv_Objs(tca1, tca2, "TestCaseAttributes"))
	    return;

	m_ContextStack.push(tca1);
	equiv_RequiredResourceList(tca1.getRequiredResources(), tca2.getRequiredResources());
	equiv_AttrElemList(tca1.getAttrElems(), tca2.getAttrElems());
	equiv_TargetSpecList(tca1.getTargetSpecs(), tca2.getTargetSpecs());
	equiv_Timeout(tca1.getTimeout(), tca2.getTimeout());
	m_ContextStack.pop();	
    }




    /**
     * Compare Keyword objects.
     * <p>
     * @param kw1 a Keyword.
     * @param kw2 a Keyword.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Keyword(String kw1, String kw2) throws TestFileException {
	if (! _equiv_Objs(kw1, kw2, "Keyword"))
	    return;

	if (! kw1.equals(kw2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Keyword", kw1, kw2));
    }


    /**
     * Compare Context objects.
     * <p>
     * @param c1 a Context.
     * @param c2 a Context.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Context(String c1, String c2) throws TestFileException {
	if (! _equiv_Objs(c1, c2, "Context"))
	    return;

	if (! c1.equals(c2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Context", c1, c2));
    }



    /**
     * Compare ExecuteClass objects.
     * <p>
     * @param ec1 a ExecuteClass.
     * @param ec2 a ExecuteClass.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExecuteClass(String ec1, String ec2) throws TestFileException {
	if (! _equiv_Objs(ec1, ec2, "ExecuteClass"))
	    return;

	if (! ec1.equals(ec2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExecuteClass", ec1, ec2));
    }



    /**
     * Compare ExecuteArgs objects.
     * <p>
     * @param ea1 a ExecuteArgs.
     * @param ea2 a ExecuteArgs.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExecuteArgs(String ea1, String ea2) throws TestFileException {
	if (! _equiv_Objs(ea1, ea2, "ExecuteArgs"))
	    return;

	if (! ea1.equals(ea2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExecuteArgs", ea1, ea2));
    }


    /**
     * Compare ExecuteNative objects.
     * <p>
     * @param en1 a ExecuteNative.
     * @param en2 a ExecuteNative.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExecuteNative(String en1, String en2) throws TestFileException {
	if (! _equiv_Objs(en1, en2, "ExecuteNative"))
	    return;

	if (! en1.equals(en2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExecuteNative", en1, en2));
    }


    /**
     * Compare Remote objects.
     * <p>
     * @param rm1 a Remote.
     * @param rm2 a Remote.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Remote(String rm1, String rm2) throws TestFileException {
	if (! _equiv_Objs(rm1, rm2, "Remote"))
	    return;

	if (! rm1.equals(rm2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Remote", rm1, rm2));
    }

    /**
     * Compare RMICClasses objects.
     * <p>
     * @param rm1 a RMICClasses.
     * @param rm2 a RMICClasses.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_RMICClasses(String rm1, String rm2) throws TestFileException {
	if (! _equiv_Objs(rm1, rm2, "RMICClasses"))
	    return;

	if (! rm1.equals(rm2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "RMICClasses", rm1, rm2));
    }

    /**
     * Compare SelectIf objects.
     * <p>
     * @param si1 a SelectIf.
     * @param si2 a SelectIf.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_SelectIf(String si1, String si2) throws TestFileException {
	if (! _equiv_Objs(si1, si2, "SelectIf"))
	    return;

	if (! si1.equals(si2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "SelectIf", si1, si2));
    }


    /**
     * Compare Timeout objects.
     * <p>
     * @param to1 a Timeout.
     * @param to2 a Timeout.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Timeout(String to1, String to2) throws TestFileException {
	if (! _equiv_Objs(to1, to2, "Timeout"))
	    return;

	if (! to1.equals(to2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Timeout", to1, to2));
    }




    /**
     * Compare AttrElem objects.
     * <p>
     * @param ae1 a AttrElem.
     * @param ae2 a AttrElem.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_AttrElem(AttrElem ae1, AttrElem ae2) throws TestFileException {
	if (! _equiv_Objs(ae1, ae2, "AttrElem"))
	    return;

	if (! ae1.equals(ae2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "AttrElem", ae1.getName(), ae2.getName()));
    }


    /**
     * Compare RequiredResource objects.
     * <p>
     * @param rr1 a RequiredResource.
     * @param rr2 a RequiredResource.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_RequiredResource(RequiredResource rr1, RequiredResource rr2) throws TestFileException {
	if (! _equiv_Objs(rr1, rr2, "RequiredResource"))
	    return;

	if (! rr1.equals(rr2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "RequiredResource", rr1.getName(), rr2.getName()));
    }


    /**
     * Compare TargetSpec objects.
     * <p>
     * @param ts1 a TargetSpec.
     * @param ts2 a TargetSpec.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_TargetSpec(TargetSpec ts1, TargetSpec ts2) throws TestFileException {
	if (! _equiv_Objs(ts1, ts2, "TargetSpec"))
	    return;
	String id1 = ts1.getID();
	String id2 = ts2.getID();

	if (! id1.equals(id2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TargetSpec", id1, id2));

	int maj1 = ts1.getMajor();
	int maj2 = ts2.getMajor();
	if (maj1 != maj2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.targetspec.vers.uneq", id1, "major", Integer.toString(maj1), 
								Integer.toString(maj2)));


	int min1 = ts1.getMinor();
	int min2 = ts2.getMinor();
	if (min1 != min2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.targetspec.vers.uneq", id1, "minor", Integer.toString(min1), 
								Integer.toString(min2)));
	    

	boolean upmod1 = ts1.isUpperModifierSet();
	boolean upmod2 = ts2.isUpperModifierSet();
	String up1val = (upmod1 ? "true" : "false");
	String up2val = (upmod2 ? "true" : "false");

	if (upmod1 != upmod2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.targetspec.versmod.uneq", id1, "upper-modifier", up1val, up2val));

	boolean lomod1 = ts1.isUpperModifierSet();
	boolean lomod2 = ts2.isUpperModifierSet();
	String lo1val = (lomod1 ? "true" : "false");
	String lo2val = (lomod2 ? "true" : "false");
	if (lomod1 != lomod2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.targetspec.versmod.uneq", id1, "lower-modifier", lo1val, lo2val));
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 
    //  "List" Equivalency methods
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 

   
    public void equiv_RequiredResourceList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "RequiredResource"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    RequiredResource rr1 = (RequiredResource) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameRequiredResource = false;

	    while (it2.hasNext()) {
		RequiredResource rr2 = (RequiredResource) it2.next();
		try {
		    equiv_RequiredResource(rr1, rr2);
		    sameRequiredResource = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameRequiredResource)
		raiseEquivException("Unequal RequiredResource List:  missing RequiredResource1: " + rr1 + ".");
	}
    }

  
    public void equiv_AttrElemList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "AttrElem"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    AttrElem ae1 = (AttrElem) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameAttrElem = false;

	    while (it2.hasNext()) {
		AttrElem ae2 = (AttrElem) it2.next();
		try {
		    equiv_AttrElem(ae1, ae2);
		    sameAttrElem = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAttrElem, fail fast
	    if (! sameAttrElem)
		raiseEquivException("Unequal AttrElem List:  missing AttrElem1: name - " + ae1.getName() + " value - " + ae1.getValue() + ".");
	}
    }

  
    public void equiv_TargetSpecList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "TargetSpec"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    TargetSpec ts1 = (TargetSpec) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameTargetSpec = false;

	    while (it2.hasNext()) {
		TargetSpec ts2 = (TargetSpec) it2.next();
		try {
		    equiv_TargetSpec(ts1, ts2);
		    sameTargetSpec = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameTargetSpec)
		raiseEquivException("Unequal TargetSpec List:  missing TargetSpec1: " + ts1 + ".");
	}
    }

  
    public void equiv_KeywordList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Keyword"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String kw1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameKeyword = false;

	    while (it2.hasNext()) {
		String kw2 = (String) it2.next();
		try {
		    equiv_Keyword(kw1, kw2);
		    sameKeyword = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameKeyword)
		raiseEquivException("Unequal Keyword List:  missing Keyword1: " + kw1 + ".");
	}
    }
  
    public void equiv_SelectIfList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "SelectIf"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String si1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameSelectIf = false;

	    while (it2.hasNext()) {
		String si2 = (String) it2.next();
		try {
		    equiv_SelectIf(si1, si2);
		    sameSelectIf = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameSelectIf)
		raiseEquivException("Unequal Keyword List:  missing Keyword1: " + si1 + ".");
	}
    }


  
    public void equiv_RemoteList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Remote"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String rm1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameRemote = false;

	    while (it2.hasNext()) {
		String rm2 = (String) it2.next();
		try {
		    equiv_Remote(rm1, rm2);
		    sameRemote = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameRemote)
		raiseEquivException("Unequal Remote List:  missing Remote1: " + rm1 + ".");
	}
    }


    //------------------------------------------------------------------------------
    //  "Code" Equivalency methods
    //------------------------------------------------------------------------------

    /**
     * compare CodeSet objects.
     * <p>
     * @param cs1 a CodeSet.
     * @param cs2 a CodeSet.
     * @throws TestFileException for some user-defined error.
     */
    public void equiv_CodeSet(CodeSet cs1, CodeSet cs2) throws TestFileException {
	// handle null objs.
	if (! _equiv_Objs(cs1, cs2, "CodeSet"))
	    return;

	m_ContextStack.push(cs1);

	equiv_DependenciesList(cs1.getDependencies(), cs2.getDependencies());
	equiv_ImportList(cs1.getImports(), cs2.getImports());
	equiv_ExecuteArgs(cs1.getExecuteArgs(), cs2.getExecuteArgs());
	equiv_Context(cs1.getContext(), cs2.getContext());
	equiv_BaseClass(cs1.getBaseClass(), cs2.getBaseClass());
	equiv_ExportList(cs1.getExports(), cs2.getExports());
	equiv_SupportCode(cs1.getSupportCode(), cs2.getSupportCode());
	equiv_SupportClassList(cs1.getSupportClasses(), cs2.getSupportClasses());
	equiv_DataList(cs1.getData(), cs2.getData());
	
	m_ContextStack.pop();	
    }

 
    /**
     * Compare BaseClass objects.
     * <p>
     * @param bc1 a BaseClass.
     * @param bc2 a BaseClass.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_BaseClass(String bc1, String bc2) throws TestFileException {
	if (! _equiv_Objs(bc1, bc2, "BaseClass"))
	    return;

	if (! bc1.equals(bc2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "BaseClass", bc1, bc2));
    }
 
 
    /**
     * Compare Import objects.
     * <p>
     * @param im1 a Import.
     * @param im2 a Import.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Import(String im1, String im2) throws TestFileException {
	if (! _equiv_Objs(im1, im2, "Import"))
	    return;

	if (! im1.equals(im2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Import", im1, im2));
    }

    /**
     * Compare Export objects.
     * <p>
     * @param ex1 a Export.
     * @param ex2 a Export.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_Export(String ex1, String ex2) throws TestFileException {
	if (! _equiv_Objs(ex1, ex2, "Export"))
	    return;

	if (! ex1.equals(ex2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "Export", ex1, ex2));
    }
 
 
    /**
     * Compare SupportCode objects.
     * <p>
     * @param sc1 a SupportCode.
     * @param sc2 a SupportCode.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_SupportCode(SupportCode sc1, SupportCode sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "SupportCode"))
	    return;

	String langtype1 = sc1.getSourceLang();
	String langtype2 = sc2.getSourceLang();
	if (! _equiv_Objs(langtype1, langtype2, "SupportCode-sourcelang"))
	    return;

	if (! langtype1.equals(langtype2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "SupportCode-langtype", langtype1, langtype2));


	String code1 = sc1.getSource();
	String code2 = sc2.getSource();
	if (! _equiv_Objs(code1, code2, "SupportCode-code"))
	    return;

	if (! code1.equals(code2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "SupportCode-code", code1, code2));
    }

 
    /**
     * Compare TestCode objects.
     * <p>
     * @param sc1 a TestCode.
     * @param sc2 a TestCode.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_TestCode(TestCode sc1, TestCode sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "TestCode"))
	    return;

	String langtype1 = sc1.getSourceLang();
	String langtype2 = sc2.getSourceLang();
	if (! _equiv_Objs(langtype1, langtype2, "TestCode-sourcelang"))
	    return;

	if (! langtype1.equals(langtype2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TestCode-langtype", langtype1, langtype2));

	String code1 = sc1.getSource();
	String code2 = sc2.getSource();
	if (! _equiv_Objs(code1, code2, "TestCode-code"))
	    return;

	if (! code1.equals(code2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "TestCode-code", code1, code2));
    }


    /**
     * Compare LibraryDependency objects.
     * <p>
     * @param sc1 a LibraryDependency.
     * @param sc2 a LibraryDependency.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_LibraryDependency(LibraryDependency sc1, LibraryDependency sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "LibraryDependency"))
	    return;


	String id1 = sc1.getID();
	String id2 = sc2.getID();
	if (! _equiv_Objs(id1, id2 , "LibraryDependency-ID"))
	    return;

	if (! id1.equals(id2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "LibraryDependency-ID", id1, id2));
    }



    /**
     * Compare ExternalSupportClass objects.
     * <p>
     * @param sc1 a ExternalSupportClass.
     * @param sc2 a ExternalSupportClass.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExternalSupportClass(ExternalSupportClass sc1, ExternalSupportClass sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "ExternalSupportClass"))
	    return;


	String langtype1 = sc1.getSourceLang();
	String langtype2 = sc2.getSourceLang();
	if (! _equiv_Objs(langtype1, langtype2, "ExternalSupportClass-sourcelang"))
	    return;

	if (! langtype1.equals(langtype2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalSupportClass-langtype", langtype1, langtype2));

	String sourcename1 = sc1.getSourceName();
	String sourcename2 = sc2.getSourceName();
	if (! _equiv_Objs(sourcename1, sourcename2 , "ExternalSupportClass-sourcename"))
	    return;

	if (! sourcename1.equals(sourcename2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalSupportClass-sourcename", sourcename1, sourcename2));


	String id1 = sc1.getClassID();
	String id2 = sc2.getClassID();
	if (! _equiv_Objs(id1, id2 , "ExternalSupportClass-ClassID"))
	    return;

	if (! id1.equals(id2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalSupportClass-ClassID", id1, id2));


    }



    /**
     * Compare InlineSupportClass objects.
     * <p>
     * @param sc1 a InlineSupportClass.
     * @param sc2 a InlineSupportClass.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_InlineSupportClass(InlineSupportClass sc1, InlineSupportClass sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "InlineSupportClass"))
	    return;

	String langtype1 = sc1.getSourceLang();
	String langtype2 = sc2.getSourceLang();
	if (! _equiv_Objs(langtype1, langtype2, "InlineSupportClass-sourcelang"))
	    return;

	if (! langtype1.equals(langtype2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineSupportClass-langtype", langtype1, langtype2));

	String targetname1 = sc1.getTargetName();
	String targetname2 = sc2.getTargetName();
	if (! _equiv_Objs(targetname1, targetname2 , "InlineSupportClass-targetname"))
	    return;

	if (! targetname1.equals(targetname2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineSupportClass-targetname", targetname1, targetname2));


	String id1 = sc1.getClassID();
	String id2 = sc2.getClassID();
	if (! _equiv_Objs(id1, id2 , "InlineSupportClass-ClassID"))
	    return;

	if (! id1.equals(id2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineSupportClass-ClassID", id1, id2));

	String source1 = sc1.getSource();
	String source2 = sc2.getSource();
	if (! _equiv_Objs(source1, source2 , "ExternalSupportClass-sourcename"))
	    return;

	if (! source1.equals(source2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalSupportClass-sourcename", source1, source2));

    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 
    //  "List" Equivalency methods
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 

    public void equiv_DependenciesList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Dependencies"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    CodeDependency dep1 = (CodeDependency) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameDep = false;

	    while (it2.hasNext()) {
		CodeDependency dep2 = (CodeDependency) it2.next();
		try {
		    if (dep1 instanceof LibraryDependency && dep2 instanceof LibraryDependency) {
			equiv_LibraryDependency((LibraryDependency) dep1, (LibraryDependency) dep2);
			sameDep = true;
			break;
		    }
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameDep)
		raiseEquivException("Unequal Dependency List:  missing Dependency1: " + dep1 + ".");
	}
    }

   
    public void equiv_ImportList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Import"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String import1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameImport = false;

	    while (it2.hasNext()) {
		String import2 = (String) it2.next();
		try {
		    equiv_Import(import1, import2);
		    sameImport = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameImport)
		raiseEquivException("Unequal Import List:  missing Import1: " + import1 + ".");
	}
    }


    public void equiv_ExportList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Export"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    String export1 = (String) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameExport = false;

	    while (it2.hasNext()) {
		String export2 = (String) it2.next();
		try {
		    equiv_Export(export1, export2);
		    sameExport = true;
		    break;
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameExport)
		raiseEquivException("Unequal Export List:  missing Export1: " + export1 + ".");
	}
    }



    public void equiv_SupportClassList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "SupportClasses"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    SupportClass sc1 = (SupportClass) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameSupClass = false;

	    while (it2.hasNext()) {
		SupportClass sc2 = (SupportClass) it2.next();
		try {
		    if (sc1 instanceof ExternalSupportClass && sc2 instanceof ExternalSupportClass) {
			equiv_ExternalSupportClass((ExternalSupportClass) sc1, (ExternalSupportClass) sc2);
			sameSupClass = true;
			break;
		    } else if (sc1 instanceof InlineSupportClass && sc2 instanceof InlineSupportClass) {
			equiv_InlineSupportClass((InlineSupportClass) sc1, (InlineSupportClass) sc2);
			sameSupClass = true;
			break;
		    }
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameSupClass)
		raiseEquivException("Unequal SupportClass List:  missing SupportClass1: " + sc1 + ".");
	}
    }


    //------------------------------------------------------------------------------
    //  "Data" Equivalency methods
    //------------------------------------------------------------------------------



    /**
     * Compare ExternalData objects.
     * <p>
     * @param sc1 a ExternalData.
     * @param sc2 a ExternalData.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_ExternalData(ExternalData sc1, ExternalData sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "ExternalData"))
	    return;


	DataType type1 = sc1.getType();
	DataType type2 = sc2.getType();
	String type1Str = (type1.isIOData() ? "IOData" : "Resource");
	String type2Str = (type2.isIOData() ? "IOData" : "Resource");

	if (type1 != type2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalData-type", 
								type1Str, 
								type2Str));

	String sourcename1 = sc1.getSourceName();
	String sourcename2 = sc2.getSourceName();
	if (! _equiv_Objs(sourcename1, sourcename2 , "ExternalData-sourcename"))
	    return;

	if (! sourcename1.equals(sourcename2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "ExternalData-sourcename", sourcename1, sourcename2));
    }



    /**
     * Compare InlineData
     * @param sc1 a InlineData.
     * @param sc2 a InlineData.
     * @throws TestFileException if they are not equal.
     */
    public void equiv_InlineData(InlineData sc1, InlineData sc2) throws TestFileException {
	if (! _equiv_Objs(sc1, sc2, "InlineData"))
	    return;


	DataType type1 = sc1.getType();
	DataType type2 = sc2.getType();
	String type1Str = (type1.isIOData() ? "IOData" : "Resource");
	String type2Str = (type2.isIOData() ? "IOData" : "Resource");

	if (type1 != type2)
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineData-type", 
								type1Str, 
								type2Str));


	String targetname1 = sc1.getTargetName();
	String targetname2 = sc2.getTargetName();
	if (! _equiv_Objs(targetname1, targetname2 , "InlineData-targetname"))
	    return;

	if (! targetname1.equals(targetname2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineData-targetname", targetname1, targetname2));



	String data1 = sc1.getData();
	String data2 = sc2.getData();
	if (! _equiv_Objs(data1, data2 , "InlineData-data"))
	    return;

	if (! data1.equals(data2))
	    raiseEquivException(LibResHandler.getResStr("tools.equiv.genstr.uneq", "InlineData-data", data1, data2));

    }

   

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 
    //  "List" Equivalency methods
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - 
   
    public void equiv_DataList(ArrayList al1, ArrayList al2) throws TestFileException {
	if (!_equiv_List(al1, al2, "Data"))
	    return;
	Iterator it1 = al1.iterator();

	while (it1.hasNext()) {
	    Data da1 = (Data) it1.next();
	    Iterator it2 = al2.iterator();
	    boolean sameData = false;

	    while (it2.hasNext()) {
		Data da2 = (Data) it2.next();

		try {
		    if (da1 instanceof ExternalData && da2 instanceof ExternalData) {
			equiv_ExternalData((ExternalData) da1, (ExternalData) da2);
			sameData = true;
			break;
		    } else if (da1 instanceof InlineData && da2 instanceof InlineData) {
			equiv_InlineData((InlineData) da1, (InlineData) da2);
			sameData = true;
			break;
		    }
		} catch (TestFileException e) {
		    // Found a discrepancy, continue searching.
		}
	    }
	    // if we got thru the loop, and there was no sameAssertion, fail fast
	    if (! sameData)
		raiseEquivException("Unequal Data List:  missing Data1: " + da1 + ".");
	}
    }


}
