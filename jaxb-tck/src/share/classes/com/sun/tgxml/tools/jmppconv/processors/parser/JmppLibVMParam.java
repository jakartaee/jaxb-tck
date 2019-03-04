/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import com.sun.jmpp.JmppException;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.util.CopyrightManager;

/**
 * Jmpp library supporting test variant generation, yet possessing
 * all of {@link com.sun.jmpp.lib.JmppLibVM} library functionality.
 *
 * @author Konstantin S. Bobrovsky
 */
public class JmppLibVMParam extends com.sun.jmpp.lib.JmppLibVM implements IRGenerator {

    /** @see IRTDParamGenerator#NM_DEFAULT_PRESERVE */
    public final static int NM_DEFAULT_PRESERVE = IRTDParamGenerator.NM_DEFAULT_PRESERVE;

    /** @see IRTDParamGenerator#NM_DEFAULT_APPEND */
    public final static int NM_DEFAULT_APPEND = IRTDParamGenerator.NM_DEFAULT_APPEND;

    /** @see IRTDParamGenerator#NM_FIXED */
    public final static int NM_FIXED = IRTDParamGenerator.NM_FIXED;

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Fields */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * An instance of {@link IRTDParamGenerator} class
     * to which the most of the variant-related work is
     * delegated
     */
    private IRTDParamGenerator irTdGen;

    /**
     * A flag for {@link #setProperties(java.util.Hashtable)} method
     */
    private boolean argsSet = false;

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* User interface methods */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * Constructor. Initializes the variant generator delegate field
     * ({@link IRTDParamGenerator} class instance).
     */
    public JmppLibVMParam() {
        irTdGen = new IRTDParamGenerator(this);
    }

    /**
     * Adds a new template parameter on which current template depends.
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id template parameter ID string (a short name,
     *     of the class implementing this parameter, actually)
     * @throws JmppException (unchecked) if specified parameter is
     *     unknown or already added
     */
    public void addDependence(String param_id) throws JmppException {
        irTdGen.addDependence(param_id);
    }

    /**
     * Adds a new template parameter on which current template depends.
     * Use the string returned by parameter's getName() method to refer
     * to this parameter within template.
     * Implementation simply redirects this call to the delegate.
     *
     * @param param template parameter instance
     * @throws JmppException (unchecked) if specified parameter is unknown or
     *     already added
     */
    public void addDependence(TemplateParameter param) throws JmppException {
        irTdGen.addDependence(param);
    }

    /**
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id parameter ID
     * @return a parameter with given ID. If such parameter has already been
     *     added to the list of dependences, then returns this parameter.
     *     Otherwise treats the ID as short parameter class name and tries to
     *     load and instantiate the class. <code>null</code> is returned if
     *     all the above attempts failed.
     */
    public TemplateParameter getParameter(String param_id) {
        try {
            return irTdGen.getParameter(param_id);
        } catch (JmppException je) {
            return null;
        }
    }

    /**
     * Implementation simply redirects this call to the delegate.
     *
     * @return <code>true</code> if current values of all dependant parameters
     *    satisfy the conditions imposed by the stack of <code>begin...</code>
     *    conditional region markers at the place of invocation. Otherwise returns
     *    <code>false</code>.
     */
    public boolean paramConditionsSatisfied() {
        return irTdGen.paramConditionsSatisfied();
    }

    /**
     * Marks the code region from the place of invocation till corresponding
     * <code>end()</code> marker as the region to be executed only if the
     * current value of given parameter is less than the value with given
     * mnemonics.
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id  parameter ID
     * @param val_mnemo value mnemonics
     * @throws JmppException (unchecked) if either parameter or mnemonics are
     *     unknown
     */
    public void beginLT(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginLT(param_id, val_mnemo);
    }

    public void beginLTE(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginLTE(param_id, val_mnemo);
    }

    public void beginEQ(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginEQ(param_id, val_mnemo);
    }

    public void beginNE(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginNE(param_id, val_mnemo);
    }

    public void beginGTE(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginGTE(param_id, val_mnemo);
    }

    public void beginGT(String param_id, String val_mnemo) throws JmppException {
        irTdGen.beginGT(param_id, val_mnemo);
    }

    /**
     * Starts conditional code region if the specified parameter is currently "true".
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id parameter ID
     * @throws JmppException (unchecked) if either parameter is unknown or does not
     *     support comparison to boolean values.
     */
    public void beginTRUE(String param_id) throws JmppException {
        irTdGen.beginTRUE(param_id);
    }

    /**
     * Starts conditional code region if the specified parameter is currently "false".
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id parameter ID
     * @throws JmppException (unchecked) if either parameter is unknown or does not
     *     support comparison to boolean values.
     */
    public void beginFALSE(String param_id) throws JmppException {
        irTdGen.beginFALSE(param_id);
    }

    /**
     * Marks the end of conditional template region.
     * Implementation simply redirects this call to the delegate.
     *
     * @param param_id ID of the parameter, for which the conditional region was started.
     * @throws JmppException (unchecked) if parameter is unknown, the conditional region has not been started or
     *   if a call to this methods interleaves with the begin...() end() pair for another parameter
     */
    public void end(String param_id) throws JmppException {
        irTdGen.end(param_id);
    }

    /**
     * Sets test case/test case variant naming mode.
     * Implementation simply redirects this call to the delegate.
     *
     * @param mode the mode indentifier. Can be one of the NM_... constants
     *     defined in this class
     * @throws JmppException (unchecked) if the mode is unknown
     */
    public void setNamingMode(int mode) {
        irTdGen.setNamingMode(mode);
    }

    /**
     * Retrieves current naming mode.
     *
     * @return current naming mode
     *     Implementation simply redirects this call to the delegate.
     * @see #setNamingMode(int)
     */
    public int getNamingMode() {
        return irTdGen.getNamingMode();
    }

    /**
     * Returns array of weak parameters.
     * Implementation simply redirects this call to the delegate.
     *
     * @return array of weak parameters sorted by sort key
     */
    public TemplateParameter[] getWeakParameters() {
        return irTdGen.getWeakParameters();
    }

    /**
     * Returns array of strong parameters.
     * Implementation simply redirects this call to the delegate.
     *
     * @return array of strong parameters sorted by sort key
     */
    public TemplateParameter[] getStrongParameters() {
        return irTdGen.getStrongParameters();
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Methods overriden in JmppLibVM, JmppLib */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * Generates necessary imports into the intermediate program. 
     *
     * @param out                   output destination
     * @param intermediateClassName intermediate program class name
     */
    public void generateProlog(PrintWriter out, String intermediateClassName) {
        out.println("package "+templatePackage+";\n");
        out.println("import com.sun.tgxml.tools.jmppconv.processors.parser.*;");
        out.println("public class "+intermediateClassName+" extends "+getClass().getName()+" {");
    }

    /**
     * Called before the <code>createTest</code> invocation cycle.
     * Notifies the variant generator delegate that
     * all parameters have been added and variant generation engine
     * can be initialized.
     */
    public void initAllTests() {
        super.initAllTests();
        irTdGen.initIterators();
        doHTML = false;
    }

    /**
     * Creates a test with given test number. Simply calls
     * {@link IRTDParamGenerator#splitTest(int)} method to "split" current
     * test case into more test cases (depending on strong TPs) and
     * each test case into more variants (depending on weak TPs)
     *
     * @param testNumber test number
     */
    public void createTest(int testNumber) {
        try {
            irTdGen.splitTest(testNumber);
        } catch (TestFileException tfe) {
            tfe.printStackTrace(System.err);
            halt("could not create test group: " + tfe.getMessage());
        }
    }

    /**
     * Sets current output destination. Overrides this method in super class:
     * <ul>
     * <li>changes the <code>fileName</code> by appending variant name suffix</li>
     * <li>invokes <code>super.setOutput</code> with given arguments.</li>
     * </ul>
     */
    public void setOutput(boolean includeToSources,
                          String fileName,
                          String extension,
                          String dirName)
    {
        fileName = irTdGen.getSourceNameVariant(fileName);
        super.setOutput(includeToSources, fileName, extension, dirName);
    }

    /**
     * Overrides this method in super class: adds current test case' name
     * to the test name.
     *
     * @return the name of current test
     */
    public String testName() {
        test = super.testName() + irTdGen.getTestCaseProperty(irTdGen.TC_NAME);
        return test; 
    }

    /**
     * Overrides this method in super class: prints given string
     * to current output only if {@link #paramConditionsSatisfied()} returns
     * <code>true</code>
     *
     * @param s the string to print
     */
    public void L(String s) {
        if (paramConditionsSatisfied()) {
            super.L(s);
        }
    }

    /**
     * Overrides this method in super class: prints given string
     * to current output only if {@link #paramConditionsSatisfied()} returns
     * <code>true</code>
     *
     * @param s the string to print
     */
    public void LN(String s) {
        if (paramConditionsSatisfied()) {
            super.LN(s);
        }
    }

    /**
     * Overridden from JmppLibTest.
     */
    public void makeHead() {
        String crn = CopyrightManager.getJmppCopyright(
            sccs,
            getSourceToBeGenerated()
        );
        if (crn == null) {
            super.makeHead();
        } else {
            L(crn);
            L("");
            LN(tagMacro.set());
        }
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Implementations of IRGenerator methods */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * @see IRGenerator#generate(java.io.File)
     */
    public ArrayList generate(File inputFile) {
        this.inFile = inputFile;
        this.templatePackage = IRTDGenerator.getTemplatePackageName(inputFile);
        this.inName = inputFile.toString();
        int lastSlash = inName.lastIndexOf(File.separator);
        if (lastSlash != -1) {
             inDir = inName.substring(0, lastSlash + 1);
             inName = inName.substring(lastSlash + 1);
        }
        if (!argsSet) {
            halt(LibResHandler.getResStr("jmppconv.error.setPropNotInvoked"));
        }
        this.makeTemplateHTML = false;
        generate();
        return ((JmppLibVMParam)stepTwoLib).getRoots();
    }

    /**
     * @see IRGenerator#setProperties(java.util.Hashtable)
     */
    public void setProperties(Hashtable hash){
        if (hash == null) {
            halt(LibResHandler.getResStr("jmppconv.error.noOptsInHash"));
        }
        parseOptions(hash);
        argsSet = true;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/
    /* Other methods */
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    /**
     * Simply throws a JmppException with given message.
     *
     * @param msg the message
     */
    public static void halt(String msg) {
        throw new JmppException(msg);
    }

    /**
     * @return a list (ArrayList) of IR root objects created during test generation.
     */ 
    protected ArrayList getRoots() {
        return irTdGen.getRoots();
    }

    /**
     * returns IRTDParamGenerator reference.
     */
    protected IRTDParamGenerator getIRTDGenerator() {
        return this.irTdGen;
    }
}
