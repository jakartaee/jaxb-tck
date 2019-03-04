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
import java.util.Iterator;
import java.util.ArrayList;

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes;
import com.sun.tgxml.tjtf.api.documentation.TestCaseDocumentation;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.tools.options.Option;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.BuildProperties;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.tools.elgen.ExcludeListMarker;
import com.sun.tgxml.tools.elgen.ExcludeListFilter;
import com.sun.tgxml.tools.elgen.ExcludeListToolFactory;
import com.sun.tgxml.tools.dependence.LibIDExtractor2;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.TestFilter2;
import com.sun.tgxml.tools.filter.processors.FilteringException;
import com.sun.tgxml.tools.filter.libutil.LibMap;
import com.sun.tgxml.tools.filter.libutil.LibSelectionInfo;
import com.sun.tgxml.tools.filter.libutil.TestGroupLibsInfo;
import com.sun.tgxml.tools.filter.libutil.LibFilterFactrory;
import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.processors.emitter.ExternalEmitter;


/**
 * Test filtering conveyer.
 * Accepts command-line arguments and instantiates
 *   ExcludeListMarker, TestFilter2 and LibraryIDExtractor components.
 * Pass IR for input TestGroup through all processing components
 *   and returns cleaned TestGroup or null if all TestGroup is ceaned out.
 *
 * @version  1.0, 02/15/2002
 * @author   Ilya V. Neverov
 */
public class TestFilterConveyer2 extends StandardOptionHandler {

    public final static String FilteredAttrElemName = "Filtered";

    String pluginName,
           libmapFileName,
	   exListFileName,
	   libListFileName,
	   libListOutFileName;

    protected PrintStream err;

    protected ExcludeListMarker elMarker;
    protected LibIDExtractor2    libEx;
    protected TestFilter2        filter;

    protected LibFilterFactrory libFilterFactory;
    protected LibSelectionInfo externalLibsInfo;

    public TestFilterConveyer2(PrintStream err) {
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
	    libEx = new LibIDExtractor2(libListFileName, libListOutFileName);
	} catch ( Exception e ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.badLibList", libListFileName
										, e.toString())  );
	}

	try {
	    FilterFactory filtFact = FilterFactory.newInstance(pluginName);
	    filter = filtFact.getTestFilter2(null);
	} catch ( FilteringException fe ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.badPlugin", pluginName
										, fe.toString())  );
	}

        libFilterFactory = FilterUtil.createLibFilterFactrory("testfilter");
        externalLibsInfo = FilterUtil.extenalSelectionInfo(
                libFilterFactory, libmapFileName);

    }

    public static void markExcluded(TestGroup testGroup, ExcludeListMarker elMarker)
									throws TestFileException {
	try {
	    elMarker.markExcluded(testGroup);

	    ArrayList tcList = testGroup.getTestCases();
            if (tcList != null) {
	        for (int j = tcList.size()-1; j >= 0; j--) {
		    elMarker.markExcluded((TestCase)tcList.get(j));
	        }
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

        if (ExternalEmitter.EXTERNAL_MULTITEST.
                equals(LibUtils.getTestType(testGroup))) {
            // check that testcases do not contain selection attributes
            // (Selection Attributes have no effect on 
            // ExternalMultiTest TestCases)
            checkExternalMultiTestTCAttributes(testGroup);
        }

	markExcluded(testGroup, elMarker);

        LibMap inlineLibMap = filterInlineLibs(testGroup);
        // merge selection info about external and inline libraries.
        TestGroupLibsInfo libsInfo = new TestGroupLibsInfo(
            externalLibsInfo, inlineLibMap);

	try {
	    if (filter.strip(testGroup, libsInfo) == null) {
		return null;
	    }
	} catch ( FilteringException fe ) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.filtering", fe.toString())  );
	}

	try {
	     libEx.extract(testGroup, inlineLibMap);
	     libEx.close();
	} catch (Exception e) {
	    throw new TestFileException(
			    LibResHandler.getResStr("filter.error.tfc.libIdExtract", e.toString())  );
	}

	IR.setAttrElem(testGroup, FilteredAttrElemName, pluginName);
	return testGroup;
    }

    /**
     * Provides filtering of inline libraries for the passed TestGroup.
     * @return LibMap for inline libraries.
     */
    protected LibMap filterInlineLibs(TestGroup tg) 
            throws TestFileException {

        ArrayList inLibs = tg.getLibraries();
        if (inLibs == null || inLibs.size() == 0) {
            return new LibMap(); // no accepted/rejected libs.
        }


        Library[] libs = (Library[])inLibs.toArray(new Library[0]);

        LibMap libMap = null;
        try {            
            libMap = FilterUtil.filterLibBundle(libs, pluginName, 
                    libFilterFactory, externalLibsInfo);
        } catch (FilteringException fe) {
            throw new TestFileException(
                "Libraries filtering failed: " + fe);
        }
        return libMap;
    }


    /**
     * Checks that testcases do not contain selection attriubtes.
     * (This method is invoked only for tests of ExternalMultiTest type).
     * @throws TestFileException if some testcases define selection attriubtes
     */
    protected void checkExternalMultiTestTCAttributes(TestGroup tg)
            throws TestFileException {
        if (tg == null)
            return;         

        ArrayList testCases = tg.getTestCases();
        if (testCases == null) {
            return;
        }

        StringBuffer errMsg = new StringBuffer();

        for (Iterator it = testCases.iterator(); it.hasNext(); ) {
            TestCase tc = (TestCase)(it.next());

            StringBuffer tcErrMsg = new StringBuffer();

            if (tc.isDeleted()) {
                tcErrMsg.append("Deleted ");
            }
            TestCaseAttributes tcAttr = tc.getTCAttributes();
            if (tcAttr != null) {
                ArrayList al = tcAttr.getRequiredResources();
                if (al != null && al.size() > 0) {
                    tcErrMsg.append("RequiredResources ");
                }

                al = tcAttr.getTargetSpecs();
                if (al != null && al.size() > 0) {
                    tcErrMsg.append("TargetSpecs ");
                }
            }

            TestCaseDocumentation tcDoc = tc.getTCDocumentation();
            if (tcDoc != null) {
                ArrayList al = tcDoc.getTestCaseSpecs();
                if (al != null && al.size() > 0) {
                    tcErrMsg.append("TestCaseSpec ");
                }
            }

            CodeSet tcCodeSet = tc.getCodeSet();
            if (tcCodeSet != null) {
                ArrayList al = tcCodeSet.getDependencies();
                if (al != null && al.size() > 0) {
                    tcErrMsg.append("Dependencies ");
                }
                al = tcCodeSet.getImports();
                if (al != null && al.size() > 0) {
                    tcErrMsg.append("Imports ");
                }
            }

            if (tc.getTestCode() != null) {
                tcErrMsg.append("TestCaseCode ");
            }

            if (tcErrMsg.length() > 0) {
                errMsg.append("  testcase: ");
                errMsg.append(tc.getID() + ":" + tcErrMsg + "\n");
            }
        }

        if (errMsg.length() > 0) {
            throw new TestFileException( 
            "Selection Attributes have no effect on ExternalMultiTest TestCases"
                + "\nTestGroup: " + tg.getID()
                + "\n" + errMsg
            );
        }
    }

    /**
     * Returns name of libMap file, passed via '-libmap' option,
     * or null if '-libmap' is missed from passed options.
     */
    public String getLibMapFileName() {
        return libmapFileName;
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
     * specifies the name of libmap file to generate
     */
    protected StringOption libMapOption = new StringOption(
        "-libmap",
        "  -libmap <filename> name of libmap file for external libraries\n"  +
        "           (required if any TestGroup, TestCase or InlineLibrary\n" +
        "           depends on external library)");


    /**
     * Registers -debug option
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(pluginOption);
        addOption(libMapOption);
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

        if (libMapOption.isSet()) {
            libmapFileName = libMapOption.getStringValue();
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
