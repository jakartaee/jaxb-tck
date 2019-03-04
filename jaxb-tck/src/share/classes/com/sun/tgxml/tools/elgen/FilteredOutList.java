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
 *  Hides format and structure of list where information about filtered out tests is gathered. 
 *  Assumes the TestGroup objects have "sourceDir" AttrElem set.
 */
public interface FilteredOutList {
    /** 
     *   adds one more testcase to the list
     */
    void add(TestCase tc) throws IncorrectAttributesException;
    /** 
     *   adds one more test group to the list
     */
    void add(TestGroup tg) throws IncorrectAttributesException;
}
