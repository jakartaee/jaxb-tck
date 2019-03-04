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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.XMLValToolBase
import org.xml.sax.*;
import com.sun.tgxml.tjtf.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.validator.*;
import com.sun.tgxml.tjtf.processors.parser.*;
import com.sun.tgxml.tjtf.processors.parser.impl.XMLParserImpl;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.processors.emitter.impl.XMLEmitterImpl;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.exceptions.*;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

// </importgen>


/**
 * XMLValToolBase - 
 * <p>
 * This class adds to the XML tool base class by handling property setting for a
 * tool to do validation while XML processing.
 * <p> 
 * The system property <code>"jck.tgxml.validator"</code> should be set to the name of 
 * the desired validator class. If the property is not defined a tool
 * does not use validator at all. 
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */

/*
 * ============================================================================================
 *    XMLValToolBase
 * ============================================================================================
 */


public  class XMLValToolBase extends XMLToolBase {
    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    private static final String CtStr_ToolName = LibResHandler.getResStr("xmlvalbasetool.name");
    
      
            /** Print Strings   */
    private static final String str_Term = System.getProperty("line.separator");

    private IRValidator m_validator;

    /*
     * The name of the system property which should be set to the name of 
     * the desired validator class. If the property is not defined the tool
     * does not use validator at all. 
     */
    protected static final String validatorPropName = "jck.tgxml.validator"; // check class descr for
									     // the property name

    /*
     * The flag variable governs using defined validator while parsing the tool input.
     * Default value of the variable is <code>true</code> and the tool derived from the
     * <strong>XMLToolBase</strong> could set it to <code>false</code> to avoid validator
     * execute while parsing.
     */
    protected boolean m_ParserValidates = true;

    /*
     * The flag variable governs using defined validator while emitting the tool output.
     * Default value of the variable is <code>false</code> and the tool derived from the
     * <strong>XMLToolBase</strong> could set it to <code>true</code> to force validator
     * execute while emitting.
     */
    protected boolean m_EmitterValidates = false;

    
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
    
    
    
    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Program Entry
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
  /**
    *  Program entry
    *
    *  This replaces the main in ToolBase (sub-classes XMLValToolBase).
    * <p>
     * @param args The command line arguments to  this tool.
    */
    public static void main(String args[]) {
        XMLValToolBase c = new XMLValToolBase(System.out, System.err);
        System.exit(c.run(args));
    }
    

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Public Methods (outside world can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLValToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public XMLValToolBase( PrintStream out, PrintStream err) {
	this(out, err, CtStr_ToolName);
    }


    /** Constructor (canon.)
     *  
     *  constructs the XMLValToolBase tool class.
     *
     * @param out  The print stream for writing program information.
     * @param err  The print stream for error diagnostics.
     * @param name The name of the tool.
     *
     * @see java.io.PrintStream 
     */
    public XMLValToolBase( PrintStream out, PrintStream err, String name) {
	super(out, err, name);
	createValidator();
    }


 
   /* 
    * -------------------------------------------------------------------
    *    Getters/Setters 
    * -------------------------------------------------------------------
    */ 


  /**
    * Create a default parser (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public void setupParser()  throws TestFileException {
	super.setupParser();

	if (m_ParserValidates && m_validator != null)
	    m_parser.setValidator(m_validator);
    }


  /**
    * Create a default parser (XML).
    * <p>
    *  Sub-classes may override this to install a
    *  different type of parser.
    * <p> 
    */
    public void setupEmitter()  throws TestFileException {
	super.setupEmitter();

	if (m_EmitterValidates && m_validator != null)
	    m_emitter.setValidator(m_validator);
    }

 
   /* 
    * -------------------------------------------------------------------
    *    
    * -------------------------------------------------------------------
    */ 

    private void createValidator() {
	String valClassName = System.getProperty(validatorPropName);

	if (valClassName == null) {
	    m_validator = null;
	    return;
	}
	try {
	    Class c = Class.forName(valClassName);
	    Object ins = c.newInstance();
	    m_validator = (IRValidator)ins;
	} catch(Exception e) {
	    throw new IllegalArgumentException(e.toString());
	}
    }

 

}
