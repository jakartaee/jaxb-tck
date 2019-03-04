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

package com.sun.tgxml.tools.filter.processors;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.tools.elgen.ExcludeListFilter;
import com.sun.tgxml.tools.elgen.FilteredOutList;
import com.sun.tgxml.tools.elgen.IncorrectAttributesException;
import com.sun.tgxml.util.IR;

/**
 * Standard implementation of TestFilter provided with filtering engine.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class TestFilterImpl implements TestFilter {

    FilterExpression TGtree;
    FilterExpression TCtree;
    FilterExpression SCtree;

    ExcludeListFilter elFilter;
    FilteredOutList foList;

    ArrayList IDset = new ArrayList();

    TestFilterImpl(FilterExpression tree, ExcludeListFilter elf,
					  FilteredOutList fol) {
	elFilter = elf;
	foList = fol;

	TGtree = makeNonEmptyTree(tree.getRelevant((TestGroup)null));
	TCtree = makeNonEmptyTree(tree.getRelevant((TestCase)null));
	SCtree = makeNonEmptyTree(tree.getRelevant((SupportClass)null));
    }
//---------------------------------------------------------------------------
    
    static FilterExpression makeNonEmptyTree(FilterExpression root) {
	return root == null? new NodeTRUE()
			   : root ;
    }

//---------------------------------------------------------------------------

    void filterOut(TestGroup testGroup) throws FilteringException {
	if (foList != null) {
	    try {
		foList.add(testGroup);
	    } catch (IncorrectAttributesException iae) {
		throw new FilteringException(
				LibResHandler.getResStr("filter.error.testfilter.repFilteredTGroup",
							iae.toString())  );
	    }
	}
    }

    void filterOut(TestCase testCase) throws FilteringException {
	if (foList != null) {
	    try {
		foList.add(testCase);
	    } catch (IncorrectAttributesException iae) {
		throw new FilteringException(
				LibResHandler.getResStr("filter.error.testfilter.repFilteredTCase",
							iae.toString())  );
	    }
	}
    }
//---------------------------------------------------------------------------

    boolean excluded(TestGroup testGroup) throws FilteringException {
	if (elFilter == null)
	    return false;
	try {
	    return elFilter.excluded(testGroup);
	} catch (IncorrectAttributesException iae) {
	    throw new FilteringException(
				LibResHandler.getResStr("filter.error.testfilter.excludedTGroup",
							iae.toString())  );
	}
    }

    boolean excluded(TestCase testCase) throws FilteringException {
	if (elFilter == null)
	    return false;
	try {
	    return elFilter.excluded(testCase);
	} catch (IncorrectAttributesException iae) {
	    throw new FilteringException(
				LibResHandler.getResStr("filter.error.testfilter.excludedTCase",
							iae.toString())  );
	}
    }
//---------------------------------------------------------------------------

    /** 
     *  removes irrelevant testcases and support classes
     */
    public TestGroup strip(TestGroup testGroup) throws FilteringException {
	if ( testGroup.isDeleted() ||  !TGtree.accept(testGroup) || excluded(testGroup) ) {
	    filterOut(testGroup);
	    return null;
	}

	ArrayList tcList = testGroup.getTestCases();
        Hashtable idHash = new Hashtable();

	for (int j = tcList.size()-1; j >= 0; j--) {
	    TestCase tc = (TestCase)tcList.get(j);
	    if ( tc.isDeleted() ||  !TCtree.accept(tc, testGroup) || excluded(tc) ) {
		filterOut(tc);
		tcList.remove(j);
		continue;
	    }
	    String id = IR.getID(tc);
            TestCase tc2 = (TestCase)idHash.get(id);
            if (tc2 == null) {
                idHash.put(id, tc);
            } else {
                // select appropriate testcase variant
                int o1 = tc.order();
                int o2 = tc2.order();

                if (o1 != o2) {
                    if (o1 < o2) {
                        // new found testcase is more appropriate
                        idHash.put(id, tc);
                        tcList.remove(tc2);
                    } else {
                        tcList.remove(tc);
                    }
                } else {
                    throw new FilteringException(
                        "filter cannot select a testcase with id = '" + id + 
                        "' between variants: '" + tc.getVarID()+ 
                        "' and '" + tc2.getVarID() + "'");
                }
            }
	}

	for (int j = tcList.size()-1; j >= 0; j--) {
	    LibraryFilterImpl.stripCodeSet((TestCase)tcList.get(j), SCtree);
        }

	if (tcList.size() == 0) {
	    filterOut(testGroup);
	    return null;
	}

	LibraryFilterImpl.stripCodeSet(testGroup, SCtree);
	return testGroup;
    }
}
//---------------------------------------------------------------------------
//---------------------------------------------------------------------------

/**
 * Standard implementation of LibraryFilter provided with filtering engine.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class LibraryFilterImpl implements LibraryFilter {

    FilterExpression LibTree;
    FilterExpression SCtree;

    LibraryFilterImpl(FilterExpression tree) {
	LibTree = TestFilterImpl.makeNonEmptyTree(tree.getRelevant((Library)null));
	SCtree  = TestFilterImpl.makeNonEmptyTree(tree.getRelevant((SupportClass)null));
    }

    /**
     *  chooses Library implementation and removes support classes
     *  from the chosen library.
     *  Returns null if no library is chosen.
     */
    public Library strip(Library[] libraryImpls) throws FilteringException {
	Library chosenLib = null;

	for (int j = libraryImpls.length-1; j >= 0; j--) {
	    Library lib = libraryImpls[j];
	    if ( LibTree.accept(lib) ) {
		if (chosenLib != null) {
                    String id1 = "";
                    String id2 = "";
                    try { 
                        id1 = lib.getID();
                        id2 = chosenLib.getID();
                    } catch (TestFileException e) {
                       // should never arise
                    }
                    
                    String vn1 = lib.getVarName();
                    String vn2 = chosenLib.getVarName();
                    int o1 = lib.order();
                    int o2 = chosenLib.order();

                    if (id1.equals(id2) && o1 != o2) {
                        if (o1 < o2) {

                            // new lib is more appropriate than already chosen
                            chosenLib = lib;
                        }
                    } else {
                        throw new FilteringException(
                            "filter cannot select an appropriate lib between " +
                            "lib1: '" + id1 + ":" + lib.getVarID() + "' and " +
                            "lib2: '" + id2 + ":" + chosenLib.getVarID() + "'");
                    }
		} else {
		    chosenLib = lib;
                }
	    }         
	}

        if (chosenLib != null) {
            stripCodeSet(chosenLib, SCtree);
        }

	return chosenLib;
    }
//---------------------------------------------------------------------------

    /**
     *  strips support classes for CodeSet of any TestItem
     */
    static void stripCodeSet(TestItem ti, FilterExpression SCtree) throws FilteringException {
	CodeSet cs = ti.getCodeSet();
	if ( cs == null )
	    return;

	ArrayList scList = cs.getSupportClasses();
	if ( scList == null )
	    return;

	ArrayList IDset = new ArrayList();

	for (int j = scList.size()-1; j >= 0; j--) {
	    SupportClass sc = (SupportClass)scList.get(j);
	    if ( !SCtree.accept(sc, cs) ) {
		scList.remove(j);
		continue;
	    }
	    String id = sc.getClassID();
	    if (id==null)
		continue;
	    if (IDset.contains(id)) {
		throw new FilteringException(
				LibResHandler.getResStr("filter.error.libfilter.duplicatedSClass",
							id, IR.getID(ti))  );
	    }
	    IDset.add(id);
	}
    }

}
