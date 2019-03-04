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

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class providing implemnataions for serializers and desirealizers
 * in JAXB TCK persistence framework. Implementations of these classes
 * are choosen using TCK configuration file 
 * @author Vladimir Sosnin
 *
 */
public class PersistenceFactory {

    /**
     * Singletone instance variable
     */
    protected static PersistenceFactory instance;

    /**
     * Syncronization lock
     */
    protected static ReentrantLock instanceLock = new ReentrantLock();

    /**
     * Hide public constructor
     */
    protected PersistenceFactory() {
        super();
    }

    /**
     * JaxbTreeSerializer factory method
     * @return Instance of implementaion of JaxbTreeSerializer defined in
     * configuration 
     */
    public JaxbTreeSerializer createJaxbTreeSerializer() {
        return new TCKJaxbTreeSerializer();
    }

    /**
     * JaxbTreeDeserializer factory method
     * @return Instance of implementaion of JaxbTreeDeserializer defined in
     * configuration 
     */
    public JaxbTreeDeserializer createJaxbTreeDeserializer() {
        return new TCKJaxbTreeDeserializer();
    }

    /**
     * Singleton instance access method
     * @return PersistenceFactory instance
     */
    public static PersistenceFactory getInstance() {
        // TODO Implement factory configuration mechanism
        instanceLock.lock();
        if (instance == null) {
            instance = new PersistenceFactory();
        }
        instanceLock.unlock();
        return instance;
    }
}
