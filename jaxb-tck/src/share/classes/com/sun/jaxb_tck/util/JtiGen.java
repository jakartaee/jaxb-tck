/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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
package com.sun.jaxb_tck.util;

import com.sun.interview.Interview;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Calendar;
import java.util.StringTokenizer;

public class JtiGen extends Interview {

    private static final String USAGE = 
        "Usage: java com.sun.jaxp_tck.util.JtiGen -single -work dir "
        + "-testsuite dir -tests testURLs "
        + "\n"
        + "java com.sun.jaxp_tck.util.JtiGen -multi -work dir "
        + "-testsuite dir -tests testURLs -jvm file -xsd_compiler file "
        + "-jaxb JAXB_CLASSES -otherEnv name=value -tests testURLs ";

    private HashMap data;

    private static class InterviewStub extends Interview {
        public InterviewStub() {
            super("interview_stub");
        }
    }

    public JtiGen(HashMap data) throws Exception {
        super(new InterviewStub(), "jti_gen");
        this.data = new HashMap();

        this.data.put("jck.concurrency.concurrency", "1");
        this.data.put("jck.env.jaxb.xsd_compiler.defaultOperationMode", "Yes");
        this.data.put("jck.env.jaxb.testExecute.otherEnvVars", "");
        this.data.put("jck.env.testPlatform.local", "Yes");
        this.data.put("jck.excludeList.latestAutoCheck", "No");
        this.data.put("jck.excludeList.latestAutoCheckInterval", "7");
        this.data.put("jck.excludeList.latestAutoCheckMode", "everyXDays");
        this.data.put("jck.excludeList.needExcludeList", "No");
        this.data.put("jck.keywords.keywords.mode", "expr");
        this.data.put("jck.keywords.needKeywords", "No");
        this.data.put("jck.priorStatus.needStatus", "No");
        this.data.put("jck.priorStatus.status", "");
        this.data.put("jck.tests.needTests", "No");
        this.data.put("jck.tests.tests", "");
        this.data.put("jck.tests.treeOrFile", "tree");
        this.data.put("jck.timeout.timeout", "1");
        // the following answer means that schemagen tests will be run
        this.data.put("jck.env.jaxb.schemagen.skipJ2XOptional", "Yes");

        if (isModeSame == true){
            this.data.put("jck.env.jaxb.xsd_compiler.run.compilerWrapperClass", "com.sun.jaxb_tck.lib.SchemaCompiler");
            this.data.put("jck.env.jaxb.agent.agentPassivePort", "");
            this.data.put("jck.env.jaxb.agent.agentType", "active");
            this.data.put("jck.env.description", "same jvm");
            this.data.put("jck.env.envName", "same_jvm");
            this.data.put("jck.env.testPlatform.multiJVM", "No");
            this.data.put("jck.env.jaxb.schemagen.run.schemagenWrapperClass", "com.sun.jaxb_tck.lib.SchemaGen");
        } else {
            this.data.put("jck.env.jaxb.xsd_compiler.testCompile.xjcCmd", "/bin/ksh solaris/bin/xjc.sh");
            this.data.put("jck.env.description", "multi jvm");
            this.data.put("jck.env.envName", "multi_jvm");
            this.data.put("jck.env.testPlatform.multiJVM", "Yes");
            this.data.put("jck.env.jaxb.testExecute.otherOpts", "");
            this.data.put("jck.env.jaxb.schemagen.run.jxcCmd", "/bin/ksh solaris/bin/schemagen.sh");
        }        
        this.data.putAll(data);
    }

    public HashMap generate() {
        super.save(data);
        return data;
    }

    public static boolean isModeSame = false;

    public static void main(String[] args) {
        try {
            HashMap data = getData(args);
            JtiGen jtiGen = new JtiGen(data);
            data = jtiGen.generate();
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

    private static HashMap getData(String[] args) {
        HashMap data = new HashMap();
        int n = args.length;
        String msg = "";

        if (n == 0) {
            msg = "Options not specified.";
            throw new IllegalArgumentException(msg);
        }

        for (int i = 0; i < n; i++) {
            if ("-single".equals(args[i])) {
                isModeSame = true;
            } else if ("-multi".equals(args[i])) {
                isModeSame = false;
            } else if ("-work".equals(args[i])) {
                i += 1;
                if (i >= n || args[i].charAt(0) == '-') {
                    msg = "Work directory not specified.";
                    throw new IllegalArgumentException(msg);
                }
                data.put("WORKDIR", args[i]);
            } else if ("-testsuite".equals(args[i])) {
                i += 1;
                if (i >= n || args[i].charAt(0) == '-') {
                    msg = "testsuite not specified.";
                    throw new IllegalArgumentException(msg);
                }
                data.put("TESTSUITE", args[i]);
            } else if ("-tests".equals(args[i])) {
                i += 1;
                StringBuffer buffer = new StringBuffer();
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
                    msg = "'-xsd_compiler' should not be used in singleJVM mode";
                    throw new IllegalArgumentException(msg);
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("xsd_compiler command is not specified");
                }
                data.put("jck.env.jaxb.xsd_compiler.testCompile.xjcCmd", args[i]);
            } else if ("-schemagen".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    msg = "'-schemagen' should not be used in singleJVM mode";
                    throw new IllegalArgumentException(msg);
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    throw new IllegalArgumentException("schemagen command is not specified");
                }
                data.put("jck.env.jaxb.schemagen.run.jxcCmd", args[i]);
            } else if ("-jaxb".equals(args[i])) {
                i += 1;
                if (isModeSame) {
                    msg = "'-jaxb' should not be used in singleJVM mode";
                    throw new IllegalArgumentException(msg);
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    msg = "JAXB classes not specified";
                    throw new IllegalArgumentException(msg);
                }
                StringBuffer buffer = new StringBuffer();
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
                    msg = "'-otherEnv' should not be used in singleJVM mode";
                    throw new IllegalArgumentException(msg);
                }
                if (i >= n || args[i].charAt(0) == '-') {
                    msg = "otherEnv not specified";
                    throw new IllegalArgumentException(msg);
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append(args[i]);
                for (i += 1; i < n && args[i].charAt(0) != '-'; i++) {
                    buffer.append(" ").append(args[i]);
                }
                --i;
                data.put("jck.env.jaxb.testExecute.otherEnvVars", buffer.toString());
            } else {
                msg = "Unknown option: " + args[i];
                throw new IllegalArgumentException(msg);
            }
        }

        return data;
    }

    private static void print(HashMap data) {
        System.out.println("#JavaTest Configuration Interview");
	System.out.print("#");
	System.out.println(Calendar.getInstance().getTime().toString());
        for (Iterator i = data.keySet().iterator(); i.hasNext(); ) {
            Object key = i.next();
            Object value = data.get(key);
            System.out.print(key);
            System.out.print("=");
            if ("jck.env.jaxb.testExecute.otherEnvVars".equals((String)key)){
                String str = "";
                StringTokenizer st = new StringTokenizer((String)value, "=", false);
                if (st.hasMoreTokens()){
                    str = st.nextToken();
                }
                while (st.hasMoreTokens()) {
                    str += "\\=" + st.nextToken();
                }
                System.out.println(str);
            } else {
                System.out.println(value);
            }
        }
    }
}
