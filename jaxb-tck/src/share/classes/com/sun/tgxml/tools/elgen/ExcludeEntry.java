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

import java.util.StringTokenizer;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

public class ExcludeEntry 
{
   private String directory;
   private String testCaseID;
   private String testGroupID;
   private String comments;
   protected KeywordSet keywords;
   protected KeywordSet bugIDs;
   
   public ExcludeEntry(String line) throws ParseException {
	   parse(line);
   }
   
   protected void parse (String line) throws ParseException  {
		  
			
		StringTokenizer st = new StringTokenizer(line);
		// parse url part of entry
		if(st.hasMoreTokens()) {
			parseURL(st.nextToken());
		} else {
			throw new ParseException("EmptyString");
		}
		
		// parse bugIDs
		if(st.hasMoreTokens()) {
			bugIDs = new KeywordSet(st.nextToken());
		} else {
			throw new ParseException("No bugIDs");
		}
		
		// parse keywords
		if(st.hasMoreTokens()) {
			keywords = new KeywordSet(st.nextToken());
		} else {
			keywords = new KeywordSet();
			//throw new ParseException("No keywords");
		}

		// find comments
		if(st.hasMoreTokens()) {
			comments = st.nextToken("\n");
		} 

		  
	}
   protected void parseURL (String line) throws ParseException  {
		  
		// find TestCaseID
		int testcaseLeft = line.indexOf("[");
		int testcaseRight = line.indexOf("]");
		if(testcaseLeft !=-1) {
			if(testcaseRight < testcaseLeft ) {
				throw new ParseException("Incorrect testcase description");
			}
			
			testCaseID = line.substring(testcaseLeft+1, testcaseRight);
			line = line.substring(0, testcaseLeft);
		}
			

		int testgroupLeft = line.lastIndexOf("/");
		if(testgroupLeft ==-1) {
			throw new ParseException("No TestGroup defined");
		}
		testGroupID = line.substring(testgroupLeft+1, line.length());
		directory = line.substring(0, testgroupLeft);
	}


	public void mergeWith(ExcludeEntry e) {
		getBugIDs().addAll(e.getBugIDs());
		getKeywords().addAll(e.getKeywords());
		comments += " " + e.getComments();
	}
	
   /**
    *
    */
   public String getDirectory() {
	   return directory;
   }
   
   /**
   */
   public String getTestGroupID() {
	   return testGroupID;
   }
   
   /**
   */
   public String getTestCaseID(){
	   return testCaseID;
   }
   
   /**
   */
   public KeywordSet getBugIDs(){
	   return bugIDs;
   }
   
   /**
   */
   public KeywordSet getKeywords(){
	   return keywords;
   }
   
   /**
   */
   public String getComments(){
	   return comments;
   }


	public String toString() {
		String str = directory + "/" + testGroupID;
		if(testCaseID != null) {
			str += "[" + testCaseID + "]";
		}
		
		str += " " + bugIDs + " " + keywords;
		if(comments != null) {
			str +=" " + comments;
		}
		return str;		
	}
    /** 
     * create key to use in exclude list hashtable search
	 */
    String createKey() {
		String key = directory + "/" + testGroupID;
		if(testCaseID != null) {
			key += "[" + testCaseID + "]";
		}
		return key;
	}

	static String createKey(TestItem ti) throws IncorrectAttributesException{
		String testCaseID = null;
		TestItem testGroup = null;
		String testGroupID = null;
		String directory = null;

		if(ti instanceof TestCase) {
			try {
				testCaseID = ti.getID();
			} catch (TestFileException e) {
				return null;
			}
				
			testGroup = ((TestCase)ti).getTestGroup();
			try {
				directory = ExcludeListUtils.getSourceDir(ti);
			} catch (IncorrectAttributesException e) {
				directory = ExcludeListUtils.getSourceDir(testGroup);
			}
		} else {			
			testGroup = ti;
			directory = ExcludeListUtils.getSourceDir(ti);
		}
		

		try {
			testGroupID = testGroup.getID();
		} catch (TestFileException e) {
			return null;
		}

        // create key the same way  that createKey() does
		String key = directory + "/" + testGroupID;
		if(testCaseID != null) {
			key += "[" + testCaseID + "]";
		}
		return key;
   	}
 

}
