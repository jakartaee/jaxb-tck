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

package com.sun.tgxml.tools.elgen;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.Enumeration;

import com.sun.tgxml.tjtf.tools.ToolBase;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.DefaultOperandsValidator;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;

public class ExcludeListGenerator extends ToolBase {
   

   private static final String CtStr_ToolName = "ExcludeListGenerator";
  
  /**
    *  Program entry
    *
    *  This replaces the main in ToolBase (sub-classes XMLToolBase)
    */
    public static void main(String args[]) {
        ExcludeListGenerator c = new ExcludeListGenerator(System.out, System.err);
        System.exit(c.run(args));
    }
    

    /* 
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     *    Public Methods (outside world can call these)
     * -----------------------------------------------------------
     * -----------------------------------------------------------
     */ 
    
    /** Constructor (canon.)
     *  
     *  constructs the XMLToolBase tool class.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream 
     */
    public ExcludeListGenerator( PrintStream out, PrintStream err) {
	super(out, err, CtStr_ToolName);
    }

 

   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    StringOption resultOption = new StringOption("-resultExcludeList", 
         "  -resultExcludeList <filename> output file name",
         OBLIGATORY);

    StringOption filterOption = new StringOption("-filter", 
         "  -filter <expression> keyword expression");

    StringOption converterOption = new StringOption("-converter", 
         "  -converter <classname> the converter class name");


    /**
     * Registers -resultExcludeList, -filter, -converter options
     */
    public void registerOptions() {

         super.registerOptions();

         addOption(resultOption);
         addOption(filterOption);
         addOption(converterOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     * Initializes operands.
     */
    public void applyOptionsValues() throws ParseArgumentException {	   

        if (filterOption.isSet()) 
            setFilter(filterOption.getStringValue());

        try {
            setOutput(resultOption.getStringValue());
        } catch (IOException e) {
	    throw new ParseArgumentException(
                LibResHandler.getResStr("file.error.ioerror",
                resultOption.getStringValue()));
        }            

        if (converterOption.isSet()) 
            setConverter(converterOption.getStringValue());


        try {
            setExcludeList(operands[0]);
        } catch (ParseException e) {
            throw new ParseArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new ParseArgumentException(
                LibResHandler.getResStr("file.error.ioerror", operands[0]));
        }

        super.applyOptionsValues();
    }

    /** 
     * Sets OperandsValidator that validates that only one operand
     * is passed, operand ends with ".jtx" does not start with "-"
     */
    protected void setOperandsValidator() {
        String[] operandsUsageLines = {
            "Operands: ",
            "  <fileName>.jtx"
        };
        operandsValidator = new DefaultOperandsValidator(1, 1, 
            "-", ".jtx", operandsUsageLines);
    }
    
    /**
     * Sets elgen usage header
     */
    protected void setToolUsageHeader() {
        toolUsageHeader = 
            "Usage: " + getProgramName() + " [<options>] <fileName>.jtx\n" + 
            "where options include:";
    }



   /* 
    * ----------------------------------------------------------------------
    *
    * ----------------------------------------------------------------------
    */

    public void startTool() {
		try {
			generate();
		} catch (TestFileException e) {
            reportErrorMsg(e.getMessage());
			setResultCode(ctInt_ErrorCode_Error);
        }
    }


	File result = null;
	ExcludeList excludeList = null;
	Vector links = new Vector();
	KeywordExpression expr = null;
    ExcludeListConverter converter = new JavaTestELConverter();

	public void setFilter(String filter) {
		expr = new KeywordExpression(filter);
	}


	public void setExcludeList(String el) throws IOException {
		excludeList = new ExcludeList(el);
	}

	public void setOutput(String file) throws IOException {
			result = new File(file);
	}

	public void setConverter(String className) {
	}

	/**
  
   */
	public void generate()  throws TestFileException {
		try {
            if(expr != null) {
                excludeList = expr.apply(excludeList);
            }			
			converter.convert(excludeList, result);
		} catch (IOException e) {
			reportErrorMsg(LibResHandler.getResStr("file.error.ioerror", "result file"));
		}
	}


}
