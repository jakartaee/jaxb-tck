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
 * This class realizes a Java multiplicative operator (see JLS 15.16). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_MultOper.java 1.7 02/04/01
 */
public class JTF_MultOper extends JTF_Slot {
	protected static final String[] data = {
/* 0 */		"*",
/* 1 */		"/",
/* 2 */		"%",
	};
		/** the operator is multiplication operator */
	public static final String MUL     = "0";
		/** the operator is division operator */
	public static final String DIV     = "1";
		/** the operator is remainder operator */
	public static final String REM     = "2";
		/** the operator is not multiplication operator */
	public static final String NON_MUL = "1-2";
		/** the operator is not division operator */
	public static final String NON_DIV = "0,2";
		/** the operator is not remainder operator */
	public static final String NON_REM = "0-1";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isMul() {
		return sp.parse(MUL).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isDiv() {
		return sp.parse(DIV).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isRem() {
		return sp.parse(REM).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num];
		res0 = res[contexts_total];
	}
		/** @return the operator */
	public String oper() {
		return res0;
	}
/*------------- Constructors ---------------*/
	public JTF_MultOper() {
		super(data.length);
		res = new String[contexts_total + 1];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_MultOper(String combs) {
		super(combs);
		res = new String[contexts_total + 1];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
