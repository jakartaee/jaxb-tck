/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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
import com.sun.tgxml.tjtf.api.data.Data;
import com.sun.tgxml.tjtf.api.data.DataType;
import com.sun.tgxml.tjtf.api.data.ExternalData;
import com.sun.tgxml.tjtf.api.data.InlineData;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.*;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;


import com.sun.tgxml.tools.testgen.api.source.java.*;
import com.sun.tgxml.tools.elgen.*;

import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.util.CopyrightManager;
import com.sun.tgxml.util.IR;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Hashtable;

public class LibraryEmitter
    extends StandardOptionHandler
    implements Generator, CopyrightManager.Constants
{
    protected String fileSep = System.getProperty("file.separator");
    protected Shell m_shell;

    public LibraryEmitter() {
    }

    public void setExcludeListCollector(ExcludeListCollector collector){
    }
    
    public void setConfiguration(Hashtable args){
    }


    /**
     *  Emit the IR's into a set of File(s).
     */
    public void generate(IRObj[] trees) throws TestFileException, IOException {
        int numIRs = trees.length;
        
        for (int i=0; i < numIRs; i++) {
            generate(trees[i]);
        }
    }

    public void generate(IRObj root) throws TestFileException, IOException  {
        if (! (root instanceof Library)) {
             return;
        }

        Library lib = (Library) root;
        if (lib.isInline()) 
            return;

        String libID = lib.getID();
        String libVarID = lib.getVarID();
        String relOutputDir = getRelativeOutputDirectory(lib);

        // Create the list of java source files            
        Hashtable sourceFiles =  new Hashtable();
        CodeSet codeSet = lib.getCodeSet();                       
        if (codeSet == null) {
            return;
        }   

        ArrayList classes = codeSet.getSupportClasses();

        // iterate through all support classes 
        if (classes != null) {
            Iterator it = classes.iterator();
            while (it.hasNext()) {
                SupportClass sc = (SupportClass)it.next();            
                if (sc instanceof InlineSupportClass) {
                    InlineSupportClass isc = (InlineSupportClass)sc;
                    JavaClass jc = new JavaClass();                   
                    
                    // determine the name of a java file to put the class to
                    String targetName = isc.getTargetName();
                    if (targetName == null || targetName.trim().equals("")) {
                        targetName = libID;
                    }
                    

                    jc.setName(targetName);                    
                    jc.setClassCode(isc.getSource());                    
                    
                    // create new java file if not yet created
                    if (!sourceFiles.containsKey(targetName)) {
                        JavaClassFile src = new JavaClassFile();
                        src.setName(targetName);
                        src.setLibID(libID);
                        src.setLibVarID(libVarID);             
                        src.setPackageName(detectPackageName(lib));
                        LibDocumentation libDoc = lib.getLibDocumentation();
                        if (libDoc != null) {
                            src.setTitle(libDoc.getTitle());
                            src.setDescription(libDoc.getDescription());
                            src.addAuthors(libDoc.getAuthors());
                        }
                        src.addImports(codeSet.getImports());
                        processDependencies(src, codeSet.getDependencies());
                        sourceFiles.put(targetName, src);

                        // insert valid copyright notice

                        String crn = "";
                        String sc_info = IR.getAttrElem("scInfo", lib.getLibAttributes());
                        String file = getOutputFile(src, relOutputDir).getAbsolutePath();

                        if (sc_info == null) {
                            String msg = "library has no scInfo attribute: ";
                            CopyrightManager.error("(LibraryEmitter) " + msg + file);
                        }
                        try {
                            crn = CopyrightManager.getCopyright(
                                  // anchor ID
                                CRN_JAVA_SHORT,
                                  // passing null source code control info will make
                                  // CopyrightManager use current year as CRN year
                                sc_info
                            );
                            if (crn != null) {
                                src.setCopyrightNotice(crn);
                                CopyrightManager.logCrnOk("(LibraryEmitter) " + file);
                            };
                        } catch (CopyrightManager.Fault f) {
                            String msg = "(LibraryEmitter) " + f.getMessage();
                            CopyrightManager.errorSkipCRN(msg, file);
                        }
                    } 
                    JavaClassFile jcf = (JavaClassFile)sourceFiles.get(targetName);
                    jcf.addClass(jc);
                } 
            } 
	    it = sourceFiles.values().iterator();           
	    while (it.hasNext()) {
            JavaClassFile current = (JavaClassFile)it.next();
            File outputFile = getOutputFile(current, relOutputDir);
            MiscUtils.mkdirs(outputFile.getParentFile());
            FileWriter os = new FileWriter(outputFile);
            os.write(current.toString());
            os.close();
	    }
            
        } 

	ArrayList data = codeSet.getData();
	// process inline IODataFiles
        if (data != null) {
            Iterator it = data.iterator();
            while (it.hasNext()) {
                Data dt = (Data)it.next();
                if (dt instanceof InlineData ) {
                    InlineData idt = (InlineData)dt;
                    DataType dtp = idt.getType();
                    if (dtp != null && dtp.isIOData() ){
                        String targetName = idt.getTargetName();
                        if (targetName == null || targetName.trim().equals("")) {
                            targetName = lib.getID();
                        }
                        File outputFile = new File (getRelativeOutputDirectory(lib), targetName); 
                        MiscUtils.mkdirs(outputFile.getParentFile());
                        FileWriter os = new FileWriter(outputFile);
                        os.write(idt.getData().trim());
                        os.close();
                    }
                }
            }
	}
        
        ResourceInstaller ri = new ResourceInstaller();
        ri.installResources(lib);

    }

    protected File getOutputFile(JavaClassFile jcf, String relOutputDir) {
        String outputFileName = relOutputDir.endsWith(fileSep)?
            relOutputDir + jcf.getName():
            relOutputDir + fileSep + jcf.getName();
        return new File(outputFileName);
    }

    public void setShell(Shell value) {
        m_shell = value;
    }
    
    public Shell getShell() throws TestFileException {
        return m_shell;
    }
    
    public void setProperties(Properties props) {}

    protected String getRelativeOutputDirectory(Library lib) 
            throws TestFileException {
        String outDir = IR.getAttrElem("OutputDir", lib);
        return (outDir == null) ? "." : outDir;
    }
    
    protected String getRelativeSourcePath(Library lib) throws TestFileException {
        String rsp = IR.getAttrElem("relSourcePath",lib);
        rsp = rsp.substring(0,rsp.lastIndexOf(fileSep));
        return rsp;
    }


    protected void processDependencies(JavaClassFile src, ArrayList dependencies) throws TestFileException {
/* Dependencies are processed by LibLinker
            if (dependencies != null && dependencies.size() >0) {
                for (Iterator libs = dependencies.iterator();
                     libs.hasNext();) {
                    Object lib = libs.next();
                    if (lib instanceof LibraryDependency) {
                        ArrayList imports = LibUtils.getLibraryImports(((LibraryDependency)lib).getID().trim());
                        Iterator it = imports.iterator();
                        while (it.hasNext()) {
                            src.addImport(it.next().toString());
                        }
                    }
                }
            }
*/
    }

    /**
     * Detects library package name either from the first Export (if specified),
     * or from the build properties.
     */
    protected String detectPackageName(Library lib) throws TestFileException {
        // Actual implmentation of this method has been moved to LibUtils. 
        // Leaving this stub around for backwards compatability.
        String res = LibUtils.detectPackageName(lib);
        if (res == "") {
            throw new TestFileException( "Cannot detect package name for library: " + lib.getID());
        }
        return res;
    }



}

