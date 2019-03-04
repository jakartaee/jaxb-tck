/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.testgen.api.source.java;

import java.util.StringTokenizer;

public class Comment {
    public static int JAVADOC = 1;
    public static int PLAIN = 2;
    public static int INLINE = 3;
    
    String indent = "";
    String content = "";
    String fill;
    
    int type = Comment.JAVADOC;
    
    public void setContent(String value) {
        content = value;
    }
    
    public void addContent(String value) {
        content += "\n \n" + value;
    }
    
    public void setIndent(int value) {
        indent = "";
        for (int i = 0; i<value; i++) indent+=" ";
    }
    
    protected String getIndent() {
        return indent;
    }
    
    protected String getFill() {
        if (fill != null) {
            return fill;
        } else return type == Comment.INLINE?"// ":" * ";
    }
    
    protected String getStartTag() {
        return type == Comment.JAVADOC?"/**\n":type == Comment.PLAIN?"/*\n":"\n";
    }
    
    protected String getEndTag() {
        return type == Comment.INLINE ? "":" */\n";
    }
    
    public String toString() {
        String result = "";
        if (content != null
                && !content.trim().equals("")) {
            result += getIndent() + getStartTag();
            StringTokenizer tk = new StringTokenizer(content, "\n");
            while (tk.hasMoreTokens()) {
                result+= getIndent() + getFill() + tk.nextToken() + "\n";
            }
            result += getIndent() + getEndTag();
        }
        return result;
    }
    
    public void setType(int value) {
        if (value>0 && value<4) {
            type = value;
        }
    }
}
