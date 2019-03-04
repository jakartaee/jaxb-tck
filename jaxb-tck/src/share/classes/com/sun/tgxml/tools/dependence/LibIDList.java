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

/**
 * The <code>LibIDList</code> is a list of library identifiers. 
 * An instance of the LibIDList can be written to a stream and read from it. 
 */

public class LibIDList extends StringArray {

    /**
     * Appends a list of library identifiers read from the specified stream.
     * The stream is expected to contain library identifiers separated
     * by blank characters &quot; \t\n\r\f&quot;.
     * @param inputStream
     *        a stream to read the list of libraries from.
     * @throws IOException
     */
    public void read(InputStream inputStream) throws java.io.IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        for(String inLine; (inLine = in.readLine()) != null;) {
            StringTokenizer tokenizer = new StringTokenizer(inLine);
            while(tokenizer.hasMoreTokens()) {
                add(tokenizer.nextToken());
            }
        }
    } 

    /**
     * Writes the list of library identifiers to the specified stream.
     * The output format is acceptable by the method read(InputStream).
     * @param outputStream
     * @see #read(InputStream)
     */
    public void write(OutputStream outputStream) {
        PrintWriter out = new PrintWriter(outputStream);
        for(int i = 0; i < size(); ++i) {
            out.println(getString(i));
        }
        out.close();
    }
}
