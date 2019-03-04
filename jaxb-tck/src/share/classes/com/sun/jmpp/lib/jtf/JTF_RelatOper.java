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
 * This class realizes a Java relational operator (see JLS 15.19). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_RelatOper.java 1.7 02/04/01
 */
public class JTF_RelatOper extends JTF_Slot {
	protected static final String[][] data = {
/* 0 */		{"<", "&lt;"},
/* 1 */		{">", "&gt;"},
/* 2 */		{"<=", "&gt;="}, 
/* 3 */		{">=", "&gt;="}, 
/* 4 */		{"instanseof", "instanseof"} 
	};
		/** the operator is <code>less than</code> operator */
	public static final String LT    = "0";
		/** the operator is <code>greater than</code> operator */
	public static final String GT    = "1";
		/** the operator is <code>less than or equal</code> operator */
	public static final String LT_EQ = "2";
		/** the operator is <code>greater than or equal</code> 
		 *  operator */
	public static final String GT_EQ = "3";
		/** the operator is <code>instanseof</code> operator */
	public static final String INST  = "4";
		/** the operator is not <code>instanseof</code> operator */
	public static final String NUM   = "0-3";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isLt() {
		return sp.parse(LT).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isGt() {
		return sp.parse(GT).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isLtEq() {
		return sp.parse(LT_EQ).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isGtEq() {
		return sp.parse(GT_EQ).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isInst() {
		return sp.parse(INST).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num][0];
		res[contexts_total + 1] = data[val_num][1];
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
	}
		/** @return the operator */
	public String oper() {
		return res0;
	}
		/** @return description of the operator */
	public String desc() {
		return res1;
	}
/*------------- Constructors ---------------*/
	public JTF_RelatOper() {
		super(data.length);
		res = new String[contexts_total + 2];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_RelatOper(String combs) {
		super(combs);
		res = new String[contexts_total + 2];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
