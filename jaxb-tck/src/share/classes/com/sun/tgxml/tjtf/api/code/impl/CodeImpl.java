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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.impl.CodeImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.Code;
// </importgen>

/**
 * Code - 
 *
 * <b>Code</b> is the marker interface to a class structure that describes the root of a test description.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Code  (marker interface)
 * ============================================================================================
 */


public  class CodeImpl implements Code {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */

    private static final String ctStr_langType_default = ctStr_langType_java;

    private String m_sourcelang;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public CodeImpl() {
	init();
    }

   public CodeImpl(String langType) {
	setSourceLang(langType);
    }

    private void init() {
	m_sourcelang = ctStr_langType_default;
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the source-language of this code  (default is "java").
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setSourceLang
    */
    public String getSourceLang() {
	return m_sourcelang;
    }
     
  /**
    *   Set the source-language of this code  (default is "java").
    *  <p>
    * @param     sourcelang The string containing the source language.
    * @see #getSourceLang
    */
    public void setSourceLang(String sourcelang) {
	if (sourcelang == null || sourcelang.equals(""))
	    m_sourcelang = ctStr_langType_default;
	else
	    m_sourcelang = sourcelang;
    }

}

