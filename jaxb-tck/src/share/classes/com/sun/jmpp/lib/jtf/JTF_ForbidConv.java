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
 * This class realizes a Java forbidden conversion slot (see JLS 5.1.7).
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_ForbidConv.java 1.7 02/04/01
 */
public class JTF_ForbidConv extends JTF_Slot { 
	private static final String suff = "fc";
	private int[][] restr = {
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, JTF_Types.iboo},
		{JTF_Types.iboo, JTF_Types.iStr},
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, -1},
		{-1, -1}
	};
		/** there are forbidden conversions involving primitive
		 *  Java types; this field controls them */
	protected JTF_Types jt = null;
	protected static final String[][] data = {
/* 0 */		{"class Cl^1 {\n\t@ v = $;\n}\n",
		 "",
		 "\n\t\t@ #^1 = $; Cl^1 cl^1 = new Cl^1();",
		 "cl^1",
		 "#^1",
		 "Cl^1",
		 "@",
		 "from a reference type to the type <code>@</code>"},
/* 1 */		{"class Cl^2 {\n\t@ v = $;\n}\n",
		 "",
		 "\n\t\t@ #^2 = $; Cl^2 cl^2 = new Cl^2();",
		 "#^2",
		 "cl^2",
		 "@",
		 "Cl^2",
		 "from type <code>@</code> to a reference type"},
/* 2 */		{"",
		 "",
		 "\n\t\t@ #^3 = $;",
		 "null",
		 "#^3",
		 "null",
		 "@",
		 "from the null type to the type <code>@</code>"},
/* 3 */		{"",
		 "",
		 "\n\t\t@ #^4 = $; boolean boo1^ = true;",
		 "#^4",
		 "boo1^",
		 "@",
		 "boolean",
		 "from the type <code>@</code> to the type boolean"},
/* 4 */		{"",
		 "",
		 "\n\t\t@ #^5 = $; boolean boo2^ = true;",
		 "boo2^",
		 "#^5",
		 "boolean",
		 "@",
		 "from the type boolean to the type <code>@</code>"},
/* 5 */		{"class ClA^ {\n\t@ a = $;\n}\nclass ClB^ {\n\tint b = 5;\n}\n",
		 "",
		 "\n\t\tClA^ clA^ = new ClA^(); ClB^ clB^ = new ClB^();",
		 "clA^",
		 "clB^",
		 "ClA^",
		 "ClB^",
		 "from class type S to a different class type T (S is not a " + 
			"subclass of T and T is not a subclass of S)"},
/* 6 */		{"interface IC^ {\n\tvoid foo();\n}\n" + 
			"final class ClC^ {\n\t@ c = $;\n}\n",
		 "",
		 "\n\t\tIC^ intC^ = null; ClC^ clC^ = new ClC^();",
		 "clC^",
		 "intC^",
		 "ClC^",
		 "IC^",
		 "from class type S to interface type K (S is final " + 
			"and does not implement K)"},
/* 7 */		{"class ClD^ {\n\t@ d = $;\n}\n",
		 "",
		 "\n\t\tClD^ clD^ = new ClD^(); @[][] arr^6 = new @[5][5];",
		 "clD^",
		 "arr^6",
		 "ClD^",
		 "@[][]",
		 "from class type S to an array type (S is not Object)"},
/* 8 */		{"interface IE^ {\n\tvoid foo();\n}\n" + 
			"final class ClE^ {\n\t@ e = $;\n}\n",
		 "",
		 "\n\t\tClE^ clE^ = new ClE^(); IE^ intE^ = null;",
		 "intE^",
		 "clE^",
		 "IE^",
		 "ClE^",
		 "from interface type J to class type T " + 
			"(T is final and does not implement J)"},
/* 9 */		{"interface IF1^ {\n\tint foo();\n}\n" + 
			"interface IF2^ {\n\tlong foo();\n}\n",
		 "",
		 "\n\t\tIF1^ intF1^ = null; IF2^ intF2^ = null;",
		 "intF1^",
		 "intF2^",
		 "IF1^",
		 "IF2^",
		 "from interface type J to interface type K (J and K declare " +
			"methods with the same signature but different " +
			"return types)"},
/* 10 */	{"class ClG^ {\n\t@ g = $;\n}\n",
		 "",
		 "\n\t\tClG^ clG^ = new ClG^(); @[] arr^7 = new @[5];",
		 "arr^7",
		 "clG^",
		 "@[]",
		 "ClG^",
		 "from an array type to a class type (other than Object or " +
			"String)"},
/* 11 */	{"interface IH^ extends Cloneable {\n\tvoid foo();\n}\n",
		 "",
		 "\n\t\tIH^ intH^ = null; @[][][] arr^8 = new @[5][5][5];",
		 "arr^8",
		 "intH^",
		 "@[][][]",
		 "IH^",
		 "from an array type to an interface type (not Cloneable)"},
/* 12 */	{"class ClI^ {\n\t@ i = $;\n}\nclass ClJ^ {\n\t@ j = $;\n}\n",
		 "",
		 "\n\t\tClI^[] arrI^ = new ClI^[5]; ClJ^[] arrJ^= new ClJ^[5];",
		 "arrI^",
		 "arrJ^",
		 "ClI^[]",
		 "ClJ^[]",
		 "from array type SC[] to array type TC[] (there is no " + 
			"permitted conversion other than a string conversion " +
			"from SC to TC"}
	};
	private int[] last_i = new int[data.length];
/*----------------------------------------------*/
	
		/** @return identifier of an object;
		 *  conversion is performed <code>from</code>
		 *  the type of the object */
	public String objFrom() {
		return res0;
	}
		
		/** @return identifier of an object;
		 *  conversion is performed <code>to</code>
		 *  the type of the object */
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
		/** @return description of the conversion */
	public String desc() {
		return res4;
	}
/*----------------------------------------------*/
	private String doSubst(int ind) {
 		String s = data[val_num][ind];
		s = subst(s, "^", "_" + suff + this_num);
		String vname = jt.typeName().substring(0, 2);
		s = subst(s, "#", vname);
		s = subst(s, "@", jt.typeName());
		String[] val = jt.genTypeVal(2);
		s = subst(s, "$", val[0]);
		return s;
	}
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		int type_id = jt.curVal();
		if (!(restr[val_num][0] == type_id || 
		      restr[val_num][1] == type_id))
			last_i[val_num] = type_id;
		jt.setTo(last_i[val_num]);
		for (int i=0; i<res.length; i++)
			res[i] = doSubst(i);
		decl0 = res[0];
		decl1 = res[1];
		decl2 = res[2];
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
		res2 = res[contexts_total + 2];
		res3 = res[contexts_total + 3];
		res4 = res[contexts_total + 4];
	}
	
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		jt = ((JTF_ForbidConv)from).jt;
	}
/*------------- Constructors ---------------*/
	public JTF_ForbidConv() {
		super(data.length);
		res = new String[data[0].length];
		jt = new JTF_Types();
		jt.setTo(jt.iint);
		for (int i=0; i<last_i.length; i++)
			last_i[i] = 0;
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
		/** @param jt	external <i>JTF_Types</i> object which
		 *  controls primitive types involved in conversions */
	public JTF_ForbidConv(JTF_Types jt) {
		super(data.length);
		res = new String[data[0].length];
		this.jt = jt;
		for (int i=0; i<last_i.length; i++)
			last_i[i] = 0;
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
// ^ substituted by full suffix
// @ substituted by type name
// # substituted by variable name
// $ substituted by type value
