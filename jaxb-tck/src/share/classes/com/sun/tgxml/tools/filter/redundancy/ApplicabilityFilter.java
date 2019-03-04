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
 * The interface for applicability filters. The applicability filter accepts 
 * TestItems using attributes and does not parse dependencies. 
 * Each filter represents some Java platform.
 */
public interface ApplicabilityFilter {

    /**
     * returns true if the given TestItem is acceptable for platform.
     * @param item a given TestItem. An instances of the ApplicabilityFilter 
     * should not modify the TestItem.
     */
    public boolean accept(TestItem item);
    
    /**
     * returns name of the filter. This name should be unique in current VM.
     */
    public String getName();
}
