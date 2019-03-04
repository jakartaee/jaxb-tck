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

package com.sun.tgxml.tools.indexgen.processors.parser;



import java.io.IOException;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.parser.*;
import com.sun.tgxml.tjtf.processors.parser.impl.*;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.*;

import com.sun.tgxml.tools.indexgen.processors.taghandlers.TestSuiteTHFactory;
/**
 * ParserFactory - 
 *
 * <b>ParserFactory</b> is a static factory class for creating Parser implementations. 
 *<p>
 *
 */


/*
 * ============================================================================================
 *    ParserFactory
 * ============================================================================================
 */


public  class TestSuiteParserFactory {

   
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
    static public XMLParser  createTestSuiteParser() throws TestFileException, IOException  {
			// get the default DTD and TagHandlers.
			// reuse them in parser and emitter (to conserve objects).
		TagHandlerTable table = TestSuiteTHFactory.getTestSuiteHandlerTable();

		return  (XMLParser)ParserFactory.createXMLParser(LibResHandler.getDTDURL("testsuite"), table);

    }
}
