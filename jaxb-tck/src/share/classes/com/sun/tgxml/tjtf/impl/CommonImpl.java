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

package com.sun.tgxml.tjtf.impl;

// <importgen> Generated imports for class: com.sun.specinfo.emitter.XMLPlatformEmitter
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.StringTokenizer;
// </importgen>

/**
 * CommonImpl - 
 *
 * <b>CommonImpl</b> contains some static helper functions.
 * <p>
 *
 *
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    CommonImpl
 * ============================================================================================
 */


public  class CommonImpl  {
   /*
    * ============================================================================================
    *    Member Fields
    * ============================================================================================
    */
    
   /*
    * ============================================================================================
    *    Methods
    * ============================================================================================
    */
    
    private CommonImpl() {}


    //---------------------------------------------------------------------------------
    // Tokenization
    //---------------------------------------------------------------------------------

    public static String getSingleToken(String str) throws TestFileException {
	if (str == null || str.equals(""))
	    return null;

	String token = "";

	// clear any space before
	StringTokenizer parser = new StringTokenizer(str, " \t\n\r");
	if (! parser.hasMoreTokens())
	    return null;
	token = parser.nextToken();
	
	if (parser.hasMoreTokens())
	    throw new TestFileException(LibResHandler.getResStr("parser.error.token.extra", str));

	return token;
    }




}
