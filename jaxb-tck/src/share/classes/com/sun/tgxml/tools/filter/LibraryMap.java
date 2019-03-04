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

package com.sun.tgxml.tools.filter;

import java.io.File;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;

/**
 * Class provides mapping library ID to list of the library implementations.
 * In this approach the map was prepared by other tools and space-separated list of
 * filenames is passed as libMap parameter of the wrapper-tool.
 * 
 * @version  2.1, 05/04/2001
 * @author   Ilya V. Neverov, Oleg Oleinik
 */
public class LibraryMap {

    protected static final char REPOSITORY_DELIMITER_CHAR = ':';
    
    /** 
     * Maps library ID to list of XML files that contains the library implementations.
     * 
     * @param libID 	  	ID of the library. 
     * @param libMap		space-separated list of filenames
     * @return zero-length array if no files are found
     * @throws TestFileException	to diagnose problems with mapping 
     */
    public static File[] getXMLFiles(String libID, String libMap) throws TestFileException {
	
	int count = parseList(libMap, null);
	String[] fNames = new String[count];
	File[]      res = new File[count];

	parseList(libMap, fNames);

	for (int i=fNames.length-1; i>=0; i--) {
	    fNames[i] = transformLibPathString(fNames[i]);
	    File f = new File(fNames[i]);
	    res[i] = f;
	}
	return res;
    }

    static int parseList(String list, String[] res) throws TestFileException {
	final String separator = " " ;		// previously = ";" 
	int count = 0;
	int i = 0;
	int len = list.length();

	while (i < len) {
	    if (Character.isWhitespace(list.charAt(i))) {
		i++;
		continue;
	    }
	    int j = list.indexOf(separator, i);
	    if (j == i)
                throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badMapList", new Integer(j).toString()));

	    if (j == -1)
		j = len;

	    int k = j;
	    while (Character.isWhitespace(list.charAt(--k))) {}

	    if (res != null)
		res[count] = list.substring(i,k+1);

	    count++;
	    i = j+1;
	}
	return count;
    }

    public static String getRepositoryPath(String libraryFile, String libIDMapString) throws TestFileException {
        StringTokenizer st = new StringTokenizer(libIDMapString);
        while (st.hasMoreTokens()){
            String libXMLFileStr, libXMLfile;
            try {
                libXMLFileStr = st.nextToken();
                libXMLfile = new File(transformLibPathString(libXMLFileStr)).getCanonicalPath();
            } catch (IOException ioe){
                continue;
            }
            if (libXMLfile.equals(libraryFile)){
                StringTokenizer st2 = new StringTokenizer(libXMLFileStr, String.valueOf(REPOSITORY_DELIMITER_CHAR));
                if (st2.countTokens() == 2){
                    return st2.nextToken();
                }
                return null;
            }
        }
        throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.mappingNotFound", libraryFile, libIDMapString));
    }

    public static String getRepositoryPath(String libID, String libraryFile, File libMapFile) throws TestFileException {
        BufferedReader reader;
        String repos_path = null;
        try {
            reader = new BufferedReader(new FileReader(libMapFile));
            String ID;
            for (String ID_map = reader.readLine(); ID_map != null; ID_map = reader.readLine()) {
                StringTokenizer st = new StringTokenizer(ID_map);
                if (st.hasMoreTokens() && (ID = st.nextToken()).equals(libID) && st.hasMoreTokens()){
                    repos_path = getRepositoryPath(libraryFile, ID_map.substring(ID.length()));
                    if (repos_path == null){
                        throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.wrongReposSeparator", 
                             libID, libMapFile.getName(), String.valueOf(REPOSITORY_DELIMITER_CHAR)));
                    }
                    break;
                }
            }
            reader.close();
            if (repos_path != null){
                return repos_path;
            }
        } catch (IOException e) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException2", libMapFile.getName(), e.toString()));
        }
        throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.mappingFileNotFound", libID, libraryFile, libMapFile.getName()));
    }
    
    public static String[] getAllIDs(File libMapFile) throws TestFileException {
        String[] IDs = new String[0];
        Vector ID_vector = new Vector();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(libMapFile));

            for (String ID_map = reader.readLine(); ID_map != null; ID_map = reader.readLine()) {
                StringTokenizer st = new StringTokenizer(ID_map);
                if (st.hasMoreTokens()){
                    ID_vector.add(st.nextToken());
                }
            }
            IDs = new String [ID_vector.size()];
            for (int i = 0; i < ID_vector.size(); ++i){
                IDs[i] = (String)ID_vector.get(i);
            }
            reader.close();
        } catch (IOException e) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException2", libMapFile.getName(), e.toString()));
        }
        return IDs;
    }

    public static File[] getXMLFiles(String libID, File libMapFile) throws TestFileException {
        String ID;
        File[] f = new File[0];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(libMapFile));
            for (String ID_map = reader.readLine(); ID_map != null; ID_map = reader.readLine()) {
                StringTokenizer st = new StringTokenizer(ID_map);
                if (st.hasMoreTokens() && (ID = st.nextToken()).equals(libID) && st.hasMoreTokens()){
                    f = getXMLFiles(ID, transformLibPathString(ID_map.substring(ID.length())));
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException2", libMapFile.getName(), e.toString()));
        }
        return f;
    }
    
    public static String transformLibPathString(String s){
        return s.replace(REPOSITORY_DELIMITER_CHAR, File.separatorChar);
    }
}
