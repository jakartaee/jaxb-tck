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

package com.sun.tgxml.tools.testgen;

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *  Utility class for use by various testgen modules
 */
public class LibUtils {
    /**
     * A string which separates test variant name from the source file name.
     * For example: a/b/c/abc001m1[^^]&lt;variant name&gt;.jasm (separator example 
     * is marked here by square brackets).
     * The field is private so that external modules, especially Jmpp libraries,
     * would not be able to rely on particular separator string and would be forced
     * to use methods of this class instead.
     *
     * @see #removeVariantNameFromSource(java.lang.String, java.lang.String)
     * @see #addVariantNameToSource(java.lang.String, java.lang.String)
     */
    private final static String SRC_VAR_NAME_SEPARATOR = "__";

    /**
     * Returns the list of imports for the given LibID.
     */
    public static ArrayList getLibraryImports(String libID) {
        ArrayList result = new ArrayList();
        String classes = BuildProperties.getString("library.id." + libID);
        if (classes == null || classes.trim().length() == 0) {
           // use autonaming scheme, import the whole package        
           // result.add(getLibraryPackage0(libID) + ".*");
           result.add(getLibraryPackage0(libID) + "." + getLibraryClassName(libID));
        } else {
            // use user-defined mapping, import all classes defined
            StringTokenizer tok = new StringTokenizer(classes);
            while (tok.hasMoreTokens()) {
                result.add(tok.nextToken());
            }
        }
        return result;
    }
    

    /**
     * Returns the primary package for the given LibID.
     */    
    public static String getLibraryPackage(String libID) {
        String classes = BuildProperties.getString("library.id." + libID);
        if (classes == null || classes.trim().length() == 0) {
            return getLibraryPackage0(libID);
        } else {
            StringTokenizer tok = new StringTokenizer(classes);
            if (tok.hasMoreTokens()) {
                String cls = tok.nextToken();
                int index =  cls.lastIndexOf(".");
                if (index > 0 && index < cls.length()-1) {
                    return cls.substring(0,index);
                }
            }        
            return "";
        }
    }
    
    
    private static String getLibraryPackage0(String libID) {

	    // Assume that last token in libID is class name, so I will need to strip that off to get 'subPackage'
	    // Assume that lib.packagePrefix is defined

	    String prefix = BuildProperties.getPrefixString("testgen", "lib.packagePrefix", null);
	    String fqn="";

	    if (prefix == null || prefix.trim().length() == 0) {
		fqn = libID;
	    }
	    else {
		fqn = prefix + "." + libID;
	    }

	    int lastDotLoc = fqn.lastIndexOf('.');
	    if (lastDotLoc > 0) {
		return fqn.substring(0,lastDotLoc);
	    }
	    else {
		return ""; // Should never happen
	    }
    }

    public static String getLibraryClassName(String libID) {
	// Assumes that token after last dot is classname, 
	// if no dot is found that entire string is class name (ie a shortname has been passed in)
	String prefix = BuildProperties.getPrefixString("testgen", "lib.packagePrefix", null);
	int lastDotLoc = libID.lastIndexOf('.');
	if (lastDotLoc > 0) {
	    return libID.substring(lastDotLoc+1);
	}
	else {
	    return libID; 
	}
    }


    /**
     * Returns the value of "tdFile" AttrElem, and null if not found
     */    
    public static String getTDFile(TestGroupAttributes tgAttributes) {
        return IR.getAttrElem("tdFile", tgAttributes);
    }

    /**
     * Returns the value of "tdFile" AttrElem, and null if not found
     */    
    public static String getTDFile(TestGroup testGroup) {
        return getTDFile(testGroup.getTGAttributes());
    }

    /**
     * Returns the value of "TestType" AttrElem, and null if not found
     */    
    public static String getTestType(TestGroup testGroup) {
        return IR.getAttrElem("testType", testGroup);
    }

    /**
     * Breaks the file name into the name and extension
     *
     * @param file_name a file name
     * @return an array of two String objects. First is the name part, second
     *     is the extension (with leading dot character)
     */
    private static String[] getNameExtensionPair(String file_name) {
        String[] res = new String[2];
        int i = file_name.lastIndexOf('.');
        if (i < 0) {
            i = file_name.length();
        }
        res[0] = file_name.substring(0, i);
        res[1] = file_name.substring(i);
        return res;
    }

    /**
     * Removes a suffix (if any) consisting of the variant separator and the test variant
     * name from given source file name. For example:
     * <code>a/b/c/abc001m1v_<b>^^xyz</b>.jasm =&gt; a/b/c/abc001m1v_.jasm</code>
     *
     * @param file_name source file name
     * @param var_name test variant name.
     * @return the stripped source name. If the variant name is <code>null</code>, the source
     *     name is simply returned.
     */
    public static String removeVariantNameFromSource(String file_name, String var_name) {
    	if (var_name == null) {
            return file_name;
        }
        String[] nam_ext = getNameExtensionPair(file_name);
        String nam = nam_ext[0];
        String ext = nam_ext[1];
        String suf = SRC_VAR_NAME_SEPARATOR + var_name;

        if (nam.endsWith(suf)) {
            file_name = nam.substring(0, nam.length() - suf.length()) + ext;
        }
        return file_name;
    }

    /**
     * Adds a suffix consisting of the variant separator and the test variant
     * name to given source file name. For example:
     * <code>a/b/c/abc001m1v_.jasm =&gt; a/b/c/abc001m1v_<b>^^xyz</b>.jasm</code>
     *
     * @param file_name source file name
     * @param var_name test variant name.
     * @return the stripped source name. If the variant name is <code>null</code>, the source
     *     name is simply returned.
     */
    public static String addVariantNameToSource(String file_name, String var_name) {
        if (var_name == null) {
            return file_name;
        }
        String[] nam_ext = getNameExtensionPair(file_name);
        String nam = nam_ext[0];
        String ext = nam_ext[1];
        return nam + SRC_VAR_NAME_SEPARATOR + var_name + ext;
    }

    /**
     * Returns true if passed libExport means that library
     * does not provide any exports
     */
    public static boolean isNoExports(String libExport) {
        if (libExport != null && libExport.trim().equals("NONE")) {
            return true;
        } else {
            return false;
        }
    }
    
     /**
      * Returns true if export value for passed in library is 
      * set to NONE
      */
    public static boolean isNoExports(Library lib) {
        ArrayList al = lib.getCodeSet().getExports();
        if (al != null && al.size() > 0) {
            return isNoExports((String)al.get(0));
        }
        return false;
    }
    
    /**
     * Returns the Package name of a library. If none can be determined, 
     * an empty String is returned.:
     */
    public static String detectPackageName(Library lib) {
        try {
            String firstExport = (String)lib.getCodeSet().getExports().get(0);
            firstExport = firstExport.trim();
            if (!isNoExports((String)firstExport)) {
                int c = firstExport.lastIndexOf(".");
                if (c <= 0) {
                    return new String();   
                } 
                return firstExport.substring(0, c);
            }
        } catch (NullPointerException e) {
            // in case of no codeset or no Export specified for library
        } catch (IndexOutOfBoundsException e) {
            // in case of empty Export list
        }
        try {
            return getLibraryPackage(lib.getID().trim());
        }
        catch (TestFileException tfe) {
            return new String();
        }
    }
    

}
