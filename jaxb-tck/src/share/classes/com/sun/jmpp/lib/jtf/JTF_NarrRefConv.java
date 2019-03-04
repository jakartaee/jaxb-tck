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
 * This class realizes a Java narrowing reference conversion slot 
 * (see JLS 5.1.5). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_NarrRefConv.java 1.7 02/04/01
 */
public class JTF_NarrRefConv extends JTF_Slot {
		/** the class' suffix */
	private static final String suff = "nrc";
	public final static String NON_ARR = "0-1,3-6";
	protected static final String[][] data = {
/* 0 -------------------------------------------*/
		{"class ClA^ {\n\tint a;\n}\n" + 
			"class ClB^ extends ClA^ {\n\tint b;\n}\n",
		 "",
		 "\t\tClA^ clA^ = new ClA^(); " + 
			"ClB^ clB^ = new ClB^();\n",
		 "clA^",	"clB^",
		 "ClA^",	"ClB^",
		 "from a class type S to a class type T, provided that S is " +
			"a superclass of T"},
/* 1 -------------------------------------------*/
		{"class ClC^ {\n\tint c;\n\tvoid foo() {}\n}\n" +
			"interface InterfC^ {\n\tvoid foo();\n}\n",
		 "",
		 "\t\tClC^ clC^ = new ClC^(); InterfC^ intC^ = null;\n",
		 "clC^",	"intC^",
		 "ClC^",	"InterfC^",
		 "from a class type S to an interface type K, provided that " +
			"S is not final and does not implement K"},
/* 2 -------------------------------------------*/
		{"",
		 "",
		 "\t\tObject objA^ = new Object(); " + 
			"int[][] arrA^ = new int[5][5];\n",
		 "objA^",	"arrA^",
		 "Object",	"int[][]",
		 "from type Object to an array type"},
/* 3 -------------------------------------------*/
		{"interface InterfD^ {\n\tvoid foo();\n}",
		 "",
		 "\t\tObject objD^ = new Object(); InterfD^ intD^ = null;\n",
		 "objD^",	"intD^",
		 "Object",	"InterfD^",
		 "from type Object to an interface type"},
/* 4 -------------------------------------------*/
		{"interface InterfE^ {\n\tvoid foo();\n}\n" +
			 "class ClE^ {\n\tint e;\n\tvoid foo() {}\n}\n",
		 "",
		 "\t\tInterfE^ intE^ = null; ClE^ clE^ = new ClE^();\n",
		 "intE^",	"clE^",
		 "InterfE^",	"ClE^",
		 "from an interface type J to a class type T that is not " + 
			 "final"},
/* 5 -------------------------------------------*/
		{"interface InterfF^ {\n\tvoid foo();\n}\n" +
			 "final class ClF^ implements InterfF^ {\n\tint f;" +
			 "\n\tpublic void foo() {}\n}",
		 "",
		 "\t\tInterfF^ intF^ = null; ClF^ clF^ = new ClF^();\n",
		 "intF^",	"clF^",
		 "InterfF^",	"ClF^",
		 "from an interface type J to a class type T that is final, " +
			"provided that T implements J"},
/* 6 -------------------------------------------*/
		{"interface InterfG^ {\n\tint foo(long x);\n}\n" +
			"interface InterfH^ {\n\tint foo(long y);\n}\n",
		 "",
		 "\t\tInterfG^ intG^ = null; InterfH^ intH^ = null;\n",
		 "intG^",	"intH^",
		 "InterfG^",	"InterfH^",
		 "from an interface type J to an interface type K, provided " +
		    "that J is not a subinterface of K and there is no " +
		    "method name m such that J and K both declare a method " +
		    "named m with the same signature but different return " +
		    "types"},
/* 7 -------------------------------------------*/
		{"class ClI^ {\n\tint i;\n}\n" + 
			"class ClJ^ extends ClI^ {\n\tint j;\n}\n",
		 "",
		 "\t\tClI^[] clI^ = new ClI^[5]; " + 
			"ClJ^[] clJ^ = new ClJ^[5];\n",
		 "clI^",	"clJ^",
		 "ClI^[]",	"ClJ^[]",
		 "from an array type SC[] to an array type TC[], provided " +
			"that SC and TC are reference types and there is a " +
			"narrowing conversion from SC to TC"}
	};
/*----------------------------------------------*/
	
		/** @return identifier of an object; the conversion is performed
		 *  <code>from</code> the type of the object */
	public String objFrom() {
		return res0;
	}
		/** @return identifier of an object; the conversion is performed
		 *  <code>to</code> the type of the object */
	public String objTo() {
		return res1;
	}
		/** @return name of a type from which the conversion is
		 *  performed */
	public String typeFrom() {
		return res2;
	}
		/** @return name of a type to which the conversion is
		 *  performed */
	public String typeTo() {
		return res3;
	}
		/** @return string that describes the conversion */
	public String desc() {
		return res4;
	}
/*----------------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		for (int i=0; i<res.length; i++)
			res[i] = subst(data[val_num][i], "^", "_" + suff + 
								    this_num);
		decl0 = res[0];
		decl1 = res[1];
		decl2 = res[2];
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
		res2 = res[contexts_total + 2];
		res3 = res[contexts_total + 3];
		res4 = res[contexts_total + 4];
	}
	
	public JTF_NarrRefConv() {
		super(data.length);
		res = new String[data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_NarrRefConv(String combs) {
		super(combs);
		res = new String[data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
// ^ substituted by full suffix
