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

package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.Properties;
import java.util.ArrayList;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tools.indexgen.api.TestSuite;

/**
 * <b>One2OneProcessor</b> can be used in cases when result
 * of processing a single IRObj is a single IRObj or null.
 * <p> Method <code>public IRObj[] process(IRObj[] irs)</code>
 * calls an appropriate method from:
 * <ul>
 *   <li><code>public Library process(Library lib)</code></li>
 *   <li><code>public TestGroup process(TestGroup tg)</code></li>
 *   <li><code>public TestSuite process(TestSuite ts)</code></li>
 * </ul>
 * for each passed IRObj and collects non-null result.
 *
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko 
 *
 */

public class One2OneProcessor extends BasicIRProcessor {

    public One2OneProcessor() {
       super();
    }

    public One2OneProcessor(Properties props) {
       super(props);
    }

    /**
     * Processes passed array of IRObj
     * @param irs  IRObj array to be processed
     * @return     result of processing passed array 
     * @exception  TestFileException  - if there is some processing problem.
     */
    public IRObj[] process(IRObj[] irs) throws TestFileException {
        ArrayList result = new ArrayList();
        for (int i = 0; i < irs.length; i++) {
            IRObj obj1 = irs[i];
            IRObj obj2 = null;
            if (obj1 instanceof TestGroup) { 
                obj2 = process((TestGroup)obj1);
            } else if (obj1 instanceof TestSuite) { 
                obj2 = process((TestSuite)obj1);
            } else if (obj1 instanceof Library) { 
                obj2 = process((Library)obj1);
            } else {
               throw new TestFileException("unknown IRObj: " + obj1);
            }
            if (obj2 != null) {
               result.add(obj2);
            }
        }
        return (IRObj[])(result.toArray(new IRObj[0]));
    }

    /**
     * Returns passed Library as is.
     * Subclasses may override this method to provide library processing.
     */
    public Library process(Library lib) throws TestFileException { 
        return lib; 
    }

    /**
     * Returns passed TestGroup as is.
     * Subclasses may override this method to provide TestGroup processing.
     */
    public TestGroup process(TestGroup tg) throws TestFileException { 
        return tg; 
    }

    /**
     * Returns passed TestSuite as is.
     * Subclasses may override this method to provide TestSuite processing.
     */
    public TestSuite process(TestSuite ts) throws TestFileException { 
        return ts;
    }

}
        
