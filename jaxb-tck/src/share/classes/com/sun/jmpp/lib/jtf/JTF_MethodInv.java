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
 * This class realizes a Java method invokation slot.
 * The invoked method can be declared with different types, access and field
 * modifiers.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_MethodInv.java 1.8 02/04/01
 */
public class JTF_MethodInv extends JTF_Slot {
		/** controls declared type of the method */
	protected JTF_Types jt;
		/** controls access modifiers of the method */
	protected JTF_AccessModif jam;
		/** controls method modifiers of the method */
	protected JTF_MethodModif[] jmm;
		/** the class' suffix */
	private static final String suff = "ma";
		/** method invokation expression contains class instance
		 *  creation expression */
	public static final String INST_CRE     = "4-10";
		/** method invokation expression does not contain class 
		 *  instance creation expression */
	public static final String NON_INST_CRE = "0-3";
		/** method is invoked thru an instance of a subclass */
	public static final String SUB          = "8-10";
		/** method is invoked thru an instance of a this class */
	public static final String NON_SUB      = "0-7";
		/** method declared in superinterface is invoked */
	public static final String INTERF       = "8";
		/** method declared not in superinterface is invoked */
	public static final String NON_INTERF   = "0-7,9-10";
		/** common subset of values */
	public static final String COMMON	= "0,3-4,7,9";
		/** Common part of declarations required for all invokations */
	protected String decl_pattern = 
			"\nclass A^#3 {\n\t#1 #2 @ foo() { return $; }\n" +
			"\tpublic A^#3 next = null;\n" + 
			"\tpublic A^#3 getNext() { return next; }\n}";
	protected static final String [][] data = {
/* 0 */		 {"\n\t\tA^1 a^1 = new A^1();",
		 "a^1.foo()",
		 "ClassInstance.method()"},
/* 1 */		 {"\n\t\tA^2 a^2 = new A^2();",
		 "a^2.getNext().foo()",
		 "ClassInstance.Method().method()"},
/* 2 */		 {"\n\t\tA^3 a^3 = new A^3();",
		 "a^3.next.foo()",
		 "ClassInstance.RefTypeField.method()"},
/* 3 */		 {"\n\t\tA^4 a^4 = new A^4();",
		 "a^4.next.getNext().foo()",
		 "ClassInstance.RefTypeField.Method().method()"},
/* 4 */		 {"",
		 "(new A^5()).foo()",
		 "(ClassInstCreExpr).method()"},
/* 5 */		 {"",
		 "(new A^6()).getNext().foo()",
		 "(ClassInstCreExpr).Method().method()"},
/* 6 */		 {"",
		 "(new A^7()).next.foo()",
		 "(ClassInstCreExpr).RefTypeField.method()"},
/* 7 */		 {"",
		 "(new A^8()).next.getNext().foo()",
		 "(ClassInstCreExpr).RefTypeField.Method().method()"},
/* 8 */	 	 {"\ninterface I#4{\n" +
			"\t@ foo();\n" + 
			"\tI#4 getNext();\n}\n" +
			"class ACI#4 implements I#4 {\n" +
			"\tpublic @ foo() { return $; }\n" +
			"\tpublic I#4 next = null;\n" +
			"\tpublic I#4 getNext() { return next; }\n}\n",
		 "",
		 "(new ACI#4()).next.getNext().foo()",
		 "(SubClassInstCreExpr).RefTypeField.Method()." +
			"SuperInterfMethod()"},
/* 9 */		 {"\nclass A^9 {\n" +
			"\t#1 #2 @ foo() { return $; }\n}\n" +
			"class B^9 extends A^9 { int a = 0; }\n",
		 "\n\t\tA^9 a^9 = new A^9();",
		 "a^9.foo()",
		 "SubClassInst.SuperClassMethod()"},
/* 10 */	 {"\nclass A^A {\n" +
			"\t#1 #2 @ foo() { return $; }\n}\n" +
			"class B^A extends A^A { int a = 0; }\n",
		 "",
		 "(new A^A()).foo()",
		 "(SubClassInstCreExpr).SuperClassMethod()"}};
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isInstCre() {
		return sp.parse(INST_CRE).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isSub() {
		return sp.parse(SUB).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isInterf() {
		return sp.parse(INTERF).contains(curVal());
	}
/*-----------------------------------------*/
 		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		String dclr = decl_pattern;
		String full_suf = "_" + suff + this_num + "" + jt.curVal();
		full_suf += jam.curVal();
		for (int i=0; i<jmm.length; i++)
			full_suf += jmm[i].curVal();
		if (data[val_num].length < 4) {
			res[context2] = subst(data[val_num][0], "^", full_suf);
			res[contexts_total] = subst(data[val_num][1], "^",
								     full_suf);
			res[contexts_total+1] = data[val_num][2];
		} else {
			dclr = data[val_num][0];
			res[context2] = subst(data[val_num][1], "^", full_suf);
			res[contexts_total] = subst(data[val_num][2], "^",
								     full_suf);
			res[contexts_total] = subst(res[contexts_total], "#4",
						  jt.curVal() + "" + this_num);
			res[contexts_total+1] = data[val_num][3];
		}
		res[context0] = subst(dclr, "^", full_suf);
		res[context0] = subst(res[context0], "@", jt.typeName());
		res[context0] = subst(res[context0], "$", jt.typeVal());
		res[context0] = subst(res[context0], "#1", jam.modif());
		String method_modif = "";
		for (int i=0; i<jmm.length; i++) {
			if (jmm[i].length() > 0)
				method_modif += jmm[i].modif() + " ";
		}
		res[context0] = subst(res[context0], "#2", method_modif);
		res[context0] = subst(res[context0], "#3", "" + (val_num + 1));
		res[context0] = subst(res[context0], "#4", jt.curVal() + "" + 
								     this_num);
		res[context1] = "";
 	}
 
/*-----------------------------------------*/
		/** @return method invokation expression */
	public String method() {
		return res[contexts_total];
	}
		/** @return description of the method invokation expression */
	public String desc() {
		return res[contexts_total+1];
	}
		/** @return name of a declared type of the invoked method */
	public String methodType() {
		return jt.typeName();
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		JTF_MethodInv fr = (JTF_MethodInv)from;
		jt = fr.jt;
		jam = fr.jam;
		jmm = fr.jmm;
	}
/*------------- Constructors ---------------*/
	public JTF_MethodInv() {
		super();
		res = new String[contexts_total + 2];
	}
		/** @param type_ind	index of declared type of the invoked
		 *  method (see <i>JTF_Types</i>) */
 	public JTF_MethodInv(int type_ind) {
		super(data.length);
		res = new String[contexts_total + 2];
		jt = new JTF_Types();
		jt.setTo(type_ind);
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jmm = JTF_MethodModif.createNumberOf(5);
		for (int i=0; i<jmm.length; i++)
			jmm[i].setTo(0);
 	}
		/** @param jt 	external <i>JTF_Types</i> object that controls
		 *  declared type of the invoked method */
 	public JTF_MethodInv(JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jmm = JTF_MethodModif.createNumberOf(5);
		for (int i=0; i<jmm.length; i++)
			jmm[i].setTo(0);
 	}	
 		/** @param combs	string that specifies allowed values */
 	public JTF_MethodInv(String combs, JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jmm = JTF_MethodModif.createNumberOf(5);
		for (int i=0; i<jmm.length; i++)
			jmm[i].setTo(0);
 	}	
		/** @param jam	external object that controls access modifiers
		 *  the invoked method is declared with 
		 *  @param jmm	array of external objects that control method
		 *  modifiers the invoked method is declared with */
 	public JTF_MethodInv(JTF_AccessModif jam,
			     JTF_MethodModif[] jmm,
			     JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		this.jmm = jmm;
	}
 	public JTF_MethodInv(String combs, 
			     JTF_AccessModif jam,
			     JTF_MethodModif[] jmm,
			     JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		this.jmm = jmm;
	}
 	public JTF_MethodInv(JTF_AccessModif jam,
			     JTF_MethodModif jmm,
			     JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		JTF_MethodModif[] tmp_jmm = {jmm};
		this.jmm = tmp_jmm;
	}
 	public JTF_MethodInv(String combs, 
			     JTF_AccessModif jam,
			     JTF_MethodModif jmm,
			     JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		JTF_MethodModif[] tmp_jmm = {jmm};
		this.jmm = tmp_jmm;
	}
}
// @ - substituted by type name
// $ - substituted by type value (literal)
// ^ - substituted by full suffix
// #1 - substituted by access modifier
// #2 - substituted by set of method modifiers
// #3 - substituted by val_num+1
// #4 - substituted by jt.curVal() + this_num
