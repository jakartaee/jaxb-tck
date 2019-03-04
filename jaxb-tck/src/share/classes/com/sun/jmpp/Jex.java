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

package com.sun.jmpp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import com.sun.jmpp.share.Options;
import com.sun.jmpp.share.OptionDescr;
import com.sun.jmpp.share.Utils;

/**
 * Java executor.
 * Reads the first line of a file specified, looks for substring
 * exToken, runs main() of a class specified after it. Parameters are
 * allowed after the name of the class and are passed to the main()
 * method being invoked.
 *
 * @author Oleg V. Ulyankin, Konstantin S. Bobrovsky
 */
public class Jex {
    static final String jexName = Jex.class.getName();
    static final String DEF_LIB_PCKG = "com.sun.jmpp.lib";
    static final String DEF_LIB_NAME = DEF_LIB_PCKG + "." + "JmppLibAPI";
    static final String exToken = "##!";

    public final static String OPT_PCKGS     = "-pp";
    public final static String OPT_FULL_NAME = "-fn";
    public final static String OPT_LOWP_NAME = "-ln";

    public final static String USG_PCKGS =
        "    -" + OPT_PCKGS + " <set of packages>\n" +
        "      specifies a set of packages to be searched for the generator library class.\n" +
        "      Different packages are separated by File.pathSeparator character.\n";
    public final static String USG_FULL_NAME =
        "    -" + OPT_FULL_NAME + " <full class name>\n" +
        "      specifies a full name of the generator library class which will be used\n" +
        "      to process given Jmpp source files. Default is " + DEF_LIB_NAME + ".\n";
    public final static String USG_LOWP_NAME =
        "    -" + OPT_LOWP_NAME + " <full class name>\n" +
        "      specifies a full class name of the generator library class which will be used\n" +
        "      only if the class name has not been specified in other ways\n";

    public final static OptionDescr DSC_PCKGS =
        new OptionDescr(OPT_PCKGS, "packages to search", OptionDescr.VAL_SINGLE, USG_PCKGS);
    public final static OptionDescr DSC_FULL_NAME =
        new OptionDescr(OPT_FULL_NAME, "full library name", OptionDescr.VAL_SINGLE, USG_FULL_NAME);
    public final static OptionDescr DSC_LOWP_NAME =
        new OptionDescr(OPT_LOWP_NAME, "secondary library name", OptionDescr.VAL_SINGLE, USG_LOWP_NAME);

    public final static OptionDescr[] validOptions = { DSC_PCKGS, DSC_FULL_NAME, DSC_LOWP_NAME };

    /**
     * Main method. Parses given arguments and makes appropriate Jmpp library
     * process the input jmpp source file.
     * @param argv command-line arguments
     */
    public static void main (String[] argv) {
        Class classToRun = null;
        String packPath = null;
        String NameOfClassToRun = null;

        Options opts = new Options(validOptions);
        String[] tail = null;
        String src_name = null;

        try {
            tail = opts.parseCommandLine(argv, false /* don't report errors on unknown option names */);
            if (tail.length < 1 || tail[tail.length - 1].startsWith(Options.OPTION_SPECIFIER))
                throw new IllegalArgumentException("source file not specified");
            src_name = tail[tail.length - 1];
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            System.out.println(getUsageInfo());
            System.exit(1);
        }

        if (opts.isSet(OPT_PCKGS))
            packPath = opts.getValue(OPT_PCKGS);

        if (opts.isSet(OPT_FULL_NAME)) {
            NameOfClassToRun = opts.getValue(OPT_FULL_NAME);
        }
        if (NameOfClassToRun == null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(argv[argv.length - 1]));
                String first_line = br.readLine();
                br.close();
                if (first_line != null && first_line.startsWith(exToken)) {
                    first_line = first_line.substring(exToken.length());
                    String[] add_args = Utils.split(first_line);
                    if (add_args.length > 0) {
                        NameOfClassToRun = add_args[0];
                        String[] sav = tail;
                        tail = new String[sav.length + add_args.length - 1];
                        System.arraycopy(add_args, 1, tail, 0, add_args.length - 1);
                        System.arraycopy(sav, 0, tail, add_args.length - 1, sav.length);
                    }
                }
            } catch (IOException e) {
                error("error reading source: " + src_name);
                System.exit(1);
            }
        }
        if (NameOfClassToRun == null && opts.isSet(OPT_LOWP_NAME)) {
            NameOfClassToRun = opts.getValue(OPT_LOWP_NAME);
        }
        if (NameOfClassToRun == null) {
            NameOfClassToRun = DEF_LIB_NAME;
        }
    
        if (NameOfClassToRun.indexOf('.') < 0 && packPath == null)
            packPath = DEF_LIB_PCKG;

        if (packPath != null) {
            String[] pckgs = Utils.split(packPath, File.pathSeparatorChar);
            for (int i = 0; i < pckgs.length; i++) {
                try {
                    classToRun = Class.forName(pckgs[i] + "." + NameOfClassToRun);
                    break;
                } catch (ClassNotFoundException e) {}
            }
        } else {
            try {
                classToRun = Class.forName(NameOfClassToRun);
            } catch (ClassNotFoundException e) {}   
        }

        if (classToRun == null) {
            error("generator library class " + NameOfClassToRun + " not found");
            System.exit(1);
        }
        
        try {
            Method meth = classToRun.getMethod("main", new Class[] { String[].class });
            meth.invoke(classToRun, new Object[] { tail });
        } catch (InvocationTargetException e) {
            Throwable trg_excp = e.getTargetException();
            if (trg_excp instanceof JmppException) {
                error(trg_excp.getMessage());
            } else {
                error("generator library " + classToRun.getName() + " thrown an exception:");
                trg_excp.printStackTrace(System.err);
            }
            System.exit(-1);
        } catch (Throwable e) {
            error("an exception thrown during execution :");
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public static String getUsageInfo() {
        String res =
            "Usage:\n\n" +
            "  % java <system options> " + jexName + " <Jex options>" +
            " <library options> <source>\n" +
            "\n<Jex options>:\n";

        for (int i = 0; i < validOptions.length; res += validOptions[i].getUsage(), i++);

        res +=
            "\n<library options>:\n" +
            "  options which are passed to the created generator library class instance\n" +
            "\n<source>:\n" +
            "  the name of jmpp source file which is to be processed\n";

        return res;
    }

    /**
     * Reports an error.
     * @param msg an error to report
     */
    protected static void error(String msg) {
        System.err.println("*** " + jexName + " error: " + msg);
    }
}
