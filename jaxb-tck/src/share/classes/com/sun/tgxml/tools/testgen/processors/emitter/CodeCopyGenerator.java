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

import com.sun.tgxml.tjtf.*;
import com.sun.tgxml.tjtf.api.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.tools.*;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.util.CopyrightManager;
import com.sun.tgxml.util.IR;

import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.api.*;
import com.sun.tgxml.tools.elgen.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileWriter;
import java.util.Vector;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;


public class CodeCopyGenerator extends StandardOptionHandler 
        implements Generator {
   
    /**
     * Source Directory
     */
    File sourceDir = null;
    
    /**
     *Output directory
     */
    File outputDir = null;

    private byte[] buffer = new byte[1024 * 32];

    public CodeCopyGenerator() {
    }

    
    public void setExcludeListCollector(ExcludeListCollector collector){
    }
    
    public void setProperties(Properties props){        
    }

    public Shell getShell() {
        return null;
    }

    public void setShell(Shell shell){}

    /**
     *  Emit the IR's into a set of File(s).
     */
    public void generate(IRObj[] trees) throws TestFileException {
        int numIRs = trees.length;
        
        for (int i=0; i < numIRs; i++) {
            generate(trees[i]);
        }
        
    }
       
    public void generate (IRObj root) throws TestFileException  {
        if (root instanceof com.sun.tgxml.tools.indexgen.api.TestSuite)
            return;

        TestItem tg = (TestItem) root;
        setSourceDir(tg);
        setOutputDir(tg);
        MiscUtils.mkdirs(outputDir);

        // Create a visitor class to get to a ExternalSupportClass
        UTDVisitorBase myVisitor = new UTDVisitorBase() {
            
            /**
             * visit a ExternalSupportClass object.
             * <p>
             * @param obj a TestCase.
             * @exception Throws TestFileException.
             */
            public void visit_ExternalSupportClass(ExternalSupportClass tdObject) 
                    throws TestFileException {

                TestCase tc = getParentTestCase();
                String var_name = tc == null ? null : tc.getVarName();
                String fname = tdObject.getSourceName();
                String out_name = LibUtils.removeVariantNameFromSource(fname, var_name);

                try {
                    copy(fname, out_name, skipCrn());
                } catch (IOException e) { 
                    throw new TestFileException(LibResHandler.getResStr(
                            "testgen.error.codecopy", fname));
                }
                
                super.visit_ExternalSupportClass(tdObject);
            }

            public void visit_ExternalData(ExternalData tdObject) 
                    throws TestFileException {

                TestCase tc = getParentTestCase();
                String var_name = tc == null ? null : tc.getVarName();
                String fname = tdObject.getSourceName();            
                try {
                    if (tdObject.getType() != null 
                        && tdObject.getType().isIOData()) {
                        String out_name =
                            LibUtils.removeVariantNameFromSource(fname,
                                                                 var_name);
                        String outputFilePath = outputDir.getAbsolutePath() + File.separator + out_name;
                        IODataLogger.getInstance().log(outputFilePath);
                        copy(fname, out_name, skipCrn());
                    }                    
                } catch (IOException e) { 
                    e.printStackTrace();
                    throw new TestFileException(LibResHandler.getResStr(
                            "testgen.error.codecopy", fname));
                }
                
                super.visit_ExternalData(tdObject);
            }

            private TestCase getParentTestCase() {
                Stack s = getContextStack();

                for (int i = s.size() - 1; i >= 0; i--) {
                    Object o = s.get(i);

               	    if (!(o instanceof TestCase)) {
                        continue;
                    }
                    return (TestCase)o;
                }
                return null;
            }

            private TestGroup getParentTestGroup() {
                Stack s = getContextStack();

                for (int i = s.size() - 1; i >= 0; i--) {
                    Object o = s.get(i);

               	    if (!(o instanceof TestGroup)) {
                        continue;
                    }
                    return (TestGroup)o;
                }
                return null;
            }

            private boolean skipCrn() {
                TestCase tc = getParentTestCase();
                boolean skip_crn =
                    tc != null &&
                    IR.getAutoGeneratedAttrElem(tc.getTCAttributes()) != null;

                if (!skip_crn) {
                    TestGroup tg = getParentTestGroup();
                    skip_crn =
                        tg != null &&
                        IR.getAutoGeneratedAttrElem(tg.getTGAttributes()) != null;
                }
                return skip_crn;
            }

            private String getParentTestCaseVarName() {
                TestCase tc = getParentTestCase();
                return tc == null ? null : tc.getVarName();
            }
        };
        // visit the clone
        myVisitor.visit(tg);

    }

    protected void copy(String name) throws IOException {
        String src = sourceDir.getAbsolutePath() + File.separator + name;
        String dst = outputDir.getAbsolutePath() + File.separator + name;
        copy(src, dst, false);
    }

    /**
     * Copies given file. Source directory is sourceDir, destination directory
     * is outputDir.
     *
     * @param src_name source file name
     * @param dst_name destination file name
     * @param skip_crn whether to skip CRN insertion
     * @throws java.io.IOException
     */
    protected void copy(String src_name,
                      String dst_name,
                      boolean skip_crn)
        throws IOException
    {
        File src = new File(sourceDir, src_name);
        File dst = new File(outputDir, dst_name);

        if (!src.getCanonicalFile().equals(dst.getCanonicalFile())) {
            if (skip_crn) {
                MiscUtils.copyFile(src, dst);
            } else {
                CopyrightManager.updateCopyright(src, dst);
            }
        }
    }

    protected void setSourceDir(TestItem tg) throws TestFileException {
        String sourcePath = IR.getAttrElem(IR.SourcePathAttrElemName, tg);
        if (sourcePath == null) {
            throw new TestFileException(LibResHandler.getResStr(
                    "testgen.error.no_sourcepath", tg.getID()));
        } else {
            sourceDir = new File(sourcePath).getParentFile();
        }
    }

    protected void setOutputDir(TestItem tg) throws TestFileException {
        String out = IR.getAttrElem("OutputDir", tg);
        if (out == null) {
            throw new TestFileException(LibResHandler.getResStr(
                    "testgen.error.no_outputdir", tg.getID()));
        } else {
            outputDir = new File(out);
        }
    }

}

