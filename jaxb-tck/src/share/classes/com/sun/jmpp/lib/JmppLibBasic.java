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

package com.sun.jmpp.lib;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;


import javasoft.sqe.jckutils.lib.JmppScript;
import com.sun.jmpp.JmppLib;
import com.sun.jmpp.JmppException;
import com.sun.jmpp.share.Options;
import com.sun.jmpp.share.OptionDescr;

/**
 * Base class to be executed by Jmpp macro preprocessor for generating
 * final macroprocessor output for test generation.
 *
 * @author Kevin T. Looney
 * @version @(#)JmppLibBasic.java	1.0 04/09/15
 */

public class JmppLibBasic
	extends com.sun.jmpp.JmppLib {

    public JmppLibBasic() {
        
    }


    /**
     * This is the only way to (externally) reset the input name to
     * arg[0] (since it was just put into outName).
     */
	public void resetInputName() {
	    inName = outName;
	}

    /**
     * This is the only way to (externally) call setOutput.
     */
	public void resetOutputName_Hook() {
	    if (inName.indexOf(".jmpp") >= 0) {
		String outName = inDir + inName.substring(0, inName.indexOf(".jmpp"));
		setOutput(outName);
	    }
	}

    /**
     * Hook - allows sub-classes to change the output filename (from the default jmpp filename)
     *  to some other filename - before the makeOut() call.
     * @param out where to output the redirect
     * @see #makeOut()
     */
	public void redirectOutputFile_Hook(PrintWriter out) {
            // initialize the input file.
		out.println("            x.resetInputName();");

            // then redirect the output file.
		out.println("            x.resetOutputName_Hook();");
	}


    /**
     * Method to override in jmpp source file and other Jmpp libraries.
     * To generate final output, the second-step library instance invokes
     * this method.
     * Note that it can be overriden in the source file only if the
     * generateProlog method is also overriden not to declare the makeOut()
     * method (or a compile error will occur). It is suggested to override the
     * prolog and epilog generation methods in a library, and this method -
     * in the jmpp source file itself.
     */
	public void make() {
	}

    /**
     * Method to override in jmpp source file and other Jmpp libraries.
     * To generate final output, the second-step library instance invokes
     * this method.
     * Note that Users generally should NOT override this method (since it redefines the
     * output filename), they should override make().
     */
	public void makeOut() {
            // re-define output.
	    resetOutputName_Hook();
	    make();
       }

    /**
     * Generates main method of the i-class (which simply creates an instance
     * of itself and calls its makeOut and closeOut methods), and instance
     * initializer for the className field.
     * @see #className
     * @see #makeOut()
     * @see #closeOut()
     */
	protected void generateMain(PrintWriter out, String shortClassName) {
		String s = getClass().getName().replace('$', '.');
		out.println("    {");
		out.println("        className = \"" + shortClassName + "\";");
		out.println("    }");
		out.println("    public static void main(String[] argv) {");
		out.println("        " + s + " x = null;");
		out.println("        try {");
		out.println("            x = (" + s + ") Class.forName(\"" +
                    intermediateClassName + "\").newInstance();");
		out.println("        } catch (Throwable e) {\n"+
                    "            System.err.println(\"" + shortClassName +
                    " error:\");\n" +
                    "            e.printStackTrace();\n" +
                    "            throw new com.sun.jmpp.JmppException" +
                    "(\"Could not create library instance\");\n" +
                    "        }");
		out.println("        if (argv.length > 0) {");
		out.println("            x.outName = argv[0];");
		redirectOutputFile_Hook(out);
		out.println("        }");
		out.println("        x.makeOut();");
		out.println("        x.closeOut();");
		out.println("    }");
	}




    /**
     * Overridden to surpress the makeOut() method definition.
     * @param out where to output the prolog
     * @param shortClassName short i-class name (without package)
     */
	protected void generateProlog(PrintWriter out, String shortClassName) {
		out.println("package " + templatePackage + ";\n");
		out.println("public class " + shortClassName+" extends " +
                    getClass().getName()+" {");
	}


    /**
     * Overridden to surpress the makeOut() closeing bracket.
     * @param out where to output the epilog
     * @param shortClassName short i-class name (without package)
     */
	protected void generateEpilog(PrintWriter out, String shortClassName) {
		generateMain(out, shortClassName);
		out.println("}");
	}





    /**
     * Override - If the (non-qualified) filename has dots
     * replace the dots with underscore (to differentiate 
     * the filename from a directory path).
     * @param s a string (filename)
     * @return the input string without trailing (if any) .*
     */
	public String trimExtension(String s) {
        int i = s.lastIndexOf('.');
	String result =  (i < 0 ? s : s.substring(0, i));
	result =  result.replace('.', '_');

	return result;
	}

    /**
     * Command-line interface.
     * @param argv command-line arguments
     */
	public static void main(String[] argv) {
		libMain(argv,new JmppLibBasic());
	}

}
