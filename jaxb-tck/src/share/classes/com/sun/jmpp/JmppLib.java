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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.jmpp.share.Options;
import com.sun.jmpp.share.OptionDescr;
import com.sun.jmpp.share.Utils;
import com.sun.jmpp.share.DirClassLoader;

import com.sun.jmpp.lib.TargetSpec;
import com.sun.jmpp.lib.TargetSpecElem;

/**
 * Base class to be executed by Jmpp macro preprocessor for generating
 * final macroprocessor output. Together with JmppReader it constitutes
 * basic preprocessor implementation, which provides only "preprocessing"
 * features in usual sense. It serves as a superclass for those classes
 * which want to use the Jmpp engine for more complicated and specific
 * purposes, e.g. for generating tests from a single jmpp source file.
 * Such classes are usually referred to as "Jmpp libraries". <br>
 * Acronyms used :<br>
 * i-program - intermediate program<br>
 * i-class   - intermediate class<br>
 *
 * @author Oleg V. Ulyankin, Konstantin S. Bobrovsky
 * @version @(#)JmppLib.java	1.62 07/10/11
 */
public class JmppLib implements JmppLibIntf {
	public static final String JMPP_EXT = ".jmpp";

    /**
     * Name of the option which makes Jmpp only generate i-program.
     */
	public static final String OPT_PP_ONLY = "p";

    /**
     * Working directory option name.
     */
	public static final String OPT_WRK_DIR = "w";

    /**
     * Output directory option name.
     */
	public static final String OPT_OUT_DIR = "o";

    public final static String USG_PP_ONLY =
        "    -" + OPT_PP_ONLY + "\n" +
        "     generate only intermediate program (do not generate tests)\n";

    public final static String USG_WRK_DIR =
        "    -" + OPT_WRK_DIR + " <work directory>\n" +
        "     specifies working directory, where the intermediate program source\n" +
        "     and class will be placed\n";

    public final static String USG_OUT_DIR =
        "    -" + OPT_OUT_DIR + " <output directory>\n" +
        "     specifies output directory where the generated tests will be placed.\n" +
        "     Does not have an effect when the -" + OPT_PP_ONLY + " is specified\n";

    public final static OptionDescr DSC_PP_ONLY =
        new OptionDescr(OPT_PP_ONLY, "preprocess only", USG_PP_ONLY);

    public final static OptionDescr DSC_WRK_DIR =
        new OptionDescr(OPT_WRK_DIR, "work directory", OptionDescr.VAL_SINGLE, USG_WRK_DIR);

    public final static OptionDescr DSC_OUT_DIR =
        new OptionDescr(OPT_OUT_DIR, "output directory", OptionDescr.VAL_SINGLE, USG_OUT_DIR);

    public final static OptionDescr[] validOptions = { DSC_PP_ONLY, DSC_WRK_DIR, DSC_OUT_DIR };

    /**
     * Name of a method whose calls are inserted into the intermediate code
     * and wich actually calculates a run-time String value of a macro or a
     * String.
     * @see #screenString(String)
     */
	public static final String stringContextScreenMethod = "screenString";

    /**
     * Standard extension of a Java source file.
     */
    public final static String JAVA_EXT = ".java";

    /**
     * A directory to put the generated sources into.
     */
	protected String outputDir = ".";

    /**
     * A vector of delayed macros.
     */
	protected Vector unflushedMacros = new Vector();

    /**
     * StringBuffer object to fill output into, if set.
     * @see #setOutput
     */
	protected StringBuffer outBuffer;

    /**
     * Variable to store name of the file to write the output to.
     */
	public String outName;

    /**
     * Stream where all error/warning messages of the underlying
     * JmppReader are redirected.
     */
	protected PrintWriter log = new PrintWriter(System.err, true);

    /**
     * Macro containing the name of a currently generated source file
     * without extension.
     * @see #setOutput(String)
     */
	protected String fileName;

    /**
     * Contains short class name of the i-program.
     * Assigned at the second (generation) step.
     * @see #setOutput
     */
	protected String className;

    /**
     * Contains a name of the intermediate class package.
     */
	public String templatePackage = "template"; //KONST: should be protected!

    /**
     * Lib specific suffix for generated file/class names
     */
    protected String CLASS_SUFF = "";

    /**
     * Contains a File object corresponding to the input file.
     */
    protected File inFile = null;

    /**
     * Contains name of a directory where the i-program
     * and its compiled class are placed.
     */
	protected String workJavaDir = ".";

    /**
     * 'dirname' (in unix sense) of the inFile.
     * Default is current dir.
     * @see #inFile
     */
	protected String inDir = "." + File.separator;

    /**
     * Specifies whether old-style macros should be processed
     * @see #transform(String)
     */
	protected boolean processOldMacroInTextBlocks = false;

    /**
     * First step preprocessor instance. Created to transform a jmpp source
     * to an intermediate Java program compile and create the second
     * (xLib) step preprocessor instance. Currently not used, but maybe
     * necessary to be used in jmpp source files.
     */
	protected JmppLib stepOneLib;

    /**
     * Second step preprocessor instance. Created by the first step
     * preprocessor instance (stepOneLib). Performs actual generation of output.
     * Actually contains a reference to the i-class instance.
     */
	protected JmppLib stepTwoLib;

    /**
     * Name of a main Java compiler class, used to compile the i-program.
     * Re-assigned it in subclasses to use different compiler.
     */
    protected String javacName = "com.sun.tools.javac.Main";

    /**
     * Name of a compiler's method which is invoked (via reflection) to
     * compile the i-program.
     */
    protected String compileMethodName = "compile";

    /**
     * Keeps the name of the i-program.
     */
    protected String intermediateSrcName;

    /**
     * Keeps the name of the i-class.
     */
    protected String intermediateClassName;

    /**
     * Controls whether the generated (at the first step) i-program
     * gets compiled and run by the preprocessor or not.
     */
	protected boolean doCompilation = true;

    /**
     * Keeps input file name (usually, jmpp source file name).
     */
	protected String inName = null;

    /**
     * maps a test name to a vector of the test's sources
     */
    protected Hashtable intfAllTests = new Hashtable();

    /**
     * contains current html index file referring all generated tests
     */
    protected String indexHtml;

    /**
     * last file to which an output occured
     */
    protected File outputFile;

    /**
     * Default constructor.
     */
    public JmppLib() {}

    /**
     * Creates new JmppLib object. Alternative to starting the preprocessor
     * via the main(String[]) method.
     * @param in File object corresponding to a file to be processed.
     */
	public JmppLib(File in) {
		inFile = in;
		inName = in.getName();
	}

    /**
     * Generates 'prolog' of the i-program: package, i-class and makeOut method
     * declarations. Override in subclasses to extend the prolog generated,
     * for example, to add necessary imports. The contens of the jmpp source
     * file (transformed by JmppReader) is actually wrapped by the code
     * generated by this method and by generateEpilog method (can be not true
     * for subclasses).
     * @param out where to output the prolog
     * @param shortClassName short i-class name (without package)
     */
	protected void generateProlog(PrintWriter out, String shortClassName) {
		out.println("package " + templatePackage + ";\n");
		out.println("public class " + shortClassName+" extends " +
                    getClass().getName()+" {");
		out.println("public void makeOut() {");
	}

    /**
     * Generates 'epilog' of the i-program: closing brace for makeOut method
     * declaration, main method and closing brace for i-class declaration.
     * @param out where to output the epilog
     * @param shortClassName short i-class name (without package)
     */
	protected void generateEpilog(PrintWriter out, String shortClassName) {
		out.println("\n}");
		generateMain(out, shortClassName);
		out.println("}");
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
		out.println("        if (argv.length > 0)");
		out.println("            x.outName = argv[0];");
		out.println("        x.makeOut();");
		out.println("        x.closeOut();");
		out.println("    }");
	}

    /**
     * Base method that screens all double quotes and backslashes of
     * given string. This method is used for dynamic screening of macros,
     * found within string context of object-level text. These macros
     * start with sequence '@' as macros within meta-text do.
     * Backslash indicates that within strings in object-level text
     * all macros are dynamically screened so it would be passed to
     * final output as it is.
     */
	public String screenString(String stringToScreen) {
		String newString = new String();
		char c;
		if (stringToScreen == null)
			throw new JmppException(JmppException.SYNTAX_ERROR,
                                    "macro used in string context is null.");
		else
			for (int i = 0; i < stringToScreen.length(); i++) {
				c = stringToScreen.charAt(i);
				if (c == '\\' || c == '"') {
					newString += "\\";
                }
				newString += c;
			}
		return newString;
	}

	public String screenString(int value) {
        return new Integer(value).toString();
    }

    public String screenString(boolean value) {
        return new Boolean(value).toString();
    }

	public String screenString(float value) {
        return new Float(value).toString();
    }

	public String screenString(double value) {
        return new Double(value).toString();
    }

	public String screenString(char value) {
        return new Character(value).toString();
    }

	public String screenString(byte value) {
        return new Byte(value).toString();
    }

	public String screenString(short value) {
        return new Short(value).toString();
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
	public void makeOut() {}

    /**
     * Output a string with trailing '\n'. You do not normally need to
     * call this method.
     * @param s a string to be written to set up output.
     */
	protected void L(String s) {
		lEndOfLine(s, "\n");
	}

    /**
     * Output a string without trailing '\n'. You do not normally need
     * to call this method.
     * @param s a string to be written to set up output.
     */
	protected void LN(String s) {
		lEndOfLine(s, "");
	}

    /**
     * Output a string to set up output file. You do not normally need
     * to call this method.
     * @param s a string to be written to set up output.
     * @param eol string epilog
     */
	protected void lEndOfLine(String s, String eol) {
		if (outBuffer == null)
			setOutput(outName);
		if (s != null)
			outBuffer.append(s + eol);
	}

    /**
     * You have to call this method when you have to change the output file.
     * @param fileName name of the file to set the output to.
     */
	protected void setOutput(String fileName) {
		closeOut();
		this.fileName = fileName;
		outBuffer = new StringBuffer();
	}

    /**
     * Final output is first written to a StringBuffer, this method flushes the
     * StringBuffer to designated file.
     */
	public void closeOut() {
		if (outBuffer == null)
			return;
		DelayedMacro dm;
		// now, flush all macros we have in the
		// vector unflushedMacros (unflushed explicitly)
		String strOut = outBuffer.toString();
		for (int i = 0; i < unflushedMacros.size(); i++) {
			dm = (DelayedMacro)unflushedMacros.elementAt(i);
			strOut = replace(strOut, dm.anchor, dm.value, 0);
		}
		unflushedMacros = new Vector();
		try {
			outBuffer = null;
			BufferedWriter bufOut = createOutputWriter();
            String tmp = postProcessOutput(strOut, getOutputFile());
            strOut = tmp == null ? strOut : tmp;
			bufOut.write(strOut, 0, strOut.length());
			bufOut.flush();
			bufOut.close();
		} catch (IOException e) {
            throw new JmppException(e);
		}
	}

    /**
     * @return the File object corresponding to the file
     *     for which the last invocation of {@link #createOutputWriter()}
     *     created a <code>BufferedWriter</code>.
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * Allows to update contents of current output file before
     * it is written to disk.
     *
     * @param contents current contents of the file which is about
     *     to be dumped to disk
     * @param file the <code>File</code> object corresponding to
     *     the current output file
     * @return <code>null</code> if the contents are not updated
     *     by this method, otherwise - the updated contents.
     */
    public String postProcessOutput(String contents, File file) {
        // this default implementation does nothing
        return null;
    }

    /**
     * @return BufferedWriter which can be used to write to the final output
     *         file
     */
	protected BufferedWriter createOutputWriter() throws IOException {
		String dir2Create = outputDir;
		if (outputDir.equals(".")) {
			int i = fileName.lastIndexOf(File.separator);
			if (i != -1)
				dir2Create = fileName.substring(0, i);
		}
		File d = new File(dir2Create);
		if(!d.exists() && !mkdirs(d))
			throw new JmppException(JmppException.SYSTEM_ERROR,
                                    "can't create directory: " + dir2Create);
        outputFile = new File(fileName);
		return new BufferedWriter(new FileWriter(fileName));
	}

    /**
     * Replace all occurrences of 'out' with 'in' for the string 'src',
     * starting from fromIndex.
     * @param src source string.
     * @param out substring of 'src' to replace with 'in'.
     * @param in string to insert into 'src' instead of 'out' for
     *       each occurrence of 'out'.
     * @param fromIndex index in the 'src' to start the replacements from.
     */
	public static String replace(String src,
                                 String out,
                                 String in,
                                 int fromIndex) {
		while ((fromIndex = src.indexOf(out, fromIndex)) != -1) {
			src = src.substring(0, fromIndex) + in +
                  src.substring(fromIndex + out.length(), src.length());
			src = replace(src, out, in, fromIndex);
		}
		return src;
	}

    /**
     * @param s a string (filename)
     * @return the input string without trailing (if any) .*
     */
	public String trimExtension(String s) {
        int i = s.lastIndexOf('.');
        return (i < 0 ? s : s.substring(0, i));
	}

    /**
     * Macro to check if specified system property is defined.
     * @param property a property to check.
     */
	public boolean defined(String property) {
		return (System.getProperty(property) != null);
	}

    /**
     * Sets the output stream for JmppReader messages.
     * @param log stream to set the output to.
     */
	public void setLog(PrintWriter log) {
		this.log = log;
	}

    /**
     * @return output stream for JmppReader messages.
     */
	public PrintWriter getLog() {
		return log;
	}

    /**
     * This class implements a so-called "delayed macro". Sometimes,
     * the value of a macro used in run-time by the i-program
     * is not defined at the moment its value is accessed and it becomes
     * defined at later stages of the execution of the i-program. In this
     * case a user should use the delayed macro instance. The method which
     * actually generates the final output file (closeOut) will replace
     * all occurances of delayed macros in its output buffer with their
     * current String values, and flush the buffer to a file after that.
     * The "occurances" are strings, returned by the macro's underlying
     * Object instance' toString() method.
     */
	public class DelayedMacro {
		public String value;
		public String anchor;

		public String toString() {
			return set();
		}

		public String set() {
			// add this macro to the vector of unflushed macros
			unflushedMacros.addElement(this);
			anchor = super.toString(); // Object.toString() - unique string
			return anchor;
		}

		public void flush() {
			String strOut = outBuffer.toString();
			strOut = replace(strOut, anchor, value, 0);
			outBuffer = new StringBuffer(strOut);
			unflushedMacros.removeElement(this);
		}
	}

    /**
     *   Tries to create directory with specified path
     *
     */
    public static boolean mkdirs(String dir) {
	return mkdirs(new File(dir));
    }

    /**
     *   Tries to create directory with path specified by File object
     *
     */
    public static boolean mkdirs(File dir) {
	if (dir.exists())
	    return dir.isDirectory();

	File f = dir.isAbsolute()? dir
				 : dir.getAbsoluteFile();

	File p = f.getParentFile();
	if (!mkdirs(p))
	    return false;

	return f.mkdir() || f.isDirectory() ;
    }

    /**
     * First step preprocessor routine. Transforms given jmpp source file
     * into the i-program.
     * @param name jmpp source file name to be transformed
     * @return i-program's File instance
     */
	public File transform(String name) throws IOException {
		File gen_prog;
		JmppReader br;
		File d = new File(workJavaDir);

		if (!d.exists() && !mkdirs(d)) {
            String msg = "can't create working directory: " + workJavaDir;
			throw new JmppException(JmppException.SYSTEM_ERROR, msg);
        }
		if (name != null)
			br = new JmppReader(new FileReader(inDir + name));
		else {
			name = inFile.getName();
			br = new JmppReader(new FileReader(inFile));
		}
        String short_class_name = trimExtension(name) + CLASS_SUFF;
        intermediateClassName = templatePackage + "." + short_class_name;
		gen_prog = new File(workJavaDir, short_class_name + JAVA_EXT);
		PrintWriter out = new PrintWriter(new FileWriter(gen_prog), true);
		br.setLog(log);
		br.processOldMacroInTextBlocks(processOldMacroInTextBlocks);
		generateProlog(out, short_class_name);
		String line = null;
        String err_msg = "";
		while (true) {
            try {
                line = br.readLine();
            } catch (BadSyntaxException e) {
                line = e.getLine();
                err_msg += "line " + e.getLineNum() + ": " +
                           e.getMessage() + "\n";
            }
            if (line == null)
                break;
			out.print(line);
        }
		generateEpilog(out, short_class_name);
		br.close();
		out.close();
        if (!err_msg.equals(""))
            throw new JmppException(JmppException.SYNTAX_ERROR, err_msg);
        intermediateSrcName = gen_prog.getAbsolutePath();
		return gen_prog;
	}

    public static final String JAVAC_THREAD_SAFE_PROPERTY_NAME = "javac.thread.safe";
    public static final String JMPP_COMPILER_NAME_PROPERTY = "jmmp.compiler.name";
    /**
     * Invokes Java compiler to compile given .java source.
     * @param src source file name to be compiled
     */
    public void compile(String src) throws Exception {
        String ambientClassPath = System.getProperty("java.class.path");
        if (ambientClassPath == null)
            ambientClassPath = ".";
        String[] argv = {
            "-classpath",
            ambientClassPath,
            "-d",
            workJavaDir,
            src
        };
        Object ret = null;
        String jmppCompilerName = System.getProperty(JMPP_COMPILER_NAME_PROPERTY);

        if(jmppCompilerName != null) {
            javacName =  jmppCompilerName;
        }       
        try {
            String javacThreadSafety = System.getProperty(JAVAC_THREAD_SAFE_PROPERTY_NAME);
            if (javacThreadSafety == null || !javacThreadSafety.equals("true")){
                ret = compileSuccessive(argv);
            } else {
                ret = compileParallel(argv);
            }
        } catch (Throwable e) {
            throw new JmppException(e);
        }
        if (ret instanceof Integer && ((Integer)ret).intValue() != 0 ||
            ret instanceof Boolean && !((Boolean)ret).booleanValue())
            throw new JmppException(JmppException.COMPILATION_ERROR, src);
    }


   
    protected Object compileSuccessive(String[] argv) throws Throwable {
        Object ret;
        Class c = Class.forName(javacName);
        synchronized (c){
            Object o = c.newInstance();
            Class[] arg_types = new Class[] { argv.getClass() };
            Object[] args = new Object[] { argv };
            Method m = c.getMethod(compileMethodName, arg_types);
            ret = m.invoke(o, args);
         }
         return ret;
    }

    private Object compileParallel(String[] argv) throws Throwable {
        Object ret;
        Class c = Class.forName(javacName);
        Object o = c.newInstance();
        Class[] arg_types = new Class[] { argv.getClass() };
        Object[] args = new Object[] { argv };
        Method m = c.getMethod(compileMethodName, arg_types);
        ret = m.invoke(o, args);
        return ret;
    }

    /**
     * Second step preprocessor routine. Compiles given i-program and runs it.
     * @param inFileName name of the file to be compiled and executed.
     */
	public void compileAndRun(String inFileName) {
		try {
            compile(inFileName);
            runXLib();
		} catch (JmppException e0) {
            throw e0;
		} catch (Exception e1) {
			throw new JmppException(e1);
		}
	}

    /**
     * Shortcut to runXLib(Class) with current i-class.
     * @see #runXLib(Class)
     */
	protected void runXLib() {
        try {
            Class c = null;
            String name = getIntermediateClassName();
            try {
                c = Class.forName(name);
            } catch (ClassNotFoundException e) {
                c = createDirClassLoader().loadClass(name);
            }
            runXLib(c);
		} catch (JmppException e0) {
            throw e0;
		} catch (Exception e1) {
			throw new JmppException(e1);
		}
	}

    /**
     * Creates DirClassLoader object - a ClassLoader instance to load an intermediate
     * class and related classes. We need own class loader since in single JVM there is no another way to
     * reload newly recompiled intermadiate class. The method creates DirClassLoader
     * with the list of packages of classes which need to be reloaded by the created DirClassLoader
     * containing the only package which is 'templatePackage' value, this means that all
     * classes from intermediate class's package will be loaded by the created class loader.
     * @return an instance of DirClassLoader created with the package list containing
     * 'templatePackage' value and 'workJavaDir' value as the only dir where to search
     * for classes to load by the created DirClassLoader if they are not in classpath.
     */
        protected DirClassLoader createDirClassLoader(){
            return new DirClassLoader(workJavaDir, templatePackage);
        }

    /**
     * Creates an instance of the specified subclass and runs its output
     * creation methods.
     * @param generatorClass the subclass' name
     */
    protected void runXLib(Class generatorClass) throws Exception {
        stepTwoLib = (JmppLib)generatorClass.newInstance();
        passDataTo(stepTwoLib);
        stepTwoLib.makeOut();
        stepTwoLib.closeOut();
        stepTwoLib.passDataTo(this);
    }

    /**
     * Assumes that the parameter is a subclass, and copies (some of)
     * its fields.
     */
	public void passDataTo(Object o) {
		JmppLib l = (JmppLib) o;
		l.stepOneLib = this;
		l.log		 = log;
		l.outputDir	 = outputDir;
		l.outName	 = outName;
		l.inDir		 = inDir;
		l.inFile	 = inFile;
		l.inName	 = inName;
        l.indexHtml  = indexHtml;
	}

    /**
     * Performs all steps: transformation, compilation and generation.
     */
	public void generate() { //KONST: should be protected!
		try {
            File generator_program = transform(inName);
            if (generator_program != null && doCompilation)
                compileAndRun(generator_program.getAbsolutePath());
		} catch (JmppException e0) {
            throw e0;
		} catch (Exception e1) {
			throw new JmppException(e1);
		}
	}

    /**
     * Command-line interface.
     * @param argv command-line arguments
     */
	public static void main(String[] argv) {
		libMain(argv, new JmppLib());
	}

    /**
     * This method allows the main (which should be overriden in every
     * subclass) to be simpler - i.e. to be exactly as it is here (only
     * library class name should be changed)
     * @param argv command line
     * @param p a library instance which is to parse the command line
     *          and perform preprocessing
     */
	public static void libMain(String[] argv, JmppLib p) {
		p.parseOptions(argv);
		p.generate();
	}

    /**
     * Parses given command line according to given descriptors of
     * all valid options.
     * @param argv command line arguments
     * @param validOptions descriptors of all valid options.
     * @return an object describing all options set in the command line
     */
    protected Options parseOptions(String[] argv, OptionDescr[] validOptions) {
        Options opts = new Options(validOptions);
        try {
            opts.parseCommandLine(argv, true);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            System.err.println(getUsageInfo());
            throw new JmppException("wrong usage");
        }

        if (opts.isSet(OPT_WRK_DIR)) {
            workJavaDir = opts.getValue(OPT_WRK_DIR);
        }
        if (opts.isSet(OPT_OUT_DIR)) {
            outputDir = opts.getValue(OPT_OUT_DIR);
            if (outputDir.endsWith(File.separator))
                outputDir = outputDir.substring(0, outputDir.length());
            if (outputDir.equals(""))
                outputDir = ".";
        }
        doCompilation = !opts.isSet(OPT_PP_ONLY);

        String[] file_args = opts.getArguments();
        if (file_args.length == 0 || file_args.length > 1) {
            error("wrong number of sources");
            System.err.println(getUsageInfo());
            throw new JmppException("wrong usage");
        }

        if (!file_args[0].endsWith(JMPP_EXT)) {
            error("source file name must end with \"" + JMPP_EXT + "\"");
            throw new JmppException("wrong usage");
        }

        inDir  = Utils.dirname(file_args[0]);
        inName = Utils.basename(file_args[0]);
        if (inDir.length() > 0)
            inDir += File.separator;

        outName = outputDir + File.separator + inName + ".out";
        return opts;
    }

    /**
     * Parses the command line
     * @param argv the command-line
     * @return an object describing all options set in the command line
     */
	public Options parseOptions(String[] argv) { //KONST: should be protected!
        return parseOptions(argv, getValidOptions());
	}

    /**
     * Override this method in subclasses to construct different set
     * of valid options, that can be passed to the sub-library. Typically,
     * this new set of valid options will consist of super.getValidOptions() +
     * new options defined in the sub-library.
     * With this method overriden, the parseOptions(String[]) method
     * will parse options accordingly to the sub-library options.
     * @return descriptors of all options which can be passed to this library
     */
    public OptionDescr[] getValidOptions() {
        return validOptions;
    }

    /**
     * Creates a "concatenation" of two arrays.
     * @param arr1 first array to be concatenated
     * @param arr2 second array to be concatenated
     * @return "concatenation" of arr1 and arr2. arr1 elements come first.
     */
    protected static OptionDescr[] concat(OptionDescr[] arr1, OptionDescr[] arr2) {
		OptionDescr[] res = new OptionDescr[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, res, 0, arr1.length);
		System.arraycopy(arr2, 0, res, arr1.length, arr2.length);
		return res;
    }

    public void error(String msg) {
        System.err.println("*** " + getClass().getName() + " error: " + msg);
    }

    /**
     * @return usage information for the library
     */
    public String getUsageInfo() {
        OptionDescr[] validOptions = getValidOptions();
        String res =
            "Usage:\n\n" +
            "  % java <system options> " + getClass().getName() + " <options> <Jmpp source>\n" +
            "Options:\n";

        for (int i = 0; i < validOptions.length; res += validOptions[i].getUsage(), i++);

        res += "<Jmpp source> : a source file to be processed by the library\n";
        return res;
    }

    /**
     * @param tmpl_name jmpp source file name
     * @return a name of the i-program file calculated from given
     *         jmpp source file
     */
    public String getIntermediateSourceName(String tmpl_name) {
        if (tmpl_name == null)
            return null;
        int ind = tmpl_name.lastIndexOf(File.separator);
        tmpl_name = ind >= 0 ? tmpl_name.substring(ind + 1) : tmpl_name;
        String res = workJavaDir == null ? "" : workJavaDir + File.separator;
        res += trimExtension(tmpl_name) + CLASS_SUFF + JAVA_EXT;
        return res;
    }

    // Below follows the implementation of the JmppLibIntf interface

    /**
     * Creates the i-program from given jmpp source file and compiles it.
     * @param inFile jmpp source file name
     */
    public void preprocessAndCompile(String inFile) throws Exception {
		parseOptions(new String[] { inFile });
        File generator_program = transform(inName);
        if (generator_program != null && doCompilation)
            compile(generator_program.getAbsolutePath());
    }

    public void generateTests(Class generatorClass) throws Exception {
        runXLib(generatorClass);
    }

    public void redirectIntermediateOutput(boolean to_mem) {}

    public String getIntermediateSourceName() {
        return intermediateSrcName;
    }

    /**
     * @return the name of the i-class (it is calculated if necessary)
     */
    public String getIntermediateClassName() {
        if (intermediateClassName == null) {
            String short_class_name = trimExtension(inName) + CLASS_SUFF;
            intermediateClassName = templatePackage + "." + short_class_name.replace('$', '.');
        }
        return intermediateClassName;
    }

    public StringBuffer getIntermediateSource() {
        return null;
    }

    public void redirectOutput(boolean to_mem) {}

    public StringBuffer[] getSources(String test_name) {
        return null;
    }

	public void setOutputDir(String s) {
        outputDir = s;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setWorkDir(String s) {
        workJavaDir = s;
    }

	public String getWorkDir() {
        return workJavaDir;
    }

    public Object getWizardDescriptor() {
        return null;
    }

    public String getInfo() {
        return "Most basic Jmpp library";
    }

    public String[] getTestNames() {
        JmppLib x_lib = stepTwoLib;
        String[] res = new String[x_lib.intfAllTests.size()];
        Enumeration e = x_lib.intfAllTests.keys();
        for (int i = 0; e.hasMoreElements(); i++) {
            res[i] = (String)e.nextElement();
        }
        return res;
    }

    public String[] getSourcesNames(String test_name) {
        JmppLib x_lib = stepTwoLib;
        Vector v = (Vector)x_lib.intfAllTests.get(test_name);
        if (v == null)
            return null;
        String[] res = new String[v.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (String)v.elementAt(i);
        }
        return res;
    }

    public String getHtmlIndexFile() {
        return indexHtml;
    }

    public void setDebugMode(boolean mode) {}

    //                                            //
    // --- RequiredResource support methods ---   //
    //                                            //

    /**
     * The list that contains all RequiredResources of the current testcase.
     */
    protected Vector testcaseRequiredResources = new Vector();

    /**
     * The list that contains all RequiredResources of the current testGroup.
     */
    protected Vector testGroupRequiredResources = new Vector();


    /**
     * Adds new RequiredResource for the current testcase
     */
    public void addTestcaseRequiredResource(String name, String value) {
        testcaseRequiredResources.add(new Pair(name, value));
    }

    /**
     * Adds new RequiredResource for the current testGroup
     */
    public void addTestGroupRequiredResource(String name, String value) {
        testGroupRequiredResources.add(new Pair(name, value));
    }

    /**
     * Revomes all RequiredResource for the current testcase
     */
    public void cleanTestcaseRequiredResource() {
        testcaseRequiredResources.clear();
    }

    /**
     * Revomes all RequiredResource for the current testGroup
     */
    public void cleanTestGroupRequiredResource() {
        testGroupRequiredResources.clear();
    }


    /**
     * Returns enumeration of RequiredResources for the current testcase
     */
    public Enumeration getTestcaseRequiredResources() {
        return testcaseRequiredResources.elements();
    }

    /**
     * Returns enumeration of RequiredResources for the current testGroup
     */
    public Enumeration getTestGroupRequiredResources() {
        return testGroupRequiredResources.elements();
    }

    /**
     * Inner class that contains implementation for name, value pair.
     */
    public static class Pair {
        String name, value;

        public Pair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //                                            //
    // ------ TargetSpecs support methods -----   //
    //                                            //

    /**
     * The list that contains all TargetSpecs  of the current testcase.
     */
    protected ArrayList testcaseTargetSpecs = new ArrayList();

    /**
     * The list that contains all TargetSpecs of the current testGroup.
     */
    protected ArrayList testGroupTargetSpecs = new ArrayList();


    /**
     * Return the list containing all TargetSpecs  of the current testcase
     */
    public ArrayList getTestcaseTargetSpec() {
        return testcaseTargetSpecs;
    }

    /**
     * Return the list containing all TargetSpecs  of the current testGroup
     */
    public ArrayList getTestGroupTargetSpec() {
        return testGroupTargetSpecs;
    }

    /**
     * Adds new TargetSpec with no SpecElems for the current testcase
     */
    public void addTestcaseTargetSpec(String id, String version) {
        addTestcaseTargetSpec(id, version, null);
    }

    /**
     * Adds new TargetSpec with a specified list of SpecElems
     * for the current testcase
     */
    public void addTestcaseTargetSpec(String id,
                                      String version,
                                      ArrayList specelems)
    {
        testcaseTargetSpecs.add(createTargetSpec(id, version, specelems));
    }

    /**
     * Adds new TargetSpec with no SpecElems for the current testgroup
     */
    public void addTestGroupTargetSpec(String id, String version) {
        addTestGroupTargetSpec(id, version, null);
    }

    /**
     * Adds new TargetSpec with a specified list of SpecElems
     * for the current testgroup
     */
    public void addTestGroupTargetSpec(String id,
                                       String version,
                                       ArrayList specelems)
    {
        testGroupTargetSpecs.add(createTargetSpec(id, version, specelems));
    }

    /**
     * Creates new TargetSpecElem with a specified name and value
     */
    public static TargetSpecElem createTargetSpecElem(String name, String value) {
        return new TargetSpecElemImpl(name, value);
    }

    /**
     * Creates new TargetSpec with a specified id, version and
     * adds given spec elements to it.
     * @param id spec id
     * @param version spec version
     * @param elems initial spec elements
     * @return created TargetSpec object
     */
    public static TargetSpec createTargetSpec(String id, String version, ArrayList elems) {
        return new TargetSpecImpl(id, version, elems);
    }

    /**
     * adds TargetSpecElem to the specified TargetSpec from TestGroup. If the
     * TargetSpec with given ID and Version does not exist in the TestGroup,
     * then it will be created. If the TargetSpec already contains the
     * TargetSpecElem with given name and value, the method do nothing.
     * @param id the ID of the TargetSpec
     * @param version the Version of the TargetSpec
     * @param name the name of the TargetSpecElem
     * @param value the value of the TargetSpecElem.
     */
    public void addTestGroupTargetSpecElem(String id,
                                           String version,
                                           String name,
                                           String value)
    {
        addTargetSpecElem(testGroupTargetSpecs, id, value, name, value);
    }

    /**
     * adds TargetSpecElem to the specified TargetSpec from TestCase. If the
     * TargetSpec with given ID and Version does not exist in the TestGroup,
     * then it will be created. If the TargetSpec already contains the
     * TargetSpecElem with given name and value, the method do nothing.
     * @param id the ID of the TargetSpec
     * @param version the Version of the TargetSpec
     * @param name the name of the TargetSpecElem
     * @param value the value of the TargetSpecElem.
     */
    public void addTestcaseTargetSpecElem(String id,
                                          String version,
                                          String name,
                                          String value)
    {
        addTargetSpecElem(testcaseTargetSpecs, id, version, name, value);
    }

    private static TargetSpec findTargetSpec(ArrayList specs,
                                             String id,
                                             String version)
    {
        for (Iterator i = specs.iterator(); i.hasNext();) {
            Object o = i.next();
            if ((o instanceof TargetSpec)
                && id.equals(((TargetSpec)o).getId())
                && version.equals(((TargetSpec)o).getVersion())) {
                return (TargetSpec)o;
            }
        }
        return null;
    }

    private static void addTargetSpecElem(ArrayList specs,
                                          String id,
                                          String version,
                                          String name,
                                          String value)
    {

        TargetSpec t_spec = findTargetSpec(specs, id, version);
        if (t_spec == null) {
            t_spec = createTargetSpec(id, version, new ArrayList());
            specs.add(t_spec);
        }
        ArrayList elems = t_spec.getTargetSpecElems();
        for (Iterator e = elems.iterator(); e.hasNext();) {
            Object o = e.next();
            if ((o instanceof TargetSpecElem)
                && name.equals(((TargetSpecElem)o).getName())
                && value.equals(((TargetSpecElem)o).getValue())) {
                return; // TargetSpecElem already exists
            }
        }

        elems.add(createTargetSpecElem(name, value));
        t_spec.setTargetSpecElems(elems);
    }

    /**
     * clears TestCase TargetSpec elements.
     */
    public void cleanTestcaseTargetSpecs() {
        testcaseTargetSpecs.clear();
    }

    /**
     * clears TestGroup TargetSpec elements.
     */
    public void cleanTestGroupTargetSpecs() {
        testGroupTargetSpecs.clear();
    }

    //                                    //
    // ------ AttrElem support code ----- //
    //                                    //

    /**
     * Storage for simple AttrElems of current test case.
     */
    protected ArrayList testcaseAttrs = new ArrayList();

    /**
     * Storage for simple AttrElems of current test group.
     */
    protected ArrayList testGroupAttrs = new ArrayList();

    /**
     * @return the AttrElems of current testcase
     */
    public ArrayList getTestcaseAttrElems() {
        return testcaseAttrs;
    }

    /**
     * @return the AttrElems of current test group
     */
    public ArrayList getTestGroupAttrElems() {
        return testGroupAttrs;
    }

    /**
     * Adds new AttrElem to current testcase. If the AttrElem
     * with given name already exists, its value is replaced.
     *
     * @param name AttrElem name
     * @param value AttrElem value
     */
    public void addTestcaseAttrElem(String name, String value) {
        addAttrElem(getTestcaseAttrElems(), name, value);
    }

    /**
     * Adds new AttrElem to current testcase. If the AttrElem
     * with given name already exists, its value is replaced.
     *
     * @param name AttrElem name
     * @param value AttrElem value
     */
    public void addTestGroupAttrElem(String name, String value) {
        addAttrElem(getTestGroupAttrElems(), name, value);
    }

    private static void addAttrElem(ArrayList attrs, String name, String value) {
        for (int i = 0; i < attrs.size(); i++) {
            Pair cur_p = (Pair)attrs.get(i);
            if (cur_p.getName().equals(name)) {
                cur_p.setValue(value);
                return;
            }
        }
        attrs.add(new Pair(name, value));
    }

    /**
     * Clears the list of AttrElems current test case has.
     */
    public void cleanTestcaseAttrElems() {
        getTestcaseAttrElems().clear();
    }

    /**
     * Clears the list of AttrElems current test group has.
     */
    public void cleanTestGroupAttrElems() {
        getTestGroupAttrElems().clear();
    }
}

class TargetSpecImpl implements TargetSpec {

     private String id = null;

     private String version = null;
     private ArrayList specelems = null;

     public TargetSpecImpl() {
     }

     public TargetSpecImpl(String id, String version) {
        this(id, version, null);
     }

     public TargetSpecImpl(String id, String version, ArrayList specelems) {
        this.id = id;
        this.version = version;
        this.specelems = specelems;
        if (specelems != null) {
            for (Iterator it = specelems.iterator(); it.hasNext();) {
                try {
                    TargetSpecElem elem = (TargetSpecElem)it.next();
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Passed ArrayList "
                            + " for id: " + id + ", version: " + version
                            + " is not list of TargetSpecElem instnaces");
                }
            }
        }
     }

     public String getVersion() {
        return version;
     }

     public String getId() {
        return id;
     }

     public void setId(String id) {
        this.id = id;
     }

     public void setVersion(String version) {
        this.version = version;
     }

     public ArrayList getTargetSpecElems() {
         return specelems;
     }

     public void setTargetSpecElems(ArrayList specelems) {
         this.specelems = specelems;
     }

}


class TargetSpecElemImpl implements TargetSpecElem {

     private String name = null;
     private String value = null;

     public TargetSpecElemImpl(String name, String value) {
        setName(name);
        setValue(value);
     }

     public String getValue() {
        return value;
     }

     public String getName() {
        return name;
     }

     public void setValue(String value) {
        if (value == null)
            throw new IllegalArgumentException(
                "value of TargetSpecElem should not be null");
        this.value = value;
     }

     public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException(
                "name of TargetSpecElem should not be null");
        this.name = name;
     }



}
