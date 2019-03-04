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

package com.sun.tgxml.tools.filter.testitemdiff;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import com.sun.tgxml.tools.elgen.ExcludeEntry;
import com.sun.tgxml.tools.elgen.ExcludeList;

/**
 * The class is responsible for parsing of the TestItem list. 
 * This implementation parses the TestItem list in the following format:
 * &lt;path&gt;/foo[:fred][[bar[:fred]]]   attributes
 * <p>
 * where<br>
 *  foo: Test Group or External Library ID;<br>
 *  bar: Test Case or Internal Library ID;<br>
 * fred: variant name.
 */
public class TestItemListParser {
    
    /**
     * reads and parses a TestItem list from the given Reader.
     */
    public TestItemList parse(Reader in) throws IOException { 
        ExcludeList list = new ExcludeList(in);
        TestItemList retVal = new TestItemList();
        for (Iterator i = list.getAllEntries(); i.hasNext();) {
            ExcludeEntry entry = (ExcludeEntry)i.next();
            StringBuffer key = new StringBuffer(entry.getDirectory());
            key.append('/');
            String tGroup = entry.getTestGroupID();
            String tCase = entry.getTestCaseID();
            int pos;
            String variant = null;
            if ((tCase == null) 
                && ((pos = tGroup.lastIndexOf(':')) > 0)) {
                variant = tGroup.substring(pos + 1);
                tGroup = tGroup.substring(0, pos);
            }
            key.append(tGroup);
            if (tCase != null) {
                pos = tCase.lastIndexOf(':');
                if (pos > 0) {
                    variant = tCase.substring(pos + 1);
                    tCase = tCase.substring(0, pos);
                }
                key.append('[' + tCase + ']');
                //key.append(']');
            }
            StringBuffer attributes = new StringBuffer();
            addNonNull(attributes, entry.getBugIDs());
            addNonNull(attributes, entry.getKeywords());
            addNonNull(attributes, entry.getComments());
            retVal.putEntry(key.toString(), new Entry(variant, attributes.toString(),
                                                      entry.toString()));
        }
        return retVal;
    }
    
    private void addNonNull(StringBuffer buff, Object o) {
        if (o != null) {
            buff.append(' ');
            buff.append(o.toString());
        }
    }
}
