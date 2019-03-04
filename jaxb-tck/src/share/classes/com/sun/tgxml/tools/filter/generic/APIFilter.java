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

/**
 *  The interface is used for checking TestedPackage, TestedClass and MemberSig
 *  test selection attributes. The instance should be registered via
 *  <code>registerAPIFilter(APIFilter&nbsp;filter)</code>.
 */
public interface APIFilter {

    /**
     * The method is invoked to check status of the TestedPackage test selection attribute.
     * @param testedPackage value of the TestedPackage tag.
     * @return true if the TestedPackage is accepted and false otherwise.
     */
    public boolean acceptTestedPackage(String testedPackage);

    /**
     * The method is invoked to check status of the TestedClass test selection attribute.
     * @param tPack value of the TestedPackage tag.
     * @param tClass value of the TestedClass tag
     * @return true if the TestedClass is accepted and false otherwise.
     */
    public boolean acceptTestedClass(String tPack, String tClass);

    /**
     * The method is invoked to check status of the MemberSig test selection attribute.
     * @param tPack value of the TestedPackage tag.
     * @param tClass value of the TestedClass tag
     * @param memberSig value of the MemberSig tag
     * @return true if the MemberSig is accepted and false otherwise.
     */
    public boolean acceptMemberSig(String tPack, String tClass, String memberSig);
}
