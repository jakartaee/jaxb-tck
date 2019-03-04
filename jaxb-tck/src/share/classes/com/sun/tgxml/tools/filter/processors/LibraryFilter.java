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

package com.sun.tgxml.tools.filter.processors;

import com.sun.tgxml.tjtf.api.tests.Library;

/**
 * Interface of Library filtering component for build tools.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public interface LibraryFilter {
    /** 
     * chooses Library implementation and removes support classes
     *
     * @param      libraryImpls  array of Library implementations to be processed. 
     *                           One impls is chosen and it is modified while the filtering.
     * @return     Library reference to chosen and cleaned Library object.
     * @throws     FilteringException to diagnose problems discovered by plugin or standard
     *                                Library filtering implementation.
     */
    public Library strip(Library[] libraryImpls) throws FilteringException;
}
