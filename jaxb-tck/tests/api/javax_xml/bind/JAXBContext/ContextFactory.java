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

package javasoft.sqe.tests.api.jakarta.xml.bind.JAXBContext;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

public class ContextFactory {
    public static JAXBContext createContext(String contextPath, ClassLoader classLoader)
                 throws JAXBException {
        try {
            Class cl = classLoader.loadClass(contextPath + "." + "CustomJAXBContext");
            return (JAXBContext)(cl.newInstance());
        } catch (ClassNotFoundException e) {
            throw new JAXBException(e);
        } catch (java.lang.InstantiationException ie) {
            throw new JAXBException(ie);
        } catch (java.lang.IllegalAccessException ae) {
            throw new JAXBException(ae);
        }
    }
}

