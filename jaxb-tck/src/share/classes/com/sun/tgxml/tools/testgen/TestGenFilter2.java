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

package com.sun.tgxml.tools.testgen;

import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.ToolBase;
import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.tjtf.tools.options.ExternalCompoundOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.util.MiscUtils;
import com.sun.tgxml.tools.filter.TestFilterConveyer2;
import com.sun.tgxml.tools.testgen.processors.parser.MiddleWareXMLParser;
/**
 * Wrapper tool including TestGen and TestFilterConveyer2.
 */

public class TestGenFilter2 extends TestGen {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "TestGenFilter2";
    
    protected TestFilterConveyer2 tfConveyer;
    private String m_outputDir = ".";

    /*
     * =========================================================================
     *    Methods
     * =========================================================================
     */
    
    /**
     * Program entry
     *
     * This replaces the main in ToolBase.
     * @param args The command line arguments to  this tool.
     */
    public static void main(String args[]) {
			//System.err.println(">>>> Start main()");
        TestGenFilter2 c = new TestGenFilter2(System.out, System.err);
        System.exit(c.run(args));
    }
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public TestGenFilter2( PrintStream out, PrintStream err) {
	super(out, err);
        setProgramName(CtStr_ToolName);
			//System.err.println(">>>> super ctor ends");
	tfConveyer = new TestFilterConveyer2(err);
	
    }
    
       
   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */


    /**
     * Registers conveyer options
     */
    public void registerOptions() {         
        super.registerOptions();
        decoder.addOptionHandler(tfConveyer);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (m_parser instanceof MiddleWareXMLParser) {
            m_outputDir = ((MiddleWareXMLParser)m_parser).getOutputDir();
        }

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
	log(">>>> super.processArgs() ");
	super.processArgs();
	log(">>>> tfConveyer.setup() ");
	tfConveyer.setup();
	log(">>>> tfConveyer.setup() ends ");
    }

    /** 
     *  Parse inputs, process the IR, emit outputs.
     * <p>
     *  Tools should override this, this function determines
     *  if something is parsed - how many things are parsed at a given time,
     *  how an IR is processed (or multiple IRs),
     *  if something is emitted - how many things are emitted at a given time.
     * <p>
     * @throws TestFileException If there is a problem with an IR tree.
     * @throws IOException if there is some type of IO problem.
     */
    public void executeTool() throws TestFileException, IOException {
	log(">>>> executeTool() starts ");
	File[] files = getInputFiles();
	log(">>>> Found "+ files.length +" files ");

	IRObj[] trees = m_parser.parse(files);
	log(">>>> Parsed "+ trees.length +" trees ");

	boolean toEmit = false;

	int len = trees.length;
	for (int j=len-1; j>=0; j-- ) {
	    if (trees[j] instanceof TestGroup) {
		log(">>>> Start filtering "+ IR.getID((TestGroup)trees[j]));
		if (tfConveyer.process((TestGroup)trees[j]) == null) {
		    log(">>>> Filtered out "+ IR.getID((TestGroup)trees[j]));
		    trees[j] = null;
		    len--;
		} else {
		    toEmit = true;
		}
	    }
	}
	log(">>>> Remained: "+ len +" trees ; emit : " + toEmit);

	if (!toEmit)
	    return;

	if (len < trees.length) {
	    IRObj[] filtered = new IRObj[len];
	    for (int j=trees.length-1; j>=0; j-- ) {
		if (trees[j] != null)
		    filtered[--len] = trees[j];
	    }
	    trees = filtered;
	}

	if (!MiscUtils.mkdirs(m_outputDir)) {
	    reportErrorMsg(LibResHandler.getResStr("filter.error.testfilter.dirCreateError", m_outputDir));
	    return;
        }

	log(">>>> Emitting "+ trees.length +" trees ");
        setLibMapFile(tfConveyer.getLibMapFileName());
	m_generator.generate(trees);
	log(">>>> Emitting finishes ");
    }
    
}

