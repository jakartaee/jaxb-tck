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

package com.sun.tgxml.tjtf.api.data.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.data.impl.DataTypeImpl
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * DataTypeImpl - 
 *
 * <b>DataTypeImpl</b>  is the enumeration for a type of data.
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    DataTypeImpl
 * ============================================================================================
 */


public class DataTypeImpl implements DataType {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    final static public String cStr_Resource = "resource";
    final static public String cStr_IOData = "iodata";

    final static private DataType Resource = new DataTypeImpl(cStr_Resource);
    final static private DataType IOData = new DataTypeImpl(cStr_IOData);

    private String m_type;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------


  /**
    *   DataTypeImpl Constructor (cannon).
    *  <p>
    *   Name and Value are non-null strings
    *  <p>
    * @return     The DataType associated with this documentation
    * @see com.sun.tgxml.tjtf.api.common.NameValuePair
    */
    private DataTypeImpl(String value){
	m_type = value;
    }


    //------------------------------------------------------------------------------
    //  Factory methods
    //------------------------------------------------------------------------------


  /**
    *   Factory DataType Constructor (Resource).
    *  <p>
    * @return     The Resource DataType associated with this Data
    */
    static public DataType createResource(){
	return Resource;
    }
     

  /**
    *   Factory DataType Constructor (IOData).
    *  <p>
    * @return     The IOData DataType associated with this Data
    */
    static public DataType createIOData(){
	return IOData;
    }
          
          

    //------------------------------------------------------------------------------
    //  Predicate methods
    //------------------------------------------------------------------------------


  /**
    *   Is this an Resource type.
    *  <p>
    * @return     The true if this technique is Resource.
    */
    public boolean isResource(){
	return (this == Resource);
    }
     

  /**
    *   Is this an IOData type.
    *  <p>
    * @return     The true if this technique is IOData.
    */
    public boolean isIOData(){
	return (this == IOData);
    }
     

    //------------------------------------------------------------------------------
    //  Factory methods
    //------------------------------------------------------------------------------


  /**
    *  return the string value of the DataType.
    *  <p>
    * @return     The DataType value.
    */
    public String toString(){
	return m_type;
    }
     

}
