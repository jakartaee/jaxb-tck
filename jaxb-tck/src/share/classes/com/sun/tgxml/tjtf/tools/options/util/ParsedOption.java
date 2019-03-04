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

/**
 * ParsedOption - class that contains an option switch and an array 
 * of option arguments
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class ParsedOption {

    String sw = null;   
    ArrayList arguments = null;   

    /**
     * Initializes ParsedOption with specified switch and arguments 
     */
    public ParsedOption(String optionSwitch, ArrayList optionArguments) {
         sw = optionSwitch;
         arguments = optionArguments;
    }

    /**
     * Initializes ParsedOption with specified option switch 
     */
    public ParsedOption(String optionSwitch) {
         this(optionSwitch, null);
    }


    /**
     * Sets option switch 
     */
    public void setSwitch(String optionSwitch) {
         sw = optionSwitch;
    }

    /**
     * Returns option switch 
     */
    public String getSwitch() {
         return sw;
    }

    /**
     * Sets option arguments 
     */
    public void setArguments(ArrayList optionArguments) {
         arguments = optionArguments;
    }

    /**
     * Returns option switch 
     */
    public ArrayList getArguments() {
         return arguments;
    }


}
