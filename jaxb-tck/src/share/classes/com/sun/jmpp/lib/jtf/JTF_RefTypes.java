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

import java.io.PrintStream;
/**
 * <i>Jmpp Test Factory</i>
 * 
 * This class realizes Java reference type slot. It's value arrays consist of 4
 * elements:
 * name of a reference type, name of corresponding simple type, class instance
 * creation expression of the reference type and a literal of the simple type. 
 * This slot also has a field of class <i>JTF_Dims</i> wich controls a number
 * of dimensions of generated types.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_RefTypes.java 1.7 02/04/01
 */
public class JTF_RefTypes extends JTF_Slot {
	protected JTF_Dims jd = null;
	protected static final String [][] data = {
/* 0 */		{"Boolean",	"boolean",	"new Boolean(false)",	"false"},
/* 1 */		{"Character",	"char",	"new Character(\'\\u0000\')",	"\'\\u0000\'"},
/* 2 */		{"Integer",	"int",		"new Integer(0)",	"0"},
/* 3 */		{"Long",	"long",		"new Long(0L)",		"0L"},
/* 4 */		{"Float",	"float",	"new Float(0.f)",	"0.f"},
/* 5 */		{"Double",	"double",	"new Double(0.)",	"0."}
	};
		/** reference type corresponds to integral type */
	public final static String INTEGRAL =		"1-3";
		/** reference type corresponds to non-integral type */
	public final static String NON_INTEGRAL =	"0,4-5";
		/** reference type corresponds to numeric type */
	public final static String NUMERIC =		"1-5";
		/** reference type corresponds to non-numeric type */
	public final static String NON_NUMERIC =	"0";
		/** reference type corresponds to floating-point type */
	public final static String FL_POINT =		"4-5";
		/** reference type corresponds to non-floating-point type */
	public final static String NON_FL_POINT =	"0-3";
	public final static String BOO = "0";
	public final static String CHA = "1";
	public final static String INT = "2";
	public final static String LON = "3";
	public final static String FLO = "4";
	public final static String DOU = "5";
	public final static String NON_BOO = "1-5";
	public final static String NON_CHA = "0,2-5";
	public final static String NON_INT = "0-1,3-5";
	public final static String NON_LON = "0-2,4-5";
	public final static String NON_FLO = "0-3,5";
	public final static String NON_DOU = "0-4";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isIntegral() {
		return sp.parse(INTEGRAL).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isNumeric() {
		return sp.parse(NUMERIC).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isFlPoint() {
		return sp.parse(FL_POINT).contains(curVal());
	}
	public boolean isInt() {
		return sp.parse(INT).contains(curVal());
	}
	public boolean isLon() {
		return sp.parse(LON).contains(curVal());
	}
	public boolean isFlo() {
		return sp.parse(FLO).contains(curVal());
	}
	public boolean isDou() {
		return sp.parse(DOU).contains(curVal());
	}
	public boolean isCha() {
		return sp.parse(CHA).contains(curVal());
	}
	public boolean isBoo() {
		return sp.parse(BOO).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		for (int i=0; i<data[0].length; i++)
			res[i+contexts_total] = data[val_num][i];
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
		res2 = res[contexts_total + 2];
		res3 = res[contexts_total + 3];
	}
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		this.jd = ((JTF_RefTypes)from).jd;
	}
		/** @return name of the current type (if <i>jd</i> field
		 *  is not NULL - it can be an array type) */
	public String typeName() {
		if (jd == null)
			return res0;
		else
			return res0 + jd.empty();
	}
		/** @return name of the current simple type (if <i>jd</i> field
		 *  is not NULL - it can be an array type) */
	public String stypeName() {
		if (jd == null)
			return res1;
		else
			return res1 + jd.empty();
	}
	private String initArray(int dim_num, int size, boolean simple) {
		String result = "";
		if (dim_num == 0) {
			return (simple ? res3 : res2);
		} else {
			result += "{";
			result += initArray(dim_num - 1, size, simple);
			for (int i=0; i<size - 1; i++)
				result += ", "+initArray(dim_num-1, size, simple);
			result += "}";
		}
		return result;
	}
		/** @return String that represents a value of the current
		 *  type; if it's an array type - then the value can be used
		 *  only as an initializer */
	public String typeVal() {
		if (jd == null)
			return res2;
		return initArray(jd.dimsNum(), 2, false);
	}
		/** @return String that represents a value of the current simple
		 *  type; if it's an array type - then the value can be used
		 *  only as an initializer */
	public String stypeVal() {
		if (jd == null)
			return res3;
		return initArray(jd.dimsNum(), 2, true);
	}
	
/*------------- Constructors ---------------*/
	public JTF_RefTypes() {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_RefTypes(String combs) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
		/** @param jd	external JTF_Dims object that controls number
		 *  of dimensions of current type */
	public JTF_RefTypes(JTF_Dims jd) {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
		this.jd = jd;
	}
	public JTF_RefTypes(String combs, JTF_Dims jd) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
		this.jd = jd;
	}
}
