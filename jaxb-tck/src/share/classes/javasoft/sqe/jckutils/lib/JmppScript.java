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
 *
 */
package javasoft.sqe.jckutils.lib;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Vector;
import java.util.Enumeration;
import java.util.StringTokenizer;
import com.sun.javatest.Script;
import com.sun.javatest.Status;
import com.sun.javatest.Test;
import com.sun.javatest.TestDescription;
import com.sun.javatest.TestEnvironment;
import com.sun.javatest.TestResult;

/**
 * A TestScript to execute a Jmpp tests, generated from Jmpp template. 
 * This code is written on the basis 
 * of TestGenScript's source code written by John R. Rose.
 *
 * @author Oleg V. Uliankin
 * @version 1.27 01/03/29
 */
 
public class JmppScript extends Script
{
    protected String compileCommand="testCompile";
    protected String errHead="JmppScript: ";
    protected String jmppIntermediateDir="tmp.jmpp";
    protected String jmppIntermediateSrcDir="src";
    protected boolean executeNegatives;
    protected boolean generate;
    protected boolean compile;
    protected boolean execute;
    protected boolean individually;
    protected boolean checkErrors = false;
    protected JmppLibrary jt = null;
    protected JmppLibrary tsrc;
    public PrintWriter tsrcLog = null;
    
    protected Vector failures;
    protected String outDir=null;     // JCK space to generate the tests to
    protected String failureReason;
    protected String failureMessage;
    protected PrintWriter failureLog;
    protected String wrkDir=null;

	
    /**
     * Generate several variations of a given test.
     * Each variation may independently be a positive or negative test.
     * If positive, it is compiled and/or executed and must succeed.
     * If negative, it is compiled, and if executed must fail,
     * else the compilation must fail, depending on `-executeNegative'.
     * <p>
     * Exactly one of the source files must be an input for Jmpp.
     * Any other files are taken to be ordinary Java sources,
     * and are compiled (and must be compilable).
     * <p>
     * The flag `-compile' determines whether a negative test
     * must fail to compile (in which case execution is not attempted).
     * It applies equally to all variations.
     * <p>
     * The flag `-execute' determines whether execution is attempted.
     * If this flag is not set, compilation is done
     * <p>
     * The flag `-generate' specifies that test variants will be
     * generated, but nothing else will be be done unless asked for.
     * If none of `-compile', `-execute', or generate are specified,
     * all three are assumed.
     * <p>
     * The flag `-individually' requests that no batching be done,
     * and that each test be executed immediately after it is compiled.
     * (This may be necessary if several variants define the same class
     * in different ways.)
     * If this flag is not set, all positive compilations are done
     * in a batch.  (Negative compilations are always separate.)
     * <p>
     * The flag `-executeNegatives' determines whether a negative test
     * must also compile successfully.
     * Normally, positive tests should be designed so as to validate
     * themselves by execution.  With `-executeNegatives' negative
     * tests are also executed, and must fail.  (In this case,
     * they may not fail to compile.)
     * This flag applies equally to all generated test variants.
     * <p>
     * Other options that are accepted are `-v0' `-v1' and `-v2' that vary the
     * amount of tracing information that is generated.
     *
     * @see javasoft.sqe.javatest.Script#compileTogether
     * @see javasoft.sqe.javatest.Script#compileIndividually
     * @see javasoft.sqe.javatest.Script#execute
     * 
     */
     
     public void setWorkDirectory(String s) {
     	// directory, where intermediate Java program is put
     	wrkDir=s;
     }
     
    public Status run(String[] args, TestDescription td, TestEnvironment env) {

	for (int i = 0; i < args.length; i++) {
	    String arg = args[i];
	    if (arg.equals("-generate"))
		generate = true;
	    else if (arg.equals("-precompile"))
		compile = true;
	    else if (arg.equals("-compile"))
		compile = true;
	    else if (arg.equals("-execute"))
		execute = true;
	    else if (arg.equals("-executeNegatives"))
		executeNegatives = true;
	    else if (arg.equals("-compiler"))
		compile = execute = true;
	    else if (arg.equals("-runtime"))
		compile = execute = true;
	    else if (arg.equals("-developer"))
		compile = execute = true;
	    else if (arg.equals("-certify"))
		compile = execute = true;
	    else if (arg.equals("-individually"))
		individually = true;
	    else if (arg.equals("-checkErrors"))
		checkErrors = true;
	    else if (arg.equals("-d")) {
	    	if ((i+1)>args.length) {
	    		if (arg.length()>2)
	    			outDir=arg.substring(2);
	    	}
	    	else
			outDir=args[i+1];
		if (outDir!=null && !outDir.endsWith(File.separator))
			outDir+=File.separator;
	    }
	    else {
	    	if (i>0 && !args[i-1].equals("-d"))
			return Status.failed("bad arg for script: `" + arg + "'");
	    }
	}

	if (!(generate || compile || execute)) {
	    // by default, do both phases
	    generate = compile = execute = true;
	}

	failureLog = null;
	failureReason = null;
	failureMessage = null;
	failures = null;

	String tClassDir;
	try {
	    tClassDir = env.lookup("testClassDir")[0];
	    setWorkDirectory(tClassDir.substring(0,tClassDir.lastIndexOf(File.separatorChar)));
	}
	catch (TestEnvironment.Fault e) {
	    return Status.failed(e.toString());
	}

	String relDir = td.getRootRelativeFile().getParent();
	if (relDir.startsWith("tests"+File.separator))
		relDir = relDir.substring(6);
	relDir=relDir.replace('/',File.separatorChar);
	relDir=relDir.replace('\\',File.separatorChar);
	if (relDir.endsWith(File.separator))
		relDir=relDir.substring(0,relDir.length()-1);
	relDir = relDir.substring(0,relDir.lastIndexOf(File.separatorChar));
	    Status cs;
	    
	    File[] sfiles = td.getSourceFiles();
	    Vector source = new Vector();
	    // all batch compilation work is collected in source

	    int nsources = 0;
	
	    try {
	    for (int i = 0; i < sfiles.length; i++)
		if (sfiles[i].getName().endsWith(".jmpp")) {
			BufferedReader br = new BufferedReader(new FileReader(sfiles[i]));
			String firstLine = br.readLine();
			if (firstLine==null)
				return Status.failed("Empty first line in the Jmpp source");
			br.close();
		
			int exCharIndex = firstLine.indexOf("!");
			if (exCharIndex==-1)
				return Status.failed("No executor class specified in the first line of the Jmpp source "+sfiles[i].getName());
		
			firstLine = firstLine.substring(exCharIndex+1);
			StringTokenizer st = new StringTokenizer(firstLine);
			if (!st.hasMoreTokens())
				return Status.failed("No executor class specified in the first line of the Jmpp source "+sfiles[i].getName());

			String classToken = st.nextToken();
			try {
				tsrc = (JmppLibrary) Class.forName("javasoft.sqe.jck_internal.jmpp."+classToken).newInstance();
			}
			catch (ClassNotFoundException e) {
				return Status.failed("Class javasoft.sqe.jck_internal.jmpp."+classToken+" not found. Add JCK/src classes to the CLASSPATH");
			}
			source.addElement(sfiles[i]);
			tsrc.setInFile(sfiles[i]);
			tsrc.setInDir(sfiles[i].getParent()+File.separator);
			nsources++;
		}
	    }
	    catch (Throwable e) {
		e.printStackTrace();
		return Status.failed("Exception occurred: "+e);
	    }
	    
	    if (nsources != 1)
		return Status.failed("there must be exactly one Jmpp source file");

	    //tsrcLog = new PrintWriter(log.openLog("Jmpp"), true);
	    TestResult testResult = getTestResult();
	    try {
		tsrcLog = testResult.getSection(testResult.getSectionCount()).createOutput("Jmpp");
	    }
	    catch (TestResult.ReloadFault f) {
		f.printStackTrace();
		return Status.failed("Fault occurred: " + f);
	    }

	    tsrc.setScript(this);
	    tsrc.setLog(tsrcLog);
	    tsrc.setWorkJavaDir(wrkDir+File.separatorChar+jmppIntermediateDir+File.separatorChar+jmppIntermediateSrcDir+File.separatorChar+relDir);

	    String dTmp = wrkDir+File.separatorChar+relDir;
	    if (outDir!=null)
		dTmp = outDir+"tests"+File.separatorChar+relDir;
	    else
		if (!compile & !execute) {
		    String s=td.getDir().getPath();
		    int k = s.lastIndexOf(File.separator);
		    if (k!=-1)
			s=s.substring(0,k);
		    dTmp = s; // one level up..
		}
	    if (dTmp.endsWith(File.separator))
		dTmp = dTmp.substring(0, dTmp.length()-1);
	    tsrc.setOutputDir(dTmp);


	// Convert Jmpp template to intermediate Java program:
	File intermediateJava = null;
	try {
		intermediateJava = tsrc.transform(null);
	} catch (Throwable e) {
		e.printStackTrace(tsrcLog);
		tsrcLog.close();
		return Status.failed(errHead+"exception caught: "+e);
	}

	// if intermediateJava==null then tsrc instance is responsible for providing
	// the allSources array - skip compilation and execution of the intermediate
	// Java program; else create instance of the intermediate Java program to produce
	// the tests (ie. allSources array)

	if (intermediateJava==null)
		jt = tsrc;
	else {
		Status xx=null;
		if ((xx = compileOne(compileCommand,intermediateJava)).getType() != Status.PASSED) {
			return Status.failed("Jmpp: compilation of intermediate Java program failed:").augment(xx);
		}
	
		String className=trimExtension(intermediateJava.getName());
		try {
			try {
	/*			We need to create new class loader here because if we do not create it,
				we will not be able to fix template and run it again. That fix would
				not have effect, because template class [old one] is already loaded
				in this JVM. Thus the line below is commented out.
		
				Can't use DirectoryClassLoader, because this new class loader will have to find
				template class in the work dir, and it will have to find JmppLibXX classes,
				because template extend those. And those ones are in another directory.
				But DirectoryClassLoader can't load classes from two different directories.
		
				Still, we can, but in this case we need to set sysclasspath to point to javatest
				classes:
	
	java -Xsysclasspath:${JAVA_HOME}/lib/classes.zip:<path>/javatest.jar -ms16m javasoft.sqe.javatest.tool.Main &
	
	*/
				ClassLoader loader = new com.sun.javatest.util.DirectoryClassLoader(new File(wrkDir+File.separatorChar+"classes"));
	
				jt = (JmppLibrary) loader.loadClass(tsrc.getTemplatePackage()+"."+className).newInstance();
			} catch (Throwable e) {
	
	/*			sysclasspath was not set, so let's at least find it with same class loader.
				this, however, means that changing templates will not have effect... ;(  */
	
				jt = (JmppLibrary) Class.forName(tsrc.getTemplatePackage()+"."+className).newInstance();
	
			}
		} catch (Throwable e) {
			tsrcLog.println(errHead+"exception caught: "+e);
			e.printStackTrace(tsrcLog);
			tsrcLog.close();
			return Status.failed(errHead+"could not find template class "+className);
		}
		tsrc.passDataTo(jt);
		jt.makeOut();	// here actual generation is
		jt.closeOut();	// ensure that entire out is closed
		jt.passDataTo(tsrc);
	}

	Vector variants[] = new Vector[jt.getAllSources().size()];
	if (jt instanceof JmppLibrary)
		(((JmppLibrary) jt).getAllSources()).copyInto(variants);


	    if (variants==null || variants.length == 0) {
		tsrcLog.println("Warning: input file produced no test variants.");
		tsrcLog.close();
		return Status.failed(errHead+"template produced no output.");
	    }
	    
	    int negatives=0;
	    for (int i = 0; i < variants.length; i++) 
	        if (isNegative(i))
                    negatives++;

	    tsrcLog.println();
	    tsrcLog.println((!compile & !execute ? "Only generating" :
			      compile & !execute ? "Only compiling" :
			     !compile &  execute ? "Only executing" :
			      compile &  execute ? "Compiling and executing"
			     : "") + " " +
			    variants.length + " test variants, including " +
			    negatives + " negative tests.");

	    tsrcLog.close();

	    if (!compile & !execute)
		return Status.passed("Generation of tests finished");

	    // Compile the non-jmpp inputs.
	    // (Note:  They may refer to individual variants!)
	    if (compile && source.size() > 0 && individually) {
	    	Vector v1[] = vtova(source);
	    	for (int t=0; t<v1.length; t++) {
			cs = compileIndividually(compileCommand,vtofa(v1[t]));
			if (cs.getType() != Status.PASSED) {
			    return Status.failed("Compilation failed unexpectedly");
			}
		}
		source.setSize(0);
	    }

	    //failureLog = new PrintWriter(log.openLog("Test Variant Failures"), true);
	    try {
		failureLog = testResult.getSection(testResult.getSectionCount()).createOutput("Test Variant Failures");
	    }
	    catch (TestResult.ReloadFault f) {
		f.printStackTrace();
		return Status.failed("Fault occurred: " + f);
	    }

	    failures = new Vector();

	    Vector execution = new Vector();
	    // If !individually, all execution is done after all compilation.

	boolean needToCheckErrors = false;
	    // Arrange to compile and/or execute each variant.
	    for (int i = 0; i < variants.length; i++) {
		Vector variant = variants[i];
		if (variant==null)
			return Status.failed("null variant was generated");
		boolean negative = isNegative(i);
		boolean expectCompile = (!negative || executeNegatives);

		if (!compile) {
		    // do we want to execute this one?
		    if (expectCompile)
			execution.addElement(new Integer(i));
		    continue;
		}

		File variantArray[] = new File[variant.size()];
		variant.copyInto(variantArray);
		
		boolean allJavaSrc = true; // assume true until proven otherwise
		for (int l = 0; l < variantArray.length && allJavaSrc; l++) {
			String srcPath = variantArray[l].getPath();
			allJavaSrc = srcPath.endsWith(".java");
		}

		if (isSerial(i) || !allJavaSrc)
			cs = compileIndividually(compileCommand,variantArray);
		else
			cs = compileTogether(compileCommand,variantArray);
			
		if ((cs.getType() == Status.PASSED) != expectCompile) {
		    failure(i,
			    expectCompile ? "Compilation failed unexpectedly"
					  : "Compilation succeeded unexpectedly");
//			failureLog.println("Variant #"+i+"; fail reason: "+cs.getReason());
		    continue;
		}
		else {
//			failureLog.println("Variant #"+i+"; pass reason: "+cs.getReason());
		    if (checkErrors && negative) 
		    	needToCheckErrors = true;
		}

		// Compilation succeeded or failed as expected.
		if (!expectCompile) {
		    continue;
		}

		// Do we want to execute this one?
		if (!individually) {
		    execution.addElement(new Integer(i));
		    continue;
		}

		// Execute it right away.
		if (execute)
		    executeOne(i);
	    }
	    
	    if (!execute) {
		execution.setSize(0);
	    }

	    // Execute each remaining variant.
	    for (Enumeration e = execution.elements(); e.hasMoreElements(); ) {
		int k = ((Integer) e.nextElement()).intValue();
		executeOne(k);
	    }

	    if (failures.size() == 0) {
		failureLog = null; // don't close it
		String what =  compile & !execute ? "compilation" :
			      !compile &  execute ? "execution" :
			       compile &  execute ? "compilation and execution"
			     : "";
		Status passStatus = Status.passed("All " + what +
				     " results were as expected");
		
		// Lines deleted by JJG, 9/27/99; Status.checkFile no longer supported	     
		//if (needToCheckErrors)
		//	return Status.checkFile(passStatus + "; checking file..");

		return passStatus;
	    }

	    failureLog.close();	// report the bad news now
	    return Status.failed(failureMessage);
    }

    /**
     * Report a failure in the ``Test Variant Failures'' log.
     * Also, add the variant name to the failures vector.
     * <p>
     * The Jmpp test as a whole fails if the failures vector
     * is non-empty after all test variants are compiled and/or executed.
     */
    protected void failure(int i, String reason) {
	String name = (String)jt.getAllTestNames().elementAt(i);
	name = name.substring(1 + name.lastIndexOf("."));

	String message;
	if (reason == null) {
	    message = "    " + name;
	} else {
	    message = name + ": " + reason;
	}
	failureLog.println(message);
	failures.addElement(new Integer(i));

	if (failures.size() == 1) {
	    failureMessage = message;
	    failureReason = reason;
	} else if (failureReason != null && failureReason.equals(reason)) {
	    failureMessage = failures.size() + " variants: " + reason;
	} else if (reason != null) {
	    failureMessage = reason + ", and other errors";
	    failureReason = null;
	}
    }

	boolean isNegative(int i) {
		return (((String)jt.getAllKeywords().elementAt(i)).indexOf("negative") >= 0);
	}
	
	boolean isSerial(int i) {
		return (((String)jt.getAllKeywords().elementAt(i)).indexOf("serial") >= 0);
	}

    /**
     * Execute one variant, which must already have been compiled.
     * Call failure(), if it fails.  Also return a status bit.
     */
    protected Status executeOne(int i) {
	String c = (String)jt.getAllTestNames().elementAt(i);
	Status s = execute("testExecute",c, td.getExecuteArgs());

	boolean negative = isNegative(i);
	if ((s.getType() != Status.FAILED) == negative) {
	    String message =
		negative ? "Execution succeeded unexpectedly"
			 : "Execution failed unexpectedly";
	    failure(i, message);
	    return Status.failed(message).augment(s);
	} else {
	    return Status.passed("Execution was as expected").augment(s);
	}
    }

    // Converter for compileTogether, etc.
    static private Vector[] vtova(Vector v) {
	Vector fa[] = new Vector[v.size()];
	v.copyInto(fa);
	return fa;
    }
    
    static private File[] vtofa(Vector v) {
	File fa[] = new File[v.size()];
	v.copyInto(fa);
	return fa;
    }
   
	public static String trimExtension(String s) {
		if (s.endsWith(".java") || s.endsWith(".jmpp"))
			return s.substring(0, s.length()-5);
		return s;
	}

    private boolean useWrapper;

	public int compileBatch(File[] sourceArray) {
		return tsrcStatus(compileTogether(compileCommand, sourceArray));
	}
	public int compileSingle(File singleSource) {
		return tsrcStatus(compileOne(compileCommand, singleSource));
	}
	public int compileOneByOne(File[] sourceArray) {
		return tsrcStatus(compileIndividually(compileCommand,sourceArray));
	}

	private int tsrcStatus(Status cs) {
		if (cs.getType() == Status.PASSED)
			return 0;
		if (tsrcLog!=null)
			tsrcLog.println(cs.getReason());
		return 1;
	}

/**
 * Interface needed for running Jmpp tests under JavaTest.
 * This class is necessary because JmppLibXX is placed into JCK workspace and
 * JavaTest doesn't know about any JCK at all, when it is built.
 */
    public interface JmppLibrary {
	public void setScript(JmppScript jmppScript);
	public void setLog(PrintWriter log);
	public void setOutputDir(String s);
	public void setInDir(String s);
	public void setWorkJavaDir(String s);
	public void setInFile(File f);
	public File transform(String name) throws java.io.IOException;
	public void makeOut();
	public void closeOut();
	public String getTemplatePackage();
	public Vector getAllKeywords();
	public Vector getAllTestNames();
	public Vector getAllSources();
	public void passDataTo(Object o);
    }
}
