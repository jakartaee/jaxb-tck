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
package com.sun.jaxb_tck.interview;

import java.util.HashMap;
import java.util.StringTokenizer;

import com.sun.interview.Interview;
import com.sun.interview.NullQuestion;
import com.sun.interview.Question;
import com.sun.javatest.TestEnvironment;
import com.sun.javatest.Parameters.EnvParameters;
import com.sun.javatest.interview.BasicInterviewParameters;

/**
 * @version 1.17
 */
public class JAXBTCKParameters extends BasicInterviewParameters implements
        EnvParameters {
    public static final int MODE_OTHER = 0;

    public static final int MODE_SAME = 1;

    public InterviewData id = new InterviewData();

    StandardInterview standardInterview = new StandardInterview(this, id);

    TestPlatformInterview testPlatformInterview = new TestPlatformInterview(
            standardInterview, id);

    Interview jaxbInterview = new JaxbInterview(standardInterview, id);

    Interview compileInterview = new XSDCompilerInterview(jaxbInterview, id);

    Interview schemagenInterview = new SchemagenInterview(jaxbInterview, id);

    public JAXBTCKParameters() throws Fault {
        super("jck");
        setResourceBundle("tckwiz");
        setHelpSet("moreInfo/tckwiz");
    }

    public TestEnvironment getEnv() {
        try {
            HashMap envProps = new HashMap();
            export(envProps);
            compileInterview.export(envProps);
            schemagenInterview.export(envProps);
            standardInterview.export(envProps);
            return new TestEnvironment(standardInterview.getName(), envProps,
                    "JAXB TCK interview");
        } catch (TestEnvironment.Fault e) {
            throw new Error("Unexpected error: " + e);
        }
    }

    public EnvParameters getEnvParameters() {
        return this;
    }

    public Question getEnvFirstQuestion() {
        return callInterview(standardInterview, callInterview(
                testPlatformInterview,
                callInterview(jaxbInterview, callInterview(compileInterview,
                        callInterview(schemagenInterview,
                                getEnvSuccessorQuestion())))));
    }

    public InterviewData getInterviewData() {
        return id == null ? new InterviewData() : id;
    }

    public void setInterviewData(InterviewData id) {
        this.id = id;
    }

    protected Question getEnvSuccessorQuestion() {
        return qParameters;
    }

    private Question qParameters = new NullQuestion(this, "parameters") {
        protected Question getNext() {
            return getTestsFirstQuestion();
        }
    };
}

class MInterview extends Interview {

    public static final String AGENT_ACTIVE = "active";

    public static final String AGENT_PASSIVE = "passive";

    public String[] agentModeSuggestions = { null, AGENT_ACTIVE, AGENT_PASSIVE };

    public InterviewData id;

    public void setInterviewData(InterviewData id) {
        this.id = id;
    }

    public InterviewData getInterviewData() {
        return id;
    }

    public MInterview(Interview i, InterviewData id) {
        super(i, "");
        this.id = id;
    }

    public MInterview(Interview i, String tag, InterviewData id) {
        super(i, tag);
        this.id = id;
    }

    public MInterview(Interview i, String tag) {
        super(i, tag);
    }

    public MInterview(String tag) {
        super(tag);
    }
}


class InterviewData {
    int xjc_sameOther = JAXBTCKParameters.MODE_OTHER;

    boolean xjc_local = true;

    boolean xjc_agentActive = false;

    boolean xjc_cp_env = false;

    boolean xjc_default_operation_mode = true;

    int exec_sameOther = JAXBTCKParameters.MODE_OTHER;

    boolean exec_local = true;

    boolean exec_agentActive = false;

    public boolean jxc_cp_env = false;
}

class Util {
    public static String replaceAll(String src, char replaced,
            String replacement) {
        StringBuffer result = new StringBuffer(src);
        for (int index = 0; index < result.length(); ++index) {
            if (result.charAt(index) == replaced) {
                result.replace(index, index + 1, replacement);
                index += replacement.length() - 1;
            }
        }
        return result.toString();
    }

    public static boolean isValidClassName(String className) {
        if (className.charAt(0) == '.'
                || className.charAt(className.length() - 1) == '.') {
            return false;
        }
        for (int i = 0; i < className.length(); ++i) {
            if (className.charAt(i) == '.' && className.charAt(i + 1) == '.') {
                return false;
            }
        }
        StringTokenizer st = new StringTokenizer(className, ".", false);
        while (st.hasMoreTokens()) {
            if (isValidIdentifier(st.nextToken()) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidMethodName(String methodName) {
        return isValidIdentifier(methodName);
    }

    public static boolean isValidIdentifier(String identifier) {
        if ("".equals(identifier)) {
            return false;
        }
        if (isJavaKeyword(identifier)) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(identifier.charAt(0))) {
            return false;
        }
        for (int i = 1; i < identifier.length(); ++i) {
            if (!Character.isJavaIdentifierStart(identifier.charAt(i))
                    && !Character.isJavaIdentifierPart(identifier.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJavaKeyword(String identifier) {
        if ("abstract".equals(identifier) || "boolean".equals(identifier)
                || "break".equals(identifier) || "byte".equals(identifier)
                || "case".equals(identifier) || "catch".equals(identifier)
                || "char".equals(identifier) || "class".equals(identifier)
                || "const".equals(identifier) || "continue".equals(identifier)
                || "default".equals(identifier) || "do".equals(identifier)
                || "double".equals(identifier) || "else".equals(identifier)
                || "extends".equals(identifier) || "final".equals(identifier)
                || "finally".equals(identifier) || "float".equals(identifier)
                || "for".equals(identifier) || "goto".equals(identifier)
                || "if".equals(identifier) || "implements".equals(identifier)
                || "import".equals(identifier)
                || "instanceof".equals(identifier) || "int".equals(identifier)
                || "interface".equals(identifier) || "long".equals(identifier)
                || "native".equals(identifier) || "new".equals(identifier)
                || "package".equals(identifier) || "private".equals(identifier)
                || "protected".equals(identifier)
                || "public".equals(identifier) || "return".equals(identifier)
                || "short".equals(identifier) || "static".equals(identifier)
                || "strictfp".equals(identifier) || "super".equals(identifier)
                || "switch".equals(identifier)
                || "synchronized".equals(identifier)
                || "this".equals(identifier) || "throw".equals(identifier)
                || "throws".equals(identifier)
                || "transient".equals(identifier) || "try".equals(identifier)
                || "void".equals(identifier) || "volatile".equals(identifier)
                || "while".equals(identifier)) {
            return true;
        }
        return false;
    }

    public static boolean isValidTemplate(String templ, char placeHolder) {
        int indx = 0;
        if (templ == null || templ.length() == 0
                || (indx = templ.indexOf((int) placeHolder)) == -1) {
            return false;
        }
        if (indx < templ.length() - 1
                && templ.indexOf((int) placeHolder, indx + 1) != -1) {
            return false;
        }
        if ("".equals(Util.replaceAll(templ, placeHolder, "").trim())) {
            return false;
        }
        return true;
    }
}
