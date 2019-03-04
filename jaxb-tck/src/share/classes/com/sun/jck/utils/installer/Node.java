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
import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;

class Node {

    private Node() {
    }
   
    public Node(String rootPath, File file) throws IOException, IllegalArgumentException {
	m_rootPath = rootPath;
	m_rootDirName = new File(rootPath).getName();
	m_fileAbsolutePath = file.getAbsolutePath();
	m_fileCanonicalPath = file.getCanonicalPath();

	if (!m_rootPath.endsWith(m_fs))
	    m_rootPath += m_fs;

	if (!new File(m_rootPath).isDirectory())
	    throw new IllegalArgumentException("The root path value is not a directory");

	if (file.isDirectory())
	    throw new IllegalArgumentException(file.getCanonicalPath() + " is not a file.");

	if (file.getAbsolutePath().indexOf(m_rootPath) != 0) 
	    throw new IllegalStateException(m_fileAbsolutePath + " does not appear to be a child of " +
					    m_rootPath);
    }

    public String getRootPath() {
	return m_rootPath;
    }

    public File getFile() {
	return new File(m_fileAbsolutePath);
    }

    public String getAbsolutePath() {
	return m_fileAbsolutePath;
    }

    public String getCanonicalPath() throws IOException{
	return m_fileCanonicalPath;
    }

    public boolean equals(Node comparator) throws IOException {
	if (m_fileCanonicalPath.equals(comparator.getCanonicalPath()))
	    return true;
	else
	    return false;
    }

    public String getRelativePath() throws IllegalStateException {

	return m_rootDirName + m_fs + m_fileAbsolutePath.substring(m_rootPath.length());
	
    }



    String m_rootPath;
    String m_rootDirName;
    String m_fileAbsolutePath;
    String m_fileCanonicalPath;
    static final String m_fs = System.getProperty("file.separator");
}
