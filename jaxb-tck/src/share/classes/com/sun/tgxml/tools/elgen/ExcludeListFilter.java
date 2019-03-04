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

package com.sun.tgxml.tools.elgen;

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;

/**
 *  Hides exclude list format and structure. Checks presence of testcase or the whole 
 *  test group in the exclude list.
 *  Assumes the TestGroup objects have "sourceDir" AttrElem set.
 */
public interface ExcludeListFilter {

    /** check if the whole test group
     *  is excluded 
     */
    boolean excluded(TestGroup tg) throws IncorrectAttributesException;

    /** check if this testcase
     *  is explicitly excluded
     */
    boolean excluded(TestCase tc) throws IncorrectAttributesException;
}

