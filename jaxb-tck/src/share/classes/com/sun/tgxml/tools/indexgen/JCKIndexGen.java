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

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.api.impl.TestSuiteImpl;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.CopyrightUtil;



import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Vector;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.StringTokenizer;

import com.sun.tgxml.tjtf.tools.ToolBase;

public class JCKIndexGen extends DefaultHtmlGenerator {
   

   static final String SM_BY_TITLE = "byTitle";
   static final String SM_BY_FILE_NAME = "byFileName";
   static final String SM_NO_SORT = "noSort";
   static final String SM_BY_PROPERTY = "byProperty";

   private static final String CtStr_ToolName = "JCKIndexGen";
  
   protected String copyrightLink = null;
  /**
    *  Program entry
    *
    *  This replaces the main in ToolBase (sub-classes XMLToolBase)
    */
    public static void main(String args[]) {
        JCKIndexGen c = new JCKIndexGen(System.out, System.err);
        System.exit(c.run(args));
    }
    
    
    /** Constructor (canon.)
     *  
     *  constructs the JCKIndexGen tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public JCKIndexGen( PrintStream out, PrintStream err) {
        super(out, err, CtStr_ToolName);
    }


    private String title = null;
    private String descr = null;
    private String id = null;
    private boolean isTable = false;

    public Vector create(File resultHTML,  TestSuite doc, Vector links)  
            throws TestFileException {
        descr = doc.getDescription();
        String format = doc.getContentsFormat();
        if (format != null && format.equals(TestSuite.TABLE_FORMAT)) {
            isTable = true;
        } else {
            isTable = false;
        }
        id = null;
        if (source != null) {
            id = source.getName();
            if (id.endsWith(".doc.xml")) {
                id = id.substring(0, id.length() - 8);
            }
        }
        title = doc.getTitle();
        if ((title == null || title.length()==0) && id != null) {
             title = id;
        }
        return super.create(resultHTML, doc, links);
    }     
    
   /**
    * Returns the value of "Title" element of the given xmlFile with 
    * all html tags removed. 
    */    
    protected String createTitle(TestSuite xmlFile) throws TestFileException {
        return title == null ? "" : removeTags(title);
    }

    /**
     * Removes html tags from the given line
     */
    protected String removeTags(String line) {
        if (line == null) 
             return null;

        StringBuffer result = new StringBuffer();
        int begin = 0;
        int sTag = line.indexOf('<');
        while (sTag >= 0) {
            int eTag = line.indexOf('>', sTag);
            if (eTag < 0) {
                sTag = -1;
            } else {            
                result.append(line.substring(begin, sTag));
                begin = eTag + 1;
                sTag = line.indexOf('<', begin);
            }
        }
        result.append(line.substring(begin));
        return result.toString();
    }

   /**
    * Returns the JCK specific descrption. 
    */    
    protected String createDescription(TestSuite xmlFile)
             throws TestFileException {

        if (descr == null) {
            return "";
        }
        if (title == null) {
            title = "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<b><big>");
        if (isTable) {
            sb.append("ASSERTION ");        
            if (id != null) {
                sb.append(id);
            }
            sb.append(" - ");
        }
        sb.append(title);
        sb.append("</big></b>\n<hr>\n<p>");
        sb.append(descr);
        return sb.toString();
    }

   /**
    * Returns links to the tests in the list format, where list
    * is sorted by title.
    */
    protected String createLinkList(TestSuite xmlFile, Vector links)  
            throws TestFileException {

         return super.createLinkList(xmlFile, sortLinks(xmlFile, links));
    }

    private Vector sortLinks(TestSuite xmlFile, Vector links)
            throws TestFileException {
         String sortMode = IR.getAttrElem("sortMode", xmlFile);
         if (sortMode == null || sortMode.equals(SM_BY_FILE_NAME)) {
             return sortLinksByFileName(links);
         } else if (sortMode.equals(SM_NO_SORT)) {
             return links;
         } else if (sortMode.equals(SM_BY_TITLE)) {
             return sortLinksByTitle(links);
         } else if (sortMode.startsWith(SM_BY_PROPERTY)) {
             String propName = sortMode.substring(SM_BY_PROPERTY.length());
             return sortLinksByProperty(links, propName);
         } else {
             throw new TestFileException("Unknown sortMode: " + sortMode);
         }

    }

    private Vector sortLinks(Vector links, Comparator comp) {
         Object[] tmpArray = links.toArray();
         Arrays.sort(tmpArray, comp);
         Vector sortedLinks = new Vector(tmpArray.length);
         sortedLinks.addAll(Arrays.asList(tmpArray));
         return sortedLinks;
    }

    private Vector sortLinksByFileName(Vector links) {
         Comparator comp = new Comparator () {
             public int compare(Object o1, Object o2) {
                 String s1 = ((DefaultHtmlFile)o1).getFile().toString();
                 String s2 = ((DefaultHtmlFile)o2).getFile().toString();
                 return s1.compareTo(s2);
             }
             public boolean equals(Object obj) { 
                 return (this == obj);
             }
         };
         return sortLinks(links, comp);
    }

    private Vector sortLinksByProperty(Vector links, String propName) {
         final String order = BuildProperties.getPrefixString("JCKIndexGen",
             "order." + propName.trim(), null);
         if (order == null)
              return sortLinksByFileName(links);

         Comparator comp = new Comparator() {
             public int compare(Object o1, Object o2) {
                 DefaultHtmlFile f1 = (DefaultHtmlFile) o1;
                 DefaultHtmlFile f2 = (DefaultHtmlFile) o2;
                 String s1 = null;
                 String s2 = null;
                 try {
                     s1 = detectHREF(f1);
                     s2 = detectHREF(f2);
                 } catch (TestFileException e) {
                     s1 = f1.getFile().toString();
                     s2 = f2.getFile().toString();
                 }
                 int i1 = order.indexOf("<"+s1+">");
                 int i2 = order.indexOf("<"+s2+">");
                 if (i1 >= 0 && i2 >= 0) {
                     // both found in order
                     return i1-i2;
                 } else if (i1 < 0 && i2 < 0) {
                     // noone is found in order
                     return s1.compareTo(s2);
                 } else if (i1 < 0) {
                     // only second is found in order
                     return 1;
                 } else {
                     // only first is found in order
                     return -1;
                 }
             }
             public boolean equals(Object obj) { 
                 return (this == obj);
             }
         };
         // comp.setOrder(order);
         return sortLinks(links, comp);
    }

    private Vector sortLinksByTitle(Vector links) {
         Comparator comp = new Comparator () {
             public int compare(Object o1, Object o2) {
                 DefaultHtmlFile f1 = (DefaultHtmlFile) o1;
                 DefaultHtmlFile f2 = (DefaultHtmlFile) o2;
                 String s1 = f1.getTitle() + f1.getFile();
                 String s2 = f2.getTitle() + f2.getFile();
                 return s1.compareTo(s2);
             }
             public boolean equals(Object obj) { 
                 return (this == obj);
             }
         };
         return sortLinks(links, comp);
    }


    protected String createLinkTable(TestSuite xmlFile, Vector links)
            throws TestFileException {

        links = sortLinks(xmlFile, links);
        StringBuffer sb = new StringBuffer();

        sb.append("<p>\n<h3>TEST CASES</h3>\n\n");
        sb.append("<TABLE BORDER=1>\n");
        sb.append("  <TR>\n");
        sb.append("    <TH scope=\"col\">");
        sb.append(        "<center>Test</center>");
        sb.append(   "</TH>\n");
        sb.append("    <TH scope=\"col\">");
        sb.append(        "<center>Title</center>");
        sb.append(    "</TH>\n");
        sb.append("  </TR>\n");
        for (Enumeration e = links.elements() ; e.hasMoreElements() ;) {
            JCKHtmlFile f = (JCKHtmlFile)e.nextElement();
            sb.append("  <TR>\n");
            sb.append("    <TD scope=\"row\">");
            sb.append(        super.createReference(f));
            sb.append(    "</TD>\n");
            sb.append("    <TD>");
            sb.append(        f.getDescription());
            sb.append(    "</TD>\n");
            sb.append("  </TR>\n");
        }
        sb.append("</TABLE>\n");
        return sb.toString();

    }

   /**
    * Returns html string that contains reference to the given 
    * DefaultHtmlFile.
    */
    protected String createReference(Object link)  throws TestFileException {

        DefaultHtmlFile f = null;
        if (link instanceof DefaultHtmlFile) {
            f = (DefaultHtmlFile)link;
        } else {
            throw new TestFileException("Cannot create reference for " + link);
        }
        String href = detectHREF(f);
        int index = href.lastIndexOf(File.separator);
        if (index <= 0) {
            return super.createReference(f);
        }
        String ref = href.substring(0, index);
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(ref, File.separator);
        while (st.hasMoreElements()) {
            sb.append(st.nextToken());
            if (st.hasMoreElements()) {
                sb.append(" ");
            }
        }
        return "<a href=\"" + href + "\">" + sb.toString() + "</a> - " 
                + f.getTitle();
    }

    /**
     * Returns an instance of JCKHtmlFile object
     */
    protected Object createLink(File f) throws IOException {
        return new JCKHtmlFile(f);
    }


    /**
     * Returns a path to the full copyright notice file.
     */
    public String getCopyrightLink() {
        return copyrightLink;
    }

   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption copyrighOption = new StringOption("-copyrightLink", 
         "  -copyrightLink <link>   link to the copyrigth file",
         OPTIONAL);

    /**
     * Registers -copyrightLink option
     */
    public void registerOptions() {
        super.registerOptions();
        addOption(copyrighOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (copyrighOption.isSet()) {
            copyrightLink = copyrighOption.getStringValue();
        }       
        super.applyOptionsValues();
    }

}
