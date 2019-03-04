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

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tools.filter.redundancy.TestItemSelectionListener;
import com.sun.tgxml.util.IR;

/**
 * The class logs dependent external libraries to the file. 
 * The file is appended only and all existing information is unchanged.
 * This implementation is multi-process safe and allow to log iformation 
 * from different processes simultinously.<p>
 * The libraries are logged in Properties format:<br>
 * <code>&lt;library ID&gt;=&lt;library variant file&gt;</code> 
 */
public class ExternalLibraryLogger extends TestItemSelectionListener.EmptyListener {
    private SynchronizedFileAppender appender;
    protected StringBuffer buff = new StringBuffer();
    
    /**
     * creates the instance with the given argument list. The fist mandatory 
     * argument defines name of the output file. 
     * The rest of arguments are ignored.
     * @param args argument list.
     */
    public ExternalLibraryLogger(String[] args) {
        this(args[0]);
    }
    
    /**
     * creates the instance with the given file name.
     * @param fileName
     */
    public ExternalLibraryLogger(String fileName) {
       this.appender = SynchronizedFileAppender.getInstance(fileName);
    }

    public synchronized void flush() {
        this.appender.append(buff.toString());
        buff.delete(0, buff.length());
    }

    public void externalLibrarySelected(Library item) throws TestFileException {
        synchronized (buff) {
            buff.append(item.getID());
            buff.append('=');
            buff.append(IR.getAttrElem(IR.SourcePathAttrElemName, item));
            buff.append('\n');
        }
    }
}
