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

package com.sun.tgxml.tools.testgen.processors.parser;

import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.processors.parser.IRParser;
import com.sun.tgxml.tjtf.processors.parser.XMLParser;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.File;
import com.sun.tgxml.tools.indexgen.processors.parser.TestSuiteParserFactory;
import com.sun.tgxml.tjtf.processors.parser.ParserFactory;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import java.io.IOException;
import org.xml.sax.SAXException;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.util.IR;

public class MiddleWareXMLParser extends StandardOptionHandler implements IRParser {
    private Shell m_shell = null;
    private String m_outputDir = ".";
    private Hashtable parsersHash;
    
    public MiddleWareXMLParser() {
        parsersHash = new Hashtable();
    }
    
    protected XMLParser getParser(String key) throws TestFileException {
        XMLParser result = null;
        
        if (parsersHash.contains(key)) {
            result = (XMLParser)parsersHash.get(key);
        } else {
            try {
                result = createParser(key);
            } catch (IOException ioe) {
                result = null;
            }
            
            
            parsersHash.put(key, result);
        }
        
        if (result == null) {
            throw new TestFileException(LibResHandler.getResStr("testgen.error.parsernotfound", key));
        }

        return result;
            
    }
    
    protected XMLParser createParser(String key) 
            throws TestFileException, 
            IOException {
        if (key.equals("doc")) {
            return TestSuiteParserFactory.createTestSuiteParser();
        } else if (key.equals("test")) {        
            return ParserFactory.createDefaultXMLParser();
        }
        
        return null;
    }
    
    protected String getKey(File file) {
        String name = file.getName();
        if (name.endsWith(".tdoc.xml")) {
            return "doc";
        } else if (name.endsWith(".test.xml") || name.endsWith(".lib.xml")) {
            return "test";
        }
        return "";
    }
    
    public IRObj[] parse(java.io.File[] files)
            throws TestFileException,
            IOException {
         IRObj[] result = new IRObj[files.length];
         for (int i=0; i<files.length; i++) {
             result[i] = parse(files[i], null);
         }
         return result;
    }
         
    public IRObj parse(java.io.File file, String tckSourceRoot)
        throws TestFileException,
        IOException {
        XMLParser parser = getParser(getKey(file));
        IRObj retVal;
        try {
           retVal = (tckSourceRoot == null) ? parser.parse(file) : parser.parse(file, tckSourceRoot);
           if (!(retVal instanceof TestSuite)) {
               setOutputDir(retVal, file);
           } else {
               String fileName = file.getName();
               String id = fileName.substring(0,  
                   fileName.length() - ".tdoc.xml".length());
               ((TestSuite)retVal).setID(id);
           }
        } catch (SAXException saxe) {
            saxe.printStackTrace();
            throw new TestFileException(LibResHandler.getResStr("testgen.error.parsefail", file.toString()));
        }
        return retVal;
    }

    // sublcasses may override this method to set output dir dependent
    // on source xml file
    protected void setOutputDir(IRObj tree, File srcFile) {
        setOutputDir(tree);
    }

    protected void setOutputDir(IRObj tree) {
        if (tree instanceof TestItem) {
            setOutputDir((TestItem)tree, m_outputDir); 
        }
    }
     
    protected void setOutputDir(TestItem ti, String value) {
        IR.setAttrElem(ti, "OutputDir", (value!=null) ? value : "");
    }
       
            
    public Shell getShell() throws TestFileException {
        return m_shell;
    }
    
    public void setShell(Shell shell) {
        m_shell = shell;
    }
    
  /** 
    *  Sets the Validator
    * <p>
    *  do nothing
    * @param validator The validator to set.
    */ 
    public void setValidator(IRValidator validator) {
	}

   /** 
    * <p>
    * @return null 
    */ 
    public IRValidator getValidator() {
        return null;
	}

   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption outputOption = new StringOption("-o", 
        "  -o <outputdir> output directory");


    /**
     * Registers -o option
     */
    public void registerOptions() {
         addOption(outputOption);
         super.registerOptions();
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {	   

        if (outputOption.isSet()) {
            m_outputDir = outputOption.getStringValue();
        }

        super.applyOptionsValues();
    }

    /**
     * returns output dir, passed via "-o" option
     */
    public String getOutputDir() {
        return m_outputDir;
    }

}
