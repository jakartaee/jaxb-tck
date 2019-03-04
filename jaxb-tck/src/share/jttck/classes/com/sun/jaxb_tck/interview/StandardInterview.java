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

import com.sun.javatest.agent.ActiveAgentCommand;
import com.sun.javatest.agent.PassiveAgentCommand;
import com.sun.jaxb_tck.lib.ExecTestSameJVMCmd;
import java.util.Map;
import java.io.File;

import com.sun.interview.Interview;
import com.sun.interview.Question;
import com.sun.interview.ChoiceQuestion;
import com.sun.interview.StringQuestion;
import com.sun.interview.IntQuestion;
import com.sun.interview.NullQuestion;
import com.sun.interview.YesNoQuestion;
import com.sun.interview.FinalQuestion;
import com.sun.interview.FileListQuestion;
import com.sun.interview.ExtensionFileFilter;
import com.sun.interview.DirectoryFileFilter;
import com.sun.interview.FileFilter;
import javasoft.sqe.javatest.lib.ExecStdTestOtherJVMCmd;
import javasoft.sqe.javatest.lib.ProcessCommand;

/**
 * Interview for common questions
 *
 * @version 1.13
 */

class StandardInterview extends MInterview {

    Interview testPlatformInterview = new TestPlatformInterview(this, id);

    public StandardInterview(Interview parent, InterviewData id){
        super(parent, "env", id);
        setFirstQuestion(q_envName);
    }

    public void export(Map data) {
        final String CLASSPATH_MAP =
                  "CLASSPATH=$testSuiteRootDir${xjc_map_file_separator}classes" +
                  "${xjc_map_path_separator}${jaxbClasses}" +
                  "${xjc_map_path_separator}$testSuiteRootDir${xjc_map_file_separator}lib${xjc_map_file_separator}javatest.jar" +
                  "${xjc_map_path_separator}$testClassDir" +
                  "${xjc_map_path_separator}$outDir ";

        final String CLASSPATH =
                  "CLASSPATH=$testSuiteRootDir$/classes" +
                  "$:${jaxbClasses}" +
                  "$:$testSuiteRootDir$/lib$/javatest.jar" +
                  "$:$testSuiteRootDir$/lib$/jtlegacy.jar" +
                  "$:$testClassDir" +
                  "$:$outDir ";

        if (id.xjc_local) {
            data.put("java_file_separator", File.separator);
            data.put("java_path_separator", File.pathSeparator);
        }

        String command;

        if (id.xjc_sameOther == JAXBTCKParameters.MODE_SAME){
            if (id.xjc_local == true){
               command =
                        "$xjc_agent_command " +
                        "$xjc_agent_passive_host " +
                        "$xjc_agent_passive_port " +
                        ExecTestSameJVMCmd.class.getName() +
                        " -loadDirs $testSuiteRootDir$/classes$:$outDir " +
                        "$testExecuteClass $testExecuteArgs";
            } else {
               command =
                       "$xjc_agent_command " +
                       "$xjc_agent_passive_host " +
                       "$xjc_agent_passive_port " +
                       "$xjc_use_map_file " +
                       ExecTestSameJVMCmd.class.getName() +
                       " -loadDirs $testSuiteRootDir${xjc_map_file_separator}classes${xjc_map_path_separator}$outDir " +
                       "$testExecuteClass $testExecuteArgs";
            }
        } else {
            if (id.xjc_local == true){
                command =
                        ExecStdTestOtherJVMCmd.class.getName() +
                        " $xjc_env_vars " +
                        CLASSPATH +
                        "$java_path " +
                        "-Djava.security.policy=$testSuiteRootDir$/lib$/tck.policy " +
                        "$java_other_opt " +
                        "$testExecuteClass $testExecuteArgs";

            } else {
                command =
                    "$xjc_agent_command " +
                    "$xjc_agent_passive_host " +
                    "$xjc_agent_passive_port " +
                    "$xjc_use_map_file " +
                    ProcessCommand.class.getName() +
                    " $xjc_env_vars " +
                    CLASSPATH_MAP +
                    "$java_path " +
                    "-Djava.security.policy=$testSuiteRootDir${xjc_map_file_separator}lib${xjc_map_file_separator}tck.policy " +
                    "$java_other_opt " +
                    "$testExecuteClass $testExecuteArgs";
                 }
        }
        data.put("command.execute", command );
    }
   /**
    * Interview for NonLocalOtherJVM mode test execution.
    */
    public void refExecutorNonLocalOtherExport(Map data) {
        refExecutorExport(data);
    }
   /**
    * Interview for LocalOtherJVM mode test execution.
    */
    public void refExecutorLocalOtherExport(Map data) {
        data.put("java_file_separator", File.separator);
        data.put("java_path_separator", File.pathSeparator);
        refExecutorExport(data);
    }
   /**
    * Interview for NonLocalSameJVM mode test execution.
    */
    public void refExecutorNonLocalSameExport(Map data) {
        refExecutorExport(data);
    }

   /**
    * Interview for LocalOtherJVM mode test execution.
    */
    public void refExecutorLocalSameExport(Map data) {
        data.put("java_file_separator", File.separator);
        data.put("java_path_separator", File.pathSeparator);
        refExecutorExport(data);
    }

    /**
     * One command for all testing modes (Local | Remote)(SameJVM | MultiJVM)
     */
    private void refExecutorExport(Map data) {
        data.put("command.execute",
             ExecStdTestOtherJVMCmd.class.getName()
             + " CLASSPATH=$testSuiteRootDir$/classes"
                 + "$:${jaxbClasses}"
                 + "$:$testSuiteRootDir$/lib$/javatest.jar"
                 + "$:$testClassDir"
                 + "$:$outDir "
             + "$java_path "
             + "-Djava.security.policy=$testSuiteRootDir$/lib$/tck.policy "
             + "$java_other_opt "
             + "$testExecuteClass $testExecuteArgs");
    }
    public String getName() {
        return q_envName.getValue();
    }

    //////////////////////////

    /////////////////////////


    //----------------------------------------------------------------------------
    //
    // Please provide a short identifier which will be used to name the
    // configuration you are creating here.

    private StringQuestion q_envName = new StringQuestion(this, "envName") {
    private /*static*/ boolean isValidIdentifier(String s) {
        if (s == null || s.equals(""))
        return false;

        if (!Character.isUnicodeIdentifierStart(s.charAt(0)))
        return false;

        for (int i = 1; i < s.length(); i++) {
        if (!Character.isUnicodeIdentifierPart(s.charAt(i)))
            return false;
        }

        return true;
    }

    public void setValue(String value) {
        valid = isValidIdentifier(value);
        super.setValue(value);
    }

    protected Question getNext() {
        if (valid)
        return q_description;
        else
        return null;
    }

    private boolean valid;
    };

    //----------------------------------------------------------------------------
    //
    // Please provide a short description which can be used to identify
    // the configuration you are creating here.

    private Question q_description = new StringQuestion(this, "description") {
    protected void export(Map data) {
        data.put("description", value);
    }

    protected Question getNext() {
        if (value == null || value.trim().equals(""))
            return null;
        else
            return new FinalQuestion(StandardInterview.this);
        }
    };

}

class TestPlatformInterview extends MInterview {

    public TestPlatformInterview(StandardInterview parent, InterviewData id) {
        super(parent, "testPlatform", id);
        setFirstQuestion(q_sameOther);
    }
    public Question q_sameOther = new YesNoQuestion(this, "multiJVM") {
        public Question getNext() {
            if (getValue() == null) {
                return null;
            } else if (NO.equals(getValue())) {
                id.xjc_sameOther = JAXBTCKParameters.MODE_SAME;
            } else {
                id.xjc_sameOther = JAXBTCKParameters.MODE_OTHER;
            }
            return q_local;
        }
        public void export(Map data) {
            data.put("xjc_sameOther", String.valueOf(id.xjc_sameOther));
        }
    };

    public ChoiceQuestion q_local = new YesNoQuestion(this, "local") {

            public Question getNext() {
                if (getValue() == null) {
                    return null;
                } else if (YES.equals(getValue())) {
                    id.xjc_local = true;
                } else {
                    id.xjc_local = false;
                }
                id.exec_local = id.xjc_local;
                return new FinalQuestion(TestPlatformInterview.this);
            }
      };
}

class JaxbInterview extends MInterview {
    Interview agentMapInterview = new AgentMapInterview(this, id);
    Interview agentModeInterview = new AgentModeInterview(this, id);
    Interview cmdLineInterview = new CmdLineInterview(this, id);
    Interview jaxbClassesInterview = new JAXBClassesInterview(this, id);
    Question q_final = new FinalQuestion(this);

    public JaxbInterview(StandardInterview parent, InterviewData id) {
        super(parent, "jaxb", id);
        setFirstQuestion(prepareFirstQuestion());
    }
    public Question prepareFirstQuestion() {
        Question q = q_final;
        if (id.xjc_sameOther == JAXBTCKParameters.MODE_OTHER) {
            q = callInterview(jaxbClassesInterview, q);
        }
        q = callInterview(cmdLineInterview, q);
        q = callInterview(agentModeInterview, q);
        return q;
    }
}

/**
 * Interview for XJC MAP (agent file_separator/path_separator/map_file questions).
 */
class AgentMapInterview extends MInterview {

    public AgentMapInterview(Interview parent, InterviewData id){
        super(parent, "agentMap", id);
        setFirstQuestion(q_mapFileSeparator);
    }

    public String[] mapFileSeparatorSuggestions = {"", "/", "\\"};
    public String[] mapSeparatorSuggestions = {"", ":", ";"};

    public Question q_mapFileSeparator = new StringQuestion(this, "fileSeparator") {
        {
            setSuggestions(mapFileSeparatorSuggestions);
        }

        public Question getNext() {
           if (getValue() != null && getValue().length() == 1){
               return q_mapSeparator;
           } else {
               return null;
           }
        }

        public void export(Map data) {
            data.put("xjc_map_file_separator", getValue());
        }
    };

    public Question q_mapSeparator = new StringQuestion(this, "pathSeparator") {
        {
            setSuggestions(mapSeparatorSuggestions);
        }

        public Question getNext() {
           if (getValue() != null && getValue().length() == 1){
              return q_useMapFile;
           } else {
              return null;
           }
        }

        public void export(Map data) {
            data.put("xjc_map_path_separator", getValue());
        }
    };

    Question q_final = new FinalQuestion(this);

    public Question q_useMapFile = new YesNoQuestion(this, "mapArgs") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            }
            return q_final;
        }

        public void export(Map data) {
            if (YES.equals(getValue())){
                data.put("xjc_use_map_file", "-mapArgs");
            }
        }
    };


}

/**
 * Interview for MODE (agent mode/host/port questions).
 */

class AgentModeInterview extends MInterview {

    public AgentModeInterview(Interview parent, InterviewData id){
        super(parent, "agent", id);
        setFirstQuestion(q_intro1);
    }

    Question q_final = new FinalQuestion(this);

    public Question q_intro1 = new NullQuestion(this, "intro1") {
            public Question getNext() {
                if ( !(id.xjc_local && (id.xjc_sameOther == JAXBTCKParameters.MODE_OTHER)) )
                    return q_agentMode;
                else
                    return q_final;
            }
            public boolean isHidden() {
                    return true;
            }
    };


    public Question q_agentMode = new ChoiceQuestion(this, "agentType") {
        {
            setChoices(agentModeSuggestions);
        }

        public Question getNext() {
            if (getValue() == null){
                return null;
            } else if (AGENT_ACTIVE.equals(getValue())){
                id.xjc_agentActive = true;
                return getAgentMapQuestion();
            } else {
                id.xjc_agentActive = false;
                return q_agentPassiveHost;
            }
        }

        public void export(Map data) {
            if (AGENT_ACTIVE.equals(getValue())){
                data.put("xjc_agent_command", ActiveAgentCommand.class.getName());
            } else {
                data.put("xjc_agent_command", PassiveAgentCommand.class.getName());
            }
        }
    };

    public Question q_agentPassiveHost = new StringQuestion(this, "agentPassiveHost") {

        public Question getNext() {
           if (getValue() != null && getValue().length() > 0){
               return q_useAgentPassiveDefaultPort;
           } else {
               return null;
           }
        }

        public void export(Map data) {
            data.put("xjc_agent_passive_host", "-host " + getValue());
        }
    };

    public Question q_useAgentPassiveDefaultPort = new YesNoQuestion(this, "useAgentPortDefault") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            } else if (NO.equals(getValue())){
                return q_agentPassivePort;
            } else {
                return getAgentMapQuestion();
            }
        }
    };

    public Question q_agentPassivePort = new IntQuestion(this, "agentPassivePort", 0, 0xffff) {

        public Question getNext() {
            if (isValueValid()) {
                return getAgentMapQuestion();
            } else {
                return null;
            }
        }

        public void export(Map data) {
            data.put("xjc_agent_passive_port", "-port " + getValue());
        }
    };

    private Question getAgentMapQuestion() {
        if (!id.xjc_local) {
            return q_mapFileSeparator;
        } else {
            return q_final;
        }
    }


    // Agent Map:
    public String[] mapFileSeparatorSuggestions = {"", "/", "\\"};
    public String[] mapSeparatorSuggestions = {"", ":", ";"};

    public Question q_mapFileSeparator = new StringQuestion(this, "fileSeparator") {
        {
            setSuggestions(mapFileSeparatorSuggestions);
        }

        public Question getNext() {
           if (getValue() != null && getValue().length() == 1){
               return q_mapSeparator;
           } else {
               return null;
           }
        }

        public void export(Map data) {
            data.put("xjc_map_file_separator", getValue());
        }
    };

    public Question q_mapSeparator = new StringQuestion(this, "pathSeparator") {
        {
            setSuggestions(mapSeparatorSuggestions);
        }

        public Question getNext() {
           if (getValue() != null && getValue().length() == 1){
              return q_useMapFile;
           } else {
              return null;
           }
        }

        public void export(Map data) {
            data.put("xjc_map_path_separator", getValue());
        }
    };

    public Question q_useMapFile = new YesNoQuestion(this, "mapArgs") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            }
            return q_final;
        }

        public void export(Map data) {
            if (YES.equals(getValue())){
                data.put("xjc_use_map_file", "-mapArgs");
            }
        }
    };
}

class CmdLineInterview extends MInterview {

    public CmdLineInterview(Interview parent, InterviewData id){
        super(parent, "testExecute", id);
        setFirstQuestion(q_intro1);
    }

    Question q_final = new FinalQuestion(this);

    public Question q_intro1 = new NullQuestion(this, "intro1") {
            public Question getNext() {
                return q_javaPath;
            }
            public boolean isHidden() {
                    return true;
            }
    };

    public Question q_javaPath = new ExecutorQuestion(this, "cmdAsFile", "java_path") {

        public Question getNext() {
            if (id.exec_local == true && !isValueValid()) {
                return errorQuestion();
            }
            return q_refExecOtherOpt;
        }

        public void export(Map data) {
            data.put(envKey, getValue().getAbsolutePath());
        }
    };
    Question q_refExecOtherOpt = new StringQuestion(this, "otherOpts") {
        public Question getNext() {
            return q_envVars;
        }

        public void export(Map data) {
            data.put("java_other_opt", getValue());
        }
    };
    public Question q_envVars = new StringQuestion(this, "otherEnvVars") {

        public Question getNext() {
            return q_final;
        }

        public void export(Map data) {
            data.put("jaxb_env_vars", getValue());
        }
    };
}

/**
 * Interview for JAXB_CLASSES (otherJVM mode, JAXB classes).
 */
class JAXBClassesInterview extends MInterview {

    Question q_final = new FinalQuestion(this);

    public JAXBClassesInterview(Interview parent, InterviewData id){
        super(parent, "classes", id);
        setFirstQuestion(q_intro1);
    }

    public Question q_intro1 = new NullQuestion(this, "intro1") {
            public Question getNext() {
                if (id.xjc_sameOther == JAXBTCKParameters.MODE_OTHER)
                    return q_needJaxbClasses;
                else
                    return q_final;
            }
            public boolean isHidden() {
                    return true;
            }
    };

    public Question q_needJaxbClasses = new YesNoQuestion(this, "needJaxbClasses") {

        public Question getNext() {
            if (getValue() == null) {
                return null;
            } else if (NO.equals(getValue())){
                return q_final;
            } else {
                return q_jaxbClasses;
            }
        }
    };

    public Question q_jaxbClasses = new JAXBQuestion(this, "jaxbClasses") {
        public Question getNext() {
            if (id.exec_local == true && !isValueValid()) {
                return errorQuestion();
            }
            return q_final;
        }

        public void export(Map data) {
            if (id.exec_local == true && !isValueValid()) {
                return;
            }
            String pathSep;
            pathSep = (String)data.get("xjc_map_path_separator");
            if (pathSep == null) {
                pathSep = File.pathSeparator;
            }
            StringBuffer buffer = new StringBuffer();
            int n = getValue().length;
            for(int i = 0; i < n; i++) {
                if (i != 0) {
                    //buffer.append("$:");
                    buffer.append(pathSep);
                }
                buffer.append(getValue()[i].getAbsolutePath());
            }
            data.put("jaxbClasses", buffer.toString());
        }
    };
}

abstract class JAXBQuestion extends FileListQuestion {

    public BadFileQuestion qBadFile = null;
    public String tag = "";

    public JAXBQuestion(Interview interview, String tag) {
        super(interview, tag);
        this.tag = tag;

        qBadFile = new BadFileQuestion(interview, "badJaxbFile");

        FileFilter[] filters = {
            new DirectoryFileFilter("Directories"),
            new ExtensionFileFilter(".ZIP", "ZIP Files"),
            new ExtensionFileFilter(".JAR", "JAR Files"),
            new ExtensionFileFilter(".zip", "zip Files"),
            new ExtensionFileFilter(".jar", "jar Files")
        };
        this.setFilters(filters);
    }

    public Question errorQuestion() {
        if (getValue() == null || getValue().length == 0) {
            return null;
        } else {
            return qBadFile;
        }
    }

    public boolean isValueValid() {
        if (getValue() == null || getValue().length == 0) {
            return false;
        }

        int n = getValue().length;

        for (int i = 0; i < n; i++) {
            if (!getValue()[i].canRead()) {
                qBadFile.setFileName(getValue()[i].getPath());
                return false;
            }
        }
        return true;
    }
}
