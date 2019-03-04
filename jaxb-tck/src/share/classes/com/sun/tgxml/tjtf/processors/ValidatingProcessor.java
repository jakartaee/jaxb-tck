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

package com.sun.tgxml.tjtf.processors;

import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * ValidatingProcessor describes the topmost interface for a processor that
 * a shell may own. 
 * <p>
 * All processors are OptionHandlers, and they can participate
 * in command-line argument delegation.
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ValidatingProcessor 
 * ============================================================================================ 
 */ 
public interface ValidatingProcessor extends Processor {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 

  /**
    *  Get the registered validator from a processor.
    * <p>
    * This validator allows a processor to validate an IR either before or after processing.
    * <p>
    * @return A Validator.
    * @exception TestFileException if there is no validator registered.
    */
    public IRValidator getValidator () throws TestFileException;


  /**
    *  Set the registered shell from a processor.
    * <p>
    * This shell allows a processor to use the tool's IO functions
    * to relay messages from the shell.
    * <p>
    * @param validator
    */
    public void setValidator (IRValidator validator);
}
