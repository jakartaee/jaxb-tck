/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.elgen;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.util.IR;


/**
 *  
 */
public class ExcludeListUtils {


    /** 
     *  The name of Excluded AttrElem 
     */
    public final static String ExcludedAttrElemName = "Excluded";

    public final static String TestDescriptionURLAttrElemName = "TestDescriptionURL";
    public final static String TestCaseNameAttrElemName = "TestCaseName";

	/** 
	 * check does the TestItem have "Excluded" AttrElem
	 */
    public static boolean isMarkedExcluded (TestItem t) {
        return (IR.getAttrElem(ExcludedAttrElemName, t) != null);
	}


   /** 
     *  Adds the "Exclude" AttrElem 
     */
    protected static void addExcludedElem(TestItem ti, ExcludeEntry e) 
    							throws IncorrectAttributesException {

        StringBuffer buf = new StringBuffer();
        addItem(buf, e.getBugIDs().toString());
        addItem(buf, e.getKeywords().toString());
        addItem(buf, e.getComments());
		IR.setAttrElem(ti, ExcludedAttrElemName, buf.toString());
    }

    private static void addItem(StringBuffer buf, String item) {
        if (item == null || item.equals("")) {
            return;
        }
        if (buf.length() > 0) {
             buf.append(" ");
        }
        buf.append(item);
    }


    /** 
     *  The method always returns some string for ID of the TestItem
     */
    public static String getID(TestItem ti) {
		try {
			return ti.getID();
		} catch (Exception e) {
			return "<not set>";
		}
    }

    /** 
     *   Determines the relative directory of the given test item.
     */
    public static String getSourceDir(TestItem ti) throws IncorrectAttributesException {
        try {    
            String path = IR.getAttrElem(IR.relSourcePathAttrElemName, ti);
            if (path == null) {
                   throw new IncorrectAttributesException(LibResHandler.getResStr("elgen.error.no_rel_sourcepath", ti.getID()));
            } else {
                File f = new File(path);
                path = f.getParent();
                path = (path == null ? null : path.replace(File.separatorChar, '/'));                
            }
            return path;
        } catch (TestFileException e) {
            throw new IncorrectAttributesException(e.getMessage());
        }
    }

	
}
