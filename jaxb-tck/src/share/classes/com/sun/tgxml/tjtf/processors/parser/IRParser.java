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

package com.sun.tgxml.tjtf.processors.parser;

import java.io.*;
import java.util.*;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.processors.ValidatingProcessor;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * IRParser is the top-most contract that a Parser must handle. 
 * <p>
 *  An implementation of an IRParser parses a (single-format) specific
 *  stream of information into an IRObj tree.
 * <p>
 *  Tools that need to parse multiple forms of information should have
 *  a shell that contains an implementation of IRParser for each different
 * form of info (i.e. an XMLTDParser (TD), an XMLTDParser (TestSuite), etc.)
 * 
 * 
 * @version 	1.0, 10/02/01 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    IRParser 
 * ============================================================================================ 
 */ 
public interface IRParser extends ValidatingProcessor {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 


  /**
    *  Parse the File(s) into a set of IR's.
    * <p>
    *  The parser implementation determines the number of files accepted and
    *  the number of IRObj trees returned.  For Example, an XMLTD Parser may
    *  return one IRObj tree for every one file parsed.  A JMPP parser may 
    *  return many IRObj trees for every one file parsed.
    * <p>
    * @param files The input-files to be parsed.
    * @return A set of IRObj trees.
    * @exception TestFileException if there is some parsing problem.
    * @exception IOException if there is a problem with the input-file.
    */
    public IRObj[] parse (File files[]) throws TestFileException, IOException;


}
