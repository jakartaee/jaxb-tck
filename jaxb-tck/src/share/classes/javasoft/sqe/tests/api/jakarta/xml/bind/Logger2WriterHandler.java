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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.PrintWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * Simple implementation of Handler for redirecting log messages to 
 * <code>java.io.PrintWriter</code>
 * 
 * @author Vladimir Sosnin
 *
 */
public class Logger2WriterHandler extends Handler {

    
    protected PrintWriter writer;

    
    public Logger2WriterHandler() {
        super();
    }

    public Logger2WriterHandler(PrintWriter writer) {
        super();
        this.writer = writer;
    }

    @Override
    public void publish(LogRecord record) {
        writer.println(this.getFormatter().formatMessage(record));
    }

    @Override
    public void flush() {
        writer.flush();
    }

    // This writer uses externally assigned writer,
    // so this writer should be closed externally
    @Override
    public void close() throws SecurityException {
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

}
