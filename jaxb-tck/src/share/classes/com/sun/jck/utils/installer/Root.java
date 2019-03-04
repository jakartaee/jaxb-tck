/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jck.utils.installer;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Iterator;

class Root {

    class Fault extends Exception {
	public Fault(String msg) {
	    super(msg);
	}
    }

    private Root() {
    }
   
    public Root(File rootFile) throws IOException, Fault {
	m_nodes = new TreeMap();
	m_rootFile = rootFile;
	m_rootPath = rootFile.getAbsolutePath();
	generateNodes(m_rootFile);
    }

    public String getRootPath() {
	return m_rootPath;
    }

    public File getRootFile() {
	return m_rootFile;
    }

    public TreeMap getNodes() {
	return m_nodes;
    }

    public Iterator iterator() {
	return this.getNodes().entrySet().iterator();
    }

    private void generateNodes(File file) throws IOException, Fault {
	File [] children;
	Node node;

	if (file.isDirectory()) {

	    children = file.listFiles();

	    for (int i = 0; i < children.length; i++) {
		generateNodes(children[i]);
	    }

	}
	else {

	    try {
		node = new Node(m_rootPath, file);
		m_nodes.put(file.getCanonicalPath(), node);
	    }
	    catch (IllegalArgumentException iae) {
		throw new Fault("Illegal argument exception thrown while generating node list for root " + m_rootPath +
				"\n" + iae.getMessage());

	    }
	    catch (IOException ioe) {
		throw new Fault("IO exception thrown while generating node list for root " + m_rootPath +
				"\n" + ioe.getMessage());
	    }

	}
	

    }

    String m_rootPath;
    File m_rootFile;
    TreeMap m_nodes;
}
