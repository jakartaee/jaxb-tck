/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
package com.sun.jaxb_tck.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.sun.interview.Interview;

public class JtiGen extends Interview {

    private static final String USAGE =
        "Usage: java com.sun.jaxb_tck.util.JtiGen -single -work dir "
        + "-testsuite dir -tests testURLs "
        + "\n"
        + "java com.sun.jaxb_tck.util.JtiGen -multi -work dir "
        + "-testsuite dir -tests testURLs -jvm file -xsd_compiler file "
        + "-jaxb JAXB_CLASSES -otherEnv name=value -tests testURLs ";

    private final Map<String, String> data;

    private static class InterviewStub extends Interview {
        public InterviewStub() {
            super("interview_stub");
        }
    }

    public JtiGen(Map<String, String> settings) {
        super(new InterviewStub(), "jti_gen");
        data = new TreeMap<>();

        data.put("jck.concurrency.concurrency", "1");
        data.put("jck.env.jaxb.xsd_compiler.defaultOperationMode", "Yes");
        data.put("jck.env.jaxb.testExecute.cmdAsFile", "java");
        data.put("jck.env.jaxb.testExecute.otherEnvVars", "");
        data.put("jck.env.testPlatform.local", "Yes");
        data.put("jck.excludeList.latestAutoCheck", "No");
        data.put("jck.excludeList.latestAutoCheckInterval", "7");
        data.put("jck.excludeList.latestAutoCheckMode", "everyXDays");
        data.put("jck.excludeList.needExcludeList", "No");
        data.put("jck.keywords.keywords.mode", "expr");
        data.put("jck.keywords.needKeywords", "No");
        data.put("jck.priorStatus.needStatus", "No");
        data.put("jck.priorStatus.status", "");
        data.put("jck.tests.needTests", "No");
        data.put("jck.tests.tests", "");
        data.put("jck.tests.treeOrFile", "tree");
        data.put("jck.timeout.timeout", "1");
        // the following answer means that schemagen tests will be run
        data.put("jck.env.jaxb.schemagen.skipJ2XOptional", "Yes");

        if (isModeSame) {
            data.put("jck.env.jaxb.xsd_compiler.run.compilerWrapperClass", "com.sun.jaxb_tck.lib.SchemaCompiler");
            data.put("jck.env.jaxb.agent.agentPassivePort", "");
            data.put("jck.env.jaxb.agent.agentType", "active");
            data.put("jck.env.description", "same jvm");
            data.put("jck.env.envName", "same_jvm");
            data.put("jck.env.testPlatform.multiJVM", "No");
            data.put("jck.env.jaxb.schemagen.run.schemagenWrapperClass", "com.sun.jaxb_tck.lib.SchemaGen");
        } else {
            data.put("jck.env.jaxb.xsd_compiler.testCompile.xjcCmd", "/bin/sh linux/bin/xjc.sh");
            data.put("jck.env.description", "multi jvm");
            data.put("jck.env.envName", "multi_jvm");
            data.put("jck.env.testPlatform.multiJVM", "Yes");
            data.put("jck.env.jaxb.testExecute.otherOpts", "");
            data.put("jck.env.jaxb.schemagen.run.jxcCmd", "/bin/sh linux/bin/schemagen.sh");
            data.put("jck.env.jaxb.classes.needJaxbClasses", "Yes");
            data.put("jck.env.jaxb.classes.jaxbClasses", "");
        }
        data.putAll(settings);
    }

    public Map<String, String> generate() {
        super.save(data);
        return data;
    }

    public static boolean isModeSame = false;

    public static void main(String[] args) {
        try {
            JtiGen jtiGen = new JtiGen(getData(args));
            Map<String, String> data = jtiGen.generate();
            print(data);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(USAGE);
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Map<String, String> getData(String[] args) {
        Map<String, String> data = new HashMap<>();
        int n = args.length;

        if (n == 0) {
            throw new IllegalArgumentException("Options not specified.");
        }

        for (int i = 0; i < n; i++) {
            if ("-single".equals(args[i])) {
                isModeSame = true;
            } else if ("-multi".equals(args[i])) {
                isModeSame = false;
            } else if ("-work".equals(args[i])) {
                i += 1;
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("Work directory not specified.");
                }
                data.put("WORKDIR", args[i]);
            } else if ("-testsuite".equals(args[i])) {
                i += 1;
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("testsuite not specified.");
                }
                data.put("TESTSUITE", args[i]);
            } else if ("-tests".equals(args[i])) {
                i += 1;
                StringBuilder buffer = new StringBuilder();
                if (i < n && args[i].charAt(0) != '-') {
                    buffer.append(args[i]);
                    for (i += 1; i < n && args[i].charAt(0) != '-'; i++) {
                        buffer.append(" ").append(args[i]);
                    }
                }
                --i;
                data.put("jck.tests.tests", buffer.toString());
            } else if ("-ri_java_home".equals(args[i])) {
                i += 1;
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("JVM not specified");
                }
                data.put("jck.env.jaxb.testExecute.cmdAsFile", args[i] + "/bin/java");
            } else if ("-xsd_compiler".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    throw new IllegalArgumentException("'-xsd_compiler' should not be used in singleJVM mode");
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("xsd_compiler command is not specified");
                }
                data.put("jck.env.jaxb.xsd_compiler.testCompile.xjcCmd", args[i]);
            } else if ("-schemagen".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    throw new IllegalArgumentException("'-schemagen' should not be used in singleJVM mode");
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("schemagen command is not specified");
                }
                data.put("jck.env.jaxb.schemagen.run.jxcCmd", args[i]);
            } else if ("-jaxb".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    throw new IllegalArgumentException("'-jaxb' should not be used in singleJVM mode");
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("JAXB classes not specified");
                }
                StringBuilder buffer = new StringBuilder();
                buffer.append(args[i]);
                for (i += 1; i < n && args[i].charAt(0) != '-'; i++) {
                    buffer.append(" ").append(args[i]);
                }
                --i;

                data.put("jck.env.jaxb.classes.needJaxbClasses", "Yes");
                data.put("jck.env.jaxb.classes.jaxbClasses", buffer.toString());
            } else if ("-otherEnv".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    throw new IllegalArgumentException("'-otherEnv' should not be used in singleJVM mode");
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("otherEnv not specified");
                }
                StringBuilder buffer = new StringBuilder();
                buffer.append(args[i]);
                for (i += 1; i < n && args[i].charAt(0) != '-'; i++) {
                    buffer.append(" ").append(args[i]);
                }
                --i;
                data.put("jck.env.jaxb.testExecute.otherEnvVars", buffer.toString());
            } else {
                throw new IllegalArgumentException("Unknown option: " + args[i]);
            }
        }

        return data;
    }

    private static void print(Map<String, String> data) {
        System.out.println("#JavaTest Configuration Interview");
        System.out.print("#");
        System.out.println(Calendar.getInstance().getTime());
        for (String key : data.keySet()) {
            String value = data.get(key);
            System.out.print(key);
            System.out.print("=");
            if ("jck.env.jaxb.testExecute.otherEnvVars".equals(key)) {
                StringBuilder str = new StringBuilder();
                StringTokenizer st = new StringTokenizer(value, "=", false);
                if (st.hasMoreTokens()) {
                    str.append(st.nextToken());
                }
                while (st.hasMoreTokens()) {
                    str.append("\\=");
                    str.append(st.nextToken());
                }
                System.out.println(str);
            } else {
                System.out.println(value);
            }
        }
    }
}
