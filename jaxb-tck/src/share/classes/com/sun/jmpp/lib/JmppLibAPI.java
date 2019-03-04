/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jmpp.lib;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.sun.jmpp.JmppLib;
import com.sun.tgxml.tools.testgen.api.TestDescriptionIR;

/**
 * This class represents JmppLibAPI library used to expand .jmpp files
 * into API tests and their html descriptions. The resulting <code>.java</code>
 * and <code>.html</code> files are suitable for run with JavaTest harness.
 * <br>
 * The following public routines are used to control test generation flow:
 * <ul>
 * <li>{@link #testcreate()} - initialization of the set of tests
 * <li>{@link #javaclose()} - creation of the particular <code>.java</code> file
 * <li>{@link #gen()} - generation of the particular test
 * <li>{@link #store()} - generation of the particular test description
 * <li>{@link #skip()} - skipping of the  particular test
 * </ul>
 * The contents of the generated <code>.java</code> and <code>.html</code> files
 * are defined through a set of JmppLibAPI predefined public variables.
 * Each of them belongs to one of there levels: <b>directory</b>, <b>file</b>
 * or <b>test case</b>. Mandatory variables are required to be defined for the
 * corresponding level. Variables-switches should be defined at least once for
 * the level.
 * <h4>Directory (jmpp source file) level variables</h4>
 * <table border=1>
 * <tr>
 * <th> Variable
 * <th> Short description
 * <th> Usage type
 * <tr>
 * <td> {@link #testpackage}
 * <td> name of package for a class under test
 * <td> mandatory
 * <tr>
 * <td> {@link #testclass}
 * <td> name of a class under test
 * <td> mandatory
 * <tr>
 * <td> {@link #index}
 * <td> text to be placed at the head of <code>.html</code> file
 * <td> mandatory
 * <tr>
 * <td> {@link #scInfo}
 * <td> Source code keywords string for this <code>.jmpp</code> file
 * <td> optional
 * <tr>
 * <td> {@link #addindex}
 * <td> links to additional <code>.html</code> files
 * <td> optional
 * <tr>
 * <td> {@link #htmlindex}
 * <td> name for the generated <code>.html</code> file
 * <td> optional
 * <tr>
 * <td> {@link #rcfiles}
 * <td> resource files to be placed along with compiled classes
 * <td> optional
 * <tr>
 * <td> {@link #method}
 * <td> signature for a method, constructor or field under test
 * <td> switch
 * <tr>
 * <td> {@link #testCount}
 * <td> number of the last generated test case.
 * <td> informational
 * </table>
 * <h4>File level variables</h4>
 * <table border=1>
 * <tr>
 * <th> Variable
 * <th> Short description
 * <th> Usage type
 * <tr>
 * <td> {@link #file}
 * <td> name for the generated <code>.java</code> file.
 * <td> mandatory
 * <tr>
 * <td> {@link #filetitle}
 * <td> text description to be placed at the head of <code>.java</code> file
 * <td> mandatory
 * <tr>
 * <td> {@link #author}
 * <td> author's name for subsequent test cases
 * <td> switch
 * <tr>
 * <td> {@link #baseClass}
 * <td> ancestor of the test class
 * <td> optional
 * <tr>
 * <td> {@link #imports}
 * <td> import statement to be placed into <code>.java</code> file
 * <td> optional
 * <tr>
 * <td> {@link #codeinclude}
 * <td> code to be included into test's class body
 * <td> optional
 * <tr>
 * <td> {@link #classinclude}
 * <td> code to be included outside test's class body
 * <td> optional
 * <tr>
 * <td> {@link #uses}
 * <td> auxiliar <code>.java</code> files to linked into test description
 * <td> optional
 * <tr>
 * <td> {@link #executeArgs}
 * <td> test's execute arguments
 * <td> optional
 * <tr>
 * <td> {@link #keywords}
 * <td> additional keywords to be placed into test description
 * <td> optional
 * <tr>
 * <td> {@link #dependencies}
 * <td> The libraries this file is dependent upon.
 * <td> optional
 * <tr>
 * <td> {@link #iodata}
 * <td> The data files used by the test
 * <tr>
 * <td> {@link #rmicClass}
 * <td> string to put into <code>rmicClass</code> field of test description
 * <td> optional
 * <tr>
 * <td> {@link #remote}
 * <td> string to put into <code>remote</code> field of test description
 * <td> optional
 * <tr>
 * <td> {@link #context}
 * <td> string to put into <code>context</code> field of test description
 * <td> optional
 * <tr>
 * <td> {@link #selectIf}
 * <td> string to put into <code>selectIf</code> field of test description
 * <td> optional
 * <tr>
 * <td> {@link #timeout}
 * <td> string to put into <code>timeout</code> field of test description
 * <td> optional
 * </table>
 * All file level variables are cleaned up by {@link #javaclose()} call.
 * <h4>Test case level variables</h4>
 * <table border=1>
 * <tr>
 * <th> Variable
 * <th> Short description
 * <th> Usage type
 * <tr>
 * <td> {@link #testTechnique}
 * <td> test technique used for current testcase
 * <td> optional
 * <tr>
 * <td> {@link #code}
 * <td> test case code
 * <td> mandatory
 * <tr>
 * <td> {@link #testCaseID}
 * <td> test case ID
 * <td> mandatory
 * <tr>
 * <td> {@link #values}
 * <td> assertion under test or method call parameters
 * <td> mandatory
 * <tr>
 * <td> {@link #precondition}
 * <td> pre-condition for test
 * <td> optional
 * <tr>
 * <td> {@link #expected}
 * <td> expected results for test
 * <td> optional
 * </table>
 * <p>
 * The {@link #code} and {@link #testCaseID} variables are cleaned up by
 * {@link #gen()} and {@link #skip()} calls. Other test case level variables
 * are cleaned up by {@link #gen()}, {@link #skip()} and {@link #store()} calls.
 * <p>
 * The following public routines can be used to customize test generation
 * <ul>
 * <li>
 * {@link #setArgs(String baseClass, String defaultArgs)} - set default execute arguments
 * for the given <code>baseClass</code>
 * <li>
 * {@link #getArgs(String baseClass)} - get default execute arguments
 * for the given <code>baseClass</code>
 * <li>
 * {@link #setBaseClassImports(String baseclass, String fullName)} - set import
 * for the given <code>baseClass</code>
 * <li>
 * {@link #getBaseClassImports(String baseclass)} - get import for the given
 * <code>baseClass</code>
 * <li>
 * {@link #setMethodDescription(String method, String description)} - set
 * description for the given <code>method</code>
 * <li>
 * {@link #getMethodDescription(String method)} - get
 * description for the given <code>method</code>
 * <li>
 * {@link #addJavaFile(String fileName, String contents)} - define auxiliary
 * <code>.java</code> file generation
 * </ul>
 * Public JmppLibAPI variables and methods are for use by test writers.
 * Protected variables and methods are for use in JmppLibAPI subclasses.
 * Please refer to Test Gen Users Guide for detailed documentation on how to
 * use JmppLibAPI for tests generation.
 */

public class JmppLibAPI extends JmppLib {

   /**
    * invokes method libMain() with passed arguments and new instance of this class.
    * If you extends JmppLibAPI class you should override main() method as follow:
    * <pre>
    *  public static void main(String[] argv) {
    *      libMain(argv, new MyJmppLibAPI());
    *  }
    *
    * Where MyJmppLibAPI extends JmppLibAPI
    * </pre>
    */
    public static void main(String[] argv) {
        libMain(argv, new JmppLibAPI());
    }

    {
	CLASS_SUFF = "Tests";		// Lib specific suffix for generated file/class names
    }

    /**
     * Copyright notice. This string is put into the head
     * of each java file.
     */
    protected String copyrights = "";

    /**
     * Name of the directory where a current test source is generated into
     * (if sourceNameIsRelative is set to true then the source maybe generated
     * into the dirName's subdirectory)
     */
	protected String dirName;

    /**
     * Package head, that will be used to set up package of the test.
     */
	public String packageHead = "";

    /**
     * The constant indicating that current testcase is part of equivalence class
     * partitioning testing for the current method.<br>
     * This is one of the possible values for <code>testTechnique</code> variable.
     * The value is 1.
     */
    public static final int EQUIVALENCE_CLASS_PARTITIONING = 1;

    /**
     * The constant indicating that the testcase is part of boundary value
     * analysis testing for the current method. <br>
     * This is one of the possible values for <code>testTechnique</code> variable.
     * The value is 2.
     */
    public static final int BOUNDARY_VALUE_ANALYSIS = 2;

    /**
     * The constant indicating that the testcase is part of ordinary assertion testing for
     * the current method. <br>
     * This is one of the possible values for <code>testTechnique</code> variable.
     * The value is 0, which means no special technique is used.
     */
    public static final int ASSERTION_TESTING = 0;

    /**
     * A string variable representing test name postfix. For each
     * <code>.java</code> file this string will be added to file name.
     * For example, if <code>file</code>="Ctor" then the correspondent
     * <code>.java</code> file name would be "CtorTests.java".
     * By default, this variable is set to "Tests".
     */
    protected String testNamePostfix = "Tests";

    /**
     * The variable representing value "MultiTest" which is used to initialize
     * <code>baseclass</code> variable.
     * Actually, not all tests extend MultiTest, some extends ReadTest,
     * one can use own different class to be a base class for a test.
     */
    private String defaultBaseClass = "MultiTest";

    /**
     * The variable containing default value for JavaTest keywords "runtime positive".
     */
    private String defaultKeywords = "runtime positive";

    /**
     * The variable containing default value for JavaTest rmic tests keywords "compiler runtime positive".
     */
    private String defaultRmicKeywords = "runtime positive";

    /**
     * This variable is used to specify source code control keywords if the
     * <code>.jmpp</code> files are under source code control system
     * which supports keywords expansion. Their expanded contents
     * will be placed into <code>.java</code> and <code>.html</code> files if the
     * readable copy of <code>.jmpp</code> file has been retrieved through
     * the source code control system. For example, SCCS users may want to assign
     * <code>scInfo = "&#037;G&#037;";</code> which will be automatically expanded into
     * <code>scInfo = "MM/DD/YY";</code> during <code>sccs get</code> operation.
     * CVS users may do the same by writing <code>scInfo = "$Date$";</code> and
     * RCS users may want to write <code>scInfo = "$Id$";</code>.
     * By default, it's value is set to an empty string.
     */
    public String scInfo =  "";

    /**
     * The variable containing system variable name "jck.destination.dir"
     * that should be specified for resource files copying.
     */
    protected String tckDestinationDir = "jck.destination.dir";

    /**
     * System property name to get <code>ini file</code> name from.
     */
    protected String configFilePropertyName = "testgen.libAPI.propfile";

    /**
     * System property name which is used to store the name of
     * configuration class.
     */
    protected String configClassPropertyName = "testgen.libAPI.propclass";

    /**
     * The name of the system property where a link to copyright file is stored.
     */
    protected String copyrightLinkPropertyName = "jmpp.tck.copyright";

    /**
     * The constant indicating which set of variables should be checked
     * in <code>checkVars</code> method.
     * CHECK_VARS_GEN indicating that all mandatory variables used
     * in <code>gen()</code> method should be checked to be set.
     */
    protected final int CHECK_VARS_GEN = 1;

    /**
     * The constant indicating which set of variables should be checked
     * in <code>checkVars</code> method.
     * CHECK_VARS_JAVACLOSE indicates that all mandatory variables used
     * in <code>javaclose()</code> method should be checked to be set.
     */
    protected final int CHECK_VARS_JAVACLOSE = 0;

    /**
     * The constant indicating which set of variables should be checked
     * in <code>checkVars</code> method.
     * CHECK_VARS_STORE indicates that all mandatory variables used
     * in <code>store()</code> method should be checked to be set.
     */
    protected final int CHECK_VARS_STORE = 2;

    /**
     * The constant indicating which set of variables should be checked
     * in <code>checkVars</code> method.
     * CHECK_VARS_SKIP indicates that nothing should be checked.
     */
    protected final int CHECK_VARS_SKIP = 3;

    /**
     * File level mandatory variable containing one or more authors for the given test
     * group. It should be specified after testcreate() and before first testcase in
     * the current file. If several authors are specified then they should be separated
     * by commas. For example:
     * <pre>
     * # author = "John Johnson, Jack Nicholson";
     * </pre>
     * The <code>author</code> variable may also be redefined for any other testcase.
     * Each time it is redefined, the new author will be added to the file authors'
     * summary list by subsequent <code>gen</code> method call.
     * The value of <code>author</code> should not be null or empty.
     */
    public String author = null;

    /**
     * This hash table contains file : author pairs for every
     * java class file.
     */
    protected Hashtable authorsHashTable = new Hashtable();

    /**
     * Directory level mandatory variable. It should be specified before testcreate().
     * The package of being tested class should be specified in
     * <code>testpackage</code>,
     * for example,
     * <pre>
     * # testpackage = "java.lang.ref";
     * </pre>
     * The value of <code>testpackage</code> should not be null or empty.
     */
    public String testpackage = null;

    /**
     * Being tested class should be specified in <code>testclass</code>.
     * For example,<br>
     * <pre>
     * # testclass = "WeakReference";<br>
     * </pre>
     * For inner classes:<br>
     * <pre>
     * # testclass = "Character.UnicodeBlock"<br>
     * </pre>
     * Directory level mandatory variable. It should be specified before testcreate().
     * The value of <code>testclass</code> should not be null or empty.
     */
    public String testclass = null;

    /**
     * Directory level variable containing some text description that should be placed
     * at the head of <code>htmlindex</code>.html file. This is an empty string by
     * default.
     */
    public String index = "";

    /**
     * Determines a title to put into html index file. This is an empty string
     * by default. If this variable is an empty string or null then the string
     * <i>Test Specifications and Descriptions for &lt;testclass&gt;</i> will appear in
     * the title. This variable is set to an empty string by default.
     */
    protected String indextitle = "";

    /**
     * Directory level variable, may contain links from <code>htmlindex</code>.html to additional
     * resources. For example, it may contain link(s) to additional standalone .html files.
     * The contents of this variable are copied into <code>htmlindex</code>.html
     * as is right after the list of methods under test. By default, it's value
     * is set to an empty string. The variable is optional.
     */
    public String addindex = "";

    /**
     * Directory level variable, determines the name of html file where to put tests
     * documentation and JavaTest description tables. The default value is "index".
     * This is mandatory variable.
     */
    public String htmlindex = "index";

    /**
     * The array containing strings representing possible testing types which
     * are used in javadoc comments for testcases and in <code>htmlindex</code>.html
     * testcase descriptions. The values are:
     * "Assertion testing", "Equivalence class partitioning", "Boundary value
     * analysis".
     */
    protected final String[] testTypeDescriptions = {"Assertion testing",
             "Equivalence class partitioning",
             "Boundary value analysis"};


    /**
     * The prefix of MemberSig that signals that test is serialTest
     */
    public static final String SERTEST_MEMBERSIG_PREFIX= "serialTest: ";

    /**
     * The prefix of MemberSig that signals that test is functional
     */
    public static final String FUNCTIONAL_MEMBERSIG_PREFIX=
            "Functional tests for ";

    /**
     * Test level variable, indicating test technique for the current testcase.
     * By default, the value is set to <code>ASSERTION_TESTING</code>
     * which means no special technique is used.
     * The variable may have values:
     * <ul>
     * <li> <code>ASSERTION_TESTING</code> - default
     * <li> <code>EQUIVALENCE_CLASS_PARTITIONING</code>
     * <li> <code>BOUNDARY_VALUE_ANALYSIS</code>
     * </ul>
     *  After every <code>skip/gen/store</code> call this variable is reset to
     *  default value.
     */
    public int testTechnique = ASSERTION_TESTING;

    /**
     * File level variable, contains a string of arguments passed to the test class
     * during execution. Initialized to getArgs(baseClass) if <code>executeArgs</code>
     * is set to null.
     */
    public String executeArgs = null;

    /**
     * Global switch, a string representing a method under test -
     * constructor/method/field signature or
     * specific assertion. If a specific assertion is specified then
     * description(method, description) should be called to specify assertion description.
     */
    public String method = null;

   /**
    * File level variable, specifies tests' base class.
    * Initialized to getDefaultBaseClass() i.e. "MultiTest"
    * by default. One can specify any different base class,
    * in this case MultiTest specific testcases
    * won't be generated.
    */
    public String baseClass = null;

   /**
    * Global switch, ("input", "output", ..) used in javadoc comments of java files.
    * It is generated automatically.
    */
    private String orientation = null;

   /**
    * Represents java file name.
    * For example,<br>
    * <pre>
    * #file = "getName";<br>
    * </pre>
    * Test source java file name will be "getNameTests.java".
    * File level mandatory variable.
    */
    public String file = null;

   /**
    * Contains the textual description for this file.
    * File level mandatory variable. Used in java file header.
    */
    public String filetitle = null;

   /**
    * Contains the textual description for this file.
    * File level optianal variable. Used in java file header after filetitle.
    */
    public String filepurpose = null;

   /**
    * Represents a string of test's additional import declarations.
    * File level optional variable. Used in java file.
    */
    public String imports = null;

   /**
    * Contains the code to be included into the test class body,
    * file level optional variable.
    * This might be useful to define additional methods, fields or inner classes
    * for the test class.
    */
    public String codeinclude = null;

   /**
    * Contains the code to be included outside the test class body,
    * file level optional variable.
    * This might be useful to define additional classes to be placed into
    * the same java source file.
    */
    public String classinclude = null;

   /**
    * Contains the additional keywords for the
    * given test to be placed into Java Test test description table. The full
    * set of keywords will be assembled from this variable value and default
    * keywords. File level optional variable. Set to null after each javaclose().
    *
    * @see #getDefaultKeywords
    */
    public String keywords = null;

   /**
    * Contains a list of java files which
    * should be compiled together with current test java source file.
    * File level optional variable. Set to empty after each javaclose().
    */
    public String uses = null;

   /**
    * Contains a space-separated list of Library ID's this test class
    * is dependent upon.
    * File level optional variable. Set to empty after each javaclose().
    */
    public String dependencies = null;

   /**
    * Contains a space-separated list of data files used by this test
    * File level optional variable. Set to empty after each javaclose().
    */
    public String iodata = null;

   /**
    * File level optional variable, if set, rmic compiler will be invoked
    * for this class, rmicClass field will be added to test description,
    * the returned value of getDefaultRmicKeywords() will be specified in
    * test description. Set to null after each javaclose().
    */
    public String rmicClass = null;

   /**
    * File level optional variable, if set, <code>remote</code> field will be added
    * to test description table. Set to null after each javaclose().
    */
    public String remote = null;

   /**
    * File level optional variable, if set, <code>context</code> field will be added
    * to test description table. Set to null after each javaclose().
    */
    public String context = null;

   /**
    * File level optional variable, if set, <code>selectIf</code> field will be added
    * to test description table. Set to null after each javaclose().
    */
    public String selectIf = null;

   /**
    * File level optional variable, if set, <code>timeout</code> field will be added
    * to test description table. Set to null after each javaclose().
    */
    public int timeout = -1;

   /**
    * Represents a list of files that need to
    * be installed into the same directory as class files for tests from the
    * current directory.<br>
    * These files should be present in the current directory with the 'rcFileExt'
    * (".rc" by default) suffix added. The files are copied by testcreate() and
    * javaclose(). File level optional variable. The variable is set to null after
    * testcreate() and each javaclose().
    */
    public String rcfiles = null;

   /**
    * Represents a list of file names that need to
    * be generated into the same directory as class files for tests from the
    * current directory.<br>
    * The contents of these files are defined in InlineData elements.
    * They names are taken from TargetName attribute of InlineData.
    * <br>
    * By default there is no special processing of such files in this
    * class.
    */
    public String rcfilesInline = null;

    /**
     * This vector contains all testCaseIDs defined in the jmpp source file
     */
    protected Vector testCaseIdFactory = new Vector();

   /**
    * The number of total testcases generated. This is informational variable,
    * read-only usage is assumed.
    */
    public int testCount = 0;

   /**
    * Legacy Extension for resource files to be copied. As of btools 1.3
    * resource files in workspace are no longer required to use this extenion.
    * By default ".rc".
    */
    protected String rcFileExt = ".rc";

    /**
     * Contains test case body in the curly braces.
     * Placed into java file (possibly after some preprocessing) by the gen() call,
     * discarded after each gen() and skip() calls, not discarded after store().
     * Test level mandatory variable.
     */
    public String code = null;

   /**
    * Test level obligatory variable containing an assertion under test
    * or parameters description if special test technique is used.
    * Along with <code>precondition</code> and <code>expected</code> plays key role
    * in test description generation.
    * Discarded after each gen(), skip(), store() calls. <br>
    *
    * If no special test technique was set then the values variable
    * should contain specification assertion under test. For Equivalence
    * Class Partitioning or Boundary Value Analysis tests this variable
    * should be used to specify input values or ranges of input values
    * as well as output values for a method or constructor under test. <br>
    *
    * See examples below demonstrating the usage of this variable with
    * different testing techniques:
    * <pre>
    * # method="public static void sortList(List list)";
    *
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # values="list: null";
    * # expected="Will throw NullPointerException or IllegalArgumentException";
    *
    * ...
    *
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # values="list: unsorted list";
    * # expected="Will sort list into ascending numerical order";
    *
    * ...
    *
    * # testTechnique = BOUNDARY_VALUE_ANALYSIS;
    * # values="list: an empty list, a list of one element";
    * # expected="The list will remain the same";
    *
    * ...
    *
    * # testTechnique = ASSERTION_TESTING;
    * # values = "method sort does not change the size of list";
    *
    * // another method
    *
    * # method = "public Process exec(String command, String[] envp)";
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # values = "
    * command: correct command
    * envp: some array of strings
    * output: new process object
    * #";
    * </pre>
    */
    public String values = null;

   /**
    * Pre-condition for the test, test level optional variable.
    * Discarded after each gen(), skip(), store() calls. <br>
    * See examples below demonstrating the usage of this variable:
    * <pre>
    * # method="public int getMaxFromList()";
    *
    * ...
    *
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # precondition = "This object has been initialized with unsorted list";
    * # values="output: the maximum element from the list";
    *
    * ...
    *
    * # testTechnique = BOUNDARY_VALUE_ANALYSIS;
    * # precondition = "This object has been initialized with an empty list";
    * # expected="will throw IllegalArgumentException";
    * </pre>
    */
    public String precondition = null;

   /**
    * Expected test results, test level optional variable.
    * Describes the results of method under test invocation other then expected
    * output value.
    * Discarded after each gen(), skip(), store() calls. <br>
    * See examples below demonstrating the usage of this variable:
    * <pre>
    * # method="public void checkPermission(Permission p)";
    *
    * ...
    *
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # precondition = "a security manager exists and there is a denied permission";
    * # values="p: denied permission";
    * # expected="Will throw SecurityException";
    *
    * ...
    *
    * # testTechnique = EQUIVALENCE_CLASS_PARTITIONING;
    * # precondition = "No security restrictions";
    * # values="p: some permission";
    * # expected="No exceptions will be thrown";
    * </pre>
    */
    public String expected = null;

   /**
    * Expected method output value, test level optional variable.
    * Describes the expected output value of method under test invocation.
    * Used only for ASSERTION_TESTING.
    * Discarded after each gen(), skip(), store() calls. <br>
    */
    public String expectedResultValue = null;

   /**
    * Internal variable, a StringBuffer for writing html files.
    */
    protected StringBuffer htmlBuffer = new StringBuffer();

    public String filePurpose = null;

   /**
    * Internal variable, a StringBuffer for writing java files.
    */
    protected StringBuffer javaBuffer;

   /**
    * Internal variable, a StringBuffer for writing headers of java files.
    */
    protected StringBuffer javaHeadBuffer;

   /**
    * Internal variable, a temporary StringBuffer for writing testcases java sources.
    */
    protected StringBuffer javaTestCasesBuffer;

   /**
    * A variable containing a string of default import declarations which
    * will be specified in all test java sources by default.
    */
    protected String defaultImports = null;

    /**
     * The variable stores value of <code>testclass</code> variable,
     * in case of inner class - the class name itself.
     */
    protected String test_class = null;

    /**
     * The variable stores the short name for test_class.
     * The name of test_class is squeezed down to a limit defined
     * in a property file as "filename.maxLength"
     */
    private String test_class_short = null;

    /**
     * Maximum file name length allowed for output files.
     */
    private int maxFileNameLength = 0;

    /**
     * The flag signals that new java file has just started (previous is closed).
     */
    protected boolean isFirstTestcase = true;

    /**
     * The vector contains all testcaseIDs of the current java file.
     */
    protected Vector testcaseIDs = new Vector();

    /**
     * the hashtable contains key: method_key (method name with all blank
     * space symbols removed), object: method name.
     */
    private Hashtable methodsHashTable = new Hashtable();

    /**
     * the hashtable contains key: method_key (method name with all blank
     * space symbols removed), object: a vector of TestDescriptions.
     */
    private Hashtable descriptionsHashTable = new Hashtable();

    /**
     * the hashtable contains key: method_key (method name with all blank
     * space symbols removed), object: a string which is method description.
     */
    private Hashtable methodsDescriptionsHashTable = new Hashtable();

    /**
     * the hashtable contains key: filename, object: new Object() - just for
     * something to be there.
     */
    private Hashtable filesHashTable = new Hashtable();

    /**
     * the hashtable contains key: baseclass name, object: a string which is
     * default arguments for baseclass.
     */
    private Hashtable baseClassesArgsHashTable = new Hashtable();

    /**
     * the hashtable contains key: baseclass name, object: a string which is
     * default import for baseclass.
     */
    private Hashtable baseClassesImportsHashTable = new Hashtable();

    /**
     * The vector of all method_keys.
     */
    protected Vector methodsVector = new Vector();

    /**
     * Hash containing name/contents pairs for auxiliar shared java files to be
     * expanded from jmpp source file
     */
    protected Hashtable extJavaFiles = new Hashtable();

    /**
     * The vector of all stored testcaseIDs, see store().
     */
    protected Vector tmpTestcasesVector = new Vector();

    /**
     * Contains name of currently generated source
     */
	protected String currentFileName;

    /**
     * Indicates whether testcreate() methdod has been called
     */
    private boolean testcreateCalled = false;

    private String htmlCopyrightNotice = "";
    

    /**
     * The main method to be implemented. Insert in this method all you want to
     * insert to your main java source.
     */
	public void makeTest() {}

    /**
     * This is the entry point for any jmpp library. It initializes jmmp file
     * expansion process. It sets default values of arguments and imports for
     * some test's base classes.
     */
    public void makeOut(){
        loadDefaults();
        outBuffer = new StringBuffer();
        makeTest();
        testclose();
        outBuffer = null;
    }

    /**
     * Used to obtain usage info for this library.
     * @return usage information for the library
     */
    public String getUsageInfo() {
        String res = super.getUsageInfo();
        res += "\nSupported <system options> are :\n"
              + "    -D" + configFilePropertyName + "=<library property file name>\n"
              + "    -D" + tckDestinationDir + "=<directory where to put resource files>\n";
        return res;
    }

    /**
     * Generates 'prolog' of the intermediate program, i.e.:<code><br>
     * package {templatePackage};<br>
     * public class {shortClassName} extends JmppLibAPI;<br></code>
     * @param out where to output the prolog
     * @param shortClassName short i-class name (without package)
     * @see #templatePackage
     */
    protected void generateProlog(PrintWriter out, String shortClassName) {
        out.println("package "+templatePackage+";\n");
        out.println("public class " + shortClassName + " extends "
                + getClass().getName()+" {");
	}

    /**
     * Generates 'epilog' of the intermediate program, i.e.
     * the <code>main()</code> method and closing brace "}" for intermediate
     * class declaration.
     * @param out where to output the epilog
     * @param shortClassName intermediate class name (without package)
     */
    protected void generateEpilog(PrintWriter out, String shortClassName) {
        generateMain(out, shortClassName);
        out.println("}");
	}

    /**
     * Creates directory with the name of the test (if it doesn't exist) and sets
     * output file to fileName. You have to call this method when you have several
     * source files in your test, to switch output to other file and to add (if
     * needed) that file to source list in HTML. Note, that there's no way to append
     * to previously created source. IOW, you can't switch to another source and then
     * switch back to the first one.
     * @param fileName name of the file to set the output to.
     * @param extension extension to be used with the new output file, e.g. `c`, `html`, etc.
     * @param dirName name of the directory to set the output to.
     */
	protected void setOutput(String fileName,
            String extension, String dirName) {
		super.setOutput(fileName);
		this.currentFileName = fileName+"."+extension;
		this.dirName = dirName;
	}

    /**
     * Loads the default properties for the library. If
     * the System property with the name <code>configClassPropertyName</code>
     * is set to non-empty string then the properties are taken from
     * the configuration class. Otherwise, properties are loaded from
     * a configuration file. Unless redefined, the name of a property class
     * is taken from System property "testgen.libAPI.propclass" and the name
     * of the configuration file is taken from property "testgen.libAPI.propfile".
     * If neither property class nor property file is specified, then the following
     * defaults will be loaded :
     * <table border=1>
     * <tr>
     * <th> property
     * <th> default value
     * <tr>
     * <td> test.copyrightNotice
     * <td> //TODO: Your copyright notice is here
     * <tr>
     * <td> test.packagePrefix
     * <td> tests.api
     * <tr>
     * <td> test.defaultImports
     * <td> java.io.PrintWriter, com.sun.javatest.Status
     * <tr>
     * <td> test.baseClasses
     * <td> com.sun.javatest.lib.MultiTest
     * <tr>
     * <td> test.defaultKeywords
     * <td> runtime positive
     * <tr>
     * <td> test.defaultRmicKeywords
     * <td> runtime positive
     * <tr>
     * <td> filename.maxLength
     * <td> 0 (means no length restriction)
     * </table>
     * @exception LibAPIException if the property file specified couldn't be
     *            found or read
     * @exception InvalidPropertyException if one or more properties are incorrectly
     *            defined in a property file.
     */
    protected void loadDefaults() {
        LibAPIProperties properties = getProperties();
        copyrights          = properties.getCopyrightNotice();
        packageHead         = properties.getPackagePrefix();
        defaultKeywords     = properties.getDefaultKeywords();
        defaultRmicKeywords = properties.getDefaultRmicKeywords();
        setDefaultImports(properties.getDefaultImports());
        baseClassesImportsHashTable.putAll(properties.getBaseClasses());
        baseClassesArgsHashTable.putAll(properties.getBaseClassExecuteArgs());
        maxFileNameLength   = properties.getMaxFileNameLength();
        htmlCopyrightNotice = properties.getHtmlCopyrightNotice();
    }


    protected LibAPIProperties getProperties() {
        LibAPIProperties properties = null;
        String propertyClass = System.getProperty(configClassPropertyName);
        if ((propertyClass != null) && (!propertyClass.equals(""))) {
            try {
                Class clazz = Class.forName(propertyClass);
                properties = (LibAPIProperties)clazz.newInstance();
            } catch (Exception e) {
                throw new LibAPIException("Unable to load configuration class "
                        + propertyClass + " : " + e);
            }
        } else {
            String propertyFileName = System.getProperty(configFilePropertyName);
            try {
                properties = new LibAPIPropertiesImpl(propertyFileName);
            } catch (FileNotFoundException fnfe) {
                throw new LibAPIException("Test Gen property file could not be found: "
                    + fnfe.getMessage());
            } catch (IOException ioe) {
                throw new LibAPIException("Can't read Test Gen property file : "
                    + ioe.getMessage());
            }
        }
        return properties;
    }

   /**
    * Starts tests generation process.
    * This method checks whether mandatory test level variables are set,
    * initializes some global variables, installs resource files. Should be
    * invoked from jmpp source file only once before any gen(), store(), skip() or javaclose() invocations.
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly
    */
    public void testcreate(){

        if (testcreateCalled) {
            throw new LibAPIException("Incorrect testcreate() usage - should be called only once");
        }

        String unsetMandatoryVars = "";

        if (testpackage == null || strRemovedBlankSpaces(testpackage).compareTo("") == 0) {
            unsetMandatoryVars += " 'testpackage'";
        }

        if (testclass == null || strRemovedBlankSpaces(testclass).compareTo("") == 0) {
            unsetMandatoryVars += " 'testclass'";
        } else {
            int i = -1;
            if ((i = testclass.lastIndexOf(".")) < 0){
                // testclass is not inner class
                test_class = testclass;
            } else {
                // testclass is inner class
                if (i == testclass.length() - 1){
                    throw new LibAPIException("incorrect 'testclass' variable value: " + testclass);
                }
                test_class = testclass.substring(i + 1);
            }
        }

        test_class_short = squeeze(test_class, maxFileNameLength);

        if (htmlindex == null || strRemovedBlankSpaces(htmlindex).compareTo("") == 0) {
            unsetMandatoryVars += " 'htmlindex'";
        }

        if (unsetMandatoryVars.compareTo("") != 0) {
            throw new LibAPIException("some mandatory variables are not set:"
                    +  unsetMandatoryVars);
        }

        installRCFiles();
        nullFileVariables();
        nullTestcaseVars();
        isFirstTestcase = true;
        testcreateCalled = true;
    }

   /**
    * Skips testcase generation.
    * This method is called when no testcase or testcase record should be actually generated.
    * skip() doesn't check for testcase-specific variables to be set, but it
    * cleans variables.
    * skip() is usually used instead of gen() when the testcase is no longer valid.
    */
    public void skip(){
        checkVars(CHECK_VARS_SKIP);
        incTestcaseCounter();
        nullTestcaseVars();
        return;
    }

   /**
    * Generates testcase.<br>
    * This method checks all necessary testcase-specific variables are set,
    * constructs testcase element which will be specified in testcases table
    * for the current method, assigns current testcase id to all testcase
    * records stored by a sequence of previous store(), adds testcase code
    * to the current java source file, increases global test case counter,
    * cleans all testcase-specific variables.
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly
    */
    public void gen(){
        checkVars(CHECK_VARS_GEN);
        testCaseIdFactory.add(testCaseID);
        addAuthor();
        // isFirstTestcase == true when new java file starts.
        if (isFirstTestcase == true){
            javaBuffer = new StringBuffer();
            javaTestCasesBuffer = new StringBuffer();
            testcaseIDs = new Vector();
            isFirstTestcase = false;
        }
        //testcaseIDs is a vector of all testcases which will be included into
        //current java file.
        testcaseIDs.add(testCaseID);
        if (tmpTestcasesVector.size() != 0){
            //tmpTestcasesVector is a vector containing all testcases
            //stored by store(), which should be assigned with the current
            //testcaseID names.
            SetTestcaseIDs(tmpTestcasesVector, testCaseID);
            for (int i = 0; i < tmpTestcasesVector.size(); ++i){
                TestcaseElement tc_tmp = (TestcaseElement)tmpTestcasesVector.get(i);
                Method method_obj_tmp = tc_tmp.getMethod();
                method_obj_tmp.AddTestCaseElement(tc_tmp);
            }
            tmpTestcasesVector.removeAllElements();
        }

        Method method_obj = null;
        try {
            method_obj = getCurrentMethod();
        } catch (MalformedValueException mve) {
            throw new LibAPIException(mve.getMessage());
        }
        if (method_obj.isAssertionTestingOnly()
                && testTechnique != ASSERTION_TESTING) {
            throw new LibAPIException(this, LibAPIException.TESTCASE_LEVEL,
                    "testTechnique is set incorrectly to "
                        + testTechnique + " for the " + method_obj + " tests");
        }

        TestcaseElement tc = constructTestcaseElement(method_obj);
        method_obj.AddTestCaseElement(tc);
        addTestcaseToJavaFile(tc);
        incTestcaseCounter();
        nullTestcaseVars();
        return;
    }

   /**
    * Adds testcase record to the testcase table for the given method.
    * The store() method is analogous to gen(), but it doesn't actually
    * generate test case code into the .java file.
    * Actual generation of test case code occurs with the first gen() call
    * following the sequence of store() calls.<br>
    * store() is usually used when several assertions are covered by one testcase.
    * This method adds testcase record to the current method's testcases table,
    * cleans all but <code>code</code> test level variables.
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly
    */
    public void store(){
        checkVars(CHECK_VARS_STORE);
        Method method_obj = null;
        try {
            method_obj = getCurrentMethod();
        } catch (MalformedValueException mve) {
            throw new LibAPIException(mve.getMessage());
        }
        TestcaseElement tc = constructTestcaseElement(method_obj);
        tmpTestcasesVector.add(tc);
        nullTestcaseVars(CHECK_VARS_STORE);
        return;
    }

   /**
    * Generates a test source java file along with test description.
    * The method should be called after generating
    * all testcases being included into current test java source file.
    * This method checks all necessary variables are set, see checkVars(),
    * completes test java source file generation, installs resource files.
    * cleans all file level variables.
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly
    */
    public void javaclose(){
        checkVars(CHECK_VARS_JAVACLOSE);
        if (isFirstTestcase == true){
            throw new LibAPIException(this, LibAPIException.FILE_LEVEL,
                    "no gen(); calls found for " + getClassFileName(file) + ".java");
        }
        generateJavaHead();
        generateJavaTail();
        constructTestDescription();
        isFirstTestcase = true;
        installRCFiles();
        nullTestcaseVars();
        nullFileVariables();
        return;
    }

   /**
    * Finishes test generation process creating all test java sources
    * and index html file. This method is called from makeOut().
    * Should not be invoked from jmpp source file.
    */
    protected void testclose(){
        generateAllJavaFiles();
        generateHTMLHead();
        generateHTML();
        generateHTMLTail();
    }

   /**
    * Sets default arguments for the given baseclass.
    * For example, for base class MultiTest the default argument
    * string is set to "-TestcaseID ALL". One can get default args for
    * a base class by getArgs(baseClass).
    * @param baseClass base class name for which to set default arguments
    * string.
    * @param defaultArgs a string with default arguments for the given base class.
    * @throws NullPointerException if either the <code>baseClass</code> or
    *         <code>defaultArgs</code> are null.
    */
    public void setArgs(String baseClass, String defaultArgs){
        baseClassesArgsHashTable.put(baseClass, defaultArgs);
        return;
    }

   /**
    * Returns default arguments for the given baseclass.
    * For example, for base class MultiTest the default argument
    * string is "-TestcaseID ALL". One can set default args for
    * a base class by setArgs(baseClass, defaultArgs).
    * This method is used in test description generation. The returned
    * string is used in test description field <code>executionArgs</code>.
    * @param baseClass base class name for which to get default arguments
    *        string.
    * @return a string of default arguments corresponding to the
    *         given base class.
    * @throws NullPointerException if the <code>baseClass</code> is null.
    */
    public String getArgs(String baseClass){
        String args = (String)baseClassesArgsHashTable.get(baseClass);
        return (args == null) ? "" : args;
    }

   /**
    * Generates additional <code>.java</code> files
    * with the same copyright notice and package statement as test class sources
    * have. The contents of these auxiliar files should be defined inside
    * <code>.jmpp</code> file. The contents shouldn't include package statement.
    * An arbitrary amount of auxiliar java files can be added in this way. However, if
    * one wants <code>fileName</code> be linked into test description, one have to mention
    * <code>fileName</code> in the <code>uses</code> variable declaration for an appropriate
    * file. This will allow <code>fileName</code> to appear in the "sources" field of
    * JavaTest test description.
    * @param fileName the name of an auxiliar file
    * @param contents the contents of an auxiliar java file, excluding copyright
    *        notice and package statement.
    * @exception NullPointerException if either <code>fileName</code> or
    *           <code>contents</code> is null.
    * @exception LibAPIException if <code>fileName</code> exceedes allowed file name
    *        length limit.
    */
    public void addJavaFile(String fileName, String contents) {
        checkFileNameLength(new File(fileName));
        extJavaFiles.put(fileName, contents);
    }

   /**
     * @return completely formatted copyright block, ready to be inserted
     *     to a java sourse file without modification.
     */
    protected String getJavaCopyrightBlock() {
        return getJavaCopyrightBlock(scInfo);
    }

    /**
     * @param sc_info source control information of the template
     * @return completely formatted copyright block, ready to be inserted
     *     to a java sourse file without modification.
     */
    protected String getJavaCopyrightBlock(String sc_info) {
        String crn = getCopyrights();

        if (crn == null || (crn = crn.trim()).length() == 0) {
            return "";
        }
        String res = "/*\n";

        if (sc_info != null) {
            res += " * " + sc_info + "\n";
            res += " *\n";
        }
        StringTokenizer tok = new StringTokenizer(crn, "\n");

        while(tok.hasMoreTokens()) {
	   String nextTok = tok.nextToken();
           if ("%separate%".equals(nextTok.trim())) {
	       if (tok.hasMoreTokens()) {
                   res += " */\n\n";
                   res += "/*\n";
	       }
           } else {
               res += " * " + nextTok + "\n";
           }	   
        }
        res += " */\n";
        return res;
    }

   /**
    * Generates all tests java source files.
    */
    protected void generateAllJavaFiles(){
        StringBuffer savedBuffer = outBuffer;
        TestDescription td = null;
        Vector v = null;
        //write all java files for test classes:
        Enumeration e = descriptionsHashTable.elements();
        while(e.hasMoreElements()){
            v = (Vector)e.nextElement();
            for (int i = 0; i < v.size(); ++i){
                td = (TestDescription)v.get(i);
                outBuffer = null;
                setOutput(td.fileName, "java", test_class_short);
                outBuffer = td.javaSource;
                closeOut();
            }
        }
        // write all auxiliar java files
        String fileName = null;
        e = extJavaFiles.keys();
        while (e.hasMoreElements()) {
             fileName = (String)e.nextElement();
             setOutput(fileName, "", test_class_short);
             currentFileName = fileName; // override JmpplibTest.setOutput()
             outBuffer = new StringBuffer();
             L(getJavaCopyrightBlock(scInfo));
             L("");
             L("package " + getTestPackageName(testpackage, testclass) + ";");
             L("");
             L((String)extJavaFiles.get(fileName));
             closeOut();
        }

        outBuffer = savedBuffer;
        return;
   }

  /**
   * Generates <code>htmlindex</code>.html file with method, testcase
   * and test descriptions. It calls generateTestcasesTable() and
   * generateTestDescriptions() methods to generate test cases and test
   * descriptions for all methods. This method is called from testclose().
   */
    protected void generateHTML(){
        String method_key = null, method_name = null, method_description = null, method_signature = null;
        Method method_obj;
        StringBuffer savedBuffer = outBuffer;
        outBuffer = htmlBuffer;
        // sort methods by their signature
        Object methodsArray[] = methodsVector.toArray();
        class ComparatorClass implements java.util.Comparator {
            public int compare(Object o1, Object o2) {
                Method method_obj1 = (Method)methodsHashTable.get(o1);
                Method method_obj2 = (Method)methodsHashTable.get(o2);
                int diff = method_obj1.getOrderPosition() - method_obj2.getOrderPosition();
                return diff != 0 ? diff : method_obj1.getSignature().compareTo(method_obj2.getSignature());
            }
        }
        java.util.Arrays.sort(methodsArray, new ComparatorClass());
        //generate top list of sorted references to methods in index.html file.
        generateHTMLTopListOfMethods(methodsArray);
        for(int i = 0; i < methodsArray.length; i++){
            method_key = (String)methodsArray[i];
            method_obj = (Method)methodsHashTable.get(method_key);
            method_name = method_obj.getName();
            method_signature = method_obj.getSignature();
            method_description = getMethodDescription(method_name);
            L("");
            L("");
            L("<P>");
            L("<HR>");
            L("<H3><A NAME=\"" + method_signature + "\"><CODE>" + method_name + "</CODE></A></H3>");
            L("");
            L("<H4>Description</H4>");
            L("<P>");
            L(method_description);
            generateTestcasesTable(EQUIVALENCE_CLASS_PARTITIONING, method_key);
            generateTestcasesTable(BOUNDARY_VALUE_ANALYSIS, method_key);
            generateTestcasesTable(ASSERTION_TESTING, method_key);
            generateTestDescriptions(method_key);
        }
        outBuffer = savedBuffer;
        return;
    }

    /**
     * Constructs class file name being based on the value of <code>file</code>
     * variable. If <code>testNamePostfix</code> is not <code>null</code> then
     * this method will add class name postfix (i.e. "Tests" by default) stored in
     * <code>testNamePostfix</code> variable to <code>file</code>. Otherwise
     * nothing will be added. The result will be squeezed to file name length limit
     * if one specified in a property file.
     * @param file   file name
     * @return class file name appended by testNamePostfix.
     * @see #testNamePostfix
     */
     protected String getClassFileName(String file){
         return squeeze(testNamePostfix == null ? file : file + testNamePostfix, maxFileNameLength-6);
     }

    /**
     * Returns default base class set by <code>setDefaultBaseClass</code> method.
     * The default value is "MultiTest".
     * Note: if the value set by <code>setDefaultBaseClass</code> is null,
     *       method <code>getDefaultBaseClass</code> will return an empty string.
     * @return default base class;
     * @see #setDefaultBaseClass
     */
     protected String getDefaultBaseClass(){
         return defaultBaseClass == null ? "" : defaultBaseClass;
     }

     /**
      * Sets default baseClass. This value is used in
      * test class header construction as a base class.
      * @param baseClass new default keywords string.
      * @see #getDefaultBaseClass
      */
     protected void setDefaultBaseClass(String baseClass) {
         defaultBaseClass = baseClass;
     }

    /**
     * Returns a string with copyright notice, i.e. the value
     * of the variable copyrights.
     * @return Copyright string stored in copyrights variable
     */
     protected String getCopyrights(){     
         return copyrights;
     }

    /**
     * Returns a string with default JavaTest keywords.
     * By default, the value of the variable defaultKeywords is "runtime positive".
     * To reset this value use <code>setDefaultKeywords</code> method.
     * Note: if the value set by <code>setDefaultKeywords</code> is null,
     * method <code>getDefaultKeywords</code> will return an empty string.
     * @return default JavaTest keywords;
     * @see #setDefaultKeywords
     */
     protected String getDefaultKeywords(){
         return defaultKeywords == null ? "" : defaultKeywords;
     }

     /**
      * Sets default keywords. This value assigns
      * to <code>keywords</code> during test description generation.
      * @see #getDefaultKeywords
      * @param keywords new default keywords string.
      */
     protected void setDefaultKeywords(String keywords) {
         defaultKeywords = keywords;
     }

     /**
      * Sets default rmic keywords. This value
      * assigns to <code>keywords</code> variable during test
      * description generation if <code>rmicClass</code> value
      * is not <code>null</code>.
      * @see #getDefaultRmicKeywords
      * @param rmicKeywords new default keywords string.
      */
     protected void setDefaultRmicKeywords(String rmicKeywords) {
         defaultRmicKeywords = rmicKeywords;
     }

    /**
     * Returns a string with default JavaTest rmic keywords,
     * by default, the value of the variable defaultRmicKeywords is
     * "compiler runtime positive".
     * To reset this value use <code>setDefaultRmicKeywords</code> method.
     * Note: if the value set by <code>setDefaultRmicKeywords</code> is null,
     * method <code>getDefaultRmicKeywords</code> will return an empty string.
     * @return default JavaTest rmic keywords;
     * @see #setDefaultRmicKeywords
     */
     protected String getDefaultRmicKeywords(){
         return defaultRmicKeywords == null ? "" : defaultRmicKeywords;
     }

    /**
     * Analyses the value of scInfo variable and tries to extract the date in
     * format \d+/\d+/\d+ (\d+ means one or more digits). If successful, returns
     * the first date extracted. Otherwise, returns an empty string.
     * @return revision date or an empty string
     */
     protected String getScDate(){
         if (scInfo == null) {
             return "";
         }
         StringTokenizer st = new StringTokenizer(scInfo);
         while (st.hasMoreTokens()) {
             String substr = st.nextToken();
             StringTokenizer st1 = new  StringTokenizer(substr, "/");
             if (st1.countTokens() == 3) {
                 boolean error = false;
                 // check that substr is d+/d+/d+
                 while (st1.hasMoreTokens()) {
                     char[] token = st1.nextToken().toCharArray();
                     for (int i=0; i<token.length; i++) {
                         if (!Character.isDigit(token[i])) {
                             error = true;
                         }
                     }
                 }
                 if (!error) {
                     return substr;
                 }
             }
         }
         return "";
     }

    /**
     * Returns the name of a test description the java and html files
     * are produced from. Ususally this is the name of jmpp file.
     */
     protected String getTestDescriptionName() {
        File f = new File(inDir, inName);
        try {
            return f.getCanonicalPath();
        } catch (IOException e) {
            return f.getAbsolutePath();
        }
     }

    /**
     * Returns a string representing a System variable name in which the
     * value of TCK destination directory is specified. By default it's
     * the value stored in variable tckDestinationDir - i.e.
     * "jck.destination.dir".
     * @return system variable name in which the path to TCK classes directory
     * should be specified;
     */
     protected String getTCKDstDirVarName(){
         return tckDestinationDir;
     }

   /**
    * Returns package name of the test's class. The name returned is constructed from
    * the value of variable packageHead, literal "api.", input parameter
    * <code>testpackage</code> and input parameter <code>testclass</code>.
    * @param testpackage package name of the class under test;
    * @param testclass   class under test;
    * @return test's class package name;
    * @see JmppLibTest#packageHead
    */
    protected String getTestPackageName(String testpackage, String testclass){
        return ((packageHead == null
                || packageHead.trim().compareTo("") == 0)? "" : packageHead + ".")
                + squeeze(testpackage + "." + testclass, maxFileNameLength);
    }

   /**
    * Returns import string for a class under test.
    * An empty string will be returned if:
    * - either <code>testpackage</code> or <code>testclass</code> is null or empty string;
    * - testpackage is java.lang
    * @param testpackage  the package name for the class under test
    * @param testclass    the class under test
    * @return 'import testpackage.testclass' or empty string.
    */

    protected String getImportForTestClass(String testpackage, String testclass) {
        if (testpackage == null || testclass == null || testpackage.equals("java.lang")
                || strRemovedBlankSpaces(testpackage).compareTo("") == 0
                || strRemovedBlankSpaces(testclass).compareTo("") == 0
                || testpackage.equals("lang")) {
            return "";
        }

        return "import " + testpackage + "." + testclass + ";";
     }

   /**
    * Generates table of testcases, corresponding to the given
    * testTechnique type (0,1 or 2) and method (method_key).
    * This method is called from generateHTML() in htmlBuffer context.
    * @param testing_type testing type i.e. Equivalence class partitioning,
    * Boundary value analysis, Assertion testing.
    * @param method_key method key of the method, for which we currently generate
    * table.
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly
    */
    protected void generateTestcasesTable(int testing_type, String method_key){
        Vector testcases = null;
        TestcaseElement testcaseElement = null;
        Method method_obj;
        int i = 0;

        method_obj = (Method)methodsHashTable.get(method_key);
        // get the Vector of testcases of this method, if it's empty, then no table generation;
        if ((testcases = method_obj.GetTestCases()).size() == 0){
            return;
        }
        // try to find testcases with 'testTechnique' = 'testing_type' for this 'method';
        if (method_obj.haveTestsForTestingType(testing_type) == false){
            return;
        }
        try {
           method_obj.checkValuesParams();
        } catch (MalformedValueException e){
            throw new LibAPIException(e.toString());
        }
        i = 0;
        L("");
        try {
            L("<H4>" + testTypeDescriptions[testing_type] + "</H4>");
        } catch (IndexOutOfBoundsException ioobe) {
            L("<H4>" + testTypeDescriptions[0] + "</H4>");
        }
        L("<TABLE BORDER=1 SUMMARY=\"Test case documentation table\">");
        L("<THEAD>");
        L("  <TR>");
        if (method_obj.havePreconditionValueFor(testing_type) == true){
           L("    <TH SCOPE=\"col\"> Pre-conditions </TH>");
        }
        if (testing_type == ASSERTION_TESTING){
            L("    <TH SCOPE=\"col\"> Assertion </TH>");
        } else {
            String th = method_obj.ParamsList2TableHeader();
            if (th != null && !th.trim().equals("")) {
                L(th);
            }
        }
        if (method_obj.haveOutputValueFor(testing_type) == true){
            L("    <TH SCOPE=\"col\"> Expected output value </TH>");
        }
        if (method_obj.haveExpectedValueFor(testing_type) == true){
            if (method_obj.haveOutputValueFor(testing_type) == true){
                L("    <TH SCOPE=\"col\"> Other expected results </TH>");
            } else {
                L("    <TH SCOPE=\"col\"> Expected results </TH>");
            }
        }
        L("    <TH SCOPE=\"col\"> Test Case ID </TH>");
        L("  </TR>");
        L("</THEAD>");
        i = 0;
        // call toString() method of all testcases of the 'method' with testTechnique=testing_type;
        while (i < testcases.size()){
            testcaseElement = (TestcaseElement)testcases.get(i);
            if (testcaseElement.testTechnique == testing_type){
                L("  <TR>");
                L(testcaseElement.toString());
                L("  </TR>");
            }
            ++i;
        }
        L("</TABLE>");
        return;
    }

   /**
    * Generates tables of test descriptions, corresponding
    * to the given method (method_key). It generates html references and
    * a list of testcases ("See test description here") for those test
    * descriptions which don't belong to the current method. For those
    * test descriptions which belong to the current method this method
    * generates them.
    * This method is called from generateHTML() in htmlBuffer context.
    * @param method_key method key of the method, for which we currently generate
    * test descriptions.
    */
    protected void generateTestDescriptions(String method_key){
        Vector testDecriptionsVector;
        Method method_obj;
        TestDescription tDescription = null;
        String strTestcasesIncluded = "", f = null, ftd = null;
        Vector filesVector = null, tcs = null;
        TestcaseElement tc = null;
        int i = 0, j = 0;

        testDecriptionsVector = (Vector)(descriptionsHashTable.get(method_key));
        if (testDecriptionsVector == null){
            testDecriptionsVector = new Vector();
        }
        method_obj = (Method)methodsHashTable.get(method_key);
        L("");
        L("<H4>Test Descriptions</H4>");

        while(i < testDecriptionsVector.size()){
           tDescription = (TestDescription)testDecriptionsVector.get(i);
           strTestcasesIncluded = tDescription.getTestcasesIncluded();
           L("<A NAME=\"" + tDescription.file + "\"></A>");
           L("<P>");
           L("Test cases included:<br>");
           L(strTestcasesIncluded);
           //L("<P>");
           L(tDescription.toString());
            ++i;
        }
        i = 0;
        if ((filesVector = method_obj.GetMethodFiles()).size() != 0){
            Collections.sort(filesVector);
            boolean first = true;
            String str = "";
            while(i < filesVector.size()){
                 f = (String)filesVector.get(i);
                 int k = 0;
                 boolean found = false;
                 while(k < testDecriptionsVector.size()){
                     ftd = ((TestDescription)testDecriptionsVector.get(k)).file;
                     if (ftd.compareTo(f) == 0){
                         found = true;
                         break;
                     }
                     ++k;
                 }
                 if (found == true) {
                     ++i;
                     continue;
                 }
                 tcs = method_obj.GetTestcasesForFile(f);
                 if (tcs == null || tcs.size() == 0){
                     ++i;
                     continue;
                 }
                 str += "<BR>\n";
                 if (first == true){
                     if (testDecriptionsVector.size() > 0) {
                         str += "See also:<BR>\n";
                     } else {
                         str += "See:<BR>\n";
                     }
                     first = false;
                 }
                 str += "<A HREF=\"#" + f + "\">Test descriptions</A>" + " for " +
                                   getClassFileName(f) + ".java\n";
                 j = 0;
                 while (j < tcs.size()){
                     tc = (TestcaseElement)tcs.get(j);
                     if (j == 0){
                         str += "  (" + tc.getTestcaseID();
                     } else {
                         str += ",\n   " + tc.getTestcaseID();
                     }
                     ++j;
                 }
                 if (j > 0){
                     str += ")";
                 }
                 ++i;
             }
             if (!str.equals("")) {
                 L(str);
             }
        }
        return;
    }

   /**
    * Generates a list of html references to all of the methods
    * which will be described in the html file.
    * This method is called from generateHTML() in htmlBuffer context;
    */
    protected void generateHTMLTopListOfMethods(Object[] methodsArray){
        String method_key = null;
        Method method_obj = null;
        L("<UL>");
        for(int i = 0; i < methodsArray.length; i++){
            method_key = (String)methodsArray[i];
            method_obj = (Method)methodsHashTable.get(method_key);
            if (method_obj != null){
    	        L("  <LI><A HREF=\"#" + method_obj.getSignature() + "\"><BIG><CODE>" + method_obj.getName() + "</CODE></BIG></A>");
            }
        }
        L("</UL>");
        L(addindex);
        return;
    }

    /**
     * An utility method to be used for formatting testcase code.
     * It removes any leading end-of-lines, of any, and pads every
     * line by 4 spaces.
     * @param codeStr The tetscase code string to be formatted
     * @return the formatted testcase code string.
     */
    private String formatCode(String codeStr) {
        if (codeStr == null || codeStr.equals("")) {
            return "";
        } else {
            StringBuffer buf = new StringBuffer(codeStr);
            // remove all leading spaces
            while (buf.length()>0 && !(buf.charAt(0) == '{')) {
                buf.deleteCharAt(0);
            }
            // remove all extra linefeeds and spaces/tabs between { and first line in testcase code
            while (buf.length()>1 && (buf.charAt(1) <= ' ')) {
                buf.deleteCharAt(1);
            }
            // New line should always be inserted after {, unless this is a VariantID comment:
            String varIDstr = "// VariantID";
            if (!(buf.length() > varIDstr.length()+2
                    && varIDstr.equals(buf.substring(1, varIDstr.length()+1)))) {
                buf.insert(1, "\n    ");
            } else {
                int i = varIDstr.length()+1;
                while ( buf.charAt(i) != '\n') {
                    i++;
                }
                while ( buf.length() > i && buf.charAt(i) <= ' ') {
                    buf.deleteCharAt(i);
                }
                buf.insert(i, "\n    ");
            }

            StringBuffer result = new StringBuffer();
            StringTokenizer st = new StringTokenizer(buf.toString(), "\n", true);
            int newLines = 0;
            boolean firstLine = true;
            while (st.hasMoreTokens()) {
                String line = st.nextToken();
                if (line.equals("\n")) {
                    // do not allow two empty lines
                    if (newLines < 2) {
                        result.append("\n");
                    }
                    newLines++;
                } else {
                    // remove all the trailing spaces
                    StringBuffer lineBuffer = new StringBuffer(line);
                    int length = lineBuffer.length();
                    while ( (length >= 1) && (lineBuffer.charAt(length - 1) <= ' ')) {
                        lineBuffer.deleteCharAt(length - 1);
                        length = lineBuffer.length();
                    }
                    if (length == 0) {
                        firstLine = false;
                        continue;
                    }
                    // substibute first tab with eight spaces
                    int tabIndex = lineBuffer.toString().indexOf("\t");
                    if ((tabIndex >= 0) && lineBuffer.substring(0, tabIndex).trim().equals("")) {
                        lineBuffer.deleteCharAt(tabIndex);
                        lineBuffer.insert(tabIndex, "        ");
                    }
                    line = lineBuffer.toString();
                    // line with content is padded by 4 spaces, if it is not
                    // the first line in a testcase code.
                    if (!firstLine) {
                        result.append("    ");
                    }
                    result.append(line);
                    firstLine = false;
                    newLines = 0;
                }
            }
            // remove all trailing linefeeds.
            while (result.length()>0 && result.charAt(result.length()-1) == '\n') {
                result.deleteCharAt(result.length()-1);
            }
            return result.toString();
        }


    }


   /**
    * Generates one testcase in the test java source.
    * It generates javadoc comments for the testcase and testcase code.
    * @param tc a testcase element that should be generated.
    */
    protected void addTestcaseToJavaFile(TestcaseElement tc){
        StringBuffer savedBuffer = outBuffer;
        outBuffer = javaTestCasesBuffer;
        
        // do not highlighting in case of assertion testing
        String values_str = (testTechnique != ASSERTION_TESTING) ?
                substituteStr(highlightParams(trim(values)), "\n", "\n     * "):
                substituteStr(                 trim(values), "\n", "\n     * ");
        String expected_str =
                substituteStr(trim(expected), "\n", "\n     * ");
        String precondition_str =
                substituteStr(trim(precondition), "\n", "\n     * ");
        String expectedResult_str = null;
        boolean isFunctional = Method.isFunctional(method);
        L("");
        L("    /**");
        if (!isFunctional) {
            L("     * " + testTypeDescriptions[testTechnique]);
        }
        if (testTechnique == EQUIVALENCE_CLASS_PARTITIONING ||
            testTechnique == BOUNDARY_VALUE_ANALYSIS){
            if(orientation != null) {
                L("     * with " + orientation + " values orientation");
            } else {
                String tmp_orientation = "";
                int output = tc.valuesHashTable.containsKey("output") ? 1 : 0;
                int numargs = tc.valuesHashTable.size();

                if (tc.precondition != null && tc.precondition.length() > 0) {
                    if (output > 0 && numargs > 1) {
                        tmp_orientation = "state, ";
                    } else if (output == 0 && numargs == 0) {
                        tmp_orientation = "state ";
                    } else {
                        tmp_orientation = "state and ";
                    }
                }

                if (numargs > output){
                    tmp_orientation += "input ";
                }
                if(output > 0){
                    tmp_orientation += (numargs > output ? "and " : "" ) + "output ";
                }
                if(tmp_orientation != null && !tmp_orientation.equals("")) {
                    L("     * with " + tmp_orientation + "values orientation");
                }
            }
        }
        if (testTechnique == ASSERTION_TESTING && expectedResultValue != null) {
            expectedResult_str =
                substituteStr(trim(expectedResultValue), "\n", "\n     * ");
        }

        if (!isFunctional) {
            L("     * for " + substituteStr(Method.description(method), "\n", "\n     * ") + ",");
        } else {
            L("     * " + substituteStr(Method.description(method), "\n", "\n     * ") + ",");
        }
        if (precondition_str != null && precondition_str.compareTo("") != 0) {
            L("     * <br><b>pre-conditions</b>: " + precondition_str + ",");
        }
        if (values_str != null && values_str.compareTo("") != 0) {
            L("     * " + values_str + ".");
        }
        if (expectedResult_str != null && expectedResult_str.compareTo("") != 0) {
            L("     * <br><b>Expected output value</b>: " + expectedResult_str);
        }
        if (expected_str != null && expected_str.compareTo("") != 0) {
            L("     * <br><b>Expected results</b>: " + expected_str);
        }
        L("     */");
        L("    public Status " + testCaseID + "() " + formatCode(code));
        outBuffer = savedBuffer;
    }

    protected String trim(String s) {
        StringTokenizer st = new StringTokenizer(s, "\n", true);
        StringBuffer output = new StringBuffer("");
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            if (line.equals("\n")) {
                output.append(line);
            } else {
                output.append(line.trim());
            }
        }
        while (output.length() > 0 && output.charAt(0) <= ' ') {
            output.deleteCharAt(0);
        }
        while (output.length() > 0 && output.charAt(output.length() - 1) <= ' ') {
            output.deleteCharAt(output.length() - 1);
        }
        return output.toString();
    }
  
   /**
    * Highlights javadoc comments that will be put
    * for every testcase in test class java source file. Namely, if the input
    * <code>str</code> has been recognized as multiline string containing
    * parameter/value definition pairs in the form: "parameter : value", then the
    * correspondent parameter will be marked as bold (i.e. &lt;b&gt; &lt;/b&gt; tags added)
    * and all pairs will be separated by &lt;br&gt; tag. In particular, this method is
    * used to process input values string for test case.
    * @param paramsString a string of javadoc comments to process.
    * @return new string containing processed javadoc comments.
    */
    protected String highlightParams(String paramsString) {
        StringBuffer sb = new StringBuffer();
        StringTokenizer tok = new StringTokenizer(paramsString, "\n");
        while(tok.hasMoreTokens()) {
            String line = tok.nextToken();
            int colonIndex = line.indexOf(':');
            if (colonIndex > 0  && isJavaIdentifierString(line.substring(0, colonIndex).trim())) {
                sb.append("<br><b>");
                sb.append(line.substring(0, colonIndex).trim());
                sb.append("</b>: ");
                sb.append(line.substring(colonIndex + 1).trim());
            } else {
                sb.append(line);
            }
            if (tok.hasMoreTokens()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks whether the given string could be
     * java identifier string or not.
     * @return true if the given string could be java identifier
     *         false otherwise.
     */
    protected boolean isJavaIdentifierString(String identifier) {
        boolean result = true;
        for (int i=0; i<identifier.length(); i++) {
            result &= (i==0) ?
                Character.isJavaIdentifierStart(identifier.charAt(i)):
                Character.isJavaIdentifierPart(identifier.charAt(i));
        }

        return result;
    }

    protected boolean isGeneratingJavaHead() {
        return (outBuffer == javaHeadBuffer);
    }

   /**
    * Generates test class java source.
    * It generates sccs keywords, copyrights, filetitle,
    * test class package name, defaults imports, imports for test base class,
    * test-specific imports, test class declaration.
    */
    protected void generateJavaHead(){
        javaHeadBuffer = new StringBuffer();
        StringBuffer savedBuffer = outBuffer;
        outBuffer = javaHeadBuffer;

        L(getJavaCopyrightBlock(null));
        L("");
        L("/*");
        L(" * " + (scInfo == null ? "" : " " + scInfo) + " " + getAuthor(file));
        L(" * Generated from : " + getTestDescriptionName().
                replaceAll("\\\\", "/"));
        L(" *");
        L(" * " + filetitle);
        if (filePurpose != null) {
            String filePurpose_str =
                substituteStr(trim(filePurpose), "\n", "\n * ");
            L(" * " + filePurpose_str);
        }
        L(" */");
        L("");
        L("package " + getTestPackageName(testpackage, testclass) + ";");

        StringBuffer imps = new StringBuffer();
        imps.append(getDefaultImports());
        imps.append(getImportForTestClass(testpackage, testclass));
        String imp = getBaseClassImports(baseClass);
        if (imp != null && !imp.equals("")) {
            imps.append("import " + imp + ";");
        }
        if (imports != null)  {
            imps.append(imports);
        }
        if (imps.length()>0) {
            L("");
            L(formatImports(imps.toString()));
        }
        String baseClassExtends;

        if (baseClass == null
                || baseClass.trim().compareTo("") == 0) {
            baseClassExtends = "";
        } else {
            baseClassExtends = " extends " + baseClass;
        }
        L("");
        L("public class " + getClassFileName(file) + baseClassExtends + " {");
        outBuffer = savedBuffer;
    }

    /**
     * An utility method used to format all import strings. All imports are
     * adjusted such that each is placed in a separate line and in the alphabetoical
     * order.
     * @param imports An imports string
     * @return formatted imports block.
     */
    private String formatImports(String imports) {
        StringBuffer result = new StringBuffer();
        StringTokenizer st = new StringTokenizer(imports.replace('\n', ' '), ";");
        TreeSet ts = new TreeSet();
        while (st.hasMoreTokens()) {
            String imp = st.nextToken();
            if (imp.indexOf("import") >= 0) {
                ts.add(imp.trim() + ";\n");
            }
        }
        Iterator it = ts.iterator();
        while (it.hasNext()) {
            result.append(it.next().toString());
        }
        return result.toString();
    }



   /**
    * Returns a string of default imports that will be put in the head of each subsequent
    * java source file. These imports are those unrelated to base class imports, but
    * necessary for java source of test class to be compiled successfully.
    * The default set of imports is JCK specified, since all JCK tests need to import
    * these classes:<br>
    * "import java.io.PrintWriter;\n <br>
    *  import com.sun.javatest.Status;"<br>
    * One can use setDefaultImports(defImports) to set default imports.
    * @return a string representing default imports or empty string if no
    * default imports was set;
    */
   protected String getDefaultImports(){
       if (defaultImports == null || defaultImports.trim().compareTo("") == 0){
           return "";
       }
       return defaultImports;
   }

   /**
    * Sets a string of default imports that will be put in the head of each subsequent
    * java source file. These imports are those unrelated to base class imports, but
    * necessary for java source of test class to be compiled successfully.
    * By default the set of imports is JCK specified, since all JCK tests need to import
    * these classes:<br>
    * "import java.io.PrintStream;\n <br>
    *  import com.sun.javatest.Status;"<br>
    * @param defImports a string of default imports to set;
    */
   protected void setDefaultImports(String defImports){
        defaultImports = defImports;
   }

   /**
    * Returns full class name string for the given base class.
    * For example, for class MultiTest it returns "com.sun.javatest.lib.MultiTest"
    * This string is used in test generation to build the input string in the head of
    * class source file.
    * The JmppLibAPI has one predefined base class:<br>
    * <ul>
    * <li>MultiTest - com.sun.javatest.lib.MultiTest<br>
    * </ul>
    * Use setBaseClassImports() routine to change these settings.
    * @param baseclass the class for which to get the full class name,
    * @return full class name string for the given base class, or
    *         empty string if no import for the <code>baseclass</code> was set.
    * @throws NullPointerException if the <code>baseclass</code> is null.
    */
    public String getBaseClassImports(String baseclass){
        String s = (String)baseClassesImportsHashTable.get(baseclass);
        return s == null ? "" : s;
    }

   /**
    * Sets full class name string for the base class.
    * For example, for class MultiTest, the "com.sun.javatest.lib.MultiTest"
    * string is set by default. This string is used in test generation to build the
    * import string in the head of test source file.
    * If either the baseclass or the full class name string are null or empty,
    * then no full class name for the given baseclass will be set.
    * @param baseclass the class for which to get full class name,
    * @param fullName full class name that corresponds to the given baseclass.
    * @see #getBaseClassImports
    */
    public void setBaseClassImports(String baseclass, String fullName){
        if (baseclass != null && baseclass.length() != 0 &&
            fullName != null && fullName.length() != 0){
            baseClassesImportsHashTable.put(baseclass, fullName);
        }
        return;
    }

   /**
    * Composes the contents of .java file, writes it to
    * <code>javaBuffer</code>:
    * first, adds <code>codeinclude</code>, then standalone interface main(),
    * contents of <code>javaTestCasesBuffer</code> - i.e. testcases itself,
    * then <code>classinclude</code>.
    */
    protected void generateJavaTail(){
        StringBuffer savedBuffer = outBuffer;
        outBuffer = javaBuffer;
        if(codeinclude != null) {
            L("");
            L(trimNewLines(codeinclude));
        }
        L("");
        L("    /* standalone interface */");
        L("    public static void main(String argv[]) {");
        L("        " + getClassFileName(file) + " test = new " + getClassFileName(file) + "();");
        L("        test.run(argv, System.err, System.out).exit();");
        L("    }");
        if(javaTestCasesBuffer != null) {
            LN(javaTestCasesBuffer.toString());
        }
        L("}");
        if(classinclude != null){
            L("");
            L(trimNewLines(classinclude));
        }
        javaHeadBuffer.append((Object) javaBuffer);
        javaBuffer = javaHeadBuffer;
        outBuffer = savedBuffer;
    }

    private String trimNewLines(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            StringBuffer buf = new StringBuffer(str);
            while (buf.length()>0 && buf.charAt(0) == '\n') {
                buf.deleteCharAt(0);
            }
            while (buf.length()>0 && buf.charAt(buf.length()-1) == '\n') {
                buf.deleteCharAt(buf.length()-1);
            }
            return buf.toString();
        }
    }

   /**
    * Generates the head of index.html file.
    * It generates <code>indextitle</code> and <code>index</code>
    * in the head of <code>.html</code> file.
    */
    protected void generateHTMLHead(){
        StringBuffer savedBuffer = outBuffer;
        outBuffer = htmlBuffer;
        L("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
        L("<HTML>");
        L("<HEAD>");
        L("<TITLE>" + indextitle + "</TITLE>");
        L("<!-- Changed by: " + getAuthorsSummary() + ", " + getScDate() + " -->");
        L("<!-- Generated from : " + getTestDescriptionName() + " -->");
        L("<!-- THIS FILE IS GENERATED AUTOMATICALLY.");
        L("     PLEASE CONTACT THE AUTHOR OF THE TESTS BEFORE ANY EDITING. -->");
        L("</HEAD>");
        L("");
        L("<BODY>");
        L("<H1>" + indextitle + "</H1>");
        L("<P>");
        L(index);
        L("<P>");
        L("<HR>");
        outBuffer = savedBuffer;
    }

   /**
     * @return relative path to the general JCK copyright file
     */
    protected String getCopyrightLink() {
        String link = System.getProperty(copyrightLinkPropertyName);

        if (link != null && !link.equals("")) {
            return "../" + link;
        } else {
            return null;
        }
    }

    /**
     * @return a copyright text ready to be inserted into generated
     *     html file.
     */
    protected String getHtmlCopyrightBlock() {
        if (htmlCopyrightNotice == null || htmlCopyrightNotice.equals("")) {
            return "";
        }
        StringBuffer buf = new StringBuffer(htmlCopyrightNotice);
        int start = htmlCopyrightNotice.indexOf("%");
        int end = htmlCopyrightNotice.lastIndexOf("%");
        if ((-1 < start) && (start < end)) {
            // link markers found, make a replacement
            String replacement = htmlCopyrightNotice.substring(start+1, end);
            String link = getCopyrightLink();
            if (link != null && !link.equals("")) {
                replacement = "<A HREF=\"" + link + "\">" + replacement + "</A>";
            }
            buf.replace(start, end+1, replacement);
        }
        return buf.toString();
    }

   /**
    * Generates the tail of <code>.html</code> file.
    * The tail will include modification date if the date keyword
    * has been defined in .jmpp file via scInfo variable.
    */
    protected void generateHTMLTail() {
        StringBuffer savedBuffer = outBuffer;
        outBuffer = htmlBuffer;
        L("");
        L("");
        L("<P>");
        L("<HR>");
        if (!getScDate().equals("")) {
            L("<EM>Last updated: " + getScDate() + "</EM><BR>");
        }
        LN(getHtmlCopyrightBlock());
        L("</BODY>");
        L("</HTML>");
        outBuffer = null;
        setOutput(htmlindex, "html", test_class_short);
        outBuffer = htmlBuffer;
        closeOut();
        indexHtml = outputFile.getAbsolutePath();
        outBuffer = savedBuffer;
   }

  /**
   * Increases <code>testCount</code> - the total number of
   * testcases generated.
   */
   protected void incTestcaseCounter(){
       ++testCount;
   }

   /**
    * Sets specific method description to be used
    * in test description html file. If no specific description for a method
    * was set then a default description is used. The generation error will be
    * reported if an attempt is made to use null or empty string for either
    * <code>method_str</code> or <code>description</code> parameter.
    * @see #getMethodDescription
    * @param method_str   method name for which description should be set.
    * @param description  description to be set for <code>method_str</code> method.
    * @exception LibAPIException if either <code>method_str</code> or
    *            <code>description</code> are null or empty string.
    */
    public void setMethodDescription(String method_str, String description){
        String method_key = Method.getMethodKey(method_str);
        if (method_str == null || method_key.compareTo("") == 0) {
            throw new LibAPIException("incorrect 'method' parameter in "
                    + "setMethodDescription(method, text) call : " + method_str);
        }

        if (description == null || description.compareTo("") == 0) {
            throw new LibAPIException("incorrect 'text' parameter in "
                    + "setMethodDescription(method, text) call : " + description);
        }

        methodsDescriptionsHashTable.put(method_key, description);

        return;
    }

    /**
     * Returns description previously set for the given method using
     * setMethodDescription routine. If no description was previously set then
     * the default description string will be returned.
     * The <code>getDefaultMethodDescription</code>
     * method is used to obtain the default description string.
     *
     * @param method  method name for which description should be returned
     * @return method description
     * @throws NullPointerException if the <code>method</code> is null.
     * @see #getDefaultMethodDescription
     */

    public String getMethodDescription(String method) {
        String method_key = Method.getMethodKey(method);

        if (methodsDescriptionsHashTable.containsKey(method_key)) {
            return (String)methodsDescriptionsHashTable.get(method_key);
        } else {
            return getDefaultMethodDescription(method);
        }
    }

    /**
     * Returns a default description string based on the given method
     * signature. If if the given string can not be identified as method, constructor
     * or field signature then this method will return just an empty string.
     *
     * @param method  method name for which description should be returned
     * @return default method description
     * @throws NullPointerException if the <code>method</code> is null.
     */
    protected String getDefaultMethodDescription(String method) {
        int start = method.indexOf('(');
        int end   = method.indexOf(')');
        if (method.lastIndexOf('(') != start
                || method.lastIndexOf(')') != end
                || start*end < 0) {
            return "";
        }

        if (start < 0
                && end < 0) {
            method = method.trim();
            if (   method.startsWith("package ")
                || method.startsWith("public class ")
                || method.startsWith("class ")) {

                //This method identified as a package or a class
                   return "Testing assertions specified by " + method +
                            " description";
            } else if (method.startsWith("serialization")
                    || method.startsWith("deserialization") ) {
                return "Testing " + method;
            } else if (method.startsWith(FUNCTIONAL_MEMBERSIG_PREFIX)) {
                return "Checking that tested functionality is correct";
            } else {
                //This method identified as a field
                   return "Checking for the value of field " + method +
                                     " being equal to specified.";
            }

        }

        String temp = method.substring(0, start).trim();
        int lastSepPos = temp.lastIndexOf(' ');
        String name = lastSepPos < 0 ? temp : temp.substring(lastSepPos + 1);

        String method_descr =
            name.equals(test_class) || name.equals(testclass)? "constructor" : "method";

        return "Domain testing of input and output conditions, and " +
                "external\npre-conditions for class " + testclass +
                ",\n" + method_descr + " <CODE>" + method + "</CODE>.";
    }

   /**
    * Checks that all necessary variables are set.
    * it is called from gen(), store(), skip() and javaclose(), to be sure
    * that all necessary variables are set properly. Besides, it sets
    * default values to some variables which are not mandatory, if no values are
    * specified (i.e. their values are null or empty string).
    * @param varsToCheck defines which variables to check.
    *     possible values:
    *     CHECK_VARS_GEN - check all mandatory variables used in gen();
    *     CHECK_VARS_JAVACLOSE - check some mandatory variables used in javaclose();
    *     CHECK_VARS_STORE - check some mandatory variables used in store();
    *     CHECK_VARS_SKIP - check only that testcreate() has been already called
    * @exception LibAPIException if necessary variables haven't been set
    *            correctly, or testcreate() hasn't been called before.
    */
    

    protected void checkVars(int varsToCheck){
        if (!testcreateCalled) {
            throw new LibAPIException("testcreate() should be called before invoking test generation API");
        }
        if (varsToCheck == CHECK_VARS_SKIP) {
            return;
        }

        String errorMsg = "";
        if (file == null || strRemovedBlankSpaces(file).compareTo("") == 0){
            errorMsg += "'file' variable is not set \n";
        } else if (filesHashTable.containsKey(file) == true) {
            errorMsg += "multiple occurence of class: " + getClassFileName(file) + " \n";
        }
        if (filetitle == null || strRemovedBlankSpaces(filetitle).compareTo("") == 0){
            errorMsg += "'filetitle' variable is not set \n";
        }
        if (index == null){
            index = "";
        }
        if (addindex == null){
            addindex = "";
        }
        if (indextitle == null || strRemovedBlankSpaces(indextitle).compareTo("") == 0){
            indextitle = "Test Specifications and Descriptions for " + testclass;
        }

        if (method == null || strRemovedBlankSpaces(method).compareTo("") == 0){
            errorMsg += "'method' variable is not set \n";
        }

        if (author == null
                || author.trim().compareTo("") == 0) {
            errorMsg += "'author' variable is not set \n";
        }

        if (testTechnique != EQUIVALENCE_CLASS_PARTITIONING &&
            testTechnique != BOUNDARY_VALUE_ANALYSIS &&
            testTechnique != ASSERTION_TESTING){
            errorMsg += "'testTechnique' variable is set incorrectly to  " +
                    testTechnique + "\n";
        }

        if (varsToCheck == CHECK_VARS_GEN
                || varsToCheck == CHECK_VARS_STORE) {
            if (testCaseID == null
                    || !isJavaIdentifierString(testCaseID)) {
                errorMsg += "'testCaseID' variable is set incorrectly to "
                        + testCaseID + "\n";
            } else if (testCaseIdFactory.contains(testCaseID)){
                errorMsg += "Such a 'testCaseID' variable is already defined : "
                    + testCaseID + "\n";
            } else if (currentTestCaseID != null
                    && !testCaseID.equals(currentTestCaseID)) {
                errorMsg += "An attempt to reset testCaseID "
                    + "between store() and gen() calls\n"
                    + "testCaseID : " + testCaseID + "\n"
                    + "current testCaseID : " + currentTestCaseID + "\n";
            }

        }

        if (varsToCheck != CHECK_VARS_JAVACLOSE) {
            if (expected == null){
                expected = "";
            }

            if (values == null || strRemovedBlankSpaces(values).compareTo("") == 0){
                if (testTechnique == ASSERTION_TESTING){
                    errorMsg += "'value' variable is set incorrectly for testing type = 0 \n";
                } else {
                     values = "";
                }
            }
            if (precondition == null){
                precondition = "";
            }
            if (varsToCheck == CHECK_VARS_GEN){
                if (code == null){
                    errorMsg += "'code' variable is set to null";
                } else if (code.trim().compareTo("") == 0){
                    errorMsg += "'code' variable is set to empty string";
                }
            }
        }
        if (uses == null) {
            uses = "";
        }

        if (baseClass == null) {
            baseClass = getDefaultBaseClass();
        }
        if (executeArgs == null){
            if ((executeArgs = (String)baseClassesArgsHashTable.get(baseClass)) == null){
                executeArgs = "";
            }
        }
            
        
        if (errorMsg.compareTo("") != 0){
            throw new LibAPIException(this, (varsToCheck == CHECK_VARS_JAVACLOSE) ?
                    LibAPIException.FILE_LEVEL : LibAPIException.TESTCASE_LEVEL,
                        errorMsg);
        }
        return;
    }
    

    /**
     * Sets file level variables to the default values:
     * <code>rcfiles</code>, <code>file</code>, <code>filetitle</code>,
     * <code>codeinclude</code>, <code>classinclude</code>, <code>imports</code>,
     * <code>keywords</code>, <code>uses</code>,
     * <code>rmicClass</code>, <code>remote</code>.
     */
     protected void nullFileVariables(){
         author = null;
         rcfiles = null;
         file = null;
         filetitle = null;
         filepurpose = null;
         codeinclude = null;
         classinclude = null;
         imports = null;
         keywords = null;
         baseClass = null;
         executeArgs = null;
         uses = "";
         rmicClass = null;
         remote = null;
         dependencies = null;
         context = null;
         selectIf = null;
         timeout = -1;
         iodata = null;
         testGroupTargetSpecs.clear();
         cleanTestGroupAttrElems();
         cleanTestGroupRequiredResource();
         return;
     }

    /**
     * Sets test level variables to the default values:
     * <code>code</code>, <code>values</code>, <code>expected</code>,
     * <code>precondition</code>.
     * This method is called from gen() and skip() to set some test level variables to
     * their default values.
     */
     protected void nullTestcaseVars(){
         code = null;
         testTechnique = ASSERTION_TESTING;
         values = null;
         expected = null;
         expectedResultValue = null;
         precondition = null;
         testCaseID = null;
         currentTestCaseID = null;
         testcaseTargetSpecs.clear();
         cleanTestcaseAttrElems();
         cleanTestcaseRequiredResource();
         return;
     }

    /**
     * Sets test level variables to the default values:
     * <code>values</code>, <code>expected</code>, <code>precondition</code>.
     * This method is called from store() to set some test level variables to
     * their default values.
     */
     protected void nullTestcaseVars(int type) {
         testTechnique = ASSERTION_TESTING;
         if (type == CHECK_VARS_STORE) {
             values = null;
             expected = null;
             precondition = null;
             if (currentTestCaseID == null) {
                 currentTestCaseID = testCaseID;
             }
         }
         return;
     }

    /**
     * Identificator string to be assigned for the test case.
     * The same string will be used to form the name of a method containing
     * the code for the test case.<br>
     * JmppLibAPI does not put any restriction on the test case identification
     * string format. The only requirement is that all the testcases inside
     * .jmpp file should have unique test case ID's. However, there are certain
     * naming conversions followed by JmppLibAPI wizard when it creates new test
     * case template. According to these conventions, the testCaseID for new
     * test case is constructed as follows:<br>
     * testCaseID = &lt;testclass&gt;&lt;testTechnique&gt;&lt;testcase number&gt;<br>
     * where:<br>
     * <ul>
     * <li> testclass - the name of the class under test
     * <li> testTechnique - integer number (0-2) denoting current test technique
     * <li> testcase number - test case number for the given test technique
     * </ul>
     * It is recommended to follow these naming conventions.
     */
    public String testCaseID = null;

    private String currentTestCaseID = null;

   /**
    * Checks whether there is already a Method object in
    * <code>methodsHashTable</code>, corresponding to the key constructed
    * from the current value of variable <code>method</code>.
    * If <code>methodsHashTable</code> contains such key and corresponding
    * Method object, then return this object, if no then create new Method
    * object, put it to <code>methodsHashTable</code>.
    * @return Method object corresponding to the key, constructed from
    *         the current value of variable <code>method</code>.
    * @exception MalformedValueException if the current value of <code>method</code>
    *        is not a valid method signature.
    */
    protected Method getCurrentMethod() throws MalformedValueException {
        Method method_obj = null;
        String method_key = Method.getMethodKey(method);
        if (methodsHashTable.containsKey(method_key) == false){
            method_obj = new Method(new String(method), testclass);
            methodsHashTable.put(method_key, method_obj);
            methodsVector.add(new String(method_key));
        } else {
            method_obj = (Method)methodsHashTable.get(method_key);
        }
        return method_obj;
    }

   /**
    * Constructs new TestcaseElement with current values of variables:
    * <code>testTechnique</code>, <code>values</code>, <code>precondition</code>,
    * <code>expected</code>, <code>file</code>, with current <code>testcaseID</code>,
    * and belonging to <code>method_obj</code> method.
    * @param method_obj Method object to which this TestcaseElement belongs.
    * @return newly constructed TestcaseElement object, which then will be added
    * to the vector of all TestcaseElements of method_obj.
    */
     protected TestcaseElement constructTestcaseElement(Method method_obj) {
         TestcaseElement testcaseElement = null;
         testcaseElement = new TestcaseElement(testTechnique, testCaseID,
                                        method_obj, values, precondition, expected,
                                        expectedResultValue, file);
         return testcaseElement;
     }

    /**
     * Constructs new TestDescription object, adds it to
     * <code>testDescriptionsVector</code>,
     * corresponding to the current value of <code>method</code> variable.
     * If there is no a vector, corresponding to the <code>method</code>,
     * then create a vector, put it to the hash table
     * <code>descriptionsHashTable</code> with key, constructed from
     * <code>method</code>.
     */
     protected void constructTestDescription() {
         String method_key = Method.getMethodKey(method);
         Vector testDescriptionsVector = null;

         TestDescriptionIR tdIR = createTestDescriptionIR();
         TestDescription testDesription = new TestDescription(method_key, testcaseIDs,
              file, getClassFileName(file), tdIR);
         testDesription.javaSource = javaBuffer;
         if ((testDescriptionsVector = (Vector)(descriptionsHashTable.get(method_key))) == null){
             testDescriptionsVector = new Vector();
             descriptionsHashTable.put(method_key, testDescriptionsVector);
         }
         testDescriptionsVector.add(testDesription);
         filesHashTable.put(file, new Object());
         return;
     }


    /**
     * TestDescription table field list
     */
     protected static final String[] tdFields = {
         TestDescriptionIR.TITLE, TestDescriptionIR.SOURCE, TestDescriptionIR.CLASS,
         TestDescriptionIR.KEYWORDS, TestDescriptionIR.EXECUTE_ARGS,
         TestDescriptionIR.RMIC_CLASSES,  TestDescriptionIR.REMOTE,
         TestDescriptionIR.CONTEXT,  TestDescriptionIR.SELECT_IF
     };

    /**
     * Constructs the TestDescriptionIR object.
     */
     protected TestDescriptionIR createTestDescriptionIR() {

         StringBuffer str_buf = new StringBuffer(getTestPackageName(testpackage, testclass));
         str_buf.append(".");
         str_buf.append(getClassFileName(file));
         String execClass = str_buf.toString();

         if (keywords == null) {
             keywords = "";
         }

         keywords = (rmicClass == null) ?
               getDefaultKeywords() + " " + keywords:
               getDefaultRmicKeywords() + " " + keywords;
         keywords = keywords.trim();

         ArrayList sources = new ArrayList();
         sources.add(getClassFileName(file) + ".java");
         StringTokenizer st = new StringTokenizer(uses);
         while (st.hasMoreTokens()){
             sources.add(st.nextToken());
         }

         TestDescriptionIR tdIR = new TestDescriptionIR();
         tdIR.setFields(tdFields);
         tdIR.setOutputFormat(TestDescriptionIR.JMPPLIBAPI_OUTPUT);
         tdIR.add(TestDescriptionIR.TITLE,         filetitle);
         tdIR.addRef(TestDescriptionIR.SOURCE,     sources);
         tdIR.add(TestDescriptionIR.CLASS,         execClass);
         tdIR.add(TestDescriptionIR.KEYWORDS,      keywords);
         if (executeArgs != null && executeArgs.compareTo("") != 0) {
             tdIR.add(TestDescriptionIR.EXECUTE_ARGS,  executeArgs);
         }
         if (rmicClass != null && rmicClass.compareTo("") != 0) {
             tdIR.add(TestDescriptionIR.RMIC_CLASSES,  rmicClass);
         }
         if (remote != null && remote.compareTo("") != 0) {
             tdIR.add(TestDescriptionIR.REMOTE,        remote);
         }
         if (context != null && context.compareTo("") != 0) {
             tdIR.add(TestDescriptionIR.CONTEXT,       context);
         }
         if (selectIf != null && selectIf.compareTo("") != 0) {
             tdIR.add(TestDescriptionIR.SELECT_IF,     selectIf);
         }
         if (timeout > 0) {
             tdIR.add(TestDescriptionIR.TIMEOUT,     "" + timeout);
         }
         return tdIR;
     }

    /**
     * Assigns <code>testcaseID</code> field of all
     * TestcaseElements in <code>tmpTestcasesVector</code>
     * vector to <code>testcaseID</code>. The method is called from gen() method.
     * @param tmpTestcasesVector a vector of TestcaseElements,
     * @param testcaseID a String which will be assigned to
     * <code>testcaseID</code> field of all
     * TestcaseElements in <code>tmpTestcasesVector</code> vector.
     */
     private void SetTestcaseIDs(Vector tmpTestcasesVector, String testcaseID){
         int i = 0;
         TestcaseElement tc;
         if (tmpTestcasesVector == null){
             return;
         }
         while (i < tmpTestcasesVector.size()){
             tc = (TestcaseElement)tmpTestcasesVector.get(i);
             tc.testcaseID = testcaseID;
             ++i;
         }
          return;
     }

     /**
      * Squeeze a string down to len characters by deleting a sequence of lowercase
      * characters, starting from left. If no acceptable sequence is found, then
      * the string would be simply truncated down to len characters.
      * If the str consists of several strings separated by ".", when each part
      * will be squeezed.
      */
     private String squeeze(String str, int len) {
        if (len <= 0 || str == null) {
            return str;
        }
        StringTokenizer tok = new StringTokenizer(str, ".");
        StringBuffer result = new StringBuffer();
        while (tok.hasMoreTokens()) {
            StringBuffer buffer = new StringBuffer(tok.nextToken());
            while (buffer.length() > len) {
                int i = 1;
                int lowerCaseStart = 0;
                int lowerCaseEnd = 0;

                // find first occurence of lowercase character
                while (i < buffer.length() && !Character.isLowerCase(buffer.charAt(i))) {
                    i++;
                }

                if (i >= buffer.length()) {
                    break; // lowerCaseStart hasn't been found
                } else {
                    lowerCaseStart = i;
                }

                // find first occurence of non-lowercase character
                while (i < buffer.length() && Character.isLowerCase(buffer.charAt(i))) {
                    i++;
                }

                if (i >= buffer.length()) {
                    break; // lowerCaseEnd hasn't been found
                } else {
                    lowerCaseEnd = i;
                }
                buffer.delete(lowerCaseStart, lowerCaseEnd);
            }

            if (buffer.length() > len) { // attempt to squeeze fails, truncate
                buffer.delete(len, buffer.length());
            }
            result.append((result.length() > 0 ? "." : "") + buffer.toString());
        }
        return result.toString();
    }


    private void checkFileNameLength(File file) {
        if (maxFileNameLength > 0) {
            String fileName = file.getName();
            if (fileName.length() > maxFileNameLength-1) {
                throw new LibAPIException("File name length for : " + fileName
                    + " exceedes allowed limit " + maxFileNameLength);
            }
        }
    }

    protected String getDestinationDir() {
        return System.getProperty(getTCKDstDirVarName());
    }


    /**
     * Installs specified in <code>rcfiles</code> variable files from
     * current directory to TCKBUILDDIR/classes/testpackage/testclass directory.
     * It checks whether the system property jck.destination.dir set by 
     * -Djck.destination.dir="..." in java start command line is specified. 
     * <br>
     * Use of legacy naming convention for resouce files in workspace (.rc) is 
     * still supported but no longer required as of btools 1.3. If a file without 
     * the legacy extension is found, then it is used. Otherwise the btools will 
     * append the extension to the resouce files' name when attempting to locate 
     * the source of the resource file.  If the legacy format is used, the 
     * extenstion will be stripped of the name the generated resource file. 
     * <br> 
     * Since one can specify resource file with a subdirectory the file will be 
     * copied into TCKBUILDDIR/classes/testpackage/testclass directory, the same 
     * subdirectory. 
     * @exception LibAPIException in case of any problems installing rc files.
     */
     protected void installRCFiles(){
         String rcfile = null;
         String rcDestDir = null;
         //check the value of rcfiles variable is not empty.
         if (rcfiles == null || rcfiles.trim().compareTo("") == 0){
             return;
         }
         String jckDestDir = getDestinationDir();
         //check variable named getTCKDstDirVarName() is specified.
         if (jckDestDir == null || jckDestDir.trim().equals("")) {
             throw new LibAPIException("'rcfiles' are specified but " + getTCKDstDirVarName() +
                                  " system variable is not specified");
         }
         rcDestDir = jckDestDir + File.separator + getTestPackageName(testpackage, testclass).replace('.', File.separatorChar) + File.separator;
         //get rc files separately
         StringTokenizer st = new StringTokenizer(rcfiles);
         while (st.hasMoreTokens()){
             rcfile = st.nextToken();
             // rcfile may be not a simple filename, but may also include subdirs.
             File f = new File(rcfile);
             String rcParent = (f.getParent() == null) ? "" : f.getParent();
             String rcName = (f.getParent() == null) ? rcfile : f.getName();
             //check whether resource file to copy exists.
             File rscFile = new File(inDir + File.separator + rcParent + File.separator +rcName);
             File rscFileLegacyFormat = new File(inDir + File.separator + rcParent + File.separator +rcName +rcFileExt);
             File file2copy = null;
             if (rscFile.exists()){
                 file2copy = rscFile;
             }
             else {
                 file2copy = rscFileLegacyFormat;
             }
             if (!file2copy.exists()){
                 throw new LibAPIException("resource file to copy doesn't exist. Can not find " + rscFile + 
                         " or " + rscFileLegacyFormat);
             }
             checkFileNameLength(file2copy);
             File dir2create = new File(rcDestDir + rcParent);
             try {
                 if (!dir2create.exists()){
                     mkdirs(dir2create);
                 }
             } catch (SecurityException se){
                 throw new LibAPIException("Unable to create dir, check permissions: " + dir2create.getPath());
             }
             File file2create = new File(dir2create, rcName);
             installRCFile(file2copy, file2create);
         }
         return;
     }

    protected void installRCFile(File src, File dst) throws LibAPIException {
             FileInputStream fis = null;
             FileOutputStream fos = null;
             try {
            fis = new FileInputStream(src);
             } catch (FileNotFoundException fe){
            throw new LibAPIException("Unable to open for reading: " + src.getPath());
             } catch (SecurityException se){
            throw new LibAPIException("Unable to read file, check permissions: " + src.getPath());
             }
             try {
            fos = new FileOutputStream(dst);
             } catch (FileNotFoundException fe){
            throw new LibAPIException("Unable to open for reading: " + dst.getPath());
             } catch (SecurityException se){
            throw new LibAPIException("Unable to read file, check permissions: " + dst.getPath());
             }
             try {
                 byte[] data = new byte[fis.available()];
                 fis.read(data);
                 fos.write(data);
                 fos.flush();
             } catch (IOException ioe){
            throw new LibAPIException("IOException reading " + src.getPath() +
                                      " or writing " + dst.getPath());
             } finally {
                 try {
                     if (fis != null) {
                         fis.close();
                     }
                     if (fos != null) {
                         fos.close();
                     }
                 } catch (IOException io){
                throw new LibAPIException("IOException closing " + src.getPath() +
                                          " or " + dst.getPath());
                 }
             }
         }

    /**
     * Removes all blank space symbols from input String
     * @param      string input string.
     * @return     String - input string with all blank spaces removed.
     * @throws     NullPointerException if <code>string</code> is
     *             <code>null</code>.
     */
     protected static String strRemovedBlankSpaces(String string){
         StringBuffer strbuf = new StringBuffer();
         for (int i=0; i<string.length(); i++) {
             if (!Character.isWhitespace(string.charAt(i))) {
                 strbuf.append(string.charAt(i));
             }
         }
         return strbuf.toString();
     }

    /**
     * Replaces all multiple blank space chains from input String with single space symbol.
     * @param      string input string.
     * @return     string without multiple blank spaces chains.
     * @throws     NullPointerException if <code>string</code> is
     *             <code>null</code>.
     */
     protected static String strRemovedDoubleSpaces(String string) {
         StringBuffer strbuf = new StringBuffer();
         String value = string.trim();
         boolean isWhiteSpace = false;
         for (int i=0; i<value.length(); i++) {
             if (!Character.isWhitespace(value.charAt(i))) {
                 isWhiteSpace = false;
                 strbuf.append(value.charAt(i));
             } else {
                 if (!isWhiteSpace) {
                    strbuf.append(' ');
                    isWhiteSpace = true;
                 }
             }
         }
         return strbuf.toString();
     }

    /**
     * Substitutes the <code>base_string</code> with <code>'with'</code>
     * string, using <code>'what'</code> as delimiter.
     * @param base_string a string to process;
     * @param what delimiter;
     * @param with a string to substitute with;
     * @return new string with all substring substitutes.
     */
     protected String substituteStr(String base_string, String what, String with){
         StringBuffer str_buf = new StringBuffer("");
         String token;
         if (base_string == null){
             return "";
         }
         StringTokenizer st = new StringTokenizer(base_string, what, true);
         while (st.hasMoreTokens()){
             token = st.nextToken();
             str_buf.append(token.equals(what) ? with : token);
         }
         return str_buf.toString();
     }

    /**
     * Creates new buffered writer for new file writing.
     * For internal use only.
     * @return newly created BufferedWriter.
     * @throws IOException
     */
     public BufferedWriter createOutputWriter() throws IOException {
         String dir2Create = outputDir;
         if (outputDir==null || outputDir.equals("")){
             dir2Create = ".";
         }
         if (dirName!=null && dirName.length()>0){
             dir2Create += File.separator+dirName;
         }
         File d = new File (dir2Create);
         if(!d.exists() && !mkdirs(d)){
             throw new IOException("can't create dir: "+dir2Create);
         }
         if (currentFileName == null || currentFileName.equals("")) {
             currentFileName = inName + ".out";
         }
         outputFile = new File(dir2Create + File.separator + currentFileName);
         Vector v = null;
         if (test_class != null) {
             v = (Vector)intfAllTests.get(test_class);
         }
         if (v == null){
             v = new Vector();
             intfAllTests.put(new String(test_class == null ? "TEST GROUP" : test_class), v);
         }
         v.addElement(new String(outputFile.getAbsolutePath()));

         return new BufferedWriter(new FileWriter(outputFile));
     }

   /**
     * Overrides #JmppLib.libMain to correctly handle errors which might
     * occur during parsing the command line options.
     * @param argv command line
     * @param p a library instance which is to parse the command line
     *          and perform preprocessing
     */
    public static void libMain(String[] argv, JmppLib p) {
        try {
            try {
        	    p.parseOptions(argv);
            } catch (com.sun.jmpp.JmppException ex) {
                System.err.println(ex);
                return;
            }
            p.generate();
        } catch (Exception e) {
            System.err.println("Generation failed :");
            e.printStackTrace(System.err);
        }
    }


    // implementing WizardDescriptor methods

    Object WizDescriptor = null;

    /**
     * Returns descriptor to be used by wizard for jmpp source file creation
     * @return an instance of APIWizardDescriptor.
     */
    public Object getWizardDescriptor(){
        final String wizardClassName = "com.sun.tdk.editor.share.wizard.api.APIWizardDescriptor";
	if (WizDescriptor == null)
        try {
            WizDescriptor = Class.forName(wizardClassName).newInstance();
        } catch ( Exception e ) {
            throw new InstantiationError(e.toString());
        }
        return WizDescriptor;
    }

    /**
     * Returns the description string for this library, i.e.:
     * "Jmpp library for generating Java API tests"
     * Override this method in JmppLibAPI subclasses to provide a different info.
     * @return this library description.
     */

    public String getInfo() {
        return "Jmpp library for generating Java API tests";
    }

    /**
     * Adds new authors for the current java file using
     * current <code>author</code> variable contents.
     * @see #gen
     */

    protected void addAuthor() {
        if (file == null
                || file.trim().compareTo("") == 0
                || author == null
                || author.trim().compareTo("") == 0) {
            return;
        }

        String key = new String(file);
        String oldAuthor = (String)authorsHashTable.get(key);

        StringTokenizer tokenizer
            = new StringTokenizer(author, ",");
        while (tokenizer.hasMoreTokens()) {
            String tempAuthor = tokenizer.nextToken();
            tempAuthor = strRemovedDoubleSpaces(tempAuthor);

            if (tempAuthor.length()>0) {
                if (oldAuthor == null
                        || oldAuthor.trim().compareTo("") == 0) {
                    oldAuthor = tempAuthor;
                } else if (strRemovedBlankSpaces(oldAuthor).
                        indexOf(strRemovedBlankSpaces(tempAuthor)) < 0) {
                    oldAuthor += ", " + tempAuthor;
                }
            }
        }

        authorsHashTable.put(key, oldAuthor);
    }

    /**
     * Returns comma separated list of authors defined
     * in the current java file - <code>keyFile</code> or null if
     * no authors are defined at the moment.
     * If the <code>keyFile</code> value is <code>null</code> or
     * empty string this method will return <code>null</code>.
     */
    protected String getAuthor(String keyFile) {
        if (keyFile == null) {
            return null;
        }
        return (String)authorsHashTable.get(keyFile);
    }

    /**
     * Returns string containing all authors defined and
     * added by <code>addAuthor</code> method in the jmpp source file.
     * @return comma separated authors summary string.
     */
    protected String getAuthorsSummary() {
        Enumeration e = authorsHashTable.elements();
        String result = "";
        while (e.hasMoreElements()) {
            StringTokenizer tokenizer
                = new StringTokenizer((String)e.nextElement(), ",");
            while (tokenizer.hasMoreTokens()) {
                String tempAuthor = tokenizer.nextToken();
                tempAuthor = strRemovedDoubleSpaces(tempAuthor);
                if (result.indexOf(tempAuthor) < 0) {
                    if (result.length() >0) result += ", ";
                    result += tempAuthor;
                }
            }
        }
        return result;
    }


    /**
     * Returns string containing the short name for test_class.
     * The name of test_class is squeezed down to a limit defined
     * in a property file as "filename.maxLength"
     */
    protected String getShortTestClassName() {
        return test_class_short;
    }


} //the end of class JmppLibAPI


//------------------------------ class Method -----------------------------

 class Method {
    private int sigType = -1;
    // possible constant values of sigType:
    // note: order position denepnds on sigType value
    private static final int FUNCTIONAL_SIG  = 0;
    private static final int PACKAGE_SIG     = 1;
    private static final int CLASS_SIG       = 2;
    private static final int FIELD_SIG       = 3;
    private static final int CTOR_SIG        = 4;
    private static final int METHOD_SIG      = 5;
    private static final int SERIAL_SIG      = 6;

    public String method_name;
    public String method_key;
    public String signature;
    public Hashtable filesHashTable = new Hashtable();
    public Vector testcasesVector = new Vector();
    public Vector method_params_names = null;
    private boolean haveTestsFor[] = {false, false, false, false, false};
    private boolean haveOutputValueFor[] = {false, false, false, false, false};
    private boolean havePreconditionValueFor[] = {false, false, false, false, false};
    private boolean haveExpectedValueFor[] = {false, false, false, false, false};

    public Method(String method_name, String test_class)
            throws MalformedValueException{

        this.method_name = method_name;
        this.method_key = getMethodKey(method_name);
        method_params_names = parseMethodParamsNames(method_name, test_class);
    }

   /**
    * adds new TestcaseElement to the vector of all testcases
    * of this method. The method is called from gen().
    * @param tc TestcaseElement to add.
    */
    public void AddTestCaseElement(TestcaseElement tc){
         Vector tcVector = null;
         boolean found = false;
         if (JmppLibAPI.strRemovedBlankSpaces(tc.expected).compareTo("") != 0){
             setHaveExpectedValueFor(tc.testTechnique);
         }
         if (JmppLibAPI.strRemovedBlankSpaces(tc.precondition).compareTo("") != 0){
             setHavePreconditionValueFor(tc.testTechnique);
         }
         if (tc.testTechnique == JmppLibAPI.ASSERTION_TESTING ||
             tc.testTechnique == JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING ||
             tc.testTechnique == JmppLibAPI.BOUNDARY_VALUE_ANALYSIS){
             if (tc.valuesHashTable.get("output") != null){
                 setHaveOutputValueFor(tc.testTechnique);
             }
         }
         setHaveTestsForTestingType(tc.testTechnique);
         testcasesVector.add(tc);
         if (filesHashTable.containsKey(tc.file) == true){
            tcVector = (Vector)filesHashTable.get(tc.file);
         } else {
            tcVector = new Vector();
            filesHashTable.put(tc.file, tcVector);
         }
         for (int i = 0; i < tcVector.size(); ++i){
             String tcID = ((TestcaseElement)tcVector.get(i)).getTestcaseID();
             if (tcID.compareTo(tc.getTestcaseID()) == 0){
                 found = true;
                 break;
             }
         }
         if (found == false){
             tcVector.add(tc);
         }
         return;
    }

  /**
   * Returns a vector of all file names which contain java sources
   * of the testcases described in tables of this method. The method is called
   * from generateTestDescriptions(). The vector is used for generating html
   * links to test descriptions of those files which are not described in this
   * method, (for example, "See test description here").
   * @return vector of file names.
   */
    public Vector GetMethodFiles(){
        Vector v = new Vector();
        Enumeration e = filesHashTable.keys();
        while(e.hasMoreElements()){
            v.add(e.nextElement());
        }
        return v;
    }

    public Vector GetTestcasesForFile(String f){
        return (Vector)filesHashTable.get(f);
    }

    public Vector GetTestCases(){
         return testcasesVector;
    }

    public String getName(){
        return method_name;
    }

    public String getSignature(){
        return signature;
    }

    public boolean haveTestsForTestingType(int testing_type){
         boolean b = false;
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 b = haveTestsFor[1];
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 b = haveTestsFor[2];
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 b = haveTestsFor[3];
                 break;
             }
             default: {
                 b = false;
             }
        }
        return b;
    }

    public void setHaveTestsForTestingType(int testing_type){
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 haveTestsFor[1] = true;
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 haveTestsFor[2] = true;
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 haveTestsFor[3] = true;
                 break;
             }
             default: {
             }
        }
        return;
    }

    public void setHaveOutputValueFor(int testing_type){
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 haveOutputValueFor[1] = true;
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 haveOutputValueFor[2] = true;
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 haveOutputValueFor[3] = true;
                 break;
             }
             default: {
             }
        }
        return;
    }

    public boolean haveOutputValueFor(int testing_type){
         boolean b = false;
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 b = haveOutputValueFor[1];
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 b = haveOutputValueFor[2];
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 b = haveOutputValueFor[3];
                 break;
             }
             default: {
                 b = false;
             }
        }
        return b;
    }

    public void setHaveExpectedValueFor(int testing_type){
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 haveExpectedValueFor[1] = true;
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 haveExpectedValueFor[2] = true;
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 haveExpectedValueFor[3] = true;
                 break;
             }
             default: {
             }
        }
        return;
    }

    public boolean haveExpectedValueFor(int testing_type){
         boolean b = false;
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 b = haveExpectedValueFor[1];
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 b = haveExpectedValueFor[2];
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 b = haveExpectedValueFor[3];
                 break;
             }
             default: {
                 b = false;
             }
        }
        return b;
    }

    public void setHavePreconditionValueFor(int testing_type){
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 havePreconditionValueFor[1] = true;
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 havePreconditionValueFor[2] = true;
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 havePreconditionValueFor[3] = true;
                 break;
             }
             default: {
             }
        }
        return;
    }

    public boolean havePreconditionValueFor(int testing_type){
         boolean b = false;
         switch (testing_type) {
             case JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING: {
                 b = havePreconditionValueFor[1];
                 break;
             }
             case JmppLibAPI.BOUNDARY_VALUE_ANALYSIS: {
                 b = havePreconditionValueFor[2];
                 break;
             }
             case JmppLibAPI.ASSERTION_TESTING: {
                 b = havePreconditionValueFor[3];
                 break;
             }
             default: {
                 b = false;
             }
        }
        return b;
    }

    public Vector parseMethodParamsNames(String method_name, String test_class) throws MalformedValueException {
    	 //replace chars
        StringBuffer bMethod = new StringBuffer(method_name);
        //replace space and comma in method_name
        boolean replaced = replaceInTag(bMethod);
        if (replaced)
            method_name = bMethod.toString();
        Vector method_params_names = new Vector(), params = new Vector();
        String param_name_str = "";
        int i = 0;
        int start = method_name.indexOf('(');
        int end = method_name.lastIndexOf(')');
        if (method_name.lastIndexOf('(') != start) {
            throw new MalformedValueException("multiple occurences of '(' in method <<" + method_name + ">>");
        }
        if (method_name.indexOf(')') != end) {
            throw new MalformedValueException("multiple occurences of ')' in method <<" + method_name + ">>");
        }
        // Get short name
        String tmp_short_name = method_name.substring(0, start < 0 ? method_name.length() : start).trim();
        int sh_index_start = tmp_short_name.lastIndexOf(' ');
        signature = sh_index_start < 0 ? tmp_short_name : tmp_short_name.substring(sh_index_start + 1);
        // Short name stored.

        if (start < 0 || end < 0){
            //field or assertion instead of method signature;
            if (tmp_short_name.startsWith("package ")) {
                sigType = PACKAGE_SIG;
            } else if (tmp_short_name.startsWith("public class ")
                    || tmp_short_name.startsWith("class ")) {
                sigType = CLASS_SIG;
            } else if (tmp_short_name.
                        startsWith(JmppLibAPI.FUNCTIONAL_MEMBERSIG_PREFIX)){
                sigType = FUNCTIONAL_SIG;
                signature = "functionalityOf_" + signature;
            } else if (tmp_short_name.
                        startsWith(JmppLibAPI.SERTEST_MEMBERSIG_PREFIX)){
                sigType = SERIAL_SIG;
                this.method_name = description(tmp_short_name);
                if (this.method_name.startsWith("deserialization")) {
                    signature = "ConstructorTests";
                } else if (this.method_name.startsWith("serialization")) {
                    signature = "InputTests";
                }
            } else {
                sigType = FIELD_SIG;
            }
            return null;
        }
        // Check if this is a constructor
        if (signature.equals(test_class)) {
            sigType = CTOR_SIG;
        } else {
            sigType = METHOD_SIG;
        }
        signature += '(';
        String substr = method_name.substring(start + 1, end);
        StringTokenizer st = new StringTokenizer(substr, ",");
        while (st.hasMoreTokens()) {
            String param = st.nextToken();
            if (JmppLibAPI.strRemovedBlankSpaces(param).compareTo("") != 0){
                params.add(param);
            }
        }
        if (params.size() == 0){
            signature += ')';
            return method_params_names;    // empty Vector
        }
        while(i < params.size()){
            signature += i == 0 ? "" : ",";
            st = new StringTokenizer((String)params.get(i));
            while (st.hasMoreTokens()) {
                param_name_str = st.nextToken();
                signature += st.hasMoreTokens() ? param_name_str : "";
            }
            method_params_names.add(param_name_str);
            param_name_str = "";
            ++i;
        }
        signature += ')';
	//restore space and comma in method signature
        if (replaced) {
            signature = restoreInTag(new StringBuffer(signature));
        }
        return method_params_names;
    } // the end of method parseMethodParamsNames();

    public String getMethodKey(){
        return method_key;
    }

    public static String getMethodKey(String method) {
        return JmppLibAPI.strRemovedBlankSpaces(method);
    }

    public Vector getMethodParamsNames(){
        return this.method_params_names;
    }

  /**
   * is called from generateTestcasesTable() to get a html row with
   * method parameters names as elements.
   * @return String which is a html row consisting of this method parameters names.
   */
    public String ParamsList2TableHeader(){
        int i = 0;
        StringBuffer str_buf = new StringBuffer("");
        if (method_params_names == null || method_params_names.size() == 0){
            return "";
        }
        while(i < method_params_names.size()){
            String param = (String)method_params_names.get(i);
            str_buf.append((i == 0 ? "" : "\n" ) + "    <TH SCOPE=\"col\"> ");
            str_buf.append(param);
            str_buf.append(" </TH>");
            ++i;
        }
        return str_buf.toString();
    }

   /**
    * Checks that method which doesn't have signature can't have
    * tests for equivalence class partitioning and boundary value analysis.
    * This method is called from generateTestcasesTable().
    * @throws MalformedValueException if this method has no signature but
    * has tests for equivalence class partitioning and boundary value analysis.
    */
    public void checkValuesParams() throws MalformedValueException {
        if (haveTestsForTestingType(JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING) == true ||
            haveTestsForTestingType(JmppLibAPI.BOUNDARY_VALUE_ANALYSIS) == true) {
            if (method_params_names == null){
                throw new MalformedValueException("method <<" + getName() + ">> seems has no signature, " +
                              "equivalence class partitioning and boundary value analysis are meaningless");
            }
        }
        if (haveTestsForTestingType(JmppLibAPI.ASSERTION_TESTING) &&
                haveOutputValueFor(JmppLibAPI.ASSERTION_TESTING) ) {
             // add empty "Expected output value" if needed.
             for (Iterator it = testcasesVector.iterator(); it.hasNext();) {
                 TestcaseElement tc = (TestcaseElement)(it.next());
                 if (tc.testTechnique == JmppLibAPI.ASSERTION_TESTING &&
                        tc.expectedResultValue == null) {
                     tc.expectedResultValue = "";
                 }
             }
        }
    }

   /**
    * @return true if this method is field, class or package i.e. has no '(' or ')' in method name.
    * otherwise false.
    */
    public boolean isAssertionTestingOnly() {
        return (sigType == PACKAGE_SIG) || (sigType == CLASS_SIG)
            || (sigType == FIELD_SIG) || (sigType == SERIAL_SIG)
            || (sigType == FUNCTIONAL_SIG);
    }

    public int getOrderPosition() {
        return sigType;
    }


    public static String description(String methodName) {
        if (methodName.startsWith(JmppLibAPI.SERTEST_MEMBERSIG_PREFIX)) {
            return methodName.substring
                    (JmppLibAPI.SERTEST_MEMBERSIG_PREFIX.length());
        }
        return methodName;
    }

    public static boolean isFunctional(String methodName) {
        return methodName.startsWith(JmppLibAPI.FUNCTIONAL_MEMBERSIG_PREFIX);
    }


    /**
     * returns string represention of sigType
     */
    public String toString() {
        switch (sigType) {
            case PACKAGE_SIG    : return "package assertion";
            case CLASS_SIG      : return "class desrciption";
            case FIELD_SIG      : return "field";
            case CTOR_SIG       : return "constructor";
            case METHOD_SIG     : return "method";
            case SERIAL_SIG     : return "serialization";
            case FUNCTIONAL_SIG : return "functional";
            default: return "unknown type";
        }
    }
    
     /**
     * This method replaces characters between &lt; and &gt;. Comma is replaced by ~, space by ^.
     * @param str Source string.
     * @return True if replace happen.
     */

    public static boolean replaceInTag(StringBuffer str) {
        int start = str.indexOf("&lt;");
        int end = str.lastIndexOf("&gt;");
        if (start != -1 && end != -1) {
            //skip &lt; length
            start += 4;
            String substr = str.substring(start, end);
            substr = substr.replace(',', '~');
            substr = substr.replace(' ', '^');
            //replace in the same StringBuffer
            str.replace(start, end, substr);
            return true;
        }
        return false;
    }

    /**
     * This method restore characters between &lt; and &gt;. ~ is replaced by comma, ^ by space.
     * @param str Source string
     */

    public static String restoreInTag(StringBuffer str) {
        int start = str.indexOf("&lt;");
        int end = str.lastIndexOf("&gt;");
        if (start != -1 && end != -1) {
            //skip &lt; length
            start += 4;
            String substr = str.substring(start, end);
            substr = substr.replace('~', ',');
            substr = substr.replace('^', ' ');
            //replace in the same StringBuffer
            str.replace(start, end, substr);
        }
        return str.toString();
    }
    
}// the end of class Method;


//------------------------------ class TestcaseElement -----------------------------

 class TestcaseElement {
     public String testcaseID;
     public String method_key;
     public Method method;
     public String precondition;
     public String expected;
     public String expectedResultValue;
     public Hashtable valuesHashTable = new Hashtable();
     public String value;
     public int testTechnique;
     public String file;

    /**
     * TestcaseElement constructor - assigns parameters to object variables,
     * if testTechnique is set to Equivalence class partitioning or Boundary value analysis,
     * parses <code>values</code> string to get variables names and their values.
     * If testTechnique is set to Assertion testing then <code>values</code> value
     * assigns to object variable <code>value</code> without parsing.
     */
     public TestcaseElement(int testTechnique, String testcaseID, Method meth, String values_str,
                            String precondition, String expected, String expectedResultValue,
                            String file){
          this.testTechnique = testTechnique;
          this.testcaseID = testcaseID;
          this.method = meth;
          this.method_key = meth.getMethodKey();
          this.expected = expected;
          this.expectedResultValue = expectedResultValue;
          this.precondition = precondition;
          this.file = file;
          if (testTechnique == JmppLibAPI.EQUIVALENCE_CLASS_PARTITIONING ||
              testTechnique == JmppLibAPI.BOUNDARY_VALUE_ANALYSIS){
              parseValuesString(values_str);
          } else {
              this.value = values_str;
              if (expectedResultValue != null) {
                    valuesHashTable.put("output", expectedResultValue);
              }
          }
          return;
     } // the end of constructor;


    /**
     * parses <code>values</code> public variable value in order
     * to construct a hashtable with method parameter names as keys and their
     * delimited with ":" values as values. This hashtable is used by toString()
     * method during html table construction. The method is called from
     * TestcaseElement constructor.
     */
     public void parseValuesString(String value){
        for (int j = 0; j < method.method_params_names.size(); ++j){
            valuesHashTable.put(method.method_params_names.get(j), "");
        }
        Vector values = new Vector();
        int i = 0;
        StringTokenizer st = new StringTokenizer(value, "\n");
        while (st.hasMoreTokens()){
            values.add(st.nextToken());
        }
        while(i < values.size()){
            String val_str = (String)values.get(i);
            int q_index = val_str.indexOf(':');
            if (q_index > 0 && q_index < val_str.length() - 1) {
                String value_param_name = val_str.substring(0, q_index);
                String value_param_value = val_str.substring(q_index + 1,  val_str.length());
                if (method.method_params_names.contains(JmppLibAPI.strRemovedBlankSpaces(value_param_name)) == true ||
                    JmppLibAPI.strRemovedBlankSpaces(value_param_name).compareTo("output") == 0){
                    valuesHashTable.put(JmppLibAPI.strRemovedBlankSpaces(value_param_name),
                                        value_param_value);
                }
            }
            ++i;
        }
        return;
    } // the end of method parseValuesString(String);

   /**
    * constructs html table entry of the given TestcaseElement.
    * Table row is constructed according to which type of table is being generated -
    * Assertion testing, Equivalence class partitioning or Boundary value analysis.
    * This method is called from generateTestcasesTable().
    * @return String representing html table row corresponding to this TestcaseElement.
    */
    public String toString(){
        int i = 0;
        String param_name, param_value;
        StringBuffer str_buf = new StringBuffer("");
        Vector paramsNamesVector = method.getMethodParamsNames();

        if (method.havePreconditionValueFor(this.testTechnique) == true){
            if (this.precondition == null ||
                JmppLibAPI.strRemovedBlankSpaces(this.precondition).compareTo("") == 0){
                str_buf.append("\n    <TD> ");
            } else {
                str_buf.append("\n    <TD> ");
                str_buf.append(this.precondition);
            }
            str_buf.append(" </TD>");
        }
        if (this.testTechnique == JmppLibAPI.ASSERTION_TESTING){
            if (this.value == null ||
                JmppLibAPI.strRemovedBlankSpaces(this.value).compareTo("") == 0){
                str_buf.append("\n    <TD> ");
            } else {
                str_buf.append("\n    <TD> ");
                str_buf.append(this.value.trim());
            }
            str_buf.append(" </TD>");
            if (expectedResultValue != null) {
                str_buf.append("\n    <TD> ");
                str_buf.append(expectedResultValue);
                str_buf.append(" </TD>");
            }
        } else {
            while(i < paramsNamesVector.size()){
                param_name = (String)paramsNamesVector.get(i);
                if ((param_value = (String)valuesHashTable.get(param_name)) == null){
                    param_value = "";
                }
                str_buf.append("\n    <TD> ");
                str_buf.append(param_value.trim());
                str_buf.append(" </TD>");
                ++i;
            }
            if (method.haveOutputValueFor(this.testTechnique) == true){
                if ((param_value = (String)valuesHashTable.get("output")) != null){
                    str_buf.append("\n    <TD> ");
                    str_buf.append(param_value.trim());
                } else {
                    str_buf.append("\n    <TD> ");
                }
                str_buf.append(" </TD>");
            }
        }
        if (method.haveExpectedValueFor(this.testTechnique) == true){
            if (this.expected == null ||
                JmppLibAPI.strRemovedBlankSpaces(this.expected).compareTo("") == 0){
                str_buf.append("\n    <TD> ");
            } else {
                str_buf.append("\n    <TD> ");
                str_buf.append(this.expected);
            }
            str_buf.append(" </TD>");
        }
        str_buf.append("\n    <TD SCOPE=\"row\"> ");
        str_buf.append(testcaseID);
        str_buf.append(" </TD>");
        if(str_buf.length() > 0) {
            str_buf.deleteCharAt(0);
        }
        return str_buf.toString();
    }

    public String getTestcaseID(){
        return testcaseID;
    }

    public Method getMethod(){
        return this.method;
    }

}// the end of class TestcaseElement


//------------------------------ class MalformedValueException -----------------------


class MalformedValueException extends Exception {
    public MalformedValueException(String s){
        super(s);
    }
}


//------------------------------ class TestDescription -------------------------------

 class TestDescription {
     public Vector testcaseIDs;
     public String method_str;
     public String file;
     public String fileName;
     public StringBuffer javaSource = null;
     public TestDescriptionIR tdIR;

   /**
    * TestDescription constructor. It assigns its parameters to
    * object variables.
    */
     public TestDescription(String method_str, Vector testcaseIDs,
             String file, String fileName, TestDescriptionIR tdIR) {
         this.method_str = method_str;
         this.testcaseIDs = testcaseIDs;
         this.file = file;
         this.fileName = fileName;
         this.tdIR = tdIR;
     }

    /**
     * constructs test description table.
     * @return a String which is test description table.
     */
     public String toString(){
          return tdIR.toString();
     }

    /**
     * constructs a String of all testcases included into the java
     * source described by this TestDescription. All testcase names are unique.
     * @return String - a list of included into the java source testcases.
     */
     public String getTestcasesIncluded(){
         int i = 0;
         StringBuffer str_buf = new StringBuffer("");
         if (testcaseIDs == null || testcaseIDs.size() == 0){
             return "";
         }
         while(i < testcaseIDs.size()){
             str_buf.append("  ");
             if (i == testcaseIDs.size() - 1){
                  str_buf.append((String)testcaseIDs.get(i));
                  str_buf.append(".");
             } else {
                  str_buf.append((String)testcaseIDs.get(i));
                  str_buf.append(",\n");
             }
             ++i;
         }
         return str_buf.toString();
     }
 }

//------------------------------ LibAPIProperties implementation class ------------------------


/**
 * Default implementation of LibAPIProperties interface which loads
 * all the properties from a configuration file.
 */

class LibAPIPropertiesImpl extends Properties implements LibAPIProperties {
    /**
     * File name to get the libAPI properties from.
     */
    protected String propertyFileName;

    /**
     * This string represents default copyright notice
     */
    protected String defaultCopyrightNotice = "//TODO: Your copyright notice is here";

    /**
     * This string represents deafult html copyright notice
     */
    protected String defaultHtmlCopyrightNotice = "<BR>TODO: Your copyright notice is here<BR>";

    /**
     * This string represents default keywords
     */
    protected String defaultKeywords = "runtime positive";

    /**
     * This string contains comma separated full base classes names predefined
     */
    protected String predefinedBaseClasses = "com.sun.javatest.lib.MultiTest";

    /**
     * This string contains predefined packagePrefix
     */
    protected String defaultPackagePrefix = "tests.api";

    /**
     * This string contains predefined default imports
     */
    protected String defaultImports = "java.io.PrintWriter,"
                                    + "com.sun.javatest.Status";

    /**
     * Maximum file name length allowed for output files.
     */
    protected int maxFileNameLength = 0;

    /**
     * returns ini file name to get the property
     * from. This string is read from tdk.editor.ini system property.
     */
    public String getPropertyFileName() {
        return propertyFileName;
    }


    /**
     * Creates LibAPIProperties with the default set of properties.
     */
    public LibAPIPropertiesImpl() {
    }

    /**
     * Initializes the property set for libAPI from <code>ini file</code
     * named <code>propertyFileName</code>.
     *
     * @throws FileNotFoundException if <code>propertyFileName</code>
     *        does not exist.
     * @throws IOException if an error occurred while reading <code>propertyFileName</code>
     */
    public LibAPIPropertiesImpl(String propertyFileName)
            throws FileNotFoundException, IOException {
        try {
            if (propertyFileName != null
                    && propertyFileName.trim().compareTo("") != 0) {
                FileInputStream is =
                    new FileInputStream(propertyFileName);
                load(is);
            }
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Unable to find "
                    + propertyFileName + " : " + fnfe);
        } catch (IOException ioe) {
            throw new FileNotFoundException("Exception while reading the file "
                    + propertyFileName + " : " + ioe);
        }
    }

    /**
     * returns copyright notice defined in the <code>ini file</code>
     * If no copyright notice is defined defaults will be returned.
     * @see #defaultCopyrightNotice
     */
    public String getCopyrightNotice() {
        return getProperty("test.copyrightNotice", defaultCopyrightNotice);
    }

    public String getHtmlCopyrightNotice() {
        return getProperty("html.copyrightNotice", defaultHtmlCopyrightNotice);
    }

    /**
     * returns default imports defined in the <code>ini file</code>
     * If no default imports is defined defaults will be returned.
     * @see #defaultImports
     */
    public String getDefaultImports() {
        String resultImports = "";
        String savedImports =
            getProperty("test.defaultImports", defaultImports);
        if (savedImports == null || savedImports.trim().equals("")) {
            return "";
        } else {
            StringTokenizer tok =
                new StringTokenizer(savedImports, new String(","));
            while(tok.hasMoreTokens()) {
                resultImports += "import " + tok.nextToken().trim() + ";\n";
            }
            return resultImports.substring(0, resultImports.length()-1);
        }
    }

    /**
     * returns hastable containing baseClass - execute arguments pairs
     * defined in the <code>ini file</code> for the given base class defined
     * in the test.baseClass.&lt;baseClass&gt;.executeArgs property.
     * If no execute args is defined an empty hashtable will be returned.
     */
    public Hashtable getBaseClassExecuteArgs() {
        Hashtable result = new Hashtable();
        Enumeration baseClasses = getBaseClasses().keys();

        while (baseClasses.hasMoreElements()) {
            String baseClassName = (String)baseClasses.nextElement();
            String arguments = getProperty("test.baseClass." + baseClassName.trim()
                + ".executeArgs");
            if (arguments != null
                    && arguments.trim().compareTo("") != 0) {
                result.put(baseClassName, arguments);
            }
        }

        return result;
    }

    /**
     * returns default keywords defined in the <code>ini file</code>
     * If no default keywords is defined defaults will be returned.
     * @see #defaultKeywords
     * @throws InvalidPropertyException if keywords defined in <code>ini file</code>
     *      are invalid
     */
    public String getDefaultKeywords() {
        return getDefaultKeywordsImpl("test.defaultKeywords");
    }

    /**
     * returns default rmic keywords defined in the <code>ini file</code>
     * If no default keywords is defined defaults will be returned.
     * @throws InvalidPropertyException if keywords defined in <code>ini file</code>
     *      are invalid
     */
    public String getDefaultRmicKeywords() {
        return getDefaultKeywordsImpl("test.defaultRmicKeywords");
    }

    private String getDefaultKeywordsImpl(String propertyName) {
        String keywords = getProperty(propertyName, defaultKeywords);
        if (keywords == null) {
            return "";
        } else {
            StringTokenizer tok = new StringTokenizer(keywords, " ");
            while (tok.hasMoreTokens()) {
                String keyword = tok.nextToken();
                for (int i=0; i<keyword.length(); i++) {
                    // We assume JavaTest keywords could consist of either letters or digits
                    if (!Character.isLetterOrDigit(keyword.charAt(i))) {
                        throw new InvalidPropertyException(propertyName, "Invalid keyword: "
                                    + keyword);
                    }
                }
            }
            return keywords;
        }
    }


    /**
     * returns hashtable containing baseclass name -
     * full baseclass name. The information is read from
     * &lt;test.baseClasses&gt; <code>ini file</code> property.
     * The <code>defaultBaseClasses</code> based pair is default value
     * and always contains in the returned hashtable if not reset
     * intentionally in the <code>ini file</code>.
     * @throws InvalidPropertyException if base classes in <code>ini file</code> are incorrect
     */
    public Hashtable getBaseClasses() {
        Hashtable result = new Hashtable();
        String key = "";
        String value = "";
        String savedBaseClasses = predefinedBaseClasses + "," +
            getProperty("test.baseClasses");
        StringTokenizer tok =
            new StringTokenizer(savedBaseClasses, new String(","));
        while(tok.hasMoreTokens()) {
            value = tok.nextToken().trim();
            // make validation for class name
            StringTokenizer tok1 = new StringTokenizer(value, ".");
            while(tok1.hasMoreTokens()) {
               String clazz = tok1.nextToken();
               for (int i = 0; i < clazz.length(); i++) {
                    if (! ((i==0) ? Character.isJavaIdentifierStart(clazz.charAt(i))
                                  : Character.isJavaIdentifierPart(clazz.charAt(i))) ) {
                        throw new InvalidPropertyException("test.baseClasses", "Invalid class name: "
                                + clazz);
                    }
                }
            }
            key   = getBaseClassShortName(value);
            result.put(key, value);
        }

        return result;
    }

    /**
     * returns package prefix defined in the <code>ini file</code>
     * If no package prefix is defined defaults will be returned.
     * @see #defaultPackagePrefix
     * @throws InvalidPropertyException if package prefix <code>ini file</code> are incorrect
     */
    public String getPackagePrefix() {
        String prefix = getProperty("test.packagePrefix", defaultPackagePrefix).trim();
        if (prefix == null) {
            return "";
        } else {
            // check that prefix is valid Java package name
            if (prefix.indexOf("..") >= 0) {
                throw new InvalidPropertyException("test.packagePrefix", "Invalid package name: "
                            + prefix);
            }
            if (prefix.startsWith("java.")) {
                throw new InvalidPropertyException("test.packagePrefix", "Invalid package name: "
                            + prefix + " (\"java\" name is reserved)");
            }
            StringTokenizer tok = new StringTokenizer(prefix, ".");
            while (tok.hasMoreTokens()) {
                String subpackage = tok.nextToken();
                for (int i = 0; i < subpackage.length(); i++) {
                if (! ((i==0) ? Character.isJavaIdentifierStart(subpackage.charAt(i))
                              : Character.isJavaIdentifierPart(subpackage.charAt(i))) ) {
                    throw new InvalidPropertyException("test.packagePrefix", "Invalid package name: "
                            + subpackage);
                    }
                }
            }
            return prefix;
        }
    }

    /**
     * Returns maximum file name length allowed for output files.
     */
    public int getMaxFileNameLength() {
        String maxLength = getProperty("filename.maxLength", String.valueOf(maxFileNameLength)).trim();
        int result = 0;
        try {
            result = Integer.valueOf(maxLength).intValue();
        } catch (NumberFormatException e) {

        }
        return result;
    }

    private String getBaseClassShortName(String baseClassName) {
        return baseClassName.substring(baseClassName.lastIndexOf(".") + 1);
    }
}


