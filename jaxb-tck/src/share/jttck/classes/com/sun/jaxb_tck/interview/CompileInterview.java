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
import com.sun.jaxb_tck.lib.SchemaCompiler;
import java.io.File;
import java.util.Map;

import com.sun.interview.ChoiceQuestion;
import com.sun.interview.ErrorQuestion;
import com.sun.interview.FileQuestion;
import com.sun.interview.FinalQuestion;
import com.sun.interview.Interview;
import com.sun.interview.NullQuestion;
import com.sun.interview.Question;
import com.sun.interview.StringQuestion;
import com.sun.interview.YesNoQuestion;
import javasoft.sqe.javatest.lib.ProcessCommand;

/**
 * Interview for XSD compilation.
 * @version 1.36
 */

@SuppressWarnings("unchecked")
class CompileInterview extends MInterview {

    public CompileInterview(Interview parent, InterviewData id) {
        super(parent, "env_compile", id);
        setFirstQuestion(q_intro);
    }

    public Question q_intro = new NullQuestion(this, "intro") {
        public Question getNext() {
            return q_defaultOperMode;
        }
    };

    Question q = new FinalQuestion(this);

    Interview xsdCompilerInterview = new XSDCompilerInterview(this, id);

    public ChoiceQuestion q_defaultOperMode = new YesNoQuestion(this,
            "defaultOperationMode") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            } else if (YES.equals(getValue())) {
                id.xjc_default_operation_mode = true;
            } else {
                id.xjc_default_operation_mode = false;
            }
            return callInterview(xsdCompilerInterview, q);
        }

        public void export(Map data) {
            if (id.xjc_default_operation_mode == true) {
                data.put("xjc_default_operation_mode", "true");
            }
        }
    };

    public void export(Map data) {
        xsdCompilerInterview.export(data);
    }
}

/**
 * Interview for XSD compilation.
 */
@SuppressWarnings("unchecked")
class XSDCompilerInterview extends MInterview {

    Interview xjcCmdLineInterview = new XJCCmdLineInterview(this, id);

    Interview xjcClassInterview = new XJCClassInterview(this, id);

    Question q_final = new FinalQuestion(this);

    public Question q_xjcNonLocalOtherQuestions = new NullQuestion(this,
            "hidden_xjcNonLocalOtherQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_xjcCmdLine = callInterview(xjcCmdLineInterview,q_final);
            return q_xjcCmdLine;
        }
    };

    public Question q_xjcNonLocalSameQuestions = new NullQuestion(this,
            "hidden_xjcNonLocalSameQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_xjcClass = callInterview(xjcClassInterview, q_final);
            return q_xjcClass;
        }
    };

    public Question q_xjcLocalOtherQuestions = new NullQuestion(this,
            "hidden_xjcLocalOtherQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_xjcCmdLine = callInterview(xjcCmdLineInterview,q_final);
            return q_xjcCmdLine;
        }
    };

    public Question q_xjcLocalSameQuestions = new NullQuestion(this,
            "hidden_xjcLocalSameQuestion") {
        public boolean isHidden() {
            return true;
        }

        public Question getNext() {
            Question q_xjcClass = callInterview(xjcClassInterview, q_final);
            return q_xjcClass;
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
            return q_defaultOperMode;
        }
    };
    public ChoiceQuestion q_defaultOperMode = new YesNoQuestion(this,
            "defaultOperationMode") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            } else if (YES.equals(getValue())) {
                id.xjc_default_operation_mode = true;
            } else {
                id.xjc_default_operation_mode = false;
            }
            return q_skip;
        }

        public void export(Map data) {
            if (id.xjc_default_operation_mode == true) {
                data.put("xjc_default_operation_mode", "true");
            }
        }
    };
    public Question q_skip = new YesNoQuestion(this, "skipValidationOptional") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            }
            return q_first;
        }

        public void export(Map data) {
            data.put("toTestOptionalValidation", Boolean.toString(YES
                    .equals(getValue())));
        }

    };



    public void export(Map data) {
        final String CLASSPATH_MAP =
            "CLASSPATH=\"$testSuiteRootDir${xjc_map_file_separator}classes" +
            "${xjc_map_path_separator}${jaxbClasses}" +
            "${xjc_map_path_separator}$testSuiteRootDir${xjc_map_file_separator}lib${xjc_map_file_separator}javatest.jar\" ";

        final String CLASSPATH =
            "CLASSPATH=\"$testSuiteRootDir$/classes$" +
            ":${jaxbClasses}$" +
            ":$testSuiteRootDir$/lib$/javatest.jar\" ";
        
        if (id.xjc_local) {
            data.put("xjc_file_separator", File.separator);
            data.put("xjc_path_separator", File.pathSeparator);
        }

        String command;

        if (id.xjc_sameOther == JAXBTCKParameters.MODE_SAME) {
            if (id.xjc_local) {
                command =  "$xjc_agent_command "
                            + "$xjc_agent_passive_host "
                            + "$xjc_agent_passive_port "
                            + JaxbCommand.class.getName()
                            + " -xjc "
                            + "$xjc_run_class "
                            + "- $empty_output_placeholder "
                            + "-p $package -d $outDir $schema";
            } else {
                command =  "$xjc_agent_command "
                            + "$xjc_agent_passive_host "
                            + "$xjc_agent_passive_port "
                            + "$xjc_use_map_file "
                            + JaxbCommand.class.getName()
                            + " -xjc "
                            + "$xjc_run_class "
                            + "- $empty_output_placeholder "
                            + "-p $package -d $outDir $schema";
            }
        } else {
            if (id.xjc_local) {
                command = ProcessCommand.class.getName()
                            + " $jaxb_env_vars "
                            + CLASSPATH
                            + " $xjc_cmd $empty_output_placeholder "
                            + "-p $package "
                            + "-d $outDir "
                            + "$schema";
            } else {
                command = "$xjc_agent_command "
                            + "$xjc_agent_passive_host "
                            + "$xjc_agent_passive_port "
                            + "$xjc_use_map_file "
                            + ProcessCommand.class.getName()
                            + " $jaxb_env_vars "
                            + CLASSPATH_MAP
                            + " $xjc_cmd $empty_output_placeholder "
                            + "-p $package "
                            + "-d $outDir "
                            + "$schema";
            }
        }
        data.put("command.compile.xsd", command );
    }

    public XSDCompilerInterview(Interview parent, InterviewData id) {
        super(parent, "xsd_compiler", id);
        setFirstQuestion(q_intro);
    }
}

/**
 * ****************************** SUB INTERVIEWS ******************************
 */

/**
 * Interview for XJC_CMD_LINE (otherJVM mode, path to xjc script to compile .xsd).
 */
@SuppressWarnings("unchecked")
class XJCCmdLineInterview extends MInterview {

    public XJCCmdLineInterview(Interview parent, InterviewData id) {
        super(parent, "testCompile", id);
        setFirstQuestion(q_xjcCmd);
    }

    Question q_final = new FinalQuestion(this);

    public final String[] xjcCmdSuggestions = { "",
                                                  "/bin/ksh solaris/bin/xjc.sh",
                                                  "/bin/ksh solaris/bin/xjc9.sh",
                                                  "/bin/sh linux/bin/xjc.sh",
                                                  "/bin/sh linux/bin/xjc9.sh",
                                                  "/bin/sh macos/bin/xjc.sh",
                                                  "/bin/sh macos/bin/xjc9.sh",
                                                  "win32\\bin\\xjc.bat",
                                                  "win32\\bin\\xjc9.bat" };

    public Question q_xjcCmd = new StringQuestion(this, "xjcCmd") {
        {
            setSuggestions(xjcCmdSuggestions);
        }

        public Question getNext() {
            if (getValue() == null || getValue().length() == 0) {
                return null;
            }
            return q_final;
        }

        public void export(Map data) {
            data.put("xjc_cmd", getValue());
        }
    };
}

/**
 * Interview for XJC_CLASS (xjc run class questions to run in sameJVM mode).
 */
@SuppressWarnings("unchecked")
class XJCClassInterview extends MInterview {

    public XJCClassInterview(Interview parent, InterviewData id) {
        super(parent, "run", id);
        setFirstQuestion(q_xjcWrapperClass);
    }

    Question q_final = new FinalQuestion(this);

    public String[] xjcWrapperClassSuggestions = { "", SchemaCompiler.class.getName() };

    public Question q_xjcWrapperClass = new StringQuestion(this,
                                            "compilerWrapperClass") {
        {
            setSuggestions(xjcWrapperClassSuggestions);
        }

        public Question getNext() {
            if (  getValue() == null      ||
                  getValue().length() == 0 ||
                  ! Util.isValidClassName(getValue()) ) {
                return null;
            } else {
                return q_final;
            }
        }

        public void export(Map data) {
            data.put("xjc_run_class", getValue());
        }
    };

}

abstract class ExecutorQuestion extends FileQuestion {

    public BadFileQuestion qBadExecutor;

    public String envKey;

    public ExecutorQuestion(Interview interview, String tag, String envKey) {
        super(interview, tag);
        qBadExecutor = new BadFileQuestion(interview, "bad." + tag);
        this.envKey = envKey;
    }

    public Question errorQuestion() {
        if (getValue() == null || getValue().getPath().length() == 0) {
            return null;
        } else {
            qBadExecutor.setFileName(getValue().getPath());
            return qBadExecutor;
        }
    }

    public boolean isValueValid() {
        return ((getValue() != null) && (getValue().getPath().length() != 0)
                && getValue().isFile() && getValue().canRead());
    }
}

class BadFileQuestion extends ErrorQuestion {

    public String fileName = null;

    public BadFileQuestion(Interview interview, String tag) {
        super(interview, tag);
    }

    public void setFileName(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException("file name is null.");

        this.fileName = fileName;
    }

    public Object[] getTextArgs() {
        return new Object[] { fileName };
    }
}
