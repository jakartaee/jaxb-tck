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

package com.sun.tgxml.tjtf.api.code.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.impl.CodeSetImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportCode;
import com.sun.tgxml.tjtf.api.code.LibraryDependency;
import java.util.ArrayList;
// </importgen>

/**
 * CodeSet - 
 *
 * <b>CodeSet</b> is the grouping of support/related code in a TestGroup, TestCase, or Library.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    CodeSetImpl
 * ============================================================================================
 */


public  class CodeSetImpl implements CodeSet {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList   m_dependencies = null;
    private ArrayList   m_imports = null;
    private ArrayList   m_exports = null;
    private String      m_baseclassname = null;
    private SupportCode m_supportcode = null;
    private ArrayList   m_supportclasses = null;
    private ArrayList   m_data = null;
    private String      m_context;
    private String      m_executeArgs;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public CodeSetImpl() {
	init();
    }

    public CodeSetImpl(ArrayList dependencies, ArrayList imports, String baseClassName, 
		      SupportCode supportCode, ArrayList supportClasses, ArrayList data) {
       this(dependencies, imports, baseClassName, 
               supportCode, supportClasses, data, null, null);
    }


    public CodeSetImpl(ArrayList dependencies, ArrayList imports, String baseClassName, 
		      SupportCode supportCode, ArrayList supportClasses, ArrayList data,
                       String context, String executeArgs) {

       m_dependencies = dependencies;
       m_imports = imports;
       m_baseclassname = baseClassName;
       m_supportcode = supportCode;
       m_supportclasses = supportClasses;
       m_data = data;
       m_context = context;
       m_executeArgs = executeArgs;
    }

    private void init() {
       m_dependencies = null;
       m_imports = null;
       m_baseclassname = null;
       m_supportcode = null;
       m_supportclasses = null;
       m_data = null;
       m_context = null;
       m_executeArgs = null;
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------
   


  /**
    *   Get the Dependencies associated with this CodeSet.
    *  <p>
    * @return   The Dependencies associated with the CodeSet, or NULL.
    * @see com.sun.tgxml.tjtf.api.dependency.Dependency
    * @see #setDependencies
    */
    public ArrayList getDependencies() {
	return m_dependencies;
    }
     
  /**
    *   Set the Dependencies associated with this CodeSet.
    *  <p>
    * @param     dependencies The Dependencies associated with the CodeSet, or NULL.
    * @see com.sun.tgxml.tjtf.api.dependency.Dependency
    * @see #getDependencies
    */
    public void setDependencies(ArrayList dependencies) {
	m_dependencies = dependencies;
    }



  /**
    *   Get the Imports associated with this TestItem.
    *  <p>
    * @return   The Imports associated with the TestItem, or NULL.
    * @see #setImports
    */
    public ArrayList getImports() {
	return m_imports;
    }
     
  /**
    *   Set the Imports associated with this TestItem.
    *  <p>
    * @param     imports The Imports associated with the TestItem, or NULL.
    * @see #getImports
    */
    public void setImports(ArrayList imports) {
	m_imports = imports;
    }

  /**
    *   Get the Exports associated with this TestItem.
    *  <p>
    * @return   The Exports associated with the TestItem, or NULL.
    * @see #setExports
    */
    public ArrayList getExports() {
	return m_exports;
    }
     
  /**
    *   Set the Exports associated with this TestItem.
    *  <p>
    * @param     exports The Exports associated with the TestItem, or NULL.
    * @see #getExports
    */
    public void setExports(ArrayList exports) {
	m_exports = exports;
    }

  /**
    *   Get the execute-arguments associated with this CodeSet.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The execute-arguments associated with this CodeSet
    * @see #setExecuteArgs
    */
    public String getExecuteArgs(){
	return m_executeArgs;
    }
     
   /**
    *   Set the execute-arguments associated with this CodeSet.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     executeArgs The execute-arguments of the CodeSet.
    * @see #getExecuteArgs
    */
    public void setExecuteArgs(String executeArgs){
	m_executeArgs = executeArgs;
    }


  /**
    *   Get the context associated with this CodeSet.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The context associated with this CodeSet
    * @see #setContext
    */
    public String getContext(){
	return m_context;
    }

   /**
    *   Set the context associated with this CodeSet.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     context The context of the CodeSet.
    * @see #getContext
    */
    public void setContext(String context){
	m_context = context;
    }

  /**
    *   Get the (fully-qualified) class name for the base class (the class that this class extends).
    *  <p>
    * @return   A string containing the (fully-qualified) class name for the base class.
    * @see #setBaseClass
    */
    public String getBaseClass()  {
	return m_baseclassname;
    }
     
  /**
    *   Set the (fully-qualified) class name for the base class (the class that this class extends).
    *  <p>
    * @param     baseClassName The string containing the (fully-qualified) class name for the base class.
    * @see #getBaseClass
    */
    public void setBaseClass(String baseClassName)  {
	m_baseclassname = baseClassName;
    }


  /**
    *   Get the  SupportCode.
    *  <p>
    * @return   the Code, or NULL.
    * @see #setSupportCode
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    public SupportCode getSupportCode() {
	return m_supportcode;
    }
     
  /**
    *   Set the SupportCode.
    *  <p>
    * @param     supportcode The supportcode.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    * @see #getSupportCode
    */
    public void setSupportCode(SupportCode supportcode){
	m_supportcode = supportcode;
    }



  /**
    *   Get the SupportClasses associated with this TestItem.
    *  <p>
    * @return   The SupportClasses associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.SupportClass
    * @see #setSupportClasses
    */
    public ArrayList getSupportClasses() {
	return m_supportclasses;
    }
     
  /**
    *   Set the Dependencies associated with this TestItem.
    *  <p>
    * @param     supportClasses The SupportClasses associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.code.SupportClass
    * @see #getSupportClasses
    */
    public void setSupportClasses(ArrayList supportClasses) {
	m_supportclasses = supportClasses;
    }



  /**
    *   Get the Data associated with this TestItem.
    *  <p>
    * @return   The Data associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.data.Data
    * @see #setData
    */
    public ArrayList getData() {
	return m_data;
    }
     
  /**
    *   Set the Data associated with this TestItem.
    *  <p>
    * @param     data The data associated with the TestItem, or NULL.
    * @see com.sun.tgxml.tjtf.api.data.Data
    * @see #getData
    */
    public void setData(ArrayList data) {
	m_data = data;
    }
}
