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

import java.util.Hashtable;
import java.util.Vector;

/**
 * This class is able to parse a command line extracting options, their values
 * and other arguments (e.g. filenames). Later options, their values and the
 * arguments can be conveniently retrieved via member methods.
 * @see #Options(OptionDescr[])
 * @see #parseCommandLine(String[], boolean)
 * @see #getArguments()
 * @see #getValue(String)
 * @see #getValues(String)
 * @author Konstantin Bobrovsky
 */
public class Options {
    public final static String OPTION_SPECIFIER = "-";

    /**
     * key: option name (String), value: Vector of option values (Strings)
     */
    protected Hashtable options = new Hashtable();

    /**
     * holds those command line arguments (Strings) which are not options
     */
    protected Vector arguments = new Vector();

    /**
     * key: option name (String), value: descriptor of the option (OptionDescr).
     * All options found in the command line (passed to the parseCommandLine
     * method) a validated using this table.
     */
    protected Hashtable validOptions = new Hashtable();

    /**
     * Creates new Options object which will accept only options conforming
     * to one of given option descriptors.
     * @param option_descrs an array of option descriptors
     */
    public Options(OptionDescr[] option_descrs) {
        for (int i = 0; i < option_descrs.length; i++) {
            validOptions.put(option_descrs[i].getName(), option_descrs[i]);
        }
    }

    /**
     * Parses given command line and constructs internal representation of
     * the options and other arguments contained in the command line. Validates
     * each option accordingly to the option descriptors passed in the
     * constructor.
     * @param args      command line arguments
     * @param parse_all if true, all given arguments will be parsed and an
     *                  exception thrown on an unknown option, otherwise,
     *                  only known options will be parsed (must follow
     *                  continuously), the rest of the arguments will be
     *                  returned.
     * @return an array of those arguments which have not been parsed
     * @throws IllegalArgumentException on option syntax error
     */
    public String[] parseCommandLine(String[] args, boolean parse_all)
        throws IllegalArgumentException {

        int i;
        for (i = 0; i < args.length; i++) {
            if (args[i].startsWith(OPTION_SPECIFIER)) {
                String value = null;
                String name = args[i].length() > 1 ? args[i].substring(1) : "";
                String fname = OPTION_SPECIFIER + name;
                OptionDescr dsc = (OptionDescr)validOptions.get(name);

                if (dsc == null) {
                    if (parse_all) {
                        String s = "unrecognized option";
                        throw new IllegalArgumentException(s + ": " + fname);
                    } else {
                        break;
                    }
                }
                if (dsc.getHasValue()   &&
                    i < args.length - 1 &&
                    !args[i+1].startsWith(OPTION_SPECIFIER)) {

                    value = args[++i];
                }
                if (dsc.getHasValue() && value == null) {
                    String s = "option must have a value";
                    throw new IllegalArgumentException(s + ": " + fname);
                }
                add(name, value);
            } else {
                break;
            }
        }
        for (int j = i; j < args.length; j++) {
            String s = args[j];
            if (s.startsWith(OPTION_SPECIFIER) && parse_all) {
                String s1 = "options must precede other arguments";
                throw new IllegalArgumentException(s1 + ": " + s);
            }
            arguments.addElement(s);
        }
        if (!parse_all) {
            String[] res = new String[arguments.size()];
            arguments.copyInto(res);
            arguments.clear();
            return res;
        }
        return null;
    }

    /**
     * @return a non-null array of those command line arguments which were not
     * classified as options in the parseCommandLine method (usually file names)
     */
    public String[] getArguments() {
        String[] res = new String[arguments.size()];
        arguments.copyInto(res);
        return res;
    }

    /**
     * Sets the option with the specified name.
     * The option is supposed to have no value.
     * No validation is performed and all previously set values
     * for the option are erased (if any).
     * @param option_name name of the option to be set
     */
    public void set(String option_name) {
        set(option_name, (String)null);
    }

    /**
     * Sets the option with the specified name to given value.
     * No validation is performed and all previously set values
     * for the option are erased (if any).
     * @param option_name  name of the option to be set
     * @param option_value the option's value.
     */
    public void set(String option_name, String option_value) {
        Vector opt_values = (Vector)options.get(option_name);
        if (opt_values == null) {
            opt_values = new Vector();
            options.put(option_name, opt_values);
        }
        if (option_value == null)
            return;
        opt_values.setSize(1);
        opt_values.setElementAt(option_value, 0);
    }

    /**
     * Adds given value to the set of values of the option with given name.
     * Both the option name and the value are validated according to the
     * descriptor of the option.
     * @param option_name  name of the option
     * @param option_value value to be added
     * @throws IllegalArgumentException if one of the following happened:
     * <ul>
     * <li> the descriptor for the option has not been found
     * <li> given value or the option's value set do not conform to the
     *      descriptor
     * </ul>
     */
    public void add(String option_name, String option_value)
        throws IllegalArgumentException {

        OptionDescr dsc = (OptionDescr)validOptions.get(option_name);
        Vector opt_values = (Vector)options.get(option_name);
        String o = OPTION_SPECIFIER + option_name;

        String s;
        if (dsc == null) {
            s = "unrecognized option";
            throw new IllegalArgumentException(s + ": " + o);
        }
        if (dsc.getHasValue() && option_value == null) {
            s = "option must have a value";
            throw new IllegalArgumentException(s + ": " + o);
        }
        if (!dsc.getHasValue() && option_value != null) {
            s = "option must not have a value";
            throw new IllegalArgumentException(s + ": " + o);
        }
        if (!dsc.getIsMultiple() && opt_values != null) {
            s = "option is specified twice";
            throw new IllegalArgumentException(s + ": " + o);
        }
        if (opt_values == null) {
            opt_values = new Vector();
            options.put(option_name, opt_values);
        }
        if (option_value == null)
            return;
        opt_values.addElement(option_value);
    }

    /**
     * Retrieves a value of an option.
     * @param option_name name of the option
     * @return a value of the option. If the option has been specified multiple
     * times then the first value is returned.
     */
    public String getValue(String option_name) {
        Vector opt_values = (Vector)options.get(option_name);
        if (opt_values == null || opt_values.size() == 0)
            return null;
        return (String)opt_values.elementAt(0);
    }

    /**
     * Retrieves all values of an option. For example, for "-opt val0 -opt val1"
     * options getValues("opt") will return {"val0", "val1"}.
     * @param option_name name of the option
     * @return an array of the option values
     */
    public String[] getValues(String option_name) {
        Vector opt_values = (Vector)options.get(option_name);
        if (opt_values == null || opt_values.size() == 0)
            return null;
        int values_total = opt_values.size();
        String[] res = new String[values_total];
        for (int i = 0; i < values_total; i++)
            res[i] = (String)opt_values.elementAt(i);
        return res;
    }

    /**
     * @param option_name option name
     * @return a boolean value indicating whether given option has been set
     * (or assigned a value)
     */
    public boolean isSet(String option_name) {
        return (options.get(option_name) != null);
    }
}
