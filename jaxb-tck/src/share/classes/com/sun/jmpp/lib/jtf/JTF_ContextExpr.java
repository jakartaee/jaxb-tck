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
 * This class realizes a slot whose values are Java expressions in
 * different contexts. These are <ul>
 * <li>a class member initializer 
 * <li>an initializer of class member of an array type 
 * <li>a parameter of the method which is an initializer of a class member 
 * <li>a parameter of the method called from another method
 * <li>a local variable initializer 
 * <li>a local array variable initializer
 * <li><code>Expression</code> in <code>for</code> statement
 * <li><code>Expression</code> in <code>switch</code> statement
 * <li><code>Expression</code> in <code>while</code> statement
 * <li><code>Expression</code> in <code>if</code> statement
 * </ul>.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_ContextExpr.java 1.10 02/04/01
 */
public class JTF_ContextExpr extends JTF_Slot {
		/** expression to be placed into different contexts */
	protected JTF_Expr je;
		/** the class' suffix */
	private static final String suff = "ce";
		/** description of current context */
	public String desc = "";
		/** statement context */
	public final static String STMT     = "6-10";
	public final static String NON_SWITCH_STMT = "6,8-10";
		/** non-statement context */
	public final static String NON_STMT = "0-5";
	protected static final String [][] data = {
/* 0 */		{"\nclass Cl^1 {\n#1#2\n\t@ v^1 = $;\n}\n",
		 "",
		 "",
		 "",
		 "a class member initializer"
		},
/* 1 */		{"\nclass Cl^2 {\n#1#2\n\t@[] v^2 = {$, $};\n}\n", 
		 "",
		 "",
		 "",
		 "an initializer of class member of an array type"
		},
/* 2 */		{"\nclass Cl^3 {\n#1#2\n" +
		 	"\tstatic @ foo^1(@ v) {\n\t\treturn v;\n\t}\n" +
			"\t@ v^3 = foo^1($);\n}\n",
		 "",
		 "",
		 "",
		 "a parameter of the method which is an initializer of " +
			"a class member"
		},
/* 3 */		{"\nclass Cl^4 {\n#1#2\n" + 
		 	"\tstatic void foo^2(@ v) { return; }\n" +
			"\tstatic void foo^2_() { foo^2($); }\n}\n",
		 "",
		 "",
		 "",
		 "a parameter of the method called from another method"
		},
/* 4 */		{"\nclass Cl^5 {\n#1#2\n\tvoid foo() { @ v^4 = $; }\n}\n",
		 "",
		 "",
		 "",
		 "a local variable initializer"
		},
/* 5 */		{"\nclass Cl^6 {\n#1#2\n\tvoid foo() { @[] v^5 = {$}; }\n}\n",
		 "",
		 "",
		 "",
		 "a local array variable initializer"
		},
/* 6 */		{"",
		 "",
		 "",
		 "for (int i=0; $; i++) {}",
		 "<code>Expression</code> in <code>for</code> statement"
		},
/* 7 */		{"",
		 "",
		 "",
		 "switch ($) {}",
		 "<code>Expression</code> in <code>switch</code> statement"
		},
/* 8 */		{"",
		 "",
		 "",
		 "while ($) {}",
		 "<code>Expression</code> in <code>while</code> statement"
		},
/* 9 */		{"",
		 "",
		 "",
		 "if ($) {}",
		 "<code>Expression</code> in <code>if</code> statement"
		},
/* 10 */	{"",
		 "",
		 "",
		 "do { int var_nameXYZ = 0; } while ($)",
		 "<code>Expression</code> in <code>do</code> statement"
		}
 	};
  
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isStmt() {
		return sp.parse(STMT).contains(curVal());
	}
/*-----------------------------------------*/	
	private String doSubst(int ind) {
 		String s = data[val_num][ind];
		String hex = Integer.toHexString(je.curVal());
		hex = "_"+ suff + this_num + hex + je.typeName().substring(0,2);
		s = subst(s, "^", hex);
		s = subst(s, "#1", je.decl1());
		String tmp = subst(je.decl2(), "\n", "");
		s = subst(s, "#2", subst(tmp, "\t\t", "\t"));
		s = subst(s, "@", je.typeName());
		s = subst(s, "$", je.expr());
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
		res0 = res[contexts_total];
		res1 = res[contexts_total+1];		
 	}
 
		/** @return description of the context */
	public String desc() {
		return res1;
	}
		/** @return statement (for statement context) */
	public String stmt() {
		return res0;
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		je = ((JTF_ContextExpr)from).je;
	}
/*------------- Constructors ---------------*/
	public JTF_ContextExpr() {
		super();
		res = new String[data[0].length];
	}
		/** @param je	expression to be placed into different 
		 *  contexts */
 	public JTF_ContextExpr(JTF_Expr je) {
		super(data.length);
		res = new String[data[0].length];
		this.je = je;
 	}	
 		/** @param combs	string that specifies allowed values */
	public JTF_ContextExpr(String combs, JTF_Expr je) {
		super(combs);
		res = new String[data[0].length];
		this.je = je;
 	}	
}
// @ - substituted by je.typeName()
// $ - substituted by je.res0
// #i - substituted by je.decli()
// ^ - substituted by full suffix
