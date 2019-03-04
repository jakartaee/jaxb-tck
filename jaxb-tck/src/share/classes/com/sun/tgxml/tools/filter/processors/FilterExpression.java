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
 * Interface of nodes in the expression tree in filtering component.
 * The same node serves as root of the tree and so it represents all the tree.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public interface FilterExpression {

    /**
     * Evaluates expression against TestGroup object and determines 
     * if the whole TestGroup satisfies the expression represented by the node.
     *
     * @param      tGroup TestGroup object to be checked.
     * @return     true if the TestGroup object satisfies the expression.
     */
    public boolean accept(TestGroup tGroup);

    /**
     * Evaluates expression against Library object and determines 
     * if this Library implementation satisfies the expression represented by the node.
     *
     * @param      lib Library object to be checked.
     * @return     true if the Library object satisfies the expression.
     */
    public boolean accept(Library lib);

    /**
     * Evaluates expression against TestCase object and determines 
     * if this TestCase satisfies the expression represented by the node.
     *
     * @param      tCase  TestCase object to be checked.
     * @param      tGroup TestGroup which the target TestCase belongs to.
     * @return     true if the TestCase satisfies the expression.
     */
    public boolean accept(TestCase tCase, TestGroup tGroup);

    /**
     * Evaluates expression against SupportClass object and determines 
     * if this SupportClass satisfies the expression represented by the node.
     *
     * @param      sClass  SupportClass object to be checked.
     * @param      cSet    CodeSet which the target SupportClass belongs to.
     * @return     true if the SupportClass satisfies the expression.
     */
    public boolean accept(SupportClass sClass, CodeSet cSet);


    /**
     * Produces, if needed, expression tree reduced for the TestGroup target object type. 
     * Source expression tree is represented by this node. 
     *
     * @param      tGroup    value of the parameter is not processed.
     * @return     reference to the root of tree reperesenting the reduced expression.
     */
    public FilterExpression getRelevant(TestGroup tGroup);

    /**
     * Produces, if needed, expression tree reduced for the Library target object type. 
     * Source expression tree is represented by this node. 
     *
     * @param      lib    value of the parameter is not processed.
     * @return     reference to the root of tree reperesenting the reduced expression.
     */
    public FilterExpression getRelevant(Library lib);

    /**
     * Produces, if needed, expression tree reduced for the TestCase target object type. 
     * Source expression tree is represented by this node. 
     *
     * @param      tCase    value of the parameter is not processed.
     * @return     reference to the root of tree reperesenting the reduced expression.
     */
    public FilterExpression getRelevant(TestCase tCase);

    /**
     * Produces, if needed, expression tree reduced for the SupportClass target object type. 
     * Source expression tree is represented by this node. 
     *
     * @param      sClass    value of the parameter is not processed.
     * @return     reference to the root of tree reperesenting the reduced expression.
     */
    public FilterExpression getRelevant(SupportClass sClass);
}
