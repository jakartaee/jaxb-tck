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

package com.sun.tgxml.tjtf.api.code;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.CodeSet
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.ArrayList;
// </importgen>

/**
 * CodeSet - 
 *
 * <b>CodeSet</b> is the grouping of support/related code in a TestGroup, TestCase, or Library.
 * A TestCase's <b>CodeSet</b> contains all of the code properties that are used to compile 
 * and execute a test. <b>CodeSet</b> contains code that is additional to the TestCode of the TestCase. 
 * 
 * <p>
 * <b>CodeSet</b> contains the following:<br><br>
 * <ul>
 *     <li> Dependency
 *     <li> Imports
 *     <li> ExecuteArgs
 *     <li> Context
 *     <li> BaseClass
 *     <li> Exports
 *     <li> SupportCode
 *     <li> SupportClass
 *     <li> Data
 *  </ul> <br>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    CodeSet
 * ============================================================================================
 */


public  interface CodeSet {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   


  /**
    *   Get the Dependencies associated with this TestItem.
    *  <p>
    * @return   The Dependencies associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.CodeDependency
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    * @see #setDependencies
    */
    public ArrayList getDependencies();
     
  /**
    *   Set the Dependencies associated with this TestItem.
    *  <p>
    * @param     dependencies The Dependencies associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.CodeDependency
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    * @see #getDependencies
    */
    public void setDependencies(ArrayList dependencies);



  /**
    *   Get the Imports associated with this TestItem.
    *  <p>
    * @return   The Imports associated with the TestItem, or NULL.
    * @see #setImports
    */
    public ArrayList getImports();
     
  /**
    *   Set the Imports associated with this TestItem.
    *  <p>
    * @param     imports The Imports associated with the TestItem, or NULL.
    * @see #getImports
    */
    public void setImports(ArrayList imports);

  /**
    *   Get the Exports associated with this TestItem.
    *  <p>
    * @return   The Exports associated with the TestItem, or NULL.
    * @see #setExports
    */
    public ArrayList getExports();
     
  /**
    *   Set the Exports associated with this TestItem.
    *  <p>
    * @param     exports The Exports associated with the TestItem, or NULL.
    * @see #getExports
    */
    public void setExports(ArrayList exports);


  /**
    *   Get the execute-arguments associated with this CodeSet.
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
    * @return     The execute-arguments associated with this CodeSet
    * @see #setExecuteArgs
    * @see java.lang.String
    */
    public String getExecuteArgs();
     
   /**
    *   Set the execute-arguments associated with this CodeSet.
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
    * @param     executeArgs The execute-arguments of the CodeSet.
    * @see #getExecuteArgs
    * @see java.lang.String
    */
    public void setExecuteArgs(String executeArgs);


  /**
    *   Get the context associated with this CodeSet.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  Context specifies configuration values required by the test. The JavaTest harness checks to 
    *  be sure these values are set before it runs the test and then passes the values through 
    *  to the test. If any of these values are not defined, the JavaTest harness reports an error.
    * <p>
    * @return     The context associated with this CodeSet
    * @see #setContext
    * @see java.lang.String
    */
    public String getContext();
     
   /**
    *   Set the context associated with this CodeSet.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    *  Context specifies configuration values required by the test. The JavaTest harness checks to 
    *  be sure these values are set before it runs the test and then passes the values through 
    *  to the test. If any of these values are not defined, the JavaTest harness reports an error.
    * <p>
    * @param     context The context of the CodeSet.
    * @see #getContext
    * @see java.lang.String
    */
    public void setContext(String context);


  /**
    *   Get the (fully-qualified) class name for the base class (the class that this class extends).
    *  <p>
    * @return   A string containing the (fully-qualified) class name for the base class.
    * @see #setBaseClass
    */
    public String getBaseClass();
     
  /**
    *   Set the (fully-qualified) class name for the base class (the class that this class extends).
    *  <p>
    * @param     baseClassName The string containing the (fully-qualified) class name for the base class.
    * @see #getBaseClass
    */
    public void setBaseClass(String baseClassName);


  /**
    *   Get the  SupportCode.
    *  <p>
    * @return   the Code, or NULL.
    * @see #setSupportCode
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    public SupportCode getSupportCode() ;
     
  /**
    *   Set the SupportCode.
    *  <p>
    * @param     supportcode The supportcode.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    * @see #getSupportCode
    */
    public void setSupportCode(SupportCode supportcode) ;



  /**
    *   Get the SupportClasses associated with this TestItem.
    *  <p>
    * @return   The SupportClasses associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.SupportClass
    * @see #setSupportClasses
    */
    public ArrayList getSupportClasses();
     
  /**
    *   Set the Dependencies associated with this TestItem.
    *  <p>
    * @param     supportClasses The SupportClasses associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.SupportClass
    * @see #getSupportClasses
    */
    public void setSupportClasses(ArrayList supportClasses);



  /**
    *   Get the Data associated with this TestItem.
    *  <p>
    * @return   The Data associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.data.Data
    * @see #setData
    */
    public ArrayList getData();
     
  /**
    *   Set the Data associated with this TestItem.
    *  <p>
    * @param     data The data associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.data.Data
    * @see #getData
    */
    public void setData(ArrayList data);
}
