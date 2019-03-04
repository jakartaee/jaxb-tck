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
import java.net.URL;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * TestFileParser - The Generic-interface for an XML parser.  This parser can be
 * re-used for other file formats by providing a dtd and a set of TagHandlers that
 * parse a file into a given IR. 
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    XMLParser 
 * ============================================================================================ 
 */ 
public interface XMLParser extends IRParser {

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 


  /**
    *  Parse the XML File (as a File).
    * <p>
    * @param xmlFile A single (XML) file to be parsed.
    * @return The IR root to the parsed object tree.
    * @throws TestFileException if the file contains generic values that do not validate.
    * @throws SAXException if there is an XML formatting issue.
    * @throws IOException if there is an IO problem.
    */
    public XMLObj parse (File xmlFile) throws TestFileException, SAXException, IOException;
    
    /**
     *  Parse the XML File (as a File) from the given respository.
     * <p>
     * @param xmlFile A single (XML) file to be parsed.
     * @param sourceRoot A repository root dorectory. It is used for calculation 
     * of the relSourcePath attribute.
     * @return The IR root to the parsed object tree.
     * @throws TestFileException if the file contains generic values that do not validate.
     * @throws SAXException if there is an XML formatting issue.
     * @throws IOException if there is an IO problem.
     */
    public XMLObj parse (File xmlFile, String sourceRoot) throws TestFileException, SAXException, IOException;



  /**
    *  Parse the XML input-stream.
    * <p>
    * @param stream A single (XML) input-stream to be parsed.
    * @return The IR root to the parsed object tree.
    * @throws TestFileException if the stream contains generic values that do not validate.
    * @throws SAXException if there is an XML formatting issue.
    * @throws IOException if there is an IO problem.
    */
    public XMLObj parse (InputStream stream) throws TestFileException, SAXException, IOException;

  /**
    *  Allow parser-debugging.
    * <p>
    * @param debug true if the parser is to be put into a diagnostic mode.
    */
    public void setDebug(boolean debug);

  /**
    *  Determine if the parser is in debug mode.
    * <p>
    * @return true if parser is in debug mode.
    */
    public boolean getDebug();

 /**
    *  Sets the mode that suppresses the addition of an "SourceFile" AttrElem.
    * <p>
    * @param mode Parser will add a "SourceFile" AttrElem when mode is true.
    */
    public void setSourceFileMode(boolean mode);


  /**
    *  get the mode that determines the addition of an "SourceFile" AttrElem.
    * <p>
    * @return true if the Parser will add a "SourceFile" AttrElem.
    */
    public boolean getSourceFileMode();

   /** 
    *  Gets the mode that determines whether the (SAX XMLReader) parser is validating.
    * <p>
    * @return true if the parser is validating XML to the DTD.
    */ 
    public boolean getXMLValidation();

   /** 
    *  Sets the mode that determines whether the (SAX XMLReader) parser is validating.
    * <p>
    * @param validating Set this to true to have the parser validate incoming XML to the DTD.
    * @throws SAXException if the SAXParser can not handle validation (should never happen).
    */ 
    public void setXMLValidation(boolean validating) throws SAXException;

   /** 
    *  Sets the Validator (3rd-level semantics validation engine).
    * <p>
    * @param validator The validator to set.
    */ 
    public void setValidator(IRValidator validator);

   /** 
    *  Sets the Validator (3rd-level semantics validation engine).
    * 
    */ 
    public IRValidator getValidator();
}
