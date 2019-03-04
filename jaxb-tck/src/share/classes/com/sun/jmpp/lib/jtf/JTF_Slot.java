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
import java.io.PrintStream;
import java.lang.ArithmeticException;
/**
 * <i>Jmpp Test Factory</i>
 *
 * This a base class for all other slots. It provides : <ul>
 *	<li> implementation of operations on slots as sets of values
 *		(multiplication, union, subtraction, intersection);
 *	<li> means to merge slots of different kinds;
 *	<li> means to define and generate combinations of values of several
 *		slots;
 *	<li> means to create slots with subsets of values;
 *	<li> automatic accumulation of declarations which must be placed into
 *		the genarated test file </ul>
 * 
 * @author Konstantin S. Bobrovsky
 * @version @(#)JTF_Slot.java 1.17 03/07/23
 */
public class JTF_Slot implements Cloneable {
		/** contains declarations of the class level */
	public JTF_StrHolder cls_decl = new JTF_StrHolder();
		/** contains declarations of the class member level */
	public JTF_StrHolder mmb_decl = new JTF_StrHolder();
		/** contains declarations of the local level */
	public JTF_StrHolder loc_decl = new JTF_StrHolder();
		/** index of class level decl string in the <i>res</i> array */
	public final static int context0	= 0;
		/** index of class member level decl string */
	public final static int context1	= 1;
		/** index of local level decl string */
	public final static int context2	= 2;
		/** total number of decl levels */
	public final static int contexts_total	= 3;
	public final static boolean ACC_DECL	= true;
		/** contains i'th value of the slot after <i>setTo(i)</i>
		 *  is invoked
		 */
	public final static int NO_VALUE = Integer.MAX_VALUE;
	public String[] res = null;
		/** shortcuts to <i>res[contexts_total + 0..4]</i> */
	public String res0  = "", res1  = "", res2  = "", res3 = "", res4 = "";
		/** shortcuts to <i>res[0..2]</i> */
	public String decl0 = "", decl1 = "", decl2 = "";
		/** counts instances of <i>JTF_Slot</i> and it's subclasses */
	protected static volatile int inst_num = -1;
		/** used to parse strings and get corresponding <i>Range</i> */
	protected StrParser sp = new StrParser();
		/** number of this instance */
	protected int this_num = 0;
		/** index of current slot's value (in <i>data</i> array) */
	protected int val_num = NO_VALUE;
		/** number of current slot's value */
	protected int seq_num = 0;
		/** total number of values in the slot */
	protected int val_total = 0;
		/** contains information about what subset of values the
		 *  slot can take */
	protected Range rng = new Range();
		/** place to store slots which constitute this slot;
		 *  if this slot has children then it is a product of them,
		 *  and has been created using one of <i>multiplyXXX</i> 
		 *  methods */
	protected JTF_Slot[] children = null;
		/** slots which were merged to get this slot, and the number
		 *  of values of this slot is the sum of numbers of values of
		 *  it's components */
	protected JTF_Slot[] components = null;
		/** index of the current slot in the <i>components</i> array;
		 *  no slot can have children and components at the same time */
	protected int cur_slot = -1;
		/** controls how declarations are handled after each call
		 *  to the <i>setTo</i> method : true - they are accumulated,
		 *  (usually in positive tests), false - they are replaced
		 *  (usually in negative tests) */
	protected boolean accum_decl = false;
	private static final PrintStream out = System.out;

		/** Traverses the tree of children and components and sets
		 *  <i>accum_decl</i> flag */
	public void accumulateDecls() {
		if (children == null) {
			if (components == null) {
				accum_decl = true;
			} else {
				for (int i=0; i<components.length; i++)
					components[i].accumulateDecls();
			}
		} else  {
			for (int i=0; i<children.length; i++)
				children[i].accumulateDecls();
		}
	}
		/** @return the current component slot */
	public JTF_Slot curSlot() {
		if (cur_slot < 0)
			return null;
		return components[cur_slot];
	}
		/** @return the number of the current slot's value */
	public int curVal() {
		return val_num;
	}
		/** @return the class level declarations */
	public String decl0() {
		return res[0];
	}
		/** @return the class member level declarations */
	public String decl1() {
		return res[1];
	}
		/** @return the local level declarations */
	public String decl2() {
		return res[2];
	}
		/** Generates next combination of values of slots from
		 *  the children's tree.
		 *
		 *  @return true - if last possible combination not
		 *  reached yet, false - if it's reached */
	public boolean genNext() {
		if (seq_num >= val_total) {
			val_num = NO_VALUE;
			return false;
		}
		setTo(seq_num++);
		gatherDecls();
		return true;
	}
	
	public void reset() {
		seq_num = 0;
		val_num = NO_VALUE;
	}
//-----------------
		/** Intersects (in mathematical sense) the sets of values of
		 *  slots of the same class.
		 *
		 *  @param slots slots whose sets of values are intersected
		 *  @return	 new slot, whose set of values is an
		 *  intersection of the input sets of values */
	public static JTF_Slot intersect(JTF_Slot[] slots) {
		if (!areSame(slots)) {
			out.println("Error : Can't intersect slots");	
			return null;
		}
		JTF_Slot res = null;
		try {
			res = (JTF_Slot)slots[0].getClass().newInstance();
		} catch (InstantiationException e) {
			out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			out.println(e.getMessage());
		}
		res.rng = slots[0].rng;
		for (int i=1; i<slots.length; i++)
			res.rng = res.rng.intersect(slots[i].rng);
		res.val_total = res.rng.length();
		res.copyFields(slots[0]);
		return res;
	}
	public static JTF_Slot intersect(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return intersect(s);
	}
	public static JTF_Slot intersect(JTF_Slot s1, JTF_Slot s2, JTF_Slot s3){
		JTF_Slot[] s = {s1, s2, s3};
		return intersect(s);
	}
//-----------------
		/** generates combinations of values in a cycle (after the 
		 *  last one is generated, generation doesn't stop and starts
		 *  from the first) */
	public void loopNext() {
		if (seq_num >= val_total)
			seq_num = 0;
		setTo(seq_num++);
		gatherDecls();
	}
		/** @return total number of values in the slot */
	public int length() {
		return val_total;
	}
//-----------------
		/** merges sets of values of the input slots (they are simply
		 *  appended to the set of the result's values); slots can be 
		 *  of different class types
		 *
		 *  @param slots slots whose sets of values are merged;
		 *  @return      new slot with merged set of values */
	public static JTF_Slot merge(JTF_Slot[] slots) {
		int values_total = 0;
		for (int i=0; i<slots.length; i++)
			values_total += slots[i].length();
		JTF_Slot result = new JTF_Slot(values_total);
		result.components = slots;
		result.cur_slot = 0;
		return result;
	}
	public static JTF_Slot merge(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return merge(s);
	}
	public static JTF_Slot merge(JTF_Slot s1, JTF_Slot s2, JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return merge(s);
	}
	public static JTF_Slot merge(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3, 
				     JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return merge(s);
	}
//-----------------
		/** merges sets of values of the input slots (they are simply
		 *  appended to the set of the result's values); slots must be
		 *  of the same class type; result can be safely casted to
		 *  the arguments' class type
		 *
		 *  @param slots slots whose sets of values are merged;
		 *  @return      new slot with merged set of values */
	public static JTF_Slot mergeSame(JTF_Slot[] slots) {
		if (!areSame(slots)) {
			out.println("Error : Can't merge slots");	
			return null;
		}
		JTF_Slot result = null;
		try {
			result = (JTF_Slot)slots[0].getClass().newInstance();
		} catch (InstantiationException e) {
			out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			out.println(e.getMessage());
		}
		result.rng = slots[0].rng;
		for (int i=1; i<slots.length; i++)
			result.rng.add(slots[i].rng);
		result.val_total = result.rng.length();
		result.copyFields(slots[0]);
		return result;
	}
	public static JTF_Slot mergeSame(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return mergeSame(s);
	}
	public static JTF_Slot mergeSame(JTF_Slot s1, 
					 JTF_Slot s2,
					 JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return mergeSame(s);
	}
	public static JTF_Slot mergeSame(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3, 
				     JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return mergeSame(s);
	}
//-----------------
		/** multiplies (in mathematical sense) sets of values of the
		 *  input slots so that number of dimensions of the producted
		 *  set is a sum of these numbers of the input sets; suffix
		 *  "FULL" means that the multiplication is performed without
		 *  any limitations, and number of values of the result set is 
		 *  merely a product of capacities of the input sets.
		 *
		 *  @param slots slots whose sets of values are multiplied
		 *  @return      new slot - result of the multiplication */
	public static JTF_Slot multiplyFULL(JTF_Slot[] slots) {
		JTF_Slot result = new JTF_Slot();
		result.children = slots;
		result.val_total = 1;
		for (int i=0; i<result.children.length; i++)
			result.val_total *= result.children[i].length();
		result.rng.add(0, result.val_total-1);
		return result;
	}
	
	public static JTF_Slot multiplyFULL(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return multiplyFULL(s);
	}
	public static JTF_Slot multiplyFULL(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return multiplyFULL(s);
	}
	public static JTF_Slot multiplyFULL(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3, 
					    JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return multiplyFULL(s);
	}
	public static JTF_Slot multiplyFULL(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3, 
					    JTF_Slot s4, 
					    JTF_Slot s5) {
		JTF_Slot[] s = {s1, s2, s3, s4, s5};
		return multiplyFULL(s);
	}
//-----------------
		/** multiplies (in mathematical sense) sets of values of the
		 *  input slots so that number of dimensions of the producted
		 *  set is a sum of these numbers of the input sets; suffix
		 *  "PSEUDO_FULL" means the following : the combinations of
		 *  sets of values of the input slots are chosen so that their
		 *  total number is minimal, while every slot takes it's every
		 *  value when all combinations are generated; number of values
		 *  of the result set is the maximum capacity of the input sets.
		 *
		 *  @param slots slots whose sets of values are multiplied
		 *  @return      new slot - result of the multiplication */
	public static JTF_Slot multiplyPSEUDO_FULL(JTF_Slot[] slots) {
		JTF_Slot result = new JTF_Slot();
		result.children = slots;
		result.val_total = result.children[max(result.children)].length();
		int[] digits = new int[result.children.length];
		int[] radix  = new int[result.children.length];
		for (int j=0; j<result.children.length; j++)
			radix[j] = result.children[j].length();
		for (int i=0; i<result.val_total; i++) {
			for (int j=0; j<result.children.length; j++)
				digits[j] = i % result.children[j].length();
			result.rng.add(assembleNum(digits, radix));
		}
		return result;
	}
	public static JTF_Slot multiplyPSEUDO_FULL(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return multiplyPSEUDO_FULL(s);
	}
	public static JTF_Slot multiplyPSEUDO_FULL(JTF_Slot s1, 
						   JTF_Slot s2, 
						   JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return multiplyPSEUDO_FULL(s);
	}
	
	public static JTF_Slot multiplyPSEUDO_FULL(JTF_Slot s1, 
						   JTF_Slot s2, 
						   JTF_Slot s3, 
						   JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return multiplyPSEUDO_FULL(s);
	}
	
	public static JTF_Slot multiplyPSEUDO_FULL(JTF_Slot s1, 
						   JTF_Slot s2, 
						   JTF_Slot s3, 
						   JTF_Slot s4, 
						   JTF_Slot s5) {
		JTF_Slot[] s = {s1, s2, s3, s4, s5};
		return multiplyPSEUDO_FULL(s);
	}
//-----------------
		/** multiplies (in mathematical sense) sets of values of the
		 *  input slots so that number of dimensions of the producted
		 *  set is a sum of these numbers of the input sets; suffix
		 *  "UNIQ" means the following : combinations of the values of
		 *  the input sets are chosen so that none two of them are
		 *  equal; here two combinations are equal if one of them can
		 *  be obtained from another just by reordering indices of 
		 *  values which consitute the combination.
		 *
		 *  @param slots slots whose sets of values are multiplied
		 *  @return      new slot - result of the multiplication */
	public static JTF_Slot multiplyUNIQ(JTF_Slot[] slots) {
		JTF_Slot result = new JTF_Slot();
		result.children = slots;
		Top t = new Top();
		t.top = slots[0].length();
		t.arr =  new int[slots.length];
		t.arr_len = t.arr.length;
		t.range = result.rng;
		makeRange(0, 0, t);
		result.rng.merge();
		result.val_total = result.rng.length();
		return result;
	}
	public static JTF_Slot multiplyUNIQ(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return multiplyUNIQ(s);
	}
	public static JTF_Slot multiplyUNIQ(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return multiplyUNIQ(s);
	}
	public static JTF_Slot multiplyUNIQ(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3, 
					    JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return multiplyUNIQ(s);
	}
	public static JTF_Slot multiplyUNIQ(JTF_Slot s1, 
					    JTF_Slot s2, 
					    JTF_Slot s3, 
					    JTF_Slot s4, 
					    JTF_Slot s5) {
		JTF_Slot[] s = {s1, s2, s3, s4, s5};
		return multiplyUNIQ(s);
	}
//-----------------
		/** makes the slot take the value with the specified
		 *  sequential number; "sequential" means the following :
		 *  if slot has 10 values (numbered 0 thru 9) and we create
		 *  new slot whose set of values is a subset (say, values with
		 *  odd numbers : 1, 3, 5, 7, 9), then <i>setTo(4)</i> operation
		 *  applied to the subslot will make it take the 9'th value. 
		 *  @param value_num	sequential number of new slot's value */
	public void setTo(int value_num) {
		int num = rng.getSpot(value_num % val_total);
		if (num == -1) {
			out.println("Error: can't set slot to neg. value number ");
			return;
		}
		val_num = num;
		if (components != null) {
			if (children != null) {
				out.println("Error : slot has both components and children");
				return;
			}
			int comp_ind = 0;
			while(comp_ind < components.length &&
			      num >= components[comp_ind].length()) {
				num -= components[comp_ind].length();
				comp_ind++;
			}
			cur_slot = comp_ind;
			if (num >= components[cur_slot].length()) {
				out.println("Internal error 0");
				return;
			}
			components[cur_slot].setTo(num);
		} else {
			if (children == null)
				return;
			if (children.length == 1) {
				children[0].setTo(num);
				return;
			}
			int[] radix = new int[children.length];
			for (int i=0; i<children.length; i++)
				radix[i] = children[i].length();
			int[] digits = parseNum(num, radix);
			for (int i=0; i<digits.length; i++)
				children[i].setTo(digits[i]);
		}
	}
	/** creates a string wich resembles this slot's tree state
	 *  @return string representation of this slot's tree state 
	 */
	public String snapshotSlotTreeState() {
		String state = doSnapshot(this);
		int length = state.length();
		for (int i=length-1; i>=1; i--) {
			if (state.charAt(i) == '0')
				state = state.substring(0, i);
			else
				break;
		}
		return state;
	}
	public JTF_Slot subSlot(int num) {
		return children[num];
	}
		/** substitutes all occurances of substring within source string
		 *  with the specified string
		 *
		 * @param src	source string
		 * @param what	substring which is being replaced
		 * @param with	string to replace the substring with */
	public static String subst(String src, String what, String with) {
		int ind = src.indexOf(what);
		if (ind != -1) {
			String pre = src.substring(0, ind);
			src = pre + with + src.substring(ind + what.length(),
							 src.length());
			src = subst(src, what, with);
		}
		return src;
	}
//-----------------
		/** performs subtraction (in mathematical sense) of sets :
		 *  sets of values of the second and further arguments
		 *  are subtracted from the first one; all arguments are
		 *  required to have the same class type
		 *  
		 *  @param slots	slots whose sets of values are to be
		 *			subtracted
		 *  @return		new slot - result of subtraction */
	public static JTF_Slot subtract(JTF_Slot[] slots) {
		if (!areSame(slots)) {
			out.println("Error: Can't subtract slots");	
			return null;
		}
		JTF_Slot res = null;
		try {
			res = (JTF_Slot)slots[0].getClass().newInstance();
		} catch (InstantiationException e) {
			out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			out.println(e.getMessage());
		}
		res.rng = slots[0].rng;
		for (int i=1; i<slots.length; i++)
			res.rng = res.rng.subtract(slots[i].rng);
		res.val_total = res.rng.length();
		res.copyFields(slots[0]);
		return res;
	}
	public static JTF_Slot subtract(JTF_Slot s1, JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return subtract(s);
	}
	public static JTF_Slot subtract(JTF_Slot s1, JTF_Slot s2, JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return subtract(s);
	}
//-----------------
		/** unites (in mathematical sense) sets of values of the
		 *  arguments; all arguments are required to have the same
		 *  class type
		 *  
		 *  @param slots	slots whose sets of values are to be
		 *			united
		 *  @return		new slot - result of union */
	public static JTF_Slot unite(JTF_Slot[] slots) {
		if (!areSame(slots)) {
			out.println("Error: can't unite slots");	
			return null;
		}
		JTF_Slot res = null;
		try {
			res = (JTF_Slot)slots[0].getClass().newInstance();
		} catch (InstantiationException e) {
			out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			out.println(e.getMessage());
		}
		res.rng = slots[0].rng;
		for (int i=1; i<slots.length; i++)
			res.rng = res.rng.unite(slots[i].rng);
		res.val_total = res.rng.length();
		res.copyFields(slots[0]);
		return res;
	}
	public static JTF_Slot unite(JTF_Slot s1, 
				     JTF_Slot s2) {
		JTF_Slot[] s = {s1, s2};
		return unite(s);
	}
	public static JTF_Slot unite(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3) {
		JTF_Slot[] s = {s1, s2, s3};
		return unite(s);
	}
	public static JTF_Slot unite(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3, 
				     JTF_Slot s4) {
		JTF_Slot[] s = {s1, s2, s3, s4};
		return unite(s);
	}
	public static JTF_Slot unite(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3, 
				     JTF_Slot s4, 
				     JTF_Slot s5) {
		JTF_Slot[] s = {s1, s2, s3, s4, s5};
		return unite(s);
	}
	public static JTF_Slot unite(JTF_Slot s1, 
				     JTF_Slot s2, 
				     JTF_Slot s3, 
				     JTF_Slot s4, 
				     JTF_Slot s5, 
				     JTF_Slot s6) {
		JTF_Slot[] s = {s1, s2, s3, s4, s5, s6};
		return unite(s);
	}
/* =========== protected methods =========== */
		/** copies necessary fields from the argument; this method is
		 *  called from <i>unite</i>, <i>subtract</i>, <i>subtract</i>
		 *  and <i>mergeSame</i> methods since their return values
		 *  are supposed to be casted from <i>JTF_Slot</i> to one of the
		 *  subclasses <i>T</i>, and be used as an instance of 
		 *  <i>T</i>; to achive this <i>T</i> must override 
		 *  <i>copyFields</i> to copy fields which are necessary for
		 *  normal class' behavior
		 *
		 *  @param from	slot to copy fields from */
	protected void copyFields(JTF_Slot from) {}

		/** substitutes macros in the ss by the corresponding strings from
		 *  <i>params</i>. If a corresponding string is not found (number
		 *  of macros is greater than <i>params.length</i>) then all unmatched
		 *  macros are replaced with the empty string. Macros have the same
		 *  syntax as Jmpp template macros.
		 *
		 * @param ss string to be searched for macros
		 * @param params values to substitute macros by
		 * @return string resulting from macro substitution within <i>ss</i> */
	public static String substituteMacro(String ss, String[] params) {
		class HandyString {
			final static char EOL = '\uFFFF';
			final static int START=100, FINISH=101, ERROR=102;
			private StringBuffer buf;
			private int length;
			int	pos		= 0;
			int	back_slash_cnt	= 0;
			int	macro_cnt	= 0;
			String[] params;
			StringBuffer result = new StringBuffer();
			HandyString(String s) {
				buf = new StringBuffer(s);
				length = buf.length();
			}
			char getc() {
				char ch;
				if (pos >= length)
					return EOL;
				ch = buf.charAt(pos);
				if (ch != '\\') {
					pos++;
					return ch;
				} else {
					back_slash_cnt = 0;
					while (ch == '\\') {
						back_slash_cnt++;
						pos++;
						if (pos >= length)
							break;
						ch = buf.charAt(pos);
					}
					return '\\';
				}
			}
			void ungetc() {
				if (pos == 0)
					return;
				if (buf.charAt(pos-1) == '\\')
					pos -= back_slash_cnt;
				else
					pos--;
			}
			int move(int state, char ch) {
				switch (state) {
				case START:
					switch (ch) {
					case '@':
						return 0;
					case '\\':
						for (int i=0; i<(back_slash_cnt/2)*2; i++)
							result.append('\\');
						if (back_slash_cnt % 2 == 0)
							return 1;
						else {
							char c = getc();
							if (c != '@')
								result.append('\\');
							if (c != EOL)
								ungetc();
							return 2;
						}
					case EOL:
						return FINISH;
					default:
						result.append(ch);	
						return START;
					}
				case 0:
					if (ch == '(')
						return 3;
					if (Character.isJavaIdentifierPart(ch))
						return 4;
					return ERROR;
				case 1:
					switch (ch) {
					case '@':
						return 0;
					case EOL:
						return FINISH;
					default:
						result.append(ch);	
						return START;
					}
				case 2:
					if (ch == EOL)
						return FINISH;
					result.append(ch);	
					return START;
				case 3:
					if (Character.isJavaIdentifierPart(ch))
						return 5;
					return ERROR;
				case 4:
					if (Character.isJavaIdentifierPart(ch))
						return 4;
					if (macro_cnt < params.length) {
						result.append(params[macro_cnt]);
						macro_cnt++;
					}
					if (ch == EOL)
						return FINISH;
					ungetc();
					return START;
				case 5:
					if (Character.isJavaIdentifierPart(ch))
						return 5;
					if (ch == ')') {
						if (macro_cnt < params.length) {
							result.append(params[macro_cnt]);
							macro_cnt++;
						}
						return START;
					}
					return ERROR;
				case ERROR:;
				default:;
					throw new Error("Bad macro syntax");
				}
			}
			String ParseAndReplace(String[] params) {
				char ch;
				int state = START;
				this.params = params;
				do {
					ch = getc();
					state = move(state, ch);
				} while (state != FINISH);
				return result.toString();
			}
		}
		if (ss == null || ss.length() == 0 || params == null)
			return ss;
		return new HandyString(ss).ParseAndReplace(params);
	}
/* =========== private methods ============= */
	private static boolean areSame(JTF_Slot[] slots) {
		String name = slots[0].getClass().getName();
		for (int i=0; i<slots.length; i++)
			if (!name.equals(slots[i].getClass().getName()) ||
			    slots[i].components != null)
				return false;
		return true;
	}
	private static int assembleNum(int[] digits, int[] radix) {
		if (digits.length == 1) 
			return digits[0];
		int[] weights = new int[radix.length];
		weights[weights.length-1] = radix[weights.length-1];
		for (int i=radix.length-1; i>1; i--)
			weights[i-1] = weights[i] * radix[i-1];
		int num = digits[digits.length-1];
		for (int i=0; i<digits.length-1; i++)
			num += digits[i]*weights[i+1];
		return num;
	}
	private static int assembleNum(int[] digits, int radix) {
		int result = 0;
		int base = 1;
		for (int i=digits.length-1; i>=0; i--) {
			result += digits[i]*base;
			base *= radix;
		}
		return result;
	}

	/** recursively traverses the input slot's tree, constructing 
	 *  string which is a concatination of string representations
	 *  of value numbers of the tree's leaves
	 *  @param entry slot to traverse the tree of
	 *  @return constructed string
	 */
	private String doSnapshot(JTF_Slot entry) {
		String accum = "";
		int cur_val;
		if (entry == null) {
			out.println("Internal error 2");
			return "";
		}
		if (entry.children != null)
			for (int i=0; i<entry.children.length; i++)
				accum += doSnapshot(entry.children[i]);
		else {
			if (entry.components == null) {
				accum += mapIntToString(entry.curVal());
			}
			else {
				accum += mapIntToString(entry.cur_slot);
				accum += doSnapshot(entry.curSlot());
			}	
		}
		return accum;
	}

	private void gatherDecls() {
		if (children == null) {
			if (res != null) {
				if (accum_decl) {
					cls_decl.addUnique(res[0]);
					mmb_decl.addUnique(res[1]);
					loc_decl.addUnique(res[2]);
				} else {
					cls_decl.setTo(res[0]);
					mmb_decl.setTo(res[1]);
					loc_decl.setTo(res[2]);
				}
			}
			if (components != null) {
				curSlot().gatherDecls();
				cls_decl.setTo(curSlot().cls_decl.toString());
				mmb_decl.setTo(curSlot().mmb_decl.toString());
				loc_decl.setTo(curSlot().loc_decl.toString());
			}
		} else {
			cls_decl.empty();
			mmb_decl.empty();
			loc_decl.empty();
			for (int i=0; i<children.length; i++) {
				children[i].gatherDecls();
				cls_decl.append(children[i].cls_decl);
				mmb_decl.append(children[i].mmb_decl);
				loc_decl.append(children[i].loc_decl);
			}
		}
	}
	private static void makeRange(int bott, int pos, Top t) {
		if (pos == t.arr_len-1) {
			t.arr[pos] = bott;
			int bot_val = assembleNum(t.arr, t.top);
			t.arr[pos] = t.top-1;
			int top_val = assembleNum(t.arr, t.top);
			t.range.add(bot_val, top_val);
		} else {
			for (int i=bott; i<t.top; i++) {
				t.arr[pos] = i;
				makeRange(i, pos+1, t);
			}
		}
	}
	/** maps int values to string values 
	 *  @param val int value to be mapped
	 *  @return String representation of the val
	 */	
	private String mapIntToString(int val) {
		if (val < 0) {
			out.println("Internal error 1");
			return null;
		}
		if (val < 10)
			return String.valueOf(val);
		if (val > 33)
			return String.valueOf(val) + "_";
		return String.valueOf((char)('a' - 10 + val));
	}
	private static int max(JTF_Slot[] slots) {
		int max_ind = 0;
		int mx = Integer.MIN_VALUE;
		for (int i=0; i<slots.length; i++)
			if (slots[i].length() > mx) {
				mx = slots[i].length();
				max_ind = i;
			}
		return max_ind;
	}
	private static int[] parseNum(int num, int[] radix) {
		int len = radix.length;
		int[] weights = new int[len];
		weights[0] = radix[len-1];
		try {
			for (int i=len-2; i>0; i--)
				weights[len-i-1] = radix[i]*weights[len-i-2];
		} catch (ArithmeticException e) {
			out.println("Error: too many combinations");
			return null;
		}
				
		int[] digits = new int[len];
		digits[0] = num / weights[len-2];
		int tail = digits[0] * weights[len-2];
		for (int i=len-2; i>=1; i--) {
			digits[len-i-1] = (num - tail) / weights[i-1];
			tail += digits[len-i-1] * weights[i-1];
		}
		digits[len-1] = num - tail;
		return digits;
	}
/* =========== Constructors =========== */
		/** creates new slot and increments instances counter */
	public JTF_Slot() {
		synchronized(JTF_Slot.class){
			this_num = ++inst_num;
		}	
	}
		/** creates new slot with the specified number of values
		 *  
		 *  @param val_total	number of values */
	public JTF_Slot(int val_total) {
		this();
		this.val_total = val_total;
		rng.add(0, val_total-1);
	}
		/** creates new slot with the specified ranges of numbers of
		 *  values
		 *
		 *  @param combs	string that specifies the ranges;
		 *  such strings are normally defined in each subclass */
	public JTF_Slot(String combs) {
		this();
		rng = sp.parse(combs);
		val_total = rng.length();
	}
}
// End of JTF_Slot
/* =========== Auxiliary classes =========== */
class Top {
	protected int top;
	protected int[] arr;
	protected int arr_len;
	protected Range range;
}

class StrParser {
	private int pos;
	private String str;
	private int readInt() {
		StringBuffer buf = new StringBuffer();
		char ch;
		while (Character.isDigit(ch = str.charAt(pos))) {
			buf.append(ch);
			pos++;
			if (pos >= str.length())
				break;
		}
		return Integer.parseInt(buf.toString());
	}
	private void readAndSave2Ints(Range save_to) {
		int lft = readInt();
		if (pos < str.length() && str.charAt(pos) == '-') {
			pos++;
			save_to.add(lft, readInt());
		}
		else
			save_to.add(lft, lft);
	}
	Range parse(String str_to_parse) {
		str = str_to_parse;
		Range res = new Range();
		pos = 0;
		if (str.charAt(pos) == '*') {
			res.add();
			return res;
		}
		readAndSave2Ints(res);
		pos++;
		while (pos < str.length()) {
			readAndSave2Ints(res);
			pos++;
		}
		return res;
	}
	void setStrToParse(String str) {
		this.str = str;
		pos = 0;
	}
}
class JTF_StrHolder {
	Vector buf = new Vector();
	void addUnique(String s) {
		String s1 = s.trim();
		for (int i=0; i<buf.size(); i++) {
			String next = (String)buf.elementAt(i);
			if (next.trim().equals(s1))
				return;
		}
		buf.addElement(s);
	}
	void add(String s) {
		if (s.trim().length() > 0)
			buf.addElement(s);
	}
	void setTo(String s) {
		buf.removeAllElements();
		add(s);
	}
	public String toString() {
		String res = "";
		for (int i=0; i<buf.size(); i++)
			res += (String)buf.elementAt(i);
		return res;
	}
	void append(JTF_StrHolder other) {
		for (int i=0; i<other.buf.size(); i++) {
			String other_str = (String)other.buf.elementAt(i);
			this.buf.addElement(other_str);
		}
	}
	void empty() {
		buf.removeAllElements();
	}
}
