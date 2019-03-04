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

import java.util.HashMap;
import java.util.Iterator;


/**
 * The class represents the TestItem list.
 */ 
public class TestItemList {
    private HashMap entries = new HashMap();

    /**
     * puts Entry with the given key.
     */
    public void putEntry(String key, Entry entry) {
        entries.put(key, entry);
    }

    /**
     * returns Iterator of keys for all entries putted to the list.
     */
    public Iterator getKeysIterator() {
        return entries.keySet().iterator();
    }

    /**
     * returns Entry instance associated with the given key or null if
     * there are no Entry.
     */
    public Entry getEntry(String key) {
        return (Entry)entries.get(key);
    }
}
