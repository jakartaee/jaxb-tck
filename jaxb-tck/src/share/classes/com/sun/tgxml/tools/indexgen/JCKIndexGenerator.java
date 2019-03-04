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

package com.sun.tgxml.tools.indexgen;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;

import java.util.Vector;

import org.xml.sax.*;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.ToolBase;

/**
 * Wrapper tool performing directory walking and
 * producing index.html files for JCK testsuite.
 */

public class JCKIndexGenerator extends ToolBase {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "JCKIndexGenerator";

    /**
     * Root dir of .doc.xml files tree
     */
	File xmlDir;

    /**
     * Root dir of resulting html files tree
     */
     File jckDir;
     
     String copyrightLink=null;

   /*
    * ----------------------------------------------------------------------
    *    Options parsing methods
    * ----------------------------------------------------------------------
    */

    protected StringOption xmlDirOption = new StringOption("-xmldir",
        "  -xmldir <xmldirpath>   root of tree containing .doc.xml files (obligatory)",
        OBLIGATORY);

    protected StringOption jckDirOption = new StringOption("-jckdir",
        "  -jckdir <jckdirpath>  root of output tree for .html filese (obligatory)",
        OBLIGATORY);

    protected StringOption copyrightLinkOption = new StringOption("-copyrightLink",
        "  -copyrightLink <pathToCopyrightFile>   path (relative) to copyright file (optional)");

    /**
     * Registers options.
     */
    public void registerOptions() {
        super.registerOptions();
        addOption(xmlDirOption);
        addOption(jckDirOption);
        addOption(copyrightLinkOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt>
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (xmlDirOption.isSet()) {
            xmlDir = new File(xmlDirOption.getStringValue());
            if (!xmlDir.isDirectory()) {
                throw new ParseArgumentException("xmldir is not a directory: " + xmlDir);
            }
        }
        if (jckDirOption.isSet()) {
            jckDir = new File(jckDirOption.getStringValue());
            if (!jckDir.isDirectory()) {
                throw new ParseArgumentException("jckdir is not a directory: " + jckDir);
            }
        }
        if (copyrightLinkOption.isSet()){
            copyrightLink = copyrightLinkOption.getStringValue();
        }
        super.applyOptionsValues();
    }

   /*
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */

    /*
     * =========================================================================
     *    Methods
     * =========================================================================
     */

    /**
     * Program entry
     *
     * @param args The command line arguments to  this tool.
     */
    public static void main(String args[]) {
  		JCKIndexGenerator generator = new JCKIndexGenerator(System.out, System.err);
        generator.setProgramName(CtStr_ToolName);
        System.exit(generator.run(args));
    }

    /** Constructor.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream
     */
    public JCKIndexGenerator(PrintStream out, PrintStream err) {
		super(out, err);
		m_needsCommandLineArguments = true;
    }


    /**
     *  Converts all .doc.xml files into .html files.
     */
    public void startTool() {
    	try {
    		// the second argument must be "" or null to indicate that there 
    		// should be no links to upper indexes in the root html file.
    		// if copyrightLink == null, then no Copyright generation.
     		Vector topElems = generateIndex(jckDir, "", copyrightLink);
/*
    		if ((topElems.size() != 1)
				|| ! ((File)(topElems.firstElement())).getAbsolutePath().endsWith("testsuite.doc.xml")) {
     			reportErrorMsg("Unresolved links for the top directory remain.");
     			reportErrorMsg(topElems.size() + " elemets:");
        		for (int i = 0; i < topElems.size(); i++)
          			reportErrorMsg(((File)topElems.get(i)).getAbsolutePath());
       			setResultCode(ctInt_ErrorCode_Error);
     		}
*/
		} catch (TestFileException e) {
            reportErrorMsg(e.getMessage());
			setResultCode(ctInt_ErrorCode_Error);
        }
      	return;
    }

    /**
     * The base method for recursive tree walk.
     * Generates index html file for one directory.
     *
     * @param dir  Directory to generate index for.
     */
    protected Vector generateIndex(File dir, String upperIndexFileName, String pathToCopyright) throws TestFileException {
	String currentIndexFN="";
    	Vector linkHolder = new Vector();

    	if (!dir.isDirectory()){
     		throw new TestFileException(dir.getAbsolutePath() + " is not a directory");
     	}
	
     	log("Generating index for " + dir.toString());

        File[] docXmls = findFiles(new File(jck2xmlDir(dir.getAbsolutePath())), ".doc.xml");

        if (docXmls.length == 1){
		currentIndexFN = docXmls[0].getName().replaceFirst("\\.doc\\.xml", ".html");
	} else if (docXmls.length > 1) {
    		throw new TestFileException("Too many XML files: " + docXmls.length + " in " + jck2xmlDir(dir.getAbsolutePath()));
	} else {
		if (upperIndexFileName != null && !upperIndexFileName.equals("")){
			currentIndexFN = ".." + File.separator + upperIndexFileName;
		} else {
			throw new TestFileException("No testsuite.doc.xml file");
		}
	}

     	File[] subdirs = findSubdirs(dir);
     	for (int i = 0; i < subdirs.length; i++){
       		linkHolder.addAll(generateIndex(subdirs[i], currentIndexFN, pathToCopyright == null ? null : ".." + File.separator + pathToCopyright));
      	}


       	// add all html files of the directory to linkHolder
       	File[] htmlFiles = findFiles(dir, ".html");
	for (int i = 0; i < htmlFiles.length; i++)
       		linkHolder.add(htmlFiles[i]);


        if (docXmls.length == 1){
	       	File xmlFile = docXmls[0];
		String finalHtmlName = xml2jckDir(xmlFile.getAbsolutePath()).replaceFirst("\\.doc\\.xml", ".html");
         	
	        File finalHtmlFile = new File(finalHtmlName);
	        if (! finalHtmlFile.isFile()) {
        	   	Vector args = new Vector();
	        	log("Generating " + finalHtmlName + " for XML file:\n" + xmlFile.getAbsolutePath());
			args.add("-contents");
			args.add(xmlFile.getAbsolutePath());
			args.add("-fileName");
			args.add(finalHtmlName);
			args.add("-log");
			if (upperIndexFileName != null && !upperIndexFileName.equals("")){
			    args.add("-backward");
			    args.add(".." + File.separator + upperIndexFileName);
	    		}
	    		if (pathToCopyright != null){
	    		    args.add("-copyrightLink");
	    		    args.add(pathToCopyright);
	    		}
	             	for (int i = 0; i < linkHolder.size(); i++) {
	              		args.add(((File)linkHolder.get(i)).getAbsolutePath());
	              	}
	
	                JCKIndexGen c = new JCKIndexGen(System.out, System.err);
	            	if (c.run((String[])args.toArray(new String[0])) == ctInt_ErrorCode_Error){
	             		throw new TestFileException("Error. Generation failed.");
	             	}
                        linkHolder.clear();
                        linkHolder.add(finalHtmlFile);
		}
        }
     	return linkHolder;
    }


    /**
     * Return array of subdirectories.
     *
     * @param dir Directory to get subdirs of.
     */
    private File[] findSubdirs(File dir) throws TestFileException {

    	if (! dir.isDirectory())
     		throw new TestFileException("findSubdirs: Directory not found - " + dir.getAbsolutePath());

		FileFilter filter = new FileFilter () {
  			public boolean accept(File pathname) {
     			return pathname.isDirectory();
     		}
       	};
       	return dir.listFiles(filter);
    }

    /**
     * Return array of files with the specified suffix in a directory.
     *
     * @param dir    Directory to get html files of.
     * @param suffix File name suffix
      */
    private File[] findFiles(File dir, final String suffix) throws TestFileException {

    	if (! dir.isDirectory())
     		return new File[0];

		FileFilter filter = new FileFilter () {
  			public boolean accept(File pathname) {
     			return pathname.isFile() &&
        			   pathname.getAbsolutePath().endsWith(suffix);
     		}
       	};
       	return dir.listFiles(filter);
    }

    /**
     * Converts jck directory name into xml directory name
     * basing on command-line parameters.
     *
     * @param jckdir JCK directory name
     *
     * @see #jckDir
     * @see #xmlDir
     */
	private String jck2xmlDir(String jckdir) {
 		return jckdir.replaceFirst(jckDir.getAbsolutePath(), xmlDir.getAbsolutePath());
 	}

    /**
     * Converts xml directory name into jck directory name
     * basing on command-line parameters.
     *
     * @param xmldir JCK directory name
     *
     * @see #xmlDir
     * @see #jckDir
     */
	private String xml2jckDir(String xmldir) {
 		return xmldir.replaceFirst(xmlDir.getAbsolutePath(), jckDir.getAbsolutePath());
 	}

}


/*
        String exIndex = dir.getAbsolutePath() + "/index.html";
        File exIndexFile = new File(exIndex);
        if (exIndexFile.exists()) {
        	linkHolder.clear();
         	linkHolder.add(exIndexFile);
        } else {
        	// add all html files of the directory to linkHolder
        	File[] htmlFiles = findFiles(dir, ".html");
     		for (int i = 0; i < htmlFiles.length; i++)
        		linkHolder.add(htmlFiles[i]);
        }
*/

