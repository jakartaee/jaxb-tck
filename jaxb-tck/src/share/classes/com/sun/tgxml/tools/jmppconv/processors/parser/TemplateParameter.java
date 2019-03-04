/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.sun.jmpp.JmppException;

/**
 * This class represents a template parameter.
 *
 * @author Konstantin S. Bobrovsky
 */
public class TemplateParameter {

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Inner classes */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * This class represents a template parameter value.
     */
    public static class Value {
        private Object content;
        private String mnemo;
        private int sortKey;
        private int weight;
        private char symbol;
        

        /**
         * Constructor.
         *
         * @param content  value content
         * @param mnemo    value mnemonics
         * @param weight   defines sort order of values in <code>varOrder</code>
         * @param sort_key defines sort order of values in name generation
         *     calculation
         * @param symbol   value symbol used in test case variant name
         *     generation
         */
        public Value(Object content,
                     String mnemo,
                     int    weight,
                     int    sort_key,
                     char   symbol)
        {
            setContent(content);
            setMnemo(mnemo);
            setWeight(weight);
            setSortKey(sort_key);
            setSymbol(symbol);
        }

        /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
        /* Set/get methods */
        /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

        public Object getContent() {
            return content;
        }

        public String getMnemo() {
            return mnemo;
        }

        public int getSortKey() {
            return sortKey;
        }

        public int getWeight() {
            return weight;
        }

        public char getSymbol() {
            return symbol;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public void setMnemo(String mnemo) {
            this.mnemo = mnemo;
        }

        public void setSortKey(int sort_key) {
            this.sortKey = sort_key;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setSymbol(char symbol) {
            this.symbol = symbol;
        }

        /**
         * Updates given set of attributes (assumed to be current test
         * case' attributes). This default implementation does nothing -
         * override in subclasses to set, for example, target specs for
         * current test case.
         *
         * @param tc_attrs the set of attributes of current test case
         */
        public void updateTestcaseAttributes(AttributeSet tc_attrs) {}

        /**
         * Updates given set of attributes (assumed to be current test
         * group's attributes). This default implementation does nothing -
         * override in subclasses to set, for example, target specs for
         * current test group.
         *
         * @param tg_attrs the set of attributes of current test case
         */
        public void updateTestGroupAttributes(AttributeSet tg_attrs) {}
    }

    /**
     * <code>java.util.Comparator</code> implementation to compare
     * template parameter values
     */
    protected static class ValueComparator implements Comparator {
        public final static boolean BY_SORT_KEY = false;
        public final static boolean BY_WEIGHT = true;

        private boolean cmpByWeight;

        /**
         * Constructor
         *
         * @param cmp_by_weight whether to compare values by their weights
         *   or by their sort keys.
         */
        public ValueComparator(boolean cmp_by_weight) {
            this.cmpByWeight = cmp_by_weight;
        }

        /**
         * Compares two values.
         *
         * @return -1, 0 or 1 if value o1 is less than, equal to or greater than
         *     the value o2
         */
        public int compare(Object o1, Object o2) {
            Value val1 = (Value)o1;
            Value val2 = (Value)o2;
            int i1;
            int i2;

            if (cmpByWeight) {
                i1 = val1.getWeight();
                i2 = val2.getWeight();
            } else {
                i1 = val1.getSortKey();
                i2 = val2.getSortKey();
            }
            long diff = (long)i1 - (long)i2;
            return (diff < 0 ? -1 : (diff > 0 ? 1 : 0));
        }

        public boolean equals(Object obj) {
            return this == obj;
        }
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Fields */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    private String name;
    private Value[] values;
    private Value[] valuesByWeight;
    private boolean isStrong;
    private int weight;
    private int sortKey;
    private Value curVal;
    private HashMap mnemo2value = new HashMap();

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Constructors */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * Constructor
     *
     * @param name   parameter name 
     * @param values set of possible values of this parameter 
     */
    public TemplateParameter(String name, Value[] values) {
        this(name, values, true, 0, 0);
    }

    /**
     * Constructor
     *
     * @param name       parameter name
     * @param values     set of possible values of this parameter
     * @param is_strong  whether this parameter is strong
     * @param weight     defines sort order of parameters in <code>varOrder</code>
     *     calculation
     * @param sort_key   defines sort order of parameters in name generation
     */
    public TemplateParameter(String  name,
                             Value[] values,
                             boolean is_strong,
                             int     weight,
                             int     sort_key)
    {
        this.name = name;
        setValues(values);
        setIsStrong(is_strong);
        setWeight(weight);
        setSortKey(sort_key);
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Set/get methods */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    public String getName() {
        return name;
    }

    /**
     * @return current value of the parameter
     * @see #setTo(int)
     */
    public Value getValue() {
        return curVal;
    }

    /**
     * @param val_mnemo value mnemonics
     * @return a value with given mnemonics
     * @throws JmppException (unchecked) if the value could not be found
     */
    public Value getValue(String val_mnemo) throws JmppException {
        Value res = (Value)mnemo2value.get(val_mnemo);
        if (res == null) {
            String msg = "unknown value mnemonics: '" + val_mnemo;
            throw new JmppException(msg + "', parameter: " + getName());
        }
        return res;
    }

    /**
     * @param by_weight if <code>true</code> then the index is treated as
     *     the index to the array of values sorted by weights, otherwise,
     *     to the array of values sorted by <code>sortKey</code>.
     * @param ind value index
     * @return the value with given index
     */
    public Value getValue(int ind, boolean by_weight) {
        Value[] arr = by_weight ? valuesByWeight : values;
        return values[ind];
    }

    /**
     * Shortcut to <code>{@link #getValue(int, boolean)}(ind, false)</code>.
     *
     * @param ind value index
     * @return the value with given index
     */
    public Value getValue(int ind) {
        return getValue(ind, false);
    }

    public boolean isStrong() {
        return isStrong;
    }

    public int getSortKey() {
        return sortKey;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Sets this parameter's set of possible values. Internally prepares
     * two arrays of the values sorted by weights and sort keys.
     *
     * @param vals the values
     */
    public void setValues(Value[] vals) {
        values         = new Value[vals.length];
        valuesByWeight = new Value[vals.length];
        System.arraycopy(vals, 0, valuesByWeight, 0, vals.length);
        System.arraycopy(vals, 0, values,         0, vals.length);
        Arrays.sort(values,         new ValueComparator(ValueComparator.BY_SORT_KEY));
        Arrays.sort(valuesByWeight, new ValueComparator(ValueComparator.BY_WEIGHT));
        mnemo2value.clear();

        for (int i = 0; i < vals.length; i++) {
            String mnemo = vals[i].getMnemo();
            if (mnemo2value.get(mnemo) != null) {
                throw new JmppException("mnemonics reused: " + mnemo);
            }
            mnemo2value.put(mnemo, vals[i]);
        }
    }

    public void setIsStrong(boolean is_strong) {
        this.isStrong = is_strong;
    }

    public void setSortKey(int sort_key) {
        this.sortKey = sort_key;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Other methods */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * @return the number of possible values
     */
    public int getValueCount() {
        return values == null ? 0 : values.length;
    }

    /**
     * @param val a value
     * @return the value's index in the array sorted by sort key
     */
    public int getValueIndex(Value val) {
        return getValueIndex(val, values);
    }

    /**
     * @param val a value
     * @return the value's index in the array sorted by weight
     */
    public int getValueWeightIndex(Value val) {
        return getValueIndex(val, valuesByWeight);
    }

    private static int getValueIndex(Value val, Value[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (val == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Compares current value of this parameter to another value with given
     * mnemonics. This default implementation behaves as follows:
     * <ol>
     * <li>finds index <code>i</code> of current value</li>
     * <li>finds index <code>j</code> of the given value</li>
     * <li>returns normalized difference <code>i - j</code></li>
     * </ol>
     * Both <code>i, j</code> are indices to the array of values sorted by weight.
     *
     * @param val_mnemo mnemonics of the value to compare current value to
     * @return <code>-1, 0, 1</code> if current value is less than, equal to or greater than
     *     the given value
     * @exception JmppException (unchecked) if a value with given mnemonics could not be
     *     found among the set of possible values of this template parameter
     */
    public int compareValueTo(String val_mnemo) {
        Value val = getValue(val_mnemo);
        int i = getValueWeightIndex(getValue());
        int j = getValueWeightIndex(val);
        return i < j ? -1 : (i == j ? 0 : 1);
    }

    /**
     * Compares current value of this parameter to given boolean value.
     * Override it in subclasses. This default implementation simply throws
     * JmppException.
     *
     * @param val the value to compare to
     * @return 0 if equal, 1 otherwise
     */
    public int compareValueTo(boolean val) {
        String msg = "can not compare to boolean value";
        throw new JmppException(msg + ", parameter: " + getName());
    }

    /**
     * Sets current value to the value with given index in the value array
     * sorted by sort key.
     *
     * @param val_number value number
     */
    public void setTo(int val_number) {
        curVal = values[val_number];
    }

    /**
     * This method is invoked by the "master" Jmpp library after
     * generating each test case to give this template parameter
     * a chance to add some attributes to this test case. This default
     * implementation simply calls corresponding method of current
     * value passing the first argument to it. Override in sub-classes
     * for more complex behavior.
     *
     * @param tc_attrs   the container to add attributes to
     * @param all_params test generation context - the list of
     *   all parameters Jmpp library depends on. This argument
     *   can be used if this template parameter depends on other
     *   parameters in the list.
     */
    public void updateTestcaseAttributes(AttributeSet tc_attrs, ArrayList all_params) {
        getValue().updateTestcaseAttributes(tc_attrs);
    }

    /**
     * This method is invoked by the "master" Jmpp library after
     * generating each test group to give this template parameter
     * a chance to add some attributes to this test group. This default
     * implementation simply calls corresponding method of current
     * value passing the first argument to it. Override in sub-classes
     * for more complex behavior.
     *
     * @param tg_attrs   the container to add attributes to
     * @param all_params test generation context - the list of
     *   all parameters Jmpp library depends on. This argument
     *   can be used if this template parameter depends on other
     *   parameters in the list.
     */
    public void updateTestGroupAttributes(AttributeSet tg_attrs, ArrayList all_params) {
        getValue().updateTestGroupAttributes(tg_attrs);
    }
}
