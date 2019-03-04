/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;


public interface TestItemSelectionListener {
    
    public static final String EXTERNAL_LIBRARY = "ExternalLibrary";
    public static final String INLINE_LIBRARY = "InlineLibrary";
    public static final String TEST_GROUP = "TestGroup";
    public static final String TEST_CASE = "TestCase";
   
    /**
     * This method is executed for each registered instance, 
     * when a external Library instance is selected. 
     * The method may be invoked more than one time for same Library.
     * @param item selected Library.
     */
    public void externalLibrarySelected(Library item) throws TestFileException;

    /**
     * This method is executed for each registered instance, 
     * when a internal Library instance is selected. 
     * The method may be invoked more than one time for same Library.
     * @param item selected Library.
     */
    public void internalLibrarySelected(Library item) throws TestFileException;
    
    /**
     * This method is executed for each registered instance, 
     * when a TestCase instance is selected. 
     * The method may be invoked more than one time for same TestCase.
     * @param item selected TestCase.
     */
    public void testCaseSelected(TestCase item) throws TestFileException;
 
    /**
     * This method is executed for each registered instance, 
     * when a TestGroup instance is selected. 
     * The method may be invoked more than one time for same TestGroup.
     * @param tGroup selected TestGroup.
     */
    public void testGroupSelected(TestGroup tGroup) throws TestFileException;
    
    /**
     * This method is executed for each registered instance when a part of a
     * task is completed. It does not mean that all libraries are processed.
     */
    public void flush();
    
    public static class EmptyListener implements TestItemSelectionListener {
        public void externalLibrarySelected(Library item) throws TestFileException {
        }

        public void internalLibrarySelected(Library item) throws TestFileException {   
        }
        
        public void testCaseSelected(TestCase item) throws TestFileException {
        }
        
        public void testGroupSelected(TestGroup tGroup) throws TestFileException {
        }
        
        public void flush() {
        }
    }
   
}
