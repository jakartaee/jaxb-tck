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
import java.util.Iterator;                
import java.util.Hashtable;                
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.LibraryDependency;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tools.filter.processors.FilteringException;
import com.sun.tgxml.tools.filter.processors.LibraryFilter;
import com.sun.tgxml.util.IR;

/**
 * The <code>LibIDExtractor</code> is a list of library identifiers 
 * capable to extract the identifiers from the given <i>TestGroup</i> 
 * and store the list to the file. <br>
 * If the <code>LibIDExtractor</code> is constructed with <strong>outUpdatesFileName</strong>
 * defined then only new entries are written to the  <strong>outUpdatesFileName</strong>.
 */

public class LibIDExtractor extends LibIDList {

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
    protected LibIDList updates;

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
    public LibIDExtractor(String fileName) throws java.io.IOException {
        this.fileName = fileName;
        FileInputStream fis = new FileInputStream(fileName);
        read(fis);
        fis.close();
    }

    /**
     * Constructs the list which updates the given output file with new entries found.
     * @param fileName
     *        name of the file where the list is read. If the name is null no pre-existent 
     *        entries are read so all found entries are treated new.
     * @param outFileName
     *        name of the file where updates of the list are written to.
     * @throws IOException
     */
    public LibIDExtractor(String fileName, String outFileName) throws java.io.IOException {
        this.fileName = fileName;
        this.outUpdatesFileName = outFileName;

        if (fileName == null)
            return;
        FileInputStream fis = new FileInputStream(fileName);
        read(fis);
        fis.close();
    }

    private void addEntry(String libID) {
	if (!addString(libID))
	    return;

	newFound = true;

	if (updates == null) {
	    if (outUpdatesFileName == null)
		return;
	    else
		updates = new LibIDList();
	}
	updates.addString(libID);
    }

		// extracts LibID from CodeSet
    
    private void extract(CodeSet codeSet, ArrayList inlineLibIDs, 
            LibIDList dependentInlineLibIDs) throws TestFileException {
        if (codeSet != null) {
            ArrayList depList = codeSet.getDependencies();
            if (depList != null) {
                for(int i = 0; i < depList.size(); ++i) {
                    Object dep = depList.get(i);
                    if (dep instanceof LibraryDependency) {
                        String libID = ((LibraryDependency)dep).getID();
                        if (inlineLibIDs.contains(libID)) {
                            dependentInlineLibIDs.add(libID);
                        } else {    
                            addEntry(libID);
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds to the list all external library identifiers referred by the given 
     * test group, its test cases and selected inline libraries. 
     * Makes selecting of inline libraries. Every selected inline library
     * should be dependent on some testcase or other selected inline library 
     * and should be acceptable by LibraryFilter.
     * @param testGroup
     *        a <i>TestGroup</i> from which the list should be exctracted
     * @param libFilter
     *        a <i>LibraryFilter</i> used for inline libraries selecting
     */
    public void extract(TestGroup testGroup, LibraryFilter libFilter) 
            throws TestFileException, FilteringException {

        LibIDList dependentInlineLibIDs = new LibIDList();
        ArrayList inlineLibIDs = IR.getInlineLibrariesIDs(testGroup);

        extract(testGroup.getCodeSet(), inlineLibIDs, dependentInlineLibIDs);

        ArrayList testCases = testGroup.getTestCases();
        if (testCases != null) {
            for(int tci = 0; tci < testCases.size(); ++tci) {
                extract( ( (TestCase)(testCases.get(tci)) ).getCodeSet(),
                        inlineLibIDs, dependentInlineLibIDs);
            }
        }

        // split all libs into accepted and filtered out.

        Hashtable acceptedID = new Hashtable();

        Iterator it = inlineLibIDs.iterator();
        while (it.hasNext()) {
            String id = (String)(it.next());
            // get all variants by id
            ArrayList libList = testGroup.getLibrary(id);
            Library[] libArray = (Library[])libList.toArray(new Library[0]);
            // select not more than one variant
            Library lib = libFilter.strip(libArray);
            if (lib != null) {
                acceptedID.put(id, lib);
            }
        }

        // create list of selected inline libraries
        ArrayList selected = new ArrayList();
        while (!dependentInlineLibIDs.isEmpty()) {
            String libID = (String)(dependentInlineLibIDs.remove(0));
            if (inlineLibIDs.contains(libID)) {
                // inline lib
                Library lib = (Library)acceptedID.get(libID);
                if (lib == null) {
                    // library is required but filtered out
                    throw new TestFileException("LibID: " + libID +
                            " is required but library is filtered out");
                } else {
                    // select library if not selected yet
                    if (!selected.contains(lib)) {
                        selected.add(lib);
                        // process dependencies of new selected library
                        // it may new libs into dependentInlineLibIDs list
                        extract(lib.getCodeSet(), 
                             inlineLibIDs, dependentInlineLibIDs);

                    }
                }
            } else {
                // extra lib
                addEntry(libID);
            }
        }
        testGroup.setLibraries(selected);
        
    }

    /**
     * Writes the list to the file which was used to create the list.
     * @throws IOException
     * @throws FileNotFoundException
     * @see #LibIDExtractor(String fileName)
     */
    public void close() 
            throws java.io.IOException, java.io.FileNotFoundException {
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
