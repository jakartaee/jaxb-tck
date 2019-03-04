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
import java.util.ArrayList;

import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.options.Option;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.tools.elgen.ExcludeListMarker;
import com.sun.tgxml.tools.elgen.ExcludeListFilter;
import com.sun.tgxml.tools.elgen.ExcludeListToolFactory;
import com.sun.tgxml.tools.dependence.LibIDExtractor;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.TestFilter;
import com.sun.tgxml.tools.filter.processors.LibraryFilter;
import com.sun.tgxml.tools.filter.processors.FilteringException;

/**
 * Test filtering conveyer.
 * Accepts command-line arguments and instantiates
 *   ExcludeListMarker, TestFilter and LibraryIDExtractor components.
 * Pass IR for input TestGroup through all processing components
 *   and returns cleaned TestGroup or null if all TestGroup is ceaned out.
 *
 * @version  1.0, 02/15/2002
 * @author   Ilya V. Neverov
 */
public class TestFilterConveyer extends StandardOptionHandler {

    public final static String FilteredAttrElemName = "Filtered";

    String pluginName,
	   exListFileName,
	   libListFileName,
	   libListOutFileName;

    protected PrintStream err;

    protected ExcludeListMarker elMarker;
    protected LibIDExtractor    libEx;
    protected TestFilter        filter;
    protected LibraryFilter     libFilter;

    public TestFilterConveyer(PrintStream err) {
	if (err == null)
	    throw new IllegalArgumentException(
				LibResHandler.getResStr("filter.error.tfc.noErrorStream"));
	this.err = err;
    }
    
//=================================================================================

    public void setup() throws TestFileException {
	try {
	    elMarker = ExcludeListToolFactory.createELMarker(exListFileName);
	} catch ( Exception e ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.badExList", exListFileName
										, e.toString())  );
	}
	
	try {
	    libEx = new LibIDExtractor(libListFileName, libListOutFileName);
	} catch ( Exception e ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.badLibList", libListFileName
										, e.toString())  );
	}

	try {
	    FilterFactory filtFact = FilterFactory.newInstance(pluginName);
	    filter = filtFact.getTestFilter(null);
            libFilter = filtFact.getLibraryFilter(null);
	} catch ( FilteringException fe ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.badPlugin", pluginName
										, fe.toString())  );
	}
    }

    public static void markExcluded(TestGroup testGroup, ExcludeListMarker elMarker)
									throws TestFileException {
	try {
	    elMarker.markExcluded(testGroup);

	    ArrayList tcList = testGroup.getTestCases();
	    for (int j = tcList.size()-1; j >= 0; j--) {
		elMarker.markExcluded((TestCase)tcList.get(j));
	    }
	} catch ( Exception e ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.elMarker", e.toString())  );
	}
    }
//=================================================================================

    public TestGroup process(TestGroup testGroup) throws TestFileException {

	if (IR.getAttrElem(FilteredAttrElemName, testGroup) != null)
	    return testGroup;

	markExcluded(testGroup, elMarker);

	try {
	    if (filter.strip(testGroup) == null) {
		return null;
	    }
	} catch ( FilteringException fe ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.filtering", fe.toString())  );
	}

	try {
	     libEx.extract(testGroup, libFilter);
	     libEx.close();
	} catch (Exception e) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.libIdExtract", e.toString())  );
	}

	IR.setAttrElem(testGroup, FilteredAttrElemName, pluginName);
	return testGroup;
    }




   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    protected StringOption pluginOption = new StringOption(LibResHandler.getResStr("filter.option.tfc.plugin.mnem"),
        LibResHandler.getResStr("filter.option.tfc.plugin"),
        OBLIGATORY);

    protected StringOption exlistOption = new StringOption(LibResHandler.getResStr("filter.option.tfc.exlist.mnem"), 
        LibResHandler.getResStr("filter.option.tfc.exlist"),
        OBLIGATORY);


    protected StringOption liblistOption = new StringOption(LibResHandler.getResStr("filter.option.tfc.liblist.mnem"), 
        LibResHandler.getResStr("filter.option.tfc.liblist"),
        OPTIONAL);

    protected StringOption liblistOutOption = new StringOption(LibResHandler.getResStr("filter.option.tfc.liblistOut.mnem"), 
        LibResHandler.getResStr("filter.option.tfc.liblistOut"),
        OPTIONAL);


    /**
     * Registers -debug option
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(pluginOption);
        addOption(exlistOption);
        addOption(liblistOption);
        addOption(liblistOutOption);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {

        if (pluginOption.isSet()) {
	    pluginName = pluginOption.getStringValue(); 
        }

        if (exlistOption.isSet()) {
	    exListFileName = exlistOption.getStringValue(); 
        }

        if (liblistOption.isSet()) {
	    libListFileName = liblistOption.getStringValue(); 
        }

        if (liblistOutOption.isSet()) {
	    libListOutFileName = liblistOutOption.getStringValue(); 
        }

        super.applyOptionsValues();
    }
}
