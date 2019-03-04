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

package com.sun.tgxml.tools.filter.generic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.tgxml.tjtf.api.attributes.TargetSpec;

/**
 * The class defined the logging method for each test selection attribute.
 */
public class StatusLogger {

    public static final String LOGGING_SYBSYSTEM_NAME = "com.sun.tgxml.tools.filter.generic";
    public static final String LOGGING_LEVEL = "logging.level";

    public static final Level ACCEPT_MEMBER_SIG = new DetailsLevel("acceptMemberSig", 850);
    public static final Level ACCEPT_REQUIRED_RESOURCE = new DetailsLevel("acceptRequiredResource", 850);
    public static final Level ACCEPT_TARGET_SPEC = new DetailsLevel("acceptTargetSpec", 850);
    public static final Level ACCEPT_TARGET_SPEC_ELEM = new DetailsLevel("acceptTargetSpecElem", 850);
    public static final Level ACCEPT_TESTED_CLASS = new DetailsLevel("acceptTestedClass", 850);
    public static final Level ACCEPT_TESTED_PACKAGE = new DetailsLevel("acceptTestedPackage", 850);
    public static final Level ACCEPT_ASSERTION_REF = new DetailsLevel("acceptAssertionRef", 850);
    public static final Level ACCEPT_KEYWORDS = new DetailsLevel("acceptKeywords", 850);

    public static final Level REJECT_MEMBER_SIG = new DetailsLevel("rejectByAttribute.memberSig", 899);
    public static final Level REJECT_REQUIRED_RESOURCE = new DetailsLevel("rejectByAttribute.RequiredResource", 899);
    public static final Level REJECT_TARGET_SPEC = new DetailsLevel("rejectByAttribute.TargetSpec", 899);
    public static final Level REJECT_TARGET_SPEC_ELEM = new DetailsLevel("rejectByAttribute.TargetSpecElem", 899);
    public static final Level REJECT_TESTED_CLASS = new DetailsLevel("rejectByAttribute.TestedClass", 899);
    public static final Level REJECT_TESTED_PACKAGE = new DetailsLevel("rejectByAttribute.TestedPackage", 899);
    public static final Level REJECT_ASSERTION_REF = new DetailsLevel("rejectByAttribute.AssertionRef", 899);
    public static final Level REJECT_KEYWORDS = new DetailsLevel("rejectByAttribute.Keywords", 899);


    public static final Level UTD_VERIFICATION = new DetailsLevel("!!! VERIFICATION ERROR", 999);

    public static final Level DETAILS = new DetailsLevel("ACCEPTANCE_DETAILS", 860);

    protected Logger logger = Logger.getLogger(LOGGING_SYBSYSTEM_NAME);


    public StatusLogger(String name) {
        logger.log(Level.INFO, "LOGGING_LEVEL=" + name);
        Level level = DETAILS;
        if (name != null) {
            name = name.trim();
            if (name.equals("STATUS")) {
                level = DETAILS;
            } else if (name.equals("WARNING")) {
                level = Level.WARNING;
            } else if (name.equals("NOTHING")) {
                level = Level.SEVERE;
            } else if (name.equals("ALL")) {
                level = Level.ALL;
            }
        }   
        logger.log(Level.INFO, "logger.setLevel(" + level + ")");
        logger.setLevel(level);
    }

    public static class DetailsLevel extends Level {
        public DetailsLevel(String name, int level) {
            super(name, level);
        }
    }

    private static MessageFormat singleFormat =
        new MessageFormat("{0} ''{1}''");
    private static MessageFormat pairFormat =
        new MessageFormat("{0} ''{1}'',''{2}''");
    private static MessageFormat tripleFormat =
        new MessageFormat("{0} ''{1}'',''{2}'',''{3}''");
    private static MessageFormat quadroFormat =
        new MessageFormat("{0} ''{1}'',''{2}'',''{3}'',''{4}''");

    protected void reportMemberSigAcceptStatus(String log_id, boolean status,
                                               String testedPack,
                                               String testedClass, String memberSig) {
        logger.log((status ? ACCEPT_MEMBER_SIG : REJECT_MEMBER_SIG),
                   tripleFormat.format(new Object[] {
                           log_id, testedPack, testedClass, memberSig
                   }));
    }

    protected void reportTestedClassAcceptStatus(String log_id, boolean status,
                                                 String testedPack, String testedClass) {
        logger.log((status ? ACCEPT_TESTED_CLASS : REJECT_TESTED_CLASS),
                   pairFormat.format(new Object[] {
                                                log_id, testedPack, testedClass
                                            }));
    }

    protected void reportTestedPackageAcceptStatus(String log_id, boolean status,
                                                   String testedPack) {
        logger.log((status ? ACCEPT_TESTED_PACKAGE : REJECT_TESTED_PACKAGE),
                   format(singleFormat, log_id, testedPack));
    }


    protected void reportAssertionRefAcceptStatus(String log_id, boolean status, String ref) {
        logger.log((status ? ACCEPT_ASSERTION_REF : REJECT_ASSERTION_REF),
                   format(singleFormat, log_id, ref));
    }

    protected void reportTargetSpecAcceptStatus(String log_id, boolean status, TargetSpec spec) {
        logger.log((status ? ACCEPT_TARGET_SPEC : REJECT_TARGET_SPEC),
                    format(pairFormat, log_id, spec.getID(), spec.getVersion()));
    }

    protected void reportTargetSpecElemAcceptStatus(String log_id, boolean status,
                                                    TargetSpec spec, String name,
                                                    String value) {
        logger.log((status ? ACCEPT_TARGET_SPEC_ELEM : REJECT_TARGET_SPEC_ELEM),
                    quadroFormat.format(new Object[] {
                            log_id, spec.getID(), spec.getVersion(), name, value
                    }));
    }

    protected void reportKeywordsAcceptStatus(String log_id, boolean status, ArrayList keys) {
        logger.log((status ? ACCEPT_KEYWORDS : REJECT_KEYWORDS),
                   format(singleFormat, log_id, keys));
    }

    protected void reportRequiredResourceAcceptStatus(String log_id, boolean status,
                                                      String name, String value) {
        logger.log((status ? ACCEPT_REQUIRED_RESOURCE : REJECT_REQUIRED_RESOURCE),
                   format(pairFormat, log_id, name, value));
    }

    public void reportVerificationError(String log_id, Exception e) {
        e.printStackTrace();
        logger.log(UTD_VERIFICATION, log_id, e);
    }

    protected static String format(MessageFormat format, String log_id, Object arg1) {
        return format.format(new Object[] {log_id, arg1});
    }

    protected static String format(MessageFormat format, String log_id, Object arg1, Object arg2) {
        return format.format(new Object[] {log_id, arg1, arg2});
    }
}
