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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestGroupComponent;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.tools.elgen.ExcludeEntry;
import com.sun.tgxml.tools.elgen.ExcludeList;
import com.sun.tgxml.tools.elgen.ExcludeListUtils;
import com.sun.tgxml.tools.elgen.IncorrectAttributesException;

/**
 * The class represents the filter, which accepts TestItems defined in InputStream. 
 * The InputStream have to be in the following format:
 * <p>
 * &lt;path&gt;/foo[:fred][[bar[:fred]]][   BugID]
 *    
 * <p>
 * where<br>
 *      
 *   foo: Test Group or External Library ID;<br>
 *   bar: Test Case or Internal Library ID;<br>
 *  fred: variant name.
 * <p>
 * The entries in the list can be duplicated and have arbitrary order.
 * For example:<br>
 * The following line accepts TestGroup<br> 
 * api/java_lang/String/Constructor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000000
 * <p>
 * The following line accepts External Library variant<br>
 * api/java_io/SystemEncoding:me&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000000   
 * <p>
 * The following line accepts TestCase variant<br>
 * api/java_lang/String/Constructor1[String0005:Original]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000000
 * <p>
 * The following line accepts all TestCase variant<br>
 * api/java_lang/String/Constructor1[String0005]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000000
 * <p>
 * Note that all TestItems undefined in the list are rejected. It causes for example, 
 * that if TestGroup is not listed, then whole TestGroup is rejected even if some 
 * TestCases from the TestGroup are in the list. 
 */
public class TestItemListFilter implements ApplicabilityFilter {

    private String filter_name;
    
    /**
     * creates instance with the given arguments list.
     * The argument list have contain at least one element, 
     * which is assumed as name of the resource, which contains TestItems list.
     * The resource should be accessible via
     * <code>ClassLoader.getResourceAsStream()</code>.
     * The rest of arguments are ignored.
     * @param args argument list
     */
    public TestItemListFilter(String[] args) {
        this(args[0]);
    }
    
    /**
     * creates instance with the given name of the resource, which contains
     * TestItems list.
     * The resource should be accessible via
     * <code>ClassLoader.getResourceAsStream()</code>.
     * @param name name of the resource.
     */
    public TestItemListFilter(String name) {
        this.filter_name = "TestItemListFilter." + name;
        this.name = name;
        if (name != null) {
            try {
                ClassLoader loader = this.getClass().getClassLoader();
                loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
                InputStream inRes = loader.getResourceAsStream(name);
                if (inRes == null) {
                    throw new RuntimeException("Can not load file URL for "
                            + "generation exclude list: " + name);
                }
                
                BufferedReader
                in = new BufferedReader(new InputStreamReader(inRes));
                ExcludeList list = new ExcludeList(in);
                for (Iterator i = list.getAllEntries(); i.hasNext();) {
                    ExcludeEntry entry = (ExcludeEntry)i.next();
                    String key = entry.getDirectory() + "/" + entry.getTestGroupID();
                    String internal = entry.getTestCaseID();
                    addExcludeEntry(key, internal);
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Can not load generation  exclude list: "
                        + name);
            }
        }
    }
    private static final Object WHOLE_TEST_GROUP = new Object();
    
    private void addExcludeEntry(String tGroup, String tCase) {
        HashSet list = (HashSet)testItemList.get(tGroup);
        if (list == null) {
            list = new HashSet();
            testItemList.put(tGroup, list);
        }
        list.add((tCase == null) ? WHOLE_TEST_GROUP : tCase);
    }

    public boolean accept(TestItem item) {
        try {
            return checkAccepted(item);
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.toString());
        }
    }

    public String getName() {
        return filter_name;
    }

    private HashMap testItemList = new HashMap();
    protected String name;

    public static String getGlobalID(TestItem item, boolean includeVariant) throws TestFileException {
        String dir = null;
        String id = null;
        String internal = null;
        try {
            if ((item instanceof TestGroupComponent)) {
                TestGroup tGroup = ((TestGroupComponent)item).getTestGroup();
                internal = item.getID();
                id = tGroup.getID();
                dir = ExcludeListUtils.getSourceDir(tGroup);
            } else {
                id = item.getID();
                dir = ExcludeListUtils.getSourceDir(item);
            }
        } catch (IncorrectAttributesException iae) {
            dir = "<unknown>/";
        }
        String variant = ((item instanceof TestVariant) && includeVariant)
                          ? ((TestVariant)item).getVarName() : null;
        dir = dir + '/' + id;
        if (internal == null) {
            return (variant == null) ? dir : (dir + ':' + variant);
        } else {
            return dir + '[' + internal + ((variant == null) ? "" : ( ':' + variant)) + ']';
        }
    }

    protected boolean checkAccepted(TestItem item) throws TestFileException {
        TestItem parent = item;
        Object internal = WHOLE_TEST_GROUP;
        Object varID = WHOLE_TEST_GROUP;
        if (item instanceof TestGroupComponent) {
            parent = ((TestGroupComponent)item).getTestGroup();
            internal = item.getID();
            varID = ((item instanceof TestVariant)
                     ? ((TestVariant)item).getVarName() : null);
            varID = internal + ((varID == null) ? "" : (":" + varID));
        }
        Set set = getExcludedComponents(parent);
        return (set != null) && (set.contains(internal) || set.contains(varID));
    }
    
    protected Set getExcludedComponents(TestItem item) throws TestFileException {
        Set set = (Set)testItemList.get(getGlobalID(item, false));
        if (set == null) {
            set = (Set)testItemList.get(getGlobalID(item, true));
        }
        return set;
    }
}    
