/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.dependence;

/**
 * The <code>LibraryDependency</code> is a pair of a library and a list of 
 * library identifiers that the library directly depends on.
 * 
 */
public class LibraryDependency extends StringArray {

    /**
     * The library identifier that depens on the libraries from the list.
     */
    protected final String libID; 

    /**
     * Constructs a library dependency with an empty list and specified
     * library identifier.
     * @param libID
     */
    public LibraryDependency(String libID) {
        this.libID = libID;
    }

    /**
     * Returns a library identifier of the list.
     */
    public String getLibID() {
        return libID;
    }
}
