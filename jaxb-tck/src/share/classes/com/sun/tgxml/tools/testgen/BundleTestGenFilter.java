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

import java.io.File;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.tools.options.StringOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
import com.sun.tgxml.tjtf.tools.ToolBase;

import com.sun.tgxml.tools.testgen.TestGenFilter;

/**
 * Wrapper tool runs multiple TestGenFilters.
 */

public class BundleTestGenFilter extends ToolBase {
    
    /**
     * Name of jtx to pass down to emitter
     */
    public static String EXLIST_SYSPROP = "bitools.input.exlist";

    /*
     * ========================================================================
     *    Member Fields
     * ========================================================================
     */
    private static final String CtStr_ToolName = "BundleTestGenFilter";

   /*
    * ----------------------------------------------------------------------
    *    Options parsing methods
    * ----------------------------------------------------------------------
    */
    
    int cur_num = 0;
    
    String baseDir, plugin, exList, libListOut, outDir, dirList, el, libListOutDir, 
        libListOutSuffix, elSuffix, elDir, copyrightLink=null;
    
    protected StringOption baseDirOption = new StringOption("-basedir",
        "  -basedir <basedir>  the base directory relatively to which the directories in -dirList are specified",
        OBLIGATORY);

    protected StringOption pluginOption = new StringOption("-plugin",
        "  -plugin <plugin_class>  the class of test filter plugin (obligatory)",
        OBLIGATORY);

    protected StringOption exListOption = new StringOption("-exlist",
        "  -exlist <exclude_list> initial tck.jtx exclude list",
        OBLIGATORY);

    protected StringOption libListOutOption = new StringOption("-liblistOut",
        "  -liblistOut <dir/libID.lst>  dirname of <dir/libID.lst> is used to store all created tests-on-libraries " + 
        "libID.lst dependencies to, basename is used as suffix for all stored dependencies files", 
        OBLIGATORY);

    protected StringOption outDirOption = new StringOption("-outDir",
        "  -outDir <outDir> base directory to store generated files to: <JCK_dir>",
        OBLIGATORY);

    protected StringOption elOption = new StringOption("-el",
        "  -el <dir/jdk.jtx>  dirname of <dir/jdk.jtx> is used to store all created jdk.jtx-es to, " + 
        "basename is used as suffix for all stored jtx files", 
        OBLIGATORY);

    protected StringOption dirListOption = new StringOption("-dirList",
        "  -dirList <dirList>  file with the bundle's list of directories for which to run TestGenFilter",
        OBLIGATORY);

    protected StringOption copyrightLinkOption = new StringOption("-copyrightLink",
        "  -copyrightLink <pathToCopyrightFile>  the path (relative) to copyright file (optional)");


    /**
     * Registers options.
     */
    public void registerOptions() {
        addOption(baseDirOption);
        addOption(pluginOption);
        addOption(exListOption);
        addOption(libListOutOption);
        addOption(outDirOption);
        addOption(elOption);
        addOption(dirListOption);
        addOption(copyrightLinkOption);
    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt>
     */
    public void applyOptionsValues() throws ParseArgumentException {
            baseDir = baseDirOption.getStringValue();
            if (!new File(baseDir).isDirectory()){
                throw new ParseArgumentException("Wrong 'basedir' directory: " + baseDir);
            }

            plugin = pluginOption.getStringValue();

            exList = exListOption.getStringValue();

            libListOut = libListOutOption.getStringValue();
            if ((libListOutDir = new File(libListOut).getParent()) == null || libListOutDir.equals("")){
                libListOutDir = ".";
            }
            libListOutSuffix = new File(libListOut).getName();
            if (!new File(libListOutDir).isDirectory()) {
                throw new ParseArgumentException("Directory " + libListOutDir + " is not a directory or does not exist");
            } else {
                libListOutDir = new File(libListOutDir).getAbsolutePath();
            }

            outDir = outDirOption.getStringValue();
            if (!new File(outDir).isDirectory()) {
                throw new ParseArgumentException("Wrong 'outDir' directory: " + outDir);
            } else {
                outDir = new File(outDir).getAbsolutePath();
            }

            el = elOption.getStringValue();
            if ((elDir = new File(el).getParent()) == null || elDir.equals("")){
                elDir = ".";
            }
            elSuffix = new File(el).getName();
            if (!new File(elDir).isDirectory()) {
                throw new ParseArgumentException("Directory " + elDir + " is not a directory or does not exist");
            } else {
                elDir = new File(elDir).getAbsolutePath();
            }

            dirList = dirListOption.getStringValue();
            if (!new File(dirList).isFile()) {
                throw new ParseArgumentException("Wrong 'dirList' file: " + dirList);
            }
            if (copyrightLinkOption.isSet()){
                copyrightLink = copyrightLinkOption.getStringValue();
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
        BundleTestGenFilter bundletestgenfilter = new BundleTestGenFilter(System.out, System.err);
        bundletestgenfilter.setProgramName(CtStr_ToolName);
        System.exit(bundletestgenfilter.run(args));
    }

    /** Constructor.
     *
     * @param out The print stream for writing program information.
     * @param err The print stream for error diagnostics.
     *
     * @see java.io.PrintStream
     */
    public BundleTestGenFilter(PrintStream out, PrintStream err) {
		super(out, err);
		m_needsCommandLineArguments = true;
    }

    /**
     *  Run TestGenFilters.
     */
    public void startTool() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(dirList)));
            boolean isAbsolute = false;
            int i = 0;
            for (String dname = reader.readLine(); dname != null; dname = reader.readLine()) {
                if (++i == 1) {
                    isAbsolute = dname.startsWith(baseDir);
                }
                String dirPath = isAbsolute == true ? dname.substring(baseDir.length()): dname;
                int uniqID = getUniqID();

                Vector args = new Vector();
                args.add("-plugin");
                args.add(plugin);
                args.add("-exlist");
                args.add(exList);
                args.add("-liblistOut");
                args.add(libListOutDir + File.separator + uniqID + libListOutSuffix);
                args.add("-o");
                args.add(outDir + File.separator + dirPath);
                args.add("-el");
                args.add(elDir + File.separator + uniqID + elSuffix);
                
                System.setProperty(EXLIST_SYSPROP, exList);
                
                if (copyrightLink != null){
                    String finalToOutRelativePath = getRelativePath((String)args.get(7), outDir);
                    // this should never happen:
                    if (finalToOutRelativePath == null){
                        reportErrorMsg("outDir: " + outDir + " is not a subdirectory of final test dir: " + (String)args.get(7));
                        setResultCode(ctInt_ErrorCode_Error);
                        break;
                    }
                    String copyrightPath = finalToOutRelativePath.equals("") ? copyrightLink : finalToOutRelativePath + File.separator + copyrightLink;
                    args.add("-emitter.copyrightLink=" + copyrightPath);
                }

                String inDir = baseDir + File.separator + dirPath;
                String[] complete_args = addOperands(args, inDir);
                if (complete_args != null){
                    reportOutputMsg("\n" + "...running TestGenFilter " + getString(complete_args));
                    int res = new TestGenFilter(getStandardOut(), getStandardErr()).run(complete_args);
                    if (res != ctInt_ErrorCode_NoError){
                        reportErrorMsg("Error occured running the last TestGenFilter iteration.");
                        setResultCode(ctInt_ErrorCode_Error);
//                        break;
                    }
                } else {
                    reportOutputMsg("\n" + "...running TestGenFilter " + getString((String[])args.toArray(new String[0])));
                    reportOutputMsg("\n" + " no .test.xml or .doc.xml files in " + inDir);
                }
            }
        } catch (Exception e) {
            reportErrorMsg(e.getMessage());
            setResultCode(ctInt_ErrorCode_Error);
        }
        return;
    }

    protected String getRelativePath(String fullPath, String parentPath){
        if (fullPath.equals(parentPath)){
            return "";
        }
        if (!fullPath.startsWith(parentPath)){
            return null;
        }
        String p = "..";
        while((fullPath = new File(fullPath).getParent()) != null && !fullPath.equals(parentPath)){
            p = p + File.separator + "..";
        }
        if (fullPath == null){
            return ""; // this should never happen.
        }
        return p;
    }

    protected String getString(String[] str_arr){
        String str = "";
        for (int i = 0; i < str_arr.length; ++i){
            str += " " + str_arr[i];
        }
        return str;
    }
    
    protected String[] addOperands(Vector predefinedArgs, String inDir){
        File[] filesInDir = new File(inDir).listFiles();
        if (filesInDir == null || filesInDir.length == 0) {
            return null;
        }
        Vector xml_files = new Vector();
        for (int i = 0; i < filesInDir.length; ++i){
            String fileName = filesInDir[i].getName();
            if (fileName.endsWith(".tdoc.xml") || fileName.endsWith(".test.xml")){
                xml_files.add(filesInDir[i].getAbsolutePath());
            }
        }
        if (xml_files.size() == 0){
            return null;
        }
        for (int i = 0; i < xml_files.size(); ++i){
            predefinedArgs.add(xml_files.get(i));
        }
        return (String[])predefinedArgs.toArray(new String[0]);
    }
    
    protected int getUniqID(){
        return ++cur_num;
    }
}





























