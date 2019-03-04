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

package com.sun.tgxml.tjtf.api.attributes.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import java.util.ArrayList;
// </importgen>

/**
 * TestGroupAttributes - 
 *
 * <b>TestGroupAttributes</b> is the basic interface for describing the attributes
 * associated with a TestGroup.
 * <p>
 * <b>TestGroupAttributes</b> contains the following (from Attributes):<br><br>
 * <ul>
 *     <li> RequiredResources
 *     <li> AttrElems
 *     <li> TargetSpecs
 *  </ul> <br>
 *
 * <b>TestGroupAttributes</b> additionally contains:<br><br>
 * <ul>
 *     <li> Keywords
 *     <li> Context
 *     <li> ExecuteClass
 *     <li> ExecuteNative
 *     <li> ExecuteArgs
 *     <li> Remote
 *     <li> RMICClasses
 *     <li> SelectIf
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


public  class TestGroupAttributesImpl extends TestAttributesImpl implements TestGroupAttributes  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList m_Keywords;
    private String    m_Context;
    private String    m_ExecuteClass;
    private String    m_ExecuteArgs;
    private String    m_ExecuteNative;
    private ArrayList m_Remotes;
    private String    m_RMICClasses;
    private ArrayList m_SelectIfs;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

    public TestGroupAttributesImpl() {
	super();
	init();
    }


    public TestGroupAttributesImpl(ArrayList reqResources, ArrayList attrElems, ArrayList targetSpecs,
				   ArrayList keywords, String context, 
				   String executeClass, String executeNative, String executeArgs, 
				   ArrayList remotes, String RMICClasses, ArrayList selectIfs, String timeout) {
	super(reqResources, attrElems, targetSpecs, timeout);
	init();
	setKeywords(keywords);
	setContext(context);
	setExecuteClass(executeClass);
	setExecuteNative(executeNative);
	setExecuteArgs(executeArgs);
	setRemotes(remotes);
	setRMICClasses(RMICClasses);
	setSelectIfs(selectIfs);
    }



    private void init() {

	if (m_Keywords == null)
	    m_Keywords = new ArrayList();
	else
	    m_Keywords.clear();

	if (m_Remotes == null)
	    m_Remotes = new ArrayList();
	else
	    m_Remotes.clear();

	if (m_SelectIfs == null)
	    m_SelectIfs = new ArrayList();
	else
	    m_SelectIfs.clear();

	m_Context = null;
	m_ExecuteClass = null;
	m_ExecuteArgs = null;
	m_ExecuteNative = null;
	m_RMICClasses = null;
    }

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
    *   more Keyword objects.
    *  <p>
    * @return     The list of keyword attributes.
    * @see #setKeywords
    */
    public ArrayList getKeywords() {
	return m_Keywords;
    }
     
   /**
    *   Set the keyword attributes associated with this entity.
    *  <p>
    *   The Keywords is always an ArrayList with zero or
    *   more Keyword objects.
    *  <p>
    * @param     keywords The list of keyword attributes.
    * @see #getKeywords
    */
    public void setKeywords(ArrayList keywords){
	m_Keywords = keywords;
    }



  /**
    *   Get the context associated with this TestGroup.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The context associated with this TestGroup
    * @see #setContext
    */
    public String getContext(){
	return m_Context;
    }

   /**
    *   Set the context associated with this TestGroup.
    *  <p>
    *   This context is optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     context The context of the TestGroup.
    * @see #getContext
    */
    public void setContext(String context){
	m_Context = context;
    }



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
    public String getExecuteClass() {
	return m_ExecuteClass;
    }
     
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
    public void setExecuteClass(String executeClass){
	m_ExecuteClass = executeClass;
    }




  /**
    *   Get the execute-arguments associated with this TestGroup.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The execute-arguments associated with this TestGroup
    * @see #setExecuteArgs
    */
    public String getExecuteArgs(){
	return m_ExecuteArgs;
    }
     
   /**
    *   Set the execute-arguments associated with this TestGroup.
    *  <p>
    *   This execute-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     executeArgs The execute-arguments of the TestGroup.
    * @see #getExecuteArgs
    */
    public void setExecuteArgs(String executeArgs){
	m_ExecuteArgs = executeArgs;
    }



  /**
    *   Get the execute-native-arguments associated with this TestGroup.
    *  <p>
    *   This execute-native-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The context associated with this TestGroup
    * @see #setContext
    */
    public String getExecuteNative(){
	return m_ExecuteNative;
    }
     
   /**
    *   Set the execute-native-arguments associated with this TestGroup.
    *  <p>
    *   This execute-native-arguments are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     executeNative The execute-native-arguments of the TestGroup.
    * @see #getContext
    */
    public void setExecuteNative(String executeNative){
	m_ExecuteNative = executeNative;
    }




  /**
    *   Get the "remote" environment-strings associated with this test.
    *  <p>
    *   The Remote attributes is always an ArrayList with zero or
    *   more strings containing "remote" string-values.
    *  <p>
    * @return     The list of remote strings.
    * @see #setRemotes
    */
    public ArrayList getRemotes(){
	return m_Remotes;
    }
     
   /**
    *   Set the "remote" environment-strings associated with this test.
    *  <p>
    *   The Remote attributes is always an ArrayList with zero or
    *   more strings containing "remote" string-values.
    *  <p>
    * @param     remotes The list of remote strings.
    * @see #getRemotes
    */
    public void setRemotes(ArrayList remotes){
	m_Remotes = remotes;
    }


  /**
    *   Get the rmic-classes associated with this TestGroup.
    *  <p>
    *   These rmic-classes are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @return     The context associated with this TestGroup
    * @see #setRMICClasses
    */
    public String getRMICClasses(){
	return m_RMICClasses;
    }
     
   /**
    *   Set the rmic-classes associated with this TestGroup.
    *  <p>
    *   These rmic-classes are optional, and must be either a valid String 
    *   or  NULL.  
    *  <p>
    * @param     rmicclasses The rmic-classes of the TestGroup.
    * @see #getRMICClasses
    */
    public void setRMICClasses(String rmicclasses){
	m_RMICClasses = rmicclasses;
    }



  /**
    *   Get the SelectIfs associated with this test.
    *  <p>
    *   The SelectIfs is always an ArrayList with zero or
    *   more strings containing "SelectIf" string-values.
    *  <p>
    * @return     The list of SelectIfs.
    * @see #setSelectIfs
    */
    public ArrayList getSelectIfs(){
	return m_SelectIfs;
    }
     
   /**
    *   Set the SelectIfs associated with this test.
    *  <p>
    *   The SelectIfs is always an ArrayList with zero or
    *   more strings containing "SelectIf" string-values.
    *  <p>
    * @param     selectifs The list of SelectIfs.
    * @see #getSelectIfs
    */
    public void setSelectIfs(ArrayList selectifs){
	m_SelectIfs = selectifs;
    }

}
