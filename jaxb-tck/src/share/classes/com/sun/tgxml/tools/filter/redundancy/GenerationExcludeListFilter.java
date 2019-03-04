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
import java.util.Set;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tools.testgen.LibUtils;

/*
 * commented to be compatible with jdk 1.3.0
import java.util.logging.Level;
import java.util.logging.Logger;
*/
/**
 * The class represents generation exclude list. 
 * The excluded TestItems are defined in format specified in superclass.
 */
public class GenerationExcludeListFilter extends TestItemListFilter {
    private static final String EXTERNAL_MULTITEST = "ExternalMultiTest";
    /*
     * commented to be compatible with jdk 1.3.0
     
    public static final String LOGGING_SYBSYSTEM_NAME = "com.sun.tgxml.tools.filter.redundancy";
        
    protected Logger logger = Logger.getLogger(LOGGING_SYBSYSTEM_NAME);
    */
    /**
     * creates instance with the given arguments list.
     * The argument list have contain at least one element, 
     * which is assumed as name of the resource, which contains TestItems list.
     * The resource should be accessible via
     * <code>ClassLoader.getResourceAsStream()</code>.
     * The rest of arguments are ignored.
     * @param args argument list
     */
    public GenerationExcludeListFilter(String[] args) {
        this(args[0]);
    }
    
    /**
     * creates instance with the given name of the resource, which contains 
     * TestItems list.
     * The resource should be accessible via
     * <code>ClassLoader.getResourceAsStream()</code>.
     * @param name name of the resource.
     */
    public GenerationExcludeListFilter(String name) {
        super(name);
    }
    
    /*
     * commented to be compatible with jdk 1.3.0
    protected boolean checkAccepted(String key) {
        boolean status = super.checkAccepted(key);

        if (!status) {
            logger.log(Level.INFO, "'" + key + "' is excluded according to '" + name
                    + "' exclude list.");
        }
         return status;
    }
     */
   
    public boolean accept(TestItem item) {
        if (item instanceof TestGroup) {
            verifyTestGroupComponents((TestGroup)item);
        }
        return !super.accept(item);
    }
    
    private void verifyTestGroupComponents(TestGroup tg) {
        
        try {
            Set set = getExcludedComponents(tg);
            if (set == null) {
                return;
            }

            for (Iterator i = set.iterator(); i.hasNext();) {
                Object current = i.next();
                if (!(current instanceof String)) {
                    continue;
                }
                String tcID = (String)current;
                String varName = null;

                if (EXTERNAL_MULTITEST.equals(LibUtils.getTestType(tg))) {
                    String id = getGlobalID(tg, false) + "[" + current + "]";
                    throw new RuntimeException(
                        LibResHandler.getResStr("filter.redundancy.error.externaMultiTestExclusion",
                                                id));
                }

                int pos;
                if ((pos = tcID.indexOf(':')) > 0) {
                    varName = tcID.substring(pos + 1);
                    tcID = tcID.substring(0, pos);
                }
                ArrayList exc = tg.getLibrary(tcID);
                if ((exc == null) || (exc.size() == 0)) {
                    exc = tg.getTestCase(tcID);
                }
                if (!containsVariant(exc, varName)) {
                    String id = getGlobalID(tg, false) + "[" + current + "]";
                    throw new RuntimeException(
                        LibResHandler.getResStr("filter.redundancy.error.btx.invalidentry",
                                                    id));
                }
            }
        } catch (TestFileException e) {
            e.printStackTrace();
            throw new RuntimeException(
                    LibResHandler.getResStr("filter.redundancy.error.unexpectedExcepton",
                                            e.toString()));
        }
        
    }
    
    public static boolean containsVariant(ArrayList items, String name) {
        if ((items == null) || (items.size() == 0)) { // No testcases
            return false;
        } else if (name == null) {
            // null menas any variant is OK
            return true;
        } else {
            for (Iterator i = items.iterator(); i.hasNext();) {
                Object o = i.next();
                if ((o instanceof TestVariant)
                    && name.equals(((TestVariant)o).getVarName())) {
                    return true;
                }
            }
            return false;
        }
    }
}
