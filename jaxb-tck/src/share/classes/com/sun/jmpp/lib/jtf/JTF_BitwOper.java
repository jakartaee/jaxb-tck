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
 * This class realizes a Java bitwise and logical operators (see JLS 15.21). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_BitwOper.java 1.9 02/04/01
 */
public class JTF_BitwOper extends JTF_Slot {
	protected static final String[][] data = {
/* 0 */		{"&", "&amp;"},
/* 1 */		{"^", "^"},
/* 2 */		{"|", "&#124;"}
	};
		/** the operator is bitwise AND operator */
	public static final String AND     = "0";
		/** the operator is bitwise XOR operator */
	public static final String XOR     = "1";
		/** the operator is bitwise OR operator */
	public static final String OR      = "2";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isAnd() {
		return sp.parse(AND).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isXor() {
		return sp.parse(XOR).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isOr() {
		return sp.parse(OR).contains(curVal());
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
	public JTF_BitwOper() {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_BitwOper(String combs) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
