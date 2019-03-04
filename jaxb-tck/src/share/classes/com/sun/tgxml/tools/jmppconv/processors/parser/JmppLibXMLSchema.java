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

import com.sun.jmpp.lib.TDGenerator;
import java.io.File;
import com.sun.jmpp.JmppException;
import java.util.ArrayList;
import java.util.Hashtable;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tools.jmppconv.processors.parser.IRGenerator;

/**  
 * The class extends com.sun.jmpp.lib.JmppLibXMLSchema,
 * but instead of HTML test descriptions IR objects corresponding
 * to test descriptions are created.
 */

public class JmppLibXMLSchema 
    extends com.sun.jmpp.lib.JmppLibXMLSchema implements IRGenerator {

/**
 * @return an ArrayList of TestRoot objects created during JMPP to test generation.
 */ 
    protected ArrayList getRoots(){
        return ((XmlSchemaIRTDGenerator) tdGenerator).getRoots();
    }

/**
 * Overriden JmppLibXMLSchema method which initializes tdGenerator variable with the 
 * instance of IRTDGenerator class.
 */ 
    protected void initTDGenerator(){
        tdGenerator = (TDGenerator) new XmlSchemaIRTDGenerator();
    }

/**
 * A flag to check whether setProperties(Hashtable) was invoked before
 * generate(File f). True if so, otherwise, false.
 */
    protected boolean argsSet = false;

/**
 * Implemented IRGenerator method, parses options passed in args.
 * @param hash a Hashtable of JMPP options to set/parse.
 * @throws JmppException (unchecked exception) if hash is null
 */
    public void setProperties(Hashtable hash){
        if (hash == null){
            throw new JmppException(LibResHandler.getResStr("jmppconv.error.noOptsInHash"));
        }
        parseOptions(hash);
        argsSet = true;
    }

/**
 * The method invokes super.parseOptions(Hashtable hash),
 * then adds workJavaDir last directory tail to the outputDir tail
 * if they are different.
 * @param hash a hashtable to get option values from
 */
        public void parseOptions(Hashtable hash) {
            super.parseOptions(hash);
            String workDirTail = lastSegments(workJavaDir, File.separator, 3);
            String outpDirTail = lastSegments(outputDir,   File.separator, 2);
            if (outpDirTail.length()>1 && 
                workDirTail.startsWith(outpDirTail)) {
                outputDir += File.separator + lastSegments(workJavaDir, File.separator, 1);
            }
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
        this.templatePackage = "com.sun.tgxml.tools.jmppconv.processors.parser" + ".sub" + getUniqNum();
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
    
    public String getUniqNum() {
        synchronized (Counter.class) {
            return new Integer(Counter.getNum()).toString();
        }
    }
}

class Counter {
    public static volatile int num = 0;
    
    public static synchronized int getNum() {
        return ++num;
    }
}

