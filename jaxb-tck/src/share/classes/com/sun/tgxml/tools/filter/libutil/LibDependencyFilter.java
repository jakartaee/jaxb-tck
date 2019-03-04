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

package com.sun.tgxml.tools.filter.libutil;

import com.sun.tgxml.tools.filter.processors.FilteringException;

/**
 * A filter of library bundle.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public interface LibDependencyFilter {

    /**
     * Provides filtering of passed library bundle by dependencies.
     * It does not provide filtering by attributes. It's supposed, that
     * all libraries in the bundle are already filtered by attributes.
     * <p>
     * The library bundle is a map. It maps libID to the variants list.
     * The purpose of this method is to select an appropriate variant
     * for each libID in the library bundle.
     * This method returns a map with the same set of keys (libIDs).
     * Returned LibMap object maps libID to accepted variant or null if 
     * library is rejected.
     * <p>
     * This methods rejects variants that depends on alreay rejected libraries.
     * The variant is accepted(not rejected) only if all libraries
     * it depends on are accepted.
     * If no variant can be accepted then the Library is considered rejected
     * along with all the Libraries that depend on it.
     * If two or more library variants are accepted, then
     * variant having the lowest VarOrder is accepted.     
     * <p>
     * Loop dependency causes to an error.
     * <p>
     * Finding unknown libID in dependency list also causes to an error.
     *
     * @param  libBundle  a bundle of libraries to be filtered
     * @param  filtered  information about already accepted/rejected libraries
     *         (null if no libraries are filtered yet).
     * @return a LibMap of filtered libraries. The number of libIDs in the
     *         returned map and in the libBundle map is exactly the same.
     *         If libID is contained in both filtered and libBundle
     *         bundles it will be treated as not filtered yet.
     * @exception FilteringException if loop dependency or unknown libID is 
     *            found out.
     */
    public LibMap filter(VariantsMap libBundle, LibSelectionInfo filtered)
            throws FilteringException;


}
