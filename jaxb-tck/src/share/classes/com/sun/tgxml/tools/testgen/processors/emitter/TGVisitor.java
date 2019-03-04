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

package com.sun.tgxml.tools.testgen.processors.emitter;

import java.util.Iterator;
import java.util.ArrayList;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.Library;

/**
 * Utitlity class containing code that could used by different emitters
 */
public class TGVisitor {

    protected TestGroup testGroup = null;

    protected static final int EXE_ARGS = 0;
    protected static final int CONTEXT = 1;

    public static final String EXE_ARGS_ATTRELEM = "externalLibsExecuteArgs";
    public static final String CONTEXT_ATTRELEM = "externalLibsContext";


    public TGVisitor(TestGroup tg) {
        this.testGroup = tg;
    }

    /**
     * Returns resulting ExecuteArgs for the TestGroup.
     */
    public String getExecuteArgs() {
        ArrayList list = new ArrayList();

        // executeArgs defined in TestGroupAttributes
        try {
            String v = testGroup.getTGAttributes().getExecuteArgs();
            if (v != null) {
                list.add(v);
            }
        } catch (NullPointerException e) {
        }

        // executeArgs defined in external libraries
        String extLibsValue = IR.getAttrElem(EXE_ARGS_ATTRELEM, testGroup);
        if (extLibsValue != null) {
            list.add(extLibsValue);
        }

        // executeArgs defined in TestGroupAttributes
        String testItemValue = collectCodeSetValues(EXE_ARGS);
        if (testItemValue != null) {
            list.add(testItemValue);
        }

        return joinList(list);

    }

    /**
     * Returns resulting Contex for the TestGroup.
     */
    public String getContext() {
        ArrayList list = new ArrayList();

        // context defined in TestGroupAttributes
        try {
            String v = testGroup.getTGAttributes().getContext();
            if (v != null) {
                list.add(v);
            }
        } catch (NullPointerException e) {
        }

        // context defined in external libraries
        String extLibsValue = IR.getAttrElem(CONTEXT_ATTRELEM, testGroup);
        if (extLibsValue != null) {
            list.add(extLibsValue);
        }

        // context defined in TestGroupAttributes
        String testItemValue = collectCodeSetValues(CONTEXT);
        if (testItemValue != null) {
            list.add(testItemValue);
        }

        return joinList(list);
    }

    protected String joinList(ArrayList list) {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = list.iterator(); it.hasNext();) {
            String value = (String)it.next();
            if (value != null) {
                sb.append(value);
                sb.append(" ");
            }
        }
        return sb.length() > 0 ? sb.toString().trim() : null;
    }

    /**
     * Walks through TestItem list TestGroup has and collects values
     * of CodeSet element. (The element is detected by passed type).
     *
     * @param type - either EXE_ARGS or CONTEXT
     * @return String where all collected values are joined together
     */
    protected String collectCodeSetValues(int type) {
        StringBuffer sb = new StringBuffer();
        ArrayList tiList = getAllTestItems();
        for (Iterator it = tiList.iterator(); it.hasNext();) {
            String value = getCodeSetValue(type, (TestItem)it.next());
            if (value != null) {
                sb.append(value);
                sb.append(" ");
            }
        }
        return sb.length() > 0 ? sb.toString().trim() : null;
    }

    protected String getCodeSetValue(int type, TestItem ti) {
        switch (type) {
            case EXE_ARGS: return IR.getExecuteArgs(ti);
            case CONTEXT: return IR.getContext(ti);
            default: return null;
        }
    }

    /**
     * Returns TestItem list TestGroup has. (TestGroup itself, TestCases
     * and InlineLibraries
     */
    protected ArrayList getAllTestItems() {
         ArrayList list = new ArrayList();
         list.add(testGroup);
         ArrayList testcases = testGroup.getTestCases();
         if (testcases != null) {
             list.addAll(testcases);
         }
         ArrayList libs = testGroup.getLibraries();
         if (libs != null) {
             list.addAll(libs);
         }
         return list;
    }
} 
