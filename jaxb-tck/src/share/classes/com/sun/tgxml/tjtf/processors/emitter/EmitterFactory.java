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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.tests.Testgroup
import java.net.URL;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.emitter.impl.*;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * EmitterFactory - 
 *
 * <b>EmitterFactory</b> is a static factory class for creating XMLEmitters. 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    EmitterFactory
 * ============================================================================================
 */


public  class EmitterFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private EmitterFactory () {

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
    *   Create a XMLEmitter.
    *  <p>
    * @return   a default XML TestDescription emitter.
    * @throws TestFileException if there is a problem with the tag handlers.
    * @see com.sun.tgxml.tjtf.processors.emitter.XMLEmitter
    */
    static public XMLEmitter  createDefaultXMLEmitter() throws TestFileException  {
	XMLEmitterImpl emitter = (XMLEmitterImpl) createXMLEmitter();
	TagHandlerTable table = TagHandlerFactory.getDefaultHandlerTable();
	TagHandlerFactory.setEmitterSupport(table, emitter.getEmitterHandlerSupport());
	emitter.setupEmitter(table);

	return emitter;
    }
     

  /**
    *   Create a XMLEmitter with an existing TagHandlerTable.
    *  <p>
    * @param table   a set of TagHandlers describing how each XML tag should be emitted.
    * @return   a default XML TestDescription emitter.
    * @throws   TestFileException if there is a problem creating an emitter.
    * @see com.sun.tgxml.tjtf.processors.emitter.XMLEmitter
    */
    static public XMLEmitter  createXMLEmitter(TagHandlerTable table) throws TestFileException  {
	XMLEmitterImpl emitter = (XMLEmitterImpl) createXMLEmitter();
	TagHandlerFactory.setEmitterSupport(table, emitter.getEmitterHandlerSupport());
	emitter.setupEmitter(table);

	return emitter;
    }
 /**
    *   Create a XMLEmitter with an existing TagHandlerTable.
    *  <p>
    * @return   a default XML TestDescription emitter.
    * @throws TestFileException if there is a problem creating an emitter.
    * @see com.sun.tgxml.tjtf.processors.emitter.XMLEmitter
    */
    static public XMLEmitter  createXMLEmitter() throws TestFileException  {
	XMLEmitterImpl emitter = new XMLEmitterImpl();

	return emitter;
    }
     
     



}
