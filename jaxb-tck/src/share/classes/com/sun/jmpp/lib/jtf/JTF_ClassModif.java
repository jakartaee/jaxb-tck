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
 * This class realizes Java class modifier slot.
 * 
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_ClassModif.java 1.7 02/04/01
 */
public class JTF_ClassModif extends JTF_Slot {
	protected static final String[] data = {
		"",
		"public",
		"abstract",
		"final"
	};
		/** the modifier is an empty string */
	public static final String EMPTY     = "0";
		/** the modifier is not an empty string */
	public static final String NON_EMPTY = "1-3";
		/** the modifier is <i>public</i> */
	public static final String PUB       = "1";
		/** the modifier is not <i>public</i> */
	public static final String NON_PUB   = "0,2-3";
		/** the modifier is <i>abstract</i> */
	public static final String ABS       = "2";
		/** the modifier is not <i>abstract</i> */
	public static final String NON_ABS   = "0-1,3";
		/** the modifier is <i>final</i> */
	public static final String FIN       = "3";
		/** the modifier is not <i>final</i> */
	public static final String NON_FIN   = "0-2";
/*---------- Value number checks ----------*/
		/** see comments for value range constants */
	public boolean isEmpty() {
		return sp.parse(EMPTY).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isPub() {
		return sp.parse(PUB).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isAbs() {
		return sp.parse(ABS).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isFin() {
		return sp.parse(FIN).contains(curVal());
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		res[contexts_total] = data[val_num];
		res0 = res[contexts_total];
	}
		/** @return the modifier */
	public String modif() {
		return res0;
	}
/*-----------------------------------------*/
		/** @param mod	array of class modifier slots
		 *  @return true - if the input set of modifiers is
		 *  syntactically correct, false - if not */
	public static boolean areCorrect(JTF_ClassModif[] mod) {
		if (mod.length > 3)
			return false;
		int abs_cnt=0, fin_cnt=0, pub_cnt=0;
		for (int i=0; i<mod.length; i++) {
			if (mod[i].isAbs())
				abs_cnt++;
			if (mod[i].isFin())
				fin_cnt++;
			if (mod[i].isPub())
				pub_cnt++;
		}
		if (abs_cnt > 1 || fin_cnt > 1 || pub_cnt > 1)
			return false;
		return !(abs_cnt == 1 && fin_cnt == 1);
	}
	public static boolean areCorrect(JTF_ClassModif m1, 
					 JTF_ClassModif m2,
					 JTF_ClassModif m3) {
		JTF_ClassModif[] m = {m1, m2, m3};
		return areCorrect(m);
	}
		/** @return array of three class modifier slots */
	public static JTF_ClassModif[] createThreeOf() {
		JTF_ClassModif[] result = new JTF_ClassModif[3];
		for (int i=0; i<result.length; i++)
			result[i] = new JTF_ClassModif();
		return result;
	}
		/** @param mod		array of class modifier slots 
		 *  @param modifier	modifier to search for 
		 *  @return true - if the input set of modifiers <i>mod</i>
		 *  contains modifier <i>modifier</i>, false - if not */
	public static boolean contains(JTF_ClassModif[] mod, String modifier) {
		Range rng = new StrParser().parse(modifier);
		for (int i=0; i<mod.length; i++) {
			if (rng.contains(mod[i].curVal()))
				return true;
		}
		return false;
	}
/*------------- Constructors ---------------*/
	public JTF_ClassModif() {
		super(data.length);
		res = new String[contexts_total+1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_ClassModif(String combs) {
		super(combs);
		res = new String[contexts_total+1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
}
