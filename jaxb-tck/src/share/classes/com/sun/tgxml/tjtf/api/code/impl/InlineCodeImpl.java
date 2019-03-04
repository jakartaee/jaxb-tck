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

package com.sun.tgxml.tjtf.api.code.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.impl.InlineCodeImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.InlineCode;
// </importgen>

/**
 * InlineCode - 
 *
 * <b>InlineCode</b> is the interface to a class structure that describes code that is inlined in a test.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineCodeImpl  
 * ============================================================================================
 */


public  class InlineCodeImpl extends CodeImpl implements InlineCode {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private String m_source;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public InlineCodeImpl() {
	super();
	init();
    }

   public InlineCodeImpl(String langtype, String source) {
       super(langtype);
       setSource(source);
    }

    private void init() {
	m_source = "";
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------
   

  /**
    *   Get the source.
    *  <p>
    * @return   A string containing the inline'd source.
    * @see #setSource
    */
    public String getSource() {
	return m_source;
    }
     
  /**
    *   Set the source.
    *  <p>
    * @param     source The string containing the source.
    * @see #getSource
    */
    public void setSource(String source) {
	m_source = source;
    }

}

