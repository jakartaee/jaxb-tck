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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.validation.Validator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiTest;

import com.sun.jaxb_tck.lib.JaxbTckScript;

/**
 * Represents an validation a generated schemas against a "golden" xml document
 *
 * execute Arguments:
 * -TestURL  (-t) <TestURL> - optional,<br>
 * Test url used as root directory to get xml document<br>
 * -negative (-n) - optional, the xml document is negative<br>
 * -document (-d) - optional, the xml document<br>
 * -schemas  (-s) - schemas<br>
 *
 * @author  Leonid Kuskov
 * @version 1.0
 */
public class SchemaGenTest extends MultiTest{
    

    /**
     * Implementation of customized SAX error handling
     */
    protected static class ErrorHandler extends DefaultHandler {
        public int warningCounter = 0;
        public int errorCounter = 0;
        protected final PrintWriter err;

        public ErrorHandler(PrintWriter err) {
            this.err = err;
        }

        public void warning(SAXParseException e) throws SAXException {
            warningCounter++;
            err.println("Warning: " + getMessage(e));
            err.flush();
        }

        public void error(SAXParseException e) throws SAXException {
            errorCounter++;
            err.println("Error: " + getMessage(e));
            err.flush();
        }

        public void fatalError(SAXParseException e) throws SAXException {
            err.println("Fatal Error: " + getMessage(e));
            err.flush();
            throw e;
        }

        public void clear() {
            warningCounter = 0;
        }
    }
    protected Source           xmlSource;
    protected StreamSource[] schemaSources;

    //Options holder:
    //-TestURL(-t)
    private String testURL;


    //-negative (-n)
    private boolean isXmlNegative = false;

    //-schema   (-s)
    private String schemaNames;

    //-document (-d)
    protected String xmlName;


    /**
     * Decode the next argument in the argument array.
     * -TestURL  (-t) <TestURL> - optional, Test url used as root directory to get xml document<br>
     * -negative (-n) - optional, the xml document is negative<br>
     * -document (-d) - optional, the xml document
     * -schema   (-s) - mandatory, the schemas
     *
     * @param args The array containing all the arguments
     * @param index The position of the next argument to be decoded.
     * @return the number of elements in the array were "consumed" by this call.
     *
     * @throws MultiTest.SetupException is there is a problem decoding the
     *   argument.
     *
     */
    @Override
    protected int decodeArg(String[] args, int index) throws MultiTest.SetupException {
        int count = 2;
        if (args[index].equals("-TestURL") || args[index].equals("-t")) {
            testURL = getNextArgument(args, index);
        } else if (args[index].equals("-document") || args[index].equals("-d")) {
            xmlName = getNextArgument(args, index);
        } else if (args[index].equals("-schemas") || args[index].equals("-s")) {
            schemaNames = getNextArgument(args, index);
        } else if (args[index].equals("-negative") || args[index].equals("-n")) {
            isXmlNegative = true;
            count = 1;
        } else {
                return super.decodeArg(args, index);
        }
        return count;
    }


    /**
     * A setup method called after argument decoding is complete,
     * and before the test cases are executed. By default, it does
     * nothing; it may be overridden to provide additional behavior.
     *
     * @throws MultiTest.SetupException if processing should not continue.
     * This may be due to some inconsistency in the arguments,
     * or if it is determined the test should not execute for
     * some reason.
     */
    @Override
    protected void init() throws MultiTest.SetupException {
        // Check mandatory option.
        if(schemaNames == null) {
            throw new MultiTest.SetupException("-schemas(-s) option not specified.");
        }

        // Fill schemaSources array
        schemaSources = getSchemaSources(schemaNames);

        //Check optional options
        String xmlURL = ( xmlName == null ) ? null :  getDocumentURL(xmlName);

        if(xmlURL != null) {
            xmlSource = new StreamSource(xmlURL);
            if (xmlSource == null) {
               throw new MultiTest.SetupException("Could not instantiate the xml source for " + xmlURL);
            }
        }

        super.init();
    }

    /**
     * The single testcase that performs an validation a generated schema
     * against a "golden" xml document if they passed.
     *
     */
    public Status compileSchema() {

        Schema schema;

        ErrorHandler errorHandler = new ErrorHandler(ref);

        try {
            SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

            factory.setErrorHandler(errorHandler);

            schema = factory.newSchema( schemaSources );

        } catch (SAXException se) {
            return Status.failed("Fatal error:" + getMessage(se) );
        }

        if (errorHandler.errorCounter != 0) {
            return Status.failed("Schema Compilation: " +
                                 errorHandler.errorCounter + " error(s), " +
                                 errorHandler.warningCounter + " warning(s) found.");
        }

        if(xmlSource != null) {
            try {
                Validator validator = schema.newValidator();
                errorHandler.clear();
                validator.setErrorHandler(errorHandler);
                validator.validate(xmlSource);
            } catch(SAXException se) {
                return processStatus( Status.failed("Fatal error: " + getMessage(se)) );
            } catch(IOException ioe) {
                return Status.failed("Fatal error: " + ioe.getMessage());
            }

            if (errorHandler.errorCounter != 0) {
                return processStatus( Status.failed("Document Validation: " +
                                      errorHandler.errorCounter + " error(s), " +
                                      errorHandler.warningCounter + " warning(s) found.") );
            }
        }

        return processStatus( Status.passed("OK") );
    }

    private Status processStatus(Status status) {
        if (isXmlNegative) {
            if(status.isPassed()) {
                return Status.failed("The negative testcase unexpectedly passed with " + status.getReason());
            } else {
                return Status.passed("OK. The negative testcase failed as expected with " + status.getReason());
            }

        }
        return status;
    }

    /**
     * Get next argument from array of args.
     *
     * @throws MultiTest.SetupException if the index + 1 is out of bounds.
     */
    public String getNextArgument(String[] args, int index) throws MultiTest.SetupException {
            index++;
            if (index >= args.length) {
                    throw new MultiTest.SetupException(" No argument is specified for "
                                    + args[index - 1] + " option");
            }
            return args[index];
    }

    /**
     * Returns document URL for a given document file name and testURL that are
     * delivered through parameters.
     *
     * @throws MultiTest.SetupException
     * if catch MalformedURLException for the testURL or docName.
     */
    public String getDocumentURL(String docName) throws MultiTest.SetupException {
        try {
            if (testURL == null) {
                    return new File(docName).toURI().toURL().toString();
            } else {
                    return new URL(new URL(testURL), docName).toString();
            }
        } catch (MalformedURLException ue) {
            throw new MultiTest.SetupException(ue.getMessage());
        }
    }

    /**
     * Helper method to format SAXParseException.
     */
    private static String getMessage(Exception ex) {
        StringBuffer sb = new StringBuffer();

        while (ex != null) {
            if (ex instanceof SAXParseException) {
                SAXParseException e = (SAXParseException) ex;
                sb.append("" + e.getSystemId() + "(" + e.getLineNumber() + ","
                        + e.getColumnNumber() + "): " + e.getMessage());
                ex = e.getException();
            } else {
                sb.append(ex);
                ex = null;
            }
        }
        return sb.toString();
    }

    /**
     * Builds StreamSource array from schema's files from schema's file names
     * presented in schemaNames that are delimited by File.pathSeparator
     *
     * @param  schemaNames list of xsd files delimited by File.pathSeparator
     */
    protected StreamSource[] getSchemaSources(String schemaNames) throws MultiTest.SetupException {

        String[] xsdFiles = schemaNames.split(JaxbTckScript.SCHEMA_SEPARATOR);

        StreamSource[] ss = new StreamSource[xsdFiles.length];

        for ( int i=0; i < xsdFiles.length; i++ ) {

            File xsdFile = new File(xsdFiles[i]);

            if(!xsdFile.isFile() || !xsdFile.exists() ) {
                throw new MultiTest.SetupException( xsdFiles[i] + " schema not found.");
            }
            ss[i] = new StreamSource( xsdFile );
        }

        return ss;
    }

    /**
     * Builds StreamSource array from schema's files
     * presented in schemaFiles collection
     *
     * @param  schemaFiles collection of schema's files
     */
    protected StreamSource[] getSchemaSources(ArrayList<File> schemaFiles) throws MultiTest.SetupException {

        StreamSource[] ss = new StreamSource[schemaFiles.size()];

        for ( int i=0; i < schemaFiles.size(); i++ ) {

            File xsdFile = schemaFiles.get(i);

            if(!xsdFile.isFile() || !xsdFile.exists() ) {
                throw new MultiTest.SetupException( xsdFile.getAbsolutePath() + " schema not found.");
            }
            ss[i] = new StreamSource( xsdFile );
        }

        return ss;
    }

    /**
     * command Line entry point.
     */
    public static void main(String[] args) {
        SchemaGenTest schemaGenTest = new SchemaGenTest();
        PrintWriter log = new PrintWriter(System.err, true);
        PrintWriter ref = new PrintWriter(System.out, true);
        Status status = schemaGenTest.run(args, log, ref);
        log.flush();
        ref.flush();
        status.exit();
    }
}
