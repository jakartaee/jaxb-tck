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

/**
 * Class for reading option with arguments from ArrayList.
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public class OptionReader {

    ArgReader argReader;   
    SwitchChecker swChecker;

    /**
     * Creates new OptionReader with specified switch checker and option 
     * arguments reader
     *
     * @param swChecker   switch checker
     * @param argReader   reader of option arguments
     *
     * @throws IllegalArgumentException if switch checker not set 
     */
    public OptionReader (SwitchChecker swChecker, ArgReader argReader) {
        if (swChecker == null) 
            throw new IllegalArgumentException("switch checker not set");
        this.swChecker = swChecker;
        this.argReader = argReader;
    }

    /**
     * Finds all occurrences of the option in the <code>unparsed</code> list,
     * removes found options with their arguments from the list.
     * 
     * @param   unparsed - unparsed command line arguments list
     *
     * @return list of ParsedOption
     *           
     * @throws  ArgumentsNumberException - if insufficient number of arguments
     *          can be read
     */
    public ArrayList read(ArrayList unparsed) throws ArgumentsNumberException {

        ArrayList v = new ArrayList();
        int i = 0; 
        while (i < unparsed.size()) {
            if (swChecker.check(unparsed, i)) {
                String sw = (String)unparsed.remove(i);
                ArrayList args = null;
                if (argReader != null) 
                    args = argReader.read(unparsed, i);
                v.add(new ParsedOption(sw, args));
            } else {
                 i++;
            }
        }

        return v;
    }

}
