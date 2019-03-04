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
import java.util.*;
import java.net.URL;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * TestFileParser - The basic engine for ClassDiag. 
 *  Visual versions of ClassDiag should override this class. 
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    XMLEmitter 
 * ============================================================================================ 
 */ 
public interface XMLEmitter extends IREmitter {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 

  /**
    *  Emit the XML File (as a File).
    *
    * @param root The root TD object from which to start emitting.
    * @param file The file descriptor describing where the XML will be emitted.
    * @throws TestFileException If there is a problem with the IR.
    * @throws IOException If there is an IO problem.
    */
    public void emit (XMLObj root, File file) throws TestFileException, IOException;

  /**
    *  Emit the XML File (as a File).
    *
    * @param root The root TD object from which to start emitting.
    * @param stream The output stream describing where the XML will be emitted.
    * @throws TestFileException If there is a problem with the IR.
    * @throws IOException If there is an IO problem.
    */
    public void emit (XMLObj root, OutputStream stream) throws TestFileException, IOException;


  /**
    *  Allow emitter-debugging.
    * @param debug True to turn on a diagnostic mode in the emitter.
    */
    public void setDebug(boolean debug);

}
