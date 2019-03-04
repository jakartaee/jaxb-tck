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

import com.sun.tgxml.tjtf.api.tests.TestItem;

/**
 * This class represents TestItems with the same ID.
 */
public class VariantList {
    
    public static final int NULL = 0;
    public static final int EXTERNAL_LIBRARY = 1;
    public static final int INLINE_LIBRARY = 2;
    public static final int TEST_CASE = 4;
    
   
    private ArrayList variants;
    private String id;
    private int type;
    
    /**
     * creates the instance with the given id and list of TestVariants 
     * @param id ID of the given TestItems. It should not be result of 
     * TestItem.getID() methods, because they are not unique.
     * The id should include ID and relSourceDir of an enclosing TestGroup
     * if applied. 
     * @param variants contains TestItem instances
     * @param type type of TestItem. It should be one from EXTERNAL_LIBRARY, 
     * INTERNAL_LIBRARY or TEST_CASE. 
     */
    public VariantList(String id, ArrayList variants, int type) {
        this.id = id;
        this.variants = variants;
        this.type = type;
    }
    
    /**
     * returns TestItem instances.
     */
    public ArrayList getTestVariants() {
        return (ArrayList)variants.clone();
    }

    /**
     * returns ID if the TestItem variants.
     */
    public String getID() {
        return id;
    }
    
    /**
     * returns type of the TestItems.
     */
    public int getType() {
        return type;
    }
    
    /**
     * sets list of TestItems. The previous TestItems list is removed.
     */
    public void setTestVariants(ArrayList list) {
        this.variants = list;
    }
    
    /**
     * adds one more TestItem in the variant list.
     */
    public void addTestVariant(TestItem item) {
        this.variants.add(item);
    }
}
