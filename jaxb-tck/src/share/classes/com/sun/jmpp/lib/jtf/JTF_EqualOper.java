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
 * This class realizes a Java equality operator (see JLS 15.17). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_EqualOper.java 1.7 02/04/01
 */
public class JTF_EqualOper extends JTF_Slot {
	protected static final String[] data = {
/* 0 */		"==",
/* 1 */		"!=",
	};
		/** the operator is "equal to" operator */
	public static final String EQU     = "0";
		/** the operator is "not equal to" operator */
	public static final String NOT_EQU = "1";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isEqu() {
		return sp.parse(EQU).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num];
		res0 = res[contexts_total];
	}
		/** @return current operator */
	public String oper() {
		return res0;
	}
/*------------- Constructors ---------------*/
	public JTF_EqualOper() {
		super(data.length);
		res = new String[contexts_total + 1];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_EqualOper(String combs) {
		super(combs);
		res = new String[contexts_total + 1];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
