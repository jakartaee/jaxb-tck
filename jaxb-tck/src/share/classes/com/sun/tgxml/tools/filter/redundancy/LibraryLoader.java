/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.redundancy;

import java.util.ArrayList;


/**
 * The basic interface of Library Loaders. Instances may implement loading 
 * in the different ways.
 */
public interface LibraryLoader {
    /**
     * loads and returns all external library variants with the given ID.
     * @throws ItemNotFoundException if the no variants with the given ID exist.
     */
    public ArrayList loadLibraryVariants(String id) throws ItemNotFoundException;
}
