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

package com.sun.tgxml.util;

/**
 *  Copyright utils
 */
public class CopyrightUtil {
    public final static String COPYRIGHT_MACRO = "%Copyright%";

    /** 
     *  Expands macro in copyright string. Inserts link instead.
     *  'xxx %yyy% zzz' --> 'xxx <A HREF="link">yyy</A> zzz'
     *
     */
    public static String expandCopyrightMacro(String copyright, String link) {
	if (copyright == null || link == null || link.equals("")) 
            return copyright;

        StringBuffer buf = new StringBuffer(copyright);
        int start = copyright.indexOf("%");
        int end = copyright.lastIndexOf("%");
        if ((-1 < start) && (start < end)) {
            String replacement = copyright.substring(start+1, end);
            replacement = "<A HREF=\"" + link 
                        + "\">" + replacement + "</A>";
            buf.replace(start, end+1, replacement);
        }
        return buf.toString();

    }

    public static boolean copyrightMacroExpanded(String copyright) {
        return copyright.indexOf(COPYRIGHT_MACRO) < 0;
    }
}
