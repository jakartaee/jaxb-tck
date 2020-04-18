/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;

import javasoft.sqe.javatest.lib.MultiTest;
import javasoft.sqe.jck.lib.SchemaLoader;
import javasoft.sqe.jck.lib.MultiTestExt;

/**
 * Base class for CTTest and JAXBTest.
 */
public class JAXBTestBase extends MultiTestExt {

    /*
     * Null indicates that during testing a schemas must be ignored since them 
     * could not be loaded. "Null" schema turns off a schema validation.
     */
    protected Schema schema;

    protected ArrayList<String> schemaNameList = new ArrayList<String>();

    protected Source[]          schemaSources;
    
    /**
     * Test URL which is used as a root directory to find xml documents. Use
     * option -TestURL.
     */
    private String testURL;
    
    /**
     * Get next argument from array of args.
     *
     * @throws MultiTest.SetupException
     *             if the index + 1 is out of bounds.
     */
    public String getNextArgument(String[] args, int index)
            throws MultiTest.SetupException {
        index++;
        if (index >= args.length) {
            throw new MultiTest.SetupException("no argument is specified for "
                    + args[index - 1] + " option");
        }
        return args[index];
    }

    /**
     * Decode the following arguments:<br>
     * -package (-p, -contextPath) <package(s)> - optional, default is ""<br>
     * -TestURL <TestURL> - optional, default is the current working dir<br>
     * -out (-o) <fileName> - file name to marshal to, use '-o :ref' to write to
     * jtr<br>
     * -classpath (-cp) <classPath> - use the classpath to create context,
     * default is system classpath<br>
     *
     * @param args
     *            The array containing all the arguments
     * @param index
     *            The position of the next argument to be decoded.
     * @return the number of elements in the array were "consumed" by this call.
     *
     * @throws MultiTest.SetupException
     *             is there is a problem decoding the argument.
     */
    protected int decodeArg(String[] args, int index)
            throws MultiTest.SetupException {
        if (args[index].equals("-package") || args[index].equals("-p")
                || args[index].equals("-contextPath")) {
            setPackageName(getNextArgument(args, index));
        } else if (args[index].equals("-TestURL")) {
    		this.testURL = getNextArgument(args, index);
        } else if (args[index].equals("-classpath")
                || args[index].equals("-cp")) {
            setClassPath(getNextArgument(args, index));
        } else if (args[index].equals("-schema")) {
            // By default schema(s) finalizes the argument list.
            int i = index+1;
            do {
                schemaNameList.add(args[i++]);
            } while (i<args.length);
            return i-index;
        } else if (args[index].equals("-out") || args[index].equals("-o")) {
            String dest = getNextArgument(args, index);
            if (dest.equals(":ref")) {
            	setOut(ref);
            } else {
                try {
                	setOut(new FileWriter(dest));
                } catch (IOException ioe) {
                    throw new MultiTest.SetupException("cannot open file "
                            + dest);
                }
            }
        } else {
            return super.decodeArg(args, index);
        }
        return 2;
    }

    /**
     * Initializes test arguments: creates classPathLoader. Initializes test
     * arguments: document url. A setup method called after argument decoding is
     * complete, and before the test cases are executed. By default, it does
     * nothing; it may be overridden to provide additional behavior.
     *
     * @throws MultiTest.SetupException
     *             if processing should not continue. This may be due to some
     *             inconsistency in the arguments, or if it is determined the
     *             test should not execute for some reason.
     */
    protected void init() throws MultiTest.SetupException {
        super.init();
        schemaSources    = getSchemaSources(schemaNameList);
        schema           = SchemaLoader.loadSchema(schemaSources);
    }  

    /**
     * Returns document URL for a given document file name and testURL set in
     * parameters.
     *
     * @throws TestFailureException
     *             if catch MalformedURLException for the testURL or docName.
     */
    public URL getDocumentURL(String docName) {
        try {
            if ( testURL == null) {
                return new File(docName).toURI().toURL();
            } else {
                return new URL(new URL(testURL), docName);
            }
        } catch (MalformedURLException e) {
            throw new TestFailureException(e);
        }
    }

    /**
     * Returns document URL for a given document file name and testURL set in
     * parameters.
     *
     * @throws TestFailureException
     *             if catch MalformedURLException for the testURL or docName.
     */
    public Source[] getSchemaSources(ArrayList<String> sourceNameList) {
        ArrayList<Source> srcList = new ArrayList<Source>();
        for( String docName : sourceNameList) {
            try {
                if (testURL == null) {
                    srcList.add( new StreamSource( new File(docName).toURI().toURL().toExternalForm()) );
                } else {
                    srcList.add( new StreamSource( new URL(new URL(testURL), docName).toExternalForm()) );
                }
            } catch (MalformedURLException e) {
                throw new TestFailureException(e);
            }
        }
        return srcList.toArray(new Source[srcList.size()]);
    }
    
    /**
     * Returns string path for a given docURL represented as URL.
     *
     * @throws TestFailureException
     *             if catch MalformedURLException for the testURL or docName.
     */
    public String getDocumentPath(URL docURL) {
        String path = docURL.getPath();
        path.replace('/', File.separatorChar);
        return path;
    }
}
