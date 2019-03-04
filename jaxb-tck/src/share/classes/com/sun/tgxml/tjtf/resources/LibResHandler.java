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
 
import java.util.ResourceBundle; 
import java.util.MissingResourceException;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
 
 
 
 
/** 
 * LibResHandler - functionality for loading, and parsing errors 
 * 
 * 
 * @version 	1.0, 08/20/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    LibResHandler 
 * ============================================================================================ 
 */ 
public class  LibResHandler { 
 
   // KTL 
  // These properties have methods for doing the parameter substitution 
  // on error messages - such that we don't have the reliance on HotJava 
  // Globals, properties, etc. 
    static protected ResourceBundle   resources; 
 
    static String dtdPath = "."; 
    static Class  resLoader = null; 
    static String progname = ""; 

    static {
	resLoader = (new LibResHandler()).getClass();
	if (! initResource())
	    System.exit(0);
    }

    static protected boolean initResource() { 
        boolean retval = false; 
            ResourceBundle resources = new SpecLibRes(); 
        try { 
             resources = ResourceBundle.getBundle("com.sun.tgxml.tjtf.resources.SpecLibRes"); 
             LibResHandler.setResources(resources); 
             retval = true; 
        } catch(MissingResourceException e) { 
            System.err.println("TestGen: Couldn't open Resources (SpecLibRes)."); 
        } 
 
        return retval; 
    } 

    public LibResHandler() {
	// constructor for local resLoader
    }
 
    static public void setLibraryName(String newProgname) { 
       progname = newProgname; 
    } 
    static public void setResLoader(Class newResLoader) { 
       resLoader = newResLoader; 
    } 

    static public void setPath(String newPath) { 
        String fileSep = System.getProperty("file.separator"); 
        dtdPath = newPath + fileSep + "dtd" + fileSep; 
    } 
 
 
    static public String getDTDPath() { 
        return dtdPath; 
    } 
 

     static public URL getDTDURL(String dtdName) { 
        if (dtdName != null && resLoader != null) {
            String fullDTDName = "/com/sun/tgxml/tjtf/resources/dtd/" + dtdName + ".dtd";
	    return resLoader.getResource(fullDTDName);
	}
	return null;
     }

     static public int getResInteger(String intName) { 
       String intStr = getMsg(intName);
       return (new Integer(intStr)).intValue();
     }

     static public String getResConcStr(String stringName) { 

       int numLines = getResInteger(stringName + ".lines");
       String msg = "";
       for (int i = 1; i <= numLines; i++) {
	 msg += getMsg(stringName + (new Integer(i)).toString());
       }
       return msg;
     }


  // KTL 
  // I changed this such that we can set properties to our own set 
  // instead of HotJava's set. 
    /** 
     * set the properties 
     */ 
    static public void setResources(ResourceBundle rb) { 
        resources = rb; 
    } 
 
 
   /** 
     * Invoke the output handler. 
     */ 
    static public String getResStr(String err, String arg1, String arg2, String arg3,
				   String arg4, String arg5, String arg6, String arg7,
				   String arg8, String arg9, String arg10) 
{ 
	    String str = null; 
	    String str2 = null; 
	    if (arg1.equals(""))
	        arg1 = progname;
	    if (resources != null) 
	        str2 = getMsg(err); 
	        if (str2 != null) 
	            str = ResUtils.handleGetSubst(str2, arg1, arg2, arg3,
						  arg4, arg5, arg6, arg7,
						  arg8, arg9, arg10); 
	    else 
	      str = err + ":: " + arg1 + " " + arg2 + " " + arg3 + "..."; 


	    if (str == null) { 
	        str = err; 
	    } 
 
 
	    return str; 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4, String arg5,
				   String arg6, String arg7, String arg8, String arg9) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4, String arg5,
				   String arg6, String arg7, String arg8) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4, String arg5,
				   String arg6, String arg7) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, arg5, arg6, arg7, "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4, String arg5,
				   String arg6) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, arg5, arg6, "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4, String arg5) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, arg5, "?", "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3, String arg4) { 
	    return getResStr(err, arg1, arg2, arg3, arg4, "?", "?", "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2,
				   String arg3) { 
	    return getResStr(err, arg1, arg2, arg3, "?", "?", "?", "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1, String arg2) { 
	    return getResStr(err, arg1, arg2, "?", "?", "?", "?", "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err, String arg1) { 
	    return getResStr(err, arg1, "?", "?", "?", "?", "?", "?", "?", "?", "?"); 
    } 
 
    static public String getResStr(String err) { 
	    return getResStr(err, "?", "?", "?", "?", "?", "?", "?", "?", "?", "?"); 
    } 
 
 
 
 
    // Get localized message 
    static public String getMsg(String key) { 
        if (resources != null) { 
            try { 
                return resources.getString(key); 
            } catch(MissingResourceException e) { 
                System.err.println("missing "+key+" from Resource Bundle"); 
		e.printStackTrace();
                return null; 
            } 
        } 
        else 
            return null; 
    }       
}
