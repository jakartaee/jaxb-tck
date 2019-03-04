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

package com.sun.tgxml.tools.filter.redundancy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusLogger {
    public static final Level REJECT_BY_ATTRIBUTE = new DetailsLevel("rejectByAttribute", 900);
    public static final Level REJECT_BY_DEPENDENCY = new DetailsLevel("rejectByDependency", 900);
    public static final Level REJECT_EXCLUDED = new DetailsLevel("rejectExcluded", 900);
    public static final Level REJECT_NEW_TEST = new DetailsLevel("rejectNewTest", 900);
    public static final Level REJECT_REDUNDANT_TEST = new DetailsLevel("rejectRedundantTest", 900);
    public static final Level REJECT_EMPTY_TEST_GROUP = new DetailsLevel("rejectEmptyTestGroup", 900);
    public static final Level REJECT_DELETED = new DetailsLevel("rejectDeleted", 900);

    public static final String LOGGING_SYBSYSTEM_NAME = "com.sun.tgxml.tools.filter.redundancy";

    protected static Logger logger = Logger.getLogger(LOGGING_SYBSYSTEM_NAME);
    
    public static class DetailsLevel extends Level {
        public DetailsLevel(String name, int level) {
            super(name, level);
        }
    }
    
    protected static void reportRejectByAttribute(String logId) {
        logger.log(REJECT_BY_ATTRIBUTE, logId);
    }

    protected static void reportRejectByDependency(String logId, String dependency) {
        logger.log(REJECT_BY_DEPENDENCY, logId + " -> '" + dependency + "'");
    }
    
    protected static void reportRejectExcluded(String logId) {
        logger.log(REJECT_EXCLUDED, logId);
    }

    protected static void reportRejectNewTest(String logId) {
        logger.log(REJECT_NEW_TEST, logId);
    }
    
    protected static void reportRejectRedundancy(String logId, String filterName) {
        logger.log(REJECT_REDUNDANT_TEST, logId, filterName);
    }
    
    protected static void reportRejectEmptyTestGroup(String logId) {
        logger.log(REJECT_EMPTY_TEST_GROUP, logId);
    }
    
    protected static void reportRejectDeleted (String logId) {
    	logger.log(REJECT_DELETED, logId);
    }
    
}
