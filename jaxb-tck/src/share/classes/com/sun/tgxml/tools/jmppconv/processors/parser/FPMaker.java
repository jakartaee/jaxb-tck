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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import com.sun.jmpp.JmppException;
import com.sun.jmpp.lib.TDGenerator;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.util.CopyrightManager;

/**
 * The class extends com.sun.jmpp.lib.FPMaker,
 * but instead of HTML test descriptions IR objects corresponding
 * to test descriptions are created.
 */

public class FPMaker
    extends com.sun.jmpp.lib.FPMaker implements IRGenerator {

    /**
     * @return an ArrayList of TestRoot objects created during JMPP to test generation.
     */
    protected ArrayList getRoots(){
        return ((IRTDGenerator) tdGenerator).getRoots();
    }

    /**
     * Overriden FPMaker method which initializes tdGenerator variable with the
     * instance of IRTDGenerator class.
     */
    protected void initTDGenerator(){
        tdGenerator = (TDGenerator) new IRTDGenerator();
    }

    /**
     * The method returns true if there is need to generate HTML file containing
     * description of generated from a template tests.
     * @return false.
     */
    protected boolean makeTemplateHTML(){
        return false;
    }

    /**
     * A flag to check whether setProperties(Hashtable) was invoked before
     * generate(File f). True if so, otherwise, false.
     */
    protected boolean argsSet = false;

    /**
     * Implemented IRGenerator method, parses options passed in args.
     * @param hash a Hashtable of JMPP options to set/parse.
     * @throws JmppException (unchecked exception) thrown if hash is null.
     */
    public void setProperties(Hashtable hash){
        if (hash == null){
            throw new JmppException(LibResHandler.getResStr("jmppconv.error.noOptsInHash"));
        }
        this.argv = new String[0];
        this.argsHash = hash;
        argsSet = true;
    }

    /**
     * Implemented IRGenerator method, starts test generation.
     * @param inputFile an input JMPP template to generate tests from.
     * @throws JmppException (unchecked exception) thrown if setProperties(Hashtable args)
     * was not previously invoked.
     * @return an ArrayList of TestRoot objects created during JMPP to test generation.
     */
    public ArrayList generate(File inputFile){
        if (argsSet != true){
            throw new JmppException(LibResHandler.getResStr("jmppconv.error.setPropNotInvoked"));
        }
        argsHash.put("-inFile", inputFile.toString());
        this.templatePackage = IRTDGenerator.getTemplatePackageName(inputFile);
        setLog(new PrintWriter(System.err, true));
        try {
            transform(null);
        } catch (Exception e){
            throw new JmppException(e);
        }
        return getRoots();
    }

    protected String getSourceCopyrightNotice(com.sun.jmpp.lib.JmppLibFP lib, String src) {
        String crn = CopyrightManager.getJmppCopyright(
            lib.sccs,
            (src == null || src.equals("") ? lib.getSourceToBeGenerated() : src)
        );
        return crn == null ? super.getSourceCopyrightNotice(lib) : crn;
    }

    /**
     * Overrides this method in <code>com.sun.jmpp.lib.FPMaker</code>
     * to return a {@link FPMaker.MyJmppLibFP} instance.
     * @return a {@link FPMaker.MyJmppLibFP} instance
     */
    protected com.sun.jmpp.lib.JmppLibFP newJmppLibFP() {
        return new MyJmppLibFP();
    }

    /**
     * <code>com.sun.jmpp.lib.JmppLibFP</code> extension which supports
     * general CRN insertion mechanism.
     */
    public static class MyJmppLibFP extends com.sun.jmpp.lib.JmppLibFP {
        /**
         * Overrides super class' method to support general CRN
	 * insertion mechanism.
         */
        public void makeHead() {
            String crn = CopyrightManager.getJmppCopyright(
                getSccs(),
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

        /**
         * Overrides generateProlog in JmppLibFP class to correctly
	 * generate base class name (which is an inner class).
         */
        public void generateProlog(PrintWriter out, String intermediateClassName) {
            out.println("package "+templatePackage+";\n");
            out.println("import javasoft.sqe.jck.fp.emath.*;");
            out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.TestCase;");
            out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.UCBInput;");
            out.println("import javasoft.sqe.jck_internal.fp.UCBTEST.UCBInputException;\n");
            out.println("import " + getClass().getPackage().getName() + ".*;\n");
            out.println("public class "+intermediateClassName+" extends "+getClass().getName().replace('$', '.') +" {");
        }
    }
}
