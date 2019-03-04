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

/**
 * This interface defines the Service Provider Interface (SPI) for 
 * the <code>IRPFactory</code> class.
 * <p>
 * <code>getProcessor(Properties props)</code> method must be implemented 
 * by each IRProcessor provider who wishes to supply the implementation of 
 * a particular IRObj processing.
 * <p>
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko 
 *
 */

public interface IRPFactorySpi {

    /**
     * Creates a <code>IRProcessor</code> using passed Properties.
     * @param props  Properties can be used for creating or initializing
     *               <code>IRProcessor</code> instance.
     */
    public IRProcessor getProcessor(Properties props);

}
        
