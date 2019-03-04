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
// This is the FileSystemParser. If you dont have a zip list file, dont
// panic...All you need is a directory containing JCK files and we will
// take care of the rest..
// 

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;

public class FileSystemParser {
  
    private String name;
    private File outFileName;
    private int msgLevel;
    private Checker[] checkers;
    private Vector clusSizeVector = new Vector();
    private int[] clusterSizes;
    private OrderedList fileList;
    private int lineNo;

    public FileSystemParser (String name, File outFileName, int msgLevel, Checker[] checkers, 
			     int[] clusterSizes) {
	this.name = name;
	this.outFileName = outFileName;
	this.msgLevel = msgLevel;
	this.checkers = checkers;
	this.clusterSizes = clusterSizes;
	this.fileList = new OrderedList (80000, System.err);
	lineNo = 0;
    }

    public void run() throws IOException {
	HTMLReportGenerator report = new HTMLReportGenerator (outFileName);
	report.generateMainPage(checkers);
	createFileList();
	callCheckerPrograms();
    }

    private void createFileList () {
	Vector dirList = new Vector();
	dirList.addElement((Object) name);

	//
	// First get all the files in the directory list and create a 
	// vector of directories.
	//
	for (int i = 0; i < dirList.size(); ++i) {
	    File f = new File ((String) dirList.elementAt(i));
	    String entries[] =  f.list();
	    File entry = new File(name);
	    for (int j = 0; j < entries.length; ++j) {
		try {
		    entry = new File(f, entries[j]);
		} catch (OutOfMemoryError e) {
		    System.out.println ("Entry number = " + lineNo + " " + e.toString() + " " + entry.getPath());
		}
		if (entry.isDirectory()) {
		    dirList.addElement((Object) entry.getPath());
		}
		else {
		    ++lineNo;
		    if ((lineNo % 500 == 0) && (msgLevel == 2)) {
			System.out.println ("Processing #" + lineNo);
		    }
		    fileList.addNode ((Comparable) new DirectoryEntry (entry.getPath(), entry.length()));
		}
	    }
	} 
	//
	// Next, list all the directories
	//     
	for (int i = 0; i < dirList.size(); ++i) {
	    long dirSize = 0;
	    ++lineNo;
	    if ((lineNo % 500 == 0) && (msgLevel == 2)) {
		System.out.println ("Processing #" + lineNo);
	    }
	    fileList.addNode ((Comparable) new DirectoryEntry ((String) dirList.elementAt(i), dirSize));
	}    
    }

    //
    // callCheckerPrograms() - Calls the appropriate Checker
    // programs which does the processing of results and writes
    // the reports
    //
    private void callCheckerPrograms() throws IOException {
	for (int i = 0; i < checkers.length; ++i) {
	    checkers[i].run(fileList);
	}
    }
}

