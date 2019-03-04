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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.documentation.ExpectedResultSideEffect
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * ExpectedResultSideEffect - 
 *
 * <b>ExpectedResultSideEffect</b> is a side-effect that is expected as the
 * outcome of a test.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ExpectedResultSideEffect
 * ============================================================================================
 */


public interface ExpectedResultSideEffect extends ExpectedResult {


    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

   


    //------------------------------------------------------------------------------
    //  Operations
    //------------------------------------------------------------------------------



  /**
    *   Get the side-effect associated with this ExpectedResult.
    *  <p>
    * @return     The side-effect associated with this ExpectedResult
    * @see #setSideEffect
    */
    public String getSideEffect();
     
   /**
    *   Set the side-effect associated with this ExpectedResult.
    *  <p>
    * @param     sideEffect The value for this ExpectedResult.
    * @throws TestFileException if the sideEffect descriptor is invalid.
    * @see #getSideEffect
    */
    public void setSideEffect(String sideEffect) throws TestFileException;
}
