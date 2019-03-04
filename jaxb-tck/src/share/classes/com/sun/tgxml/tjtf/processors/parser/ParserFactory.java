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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.Testgroup
import java.net.URL;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.parser.*;
import com.sun.tgxml.tjtf.processors.parser.impl.*;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.*;
// </importgen>

/**
 * ParserFactory - 
 *
 * <b>ParserFactory</b> is a static factory class for creating Parser implementations. 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ParserFactory
 * ============================================================================================
 */


public  class ParserFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private ParserFactory () {

    }

    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    
    
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Factories
    //------------------------------------------------------------------------------


  /**
    *   Create a XMLParser.
    *  <p>
    * @return   a default XML TestDescription parser.
    * @throws    TestFileException if there is an internal problem.
    * @throws    IOException if there is a problem getting the  dtd.
    * @see com.sun.tgxml.tjtf.processors.parser.XMLParser
    */
    static public XMLParser  createDefaultXMLParser() throws TestFileException, IOException  {
	try {
	    XMLParserImpl parser = (XMLParserImpl) createXMLParser();
	    TagHandlerTable table = TagHandlerFactory.getDefaultHandlerTable();
	    TagHandlerFactory.setParserSupport(table, parser.getParserHandlerSupport());
	    parser.setupParser(table);

	    return parser;
	} catch (SAXException e) {
	    throw new EmbeddedTFException(e);
	}
    }
     

  /**
    *   Create an (vanilla) XMLParser object.
    * <p>
    *  Still need to setup the parser with a DTD and a handler
    *  <p>
    * @return   a default XML TestDescription parser.
    * @see com.sun.tgxml.tjtf.processors.parser.XMLParser
    */
    static public XMLParser  createXMLParser() {
	XMLParserImpl parser = new XMLParserImpl();
	return parser;
    }
     


  /**
    *   Create an (vanilla) XMLTD parser object.
    * <p>
    *  Still need to setup the parser with a DTD and a handler
    *  <p>
    * @param dtd The XML file-format
    * @param table The table of tag-handlers that a parser uses to handle elements in the dtd.
    * @return   a default XMLParser.
    * @throws    TestFileException if there is an internal problem.
    * @throws    IOException if there is a problem getting the  dtd.
    * @see com.sun.tgxml.tjtf.processors.parser.XMLParser
    */
    static public XMLParser  createXMLParser(URL dtd, TagHandlerTable table)  throws TestFileException, IOException  {
	try {
	    XMLParserImpl parser = (XMLParserImpl) createXMLParser();
	    TagHandlerFactory.setParserSupport(table, parser.getParserHandlerSupport());
	    parser.setupParser(table);
	    
	    return parser;
	} catch (SAXException e) {
	    throw new EmbeddedTFException(e);
	}
    }

}
