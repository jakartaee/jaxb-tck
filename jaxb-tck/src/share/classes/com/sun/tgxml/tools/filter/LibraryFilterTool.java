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
import java.io.IOException;

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.XMLValToolBase;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;

import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.resources.LibResHandler;

/**
 * Build shell-tool. Accepts command-line arguments and instantiates
 * Filter and LibraryDepExtractor components.
 * Creates array of Library implementation IRs for input Library ID, 
 * pass it through the all processing components
 * and emits selected and cleaned Library to UTD XML file.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public class LibraryFilterTool extends XMLValToolBase {

    String libMap2FileName,
	   libID, libIDMapping, libListFileName, libListOutFileName;

    protected LibFilterConveyer lfConveyer;

    LibraryFilterTool(PrintStream out, PrintStream err) {
	super(out, err, "LibraryFilterTool");
	lfConveyer = new LibFilterConveyer(err);
    }

    public static void main(String args[]) {
        LibraryFilterTool tool = new LibraryFilterTool(System.out, System.err);
        System.exit(tool.run(args));
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    protected StringOption libmap2FileOption = new StringOption(LibResHandler.getResStr("filter.libmap2File.mnem"),
        LibResHandler.getResStr("filter.libmap2File.desc"),
        OPTIONAL);

    protected StringOption libIDOption = new StringOption(LibResHandler.getResStr("filter.libID.mnem"), 
        LibResHandler.getResStr("filter.libID.desc"),
        OBLIGATORY);

    protected StringOption libIDMappingOption = new StringOption(LibResHandler.getResStr("filter.libmap.mnem"), 
        LibResHandler.getResStr("filter.libmap.desc"),
        OBLIGATORY);

    protected StringOption liblistOption = new StringOption(LibResHandler.getResStr("filter.liblist.mnem"), 
        LibResHandler.getResStr("filter.liblist.desc"),
        OPTIONAL);

    protected StringOption liblistOutOption = new StringOption(LibResHandler.getResStr("filter.liblistOut.mnem"), 
        LibResHandler.getResStr("filter.liblistOut.desc"),
        OPTIONAL);

    /**
     * Registers -plugin, -liblist, -libmap, -libmap2File, -libID, -out options
     * Makes -file option defined in XMLToolBase not obligatory
     */
    public void registerOptions() {

        // do not register fileOption 
        fileOption.setObligatory(OPTIONAL);

        super.registerOptions();
        decoder.addOptionHandler(lfConveyer);
        addOption(liblistOption);
        addOption(liblistOutOption);
        addOption(libmap2FileOption);
        addOption(libIDOption);
        addOption(libIDMappingOption);
   }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (libmap2FileOption.isSet()) {
            libMap2FileName    = libmap2FileOption.getStringValue();
        }
        if (liblistOption.isSet()){
            libListFileName = liblistOption.getStringValue();
        }
        if (liblistOutOption.isSet()){
            libListOutFileName = liblistOutOption.getStringValue();
        }
        libID              = libIDOption.getStringValue();
        libIDMapping       = libIDMappingOption.getStringValue();

        super.applyOptionsValues();
    }

   /* 
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */

    /** 
     * Selects and parses input files, filter the Library array, and emit the cleaned tree.
     * 
     * @throws TestFileException If there is a problem with the IR parse tree.
     * @throws IOException if there is an IO problem.
     */
    public void executeTool() throws TestFileException, IOException {
	lfConveyer.setLibListFileName(libListFileName);
	lfConveyer.setLibListOutFileName(libListOutFileName);
	lfConveyer.setup();
	Library selectedLibrary = lfConveyer.process(libID, m_parser, libIDMapping);
	lfConveyer.finish();
	lfConveyer.setLibMap2FileName(libMap2FileName);
        if (selectedLibrary != null) {
            lfConveyer.outputResult(selectedLibrary, (XMLEmitter)m_emitter);
        }
    }
}
