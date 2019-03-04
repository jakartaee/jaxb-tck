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


import com.sun.tgxml.tools.filter.redundancy.processors.ExternalLibraryMapCollector;
import com.sun.tgxml.tools.testgen.processors.ir.IRPFactorySpi;
import com.sun.tgxml.tools.testgen.processors.ir.IRProcessor;
import java.util.Properties;

/**
 * <b>RuntimeLibraryIRPFactorySpi</b> class that provides implementation of 
 * <code>IRPFactorySpi</code> interface.
 * <p>
 * The {@link com.sun.tgxml.tools.testgen.processors.ir.IRPFactory#IRP_FACTORY_SPI_PROP_NAME}
 * defines name of a build property used to specify <code>IRPFactorySpi</code> implementation.
 * <p>
 * @author      Maxim V. Sokolnikov 
 *
 */

public class RuntimeLibraryIRPFactorySpi implements IRPFactorySpi {

	private static ExternalLibraryMapCollector libMapCollector = new ExternalLibraryMapCollector();
	
	public static TestItemSelectionListener getListener() {
		return libMapCollector;
	}
	
    public IRProcessor getProcessor(Properties props) {
        return new RuntimeLibraryLinker(props, libMapCollector);
    }
}
