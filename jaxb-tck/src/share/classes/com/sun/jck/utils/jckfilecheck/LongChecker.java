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

//
// Program: LongChecker.java
// Author : Rampalli Narasimhan
// Purpose: To do a detailed checking on all the files/dir entries.
//

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Vector;


public class LongChecker implements Checker {

    private File outFileName;
    private String[] legalExtns;
    private OrderedList fileList;
    private Vector fileTypes = new Vector();
    private long noOfFiles[] = new long[20];
    private long sizeOfFiles[] = new long[20];
    private long noOfDirs = 0;
    private long totalEntries = 0;

    public LongChecker (File outDir, String[] legalExtns) {
	outFileName = new File(outDir, "longchek.html");
	this.legalExtns = legalExtns;
    }

    public File getOutputFile() {
	return outFileName;
    }

    public String getName() {
	return "Detailed Checker";
    }

 
    public void run(OrderedList fileList)  throws IOException { 
	this.fileList = fileList;
	HTMLReportGenerator rep = new HTMLReportGenerator(outFileName);
	calculateAllStatistics();
	rep.generateLongCheckerReport (fileTypes, noOfFiles, sizeOfFiles, noOfDirs, totalEntries, legalExtns);
    }

    private void calculateAllStatistics() throws IOException {
	Enumeration fileListIter;
	DirectoryEntry dirEntry;
	fileListIter = fileList.getIterator();
	fileTypes = new Vector();
        
	while (fileListIter.hasMoreElements()) {
	    dirEntry = new DirectoryEntry();
	    dirEntry = (DirectoryEntry) fileListIter.nextElement();
	    if (dirEntry.isDirectory() == false) {
		String entryName = dirEntry.getEntry();
		StringTokenizer t = new StringTokenizer (entryName, "/");
		String temp = null; // Holds the temp string value
		try {
		    while (t.hasMoreElements()) {
			temp = (String) t.nextElement();
		    } 
		} catch (NoSuchElementException e) {
		    System.out.println ("Internal Error: Input file format incorrect");
		} 
		//
		// Now you have got the filename, so get the file extension
		//
		t = new StringTokenizer (temp, ".");
		String temp1 = null;
		try {
		    while (t.hasMoreElements()) {
			temp1 = (String) t.nextElement();
		    }
		}
		catch (NoSuchElementException e) {
		    System.out.println ("Internal Error: Input file format error");
		}
		if (fileTypes.contains((Object) temp1) == false) {
		    fileTypes.addElement((Object) temp1);
		}
	    }
	}	 

	noOfFiles = new long[fileTypes.size()];
	sizeOfFiles = new long[fileTypes.size()];
	//
	// Now Compute the size and number of files for each category
	//
	fileListIter = fileList.getIterator();
	while (fileListIter.hasMoreElements()){
	    dirEntry = (DirectoryEntry) fileListIter.nextElement();
	    if (dirEntry.isDirectory() == false) {
		String entryName = dirEntry.getEntry();
		StringTokenizer t = new StringTokenizer (entryName, "/");
		String temp = null; // Holds the temp string value
		try {
		    while (t.hasMoreElements()) {
			temp = (String) t.nextElement();
		    } 
		} catch (NoSuchElementException e) {
		    System.out.println ("Internal Error: Input file format incorrect");
		} 
		//
		// Now you have got the filename, so get the file extension
		//
		t = new StringTokenizer (temp, ".");
		String temp1 = null;
		try {
		    while (t.hasMoreElements()) {
			temp1 = (String) t.nextElement();
		    }
		}
		catch (NoSuchElementException e) {
		    System.out.println ("Internal Error: Input file format error");
		}
		for (int i = 0; i < fileTypes.size(); ++i) {
		    if (temp1.equals ((String) fileTypes.elementAt(i))) {
			long lVal = noOfFiles[i] + 1;
			noOfFiles[i] = lVal;
			long lSize = sizeOfFiles[i] + dirEntry.getSizeValue();
			sizeOfFiles[i] = lSize;
		    }
		}
	    } else {
		noOfDirs ++;
	    }
	}
	totalEntries = fileList.size();
    }   	        
}  





