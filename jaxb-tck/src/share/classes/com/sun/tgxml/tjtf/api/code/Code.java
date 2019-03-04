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

package com.sun.tgxml.tjtf.api.code;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.SupportCode
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
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


public  interface Code {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */

    /** Java lang-type. */
    public static final String ctStr_langType_java  = "java";

    /** jcod lang-type. */
    public static final String ctStr_langType_jcod  = "jcod";

    /** jasm lang-type. */
    public static final String ctStr_langType_jasm  = "jasm";

    /** c lang-type. */
    public static final String ctStr_langType_c     = "c";

    /** xml lang-type. */
    public static final String ctStr_langType_xml   = "xml";

    /** jca lang-type. */
    public static final String ctStr_langType_jca   = "jca";

    /** jcasm lang-type. */
    public static final String ctStr_langType_jcasm = "jcasm";

    /** cfg lang-type. */
    public static final String ctStr_langType_cfg   = "cfg";

    /** scr lang-type. */
    public static final String ctStr_langType_scr   = "scr";





    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

  /**
    *   Get the source-language of this code  (default is "java").
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setSourceLang
    */
    public String getSourceLang();
     
  /**
    *   Set the source-language of this code  (default is "java").
    *  <p>
    * @param     sourcelang The string containing the source language.
    * @see #getSourceLang
    */
    public void setSourceLang(String sourcelang);

}

