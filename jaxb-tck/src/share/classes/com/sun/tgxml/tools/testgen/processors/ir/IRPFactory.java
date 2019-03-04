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

package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.Properties;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.BuildProperties;

/**
 * <b>IRPFactory</b> factory used by TestGen tool to gen an instance
 * of IRProcessor.
 * <p>
 * This class uses a provider-based architecture. To create a IRPFactory, 
 * call one of the static getInstance methods.
 * <p>
 * Once a IRPFactory object has been created, IRProcessor can be 
 * constructed by calling the getIRProcessor() method and passing it 
 * a Property. 
 * <p>
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko 
 *
 */

public final class IRPFactory {

    /**
     * Default build property name that specifies IRPFactorySpi class name
     */ 
    public static final String IRP_FACTORY_SPI_PROP_NAME = 
        "IRPFactorySpiClassName";

    private static IRPFactory factory = null;
    private IRPFactorySpi spi = null;

    private IRPFactory() throws TestFileException {
        this(IRP_FACTORY_SPI_PROP_NAME);
    }

    private IRPFactory(String tckPropertyName) throws TestFileException {
        String spiClassName = BuildProperties.getString(tckPropertyName);
        if (spiClassName == null) {
            spi = new DefaultIRPFactorySpi();
        } else {
            try {
                Class spiClass = Class.forName(spiClassName);
                spi = (IRPFactorySpi)spiClass.newInstance();
            } catch (Exception e) {
                throw new TestFileException("Cannot create an instance of "
                    + " IRPFactorySpi: " + e);
            }
        }
    }

    /**
     * Returns getInstance(IRP_FACTORY_SPI_PROP_NAME)
     * @see #getInstance(String tckPropertyName)
     */ 
    public static IRPFactory getInstance() throws TestFileException {
       return getInstance(IRP_FACTORY_SPI_PROP_NAME);
    }


    /**
     * Returns IRPFactory instance.
     * IRPFactory uses IRPFactorySpi to get IRProcessor which is initialized
     * during the first call of this method.
     * The spi class name is taken from build properies.
     * If <code>tckPropertyName</code> property is not defined
     * then DefaultIRPFactorySpi will be used.
     *
     * @param tckPropertyName build property name
     * @return factory instance
     * @exception TestFileException if instance of IRPFactorySpi cannot 
     *            be created.
     *
     */ 
    public static IRPFactory getInstance(String tckPropertyName)
            throws TestFileException {
        if (factory != null)
            return factory;
        factory = new IRPFactory(tckPropertyName);
        return factory;
    }

    /**
     * Returns <code>spi.getProcessor(props)</code>, where 
     * spi is IRPFactorySpi. 
     */ 
    public IRProcessor getProcessor(Properties props)
            throws TestFileException {
        return spi.getProcessor(props);
    }

}   
