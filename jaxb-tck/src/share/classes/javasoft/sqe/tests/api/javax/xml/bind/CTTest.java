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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.ValidationException;
import jakarta.xml.bind.util.JAXBSource;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import javasoft.sqe.javatest.Status;

/**
 * Base class for all Content Tree Tests generated in TCK JAXB.
 */
public class CTTest extends JAXBTestBase {

    /**
     * unmarshalling documents with validation enabled.
     * Just checks that content classes parse valid docs and failed to parse invalid ones.
     */
    public Object unmarshal(String docName) {

        ErrorCollector eh = new ErrorCollector();

        try {
            JAXBContext jc = getJAXBContext();
            Unmarshaller u = jc.createUnmarshaller();

            eh.setParent(u.getEventHandler());

            u.setSchema(schema);
            u.setEventHandler(eh);

            Object obj = u.unmarshal(getDocumentURL(docName));

            if (!eh.hasEvents()) {
                return obj;
            }

            throw new TestFailureException("No exception is thrown but some error events are handled");

        } catch (JAXBException jaxbe) {
            if (!eh.hasEvents()) {
                throw new TestFailureException("no error events are handled but " + jaxbe + " is thrown", jaxbe);
            }
            throw new TestFailureException(jaxbe);
        } finally {
            eh.printEvents(ref);
        }
    }

    /**
     * validating content trees for successfully parsed docs. Valid docs should be
     * unmarshalled and validated OK and errors should be got for invalid docs
     * Just checks that content classes parse valid docs and failed to parse invalid ones.
     */
    public Status validate(Object docInstance) {
        boolean isValid;
        ErrorCollector eh = new ErrorCollector();

        try {
            JAXBContext jc = getJAXBContext();
            JAXBSource source = new JAXBSource(jc,docInstance);
            Validator v = schema.newValidator();
            // eh.setParent(v.getEventHandler());
            v.setErrorHandler(eh);

            v.validate(source);
            isValid = true;

        } catch (ValidationException ve) {
            ve.printStackTrace(ref);
            isValid = false;
        } catch (JAXBException jaxbe) {
            throw new TestFailureException(jaxbe);
        } catch (SAXException e) {
            e.printStackTrace(ref);
            isValid = false;
        } catch (IOException e) {
            return Status.error(e.getMessage());
        } finally {
            eh.printEvents(ref);
        }

        if (isValid) {
            if (!eh.hasEvents()) {
                return Status.passed("OK");
            } else {
                throw new TestFailureException("validateRoot passed but some error events are handled");
            }
        } else {
            if (eh.hasEvents()) {
                return Status.failed("validateRoot failed and some error events are handled");
            } else {
                throw new TestFailureException("validateRoot failed but no error events are handled");
            }
        }

    }

    /**
     * marshalling documents.
     * In the simplest case the test just invokes marshalling for valid doc and checks that
     * no errors are reported.
     */
    public Status marshal(Object docInstance) {
        ErrorCollector eh = new ErrorCollector();

        try {
            JAXBContext jc = getJAXBContext();

            Marshaller m = jc.createMarshaller();
            m.setEventHandler(eh);
            m.marshal(docInstance, getOut());

            return Status.passed("OK");

        } catch (JAXBException jaxbe) {
            throw new TestFailureException(jaxbe);
        }
    }
}
