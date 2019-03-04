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

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportClass;

/**
 * Node TRUE implementation to be used for expression tree creation.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public class NodeTRUE implements FilterExpression {

    public boolean accept(TestGroup tg) {
	return true;
    }

    public boolean accept(Library lib) {
	return true;
    }

    public boolean accept(TestCase tc, TestGroup tg) {
	return true;
    }

    public boolean accept(SupportClass cls, CodeSet cs) {
	return true;
    }


    public FilterExpression getRelevant(TestGroup tg) {
	return this;
    }

    public FilterExpression getRelevant(Library lib) {
	return this;
    }

    public FilterExpression getRelevant(TestCase tc) {
	return this;
    }

    public FilterExpression getRelevant(SupportClass cls) {
	return this;
    }
}
