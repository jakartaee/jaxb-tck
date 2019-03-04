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
 * This class realizes a slot giving all possible pairs of primitive 
 * types involved in the narrowing primitive conversion (see JLS 5.1.3). 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_NarrPrimConv.java 1.7 02/04/01
 */
public class JTF_NarrPrimConv extends JTF_Slot {
	private JTF_Types tbyt = new JTF_Types(JTF_Types.BYT);
	private JTF_Types tsho = new JTF_Types(JTF_Types.SHO);
	private JTF_Types tcha = new JTF_Types(JTF_Types.CHA);
	private JTF_Types tint = new JTF_Types(JTF_Types.INT);
	private JTF_Types tlon = new JTF_Types(JTF_Types.LON);
	private JTF_Types tflo = new JTF_Types(JTF_Types.FLO);
	private JTF_Types tdou = new JTF_Types(JTF_Types.DOU);
		/* total number of possible conversions */
	private final static int length = 23;
	protected JTF_Slot root = null;
	protected JTF_Types type_to = null;
	protected JTF_Types type_from = null;
	
	{
		JTF_Slot[] tmp = {
			multiplyFULL(tbyt, tcha),
			multiplyFULL(
				tsho, 
				unite(tbyt, tcha)),
			multiplyFULL(
				tcha,
				unite(tbyt, tsho)),
			multiplyFULL(
				tint,
				unite(tbyt, tsho, tcha)),
			multiplyFULL(
				tlon,
				unite(tbyt, tsho, tcha, tint)),
			multiplyFULL(
				tflo,
				unite(tbyt, tsho, tcha, tint, tlon)),
			multiplyFULL(
				tdou,
				unite(tbyt, tsho, tcha, tint, tlon, tflo))};
		root = merge(tmp);
	}
/*-----------------------------------------*/
		/**
		 * @return <i>JTF_Types</i> object corresponding to the type
		 * which the conversion is performed to
		 */
	public JTF_Types typeTo() {
		return type_to;
	}
		/**
		 * @return <i>JTF_Types</i> object corresponding to the type
		 * which the conversion is performed from
		 */
	public JTF_Types typeFrom() {
		return type_from;
	}
/*-----------------------------------------*/
		/** See <i>JTF_Slot</i> */
	public void setTo(int value_num) {
		super.setTo(value_num);
		root.setTo(value_num);
		type_to  = (JTF_Types)root.curSlot().subSlot(1);
		type_from = (JTF_Types)root.curSlot().subSlot(0);
	}
/*------------- Constructors ---------------*/
	public JTF_NarrPrimConv() {
		super(length);
	}
}
