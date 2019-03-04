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
import java.util.HashSet;
import java.util.Iterator;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestGroupComponent;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.util.IR;

/**
 * The interface represents redundancy checking scheme.
 */
public interface RedundancyAnalyzer {
    
    /**
     * checks that given TestCase is redundant.
     * @param enclGroup TestGroup, which contains given TestCase
     * @param testCaseID ID of the given TestCase
     * @param selector TestItemSelector, which calculates accepted by dependencies TestItems.
     * @param applicability represents target platform 
     * @param redundancy represents platform, tests for which are redundant
     * @return true if the TestCase is redundant, false otherwise.
     */
    public boolean isRedundant(TestGroup enclGroup, 
                               String testCaseID,
                               TestItemSelector selector,
                               ApplicabilityFilter applicability,
                               ApplicabilityFilter redundancy) throws TestFileException;
    /**
     * The default implementation that assumes that TestCase is redundant if TestCase and 
     * all recursively dependent libraries have the same variant name.
     */
    public static final RedundancyAnalyzer DEFAULT = new DefaultRedundancyAnalyzer();

}


class DefaultRedundancyAnalyzer implements RedundancyAnalyzer {

        public synchronized boolean isRedundant(TestGroup encl, 
                                                String testCaseID,
                                                TestItemSelector selector,
                                                ApplicabilityFilter applicability, 
                                                ApplicabilityFilter redundancy) throws TestFileException {
            TestItem item = selector.getAcceptableTestCase(applicability, encl, testCaseID);
            try {
                TestItem redundantCase = (selector.isAcceptedTestGroup(redundancy, encl) 
                                          ? selector.getAcceptableTestCase(redundancy, encl, testCaseID)
                                          : TestItemSelector.REJECTED);
                return equals(item, redundantCase)
                       && allLibsAreRedundant(selector, item, applicability, redundancy);
            } finally {
                done.clear();
            }
        }
        
        protected boolean equals(TestItem left, TestItem right) throws TestFileException {
            if (!left.getID().equals(right.getID())) {
                return false;
            }

            if ((left instanceof TestGroupComponent) != (right instanceof TestGroupComponent)) {
                return false;
            }

            if ((left instanceof TestVariant) && (right instanceof TestVariant)) {
                String lV = ((TestVariant)left).getVarName();
                String rV = ((TestVariant)right).getVarName();
                return (lV == null) ? (rV == null) : lV.equals(rV);
            } else {
                return !((left instanceof TestVariant) || (right instanceof TestVariant));
            }
        }

        private HashSet done = new HashSet();
        
        private boolean allLibsAreRedundant(TestItemSelector selector, TestItem item, 
                ApplicabilityFilter f1, ApplicabilityFilter f2) throws TestFileException {
            ArrayList dependencies = IR.getDependentLibs(item);
            if (done.contains(item.getID())) {
                return true;
            }
            done.add(item.getID());
            if (item instanceof TestCase) { // need to add TestGroup dependencies as well
                ArrayList groupDependencies = IR.getDependentLibs(
                        ((TestCase)item).getTestGroup());
                dependencies.addAll(groupDependencies);
            }
            for (Iterator depIt = dependencies.iterator(); depIt.hasNext();) {
                String libID = (String)(depIt.next());
                Library l1 = selector.getAcceptableLibrary(f1, item, libID);
                Library l2 = selector.getAcceptableLibrary(f2, item, libID);
                if (!equals(l1, l2) || ! allLibsAreRedundant(selector, l1, f1, f2)) {
                    return false;
                }
            }
            return true;
        }
}
