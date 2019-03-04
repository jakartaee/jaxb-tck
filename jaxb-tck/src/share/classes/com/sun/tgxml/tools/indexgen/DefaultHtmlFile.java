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
 * DefaultHtmlFile is a simple representation of html file.
 * This class provides methods to read an html title from the html file.
 */
public class DefaultHtmlFile {
   
    protected String title = null;
    protected File name = null;
    protected String descr = null;
    protected StringBuffer buf = null;
    
    /** 
     * create DefaultHtmlFile object from the file.
     * the html file is parsed and the title of the html file became a title
     * of the oblect
     */
    public DefaultHtmlFile(File name) throws IOException {
        buf = readFile(name);
        this.name = name;
        this.title = findTitle(buf);        
    }

    /** 
     * create DefaultHtmlFile object for given file and with given title.
     */

    public DefaultHtmlFile(File name, String title) throws IOException {
        this(name);
        setTitle(title);
    }

    private String findTitle(StringBuffer buf) {
        String str = buf.toString().toLowerCase();
        int start = str.indexOf("<title>");
        int end = str.indexOf("</title>");
        if (start > -1 && start < end) {
            return title = buf.substring(start+7, end).replace('\n', ' ');
        } else {
            return "no title";
        }    
    }

    private StringBuffer readFile(File name)  throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        StringBuffer buf = new StringBuffer();
        String str;
        while ((str = br.readLine()) != null) {
            buf.append(str);
            buf.append("\n");
        }
        br.close();
        return buf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }


    public File getFile() {
        return name;
    }


    public void setFile(File newFile) {
        name = newFile;
    }

}

