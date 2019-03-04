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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestFactory;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.tjtf.api.tests.VariableTestItem;

/**
 * The class represents TestItem selection by dependencies mechanism.
 * This class performs basic work on detecting of TestItems, applicable
 * for target platforms.
 * <p>
 * The class contains methods, which allow obtain TestItems selected on some
 * platform, represented by ApplicabilityFilter. Additionally methods for
 * selection of TestItems also added to the class.
 */
public class TestItemSelector {

    /**
     * this instance is returned if all variants of requested TestCase is rejected.
     */
    public static final VariableTestItem REJECTED;

    /**
     * this instance is returned if all variants of requested Library is rejected.
     */
    public static final Library REJECTED_LIBRARY;

    private DependencyManager manager;

    static {
        try {
            REJECTED = TestFactory.createTestCase(
                    "TEST_CASE_REJECTED", null, null, null, null, null);
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can not create REJECTED TestItem");
        }
        try {
            REJECTED_LIBRARY = TestFactory.createLibrary("LIBRARY_REJECTED", null,null,null);
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can not create REJECTED_LIBRARY TestItem");
        }
    }

    /**
     * creates instance of the  with the given DependencyManager.
     * @param manager the given DependencyManager.
     * It is used to resolve dependencies from TestItems.
     */
    public TestItemSelector(DependencyManager manager) {
        this.manager = manager;
    }



    /**
     * returns TestCase from the given TestGroup with given ID,
     * which is accepted by the given
     * ApplicabilityFilter. <br>
     * A TestCase is accepted if the TestCase and
     * all depended Libraries are accepted by ApplicabilityFilter. The
     * depended libraries includes all recursively depended libraries.
     */
    public TestCase getAcceptableTestCase(ApplicabilityFilter filter, TestGroup tGroup,
                                          String itemID)
        throws ItemNotFoundException, TestFileException {
        ArrayList tCases = tGroup.getTestCase(itemID);
        String key = TestItemListFilter.getGlobalID((TestItem)tCases.get(0),
                                                    false);
        VariantList list = new VariantList(key, tCases,
                                           VariantList.TEST_CASE);
        return (TestCase)getAcceptableVariant(filter, list, true);
    }

    /**
     * returns Library with given ID dependent on the given TestItem,
     * which is accepted by the given ApplicabilityFilter.
     * <br>
     * A Library is accepted if the Library and
     * all depended Libraries are accepted by ApplicabilityFilter. The
     * depended libraries includes all recursively depended libraries.
     */
    public Library getAcceptableLibrary(ApplicabilityFilter filter, TestItem from, String libID)
        throws TestFileException {
        TestItem retVal = getAcceptableVariant(filter, manager.resolveDependency(from, libID), true);
        return (retVal == REJECTED) ? REJECTED_LIBRARY : (Library)retVal;
    }


    public boolean isAcceptedTestGroup(ApplicabilityFilter filter,
                                       TestGroup tGroup) throws TestFileException {
        return (filter.accept(tGroup)
                && (allDependenciesSatisfied(filter, tGroup)) != FALSE);
    }

    /**
     * selects the given TestCase with all dependent libraries.
     */
    public void selectTestCase(ApplicabilityFilter filter, TestItemSelectionListener listener,
                               TestCase tCase)
        throws TestFileException {
        selectDependentLibraries(filter, listener, tCase);
        listener.testCaseSelected(tCase);
    }

    /**
     * selects the given TestGroup with all dependent libraries.
     */
    public void selectTestGroup(ApplicabilityFilter filter, TestItemSelectionListener listener,
                                TestGroup tGroup)
        throws TestFileException {
        selectDependentLibraries(filter, listener, tGroup);
        listener.testGroupSelected(tGroup);
    }

    /**
     * compare two test variants.
     */
    protected int compareTestVariant(TestVariant left, TestVariant right) {
        return (left.order() < right.order()) ? -1 : ((left.order() > right.order()) ? 1 : 0);
    }

    // private methods and fields.

    private Map cache = Collections.synchronizedMap(new WeakHashMap());

    private static String getKey(ApplicabilityFilter filter, String itemID) {
        return filter.getName() + "->" + itemID;
    }

    private static final int TRUE     = 0;
    private static final int FALSE    = 1;
    private static final int IN_PROCESS  = 2;

    private VariableTestItem getAcceptableVariant(ApplicabilityFilter filter,
                                                  VariantList variants,
                                                  boolean isInProcessCached)
        throws ItemNotFoundException, TestFileException {
        String key = getKey(filter, variants.getID());
        VariableTestItem retVal = (VariableTestItem)cache.get(key);
        int depStatus = 0;
        if (retVal != null) {
            return retVal;
        }

        if (isBeingProcessed(variants.getID())) {
            return TEST_ITEM_IN_PROCESS; // this is a cyclic dependency
        }
        try {
            beginProcessing(variants.getID());
            ArrayList variantsList = variants.getTestVariants();
            VariableTestItem[] varArray = new VariableTestItem[variantsList.size()];

            if (varArray.length == 1) {
                varArray[0] = (VariableTestItem)variantsList.get(0);
            } else {
                // sort variants so that the best one comes first
                Arrays.sort(variantsList.toArray(varArray),
                            new Comparator() {
                                public int compare(Object o1, Object o2) {
                                    return compareTestVariant((TestVariant)o1, (TestVariant)o2);
                                }
                            });
            }

            for (int i = 0; i < varArray.length; i++) {
                if (filter.accept(varArray[i])) {
                    depStatus = allDependenciesSatisfied(filter, varArray[i]);
                    // the first one found is the best
                    if (depStatus == FALSE) {
                        continue;
                    } else if ((depStatus == TRUE) || isInProcessCached) {
                        cache.put(key, varArray[i]);
                        return varArray[i];
                    } else {
                        return TEST_ITEM_IN_PROCESS;
                    }
                }
            }
            cache.put(key, REJECTED);
            return REJECTED; // no suitable variants found
        } finally {
            endProcessing(variants.getID());
        }
    }


    private HashSet processed = new HashSet();

    // hash of hashes keyed by worker threads
    // three methods to work with it

    private void beginProcessing(String itemID) {
        // mark libID as being in process by the current thread
        processed.add(getProcessingID(itemID));
    }

    private static String getProcessingID(String itemID) {
        // it have to be processed by current Thread
        return Thread.currentThread().hashCode() + "->" + itemID;
    }
    private void endProcessing(String itemID) {
        // remove libID from the hash
        processed.remove(getProcessingID(itemID));
    }
    private boolean isBeingProcessed(String itemID) {
        // check for presence in the hash
        return processed.contains(getProcessingID(itemID));
    }

    private static final VariableTestItem TEST_ITEM_IN_PROCESS;

    static {
        try {
            TEST_ITEM_IN_PROCESS = TestFactory.createTestCase(
                    "TEST_ITEM_IN_PROCESS", null, null, null, null, null);
        } catch (TestFileException e) {
            throw new IllegalArgumentException("TestFileException is thrown: " + e);
        }
    }


    private int allDependenciesSatisfied(ApplicabilityFilter filter,
                                         TestItem item)
        throws ItemNotFoundException, TestFileException {
        int retVal = TRUE;
        String libID = null;
        for (Iterator depIt = manager.getDependencies(item); depIt.hasNext();) {
            libID = (String) (depIt.next());
            VariantList list = manager.resolveDependency(item, libID);
            TestItem var = getAcceptableVariant(filter, list, false);
            if (var == REJECTED) {
                StatusLogger.reportRejectByDependency(TestItemListFilter.getGlobalID(item, true),
                                                      list.getID());
                return FALSE;
            } else if (var == TEST_ITEM_IN_PROCESS) {
                retVal = IN_PROCESS;
            }
        }
        return retVal;
    }

    private HashSet selected = new HashSet();

    private void selectDependentLibraries(ApplicabilityFilter filter,
                                          TestItemSelectionListener listener,
                                          TestItem item) throws TestFileException {
        for (Iterator depIt = manager.getDependencies(item); depIt.hasNext();) {
            String libID = (String)depIt.next();
            VariantList list = manager.resolveDependency(item, libID);
            String key = list.getID();
            if (selected.contains(key)) {
                continue;
            }
            selected.add(key);
            Library lib = getAcceptableLibrary(filter, item, libID);
            selectDependentLibraries(filter, listener, lib);
            switch (list.getType()) {
            case VariantList.EXTERNAL_LIBRARY: listener.externalLibrarySelected(lib); break;
            case VariantList.INLINE_LIBRARY: listener.internalLibrarySelected(lib); break;
            default:
                throw new IllegalArgumentException("The dependency of type: "
                                                   + list.getType() + " isunsuported");
            }
        }
    }
}
