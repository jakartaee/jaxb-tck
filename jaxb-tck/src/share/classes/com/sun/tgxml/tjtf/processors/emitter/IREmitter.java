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

package com.sun.tgxml.tjtf.processors.emitter;

import java.io.*;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.processors.ValidatingProcessor;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
 
/** 
 * IREmitter is the top-most contract that an Emitter must handle. 
 * <p>
 *  An implementation of an IREmitter emits a (single-format, validatable)
 *   IRObj tree into some stream or file.
 * <p>
 *  Tools that need to emit multiple forms of information should have
 *  a shell that contains an implementation of IREmitter for each different
 * form of info (i.e. an XMLTDEmitter (TD), an XMLTDEmitter (TestSuite), etc.)
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    IREmitter 
 * ============================================================================================ 
 */ 
public interface IREmitter extends ValidatingProcessor {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 


  /**
    *  Emit the IR trees into the File(s).
    * <p>
    *  The emitter implementation determines the number of IR trees and files accepted.
    *  For Example, an XMLTD Emitter may
    *  return one file for many IRObj trees.  Another emitter may 
    *  return many files for one IRObj tree.
    * <p>
    * @param irs The IR trees to be emitted.
    * @param files The output-files to be emitted.
    * @exception TestFileException if there is some emitting problem.
    * @exception IOException if there is a problem with the output-file.
    */
    public  void emit ( IRObj irs[], File files[]) throws TestFileException, IOException;



  /**
    *  Emit the IR trees into the OutputStream(s).
    * <p>
    *  The emitter implementation determines the number of IR trees and streams accepted.
    *  For Example, an XMLTD Emitter may
    *  return one stream for many IRObj trees.  Another emitter may 
    *  return many streams for one IRObj tree.
    * <p>
    * @param irs The IR trees to be emitted.
    * @param outputs The output-streams to be emitted.
    * @exception TestFileException if there is some emitting problem.
    * @exception IOException if there is a problem with the output-stream.
    */
    public  void emit ( IRObj irs[], OutputStream outputs[]) throws TestFileException, IOException;


}
