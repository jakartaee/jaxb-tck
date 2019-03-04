/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jaxb_tck.lib;

import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;
import com.sun.javatest.TestFilter;
import com.sun.javatest.util.I18NResourceBundle;
import com.sun.jck.lib.Expr;

/**
 * Filter to select JAXB tests based on test-specific dependencies
 */
public class JaxbTestFilter extends TestFilter {
    public JaxbTestFilter(TestEnvironment env) {
        this.env = env;
    }

    public String getName() {
        return "JAXB TCK Filter";
    }

    public String getDescription() {
        return "For product type selection and runtime test selection";
    }

    public String getReason() {
        return "Test does not match test-specific selection criteria";
    }

    public boolean accepts(TestDescription td) throws Fault {
        try {
            String s = td.getParameter("selectIf");
            if (s == null || s.length() == 0) {
                return true;
            }

            Expr e = Expr.parse(s);
            return e.evalBoolean(env);
        } catch (Expr.Fault e) {
            return true;
        }
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof JaxbTestFilter))
            return false;

        JaxbTestFilter other = (JaxbTestFilter) o;
        return (this.env == env);
    }

    private boolean containsKeyword(String all, String reqd) {
        int reqdLen = reqd.length();
        int allLen = (all == null ? 0 : all.length());
        for (int i = 0; i <= allLen - reqdLen; i++) {
            if (all.regionMatches(true, i, reqd, 0, reqdLen)) {
                if ((i == 0 || isBoundaryCh(all.charAt(i - 1)))
                        && (i + reqdLen == all.length() || isBoundaryCh(all
                                .charAt(i + reqdLen))))
                    return true;
            }
        }
        return false;
    }

    private static boolean isBoundaryCh(char c) {
        return !(Character.isUnicodeIdentifierStart(c) || Character
                .isUnicodeIdentifierPart(c));
    }

    private TestEnvironment env;

    private static I18NResourceBundle i18n = I18NResourceBundle
            .getBundleForClass(JaxbTestFilter.class);
}
