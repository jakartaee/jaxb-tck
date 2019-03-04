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
/**
 * <i>Jmpp Test Factory</i>
 * 
 * This class realizes 1-dimensional sets of natural numbers and operations
 * on them. 
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)Range.java 1.8 02/04/01
 */
class Range {
		/** This class realizes interval. */
	class interval {
			/** left boundary of the interval */
		int lft = Integer.MIN_VALUE;
			/** right boundary of the interval */
		int rgt = Integer.MAX_VALUE;
		boolean tmp = false;
			/** @param val 	value to test
			 *  @return true - if <i>val</i> hits the interval,
			 *  false - if not */
		boolean contains(int val) {
			return val >= lft && val <= rgt;
		}
	
			/** @param s interval to compare with
			 *  @return true - if this interval and <i>s</i> are
			 *  equal */
		boolean equals(interval s) {
			return lft == s.lft && rgt == s.rgt;
		}
		
			/** @param lft	left boundary of the interval
			 *  @param rgt	right boundary of the interval */
		interval(int lft, int rgt) {
			this.lft = lft;
			this.rgt = rgt;
		}
	
			/** @param n both boundaries of the constructed 
			 *  interval are equal to this parameter */
		interval(int n) {
			this(n, n);
		}
		
		interval() {
		}
	}
		/** here all intervals of the range are stored */
	Vector range = new Vector();
		/** @param sr	interval which is added to the range */
	void add(interval sr) {
		if (sr.lft <= sr.rgt)
			range.addElement(sr);
	}
		/** adds an infinite interval */
	void add() {
		range.addElement(new interval());
	}
		/** adds a single point */
	void add(int num) {
		range.addElement(new interval(num));
	}
	
		/** adds an interval with the specified boundaries */
	void add(int lft, int rgt) {
		add(new interval(lft, rgt));
	}
		/** appends all intervals from the other range */
	void add(Range other) {
		for (int i=0; i<other.range.size(); i++) {
			interval sr = (interval)other.range.elementAt(i);
			add(sr.lft, sr.rgt);
		}	
	}
		/** replaces all intervals of this range with single interval
		 *  with the specified boundaries */
	void replace(int lft, int rgt) {
		if (range.size() != 1) {
			range.removeAllElements();
			range.addElement(new interval(lft, rgt));
		} else {
			interval sr = (interval)range.elementAt(0);
			sr.lft = lft;
			sr.rgt = rgt;
		}
	}
		/** replaces all intervals of this range with single point */
	void replace(int num) {
		if (range.size() != 1) {
			range.removeAllElements();
			range.addElement(new interval(0, num - 1));
		} else {
			interval sr = (interval)range.elementAt(0);
			sr.lft = 0;
			sr.rgt = num - 1;
		}
	}
		/** prints this range */
	void print() {
		for (int i=0; i<range.size(); i++) {
			interval sr = (interval)range.elementAt(i);
			System.out.print("["+sr.lft);
			if (sr.lft != sr.rgt)
				System.out.print("-"+sr.rgt);
			System.out.print("]");
			if (i < range.size() - 1)
				System.out.print(", ");
			else
				System.out.println();
		}
	}
		/** all elements of the range are sequentially numbered 
		 *  starting from zero; for example, range [5,10], [15,15] 
		 *  contains 2 intervals and 7 elements, wich are numbered
		 *  0 tru 6; this method returns the element with the specified
		 *  number */
	int getSpot(int num) {
		for (int i=0; i<range.size(); i++) {
			interval sr = (interval)range.elementAt(i);
			if (num < sr.rgt - sr.lft + 1)
				return sr.lft + num;
			num -= sr.rgt - sr.lft + 1;
		}
		return -1;
	}
		/** @return number of elements in the range */
	int length() {
		int len1 = 0;
		for (int i=0; i<range.size(); i++) {
			interval s = (interval)range.elementAt(i);
			len1 += s.rgt - s.lft + 1;
		}
		return len1;
	}
		/** @param val value to test
		 *  @return true - if <i>val</i> hits the range, false - if 
		 *  not */
	boolean contains(int val) {
		for (int i=0; i<range.size(); i++)
			if (((interval)range.elementAt(i)).contains(val))
				return true;
		return false;
	}
		/** @param min	left boundary of some interval 
		 *  @param max	right boundary of that interval
		 *  @return true - if this range has an intersection
		 *  with the interval, false - if not */
	boolean intersectsWith(int min, int max) {
		for (int i=0; i<range.size(); i++) {
			interval sr = (interval)range.elementAt(i);
			if (max >= sr.lft && min <= sr.rgt)
				return true;
		}
		return false;
	}
		/** merges intervals of this range where possible; for example :
		 *  [1,5] merged with [5,7] would result in [1,7] */
	void merge() {
		int ind = 1;
		int size = range.size();
		while (ind < size) {
			interval sr1 = (interval)range.elementAt(ind-1);
			interval sr2 = (interval)range.elementAt(ind);
			if (sr2.lft - sr1.rgt <= 1) {
				sr1.rgt = sr2.rgt;
				range.removeElementAt(ind);
				size = range.size();
				ind--;
			}
			ind++;
		}
	}
/*-------- Set operations --------*/
		/** auxiliary class to perform set operations */
	class bound {
		boolean side = false; // left - false, right - true
		boolean me   = false; // other Range - false, this - true
		int     num;
	}
		/** auxiliary class to perform set operations */
	class pointer {
		boolean	side = false;
		int     index = 0;
		int	size;
		Range   to;
		int value() {
			if (index >= size)
				return Integer.MAX_VALUE;
			interval s = (interval)to.range.elementAt(index);
			return (side ? s.rgt : s.lft);
			
		}
		bound next(Range this_one) {
			if (value() == Integer.MAX_VALUE)
				return null;
			bound res = new bound();
			res.me = (to == this_one);
			res.side = side;
			res.num = value();
			side = !side;
			if (!side)
				index++;
			return res;
		}
		pointer (Range r) {
			size = r.range.size();
			to = r;
		}
	}
		/** puts all elements of this an <i>other</i> ranges
		 *  to a <i>Vector</i> in increasing order, while saving
		 *  necessary information for each element (to which range
		 *  it belongs, whether is left boundary or right) */
	Vector lineUpWith(Range other) {
		this.merge();
		other.merge();
		pointer t = new pointer(this);
		pointer o = new pointer(other);
		Vector res = new Vector();
		for (int i=0; i < 2*(t.size + o.size); i++) {
			int num_t = t.value();
			int num_o = o.value();
			pointer src = t;
			if (num_t > num_o || 
			    num_t == num_o && t.side && !o.side)
				src = o;
			bound bnd = src.next(this);
			if (bnd != null)
				res.addElement(bnd);
		}
		return res;
	}
	
		/** removes duplicated intervals from this range */
	void removeSame() {
		for (int i=0; i<range.size()-1; i++) {
			interval pattern = (interval)range.elementAt(i);
			for (int j=i+1; j<range.size(); j++ ) {
				interval match = (interval)range.elementAt(j);
				if (match.equals(pattern))
					match.tmp = true;
			}
		}
		int right_index = range.size()-1;
		int cur_index = 0;
		while (cur_index <= right_index) {
			interval s = (interval)range.elementAt(cur_index);	
			if (s.tmp) {
				range.removeElementAt(cur_index);
				cur_index--;
				right_index = range.size()-1;	
			}
			cur_index++;
		}
	}
		/** @param var - converted boolean value
		 *  @return <code>(var ? 1 : 0)</code> */
	int indx(boolean var) {
		return (var ? 1 : 0);
	}
		/** @return new range which is intersection of this and 
		 *  <i>other</i> ranges */
	Range intersect(Range other) {
		Vector bounds = this.lineUpWith(other);
		Range res = new Range();
		boolean[] last_side = new boolean[2];
		bound cur_bnd = (bound)bounds.elementAt(0);
		last_side[indx(cur_bnd.me)] = false;
		last_side[(indx(cur_bnd.me) + 1) % 2] = true;
		int cur_left = 0;
		for (int i=1; i < bounds.size(); i++) {
			cur_bnd = (bound)bounds.elementAt(i);
			int ind = indx(cur_bnd.me);
			if (!cur_bnd.side) {
				if (!last_side[(ind + 1) % 2]) 
					cur_left = cur_bnd.num;
			} else {
				if (!last_side[(ind + 1) % 2])
					res.add(cur_left, cur_bnd.num);
			}
			last_side[ind] = cur_bnd.side;
		}
		res.removeSame();
		return res;
	}
		/** @return new range which is union of this and 
		 *  <i>other</i> ranges */
	Range unite(Range other) {
		Vector bounds = this.lineUpWith(other);
		Range res = new Range();
		boolean owner = ((bound)bounds.elementAt(0)).me;
		int cur_left  = ((bound)bounds.elementAt(0)).num;
		int between_cnt = 0;
		boolean find_owner = false;
		for (int i=1; i < bounds.size(); i++) {
			bound cur_bnd = (bound)bounds.elementAt(i);
			if (find_owner) {
				find_owner = false;
				owner = cur_bnd.me;
				cur_left = cur_bnd.num;
				continue;
			}
			if (cur_bnd.me == owner) {
				if (between_cnt % 2 == 0) {
					res.add(cur_left, cur_bnd.num);
					find_owner = true;
				} else
					owner = !owner;
				between_cnt = 0;
			} else
				between_cnt++;
		}
		res.removeSame();
		return res;
	}
		/** @return new range which is a result of subtraction of
		 *  <i>other</i> range from this range */ 
	Range subtract(Range other) {
		other.merge();
		Range complement = new Range();
		int cur_left = Integer.MIN_VALUE;
		for (int i=0; i<other.range.size(); i++) {
			interval s = (interval)other.range.elementAt(i);
			complement.add(cur_left, s.lft - 1);
			cur_left = s.rgt + 1;
		}
		complement.add(cur_left, Integer.MAX_VALUE);
		Range res = this.intersect(complement);
		res.removeSame();
		return res;
	}
		/** @return true - if this and <i>other</i>ranges have the
		 *  same set of intervals, false - if not */
	boolean equals(Range other) {
		this.merge();
		other.merge();
		if (this.range.size() != other.range.size())
			return false;
		for (int i=0; i<this.range.size(); i++) {
			interval s_this = (interval)this.range.elementAt(i);
			interval s_other = (interval)other.range.elementAt(i);
			if (s_this.lft != s_other.lft || 
			    s_this.rgt != s_other.rgt)
				return false;
		}
		return true;
	}
/*--------------------------------*/
	Range() {}
		/** constructs new range consisting of single interval with the
		 *  specified boundaries */
	Range(int lft, int rgt) {
		super();
		add(lft, rgt);
	}
		/** constructs new range consisting of single point */
	Range(int num) {
		super();
		add(num);
	}
}
