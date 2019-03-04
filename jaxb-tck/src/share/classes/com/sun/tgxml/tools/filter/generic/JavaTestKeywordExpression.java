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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.sun.javatest.Keywords;
import com.sun.javatest.Keywords.Fault;

/**
 * The class implements the <code>KeywordsFilter</code> interface and
 * defines constructor with String[] parameter. The parameter define
 * the JavaTest a keywords expression. The class accepts keywords that
 * satisfy the JavaTest keyword expression.
 */
public class JavaTestKeywordExpression implements KeywordsFilter {
    private Keywords keywords;

    /**
     * creates the filter with the given JavaTest expression.
     * @param expr String, which represents the expression in the
     * JavaTest keywords format.
     */
    public JavaTestKeywordExpression(String expr) {
        try {
            this.keywords = Keywords.create(Keywords.EXPR, expr);
        } catch (Fault e) {
            throw new IllegalArgumentException("Can not parse JavaTest keywords: " + expr);
        }
    }

    /**
     * creates the filter with the given JavaTest expression.
     * @param args String[], which represents the expression in the
     * JavaTest keywords format. The elements of the array are
     * combined with space as delimiter.
     */
    public JavaTestKeywordExpression(String[] args) {
        this(combine(args));
    }

    public boolean acceptKeywords(ArrayList keywords) {
        HashSet set = new HashSet();
        for (Iterator e = keywords.iterator(); e.hasNext(); set.add(e.next()));
        return this.keywords.accepts(set);
    }

    private static String combine(String[] args) {
        StringBuffer retVal = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            retVal.append(' ');
            retVal.append(args[i]);
        }
        return retVal.substring(1);
    }
}
