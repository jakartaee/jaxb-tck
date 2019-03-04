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
 * This class realizes a Java field access expression. It allows the field
 * to have different types, field and access modifiers.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_FieldAccess.java 1.8 02/04/01
 */
public class JTF_FieldAccess extends JTF_Slot {
		/** Type of the field */
	protected JTF_Types jt;
		/** Access modifiers */
	protected JTF_AccessModif jam;
		/** Field modifiers */
	protected JTF_FieldModif[] jfm;
		/** Class's suffix */
	private static final String suff = "fa";
	public String desc = "";
		/** the expression contains an instance creation expression */
	public static final String INST_CRE     = "4-10";
		/** the expression does not contain an instance creation
		 *  expression */
	public static final String NON_INST_CRE = "0-3";
	public static final String ARR          = "1000"; // obsolete
	public static final String NON_ARR      = "0-9";  // obsolete
		/** field declared in superclass is accessed */
	public static final String SUB          = "9-10";
		/** field declared in this class is accessed */
	public static final String NON_SUB      = "0-8";
		/** field declared in superinterface is accessed */
	public static final String INTERF       = "10";
		/** field declared not in superinterface is accessed */
	public static final String NON_INTERF   = "0-9";
		/** common subset of values */
	public static final String COMMON       = "0,2,5,7,9";
		/** Common part of declarations required for all expressions */
	protected String decl_pattern = 
			"\nclass A^#3 {\n\t#1 #2 @ a = $;\n" +
			"\tpublic A^#3 next = null;\n" + 
			"\tpublic A^#3 getNext() { return next; }\n}";
	protected static final String [][] data = {
/* 0 */		 {"\n\t\tA^1 a^1 = new A^1();",
		 "a^1.a",
		 "ClassInstance.field"},
/* 1 */		 {"\n\t\tA^2 a^2 = new A^2();",
		 "a^2.getNext().a",
		 "ClassInstance.Method().field"},
/* 2 */		 {"\n\t\tA^3 a^3 = new A^3();",
		 "a^3.next.a",
		 "ClassInstance.RefTypeField.field"},
/* 3 */		 {"\n\t\tA^4 a^4 = new A^4();",
		 "a^4.next.getNext().a",
		 "ClassInstance.RefTypeField.Method().field"},
/* 4 */		 {"",
		 "(new A^5()).a",
		 "(ClassInstCreExpr).field"},
/* 5 */		 {"",
		 "(new A^6()).getNext().a",
		 "(ClassInstCreExpr).Method().field"},
/* 6 */		 {"",
		 "(new A^7()).next.a",
		 "(ClassInstCreExpr).RefTypeField.field"},
/* 7 */		 {"",
		 "(new A^8()).next.getNext().a",
		 "(ClassInstCreExpr).RefTypeField.Method().field"},
/* 8 */		 {"\nclass AB^ {\n" +
			"\t#1 #2 @ a = $;\n" + 
			"\tpublic AB^ next = null;\n" + 
			"\tpublic AB^ getNext() { return next; }\n}\n" +
			"class AB1^ extends AB^ {}\n",
		 "",
		 "(new AB1^()).next.getNext().a",
		 "(SubClassInstCreExpr).RefTypeField.Method().SuperClassField"},
/* 9 */		 {"\ninterface I#4{\n" +
			"\t@ a = $;\n" + 
			"\tI#4 getNext();\n}\n" +
			"class AC#4 implements I#4 {\n" +
			"\tpublic I#4 next = null;\n" +
			"\tpublic I#4 getNext() { return next; }\n}\n",
		 "",
		 "(new AC#4()).next.getNext().a",
		 "(SubClassInstCreExpr).RefTypeField.Method()." +
			"SuperInterfField"}};
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isInstCre() {
		return sp.parse(INST_CRE).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isArr() {
		return sp.parse(ARR).contains(curVal());
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
		for (int i=0; i<jfm.length; i++)
			full_suf += jfm[i].curVal();
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
		String field_modif = "";
		for (int i=0; i<jfm.length; i++)
			field_modif += jfm[i].modif() + " ";
		res[context0] = subst(res[context0], "#2", field_modif);
		res[context0] = subst(res[context0], "#3", "" + (val_num + 1));
		res[context0] = subst(res[context0], "#4", jt.curVal() + "" + 
								     this_num);
		res[context1] = "";
 	}
 
		/** @return field access expression */
	public String field() {
		return res[contexts_total];
	}
		/** @return description of what the expression is */
	public String desc() {
		return res[contexts_total+1];
	}
		/** @return the type of the field */
	public String fieldType() {
		String type_name = jt.typeName();
		if (isArr())
			type_name += "[]";
		return type_name;
	}
		/** @return value which can be used as an initializer of the
		 *  field */
	public String fieldVal() {
		if (isArr())
			return "{" + jt.typeVal() + ", " + jt.typeVal() + "}";
		else
			return jt.typeVal();
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		JTF_FieldAccess fr = (JTF_FieldAccess)from;
		jt = fr.jt;
		jam = fr.jam;
		jfm = fr.jfm;
	}
/*------------- Constructors ---------------*/
	public JTF_FieldAccess() {
		super();
		res = new String[contexts_total + 2];
	}
		/** @param type_ind	specifies index of the field's type
		 *  (see also <i>JTF_Types</i>) */
 	public JTF_FieldAccess(int type_ind) {
		super(data.length);
		res = new String[contexts_total + 2];
		jt = new JTF_Types();
		jt.setTo(type_ind);
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jfm = JTF_FieldModif.createFourOf();
		for (int i=0; i<jfm.length; i++)
			jfm[i].setTo(0);
 	}
		/** @param jt	specifies external <i>JTF_Types</i> object
		 *  which controls the types of the field */
 	public JTF_FieldAccess(JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jfm = JTF_FieldModif.createFourOf();
		for (int i=0; i<jfm.length; i++)
			jfm[i].setTo(0);
 	}	
 		/** @param combs	string that specifies allowed values */
 	public JTF_FieldAccess(String combs, JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		jam = new JTF_AccessModif(JTF_AccessModif.EMPTY);
		jam.setTo(0);
		jfm = JTF_FieldModif.createFourOf();
		for (int i=0; i<jfm.length; i++)
			jfm[i].setTo(0);
 	}	
		/** @param jam	<i>JTF_AccessModif</i> object which controls
		 *  access modifiers of the field 
		 *  @param jfm	array of <i>JTF_FieldModif</i> objects which
		 *  control field modifiers */
 	public JTF_FieldAccess(JTF_AccessModif jam,
			       JTF_FieldModif[] jfm,
			       JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		this.jfm = jfm;
	}
 	public JTF_FieldAccess(String combs, 
			       JTF_AccessModif jam,
			       JTF_FieldModif[] jfm,
			       JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		this.jfm = jfm;
	}
 	public JTF_FieldAccess(JTF_AccessModif jam,
			       JTF_FieldModif jfm,
			       JTF_Types jt) {
		super(data.length);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		JTF_FieldModif[] tmp_jfm = {jfm};
		this.jfm = tmp_jfm;
	}
 	public JTF_FieldAccess(String combs, 
			       JTF_AccessModif jam,
			       JTF_FieldModif jfm,
			       JTF_Types jt) {
		super(combs);
		res = new String[contexts_total + 2];
		this.jt = jt;
		this.jam = jam;
		JTF_FieldModif[] tmp_jfm = {jfm};
		this.jfm = tmp_jfm;
	}
}
// @ - substituted by type name
// $ - substituted by type value (literal)
// ^ - substituted by full suffix
// #1 - substituted by access modifier
// #2 - substituted by set of four field modifiers
// #3 - substituted by val_num+1
// #4 - substituted by jt.curVal() + this_num
