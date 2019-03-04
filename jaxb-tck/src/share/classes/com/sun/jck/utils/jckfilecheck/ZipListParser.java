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
// Program: ZipListParser.java
// Author : Rampalli Narasimhan
// Purpose: To parse the input file and then call the appropriate checker
//          programs - ISOChecker, ShortChecker, ClusterChecker, LongChecker
//          First read the input text and create the OrderedList. Then pass
//          that as an argument to each of these checker programs depending
//          upon the arguments passed to Main.
//

package com.sun.jck.utils.jckfilecheck;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;


public class ZipListParser {

    //
    // msgLevel     : How verbose you want the system to operate?
    // zipFileName  : The input file name
    // outFileName  : The output file name
    // checkers  : The list of checks that needs to be done
    // lineNo       : Current line Number in the input file
    //
    private int msgLevel = 1;
    private String zipFileName = null;
    private File outFileName;
    private Checker[] checkers;
    private int lineNo = 0; 
    private InputStream iStream;
    private OrderedList fileList = new OrderedList (60000, System.err);
    private int[] clusterSizes;
    protected int c;

    //
    // Constructor()
    //
    public  ZipListParser (String zipFile, File outFile, int msgLevel, 
			   Checker[] checkers, 
			   int[] clusterSize) 
	{
	    zipFileName = zipFile;
	    outFileName = outFile;
	    this.msgLevel = msgLevel;
	    this.checkers = checkers;
	    this.clusterSizes = clusterSizes;
	}


    //
    // run() - calls parser to parse the zip file text. Then
    // calls the appropriate checker programs.
    //
    public void run() throws IOException {
	HTMLReportGenerator report = new HTMLReportGenerator (outFileName);
	report.generateMainPage(checkers);
	FileInputStream fis = new FileInputStream (zipFileName);
	BufferedInputStream bis = new BufferedInputStream (fis, 4096);
	iStream = bis;
	try {
	    parseZipFile();
	} catch (OutOfMemoryError e) {
	    System.out.println ("Error: Out of Memory at line " + lineNo);
	}
	callCheckerPrograms();
    }

    //
    // parseZipFile() - parses the input zip file and generates the fileList
    // that contains the information we need to call the checker programs.
    //
    private void parseZipFile() throws IOException {

	String tok;                    // The file/directory name
	boolean inList = false;
	long fileSize;                 // The file/directory size

	// The parsing starts here
	nextChar();

	while (c > 0) {
	    tok = readToken ();
	    if (msgLevel == 2) {
		System.err.println("Token: " + tok);
	    }
	    if (tok.equals ("------")) {
		if (msgLevel == 2) {
		    System.err.println ("In List ...");
		}
		inList = !inList;
		skipToEOL();
		continue;
	    }
	    if (inList) {
		//
		// In order to understand this you need to take a look at the 
		// input file. The input file contains the following format:
		// 
		// size date time name
		// 
		// All we need is the size and the name
		//
		fileSize = Long.parseLong (tok);
		skipSpace();
		skipToken();
		skipSpace();
		skipToken();
		skipSpace();
		tok = readToken();
		skipToEOL();
		fileList.addNode ((Comparable) new DirectoryEntry (tok, fileSize));
		if (msgLevel == 2) {
		    System.err.println (fileSize + "... " + tok);
		}
		if ((lineNo % 500 == 0) && (msgLevel == 2)) {
		    System.out.println ("Working on...: " + tok);
		}
	    }
	}
    }
    
    //
    // The following functions are all helper functions. They help the parsing
    // process.
    //              
    protected void nextChar() throws IOException {
	c = iStream.read();
	if (c == '\n') {
            ++lineNo;   
	}
    }

    protected boolean isSpace() {
	return ((c == ' ') || (c == '\t') || (c == '\n') || (c == '\r'));
    }

    protected void skipToEOL() throws IOException {
	while (c != '\n') {
	    nextChar();
	}
	nextChar();
    }

    protected void skipSpace() throws IOException {
	while (isSpace()) {
	    nextChar();
	}
    }

    protected void skipToken() throws IOException {
	while (! isSpace()) {
	    nextChar();
	}
    }

    protected String readToken() throws IOException {
	int i;
	int bufSize = 1024;
	char buf[] = new char[bufSize];
	skipSpace();
     
	for (i = 0; i < bufSize && (! isSpace()); ++i) {
	    buf[i] = (char) c;
	    nextChar();
	}
	return new String (buf, 0, i);
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



