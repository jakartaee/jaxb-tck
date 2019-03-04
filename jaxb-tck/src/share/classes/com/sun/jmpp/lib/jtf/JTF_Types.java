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
 * This class realizes Java type slot. It's value arrays consist of 2 elements:
 * name of a type and a literal of that type. Any value can also be of an array 
 * type.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_Types.java 1.7 02/04/01
 */
public class JTF_Types extends JTF_Slot {
	protected JTF_Dims jd = null;
	protected static final String [][] data = {
/* 0 */		{"byte",	"(byte)0"},
/* 1 */		{"short",	"(short)0"},
/* 2 */		{"int",		"0"},
/* 3 */		{"long",	"0L"},
/* 4 */		{"float",	"0.f"},
/* 5 */		{"double",	"0."},
/* 6 */		{"char",	"'\\u0000'"},
/* 7 */		{"boolean",	"false"},
/* 8 */		{"String",	"\"\""}
	};
	public final static int ibyt = 0;
	public final static int isho = 1;
	public final static int iint = 2;
	public final static int ilon = 3;
	public final static int iflo = 4;
	public final static int idou = 5;
	public final static int icha = 6;
	public final static int iboo = 7;
	public final static int iStr = 8;
		/** integral types */
	public final static String INTEGRAL =		"0-3,6";
		/** non-integral types */
	public final static String NON_INTEGRAL =	"4,5,7-8";
		/** numeric types */
	public final static String NUMERIC =		"0-6";
		/** non-numeric types */
	public final static String NON_NUMERIC =	"7-8";
		/** floating-point types */
	public final static String FL_POINT =		"4-5";
		/** non-floating-point types */
	public final static String NON_FL_POINT =	"0-3,6-8";
		/** common set of values */
	public final static String COMMON =		"2,5-8";
	public final static String BYT = "0";
	public final static String SHO = "1";
	public final static String INT = "2";
	public final static String LON = "3";
	public final static String FLO = "4";
	public final static String DOU = "5";
	public final static String CHA = "6";
	public final static String BOO = "7";
	public final static String STR = "8";
	public final static String NON_BYT = "1-8";
	public final static String NON_SHO = "0,2-8";
	public final static String NON_INT = "0-1,3-8";
	public final static String NON_LON = "0-2,4-8";
	public final static String NON_FLO = "0-3,5-8";
	public final static String NON_DOU = "0-4,6-8";
	public final static String NON_CHA = "0-5,7-8";
	public final static String NON_BOO = "0-6,8";
	public final static String NON_STR = "0-7";
/*-------------------------------------*/
		/** contains an identifier unique for each
		 *  value */
	public String varname = "";
		/** generates literals of the specified type
		 *  @param type_id	index of type (@see JTF_Types)
		 *  @param base		value of the first generated literal
		 *  (converted to other types when necessary) 
		 *  @param step		difference between two consequently
		 *  generated literals 
		 *  @param total	total number of literals to be
		 *  generated
		 *  @return array of String representations of the generated
		 *  literals */
	public String[] genTypeVal(int type_id, int base, int step, int total) {
		if (total <= 0 || type_id < ibyt)
			return null;
		String[] val = new String[total];
		for (int i=0; i<total; i++, base+= step) 
		switch (type_id) {
		case ibyt:
			val[i] = "(byte)" + String.valueOf(base % 256);
			break;
		case isho:
			val[i] = "(short)" + String.valueOf(base % 65535);
			break;
		case iint:
			val[i] = String.valueOf(base);
			break;
		case ilon:
			val[i] = String.valueOf(base) + "L";
			break;
		case iflo:
			val[i] = String.valueOf(base) + ".f";
			break;
		case idou:
			val[i] = String.valueOf(base) + ".";
			break;
		case icha:
			val[i] = "\'\\u";
			String num = String.valueOf(base);
			for (int j=0; j<4-num.length(); j++)
				val[i]+= "0";
			val[i]+= num + "\'";
			break;
		case iboo:
			val[i] = (i % 2 == 0) ? "false" : "true";
			break;
		case iStr:		
			val[i] = "\"";
			for (int j=0; j<base%1025; j++) 
				val[i]+= "a";
			val[i]+= "\"";
			break;
		}
		return val;
	}
	public String[] genTypeVal(int type_id, int total) {
		return genTypeVal(type_id, 0, 1, total);
	}
	public String[] genTypeVal(int base) {
		return genTypeVal(val_num, base, 1, 1);
	}
		/** @return an identifier unique for each type name */
	public String getVarName(int value_num) {
		return data[value_num % data.length][0].substring(0,3);
	}
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
	public boolean isByt() {
		return curVal() == ibyt;
	}
	public boolean isSho() {
		return curVal() == isho;
	}
	public boolean isInt() {
		return curVal() == iint;
	}
	public boolean isLon() {
		return curVal() == ilon;
	}
	public boolean isFlo() {
		return curVal() == iflo;
	}
	public boolean isDou() {
		return curVal() == idou;
	}
	public boolean isCha() {
		return curVal() == icha;
	}
	public boolean isBoo() {
		return curVal() == iboo;
	}
	public boolean isStr() {
		return curVal() == iStr;
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		for (int i=0; i<data[0].length; i++)
			res[i+contexts_total] = data[val_num][i];
		res0 = res[contexts_total];
		res1 = res[contexts_total + 1];
		varname = getVarName(val_num);
	}
		/** See <i>JTF_Slot</i> */
	protected void copyFields(JTF_Slot from) {
		this.jd = ((JTF_Types)from).jd;
	}
		/** @return name of the current type (if <i>jd</i> field
		 *  is not NULL - it can be an array type) */
	public String typeName() {
		if (jd == null)
			return res0;
		else
			return res0 + jd.empty();
	}
	private String initArray(int dim_num, int size) {
		String result = "";
		if (dim_num == 0) {
			return res1;
		} else {
			result += "{";
			result += initArray(dim_num - 1, size);
			for (int i=0; i<size - 1; i++)
				result += ", " + initArray(dim_num - 1, size);
			result += "}";
		}
		return result;
	}
		/** @return String that represents a value of the current
		 *  type; if it's an array type - then the value can be used
		 *  only as an initializer */
	public String typeVal() {
		if (jd == null)
			return res1;
		return initArray(jd.dimsNum(), 2);
	}
	
/*------------- Constructors ---------------*/
	public JTF_Types() {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_Types(String combs) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
		/** @param jd	external JTF_Dims object that controls number
		 *  of dimensions of current type */
	public JTF_Types(JTF_Dims jd) {
		super(data.length);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
		this.jd = jd;
	}
	public JTF_Types(String combs, JTF_Dims jd) {
		super(combs);
		res = new String[contexts_total + data[0].length];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
		this.jd = jd;
	}
}
