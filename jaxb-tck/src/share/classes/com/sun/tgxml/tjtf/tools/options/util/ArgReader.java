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

package com.sun.tgxml.tjtf.tools.options.util;

import java.util.ArrayList;
import com.sun.tgxml.tjtf.tools.options.ArgumentsNumberException;
import com.sun.tgxml.tjtf.tools.options.resources.ErrorMessages;

/**
 * Class for reading option arguments from ArrayList.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class ArgReader {

    ArgChecker argChecker = null;   
    int min = 0;   
    int max = 0;   

    private String patt_missedOptionParameter = 
            ErrorMessages.getPattern("option.ArgumentsNumber.missed");

    private String optionName = "";

    /**
     * Creates new ArgReader. 
     * @param min  a number of minimal required parameters
     * @param max  a number of maximum required parameters
     * @param argChecker  argument validator used to check whether argument 
     * is acceptable or not     
     *
     * @throws IllegalArgumentException if the following condition is 
     *         is broken: <code> 0 <= min <= max </code>
     */
    public ArgReader (int min, int max, ArgChecker argChecker) {
        if (min < 0 || min > max) 
             throw new IllegalArgumentException("Illegal paramaters: " + min 
                 + ", " + max);
        this.min = min;
        this.max = max;
        this.argChecker = argChecker;
        if (argChecker == null)
            this.argChecker = new ArgChecker(null, null);
    }

    /**
     * Creates new ArgReader. 
     * @param min  a number of minimal required parameters
     * @param max  a number of maximum required parameters
     * @param invalidPrefix if set arguments should not start with this prefix
     * @param validPrefix  if set arguments should start with this prefix
     *
     * @throws IllegalArgumentException if the following condition is 
     *         is broken: <code> 0 <= min <= max </code>
     */
    public ArgReader (int min, int max, 
              String invalidPrefix, String validPrefix) {
         this(min, max, new ArgChecker(invalidPrefix, validPrefix));
    }

    /**
     * Reads option arguments from array and removes them from array.
     * 
     * @param arr - unparsed command line arguments list
     * @param index - current index in the array
     * @return an array of option agruments
     * @throws ArgumentsNumberException - if insufficient number of arguments
     *         can be read
     */
    public ArrayList read(ArrayList arr, int index) 
        throws ArgumentsNumberException {

        int n = 0; // number of read arguments
        ArrayList arguments = new ArrayList(min);
        while ( n < max && argChecker.check(arr, index)) {
            arguments.add((String)arr.remove(index));
            n++;
        }

        if (n < min)
            throw new ArgumentsNumberException(ErrorMessages.getMessage(
                     patt_missedOptionParameter, optionName, min, n));

        return arguments;
    }

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if option parameter
     * is missed.
     * <p>Example of pattern : 
     * <pre>
     *     "{0}" option expects {1} parameter(s), passed only {2}
     * </pre>
     */
    public void setOptionParameterMissedPattern(String patt) {
        patt_missedOptionParameter = patt;
    }

    /**
     * Returns error message pattern will be printed if option parameter
     * is missed.
     */
    public String getOptionParameterMissedPattern() {
        return patt_missedOptionParameter;
    }


    /**
     * Sets option name, used to be printed in case of error occures.
     */
    public void setOptionName(String name) {
        optionName = name;
    }

    /**
     * Returns option name, used to be printed in case of error occures.
     */
    public String getOptionName() {
        return optionName;
    }

}
