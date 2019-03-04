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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.impl.AttributesImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import java.util.ArrayList;
// </importgen>

/**
 * Attributes - 
 *
 * <b>Attributes</b> is the basic interface for describing the build and target attributes
 * that a test can have.
 * <p>
 * <b>Attributes</b> contains the following:<br><br>
 * <ul>
 *     <li> RequiredResources
 *     <li> AttrElems
 *     <li> TargetSpecs
 *  </ul> <br>
 *
 * <p>
 * <b>Attributes</b> are registered with a <b>TestGroup</b>, <b>Library</b>, and <b>TestCase</b> 
 *  elements.  
 * <p> 
 * Each of the fields above have setter and getter methods that return information based
 * on the local description for that element.  
 * <p>
 *
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Attributes
 * ============================================================================================
 */


public  class  AttributesImpl implements Attributes  {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
   
    private ArrayList m_RequiredResources;
    private ArrayList m_AttrElems;
    private ArrayList m_TargetSpecs;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

    public AttributesImpl() {
	init();
    }


    public AttributesImpl(ArrayList reqResources, ArrayList attrElems, ArrayList targetSpecs) {
	init();
	setRequiredResources(reqResources);
	setAttrElems(attrElems);
	setTargetSpecs(targetSpecs);
    }



    private void init() {
	if (m_RequiredResources == null)
	    m_RequiredResources = new ArrayList();
	else
	    m_RequiredResources.clear();

	if (m_AttrElems == null)
	    m_AttrElems = new ArrayList();
	else
	    m_AttrElems.clear();

	if (m_TargetSpecs == null)
	    m_TargetSpecs = new ArrayList();
	else
	    m_TargetSpecs.clear();


    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**
    *   Get the required-resources associated with this entity.
    *  <p>
    *   The RequiredResources is always an ArrayList with zero or
    *   more RequiredResource objects.
    *  <p>
    * @return     The list of required resources.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    * @see #setRequiredResources
    */
    public ArrayList getRequiredResources() {
	return m_RequiredResources;
    }
     
   /**
    *   Set the required-resources associated with this entity.
    *  <p>
    *   The RequiredResources is always an ArrayList with zero or
    *   more RequiredResource objects.
    *  <p>
    * @param     reqres The list of required resources.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    * @see #getRequiredResources
    */
    public void setRequiredResources(ArrayList reqres) {
	m_RequiredResources = reqres;
    }


  /**
    *   Get the repository-specific attributes associated with this entity.
    *  <p>
    *   The AttrElems is always an ArrayList with zero or
    *   more AttrElem objects.
    *  <p>
    * @return     The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    * @see #setAttrElems
    */
    public ArrayList getAttrElems() {
	return m_AttrElems;
    }
     
   /**
    *   Set the repository-specific attributes associated with this entity.
    *  <p>
    *   The AttrElems is always an ArrayList with zero or
    *   more AttrElem objects.
    *  <p>
    * @param     attrelems The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    * @see #getAttrElems
    */
    public void setAttrElems(ArrayList attrelems) {
	m_AttrElems = attrelems;
    }



  /**
    *   Get the specifications associated with this entity.
    *  <p>
    *   The TargetSpecs is always an ArrayList with zero or
    *   more TargetSpec objects.
    *  <p>
    * @return     The list of specifications.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    * @see #setTargetSpecs
    */
    public ArrayList getTargetSpecs() {
	return m_TargetSpecs;
    }
     
   /**
    *   Set the specifications associated with this entity.
    *  <p>
    *   The TargetSpecs is always an ArrayList with zero or
    *   more TargetSpec objects.
    *  <p>
    * @param     specs The list of specifications.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    * @see #getTargetSpecs
    */
    public void setTargetSpecs(ArrayList specs) {
	m_TargetSpecs = specs;
    }



}
