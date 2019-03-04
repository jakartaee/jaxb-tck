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

package com.sun.tgxml.tjtf.api.documentation;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.InlineAssertion
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * InlineAssertion - 
 *
 * <b>InlineAssertion</b> is an internal statement (specification assertion)
 * that is to be tested. An InlineAssertion contains the text of the assertion.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineAssertion
 * ============================================================================================
 */


public interface InlineAssertion extends Assertion {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   

    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the inline value associated with this assertion.
    *  <p>
    * @return     The value associated with this assertion
    * @see #setValue
    */
    public String getValue();

   /**
    *   Set the inline value associated with this assertion.
    *  <p>
    * @param     value The value of the assertion.
    * @throws TestFileException if the value descriptor is invalid.
    * @see #getValue
    */
    public void setValue(String value) throws TestFileException;

}
