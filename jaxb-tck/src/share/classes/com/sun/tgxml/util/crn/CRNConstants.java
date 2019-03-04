/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.util.crn;

/**
 * Defines "known" macro names and anchor IDs.
 *
 * @author Konstantin S. Bobrovsky
 */
public interface CRNConstants {
    String MACRO_Z = "Z";
    String MACRO_M = "M";
    String MACRO_I = "I";
    String MACRO_W = "W";
    String MACRO_E = "E";
    String MACRO_T = "T";
    String MACRO_YEAR = "YEAR";
    String MACRO_SC_INFO = "SC_INFO";

    /**
     * This macro matches a path to the master TCK copyright
     * file. (/^(?:\.\.\/)*\S+/ in Perl notation)
     */
    String MACRO_FULL_CRN_LINK = "FULL_COPYRIGHT_LINK";
    String MACRO_AUTHOR = "AUTHOR";
    String MACRO_AUTHOR_LIST = "AUTHOR_LIST";

    /**
     * This macro matches any text till the end of line.
     */
    String MACRO_TEXT_TILL_EOL = "TEXT_TILL_EOL";
    String MACRO_FILE = "FILE";
    String MACRO_TEMPLATE = "TEMPLATE";

    /**
     * This macro matches a sequence of space and tab characters.
     */
    String MACRO_SPACES = "SPACES";

    /**
     * This macro matches a sequence of /^\s*\*\n$/ strings
     * (Perl notation).
     */
    String MACRO_STARS  = "STARS";

    String CRN_JAVA_SHORT = "java_short";
    String CRN_JAVA_LONG  = "java_long";
    String CRN_HTML_SHORT = "html_short";
    String CRN_HTML_LONG  = "html_long";
    String CRN_C_SHORT = "c_short";
    String CRN_C_LONG  = "c_long";
    String CRN_JMPP_GEN = "jmpp_gen";
    String CRN_JMPP_GEN_XML = "jmpp_gen_xml";
}
