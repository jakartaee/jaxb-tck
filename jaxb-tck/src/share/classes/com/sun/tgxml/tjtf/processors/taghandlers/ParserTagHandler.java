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

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;


 
/** 
 * ParserTagHandler - The tag-handler abstraction. This is the
 * contract that a TagHandler must support (from the parser's perspective).
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ParserTagHandler 
 * ============================================================================================ 
 */ 
public interface ParserTagHandler extends TagHandler  {


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
    //  Handlers
    //------------------------------------------------------------------------------


  /**
    *   Start handling a given XML tag.
    *  <p>
    * @param attrs The (SAX) attributes associated with this entity tag.
    * @throws SAXException for any (semantic) parsing problem.
    * @see #endTag
    */
    public void startTag(org.xml.sax.Attributes attrs) throws SAXException;


  /**
    *   End handling a given XML tag.
    *  <p>
    * @throws SAXException for any (semantic) parsing problem.
    * @see #startTag
    */
    public void endTag() throws SAXException;
          

  /**
    *   Set the Handler.  The ParserHandlerSupport is the (internal) contract
    * that the parser provides to the TagHandler for IO and delegation functions.
    *  <p>
    * @param handler The parser that fulfills the ParserHandlerSupport contract.
    */
    public void setParserHandler(ParserHandlerSupport handler);
          

}
