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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.StringTokenizer;


/**
 * Class to be extend for test generation in section javasoft.sqe.tests.vm.
 *
 * @author Oleg V. Ulyankin, Artem A. Jilenko, Viatcheslav G. Rybalov
 * @version @(#)JmppLibVM.java	1.30 06/02/15
 */
public class JmppLibVM extends JmppLibTest {
    public final static String JVMS_STACK_MAP = "StackMap";
    public final static String JVMS_DEFAULT = "default";
    public final static String IS_STRICT_MODE_KEY = "jmpp.is_strict_mode";
    public final static int DEFAULT_MAJOR_CLASS_FILE_VERSION = 45;
    public final static int DEFAULT_MINOR_CLASS_FILE_VERSION = 3;
    public final static String CF_MAJOR_VERSION_PREFIX =
            "class.format.major.version";
    public final static String CF_MINOR_VERSION_PREFIX =
            "class.format.minor.version";
    public final static String FALLBACK_EXECUTE_OPTION = "-fallbacksupported";
    public final static String FALLBACK_EXECUTE_VALUE =
            " $platform.isFallbackSupported";

    // default value for the stackMapsUsed field
    public final static int NO_INVALID_MAPS = -1;

    // test tests valid stack maps
    public final static int VALID_MAPS = -2;

    // test tests invalid stack maps
    public final static int INVALID_MAPS = -3;

    // test tests valid and invalid stack maps
    public final static int MIXED_MAPS = -4;

    /**
     * Test value to check typechecker mode. Should be assigned to VALID_MAPS,
     * INVALID_MAPS or MIXED_MAPS in corresponding test if test checks
     * functionality concerned with VM typechecker mode.
     * @see #NO_INVALID_MAPS
     * @see #VALID_MAPS
     * @see #INVALID_MAPS
     * @see #MIXED_MAPS
     * @see #isStackMapAttributeTest()
     */
    protected int stackMapsUsed = NO_INVALID_MAPS;

    /**
     * For debuging poposes only. Set to false in the final release.
     */
    private final boolean debug = false;

    public JmppLibVM() {
        packageHead += "vm";
        setPackageName(packageHead);
    }

    /**
     * Check if test tests stack map attribute.
     * @see #stackMapsUsed
     */
    public boolean isStackMapAttributeTest(int attributeValue) {
        boolean ret = false;

        switch (attributeValue) {
        case VALID_MAPS:
            ret = true;
            break;

        case INVALID_MAPS:
            ret = true;
            break;

        case MIXED_MAPS:
            ret = true;
            break;

        default:
            ret = false;
            break;
        }
        return ret;
    }

    /**
     * Overriden JmppLibTest's method which initializes tdGenerator with the
     * instance of HTMLTDGenerator.
     */
    protected void initTDGenerator() {
        tdGenerator = (TDGenerator) new HTMLTDGenerator();
    }

    /**
     * The variable UseCommonWrapper should be set to "use" value if the common
     * wrapper are used for tests which are generated from jmpp templates.
     */
    public static final String UseCommonWrapper = "do not use"; // "use";

    /**
     * Macro containing current bifile name, resulted from appending suufix 'p'
     * or 'n' to the value of variable test, e.g. `codlen00101p` or
     * `codlen00101n`. May be used only within biTestBody() declaration.
     * @see #biTestBody()
     */
    public String bitest;

    /**
     * Macro containing value, that coincides with packageName besides each dot
     * replaced with slash character.
     * @see JmppLibTest#packageName
     */
    public String packageNameSlashed;

    /**
     * If true, than package name is consructed as to locate each test
     * generated into a separate package. Set it to false in your makeOut()
     * method definition, if you want all tests generated for given assertion
     * to be within the same package. Otherwise, each test class is located in
     * a separate subpackage whose last name component coincides with the test
     * name.
     * @see JmppLibTest#packageName
     */
    public boolean separatePackageForTest = true;

    /**
     * If true, than the corresponding exception\error will be cought in the
     * wrapper of the test. If this exception was cought the test is passed.
     */
    public boolean catchOutOfMemoryError = false;
    public boolean catchStackOverflowError = false;
    public boolean catchVirtualMachineError = false;

    /**
     * Method to evaluate test name variable (macro) `test`. Overrides method
     * in the class JmppLibTest.
     * @see JmppLibTest#testName()
     */
    public String testName() {
        test = className + String.valueOf(testInt);
        return test;
    }

    /**
     * Method to create sources for the one test number testNumber by means of
     * makeTest() method invocation.  Normally, you neither call nor override
     * this method.
     * @param testNumber number of the test to create.
     * @see #makeTest()
     */
    public void generateTest(int testNumber) {
        preMakeTest(testNumber);
        keywords = posRuntimeKeywords;
        setPackageName(packageHead);

        if (separatePackageForTest) {
            appendToPackageName("." + test);
        }
        multiTestVariantInfo = new Vector(17);
        multiTestVariantValue = new Vector(17);
        makeTest();
        postMakeTest(testNumber);
        generateTemplateTable();
    }

    /**
     * The main method to be implemented. Insert in this method all you want to
     * be generated for one test case. It is analogous in the way you use it to
     * makeTest() method of JmppLibTest, particulary you may set variables
     * title, description, keywords, etc. within this method definition. The
     * difference is that using this method you should not assume the output
     * file has already been specified nor statndard header for it has been
     * generated. Do it yourself in the way you need.
     */
    public void makeTest() { };

    /**
     * Method to create sources for the bi-test.  Normally, you call this
     * method from within your makeTest() method declaration.
     * @param fileExtension an extension of the bi-test files to create
     * @see #makeTest()
     */
    public void makeBiTest(String fileExtension) {
        bitest = test + "p";
        setOutput(true, bitest, fileExtension);
        beforeHead();
        makeHead();
        biTestBody();
        bitest = test + "n";
        setOutput(true, bitest, fileExtension);
        beforeHead();
        makeHead();
        biTestBody();
    }

    /**
     * Override this method if you call makeBiTest() method in your template
     * for generating negative tests with special wrapper (so called bi-tests).
     * You may use macros beginRight(), endRight(), beginWrong(), endWrong()
     * within its declaration.
     * @see #makeBiTest(java.lang.String)
     * @see #beginRight()
     * @see #endRight()
     * @see #beginWrong()
     * @see #endWrong()
     */
    public void biTestBody() {}

    /**
     * Call this macro from within biTestBody() method definition. Invocation
     * of this normally must be followed by corresponding invocation of
     * endRight() macro. The text enclosed inside these two invocation will be
     * commented out in negative source file generated. Never use bracket
     * comments in the text enclosed.
     * @see #biTestBody()
     * @see #endRight()
     */
    public void beginRight() {
        if (bitest == null) {
            generationError(log,
                    "beginRight() macro invocation should be"
                    + " within biTestBody() definition.");
        } else if (bitest.endsWith("p")) {
            L("// right:");
        } else if (bitest.endsWith("n")) {
            L("/* right:");
        } else {
            generationError(log,
                    "beginRight() macro invocation should be"
                    + " within biTestBody() definition.");
        }
    }

    /**
     * Call this macro from within biTestBody() method definition.
     * @see #biTestBody()
     * @see #beginRight()
     */
    public void endRight() {
        if (bitest == null) {
            generationError(log,
                    "endRight() macro invocation should be"
                    + " within biTestBody() definition.");
        } else if (bitest.endsWith("p")) {
            L("// end right");
        } else if (bitest.endsWith("n")) {
            L("end right */");
        } else {
            generationError(log,
                    "endRight() macro invocation should be"
                    + " within biTestBody() definition.");
        }
    }

    /**
     * Call this macro from within biTestBody() method definition. Invocation
     * of this normally must be followed by corresponding invocation of
     * endWrong() macro. The text enclosed inside these two invocation will be
     * commented out in positive source file generated. Never use bracket
     * comments in the text enclosed.
     * @see #biTestBody()
     * @see #endWrong()
     */
    public void beginWrong() {
        if (bitest == null) {
            generationError(log,
                    "beginWrong() macro invocation should be"
                    + " within biTestBody() definition.");
        } else if (bitest.endsWith("p")) {
            L("/* wrong:");
        } else if (bitest.endsWith("n")) {
            L("// wrong:");
        } else {
            generationError(log,
                    "beginWrong() macro invocation should be"
                    + " within biTestBody() definition.");
        }
    }

    /**
     * Call this macro from within biTestBody() method definition.
     * @see #biTestBody()
     * @see #beginWrong()
     */
    public void endWrong() {
        if (bitest == null) {
            generationError(log,
                    "endWrong() macro invocation should be"
                    + " within biTestBody() definition.");
        } else if (bitest.endsWith("p")) {
            L("end wrong */");
        } else if (bitest.endsWith("n")) {
            L("// end wrong");
        } else {
            generationError(log,
                    "endWrong() macro invocation should be"
                    + " within biTestBody() definition.");
        }
    }

    /**
     * Calls <code>makeNegLoadWrapper</code> with default
     * <code>LinkageError</code> exception.
     * @see #makeNegLoadWrapper(String expectedException)
     */
    public void makeNegLoadWrapper() {
        makeNegLoadWrapper("LinkageError");
    }

    /**
     * Method to create java source for standard negative linkage wrapper,
     * which loads bi-tests. Normally, you call this method from within
     * makeTest() method declaration, The wrapper first tries to load the
     * positive class (generated by biTestBody() method). If it fails, when the
     * entire test fails.  If succeeds, it tries to load the negative class. If
     * fails because of expected Exception, when the entire test succeeds.
     * Otherwise, (if loading of the wrong class is successful or the reason of
     * failure differs from those above) the test is considered to fail.
     * @param expectedException a class of the expected exception
     * @see #makeTest()
     * @see #biTestBody()
     * @see #makeNegLoadWrapperBody()
     * @see #makeNegLoadWrapperBodyForFallbackMode()
     */
    public void makeNegLoadWrapper(String expectedException) {
        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";
            executeArgs = "2 " + packageName + " ";
            executeArgs += test + "p pload noExc 0 ";
            executeArgs += test + "n nload " + expectedException + " 0";
        } else {
            if (debug) {
                System.out.println(
                        "...debug messages from makeNegLoadWrapperBody...");
                System.out.println("stackMapsUsed:" + stackMapsUsed);
            }

            if (isStackMapAttributeTest(stackMapsUsed)) {
                if (executeArgs == null) {
                    executeArgs = FALLBACK_EXECUTE_OPTION
                            + FALLBACK_EXECUTE_VALUE;
                } else {
                    executeArgs += FALLBACK_EXECUTE_OPTION
                            + FALLBACK_EXECUTE_VALUE;
                }
                makeNegLoadWrapperBodyForFallbackMode(expectedException);
            } else {
                makeNegLoadWrapperBody(expectedException);
            }
        }
    }

    public void makeNegLoadWrapperBody(String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for makeNegLoadWrapper()"
                    + " should be specified.");
        }
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        try {");
        L("            Class goodClass = Class.forName(\"" + packageName + "."
                + test + "p\");");
        L("            Object obj = goodClass.newInstance();");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load or instantiate good classfile " + test
                + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        try {");
        L("            Class badClass = Class.forName(\"" + packageName + "."
                + test + "n\");");
        L("            out.println(\"test " + test
                + " failed to reject bad classfile " + test + "n\");");
        L("            return 2/*STATUS_FAILED*/;");
        L("        } catch (" + expectedException + " e) {");
        L("            //expected ");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed with unexpected loading exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    public void makeNegLoadWrapperBodyForFallbackMode(String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for makeNegLoadWrapper()"
                    + " should be specified.");
        }
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        boolean isFallbackSupported = false;");
        L("");
        L("        for (int i = 0; i < argv.length; i++) {");
        L("            if ((i + 1) <= (argv.length - 1) &&");
        L("                   \"" + FALLBACK_EXECUTE_OPTION
                + "\".equalsIgnoreCase" + "(argv[i])) {");
        L("                isFallbackSupported = Boolean.parseBoolean(argv"
                + "[i + 1]);");
        L("            }");
        L("        }");
        L("        out.println(\"test run with parameter:"
                + " isFallbackSupported = \" + isFallbackSupported);");
        L("");
        L("        try {");
        L("            Class goodClass = Class.forName(\"" + packageName + "."
                + test + "p\");");
        L("            Object obj = goodClass.newInstance();");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load or instantiate good classfile " + test
                + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        try {");
        L("            Class badClass = Class.forName(\"" + packageName + "."
                + test + "n\");");
        L("            if (!isFallbackSupported) {");
        L("                out.println(\"test " + test
                + " failed to reject bad classfile " + test + "n\");");
        L("                return 2/*STATUS_FAILED*/;");
        L("            }");
        L("        } catch (" + expectedException + " e) {");
        L("            if (isFallbackSupported) {");
        L("                out.println(\"test " + test
                + " failed with unexpected " + expectedException + " : \" + e);");
        L("                return 2/*STATUS_FAILED*/;");
        L("            }");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed with unexpected loading exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Method to create java source for special negative linkage wrapper, which
     * loads bi-tests for optianal attributes. Normally, you call this method
     * from within makeTest() method declaration.  The wrapper first tries to
     * load and insatantiate the positive class (generated by biTestBody()
     * method). If it fails, when the entire test fails.  If succeeds, it tries
     * to load the negative class. If fails because of expected Exception or
     * passed, when the entire test succeeds.  Otherwise, (if loading of the
     * wrong class is successful or the reason of failure differs from those
     * above) the test is considered to fail.
     * @param expectedException a class of the expected exception
     * @see #makeTest()
     * @see #biTestBody()
     */
    public void makeSpecialNegLoadWrapper(String expectedException) {
        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";
            executeArgs = "2 " + packageName + " ";
            executeArgs += test + "p pload noExc 0 ";
            executeArgs += test + "n nload " + expectedException + " 0";
        } else {
            makeSpecialNegLoadWrapperBody(expectedException);
        }
    }

    public void makeSpecialNegLoadWrapperBody(String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for"
                            + " makeSpecialNegLoadWrapper() should be"
                    + " specified.");
        }
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        try {");
        L("            Class goodClass = Class.forName(\"" + packageName + "."
                + test + "p\");");
        L("            Object obj = goodClass.newInstance();");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load or instantiate good classfile " + test
                + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        try {");
        L("            Class badClass = Class.forName(\"" + packageName + "."
                + test + "n\");");
        L("        } catch (" + expectedException + " e) {");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed with unexpected loading exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Method adds into wrapper the processing of exception which was defined
     * by flags catchOutOfMemoryError, catchStackOverflowError and
     * catchVirtualMachineError.
     */
    protected void catchAddError() {
        if (catchOutOfMemoryError) {
            L("        } catch (OutOfMemoryError e) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + e);");
            L("            return 0/*STATUS_PASSED*/;");
        }

        if (catchStackOverflowError) {
            L("        } catch (StackOverflowError e) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + e);");
            L("            return 0/*STATUS_PASSED*/;");
        }

        if (catchVirtualMachineError) {
            L("        } catch (VirtualMachineError e) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + e);");
            L("            return 0/*STATUS_PASSED*/;");
        }
    }

    /**
     * Method adds into "invoke" part of wrapper the processing of exception
     * which was defined by flags catchOutOfMemoryError,
     * catchStackOverflowError and catchVirtualMachineError.
     */
    protected void checkAddError() {
        if (catchOutOfMemoryError) {
            L("        if (ee instanceof OutOfMemoryError) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + ee);");
            L("            return 0/*STATUS_PASSED*/;");
            L("        }");
        }

        if (catchStackOverflowError) {
            L("        if (ee instanceof StackOverflowError) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + ee);");
            L("            return 0/*STATUS_PASSED*/;");
            L("        }");
        }

        if (catchVirtualMachineError) {
            L("        if (ee instanceof VirtualMachineError) {");
            L("            out.println(\"Passed with runtime exception:"
                    + " \" + ee);");
            L("            return 0/*STATUS_PASSED*/;");
            L("        }");
        }
    }

    /**
     * Method which makes java text for loading and running positive execution
     * test class. Normally, you neither call nor override this method.
     * @param className a name of the tested class
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeRunPositive(String className) {
        L("    Class goodClass = null;");
        L("    try {");
        L("        goodClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Failed with loading exception: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Class[] argTypes = {String[].class, PrintStream.class};");
        L("    Method runMethod = null;");
        L("    try {");
        L("        runMethod = goodClass.getDeclaredMethod(\"run\","
                + " argTypes);");
        L("    } catch (SecurityException e) {");
        L("        out.println(\"SecurityException on looking for run()"
                + " method in the test class: \" + e);");
        L("        return 0/*STATUS_PASSED*/;");
        L("    } catch (NoSuchMethodException e) {");
        L("        out.println(\"run() not found in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on goodClass"
                + ".getDeclaredMethod() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Object[] args={argv, out};");
        L("    Integer retStatus = new Integer(2); // STATUS_FAILED");

       
        L("    try {");
        L("        retStatus = (Integer)runMethod.invoke(null, args);");
        L("    } catch (NullPointerException e) {");
        L("        out.println(\"run() must be static in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (IllegalAccessException e) {");
        L("        out.println(\"run() is not accessible in the test"
                + " class \" + " + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (InvocationTargetException e) {");
        L("        Throwable ee = e.getTargetException();");
        checkAddError();
        L("        out.println(\"Failed with runtime exception: \" + ee);");
        L("        return 2/*STATUS_FAILED*/;");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on runMethod"
                + ".invoke() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    return retStatus.intValue();");
    }

    /**
     * Method which makes java text for loading and running negative execution
     * test class. Normally, you neither call nor override this method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeRunNegative(String className, String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for makeRunNegative()"
                    + " should be specified.");
        }
        L("    Class badClass = null;");
        L("    try {");
        L("        badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        if (" + expectedException + ".isInstance(e)) {");
        L("            out.println(\"Passed with loading exception: \" + e);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("    }");
        L("");
        L("    Class[] argTypes = {String[].class, PrintStream.class};");
        L("    Method runMethod = null;");
        L("    try {");
        L("        runMethod = badClass.getDeclaredMethod(\"run\", argTypes);");
        L("    } catch (SecurityException e) {");
        L("        out.println(\"SecurityException on looking for run()"
                + " method in the test class: \" + e);");
        L("        return 0/*STATUS_PASSED*/;");
        L("    } catch (NoSuchMethodException e) {");
        L("        out.println(\"run() not found in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on badClass"
                + ".getDeclaredMethod() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Object[] args={argv, out};");
        L("    try {");
        L("        Object ignore = runMethod.invoke(null, args);");
        L("    } catch (NullPointerException e) {");
        L("        out.println(\"run() must be static in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (IllegalAccessException e) {");
        L("        out.println(\"run() is not accessible in the test"
                + " class \" + " + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (InvocationTargetException e) {");
        L("        Throwable ee = e.getTargetException();");
        checkAddError();
        L("        if (" + expectedException + ".isInstance(ee)) {");
        L("            out.println(\"Passed with runtime exception: \" + ee);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected runtime"
                + " exception: \" + ee);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on runMethod"
                + ".invoke() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    out.println(\"Failed to reject invalid class \" + " + className
                + ");");
        L("    return 2/*STATUS_FAILED*/;");
    }

    /**
     * Method which makes java text for loading and running negative execution
     * test class with fallback mode support. Normally, you neither call nor
     * override this method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeRunNegativeForFallbackMode(String className,
            String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for"
                            + " makeRunNegativeForFallbackMode()"
                    + " should be specified.");
        }
        L("    Class badClass = null;");
        L("    try {");
        L("        badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        if (" + expectedException + ".isInstance(e)) {");
        L("            out.println(\"Passed with loading exception: \" + e);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("    }");
        L("");
        L("    Class[] argTypes = {String[].class, PrintStream.class};");
        L("    Method runMethod = null;");
        L("    try {");
        L("        runMethod = badClass.getDeclaredMethod(\"run\", argTypes);");
        L("    } catch (SecurityException e) {");
        L("        out.println(\"SecurityException on looking for run()"
                + " method in the test class: \" + e);");
        L("        return 0/*STATUS_PASSED*/;");
        L("    } catch (NoSuchMethodException e) {");
        L("        out.println(\"run() not found in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on badClass"
                + ".getDeclaredMethod() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Object[] args={argv, out};");
        L("    boolean isFallbackSupported = false;");
        L("");
        L("    for (int i = 0; i < argv.length; i++) {");
        L("        if ((i + 1) <= (argv.length - 1) &&");
        L("                \"" + FALLBACK_EXECUTE_OPTION + "\".equalsIgnoreCase"
                + "(argv[i])) {");
        L("            isFallbackSupported = Boolean.parseBoolean(argv"
                + "[i + 1]);");
        L("        }");
        L("    }");
        L("    out.println(\"test run with parameter:"
                + " isFallbackSupported = \" + isFallbackSupported);");
        L("");
        L("    try {");
        L("        Object ignore = runMethod.invoke(null, args);");
        L("    } catch (NullPointerException e) {");
        L("        out.println(\"run() must be static in the test class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (IllegalAccessException e) {");
        L("        out.println(\"run() is not accessible in the test"
                + " class \" + " + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (InvocationTargetException e) {");
        L("        Throwable ee = e.getTargetException();");
        checkAddError();
        L("        if (" + expectedException
                + ".isInstance(ee) && isFallbackSupported == false) {");
        L("            out.println(\"Passed with runtime exception: \" + ee);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected runtime"
                + " exception: \" + ee);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on runMethod"
                + ".invoke() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    if (isFallbackSupported) {");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("    out.println(\"Failed to reject invalid class \" + " + className
                + ");");
        L("    return 2/*STATUS_FAILED*/;");
    }

    /**
     * Method which makes java text for loading and instantiation positive
     * execution test class. Normally, you neither call nor override this
     * method.
     * @param className a name of the tested class
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeInstantiatePositive(String className) {
        L("        Class goodClass = null;");
        L("        try {");
        L("            goodClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("        } catch (ThreadDeath e) {");
        L("            throw e;");
        L("        } catch (Throwable e) {");
        L("            out.println(\"Failed with loading exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        try {");
        L("            Object obj = goodClass.newInstance();");
        catchAddError();
        L("        } catch (ThreadDeath e) {");
        L("            throw e;");
        L("        } catch (Throwable e) {");
        L("            out.println(\"Failed with runtime exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
    }

    /**
     * Method which makes java text for loading and instantiation negative
     * execution test class. Normally, you neither call nor override this
     * method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeInstantiateNegative(String className,
            String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for"
                            + " makeInstantiateNegative() should be"
                    + " specified.");
        }
        L("        Class badClass = null;");
        L("        try {");
        L("            badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        L("            try {");
        L("                Object obj = badClass.newInstance();");
        catchAddError();
        L("            } catch (ThreadDeath e) {");
        L("                throw e;");
        L("            } catch (Throwable e) {");
        L("                if (" + expectedException + ".isInstance(e)) {");
        L("                    out.println(\"Passed with runtime"
                + " exception: \" + e);");
        L("                    return 0/*STATUS_PASSED*/;");
        L("                } else {");
        L("                    out.println(\"Failed with unexpected"
                + " runtime exception: \" + e);");
        L("                    return 2/*STATUS_FAILED*/;");
        L("                }");
        L("            }");
        catchAddError();
        L("        } catch (ThreadDeath e) {");
        L("            throw e;");
        L("        } catch (Throwable e) {");
        L("            if (" + expectedException + ".isInstance(e)) {");
        L("                out.println(\"Passed with loading exception:"
                + " \" + e);");
        L("                return 0/*STATUS_PASSED*/;");
        L("            } else {");
        L("                out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("                return 2/*STATUS_FAILED*/;");
        L("            }");
        L("        }");
        L("        out.println(\"Failed to reject invalid class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
    }

    /**
     * Method which makes java text for loading and instantiation negative
     * execution test class with fall back mode support. Normally, you neither
     * call nor override this method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeInstantiateNegativeForFallbackMode(String className,
            String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for"
                            + " makeInstantiateNegative() should be"
                    + " specified.");
        }
        L("        Class badClass = null;");
        L("        boolean isFallbackSupported = false;");
        L("");
        L("        for (int i = 0; i < argv.length; i++) {");
        L("            if ((i + 1) <= (argv.length - 1) &&");
        L("                    \"" + FALLBACK_EXECUTE_OPTION
                + "\".equalsIgnoreCase" + "(argv[i])) {");
        L("                isFallbackSupported = Boolean.parseBoolean(argv"
                + "[i + 1]);");
        L("            }");
        L("        }");
        L("        out.println(\"test run with parameter:"
                + " isFallbackSupported = \" + isFallbackSupported);");
        L("");
        L("        try {");
        L("            badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        L("            try {");
        L("                Object obj = badClass.newInstance();");
        catchAddError();
        L("            } catch (ThreadDeath e) {");
        L("                throw e;");
        L("            } catch (Throwable e) {");
        L("                if (" + expectedException
                + ".isInstance(e) && isFallbackSupported == false) {");
        L("                    out.println(\"Passed with runtime"
                + " exception: \" + e);");
        L("                    return 0/*STATUS_PASSED*/;");
        L("                } else {");
        L("                    out.println(\"Failed with unexpected"
                + " runtime exception: \" + e);");
        L("                    return 2/*STATUS_FAILED*/;");
        L("                }");
        L("            }");
        catchAddError();
        L("        } catch (ThreadDeath e) {");
        L("            throw e;");
        L("        } catch (Throwable e) {");
        L("            if (" + expectedException
                + ".isInstance(e) && isFallbackSupported == false) {");
        L("                out.println(\"Passed with loading exception:"
                + " \" + e);");
        L("                return 0/*STATUS_PASSED*/;");
        L("            } else {");
        L("                out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("                return 2/*STATUS_FAILED*/;");
        L("            }");
        L("        }");
        L("        if (isFallbackSupported) {");
        L("            return 0/*STATUS_PASSED*/;");
        L("        }");
        L("        out.println(\"Failed to reject invalid class \" + "
                + className + ");");
        L("        return 2/*STATUS_FAILED*/;");
    }

    /**
     * Method which makes java text for loading and linking positive
     * execution test class. Normally, you neither call nor override this
     * method.
     * @param className a name of the tested class
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeLoadPositive(String className) {
        L("    Class goodClass = null;");
        L("    try {");
        L("        goodClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Failed with loading exception: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    return 0/*STATUS_PASSED*/;");
    }

    /**
     * Method which makes java text for loading and linking negative execution
     * test class. Normally, you neither call nor override this method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeLoadNegative(String className,
            String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for makeLoadNegative()"
                    + " should be specified.");
        }
        L("    Class badClass = null;");
        L("    try {");
        L("        badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        if (" + expectedException + ".isInstance(e)) {");
        L("            out.println(\"Passed with loading exception: \" + e);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("    }");
        L("    out.println(\"Failed to reject invalid class \" + " + className
                + ");");
        L("    return 2/*STATUS_FAILED*/;");
    }

    /**
     * Method which makes java text for loading and linking negative execution
     * test class with fall back mode support. Normally, you neither call nor
     * override this method.
     * @param className a name of the tested class
     * @param expectedException a class of the expected exception
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    protected void makeLoadNegativeForFallbackMode(String className,
            String expectedException) {
        if (expectedException == null) {
            generationError(log,
                    "expected exception for"
                            + " makeLoadNegativeForFallbackMode()"
                    + " should be specified.");
        }
        L("    Class badClass = null;");
        L("    boolean isFallbackSupported = false;");
        L("");
        L("    for (int i = 0; i < argv.length; i++) {");
        L("        if ((i + 1) <= (argv.length - 1) &&");
        L("                \"" + FALLBACK_EXECUTE_OPTION + "\".equalsIgnoreCase"
                + "(argv[i])) {");
        L("            isFallbackSupported = Boolean.parseBoolean(argv"
                + "[i + 1]);");
        L("        }");
        L("    }");
        L("    out.println(\"test run with parameter:"
                + " isFallbackSupported = \" + isFallbackSupported);");
        L("");
        L("    try {");
        L("        badClass = Class.forName(\"" + packageName + ".\" + "
                + className + ");");
        catchAddError();
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        if (" + expectedException
                + ".isInstance(e) && isFallbackSupported == false) {");
        L("            out.println(\"Passed with loading exception: \" + e);");
        L("            return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("            out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("    }");
        L("");
        L("    if (isFallbackSupported) {");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("    out.println(\"Failed to reject invalid class \" + " + className
                + ");");
        L("    return 2/*STATUS_FAILED*/;");
    }

    /**
     * Constant for specifying that test variant should be loaded and executed
     * by invoking its run method.
     */
    public static final Integer INVOKE_RUN = new Integer(2);

    /**
     * Constant for specifying that test variant should be loaded and
     * instantiated.
     */
    public static final Integer INSTANTIATE = new Integer(1);

    /**
     * Constant for specifying that test variant should be just loaded.
     */
    public static final Integer LOAD = new Integer(0);

    /**
     * This macro holds an index of current variant.  May be used from within a
     * biTestBody method definition for indexing arrays, etc.
     */
    public int variantID;

    /**
     * This macro holds a current variant value.  May be used from within a
     * biTestBody method definition.
     */
    public String[] variant = { "" };

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param variantValues an arbitrary array of strings whose value may be
     * used from within your biTestBody() declaration as 'variant[i]' macro
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @see #makeMultiTest(java.lang.String)
     */
    public void positiveVariant(String[] variantValues, Integer wrapperKind) {
        Object[] oo = new Object[2];
        oo[0] = Boolean.TRUE;
        oo[1] = wrapperKind;
        multiTestVariantInfo.addElement(oo);
        multiTestVariantValue.addElement(variantValues);
    }

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param variantValue an arbitrary string whose value may be used from
     * within your biTestBody() declaration as 'variant[0]' macro
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @see #makeMultiTest(java.lang.String)
     */
    public void positiveVariant(String variantValue, Integer wrapperKind) {
        Object[] oo = new Object[2];
        oo[0] = Boolean.TRUE;
        oo[1] = wrapperKind;
        multiTestVariantInfo.addElement(oo);
        String[] ss = new String[1];
        ss[0] = variantValue;
        multiTestVariantValue.addElement(ss);
    }

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @see #makeMultiTest(java.lang.String)
     */
    public void positiveVariant(Integer wrapperKind) {
        Object[] oo = new Object[2];
        oo[0] = Boolean.TRUE;
        oo[1] = wrapperKind;
        multiTestVariantInfo.addElement(oo);
        String[] ss = { "" };
        multiTestVariantValue.addElement(ss);
    }

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param variantValues an arbitrary array of strings whose value may be
     * used from within your biTestBody() declaration as 'variant[i]' macro
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @param expectedException a class name of the expected exception
     * @see #makeMultiTest(java.lang.String)
     */
    public void negativeVariant(String[] variantValues, Integer wrapperKind,
            String expectedException) {
        Object[] oo = new Object[3];
        oo[0] = Boolean.FALSE;
        oo[1] = wrapperKind;
        oo[2] = expectedException;
        multiTestVariantInfo.addElement(oo);
        multiTestVariantValue.addElement(variantValues);
    }

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param variantValue an arbitrary string whose value may be used from
     * within your biTestBody() declaration as 'variant[0]' macro
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @param expectedException a class name of the expected exception
     * @see #makeMultiTest(java.lang.String)
     */
    public void negativeVariant(String variantValue, Integer wrapperKind,
            String expectedException) {
        Object[] oo = new Object[3];
        oo[0] = Boolean.FALSE;
        oo[1] = wrapperKind;
        oo[2] = expectedException;
        multiTestVariantInfo.addElement(oo);
        String[] ss = new String[1];
        ss[0] = variantValue;
        multiTestVariantValue.addElement(ss);
    }

    /**
     * Method to define kind (and finally, total number) of variant for the
     * multi-test.  Normally, you call this method from within your makeTest()
     * method declaration.
     * @param wrapperKind a type of wrapper which should be used for this
     * variant testing
     * @param expectedException a class name of the expected exception
     * @see #makeMultiTest(java.lang.String)
     */
    public void negativeVariant(Integer wrapperKind, String expectedException) {
        Object[] oo = new Object[3];
        oo[0] = Boolean.FALSE;
        oo[1] = wrapperKind;
        oo[2] = expectedException;
        multiTestVariantInfo.addElement(oo);
        String[] ss = { "" };
        multiTestVariantValue.addElement(ss);
    }

    /**
     * Method to create sources for the multi-test.  Normally, you call this
     * method from within your makeTest() method declaration. Note: if you use
     * this method you do not need to call makeMultiTestWrapper method, since
     * it will be called automatically.
     * @param fileExtension an extension of the multi-test files to create
     * @see #makeTest()
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    public void makeMultiTest(String fileExtension) {
        String suffix;
        Object[] info;

        if (multiTestVariantInfo.size() < 1) {
            generationError(log,
                    "before makeMultiTest() invocation all the"
                            + " required xxxVariant() invocations should"
                    + " be" + " placed.");
        }

        for (int i = 0; i < multiTestVariantInfo.size(); i++) {
            info = (Object[]) multiTestVariantInfo.elementAt(i);

            if (info[0].equals(Boolean.TRUE)) {
                suffix = "p";
            } else {
                suffix = "n";
            }
            bitest = test + i + suffix;
            setOutput(true, bitest, fileExtension);
            beforeHead();
            makeHead();
            variantID = i;
            variant = (String[]) multiTestVariantValue.elementAt(i);
            biTestBody();
        }

        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            setParametersToCommonWrapper();
        } else {
            makeMultiTestWrapper("LinkageError");
        }
    }

    /**
     * Method to create a one source multi-test.  Normally, you call this
     * method from within your makeTest() method declaration. Note: if you use
     * this method you do not need to call makeMultiTestWrapper method, since
     * it will be called automatically.
     * @param fileExtension an extension of the test file to create
     * @see #makeTest()
     * @see #makeMultiTestWrapper(java.lang.String)
     */
    public void makeMultiTestInOneSource(String fileExtension) {
        String suffix;
        Object[] info;

        if (multiTestVariantInfo.size() < 1) {
            generationError(log,
                    "before makeMultiTest() invocation all the"
                            + " required xxxVariant() invocations should"
                    + " be" + " placed.");
        }
        setOutput(true, test + "v", fileExtension);
        beforeHead();
        makeHead();

        if (fileExtension == "java" && packageName != null
                && !packageName.equals("")) {
            L("package " + packageName + ";");
            L("");
        } else if (fileExtension == "jasm" && packageNameSlashed != null
                && !packageNameSlashed.equals("")) {
            L("package " + packageNameSlashed + ";");
            L("");
        }

        for (int i = 0; i < multiTestVariantInfo.size(); i++) {
            info = (Object[]) multiTestVariantInfo.elementAt(i);

            if (info[0].equals(Boolean.TRUE)) {
                suffix = "p";
            } else {
                suffix = "n";
            }
            bitest = test + i + suffix;
            variantID = i;
            variant = (String[]) multiTestVariantValue.elementAt(i);
            biTestBody();
        }

        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            setParametersToCommonWrapper();
        } else {
            makeMultiTestWrapper("LinkageError");
        }
    }

    /**
     * Method to create java source for multi-test execution wrapper, which
     * loads and executes all multi-test classes. Normally, you call this
     * method from within makeTest() method declaration, The wrapper tries to
     * load test class(es) and invoke its method public static int run(String
     * argv[], PrintStream out).  If it fails because of any exception (except
     * expected one for a negative execution class), when the entire test
     * fails.
     * @param expectedException a class name of the default expected exception
     * @see #makeMultiTest(java.lang.String)
     */
    public void makeMultiTestWrapper(String expectedException) {
        Object[] info;
        boolean use_load_positive = false;
        boolean use_load_negative = false;
        boolean use_instan_positive = false;
        boolean use_instan_negative = false;
        boolean use_run_positive = false;
        boolean use_run_negative = false;
        boolean isFallbackSupported = false;

        if (expectedException == null || expectedException.equals("")) {
            expectedException = "LinkageError";
        }
        String expected_exception_for_variant = expectedException;

        if (debug) {
            System.out.println(
                    "...debug messages from makeMultiTestWrapper...");
            System.out.println("stackMapsUsed:" + stackMapsUsed);
        }

        if (isStackMapAttributeTest(stackMapsUsed)) {
            if (executeArgs == null) {
                executeArgs = FALLBACK_EXECUTE_OPTION + FALLBACK_EXECUTE_VALUE;
            } else {
                executeArgs += FALLBACK_EXECUTE_OPTION + FALLBACK_EXECUTE_VALUE;
            }
            isFallbackSupported = true;
        }

        for (int i = 0; i < multiTestVariantInfo.size(); i++) {
            info = (Object[]) multiTestVariantInfo.elementAt(i);

            if (info[1].equals(INVOKE_RUN)) {
                if (info[0].equals(Boolean.TRUE)) {
                    use_run_positive = true;
                } else {
                    use_run_negative = true;
                }
            } else if (info[1].equals(INSTANTIATE)) {
                if (info[0].equals(Boolean.TRUE)) {
                    use_instan_positive = true;
                } else {
                    use_instan_negative = true;
                }

            } else

            /* if (info[1].equals(LOAD)) */
            {
                if (info[0].equals(Boolean.TRUE)) {
                    use_load_positive = true;
                } else {
                    use_load_negative = true;
                }
            }
        }
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("");
        L("import java.io.PrintStream;");

        if (use_run_positive || use_run_negative) {
            L("import java.lang.reflect.*;");
        }
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");

        for (int i = 0; i < multiTestVariantInfo.size(); i++) {
            info = (Object[]) multiTestVariantInfo.elementAt(i);

            if (info[0].equals(Boolean.FALSE)) {
                expected_exception_for_variant = (String) info[2];

                if (expected_exception_for_variant == null
                        || expected_exception_for_variant.equals("")) {
                    expected_exception_for_variant = expectedException;
                }
            }

            if (info[1].equals(INVOKE_RUN)) {
                if (info[0].equals(Boolean.TRUE)) {
                    L("    if (runPositive(\"" + test + i
                            + "p\", argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                } else {
                    L("    if (runNegative(\"" + test + i + "n\", "
                            + expected_exception_for_variant
                            + ".class, argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                }
            } else if (info[1].equals(INSTANTIATE)) {
                if (info[0].equals(Boolean.TRUE)) {
                    L("    if (instantiatePositive(\"" + test + i
                            + "p\", argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                } else {
                    L("    if (instantiateNegative(\"" + test + i + "n\", "
                            + expected_exception_for_variant
                            + ".class, argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                }

            } else

            /* if (info[1].equals(LOAD)) */
            {
                if (info[0].equals(Boolean.TRUE)) {
                    L("    if (loadPositive(\"" + test + i
                            + "p\", argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                } else {
                    L("    if (loadNegative(\"" + test + i + "n\", "
                            + expected_exception_for_variant
                            + ".class, argv, out) != 0/*STATUS_PASSED*/)");
                    L("        return 2/*STATUS_FAILED*/;");
                }
            }
        }
        L("");
        L("    return 0/*STATUS_PASSED*/;");
        L("    }");

        if (use_run_positive) {
            L("");
            L("    public static int runPositive(String testName, String"
                    + " argv[], PrintStream out) {");
            L("");
            makeRunPositive("testName");
            L("    }");
        }

        if (use_run_negative) {
            L("");
            L("    public static int runNegative(String testName, Class"
                    + " expectedException, String argv[], PrintStream out) {");
            L("");

            if (isFallbackSupported) {
                makeRunNegativeForFallbackMode("testName", "expectedException");
            } else {
                makeRunNegative("testName", "expectedException");
            }
            L("    }");
        }

        if (use_instan_positive) {
            L("");
            L("    public static int instantiatePositive(String testName"
                    + ", String argv[], PrintStream out) {");
            L("");
            makeInstantiatePositive("testName");
            L("    }");
        }

        if (use_instan_negative) {
            L("");
            L("    public static int instantiateNegative(String testName"
                    +
                            ", Class expectedException, String argv[], PrintStream out)"
                    + " {");
            L("");

            if (isFallbackSupported) {
                makeInstantiateNegativeForFallbackMode("testName",
                        "expectedExceptio" + "n");
            } else {
                makeInstantiateNegative("testName", "expectedException");
            }
            L("    }");
        }

        if (use_load_positive) {
            L("");
            L("    public static int loadPositive(String testName,"
                    + " String argv[], PrintStream out) {");
            L("");
            makeLoadPositive("testName");
            L("    }");
        }

        if (use_load_negative) {
            L("");
            L("    public static int loadNegative(String testName, Class"
                    + " expectedException, String argv[], PrintStream out) {");
            L("");

            if (isFallbackSupported) {
                makeLoadNegativeForFallbackMode("testName",
                        "expectedException");
            } else {
                makeLoadNegative("testName", "expectedException");
            }
            L("    }");
        }
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Vector, accumulating info about multi-test variants after invocations of
     * xxxVariant() method.
     */
    protected Vector multiTestVariantInfo = new Vector(17);

    /**
     * Vector, accumulating arbitrary string values for multi-test variants
     * after invocations of xxxVariant() method.
     */
    protected Vector multiTestVariantValue = new Vector(17);

    /**
     * Normally, you neither call nor override this method, which is called to
     * to set new values for both packageName and packageNameSlashed variables.
     */
    protected void setPackageName(String name) {
        packageName = name;
        packageNameSlashed = name.replace('.', '/');
    }

    /**
     * Normally, you neither call nor override this method, which is called to
     * to add suffix to both packageName and packageNameSlashed variable
     * values.
     * @param suffix a string to be appended to value of packageName; value of
     * packageNameSlashed will be modified correspondingly.
     */
    protected void appendToPackageName(String suffix) {
        packageName += suffix;
        packageNameSlashed = packageName.replace('.', '/');
    }

    public static void main(String[] argv) {
        libMain(argv, new JmppLibVM());
    }

    /**
     * Method to create parameters to call a test when test use a common
     * wrapper.  Normally, you neither call nor override this method.
     * The function generate next parameters for test:
     * String numberOfTestCases;
     * String nameOfPackege;    // all test cases should be placed in one package
     * String nameOfExecutingClass;
     * String (Positive/Negative)*(Load/Instance/Run);    // mode of execution
     * String expectedException;  // value "noExc" for positive test
     * String numberOfArguments;
     * String execArguments;
     */
    public void setParametersToCommonWrapper() {
        Object[] info;
        String tmpExecuteArgs = null;
        int numberOfExecuteArgs = 0;
        String expected_exception_for_variant;
        executeClass = "javasoft.sqe.jck.lib.CommonWrapper";

        if (executeArgs != null) {
            tmpExecuteArgs = executeArgs;
            StringTokenizer parser = new StringTokenizer(executeArgs);
            numberOfExecuteArgs = parser.countTokens();
        }
        executeArgs = Integer.toString(multiTestVariantInfo.size()) + " "
                + packageName;

        for (int i = 0; i < multiTestVariantInfo.size(); i++) {
            info = (Object[]) multiTestVariantInfo.elementAt(i);

            if (info[0].equals(Boolean.TRUE)) {
                executeArgs += " " + test + i + "p";

                if (info[1].equals(INVOKE_RUN)) {
                    executeArgs += " prun noExc ";

                    if (numberOfExecuteArgs != 0) {
                        executeArgs += numberOfExecuteArgs + " "
                                + tmpExecuteArgs;
                    } else {
                        executeArgs += "0";
                    }
                } else if (info[1].equals(INSTANTIATE)) {
                    executeArgs += " pinst noExc 0";

                } else

                /* if (info[1].equals(LOAD)) */
                {
                    executeArgs += " pload noExc 0";
                }
            } else {
                executeArgs += " " + test + i + "n";
                expected_exception_for_variant = (String) info[2];

                if (info[1].equals(INVOKE_RUN)) {
                    executeArgs += " nrun " + expected_exception_for_variant
                            + " ";

                    if (numberOfExecuteArgs != 0) {
                        executeArgs += numberOfExecuteArgs + " "
                                + tmpExecuteArgs;
                    } else {
                        executeArgs += "0";
                    }
                } else if (info[1].equals(INSTANTIATE)) {
                    executeArgs += " ninst " + expected_exception_for_variant
                            + " 0";

                } else

                /* if (info[1].equals(LOAD)) */
                {
                    executeArgs += " nload " + expected_exception_for_variant
                            + " 0";
                }
            }
        }
    }

    /**
     * Replaces all specified character of a StringBuffer with the specified
     * String. The String must contain at least two characters.
     * The function is used by quoteForHTML(String).
     * @see #quoteForHTML(java.lang.String)
     */
    private static void replaceCharWithString(StringBuffer strbuff, char c,
            String subst) {
        int index = 0;
        int charcount = 0; // an additional size for the buffer
        int substlen = subst.length(); // note: substlen>1

        // count the additional size for the buffer
        for (; index < strbuff.length(); ++index) {
            if (strbuff.charAt(index) == c) {
                charcount += substlen - 1;

                // extend the buffer
            }
        }
        strbuff.setLength(strbuff.length() + charcount);

        // do substitute
        for (index = strbuff.length() - 1; charcount > 0 && index > 0;) {
            if (strbuff.charAt(index - charcount) == c) {
                strbuff.replace(index - substlen + 1, index + 1, subst);
                index -= substlen;
                charcount -= substlen - 1;
            } else {
                strbuff.setCharAt(index, strbuff.charAt(index - charcount));
                --index;
            }
        }
    }

    /**
     * Returns a new String resulting from replacing all occurrences of special
     * characters '<','>','&' and '"' in this String with "&lt;",
     * "&gt;","&amp;" and "quot;" respectively. It is needed to insert the
     * string into an html file.
     */
    private static String quoteForHTML(String str) {
        StringBuffer strbuff = new StringBuffer(str);
        replaceCharWithString(strbuff, '&', "&amp;"); // this line must be the first one
        replaceCharWithString(strbuff, '<', "&lt;");
        replaceCharWithString(strbuff, '>', "&gt;");
        replaceCharWithString(strbuff, '"', "&quot;");
        return strbuff.toString();
    }

    /**
     * If this flag is not set, functions getPseudoSourceComment and
     * getPseudoSourceDescription return empty strings. So if you want to build
     * all VM-sources without pseudo sources included you change the line
     * "public boolean needPseudoSource=true;" in JmppLibVM.java to
     * "public boolean needPseudoSource=false;" and vise versa.
     * @see #getPseudoSourceComment(java.lang.String)
     * @see #getPseudoSourceComment()
     * @see #getPseudoSourceDescription(java.lang.String)
     * @see #getPseudoSourceDescription()
     */
    public boolean needPseudoSource = true;

    /**
     * Contains pseudo source (Java source the test may look like). It is used
     * by both getPseudoSourceComment and getPseudoDescription to generate
     * their output.
     * @see #getPseudoSourceComment(java.lang.String)
     * @see #getPseudoSourceComment()
     * @see #getPseudoSourceDescription(java.lang.String)
     * @see #getPseudoSourceDescription()
     */
    public String pseudoSource;

    /**
     * Call this method to get a comment (string enclosed in '/*' and '* /')
     * containing Java source the test may look like. It is used to generate
     * source files (*.jasm or *.jcod). The method produces an empty string if
     * data memeber needPseudoSource is set to false, otherwise it returns "/*
     * "+header+"\n"+pseudoSource+"* /". Normaly you need to call
     * getPseudoSourceComment() which uses standard header: "Java source of the
     * test may look like this:". To add the pseudo source to the description
     * you should call getPseudoSourceDescription.
     * @see #pseudoSource
     * @see #needPseudoSource
     * @see #getPseudoSourceComment()
     * @see #getPseudoSourceDescription()
     */
    public String getPseudoSourceComment(String header) {
        return !needPseudoSource ? "" : "/* " + (header ==
                null ? "Java source of the test may look like this:"
                        : header) + "\n" + pseudoSource + "\n*/\n";
    }

    /**
     * It is equivalent to getPseudoSourceComment(""Java source of the test may
     * look like this:"");
     * @see #getPseudoSourceComment(java.lang.String)
     */
    public String getPseudoSourceComment() {
        return getPseudoSourceComment(
                "Java source of the test may look like this:");
    }

    /**
     * Call this method to get a html-formatted string containing Java source
     * the test may look like. It is used to generate description section for
     * the test. The method produces an empty string if data memeber
     * needPseudoSource is set to false. Normaly you need to call
     * getPseudoSourceDescription() which uses standard header: "Java source of
     * the test may look like this:". To add the pseudo source to the source
     * files (*.jasm or *.jcod) you should call getPseudoSourceComment.
     * @see #pseudoSource
     * @see #needPseudoSource
     * @see #getPseudoSourceComment()
     * @see #getPseudoSourceDescription()
     */
    public String getPseudoSourceDescription(String header) {
        return !needPseudoSource ? "" : "<br>" + header + "<pre>\n" +
                quoteForHTML(pseudoSource)
                + "\n</pre>";
    }

    /**
     * It is equivalent to getPseudoSourceDescription(""Java source of the test
     * may look like this:"");
     * @see #getPseudoSourceDescription(java.lang.String)
     */
    public String getPseudoSourceDescription() {
        return getPseudoSourceDescription(
                "Java source of the test may look like this:");
    }

    /******* JNI ************************************************************************** */

    /**
     * The value of variable generalLibrary is the name of general jni library.
     * Default value is "jckjni".
     */
    private static final String generalLibrary = "jckjni";

    /**
     * Method to create a set of include lines in C source.
     */
    public void jniCSourceStandardIncludes() {
        L("#include <stdlib.h>");
        L("#include <string.h>");
        L("#include <stdio.h>");
        L("#include \"jckjni.h\"");
        L("");
    }

    /**
     * Method to create a set of standart definitions for jni tests in C source.
     * @see #jniCSourceEndDefines()
     */
    public void jniCSourceBeginDefines() {
        L("#ifdef __cplusplus");
        L("extern \"C\" {");
        L("#endif");
        L("");
        L("#ifndef JNI_ENV_ARG");
        L("");
        L("#ifdef __cplusplus");
        L("#define JNI_ENV_ARG(x, y) y");
        L("#define JNI_ENV_PTR(x) x");
        L("#else");
        L("#define JNI_ENV_ARG(x,y) x, y");
        L("#define JNI_ENV_PTR(x) (*x)");
        L("#endif");
        L("");
        L("#endif");
        L("");
    }

    /**
     * Method to create definition in C source.
     * @see #jniCSourceBeginDefines()
     */
    public void jniCSourceEndDefines() {
        L("#ifdef __cplusplus");
        L("}");
        L("#endif");
    }

    /**
     * Method to create jni diagnostics C functions.
     * @param verbose verbose mode
     * @see #jniDiagnostics(boolean verbose)
     */
    public void jniCSourceDiagnostics(boolean verbose) {
        if (verbose) {
            L("static int " + (test) + "_statusStringLength = 0;");
            L("static char* " + (test) + "_statusString = (char*) 0;");
            L("");
            L("#define " + (test) + "_GRANULARITY    1024");
            L("#define " + (test) + "_RESTRICTION    10000");
            L("");
            L("void " + (test)
                    + "_AppendToStatusString(const char* errorString, ...) {");
            L("    va_list lst;");
            L("    char* " + (test) + "_tmpReallocString = (char*) 0;");
            L("    int oldLength = 0;");
            L("    if (" + (test) + "_statusString != 0)");
            L("        oldLength = strlen( " + (test) + "_statusString );");
            L("    if ((" + (test) + "_statusString == 0) ||");
            L("        (strlen(" + (test)
                    + "_statusString) + strlen( errorString ) + 100 < " + (test)
                    + "_statusStringLength))");
            L("    {");
            L("        " + (test)
                    + "_statusStringLength += strlen( errorString ) + " + (test)
                    + "_GRANULARITY;");
            L("        " + (test)
                    + "_tmpReallocString = (char*) realloc((void*) " + (test)
                    + "_statusString, ");
            L("            (size_t) " + (test) + "_statusStringLength);");
            L("        if ( " + (test) + "_tmpReallocString == 0 ) ");
            L("            return;");
            L("        " + (test) + "_statusString = " + (test)
                    + "_tmpReallocString;");
            L("        *( " + (test) + "_statusString + oldLength ) = 0;");
            L("    }");
            L("    va_start(lst, errorString);");
            L("    vsprintf(((char*) " + (test)
                    + "_statusString + oldLength), errorString, lst);");
            L("    va_end(lst);");
            L("}");
            L("");
            L("JNIEXPORT jint JNICALL Java_javasoft_sqe_tests_vm_jni_" + (asrt)
                    + "_" + (test) + "_" + (test) + "_GetStatus(");
            L("    JNIEnv *env, jobject mainObject, int charNumber) {");
            L("    if (" + (test) + "_statusString && (charNumber >= strlen("
                    + (test) + "_statusString)))");
            L("        return 0;");
            L("    if ((charNumber >= " + (test) + "_statusStringLength) || ");
            L("        (charNumber >= " + (test)
                    + "_RESTRICTION /* unpredictability restriction */)) {");
            L("        if (" + (test) + "_statusString != NULL)");
            L("            *" + (test) + "_statusString = (char) 0;");
            L("        return 0;");
            L("    }");
            L("    return (jint) *( charNumber * sizeof( *" + (test)
                    + "_statusString ) + " + (test) + "_statusString );");
            L("}");
            L("");
        }
    }

    /**
     * Method to create jni diagnostics java methods.
     * @param verbose verbose mode
     * @see #jniCSourceDiagnostics(boolean verbose)
     */
    public void jniDiagnostics(boolean verbose) {
        if (verbose) {
            L("    private native int GetStatus(int num);");
            L("");
            L("    private void CReport(PrintStream out) {");
            L("        String message = new String();");
            L("        int nextChar;");
            L("        int i=0;");
            L("        for (; (nextChar = GetStatus(i)) != 0; i++) {");
            L("            message += (char)nextChar;");
            L("        }");
            L("        out.println(\"C code error report: \" + message);");
            L("    }");
            L("");
        }
    }

    /**
     * Method to create methods for library loding.
     */
    public void jniLoadLibrary() {
        L("    static String loadLibraryStatus = \"\";");
        L("    static {");
        L("        if(!loadLib(\"" + screenString(test) + "\")) {");
        L("            loadLib(\"" + screenString(generalLibrary) + "\");");
        L("        }");
        L("    }");
        L("");
        L("    static boolean loadLib(String libName){");
        L("        try {");
        L("            System.loadLibrary(libName);");
        L("            loadLibraryStatus = null;");
        L("            return true;");
        L("        } catch (SecurityException e) {");
        L("            loadLibraryStatus  += \"loadLibrary(\\\"\" +"
                + " libName + \"\\\") throws: \" + e + \"\\n\";");
        L("        } catch (UnsatisfiedLinkError e) {");
        L("            loadLibraryStatus  +=  \"loadLibrary(\\\"\" +"
                + " libName + \"\\\") throws: \" + e + \"\\n\";");
        L("        }");
        L("        return false;");
        L("    }");
        L("");
    }

    /**
     * Method to create run method.
     */
    public void jniRunMethod() {
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        boolean loadLibraryAllowed = false;");
        L("");
        L("        /* Check number of arguments */");
        L("        if (argv.length != 2){");
        L("            out.println(\"Invalid arguments: only argument "
                + "-platform.nativeCodeSupported with value 'true' or 'false'"
                + " should be specified for JNI tests.\");");
        L("            return " + statusFailed + ";");
        L("        }");
        L("");
        L("        /* Parse command line arguments */;");
        L("        if (argv[0].equals(\"-platform.nativeCodeSupported\")) {");
        L("            if (argv[1].equalsIgnoreCase(\"true\")) {");
        L("                loadLibraryAllowed = true;");
        L("            }");
        L("        } else {");
        L("            out.println(\"invalid argument: \" + argv[0]);");
        L("            return " + statusFailed + ";");
        L("        }");
        L("");
        L("        if (loadLibraryStatus != null) {");
        L("            out.println(\"Library loading:\\n\" +"
                + " loadLibraryStatus);");
        L("            return (loadLibraryAllowed ? " + statusFailed + " : "
                + statusPassed + ");");
        L("        }");
        L("        if (!loadLibraryAllowed) {");
        L("            out.println(\"Library was incorrectly loaded, it"
                + " is not allowed\\n\");");
        L("            return " + statusFailed + ";");
        L("        }");
        L("");
        L("        return testChecks(out);");
        L("    }");
        L("");
    }

    /**
     * Method to create main method.
     */
    public void mainMethod() {
        L("    public static void main(String argv[]) {");
        L("        System.exit(run(argv, System.out) + " + statusTemp + ");");
        L("    }");
    }

    /************************************************************************************** */

    /*********** Deprecated *************************************************************** */

    /**
     * Deprecated variables for keywords. Please, use ones defined in JmppLibTest.
     * @deprecated
     */
    public static final String negativeKeywords =
            "execute negative simple virtualMachine";
    public static final String negativeSerialKeywords =
            "serial execute negative simple virtualMachine";
    public static final String positiveKeywords =
            "execute positive simple virtualMachine";
    public static final String positiveSerialKeywords =
            "serial execute positive simple virtualMachine";

    /**
     * Method to create sources for the negative execution test with wrapper
     * which. tries to load test class and execute its run() method.
     * Normally, you call this method from within your makeTest() method
     * declaration. The wrapper will be created automatically. - Deprecated
     * @param fileExtension an extension of the test class file
     * @see #makeTest()
     * @deprecated
     */
    public void makeNegRunTest(String fileExtension) {
        makeNegRunWrapper();
        setOutput(true, test + "n", fileExtension);
        beforeHead();
        makeHead();
    }

    /**
     * Method to create java source for standard positive execution wrapper,
     * which loads and executes a test class. Normally, you call this method
     * from within makeTest() method declaration,
     * The wrapper tries to load and instantiate the positive class.
     * If it fails because of any exception, when the entire test fails. - Deprecated
     * @see #makeTest()
     * @deprecated
     */
    public void makeNegRunWrapper() {
        String tmpExecuteArgs = null;
        int numberOfExecuteArgs = 0;

        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";

            if (executeArgs != null) {
                tmpExecuteArgs = executeArgs;
                StringTokenizer parser = new StringTokenizer(executeArgs);
                numberOfExecuteArgs = parser.countTokens();
                executeArgs = "1 " + packageName + " " + test
                        + "n nrun LinkageError " + numberOfExecuteArgs + " "
                        + tmpExecuteArgs;
            } else {
                executeArgs = "1 " + packageName + " " + test
                        + "n nrun LinkageError 0";
            }
        } else {
            makeNegRunWrapperBody();
        }
    }

    /**
     * @deprecated
     */
    public void makeNegRunWrapperBody() {
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("import java.lang.reflect.*;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("    Class badClass = null;");
        L("    try {");
        L("        badClass = Class.forName(\"" + packageName + "." + test
                + "n\");");
        L("    } catch (LinkageError e) {");
        L("        out.println(\"Passed with loading exception: \" + e);");
        L("        return 0/*STATUS_PASSED*/;");
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Failed with unexpected loading"
                + " exception: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Class[] argTypes = {String[].class, PrintStream.class};");
        L("    Method runMethod = null;");
        L("    try {");
        L("        runMethod = badClass.getDeclaredMethod(\"run\", argTypes);");
        L("    } catch (SecurityException e) {");
        L("        out.println(\"SecurityException on looking for run()"
                + " method in the test class: \" + e);");
        L("        return 0/*STATUS_PASSED*/;");
        L("    } catch (NoSuchMethodException e) {");
        L("        out.println(\"run() not found in the test class " + test
                + "n\");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on badClass"
                + ".getDeclaredMethod() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    Object[] args={argv, out};");
        L("    try {");
        L("        Object ignore = runMethod.invoke(null, args);");
        L("    } catch (NullPointerException e) {");
        L("        out.println(\"run() must be static in the test class " + test
                + "n\");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (IllegalAccessException e) {");
        L("        out.println(\"run() is not accessible in the test class "
                + test + "n\");");
        L("        return 2/*STATUS_FAILED*/;");
        L("    } catch (InvocationTargetException e) {");
        L("        Throwable ee = e.getTargetException();");
        L("        if (ee instanceof LinkageError) {");
        L("        out.println(\"Passed with runtime exception: \" + ee);");
        L("        return 0/*STATUS_PASSED*/;");
        L("        } else {");
        L("        out.println(\"Failed with unexpected runtime"
                + " exception: \" + ee);");
        L("        return 2/*STATUS_FAILED*/;");
        L("        }");
        L("    } catch (ThreadDeath e) {");
        L("        throw e;");
        L("    } catch (Throwable e) {");
        L("        out.println(\"Unexpected exception on runMethod"
                + ".invoke() invocation: \" + e);");
        L("        return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    out.println(\"Failed to reject invalid class " + test + "n\");");
        L("    return 2/*STATUS_FAILED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("    System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Method to create java source for standard negative execution wrapper,
     * which loads and executes bi-tests. Normally, you call this method from
     * within makeTest() method declaration,
     * The wrapper first tries to load and instantiate the positive class
     * (generated by biTestBody() method). If it fails, when the entire test
     * fails. If succeeds, it tries to load and instantiate the negative class.
     * If fails because of LinkageError, when the
     * entire test succeeds. Otherwise, (if both loading and instantiation of
     * the wrong class is successful or the reason of the failure differs from
     * those above) the test is considered to fail. - Deprecated
     * @see #makeTest()
     * @see #biTestBody()
     * @deprecated
     */
    public void makeNegExecWrapper() {
        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";
            executeArgs = "2 " + packageName + " ";
            executeArgs += test + "p pinst noExc 0 ";
            executeArgs += test + "n ninst LinkageError 0";
        } else {
            makeNegExecWrapperBody();
        }
    }

    /**
     * @deprecated
     */
    public void makeNegExecWrapperBody() {
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        Class goodClass;");
        L("        try {");
        L("               goodClass = Class.forName(\"" + packageName + "."
                + test + "p\");");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load good classfile " + test + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("        try {");
        L("            Object obj=goodClass.newInstance();");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to instantiate good classfile " + test
                + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        Class badClass;");
        L("        try {");
        L("            badClass = Class.forName(\"" + packageName + "." + test
                + "n\");");
        L("            try {");
        L("                Object obj=badClass.newInstance();");
        L("                out.println(\"test " + test
                + " failed to reject bad classfile " + test + "n\");");
        L("                return 2/*STATUS_FAILED*/;");
        L("            } catch (LinkageError e) {");
        L("                out.println(\"test " + test
                + " passed with runtime exception: \" + e);");
        L("            } catch (Throwable e) {");
        L("                out.println(\"test " + test
                + " failed with unexpected runtime exception: \" + e);");
        L("            }");
        L("        } catch (LinkageError e) {");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed with unexpected loading exception: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Method to create java source for standard positive execution wrapper,
     * which loads and executes a test class. Normally, you call this method
     * from within makeTest() method declaration,
     * The wrapper tries to load and instantiate the positive class.
     * If it fails because of any exception, when the entire test fails.
     * @see #makeTest()
     * @deprecated
     */
    public void makePosExecWrapper_() {
        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";
            executeArgs = "1 " + packageName + " " + test + "p pinst noExc 0";
        } else {
            makePosExecWrapperBody();
        }
    }

    /**
     * @deprecated
     */
    public void makePosExecWrapperBody() {
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        Class goodClass;");
        L("        try {");
        L("            goodClass = Class.forName(\"" + packageName + "." + test
                + "p\");");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load good classfile " + test + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("        try {");
        L("            Object obj=goodClass.newInstance();");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to instantiate good classfile " + test
                + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /**
     * Method to create java source for standard positive linkage wrapper,
     * which loads a test class. Normally, you call this method from within
     * makeTest() method declaration,
     * The wrapper tries to load the positive class. If it fails becuase of
     * any exception, when the entire test fails.
     * @see #makeTest()
     * @deprecated
     */
    public void makePosLoadWrapper_() {
        if (UseCommonWrapper.equalsIgnoreCase("use")) {
            executeClass = "javasoft.sqe.jck.lib.CommonWrapper";
            executeArgs = "1 " + packageName + " " + test + "p pload noExc 0";
        } else {
            makePosLoadWrapperBody();
        }
    }

    /**
     * @deprecated
     */
    public void makePosLoadWrapperBody() {
        setOutput(true, test, "java");
        makeHead();
        L("package " + packageName + ";");
        L("import java.io.PrintStream;");
        L("");
        L("public class " + test + " {");
        L("");
        L("    public static int run(String argv[], PrintStream out) {");
        L("");
        L("        try {");
        L("            Class goodClass = Class.forName(\"" + packageName + "."
                + test + "p\");");
        L("        } catch (Throwable e) {");
        L("            out.println(\"test " + test
                + " failed to load good classfile " + test + "p: \" + e);");
        L("            return 2/*STATUS_FAILED*/;");
        L("        }");
        L("");
        L("        return 0/*STATUS_PASSED*/;");
        L("    }");
        L("");
        L("    public static void main(String args[]) {");
        L("        System.exit(run(args, System.out) + 95/*STATUS_TEMP*/);");
        L("    }");
        L("");
        L("}");
    }

    /************************************************************************************** */
    private HashMap templateAttributes = new HashMap();

    /**
     * Calculates a class file format version which supports all attributes
     * added through the {@link #addTemplateAttribute(java.lang.String)}
     * and {@link #addTemplateAttributes(java.lang.String[])} methods and
     * returns the major version number. The version for attribute support
     * can be defined by build properties using concationation of the
     * {@link #CF_MAJOR_VERSION_PREFIX} and "." followed by the
     * attribute name.
     * @return the major number of the calculated version.
     */
    public int getMajorClassFileVersion() {
        return calculateCurClassFileVersion() [0];
    }

    /**
     * Calculates a class file format version which supports all attributes
     * added through the {@link #addTemplateAttribute(java.lang.String)}
     * and {@link #addTemplateAttributes(java.lang.String[])} methods and
     * returns the minor version number. The version for attribute support
     * can be defined by build properties using concationation of the
     * {@link #CF_MINOR_VERSION_PREFIX} and "." followed by the
     * attribute name.
     * @return the minor number of the calculated version.
     */
    public int getMinorClassFileVersion() {
        return calculateCurClassFileVersion() [1];
    }

    /**
     * adds multiply logical attributes. The method is equals to
     * {@link #addTemplateAttribute(java.lang.String)} for each array element.
     */
    public void addTemplateAttributes(String[] attributes) {
        for (int i = 0; i < attributes.length;
                addTemplateAttribute(attributes[i++]));
    }

    /**
     * adds single logical attribute for the current test case. This
     * method sets <code>Boolean.TRUE</code> as value of the given
     * attribute name.
     * @param attribute a name of the attribute.
     * @throws RuntimeException if build properties do not contain
     * class format version definition and contain property
     * {@link #IS_STRICT_MODE_KEY} set to true.
     */
    public void addTemplateAttribute(String attribute) {
        if (isStrict()) {
            getClassFileVersion(attribute, true);
        }
        templateAttributes.put(attribute, Boolean.TRUE);
    }

    /**
     * clears all template attributes. This method is automatically
     * invoked after each test case completion.
     */
    public void clearTemplateAttributes() {
        templateAttributes.clear();
    }

    /**
     * returns true if {@link #IS_STRICT_MODE_KEY} is set to true in the build properties.
     * and false otherwise.
     */
    private boolean isStrict() {
        BuildPropertiesProvider provider = getBuildPropertiesProvider();
        return ((provider != null)
                && (provider.getBuildProperty(IS_STRICT_MODE_KEY,
                "false").equals("true")));
    }

    private static int[] maxVersion(int[] first, int[] second) {
        return (first[0] < second[0] || // compare major part first
        (first[0] == second[0])
                && first[1] < second[1] // compare minor part next
        ? second : first);
    }

    /**
     * calculates the version for the set attributes.
     * @return two element array. The first element is major
     * version. The second element is minor version.
     */
    protected int[] calculateCurClassFileVersion() {
        if (this.templateAttributes.size() == 0) {
            this.templateAttributes.put(JVMS_DEFAULT, Boolean.TRUE);
        }
        int retVal[] = { -1, -1 };
        boolean isStrict = isStrict();

        for (Iterator e = templateAttributes.keySet().iterator();
                e.hasNext();) {
            String feature = (String) e.next();

            if (Boolean.TRUE.equals(this.templateAttributes.get(feature))) {
                retVal = maxVersion(retVal, getClassFileVersion(feature,
                        (!feature.equals(JVMS_DEFAULT) && isStrict)));
            }
        }
        return retVal;
    }

    /**
     * calculates version for the given attribute name.
     * @param attribute name of the attribute
     * @param isStrict flags that RuntimeException should be thrown if
     * the build properties do not contain class file format version
     * for the given attribute.
     */
    protected int[] getClassFileVersion(String attribute, boolean isStrict) {
        BuildPropertiesProvider provider = getBuildPropertiesProvider();

        if (provider == null) {
            throw new RuntimeException("The BuildPropertiesProvider is unset.");
        }
        return new int[] {
            getInt(provider, CF_MAJOR_VERSION_PREFIX + "." + attribute,
                    DEFAULT_MAJOR_CLASS_FILE_VERSION, isStrict),
                    getInt(provider, CF_MINOR_VERSION_PREFIX + "." + attribute,
                    DEFAULT_MINOR_CLASS_FILE_VERSION, isStrict) };
    }

    private int getInt(BuildPropertiesProvider provider, String key,
            int defaultValue, boolean isStrict)
            throws IllegalArgumentException {
        String version = null;

        if (provider == null) {
            if (isStrict) {
                throw new RuntimeException(
                        "The BuildPropertiesProvider is unset.");
            }
        } else {
            version = provider.getBuildProperty(key, null);
        }

        if (isStrict && (version == null)) {
            throw new IllegalArgumentException("The build properties file does not "
                    + "contain value for " + key);
        }

        try {
            return (version == null) ? defaultValue : Integer.parseInt(version);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can not parse version: "
                    + version);
        }
    }

    public void postMakeTest(int testNumber) {
        clearTemplateAttributes();
        super.postMakeTest(testNumber);
    }

    /**
     * Returns stack map attribute name.
     * @return stack map attribute name.
     */
    public String stackMapAttrName() {
        String result;
        int version = calculateCurClassFileVersion() [0];

        if (version >= 50) {
            result = "StackMapTable";
        } else { //version < 50
            result = "StackMap";
        }
        return result;
    }

    /**
     * Returns stack map full frame type value.
     * Since J2SE 6.0 (classfile version 50) the stack map
     * full frame starting on a byte 255.
     * @return stack map full frame type value.
     */
    public String stackMapFullFrame() {
        String result;
        int version = calculateCurClassFileVersion() [0];

        if (version >= 50) {
            result = "255b, ";
        } else { //version < 50
            result = "";
        }
        return result;
    }
}

