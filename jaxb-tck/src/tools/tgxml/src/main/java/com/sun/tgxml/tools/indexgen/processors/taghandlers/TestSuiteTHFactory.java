/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.tgxml.tools.indexgen.processors.taghandlers;

import java.net.URL;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerFactory;
import com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerTable;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.AttrElem_TH;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.Comments_TH;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.Contents_TH;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.Description_TH;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.TestSuiteTagsImpl;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.TestSuite_TH;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.Title_TH;


/**
 * TagHandlerFactory - 
 *
 * <b>TagHandlerFactory</b> is used to create default TagHandlers. 
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


public  class TestSuiteTHFactory {


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
    //  Defaults Factories
    //------------------------------------------------------------------------------

  /**
    *   Get the default DTD.
    *  <p>
    * @return A URL to the (internally bundled) TestDescription dtd.
    */
    static public URL  getTestSuiteDTD() {
	return  LibResHandler.getDTDURL("testsuite");
    }
     
   /** 
    *  Get and initialize the default TagHandlerTable for a given parser and emitter. 
    * <p>
    * @param phs The parser (ParserHandlerSupport).
    * @param ehs The emitter (EmitterHandlerSupport).
    * @return A TestSuite TagHandlerTable.
    * @throws TestFileException if there is a problem setting the support in any TagHandler.
    */ 
    static public TagHandlerTable getTestSuiteHandlerTable(
						   ParserHandlerSupport phs, 
						  EmitterHandlerSupport ehs) throws TestFileException {
		TagHandlerTable handlerTable = getTestSuiteHandlerTable();
		TagHandlerFactory.setParserSupport(handlerTable, phs);
		TagHandlerFactory.setEmitterSupport(handlerTable, ehs);
		
		return handlerTable;
    }


   /** 
    *  Create a default HandlerTable - creates Handlers for the 
    *  TestDescription (dtd). 
    * <p>
    * @return A TestSuite TagHandlerTable.
    * @throws TestFileException if there is a problem setting the support in any TagHandler.
    */ 
    static public TagHandlerTable getTestSuiteHandlerTable() throws TestFileException {
		
         TagHandlerTable table = new TagHandlerTable();
		 table.put(new TestSuite_TH());
		 table.put(new Title_TH());
		 table.put(new Description_TH());
		 table.put(new Contents_TH());
		 table.put(new Comments_TH());
         table.put(new AttrElem_TH());
		 table.addRootAssociation(TestSuite.class, TestSuiteTagsImpl.ctStr_tag_testsuite);
		 table.setDocURI(LibResHandler.getDTDURL("testsuite").toString());
         table.setDocName("testsuite.dtd");
		 return table;
    }

}
