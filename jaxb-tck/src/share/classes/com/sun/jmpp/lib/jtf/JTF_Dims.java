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
 * This class realizes a "dimensions" slot. It's value arrays consist of 4
 * elements: <ul>
 *	<li> set of square brackets (used when an array type is declared)
 *	<li> set of square brackets filled with int literals (used when object
 *		of an array type is created)
 *	<li> set of square brackets filled with int literals (used when an array
 *		element is accessed)
 *	<li> set of square brackets in amount of 1 pair ("[]") less than in the
 *		first case
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_Dims.java 1.7 02/04/01
 */
public class JTF_Dims extends JTF_Slot {
	private static final int res_size = 4;
		/** slot that controls expressions placed instead of 
		 *  subscripts */
	protected JTF_Expr expr = null;
	protected static final int [] data = {0, 1, 2, 3, 4, 50};
		/** few dimensions */
	public final static String FEW      = "1-4";
		/** 50 demensions */
	public final static String NON_FEW  = "5";
		/** the number of dimensions is not zero */
	public final static String NON_ZERO = "1-5";
		/** the number of dimensions is zero */
	public final static String ZERO     = "0";
		/** the number of dimensions is one */
	public final static String ONE      = "1";
		/** the number of dimensions is two */
	public final static String TWO      = "2";
		/** the number of dimensions is zero or one */
	public final static String ZER_ONE  = "0,1";
	
		/** @return set of square brackets filled with int literals or
		 *  expressions (used when object of an array type is 
		 *  created) */
	public String filled() {
		return res0;
	}
		/** @return set of square brackets filled with int literals or
		 *  expressions (used when an array element is accessed) */
	public String accessed() {
		return res1;
	}
		/** @return set of square brackets (used when an array type 
		 *  is declared) */
	public String empty() {
		return res2;
	}
		/** @return set of square brackets in amount of 1 pair ("[]")
		 *  less than the <i>empty</i> method returns */
	public String emptyBad() {
		return res3;
	}
		/** @return current number of dimensions */
	public int dimsNum() {
		return data[val_num];
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		String dim_expr = "3";
		if (expr != null)
			dim_expr = expr.expr();
		for (int i=0; i<res.length; i++)
			res[i] = "";
		for (int i=0; i<data[val_num]; i++) {
			res[contexts_total + 3] = res[contexts_total + 2];
			res[contexts_total + 2] += "[]";
			res[contexts_total]   += "[" + dim_expr + "]";
			res[contexts_total+1] += "[" + dim_expr + "-1]";
		}
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
		res2 = res[contexts_total + 2];
		res3 = res[contexts_total + 3];
	}
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		expr = ((JTF_Dims)from).expr;
	}
/*------------- Constructors ---------------*/
	public JTF_Dims() {
		super(NON_ZERO);
		res = new String[contexts_total + res_size];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
		/** @param expr	controls expression which will be used as a
		 *  subscript */
	public JTF_Dims(JTF_Expr expr) {
		super(NON_ZERO);
		res = new String[contexts_total + res_size];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
		this.expr = expr;
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_Dims(String combs) {
		super(combs);
		res = new String[contexts_total + res_size];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
	public JTF_Dims(String combs, JTF_Expr expr) {
		super(combs);
		res = new String[contexts_total + res_size];
		for (int i=0; i<res.length; i++)
			res[i] = "";
		this.expr = expr;
	}
}
