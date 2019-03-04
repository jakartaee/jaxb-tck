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

import com.sun.tgxml.tools.elgen.ExcludeListFilter;
import com.sun.tgxml.tools.elgen.FilteredOutList;
import com.sun.tgxml.tools.filter.processors.FilterExpression;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.FilteringException;
import com.sun.tgxml.tools.filter.processors.NodeTRUE;
import com.sun.tgxml.tools.filter.processors.TestFilter;

/**
 * It is wrapper for enhanced filtering scheme. It designed for the TestGroup 
 * filtering only. 
 */
public class Factory extends FilterFactory {
    
    
    public FilterExpression cfgRead(String configuration) throws FilteringException {
        return new NodeTRUE();
    }

    private static TestGroupFilter filter;
    
    public synchronized TestFilter getTestFilter(String configuration,
                                                 ExcludeListFilter elf,
                                                 FilteredOutList fol)
        throws FilteringException {
        Initializer.init();
        if (filter == null) {
            filter = new TestGroupFilter();
        }
        return filter;
    }
}
