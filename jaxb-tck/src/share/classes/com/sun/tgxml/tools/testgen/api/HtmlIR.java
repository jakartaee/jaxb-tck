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

package com.sun.tgxml.tools.testgen.api;

import java.util.Vector;
import java.util.Enumeration;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.util.CopyrightUtil;

/**
 * Internal representation of generating html file.
 * toString() method returns the contents of html file.
 */
public class HtmlIR {

   private String header;
   private String title;
   private String copyright;
   private String copyrightLink;
   private String fileName = null;
   private Vector testsIR;
   private Vector comments;
   private Vector testedClasses;

   /**
    * Constructs new HtmlIR.
    * Reads copyright from BuildProperties.
    */
   public HtmlIR() {
       copyright = BuildProperties.getPrefixString("testgen",
               "html.copyrightNotice");
       testsIR = new Vector();
       comments = new Vector();
       testedClasses = new Vector();
   }

   /**
    * Returns string representation of the html file.
    */
   public String toString() {
       String contents =
           "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
           "<html>\n" +
           "<head>\n" +
           "<title>" + titleToString() + "</title>" +
           "\n</head>\n\n" +
           "<body>\n" +
                    headerToString() +
                    testTable() +
                    testToString() +
                    commentsToString() +
                    copyrightToString() +
           "\n</body>" +
           "\n</html>\n";

       return contents;
   }

    /**
     * Adds new testIR to the list of testIR
     */
    public void addHtmlTestIR(HtmlTestIR testIR) {
        testsIR.add(testIR);
    }

   /**
    * Calculates value of the html title. If list of tested class is not empty
    * returns "Test Specifications and Descriptions for class1, class2, ...".
    * If no one tested class is specified returns list of ids of testIRs
    * @return   the calculated value of the html title
    */
    public String titleToString() {
        if (title == null || title.equals("")) {
            StringBuffer defaultTitle = new StringBuffer();
            if (testedClasses.size() > 0) {
                defaultTitle.append("Test Specifications and Descriptions for ");
                for (Enumeration e = testedClasses.elements(); e.hasMoreElements();)
                {
                    defaultTitle.append(e.nextElement().toString());
                    if (e.hasMoreElements()) {
                        defaultTitle.append(", ");
                    }
                }
            } else {
                for (Enumeration e = testsIR.elements() ; e.hasMoreElements() ;) {
                    String testTitle = ((HtmlTestIR)e.nextElement()).id();
                    if (testTitle != null) {
                        defaultTitle.append(testTitle);
                        if (e.hasMoreElements()) {
                            defaultTitle.append(", ");
                        }
                    }
                }
            }
            return defaultTitle.toString();
        } else {
            return title.toString();
        }
    }

    /**
     * Adds a className to the list of tested classes, if className
     * is not null and not added yet
     */
    public void addTestedClass(String className) {
        if (className != null && !testedClasses.contains(className)) {
            testedClasses.add(className);
        }
    }

    /**
     * Removes a className from the list of tested classes if className
     * is not null and not added yet
     */
    public void removeTestedClass(String className) {
        if (className != null && testedClasses.contains(className)) {
            testedClasses.remove(className);
        }
    }


    /**
     * Adds a comment to this HTML
     */
    public void addComment(String comment) {
        comments.add(comment);
    }


    protected String headerToString() {
        if (getHeader() == null) {
            return "";
        }
        return getHeader() +
           "<p><hr>\n";
    }

    protected String copyrightToString() {
        String crn = getCopyright();

        if (crn == null) {
            return "";
        }
        String res = CopyrightUtil.copyrightMacroExpanded(crn) ?
            crn :
            CopyrightUtil.expandCopyrightMacro(crn, getCopyrightLink());
        return "\n" + res;
    }

    protected String commentsToString() {
        StringBuffer buf = new StringBuffer();
        for (Enumeration e = comments.elements() ; e.hasMoreElements() ;) {
            String comment = e.nextElement().toString();
            buf.append("\n<!-- ");
            buf.append(comment);
            buf.append(" -->");
        }
        return buf.toString();
    }

    protected String testTable() {
        if (testsIR.size() < 2)
            return "";
        StringBuffer table = new StringBuffer();
        table.append("<ul>\n");
        for (Enumeration e = testsIR.elements() ; e.hasMoreElements() ;) {
            String id = ((HtmlTestIR)e.nextElement()).id();
            table.append("    <li><a href=\"#" + id + "\">" +
                "<big><code>" + id + "</code></big></a>\n");
        }
        table.append("</ul>\n");
        return table.toString();
    }

    protected String testToString() {
        StringBuffer testDescr = new StringBuffer();
        boolean isAnchorAlwaysRequired = false;
        if (testsIR.size() > 1)
             isAnchorAlwaysRequired = true;

        for (Enumeration e = testsIR.elements() ; e.hasMoreElements() ;) {
            HtmlTestIR testIR = (HtmlTestIR)e.nextElement();
            if (isAnchorAlwaysRequired)
                testIR.setAnchorRequired(true);
            testDescr.append(testIR.toString());
            testDescr.append("\n\n<p><hr>\n");
        }
        return testDescr.toString();
    }


   /**
    * Access method for the header property.
    *
    * @return   the current value of the header property
    */
    public String getHeader() {
        return header;
    }


    /**
     * Sets the value of the header property.
     *
     * @param aHeader the new value of the header property
     */
    public void setHeader(String aHeader) {
        header = aHeader;
    }

   /**
    * Access method for the title property.
    *
    * @return   the current value of the title property
    */
    public String getTitle() {
        return title;
    }


    /**
     * Sets the value of the title property.
     *
     * @param title the new value of the title property
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Access method for the copyright property.
     *
     * @return   the current value of the copyright property
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the value of the copyright property.
     *
     * @param aCopyright the new value of the copyright property
     */
    public void setCopyright(String aCopyright) {
        copyright = aCopyright;
    }

    /**
     * Access method for the copyright link property.
     *
     * @return   the current value of the copyright link property
     */
    public String getCopyrightLink() {
        return copyrightLink;
    }

    /**
     * Sets the value of the copyright link property.
     *
     * @param link the new value of the copyright link property
     */
    public void setCopyrightLink(String link) {
        copyrightLink = link;
    }

    /**
     * Access method for the fileName property.
     *
     * @return   the relative file name of the html
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     *
     * @param name the relative file name of the html
     */
    public void setFileName(String name) {
        fileName = name;
    }


}
