/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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

import com.sun.jaxb_tck.lib.JaxbCommand;
import com.sun.jaxb_tck.lib.SchemaGen;
import java.util.Map;

import com.sun.interview.FinalQuestion;
import com.sun.interview.Interview;
import com.sun.interview.NullQuestion;
import com.sun.interview.Question;
import com.sun.interview.StringQuestion;
import com.sun.interview.YesNoQuestion;
import javasoft.sqe.javatest.lib.ProcessCommand;

/**
 * Interview for schema generation.
 *
 * @version 1.15
 */

class SchemagenInterview extends MInterview {

    Interview jxcCmdLineInterview = new JXCCmdLineInterview(this, id);

    Interview jxcClassInterview = new JXCClassInterview(this, id);

    Question q_final = new FinalQuestion(this);

    public Question q_xjcNonLocalOtherQuestions = new NullQuestion(this,
            "hidden_xjcNonLocalOtherQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_jxcCmdLine = callInterview(jxcCmdLineInterview, q_final);
            return q_jxcCmdLine;
        }
    };

    public Question q_xjcNonLocalSameQuestions = new NullQuestion(this,
            "hidden_xjcNonLocalSameQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_jxcClass = callInterview(jxcClassInterview, q_final);
            return q_jxcClass;
        }
    };

    public Question q_xjcLocalOtherQuestions = new NullQuestion(this,
            "hidden_xjcLocalOtherQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_jxcCmdLine = callInterview(jxcCmdLineInterview, q_final);
            return q_jxcCmdLine;
        }
    };

    public Question q_xjcLocalSameQuestions = new NullQuestion(this,
            "hidden_xjcLocalSameQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_jxcClass = callInterview(jxcClassInterview, q_final);
            return q_jxcClass;
        }
    };

    public Question q_first = new NullQuestion(this, "hidden_first_question") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            if (id.xjc_sameOther == JAXBTCKParameters.MODE_SAME) {
                if (id.xjc_local) {
                    return q_xjcLocalSameQuestions;
                } else {
                    return q_xjcNonLocalSameQuestions;
                }
            } else {
                if (id.xjc_local) {
                    return q_xjcLocalOtherQuestions;
                } else {
                    return q_xjcNonLocalOtherQuestions;
                }
            }
        }
    };

    public Question q_intro = new NullQuestion(this, "intro") {
        public Question getNext() {
            return q_skip;
        }
    };

    public Question q_skip = new YesNoQuestion(this, "skipJ2XOptional") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            }
            if (YES.equals(getValue())) {
                return q_first;
            }
            return q_final;
        }

        public void export(Map data) {
            data.put("toTestOptionalSchemagen", Boolean.toString(YES
                    .equals(getValue())));
        }

    };

    public SchemagenInterview(Interview parent, InterviewData id) {
        super(parent, "schemagen", id);
        setFirstQuestion(q_intro);
    }

    public void export(Map data) {
        final String CLASSPATH_MAP =
            "CLASSPATH=\"$testSuiteRootDir${xjc_map_file_separator}classes" +
            "${xjc_map_path_separator}${jaxbClasses}" +
            "${xjc_map_path_separator}$testSuiteRootDir${xjc_map_file_separator}lib${xjc_map_file_separator}javatest.jar\" ";

        final String CLASSPATH =
            "CLASSPATH=\"$testSuiteRootDir$/classes$" +
            ":${jaxbClasses}$" +
            ":$testSuiteRootDir$/lib$/javatest.jar\" ";

        String command;
        if (id.xjc_sameOther == JAXBTCKParameters.MODE_SAME) {
            if (id.xjc_local) {
                command =
                    "$xjc_agent_command " +
                    "$xjc_agent_passive_host " +
                    "$xjc_agent_passive_port "  +
                    JaxbCommand.class.getName() +
                    " -jxc " +
                    "$jxc_run_class " +
                    "- " +
                    "-d $outDir " +
                    "$java_source_files";
            } else {
                command =
                    "$xjc_agent_command " +
                    "$xjc_agent_passive_host " +
                    "$xjc_agent_passive_port " +
                    "$xjc_use_map_file " +
                    JaxbCommand.class.getName() +
                    " -jxc " +
                    "$jxc_run_class " +
                    "- " +
                    "-d $outDir " +
                    "$java_source_files";
            }
        } else {
            if (id.xjc_local) {
                command =
                    ProcessCommand.class.getName() +
                    " $jaxb_env_vars " +
                    CLASSPATH +
                    "$jxc_cmd " +
                    "-d $outDir " +
                    "$java_source_files";
            } else {
                command =
                    "$xjc_agent_command " +
                    "$xjc_agent_passive_host " +
                    "$xjc_agent_passive_port " +
                    "$xjc_use_map_file " +
                    ProcessCommand.class.getName() +
                    " $jaxb_env_vars " +
                    CLASSPATH_MAP +
                    "$jxc_cmd " +
                    "-d $outDir " +
                    "$java_source_files";
            }
        }
        data.put("command.schemagen.jxc", command);
    }
}

class JXCClassInterview extends MInterview {

    public JXCClassInterview(Interview parent, InterviewData id) {
        super(parent, "run", id);
        setFirstQuestion(q_jxcWrapperClass);
    }

    Question q_final = new FinalQuestion(this);

    public String[] jxcWrapperClassSuggestions = { "",
            SchemaGen.class.getName() };

    public Question q_jxcWrapperClass = new StringQuestion(this,
            "schemagenWrapperClass") {
        {
            setSuggestions(jxcWrapperClassSuggestions);
        }

        public Question getNext() {
            if (getValue() == null || getValue().length() == 0
                    || !Util.isValidClassName(getValue())) {
                return null;
            } else {
                return q_final;
            }
        }

        public void export(Map data) {
            data.put("jxc_run_class", getValue());
        }
    };
}

class JXCCmdLineInterview extends MInterview {

    public JXCCmdLineInterview(Interview parent, InterviewData id) {
        super(parent, "run", id);
        setFirstQuestion(q_jxcCmd);
    }

    Question q_final = new FinalQuestion(this);

    public final String[] jxcCmdSuggestions = { "",
            "/bin/ksh solaris/bin/schemagen.sh",
            "/bin/ksh solaris/bin/schemagen9.sh",
            "/bin/sh linux/bin/schemagen.sh",
            "/bin/sh linux/bin/schemagen9.sh",
            "/bin/sh macos/bin/schemagen.sh",
            "/bin/sh macos/bin/schemagen9.sh",
            "win32\\bin\\schemagen.bat",
            "win32\\bin\\schemagen9.bat" };

    public Question q_jxcCmd = new StringQuestion(this, "jxcCmd") {
        {
            setSuggestions(jxcCmdSuggestions);
        }

        public Question getNext() {
            if (getValue() == null || getValue().length() == 0) {
                return null;
            }
            return q_final;
        }

        public void export(Map data) {
            data.put("jxc_cmd", getValue());
        }
    };
}
