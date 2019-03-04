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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.tools.Shell
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * Shell - 
 *
 * <b>Shell</b> is a generic contract for services that a Tool BaseClass provides to
 * it's component processors.
 * <p>
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Shell
 * ============================================================================================
 */


public  interface Shell  {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

 
   /* 
    * -------------------------------------------------------------------
    *    I/O Functions - provide conduit for streaming messages.
    * -------------------------------------------------------------------
    */   

 
  /**
    * output a message.
    * @param msg the message to output.
    */
    public void reportOutputMsg(String msg);


  /**
    * output an error.
    * @param msg the error message to output.
    */
    public void reportErrorMsg(String msg);

 
  /**
    * log a message.
    * @param msg the message to output.
    */
    public void log(String msg) throws TestFileException;

  }
