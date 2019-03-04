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

package com.sun.tgxml.tools.filter.redundancy.processors;

import java.util.HashMap;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tools.filter.redundancy.TestItemSelectionListener.EmptyListener;

/**
 * The class implements TestItemSelectionListener interface. 
 * After it is registered as TestItemSelectionListener, it collects mapping 
 * between Library ID and a selected Library IR.
 * <p>
 * Afterward the mapping information is accessible.
 * 
 */
public class ExternalLibraryMapCollector extends EmptyListener {
	/**
	 * internal map for storring mapping information
	 */
	private HashMap map = new HashMap();
	
	public void externalLibrarySelected(Library item)
			throws TestFileException {
		map.put(item.getID(), item);
	}
	
	/**
	 * returns selected Library with the given ID, or null if there were no 
	 * libraries with the given ID selected. 
	 * <p>
	 * The library have to be selected by enhansed filtering tool prior this
	 * method is invoked.
	 */
	public Library getLibrary(String id) {
		return (Library)this.map.get(id);
	}
}
