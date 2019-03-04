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
package javasoft.sqe.jck.lib;

import javasoft.sqe.javatest.lib.MultiTest.SetupException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.Source;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Helper to load a schema.
 */
public class SchemaLoader {
    static SchemaFactory sf;
    
    static public Schema loadSchema(Source... sources) throws SetupException {
        try {
            if (sf == null) {
              sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            }
            boolean isSecure = true;
            while (true) {
                try {
                    Schema schema = sf.newSchema(sources);
                    // schema loaded successfully
                    return schema;
                } catch (SAXParseException spe) {
                    // Current configuration of the parser doesn't allow a
                    // maxOccurs
                    // attribute value to be set greater than the value 5,000.
                    // (SecurityManager.DEFAULT_MAX_OCCUR_NODE_LIMIT)
                    try {
                        isSecure = sf.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING);
                    } catch (Exception se) {
                        // The feature is not supported or its value can't be
                        // recognized.
                        throw new SetupException(se.getMessage());
                    }
                    if (isSecure) {
                        try {
                            sf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
                        } catch (Exception se) {
                            // The feature is not supported or its value can't
                            // be changed.
                            throw new SetupException(se.getMessage());
                        }
                        continue;
                    }
                    throw new SetupException(spe.getMessage());
                } catch (OutOfMemoryError oome) {
                    // since schema cannot be loaded it's null.
                    return null;
                } catch (SAXException se) {
                    throw new SetupException(se.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SetupException(e.getMessage());
                }
            }
        } catch (Exception ex) {
            throw new SetupException(ex.getMessage());
        }
    }
}
