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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Enumeration;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.tjtf.api.tests.Library;

/**
 * 
 * Default implementation of LibMapFile interface.
 * It uses java.util.Proptery format for storage LibMap,
 * where property name is libID, property value is lib.xml file name
 * for accepted library and an empty string for rejected one. 
 * <p>
 * This implementation does not provide parsing of lib.xml files.
 * So getLibMap() just returns the value set by setLibMap() method.
 * But it allows to retrieve name of lib.xml file by libID.
 *
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class DefaultLibMapFile implements LibMapFile, XmlFileNameMap {

    protected String fileName = null;
    protected String libDir = null;
    protected LibMap libMap = null;
    protected Properties props = null;
    protected LibsInfo libsInfo = null;

    protected boolean isRead = false;

    /**
     * Sets the file name for LibMap storage
     */
    public void setFileName(String name) {
        fileName = name;
    }

    /**
     * Returns the file name of LibMap storage
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the filtered libraries directory name.
     * Appends file separator if needed.
     */
    public void setLibDir(String name) {
        libDir = name;
        if (libDir != null && !libDir.endsWith(File.separator)) {
            libDir += File.separator;
        }
    }

    /**
     * Returns the filtered libraries directory name
     */
    public String getLibDir() {
        return libDir;
    }


    /**
     * Sets the LibMap to be stored
     */
    public void setLibMap(LibMap map) {
        libMap = map;
    }

    /**
     * Returns the LibMap set by setLibMap() method
     */
    public LibMap getLibMap() {
        return libMap;
    }

    /**
     * Reads the LibMap from the file specified by setFileName() method.
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException if file name is not specified or
     *         other exception occurs during file reading.
     */
    public void read() throws IOException {
        if (fileName == null) 
            throw new IllegalArgumentException("fileName is not set");
        props = new Properties();
        props.load(new FileInputStream(new File(fileName)));
        libsInfo = new LibsInfo();
        for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
            String libID = (String)en.nextElement();
            String xmlFileName = xmlFileName(libID);
            if (xmlFileName != null) {
                libsInfo.addAccepted(libID);
            } else {
                libsInfo.addRejected(libID);
            }
        }
        isRead = true;
    }

    /**
     * Writes the LibMap into the file specified by setFileName() method.
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException if file name is not specified.
     */
    public void write() throws IOException {
        if (fileName == null) 
            throw new IllegalArgumentException("fileName is not set");
        if (props == null) {
            libMap2Properties();
        }
        File fl = new File(fileName);
        File dir = fl.getParentFile();
        if (dir != null && !MiscUtils.mkdirs(dir)){
            throw new IOException("Cannot create directory " + dir);
        }
        props.store(new FileOutputStream(fl), "LibDir: " + libDir);
    }

    /**
     * Returns the information about accepted/rejected libraries.
     */
    public LibSelectionInfo libsInfo() {
        if (isRead)
            return libsInfo;
        if (libMap != null)
            return libMap;
        return libsInfo;
    }

    /**
     * Converts LibMap into Properties.
     * @exception IllegalArgumentException if "relSourcePath" AttrElem is not
     *            specified for accepted library.
     */
    protected void libMap2Properties() {
        if (libMap == null) 
            throw new IllegalArgumentException("LibMap is not set");
        if (libDir == null)
            throw new IllegalArgumentException("LibDir is not set");

        props = new Properties();
        for (Iterator it = libMap.iterator(); it.hasNext();) {
            String libID = (String)it.next();
            String xmlName = "";
            Library lib = libMap.get(libID);
            if (lib != null) {
                String relName = 
                        IR.getAttrElem(IR.relSourcePathAttrElemName, lib);
                if (relName == null) 
                    throw new IllegalArgumentException(
                            "relSourcePath is not specified for " + libID);
                xmlName = libDir + relName;                 
            }
            props.setProperty(libID, xmlName);            
        }
    }

    /**
     * Returns an name of lib xml file by libID if library is accpeted
     * and null if rejected.
     */
    public String xmlFileName(String libID) {
        String xmlName = (String)props.getProperty(libID);
        if (xmlName != null) {
           xmlName = xmlName.trim();
           if (xmlName.length() == 0)
              xmlName = null;
        }
        return xmlName;
    }

    public Properties getProperties() {
        if (isRead)
            return props;

        if (libMap != null)
            libMap2Properties();            
        return props;
    }

    public void setProperties(Properties p) {
        props = p;
    }

}
