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

package com.sun.tgxml.tools.filter.processors;

import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.tools.elgen.ExcludeListFilter;
import com.sun.tgxml.tools.elgen.FilteredOutList;


/**
 * Factory for filter instances.
 * The class serves also as base class for filtering plugins.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public abstract class FilterFactory {

    /**
     * The method is used to get a factory instance
     *  initialized with plugin 
     *
     * @param      pluginClassName    full name of main plugin class.
     * @return     FilterFactory instance.
     * @throws     FilteringException to diagnose problems with plugin.
     */
    public static FilterFactory newInstance(String pluginClassName)
                                              throws FilteringException {
	try {
	    Class pluginClass = Class.forName(pluginClassName);
	    Object plugin = pluginClass.newInstance();
	    return (FilterFactory)plugin;
	} catch (Throwable thr) {
	    throw new FilteringException(
                            LibResHandler.getResStr("filter.error.factory.loadPlugin", thr.toString()) );
	}
    }

    /**
     * Creates TestFilter instance initialized with configuration data 
     * and Exclude List components to get and supply exclude list data. 
     *
     * @param      configuration   string value identifying configuration data 
     *                             in a way specific for plugin : filename, etc
     * @param      elf    instance of the ExcludeListFilter implementation.
     * @param      fol    instance of the FilteredOutList implementation.
     * @return     TestFilter instance.
     * @throws     FilteringException to diagnose problems with configuration data.
     */
    public TestFilter    getTestFilter(String configuration, ExcludeListFilter elf,
							     FilteredOutList fol)
							     throws FilteringException {
	FilterExpression tree = cfgRead(configuration);
	return new TestFilterImpl(tree, elf, fol);
    }

    /**
     * Creates TestFilter instance initialized with configuration data .
     * The filter does not interact with Exclude List components. 
     *
     * @param      configuration   string value identifying configuration data 
     *                             in a way specific for plugin : filename, etc
     * @return     TestFilter instance.
     * @throws     FilteringException to diagnose problems with configuration data.
     */
    public TestFilter    getTestFilter(String configuration) throws FilteringException {
	return getTestFilter(configuration, null, null);
    }


    /**
     * Creates TestFilter instance initialized with configuration data 
     * and Exclude List components to get and supply exclude list data. 
     *
     * @param      configuration   string value identifying configuration data 
     *                             in a way specific for plugin : filename, etc
     * @param      elf    instance of the ExcludeListFilter implementation.
     * @param      fol    instance of the FilteredOutList implementation.
     * @return     TestFilter instance.
     * @throws     FilteringException to diagnose problems with 
     *             configuration data.
     */
    public TestFilter2 getTestFilter2(String configuration, 
            ExcludeListFilter elf, FilteredOutList fol)
            throws FilteringException {

	FilterExpression tree = cfgRead(configuration);
	return new TestFilterImpl2(tree, elf, fol);
    }

    /**
     * Creates TestFilter2 instance initialized with configuration data .
     * The filter does not interact with Exclude List components. 
     *
     * @param      configuration   string value identifying configuration data 
     *                             in a way specific for plugin : filename, etc
     * @return     TestFilter2 instance.
     * @throws     FilteringException to diagnose problems with 
     *             configuration data.
     */
    public TestFilter2 getTestFilter2(String configuration)
           throws FilteringException {

	return getTestFilter2(configuration, null, null);
    }

    /**
     * Creates LibraryFilter instance initialized with configuration data. 
     *
     * @param      configuration   string value identifying configuration data 
     *                             in a way specific for plugin : filename, etc
     * @return     LibraryFilter instance.
     * @throws     FilteringException to diagnose problems with configuration data.
     */
    public LibraryFilter getLibraryFilter(String configuration)
						          throws FilteringException{
	FilterExpression tree = cfgRead(configuration);
	return new LibraryFilterImpl(tree);
    }

    // ---------- methods below are intended for use in plugin

    /** 
     *  Creates standard leaf TRUE node.
     */
    public static FilterExpression createTRUE() {
	return new NodeTRUE();
    }

    /** 
     *  Creates standard non-leaf NOT node. Hides node implementing class.
     */
    public static FilterExpression createNOT(FilterExpression child) {
	return new NodeNOTimpl(child);
    }

    /** 
     *  Creates standard non-leaf AND node. Hides node implementing class.
     */
    public static FilterExpression createAND(FilterExpression left, FilterExpression right) {
	return new NodeANDimpl(left, right);
    }

    /** 
     *  Creates standard non-leaf OR node. Hides node implementing class.
     */
    public static FilterExpression createOR(FilterExpression left, FilterExpression right) {
	return new NodeORimpl(left, right);
    }

    /** This method should be implemented in plugin.
     *  Plugin returns root node of expression tree for specified configuration.
     */
    public abstract FilterExpression cfgRead(String configuration) throws FilteringException;
}
