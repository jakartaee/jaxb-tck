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

import com.sun.tgxml.tjtf.processors.emitter.EmitterFactory;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.jmpp.lib.TDGenerator;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import java.io.File;
import java.io.IOException;
import com.sun.jmpp.JmppException;
import java.util.ArrayList;
import java.util.Hashtable;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.util.CopyrightManager;

/**  
 * The class extends com.sun.jmpp.lib.JmppLibFP,
 * but instead of HTML test descriptions IR objects corresponding
 * to test descriptions are created.
 */

public class JmppLibFP 
    extends com.sun.jmpp.lib.JmppLibFP implements IRGenerator {

/**
 * @return an ArrayList of TestRoot objects created during JMPP to test generation.
 */ 
    protected ArrayList getRoots(){
        return ((IRTDGenerator) tdGenerator).getRoots();
    }

/**
 * Overriden JmppLibFP method which initializes tdGenerator variable with the 
 * instance of IRTDGenerator class.
 */ 
    protected void initTDGenerator(){
        tdGenerator = (TDGenerator) new IRTDGenerator();
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
        parseOptions(hash);
        argsSet = true;
    }
    
/**
 * Implemented IRGenerator method, starts test generation.
 * @param inputFile an input JMPP template to generate tests from.
 * @return an ArrayList of TestRoot objects created during JMPP to test generation.
 * @throws JmppException (unchecked exception) thrown if setProperties(Hashtable)
 * was not previously invoked.
 */
    public ArrayList generate(File inputFile){
        this.inFile = inputFile;
        this.templatePackage = IRTDGenerator.getTemplatePackageName(inputFile);
        this.inName = inputFile.toString();
        int lastSlash = inName.lastIndexOf(File.separator);
        if (lastSlash != -1) {
             inDir = inName.substring(0, lastSlash + 1);
             inName = inName.substring(lastSlash + 1);
        }
        if (argsSet != true){
            throw new JmppException(LibResHandler.getResStr("jmppconv.error.setPropNotInvoked"));
        }
        this.makeTemplateHTML = false;
        try {
            generate();
        } catch (Exception e){
            throw new JmppException(e);
        }
        return getRoots();
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
}
