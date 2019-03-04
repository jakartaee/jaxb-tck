/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.indexgen;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.util.Vector;

/**
 * JCKHtmlFile is a JCK specific representation of html file.
 * This class provides methods to read an html title from the html file.
 * It also contains method that parses html file to detect description
 * for lang/vm tests. It makes an assumption that lang/vm descrption 
 * stored in the one of the followin formats:
 *   1.  <b></big> 'something' </big></b> - 'desription' <p>
 *   2.  <b></big> 'something' - 'desription' </big></b>
 */

public class JCKHtmlFile extends DefaultHtmlFile {
   
    protected String descr = null;
    
    /** 
     * create JCKHtmlFile object from the file.
     * the html file is parsed and the title of the html file became a title
     * of the oblect
     */
    public JCKHtmlFile(File name) throws IOException {
        super(name);
        this.descr = findDescr(buf);        
    }

    private String findDescr(StringBuffer buf) {
           // I don't know the better way how to find description
           // than implemented here
        String str = buf.toString().toLowerCase();
        int start = str.indexOf("</big></b> -");
        int end = -1;
        if (start > -1) {   
            end = str.indexOf("<p>", start);
            if (start >= end) {
                return "";
            }    
            start += 12;
        } else {
            start = str.indexOf("<b><big>");
            if (start < 0) {
                return "";
            }
            end = str.indexOf("</big></b>", start);
            start = str.indexOf(" - ", start);
            if (start >= end || start < 0) {
                return "";
            }
            start += 3;
        }
        while (str.charAt(end) == '\n') {
            end--;
        }
        return buf.substring(start, end);
    }

    public String getDescription() {
        return descr;
    }


    public void getDescription(String newDescr) {
        descr = newDescr;
    }

}

