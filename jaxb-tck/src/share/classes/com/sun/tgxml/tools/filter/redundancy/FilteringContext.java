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

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;

/**
 * The class is simple container for used filters. It contains access methods
 * and setting methods for the following filters.
 * <ul>
 *   <li> required main applicability filter. This filter should be set.
 *   <li> optional array of redundancy filters. These filters are usually
 *     main applicability filters, for other platforms. If the filters are not
 *     set the redundancy checking is not performed.
 *   <li> optional exclude list filter. It rejects TestItems defined in the
 *     exclude list. If the filter is not set, the exclude list assumed to be
 *     empty.
 *   <li> optional new test filter. It rejects TestItems which is not defined
 *     in the existing TestItem list. It the filter is not set, new test
 *     rejection is not performed.
 *   <li> optional RedundancyAnalyzer implements algorithm of the redundancy
 *     checking. If it is not set, the RedundancyAnalyzer.DEFAULT is used.
 * </ul>
 *  All setting methods does not allow null as parameter.
 */
public class FilteringContext {

    /**
     * processes TestItem using set main applicability filter, exclude list
     * filter and new tests filter.
     */
    private static ApplicabilityFilter combinedFilter = new ApplicabilityFilter() {
    	
    	private boolean isDeletedTestItem(TestItem item) {
    		if (item instanceof TestCase) {
    			TestCase tc = (TestCase)item;
    			return tc.isDeleted();
    		}
    		else if (item instanceof TestGroup ) {
    			TestGroup tg = (TestGroup)item;
    			return tg.isDeleted();
    		}
    		else {
    			return false;
    		}

    	}

        public boolean accept(TestItem item) {
            String id;
            try {
                id = TestItemListFilter.getGlobalID(item, true);
            } catch (TestFileException e) {
                id = "Unknown";
            }
            
            if (isDeletedTestItem(item)) {
            	StatusLogger.reportRejectDeleted(id);
            	return false;
            }

            if ((mainApplicablityFilter == null)
                 || !mainApplicablityFilter.accept(item)) {
                StatusLogger.reportRejectByAttribute(id);
                return false;
            }

            if ((excludeListFilter != null) && !excludeListFilter.accept(item)) {
                StatusLogger.reportRejectExcluded(id);
                return false;
            }

            if ((newTestsFilter != null) && !(item instanceof Library)
                && !newTestsFilter.accept(item)) {
                StatusLogger.reportRejectNewTest(id);
                return false;
            }
            return true;
        }

        private String name;

        public String getName() {
            if (name == null) {
                name = "combinedFilter(" + getFilterName(mainApplicablityFilter)
                       + ", " + getFilterName(excludeListFilter)
                       + ", " + getFilterName(newTestsFilter) +")";
             }
            return name;
       }
    };

    private static String getFilterName(ApplicabilityFilter filter) {
        return (filter == null) ? null : filter.getName();
    }

    /**
     * returns filter, which performs all logic required by main applicability
     * filter, exclude list filter and new tests filter.
     */
    public static ApplicabilityFilter getCombinedApplicabilityFilter() {
        return combinedFilter;
    }

    public static ApplicabilityFilter[] getApplicablityFiltersForRedundancy() {
        return applicabilityFiltersForRedundancy;
    }

    public static ApplicabilityFilter getExcludeListFilter() {
        return excludeListFilter;
    }

    public static ApplicabilityFilter getNewTestsFilter() {
        return newTestsFilter;
    }

    static void setMainApplicablityFilter(ApplicabilityFilter filter) {
        checkNull(filter);
        mainApplicablityFilter = filter;
    }


    static void setApplicabilityFiltersForRedundancy(ApplicabilityFilter[] filters) {
        applicabilityFiltersForRedundancy = filters;
    }

    static void setExcludeListFilter(ApplicabilityFilter filter) {
        checkNull(filter);
        excludeListFilter = filter;
    }

    static void setNewTestsFilter(ApplicabilityFilter filter) {
        checkNull(filter);
        newTestsFilter = filter;
    }

    static void setRedundancyAnalyzer(RedundancyAnalyzer ra) {
        checkNull(ra);
        redundancyAnalyzer = ra;
    }

    public static RedundancyAnalyzer getRedundancyAnalyzer() {
        return redundancyAnalyzer;
    }

    public static final ApplicabilityFilter TRUE = new ApplicabilityFilter() {

        public boolean accept(TestItem item) {
            return true;
        }

        public String getName() {
            return "TRUE";
        }
    };

    /**
     * throws IllegalArgumentException if the given argument is null
     */
    private static void checkNull(Object filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The argument should be non-null");
        }
    }

    // by default everything is selected
    private static ApplicabilityFilter mainApplicablityFilter = TRUE;
    private static ApplicabilityFilter excludeListFilter = TRUE;
    private static ApplicabilityFilter newTestsFilter = TRUE;

    // by default no redundancy checks are done
    private static ApplicabilityFilter[] applicabilityFiltersForRedundancy = null;

    // by default the exact match is considered redundant
    private static RedundancyAnalyzer redundancyAnalyzer = RedundancyAnalyzer.DEFAULT;
}
