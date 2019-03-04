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

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * The <code>LibraryDependencies</code> is a set of library dependencies
 * (LibraryDependency). It can be written to a stream and read from it. 
 * It also provides fast methods to put and find a dependecy for 
 * a given library identifier.
 * @see LibraryDependency
 */
public class LibraryDependencies extends Hashtable {
    /**
     * Inserts the specified library dependency. The dependency cannot 
     * be null.
     * <p>
     * The dependency can be retrieved by calling the get method with 
     * a string that is equal to the libID of the specified dependency.
     * @param libraryDependency the library dependency to be put.
     * @see #get(String)
     */
    public void put(LibraryDependency libraryDependency) {
        put(libraryDependency.getLibID(), libraryDependency);
    }

    /**
     * Returns the library dependency for the specified library identifier.
     * @param libID 
     * @return the library dependency which corresponds to the specified
     *         library. <code>null</code> if there is no dependency defined
     *         for the library.
     * @see #put(LibraryDependency)
     */
    public LibraryDependency get(String libID) {
        return (LibraryDependency)(super.get(libID));
    }

    /**
     * Appends a list of library dependencies read from the specified stream.
     * The stream is expected to contain lines in the follwong format:<br>
     * <code>dependentLibID [LibID]*</code>
     * @param inputStream
     *        a stream to read the list of library dependencies.
     * @throws IOException
     */
    public void read(InputStream inputStream) throws java.io.IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        for(String inLine; (inLine = in.readLine()) != null;) {
            // each string is expected to be in the following format:
            // [libID [libID1 ... ]]
            StringTokenizer tokenizer = new StringTokenizer(inLine);

            if(tokenizer.hasMoreTokens()) {
                LibraryDependency libraryDependency 
                    = new LibraryDependency(tokenizer.nextToken());

                while(tokenizer.hasMoreTokens()) {
                    libraryDependency.addString(tokenizer.nextToken());
                }

                if (!libraryDependency.isEmpty()) {
                    // do not store independent libraries,
                    put(libraryDependency);
                }
            }
        }
    } 
    /**
     * Writes the list of dependencies to the specified stream.
     * The output format is acceptable by the method read(InputStream).
     * @param outputStream
     * @see #read(InputStream)
     */
    public void write(OutputStream outputStream) {
        PrintWriter out = new PrintWriter(outputStream);
        Iterator valueIterator = values().iterator();
        while(valueIterator.hasNext()) {
            LibraryDependency libraryDependency 
                = (LibraryDependency)(valueIterator.next());
            out.print(libraryDependency.getLibID());
            out.print(' ');
            for(int i = 0; i < libraryDependency.size(); ++i) {
                out.print(libraryDependency.getString(i));
                out.print(' ');
            }
            out.println();
        }
        out.close();
    }
}
