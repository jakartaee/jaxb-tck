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

package com.sun.tgxml.tjtf.processors.emitter.impl;

import java.io.*;
import java.util.*;
import java.net.URL;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.processors.impl.ValidatingProcessorImpl;
import com.sun.tgxml.tjtf.processors.validator.NullValidator;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerTable;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.options.FlagOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;

/** 
 * XMLEmitterImpl - The basic engine for an XML emitter. 
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    XMLEmitterImpl 
 * ============================================================================================ 
 */ 
public class XMLEmitterImpl extends ValidatingProcessorImpl implements XMLEmitter {


    private static final String  str_Separator = System.getProperty("path.separator");
    private static final String  str_Term = System.getProperty("line.separator");
    private BaseEmitterHandlerImpl m_handler;


   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   XMLEmitterImpl constructor - 
    *       Initialize our internal fields. 
    */ 
    public XMLEmitterImpl() {
	super();
	m_handler = new BaseEmitterHandlerImpl();
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
    *   resets the parser for reuse. 
    */ 
    public void setDebug(boolean debug) {
	m_handler.setDebug(debug);
    }



   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    FlagOption debugOption = new FlagOption("-Ediag", 
            "  -Ediag   debug mode");

    /**
     * Registers -debug option
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(debugOption);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (debugOption.isSet()) {
	    setDebug(true); 
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
     *  EmitterHandler.  ToolWriters that are creating a new emitter implementation
     *  will need to create their TagHandlers with this EmitterHandler.
     */
    public EmitterHandlerSupport getEmitterHandlerSupport () {
	return m_handler;
    }
    


  /**
    *  Emit the IR's into a set of File(s).
    */
    public  void emit ( IRObj irs[], File files[]) throws TestFileException, IOException {
	int numIRs = irs.length;
	int numFiles = files.length;
	
	if (numIRs != numFiles)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.generic.numouts"));
	
	for (int i=0; i < numFiles; i++) {
	    emit((XMLObj) irs[i], files[i] );
	}
	
    }


  /**
    *  Emit the IR's into a set of File(s).
    */
    public  void emit ( IRObj irs[],  OutputStream outputs[]) throws TestFileException, IOException {
	int numIRs = irs.length;
	int numOutputs = outputs.length;
	
	if (numIRs != numOutputs)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.generic.numouts"));
	
	for (int i=0; i < numOutputs; i++) {
	    emit((XMLObj) irs[i], outputs[i] );
	}
	
    }
   
    
    
    /**
     *  Record the locator for diagnostics messages.
     */
    public void emit (XMLObj root, File xmlTDFile) throws TestFileException, IOException  {
	// Test validity of file
	// ....

	FileOutputStream os = new FileOutputStream(xmlTDFile);
	emit(root, os);
    }
	
    
    /**
     *  Record the locator for diagnostics messages.
     */
    public void emit (XMLObj root, OutputStream stream) throws TestFileException, IOException  {
	m_validator.validate(root);
	m_handler.setStream(stream);
	m_handler.startDocument(root);
	m_handler.endDocument();
	m_handler.setStream(null);
    }

   /* 
    * -------------------------------------------------------------------------------------------- 
    *    utility methods 
    * -------------------------------------------------------------------------------------------- 
    */

 
  /**
    *  Record the locator for diagnostics messages.
    */
    public void setupEmitter (TagHandlerTable handlerTable) throws TestFileException  {
	m_handler.setHandlerTable(handlerTable);
	m_handler.reset();

    }


}
