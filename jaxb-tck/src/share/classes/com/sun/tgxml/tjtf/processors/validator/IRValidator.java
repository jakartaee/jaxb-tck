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

package com.sun.tgxml.tjtf.processors.validator;

import java.io.*;
import java.util.*;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.processors.Processor;
import com.sun.tgxml.tjtf.api.exceptions.ValidatorException;
 
/** 
 * IRValidator is the top-most contract that a Validator must handle. 
 * <p>
 *  An implementation of an IRValidator validates a (single-format) specific
 *  IRObj tree.
 * <p>
 * 
 * 
 * @version 	1.0, 11/26/01 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    IRValidator 
 * ============================================================================================ 
 */ 
public interface IRValidator extends Processor {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 


  /**
    *  Primary Validation contract.
    * <p>
    *  The validator takes an IRObj tree, and simply returns normally if
    *  the tree validates.  A Validator exception is thrown otherwise.
    * <p>
    * @param objTree The IR object tree to be validated.
    * @exception ValidatorException if there is a violation.
    */
    public void validate (IRObj objTree) throws ValidatorException;


}
