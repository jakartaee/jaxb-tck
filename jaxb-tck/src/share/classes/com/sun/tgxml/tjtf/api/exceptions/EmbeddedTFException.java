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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.CorrectSourceElement
import java.io.*;
// </importgen>

/**
 * EmbeddedTFException - 
 *
 * <b>EmbeddedTFException</b> is the basic Exception class for the TestDescription library.
 * All throwable exceptions from the library derive from this exception.
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    EmbeddedTFException
 * ============================================================================================
 */


public  class EmbeddedTFException  extends TestFileException {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

    private Exception m_embedded;
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------

    /**
     * Constructs a EmbeddedTFException object.
     *
     * @param embedded  an exception embedded within  this TestFileException.
     */
    public EmbeddedTFException (Exception embedded) {
	super(embedded.getMessage());
	m_embedded = embedded;
    }


    /**
     * Get the (embedded) exception's message.
     *
     * @return The embedded exception's message.
     */
    public String getMessage() {
	return m_embedded.getMessage();
    }


    /**
     * Get the (embedded) exception.
     *
     * @return The embedded exception.
     */
    public Exception getException() {
	return m_embedded;
    }
   

    /**
     * Print the (embedded) exception's stack-trace.
     *
     */
    public void printStackTrace() {
	m_embedded.printStackTrace();
    }

    /**
     * Print the (embedded) exception's stack-trace.
     * @param s The print-stream to use to print the stack-trace.
     */
    public void printStackTrace(PrintStream s) {
	m_embedded.printStackTrace(s);
    }

    /**
     * Print the (embedded) exception's stack-trace.
     * @param s The print-writer to use to print the stack-trace.
     */
    public void printStackTrace(PrintWriter s) {
	m_embedded.printStackTrace(s);
    }
}
     

