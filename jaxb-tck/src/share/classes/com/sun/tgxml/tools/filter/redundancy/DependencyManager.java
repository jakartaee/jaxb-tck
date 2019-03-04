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

package com.sun.tgxml.tools.filter.redundancy;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestGroupComponent;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.util.IR;

/**
 * The class manages dependencies from TestItems to Libraries.
 */
public class DependencyManager {
    protected LibraryLoader loader;
    
    /**
     * sets LibraryLoader used for external libraries resolving.
     * @param loader
     */
    public void setLibraryLoader(LibraryLoader loader) {
        this.loader = loader;
    }
    
    /**
     * extracts existing dependencies list from the given TestItem.
     * @param item
     * @return list of String objects, which defines ID of dependent TestItems.
     * @throws TestFileException
     */ 
    public Iterator getDependencies(TestItem item) throws TestFileException {
        return IR.getDependentLibs(item).iterator();
    }
    
    /**
     * resolves dependency from current TestItem with given name. All TestItems
     * variant visible from current TestItem are loaded and returned. 
     * Currently only dependency on libraries are supported by B&amp;I.
     * The method implements the following scheme for dependency resolving:
     * <ul>
     *  <li>If the current TestItem is part of enclosing TestGroup and inline 
     *    libraries with the given ID exist in enclosing TestGroup, the all 
     *    inline libraries with the given ID are returned
     *  <li> Otherwise, if there are external libraries with the given ID, then they are returned.
     *  <li> Otherwise, ItemNotFoundException is thrown.
     * </ul>
     * @param current current TestItem
     * @param refID ID of dependent TestIem. Currently B&amp;I supports only
     * references to libraries.
     * @return all visible TestItems (currently all they are Libraries)
     */
    public VariantList resolveDependency(TestItem current, String refID) 
        throws TestFileException, ItemNotFoundException {
        TestGroup tg = null;
        if (current instanceof TestGroup) {
            tg = (TestGroup)current;
        }
        if (current instanceof TestGroupComponent) {
            tg = ((TestGroupComponent)current).getTestGroup();
        }
        if (tg != null) {
            ArrayList variantsList;
            try {
                variantsList = tg.getLibrary(refID);
            } catch (TestFileException e) {
                e.printStackTrace();
                throw new ItemNotFoundException(refID);
            }
            if ((variantsList != null) && (variantsList.size() != 0)) {
                return new VariantList(TestItemListFilter.getGlobalID((TestItem)variantsList.get(0), false),
                                       variantsList,
                                       VariantList.INLINE_LIBRARY);
            }
        }
        return new VariantList(refID, loader.loadLibraryVariants(refID),
                               VariantList.EXTERNAL_LIBRARY);
    }
}
