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

package com.sun.tgxml.tools.indexgen;

import java.io.File;
import java.util.Vector;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tools.indexgen.api.TestSuite;

public interface HtmlGenerator {
   
   /**
    * Creates specified html file from the doc.xml file.
    * Inserts links into generating html either as a list or as a table.
    * (The decision is based on contents format attribute)
    */
    public Vector create(File resultHTML, TestSuite xmlFile, Vector links)
         throws TestFileException;
  
}
