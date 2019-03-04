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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.parser.IRParser;
import com.sun.tgxml.tjtf.processors.validator.IRValidator;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.PrefixOption;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tools.jmppconv.processors.util.Arguments;
import com.sun.tgxml.tjtf.resources.LibResHandler; 
import com.sun.tgxml.tjtf.tools.BuildProperties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JmppTDParser extends StandardOptionHandler implements IRParser {
    protected IRGenerator       theIRGenerator;
    private String              m_workingDirName="";
    private String              m_outputDirName="";
    protected String            className = "";
    protected String            packagePrefix =
           "com.sun.tgxml.tools.jmppconv.processors.parser";
    private Arguments           parserOptions;
    
    /**  The Shell this parser belongs to */
    private Shell               m_shell;
    
    static final String exToken = "!";

    public JmppTDParser() {
        parserOptions = new Arguments();
        
    }
    
    public IRObj[] parse(java.io.File[] files) 
            throws TestFileException, java.io.IOException {
        if (files.length > 1) {
            throw new TestFileException(
                    LibResHandler.getResStr("jmppconv.error.multiplefiles"));
        }
        if (theIRGenerator == null) {
            theIRGenerator = createIRGenerator(packagePrefix,
                        getIRGeneratorClassName(files[0]));
        }

        theIRGenerator.setProperties(parserOptions.clone("-P"));
                
        ArrayList result = theIRGenerator.generate(files[0]);
        
        Object[] temp = result.toArray();
        IRObj[] tempRes = new IRObj[temp.length];
        for (int i=0; i<temp.length; i++) {
            tempRes[i] = (IRObj)temp[i];
        }
        
        return tempRes;
    }

    public IRObj[] parse(java.io.InputStream[] inputs)
              throws TestFileException, java.io.IOException {
        throw new TestFileException(
                LibResHandler.getResStr("jmppconv.error.inputstream"));
    }
                     

    protected String getIRGeneratorClassName(File inputFile)
            throws TestFileException{
        FileReader     fr;
        BufferedReader br;
        String         firstLine = null;
        String         className = null;
        
        try {
            fr = new FileReader(inputFile);
            br = new BufferedReader(fr);
            while((firstLine = br.readLine()) != null
                    && firstLine.trim().equals("")) {
            }
        } catch (FileNotFoundException fnfe) {
            throw new TestFileException(
                LibResHandler.getResStr("jmppconv.error.filenotfound", 
                inputFile.toString()));
        } catch (IOException ioe) {
            throw new TestFileException(
                LibResHandler.getResStr("jmppconv.error.fileread", 
                inputFile.toString()));
        }
        
        //can't create IRGenerator
        if (firstLine == null) return null;
        
        int start = firstLine.indexOf(JmppTDParser.exToken);
        if (start <0) return null;
        
        StringTokenizer st = 
                new StringTokenizer(firstLine.substring(start+1));
        if (st.countTokens() > 0) className = st.nextToken();
    
        return className;
    }
    
    /**
     * Creates IRGenerator by 'genClass' and 'packageName'.
     * If IRGenerater class name for 'genClass' is defined in the 
     * build properties an instance of specified class will be returned.
     * Otherwise the IRGenerater class name will be calculated as
     * 'packageName' + "." + 'genClass'
     */
    protected IRGenerator createIRGenerator(String packageName, String genClass)
            throws TestFileException {

        if (genClass == null) {
            throw new TestFileException(
                LibResHandler.getResStr("jmppconv.error.genclass", genClass));
        }


        String fqClassName = BuildProperties.getPrefixString(
                "jmppconv", "parser." + genClass, null);
        // check whether IRGenerater class name is not defined 
        // in the build properties 
        if (fqClassName == null) {
            fqClassName = packageName + "." + genClass;
        }

        return createIRGenerator(fqClassName);

    }

    /**
     * Creates IRGenerator by fully qualified 'fqClassName'
     */
    protected IRGenerator createIRGenerator(String fqClassName)
            throws TestFileException {
        IRGenerator    result    = null;               
        try {
            result = (IRGenerator)Class.forName(fqClassName).newInstance();
        } catch (Exception e) {
            throw new TestFileException(
               LibResHandler.getResStr("jmppconv.error.genclass", fqClassName));
        }
        
        return result;

    }

    
   /**
    * Sets the Shell that owns this parser.
    * <p>
    * @param shellClass The shell that owns this parser.
    */
    public void setShell(Shell shellClass) {
        m_shell = shellClass;
    }

   /**
    * Gets the Shell that owns this parser.
    * <p>
    * @return The shell that owns this parser.
    */
    public Shell getShell() throws TestFileException {
        return m_shell;
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

     StringOption workdirOption = new StringOption("-w", 
         "  -w <workdir> working directory");

     StringOption fnOption = new StringOption("-fn", 
         "  -fn <classname> parser class name");

     StringOption ppOption = new StringOption("-pp", 
         "  -pp <packagename> parser class name package");

     PrefixOption POption = new PrefixOption("-P",
         "  -P<optonName[=value]>   special parser options");




    /**
     * Registers -o, -w, -fn, -pp, -P options
     */
    public void registerOptions() {
         addOption(outputOption);
         addOption(workdirOption);
         addOption(fnOption);
         addOption(ppOption);
         addOption(POption);
         super.registerOptions();
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {       

        if (fnOption.isSet()) {
            className = fnOption.getStringValue();
        }

        if (ppOption.isSet()) {
            packagePrefix = ppOption.getStringValue();
        }

        if (workdirOption.isSet()) {
            m_workingDirName = workdirOption.getStringValue();
            parserOptions.put("-w", m_workingDirName);
        }

        if (outputOption.isSet()) {
            m_outputDirName = outputOption.getStringValue();
            parserOptions.put("-o", m_outputDirName);
        }

        //special parser options
        if (POption.isSet()) {
            String[] ops = (String[])POption.foundSwitches().toArray(new String[0]);

            for (int i = 0; i < ops.length; i++) {
                int delim = ops[i].indexOf("=");
                if (delim < 0) {
                    parserOptions.put(ops[i],"true");
                } else {
                    parserOptions.put(ops[i].substring(0,delim), 
                                      ops[i].substring(delim+1));
                }
            }
        }

        // create IRGenereator if className is defined
        if (className != null && !className.trim().equals("")) {
            String fqClassName = className;
            // add packagePrefix to the className is defined
            // (if packagePrefix is not defined className is treated 
            // as fully qualified)
            if (packagePrefix != null && !packagePrefix.trim().equals("")) {
                 // remove last '.' from the package prefix if needed
                 if (packagePrefix.endsWith(".")) {
                     packagePrefix = 
                         packagePrefix.substring(0, packagePrefix.length() - 1);
                 }
                 fqClassName = packagePrefix + "." + className;
            }
        
            try {
                theIRGenerator = createIRGenerator(fqClassName);
            } catch (Exception e) {
                throw new ParseArgumentException(e.toString());
            }
            theIRGenerator.setProperties(parserOptions.clone("-P"));
        }

        super.applyOptionsValues();
    }
}
