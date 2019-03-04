/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jaxb_tck.lib.persistence;

import java.io.InputStream;

/**
 * The JAXB tree instantiation interface. The JAXB tree is read from input stream
 * and instantiated using mechanism that is different from standard JAXB 
 * unamrshalling.
 *  
 * @author Vladimir Sosnin
 *
 */
public interface JaxbTreeDeserializer {
    /**
     * Try to instantiate JAXB tree.
     * 
     * @param input - serialized JAXB tree.
     * @param cl - ClassLoader holding classes used in JAXB tree.
     * @return JAXB tree
     */
    Object deserialize(InputStream input, ClassLoader cl);
}
