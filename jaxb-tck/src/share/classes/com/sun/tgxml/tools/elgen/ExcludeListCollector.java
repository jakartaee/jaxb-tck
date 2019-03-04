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

import java.io.IOException;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;

public interface ExcludeListCollector {

   /** This method adds new entry to the Collector if testcase contains 
     * "excluded" attribute.
     * The tescase should have "testDescriptionURL" and "testCaseName"
     * attributes. 
     * @throws IncorrectAttributesException if "testDescriptionURL"  are not defined.
     * @return true, if exclude entry was added 
     */

    public boolean addEntry(TestCase testCase) 
           throws IncorrectAttributesException, IOException;


   /** This method adds new entry to the Collector if testgroup contains 
     * "excluded" attribute.
     * The tescase should have "testDescriptionURL" attribute. 
     * @throws IncorrectAttributesException if "testDescriptionURL"  are not defined.
     * @return true, if exclude entry was added 
     */

    public boolean addEntry(TestGroup group) 
           throws IncorrectAttributesException, IOException;

    /**
     * sets exclude list output file name
     */
    public void setExcludeListFileName(String fileName);


    /**
     * Returns exclude list output file name
     */
    public String getExcludeListFileName();



}
