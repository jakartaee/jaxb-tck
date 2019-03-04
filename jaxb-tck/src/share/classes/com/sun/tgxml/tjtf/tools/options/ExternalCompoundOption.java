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
import com.sun.tgxml.tjtf.tools.options.util.ParsedOption;


/**
 *  ExternalCompoundOption passes parsed arguments to the specified extenal
 *  handler
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class ExternalCompoundOption extends CompoundOption {

    OptionHandler optionHandler = null;


    /**
     * Creates CompoundOption with a specified external OptionHandler
     *
     * @throws IllegalArgumentException if set of options is empty  
     *         or handler is null
     */
    public ExternalCompoundOption(Option[] options, OptionHandler oHandler) {
        super(options);
        this.optionHandler = oHandler;
    }


    /**
     * parses all options in the specified order and then calls
     * the external handler with set of parsed agruments
     * @return new list of unparsed arguments
     * @throws ParseArgumentException if option is required but missed
     *        or option format is incorrect
     *
     */
    public ArrayList parse(ArrayList unparsed) throws ParseArgumentException {
        ArrayList copy = (ArrayList)unparsed.clone();
        unparsed = super.parse(unparsed);
        copy.removeAll(unparsed);

        if (optionHandler != null) {
            copy = optionHandler.parseArguments(copy);
            if (copy != null && copy.size() > 0) {
                StringBuffer sb = new StringBuffer((String)copy.get(0));
                for (int i = 1; i < copy.size(); i++) {
                    sb.append(", ");
                    sb.append((String)copy.get(i));
                }
                throw new ParseArgumentException(optionHandler 
                    + " cannot process arguments: " + sb.toString());
            }
        }        

        return unparsed;
    }

}
