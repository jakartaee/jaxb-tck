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

import org.xml.sax.*;
import com.sun.tgxml.tjtf.*;

import com.sun.tgxml.tjtf.tools.*;

import org.xml.sax.*;
import com.sun.tgxml.tjtf.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.processors.taghandlers.*;
import com.sun.tgxml.tjtf.processors.parser.*;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;

import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.api.impl.TestSuiteImpl;
import com.sun.tgxml.tools.indexgen.processors.taghandlers.impl.*;
import com.sun.tgxml.tools.indexgen.processors.parser.*;
import com.sun.tgxml.util.CopyrightManager;
import com.sun.tgxml.util.CopyrightUtil;
import com.sun.tgxml.util.MiscUtils;



import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileWriter;
import java.util.Vector;
import java.util.Enumeration;
import java.util.StringTokenizer;

import com.sun.tgxml.tjtf.tools.ToolBase;

public class DefaultHtmlGenerator
    extends ToolBase
    implements HtmlGenerator, CopyrightManager.Constants
{
   
   private static final String CtStr_ToolName = "HtmlGenerator";
   protected String copyright;   
    private String scInfo;
  

  /**
    *  Program entry
    *
    *  This replaces the main in ToolBase (sub-classes XMLToolBase)
    */
    public static void main(String args[]) {
        DefaultHtmlGenerator c = new DefaultHtmlGenerator(System.out, System.err);
        System.exit(c.run(args));
    }
    

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Public Methods (outside world can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public DefaultHtmlGenerator( PrintStream out, PrintStream err) {
        this(out, err, CtStr_ToolName);
    }

    public DefaultHtmlGenerator( PrintStream out, PrintStream err, String name)
    {
        super(out, err, name);
    }


    public void startTool() {
        copyright = BuildProperties.getPrefixString("indexgen",
                "html.copyrightNotice");
        try {
            TestSuite doc = parseXML(source);
            create(result, doc, links);
        } catch (TestFileException e) {
            reportErrorMsg(e.getMessage());
            setResultCode(ctInt_ErrorCode_Error);
        }
        for (Enumeration e =links.elements() ; e.hasMoreElements() ;) {
             System.out.println(((DefaultHtmlFile)e.nextElement()).getFile());
        }        
    }


    File result;
    File source;
 
    String resultDirectory = null;
    String backwardLink = null;
    Vector links = new Vector();


   /**
    * Creates specified html file from the doc.xml file.
    * Inserts links into generating html either as a list or as a table.
    * (The decision is based on contents format attribute)
    */
    public Vector create(File resultHTML,  TestSuite doc, Vector links)  
            throws TestFileException {
        result = resultHTML;
        File resultDirFile = result.getParentFile();
        if (resultDirFile != null) {
            try {
                resultDirectory = resultDirFile.getCanonicalPath();
            } catch (java.io.IOException e2) {
                throw new TestFileException (e2.getMessage());
            }
        } else {
            resultDirectory = null;
        }

        this.links = links;

        StringBuffer contents = new StringBuffer();
        contents.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
        contents.append("<html>\n");
        contents.append("    <head>\n");
        contents.append("        <title>" + createTitle(doc) + "</title>\n");
        contents.append("    </head>\n");
        contents.append("<body>\n");
        contents.append(createBackwardLink(backwardLink));
        contents.append("\n<hr>\n<p>\n");
        contents.append(createDescription(doc));
        contents.append("\n");
        contents.append(createTestLinks(doc, links));
        contents.append(createComments(doc));
        contents.append("\n<p><hr>\n");

        try {
            String crn = getCopyright();
            if (crn != null) {
                CopyrightManager.logCrnOk("(DefaultHtmlGenerator) " + resultHTML.getAbsolutePath());
            } else {
                crn = getDefaultCopyright();
            }
            contents.append(crn);
        } catch (CopyrightManager.Fault f) {
            String s = "(DefaultHtmlGenerator) " + f.getMessage();
            CopyrightManager.errorSkipCRN(s, resultHTML.getAbsolutePath());
            contents.append(getDefaultCopyright());
        }
        contents.append("\n</body>\n");
        contents.append("</html>\n");
        
        saveResult (resultHTML, contents.toString());
        
        //links.add(new DefaultHtmlFile(resultHTML, createTitle(doc)));
        try {
            links.add(createLink(resultHTML));
        } catch (IOException e) {
            throw new TestFileException(e.getMessage());
        }

        return links;
    }

    protected void saveResult(File resultHTML, String contents) 
            throws TestFileException {
        try {
            FileWriter f = new FileWriter(resultHTML);
            f.write(contents);
            f.close();
        } catch (IOException e){
            throw  new TestFileException ("Cannot create result file");
        }
    }

    private static String parseScInfo(File xmlFile) throws TestFileException, IOException {
        // this method is a workaround for #4964235
        final String SC_BEG_TAG = "<!--";
        final String SC_END_TAG = "-->";
        String text = MiscUtils.readTextFile(xmlFile);
        int i = text.lastIndexOf(SC_BEG_TAG);

        if (i >= 0) {
            int j = text.indexOf(SC_END_TAG, i);

            if (j < 0) {
                String msg = "bad sc info tags, file: ";
                CopyrightManager.error(msg + xmlFile.getAbsolutePath());
                return "NONE";
            }
            return text.substring(i + SC_BEG_TAG.length(), j).trim();
        }
        return null;
    }

    protected TestSuite parseXML(File xmlFile) throws TestFileException {
        try {
            setScInfo(parseScInfo(xmlFile));
            XMLParser parser = 
                (XMLParser)TestSuiteParserFactory.createTestSuiteParser();
 
            return (TestSuite)parser.parse(xmlFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new  TestFileException(e.getMessage());
        }
    }

   /**
    * Returns the value of "Title" element of the given xmlFile. If
    * "Title" is not set returns an empty string
    */    
    protected String createTitle(TestSuite xmlFile) throws TestFileException {
        String title = xmlFile.getTitle();
        return title == null ? "" : title;
    }

   /**
    * Returns the value of "Description" element of the given xmlFile. If
    * "Description" is not set returns an empty string
    */    
    protected String createDescription(TestSuite xmlFile)
            throws TestFileException {
        String desrc = xmlFile.getDescription();
        return desrc == null ? "" : desrc;
    }

   /**
    * Returns links to the tests. This method checks
    * the value of "Contents" element of the given xmlFile and 
    * invokes either createLinkList() or createLinkTable() method.
    */    
    protected String createTestLinks(TestSuite xmlFile, Vector links)
             throws TestFileException {
        String format = xmlFile.getContentsFormat();

        if (links.size() == 0 || format == null) 
            return "<p>\n";

        if (format.equals(TestSuite.LIST_FORMAT)) {
            return createLinkList(xmlFile, links);
        } else if (format.equals(TestSuite.TABLE_FORMAT)) {
            return createLinkTable(xmlFile, links);
        } else {
            // unknown Contents Format (should never occur)
            return "<p>\n";
        }
    } 

   /**
    * Returns links to the tests in the list format
    */
    protected String createLinkList(TestSuite xmlFile, Vector links)  
            throws TestFileException {

        StringBuffer sb = new StringBuffer();
        sb.append("\n<ul>\n");
        for (Enumeration e = links.elements() ; e.hasMoreElements() ;) {
            sb.append("    <li> ");
            sb.append(     createReference((DefaultHtmlFile)e.nextElement()));
            sb.append("\n");
        }
        sb.append("</ul>\n");
        return sb.toString();
    }

   /**
    * Returns links to the tests in the table format with only 
    * one column "Test". Subclasses may override this method
    * to implement the different table format.
    */
    protected String createLinkTable(TestSuite xmlFile, Vector links)
            throws TestFileException {

        StringBuffer sb = new StringBuffer();
        sb.append("<TABLE BORDER=1>\n");
        sb.append("  <TR>\n");
        sb.append("    <TH scope=\"col\">");
        sb.append(        "<center>Test</center>");
        sb.append(   "</TH>\n");
        sb.append("  </TR>\n");

        for (Enumeration e = links.elements() ; e.hasMoreElements() ;) {
            DefaultHtmlFile f = (DefaultHtmlFile)e.nextElement();
            sb.append("  <TR>\n");
            sb.append("    <TD>");
            sb.append(        createReference(f));
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
        return "<a href=\"" + detectHREF(f) + "\">" + f.getTitle() + "</a>";
    }

   /**
    * Returns href to the given html file.
    */
    protected String detectHREF(DefaultHtmlFile f) throws TestFileException {
        String href =  f.getFile().getPath();
        if(resultDirectory != null) {
            try {
                href = getRelativePath(resultDirectory, f.getFile().getCanonicalPath());
            } catch (java.io.IOException e2) {
                throw new TestFileException (e2.getMessage());
            }
        }
        return href;
    }

    /**
     * Returns an Object that represent an internal representation
     * of html file. DefaultHmtlGenerator uses DefaultHtmlFile
     * to represent html file. Subclasses may use alternative
     * classes.
     */
    protected Object createLink(File f) throws IOException {
        return new DefaultHtmlFile(f);
    }


   /**
    * Returns the value of "Comments" element of the given xmlFile. If
    * "Comments" is not set returns an empty string
    */    
    protected String createComments(TestSuite xmlFile) {
        String comm = xmlFile.getComments();
        return comm == null ? "" : "\n<h3>COMMENTS</h3>\n" + comm;
    }

   /**
    * Returns html string that contains reference to the backward link.
    */
    protected String createBackwardLink(String link) {
        if (link == null) 
            return "";
        return "<a href=\"" + link + "\">to upper index</a>";
    }


    private String getRelativePath (String to, String name) {


        String result=null;
        StringTokenizer toTokenizer = new StringTokenizer(to, File.separator);
        StringTokenizer nameTokenizer = new StringTokenizer(name, File.separator);

        while (toTokenizer.hasMoreTokens() && nameTokenizer.hasMoreTokens()) {
            String toTok = toTokenizer.nextToken();
            String nameTok = nameTokenizer.nextToken();
            if(!toTok.equals(nameTok)) {
                result = "../"  + nameTok;
            }
        }
        if(toTokenizer.hasMoreTokens()) { 
            if(result==null) {
                toTokenizer.nextToken();
                result="..";
            }
                
            for (int i = 0; i < toTokenizer.countTokens(); i++) {
                result = "../" + result;
            }
        }

        if(nameTokenizer.hasMoreTokens()) { 
            if(result==null) {
                result=nameTokenizer.nextToken();
            }
            while (nameTokenizer.hasMoreTokens()) {
                result+="/" + nameTokenizer.nextToken();
            }
        }
        return result;
    }

    /**
     * @return copyright string to be inserted into the html file
     */
    public String getCopyright() throws CopyrightManager.Fault {
        String crn = CopyrightManager.getCopyright(
              // anchor ID
            CRN_HTML_SHORT,
              // macros to expand
            new String[][] {
                { MACRO_SC_INFO,       getScInfo()        },
                { MACRO_FULL_CRN_LINK, getCopyrightLink() }
    }   
        );
        return crn;
    }

    /**
     * @return default copyright notice (used when {@link com.sun.tgxml.util.CopyrightManager}
     *     returns <code>null</code>).
     */
    public String getDefaultCopyright() {
        return CopyrightUtil.expandCopyrightMacro(copyright, getCopyrightLink());
    }
   
    /**
     * This default implementation returns an empty String.
     * Override in subclasses to return correct link.
     *
     * @return a path to the full copyright notice file.
     */
    public String getCopyrightLink() {
        return "";
    }

    public void setScInfo(String sc_info) {
        scInfo = sc_info;
    }

    public String getScInfo() {
        return scInfo;
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption contentsOption = new StringOption("-contents", 
         "  -contents <source.doc.xml> source .doc.xml file (obligatory)",
         OBLIGATORY);

    StringOption fileNameOption = new StringOption("-fileName", 
         "  -fileName <filename>    output file name (obligatory)",
         OBLIGATORY);

    StringOption backwardOption = new StringOption("-backward", 
         "  -backward <link>        backward link ",
         OPTIONAL);

    /**
     * Registers -contents, -fileName, -backward options
     */
    public void registerOptions() {

         super.registerOptions();

         addOption(contentsOption);
         addOption(fileNameOption);
         addOption(backwardOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {

        if (contentsOption.isSet()) {
            source = new File(contentsOption.getStringValue());
            if (!source.exists() || source.isDirectory()) {
                throw new ParseArgumentException("file does not exist or is a directory: " + source);
            }
        }

        if (fileNameOption.isSet()) {
            result = new File(fileNameOption.getStringValue());
            if (result.isDirectory()) {
                throw new ParseArgumentException("file is a directory: " + result);
            }
        }

        if (backwardOption.isSet()) {
            backwardLink = backwardOption.getStringValue();
        }

        for (int i = 0; i < operands.length; i++) {
            try {        
                links.add(createLink(new File(operands[i])));
            } catch (IOException e) {
                throw new ParseArgumentException(LibResHandler.getResStr("file.error.ioerror", operands[i]));
            }
        }
        super.applyOptionsValues();       
    }

    /** 
     * Sets OperandsValidator thats validates that at least one operand
     * is passed, operands end with ".html" and operands do not start with "-"
     */
    protected void setOperandsValidator() {
        String[] operandsUsageLines = {
            "Operands: ",
            "  [file1.html file2.htlm ...] "
        };
        operandsValidator = new DefaultOperandsValidator(0, Integer.MAX_VALUE, 
            "-", ".html", operandsUsageLines);
    }
    
    /**
     * Sets indexgen usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = 
            "Usage: " + getProgramName() + " [<options>] [file1.html file2.htlm ...]\n" + 
            "where options include:";
    }

}
