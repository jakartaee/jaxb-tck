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

package com.sun.tgxml.tjtf.tools.options;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * StandardOptionHandler - 
 *
 * <b>StandardOptionHandler</b> is a standard implementation of the 
 * OptionHandler interface. Subclasses should not care about 
 * implementing options adding and decoding. Subclasses may only
 * register their own options and apply their values.
 * <p>
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class StandardOptionHandler implements OptionHandler {
   
    private Vector options = new Vector();
    private String[] optionsUsage = null;

    /** <code>OBLIGATORY = true</code> used to define option as obligatory */
    protected final boolean OBLIGATORY = true;

    /** <code>OPTIONAL = false</code> used to define option as non obligatory */
    protected final boolean OPTIONAL = false;

    /**
     * Creates OptionHandler with an empty option list.
     */
    public StandardOptionHandler() {
    }


    /**
     * Parses command line arguments.
     * Consequently calls parse() method of the each handler option. 
     * @return unparsed arguments
     * @throws ParseArgumentException if arguments cannot be parsed
     * or incomplete.
     *
     */
    public ArrayList parseArguments(ArrayList args) 
            throws ParseArgumentException {

        Enumeration ops = options.elements();
        while(ops.hasMoreElements()) {
            Option option = (Option)ops.nextElement();
            args = option.parse(args);
        }
        return args;
    }

    /**
     * Adds option to the list of handler options (if option is not added yet)
     */
    public void addOption(Option op) {
        if (op != null && !options.contains(op)) {
             options.add(op);
        }
    }

    /**
     * Removes option from the list of handler options 
     */
    public void removeOption(Option op) {
        if (op != null) {
             options.remove(op);
        }
    }

    /**
     * Resets all handler options 
     */
    public void resetOptions() {
        Enumeration ops = options.elements();
        while(ops.hasMoreElements()) {
            ((Option)ops.nextElement()).reset();
        }
    }

    /**
     * Sets Option Usage Info. By default getOptionsUsageInfo() returns
     * summary of handler options usage. Call this method to set
     * OptionsUsageInfo. 
     * @param oui new OptionsUsageInfo, if null usage info will be calculated
     */
    public void setOptionsUsageInfo(String[] oui) {
        optionsUsage = oui;
    }

    /**
     * Returns Option Usage Info. 
     * @return summary of handler options usage if usage info has not
     *          been set with setOptionsUsageInfo method
     */
    public String[] getOptionsUsageInfo() {
        if (optionsUsage != null)
            return optionsUsage;

        Vector lines = new Vector(options.size());

        Enumeration ops = options.elements();
        while(ops.hasMoreElements()) {
            Option option = (Option)ops.nextElement();
            String info = option.getUsageInfo();
            StringTokenizer st = new StringTokenizer(info, "\n");
            while (st.hasMoreElements()) {
                lines.add(st.nextToken());
            }
        }
        return (String[])lines.toArray(new String[0]);
    }


    /**
     * Returns the list of the all handler options. 
     */
    public Option[] allOptions() {
        return (Option[])options.toArray(new Option[0]);
    }


    /** 
     * Registers handler options. 
     * <p>Subclasses should override this method to register own options
     * <p> StandardOptionHandler uses FIFO order of options parsing:
     * first registered options will be used first for parsing.
     * Option are registered by <tt>addOption(option)</tt> call.
     * To use options registered by superclass subclass should call 
     * <tt>super.registerOptions()</tt>.
     * Options registered before this call
     * will be processed before superclass options. 
     * Options registered after this call will be processed after
     * superclass options. 
     * If <tt>super.registerOptions()</tt> method is not invoked
     * no superclass options will be registered.
     * <p><code>Notes: initialization work is doing by applyOptionsValues()
     * after decoding is done. So options objects should be defined outside 
     * of registerOptions() to be visible from applyOptionsValues() </code>
     */

    public void registerOptions() {
    }


    /**
     * Does initialization work
     * This method is invoked after all options parsing is done and
     * the set of registered Option objects are initialized.
     * Subclasses should override this method if to apply values of own options.
     * To apply values of superclass options <tt>super.applyOptionsValues()</tt>      
     * should be called from this method.
     * @throws ParseArgumentException if options value cannot be applied
     *         (incorrect or insufficient arguments passed)
     */
    public void applyOptionsValues() throws ParseArgumentException {
    }

}
