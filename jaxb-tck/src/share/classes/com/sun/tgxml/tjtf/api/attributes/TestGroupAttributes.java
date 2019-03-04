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

package com.sun.tgxml.tjtf.api.attributes;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.ArrayList;
// </importgen>

/**
 * TestGroupAttributes - 
 *
 * <b>TestGroupAttributes</b> is the typed-attribute interface for describing the attributes
 * associated with a TestGroup.
 * <p>
 * <b>TestGroupAttributes</b> contains the following (from Attributes):<br><br>
 * <ul>
 *     <li> RequiredResources
 *     <li> AttrElems
 *     <li> TargetSpecs
 *  </ul> <br>
 *
 * <b>TestGroupAttributes</b> additionally contain (as Strings or ArrayLists of Strings):<br><br>
 * <ul>
 *     <li> Keywords    (ArrayList)
 *     <li> Context
 *     <li> ExecuteClass
 *     <li> ExecuteNative
 *     <li> ExecuteArgs
 *     <li> Remote      (ArrayList)
 *     <li> RMICClasses
 *     <li> SelectIf    (ArrayList)
 *     <li> Timeout
 *  </ul> <br>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TestGroupAttributes
 * ============================================================================================
 */


public  interface TestGroupAttributes extends TestAttributes  {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the keyword attributes associated with this entity.
    *  <p>
    *   The Keywords is always an ArrayList with zero or
    *   more Keywords (java Strings).
    *<p>
    *  Keywords are attributes from a predefined set that (generally) describe runtime properties 
    *  about a test (usually for JavaTest). Keywords are String tokens that can be associated with 
    *  a given test. They describe attributes or characteristics of the test.
    *  Keywords are often used to select/deselect tests from a test run. Keywords are also used to 
    *  select how the test will be executed by the JavaTest harness. 
    *  <p>
    * @return     The list of keyword attributes.
    * @see #setKeywords
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public ArrayList getKeywords();
     
   /**
    *   Set the keyword attributes associated with this entity.
    *  <p>
    *   The Keywords is always an ArrayList with zero or
    *   more Keywords (java Strings).
    *<p>
    *  Keywords are attributes from a predefined set that (generally) describe runtime properties 
    *  about a test (usually for JavaTest). Keywords are String tokens that can be associated with 
    *  a given test. They describe attributes or characteristics of the test.
    *  Keywords are often used to select/deselect tests from a test run. Keywords are also used to 
    *  select how the test will be executed by the JavaTest harness. 
    *  <p>
    * @param     keywords The list of keyword attributes.
    * @see #getKeywords
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public void setKeywords(ArrayList keywords);




  /**
    *   Get the context associated with this TestGroup.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  Context specifies configuration values required by the test. The JavaTest harness checks to 
    *  be sure these values are set before it runs the test and then passes the values through 
    *  to the test. If any of these values are not defined, the JavaTest harness reports an error.
    * <p>
    * @return     The context associated with this TestGroup
    * @see #setContext
    * @see java.lang.String
    */
    public String getContext();
     
   /**
    *   Set the context associated with this TestGroup.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  Context specifies configuration values required by the test. The JavaTest harness checks to 
    *  be sure these values are set before it runs the test and then passes the values through 
    *  to the test. If any of these values are not defined, the JavaTest harness reports an error.
    * <p>
    * @param     context The context of the TestGroup.
    * @see #getContext
    * @see java.lang.String
    */
    public void setContext(String context);



  /**
    *   Get the execute-class associated with this TestGroup.
    *  <p>
    *   This execute-class is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteClass is a string that is passed as the class that is
    * executed to drive a test. 
    * <p>
    * @return     The execute-class associated with this TestGroup
    * @see #setExecuteClass
    * @see java.lang.String
    */
    public String getExecuteClass();
     
   /**
    *   Set the execute-class associated with this TestGroup.
    *  <p>
    *   This execute-class is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteClass is a string that is passed as the class that is
    * executed to drive a test. 
    * <p>
    * @param     executeClass The execute-class of the TestGroup.
    * @see #getExecuteClass
    * @see java.lang.String
    */
    public void setExecuteClass(String executeClass);



  /**
    *   Get the execute-arguments associated with this TestGroup.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteArgs is an array of strings that are passed to the test classes being executed. 
    *  The arguments may be fixed but often involve symbolic values that are substituted from 
    *  the test environment (variables defined elsewhere in the test environment). These arguments
    *  form the basis for the set of arguments that are passed into the tests defined in the 
    *  executeClass and executeNative fields. Note: If any of these values are not defined 
    *  in the test environment they are passed to the test as an empty string. Note: It is a 
    *  good idea to use &lt;Context&gt; to pass configuration parameters to the test. So in most 
    *  cases &lt;Context&gt; should be used instead of &lt;ExecuteArgs&gt; 
    * <p>
    * @return     The execute-arguments associated with this TestGroup
    * @see #setExecuteArgs
    * @see java.lang.String
    */
    public String getExecuteArgs();
     
   /**
    *   Set the execute-arguments associated with this TestGroup.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteArgs is an array of strings that are passed to the test classes being executed. 
    *  The arguments may be fixed but often involve symbolic values that are substituted from 
    *  the test environment (variables defined elsewhere in the test environment). These arguments
    *  form the basis for the set of arguments that are passed into the tests defined in the 
    *  executeClass and executeNative fields. Note: If any of these values are not defined 
    *  in the test environment they are passed to the test as an empty string. Note: It is a 
    *  good idea to use &lt;Context&gt; to pass configuration parameters to the test. So in most 
    *  cases &lt;Context&gt; should be used instead of &lt;ExecuteArgs&gt; 
    * <p>
    * @param     executeArgs The execute-arguments of the TestGroup.
    * @see #getExecuteArgs
    * @see java.lang.String
    */
    public void setExecuteArgs(String executeArgs);



  /**
    *   Get the execute-native-arguments associated with this TestGroup.
    *  <p>
    *   This execute-native-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteNative is the name of the platform-native program used to execute this test. 
    *  This is specific to Java Native Interface (JNI) tests that launch
    *  a Java Virtual Machine from native C code. 
    * <p>
    * @return     The context associated with this TestGroup
    * @see #setExecuteNative
    * @see java.lang.String
    */
    public String getExecuteNative();
     
   /**
    *   Set the execute-native-arguments associated with this TestGroup.
    *  <p>
    *   This execute-native-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  ExecuteNative is the name of the platform-native program used to execute this test. 
    *  This is specific to Java Native Interface (JNI) tests that launch
    *  a Java Virtual Machine from native C code. 
    * <p>
    * @param     executeNative The execute-native-arguments of the TestGroup.
    * @see #getExecuteNative
    * @see java.lang.String
    */
    public void setExecuteNative(String executeNative);





  /**
    *   Get the "remote" environment-strings associated with this test.
    *  <p>
    *   The Remote attributes is always an ArrayList with zero or
    *   more strings containing "remote" string-values.
    *  <p>
    * Remotes are used for the distributed test; defines the command to be executed on the remote host(s).
    * <p>
    * @return     The list of remote strings.
    * @see #setRemotes
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public ArrayList getRemotes();
     
   /**
    *   Set the "remote" environment-strings associated with this test.
    *  <p>
    *   The Remote attributes is always an ArrayList with zero or
    *   more strings containing "remote" string-values.
    *  <p>
    * Remotes are used for the distributed test; defines the command to be executed on the remote host(s).
    * <p>
    * @param     remotes The list of remote strings.
    * @see #getRemotes
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public void setRemotes(ArrayList remotes);


  /**
    *   Get the rmic-classes associated with this TestGroup.
    *  <p>
    *   These rmic-classes are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  RMICClasses are used for RMI tests; this nominates the set of classes that are passed to the RMI compiler
    * <p>
    * @return     The context associated with this TestGroup
    * @see #setRMICClasses
    * @see java.lang.String
    */
    public String getRMICClasses();
     
   /**
    *   Set the rmic-classes associated with this TestGroup.
    *  <p>
    *   These rmic-classes are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  RMICClasses are used for RMI tests; this nominates the set of classes that are passed to the RMI compiler
    * <p>
    * @param     rmicclasses The rmic-classes of the TestGroup.
    * @see #getRMICClasses
    * @see java.lang.String
    */
    public void setRMICClasses(String rmicclasses);



  /**
    *   Get the SelectIfs associated with this test.
    *  <p>
    *   The SelectIfs is always an ArrayList with zero or
    *   more strings containing "SelectIf" string-values.
    *  <p>
    *  Specifies a condition that must be satisfied in order for the test to be executed. 
    *  The selectIf field makes it possible to execute (or not execute) tests based on values 
    *  in the test environment. This field is constructed using environment values and the full set of
    *  boolean operators (!, <, <=, >, >=, ==, !=, &, |). The following is an example:  <br><br>
    *    IntegerValue<=4 & display=="my_computer:0"  <br><br>
    *  If the boolean expression evaluates to false the test is not run. If the expression 
    *  evaluates to true the test is run. If any of the values are not defined in the test 
    *  environment, the JavaTest harness considers the test to be in error. (SelectIf may be 
    *  deprecated - it is currently included for legacy code).
    * <p>
    * @return     The list of SelectIfs.
    * @see #setSelectIfs
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public ArrayList getSelectIfs();
     
   /**
    *   Set the SelectIfs associated with this test.
    *  <p>
    *   The SelectIfs is always an ArrayList with zero or
    *   more strings containing "SelectIf" string-values.
    *  <p>
    *  Specifies a condition that must be satisfied in order for the test to be executed. 
    *  The selectIf field makes it possible to execute (or not execute) tests based on values 
    *  in the test environment. This field is constructed using environment values and the full set of
    *  boolean operators (!, <, <=, >, >=, ==, !=, &, |). The following is an example:  <br><br>
    *    IntegerValue<=4 & display=="my_computer:0"  <br><br>
    *  If the boolean expression evaluates to false the test is not run. If the expression 
    *  evaluates to true the test is run. If any of the values are not defined in the test 
    *  environment, the JavaTest harness considers the test to be in error. (SelectIf may be 
    *  deprecated - it is currently included for legacy code).
    * <p>
    * @param     selectifs The list of SelectIfs.
    * @see #getSelectIfs
    * @see java.util.ArrayList
    * @see java.lang.String
    */
    public void setSelectIfs(ArrayList selectifs);



}
