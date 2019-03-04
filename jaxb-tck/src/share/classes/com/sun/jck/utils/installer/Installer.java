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
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


public class Installer {




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




    public Installer ()  {
    }



     public static void main (String [] args) {
	 Installer i = new Installer ();

	 try {
	    i.run(args);
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
	 String currentOption;
	 MegaZipFile mzf;
	 boolean installOK = false;

	 if (System.getProperty("DEBUG") != null)
	     m_verbosity = MegaZipFile.VERBOSE_LOUD;

	 //decode options: all options start with -
	 while (i < args.length ) {
	     
	     currentOption = args[i++];

	     if (currentOption.equalsIgnoreCase("-usage") || currentOption.equalsIgnoreCase("-help") 
		    || currentOption.equalsIgnoreCase("\\?") ) {

		 this.usage();
		 return;

	     }

	     else if(currentOption.equalsIgnoreCase("-q")) {

		 m_verbosity = MegaZipFile.VERBOSE_QUIET;

	     }

	     
	     else if(currentOption.equalsIgnoreCase("-l")) {

		 m_mode = MegaZipFile.MODE_LIST;

	     }
	     else if(currentOption.equalsIgnoreCase("-o")) {

		 if ( ! (i < args.length) ) {
		    throw new BadArgs("No value provided for output directory (-o) option");
		 }

		 m_outdir = new File(args[i++]);

	     }

	     else if(currentOption.equalsIgnoreCase("-f")) {

		 if ( ! (i < args.length) ) {
		    throw new BadArgs("No value provided for archive file (-f) option");
		 }

		 m_archiveFile = new File(args[i++]);

		 if (!m_archiveFile.exists()) {
		    throw new BadArgs("The archive File " + m_archiveFile.getPath() + " does not exist");

		 }


	     }


	     else if(currentOption.equalsIgnoreCase("-onError")) {

		 if ( ! (i < args.length) ) {
		    throw new BadArgs("No value provided for on Error (-onError) mode");
		 }

		 m_onError = args[i++];
	     }
	     else {

		throw new BadArgs("Unrecognized option " + currentOption);

	     }

	 }


	//validate args and options
	if (m_outdir == null)
	    m_outdir = new File(".");
	
	if (!m_outdir.isDirectory())
	    throw new BadArgs("The specified output directory " + m_outdir.getPath() 
				+ " is not a directory or can not be found.");

	if (!m_outdir.canWrite())
	    throw new BadArgs("You do not have permision to write to the output directory " + m_outdir.getPath() );


	//configure mzf

	if (m_archiveFile == null) {

	    m_archiveFile = new File (System.getProperty("java.class.path"));

	}

	try {
	    mzf = new MegaZipFile(m_archiveFile,System.out);
	    mzf.setVerbosity(m_verbosity);
	}
	catch (MegaZipFile.Fault f) {
	    throw new Fault ("An error occured while preparing to unpack the JCK Archive File. \n" +
				f.getMessage());
	}

	catch (IllegalArgumentException iae) {
	    throw new Fault ("An error occured while preparing to unpack the JCK Archive File. \n" +
				iae.getMessage());
	}

	if (m_onError != null) {
	    if (m_onError.equalsIgnoreCase("ignore"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_IGNORE);
	    else if (m_onError.equalsIgnoreCase("report"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_REPORT);
	    else if (m_onError.equalsIgnoreCase("fail"))
		mzf.setErrorMode(MegaZipFile.ON_ERROR_FAIL);
	    else 
		throw new BadArgs("Invalid OnError value: " + m_onError +"\n" +
				    "Valid values are ignore, report, fail.");
	}

	//do it
	try {
	    if (m_mode == MegaZipFile.MODE_LIST) 
		installOK = mzf.list();
	    else 
		installOK = mzf.unzip(m_outdir);
	}
	catch (Exception e) {
	    handleErrors(mzf.getErrors(), e);
	}

	if (!installOK) 
	    handleErrors(mzf.getErrors(), null);

     }

     private void handleErrors(Map zipErrors, Exception e) throws Fault {
	    
	    Iterator it; 
	    String entry;
	    MegaZipFile.EntryFault currentError;
	    Map.Entry me;

	    if (e != null)  {
		System.err.println("An exception occured while installing " + m_archiveFile.getPath() + ": " + e.getMessage());
		System.err.println();
	    }


	    if (zipErrors.size() > 0) {

		it = zipErrors.entrySet().iterator();

		System.err.println("Errors were encountered while installing the following files:");
		System.err.println("-------------------------------------------------------------");

		while (it.hasNext()) {

		    me = (Map.Entry) it.next();
		    currentError = (MegaZipFile.EntryFault) me.getValue();
		    System.err.println(currentError.getEntry() +" :");
		    System.err.println("     " + currentError.getMessage());
		    System.err.println();
		}

		System.err.println("-------------------------------------------------------------");
		System.err.println();
		System.err.println(zipErrors.size() + " Errors.");

	    }
	    throw new Fault ("Install Failed!");
     }

     public static void usage() {
	 String prog = System.getProperty("program", "java " + Installer.class.getName());
	 System.err.println("");
	 System.err.println("Usage: ");
	 System.err.println("  " + prog + " [options] ");
	 System.err.println("");
	 System.err.println("Options are:");
	 System.err.println("  -q                       extract JCK in quiet mode");
	 System.err.println("  -l                       list contents of JCK archive file");
	 System.err.println("  -onError 'mode '         mode of sensitivity Installer has to errors");
	 System.err.println("  -o 'target directory'    path of directory where JCK will be installed");
	 System.err.println("");

     }


    private File m_outdir;
    private int m_verbosity = MegaZipFile.VERBOSE_NORMAL;
    private int m_mode;
    private File m_archiveFile;
    private String m_onError;
}
