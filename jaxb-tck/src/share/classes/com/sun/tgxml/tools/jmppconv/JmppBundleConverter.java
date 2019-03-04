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

package com.sun.tgxml.tools.jmppconv;

import java.io.File;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.ToolBase;

import com.sun.tgxml.tools.jmppconv.JmppConverterTool;

/**
 * Wrapper tool runs multiple JMPP templates in a single VM with
 * JmppConverterTool.
 */

public class JmppBundleConverter extends ToolBase {
    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "JmppBundleConverter";

   /*
    * ----------------------------------------------------------------------
    *    Options parsing methods
    * ----------------------------------------------------------------------
    */
    String baseDir = null, outDir = null, workDir = null, jmppFilesList = null;
    
    protected StringOption outDirOption = new StringOption("-outdir",
        "  -outdir <outdir>   the root directory to put resulted files to, <JCK>/tests/ as rule (obligatory)",
        OBLIGATORY);

    protected StringOption workDirOption = new StringOption("-workdir",
        "  -workdir <workdir>  working directory to put intermediate JMPP files/classes to (obligatory)",
        OBLIGATORY);

    protected StringOption baseDirOption = new StringOption("-basedir",
        "  -basedir <basedir>  the base directory relatively to which the files in -jmppFilesList are specified",
        OBLIGATORY);

    protected StringOption jmppFilesListOption = new StringOption("-jmppFilesList",
        "  -jmppFilesList <jmppFilesList>  the list of JMPP files to run, in form: either absolute or relative if -basedir is specified (obligatory)",
        OBLIGATORY);

    /**
     * Registers options.
     */
    public void registerOptions() {
        addOption(outDirOption);
        addOption(workDirOption);
        addOption(baseDirOption);
        addOption(jmppFilesListOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt>
     */
    public void applyOptionsValues() throws ParseArgumentException {
            outDir = outDirOption.getStringValue();
            if (!new File(outDir).isDirectory()) {
                throw new ParseArgumentException("Wrong 'outdir' directory: " + outDir);
            } else {
                outDir = new File(outDir).getAbsolutePath();
            }
            workDir = workDirOption.getStringValue();
            if (!new File(workDir).isDirectory()) {
                throw new ParseArgumentException("Wrong 'workdir' directory: " + workDir);
            } else {
                workDir = new File(workDir).getAbsolutePath();
            }
            jmppFilesList = jmppFilesListOption.getStringValue();
            if (!new File(jmppFilesList).isFile()) {
                throw new ParseArgumentException("Wrong 'jmppFilesList' file: " + jmppFilesList);
            }
            baseDir = baseDirOption.getStringValue();
            if (!new File(baseDir).isDirectory()){
                throw new ParseArgumentException("Wrong 'basedir' directory: " + baseDir);
            }
            return;
    }

    /*
     * =========================================================================
     *    Methods
     * =========================================================================
     */

    /**
     * Program entry
     * @param args The command line arguments to  this tool.
     */
    public static void main(String args[]) {
        JmppBundleConverter converter = new JmppBundleConverter(System.out, System.err);
        converter.setProgramName(CtStr_ToolName);
        System.exit(converter.run(args));
    }

    /** Constructor.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream
     */
    public JmppBundleConverter(PrintStream out, PrintStream err) {
		super(out, err);
		m_needsCommandLineArguments = true;
    }

    /**
     *  Run JMPPs.
     */
    public void startTool() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(jmppFilesList)));
            boolean isAbsolute = false;
            int i = 0;
            for (String fname = reader.readLine(); fname != null; fname = reader.readLine()) {
                String[] args = {"-o", null, "-w", null, null};
                if (++i == 1) {
                    isAbsolute = fname.startsWith(baseDir);
                }
                String templPath = isAbsolute == true ? fname.substring(baseDir.length()): fname;
                args[1] = outDir + File.separator + new File(templPath).getParent();
                args[3] = workDir;
                args[4] = baseDir + File.separator + templPath;
                reportOutputMsg("\n" + "...running JmppBundleConverter " + getString(args)); 
                int res = new JmppConverterTool(getStandardOut(), getStandardErr()).run(args);
                if (res != ctInt_ErrorCode_NoError){
                    reportErrorMsg("Error occured running the last template");
                    setResultCode(ctInt_ErrorCode_Error);
                    break;
                }
            }
        } catch (Exception e) {
            reportErrorMsg(e.getMessage());
            setResultCode(ctInt_ErrorCode_Error);
        }
        return;
    }

    protected String getString(String[] str_arr){
        String str = "";
        for (int i = 0; i < str_arr.length; ++i){
            str += " " + str_arr[i];
        }
        return str;
    }

}



