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

import java.util.Iterator;
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
import com.sun.tgxml.tools.filter.FilterUtil;
import com.sun.tgxml.tools.filter.libutil.LibSelectionInfo;
import com.sun.tgxml.tools.filter.libutil.TestGroupLibsInfo;
import com.sun.tgxml.util.IR;

/**
 * Standard implementation of TestFilter2 provided with filtering engine.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class TestFilterImpl2 implements TestFilter2 {

    FilterExpression TGtree;
    FilterExpression TCtree;
    FilterExpression SCtree;

    ExcludeListFilter elFilter;
    FilteredOutList foList;

    ArrayList IDset = new ArrayList();

    TestFilterImpl2(FilterExpression tree, ExcludeListFilter elf,
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
     *  Removes irrelevant testcases and support classes
     *  Provides attribute filtering and filtering by dependencies.
     */
    public TestGroup strip(TestGroup testGroup, TestGroupLibsInfo libsInfo)
            throws FilteringException {


	if ( testGroup.isDeleted() || !TGtree.accept(testGroup) ||
                  excluded(testGroup) || !accept(testGroup, libsInfo)) {
	    filterOut(testGroup);
	    return null;
	}


	ArrayList tcList = testGroup.getTestCases();
        Hashtable idHash = new Hashtable();

	if (tcList == null || tcList.size() == 0) {
	    filterOut(testGroup);
	    return null;
	}

	for (int j = tcList.size()-1; j >= 0; j--) {
	    TestCase tc = (TestCase)tcList.get(j);
	    if ( tc.isDeleted() || !TCtree.accept(tc, testGroup) ||
                      excluded(tc) || !accept(tc, libsInfo)) {
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
                TestCase selectedTC = 
                        (TestCase)FilterUtil.selectVariant(tc, tc2);
                if (tc == selectedTC) {
                    // new found testcase is more appropriate
                    idHash.put(id, tc);
                    tcList.remove(tc2);
                } else {
                    tcList.remove(tc);
                }
            }
	}

	if (tcList.size() == 0) {
	    filterOut(testGroup);
	    return null;
	}

	for (int j = tcList.size()-1; j >= 0; j--) {
	    FilterUtil.stripCodeSet((TestCase)tcList.get(j), SCtree);
        }


	FilterUtil.stripCodeSet(testGroup, SCtree);
	return testGroup;
    }

    /**
     *  returns true if all libraries of passed TestItem are accepted
     *  @exception FilteringException if TestItem depends on unknown 
     *             library or it's impossible to detect its dependencies
     */
    public boolean accept(TestItem ti, TestGroupLibsInfo libsInfo) 
            throws FilteringException {

        ArrayList depLibs = null;
        try {
            depLibs = IR.getDependentLibs(ti);
        } catch (TestFileException e) {
            throw new FilteringException("Cannot detect dependencies for " +
                 "TestItem: " + ti + " with ID=" + IR.getID(ti));
        }
        for (Iterator it = depLibs.iterator(); it.hasNext();) {
            String libID = (String)it.next();
            if (!libsInfo.isAccepted(libID)) {
                if (libsInfo.isKnown(libID)) {
                    return false; // ti depends on rejected library
                } else  {
                    throw new FilteringException("TestItem: " + ti + " with ID="
                         + IR.getID(ti) + " depends on unknown lib: " + libID);
                }
            }
        }
        return true;
    }

}

