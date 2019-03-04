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

package com.sun.tgxml.tjtf.resources; 
 
import java.io.InputStream; 
import java.io.IOException; 
import java.text.MessageFormat; 
import java.io.File; 
import java.io.BufferedInputStream; 
import java.io.FileInputStream; 
import java.util.Properties; 
import java.util.Enumeration; 
import java.util.MissingResourceException; 
import java.util.Vector; 
 
 
/** 
 * This is just a set of utils for error string substitution. 
 * 
 * @version 	1.16, 08/17/97 
 * @author 	Kevin T. Looney 
 */ 
public class ResUtils { 
 
    static public String handleGetSubst( String patt, String arg) { 
	    String[] s = { arg }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, String arg2) { 
	    String[] s = { arg1, arg2 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3) { 
	    String[] s = { arg1, arg2, arg3 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4) { 
	    String[] s = { arg1, arg2, arg3, arg4 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5, String arg6) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 , arg6 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5, 
				  String arg6, String arg7) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 , arg6, arg7 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5, 
				  String arg6, String arg7, String arg8) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 , arg6, arg7, arg8 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5, 
				  String arg6, String arg7, String arg8, String arg9) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 , arg6, arg7, arg8, arg9 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String arg1, 
				  String arg2, String arg3, String arg4, String arg5, 
				  String arg6, String arg7, String arg8, String arg9, String arg10) { 
	    String[] s = { arg1, arg2, arg3, arg4, arg5 , arg6, arg7, arg8, arg9, arg10 }; 
	    return handleGetSubst(patt, s); 
    } 
 
    static public String handleGetSubst( String patt, String[] args) { 
	if (patt == null) { 
	    return null; 
	} 
	
	for (int i = 0; i < args.length; i++) { 
	    if (args[i] == null) { 
		args[i] = "null"; 
	    } 
	} 
	
	String retVal = null; 
	
	try  { 
	    retVal = MessageFormat.format(patt, args); 
	} 
	catch (NullPointerException e)  { 
	    System.out.println("Res.subst(): Exception occured: "+e); 
	} 
	
	return retVal; 
    } 
} 


