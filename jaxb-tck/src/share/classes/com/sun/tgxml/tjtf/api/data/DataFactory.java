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

package com.sun.tgxml.tjtf.api.data;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.DataFactory
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.data.impl.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
// </importgen>

/**
 * DataFactory - 
 *
 * <b>DataFactory</b> is a static factory class for creating data objects (InlineData, ExternalData). 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    DataFactory
 * ============================================================================================
 */


public  final class DataFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private DataFactory () {

    }

    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    
    
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Factories
    //------------------------------------------------------------------------------


  /**
    *   Create an (uninitialized) InlineData object.
    *  <p>
    * @return   a new InlineData.
    * @see com.sun.tgxml.tjtf.api.data.InlineData
    */
    static public InlineData  createInlineData() {
	return new InlineDataImpl();
    }
     

  /**
    *   Create an (cannon) InlineData object.
    *  <p>
    * @param data The InlineData's data
    * @param targetFilename The InlineData's target filename
    * @param type The InlineData's data-type
    * @return   a new InlineData.
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.InlineData
    */
    static public InlineData  createInlineData (String data, String targetFilename, DataType type ) throws TestFileException {
	InlineData id = createInlineData();
	id.setData(data);
	id.setTargetName(targetFilename);
	id.setType(type);

	return id;
    }
     



  /**
    *   Create an (clone) InlineData object.
    *  <p>
    * @param origID The InlineData to be cloned
    * @return   a new (cloned) InlineData.
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.InlineData
    */
    static public InlineData  cloneInlineData (InlineData origID ) 
	throws TestFileException {
	if (origID == null)
	    return null;

	InlineData cloneID = createInlineData();

	// let sub-classes override their clone fields
	cloneOverrideInlineData(origID, cloneID);

	cloneID.setData(origID.getData());
	cloneID.setTargetName(origID.getTargetName());
	cloneID.setType(origID.getType());

	return cloneID;
    }
     


  /**
    *   Override this method to set fields on sub-classes of InlineData.
    *  <p>
    * @param origID The InlineData to be cloned
    * @param cloneID The InlineData clone
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.InlineData
    */
    static public void  cloneOverrideInlineData (InlineData origID, InlineData cloneID ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) ExternalData object.
    *  <p>
    * @return   a new ExternalData.
    * @see com.sun.tgxml.tjtf.api.data.ExternalData
    */
    static public ExternalData  createExternalData() {
	return new ExternalDataImpl();
    }
     

  /**
    *   Create an (cannon) ExternalData object.
    *  <p>
    * @param sourceFilename The ExternalData's filename
    * @return   a new ExternalData.
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.ExternalData
    */
    static public ExternalData  createExternalData (String sourceFilename ) throws TestFileException {
	ExternalData ed = createExternalData();
	ed.setSourceName(sourceFilename);

	return ed;
    }



  /**
    *   Create an (clone) ExternalData object.
    *  <p>
    * @param origED The ExternalData to be cloned
    * @return   a new (cloned) ExternalData.
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.ExternalData
    */
    static public ExternalData  cloneExternalData (ExternalData origED ) 
	throws TestFileException {
	if (origED == null)
	    return null;

	ExternalData cloneED = createExternalData();

	// let sub-classes override their clone fields
	cloneOverrideExternalData(origED, cloneED);

	cloneED.setSourceName(origED.getSourceName());
	cloneED.setType(origED.getType());

	return cloneED;
    }
     


  /**
    *   Override this method to set fields on sub-classes of ExternalData.
    *  <p>
    * @param origED The ExternalData to be cloned
    * @param cloneED The ExternalData clone
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.ExternalData
    */
    static public void  cloneOverrideExternalData (ExternalData origED, ExternalData cloneED ) throws TestFileException {

    }

     

  /**
    *   Create an (cannon) ExternalData object.
    *  <p>
    * @param dt The Data type descripter.
    * @return   a new ExternalData.
    * @throws TestFileException if there is a parameter problem.
    * @see com.sun.tgxml.tjtf.api.data.DataType
    */
    static public DataType  createDataType (String dt ) throws TestFileException {
	DataType dtp = null;

	if (dt == null)
	    throw new TestFileException("Null data type string.");

	if (dt.equals(DataTypeImpl.cStr_Resource))
	    dtp =  DataTypeImpl.createResource();

	else if (dt.equals(DataTypeImpl.cStr_IOData))
	    dtp = DataTypeImpl.createIOData();
	else
	    throw new TestFileException("Undefined DataType.");

	return dtp;
    }
     
}
