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
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URL;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiTest;
import com.sun.jaxb_tck.lib.persistence.Comparator;
import com.sun.jaxb_tck.lib.persistence.JaxbTreeDeserializer;
import com.sun.jaxb_tck.lib.persistence.PersistenceFactory;

/**
 * Main test class for testing XML Schema to Java compatibility
 *
 * @author Vladimir Sosnin
 *
 */
public class JAXBTest extends JAXBTestBase {

    /**
     * Command line starter.
     */
    public static void main(String[] args) {
        JAXBTest test = new JAXBTest();
        PrintWriter err = new PrintWriter(System.err, true);
        PrintWriter out = new PrintWriter(System.out, true);
        Status status = test.run(args, err, out);
        err.flush();
        out.flush();
        status.exit();
    }

    /**
     * XML document file name to process.
     */
    protected String document;

    /**
     * Document URL which is used to marshal and unmarshal the document.
     */
    protected URL documentURL;

    /**
     * If the flag set, the XML document is expected to be invalid.
     */
    protected boolean isInvalid;

    /**
     * If the flag set, the jbx validation is expected.
     */
    protected boolean isBeanTest = false;
    
    /**
     * Name of file with serialized content
     */
    protected String pcontentName = null;

    /**
     * URL to file with serialized content
     */
    protected URL pcontentURL;

    protected JAXBContext jc  = null;
    protected Unmarshaller u  = null;
    protected Marshaller   m  = null;

    
    /**
     * Decode the following arguments (n addition to ones of the base class):<br>
     * -invalid (-i) - the document is invalid<br>
     * -document (-d) <xml document> - <br>
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
        if (args[index].equals("-invalid") || args[index].equals("-i")) {
            isInvalid = true;
            return 1;
        } else if (args[index].equals("-pcontent")) {
            pcontentName = getNextArgument(args, index);
        } else if (args[index].equals("-document") || args[index].equals("-d")) {
            document = getNextArgument(args, index);
        } else {
            return super.decodeArg(args, index);
        }
        return 2;
    }

    /**
     * Initializes test arguments: document url. A setup method called after
     * argument decoding is complete, and before the test cases are executed. By
     * default, it does nothing; it may be overridden to provide additional
     * behavior.
     *
     * @throws MultiTest.SetupException
     *             if processing should not continue. This may be due to some
     *             inconsistency in the arguments, or if it is determined the
     *             test should not execute for some reason.
     */
    protected void init() throws MultiTest.SetupException {
        super.init();
        if (document == null) {
            throw new MultiTest.SetupException("document is not specified");
        }

        try {
            documentURL = getDocumentURL(document);
            if( pcontentName != null ) {
                isBeanTest = ! pcontentName.equals("skip");
                if(isBeanTest) {
                    pcontentURL = getDocumentURL(pcontentName);
                }
            }
        } catch (TestFailureException tfe) {
            throw new MultiTest.SetupException(tfe.getMessage());
        } 
        // 1. JAXB Context
        try {
          jc = getJAXBContext();
        } catch (JAXBException je) {
          String msg=je.getMessage();
          je.printStackTrace(ref);
          throw new MultiTest.SetupException("Getting a new instance of JAXB context failed\n" 
              + (msg != null ? " with the message \"" + msg + "\" " : ""));
        }
        //2. Unmarshaller.
          try {
            u = jc.createUnmarshaller();
          } catch (JAXBException je) {
            String msg=je.getMessage();
            je.printStackTrace(ref);
            throw new MultiTest.SetupException("Creating an Unmarshaller object failed\n" 
                + (msg != null ? " with the message \"" + msg + "\" " : ""));
          }
        //3. Marshaller.
        try {
          m = jc.createMarshaller();
        } catch (JAXBException je) {
          String msg=je.getMessage();
          je.printStackTrace(ref);
          throw new MultiTest.SetupException("Creating an Marshaller object failed\n" 
              + (msg != null ? " with the message \"" + msg + "\" " : ""));
        }
        
    }

    protected Status adjust(Status status) {
        if (isInvalid) {
            if (status.isPassed()) {
                return Status.failed("Unexpectedly passed with " + status);
            } else if (status.isFailed()) {
                return Status.passed("Failed as expected with " + status);
            }
        }
        return status;
    }

    /**
     * The first test case: unmarshaling documents with validation enabled.
     * Just checks that content classes parse valid documents and failed to parse
     * invalid ones.
     */
    public Status unmarshal() {
        ErrorCollector eh = new ErrorCollector();
        try {
          
            u.setEventHandler(eh);
            u.setSchema(schema);
            u.unmarshal(documentURL);

            if (!eh.hasEvents()) {
                return adjust(Status.passed("OK"));
            }

            return Status.failed("No exception is thrown but some error events are handled");

        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace(ref);
            if (!eh.hasEvents()) {
                return Status.failed("No error events are handled but " + 
                                     jaxbe +  
                                     " is thrown");
            }
            return adjust(Status.failed(eh.getFirstEventString()));
        } catch(RuntimeException re) {
            String msg = re.getMessage();
            return adjust(Status.failed("Runtime Exception of type: " +
                                        re.getClass().getName() + 
                                        msg != null ? " with message: " + msg : ""));
        } finally {
            eh.printEvents(ref);
        }
    }
 
    /**
     * The third test case: marshaling documents. In the simplest case the test
     * just invokes marshaling for valid doc and checks that no errors are
     * reported.
     */
    public Status marshal() {

        if (isInvalid) {
            return Status
                    .passed("This testcase is not applicable to invalid documents.");
        }

        ErrorCollector eh = new ErrorCollector();

        try {
            //1
            u = jc.createUnmarshaller();
            u.setEventHandler(eh);
            Object jaxbTree = u.unmarshal(documentURL);
            //2
            m = jc.createMarshaller();
            m.setEventHandler(eh);
            m.marshal(jaxbTree, getOut());

            return adjust(Status.passed("OK"));

        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace(ref);
            return Status.failed(jaxbe.toString());
        }

    }

    /**
     * Test case performs comparison of serialized content against content got
     * using JAXB unmarshaling.
     *
     * @return Status
     */
    public Status compareContent() {
        if (isInvalid) {
            return Status.passed("This testcase is not applicable to invalid documents.");
        }
        
        if(!isBeanTest) {
            return Status.passed("Passed without testing. " +
                                  "This testcase is not applicable to such kind of tests");
        }

        try {
            Object jaxbTree = u.unmarshal(documentURL);
            File pcontent = new File(pcontentURL.getFile());
            if (!pcontent.exists()) {
                return Status.failed("The content comparison failed.\n" +
                                      "The \"golden\" content file " + 
                                      pcontentURL + 
                                      " doesn't exists.");
            }
            Object docPersistent = deserealizeBean(pcontent);
            return Comparator.compareBeans(docPersistent, jaxbTree);
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace(ref);
            return adjust(Status.failed("The content comparison failed: "
                    + jaxbe.toString()));
        }
    }

    /**
     * Deserializes bean tree produced by JaxbTreeSerializer
     * @param xmlFile
     * @return Objects tree that should be compared with JAXB tree
     */
    protected Object deserealizeBean(File xmlFile) {
        Object obj = null;
        JaxbTreeDeserializer deserializer = PersistenceFactory.getInstance().createJaxbTreeDeserializer();
        try {
            obj = deserializer.deserialize(new FileInputStream(xmlFile), getClassPathLoader());
        } catch (Exception ex) {
          String msg=ex.getMessage();
          ex.printStackTrace(ref);
          throw new TestFailureException("Deserializing a bean object failed\n" 
              + (msg != null ? " with the message \"" + msg + "\" " : ""));
        }          
        return obj;
    }
}
