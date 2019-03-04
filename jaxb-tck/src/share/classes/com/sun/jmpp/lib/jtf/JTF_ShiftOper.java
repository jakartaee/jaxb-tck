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
 * This class realizes a Java shift operator (see JLS 15.18). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_ShiftOper.java 1.7 02/04/01
 */
public class JTF_ShiftOper extends JTF_Slot {
	protected static final String[][] data = {
/* 0 */		{"<<", "&lt;&lt;"},
/* 1 */		{">>", "&gt;&gt;"},
/* 2 */		{">>>", "&gt;&gt;&gt;"}
	};
		/** the operator is left shift operator */
	public static final String LFT     = "0";
		/** the operator is right shift operator */
	public static final String RGT     = "1";
		/** the operator is unsigned right shift operator */
	public static final String URGT    = "2";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isLft() {
		return sp.parse(LFT).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isRgt() {
		return sp.parse(RGT).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isURgt() {
		return sp.parse(URGT).contains(curVal());
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
	public JTF_ShiftOper() {
		super(data.length);
		res = new String[contexts_total + 2];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_ShiftOper(String combs) {
		super(combs);
		res = new String[contexts_total + 2];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}
}
