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

import com.sun.tgxml.tjtf.api.tests.TestItem;

/**
 * The class represents union of platforms, represented by given
 * ApplicabilityFilters.
 */
public class CombinedApplicabilityFilter implements ApplicabilityFilter {
    private ApplicabilityFilter[] filters = null;
    private String name;
    
    /**
     * creates CombinedApplicabilityFilter with the given ApplicabilityFilter
     * list.
     */
    public CombinedApplicabilityFilter(ApplicabilityFilter[] filters) {
        this.filters = filters;
        if ((filters == null) || (filters.length == 0)) {
            throw new IllegalArgumentException("filters array is null or has "
                                               + "zero length: " + filters);
        }
        StringBuffer buff = new StringBuffer("CombinedApplicabilityFilter([");
        buff.append(filters[0]);
        for (int i = 1; i <filters.length; i++) {
            buff.append(", ");
            buff.append(filters[i].getName());
        }
        buff.append("])");
        name = buff.toString();
    }

    /**
     * returns true if at least one ApplicabilityFilter from union accepts 
     * TestItem.
     */
    public boolean accept(TestItem item) {
        for (int i = 0; i < filters.length; i++) {
            if (filters[i].accept(item)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
