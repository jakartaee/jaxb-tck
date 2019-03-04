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
 * This class realizes a Java expression slot. It can be <ul>
 * <li>literal 
 * <li>variable 
 * <li>parenthesized expression 
 * <li>array access
 * <li>class instance creation 
 * <li>method invokation </ul>.
 * Besides, it can be of any Java type.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_Expr.java 1.9 05/04/08
 */
public class JTF_Expr extends JTF_Slot {
		/** contains information about the type of the expression */
	protected JTF_Types jt;
		/** class' suffix */
	private static final String suff = "expr";
		/** contains comment used in .html file */
	protected String desc = "";
		/** contains the expression itself */
	protected String expr = "";
		/** the expression can not be assigned a value */
	public static final String NON_VARIABLE   = "0-6,10-11";
		/** the expression can be assigned a value */
	public static final String VARIABLE       = "7-9";
		/** the expression contains arithmetic operators */
	public static final String ARITHMETIC     = "5-6";
		/** the expression does not contain arithmetic operators */
	public static final String NON_ARITHMETIC = "0-4,7-13";
		/** the expression is a class instance/array creation */
	public static final String CREATION       = "10-11";
		/** the expression is not a class instance/array creation */
	public static final String NON_CREATION   = "0-9,13-13";
		/** the expression is a void method invokation */
	public static final String VOID           = "12-13";
		/** the expression is not a void method invokation */
	public static final String NON_VOID       = "0-11";
		/** the expressiom is a literal */
	public static final String LITERAL	  = "0";
		/** the expressiom is not a literal */
	public static final String NON_LITERAL	  = "1-13";
		/** common subset of values */
	public static final String COMMON	  = "0,3,7,9";
		/** contains templates for the slot's values */
	protected static final String [][] data = {
/* 0 */		{"",
		 "",
		 "",
		 "$",
		 "literal of type <code>@</code>"},
/* 1 */		{"",		
		 "",	
		 "\n\t\tfinal @ #^1 = $;",
		 "#^1",		
		 "final variable of type <code>@</code>"},
/* 2 */		{"\nclass Cl^1 {\n\tfinal @ v = $;\n}\n",		
		 "",	
		 "\n\t\tCl^1 cl^1 = new Cl^1();",
		 "cl^1.v",		
		 "final class member variable of type <code>@</code>"},
/* 3 */		{"",		
		 "\n\tstatic @ foo^1(@ var) {\n\t\t@ a= $;\n\t\treturn a;\n\t}",
		 "\n\t\t@ #^2 = $;",
		 "foo^1(#^2)",		
		 "value of type @ returned by method"},
/* 4 */		{"\nclass Cl^2 {\n\t@ v = $;\n\t@ foo(@ var)" +
			" { \n\t\treturn v; \n\t} \n}\n",		
		 "",	
		 "\n\t\t@ #^3 = $; Cl^2 cl^2 = new Cl^2();",
		 "cl^2.foo(#^3)",		
		 "value of type <code>@</code> returned by another class " +
			"member method"},
/* 5 */		{"",		
		 "",	
		 "\n\t\t@ #^4 = $;",
		 "(#^4 + $)",		
		 "parenthesized expression of type <code>@</code>"},
/* 6 */ 	{"\nclass Cl^3 { @ v = $; }",		
 		 "\n\tstatic  @ foo^2(@ var) {" +
			"\n\t\t@ a = $;\n\t\t return a; \n\t}",
 		 "\n\t\tCl^3 cl^3 = new Cl^3();"+
			"\n\t\t@[] arr^2 = {$, $}; @ #^6 = $;",
 		 "((foo^2($) + (++arr^2[0]))/$*#^6 - cl^3.v)",		
		 "complex parenthesized expression of type <code>@</code>"}, 
/*---- end of value expressions ---*/
/* 7 */		{"",		
		 "",	
		 "\n\t\t@ #^5 = $;",
		 "#^5",		
		 "variable of type <code>@</code>"},
/* 8 */		{"\nclass Cl^4 {\n\t@ v = $;\n}\n",		
		 "",	
		 "\n\t\tCl^4 cl^4 = new Cl^4();",
		 "cl^4.v",		
		 "class member variable of type <code>@</code>"},
/* 9 */		{"",		
 		 "",	
 		 "\n\t\t@[] arr^1 = {$, $, $, $};",	
 		 "arr^1[3]",		
		 "array access expression of type <code>@</code>"},
/*---- end of variable expressions ---*/
/* 10 */	{"\nclass Cl^5 {\n\t@ v = $;\n}\n",		
 		 "",	
 		 "",	
 		 "new Cl^5()",		
		 "class instance creation expression"},
/* 11 */	{"",		
 		 "",	
 		 "",	
 		 "new @[2]",		
		 "<code>@</code> array creation expression"},
/* 12 */	{"\nclass Cl^6 {\n\tvoid foo() { int a = 0; }\n}\n",		
 		 "",	
 		 "\n\t\tCl^6 cl^6 = new Cl^6();",	
 		 "cl^6.foo()",		
		 "another class' void method invocation"},
/* 13 */	{"",		
 		 "\tstatic void foo^3() { int a = 0; }\n",	
 		 "",	
 		 "foo^3()",		
		 "void method invocation"}
 	};
  
/*---------- Value number checks ----------*/
		/** @return true - if the expression can be assigned a value,
		 *  false - if it can not */
	public boolean isVariable() {
		return sp.parse(VARIABLE).contains(curVal());
	}
		/** @return true - if the expression contains arithmetic 
		 *  operators, false - if it does not */
	public boolean isArithmetic() {
		return sp.parse(ARITHMETIC).contains(curVal());
	}
		/** @return true - if the expression is class instance/array 
		 *  creation expression, false - if it is not */
	public boolean isCreation() {
		return sp.parse(CREATION).contains(curVal());
	}
		/** @return true - if the expression is a void method
		 *  invokation, false - if it is not */
	public boolean isVoid() {
		return sp.parse(VOID).contains(curVal());
	}
/*-----------------------------------------*/
	private String doSubst(int ind) {
 		String s = data[val_num][ind];
		s = subst(s, "^", "_" + suff + this_num);
		String vname = jt.typeName().substring(0, 2);
		s = subst(s, "#", vname);
		s = subst(s, "@", jt.typeName());
		s = subst(s, "$", jt.typeVal());
		return s;
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
 	public void setTo(int value_num) {
		super.setTo(value_num);
		for (int i=0; i<res.length; i++)
			res[i] = doSubst(i);
		decl0 = res[0];
		decl1 = res[1];
		decl2 = res[2];
		expr = res0 = res[contexts_total];
		desc = res1 = res[contexts_total+1];		
 	}
 
		/** @return expression */
	public String expr() {
		return expr;
	}
		/** @return description of what the expression is */
	public String desc() {
		return desc;
	}
		/** sets slot to get types from
		 *  @param jt	Java type slot */
	public void setType(JTF_Types jt) {
		this.jt = jt;
	}
		/** @return the type of the expression */
	public String typeName() {
		return jt.typeName();
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		jt = ((JTF_Expr)from).jt;
	}
/*------------- Constructors ---------------*/
	public JTF_Expr() {
		super();
		res = new String[data[0].length];
	}
		/** This constructor is useful when there's no need to
		 *  change type of the expression
		 *  @param type_ind index of Java type (see <i>JTF_Types</i>)
		 */
 	public JTF_Expr(int type_ind) {
		super(data.length);
		res = new String[data[0].length];
		jt = new JTF_Types();
		jt.setTo(type_ind);
 	}
		/** Types of the expression slot created by this constructor
		 *  can be controlled using external <i>JTF_Types<i> object
		 *
		 *  @param jt external <i>JTF_Types<i> object
		 */
 	public JTF_Expr(JTF_Types jt) {
		super(data.length);
		res = new String[data[0].length];
		this.jt = jt;
 	}	
		/** expression will always have type <i>int</i> (or void)
		 *  @param combs ranges indices (in the <i>data</i> array)
		 *  of values which this slot is allowed to take
		 */
 	public JTF_Expr(String combs) {
		super(combs);
		res = new String[data[0].length];
		jt = new JTF_Types();
		jt.setTo(JTF_Types.iint);
 	}	
		
		/** Create expression slot with the specified indices of
		 *  values and <i>JTF_Types</i> object
		 *  @param combs	string that specifies allowed values
		 *  @param jt		external <i>JTF_Types<i> object
		 */
 	public JTF_Expr(String combs, JTF_Types jt) {
		super(combs);
		res = new String[data[0].length];
		this.jt = jt;
 	}	
}
// @ - substituted by type name
// $ - substituted by value (literal)
// # - substituted by JTF_Types.varname
// ^ - substituted by class prefix
