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

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;

/**
 * The class is responsible for formatting and reporting of
 * messages.<p>
 * The differences are grouped by a type and sorted by a TestItem name. 
 * The groups are reported in the following order:
 * <ul>
 *     <li> Missing TestItems
 *     <li> New TestItems
 *     <li> TestItems with a changed variant names. The report output contains 
 *          old and new variant name.
 *     <li> TestItems with a changed attributes. The report output contains old
 *          and new attributes name. 
 * </ul>
 */
public class TestItemDiffReporter {
    private TreeMap lostItems = new TreeMap();
    private TreeMap newItems = new TreeMap();
    private TreeMap changedVariants = new TreeMap();
    private TreeMap changedAttributes = new TreeMap();

    /**
     * the method is invoked if a TestItem is missing in the new log file.
     */ 
    protected void reportMissing(String key, Entry oldEntry) {
        this.lostItems.put(key, oldEntry.getFullName());
    }
    
    /**
     * the method is invoked if a new TestItem is found in the new
     * log file and new TestItem reporting is turned on.
     */
    protected void reportNew(String key, Entry entry) {
        this.newItems.put(key, entry.getFullName());
    }

    /**
     * the method is invoked if a variant name is changed in the new
     * list and variant changes reporting is turned on.
     */
    protected void reportChangedVariant(String key, Entry oldEntry, Entry newEntry) {
        this.changedVariants.put(key, key + ": Old Variant Name = '" + oldEntry.getVariant() 
                                      + "' New Variant Name = '" + newEntry.getVariant() + "'");
    }

    /**
     * the method is invoked if a attributes are changed in the new
     * list and attribute changes reporting is turned on.
     */
    protected void reportChangedAttributes(String key, Entry oldEntry,
                                           Entry newEntry) {
        this.changedAttributes.put(key,
                                   key + ": Old Attributes = '"
                                   + oldEntry.getAttributes() 
                                   + "' New Attributes = '"
                                   + newEntry.getAttributes() + "'");
        
    }

    /**
     * the method is invoked when the comparison is completed.
     */
    protected void finalizeReport() throws TestFileException {
        StringBuffer summary = new StringBuffer();
  
        logMessages("missing", lostItems, summary);
        logMessages("added", newItems, summary);
        logMessages("changedvariants", changedVariants, summary);
        logMessages("changedattributes", changedAttributes, summary);
        logMessage(summary.toString());
    }

    /**
     * logs the messages from the given map.
     */
    private void logMessages(String type, Map map, StringBuffer summary)
        throws TestFileException {
        if (map.size() > 0) {
            logMessage(LibResHandler.getResStr("testitemlogdiff.report."
                                        + type + ".header"));
            for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                logMessage(map.get(i.next()).toString());
            }
            logMessage("\n\n");
            summary.append(LibResHandler.getResStr("testitemlogdiff.report."
                                                   + type + ".summary", 
                                                   Integer.toString(map.size())));
        }
    }

    /**
     * logs the formatted messages. The all messages are logged via this method.
     */
    protected void logMessage(String data) throws TestFileException {
    };
}
