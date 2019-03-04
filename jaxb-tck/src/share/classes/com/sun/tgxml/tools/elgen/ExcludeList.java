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

import java.util.Iterator;
import java.util.Hashtable;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;

/**
* Represent an exclude list
*/
public class ExcludeList 
{
   protected Hashtable entries = new Hashtable();
   
   /** 
     * create an empty exclude list
     */
	public ExcludeList(){
    }

   /** 
     * read exclude list from the file 
     */
	public ExcludeList(Reader source) throws IOException {
		BufferedReader reader = new BufferedReader(source);
		for (String str = reader.readLine(); str !=null; 
			     str = reader.readLine()) {
			if(!isComments(str)) {
				addEntry(new ExcludeEntry(str));
			}
		}
	}

   /** 
     * read exclude list from the file 
     */
	public ExcludeList(String name)  throws IOException {
		this(new FileReader(name));
	}

	protected boolean isComments(String line) {
	   String str = line.trim();
	   return (str.length() == 0 || str.startsWith("#"));
	}

	public void addEntry(ExcludeEntry entry) {
		String key = entry.createKey();
		ExcludeEntry old = (ExcludeEntry)entries.get(key);
		if (old == null) {
			entries.put(key, entry);
		} else {
			old.mergeWith(entry);
		}
	}

	public void addEntries(ExcludeList list) {}

	public void removeEntry(ExcludeEntry entry) {}

	public void removeEntries(ExcludeList list) {}

	/**
	 * retrun internal text representation of the exclude list
	 */
	public String toString() {
		String result ="";
		for (Iterator entries = getAllEntries(); entries.hasNext(); ) {
			result += ((ExcludeEntry) entries.next()).toString() + "\n";
		}
		return result;
	}
   
	/**
	 * Returns any iterator for all exclude entries.
	 */
	public Iterator getAllEntries() {
		return entries.values().iterator();
	}
   
	/**
	 * Access method for the entries property.
	 *
	 * @return   the current value of the entries
	 */
	//public ExcludeEntry[] getEntries(){} 

	/**
     * Sets the value of the entries.
     *
     * @param aEntries the new value of the entries
	 */
	public void setEntries(ExcludeEntry[] aEntries) {}


    /** 
	 * Find entry correspondent to the given TestItem.
	 * Retruns null if entry is not found
	 */
	
	public ExcludeEntry find(TestItem item) throws IncorrectAttributesException{
		String key = ExcludeEntry.createKey(item);
		return (ExcludeEntry) (entries.get(key));
	}
}
