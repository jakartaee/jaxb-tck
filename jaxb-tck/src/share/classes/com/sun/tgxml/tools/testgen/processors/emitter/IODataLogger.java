/*
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.testgen.processors.emitter;

import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.filter.redundancy.processors.SynchronizedFileAppender;

/**
 * A utility class that is used to log names of ExternalData or InlineData whose
 * type is iodata. This is useful for TCKs which need to keep track of the I/O files
 * accessed by tests.
 */
public class IODataLogger {
    private String logFile = null;
    /**
     * Build property name which designate the log file name.
     */
    public static final String LOG_FILENAME = "testgen.emitter.IOData.logFile";
    
    private static IODataLogger logger = new IODataLogger();
    
    private IODataLogger() {            
        logFile = BuildProperties.getString(LOG_FILENAME);
    }

    public static IODataLogger getInstance() {
        return logger;
    }
    
    /**
     * Log the IO file name into the log file.
     */
    public void log(String name) {
        if (logFile == null) {
            return;
        }
        SynchronizedFileAppender appender = SynchronizedFileAppender.getInstance(logFile);
        appender.append(name + "\n");
    }
}
