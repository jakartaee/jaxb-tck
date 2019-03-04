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

package com.sun.tgxml.tools.filter.generic;

import java.util.regex.Pattern;

/**
 * The class implements <code>ValueFilter</code> interface and defines
 * constructor with String[] parameter. The first element of the
 * parameter defines a J2SE regular expression. The filter accepts a
 * value which is matched by the regular expression.
 */
public class RegExp implements ValueFilter {
    private Pattern pattern;

    public RegExp(String[] args) {
        this(args[0]);
    }

    public RegExp(String value) {
        pattern = Pattern.compile(value.substring(1, value.length() - 1));
    }

    public boolean acceptValue(String value) {
        return pattern.matcher(value).matches();
    }
}
