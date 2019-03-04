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

import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class KeywordExpression {
	
   private HashSet expr;
   public KeywordExpression(String line) {
	   expr = new HashSet();
	   StringTokenizer st = new StringTokenizer(line, "|");
	   while (st.hasMoreTokens()) {
		   expr.add(st.nextToken());
	   }	  
	   
 
  }

   public boolean accept(KeywordSet set) {
	   for( Iterator keywords = expr.iterator(); keywords.hasNext();) {
		   if(set.contains(keywords.next())) {
			   return true;
		   }
	   }	   
	   return false;
    }

   public boolean accept(ExcludeEntry entry) {
	   return accept(entry.getKeywords());
   }


	/**
	 * return new ExcludeList that contains only accpeted entries
	 */

	public ExcludeList apply (ExcludeList el) {
	   ExcludeList list = new ExcludeList();
	   for( Iterator entries = el.getAllEntries(); entries.hasNext();) {
		   ExcludeEntry entry = (ExcludeEntry)entries.next();
		   if(accept(entry)) {
			   list.addEntry(entry);
		   }
	   }
	   return list;
	}

    public String toString() {
		String result = "";
        if(expr.size() > 0) {
			Iterator keywords = expr.iterator(); 
			result += keywords.next();
			
	   	    while ( keywords.hasNext()) {
				result += "|" + keywords.next();
			}
		} 		
		
		return result;

	}
}
