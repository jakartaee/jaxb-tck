/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.redundancy.processors;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tools.filter.redundancy.TestItemListFilter;
import com.sun.tgxml.tools.filter.redundancy.TestItemSelectionListener;
import com.sun.tgxml.tools.testgen.processors.emitter.EmitterManager;
import com.sun.tgxml.tools.testgen.processors.emitter.Generator;
import com.sun.tgxml.util.IR;

/**
 * The class generates external libraries on fly.
 */
public class ExternalLibraryBuilder extends TestItemSelectionListener.EmptyListener {
    private Generator generator = new EmitterManager();
    private HashSet done = new HashSet();
    private String tckRootDir;
    
    /**
     * creates instance with "." root directory.
     */
    public ExternalLibraryBuilder() {
        this(".");
    }
    
    public ExternalLibraryBuilder(String tckRoot, Properties properies) {
        this(tckRoot);
        generator.setProperties(properies);
    }
    /**
     * creates instance with given arguments.
     * @param args arguments. The array should contain at least one element,
     *   and the first element is assumed as output root directory.
     */
    public ExternalLibraryBuilder(String[] args) {
        this(args[0]);
    }
    
    /**
     * creates instance with a diven root directory.
     */
    public ExternalLibraryBuilder(String tckRoot) {
        this.tckRootDir = tckRoot;
        if (tckRootDir != null) {
            if (!tckRootDir.endsWith(File.separator)) {
                tckRootDir += File.separator;
            }
        } else {
            tckRootDir = "." + File.separator;
        }
    }

    public void externalLibrarySelected(Library item) throws TestFileException {
        String id = TestItemListFilter.getGlobalID(item, false);
        if ((id != null) && done.contains(id)) {
            return;
        }
        try {
            String path = IR.getAttrElem(IR.relSourcePathAttrElemName, item);
            path = tckRootDir + ((path != null) ? new File(path).getParent() : "");
            IR.setAttrElem(item, "OutputDir", path);
            this.generator.generate((IRObj)item);
            done.add(id);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can not generate library. IOException is thrown:" + e);
        }
    }
           
    public void flush() {
    }
}
