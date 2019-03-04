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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.InlineSupportClass
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.Export;
import com.sun.tgxml.tjtf.api.code.InlineSupportClass;
import com.sun.tgxml.tjtf.api.code.InlineCode;
import java.util.ArrayList;
// </importgen>

/**
 * InlineSupportClass - 
 *
 * <b>InlineSupportClass</b> is the marker interface to a class structure that describes the root of a test description.
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


public  class InlineSupportClassImpl extends SupportClassImpl implements InlineSupportClass, Export {
    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private String m_targetName;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public InlineSupportClassImpl() {
	super();
	init();
    }

    public InlineSupportClassImpl(String langtype, String source, String classID, ArrayList targetSpecs, 
				  String targetName) {
       super(classID, targetSpecs);
       setCodeDelegate(new InlineCodeImpl(langtype, source));
       setTargetName(targetName);
    }


    private void init() {
	setCodeDelegate(new InlineCodeImpl());
	m_targetName = null;
    }


    //------------------------------------------------------------------------------
    //  Getters & Setters
    //------------------------------------------------------------------------------
  /**
    *   (delegate) Get the source.
    *  <p>
    * @return   A string containing the inline'd source.
    * @see #setSource
    */
    public String getSource() {
	return ((InlineCode) getCodeDelegate()).getSource();
    }
     
  /**
    *   (delegate) Set the source.
    *  <p>
    * @param     source The string containing the source.
    * @see #getSource
    */
    public void setSource(String source) {
	((InlineCode) getCodeDelegate()).setSource(source);
    }


  /**
    *   Get the Filename for the exportable.
    *  <p>
    * @return   A string containing the Filename.
    * @see #setTargetFilename
    */
    public String getTargetName() {
	return m_targetName;
    }

     
  /**
    *   Set the Filename for the exportable.
    *  <p>
    * @param     filename The string containing the filename for the exportable.
    * @see #getTargetFilename
    */
    public void setTargetName(String filename) {
	m_targetName = filename;
    }

     
  /**
    *   Is this item exportable (predicate).
    *  <p>
    */
    public boolean isExport() {
	return (m_targetName != null && ! m_targetName.equals("") );
    }

   

}

