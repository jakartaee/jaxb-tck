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

import java.util.Properties;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tools.filter.redundancy.processors.ExternalLibraryMapCollector;
import com.sun.tgxml.tools.testgen.processors.ir.LibLinker;

public class RuntimeLibraryLinker extends LibLinker {
	
	private ExternalLibraryMapCollector listener;

	public RuntimeLibraryLinker(Properties properties, ExternalLibraryMapCollector map) {
		super(properties);
		this.listener = map;
	}
	
    public boolean canGetExternalLibrary() {
        return (this.listener != null);
    }

	public Library getExternalLibrary(String libID) throws TestFileException {
		return this.listener.getLibrary(libID);
	}

	protected void initialize() throws TestFileException {
	}	
}
