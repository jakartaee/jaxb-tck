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

package com.sun.tgxml.tools.filter.libutil;

import com.sun.tgxml.tools.filter.processors.FilterExpression;
import com.sun.tgxml.tools.filter.processors.FilteringException;

/**
 * This interface defines a factory for creating 
 * LibAttributesFilter, LibDependencyFilter and LibMapFile.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public interface LibFilterFactrory {

    /**
     * Creates an instance of LibAttributesFilter by the 
     * specified plugin name
     * @exception FilteringException if plugin cannot be created
     */
    public LibAttributesFilter createLibAttributesFilter(String pluginName)
            throws FilteringException;

    /**
     * Creates a instance of LibBundleFilter
     */
    public LibDependencyFilter createLibDependencyFilter();

    /**
     * Creates a instance of LibMapFile
     */
    public LibMapFile createLibMapFile();


}
