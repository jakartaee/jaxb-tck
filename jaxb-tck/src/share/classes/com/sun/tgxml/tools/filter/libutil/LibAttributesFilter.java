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

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tools.filter.processors.FilteringException;

/**
 * A class that filters library by its attribute set.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public interface LibAttributesFilter {

    /**
     * Provides filtering of passed library bundle by attributes.
     * <p>
     * The method returns VariantsMap that contains only stripped libraries.
     * So if all library variants are stripped libID in the returned VariantsMap
     * will map to an empty ArrayList.
     * <p>Note: this method does not check dependencies and does not
     * provide variant selection, only attributes check.
     *
     * @param  libBundle  a bundle of libraries to be filtered
     * @return an bundle of stripped libraries.
     * @exception FilteringException if passed libraries cannot be filtered
     * 
     */
    public VariantsMap filter(VariantsMap libBundle) throws FilteringException;

    /**
     * Provides attribute filtering of passed library.
     * Methods returns null if library attributes does not satisfy
     * the selection criteria.
     * Otherwise it returns stripped Library (removes irrelevant 
     * support classes from Library CodeSet).
     *
     * @param  lib  a library to be filtered
     * @return null if library is rejected or stripped library.
     * @exception FilteringException if passed library cannot be filtered
     */
    public Library filter(Library lib) throws FilteringException;


}
