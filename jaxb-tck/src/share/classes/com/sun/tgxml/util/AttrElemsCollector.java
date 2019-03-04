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

package com.sun.tgxml.util;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;


/**
 * This class is designed as an utility to collect AttrElem values of the 
 * specified names. Initially when new instance is created each name maps
 * an empty list. After the calls of <code>processTestItem()</code> 
 * Each name will map a list of values of AttrElems with such name of 
 * all passed TestItems.
 */
public class AttrElemsCollector {

    protected ArrayList names = null;
    protected HashMap  map = null;

    /**
     * Creates AttrElemsCollector for the specified name list.
     * @param names  list of AttrElem names whose values should be collected.
     */
    public AttrElemsCollector(ArrayList names) {
        this.names = names;
        map = new HashMap();
    }

    /**
     * Looks through AttrElems of the passed TestItem and
     * collects values of the specified names. 
     */
    public void processTestItem(TestItem ti) {
        if (names == null || ti == null)
            return;

        for (Iterator it = names.iterator(); it.hasNext();) {
            String name = (String)(it.next());
            ArrayList newFound = IR.getAllAttrElems(name, ti);            
            if (newFound != null) {
                for (Iterator it2 = newFound.iterator(); it2.hasNext();) {
                    addValue(name, (String)(it2.next()));
                }
            }
        }

    }

    /**
     * Sets new list of AttrElem names whose values should be collected 
     */
    public void setNameList(ArrayList newList) {
        names = newList;
    }

    /**
     * Returns list of AttrElem names whose values should be collected 
     */
    public ArrayList getNameList() {
        return names;
    }

    /**
     * Adds new name to the name list. 
     */
    public void addName(String n) {
        if (names == null) {
            names = new ArrayList();
        }
        if (!names.contains(n)) {
            names.add(n);
        }
    }

    /**
     * Removes name from the name list. 
     */
    public void removeName(String n) {
        if (names != null) {
            names.remove(n);
        }
    }

    /**
     * Adds new value to the collection for the name.
     */ 
    public void addValue(String name, String value) {
        ArrayList list = (ArrayList)map.get(name);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(value);
        map.put(name, list);
    }

    /**
     * Returns the value list collected for the name.
     */ 
    public ArrayList getValues(String name) {
        return (ArrayList)map.get(name);
    }

    /**
     * Clears the value collection for the name.
     */ 
    public void clearValues(String name) {
        map.put(name, null);
    }



    /**
     * Clears collected information
     */
    public void clear() {
        map = new HashMap();
    }

    /**
     * Returns map where keys are names and where name maps
     * either an ArrayList of collected values or null 
     * if no AttrElem with such name is found.
     */
    public HashMap getMap() {
        return map;
    }

    /**
     * Removes duplicate values from collected list for each name from
     * the noDups.
     */
    public void removeDuplicatesFor(ArrayList noDups) {
        if (noDups != null && noDups.size() > 0) {
            for (Iterator it = noDups.iterator(); it.hasNext();) {
                String name = (String)(it.next());
                ArrayList values = (ArrayList)(map.get(name));
                if (values != null && values.size() > 1) {
                     ArrayList newValues = new ArrayList();
                     for (Iterator v = newValues.iterator(); v.hasNext();) {
                          String nv = (String)(v.next());
                          if (!newValues.contains(nv)) {
                               newValues.add(nv);
                          }
                     }
                     map.put(name, newValues);
                }
            }
        }
    }

}

