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
package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Derived from:
 * <b>JCKIRPFactorySpi</b> class that provides JCK specific
 * implementation of <code>IRPFactorySpi</code> interface.
 * <p>
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko 
 *
 */

/*
 * Implementation note:
 * Currently two hardcoded processor will be used,
 * but this class could be updated to get list of processor
 * from the tck.properties
 */

public class JCKIRPFactorySpi implements IRPFactorySpi {
    /**
     * Returns ConveyerIRProcessor than invokes LibLinker first
     * and then DistributedIRProcessor.
     */
    public IRProcessor getProcessor(Properties props) {
        
        ArrayList ps = new ArrayList();
        ps.add(new XMLSchemaTestsIRProcessor(props));
        return new ConveyerIRProcessor((IRProcessor[])
                ps.toArray(new IRProcessor[0]));
    }

}
        
