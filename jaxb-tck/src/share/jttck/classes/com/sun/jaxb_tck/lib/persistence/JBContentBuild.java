/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jaxb_tck.lib.persistence;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.Test;

/**
 * Class generating auxiliary info for JAXB TCK content tests. It's executed at
 * precompile stage of TCK.
 *
 * @author Vladimir Sosnin
 *
 */
public class JBContentBuild implements Test {

    /**
     * Package name
     */
    protected String packageName;

    /**
     * XML document name
     */
    protected String docName;

    /**
     * Name of file where to store serialized JAXB tree of XML document
     */
    protected String pconName;

    /**
     * Classpath for the test
     */
    protected String testClasspath;

    /**
     * Location of the test data
     */
    protected String testDirPath;

    /**
     * Test's ClassLoader
     */
    protected ClassLoader testClassLoader;

    /**
     * Validity
     */
    protected boolean isInvalid;

    /**
     * Test output
     */
    protected Writer output;

    /**
     * Error output
     */
    protected Writer errOutput;

    /**
     * Main method.
     *
     * @param rawArgs -
     *            see tests description for command line arguments
     */
    public static void main(String[] args) {
        Status s = new JBContentBuild().run(args, new PrintWriter(System.out),
                new PrintWriter(System.err));
        System.out.println(s.getReason());
    }

    public Status run(String[] args, PrintWriter out1, PrintWriter out2) {
        init(args);
        output = out1;
        errOutput = out2;
        if (isInvalid)
            return Status
                    .passed("Content tree serialization isn't applicable to negative document.");
        try {
            Object jaxbTree = getJaxbTree();
            JaxbTreeSerializer serializer = PersistenceFactory.getInstance().createJaxbTreeSerializer();
            serializer.serialize(jaxbTree, 
                                 new BufferedOutputStream(new FileOutputStream( (new File(testDirPath,pconName)).getCanonicalPath() ) ), 
                                 testClassLoader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Status.passed("Content tree document has been serialized.");
    }

    /**
     * Helper method for getting JAXB tree
     *
     * @return Unmarshaled JAXB tree.
     */
    protected Object getJaxbTree() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(packageName, testClassLoader);
            Unmarshaller u = ctx.createUnmarshaller();
            return u.unmarshal(new File(docName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for parsing arguments passed to main.
     *
     * @param rawArgs
     */
    protected void init(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-invalid") || args[i].equals("-i")) {
                isInvalid = true;
            } else if ("-package".equals(args[i]) || "-p".equals(args[i])) {
                packageName = args[++i];
            } else if ("-document".equals(args[i]) || "-d".equals(args[i])) {
                docName = args[++i];
            } else if ("-pcontent".equals(args[i])) {
                pconName = args[++i];
            } else if ("-Classpath".compareToIgnoreCase(args[i]) == 0) {
                testClasspath = args[++i];
            } else if ("-TestURL".compareToIgnoreCase(args[i]) == 0) {
                testDirPath = args[++i].substring(0, args[i]
                        .lastIndexOf(File.separator))
                        + File.separator;
            }
        }
        File path = new File(testClasspath);
        try {
            testClassLoader = new URLClassLoader(new URL[] { path.toURI().toURL() }, this.getClass().getClassLoader());
            if(testClassLoader == null)
                testClassLoader = ClassLoader.getSystemClassLoader();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
