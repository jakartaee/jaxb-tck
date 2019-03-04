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

package com.sun.tgxml.tools.filter;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.tgxml.util.IR;

import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestVariant;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.filter.processors.FilterExpression;
import com.sun.tgxml.tools.filter.processors.FilteringException;

import com.sun.tgxml.tools.filter.libutil.LibMap;
import com.sun.tgxml.tools.filter.libutil.LibsInfo;
import com.sun.tgxml.tools.filter.libutil.LibMapFile;
import com.sun.tgxml.tools.filter.libutil.VariantsMap;
import com.sun.tgxml.tools.filter.libutil.LibSelectionInfo;
import com.sun.tgxml.tools.filter.libutil.LibFilterFactrory;
import com.sun.tgxml.tools.filter.libutil.LibAttributesFilter;
import com.sun.tgxml.tools.filter.libutil.LibDependencyFilter;

/**
 * Miscellaneous filtering utils
 *
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */
public class FilterUtil  {

    /**
     * Creates an instance of LibFilterFactrory.
     * Looks for <toolname>.LibFilterFactrory build property.
     * If not specified, looks for "LibFilterFactrory" build property.
     *
     * @return the instance of class specified by build property
     * or "com.sun.tgxml.tools.filter.libutil.DefaultLibFilterFactrory"
     * by default.
     * 
     * @exception TestFileException if cannot create LibFilterFactrory
     */
    public static LibFilterFactrory createLibFilterFactrory(String toolname) 
            throws TestFileException {

        String factoryClassName = BuildProperties.getPrefixString(
            toolname, "LibFilterFactrory",
            "com.sun.tgxml.tools.filter.libutil.DefaultLibFilterFactrory");
        try {
            Class factoryClass = Class.forName(factoryClassName);
            return (LibFilterFactrory)factoryClass.newInstance();
        } catch (Exception e) {
            throw new TestFileException("Cannot create an instance of "
                + " LibFilterFactrory: " + e);

        }

    }


    /**
     * Return selection info about external libraries.
     * 
     * @param factory LibFilterFactrory that provides LibMapFile instance
     * @param filename the name of libmap file containing selection info.
     *
     * @exception TestFileException if libmap file cannot be parsed.
     */
    public static LibSelectionInfo extenalSelectionInfo
            (LibFilterFactrory factory, String filename) 
            throws TestFileException {
        if (filename == null)
            return new LibsInfo();

        try {
            LibMapFile libMapFile = factory.createLibMapFile();
            libMapFile.setFileName(filename);
            libMapFile.read();
            return libMapFile.libsInfo();
        } catch (IOException e) {
             throw new TestFileException(
                 "The error with reading libmap file: " + filename);
        }
    }


    /**
     * Returns the more appropriate variant from the passed pair
     * @exception FilteringException if variant cannot be selected.
     */
    public static TestVariant selectVariant(TestVariant var1, TestVariant var2)
             throws FilteringException {
        String v1 = var1.getVarID();
        String v2 = var2.getVarID();
        int o1 = var1.order();
        int o2 = var2.order();

        if (v1 != null && v2 != null && o1 != o2) {
            if (o1 < o2) {
                return var1;
            } else {
                return var2;
            }
        } else {
            throw new FilteringException(
                "cannot select variant of " + IR.getID((TestItem)var1) + 
                " between '" + v1 + "' and '" + v2 + "'");
        }
    }


    /**
     * Filters passed library array.
     *
     * @param bundle      libraries to be filtered
     * @param pluginName  filter plugin class name
     * @param factory     LibAttributesFilter and LibDependencyFilter provider
     * @param filtered    information about already accepted/rejected libraries
     *         (null if no libraries are filtered yet).
     *
     * @return   a LibMap of filtered libraries
     * @exception FilteringException if libraries cannot be filtered.
     */
    public static LibMap filterLibBundle(Library[] bundle, String pluginName,
            LibFilterFactrory factory, LibSelectionInfo filtered)
            throws FilteringException {

        VariantsMap varMap = new VariantsMap(bundle);
        // filter libraries by attributes
        LibAttributesFilter attrFilter = 
                factory.createLibAttributesFilter(pluginName);
        VariantsMap strippedMap = attrFilter.filter(varMap);

        // filter libraries by dependencies
        LibDependencyFilter depFilter = factory.createLibDependencyFilter();
        return depFilter.filter(strippedMap, filtered);
    }



    /**
     *  strips support classes for CodeSet of any TestItem
     */
    public static void stripCodeSet(TestItem ti, FilterExpression SCtree) 
            throws FilteringException {
        CodeSet cs = ti.getCodeSet();
        if ( cs == null )
            return;

        ArrayList scList = cs.getSupportClasses();
        if ( scList == null )
            return;

        ArrayList IDset = new ArrayList();

        for (int j = scList.size()-1; j >= 0; j--) {
            SupportClass sc = (SupportClass)scList.get(j);
            if ( !SCtree.accept(sc, cs) ) {
                scList.remove(j);
                continue;
            }
            String id = sc.getClassID();
            if (id==null)
                continue;
            if (IDset.contains(id)) {
                throw new FilteringException("Duplicated SupportClass :" + id + 
                   " for TestItem :" + IR.getID(ti));
            }
            IDset.add(id);
        }
    }


}
