/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.indexgen.processors.emitter;


import java.net.URL;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.emitter.impl.*;
import com.sun.tgxml.tjtf.processors.emitter.EmitterFactory;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tools.indexgen.processors.taghandlers.TestSuiteTHFactory;

/**
 * EmitterFactory - 
 *
 * <b>EmitterFactory</b> is a static factory class for creating XMLEmitters. 
 *<p>
 *
 */


/*
 * ============================================================================================
 *    EmitterFactory
 * ============================================================================================
 */


public  class TestSuiteEmitterFactory {


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
    static public XMLEmitter  createTestSuiteEmitter() throws TestFileException  {
		XMLEmitterImpl emitter = (XMLEmitterImpl) EmitterFactory.createXMLEmitter();
		TagHandlerTable table = TestSuiteTHFactory.getTestSuiteHandlerTable();
		TagHandlerFactory.setEmitterSupport(table, emitter.getEmitterHandlerSupport());
		emitter.setupEmitter(table);
		return emitter;
    }

}
