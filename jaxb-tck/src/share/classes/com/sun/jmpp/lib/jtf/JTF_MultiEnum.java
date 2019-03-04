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

import java.util.Vector;
/**
 * <i>Jmpp Test Factory</i>
 * 
 * This class realizes an extended "enumeration" slot. 
 * The slot's values are equally sized arrays of strings.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_MultiEnum.java 1.5 02/04/01
 */
public class JTF_MultiEnum extends JTF_Slot {
		/** place to store the set of values */
	protected Vector values = new Vector();
		/** contains current value */
	protected String[] value;
		/** adds one value to the set of values
		 *  @param val string to add */
	public void add(String[] val) {
		if (val != null) {
			values.addElement(val);
			val_total++;
		}
		rng.replace(val_total);
	}
		/** adds an array of values to the set of values
		 *  @param vals	values to add */
	public void add(String[][] vals) {
		for (int i=0; i<vals.length; i++) {
			if (vals[i] != null) {
				values.addElement(vals[i]);
				val_total++;
			}
		}
		rng.replace(val_total);
	}
		/** adds set of values of another enumeration slot to
		 *  this set of values
		 *  @param other	the other slot */
	public void add(JTF_MultiEnum other) {
		for (int i=0; i<other.values.size(); i++)
			values.addElement(other.values.elementAt(i));
		val_total += other.length();
		rng.replace(val_total);
	}
	
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		value = (String[])values.elementAt(val_num);
	}
		/** declared to use the slot's instance identifier as a 
		 *  String */
	public String toString() {
		String res = "";
		for (int i=0; i<value.length; i++)
			res += value[i];
		return res;
	}
		/** @return current value of the slot */
	public String[] value() {
		return value;
	}
/*------------- Constructors ---------------*/
	public JTF_MultiEnum() {
		res = new String[contexts_total + 1];
		for (int i=0; i<res.length; i++)
			res[i] = "";
	}

	public JTF_MultiEnum(String[] val) {
		this();
		add(val);
	}
		/** @param vals	values that the created slot's set of
		 *  values will contain */
	public JTF_MultiEnum(String[][] vals) {
		this();
		add(vals);
	}
}
