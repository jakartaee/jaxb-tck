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

package com.sun.tgxml.tools.filter.generic;

import java.util.HashSet;

/**
 * The class implements the Value filter, that accepts only values
 * from the defined set. The set is defined during the ValueSet
 * creation.
 */
public class ValueSet implements ValueFilter {
    private HashSet set = new HashSet();

    /**
     * creates the filter with empty set, which always reject values.
     */
    public ValueSet() {
    }

    /**
     * creates filter that accepts only values from the given set.
     * @param args given set.
     */
    public ValueSet(String[] args) {
        for (int i = 0; i < args.length; set.add(args[i++].trim()));
    }


    public boolean acceptValue(String value) {
        return set.contains(value.trim());
    }

    public String toString() {
        return "ValueSet(" + set + ")";
    }
}
