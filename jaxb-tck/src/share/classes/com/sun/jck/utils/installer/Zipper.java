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
 *
 * */


package com.sun.jck.utils.installer;

import java.io.File;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


public class Zipper {



    class BadArgs extends Exception {
	BadArgs(String msg) {
	    super(msg);
	}
    }




    class Fault extends Exception {
	Fault(String msg) {
	    super(msg);
	}
    }




    public Zipper ()  {
    }



     public static void main (String [] args) {
	 Zipper z = new Zipper();

	 try {
	    z.run(args);
	    System.exit(0);
	 }
	 catch (BadArgs b) {
	     System.err.println("Invalid or poorly formed command line argument:");
	     System.err.println(b.getMessage());
	     usage();
	     System.exit(1);
	 }
	 catch (Fault f) {
	     System.err.println(f.getMessage());
	     System.exit(2);
	 }
	 catch (Throwable t) {
	     t.printStackTrace(System.err);
	     System.exit(3);
	 }
     }




     public void run(String args [])  throws BadArgs, Fault {

	 int i = 0;
	 MegaZipFile mzf = null;
	 File archiveDir;
	 File currentFile;
	 Iterator fileList;
	 String currentOption;
	 File [] fileListArray;
	 String [] classArray;
	 boolean zipOK = false;

	 //decode options: all options start with -
	 while (i < args.length && args[i].startsWith("-") ) {

	     currentOption = args[i++];

	     if (currentOption.equalsIgnoreCase("-usage") || currentOption.equalsIgnoreCase("-help")) {

		 this.usage();
		 return;

	     }

	     else if(currentOption.equalsIgnoreCase("-q")) {

		 m_verbosity = MegaZipFile.VERBOSE_QUIET;

	     }

	     else if(currentOption.equalsIgnoreCase("-v")) {

		 m_verbosity = MegaZipFile.VERBOSE_LOUD;

	     }


	     else if(currentOption.equalsIgnoreCase("-n")) {

		 String intValue;

		 if (! (i < args.length) ){
		    throw new BadArgs("No value provided for maximum entry (-n) option");
		 }

		 intValue = args[i++];

		 try {
		     m_maxEnt = Integer.parseInt(intValue);
		 }
		 catch (NumberFormatException nse) {
		    throw new BadArgs("The value provided for maximum entry (-n) option" 
					+ intValue + " is not a valid integer");
		 }

	     }
	     
	     else if(currentOption.equalsIgnoreCase("-m")) {

		 if ( ! (i < args.length) ) {
		    throw new BadArgs("No value provided for main class (-m) option");
		 }

		 m_mainClass = args[i++];
	     }

	     else if(currentOption.equalsIgnoreCase("-e")) {

		 if ( !(i < args.length) ) {
		    throw new BadArgs("No value provided for export-class file (-e) option");
		 }

		 m_exportClass = new File(args[i++]);
	     }

	     else if(currentOption.equalsIgnoreCase("-t")) {

		 if ( !(i < args.length) ) {
		    throw new BadArgs("No value provided for temporary directory (-t) option");
		 }

		 m_tempDir = new File(args[i++]);

	     }

	     else if(currentOption.equalsIgnoreCase("-f")) {

		 if ( !(i < args.length) ) {
		    throw new BadArgs("No value provided for top level file (-f) option");
		 }

		 m_topLevelFile.add(new File(args[i++]));
	     }

	     else if(currentOption.equalsIgnoreCase("-c")) {

		 if ( !(i < args.length) ) {
		    throw new BadArgs("No value provided for copyright file (-c) option");
		 }

		 m_copyRightFile = new File(args[i++]);
	     }

	     else if(currentOption.equalsIgnoreCase("-onError")) {

		 if ( !(i < args.length) ) {
		    throw new BadArgs("No value provided for on Error (-onError) option");
		 }

		 m_onError= args[i++];
	     }
	     else {

		throw new BadArgs("Unrecognized option " + currentOption);

	     }

	 }

	//get args
	if ( ! (i < args.length) ) 
	    throw new BadArgs("Target zip file was not specified on command line");
	
	m_archiveFile = new File(args[i++]);
	archiveDir = new File(m_archiveFile.getParent() == null ? "." : m_archiveFile.getParent());

	if ( ! (i < args.length)  ) 
	    throw new BadArgs("No files specified on command line");
	
	while ( i < args.length) {
	    m_files.add( new File(args[i++]));
	}


	//validate args and options
	if (m_maxEnt <= 0 || m_maxEnt > MegaZipFile.MEGAZIP_MAX_ENTRIES) {
	    throw new BadArgs("Invalid maximum entry value: " + m_maxEnt + "\n" 
				+"Value must be between 1 and " + MegaZipFile.MEGAZIP_MAX_ENTRIES);
	}

	if (m_archiveFile.exists())
	    throw new BadArgs("The target zip file " + m_archiveFile.getPath() + " already exists.");

	if (!archiveDir.exists()) 
	    throw new BadArgs("The output directory "+ archiveDir.getPath() 
			    + " for the zip file does not exist.");
	
	if (!archiveDir.canWrite()) 
	    throw new BadArgs("The output directory "+ archiveDir.getPath() + " is not writable.");
	
	if (m_copyRightFile != null) {

	    if ( !m_copyRightFile.exists())
		throw new BadArgs("Can not find copyright file "+ m_copyRightFile.getPath() );

	    if ( !m_copyRightFile.isFile())
		throw new BadArgs("copyright file "+ m_copyRightFile.getPath() + " is not a regular file." );

	    if ( !m_copyRightFile.canRead())
		throw new BadArgs("Can not read copyright file "+ m_copyRightFile.getPath() );
	}

	if (m_exportClass != null) {

	    if ( !m_exportClass.exists())
		throw new BadArgs("Can not find export-class file "+ m_exportClass.getPath() );

	    if ( !m_exportClass.isFile())
		throw new BadArgs("Export-class file "+ m_exportClass.getPath() + " is not a regular file." );

	    if ( !m_exportClass.canRead())
		throw new BadArgs("Can not read export-class file "+ m_exportClass.getPath() );
	}

	if (m_topLevelFile.size() != 0) {
	    Iterator iter = m_topLevelFile.iterator();
	    File currentTF;

	    while (iter.hasNext()) {
		try {
		    currentTF = (File) iter.next();

		    if (!currentTF.exists()) 
			throw new BadArgs("Can not find top level file "+ currentTF.getPath() );

		    if (!currentTF.isFile()) 
			throw new BadArgs("Top level file "+ currentTF.getPath() + " is not a regular file." );

		    if (!currentTF.canRead()) 
			throw new BadArgs("Can not read top level file "+ currentTF.getPath() );
		}
		catch (NoSuchElementException nse) {
		    throw new Fault("An error occured while validating top level files.\n" +
				    nse.getMessage());
		}
	    }

	}

	if (m_tempDir != null && !m_tempDir.exists()) 
	    throw new BadArgs("The temporary directory "+ m_tempDir.getPath() + " does not exist.");
	
	if (m_tempDir != null && !m_tempDir.canWrite()) 
	    throw new BadArgs("The temporary directory "+ m_tempDir.getPath() + " is not writable.");

	fileList = m_files.iterator();
	while (fileList.hasNext()) {
	    try {
		currentFile = (File) fileList.next();
	    }
	    catch (NoSuchElementException nse) {
		throw new Fault(nse.getMessage());
	    }

	    if (!currentFile.exists())
		throw new BadArgs("Can not find file " + currentFile.getPath());

	    if (!currentFile.canRead())
		throw new BadArgs("Can not read file " + currentFile.getPath());

	}


	//configure mzf
	try {
	    mzf = new MegaZipFile(m_archiveFile,System.out);
	}
	catch (MegaZipFile.Fault f) {
	    throw new Fault ("Unable to create MegaZipFile.\n" + f.getMessage());
	}

	if (m_onError != null ) {
	    if (m_onError.equalsIgnoreCase("ignore"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_IGNORE);
	    else if (m_onError.equalsIgnoreCase("report"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_REPORT);
	    else if (m_onError.equalsIgnoreCase("fail"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_FAIL);
	    else
		throw new BadArgs("Invalid On Error value " + m_onError + "\n" + 
				"Valid values are ignore, report, or fail");
	}

	try {
	    mzf.setVerbosity(m_verbosity);

	    mzf.setSubZipEntries(m_maxEnt);

	    if (m_exportClass != null)
		mzf.setExportClasses(m_exportClass);

	    if (m_tempDir != null) 
		mzf.setTempDir(m_tempDir);

	    if (m_copyRightFile != null) 
		mzf.setCopyRightFile(m_copyRightFile);

	    if (m_topLevelFile.size() != 0)
		mzf.addTopLevelEntry((File[]) m_topLevelFile.toArray(new File[]{}));

	    if (m_mainClass != null)
		mzf.setMainClass(m_mainClass);

	}
	catch (IllegalArgumentException iae) {
	    throw new Fault ("Illegal Argument encountered while configure MegaZipFile" +
			    "\n" + iae.getMessage());
	}
	catch (IOException ioe) {
	    throw new Fault ("An IO exception was thrown while configure MegaZipFile.\n" +
			    "This could be a problem with temporary directory used by the MegaZipFile. \n" +
			    "\n" + ioe.getMessage());
	}
	catch (MegaZipFile.Fault f) {
	    throw new Fault ("A Fault was encountered while configure MegaZipFile" +
			    "\n" + f.getMessage());
	}

	//do it
	try {
	    fileListArray = new File[m_files.size()];
	    zipOK = mzf.zip((File[]) m_files.toArray(fileListArray));
	}
	catch (Exception e) {
	    handleErrors(mzf.getErrors(), e);
	}

	if (!zipOK) 
	    handleErrors(mzf.getErrors(), null);
     }

    private void handleErrors(Map zipErrors, Exception e) throws Fault {
	    
	    Iterator it; 
	    String entry;
	    MegaZipFile.EntryFault currentError;
	    Map.Entry me;

	    if (e != null)  {
		System.err.println("An exception occured while zipping "  + m_archiveFile.getPath() +":");
		System.err.println("     "+e.getMessage());
	    }


	    if (zipErrors.size() > 0) {

		System.err.println();
		System.err.println("Errors were encountered when zipping the following entries:");
		System.err.println("-----------------------------------------------------------");

		it = zipErrors.entrySet().iterator();

		while (it.hasNext()) {

		    me = (Map.Entry) it.next();
		    currentError = (MegaZipFile.EntryFault) me.getValue();
		    System.err.println();
		    System.err.println(currentError.getEntry() +" :");
		    System.err.println("     " + currentError.getMessage());
		}

		System.err.println("-----------------------------------------------------------");
		System.err.println(zipErrors.size() + " Errors.");
		System.err.println();
	    }
	    throw new Fault ("Zip Failed!");
     }

     public static void usage() {
	 String prog = System.getProperty("program", "java " + Zipper.class.getName());
	 System.err.println("");
	 System.err.println("Usage: ");
	 System.err.println("  " + prog + " [options] file list...");
	 System.err.println("");
	 System.err.println("Options are:");
	 System.err.println("  -q                       execute in quiet mode");
	 System.err.println("  -v                       execute in verbose mode");
	 System.err.println("  -n 'subZipEntries'       number of entries allowed in a subZip");
	 System.err.println("  -m 'mainClass'           class to be deemed Main Class in resulting jar file");
	 System.err.println("  -t 'tmpDir'              directory to contain work files created by zipper");
	 System.err.println("  -e 'export-class file'   path to file containing classes to be included in jar");
	 System.err.println("  -f 'top level file'      path to file which is to be added at top level of jar");
	 System.err.println("  -c 'copy right file'     path to copy right file which should be added to jar");
	 System.err.println("  -onError 'mode'          mode describing sensitivity zip has to errors");
	 System.err.println("");
	 System.err.println("file                       path to the jar file to be created");
	 System.err.println("list                       list of files and directories to added to jar");
	 System.err.println("");

     }


    private int m_verbosity = MegaZipFile.VERBOSE_NORMAL;
    private int m_maxEnt = MegaZipFile.MEGAZIP_MAX_ENTRIES;
    private File m_tempDir;
    private String m_mainClass;
    private File m_exportClass; 
    private ArrayList m_topLevelFile = new ArrayList();
    private ArrayList m_files = new ArrayList();
    private File m_archiveFile;
    private File m_copyRightFile;
    private String m_onError;
}
