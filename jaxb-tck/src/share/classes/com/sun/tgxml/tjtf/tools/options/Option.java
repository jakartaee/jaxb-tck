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

/**
 * Interface of methods to parse option.
 * Subclasses of these should override the methods
 * of this interface in order to implement their own algorithm of
 * option parsing. Subclasses should also provide type-safe methods 
 * to get value of the parsed option.
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */

public interface Option {
   
    /**
     * Parse and extracts itself from options list.
     * @return new list of unparsed arguments (without this option)
     * @throws ParseArgumentException if option is required but missed
     *        or option format is incorrect
     *
     */
    public ArrayList parse(ArrayList opts) throws ParseArgumentException;

    /**
     * Returns true if option value has been set
     */
    public boolean isSet();

    /**
     * Marks option as unset
     */
    public void reset();

    /**
     * Returns string with usage info for this option
     */
    public String getUsageInfo();

    /**
     * Sets new usage info
     */
    public void setUsageInfo(String info);
}
