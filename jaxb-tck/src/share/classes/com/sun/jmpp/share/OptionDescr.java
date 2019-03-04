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

package com.sun.jmpp.share;

/**
 * This class offers a formal way of describing an option, e.g.,
 * whether it has a value, whether it may be specified more than once,
 * set of possible values (if any), it's default value if any,
 * it's usage information.
 * @author Konstantin Bobrovsky
 */
public class OptionDescr {
    public final static int VAL_NONE   = 0;
    public final static int VAL_SINGLE = 1;
    public final static int VAL_MULTI  = 2;

    /**
     * option name
     */
    protected String name;

    /**
     * oprion title (should be more descriptive than the name).
     * Can be used in GUI apps that graphically render this option.
     */
    protected String title;

    /**
     * whether the option has a value (or it just a switch)
     */
    protected boolean hasValue = false;

    /**
     * set of pairs : &lt;possible value&gt;, &lt;its description&gt;
     */
    protected String[][] allowedValues;

    /**
     * whether the option may be specified more than once
     */
    protected boolean isMultiple = false;

    /**
     * usage information
     */
    protected String usage;

    /**
     * default value
     */
    protected String defVal;

    /**
     * Default constructor
     */
    public OptionDescr() {}

    /**
     * Creates new OptionDescr. Defaults :<br>
     * - no value<br>
     * - no possible values<br>
     * - no default value<br>
     * @see #OptionDescr(String, String, int, String[][], String, String)
     */
    public OptionDescr(String name, String title, String usage) {
        this(name, title, VAL_NONE, null, usage, null);
    }

    /**
     * Creates new OptionDescr. Defaults :<br>
     * - can be specified only once
     * @see #OptionDescr(String, String, int, String[][], String, String)
     */
    public OptionDescr(String name,
                       String title,
                       String[][] allowed_values,
                       String usage,
                       String def_val) {
        this(name, title, VAL_SINGLE, allowed_values, usage, def_val);
    }

    /**
     * Creates new OptionDescr. Defaults :<br>
     * - possible value is arbitrary<br>
     * - no default value
     * @see #OptionDescr(String, String, int, String[][], String, String)
     */
    public OptionDescr(String name, String title, int val_kind, String usage) {
        this(name, title, val_kind, null, usage, null);
    }

    /**
     * Creates new OptionDescr
     * @param name     option name
     * @param title    oprion title (should more descriptive than the name)
     * @param val_kind one of<br>
     * <pre>
     *   <code>VAL_NONE</code>   no value
     *   <code>VAL_SINGLE</code> has a value and may be specified only once
     *   <code>VAL_MULTI</code>  has a value and may be specified multiple times
     * </pre>
     * @param allowed_values set of pairs : &lt;possible value&gt;,
     *                                      &lt;its description&gt;
     * @param usage         usage information
     * @param def_val       default value
     */
    public OptionDescr(String     name,
                       String     title,
                       int        val_kind,
                       String[][] allowed_values,
                       String     usage,
                       String     def_val) {
        this.name          = name;
        this.title         = title;
        this.usage         = usage;
        this.allowedValues = allowed_values;
        this.defVal        = def_val;
        if (val_kind != VAL_NONE)
            hasValue = true;
        if (val_kind == VAL_MULTI)
            isMultiple = true;
    }

    /** 
     * Creates new OptionDescr, filling all fields from &lt;src&gt;
     * @param src OptionDescr instance to copy fields from
     */
    public OptionDescr(OptionDescr src) {
        name          = src.name;
        title         = src.title;
        hasValue      = src.hasValue;
        allowedValues = src.allowedValues;
        isMultiple    = src.isMultiple;
        usage         = src.usage;
        defVal        = src.defVal;
    }
    
    /**
     * @return true if &lt;this&gt; equals o, false otherwise
     */
    public boolean equals(Object o) {
        if (!(o instanceof OptionDescr))
            return false;
        OptionDescr od = (OptionDescr)o;
        return name.equals(od.name); 
    }

    // Accessor methods

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean getHasValue() {
        return hasValue;
    }

    public String[][] getAllowedValues() {
        return allowedValues;
    }

    public boolean getIsMultiple() {
        return isMultiple;
    }

    public String getUsage() {
        return usage;
    }

    public String getDefaultValue() {
        return defVal;
    }
}
