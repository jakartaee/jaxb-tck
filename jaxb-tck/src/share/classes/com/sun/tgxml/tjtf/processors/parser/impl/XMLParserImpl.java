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

package com.sun.tgxml.tjtf.processors.parser.impl;

import java.io.*;
import java.util.*;
import java.net.URL;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.processors.impl.ValidatingProcessorImpl;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.processors.parser.XMLParser;
import com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerTable;
import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.tools.options.FlagOption;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.tools.BuildProperties;
 
/** 
 * XMLParserImpl - The basic engine for an XML parser. 
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    XMLParserImpl 
 * ============================================================================================ 
 */ 
public class XMLParserImpl extends ValidatingProcessorImpl implements XMLParser {


    private static final String  str_Separator = System.getProperty("path.separator");
    private static final String  str_Term = System.getProperty("line.separator");


    /** The URI to the DTD that this parser conforms to (see setupParser) */
    private String                 m_uri;

    /** The XMLParserHandler */
    private BaseParserHandlerImpl  m_handler;

    /** The (reusable) XML reader that starts the parsing of a document */
    private XMLReader              m_xmlReader;

    private  boolean               m_addSourceDirAttr;
    private  boolean               m_xmlValidating;

    private  String                m_alternateParser;



   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   XMLParserImpl constructor - 
    *       Initialize our internal fields. 
    */ 
    public XMLParserImpl() {
	super();
	m_handler = new BaseParserHandlerImpl();
	m_addSourceDirAttr = true;
	m_xmlValidating = false;
	m_alternateParser = null;
	reset();
    }


   /** 
    *   resets the parser for reuse. 
    */ 
    public void reset() {
	if (m_handler != null)
	    m_handler.reset();
    }




   /** 
    *  Gets the mode that determines whether the (SAX XMLReader) parser is validating.
    * <p>
    * @return true if the parser is validating XML to the DTD.
    */ 
    public boolean getXMLValidation() {
	return m_xmlValidating;
    }




   /** 
    *  Sets the mode that determines whether the (SAX XMLReader) parser is validating.
    * <p>
    * @param validating Set this to true to have the parser validate incoming XML to the DTD.
    * @throws SAXException if the SAXParser can not handle validation (should never happen).
    */ 
    public void setXMLValidation(boolean validating) throws SAXException {
	if (validating) {
	    // Turn on validation
	    m_xmlReader.setFeature("http://xml.org/sax/features/validation", true);
	} else {
	    // Turn off validation
	    m_xmlReader.setFeature("http://xml.org/sax/features/validation", false);
	}
	// Turn off namespace awareness
	m_xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
	m_xmlValidating = validating;
    }



   /** 
    *   Set the parser's debug mode. 
    */ 
    public void setDebug(boolean debug) {
	m_handler.setDebug(debug);
    }

   /** 
    *   Get the parser's debug mode. 
    */ 
    public boolean getDebug() {
	return m_handler.getDebug();
    }

  /**
    *  Sets the mode that suppresses the addition of an "SourceFile" AttrElem.
    * <p>
    * @param mode Parser will add a "SourceFile" AttrElem when mode is true.
    */
    public void setSourceFileMode(boolean mode) {
	m_addSourceDirAttr = mode;
    }

  /**
    *  get the mode that determines the addition of an "SourceFile" AttrElem.
    * <p>
    * @returns true if the Parser will add a "SourceFile" AttrElem.
    */
    public boolean getSourceFileMode() {
	return m_addSourceDirAttr;
    }


  /**
    *  Sets the mode that suppresses the addition of an "SourceFile" AttrElem.
    * <p>
    * @param parserClassName The (fully-qualified) name of a SAX 2.0 compliant XMLReader.
    */
    public void setAlternateParser(String parserClassName) {
	m_alternateParser = parserClassName;
    }

  /**
    *  gets the mode that suppresses the addition of an "SourceFile" AttrElem.
    * <p>
    * @returns The (fully-qualified) name of a SAX 2.0 compliant XMLReader.
    */
    public String getAlternateParser() {
	return m_alternateParser;
    }


  /**
    *  Sets the Shell that owns this parser.
    * <p>
    * @param shellClass The shell that owns this parser.
    */
    public void setShell(Shell shellClass) {
	super.setShell(shellClass);
	m_handler.setShell(shellClass);
    }

   
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    FlagOption debugOption = new FlagOption("-Pdiag", 
            "  -Pdiag   debug mode");

    FlagOption suppressOption = new FlagOption("-PsuppressSourceFile", 
            "  -PsuppressSourceFile   suppress source file name ");

    StringOption alternateOption = new StringOption("-PalternateParser", 
            "  -PalternateParser  <classname>   alternate parser class name ");

    /**
     * Registers -debug option
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(debugOption);
        addOption(suppressOption);
        addOption(alternateOption);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (debugOption.isSet()) {
	    setDebug(true); 
        }

        if (suppressOption.isSet()) {
	    setSourceFileMode(false);
        }

        if (alternateOption.isSet()) {
	    setAlternateParser(alternateOption.getStringValue());
        }

	if (m_alternateParser != null) {
	    try {
		Class.forName(m_alternateParser);
	    } catch (Exception e) {
		throw new ParseArgumentException(LibResHandler.getResStr("parser.altparser.error.class.missing", m_alternateParser));
	    }
	}

        super.applyOptionsValues();
    }



   /* 
    * -------------------------------------------------------------------------------------------- 
    *    XMLParser Interface implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 

   
    /**
     *  A parser needs to create TagHandlers with a reference to the
     *  ParserHandler.  ToolWriters that are creating a new parser implementation
     *  will need to create their TagHandlers with this ParserHandler.
     */
    public ParserHandlerSupport getParserHandlerSupport () {
	return m_handler;
    }
  


  /**
    *  Parse the File(s) into a set of IR's (General Parse contract).
    */
    public IRObj[] parse (File files[]) throws TestFileException, IOException {
	try {
	    int numFiles = files.length;
	    IRObj[] IRs = new IRObj[numFiles];

	    for (int i=0; i < numFiles; i++) {
		IRs[i] = parse(files[i]);
		reset();
	    }
	    
	    return IRs;
	} catch (SAXException e) {
	    throw new EmbeddedTFException(e);
	}
    }


  /**
    *  Parse the File(s) into a set of IR's (General Parse contract).
    */
    public IRObj[] parse (InputStream inputs[]) throws TestFileException, IOException {
	try {
	    int numInputs = inputs.length;
	    IRObj[] IRs = new IRObj[numInputs];

	    for (int i=0; i < numInputs; i++) {
		IRs[i] = parse(inputs[i]);
	    }
	    
	    return IRs;
	} catch (SAXException e) {
	    throw new EmbeddedTFException(e);
	}
    }
   
    
    /**
     * Parse the TD from an XML file.
     */
    public XMLObj parse (File xmlFile) throws TestFileException, SAXException, IOException  {
        return this.parse(xmlFile, BuildProperties.getString("tck.source.dir"));
    }

    public XMLObj parse (File xmlFile, String tckSourceDir) throws TestFileException, SAXException, IOException  {
	// Test validity of file
	// ....

	FileInputStream is = new FileInputStream(xmlFile);
	InputSource inputSource = new InputSource(is);
	inputSource.setPublicId(xmlFile.getCanonicalPath());

	XMLObj root =  parseSource(inputSource);
	if (m_addSourceDirAttr) 
	    addPathAttr(root, tckSourceDir, xmlFile.getCanonicalPath());
	return root;
    }

  
	
    
    /**
     * Parse the TD from an input stream.
     */
    public XMLObj parse (InputStream stream) throws TestFileException, SAXException, IOException {
	InputSource inputSource = new InputSource(stream);
	return parseSource(inputSource);
    }
	

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    utility methods 
    * -------------------------------------------------------------------------------------------- 
    */
     /**
     *  Parse the TD IR from an input stream.
     */
    private XMLObj parseSource (InputSource inputSource) throws TestFileException, SAXException, IOException  {
	TagHandlerTable tht = m_handler.getHandlerTable();
	String uri = tht.getDocURI();
	inputSource.setSystemId(uri);	

	// parse the document
	m_xmlReader.parse(inputSource);

	XMLObj tree = m_handler.getRoot();
	m_validator.validate(tree);
	return tree;
    }

   
    /**
     *  Parse the TD IR from an input stream.
     */
    private XMLObj parseStream (InputStream is) throws TestFileException, SAXException, IOException  {
	//
	// Turn the input stream into an input source
	//
	InputSource inputSource = new InputSource(is);
	TagHandlerTable tht = m_handler.getHandlerTable();
	String uri = tht.getDocURI();
	inputSource.setSystemId(uri);	

	// parse the document
	m_xmlReader.parse(inputSource);

	XMLObj tree = m_handler.getRoot();
	m_validator.validate(tree);
	return tree;
    }

  /**
    *  Record the locator for diagnostics messages.
    */
    public void setupParser (TagHandlerTable handlerTable) throws TestFileException, SAXException  {

	// Create a JAXP SAXParserFactory and configure it
	//	    SAXParserFactory spf = SAXParserFactory.newInstance();
	//	    spf.setValidating(true); 
	
	// Create a JAXP SAXParser
	//	    SAXParser parser = spf.newSAXParser();
	
	m_handler.setHandlerTable(handlerTable);
	m_handler.reset();
	
	if (m_alternateParser != null) {
	    // Get the encapsulated SAX XMLReader
	    //  Use this method if creating an XML reader in the standard SAX 2.0 manner
	    m_xmlReader = XMLReaderFactory.createXMLReader(m_alternateParser);
	} else {
	    // Get the encapsulated SAX XMLReader
	    //  Use this method if creating an XML reader using Sun's JAXP/Crimson
	    // Create a JAXP SAXParserFactory and configure it
	    try {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setValidating(true);
		// Create a JAXP SAXParser
		SAXParser saxParser = spf.newSAXParser();
		
		// Get the encapsulated SAX XMLReader
		m_xmlReader = saxParser.getXMLReader();
	    } catch (ParserConfigurationException e) {
		throw new TestFileException(LibResHandler.getResStr("parser.error.internal.config"));
	    }
	}	
	
	// Set the ContentHandler of the XMLReader
	m_xmlReader.setContentHandler(m_handler);
	m_xmlReader.setErrorHandler(m_handler.getErrorHandler());

    }


    
    /**
     *  Add a "SourcePath" attribute to "TestRoot" sub-classes.
     */
    private void addPathAttr (XMLObj root, String rootPath, String path) throws TestFileException  {
        if (root == null)
            return;

        if (path == null || path.equals(""))
            throw new TestFileException(LibResHandler.getResStr("parser.error.nullpath"));

        String relPath = null;

        // Determine relative path for XML object, if available
        if (rootPath != null && !rootPath.equals("")) {
            File f = new File(rootPath);
            try {
                rootPath = f.getCanonicalPath();
            } catch (IOException e) {
                throw new TestFileException(e.getMessage());
            }
            if (!path.startsWith(rootPath)) {
                throw new TestFileException(LibResHandler.getResStr("parser.error.invalidroot", rootPath, path));
            } else {
                relPath = path.substring(rootPath.length() + 1);
            }
        }

        setAttrElem(root, IR.SourcePathAttrElemName, path);
        if (relPath != null) {
            setAttrElem(root, IR.relSourcePathAttrElemName, relPath);
        }    
    }
    
    private void setAttrElem(XMLObj obj, String elemName, String path) {
        String oldVal = null;
        if (obj instanceof TestSuite) {
            TestSuite ts = (TestSuite)obj;
            oldVal = IR.getAttrElem(elemName, ts);
            if (oldVal == null || oldVal.equals("")) {
                IR.setAttrElem(ts, elemName, path);
            }
        } else if (obj instanceof TestItem) {
            TestItem ti = (TestItem)obj;
            oldVal = IR.getAttrElem(elemName, ti);
            if (oldVal == null || oldVal.equals("")) {
                IR.setAttrElem(ti, elemName, path);
            }
        }
    }
    
}
