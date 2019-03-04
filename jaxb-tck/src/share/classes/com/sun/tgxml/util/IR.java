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

package com.sun.tgxml.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

import com.sun.tgxml.tjtf.api.code. LibraryDependency;
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
import com.sun.tgxml.tools.indexgen.api.TestSuite;

/**
 *  Utils to access IR trees
 */
public class IR {

    public final static String SourcePathAttrElemName = "SourcePath";
    public final static String relSourcePathAttrElemName = "relSourcePath";
    public final static String serialTestTypeAttrElemName = "serialTestType";
    public final static String testSubPackageAttrElemName = "testSubPackage";

    /** 
     *  The method always returns some string for ID of the TestItem
     */
    public static String getID(TestItem ti) {
	try {
	    return ti.getID().trim();
	} catch (Exception e) {
	    return "<not set>";
	}
    }
//=================================================================================
    /** 
     *   Tries to find specified AttrElem of the TestItem
     *
     */
    public static String getAttrElem(String name, TestItem ti) {        
        Attributes attrs = ti.getAttributes();
    	if (attrs == null) {
            return null;
        } else {
            return getAttrElem(name, attrs);
        }
    }

    public static String getAttrElem(String name, Attributes attrs) {
        ArrayList attrElems = attrs.getAttrElems();
        if (attrElems == null) { 
            return null;
        } else {
            return getAttrElem(name, attrElems);
        }
    }

    public static String getAttrElem(String name, TestSuite ts) {
        ArrayList attrElems = ts.getAttrElems();
        if (attrElems == null) { 
            return null;
        } else {
            return getAttrElem(name, attrElems);
        }
    }     

    public static String getAttrElem(String name, ArrayList attrElems) {
        if (name == null) { 
             return null;
        }
        Iterator it = attrElems.iterator();
        while (it.hasNext()) {
            AttrElem elem = (AttrElem)it.next();
            if (name.equals(elem.getName())) {
                String value = elem.getValue();
                return ( value == null ) ? "" : value;
            }
        }
        return null;
    }
    

   /** set value of AttrElem for TestGroup and TestCase items. 
    *  Add the new AttrElem if needed.
    *  @return  null if the AttrElem had no value;
    *           old value of the AttrElem
    */

    public static String setAttrElem(TestItem ti, String attrElemName, String value) {
        Attributes attrs = ti.getAttributes();
        if (attrs == null) {
            attrs = createAttributes(ti);
            setAttributes(ti, attrs);
        }
        return setAttrElem(attrs, attrElemName, value);
    }    	

    public static String setAttrElem(TestSuite ts, String attrElemName, String value) {
        ArrayList attrElems = ts.getAttrElems();
        if (attrElems == null) {
            attrElems = new ArrayList(1);
            ts.setAttrElems(attrElems);
        }
        return setAttrElem(attrElems, attrElemName, value);
    }
    
    public static String setAttrElem(Attributes attrs, String attrElemName, String value) {
        ArrayList attrElems = attrs.getAttrElems();
        if (attrElems == null) {
            attrElems = new ArrayList(1);
            attrs.setAttrElems(attrElems);
        }        
        return setAttrElem(attrElems, attrElemName, value);
    }
    

    public static String setAttrElem(ArrayList attrElems, String attrElemName, String value) {

        Iterator it = attrElems.iterator();
	    while (it.hasNext()) {
            AttrElem ae = (AttrElem) it.next();
	        if (ae.getName().equals(attrElemName)) {
                String old = ae.getValue();
                ae.setValue(value);
                return old;
            }
        }    
        // No such AttrElem found, create new one
        AttrElem ae = AttributesFactory.createAttrElem();
        try {
            ae.setName(attrElemName);
            ae.setValue(value);
        } catch (TestFileException tfe) {
            // this should never happen
        }
        attrElems.add(ae);
        return null;
    }
    

    /** 
     * Tries to find all specified AttrElems of the TestItem
     * Return ArrayList of found AttrElem values or null if nothing if found.
     */
    public static ArrayList getAllAttrElems(String name, TestItem ti) {
        try {
            ArrayList attrElems = ti.getAttributes().getAttrElems();
            ArrayList result = new ArrayList();

            Iterator it = attrElems.iterator();
            while (it.hasNext()) {
                AttrElem elem = (AttrElem)it.next();
                if (name.equals(elem.getName())) {
                    result.add(elem.getValue());
                }
            }
            return ( result.size() == 0 ) ? null : result;
        } catch (NullPointerException e) {
            return null;
        }
    }

    
//=================================================================================

   /** create empty instance of Attributes subtype appropriate for TestItem argument. 
    *  @return  null if argument type is not TestGroup, TestCase, Library
    */

    public static Attributes createAttributes(TestItem ti) {
	    if (ti instanceof TestGroup) {
		return AttributesFactory.createTestGroupAttributes();
	    } else if (ti instanceof TestCase) {
		return AttributesFactory.createTestCaseAttributes();
	    } else if (ti instanceof Library) {
		return AttributesFactory.createLibAttributes();
	    } else {
	    	return null;
	    }
    }

   /** set attrs as attributes for the ti testitem 
    *  @return  true if the value is set successfully
    *           false if argument types are not appropriate
    */

    public static boolean setAttributes(TestItem ti, Attributes attrs) {
	    if (ti instanceof TestGroup) {

		if (! (attrs instanceof TestGroupAttributes))
		    return false;
		((TestGroup)ti).setTGAttributes((TestGroupAttributes)attrs);
		return true;

	    } else if (ti instanceof TestCase) {

		if (! (attrs instanceof TestCaseAttributes))
		    return false;
		((TestCase)ti).setTCAttributes((TestCaseAttributes)attrs);
		return true;

	    } else if (ti instanceof Library) {

		if (! (attrs instanceof LibAttributes))
		    return false;
		((Library)ti).setLibAttributes((LibAttributes)attrs);
		return true;

	    } else {
	    	return false;
	    }
    }
//=================================================================================

    /**
     * Returns list of inline libraries IDs for the passed TestGroup
     * If passed TestGroup is null or does not contain inline libraries
     * an empty list is returned.
     */
    public static ArrayList getInlineLibrariesIDs(TestGroup tg) 
            throws TestFileException {
        ArrayList inlineLibrariesIDs = new ArrayList();
        if (tg != null) {
            ArrayList libs = tg.getLibraries();
            if (libs != null) {
                Iterator it = libs.iterator();
                while (it.hasNext()) {
                    String id = ((Library)it.next()).getID();
                    if (!inlineLibrariesIDs.contains(id)) {
                        inlineLibrariesIDs.add(id);
                    }
                }
            }
        }
        return inlineLibrariesIDs;
    }

    /**
     * Returns list of dependent library IDs for the passed TestItem
     * If passed TestItem is null or testItem CodeSet is null
     * an empty list is returned.
     */
    public static ArrayList getDependentLibs(TestItem testItem) 
            throws TestFileException {
        ArrayList ids = new ArrayList();
        try {
            ArrayList depList = testItem.getCodeSet().getDependencies();
            for(int i = 0; i < depList.size(); ++i) {
                Object dep = depList.get(i);
                if (dep instanceof LibraryDependency) {
                    String libID = ((LibraryDependency)dep).getID();
                    ids.add(libID.trim());
                }
            }
        } catch (NullPointerException e) {
        }
        return ids;
    }

    /**
     * An attribute element name. Marks an item in whose attributes
     * this element is present as auto-generated.
     */
    public final static String ATTR_ELEM_AUTO_GENERATED = "AutoGenerated";

    /**
     * Sets the {@link #ATTR_ELEM_AUTO_GENERATED} attribute element
     * in given attributes.
     *
     * @param attrs the attributes to set the element in
     * @param attr_value the value of the element
     */
    public static void setAutoGeneratedAttrElem(Attributes attrs, String attr_value) {
        if (attrs == null) {
            return;
        }
        IR.setAttrElem(attrs, ATTR_ELEM_AUTO_GENERATED, attr_value);
    }

    /**
     * @param attrs a set of attributes
     * @return a value of the {@link #ATTR_ELEM_AUTO_GENERATED} attribute
     *     element of the set or <code>null</code> if the element is not found
     *     in the set
     */
    public static String getAutoGeneratedAttrElem(Attributes attrs) {
        return attrs == null ?
            null :
            IR.getAttrElem(ATTR_ELEM_AUTO_GENERATED, attrs);
    }

//===========================================================================

    /**
     * Returs ExecuteArgs if defined in the CodeSet of TestItem,
     * or null.
     */
    public static String getExecuteArgs(TestItem ti) {
        try {
            return ti.getCodeSet().getExecuteArgs();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Returs Context if defined in the CodeSet of TestItem,
     * or null.
     */
    public static String getContext(TestItem ti) {
        try {
            return ti.getCodeSet().getContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

}

