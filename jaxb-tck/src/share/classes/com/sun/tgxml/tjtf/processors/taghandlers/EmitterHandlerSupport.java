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

package com.sun.tgxml.tjtf.processors.taghandlers;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.Stack;
import com.sun.tgxml.tjtf.api.tests.TestRoot;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.io.IOException;

 
/** 
 * EmitterHandlerSupport - The contract of methods that a (XML - TD) ParserHandler must provide 
 * in order to support ParserTagHandlers. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    EmitterHandlerSupport 
 * ============================================================================================ 
 */ 
public interface EmitterHandlerSupport   {





   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 


  /**
    *  Log Diagnostics.
    * <p>
    * @param message The message on the (outside) exception wrapper.
    */
    public void log (String message) throws TestFileException;


   /** 
    *   emit a tag-handler (tdObject) of the given type (tdTagType). 
    * <p>
    *  @param tdTagType The actual tag-name that is being emitted.
    *  @param tdObj A generic object handle of that type (the TagHandler downcasts the obj).
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emit(String tdTagType, Object tdObj) throws TestFileException, IOException;
 
   /** 
    *   Start a normal begin tag. 
    * <p>
    *  @param tdTagType The actual tag-name that is being emitted.
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitHeadBeginTag(String tdTagType) throws TestFileException, IOException;

   /** 
    *   Close-bracket a normal begin tag. 
    * <p>
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitHeadEndTag() throws TestFileException, IOException;
          
   /** 
    *   Emit a normal end-tag. 
    * <p>
    *  @param tdTagType The actual tag-name that is being emitted.
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitTailTag(String tdTagType) throws TestFileException, IOException;
 
   /** 
    *   Start an XML singleton tag. 
    * <p>
    *  @param tdTagType The actual tag-name that is being emitted.
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitSingletonBeginTag(String tdTagType) throws TestFileException, IOException;

   /** 
    *   Close-bracket an XML singleton tag. 
    * <p>
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitSingletonEndTag() throws TestFileException, IOException;

   /** 
    *   Emit free-flow (PCDATA) text enclosed by a tag. 
    * <p>
    *  @param text The PCDATA being emitted.
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitText(String text) throws TestFileException, IOException;

   /** 
    *   Emit an XML attribute (i.e. name="value"). 
    * <p>
    *  @param name The attribute name being emitted.
    *  @param value The attribute value being emitted.
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void emitAttribute(String name, String value) throws TestFileException, IOException;
          
   /** 
    *   Increment the indentation level. 
    */ 
    public void incrementIndentation();

   /** 
    *   The decrement the indentation level. 
    */ 
    public void decrementIndentation();


   /** 
    *   flush a line in the print buffer. 
    * <p>
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void newline() throws TestFileException, IOException;


   /** 
    *  Move a print buffer out to the proper indentation. 
    * <p>
    * @throws TestFileException If there is a casting or IR problem.
    * @throws IOException if there is an IO problem.
    */ 
    public void indent() throws TestFileException, IOException;

}
