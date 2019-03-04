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

import com.sun.tgxml.tjtf.resources.LibResHandler;


 
/** 
 * TagHandler - The tag-handler abstraction. 
 * XML processors (parsers and emitters) get their abstraction
 * by defining a set of (plug-in) TagHandlers whose function is to
 * handle a tag.  So different parsers/emitters formats can be defined by
 * different sets of TagHandlers that "plug-into" them.
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    TagHandler 
 * ============================================================================================ 
 */ 
public interface TagHandler   {



  /**
    *   Get the name associated with this tag.
    *  <p>
    * @return The String that identifies the tag.
    */
     public String getTagName();
}
