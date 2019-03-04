/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.util.Stack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.UTDVisitorBase;
import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.util.CopyrightManager;
import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;

/**
 * This class walks the components of a TestItem tree and installs 
 * ExternalData tags of type Resource.
 *
 */

public class ResourceInstaller {
    
    File m_classesDir = null;
           
    
    /** Creates a new instance of ResourceInstaller */
    public ResourceInstaller() {
    }
    
    /**
     * Returns the root directory into which resource files
     * will be placed (AKA classes directory).
     *
     * @return path to resources destination root diretory.
     */
    protected String getDestinationDir() {
        return BuildProperties.getString(getTCKDstDirVarName());
    }

    /**
     * Returns the name of the system property which contains
     * the value for the resources destination root directory
     *
     * @return name of system property
     */
    protected String getTCKDstDirVarName(){
        return "tck.classes.dir";
    }
    
    /**
     * Returns the default package name for libraries. When installing a 
     * resource file for a library, if the package for the  library can not be
     * determined, this value is used.
     *
     * @return default library package
     */
    protected String getDefaultLibPackage () {
        return "javasoft.sqe.tests";
    }
    
    /* Will walk the test item looking for external data of type resource
     * For each external data, the installData method will be
     * invoked
     */
    public void installResources(TestItem ti) throws TestFileException {
        

      
        UTDVisitorBase myVisitor = new UTDVisitorBase() {
            
            public void visit_ExternalData(ExternalData tdObject) throws TestFileException {
                handleData(tdObject);
            }
            
            public void visit_InlineData(InlineData tdObject) throws TestFileException {
                handleData(tdObject);                
            }
            
            private void handleData (Data data) throws TestFileException {
                if (data.getType() != null && data.getType().isResource()) {
                    TestItem ti = getRootTestItem();
                    if (ti == null) {
                        throw new TestFileException (LibResHandler.getResStr("testgen.error.resource.invalid_rootContainer"));
                    }
                    installResource(ti,data); 
                }
            }
            
            private TestItem getRootTestItem () {
                
                Stack s = getContextStack();
                for (int i = s.size() - 1; i >= 0; i--) {
                    Object o = s.get(i);
               	    if (!(o instanceof TestGroup) && !(o instanceof Library)) {
                        continue;
                    }
                    return (TestItem)o;
                }
                return null;
            }
            
            
            private void installResource(TestItem ti, Data dt) throws TestFileException {
                           
                File sourceDir = null;
                String sourcePath = IR.getAttrElem(IR.SourcePathAttrElemName, ti);
                File destFile = null;
                String pkg = null;
                
                //Set up classDir
                String classDirPath = getDestinationDir();
                if (classDirPath == null || classDirPath.trim().equals("")) {
                    throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.no_outputdir", getTCKDstDirVarName()));
                }
                m_classesDir = new File(classDirPath);
                if  (!m_classesDir.exists()) {
                    MiscUtils.mkdirs(m_classesDir);
                }
                
                //get location of directory in which source resource file exists
                if (sourcePath == null) {
                    throw new TestFileException(LibResHandler.getResStr(
                        "testgen.error.no_sourcepath", ti.getID()));
                } else {
                    sourceDir = new File(sourcePath).getParentFile();
                }
                

                if (ti instanceof TestGroup) {
                    TestGroup tg = (TestGroup) ti;
                    pkg=MiscUtils.getParentPackage(tg.getTGAttributes().getExecuteClass());
                }
                if (ti instanceof Library) {
                    Library l = (Library) ti;
                    if (!LibUtils.isNoExports(l)) {
                        pkg = LibUtils.detectPackageName(l);
                    }
                    if (pkg == null || pkg.equals("")){
                        pkg = getDefaultLibPackage();
                    }
                    
                
                }
                
                if (pkg == null || pkg.trim().equals("")) {
                        throw new TestFileException(LibResHandler.getResStr(
                        "testgen.error.resource.no_pkg"));               
                }
                
                try {
                    if (dt instanceof ExternalData) {
                        ExternalData ed = (ExternalData)dt;
                        String sourceName = ed.getSourceName();
                        if (sourceName == null || sourceName.trim().equals("")) {
                            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.no_sourcefile_external"));
                        }                
                        File sourceFile = new File(sourceDir, sourceName);
                        destFile = new File(m_classesDir, pkg.replace('.', File.separatorChar) 
                            + File.separator + sourceName);      
                        installResourceFile(sourceFile, destFile);
                        
                    }
                
                    if (dt instanceof InlineData) {
                        InlineData id = (InlineData) dt;
                        String targetName = id.getTargetName();
                        if (targetName == null || targetName.trim().equals("")) {
                            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.no_targetname_inline"));
                        }
                        destFile = new File(m_classesDir, pkg.replace('.', File.separatorChar) 
                            + File.separator + targetName);
                        installInlineResourceFile(id, destFile);
                    }
                }
                catch (IOException ioe) {
                            ioe.printStackTrace();
                            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.ioerror_install",
                                    destFile.getPath(), ioe.getMessage()));
                }

                
                
            }
        };
        
        //visit TestItem
        myVisitor.visit(ti);
     }   
    
   
    
    /**
     * Install a source resource file into the destination directory.
     *
     * @throws TestFileException if either source file of destination file are null.
     */
    public void installResourceFile (File sourceFile, File destFile) throws IOException, TestFileException {
        
        if (sourceFile == null) {
            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.nullsrc_install"));
        }

        if (destFile == null) {
            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.nulldest_install"));
        }
        
        if (!sourceFile.getCanonicalFile().equals(destFile.getCanonicalFile())) {
                MiscUtils.copyFile(sourceFile, destFile);
        }  
        
    }
    
    /**
     * Install a inline resource file into the destination directory.
     *
     * @throws TestFileException if either destination file are null or data are null.
     */
    public void installInlineResourceFile (InlineData dt, File destFile) throws IOException, TestFileException {
        if (dt == null) {
            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.nulldata_install"));
        }

        if (destFile == null) {
            throw new TestFileException(LibResHandler.getResStr("testgen.error.resource.nulldest_install"));
        }

	MiscUtils.mkdirs(destFile.getParentFile());
        
        FileWriter os = new FileWriter(destFile);
        os.write(dt.getData().trim());
        os.close();
    }
    
}
