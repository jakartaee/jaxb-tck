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
// Program: Main.java
// Author : Rampalli Narasimhan
// Purpose: This program parses the command line arguments and invokes the 
//          appropriate parsers with the arguments passed.
//

package com.sun.jck.utils.jckfilecheck;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

public class Main {

    // 
    // msgLevel     : The amount of debug information at runtime.
    // DEFAULT_MSG  : The default set of system messages
    // VERBOSE_MSG  : The msgLevel will be verbose
    // zipFileName  : Name of the zip file to be parsed
    // outFileName  : Name of the output file for the HTML/Text/TeX/PS report
    // checkers  : Will contain a list of checks that are required to be done
    //                by this program like ISO, filecheck, accumlator, cluster etc.
    // legalExtns   : This vector contains a list of legal extensions permitted
    // clusterSize  : Size of a cluster : default is 512
    // parseDir     : Should I parse the file system?
    // directoryName: The name of the directory
    // parseZipTxt  : Should I parse the zip txt file.
    //
    private int msgLevel;
    static final int DEFAULT_MSG = 1;
    static final int VERBOSE_MSG = 2;
    private String zipFileName;
    private File outFileName;
    private String[] legalExtns;
    private Checker[] checkers;
    private int[] clusterSizes;
    private boolean parseDir = false;
    private boolean parseZipTxt = false;
    private String directoryName;

    //
    // main() triggers off the program
    //
    public static void main (String args[]) throws IOException {
	if(args.length > 0)
	    if(args[0].equals("-usage") || args[0].equals("-help") || args[0].equals("-h") || args[0].equals("/?")) {
		printHelp(System.out);
		System.exit(0);
	    }

	Main filecheck = new Main();
	if (args.length == 0) {
	    // Incorrect Usage. Print Help.
	    printHelp(System.out);
	    return;
	}
	if (filecheck.init(args)) {
	    filecheck.run();
	} else {
	    printHelp(System.out);
	}
    }

    //
    // Constructor: Sets the default values
    //
    public Main() {
	msgLevel = DEFAULT_MSG;
	zipFileName = null;
	outFileName = null;
    } 


    // 
    // printHelp(System.out)
    //
    public static void printHelp (PrintStream out) {
	out.println ("filecheck usage:");
	out.println ("     -i : Name of the input file (or)");
	out.println ("  -d dir: You should give the name of a directory dir");
	out.println (" If you have given both -i and -d, the latter one will be considered");
	out.println ("     -o : Name of the output directory (or primary output file)");
	out.println (" -extns : Start the extension file list");
	out.println ("   -end : End the extension file list");
	out.println ("-c Size : do cluster check for this cluster size");
	out.println ("-cbegin : Begin multiple cluster sizes");
	out.println ("  -cend : End multiple cluster sizes");
	out.println ("     -a : Accumulator - No. of files and Dirs check");
	out.println ("   -iso : Do an ISO 9660 Spec Check");
	out.println ("     -f : do detailed file/dirs analysis");
	out.println ("     -v : Verbose Message");
    }

    //
    // init (String args)
    //
    private boolean init (String args[]) {  
	Vector clusSizeVector = new Vector();
	Vector legalExtnsVector = new Vector();
	boolean doISO = false;
	boolean doCluster = false;
	boolean doFileAnalysis = false;
	boolean doAccumulator = false;

	for (int i = 0; i < args.length; ++i) {
	    if (args[i].equals("-a")) {
		doAccumulator = true;
	    } 
	    else if (args[i].equals("-c")) {
		doCluster = true;
		clusSizeVector.addElement(Integer.valueOf(args[++i]));
	    }
	    else if (args[i].equals("-cbegin")) {
		doCluster = true;
		try {
		    while (!args[++i].equals("-cend")) {
			clusSizeVector.addElement(new Integer(args[i]));
		    }
		} 
		catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println ("Error: Did you forget the -cend?");
		    return false;
		} 
	    } 
	    else if (args[i].equals("-d")) {
		directoryName = args[++i];
		parseDir = true;
		parseZipTxt = false;
	    } 
	    else if (args[i].equals("-extns")) {
		try {
		    while (!args[++i].equals("-end")) {
			legalExtnsVector.addElement(args[i]);
		    }
		} 
		catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println ("Error: Did you forget the -end?");
		    return false;
		} 
	    }
	    else if (args[i].equals("-f")) {
		doFileAnalysis = true;
	    }  
	    else if (args[i].equals("-i")) {
		zipFileName = args[++i];
		parseZipTxt = true;
		parseDir = false;
	    } 
	    else if (args[i].equals("-iso")) {
		doISO = true;
	    } 
	    else if (args[i].equals("-o")) {
		outFileName = new File(args[++i]);
	    } 
	    else if (args[i].equals("-v")) {
		msgLevel = VERBOSE_MSG;
	    }
	    else {
		System.out.println ("Error: Did you use any incorrect options?");
		return false;
	    }
	}

	legalExtns = new String[legalExtnsVector.size()];
	legalExtnsVector.copyInto(legalExtns);

	if (clusSizeVector.size() == 0)
	    clusterSizes = new int[] {512};
	else {
	    clusterSizes = new int[clusSizeVector.size()];
	    for (int i = 0; i < clusSizeVector.size(); i++) {
		clusterSizes[i] = ((Integer)clusSizeVector.elementAt(i)).intValue();
	    }
	}

	if (outFileName == null) {
	    if (msgLevel == VERBOSE_MSG) {
		System.out.println ("Warning: No Output Filename given. Using default - jckreport.html");
	    }
	    outFileName = new File("jckreport.html");
	}

	File outDir;
	if (outFileName.isDirectory()) {
	    outDir = outFileName;
	    outFileName = new File(outDir, "jckReport.html");
	}
	else {
	    String p = outFileName.getParent();
	    if (p == null)
		p = System.getProperty("user.dir");
	    outDir = new File(p);	
	}

	Vector checkVector = new Vector();
	if (doISO) 
	    checkVector.addElement(new ISOChecker(outDir));
	if (doCluster) 
	    checkVector.addElement(new ClusterChecker(outDir, clusterSizes));
	if (doAccumulator)  
	    checkVector.addElement(new ShortChecker(outDir));
	if (doFileAnalysis) 
	    checkVector.addElement(new LongChecker(outDir, legalExtns));

	checkers = new Checker[checkVector.size()];
	checkVector.copyInto(checkers);

	if (checkers.length == 0) {
	    System.out.println ("Error: You have not specified what checks should be done!");
	    return false;
	}
     
	if ((zipFileName == null) && (directoryName == null)) {
	    System.out.println ("Error: No input file name or Input Directory name given");
	    return false;
	}

	return true;
    }
   
    //
    // run() will start the Parsers. 
    //
    public void run() throws IOException {
	ZipListParser zlp = null;
	FileSystemParser fsp = null;

	if (parseZipTxt) {
	    if (msgLevel == VERBOSE_MSG) {
		System.out.println("Starting the Zip File Parser to parse the input file");
	    }
	    zlp = new ZipListParser(zipFileName, outFileName, msgLevel, 
				     checkers, clusterSizes);
	    zlp.run();
	}
	if (parseDir) {
	    if (msgLevel == VERBOSE_MSG) {
		System.out.println("Starting the File System Parser to parse the input directory");
	    }
	    fsp = new FileSystemParser(directoryName, outFileName, msgLevel, 
					checkers, clusterSizes);
	    fsp.run();
	}

	if (msgLevel == VERBOSE_MSG) {
	    System.out.println ("All Processing Completed....");
	}
    }
}






