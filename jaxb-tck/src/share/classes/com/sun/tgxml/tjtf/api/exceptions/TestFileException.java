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

package com.sun.tgxml.tjtf.api.exceptions;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.exceptions.TestFileException
// </importgen>

/**
 * TestFileException - 
 *
 * <b>TestFileException</b> is the basic Exception class for the TestDescription library.
 * All throwable exceptions from the library derive from this exception.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestFileException
 * ============================================================================================
 */


public  class TestFileException  extends Exception {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

    /**
     * Constructs a TestFileException object.
     *
     * @param message  a an exception message string.
     */
    public TestFileException (String message) {
	super(message);
    }
}
     

