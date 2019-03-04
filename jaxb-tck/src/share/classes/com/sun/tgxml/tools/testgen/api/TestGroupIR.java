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

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

public class TestGroupIR implements HtmlTestIR {

    private String id = null;
    private String description = null;
    private String comments = null;
    private ArrayList testCases = null;
    private StringBuffer tcListSection = null;
    private StringBuffer tcDescrSection = null;
    private boolean anchorRequired = true;
    private TestDescriptionIR tdIR = null;
    
    public TestGroupIR(String id) {
        this.id = id;
    }
   

   /**
    * Returns html representation of of the TestGroup 
    */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\n");

        if (isAnchorRequired()) {
            sb.append("\n<p>\n");
            sb.append("<A NAME=\"" + id() + "\"></A>\n");
        }

        if (description != null) {
            sb.append(description);
        }

        try {
            prepareTCSections();
        } catch (TestFileException e) {
            // should never arise
            tcListSection = new StringBuffer();
            tcDescrSection = new StringBuffer();
        }

        sb.append((Object) tcListSection);

        if (tdIR != null)
            sb.append(tdIR.toString());

        sb.append((Object) tcDescrSection);

        if (comments != null) {
            sb.append("<!-- " + comments + "-->");
        }
        return sb.toString();

    }

    /**
     * sets values for tcListSection and tcDescrSection variables
     */
    private void prepareTCSections() throws TestFileException {
        tcListSection = new StringBuffer();
        tcDescrSection = new StringBuffer();
        if (testCases == null || testCases.size() == 0) {
            // no testcases
            return;
        }
        tcListSection.append("\nTest cases included:<p>\n");
        for (Iterator it = testCases.iterator(); it.hasNext();) {
            TestCase tc = (TestCase)it.next();
            StringBuffer tcDoc = getTCDoc(tc);

            if (tcDoc != null) {
                if (tcDescrSection.length() == 0) {
                    tcDescrSection.append("\nTest cases description:<p>\n");
                }
                tcDescrSection.append((Object) tcDoc);
                tcListSection.append(tcDescrLink(tc.getID()));
            } else {
                tcListSection.append(tc.getID());
            }
            tcListSection.append(", ");
        }
        // replace last ", " with "."
        tcListSection.replace(tcListSection.length() - 2,
                tcListSection.length() - 1, ".");

    }

    /**
     * Returns documentation of testcase to be placed in html file
     * and null if testcase does not contains documentation.
     */
    private StringBuffer getTCDoc(TestCase tc)  throws TestFileException {
        TestCaseDocumentation tcdoc = tc.getTCDocumentation();
        if (tcdoc == null)
            return null;
        String tcTitle = trim(tcdoc.getTitle());
        String tcDescription = trim(tcdoc.getDescription());
        String tcAuthors = null;
        String tcAuthorKey = "author";
        ArrayList authors = tcdoc.getAuthors();
        if (authors != null) {
            for (Iterator it = authors.iterator(); it.hasNext();) {
                String a = trim((String)it.next());
                if (a != null) {
                    if (tcAuthors == null) { 
                        tcAuthors = a;
                    } else {
                        tcAuthors = tcAuthors + ", " + a;
                        tcAuthorKey = "authors"; // more than one author
                    }
                }
            }
        }

        if (tcTitle == null && tcDescription == null && tcAuthors == null)
            return null; // no usesful documentation

        StringBuffer sb = new StringBuffer();
        String tcid = trim(tc.getID());
        sb.append("<A NAME=\"" + tcAnchorName(tcid) + "\"></A>\n");
        sb.append("TestCase: <b>" + tcid + "</b>\n");
        sb.append("<table border=\"1\">\n");
        if (tcTitle != null) 
            sb.append(tcDesrcRow("title", tcTitle));
        if (tcDescription != null) 
            sb.append(tcDesrcRow("description", tcDescription));
        if (tcAuthors != null) 
            sb.append(tcDesrcRow(tcAuthorKey, tcAuthors));
        sb.append("</table>\n<p>\n");
        return sb;
    }

    /**
     * Returns table row of testcase description
     */
    private String tcDesrcRow(String key, String value) {
        return "  <tr>\n" + 
               "      <th scope=\"row\">" + key + "</th>\n" + 
               "      <td>" + value + "</td></tr>\n";
    }

    /**
     * Returns testcase anchor name by its id
     */
    private String tcAnchorName(String tcid) {
        return "tc_" + tcid;
    }

    /**
     * Returns a string that contains a link on testcase documentaion
     */
    private String tcDescrLink(String tcid) {
        return "<a href=\"#" + tcAnchorName(tcid) + "\">" + tcid + "</a>";
    }


    /**
     * Returns null if str is null or empty.
     * Otherwise returns trimmed string
     */
    private String trim(String str) {
        if (str != null) {
            String s = str.trim();
            if (s.length() > 0)
                return s;
        }
        return null;       
    }


   
    /**
     * Access method for the description property.
     *
     * @return   the current value of the description property
     */
    public String getDescription() {
        return description;
    }
   
    /**
     * Sets the value of the description property.
     *
     * @param aDescription the new value of the description property
     */
    public void setDescription(String aDescription) {
        description = aDescription;
    }
  

    /**
     * Add the value to the description property.
     *
     * @param aDescription the  value of the description property
     */
    public void addDescription(String aDescription) {
        if (description == null) {
            description = aDescription;
        } else {
            description += aDescription;
        }
    }
   

    /**
     * Access method for the comments property.
     *
     * @return   the current value of the comments property
     */
    public String getComments() {
        return comments;
    }
   
    /**
     * Sets the value of the comments property.
     *
     * @param aComments the new value of the comments property
     */
    public void setComments(String aComments) {
        comments = aComments;
    }

    /**
     * Add the value to the comments property.
     *
     * @param aComments the  value of the comments property
     */
    public void addComments(String aComments) {
        if (comments == null) {
            comments = aComments;
        } else {
            comments += aComments;
        }
    }

   /**
    * Access method for the testgroup id.
    *
    * @return   the testgroup id
    */
    public String id() {
        return id;
    }
    
    /**
     * Sets requirenment of the html anchor generation 
     *
     * @param isNeeded if true the html anchor will be always inserted, 
     *        if false the anchor will be inserted only in case
     *        when TestDescription contains two or more testcases 
     *        or html file contains two or more TestDescriptions.
     *      
     */
    public void setAnchorRequired(boolean isNeeded) {
        anchorRequired = isNeeded;
    }

    /**
     * @return true if the html anchor will be always inserted 
     *        false if the anchor will be inserted only in case
     *        when TestDescription contains two or more testcases 
     *        or html file contains two or more TestDescriptions.
     */
    public boolean isAnchorRequired() {
        return anchorRequired;
    }

    /**
     * Adds the new testCase to the list of TestCase.
     *
     * @param tc  new TestCase
     */
    public void addTestCase(TestCase tc) {
        if (testCases == null) {
            testCases = new ArrayList();
        }
        testCases.add(tc);
    }

    /**
     * Sets the new value of the testCase list.
     *
     * @param tcList the new TestCase list
     */
    public void setTestCases(ArrayList tcList) {
        testCases = tcList;
    }

    /**
     * Returns the testCase list.
     */
    public ArrayList getTestCases() {
        return testCases;
    }


    /**
     * Sets the new value of the TestDescriptionIR.
     *
     * @param tdIR the new TestDescriptionIR
     */
    public void setTestDescriptionIR(TestDescriptionIR tdIR) {
        this.tdIR = tdIR;
    }

    /**
     * Returns the TestDescriptionIR.
     */
    public TestDescriptionIR getTestDescriptionIR() {
        return tdIR;
    }

}
