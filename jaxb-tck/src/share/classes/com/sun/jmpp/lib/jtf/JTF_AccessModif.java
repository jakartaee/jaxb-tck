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
 * This class realizes Java access modifier slot.
 * 
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_AccessModif.java 1.8 02/04/01
 */
public class JTF_AccessModif extends JTF_Slot {
	protected static final String[] data = {
		"",
		"public",
		"protected",
		"private"
	};
		/** the modifier is an empty string */
	public static final String EMPTY     = "0";
		/** the modifier is not an empty string */
	public static final String NON_EMPTY = "1-3";
		/** the modifier is <i>public</i> */
	public static final String PUB       = "1";
		/** the modifier is not <i>public</i> */
	public static final String NON_PUB   = "0,2-3";
		/** the modifier is <i>protected</i> */
	public static final String PRO       = "2";
		/** the modifier is not <i>protected</i> */
	public static final String NON_PRO   = "0-1,3";
		/** the modifier is <i>private</i> */
	public static final String PRI       = "3";
		/** the modifier is not<i>private</i> */
	public static final String NON_PRI   = "0-2";
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
	public boolean isPro() {
		return sp.parse(PRO).contains(curVal());
	}
		/** see comments for value range constants */
	public boolean isPri() {
		return sp.parse(PRI).contains(curVal());
	}
/*-----------------------------------------*/
	public static boolean containsRepeated(JTF_AccessModif[] mod) {
		int pub_cnt=0, pro_cnt=0, pri_cnt=0;
		for (int i=0; i<mod.length; i++) {
			if (mod[i].isPub())
				pub_cnt++;
			else if (mod[i].isPro())
				pro_cnt++;
			else if (mod[i].isPri())
				pri_cnt++;
		}
		return (pub_cnt > 1 || pro_cnt > 1 || pri_cnt > 1);
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
/*------------- Constructors ---------------*/
	public JTF_AccessModif() {
		super(data.length);
		res = new String[contexts_total + 1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
 		/** @param combs	string that specifies allowed values */
	public JTF_AccessModif(String combs) {
		super(combs);
		res = new String[contexts_total + 1];
		for (int i=0; i<=contexts_total; i++)
			res[i] = "";
	}
}
