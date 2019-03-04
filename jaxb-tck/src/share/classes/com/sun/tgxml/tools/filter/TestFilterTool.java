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

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.tools.XMLValToolBase;

import com.sun.tgxml.tjtf.tools.options.Option;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ExternalCompoundOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;

/**
 * Build shell-tool. Accepts command-line arguments and instantiates ExcludeListMarker,
 * Filter and LibraryIDExtractor components.
 * Creates IR for input TestGroup, pass it through all processing components
 * and emits cleaned TestGroup to UTD XML file.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public class TestFilterTool extends XMLValToolBase {

    String inputFileName,
	   outputFileName;

    TestFilterConveyer tfConveyer;
    
    public TestFilterTool(PrintStream out, PrintStream err) {
	super(out, err, "TestFilterTool");
	tfConveyer = new TestFilterConveyer(err);
    }

    public static void main(String args[]) {
        TestFilterTool tool = new TestFilterTool(System.out, System.err);
        System.exit(tool.run(args));
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */


    protected StringOption outOption = new StringOption(LibResHandler.getResStr("filter.option.testfilter.out.mnem"),
        LibResHandler.getResStr("filter.option.testfilter.out"),
        OBLIGATORY);



    /**
     * Registers conveyer option handler.
     * Add switch '-in' to -file option, defined in XMLToolBase.
     */
    public void registerOptions() {

        // add "-in" switch to fileOption defined in XMLToolBase 
        fileOption.addSwitch(LibResHandler.getResStr("filter.option.testfilter.in.mnem"));
        fileOption.setUsageInfo(LibResHandler.getResStr("filter.option.testfilter.in"));

        addOption(outOption);
        decoder.addOptionHandler(tfConveyer);

        super.registerOptions();

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {	   

        outputFileName  = outOption.getStringValue();

        super.applyOptionsValues();
    }

   /* 
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */


    /** 
     *  
     * Sub-classes override this method when they wish to process any arguments
     * before running the "executeTool" method. (Called by startTool()).
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void processArgs() throws TestFileException, IOException {
	super.processArgs();
	tfConveyer.setup();
    }

//    public void process(IRObj testGroup) throws TestFileException {
//
    /** 
     *  Parse an input file, filter the TestGroup, and emit the cleaned tree.<b>
     * TestFilter needs to override this method since no output should be produced if
     * the whole TestGroup is filtered out.
     * <p>
     * @throws TestFileException If there is a problem with the IR parse tree.
     * @throws IOException if there is an IO problem.
     */
    public void executeTool() throws TestFileException, IOException {
	XMLEmitter emitter = (XMLEmitter) m_emitter;

	File files[] = { getInputFile() };
	IRObj ir = (m_parser.parse(files))[0];

	if (! (ir instanceof TestGroup) )
	    throw new TestFileException(LibResHandler.getResStr("filter.error.testfilter.badTestRootType", ir.getClass().toString()));

	TestGroup testGroup = tfConveyer.process((TestGroup)ir);
	
	if (testGroup == null) {
//	    m_emitter = new NullEmitter();
	    log(">>>> The whole TestGroup "+ IR.getID((TestGroup)ir) +
							" is filtered out >>>> no output");
	    return;
	}

	try {
	    File fl = new File(outputFileName);
	    File dir = fl.getParentFile();
	    if (dir != null && !MiscUtils.mkdirs(dir))
                        throw new TestFileException(LibResHandler.getResStr("filter.error.testfilter.DirCreateError", dir.getName()));
	     
	    OutputStream output = new BufferedOutputStream(new FileOutputStream(fl), 32*1024);
	    emitter.emit(testGroup, output);
	    output.close();
	} catch (Exception e) {
	    reportErrorMsg(LibResHandler.getResStr("filter.error.testfilter.outputError", outputFileName, e.toString()));
	    if (m_debug)
		e.printStackTrace(getStandardErr());
	    throw new TestFileException(LibResHandler.getResStr("filter.error.testfilter.outputError", outputFileName, e.toString()));
	}
    }
}
//=================================================================================

/*
    static class NullEmitter implements XMLEmitter {
	public void emit(IRObj[] irs, File[] files)          {}
	public void emit(IRObj[] irs, OutputStream[] outputs){}
	public void emit(XMLObj root, File file)	     {}
	public void emit(XMLObj root, OutputStream stream)   {}
	public void setDebug(boolean debug)		     {}
    }
*/

