/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.util.crn;

import java.util.ArrayList;

/**
 * Represents a CRN macro. CRN macro object possesses the following
 * main characteristics: name, value, default value, value parser.
 *
 * @author Konstantin S. Bobrovsky
 */
public class Macro implements MacroParser {
    private String name;

    /**
     * A name with suffix '.n' stripped. Macros with the same
     * base names are assumed to have the same value parsers.
     */
    private String baseName;
    private String value;
    private String defaultValue;
    private MacroParser parser;

    /**
     * Constructor.
     *
     * @param name macro name
     */
    public Macro(String name) {
        setName(name);
    }

    /**
     * Constructor.
     *
     * @param name macro name
     * @param value  macro value
     */
    public Macro(String name, String value) {
        setName(name);
        setValue(value);
    }

    /**
     * Constructor.
     *
     * @param name macro name
     * @param parser macro value parser
     */
    public Macro(String name, MacroParser parser) {
        setName(name);
        setValueParser(parser);
    }

    /**
     * Clones this object. Copies name, parser and default value;
     *
     * @return the copy of this macro.
     */
    public Object clone() {
        Macro clone = new Macro(getName(), getValueParser());
        clone.setDefaultValue(getDefaultValue());
        return clone;
    }

    /**
     * @return macro name
     */
    public String getName() {
        return name;
    }

    /**
     * @return macro value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return <code>true</code> if the value has been set,
     *     <code>false</code> otherwise.
     */
    public boolean isExpanded() {
        return getValue() != null;
    }

    /**
     * Sets macro name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        baseName = name;
        int dot_ind = baseName.indexOf('.');

        if (dot_ind >= 0) {
            baseName = baseName.substring(0, dot_ind);
        }
    }

    /**
     * @return the base name of this macro. Base name is the part of
     *     the name till the first dot.
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * Sets macro value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sets macro value parser.
     *
     * @param parser the parser
     */
    public void setValueParser(MacroParser parser) {
        this.parser = parser;
    }

    /**
     * @return value parser for this macro.
     */
    public MacroParser getValueParser() {
        return parser;
    }

    /**
     * Sets default value of this macro.
     *
     * @param default_value the default value.
     */
    public void setDefaultValue(String default_value) {
        defaultValue = default_value;
    }

    /**
     * @return default value of this macro.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Implementation of the {@link MacroParser#parseValue(String, int)}
     * method. Simply invokes underlying parser. If parsing succeedes, sets
     * the macro value to the parsed substring.
     *
     * @param s a string
     * @param start_index starting index of substring which is supposed
     *     to be a value of this macro.
     * @return
     *     -1 if parsing failed, the end index of the substring plus one.
     */
    public int parseValue(String s, int start_index) {
        int ind = parser.parseValue(s, start_index);

        if (ind >= 0) {
            if (ind < start_index) {
                throw new IndexOutOfBoundsException();
            }
            setValue(s.substring(start_index, ind));
        }
        return ind;
    }

    /**
     * @return string representation of the macro
     */
    public String toString() {
        return "MACRO: {<" + getName() + ">,<" + getValue() + ">," + getValueParser() + "}";
    }
}
