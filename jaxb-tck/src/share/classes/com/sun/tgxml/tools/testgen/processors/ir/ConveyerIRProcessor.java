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
package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.Properties;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;


/**
 * <b>ConveyerIRProcessor</b> - IRProcessor that just consecutively
 * invokes passed processors
 *
 * @author      Dmitry Fazunenko
 *
 */

public class ConveyerIRProcessor extends BasicIRProcessor {
    
    IRProcessor[] ps = null;
    
    public ConveyerIRProcessor(IRProcessor[] processors) {
        super();
        this.ps = processors;
    }
    
    
    /**
     * Invokes processors consecutively on passed array of IRObj
     * @param irs  IRObj array to be processed
     * @return     result of processing passed array 
     * @exception  TestFileException  - if there is some processing problem.
     */
    public IRObj[] process(IRObj[] irs) throws TestFileException {
        if (irs == null)
            return irs;

        for (int i = 0; i < ps.length; i++) {
            irs = ps[i].process(irs);
        }

        return irs;

    }

    

}

