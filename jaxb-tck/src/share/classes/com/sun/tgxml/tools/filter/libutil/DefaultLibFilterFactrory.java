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

import com.sun.tgxml.tools.filter.processors.FilteringException;

/**
 * This factory provides default implementations for 
 * LibAttributesFilter, LibDependencyFilter and LibMapFile interfaces
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class DefaultLibFilterFactrory implements LibFilterFactrory {

    /**
     * Returns an instance of DefaultLibAttributesFilter object
     */
    public LibAttributesFilter createLibAttributesFilter(String puginName)
            throws FilteringException {
        return new DefaultLibAttributesFilter(puginName);
    }

    /**
     * Returns an instance of DefaultLibDependencyFilter object
     */
    public LibDependencyFilter createLibDependencyFilter() {
        return new DefaultLibDependencyFilter();
    }

    /**
     * Returns an instance of DefaultLibMapFile object
     */
    public LibMapFile createLibMapFile() {
        return new DefaultLibMapFile();
    }


}
