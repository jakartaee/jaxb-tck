/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.elgen;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Enumeration;
import com.sun.tgxml.tools.elgen.javatest.ExcludeTable;

/**
  * To support new test monitor exclude list format, a developer
  * should implements this interface, and pass the implementation to the ExcludeListGenerator algorithm
*/
public class JavaTestELConverter implements ExcludeListConverter {
   
	public String avalablePlatforms="solaris-sparc,win-NT,win-95,generic";

	protected KeywordSet aPlatform = null;

	public JavaTestELConverter () {
		aPlatform = new KeywordSet (avalablePlatforms);
	}

   /**
    * Implements this method to convert exclude list class to the
    * stream. The stream will be saved in the result file or redirected.
   */
	public void convert(ExcludeList el, File output) throws IOException{

		ExcludeTable table = new ExcludeTable();
		for (Iterator entries = el.getAllEntries(); entries.hasNext();) {
			ExcludeTable.Entry entry = convertEntry ((ExcludeEntry)entries.next());
			try {
				table.addEntry(entry);
			} catch (ExcludeTable.Fault e) {
				//TODO
			}
		}

		table.write(output);
	}

	protected ExcludeTable.Entry convertEntry (ExcludeEntry src) {
		String u = src.getDirectory() + "/" + src.getTestGroupID(); 
		String tc = src.getTestCaseID();
		String s = src.getComments();
		if (s == null) {
			s = "";
		}

		// create bugIds
		KeywordSet bugIDs = src.getBugIDs();
 		int[] b = new int [bugIDs.size()];
					
		int i = 0;
		for (Iterator bugIter = bugIDs.iterator(); bugIter.hasNext();i++) {
			b[i] = Integer.parseInt ((String)(bugIter.next()));
		}

		 // filter and create platforms
		 KeywordSet keywords =  src.getKeywords();
		 keywords.retainAll(aPlatform);
		 if(keywords.size() == 0) {
			 keywords.add("generic");
		 }
		 String[] p = new String[keywords.size()];
		 i = 0;
		 for (Iterator keyIter = keywords.iterator(); keyIter.hasNext();i++) {
			 p[i] = (String)(keyIter.next());
		 }

		 return new ExcludeTable.Entry (u, tc, b, p, s);
	} 
}
