/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sun.tgxml.util.AttrElemsCollector;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.testgen.api.TestDescriptionIR;


/**
 * Utility class that collects tck specific TestDescription entries.
 */
public class TDEntriesCollector extends AttrElemsCollector {

    public static final String TD_ENTRIES_PROP_NAME = 
            "testgen.html.TestDescriptionExtensions";


    protected ArrayList noDupValues = null;
    protected ArrayList singleEntry = null;

    public TDEntriesCollector() {
        this(detectTCKSepcificTDEntries());
    }

    /**
     * Creates Collector with the specified entry name list.
     *
     * @param entryNames list of tck specific TestDescription entry names
     */
    public TDEntriesCollector(ArrayList entryNames) {
        this(entryNames, null, null);
    }

    /**
     * Creates Collector with the specified list of entry names.
     *
     * @param entryNames  list of tck specific TestDescription entry names
     * @param noDupValues names for which no duplicate values are allowed
     * @param singleEntry names for which multiple TD entries are not allowed
     */
    public TDEntriesCollector(ArrayList entryNames, ArrayList noDupValues, 
             ArrayList singleEntry) {

        super(entryNames);
        this.noDupValues = noDupValues;
        this.singleEntry = singleEntry;
    }


    /**
     * Detects list of tck specific TestDescription entries from 
     * BuildProperties.
     */
    public static ArrayList detectTCKSepcificTDEntries() {
        String prop = BuildProperties.getString(TD_ENTRIES_PROP_NAME);
        if (prop != null) {
            ArrayList list = new ArrayList();
            StringTokenizer st = new StringTokenizer(prop);
            while (st.hasMoreTokens()) {
                String entry = st.nextToken();
                if (!list.contains(entry)) {
                    list.add(entry);
                }
            }
            return list;
        } else {
            return null;
        }
    }

    /**
     * Sets entry list for which no duplicate values are allowed.
     */
    public void setNoDuplicateValues(ArrayList entries) {
        this.noDupValues = entries;
    }

    /**
     * Returns entry names for which no duplicate values are allowed.
     */
    public ArrayList getNoDuplicateValues() {
        return noDupValues;
    }

    /**
     * Adds field to the entry name list for which no duplicate values 
     * are allowed.
     */
    public void addNoDuplicateValues(String field) {
        if (noDupValues == null) {
            noDupValues = new ArrayList(1);
            noDupValues.add(field);
        } else if (!noDupValues.contains(field)){
            noDupValues.add(field);
        }
    }
    

    /**
     * Sets entry names for which multiple TD entries are not allowed.
     */
    public void setSingleEntry(ArrayList entries) {
        this.singleEntry = entries;
    }
    

    /**
     * Returns entry names for which multiple TD entries are not allowed.
     */
    public ArrayList getSingleEntry() {
        return singleEntry;
    }

    /**
     * Adds field to the entry name list for which multiple TD entries 
     * are not allowed.
     */
    public void addSingleEntry(String field) {
        if (singleEntry == null) {
            singleEntry = new ArrayList(1);
            singleEntry.add(field);
        } else if (!singleEntry.contains(field)){
            singleEntry.add(field);
        }
    }


    /**
     * Adds entries to the TestDescription table if needed
     */
    public void putIntoTD(TestDescriptionIR tdIR) {
        if (names != null && map != null) {
            removeDuplicatesFor(noDupValues);
            for (Iterator it = names.iterator(); it.hasNext();) {
                String name = (String)(it.next());
                ArrayList list = (ArrayList)(map.get(name));
                Object tdValue = createTDValue(name, list);
                if (tdValue == null) {
                    // do nothing
                } else if (tdValue instanceof String) {
                    tdIR.set(name, (String)tdValue);
                } else if (tdValue instanceof String[]) {
                    tdIR.set(name, (String[])tdValue);
                }
            }
        }
    }

    /**
     * Creates an TestDescription entry to be put into TestDescriptionIR
     * by the specified name and values.
     * Returned object must be either null, or an instance of String,
     * or an instance of String[].
     */
    protected Object createTDValue(String name, ArrayList values) {
        if (values != null && values.size() > 0) {
            if (singleEntry != null && singleEntry.contains(name)) {
                // join values
                StringBuffer sb = new StringBuffer();
                for (Iterator it = values.iterator(); it.hasNext();) {
                    sb.append( ((String)it.next()).trim() + " ");
                }
                return sb.toString().trim();
            } else {
                return (String[])values.toArray(new String[0]);
            }
        } 
        return null;
    }

}

