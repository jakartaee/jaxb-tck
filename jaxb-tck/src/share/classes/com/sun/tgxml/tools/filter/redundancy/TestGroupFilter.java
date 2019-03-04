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

package com.sun.tgxml.tools.filter.redundancy;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tools.filter.processors.FilteringException;
import com.sun.tgxml.tools.filter.processors.TestFilter;

/**
 * It is main class for the new filtering mechanism. It implements
 * TestFilter interface and select out TestItems according new
 * scheme. It defines TestItem which should be selected using
 * TestItemSelector class.
 */
public class TestGroupFilter implements TestFilter {

    static ListenerGroup listeners = new ListenerGroup();
    static DependencyManager manager;
    static TestItemSelector selector;

    public TestGroup strip(TestGroup testGroup) throws FilteringException {
        ApplicabilityFilter combined = FilteringContext.getCombinedApplicabilityFilter();
        final ArrayList libs = new ArrayList();
        TestItemSelectionListener libCollector = new TestItemSelectionListener.EmptyListener() {
            public void internalLibrarySelected(Library item) throws TestFileException {
                if (!libs.contains(item)) {
                    libs.add(item);
                }
            }
        };
        ListenerGroup copy = (ListenerGroup)listeners.clone();
        copy.registerListener(libCollector);

        try {
            if (!selector.isAcceptedTestGroup(combined, testGroup)) {
                return null; //deselected
            }

            // now need to work on individual TestCases
            ArrayList testCaseIDs = testGroup.getTestCaseIDs();
            ArrayList testCases = new ArrayList();
            for (Iterator caseIt = testCaseIDs.iterator(); caseIt.hasNext();) {
                String id = (String)caseIt.next();
                TestCase current = selector.getAcceptableTestCase(combined, testGroup, id);
                if ((current != TestItemSelector.REJECTED)
                    && ! TestGroupFilter.isRedundant(testGroup, id, selector)) {
                    selector.selectTestCase(combined, copy, current);
                    testCases.add(current);
                }
            }
            if (!testCases.isEmpty()) {
                selector.selectTestGroup(combined, copy, testGroup);
                testGroup.setTestCases(testCases);
                testGroup.setLibraries(libs);
                return testGroup;
            } else {
                StatusLogger.reportRejectEmptyTestGroup(TestItemListFilter.getGlobalID(testGroup, false));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilteringException("Exception is thrown:" + e);
        } finally {
            listeners.flush();
        }
    }


    public static boolean isRedundant(TestGroup enclGroup,
                                       String testCaseID,
                                       TestItemSelector selector)
        throws TestFileException {
        ApplicabilityFilter main = FilteringContext.getCombinedApplicabilityFilter();
        ApplicabilityFilter[] redundency = FilteringContext.getApplicablityFiltersForRedundancy();
        RedundancyAnalyzer ra = FilteringContext.getRedundancyAnalyzer();
        if (redundency == null ||
            redundency.length == 0) {
            return false;
        }
        for (int i = 0; i < redundency.length; i++) {
            if (ra.isRedundant(enclGroup, testCaseID,
                               selector, main, redundency[i])) {
                String log_id = TestItemListFilter.getGlobalID(enclGroup, false);
                StatusLogger.reportRejectRedundancy(log_id + "[" + testCaseID + "]",
                                                    redundency[i].getName());
                return true;
            }
        }
        return false;
    }
}
