/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tjtf.processors.taghandlers.impl;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.SAXException;

import com.sun.tgxml.tjtf.api.documentation.DocumentationFactory;
import com.sun.tgxml.tjtf.api.documentation.SpecElem;
import com.sun.tgxml.tjtf.api.documentation.TestCaseSpec;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.impl.TagsImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler;


/**
 * SpecElem_TH - The tag-handler for a SpecElem tag.
 *
 *
 * @version     1.0, 10/02/00
 * @author Kevin T. Looney
 */


/*
 * ============================================================================================
 *    SpecElem_TH
 * ============================================================================================
 */
public class SpecElem_TH extends NameValueTagHandler  {


   /*
    * ============================================================================================
    *    Fields
    * ============================================================================================
    */




   /*
    * ============================================================================================
    *    Methods
    * ============================================================================================
    */


    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /**
    *   SpecElem_TH constructor -
    *       Initialize our internal fields.
    */
    public SpecElem_TH( ) {
    super( );

    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
    return TagsImpl.ctStr_tag_specelem;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String name, String value) throws SAXException {
    try {
        super.endTag(name, value);

        Stack testItemStack = m_ParserHandler.getStack();
        Object testitem = testItemStack.peek();

        if (testitem == null)
        m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

        if (! (testitem instanceof TestCaseSpec)  )
        m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testcasespec));

        //  Nothing is pushed onto the stack
        TestCaseSpec tcs = (TestCaseSpec) testitem;

        ArrayList specelems = tcs.getSpecElems();
        if (specelems == null) {
        specelems = new ArrayList();
        tcs.setSpecElems(specelems);
        }

        SpecElem se = DocumentationFactory.createSpecElem(name, value);
        // validated the import name
        // validateSpecElem(se);
        specelems.add(se);
    } catch (TestFileException e) {
        m_ParserHandler.throwError(e.getMessage());
    }
    }


    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------

    // NameValueTagHandler handles emitter.


}
