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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.filter.redundancy.processors.TestItemLogger;


class Initializer {
    public static final String PREFIX = "filters.";
    public static final String APPLICABILITY_FILTER = PREFIX + "applicability";
    public static final String REDUNDANCY_FILTER = PREFIX + "redundancy";
    public static final String REDUNDANCY_ANALYZER = PREFIX + "redundancy_ananlyzer";
    public static final String EXTERNAL_LIBRARY_LOADER = PREFIX + "external_library.loader";
    public static final String EXTERNAL_LIBRARY_MAP_NAME = PREFIX + "external_library.map";
    public static final String DEPENDENCY_MANAGER = PREFIX + "dependency_manager";

    public static final String EXCLUDE_LIST_NAME = PREFIX + "build_time_jtx";
    public static final String TEST_ITEM_LIST_NAME_IN = PREFIX + "test_item_list.in";
    public static final String TEST_ITEM_LIST_NAME_OUT = PREFIX + "test_item_list.out";

    public static final String SELECTION_LISTENER = PREFIX + "selection.listener";

    private static boolean isFilteringContextInitilized = false;
    private static boolean isTestGroupFilterInitilized = false;
    private static TestItemSelectionListener logger = null;

    /**
     * inititalizes given SelectionContext and static context. The method
     * initializes the static context if the static context is not initialized yet.
     * @param groupContext
     * @throws TestFileException
     */
    public static void init() {
        initFilteringContext();
        initTestGroupFilter();
    }

    private static void initFilteringContext() {
        if (isFilteringContextInitilized) {
            return;
        }
        FilteringContext.setMainApplicablityFilter(createAndInit(APPLICABILITY_FILTER));
        ArrayList list = loadByPrefix(REDUNDANCY_FILTER);
        ApplicabilityFilter[] fs = (ApplicabilityFilter[])list.toArray(new ApplicabilityFilter[list.size()]);
        FilteringContext.setApplicabilityFiltersForRedundancy(fs);
        String name = BuildProperties.getString(EXCLUDE_LIST_NAME);
        if (name != null) {
            FilteringContext.setExcludeListFilter(new GenerationExcludeListFilter(name));
        }
        name = BuildProperties.getString(TEST_ITEM_LIST_NAME_IN);
        if (name != null) {
            FilteringContext.setNewTestsFilter(new TestItemListFilter(name));
        }

        Object o = Utils.createAndInitObject(REDUNDANCY_ANALYZER, true);
        if (o != null) {
            FilteringContext.setRedundancyAnalyzer((RedundancyAnalyzer)o);
        }
        isFilteringContextInitilized = true;
    }

    private static ArrayList loadByPrefix(String prefix) {
        ArrayList list = new ArrayList();
        for (Enumeration e = BuildProperties.propertyNames();
             e.hasMoreElements();) {
            String key = (String)e.nextElement();
            if (key.equals(prefix)
                || key.startsWith(prefix + ".")) {
                list.add(Utils.createAndInitObject(key, false));
            };
        }
        return list;
    }

    private static void initTestGroupFilter() {
        if (isTestGroupFilterInitilized) {
            return;
        }
        // init ExternalLibraryContext

        // init ExternalLibraryProcessors
        
        // register a listener for LibLInker. The listener collect mapping of 
        // the Library ID to a selected Library IR. 
        TestGroupFilter.listeners.registerListener(RuntimeLibraryIRPFactorySpi.getListener());

        registerListeners(TestGroupFilter.listeners, SELECTION_LISTENER);
        String value = BuildProperties.getString(TEST_ITEM_LIST_NAME_OUT);
        if (value != null) {
            logger = new TestItemLogger(value);
            TestGroupFilter.listeners.registerListener(logger);
        }
        TestGroupFilter.manager = (DependencyManager)Utils.createAndInitObject(DEPENDENCY_MANAGER, true);
        TestGroupFilter.manager = (TestGroupFilter.manager == null)
                                  ? new DependencyManager()
                                  : TestGroupFilter.manager;
        LibraryLoader
                loader = (LibraryLoader)Utils.createAndInitObject(EXTERNAL_LIBRARY_LOADER, true);
        if (loader == null) {
            value = BuildProperties.getString(EXTERNAL_LIBRARY_MAP_NAME);
            ClassLoader l = (new Object()).getClass().getClassLoader();
            l = (l == null) ? ClassLoader.getSystemClassLoader() : l;
            InputStream in = l.getResourceAsStream(value);
            if (in == null) {
                throw new IllegalArgumentException("Undefined library loader");
            }
            loader = new LibMapLibraryLoader(in);
        }
        TestGroupFilter.manager.setLibraryLoader(loader);
        TestGroupFilter.selector = new TestItemSelector(TestGroupFilter.manager);
        isTestGroupFilterInitilized = true;
    }

    public static void registerListeners(ListenerGroup impl, String prefix) {
        ArrayList list = loadByPrefix(prefix);
        for (Iterator i = list.iterator(); i.hasNext();
             impl.registerListener((TestItemSelectionListener)i.next()));
    }

    private static ApplicabilityFilter createAndInit(String class_key) {
        return (ApplicabilityFilter)Utils.createAndInitObject(class_key, false);
    }
}
