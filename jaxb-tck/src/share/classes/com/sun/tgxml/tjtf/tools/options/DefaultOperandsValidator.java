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
import com.sun.tgxml.tjtf.tools.options.resources.ErrorMessages;

/**
 * DefaultOperandsValidator verifies that number of operands is expected,
 * there is no operand startring with invalid prefix, all operands 
 * end with specified suffix
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */
public class DefaultOperandsValidator implements OperandsValidator {
   
    /** 
     * The number of minimal required parameters
     */
    protected int min = 0;

    /** 
     * The number of maximum required parameters
     */
    protected int max = 0;

    /** 
     * Descriptions of operands usage
     */
    protected String[] usageLines = null;

    /** 
     * Invalid operand prefix, if null - any prefix allowed
     */
    protected String invalidPrefix = null;

    /** 
     * Obligatory operand suffix, if null - any suffix allowed
     */
    protected String validSuffix = null;


    private String patt_badPrefix = 
            ErrorMessages.getPattern("operand.BadPrefix");

    private String patt_badSuffix = 
            ErrorMessages.getPattern("operand.BadSuffix");

    private String patt_tooFew    = ErrorMessages.getPattern("operand.TooFew");

    private String patt_tooMany   = ErrorMessages.getPattern("operand.TooMany");


    /**
     * Creates new DefaultOperandsValidator with invalid prefix "-"
     * @param min         a number of minimal required parameters
     * @param max         a number of maximum required parameters
     * @param usageLines  descriptions of operands
     * @throws IllegalArgumentException if the following condition is 
     *         is broken: <code> 0 <= min <= max </code>
     */

    public DefaultOperandsValidator(int min, int max, String[] usageLines) {
        this(min, max, "-", null, usageLines);
    }


    /**
     * Creates new DefaultOperandsValidator. 
     * @param min         a number of minimal required parameters
     * @param max         a number of maximum required parameters
     * @param invalidPrefix  invalid operand prefix
     * @param validSuffix    obligatory operand suffix
     * @param usageLines  descriptions of operands
     * @throws IllegalArgumentException if the following condition is 
     *         is broken: <code> 0 <= min <= max </code>
     */
    public DefaultOperandsValidator(int min, int max, 
            String invalidPrefix, String validSuffix, String[] usageLines) {
        if (min < 0 || min > max) 
             throw new IllegalArgumentException("Illegal paramaters: " + min 
                 + ", " + max);
        this.min = min;
        this.max = max;
        this.invalidPrefix = invalidPrefix;
        this.validSuffix = validSuffix;
        this.usageLines = usageLines;
    }

    /**
     * validates operands 
     * @throws OperandException if operands format is incorrect
     */
    public void validate(ArrayList operands) throws OperandException {
         int size = operands.size();
         StringBuffer list = new StringBuffer();

         for (int i = 0; i < size; i++) {
             list.append((String)operands.get(i));
             list.append(" ");
             if ( invalidPrefix != null
                     && ((String)operands.get(i)).startsWith(invalidPrefix) )
                 throw new OperandException(ErrorMessages.getMessage(
                     patt_badPrefix, operands.get(i), invalidPrefix));

             if ( validSuffix != null
                     && !((String)operands.get(i)).endsWith(validSuffix) )
                 throw new OperandException(ErrorMessages.getMessage(
                     patt_badSuffix, operands.get(i), validSuffix));
         }

         if (size < min) 
             throw new OperandException(ErrorMessages.getMessage(
                     patt_tooFew, min, size));
         else if (size > max) 
             throw new OperandException(ErrorMessages.getMessage(
                     patt_tooMany, max, size));
    }

    /**
     * Sets operands description
     */
    public void setOperandsUsageLines(String[] usage) {
        usageLines = usage;
    }

    /**
     * Returns lines with operands description
     */
    public String[] getOperandsUsageLines() {
        return usageLines;
    }

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if operand prefix is
     * is incorrect. 
     * <p>Example of pattern : 
     * <pre>
     *     Bad prefix of operand: {0} (should not start with {1})
     * </pre>
     */
    public void setIncorrectPrefixPattern(String patt) {
        patt_badPrefix = patt;
    }

    /**
     * Returns error message pattern will be printed if operand prefix is
     * is incorrect. 
     */
    public String getIncorrectPrefixPattern() {
        return patt_badPrefix;
    }



    /**
     * Sets error message pattern will be printed if operand suffix is
     * is incorrect. 
     * <p>Example of pattern : 
     * <pre>
     *     Bad suffix of operand: {0} (should end with {1})
     * </pre>
     */
    public void setObligatorySuffixPattern(String patt) {
        patt_badSuffix = patt;
    }

    /**
     * Returns error message pattern will be printed if operand suffix is
     * is incorrect. 
     */
    public String getObligatorySuffixPattern() {
        return patt_badSuffix;
    }


    /**
     * Sets error message pattern will be printed if operand missed
     * <p>Example of pattern : 
     * <pre>
     *     Too few operands: {1}  (expected: {0})
     * </pre>
     */
    public void setOperandMissedPattern(String patt) {
        patt_tooFew = patt;
    }

    /**
     * Returns error message pattern will be printed if operand missed 
     */
    public String getOperandMissedPattern() {
        return patt_tooFew;
    }


    /**
     * Sets error message pattern will be printed if too many operands passed
     * <p>Example of pattern : 
     * <pre>
     *     Too many operands: {1}  (expected: {0})
     * </pre>
     */
    public void setTooManyOperandsPattern(String patt) {
        patt_tooMany = patt;
    }

    /**
     * Returns error message pattern will be printed if too many operands passed
     */
    public String getTooManyOperandsPattern() {
        return patt_tooMany;
    }

}
