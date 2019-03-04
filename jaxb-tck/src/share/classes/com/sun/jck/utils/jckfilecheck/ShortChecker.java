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
// Program: ShortChecker.java  (Not a very creative name :-( )
// Author : Rampalli Narasimhan
// Purpose: Just do a statistics check on the number of files/directories
//         

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class ShortChecker implements Checker {
    
    // 
    // fileList    : A list of entries processed by the parser
    // sizeOfFiles : size of all the files in the list
    // noOfFiles   : Number of all the files in the list
    // noOfDirs    : Number of all the directories in the list
    //
    private OrderedList fileList;
    private File outFileName;
    private long sizeOfFiles = 0;
    private long noOfFiles = 0;
    private long noOfDirs = 0;

    //
    // Constructor()
    //
    public ShortChecker (File outDir) {
	outFileName = new File(outDir, "shrcheck.html");
    }

    public File getOutputFile() {
	return outFileName;
    }

    public String getName() {
	return "Accumulator Checker";
    }

    //
    // run() - calculate the sizes and generate the report
    //
    public void run(OrderedList fileList) throws IOException {
	this.fileList = fileList;
	HTMLReportGenerator rep = new HTMLReportGenerator (outFileName);
	calculateStatistics();
	rep.generateShortCheckerReport (noOfFiles, noOfDirs, sizeOfFiles);
    }

    private void calculateStatistics() {
	Enumeration fileListIter = fileList.getIterator();
	DirectoryEntry entry = new DirectoryEntry();
	while (fileListIter.hasMoreElements()) {
	    entry = (DirectoryEntry) fileListIter.nextElement();
	    if (entry.isDirectory()) {
		noOfDirs += 1;
	    } else {
		noOfFiles += 1;
		sizeOfFiles += entry.getSize();
	    }
	}
	System.out.println ("Dirs  = " + noOfDirs);
	System.out.println ("Files = " + noOfFiles);
    }
}
