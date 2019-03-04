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
import java.util.Stack;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * ParserHandlerSupport - The contract of methods that a (XML) ParserHandler must provide 
 * in order to support ParserTagHandlers. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ParserHandlerSupport 
 * ============================================================================================ 
 */ 
public interface ParserHandlerSupport   {





   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *  Get the (XML TD) ParserHandler's stack. 
    * <p>
    * @return a Stack of the items in the contect of the currently parsed item.
    */ 
    public Stack getStack();

  /**
    *  Set the root of the Java-object parse tree.
    * <p>
    * @param root The generic XMLObj.
    * @see #getRoot
    */
    public void setRoot (XMLObj root);


  /**
    *  Get the root of the Java-object parse tree.
    * <p>
    * @return a XMLObj IR object.
    * @see #setRoot
    */
    public XMLObj getRoot ();

  /**
    *  Report (throw) a semantic error through the ParserHandler.
    * <p>
    * @param error The error message to embed in the SAXException
    * @throws SAXException for any error that needs to be passed through the SAX parser.
    */
    public void throwError (String error)  throws SAXException;

  /**
    *  Report (throw) a semantic error through the ParserHandler.
    * <p>
    * @param error The error to embed in the SAXException
    * @throws SAXException for any error that needs to be passed through the SAX parser.
    */
    public void throwError (Exception error)  throws SAXException;

  /**
    *  Report (throw) a semantic error through the m_ParserHandler.
    * <p>
    * @param error The error to embed in the SAXException
    * @param message The message on the (outside) exception wrapper.
    * @throws SAXException for any error that needs to be passed through the SAX parser.
    */
    public void throwError (Exception error, String message)  throws SAXException;


  /**
    *  Log Diagnostics.
    * <p>
    * @param message The message on the (outside) exception wrapper.
    */
    public void log (String message) throws TestFileException;

  /**
    *  Test the debug flag.
    * <p>
    * @return true if the parser is in debug mode.
    */
    public boolean getDebug ();


  /**
    *  Get the running text stream for a given tag.
    * <p>
    *  The parser accuulates free-running text in-between tags,
    *  and this method passes that string to a tag-handler.
    * <p>
    * @return A string of text.
    */
    public String getTextStream ();
          

}
