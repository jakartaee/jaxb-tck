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
 * This class realizes Java field modifier slot.
 * 
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_FieldModif.java 1.8 02/04/01
 */
public class JTF_FieldModif extends JTF_Slot {
	protected static final String[] data = {
		"",
		"static",
		"final",
		"transient",
		"volatile"
	};
		/** the modifier is an empty string */
	public static final String EMPTY     = "0";
		/** the modifier is not an empty string */
	public static final String NON_EMPTY = "1-4";
		/** the modifier is <i>static</i> */
	public static final String STA       = "1";
		/** the modifier is not <i>static</i> */
	public static final String NON_STA   = "0,2-4";
		/** the modifier is <i>final</i> */
	public static final String FIN       = "2";
		/** the modifier is not <i>final</i> */
	public static final String NON_FIN   = "0-1,3-4";
		/** the modifier is <i>transient</i> */
	public static final String TRA       = "3";
		/** the modifier is not <i>transient</i> */
	public static final String NON_TRA   = "0-2,4";
		/** the modifier is <i>volatile</i> */
	public static final String VOL       = "4";
		/** the modifier is not <i>volatile</i> */
	public static final String NON_VOL   = "0-3";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isEmpty() {
		return sp.parse(EMPTY).contains(curVal());
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
	public boolean isTra() {
		return sp.parse(TRA).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isVol() {
		return sp.parse(VOL).contains(curVal());
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
/*-----------------------------------------*/
		/** @param mod	array of field modifier slots
		 *  @return true - if the input set of modifiers is
		 *  syntactically correct, false - if not */
	public static boolean areCorrect(JTF_FieldModif[] mod) {
		if (mod.length > 4)
			return false;
		int sta_cnt=0, fin_cnt=0, tra_cnt=0, vol_cnt = 0;
		for (int i=0; i<mod.length; i++) {
			if (mod[i].isSta())
				sta_cnt++;
			else if (mod[i].isFin())
				fin_cnt++;
			else if (mod[i].isTra())
				tra_cnt++;
			else if (mod[i].isVol())
				vol_cnt++;
		}
		if (sta_cnt > 1 || fin_cnt > 1 || tra_cnt > 1 || vol_cnt > 1)
			return false;
		if (fin_cnt >=1 && vol_cnt >= 1)
			return false;
		return true;
	}
	public static boolean areCorrect(JTF_FieldModif m1, 
					 JTF_FieldModif m2,
					 JTF_FieldModif m3,
					 JTF_FieldModif m4) {
		JTF_FieldModif[] m = {m1, m2, m3, m4};
		return areCorrect(m);
	}
		/** @return array of four field modifier slots */
	public static JTF_FieldModif[] createFourOf() {
		JTF_FieldModif[] result = new JTF_FieldModif[4];
		for (int i=0; i<result.length; i++)
			result[i] = new JTF_FieldModif();
		return result;
	}
		/** @param mod		array of field modifier slots 
		 *  @param modifier	modifier to search for 
		 *  @return true - if the input set of modifiers <i>mod</i>
		 *  contains modifier <i>modifier</i>, false - if not */
	public static boolean contains(JTF_FieldModif[] mod, String modifier) {
		Range rng = new StrParser().parse(modifier);
		for (int i=0; i<mod.length; i++) {
			if (rng.contains(mod[i].curVal()))
				return true;
		}
		return false;
	}
		/** multiplies the input modifier slots so that
		 *  the result of the multiplication would generate
		 *  only syntactically correct combinations of values
		 *  of the input slots; all combinations are unique 
		 *
		 *  @param mod modifier slots which are to be multiplied
		 *  @return product of the input slots */
	public static JTF_Slot multiplyCorrect(JTF_FieldModif[] mod) {
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

	public static boolean containsRepeated(JTF_FieldModif[] mod) {
		int sta_cnt=0, fin_cnt=0, tra_cnt=0, vol_cnt=0;
		for (int i=0; i<mod.length; i++) {
			if (mod[i].isSta())
				sta_cnt++;
			else if (mod[i].isFin())
				fin_cnt++;
			else if (mod[i].isTra())
				tra_cnt++;
			else if (mod[i].isVol())
				vol_cnt++;
		}
		return (sta_cnt > 1 || fin_cnt > 1 || tra_cnt > 1 || vol_cnt > 1);
	}

/*------------- Constructors ---------------*/
	public JTF_FieldModif() {
		super(data.length);
		res = new String[contexts_total+1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_FieldModif(String combs) {
		super(combs);
		res = new String[contexts_total+1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
}
