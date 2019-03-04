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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.impl.SupportClassImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.Export;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.code.Code;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import java.util.ArrayList;
// </importgen>

/**
 * SupportClass - 
 *
 * <b>SupportClass</b> is the marker interface to a class structure that describes the root of a test description.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineSupportClassImpl 
 * ============================================================================================
 */


public  class SupportClassImpl implements SupportClass {
    /*
     * ============================================================================================
     *    constants
     * ============================================================================================
     */
    private static final String TARGET_SPEC_LEGACY_MODE = "legacymode.support_class.targetspec";
    private static final String TRUE = "true";
    
    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private String    m_classID;
    private ArrayList m_targetSpecs;
    private Code      m_codeDelegate;

    private boolean isTargetSpecLegacyMode = TRUE.equals(BuildProperties.getString(TARGET_SPEC_LEGACY_MODE));
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public SupportClassImpl() {
	init();
    }

    public SupportClassImpl(String classID, ArrayList targetSpecs) {
	m_classID = classID;
	setTargetSpecs(targetSpecs);
    }


    private void init() {
	m_classID = null;
	m_targetSpecs = new ArrayList();
    }


    //------------------------------------------------------------------------------
    //  Getters & Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the ClassID string.
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setClassID
    */
    public Code getCodeDelegate() {
	return m_codeDelegate;
    }
     
  /**
    *   Set the ClassID string.
    *  <p>
    * @param     classID The string containing the class ID.
    * @see #getClassID
    */
    public void setCodeDelegate(Code del) {
	m_codeDelegate = del;
    }


  /**
    *   (delegate impl) Get the source-language of this code  (default is "java").
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setSourceLang
    */
    public String getSourceLang() {
	return m_codeDelegate.getSourceLang();
    }
     
  /**
    *   (delegate impl) Set the source-language of this code  (default is "java").
    *  <p>
    * @param     sourcelang The string containing the source language.
    * @see #getSourceLang
    */
    public void setSourceLang(String sourcelang) {
	m_codeDelegate.setSourceLang(sourcelang);
    }
   

  /**
    *   Get the ClassID string.
    *  <p>
    * @return   A string containing the name of a source language.
    * @see #setClassID
    */
    public String getClassID() {
	return m_classID;
    }
     
  /**
    *   Set the ClassID string.
    *  <p>
    * @param     classID The string containing the class ID.
    * @see #getClassID
    */
    public void setClassID(String classID) {
	m_classID = classID;
    }




  /**
    *   Get the TargetSpecs associated with this support-class.
    *  <p>
    *   The TargetSpecs list is always defined, and may contain zero or more entries.
    *  <p>
    * @return     The TargetSpecs associated with this documentation
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    * @see #setTargetSpecss
    */
    public ArrayList getTargetSpecs() {
        checkTargetSpecs(m_targetSpecs);
	return m_targetSpecs;
    }

    /**
     * checks that TargetSpec are supported in cirrent mode. If a
     * TargetSpec is defined and current mode is not legacy mode, then
     * IllegalStateException is thrown.
     */
    private void checkTargetSpecs(ArrayList targetspecs) throws IllegalStateException {
        if ((m_targetSpecs != null) && (m_targetSpecs.size() > 0)) {
            if (isTargetSpecLegacyMode) {
                System.err.println("WARNING! TargetSpec in SupportClass is not supported!");
            } else {
                throw new IllegalStateException("The TargetSpec is not supported in non-legacy mode");
            }
        }
    }
    
   /**
    *   Set the TargetSpecs associated with this support-class.
    *  <p>
    *   The TargetSpecs list is always defined, and may contain zero or more entries.
    *  <p>
    * @param     targetspecs The ArrayList of target-spec entries.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    * @see #getSpecElems
    */
    public void setTargetSpecs(ArrayList targetspecs) {
        checkTargetSpecs(targetspecs);
	if (targetspecs == null)
	    targetspecs.clear();
	else
	    m_targetSpecs = targetspecs;
    }

   

}

