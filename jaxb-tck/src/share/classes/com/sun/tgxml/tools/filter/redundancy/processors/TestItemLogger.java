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

package com.sun.tgxml.tools.filter.redundancy.processors;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tools.filter.redundancy.TestItemListFilter;
import com.sun.tgxml.tools.filter.redundancy.TestItemSelectionListener;


/**
 * The class logs all selected TestItems to the file. 
 * The file is appended only and all existing information is unchanged.
 * This implementation is multi-process safe and allow to log iformation 
 * from different processes simultinously.<p>
 * The TestItems are logged in format of the generation exclude list and 
 * can be used for new test filter later.
 */
public class TestItemLogger extends ExternalLibraryLogger {
    
    /**
     * creates the instance with the given file name.
     */
    public TestItemLogger(String name) {
        super(name);
    }
    
    /**
     * creates the instance with the given argument list. The fist mandatory 
     * argument defines name of the output file. 
     * The rest of arguments are ignored.
     * @param args argument list.
     */
    public TestItemLogger(String args[]) {
        this(args[0]);
    }

    /**
     * locks buffer and log selected TestItem using <code>logTestItem</code>
     */
    protected void logTestItem(TestItem item, String type) throws TestFileException {
        buff.append(TestItemListFilter.getGlobalID(item, true));
        buff.append(" type=");
        buff.append(type);
        buff.append(";\n");
    }

    public void externalLibrarySelected(Library item) throws TestFileException {
        synchronized (buff) {
            logTestItem(item, TestItemSelectionListener.EXTERNAL_LIBRARY);
        }
    }
    public void internalLibrarySelected(Library item) throws TestFileException {
        synchronized (buff) {
            logTestItem(item, TestItemSelectionListener.INLINE_LIBRARY);
        }
    }

    public void testCaseSelected(TestCase item) throws TestFileException {
        synchronized (buff) {
            logTestItem(item, TestItemSelectionListener.TEST_CASE);
        }
    }

    public void testGroupSelected(TestGroup tGroup) throws TestFileException {
        synchronized (buff) {
            logTestItem(tGroup, TestItemSelectionListener.TEST_GROUP);
        }
    }
}
