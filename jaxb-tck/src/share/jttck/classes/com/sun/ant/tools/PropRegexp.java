/*
 * Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.
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
package com.sun.ant.tools;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Property;

public class PropRegexp extends org.apache.tools.ant.Task {

    private String prop = null;
    private String value = null;
    private String suffix = null;

    public void execute() throws BuildException {
        StringBuffer sb = new StringBuffer();

        if (value.equals(" ")) {
            value = "tests/**/" + suffix;
        } else {
            // merge spaces
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                if (ch == ' ' && sb.length() > 0 && sb.charAt(sb.length() - 1) == ' '
                        || ch == '"') {
                    continue;
                }
                sb.append(ch);
            }
            value = sb.toString();
        
            // add templates
            sb.setLength(0);
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                if (ch == ' ') {
                    sb.append("/**/" + suffix);
                }
                sb.append(ch);
            }
            value = sb.toString();
        }
        Property p = new Property();
        p.setName(prop);
        p.setValue(value);
        p.setProject(getProject());
        p.execute();
    }

    public void setProperty(String prop) {
        this.prop = prop;
    }

    public void setValue(String value) {
        this.value = value + ' ';
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
