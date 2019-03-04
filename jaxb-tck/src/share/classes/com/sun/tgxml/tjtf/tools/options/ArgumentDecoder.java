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

import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;
import java.util.StringTokenizer;


/** 
 * ArgumentDecoder -
 * <p>This is the class that allows to parse arguments based on
 *   set of options. Tool specifies a number of required options
 *   and registries theses options in OptionHandler. (This 
 *   OptionHandler should be added to ArgumentDecoder handlers list)
 *   During the initialization tool calls parseArguments(String[] args) 
 *   method of 
 *   ArgumentDecoder with an arguments list. 
 * <p>Method parseArguments() method does:
 * <ul>
 * <li>returns an array of string, that contains a set of tool operands
 * <li>if arguments contains registered options sets their values
 * <li>if arguments does contain required option throws an exception
 * <li>if format of option is incorrect throws an exception
 * <li>if number of tool operands is incorrect throws an exception
 * </ul>
 * <p>To registry an option use <tt>addOption(Option o)</tt> method.
 *   ArgumentDecoder uses the following algorithm of options parsing:
 * <pre>
 *   1. Converts array of arguments into array list of string
 *   2. Consequently calls parseArguments() method for all handlers
 *      with current unparsed list. Each handler, in turn, consequently 
 *      calls parse() method for the each registred option. Each option 
 *      knows how to process unparsed list:
 *         - it cuts own parameters from the list 
 *         - initializes own value
 *         - returns the new list without parsed parameters.
 *         - throws an exception if option is required but missed
 *           or option format is incorrect
 *   3. Remaining list are treated as operands list. If operands validator
 *      is set, operands will be checked by validator.
 * 
 *   4. ArgumentDecoder uses FIFO order of options parsing:
 *      first registered options will be used first for parsing.
 * </pre>
 * 
 *   ArgumentDecoder has <tt>getUsageLines()</tt> method that returns
 *   a tool usage info (list of all registered options with their meanings).
 * 
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */ 

public class ArgumentDecoder {

    Vector handlers = new Vector();
    OperandsValidator validator = null;

    String[] usageLines   = null;

    String[] usageHeader   = null;
    String[] usageBottom   = null;
    String[] usageOptions  = null;
    String[] usageOperands = null;

    /** 
     * Creates ArgumentDecoder object
     */
    public ArgumentDecoder() {
    }

    /**
     * Returns operands, set values for all options
     * @throws ParseArgumentException  if parsing error occurs
     */
    public String[] parseArguments(String args[]) 
            throws ParseArgumentException {

        // convert String[] --> ArrayList
        ArrayList unparsed = new ArrayList(args.length);
        for (int i = 0; i < args.length; i++) {
            unparsed.add(args[i]);
        }

        Enumeration ohs = handlers.elements();
        while(ohs.hasMoreElements()) {
            OptionHandler handler = (OptionHandler)ohs.nextElement();
            unparsed = handler.parseArguments(unparsed);
        }

        // check operands
        if (validator != null) {
            validator.validate(unparsed);
        }

        return (String[])unparsed.toArray(new String[0]);
    }

    /**
     * Regesters handlers options
     */
    public void registerOptions() {
        Enumeration ohs = handlers.elements();
        while(ohs.hasMoreElements()) {
            ((OptionHandler)ohs.nextElement()).registerOptions();
        }
    }

    /**
     * Resets handlers options
     */
    public void resetOptions() {
        Enumeration ohs = handlers.elements();
        while(ohs.hasMoreElements()) {
            ((OptionHandler)ohs.nextElement()).resetOptions();
        }
    }

    /**
     * Applies values of handlers options
     * @throws ParseArgumentException  if error occurs during applying
     */
    public void applyOptionsValues()
            throws ParseArgumentException {
        Enumeration ohs = handlers.elements();
        while(ohs.hasMoreElements()) {
            ((OptionHandler)ohs.nextElement()).applyOptionsValues();
        }
    }


    /**
     * Adds option handler to the the handlers list if handler is not added yet.
     */
    public void addOptionHandler(OptionHandler oh) {
        if (oh != null && !handlers.contains(oh)) {
             handlers.add(oh);
        }
    }

    /**
     * Removes option handler from the handler list.
     */
    public void removeOptionHandler(OptionHandler oh) {
        if (oh != null ) {
             handlers.remove(oh);
        }
    }

    /**
     * Sets validator to check operands format
     */
    public void setOperandsValidator(OperandsValidator validator) {
        this.validator = validator;
    }

    /**
     * Returns operand validator
     */
    public OperandsValidator getOperandsValidator() {
        return validator;
    }



    /*
     *==============================================
     *            Usage Info methods
     *==============================================
     */
 
    // ---- header -----

    /**
     * Sets the header of tool usage message
     */
    public void setUsageHeader(String line) {
         usageHeader = splitLine(line);
    }

    /**
     * Sets the header of tool usage message
     */
    public void setUsageHeader(String[] usage) {
         usageHeader = usage;
    }


    /**
     * Retruns the header of tool usage message
     */
    public String[] getUsageHeader() {
         if (usageHeader != null)
             return usageHeader;
         return new String[0];
    }

    // ---- bottom -----

    /**
     * Sets the bottom of tool usage message
     */
    public void setUsageBottom(String line) {
         usageBottom = splitLine(line);
    }

    /**
     * Sets the bottom of tool usage message
     */
    public void setUsageBottom(String[] usage) {
         usageBottom = usage;
    }


    /**
     * Retruns the bottom of tool usage message
     */
    public String[] getUsageBottom() {
         if (usageBottom != null)
             return usageBottom;
         return new String[0];
    }


    // ------- options ----------

    /**
     * Sets the options usage message
     */
    public void setUsageOptions(String line) {
         usageOptions = splitLine(line);
    }

    /**
     * Sets the options usage message
     */
    public void setUsageOptions(String[] usage) {
         usageOptions = usage;
    }


    /**
     * Retruns the options usage message
     */
    public String[] getUsageOptions() {
         if (usageOptions != null)
             return usageOptions;

        Vector v = new Vector();
        Enumeration ohs = handlers.elements();
        while(ohs.hasMoreElements()) {
            OptionHandler handler = (OptionHandler)ohs.nextElement();
            String info[] = handler.getOptionsUsageInfo();
            addLines(v, info);
        }
        return (String[])v.toArray(new String[0]);
    }


    // ------- operands ----------

    /**
     * Sets the operands usage message
     */
    public void setUsageOperands(String line) {
         usageOperands = splitLine(line);
    }

    /**
     * Sets the operands usage message
     */
    public void setUsageOperands(String[] usage) {
         usageOperands = usage;
    }


    /**
     * Retruns the operands usage message
     */
    public String[] getUsageOperands() {
        if (usageOperands != null)
            return usageOperands;

        if (validator != null && validator.getOperandsUsageLines() != null) {
            return validator.getOperandsUsageLines();
        }

        return new String[0];
    }


    // ------- usage ----------


    /**
     * Sets the operands usage message
     */
    public void setUsageInfo(String line) {
         usageLines = splitLine(line);
    }

    /**
     * Sets the operands usage message
     */
    public void setUsageInfo(String[] usage) {
         usageLines = usage;
    }


    /**
     * Returns usage info for all registered options. Splits string containing
     * "\\n".
     * If usage lines are explicitly set returns set lines.
     */
    public String[] getUsageInfo() {
        if (usageLines != null) 
            return usageLines;


        Vector usageInfo = new Vector();
        addLines(usageInfo, getUsageHeader());
        addLines(usageInfo, getUsageOptions());
        addLines(usageInfo, getUsageOperands());
        addLines(usageInfo, getUsageBottom());

        return (String[])usageInfo.toArray(new String[0]);
    }

    /**
     * Prints usage info into the specified PrintStream
     */
    public void printUsageInfo(java.io.PrintStream out) {
        String[] usageLines = getUsageInfo();
        for (int i = 0; i < usageLines.length; i++) 
            out.println(usageLines[i]);
    }


    /**
     * Splits line into array of string
     */
    private String[] splitLine(String line) {
        if (line == null)
            return null;

        Vector v = new Vector();
        addLine(v, line);
        return (String[])v.toArray(new String[0]);
    }

    private void addLine(Vector v, String line) {
        if (line == null)
            return;
        StringTokenizer st = new StringTokenizer(line, "\n");
        while (st.hasMoreElements()) {
            v.add(st.nextToken());
        }    
    }

    private void addLines(Vector v, String[] lines) {
        if (lines == null)
            return;
        for (int i = 0; i < lines.length; i++) {
            addLine(v, lines[i]);
        }
    }


}
