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

import java.io.*;
import java.util.Vector;
import java.lang.reflect.Method;
import javasoft.sqe.jckutils.lib.JmppScript;
import java.util.Hashtable;

/**
 * The class runs Jmpp in certain modes for FP templates
 * to obtain either JCK test or verifying program for that test.
 *
 * @author Dmitry I. Khukhro
 * @version @(#)FPMaker.java	1.20 04/03/09
 */

public class FPMaker implements JmppScript.JmppLibrary {

/**
 *   Contains Jmpp actual arguments or <code>null</code> when
 *   running under the JavaTest harness
 */
    public String[] argv = null;

    private static final String HOT_SUFF = "_hot";
    private static final boolean FIRST_TIME = true;
    private static final boolean ONCE_MORE = false;

    private JmppLibFP lib_test;
    private String workDir;
    private String testPackage;

	private JmppScript script;
	private PrintWriter log;
	private String outDir;
	private String inDir;
	private String wDir;
	private File inFile;
	private Vector srcVector;

/**
 * test description generator
 */
    public TDGenerator tdGenerator;

/**
 * Name of a main Java compiler class, used to compile the i-program.
 * Re-assigned it in subclasses to use different compiler.
 */
    protected String javacName = "com.sun.tools.javac.Main";

/**
 * A variable which contains a hashtable of options, is used
 * if JMPP library options are stored not in String array but in a Hashtable.
 */
    protected Hashtable argsHash = null;

    protected String templatePackage = null;

/**
 *   Start FP tests generation when Jmpp is run separately
 *   (not under the JavaTest harness
 *
 *   @param argv actual arguments passed to Jmpp
 */
    public static void main(String[] argv) throws IOException {

        FPMaker maker = new FPMaker();
        maker.log = new PrintWriter(System.err, true);
        maker.argv = argv;
        maker.transform(null);
    }


/**
 *   Run Jmpp three times: to generate test files containing tested
 *   expressions, files containing estimation calculation and
 *   files for constructing "hot" estimation sets.
 *   This method implements the one from the interface JmppLibrary.
 *
 *   @param name is ignored
 *   @return always <code>null</code>
 */
    public File transform(String name) throws IOException {
        Class c, c2;
        initTDGenerator();
        try {
            c = Class.forName("javasoft.sqe.jck.fp.emath.EValue");
            c2 = Class.forName("javasoft.sqe.jck.fp.emath.EFloat");
            synchronized(c) {
                lib_test = newJmppLibFP();
                lib_test.set_f_est_hot(false);
                lib_test.set_verifType(System.getProperty("verify"));
                lib_test.set_f_verify(lib_test.verifType != null);
                lib_test.set_f_estimate(false);
                lib_test.initValues();
                lib_test.doHTML = false;
                lib_test.makeTemplateHTML = false;

                setParams(lib_test);
                generate(lib_test, FIRST_TIME);

                JmppLibFP lib_est = newJmppLibFP();
                lib_est.set_f_est_hot(false);
                lib_est.set_verifType(System.getProperty("verify"));
                lib_est.set_f_verify(false);
                lib_est.set_f_estimate(true);
                lib_est.initValues();
                lib_est.doHTML = false;
                lib_est.makeTemplateHTML = false;

                lib_test.set_f_verify(false);
                lib_test.set_f_estimate(true);

                setParams(lib_est);

                generate(lib_est, ONCE_MORE);

                formSrcVector(lib_est);

                JmppLibFP lib_hot  = newJmppLibFP();
                lib_hot.set_f_est_hot(true);
                lib_hot.set_verifType(System.getProperty("verify"));
                lib_hot.set_f_verify(false);
                lib_hot.set_f_estimate(true);
                lib_hot.initValues();
                lib_hot.doHTML = false;
                lib_hot.makeTemplateHTML = false;

                lib_test.set_f_est_hot(true);
                lib_test.set_f_verify(false);
                lib_test.set_f_estimate(true);

                lib_est.set_f_est_hot(true);
                lib_est.set_f_verify(false);
                lib_est.set_f_estimate(true);

                setParams(lib_hot);
                workDir = lib_hot.getWorkJavaDir();
                lib_hot.setOutputDir(workDir);
                generate(lib_hot, ONCE_MORE);
                testPackage = lib_hot.packageName;

                genHotEstim( lib_test, lib_hot );
                generateHTML(lib_est);

                Method m = c.getDeclaredMethod("setExtFormatDefValues", new Class[0]);
                m.invoke(null, null);
                m = c2.getDeclaredMethod("setDefRoundModeValue", new Class[0]);
                m.invoke(null, null);
            }
        } catch (Exception e){
            makeError(e);
        }
        return null;
    }

    /**
     * Creates a <code>com.sun.jmpp.lib.JmppLibFP</code>
     * instance. Overridden in
     * <code>com.sun.tgxml.tools.jmppconv.processors.parser.FPMaker</code>
     * to return CRN-enabled JmppLibFP instance.
     * @return a <code>com.sun.jmpp.lib.JmppLibFP</code> instance
     */
    protected JmppLibFP newJmppLibFP() {
        return new JmppLibFP();
    }

    /**
     *   Form a vector that contains source vector for each generated test
     *
     *   @param est_lib FP library instance used for generating class that
     *                  is used for estimations generating
     */
    private void formSrcVector(JmppLibFP est_lib) {

        Vector estVect = est_lib.getAllSources();
        Vector dest, src;

        srcVector = lib_test.getAllSources();

/* ======== skip _est sources
		for (int i = 0; i < srcVector.size(); i++) {
            dest = (Vector)srcVector.elementAt(i);
            src = (Vector)estVect.elementAt(i);
            for (int j = src.size() - 1; j >= 0; j--) {
                dest.insertElementAt(src.elementAt(j), 0);
            }
// 1.2 version:	((Vector)srcVector.elementAt(i)).addAll(0, (Vector)estVect.elementAt(i));
		}
 ========*/
	}


/**
 *   Says if working under the JavaTest harness or not.
 */
    private boolean underJavaTest() {
        return argv == null;
    }


/**
 *   If Jmpp is run separately then invoke parameters parsing, otherwise
 *   pass parameters recieved from JavaTest to FP library for using
 *   while generating tests
 *
 *   @param lib JmppLibFP object.
 */
    private void setParams(JmppLibFP lib) {

	    if (underJavaTest()) {
		    lib.setLog(log);
		    lib.setOutputDir(outDir);
		    lib.setInDir(inDir);
		    lib.setWorkJavaDir(wDir);
		    lib.setInFile(inFile);
		} else {
		    if (argsHash != null){
                        lib.parseOptions(argsHash);
                    } else {
                        lib.parseOptions(argv);
                    }
                    if (templatePackage != null){
                        lib.templatePackage = templatePackage;
                    }
                }
            }


/**
 *   Generate tests from a template
 *
 *   @param lib JmppLibFP object.
 *   @param first_time flag: intermediate program was not generated and compiled.
 */
    private void generate(JmppLibFP lib, boolean first_time) {

	    if (!first_time) {
	        lib.runXLib();
	        return;
	    }
	    if (!underJavaTest()) {
	        lib.generate();
	        return;
	    }
        try {
            File srcGen = lib.transform(null);
	        if (script.compileSingle(srcGen) != 0) {
		        makeError("compilation of "+srcGen.getName()+" has failed.");
		    }
		    lib.runXLib();
		} catch(Throwable e) {
		    makeError(e);
		}
	}

     protected boolean makeTemplateHTML(){
         return true;
     }

/**
 *   Generate test html files for every generated from template test
 *
 *   @param lib JmppLibFP object.
 */
    private void generateHTML(JmppLibFP lib) {

        Vector test_files;
        Vector test_names;

        lib.fpSources = new Vector();
		for (int i = 0; i < srcVector.size(); i++) {
            test_files = (Vector)srcVector.elementAt(i);
            test_names = new Vector();
		    for (int j = 0; j < test_files.size(); j++) {
                test_names.addElement(((File)test_files.elementAt(j)).getName());
		    }
		    lib.fpSources.addElement(test_names);
        }

        lib.doHTML = true;
        lib.tdGenerator = tdGenerator;
        lib.makeTemplateHTML = makeTemplateHTML();
        lib.doSrc = false;
        lib.actTID = 0;

        lib.set_f_est_hot(false);
        lib.set_f_estimate(false);
        lib.set_f_verify(false);

        generate(lib, ONCE_MORE);
	}

/**
 * The method initializes tdGenerator
 * with the instance of HTMLTDGenerator.
 */
        protected void initTDGenerator(){
            tdGenerator = new HTMLTDGenerator();
        }

/*
 * This variable is a copy of FPFormat.CS_PROPERTY_NAME, they must be
 * the same.
 */
    static final String CS_PROPERTY_NAME = "-hardware.xFP_ExponentRanges";

/*
 * IMPORTANT: these String arrays correspond to the FPFormat.important
 * array of FPFormat objects. If the content of FPFormat.important is changed
 * this array MUST be synchronized.
 */
    public static final String[][] important = {
        {CS_PROPERTY_NAME, "-126:127:-1022:1023"}, // standard IEEE-754
        {CS_PROPERTY_NAME, "-16382:16383:-16382:16383"} // Intel - Joe Darcy

    /* the formats aren't admittable since final version of spec for jdk1.2fcs
        {CS_PROPERTY_NAME, "-16382:16383:-16382:16383"}, // Intel 80 bits
        {CS_PROPERTY_NAME, "-16382:16383:-16382:16383"} // Sparc, PPC, HP-700
     */
    };


/**
 *   Generate source file for the class containing hot format estimation
 *   sets.
 *
 *   @param tst_lib FP library instance used for test generating
 *   @param est_lib FP library instance used for generating class that
 *                  is used for "hot" estimations generating
 */
    private void genHotEstim(JmppLibFP tst_lib, JmppLibFP est_lib) {

        Vector destVect = tst_lib.getAllSources();
        Vector nameVect = est_lib.getAllTestNames();
        Vector testVect = est_lib.getAllSources();
        String mainClass, outputDir, fileName, shortName;
        File headFile;
        File genFile;
        PrintStream genOut;

        try {    // for each generated test
	        for (int i = 0; i < testVect.size(); i++) {
		        mainClass = (String)nameVect.elementAt(i);
		        testPackage = mainClass.substring(0, mainClass.lastIndexOf('.'));
		        mainClass = mainClass.substring(mainClass.lastIndexOf('.') + 1);
                headFile = mainFile((Vector)destVect.elementAt(i), mainClass);
                outputDir = headFile.getParent();

                headFile = mainFile((Vector)testVect.elementAt(i), mainClass + est_lib.TMP_SUFF);
		        fileName = headFile.getName();
		        shortName = fileName.substring(0, fileName.lastIndexOf('.'));
								      //HOT_SUFF
			    genFile = new File(outputDir, mainClass + est_lib.EST_SUFF + ".java");
			    genOut = new PrintStream(new FileOutputStream(genFile));
			    genOut.println(getSourceCopyrightNotice(est_lib, genFile.getName()));
			    genOut.println("");

			    genOut.println("package " + testPackage + ";\n");
//			    genOut.println("import java.util.Vector;\n");
			    genOut.println("import java.io.PrintStream;\n");
								      //HOT_SUFF
			    genOut.println("class " + mainClass + est_lib.EST_SUFF + " {\n");
			    genOut.println("    private static int index = -1;\n");
			    genOut.println("    static void run(String argv[], PrintStream out) {");
			    genOut.println("	index = -1;");
			    genOut.println("    }\n");
			    genOut.println("    static String estimationId() {");
			    genOut.println("	return \", case # \"+index;");
			    genOut.println("    }\n");
			    genOut.println("    static Object nextEstimation() {");
			    genOut.println("	return hotEstim[++index];");
			    genOut.println("    }\n");

//			    genOut.println("public static Vector hotEstim = new Vector();\n");
//			    genOut.println("static {");
//			    genOut.println("    Vector estVect;\n");

			    compileGen(headFile, shortName);

			    genOut.println("    private static Object[] hotEstim = {\n");
//			    for (int j = 0; j < important.length; j++) {
//			        genOut.println("estVect = new Vector();\n");
			        int j = 1;		// Intel - Joe Darcy
			        runGen(shortName, important[j], genOut);
//                    genOut.println("\nhotEstim.addElement(estVect);");
//			    }
			    genOut.println("\n    };");	// hotEstim
			    genOut.println("\n}");	// class *_hot
			    genOut.close();
		        ((Vector)srcVector.elementAt(i)).insertElementAt(genFile, 0);
		    }
	    } catch (java.lang.reflect.InvocationTargetException e) {
			makeError("FPMaker: test method run has thrown an exception:\n" +
			          e + "\nthis is an error in the test");
		} catch (Throwable e) {
			makeError(e);
		}
    }

    protected String getSourceCopyrightNotice(JmppLibFP est_lib){
        return JmppLibFP.wrapInJavaCommentTags(est_lib.getCopyrightInfo());
    }

    /**
     * Introduced to be overridden in com.sun.tgxml...FPMaker, which
     * (unlike this FPMaker class) uses the source name to construct a
     * copyright notice.
     *
     * @param est_lib JmppLibFP instance holding necessary context
     * @param src_name source name where the copyright notice is to
     *     be inserted
     * @return copyright notice block ready to be inserted "as is"
     *
     */
    protected String getSourceCopyrightNotice(JmppLibFP est_lib, String src_name){
        return JmppLibFP.wrapInJavaCommentTags(est_lib.getCopyrightInfo());
    }

/**
 *   Look up main test file in the vector of all test files.
 *
 *   @param fileVect vector of all test files.
 *   @param mainClass name of the test class that is to be run from outside.
 */
    private File mainFile(Vector fileVect, String mainClass) throws Throwable {

        String fileName, shortName, ext;

		for (int i = 0; i < fileVect.size(); i++) {
		     fileName = ((File)fileVect.elementAt(i)).getName();
		     ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		     shortName = fileName.substring(0, fileName.lastIndexOf('.'));
		     if ( shortName.equals(mainClass) && ext.equals("java") ) {
			     return (File)fileVect.elementAt(i);
		     }
		}
		throw new Exception("no main file for "+mainClass+" class.");
    }


/**
 *   Compile generator of the class containing calc. estimation data.
 *
 *   @param genFile the generator source file.
 *   @param genName name of the generator source file without extension.
 */
	private void compileGen(File genFile, String genName)
	                    throws Throwable {

        if (underJavaTest()) {
	        if (script.compileSingle(genFile) != 0) {
		        makeError("compilation of "+genName+" has failed.");
		    }
        } else {
            String ambientClassPath = System.getProperty("java.class.path");
            if (ambientClassPath == null) {
                ambientClassPath = "";
            }
            String[] argv = { "-classpath", ambientClassPath, "-d", workDir, genFile.getPath() };
            Object ret = null;
            try {
                String javacThreadSafety = System.getProperty(JAVAC_THREAD_SAFE_PROPERTY_NAME);
                if (javacThreadSafety == null || !javacThreadSafety.equals("true")){
                    ret = compileSuccessive(argv);
                } else {
                    ret = compileParallel(argv);
                }
            } catch (Throwable e) {
                    throw new Exception("compilation of "+genName+" has failed.");
            }
            if (ret instanceof Integer && ((Integer)ret).intValue() != 0 ||
                ret instanceof Boolean && !((Boolean)ret).booleanValue()){
                throw new Exception("compilation of "+genName+" has failed.");
            }
        }
    }

    public static final String JAVAC_THREAD_SAFE_PROPERTY_NAME = "javac.thread.safe";

	private Object compileSuccessive(String[] argv) throws Throwable {
	        Object ret;
                Class c = Class.forName(javacName);
                synchronized (c){
                    Object o = c.newInstance();
                    Class[] arg_types = new Class[] { argv.getClass() };
                    Object[] args = new Object[] { argv };
                    Method m = c.getMethod("compile", arg_types);
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
                Method m = c.getMethod("compile", arg_types);
                ret = m.invoke(o, args);
                return ret;
        }


/**
 *   Run generator of the class containing calc. estimation data for
 *   given format.
 *
 *   @param genName name of the generator source file without extension.
 *   @param fmt_par format which the estimation should be obtained for
 *   @param out stream for writing into output file.
 */
    private void runGen(String genName, String[] fmt_par, PrintStream out)
								throws Throwable {
	Class c          = Class.forName(testPackage+"."+genName);
        Method genRun    = c.getDeclaredMethod("run", new Class[] { fmt_par.getClass(),
									out.getClass() }  );
	genRun.invoke(null, new Object[]{fmt_par, out});
    }


/**
 *   In the case of an error print a message and exit FPMaker.
 *
 *   @param msg error message string.
 */
	private void makeError(String msg) {
		lib_test.generationError(log, "FPMaker: generation failed.\nReason: "+msg);
	}


/**
 *   In the case of an exception occurrence print the stack,
 *   print a message from the exception and exit FPMaker.
 *
 *   @param msg error message string.
 */
	private void makeError(Throwable exc) {
            exc.printStackTrace();
            lib_test.generationError(log, exc);
	}


/*
 * Methods needed to implement interface JmppLibrary
 * for running FP Jmpp tests under JavaTest.
 */

	public void setScript(JmppScript scr) {
	    script = scr;
	}
	public void setLog(PrintWriter pw) {
	    log = pw;
	}
	public void setOutputDir(String s) {
	    outDir = s;
	}
	public void setInDir(String s) {
	    inDir = s;
	}
	public void setWorkJavaDir(String s) {
	    wDir = s;
	}
	public void setInFile(File f) {
	    inFile = f;
	}

	public String getTemplatePackage() {
	    return lib_test.getTemplatePackage();
	}
	public Vector getAllKeywords() {
	    return lib_test.getAllKeywords();
	}
	public Vector getAllTestNames() {
	    return lib_test.getAllTestNames();
	}

	public void makeOut() {};
	public void closeOut() {};

	public Vector getAllSources() {
	    return srcVector;
	}

	public void passDataTo(Object o) {}
}
