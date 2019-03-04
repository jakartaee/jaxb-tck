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
// Program: HTMLReportGenerator.java
// Author : Rampalli Narasimhan 
// Purpose: To Generate some HTML Reports. Lots of stuff
//          in this program is to generate the HTML doc related 
//          stuff. 
//


package com.sun.jck.utils.jckfilecheck;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import java.util.Enumeration;


public class HTMLReportGenerator {

    private File outFileName;
    private OrderedList ISODataList;
    private DataOutputStream dos;

    public HTMLReportGenerator(File outFileName) throws IOException {
	this.outFileName = outFileName;
	FileOutputStream fis = new FileOutputStream(outFileName);
	BufferedOutputStream bis = new BufferedOutputStream(fis, 4096);
	dos = new DataOutputStream(bis);
    }

    public void generateMainPage(Checker[] checkers) throws IOException {
	dos.writeBytes("\n <!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("\n <html>\n");
	dos.writeBytes("\n <BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("<font size =+4>");
	dos.writeBytes("\n <head>\n");
	dos.writeBytes("\n <title> JCK Checker Program Results </title>");
	dos.writeBytes("\n      </head>");
	dos.writeBytes("<CENTER>");
	dos.writeBytes("<H1> JCK Checker Program Results </H1>");
	dos.writeBytes("</CENTER>");
	dos.writeBytes("<p> The JCK checker program has generated the following results. Please");
	dos.writeBytes(" follow the following links to get detailed information </p>");
	dos.writeBytes("<UL>");
	for (int i = 0; i < checkers.length; ++i) {
	    Checker c = checkers[i];
	    dos.writeBytes("<li><a href=\"" + c.getOutputFile().getName() + "\">" + c.getName() + "</a>");
	}
	dos.writeBytes("</UL>");
	dos.writeBytes("</body>");
	dos.writeBytes("</html>");
	dos.close();
    }


    public void generateISOCheckerReport(OrderedList ISODataList, long vData[]) throws IOException {
	this.ISODataList = ISODataList;
	Enumeration fileListIter;
	long ISOData[] = vData;
    
	//
	// Generate the table
	//

	String tags[] = { "Conforms to all ISO-9660 Specs",
			  "Invalid Directory Name",
			  "Directory depth is > 8",
			  "Invalid File Name",
			  "8.3 Filename Clash",
			  "FileName is greater than 8.3",
			  "Directory Name is > 32",
			  "Directory Name Clash at 8 chars" };

	String detFiles[] = { "isook.html", "invdir.html", "invdirdp.html", "invfn.html", "fnclash.html", "fnlong.html",
			      "dirnlong.html", "dirclash.html" };

	dos.writeBytes("<!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("<BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("<html>\n");
	dos.writeBytes("<head>\n");
	dos.writeBytes("<title> JCK Checker Program Results </title>");
	dos.writeBytes("</head>");
	dos.writeBytes("<h1>ISO 9660 Specification Results<br></h1>\n");
	dos.writeBytes("<table border=1 cellspacing=0 cellpadding=5>\n");
	dos.writeBytes("<tr <ALIGN=\"CENTER\" BGCOLOR=\"#FFCDF4\">");
	dos.writeBytes("<th ALIGN=\"CENTER\">ISO 9660 - Conformance Status</th>");
	dos.writeBytes("<th ALIGN=\"CENTER\">Number of Files/Directories</th>");
	dos.writeBytes("</tr><p>");
    
	for (int i = 0; i < tags.length; ++i) {
	    dos.writeBytes("<tr>");
	    dos.writeBytes("<td>" + tags[i] + "</td>");
	    dos.writeBytes("<td ALIGN=\"CENTER\"><A HREF=\"" + detFiles[i] + "\">" + ISOData[i] + "</a></td>");
	}
	dos.writeBytes("</table> <p> </body>");
	dos.writeBytes("</html>");
	dos.close();

	//
	// Generate the detailed information about each of these categories
	//
	// the following names must correspond to the ISODataBase entries
	DataOutputStream[] files = new DataOutputStream[detFiles.length];
	for (int i = 0; i < files.length; i++) {
	    DataOutputStream f = open(detFiles[i]);
	    f.writeBytes("<!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	    f.writeBytes("<html>\n");
	    f.writeBytes("<title>" + tags[i] + "</title>\n");
	    f.writeBytes("<h1>" + tags[i] + "</h1>\n");
	    f.writeBytes("<body>\n");
	    f.writeBytes("<ul>\n");
	    files[i] = f;
	}
	
	
	fileListIter = ISODataList.getIterator();
	while (fileListIter.hasMoreElements()) {
	    ISODataBase temp = (ISODataBase) fileListIter.nextElement();
	    files[temp.getStatus()].writeBytes("<li>" + temp.getEntry() + "\n");
	}

	for (int i = 0; i < files.length; i++) {
	    DataOutputStream f = files[i];
	    f.writeBytes("</ul>\n");
	    f.writeBytes("</body>\n");
	    f.writeBytes("</html>\n");
	    f.close();
	}

    }

    private DataOutputStream open(String name) throws IOException {
	String dir = outFileName.getParent();
	File f = (dir == null ? new File(name) : new File(dir, name));
	return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
    }

 
    public void generateLongCheckerReport (Vector fileT, long noOfF[],
					   long sizeOfF[], long noOfD,
					   long totalE, String[] legalE) throws IOException {

	long temp = 0;
	Vector fileType = new Vector();
	fileType = fileT;
	long noOfFiles[] = noOfF;
	long sizeOfFiles[] = sizeOfF;
	long noOfDirs = noOfD;
	long totalEntries = totalE;
	String[] legalExtns = legalE;
	boolean warnReqd = false;
	long totalNoOfFiles = 0;
	long totalSizeOfFiles = 0;
	//
	// Calculate the total number of files and total size of files
	// 
	for (int i = 0; i < noOfFiles.length; ++i) {
	    totalNoOfFiles += noOfFiles[i];
	}
	for (int j = 0; j < sizeOfFiles.length; ++j) {
	    totalSizeOfFiles += sizeOfFiles[j];
	}

	//
	// Generate the code
	// 
	dos.writeBytes("\n <!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("\n <BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("\n <html>\n");
	dos.writeBytes("\n <head>\n");
	dos.writeBytes("\n <title> JCK Checker Program Results </title>");
	dos.writeBytes("\n      </head>");
	dos.writeBytes("<table border=1 cellspacing=0 cellpadding=5 width=90%>\n");
	dos.writeBytes("<caption><font size=+4>Detailed File/Directory Analysis Report</font><br></caption>\n");
	dos.writeBytes("<tr <ALIGN=\"CENTER\" BGCOLOR=\"#FFCDF4\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Type of File</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Number </font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Percentage(%) </font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Total Size (in Bytes)</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Percentage(%) </font></td>");
	dos.writeBytes("</tr><p>");

	for (int i = 0; i < fileType.size(); i++) { 
	    boolean ok = false;
	    for (int e = 0; e < legalExtns.length; e++) {
		if (legalExtns[e].equals(fileType.elementAt(i))) {
		    ok = true;
		    break;
		}
	    }

	    if (!ok) 
		warnReqd = true;

	    dos.writeBytes("<tr <ALIGN=\"LEFT\"" + (ok ? "" : "BGCOLOR=\"#IFFFEF\"") + ">");
	    dos.writeBytes("<td>" + fileType.elementAt(i) + "</td>");
	    dos.writeBytes("<td ALIGN=\"CENTER\">" + noOfFiles[i] + "</td>");
	    temp =  ((noOfFiles[i] * 100)/ totalNoOfFiles);
	    dos.writeBytes("<td ALIGN=\"CENTER\">" + temp + "</td>");
	    dos.writeBytes("<td ALIGN=\"CENTER\">" + sizeOfFiles[i] + " </td>");
	    temp = ((sizeOfFiles[i] * 100)/ totalSizeOfFiles);
	    dos.writeBytes("<td ALIGN=\"CENTER\">" + temp + " </td>");
	    dos.writeBytes("</tr> <p>");
	} 
	dos.writeBytes("</table>");
	dos.writeBytes("<p>");
	if (warnReqd) {
	    dos.writeBytes("If there is a light blue band, then\n");
	    dos.writeBytes("Watch Out!!! This file is <b>NOT</b> supposed to be here.<br></font>");
	}
	dos.writeBytes("</table> <p> </body>");
	dos.writeBytes("</html>");
	dos.close();
    }

    public void generateClusterCheckerReport (int[] clusterSizeArray, int[] noOfClustersArray, int[] totalSpaceArray) 
	throws IOException {
	dos.writeBytes("\n <!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("\n <BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("\n <html>\n");
	dos.writeBytes("\n <head>\n");
	dos.writeBytes("\n <title> JCK Checker Program Results </title>");
	dos.writeBytes("\n      </head>");
	dos.writeBytes("<table border=1 cellspacing=0 cellpadding=5 width=90%>\n");
	dos.writeBytes("<caption><font size=+4>Cluster Checker Results</font><br></caption>\n");
	dos.writeBytes("<tr <ALIGN=\"CENTER\" BGCOLOR=\"#FFCDF4\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Cluster Size</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> No. of Clusters</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Total Size (in MB)</font></td>");
	dos.writeBytes("</tr><p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	for (int i = 0; i < clusterSizeArray.length; ++i) {
	    dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1> " + clusterSizeArray[i] + "</font></td>");
	    dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + noOfClustersArray[i] + "</font></td>");
	    dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + totalSpaceArray[i] + "</font></td>");
	    dos.writeBytes("</font></tr> <p>");
	}
	dos.writeBytes("</table> <p> </body>");
	dos.writeBytes("</html>");
	dos.close();
    }
    public void generateClusterCheckerReport (long clusterSize, long noOfClusters, long totalSpace) 
	throws IOException {
	dos.writeBytes("\n <!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("\n <BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("\n <html>\n");
	dos.writeBytes("\n <head>\n");
	dos.writeBytes("\n <title> JCK Checker Program Results </title>");
	dos.writeBytes("\n      </head>");
	dos.writeBytes("<table border=1 cellspacing=0 cellpadding=5 width=90%>\n");
	dos.writeBytes("<caption><font size=+4>Cluster Checker Results</font><br></caption>\n");
	dos.writeBytes("<tr <ALIGN=\"CENTER\" BGCOLOR=\"#FFCDF4\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Cluster Size</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> No. of Clusters</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Total Size (in MB)</font></td>");
	dos.writeBytes("</tr><p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1> " + clusterSize + "</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + noOfClusters + "</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + totalSpace + "</font></td>");
	dos.writeBytes("</font></tr> <p>");
	dos.writeBytes("</table> <p> </body>");
	dos.writeBytes("</html>");
	dos.close();
    }

    public void generateShortCheckerReport (long noOfFiles, long noOfDirs, long sizeOfFiles) 
	throws IOException {
	dos.writeBytes("\n <!DOCTYPE HTML_PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n");
	dos.writeBytes("\n <BODY BGCOLOR=\"#FFFFFF\"\n");
	dos.writeBytes("\n <html>\n");
	dos.writeBytes("\n <head>\n");
	dos.writeBytes("\n <title> JCK Checker Program Results </title>");
	dos.writeBytes("\n      </head>");
	dos.writeBytes("<table border=1 cellspacing=0 cellpadding=5 width=90%>\n");
	dos.writeBytes("<caption><font size=+4>Accumulator Checker Results</font><br></caption>\n");
	dos.writeBytes("<tr <ALIGN=\"CENTER\" BGCOLOR=\"#FFCDF4\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Type</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+2> Number</font></td>");
	dos.writeBytes("</tr><p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1> Files</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + noOfFiles + "</font></td>");
	dos.writeBytes("</font></tr> <p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1>Directories</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + noOfDirs + "</font></td>");
	dos.writeBytes("</font></tr> <p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1> Total</font></td>");
	long temp = noOfFiles + noOfDirs;
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + temp + "</font></td>");
	dos.writeBytes("</font></tr> <p>");
	dos.writeBytes("<tr <ALIGN=\"CENTER\">");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font face=\"arial,helvetica\" size=+1> Total size of Files");
	dos.writeBytes("</font></td>");
	dos.writeBytes("<td ALIGN=\"CENTER\"><font size=+1>" + sizeOfFiles + "</font></td>");
	dos.writeBytes("</font></tr> <p>");
	dos.writeBytes("</table> <p> </body>");
	dos.writeBytes("</html>");
	dos.close();
    }
}
