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

package com.sun.tgxml.tjtf.processors.impl;

import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.processors.ValidatingProcessor;
import com.sun.tgxml.tjtf.processors.validator.NullValidator;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * ValidatingProcessorImpl - The basic functionality for a processor. 
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ValidatingProcessorImpl 
 * ============================================================================================ 
 */ 
public class ValidatingProcessorImpl extends ProcessorImpl implements ValidatingProcessor {

    protected IRValidator m_validator;


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   ValidatingProcessorImpl constructor - 
    *       Initialize our internal fields. 
    */ 
    public ValidatingProcessorImpl() {
	// by default - get the null validator
	setValidator(null);
    }



   /** 
    *  Sets the Validator (3rd-level semantics validation engine).
    * <p>
    * @param validator The validator to set.
    */ 
    public void setValidator(IRValidator validator) {
	if (validator == null)
	    // set the validator to the null validator
	    m_validator = new NullValidator();
	else
	    m_validator = validator;

	m_validator.setShell(m_shell);
    }

  /**
    *  Gets the Shell that owns this parser.
    * <p>
    * @returns The shell that owns this parser.
    */
    public IRValidator getValidator() {
	return m_validator;
    }

}
