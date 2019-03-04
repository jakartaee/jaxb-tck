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

package com.sun.tgxml.tools.testgen.processors.emitter;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.code.InlineSupportClass;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.util.CopyrightManager;

public class InlineSupportUtil {

    /**
     * This method looks in the passed TestGroup for 'InlineSupportClass'  
     * and 'InlineData' elements with specified 'TargetName' attribute.
     * When such element is found it writes its contents
     * to the file specified by this attribute.
     * For 'InlineSupportClass' element specified copyright (constructed from
     * the supplied source code control information), package
     * and imports will be put into the begin of generated file.
     * 
     * @param tg the TestGroup for processing
     * @param outputDir the name of output directory
     * @param scInfo the source code control information 
     * @param defaultCopyright default copyright notice 
     * @param packageName the name of package 
     * @param imports the list of requried imports 
     * 
     * @exception  TestFileException if IOException occured during
     *             the generation.
     */
    public static void generateInlines(
            TestGroup tg, 
            String outputDir,
            String scInfo, 
            String defaultCopyright, 
            String packageName,
            ArrayList imports
        ) throws TestFileException {

        InlineGenerator visitor = new InlineGenerator( 
                outputDir, scInfo, defaultCopyright,
                packageName, imports);
        visitor.visit_TestGroup(tg);

    }

    static class InlineGenerator {
        String outputDir;
        String scInfo;
        String defaultCopyright;
        String packageName;
        ArrayList imports;
        String outputDirPath;
        
        public InlineGenerator(
                String outputDir,
                String scInfo, 
                String defaultCopyright, 
                String packageName,
                ArrayList imports
            ) throws TestFileException {

            this.outputDir = outputDir;
            this.scInfo = scInfo;
            this.defaultCopyright = defaultCopyright;
            this.packageName = packageName;
            this.imports = imports;
            outputDirPath = (new File(outputDir)).getAbsolutePath();
        }

        public void visit_TestGroup(TestGroup tg)
                throws TestFileException {
            if (tg == null) 
                return;

            visit_CodeSet(tg.getCodeSet());

            ArrayList librarys = tg.getLibraries();
            if (librarys != null) {
                for (Iterator it = librarys.iterator(); it.hasNext();) {
                    visit_CodeSet(((Library) it.next()).getCodeSet());
                }
            }    

            ArrayList testCases = tg.getTestCases();
            if (testCases != null) {
                for (Iterator it = testCases.iterator(); it.hasNext();) {
                    visit_CodeSet(((TestCase) it.next()).getCodeSet());
                }
            }    
        }
        
        public void visit_CodeSet(CodeSet cs) 
                throws TestFileException {
            if (cs == null) 
                return;

            ArrayList supportClasses = cs.getSupportClasses();
            if (supportClasses != null) {
                for (Iterator it = supportClasses.iterator(); it.hasNext();) {
                    visit_SupportClass((SupportClass)it.next());
                }
            }    

            ArrayList data = cs.getData();
            if (data != null) {
                for (Iterator it = data.iterator(); it.hasNext();) {
                    visit_Data((Data)it.next());
                }
            }    
        }


        public void visit_SupportClass(SupportClass sc) 
                throws TestFileException {

            if (sc == null || ! (sc instanceof InlineSupportClass))
                return;

            InlineSupportClass isc = (InlineSupportClass)sc;

            if (isc.isExport() && (isc.getSourceLang() == null
                          || "java".equals(isc.getSourceLang()))) {
                generateJavaFile(isc.getTargetName(), isc.getSource());
            }
        }
        
        public void visit_Data(Data d) throws TestFileException {

            if (d == null || !(d instanceof InlineData))
                return;
            InlineData id = (InlineData)d;
            String fileName = id.getTargetName();

            if (id.isExport()) {
                if (id.getType().isIOData()) {
                    String outputFilePath = outputDirPath + File.separator + fileName;
                    IODataLogger.getInstance().log(outputFilePath);
                }
                generateFile(fileName, id.getData());
            }
        }

        protected String getDefaultCopyright() {
            String s = "";

            if (defaultCopyright != null && defaultCopyright.length() > 0) {
                s += "/*\n";
                StringTokenizer st = new StringTokenizer(defaultCopyright, "\n");

                while (st.hasMoreTokens()) {
                    s += " * " + st.nextToken() + "\n";
                }
                s += " */";
            }
            return s;
        }

        public void generateJavaFile(String fileName, String fileData) 
               throws TestFileException
        {
            String crn = "";
            String full_path = outputDir + "/" + fileName;
            
            try {
                crn = CopyrightManager.getCopyright(
                    CopyrightManager.CRN_JAVA_SHORT,
                    scInfo
                );
                if (crn != null) {
                    CopyrightManager.logCrnOk("(InlineSupportUtil) " + full_path);
                } else {
                    crn = getDefaultCopyright();
                }
            } catch (CopyrightManager.Fault f) {
                CopyrightManager.errorSkipCRN("(InlineSupportUtil) " + f.getMessage(), full_path);
                crn = getDefaultCopyright();
            }
            StringBuffer sb = new StringBuffer();
            sb.append(crn + "\n\n");

            if (packageName != null) {
                sb.append("package " + packageName + ";\n\n");
            }
            if (imports != null) {
                for (Iterator it = imports.iterator(); it.hasNext();) {
                    sb.append("import " + it.next() + ";\n");
                }
                sb.append("\n");
            }
            if (fileData != null) {
                sb.append(fileData);
            }
            writeFile(new File(outputDir, fileName), sb.toString());
        }


        public void generateFile(String fileName, String fileData) 
                throws TestFileException
        {
            File f = new File(outputDir, fileName);
            String s = CopyrightManager.insertCopyright(
                fileData,
                f,
                scInfo
            );
            writeFile(f, s);
        }

        protected void writeFile(File outputFile, String fileData)
            throws TestFileException
        {
            try {
                MiscUtils.writeTextFile(outputFile, fileData);
            } catch (IOException e) {
                String s = "cannot write file " + outputFile.getAbsolutePath();
                throw new TestFileException(s + ": " + e.getMessage());
            }
        }
    }
} 
