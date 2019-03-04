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

package com.sun.tgxml.tools.filter.libutil;

import java.io.IOException;

/**
 * An interface that contains methods for read/write LibMap in/from file.
 * This interface allows to isolate usage of LibMap class from the way
 * of it storage on disk.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public interface LibMapFile {


    /**
     * Sets the file name for LibMap storage
     */
    public void setFileName(String name);

    /**
     * Returns the file name of LibMap storage
     */
    public String getFileName();


    /**
     * Sets the filtered libraries directory name
     */
    public void setLibDir(String name);

    /**
     * Returns the filtered libraries directory name
     */
    public String getLibDir();


    /**
     * Sets the LibMap to be stored
     */
    public void setLibMap(LibMap map);

    /**
     * Returns the LibMap set by setLibMap() method or read from libMap file
     * Returns null if neither read() nor setLibMap() are inoveked before.
     */
    public LibMap getLibMap();


    /**
     * Reads the LibMap from the file specified by setFileName() method.
     * @throws IOException If an I/O error occurs
     * @throws IlleagalArgumentException if file name is not specified or
     *         other exection occurs during file reading.
     */
    public void read() throws IOException;

    /**
     * Writes the LibMap into the file specified by setFileName() method.
     * @throws IOException If an I/O error occurs
     * @throws IlleagalArgumentException if file name is not specified.
     */
    public void write() throws IOException;

    /**
     * Returns the information about accepted/rejected libraries.
     * This method should be used when libraries IR is not required.
     * It may allow to avoid parsing xml files.
     */
    public LibSelectionInfo libsInfo();
}
