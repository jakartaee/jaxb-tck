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

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.util.IR;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;


public class DefaultExcludeListCollector implements ExcludeListCollector {

	
    private PrintWriter out = null;
    private String fileName = null;
    
    public DefaultExcludeListCollector() {
    }

    public DefaultExcludeListCollector(String fileName) {
        this();
        setExcludeListFileName(fileName);
    }

    /**
     * sets exclude list output file name
     */
    public void setExcludeListFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Returns exclude list output file name
     */
    public String getExcludeListFileName() {
        return fileName;
    }


    private synchronized void writeToFile(String url, String testCaseName, 
                       String excludedContents) throws IOException {
        if (out == null) {
            out = new PrintWriter(new FileWriter(fileName, true));
        }
        StringBuffer buf = new StringBuffer(url);
        if (testCaseName != null) {
            buf.append("[");
            buf.append(testCaseName);
            buf.append("]");
        }
        buf.append(" ");
        buf.append(excludedContents);
        out.println(buf.toString());
        out.flush();
    
    }
   /** This method adds new entry to the Collector if testcase contains 
     * "excluded" attribute.
     * The tescase should have "testDescriptionURL" and "testCaseName"
     * attributes. 
     * @throws IncorrectAttributesException if "testDescriptionURL"  are not defined.
     * @return true, if exclude entry was added 
     */

	public synchronized boolean addEntry(TestCase testCase) throws IncorrectAttributesException, IOException {
		if (!ExcludeListUtils.isMarkedExcluded(testCase)) {
			return false;
		}
		TestGroup parent = testCase.getTestGroup();
        String url = null;
		String testCaseName = null;
		String excludedContents = null;

		url = IR.getAttrElem(ExcludeListUtils.TestDescriptionURLAttrElemName, testCase);
        if (url == null) {
            IR.getAttrElem(ExcludeListUtils.TestDescriptionURLAttrElemName, parent);
        }

		excludedContents = IR.getAttrElem(ExcludeListUtils.ExcludedAttrElemName, testCase);
		testCaseName = IR.getAttrElem(ExcludeListUtils.TestCaseNameAttrElemName, testCase);
		writeToFile(url, testCaseName, excludedContents);
	    return true;

	}


   /** This method adds new entry to the Collector if testgroup contains 
     * "excluded" attribute.
     * The tescase should have "testDescriptionURL" attribute. 
     * @throws IncorrectAttributesException if "testDescriptionURL"  are not defined.
     * @return true, if exclude entry was added 
     */

	public synchronized boolean addEntry(TestGroup group) throws IncorrectAttributesException, IOException  {
		if (!ExcludeListUtils.isMarkedExcluded(group)) {
			return false;
		}
        String url = null;
		String excludedContents = null;

		url = IR.getAttrElem(ExcludeListUtils.TestDescriptionURLAttrElemName, group);
		excludedContents = IR.getAttrElem(ExcludeListUtils.ExcludedAttrElemName, group);
		writeToFile(url, null, excludedContents);
	    return true;

	}

}
