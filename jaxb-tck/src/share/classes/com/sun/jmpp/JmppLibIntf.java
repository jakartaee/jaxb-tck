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

/**
 * Specifies a contract which must be implemented by any Jmpp library
 * which is to be supported by TestGen. There are two levels of support that a
 * Jmpp library can request from TestGen (or some other Jmpp-based interactive
 * test development system):
 * <ul>
 * <li> <b>basic</b> when only test generation is supported 
 * <li> <b>full </b> when the basic level plus jmpp source file creation wizard are
 *                   supported
 * </ul>
 * A TestGen-supported Jmpp library is required to implement methods at least
 * for the basic support.
 */
public interface JmppLibIntf {
    /**
     * Used to redirect an output to memory.
     * @see #redirectOutput(boolean)
     */
    public final static boolean TO_MEM  = true;

    /**
     * Used to redirect an output to disk.
     * @see #redirectOutput(boolean)
     */
    public final static boolean TO_DISK = false;

    /**
     * @return full description of what this library is supposed to be used for
     */
    String getInfo();

    /**
     * @return an object describing a structure and contents of a wizard which
     *         can be created by TestGen (or other interactive test development
     *         system) upon loading this library. TestGen requires the returned
     *         object to be a subclass of
     *         com.sun.tdk.editor.share.wizard.def.WizardDescriptor class.
     */
    Object getWizardDescriptor();

    /**
     * Performs transformation of the input jmpp source file to an intermediate
     * program and compiles the program (creating an intermediate class).
     * @param template_name name of the source file
     * @throws Exception if some errors occurred during preprocessing or
     *         compilation.
     */
    void preprocessAndCompile(String template_name) throws Exception;

    /**
     * Generates final output (tests). Normally, preprocessing and compilation
     * step must be performed before calling this method.
     * @param generatorClass a class which actually performs test generation.
     *                       (in TDK it is the intermediate class)
     * @see #preprocessAndCompile(String)
     * @see #getIntermediateClassName()
     */
    void generateTests(Class generatorClass) throws Exception;

    /**
     * @return a name of the class resulted from compilation of the intermadiate
     *         program.
     */
    String getIntermediateClassName();

    /**
     * Redirects output of the intermediate program.
     * @param to_mem to memory if if this parameter is true, to disk otherwise.
     *               It is suggested to use the TO_MEM and TO_DISK constants
     *               instead of boolean literals.
     */
    void redirectIntermediateOutput(boolean to_mem);

    /**
     * @return a name of the intermediate program file.
     */
    String getIntermediateSourceName();

    /**
     * @return a StringBuffer instance containing the intermediate program.
     */
    StringBuffer getIntermediateSource();

    /**
     * Redirects output of the generated tests.
     * @param to_mem to memory if if this parameter is true, to disk otherwise.
     *               It is suggested to use the TO_MEM and TO_DISK constants
     *               instead of boolean literals.
     */
    void redirectOutput(boolean to_mem);

    /**
     * @return an array of names of generated tests.
     */
    String[] getTestNames();

    /**
     * @param test_name a name of a test (must be an element of the array
     *                  returned by the getTestNames methods) for which
     *                  generated source names are requested.
     * @return an array of names of generated files which constitute the test
     *         with given name
     */
    String[] getSourcesNames(String test_name);

    /**
     * @param test_name a name of a test (must be an element of the array
     *                  returned by the getTestNames methods) for which
     *                  generated sources are requested
     * @return an array of StringBuffer objects containing sources generated for
     *         the test with given name
     */
    StringBuffer[] getSources(String test_name);

    /**
     * @return a name of an HTML file which is a root of the hierarchy of
     *         generated test descriptions. Useful for running generated tests
     *         under JavaTest.
     */
    String getHtmlIndexFile();

    /**
     * Sets the output directory where all generated test sources are placed.
     * @param dir new output directory
     */
    void setOutputDir(String dir);

    /**
     * Sets the working directory where all intermediate files are placed.
     * @param dir new working directory
     */
    void setWorkDir(String dir);

    /**
     * @return current output directory name
     */
    String getOutputDir();

    /**
     * @return current working directory name
     */
    String getWorkDir();

    /**
     * Sets the debug mode.
     * @param mode if true the debug mode is enabled, if false the debug mode
     *             is disabled
     */
    void setDebugMode(boolean mode);
}
