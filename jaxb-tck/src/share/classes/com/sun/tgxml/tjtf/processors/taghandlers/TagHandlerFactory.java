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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.processors.taghandlers.TagHandlerFactory
import java.net.URL;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.sun.tgxml.tjtf.processors.taghandlers.impl.*;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.Library;
// </importgen>

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


public  class TagHandlerFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private TagHandlerFactory () {

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
    //  Defaults Factories
    //------------------------------------------------------------------------------

  /**
    *   Get the default DTD.
    *  <p>
    * @return A URL to the (internally bundled) TestDescription dtd.
    */
    static public URL  getDefaultDTD() {
	return  LibResHandler.getDTDURL("testgenspec");
    }
     
   /** 
    *  Get and initialize the default TagHandlerTable for a given parser and emitter. 
    * <p>
    * @param phs The parser (ParserHandlerSupport).
    * @param ehs The emitter (EmitterHandlerSupport).
    * @return A (default TD) TagHandlerTable.
    * @throws TestFileException if there is a problem setting the support in any TagHandler.
    */ 
    static public TagHandlerTable getDefaultHandlerTable(ParserHandlerSupport phs, 
							 EmitterHandlerSupport ehs) throws TestFileException {
	TagHandlerTable handlerTable = getDefaultHandlerTable();
	setParserSupport(handlerTable, phs);
	setEmitterSupport(handlerTable, ehs);
	  
	return handlerTable;
    }


   /** 
    *  Create a default HandlerTable - creates Handlers for the 
    *  TestDescription (dtd). 
    * <p>
    * @return A (default TD) TagHandlerTable.
    * @throws TestFileException if there is a problem setting the support in any TagHandler.
    */ 
    static public TagHandlerTable getDefaultHandlerTable() throws TestFileException {
	// set-up the Tag handler-table
	TagHandlerTable handlerTable = new TagHandlerTable();
	TagHandlerImpl impl;

	handlerTable.put( new TestGroup_TH());
	handlerTable.put( new TestCase_TH());
	handlerTable.put( new Library_TH());
	handlerTable.put( new TestGroupDocumentation_TH());
	handlerTable.put( new LibraryDocumentation_TH());
	handlerTable.put( new TestCaseDocumentation_TH());
	handlerTable.put( new TestGroupAttributes_TH());
	handlerTable.put( new LibraryAttributes_TH());
	handlerTable.put( new TestCaseAttributes_TH());
	handlerTable.put( new CodeSet_TH());
	handlerTable.put( new Dependency_TH());
	handlerTable.put( new Lib_TH());
	handlerTable.put( new Import_TH());
	handlerTable.put( new BaseClass_TH());
	handlerTable.put( new Export_TH());
	handlerTable.put( new TestCode_TH());
	handlerTable.put( new SupportCode_TH());
	handlerTable.put( new ExternalSupportClass_TH());
	handlerTable.put( new InlineSupportClass_TH());
	handlerTable.put( new CodeSource_TH());
	handlerTable.put( new ExternalData_TH());
	handlerTable.put( new InlineData_TH());
	handlerTable.put( new Title_TH());
	handlerTable.put( new Description_TH());
	handlerTable.put( new AssertionRef_TH());
	handlerTable.put( new InlineAssertion_TH());
	handlerTable.put( new TestedPackage_TH());
	handlerTable.put( new TestedClass_TH());
	handlerTable.put( new DocElem_TH());
	handlerTable.put( new Author_TH());
	handlerTable.put( new TestCaseSpec_TH());
	handlerTable.put( new TestTechnique_TH());
	handlerTable.put( new MemberSig_TH());
	handlerTable.put( new Input_TH());
	handlerTable.put( new Precondition_TH());
	handlerTable.put( new ExpectedResultValue_TH());
	handlerTable.put( new ExpectedResultSideEffect_TH());
	handlerTable.put( new ExpectedResultException_TH());
	handlerTable.put( new SpecElem_TH());
	handlerTable.put( new RequiredResource_TH());
	handlerTable.put( new AttrElem_TH());
	handlerTable.put( new TargetSpec_TH());
	handlerTable.put( new TargetSpecElem_TH());
	handlerTable.put( new Keyword_TH());
	handlerTable.put( new Context_TH());
	handlerTable.put( new ExecuteArgs_TH());
	handlerTable.put( new ExecuteClass_TH());
	handlerTable.put( new ExecuteNative_TH());
	handlerTable.put( new Remote_TH());
	handlerTable.put( new RMICClasses_TH());
	handlerTable.put( new SelectIf_TH());
	handlerTable.put( new Timeout_TH());

	// Describe the root objects.
	handlerTable.addRootAssociation (TestGroup.class, TagsImpl.ctStr_tag_testgroup);
	handlerTable.addRootAssociation (Library.class, TagsImpl.ctStr_tag_library);

	URL dtdURL = LibResHandler.getDTDURL("testgenspec");
	handlerTable.setDocURI(dtdURL.toString());
	handlerTable.setDocName("testgenspec.dtd");

	return handlerTable;
    }




    //------------------------------------------------------------------------------
    //  Support registration
    //------------------------------------------------------------------------------

   /** 
    *  For the given TagHandlerTable, set all of it's TagHandlers to use the given Parser (ParserHandlerSupport). 
    * <p>
    * @param table The TagHandlerTable.
    * @param phs The Parser (ParserHandlerSupport)
    * @throws TestFileException If there is a problem with setting the support in a TagHandler.
    */ 
    static public void setParserSupport(TagHandlerTable table, ParserHandlerSupport phs) throws TestFileException {
	// set-up the Tag handler-table
	TagHandlerImpl handler;

	Iterator it = table.iterator();
	while (it.hasNext()) {
	    handler = (TagHandlerImpl) it.next();
	    handler.setParserHandler(phs);
	}
    }


   /** 
    *  For the given TagHandlerTable, set all of it's TagHandlers to use the given Emitter (EmitterHandlerSupport). 
    * <p>
    * @param table The TagHandlerTable.
    * @param ehs The Emitter (EmitterHandlerSupport)
    * @throws TestFileException If there is a problem with setting the support in a TagHandler.
    */ 
    static public void setEmitterSupport(TagHandlerTable table, EmitterHandlerSupport ehs) throws TestFileException {
	// set-up the Tag handler-table
	TagHandlerImpl handler;

	Iterator it = table.iterator();
	while (it.hasNext()) {
	    handler = (TagHandlerImpl) it.next();
	    handler.setEmitterHandler(ehs);
	}
    }


}
