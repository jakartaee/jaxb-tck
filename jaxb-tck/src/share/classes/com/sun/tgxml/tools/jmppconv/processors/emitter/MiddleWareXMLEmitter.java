/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.emitter;

import java.io.*;
import java.util.*;

import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.XMLObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.processors.emitter.impl.XMLEmitterImpl;
import com.sun.tgxml.tjtf.processors.emitter.XMLEmitter;
import com.sun.tgxml.tjtf.processors.emitter.EmitterFactory;
import com.sun.tgxml.tjtf.api.tests.TestRoot;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.indexgen.processors.emitter.TestSuiteEmitterFactory;
 
/** 
 * Combined TestGroup &amp; Doc.xml emitter;
 * 
 * @version 	1.0, 10/02/97 
 * @author Nickolay Kuznetsov
 */ 
 
public class MiddleWareXMLEmitter extends XMLEmitterImpl {

  	XMLEmitter m_testrootEmitter = null;
	XMLEmitter m_docxmlEmitter = null;

	public MiddleWareXMLEmitter() {
		
	}
    
    /**
     * @param root The root TD object from which to start emitting.
     * @param stream The output stream describing where the XML will be emitted.
     * @throws TestFileException If there is a problem with the IR.
     * @throws IOException If there is an IO problem.
     */
    public void emit (XMLObj root, OutputStream stream) throws TestFileException, IOException {
        if (root instanceof TestRoot) {
            if (m_testrootEmitter == null) {
                m_testrootEmitter = EmitterFactory.createDefaultXMLEmitter();
            }
            m_testrootEmitter.emit(root, stream);
        } else if (root instanceof TestSuite) {
            if (m_docxmlEmitter == null) {
                m_docxmlEmitter = TestSuiteEmitterFactory.createTestSuiteEmitter();
            }
            m_docxmlEmitter.emit(root, stream);
        }
    }
}
