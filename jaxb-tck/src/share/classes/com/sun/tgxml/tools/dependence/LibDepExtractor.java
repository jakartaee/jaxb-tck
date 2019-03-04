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

package com.sun.tgxml.tools.dependence;

import java.util.ArrayList;                
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

/**
 * The <code>LibDepExtractor</code> is a list of library dependencies
 * capable to extract the dependencies from the given <i>Library</i> 
 * and store them to the file.
 * If the <code>LibDepExtractor</code> is constructed with <strong>outUpdatesFileName</strong>
 * defined then only new entries are written to the  <strong>outUpdatesFileName</strong>.
 */

public class LibDepExtractor extends LibraryDependencies {

    /**
     * Name of the file where the list is read from. If set to null no pre-existent entries are read.
     */
    protected String fileName;

    /**
     * Name of the file where updates of the list are written to. 
     * If set to null the input file is updated.
     */
    protected String outUpdatesFileName;

    /**
     * The updates are collected there if output file is defined. 
     */
    protected LibraryDependencies updates;

    /**
     * Flag new entries are found.
     */
    protected boolean newFound = false;

    /**
     * Constructs the list from a file with the given file name. The file will be updated
     * with new entries found.
     * @param fileName
     *        name of the file where the list is stored
     * @throws IOException
     */
    public LibDepExtractor(String fileName) throws IOException {
        this.fileName = fileName;
        FileInputStream fis = new FileInputStream(fileName);
        read(fis);
        fis.close();
    }

    /**
     * Constructs the list which updates the given output file with new entries found.
     * @param fileName
     *        name of the file where the list is read from. If the name is null no pre-existent 
     *        entries are read so all found entries are treated new.
     * @param outFileName
     *        name of the file where updates of the list are written to.
     * @throws IOException
     */
    public LibDepExtractor(String fileName, String outFileName) throws IOException {
        this.fileName = fileName;
        this.outUpdatesFileName = outFileName;

        if (fileName == null)
            return;
        FileInputStream fis = new FileInputStream(fileName);
        read(fis);
        fis.close();
    }

    /**
     * Adds to the list one library dependecy extracted from the the given 
     * library. 
     * @param library
     *        a <i>Library</i> from which the dependency should be exctracted
     * @throws TestFileException
     */
    public void extract(Library library) throws TestFileException {
        CodeSet codeSet = library.getCodeSet();
        if (codeSet == null)
            return;

        ArrayList depList = codeSet.getDependencies();
        if (depList == null || depList.size() == 0)
            return;

        String libID = library.getID();
        LibraryDependency dependency = get(libID);

        boolean newDep = dependency == null;
        if (newDep) {
            dependency = new LibraryDependency(libID);
        }

        boolean newId = false;
        for(int i = 0; i < depList.size(); ++i) {
            Object dep = depList.get(i);
            if (dep instanceof com.sun.tgxml.tjtf.api.code.LibraryDependency) {
                newId |= dependency.addString((
                              (com.sun.tgxml.tjtf.api.code.LibraryDependency)dep).getID());
            }
        }
	if (!newId)
	    return;

	newFound = true;
	if (newDep)
            put(dependency);

	if (updates == null) {
	    if (outUpdatesFileName == null)
		return;
	    else
		updates = new LibraryDependencies();
	}
	if (updates.get(libID) == null)
	    updates.put(dependency);
    }

    /**
     * Writes the list to the file which was used to create the list.
     * @throws IOException
     * @throws FileNotFoundException
     * @see #LibDepExtractor(String fileName)
     */
    public void close() throws IOException, FileNotFoundException {
        if (! newFound)
            return;

        String fn = outUpdatesFileName;
        if (fn == null) {
            if (fileName == null)
                return;
            else
		fn = fileName;
        }
        FileOutputStream fos = new FileOutputStream(fn);
        if (outUpdatesFileName == null)
            write(fos);
        else
            updates.write(fos);
        fos.close();
    }
}
