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

//-----------------------------------------------------------------

/**
 * Node NOT implementation to be used for expression tree creation.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class NodeNOTimpl implements FilterExpression {
    FilterExpression child;

    NodeNOTimpl(FilterExpression _child) {
	child = _child;
    }

    public boolean accept(TestGroup tg) {
	return ! child.accept(tg);
    }

    public boolean accept(Library lib) {
	return ! child.accept(lib);
    }

    public boolean accept(TestCase tc, TestGroup tg) {
	return ! child.accept(tc, tg);
    }

    public boolean accept(SupportClass cls, CodeSet cs) {
	return ! child.accept(cls, cs);
    }


    FilterExpression getRelevant(FilterExpression _child) {
	if ( _child == null )
	    return null;
	if ( _child == child )
	    return this;	// this subtree could be shared between trees
	return new NodeNOTimpl(_child);
    }

    public FilterExpression getRelevant(TestGroup tg) {
	return getRelevant(child.getRelevant(tg));
    }

    public FilterExpression getRelevant(Library lib) {
	return getRelevant(child.getRelevant(lib));
    }

    public FilterExpression getRelevant(TestCase tc) {
	return getRelevant(child.getRelevant(tc));
    }

    public FilterExpression getRelevant(SupportClass cls) {
	return getRelevant(child.getRelevant(cls));
    }
}
//-----------------------------------------------------------------

/**
 * Node AND implementation to be used for expression tree creation.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class NodeANDimpl implements FilterExpression {
    FilterExpression left, right;

    NodeANDimpl(FilterExpression _left, FilterExpression _right) {
	left = _left;
	right = _right;
    }

    public boolean accept(TestGroup tg) {
	return left.accept(tg) && right.accept(tg);
    }

    public boolean accept(Library lib) {
	return left.accept(lib) && right.accept(lib);
    }

    public boolean accept(TestCase tc, TestGroup tg) {
	return left.accept(tc, tg) && right.accept(tc, tg);
    }

    public boolean accept(SupportClass cls, CodeSet cs) {
	return left.accept(cls, cs) && right.accept(cls, cs);
    }


    FilterExpression getClone(FilterExpression _left, FilterExpression _right) {
	return new NodeANDimpl(_left, _right);
    }

    FilterExpression getRelevant(FilterExpression _left, FilterExpression _right) {
	if ( _left == null )
	    return _right;		// _right may be == null also
	if ( _right == null )
	    return _left;
	if ( _left == left && _right == right )
	    return this;		// this subtree could be shared between trees
	return getClone(_left, _right);
    }

    public FilterExpression getRelevant(TestGroup tg) {
	return getRelevant(  left.getRelevant(tg),
			    right.getRelevant(tg)  );
    }

    public FilterExpression getRelevant(Library lib) {
	return getRelevant(  left.getRelevant(lib),
			    right.getRelevant(lib)  );
    }

    public FilterExpression getRelevant(TestCase tc) {
	return getRelevant(  left.getRelevant(tc),
			    right.getRelevant(tc)  );
    }

    public FilterExpression getRelevant(SupportClass cls) {
	return getRelevant(  left.getRelevant(cls),
			    right.getRelevant(cls)  );
    }
}
//-----------------------------------------------------------------

/**
 * Node OR implementation to be used for expression tree creation.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
class NodeORimpl extends NodeANDimpl {
    NodeORimpl(FilterExpression _left, FilterExpression _right) {
	super(_left, _right);
    }

    public boolean accept(TestGroup tg) {
	return left.accept(tg) || right.accept(tg);
    }

    public boolean accept(Library lib) {
	return left.accept(lib) || right.accept(lib);
    }

    public boolean accept(TestCase tc, TestGroup tg) {
	return left.accept(tc, tg) || right.accept(tc, tg);
    }

    public boolean accept(SupportClass cls, CodeSet cs) {
	return left.accept(cls, cs) || right.accept(cls, cs);
    }

    FilterExpression getClone(FilterExpression _left, FilterExpression _right) {
	return new NodeORimpl(_left, _right);
    }

    // getRelevant() methods are inherited
}
