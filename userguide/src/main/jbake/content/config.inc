///////////////////////////////////////////////////////////////////////
NOTE TO WRITERS:
The following sections should be customized for the technology.
This text was originally from the JAX-RS TCK.  Most references
to JAX-RS have been parameterized to serve as a simple starting
point for customization.  There are still many details that will
need to be changed or removed.  The major sections 4.1, 4.2, and
4.3 should be preserved.  If their titles are changed, the links
at the top of config.adoc will need to be changed as well as well
as toc.adoc.
///////////////////////////////////////////////////////////////////////

[[GBFVU]][[starting-java-test-harness]]

4.1 Starting the JavaTest Harness
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Before executing the JavaTest harness software, you must have a valid test suite and
Oracle Java SE {SEversion} or later installed on your system.
When executing the JavaTest harness, you can include arguments at the end of the
command string that specify how it starts. These command-line options are described
in JavaTest documentation that is located in the `<TS_HOME>/doc/javatest`
directory.
You can start the JavaTest harness in the following ways:

* Run a startup script : You can use one of the startup scripts that are provided
with the {TechnologyShortName} TCK as described in link:#GBFUY[Section, "Java Harness Scripts."]
* Execute the JAR file: You can execute the harness from the top-level directory of
your test suite by directly executing the JAR file at the command prompt: +
`java -jar <TS_HOME>/lib/javatest.jar`
* Double-click the icon (Windows platforms only): In Windows GUI, double-click
the `javatest.jar` file icon to start the harness.

[NOTE]
=======================================================================

The {TechnologyShortName} TCK requires Java SE {SEversion} or later and
at least 256 MB RAM.

=======================================================================

[[GBFVW]][[starting-javatest-harness-first-time]]

4.1.1 Starting JavaTest Harness For the First Time
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
When you execute the JavaTest harness for the first time, it displays a *Welcome to
JavaTest* dialog box that guides you through the process of either opening an existing
work directory or creating a new work directory, as described in the JavaTest online
help.
Subsequently, when you start the harness, if the JavaTest harness is able to find both
work directory and test suite automatically, then it will resume your last test run with
the same configuration and work directory. The -newDesktop command-line option will
ensure that the JavaTest harness always displays the *Welcome to JavaTest* dialog
box. If the JavaTest harness is unable to open a test suite, then it displays an error
message describing the reason for the error. Close the JavaTest harness and resolve
the error. Contact your support representative if you are unable to resolve the error.

[NOTE]
=======================================================================

To enable the JavaTest harness to find and load the test suite data, change
to the `<TS_HOME>` directory before launching the JavaTest software .
Alternatively, you can load test suite data by selecting *File* and then *Open*
Test Suite from the JavaTest main menu.
=======================================================================

After you specify a work directory, you can use the Test Manager to configure and run
tests. See Running the JAXB TCK.

[[GBFVX]][[javatest-harness-scripts]]

4.1.2 JavaTest Harness Scripts
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The JAXB TCK provides platform-specific startup scripts that can be used to execute
the JavaTest harness (on Oracle Solaris, Windows, and Linux) from the `<TS_HOME>`
directory. Each script is named javatest and is self-documented. These scripts are
found in their respective directories:

* `<TS_HOME>/solaris/bin/javatest`
* `<TS_HOME>\win32\bin\javatest`
* `<TS_HOME>/linux/bin/javatest`



[[GCLHU]][[configuring-javatest-harness]]

4.2 Configuring the JavaTest Harness
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Before the JavaTest harness can execute the test suite, it requires information about
how your computing environment is configured.


The JavaTest harness requires two types of configuration information:

* Test environment: This data is used by the tests (for example, the path to the
Java runtime, how to start the product being tested, network resources, and other
information required by the tests). This information does not change frequently and
usually stays constant from test run to test run.

* Test parameters: These parameters are values used by the JavaTest harness
that determine which tests in the test suite are run, how the tests should be run,
and where the test reports are stored. This information often changes from test run
to test run.

The first time that you run JavaTest harness, you must specify the test suite and work
directory that you want to use. These parameters can be changed later from within the
JavaTest harness GUI.

After the JavaTest harness GUI is displayed, whenever you select *Run Tests* and
then select *Start* to begin a test run, the JavaTest harness determines whether all the
required configuration information has been supplied:

  ** If the test environment and parameters have been completely configured, then the
  test run starts immediately.
  ** If any required configuration information is missing, then the configuration editor
  displays a series of questions asking for the necessary information. This is called
  the configuration interview. When you have entered the configuration data, you are
  asked if you want to proceed with running the test.

[[GCLHUA]][[configuration-interview]]

4.2.1 The Configuration Interview
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
The answers you give to some of the configuration interview questions are specific to
your site. An example is the name of the host on which the JavaTest harness is
running. Other configuration parameters can be of your choice. For example, the
location of the {TechnologyShortName} launcher or where you want test report files to be stored.

At any point in the configuration interview, you can select *File* and then *Save As* or
*File* and then *Save* menu options to save your answers and your position in the
interview to a `.jti` file. As explained in the JavaTest harness documentation, you can
load a previously saved `.jti` file from the command line when you execute the
JavaTest harness software. In that case, the JavaTest harness starts with the
configuration settings that are specified in the `.jti` file.

You can have multiple configuration files. For example, you can have different files for
different test device configurations or option selections. You can load a configuration
file by selecting *Configure* and then *Change Configuration* from the JavaTest
harness main menu. You can also specify the configuration file name on the command
line when you execute the JavaTest harness software.

To configure the JavaTest harness to run the {TechnologyShortName} TCK tests, complete the following
steps. You only need to complete these steps the first time you start the JavaTest test
harness. After you complete these steps, you can either run all or a subset of the tests,
as described in Running the {TechnologyShortName} TCK.

1.  Start the JavaTest harness GUI.
    See link:config.html#GBFVU[Section, "Starting the JavaTest Harness"].

2.  Specify the test suite and the work directory to store the test run results.
    From the JavaTest harness main menu, select *File* and then *Open Quick Start
    Wizard* to specify the test suite and the work directory to store the test run results.
    Complete the wizard instructions.

    [NOTE]
    =======================================================================
    You are prompted to specify the work directory when the JavaTest
    harness is started for the first time, or when it is started with the -
    `newDesktop` option. If you do not specify the work directory, then you need
    to specify when you run or configure the tests in the
    {TechnologyShortName} TCK.
    =======================================================================

3.  Use the *Configure* menu to load, edit, or create new configuration.
    Answer the questions when prompted. If you enter an invalid answer, then the
    Configuration Editor will indicate that the answer is not valid. The configuration
    options specific to the JAXB TCK are described in the TCK Configuration
    Concepts.

[[GCLHUB]][[tck-configuration-concepts]]

4.2.2 TCK Configuration Concepts
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
{TechnologyShortName} {TechnologyVersion} comprises of a schema compiler and
a schema generator. Both are types of compilers; the former maps from a
schema to Java, and the latter maps from Java to schema.
For the purposes of brevity in these instructions, the term compiler refers to
both the schema compiler and the schema generator.

Remote Versus Local
^^^^^^^^^^^^^^^^^^^
There are two general stages in the tests performed by the {TechnologyShortName} TCK suite:

* Compilation of test schemas into class files and Java sources into schemas
* Testing of implementation classes, generated classes, generated schemas, and
APIs

Both stages may be independently configured to be performed either on the same
machine or on the remote one. That is, you can compile locally and execute remotely,
compile remotely and execute locally, compile and execute locally, or compile and
execute remotely.

[NOTE]
=======================================================================
If you compile or execute remotely, then you need to use a JavaTest agent.
Ensure that you start the agent before executing any tests. See the JavaTest
documentation for more information about JavaTest agents. Basic
instructions for configuring a {TechnologyShortName} JavaTest agent are provided later in this
section.
=======================================================================

SingleJVM Versus MultiJVM
^^^^^^^^^^^^^^^^^^^^^^^^^
Another important consideration is whether you want to run the {TechnologyShortName} compilers or
execute the tests in a single JVM process, or you want to create a separate JVM for
every individual test in the test suite.

[NOTE]
=======================================================================
If you compile schemas or execute tests in a single JVM mode, then you
must also run a JavaTest agent. Instructions for doing this are provided later
in this section.
=======================================================================

* Using a single JVM for all compilations or test executions is generally much faster
than creating separate JVMs for each test process. SingleJVM mode is sometimes
the only way you can run tests on some remote systems. However, the
configuration for SingleJVM mode is more complicated because it requires you to
configure a JavaTest agent.

* Using a separate JVM for each test process, sometimes called MultiJVM mode, is
somewhat easier to configure both initially and later for batch mode processing, if
desired. MultiJVM mode is much slower because a separate Java instance must
be started for each test. Moreover, depending on the JAXB implementation being
tested, MultiJVM mode can be more susceptible to errors that are introduced by
memory leaks.

JavaTest Agents
^^^^^^^^^^^^^^^

Use JavaTest agent if you want to compile or test in any of the following modes:

* Locally, SingleJVM mode
* Non-locally, SingleJVM mode
* Non-locally, MultiJVM mode

[NOTE]
=======================================================================
Complete details about JavaTest agents are provided in the JavaTest Agent
User's Guide (`<TS_HOME>/doc/javatest/javatestAgent.pdf`).
=======================================================================

GUI Versus Batch Mode
^^^^^^^^^^^^^^^^^^^^^

Once you have configured your {TechnologyShortName} TCK environment using the JavaTest GUI, you
can run the tests through the GUI or in batch mode using scripts. However, you cannot
edit your {TechnologyShortName} TCK configuration files (`*.jti`) outside the JavaTest GUI.
Even though the `*.jti` configuration files are straight ASCII text, they have embedded checksum
strings to ensure validity and consistency with the requirements of the
{TechnologyShortName} TCK suite.

Batch mode is particularly useful after you have worked out the basics of your test
configurations through the JavaTest GUI. Batch mode provides a convenient way to
automate your test runs, and also allows you to change work and report directories,
which is often desirable when running tests repeatedly over progressive builds.

Using the JAXB TCK Configuration Interfaces
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

You can configure the {TechnologyShortName} TCK and the JavaTest harness from the JavaTest GUI, or
by passing configuration parameters directly on the command line in a command shell.

*{TechnologyShortName} TCK Configuration With the JavaTest GUI*

1.  Start the JavaTest harness GUI.
    See link:config.html#GBFVU[Section, "Starting the JavaTest Harness"].

2.  Complete the JavaTest harness *Quick Start Wizard* or use the *Test Manager*
    menu to perform the following tasks:

    * Select *File* and then *Open Test Suite* to open a test suite.

    * Select *File* and then *New Work Dir* to create a new work directory, or
    *File* and then *Open Work Dir* to open an existing work directory.

    * Select *Configure* then *New Configuration* to create a new configuration,
    *Configure* then *Load Configuration* to load an existing configuration, or
    *Configure* and then *Change Values* to modify the current configuration for
      your test environment. +
      The JavaTest harness opens the configuration editor window. See the
      JavaTest Harness User's Guide: Graphical User Interface included with this
      release or the JavaTest harness online help for detailed instructions on using
      the configuration editor window.

    * Click *Start Running Tests* on the *Test Manager* tool bar , or select *Run Tests*
      and then *Start* from the *Test Manager* menu. +
      See the JavaTest Harness User's Guide: Graphical User Interface included
      with this release or the JavaTest harness online help for detailed instructions
      about running the tests.

TCK Configuration From the JavaTest Command Line
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

To run the tests non-interactively, for example as part of a product build process, use
the JavaTest harness command-line interface. To run tests from the command line,
you must use a configuration file. The JavaTest harness command-line interface
provides a number of commands that you can use to specify individual configuration
values used to run tests. Values that are set in the command line will override but not
change the values in the configuration file. Permanent changes to the configuration
are made by using the Configuration Editor to change the values in the configuration
file.


See the JavaTest Harness User's Guide: Command Line Interface included with this
release or the JavaTest harness online help for detailed instructions about using the
command line to run tests and to modify the configuration.

*Setting Configuration Values on the Command Line*

If you intend to set one or more configuration values on the command line for a test
run, then see the JavaTest Harness User's Guide: Command-Line Interface, or the
JavaTest harness online help for a detailed description.

You must use one of the following sources to identify the configuration question-tag
names used in the command line:

* *Question Log file*: (`questionLognnnn.html`) generated by the Configuration Editor
in `workdirectory/jtData`

* *Config.html report*: generated from the JavaTest harness GUI

* *Question Tags*: displayed in the Configuration Editor

See the JavaTest Harness User's Guide: Graphical User Interface or the JavaTest
harness online help for a detailed description of displaying the Question Tags in the
Configuration Editor and generating reports containing the `config.html` file.

[[GCLHUC]][[jaxb-specific-configuration-settings]]

4.2.3 {TechnologyShortName}-Specific Configuration Settings
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
A number of questions in the JavaTest harness configuration interview are generic and
apply to most Java product TCK suites. This section describes configuration settings
that are specific to the {TechnologyShortName} TCK.

The {TechnologyShortName} TCK {TechnologyVersion} includes a special configuration
interview used by the JavaTest harness to collect information from you about
your test environment and to create the configuration used to run tests.
The TCK configuration interview (TCK interview) consists of the following sections:

* *Test Environment*: the first section of the interview collects information about the
test environment used to run the {TechnologyShortName} TCK {TechnologyVersion}
(such as the type of environment and the type of agent used).
* *Schema Compiler Questions*: the second section of the interview collects
information about how to compile the source schema on your platform.
  ** XML Validation Tests: optional tests that must be run if schema validation is
performed using a mechanism other than JAXP.
* *Schema Generator Questions*: the third section of the interview collects
information about how to generate XML schema from Java sources on your
platform.
* *How Tests are Run*: the last section of the interview collects information on how
the tests are run (such as Keywords and the Exclude List).

The Configuration Editor Questions
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The table describes the Configuration Editor questions, lists the question tags
and text, and gives examples of relevant answers to correctly configure the
JavaTest harness to run the test suite.

[NOTE]
=======================================================================
To view the question tags in the Configuration Editor, select *View* and then
*Question Tag* in the editor menu.
=======================================================================

[[GCLHUC1]][[GCLHUC2]][[GCLHUC3]]

Table 4-1 Configuration Editor Questions and Relevant Answers 

[width="100%",cols="10%,45%,45%",options="header",]
|=======================================================================
|Item Name |Question Tag and Text |Comments and Proposed Answers
|Welcome! |
*Tag: jck.prolog*

Welcome to the Configuration Editor for JAXB TCK testsuite!

|None

|Configuration Name |
*Tag: jck.env.envName*

Provide a short identifier that names the configuration you are creating. |
This is a short string that identifies this particular configuration. Select a name that
describes the configuration.

Example: `jaxb_win32`

The name must begin with a letter, followed by letters, digits, or an underscore, and
must not contain a white space or punctuation characters.

|Description |
*Tag: jck.env.description*

Provide a short description that identifies the configuration you are creating. |
JavaTest uses this short description to
provide more detail about the configuration,
for example, in reports. This information
might be useful to an auditor looking at the
test run reports, and could include the
version number of the product and the name
of the tester.

Example: `John Smith x86 Java Architecture for XML Binding 2.3`

|Execution Mode |
*Tag: jck.env.testPlatform.multiJVM*

Specify whether you want to run the tests in SingleJVM or MultiJVM
mode. Depending on the selected mode, you can configure to run the
compiler either from the command line, or via the Java Compiler API,
or via a custom class accessed by the core reflection API. |
Answer `MultiJVM Mode` if the JavaTest harness can directly start your product, for
example, from a command line in an MSDOS window or UNIX shell.

Typically, applications that do not support such actions run in point-and-click
environments or in browsers. For example, the JavaTest harness cannot start a new
instance of a Java virtual machine inside a browser.

If you answer MultiJVM Mode , then you need to answer additional interview
questions to specify whether you plan to run the tests locally. In addition, you need to
specify the details about the Java Virtual Machine in which the tests will run (the
questions in the *Runtime Other JVM* section of this interview) and the details
about the Java launcher.

Answer `SingleJVM Mode` if the JavaTest harness cannot start your {TechnologyShortName}
implementation directly.

If you answer `SingleJVM Mode`, then you need to answer additional interview
questions about the test platform file separator and path separator. In addition,
you need to provide the details about how to run the agent (the questions in the *Runtime
Same JVM* section of this interview).

[NOTE]: Under the MultiJVM mode, you can run the compiler either from command line,
or from the Java Compiler API. Under the SingleJVM mode, you can run the
compiler either from the Java Compiler API, or from a custom class accessed by
the core reflection API.

|Execute Locally |
*Tag: jck.env.testPlatform.local*

Will you run the TCK tests on the system that hosts the JavaTest harness?

|If you plan to run the tests on the system on which you are running this interview, then
the editor gathers configuration information directly from the system. For example, you
will be able to browse the local file system to point to the required files.

|Agent Type |
*Tag: jck.env.jaxb.agent.agentType*

The JavaTest Agent can be run in two modes: active and passive.
Which mode do you wish to use?

|Select the agent type you wish to use. If you want to use the passive agent, then two
additional questions will appear asking you to specify the passive agent host and
default agent port.

|Passive Host |
*Tag: jck.env.jaxb.agent.agentPassiveHost*

What is the of the host on which you run the tests?

|This is the name of the system on which the passive agent runs and executes tests. The
name of the system must be accessible from the system on which JavaTest runs.
Since the JavaTest agent is passive, you can only change the machine you specify
here by running this interview again and changing the name of the passive host.

|Default Agent Port |
*Tag: jck.env.jaxb.agent.useAgentPortDefault*

Do you wish to use the default port for the JavaTest harness to contact
the JavaTest Agent?

|By default, the JavaTest harness communicates with a passive agent using
port 1908. You must change only the default if you are running more than one passive
agent on a system; this might be the case if you have set up batch runs.

If you answer `No`, then you will need to specify the default port number in the next
interview question.

|Passive Port |
*Tag: jck.env.jaxb.agent.agentPassivePort*

Which port does the JavaTest harness use to contact the JavaTest Agent?

|Specify the port you want to use to communicate with the JavaTest agent.

|Test Platform File Separator |
*Tag: jck.env.jaxb.agent.fileSeparator*

Which file separator character is used on the test platform?

|Specify the appropriate file separator for your operating system.

For example, for Windows, use the back slash (\) character. For Solaris OS or Linux
operation system, use the forward slash (/) character.

|Test Platform Path Separator |
*Tag: jck.env.jaxb.agent.pathSeparator*

Which path separator character is used on the test platform?.

|Specify the appropriate path separator for your operating system.

For example, on Windows systems, specify the ; character, on Unix systems specify
the : character. Other platforms may differ.

|Agent Map File |
*Tag: jck.env.jaxb.agent.mapArgs*

|Because JavaTest and the JavaTest agent run on different systems, values such as
path names and variable names may differ on each system. For example, if a file is
accessed over a network, it might be mounted as `H:\tests\lib` on the
JavaTest system and as `F:\tests\lib` on the system running the JavaTest agent.
The map file is used to associate values used on the JavaTest agent with those used
by JavaTest. The map file is specified on the JavaTest agent, but the use of the map
must be enabled here. For more information about map files, refer to the JavaTest User's
Guide or to the JavaTest online help.

|Java Launcher |
*Tag: jck.env.jaxb.testExecute.cmdAsF
ile*

Enter the full path name of the Java launcher that runs the tests through
the Java Compiler API interface.

|This interview question appears only if you answered `Yes` to the Test Platform
MultiJVM question of this interview.

This is the command used on the reference system to invoke the Oracle reference Java.

NOTE: Use the `java` command in JDK (not JRE).

* On Solaris or Linux platforms, type:`$JAVA_HOME/bin/java`

  For example: `/usr/java/bin/java`

* On Win32 systems, type: `%JAVA_HOME%\bin\java.exe`

  For example: “C:\Java\jdk–9\bin\java.exe”

  Ensure that you supply the full file name, including the file extension.

|Other Options |
*Tag: jck.env.jaxb.testExecute.otherOpts*

Specify any additional commandline options that must be set.

|Specify any command-line options that JavaTest should set for your compiler when
it runs the tests on your system. Specify the options using `name=value` pairs. For
example you may want to specify: `-Xmx256m -Xms128m`
to change the default maximum and initial Java heap size.

NOTE: If you use Java Launcher from JCK 8 to run the tests, specify the
`-Djava.endorsed.dirs=<JAXB_HOME>/lib` option.

|Other Environment Variables |
*Tag: jck.env.jaxb.testExecute.otherEnvVars*

Except for CLASSPATH, if there are any other environment variables
that must be set for the reference Java launcher, enter them here.

|Specify any environment variables that the JavaTest harness should use in the
environment created to run the tests on your system. Specify the variables using the
`name=value` pairs.

For example, on Solaris systems: `special_path=/home/env/config`

NOTE: You must specify both variables: JAVA_HOME and JAXB_HOME.
For example, on Solaris: JAVA_HOME=/jdk-8/solarissparcv9 JAXB_HOME=/jaxb-2.3
on Windows: JAVA_HOME=d:\jdk-8\windows-i586 JAXB_HOME=d:\jaxb-2.3

Where the path to the directory of J2SE on Solaris should be specified in JAVA_HOME,
JAXB_HOME points to the directory under which the bin/, docs/, examples/, and lib/
directories reside.

The JavaTest cannot inherit environment variables from your operating system
environment. If you find that your tests run correctly outside of the JavaTest harness,
but do not run correctly using the JavaTest harness, then the reason might be that a
value set in your operating system environment is not available to the test. If
you check your operating system environment and determine that this is the
case, then set the missing values here.

|Need Extra JAXB Classes |
*Tag: jck.env.jaxb.classes.needJaxbClasses*

Do you need to specify extra JAXB classes for the Java launcher to execute tests?

|The JAXB classes must be available (that is, can be found and loaded) during test
execution. This means that {TechnologyShortName} JAR archives or directories which contain the
{TechnologyShortName} classes must be in the test executing Java class path.

Answer `No` if the {TechnologyShortName} JAR archives are in the Java default class path, for example, all
jar files are stored in the jdk_install_dir/jre/lib/ext directory.

Answer `Yes` if some directories or archives are located not in the Java default class
path.

If you answer `Yes`, then you will be asked to specify necessary class paths or {TechnologyShortName}
archives.

|Schema Compiler Questions |
*Tag: jck.env.jaxb.xsd_compiler.intro*

The following questions collect information about how to compile the source schema on your
platform.

|None.

|Schema Compiler Operation Mode |
*Tag: jck.env.jaxb.xsd_compiler.defaultOperationMode*

Would you like to run your schema compiler in default operation mode?

|A {TechnologyShortName} compatible implementation must
support a default operating mode in which
all the required XML Schema to Java
bindings that are described in the JAXB
specification must be implemented. Errors
must be reported when alternative or
extension non-required features are
encountered.

A {TechnologyShortName} compatible implementation may
support non-default operating modes. These
modes must support all the XML Schema to
Java bindings that are described in JAXB
specification, but may also generate
alternative or extension bindings for XML
Schema constructs, which are not required
by this specification.

If you select `Yes`, then the schema compiler
is supposed to run in default operation
mode and the tests for non-required
features must be passed (as negative tests
with expected error messages).

If you select `No`, then the schema compiler
is supposed to run in non-default operation
mode and the tests for non-required
features will not be run. For more
information on default operation mode
requirements, see the Compatibility section
of the {TechnologyShortName} specification.

|Optional XML Validation Tests |
*Tag: jck.env.jaxb.xsd_compiler.skipValidationOptional*

Is Schema Validation implemented via mechanism different from JAXP?

|XML validation can be implemented from
JAXP or from some other mechanism. If the
validation is implemented from JAXP, then
validation checking tests can be skipped by
deselecting them in the test interview. If you
use some other tools for schema validation,
then XML validation tests are mandatory.

|Schema Compiler Run Command |
*Tag: jck.env.jaxb.xsd_compiler.testCompile. xjcCmd*

The remaining questions ask which tests you want to run and how you want them to be run.

|This is the command used to invoke your
schema compiler. For example, for the
Oracle JAXB implementation on Solaris we
provide a script `TCKDIR/solaris/bin/
xjc.sh`, which compiles schemas into class
files. The command is as follows:
`/bin/ksh solaris/bin/xjc.sh`
For the Oracle JAXB implementation on
Windows, we provide a script TCKDIR
`\win32\bin\xjc.bat`, which compiles
schemas into class files.
The command is as follows:
`win32\bin\xjc.bat`
The xjc script supports the following
mandatory command-line options:
`xjc -p <pkg> -d <dir> <schema files>`
where:
• `-p <pkg>` specifies the target package
• `-d <dir>` specifies the directory to
store generated files.


|Schema Compiler Class |
*Tag: jaxb.xsd_compiler.run.compilerWrapperClass*

You must provide a custom class to invoke your compiler. This class
should implement the com.sun.jaxb_tck.lib.SchemaCompilerTool interface.

|When you run schema compilation through
the JavaTest agent, your schema compiler
will be executed in the agent's JVM. Your
implementation must provide a custom class
to invoke your schema compilation tool. This
class should implement the
`SchemaCompilerTool` interface:
`package com.sun.jaxb_tck.lib;
public interface SchemaCompilerTool {
/**
* @param xsdFiles – array of strings
containing schema files
* @param packageName – the target
package
* @param outDir – output directory
where java file(s) will be generated
* @param out - output stream for
logging
* @param err - error stream for logging
* @return 0 if java file(s) generated
successfully
*/
int compile(String[] xsdFiles, String
packageName, File outDir, PrintStream
out, PrintStream err);
}`
Schema compilation should be
accomplished during invocation of compile
method.
The JAXB TCK includes the following class
that is fully compatible with Oracle's
reference implementation and that can be
used for schema compilation:
`com.sun.jaxb_tck.lib.SchemaCompiler.`
The realization of the
method `compile(String[] xsdFiles,
String packageName, File outDir,
PrintStream out, PrintStream err)`
compiles a schema into `java` sources.
If your implementation doesn't provide such
a class, then create it and specify it here.


|Schema Generator Questions |
*Tag: jck.env.jaxb.schemagen.intro*

The following questions collect information about how to generate xml schema
from java sources on your platform.

|None.


|Schema Generator Presence |
*Tag: jck.env.jaxb.schemagen.skipJ2XOptional*

Does your implementation provide schema generator which is able to
generate schemas from Java sources?

|Java Architecture for XML Binding 2.3
specification does not require any
implementation, which provides a possibility
to generate the schemas from the java
sources. If they are provided, then all
specification requirements should be met.

|Schema Generator Run Command |
*Tag: jck.env.jaxb.schemagen.run.jxcCmd*

Enter the command to run the schema generator.

|This is the command used to invoke your
schema generator. For example, for the
Oracle JAXB implementation on Solaris we
provide a script `TCKDIR/
solaris/bin/schemagen.sh`, which
generates the schemas from the java files.
The command is:
`/bin/ksh solaris/bin/schemagen.sh`
For the Oracle {TechnologyShortName} implementation on
Windows, we provide a script `TCKDIR
\win32\bin\schemagen.bat`, which
compiles the schemas into class files. The
command is:
`win32\bin\schemagen.bat`
The schemagen script supports the following
mandatory command-line options:
`schemagen -d <dir> <java files>`, where,
`-d <dir>` specifies the directory to store the
generated files.


|Schema Generator Class |
*Tag: jck.env.jaxb.schemagen.run.schemagenWrapperClass*

You must provide a custom class to invoke your schema generator. This
class should implement the com.sun.jaxb_tck.lib.SchemaGenTool interface.

|When you run schema generation through
JavaTest agent, your schema generator will
be executed in the agent's JVM. Your
implementation must provide a custom class
to invoke your schema generation tool. This
class should implement the SchemaGenTool
interface:

`package com.sun.jaxb_tck.lib;
public interface SchemaGenTool {
/**
* @param javaFiles - array of strings
containing java files
* @param outDir - output directory
where schema file(s) will be generated
* @param out – output stream for
logging
* @param err – error stream for logging
* @return 0 if schema file(s)
generated successfully
*/
int generate(String[] javaFiles, File
outDir, PrintStream out, PrintStream
err);
}`
Schema generations should be complete
during the invocation of the generate
method.
The JAXB TCK includes the following class
that is fully compatible with Oracle's
reference implementation and that can be
used for schema generation:
`com.sun.jaxb_tck.lib.SchemaGen`. The
realization of the method
`generate(String[] javaFiles, File
outDir, PrintStream out, PrintStream
err)` generates the XML schemas from the
`java` files.
If your implementation doesn't provide such
a class, then create it and specify it here.


|Schema Compiler Questions |
*Tag: jck.env.jaxb.xsd_compiler.intro*

The following questions collect information about how to compile the source schema on your
platform.

|None.


|Standard Values |
*Tag: jck.parameters*

The remaining questions ask which tests you want to run and how you
want them to be run.

|None.


|Specify an Exclude List? |
*Tag: jck.excludeList.needExcludeList*

Do you wish to specify an exclude list?

|Answer `Yes` if you want to specify an
exclude list for the test run. If you select
Yes, then select the location of the exclude
list to use.

Answer `No` if you want to run all tests that
are not filtered out by other run filters.


|Which
Exclude
List? |
*Tag:
jck.excludeList.excludeListType*

Which exclude list do you wish to
use?

|This question appears only if you answered
Yes to the previous configuration interview
question.

Select either the Initial option to use the
exclude list provided with the test suite or
the Specify option to specify the exclude list
you wish to use.

|Specify
Exclude
List Files |
*Tag: jck.excludeList.customFiles*

Specify the files that make up the
exclude list you wish to use.

|Click *Add* to activate a file chooser with
which you select an exclude list file. Exclude
list files conventionally use the
extension ``.jtx`.


|Specify
Keywords? |
*Tag:
jck.keywords.needKeywords*

Do you wish to specify a keyword
expression to select/reject tests
based on keywords contained in
each test description?

|Keywords are tokens associated with
specific tests.

Answer `Yes` to run tests based on the
keyword and the other run filter settings. If
you select Yes, then you are asked to
specify the keyword to use.

Answer `No` to run all tests that are not
filtered out by other run filters.


|Specify
Status? |
*Tag: jck.priorStatus.needStatus*

Do you wish to select tests to run
based on their result in a previous
run?

|Answer Yes to run tests based on the
status from the previous test run and the
other run filter settings. If you select Yes,
then you are asked for the status or
statuses.

Answer No to run all tests that are not
filtered out by other run filters.


|Concurrency |
*Tag:
jck.concurrency.concurrency*

The JavaTest harness can run tests
concurrently. Specify the maximum
number of tests that should be run
concurrently:

|The JavaTest harness can run tests
concurrently. If you are running the tests on
a multiprocessor computer, then the
concurrency can accelerate your test runs.
The default range of values that the
JavaTest harness uses is from 1 to 50.
For the first test run, keep this value set to
1.


|Time
Factor |
*Tag: jck.timeout.timeout*

Specify a time factor that is applied
to each test's default timeout. For
example, specifying 2 doubles the
time for each test (the default is 1):

|The default 1.0 corresponds to 10 minutes.
Use the floating point format specific to your
locale.

NOTE: Setting a
proper time
factor value
requires
consideration
of such
technical
factors as the
network
connector
speed.


|Completed! |
*Tag: jck.end*

Congratulations! This completes the
configuration. Click Done to save
your answers and close the editor.
To run the test suite, choose Run
Tests->Start.

|None.
|=======================================================================


[[GCLHUD]][[creating-map-file]]

4.2.4 Creating a Map File
^^^^^^^^^^^^^^^^^^^^^^^^^
The network file systems may be mounted differently on different systems, and the
path names specified in the configuration interview and used by the JavaTest harness
might not be the same as the agent. The agent uses a map file to translate differing
paths into appropriate ones for the agent's platform to run tests. The map file can
contain any string mappings, not only the path name mappings.

The map file contains a blank-space separated pairs, each on a new line:
`string_to_be_substituted string_to_substitute_with`

A comment line begins with the # symbol and provides information that is not
processed by the agent. Comment lines are optional.

For the JAXB TCK, the map file should contain the following mappings:

1.  Mapping of the `<TS_HOME>`` path (the paths should not end with a file separator).
2.  Mapping of the work directory path (the paths should not end with a file separator).
3.  Mappings of all paths specified in the interview, if necessary (for example, path to
    Java, path to the {TechnologyShortName} jars, and so forth).
4.  Mappings for the URL protocol notation:
    * If the JavaTest runs on the Solaris platform and the agent runs on a platform
    where a file separator is not '/', then the mapping “file:// file:///” should be
    specified.
    * If the JavaTest runs on the Windows platform and the agent runs on the Unix
    platform, then the mapping “file://// file:///” should be specified.

    [NOTE]
    ==================================================================================
    All strings should be unique in the map file. If you want to specify two
    mappings of `string1` and `string2`, and `string1` is a substring of
    `string2`, then you should only specify mappings of `string1` and the rest
    of `string2` (string2 without string1). +
    For example:
    In the interview, the path `/dest/jdk-9/solaris-sparc` and `/dest/jdk-9/
    solaris-sparc/bin/java` are specified. Since the string `/dest/jdk-9/solarissparc`
    is a substring of the string `/dest/jdk-9/solaris-sparc/bin/java`, only
    mappings of the following strings should be specified:
    `/dest/jdk-9/solaris-sparc d:\jdk-9\solaris-sparc /bin/java \bin\java`

    ==================================================================================

Examples of Map Files
^^^^^^^^^^^^^^^^^^^^^

This section contains the examples of the map files:

*Example 4-1 JavaTest on Solaris, Agent on Windows, SingleJVM mode*

`# mapping for TCK_HOME
/home/sqe/jaxb_tck23 Z:\sqe\jaxb_tck23`

`# mapping for the working directory
/home/sqe/qa/work1 Z:\sqe\qa\work1`

`# mapping for URL protocol notation
file:// file:///`

*Example 4-2 JavaTest on Windows, Agent on Solaris, SingleJVM mode*

`# mapping for TCK_HOME
Z:\sqe\jaxb_tck23 /home/sqe/jaxb_tck23`

`# mapping for the working directory
Z:\sqe\qa\work1 /home/sqe/qa/work1`

`# mapping for URL protocol notation
file://// file:///`

*Example 4-3 JavaTest on Solaris, Agent on Windows, MultiJVM mode*

`# mapping for TCK_HOME
/home/sqe/jaxb_tck23 Z:\sqe\jaxb_tck23`

`# mapping for the working directory
/home/sqe/qa/work1 Z:\sqe\qa\work1`

`# mapping for URL protocol notation
file:// file:///`

`# mappings for paths specified in the interview
/dest/jdk1.5.0_01/solaris-sparc d:\jdk1.5.0_01
/dest/jaxb-2.3 d:\jaxb-2.3
/lib/ \lib\
/bin/java \bin\java.exe`

*Example 4-4 JavaTest on Windows, Agent on Solaris, MultiJVM mode*

``# mapping for TCK_HOME
Z:\sqe\jaxb_tck23 /home/sqe/jaxb_tck23`

`# mapping for the working directory
Z:\sqe\qa\work1 /home/sqe/qa/work1`

`# mapping for URL protocol notation
file://// file:///`

`# mappings for paths specified in the interview
d:\jdk1.5.0_01 /dest/jdk1.5.0_01/solaris-sparc
d:\jaxb-2.3 /dest/jaxb-2.3
\lib\ /lib/
\bin\java.exe /bin/java`

See the JavaTest documentation for more information about agent map files.
