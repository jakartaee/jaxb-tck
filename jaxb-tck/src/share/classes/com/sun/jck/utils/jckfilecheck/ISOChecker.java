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
// Program: ISOChecker.java
// Author : Rampalli Narasimhan
// Purpose: To check for ISO 9660 Specifications
//

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Vector;


public class ISOChecker implements Checker {

    private File outFileName;
    private OrderedList fileList;
    private ISODataBase isoDataEntry;
    private OrderedList ISODataList;
    private long prevDepth = 0;
 
    private long vISOData[] = new long[8];

    public ISOChecker (File outDir) {
	outFileName = new File(outDir, "isochek.html");
    }

    public File getOutputFile() {
	return outFileName;
    }

    public String getName() {
	return "ISO Checker";
    }

 
    public void run(OrderedList fileList) throws IOException { 
	this.fileList = fileList;
	HTMLReportGenerator rep = new HTMLReportGenerator(outFileName);
	calculateAllStatistics();
	rep.generateISOCheckerReport (ISODataList, vISOData);
    }

    private void calculateAllStatistics() {
   
	Vector fileNameClash = new Vector();
	Enumeration fileListIter;
	ISODataBase isoDataEntry;
	boolean inDirectory = false;
	ISODataList = new OrderedList (fileList.size());
	fileListIter = fileList.getIterator();
	int depth = 0;
	Vector dirNameVector = new Vector(); /* This vector contains all the directory names */
	while (fileListIter.hasMoreElements()) {
	    DirectoryEntry temp = new DirectoryEntry();
	    temp = (DirectoryEntry) fileListIter.nextElement();
	    if (temp.isDirectory()) {
		//
		// Do all the checking for a directory that is required
		// 
		inDirectory = true; 
		/* Is there a clash in directory names if directory name is reduced to 8 chars? */
		boolean isClash = false;
		boolean isInvalidName = false;
		fileNameClash = new Vector();
		String name = temp.getEntry();
		StringTokenizer t = new StringTokenizer (name, "/");
		String temp1 = null; // This will hold the directory name which is required for other checks;
		try {
		    while (t.hasMoreElements()) {
			temp1 = (String) t.nextElement();
			depth += 1;
		    }
		} catch (NoSuchElementException e) {
		    System.err.println ("Unexpected Exception: " + e.toString());
		}
		//
		// We need to do this check for directories that contains
		// no files - only sub-directories. In which case we need
		// to ensure that the dirNameVector is cleaned up and its
		// filled correctly
		// 
		if ((prevDepth + 1) == depth) {
		    dirNameVector = new Vector();
		}
		prevDepth = depth;
		//
		// Check if directory has any invalid characters
		//
         
		char dName[] = new char[temp1.length()];
		temp1.getChars(0, temp1.length(), dName, 0);
		if (dName.length > 8) {
		    isInvalidName = true;
		}
		for (int a = 0; a < dName.length; ++a) {
		    if (((dName[a] >= 'a') && (dName[a] <= 'z')) || ((dName[a] >= 'A') && (dName[a] <= 'Z'))) {
		    } else { 
			isInvalidName = true;
		    }
		}

		//
		// If the directory name is valid, then we should check if the directory
		// has a name clash when reduced to 8 character length.
		// We need to do this if and only if we are inside a directory
		//
		if (!isInvalidName) {
		    char shtName[] = new char[8];
		    if (temp1.length() > 8) {
			temp1.getChars (0, 8, shtName, 0);
		    } else {
			temp1.getChars (0, temp1.length(), shtName, 0);
		    }   
		    String uctemp1 = temp1.toUpperCase();
		    if (!dirNameVector.contains(uctemp1)) {
			dirNameVector.addElement ((Object) uctemp1);
		    } else {
			System.out.println ("Already has: " + uctemp1 + " :: " + name);
			isClash = true;
		    }
		}
		if (isInvalidName) {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.INV_DIR));
		    depth = 0;
		} else if (isClash) {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.CLASH_DIR));
		    depth = 0;
		}  else if (depth > 8) {
		    ISODataList.addNode ( new ISODataBase(temp.getEntry(), ISODataBase.INV_DIR_DEPTH));
		    depth = 0;
		} else if (((temp.getEntry()).length()) > 32) {
		    ISODataList.addNode ( new ISODataBase(temp.getEntry(), ISODataBase.LONG_DIR));
		    depth = 0;
		} else {
		    ISODataList.addNode (new ISODataBase(temp.getEntry(), ISODataBase.OK));
		    depth = 0;
		}      

	    } else {

		// This is a filename. So do all the filename checking

		boolean isClash = false;
		boolean isInvalidName = false;
		String bName = null;
		String bExtn = null;
		int    bDots = 0;
		int nDots = 0;
		dirNameVector = new Vector();
		String name = temp.getEntry();
		StringTokenizer t = new StringTokenizer (name, "/");
		String temp1 = null; // Holds the filename
		//
		// Our first task is to filter out the filename (full) 
		//
		try {
		    while (t.hasMoreElements()) {
			temp1 = (String) t.nextElement();
		    }
		} catch (NoSuchElementException e) {
		    System.err.println ("Unexpected Exception: " + e.toString());
		}
		t = new StringTokenizer (temp1, ".");
		try {
		    while (t.hasMoreElements()) {
			bName = temp1;
			++bDots;
			temp1 = (String) t.nextElement();
		    }
		    bExtn = temp1;
		} catch (NoSuchElementException e) {
		    System.err.println ("Unexpected Exception: " + e.toString());
		}
        
        
		//
		// Check if the full filename is a validname or not
		//    
		char nFirst[] = new char[bName.length()];
		String sFirst = null;
		char nExtn[] = new char[bExtn.length()];
		String sExtn = null;
      
       
		bName.getChars (0, bName.length(), nFirst, 0);
		bExtn.getChars (0, bExtn.length(), nExtn, 0);
       
		for (int a = 0; a < nFirst.length; a++) {
		    if (((nFirst[a] >= 'a') && (nFirst[a] <= 'z')) || 
			((nFirst[a] >= 'A') && (nFirst[a] <= 'Z')) ||
			((nFirst[a] >= '0') && (nFirst[a] <= '9')) ||
			(nFirst[a] == '_')) {
			/* No Problems, just move on to the next character */
		    }
		    else {
			isInvalidName = true;
		    }
		}
		for (int a = 0; a < nExtn.length; a++) {
		    if (((nExtn[a] >= 'a') && (nExtn[a] <= 'z')) ||
			((nExtn[a] >= 'A') && (nExtn[a] <= 'Z')) || 
			((nExtn[a] >= '0') && (nExtn[a] <= '9')) ||
			(nExtn[a] == '_')) {
			/* No problems, just move on to the next character */
		    } else {
			isInvalidName = true;
		    }
		}
   
		//
		// Check for 8.3 filename clash
		//
		nFirst = new char[8];
		nExtn  = new char[3];

		if (bName.length() >= 8) {
		    bName.getChars (0, 8, nFirst, 0);
		} else {
		    bName.getChars (0, bName.length(), nFirst, 0);
		}

		if (bExtn.length() >= 3) {
		    bExtn.getChars (0, 3 , nExtn, 0);
		} else {
		    bExtn.getChars (0, bExtn.length(), nExtn, 0);
		}
      
		sFirst = String.valueOf (nFirst);
		sExtn  = String.valueOf (nExtn);
      
		temp1 = sFirst + "." + sExtn;
		/* Convert the string to all UPPER CASE */
		String uctemp1 = temp1.toUpperCase();
		if (!fileNameClash.contains(uctemp1)) {
		    fileNameClash.addElement ((Object) uctemp1);
		} else { 
		    isClash = true;
		}
		if ((bDots > 2) || isInvalidName) {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.INV_FILE));
		} else if (isClash) {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.CLASH_FILE));
		} else if ((bName.length() > 8) || (bExtn.length() > 3)) {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.LONG_FILE));
		} else {
		    ISODataList.addNode (new ISODataBase (temp.getEntry(), ISODataBase.OK));
		} 
	    } 
	}
	fileListIter = ISODataList.getIterator ();
	while (fileListIter.hasMoreElements()) {
	    isoDataEntry = (ISODataBase) fileListIter.nextElement();
	    int vtemp = isoDataEntry.getStatus();
	    vISOData[vtemp] += 1;
	}
    }  	        
}  



