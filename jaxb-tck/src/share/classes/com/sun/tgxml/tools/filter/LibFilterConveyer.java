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

package com.sun.tgxml.tools.filter;

import java.io.PrintStream;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.processors.parser.IRParser;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.Attributes;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tools.testgen.processors.emitter.Generator;

import com.sun.tgxml.tools.dependence.LibDepExtractor;

import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.LibraryFilter;
import com.sun.tgxml.tools.filter.processors.FilteringException;

public class LibFilterConveyer extends StandardOptionHandler {

    String pluginName,
	   libListFileName,
	   libListOutFileName,
	   outputName;

    LibraryFilter filter;
    LibDepExtractor libEx;
    PrintStream err;

    public LibFilterConveyer(PrintStream err) {
	if (err == null){
	    throw new IllegalArgumentException(LibResHandler.getResStr("filter.error.libfilter.noErrorStream"));
	}
	this.err = err;
    }

    public void setup() throws TestFileException {
	try {
	    FilterFactory filtFact = FilterFactory.newInstance(pluginName);
	    filter = filtFact.getLibraryFilter(null);
	} catch ( FilteringException fe ) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badPlugin", pluginName, fe.toString()));
	}
	try {
	    libEx = new LibDepExtractor(libListFileName, libListOutFileName);
	} catch (Exception e) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badDepLibList", libListFileName, e.toString()));
	}
    }

    protected StringOption pluginOption = new StringOption(LibResHandler.getResStr("filter.plugin.mnem"),
        LibResHandler.getResStr("filter.plugin.desc"),
        OBLIGATORY);

    protected StringOption outOption = new StringOption(LibResHandler.getResStr("filter.out.mnem"), 
        LibResHandler.getResStr("filter.out.desc"),
        OBLIGATORY);

    protected String libMap2FileName = null;

    protected String libIDMapString = null;
    
    public void registerOptions() {
        super.registerOptions();
        addOption(pluginOption);
        addOption(outOption);
    }

    public void applyOptionsValues() throws ParseArgumentException {
        pluginName = pluginOption.getStringValue();
        outputName = outOption.getStringValue();
        super.applyOptionsValues();
    }
    
    public Library process(String libID, IRParser parser, String libIDMap) throws TestFileException {
        if (libIDMap == null || libIDMap.trim().equals("")){
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.noLibImpl", libID, libIDMap));
        }
	File libFiles[] = LibraryMap.getXMLFiles(libID, libIDMap);
	if (libFiles.length == 0) {
		throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.noLibImpl", libID, libIDMap));
        }
        libIDMapString = libIDMap;
        return process(libID, parser, libFiles);
    }

    public Library process(String libID, IRParser parser, File[] libFiles) throws TestFileException {
       IRObj[] irs;
       try {
           irs = parser.parse(libFiles);
       } catch (IOException ioe){
           throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.libXMLParser", libID));
       }

       Library[] libImpls = new Library[irs.length];

       for (int i = irs.length-1; i>=0; i--) {
           if (!(irs[i] instanceof Library) ) {
               throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badTestRoot", irs[i].getClass().toString(), libFiles[i].toString()));
           }
           Library lib = (Library)irs[i];
           String libName = IR.getID(lib);
           if (!libID.equals(libName)) {
               throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badLibID", libName, libID, libFiles[i].toString()));
           }
           libImpls[i] = lib;
       }

	Library chosenLib;
	try {
	    chosenLib = filter.strip(libImpls);
	} catch ( FilteringException fe ) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.libFilterError", libID, fe.toString()));
	}
	if (chosenLib == null){
	    return null;
	}
	try {
	     libEx.extract(chosenLib);
	} catch (Exception e) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.libIDExtrError", e.toString()));
	}
	return chosenLib;
    }
    
    protected String constructOutputPath(Library lib) throws TestFileException {
        String path, root = getRepositoryPath(lib);
        try {
            path = IR.getAttrElem(IR.SourcePathAttrElemName, lib);
        } catch (Exception e) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.noAttrElem", IR.SourcePathAttrElemName));
        }
        if (path == null) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.emptyAttrElem", IR.SourcePathAttrElemName));
        }
        try {
            root = (new File(root)).getCanonicalPath() + File.separatorChar;
            path = (new File(path)).getCanonicalPath();
        } catch (IOException ioe){
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException", "getCanonicalPath()", root, ioe.toString()));
        }
        if (!path.startsWith(root)) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.badTCKDir", "",
                                 root, IR.SourcePathAttrElemName, path));
        }
        
        path = outputName + File.separatorChar + transformLibSubDir(path.substring(root.length()));
        return path;
    }
    
    protected String transformLibSubDir(String subDir){
        return subDir;
    }

    protected String getRepositoryPath(Library lib) throws TestFileException {
        String path;
        try {
            path = IR.getAttrElem(IR.SourcePathAttrElemName, lib);
        } catch (Exception e) {
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.noAttrElem", IR.SourcePathAttrElemName));
        }
        String repos = LibraryMap.getRepositoryPath(path, libIDMapString);
        repos = ((repos == null) ? System.getProperty("tck.source.dir") : repos);
        return repos;
    }

    public void outputResult(Library lib, XMLEmitter emitter) throws TestFileException {
        String path = constructOutputPath(lib);
        File fl = new File(path);
        File dir = fl.getParentFile();
        if (dir != null && !MiscUtils.mkdirs(dir)){
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.DirCreateError", dir.getName()));
        }
        try {
            OutputStream output = new BufferedOutputStream(new FileOutputStream(fl), 32*1024);
            emitter.emit(lib, output);
            output.close();
	} catch (Exception e) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException2", path, e.toString()));
	}

	if (libMap2FileName == null){
	    return;
	}

	String libMapEntry = lib.getID() + " " + path + "\n";
	try {
	    File flmap = new File(libMap2FileName);
	    File dirmap = flmap.getParentFile();
	    if (dirmap != null && !MiscUtils.mkdirs(dirmap)){
                throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.DirCreateError", dirmap.getName()));
            }
	    OutputStream output = new FileOutputStream(libMap2FileName, true);
	    output.write(libMapEntry.getBytes());
	    output.close();
	} catch (Exception e) {
	    throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.ioException2", libMap2FileName, e.toString()));
	}
    }

    public void setLibListFileName(String fn){
        libListFileName = fn;
    }
    
    public void setLibListOutFileName(String fn){
        libListOutFileName = fn;
    }

    public void setLibMap2FileName(String fn){
        libMap2FileName = fn;
    }

    public void finish() throws TestFileException {
        try {
            libEx.close();
        } catch (IOException ioe){
            throw new TestFileException(LibResHandler.getResStr("filter.error.libfilter.libDepCloseError"));
        }
    }
}
