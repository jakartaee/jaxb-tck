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
import com.sun.tgxml.tjtf.tools.options.util.OptionReader ;
import com.sun.tgxml.tjtf.tools.options.util.SwitchChecker ;
import com.sun.tgxml.tjtf.tools.options.resources.ErrorMessages;

/**
 * Abstract class implementing based algorithm of option parsing
 * To implement parsing subclasses need provide implementaistions only for 
 * the following methods:<p>
 * <tt>getOptionReader()</tt><p>
 * <tt>getSwitchChecker()</tt><p>
 * <tt>calculateValue(ArrayList parsedOption)</tt><p>
 * 
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public abstract class StandardOption implements Option {
   
    private String usageInfo = null;
    private boolean isSet = false;

    /*
     * if isObligatory == true this option must be present in the command
     * line arguments list
     */
    private boolean isObligatory = false;

    private String patt_missedOption = 
            ErrorMessages.getPattern("option.ObligatoryOptionMissed");


    /**
     * Parse and extracts itself from options list.
     * @return new list of unparsed arguments (without this option)
     * @throws ParseArgumentException if option is obligatory but missed
     *        or option format is incorrect
     *
     */

    public final ArrayList parse(ArrayList unparsed) 
            throws ParseArgumentException {
        OptionReader oReader = getOptionReader();
        ArrayList parsedOption = oReader.read(unparsed);
        if (isObligatory() && parsedOption.size() == 0)
            throw new ObligatoryOptionMissedException(ErrorMessages.getMessage(
                     patt_missedOption, this));
        if (parsedOption.size() > 0) {
            isSet = true;
            calculateValue(parsedOption);
        } else {
            isSet = false;
        }

        return unparsed;
    }

    /**
     * Returns OptionReader used to extact option with arguments from the list
     */ 
    public abstract OptionReader getOptionReader() ;

    /**
     * Returns SwitchChecker used to check whether an element may be an option
     * switch or not
     */ 
    public abstract SwitchChecker getSwitchChecker();

    /**
     * Calculates value of extracted option 
     */ 
    public abstract void calculateValue(ArrayList parsedOption)
            throws ParseArgumentException;

    /** 
     * Returns true if option is obligatory, false - otherwise
     */
    public boolean isObligatory() {
        return isObligatory;
    }

    /** 
     * Sets type of opotoin
     * @param flag <code>true</code>if option is obligatory, 
     *             <code>false</code> - otherwise
     */
    public void setObligatory(boolean flag) {
        isObligatory = flag;
    }



    /** 
     * Returns true if option is present in the list
     */
    public boolean isSet() {
        return isSet;
    }


    /** 
     * Marks option as unset.
     */
    public void reset() {
        isSet = false;
    }


    /**
     * Returns string with usage info for the option
     */
    public String getUsageInfo() {
        return usageInfo;
    }

    /**
     * Sets new usage info
     */
    public void setUsageInfo(String info) {
        usageInfo = info;
    }

    /*
     ---------------------------------------------
        Error patterns set/get methods
     ---------------------------------------------
    */

    /**
     * Sets error message pattern will be printed if option is specified
     * as obligatory but missed.
     * <p>Example of pattern : 
     * <pre>
     *     Option "{0}" is required but missed
     * </pre>
     */
    public void setObligatoryOptionMissedPattern(String patt) {
        patt_missedOption = patt;
    }

    /**
     * Returns error message pattern will be printed if option is specified
     * as obligatory but missed.
     */
    public String getObligatoryOptionMissedPattern() {
        return patt_missedOption;
    }

}
