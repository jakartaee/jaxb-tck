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

package com.sun.jmpp.lib.jtf;
/**
 * <i>Jmpp Test Factory</i>
 * 
 * This class realizes a context slot. It can place string values of
 * the input JTF_Enum slot into the following contexts : <ul>
 * <li>package member 
 * <li>class member 
 * <li>local (block) member 
 * <li>inner class member </ul>.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_Context.java 1.6 02/04/01
 */
public class JTF_Context extends JTF_Slot {
		/** contains what's put into a particular context */
	protected JTF_Enum je = null;
		/** package member context */
	public static final String PCKG		= "0";
		/** class member context */
	public static final String CLSS		= "1";
		/** local (block) member context */
	public static final String LOC		= "2";
		/** inner class member context */
	public static final String ICLS		= "3";
		/** common subset of values */
	public static final String COMMON	  = "1-2";

		/** contains templates for the slot's values */
	protected static final String [][] data = {
/* 0 */		{"###",
		 "package member"},
/* 1 */		{"class A^ {\n\t###\n}",		
		 "class member"},
/* 2 */		{"class A^ {\n\tvoid foo() {\n\t\t###\n\t}\n}",		
		 "local (block) member"},
/* 3 */		{"class A^ {\n\tclass Innr {\n\t\t###\n\t}\n}",		
		 "inner class member"}};
  
/*---------- Value number checks ----------*/
		/** @return true - if current context is 'package member',
		 *  false - if not */
	public boolean isPckg() {
		return sp.parse(PCKG).contains(curVal());
	}
		/** @return true - if current context is 'class member',
		 *  false - if not */
	public boolean isClss() {
		return sp.parse(CLSS).contains(curVal());
	}
		/** @return true - if current context is 'local',
		 *  false - if not */
	public boolean isLoc() {
		return sp.parse(LOC).contains(curVal());
	}
		/** @return true - if current context is 'inner class member',
		 *  false - if not */
	public boolean isIcls() {
		return sp.parse(ICLS).contains(curVal());
	}

/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
 	public void setTo(int value_num) {
		super.setTo(value_num);
		String suff = "cont" + this_num + "" + val_num;
		res[contexts_total] = subst(data[val_num][0], "^", suff);
		res[contexts_total+1] = data[val_num][1];
 	}
 
		/** @return current value of the slot */
	public String value() {
		String macro_value;
		macro_value = je == null ? "" : je.value();
		return subst(res[contexts_total], "###", macro_value);
	}
	public String value(String macro_value) {
		return subst(res[contexts_total], "###", macro_value);
	}
		/** @return description of the current context */
	public String desc() {
		return res[contexts_total+1];
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		je = ((JTF_Context)from).je;
	}
/*------------- Constructors ---------------*/
	public JTF_Context() {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
		/** @param je - external <i>JTF_Enum<i> object
		 *  whose values are used in different contexts */
 	public JTF_Context(JTF_Enum je) {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		this.je = je;
		for (int i=0; i<res.length; i++)
			res[i] = "";
 	}	
		/** @param combs - string that specifies allowed values */
	public JTF_Context(String combs) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
 	}	
		/** @param combs - string that specifies allowed values */
	public JTF_Context(String combs, JTF_Enum je) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		this.je = je;
		for (int i=0; i<res.length; i++)
			res[i] = "";
 	}	
}
