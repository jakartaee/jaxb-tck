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
 * This class realizes Java method modifier slot.
 * 
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_MethodModif.java 1.7 02/04/01
 */
public class JTF_MethodModif extends JTF_Slot {
	protected static final String[] data = {
		"",
		"abstract",
		"static",
		"final",
		"synchronized",
		"native",
	};
		/** the modifier is an empty string */
	public static final String EMPTY     = "0";
		/** the modifier is not an empty string */
	public static final String NON_EMPTY = "1-5";
		/** the modifier is <i>abstract</i> */
	public static final String ABS       = "1";
		/** the modifier is not <i>abstract</i> */
	public static final String NON_ABS   = "0,2-5";
		/** the modifier is <i>static</i> */
	public static final String STA       = "2";
		/** the modifier is not <i>static</i> */
	public static final String NON_STA   = "0-1,3-5";
		/** the modifier is <i>final</i> */
	public static final String FIN       = "3";
		/** the modifier is not <i>final</i> */
	public static final String NON_FIN   = "0-2,4-5";
		/** the modifier is <i>synchronized</i> */
	public static final String SYN       = "4";
		/** the modifier is not <i>synchronized</i> */
	public static final String NON_SYN   = "0-3,5";
		/** the modifier is <i>native</i> */
	public static final String NAT       = "5";
		/** the modifier is not <i>native</i> */
	public static final String NON_NAT   = "0-4";
		/** the modifier is an empty string or <i>static</i> or
		 *  <i>final</i> */
	public static final String EMP_STA_FIN = "0,2-3";
		/** the modifier is neither <i>static</i> nor <i>final</i> */
	public static final String NON_STA_FIN = "0-1,4-5";
		/** the modifier is <i>synchronized</i> or <i>native</i> */
	public static final String SYN_NAT   = "4-5";
		/** the modifier is neither <i>synchronized</i> nor
		 *  <i>native</i> */
	public static final String NON_SYN_NAT = "0-3";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isEmpty() {
		return sp.parse(EMPTY).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isAbs() {
		return sp.parse(ABS).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isSta() {
		return sp.parse(STA).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isFin() {
		return sp.parse(FIN).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isSyn() {
		return sp.parse(SYN).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isNat() {
		return sp.parse(NAT).contains(curVal());
	}
/*-----------------------------------------*/
		/** @param num	number of method modifier slots to create
		 *  @return array of <i>num</i> method modifier slots */
	public static JTF_MethodModif[] createNumberOf(int num) {
		JTF_MethodModif[] result = new JTF_MethodModif[num];
		for (int i=0; i<num; i++)
			result[i] = new JTF_MethodModif();
		return result;
	
	}
		/** @param comb	specifies values which every created slot can
		 *  take
		 *  @param num	number of method modifier slots to create
		 *  @return array of <i>num</i> method modifier slots */
	public static JTF_MethodModif[] createNumberOf(String comb, int num) {
		JTF_MethodModif[] result = new JTF_MethodModif[num];
		for (int i=0; i<num; i++)
			result[i] = new JTF_MethodModif(comb);
		return result;
	
	}
		/** @param mod	array of method modifier slots to check
		 *  @return true - if the input set of modifiers is
		 *  syntactically correct, false - if not */
	public static boolean areCorrect(JTF_MethodModif[] mod) {
		if (mod.length > 5)
			return false;
		int abs_cnt=0, sta_cnt=0, fin_cnt=0, syn_cnt = 0, nat_cnt = 0;
		for (int i=0; i<mod.length; i++) {
			if (mod[i].isAbs())
				abs_cnt++;
			if (mod[i].isSta())
				sta_cnt++;
			if (mod[i].isFin())
				fin_cnt++;
			if (mod[i].isSyn())
				syn_cnt++;
			if (mod[i].isNat())
				nat_cnt++;
		}
		if (abs_cnt>1 || 
		    sta_cnt>1 || 
		    fin_cnt>1 || 
		    syn_cnt>1 || 
		    nat_cnt>1)
			return false;
		if (abs_cnt == 1 && (sta_cnt == 1 ||
				     fin_cnt == 1 || 
				     syn_cnt == 1 || 
				     nat_cnt == 1))
			return false;
		return true;
	}
		/** multiplies the input modifier slots so that
		 *  the result of the multiplication would generate
		 *  only syntactically correct combinations of values
		 *  of the input slots; all combinations are unique 
		 *
		 *  @param mod modifier slots which are to be multiplied
		 *  @return product of the input slots */
	public static JTF_Slot multiplyCorrect(JTF_MethodModif[] mod) {
		JTF_Slot result = JTF_Slot.multiplyUNIQ(mod);
		Range new_rng = new Range();
		while (result.genNext()) {
			if (areCorrect(mod))
				new_rng.add(result.curVal());
		}
		result.rng = new_rng;
		result.rng.merge();
		result.val_total = result.rng.length();
		return result;
	}
		/** @param mod		array of method modifier slots 
		 *  @param modifier	modifier to search for 
		 *  @return true - if the input set of modifiers <i>mod</i>
		 *  contains modifier <i>modifier</i>, false - if not */
	public static boolean contains(JTF_MethodModif[] mod, String modifier) {
		Range rng = new StrParser().parse(modifier);
		for (int i=0; i<mod.length; i++) {
			if (rng.contains(mod[i].curVal()))
				return true;
		}
		return false;
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num];
		res0 = res[contexts_total];
	}
		/** @return current modifier */
	public String modif() {
		return res0;
	}
/*------------- Constructors ---------------*/
	public JTF_MethodModif() {
		super(data.length);
		res = new String[contexts_total + 1];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_MethodModif(String combs) {
		super(combs);
		res = new String[contexts_total + 1];
		for (int i=0; i<contexts_total; i++)
			res[i] = "";
	}
}
