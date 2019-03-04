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

/**
 * The class s container for the entry from TestItem list.
 */
public class Entry {
    private String variant;
    private String attributes;
    private String fullName;

    /**
     * creates instance with the given variant name, attributes and full name.
     */
    public Entry(String variant, String attributes, String fullName) {
        this.variant = variant;
        this.attributes = attributes;
        this.fullName = fullName;
    }

    /**
     * returns the variant name.
     */
    public String getVariant() {
        return this.variant;
    }

    /**
     * returns the attributes.
     */
    public String getAttributes() {
        return this.attributes;
    }

    /**
     * returns the full name of the entry.
     */
    public String getFullName() {
        return this.fullName;
    }
}
