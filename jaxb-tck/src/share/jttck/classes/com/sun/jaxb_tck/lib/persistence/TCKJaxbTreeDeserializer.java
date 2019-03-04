/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.io.InputStream;

/**
 * @author Vladimir Sosnin
 * 
 */
class TCKJaxbTreeDeserializer implements JaxbTreeDeserializer {
    

    public Object deserialize(InputStream input, ClassLoader cl) {
        Object docRoot = null;
        XMLDecoder decoder = null;
        Thread curThread = Thread.currentThread();
        ClassLoader ccl = curThread.getContextClassLoader();

        try {
            curThread.setContextClassLoader(cl);
            decoder = new XMLDecoder(input);
            // TODO remove debug code below
            decoder.setExceptionListener(new ExceptionListener() {

                public void exceptionThrown(Exception e) {
                    throw new RuntimeException(e);
                }

            });
            // end debug code
            docRoot = decoder.readObject();
        } finally {
            if (decoder != null)
                decoder.close();
            curThread.setContextClassLoader(ccl);
        }
        return docRoot;
    }
}
