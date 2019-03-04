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

package com.sun.tgxml.tjtf.processors.validator;

import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.processors.impl.ProcessorImpl;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.exceptions.*;
import com.sun.tgxml.tjtf.tools.options.FlagOption;
import com.sun.tgxml.tjtf.tools.options.ParseArgumentException;
 
/** 
 * NullValidator - The basic engine for a validator. 
 * <p>
 * A null validator accepts anything thrown at it (except null).
 * 
 * 
 * @version 	1.0, 10/02/97 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    NullValidator 
 * ============================================================================================ 
 */ 
public class NullValidator extends ProcessorImpl implements IRValidator {


 


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 
    /** The debugging flag for this validator. */
    protected boolean m_debug;

   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 
 
   /** 
    *   NullValidator constructor - 
    *       Initialize our internal fields. 
    */ 
    public NullValidator() {
	init();
    }


   /** 
    *   init internal fields. 
    */ 
    private void init() {
	m_debug = false;
    }


   /** 
    *   Setter for the debug flag. 
    */ 
    public void setDebug(boolean debug) {
	m_debug = debug;
    }



   /* 
    * -------------------------------------------------------------------------------------------- 
    *    IRValidator Interface implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 

  /**
    *  Primary Validation contract.
    * <p>
    *  The validator takes an IRObj tree, and simply returns normally if
    *  the tree validates.  A Validator exception is thrown otherwise.
    * <p>
    * This method is not overrideable, but calls _validate (which is overrideable).
    * This method enforces that the objTree is not NULL, and that the
    * validator can accept the root-object that describes this tree.
    * <p>
    * @param objTree The IR object tree to be validated.
    * @exception ValidatorException if there is a violation.
    * @see #accepts
    */
    public final void validate (IRObj objTree) throws ValidatorException {
	if (objTree == null)
	    throw new ValidatorException(LibResHandler.getResStr("nullvalidator.error.irobj.null"));

	if (! accepts(objTree))
	    throw new ValidatorException(LibResHandler.getResStr("nullvalidator.error.type.notaccept", objTree.getClass().getName()));

	_validate(objTree);
    }


   /* 
    * ----------------------------------------------------------------------
    *    Options parsing methods 
    * ----------------------------------------------------------------------
    */

    FlagOption debugOption = new FlagOption("-Vdiag", 
            "  -Vdiag   debug mode");

    /**
     * Registers -debug option
     */
    public void registerOptions() {

        super.registerOptions();

        addOption(debugOption);

    }

    /**
     * Applies values for options registered by <tt>registerOptions()</tt> 
     */
    public void applyOptionsValues() throws ParseArgumentException {
        if (debugOption.isSet()) {
	    setDebug(true); 
        }

        super.applyOptionsValues();
    }


   /* 
    * -------------------------------------------------------------------------------------------- 
    *    NullValidator implementation methods 
    * -------------------------------------------------------------------------------------------- 
    */ 


  /**
    * Describes the type of IRObj tree roots that this validator implementation knows how to handle.
    * <p>
    * Typically, a validator sub-classes accepts()  does an "instanceof" comparison of the objTree to any known
    * root classes that the validator knows how to validate. i.e.
    * <p>
    * <code><pre>
    *   if (objTree instanceof TestRoot)
    *     return true;
    *   else
    *     return false;
    * </pre></code>
    * <p>
    *
    * @param objTree The IR object tree to be validated.
    * @return false if the validator doesn't validate the given IRObj type.
    */
   
    public boolean accepts(IRObj objTree) {
	return true;
    }



  /**
    *  Primary Validation override.
    * <p>
    *  Validator Sub-classes override this method to start the validation process.
    * <p>
    * A NullValidator simply validates everything, so there is no implementation.
    * <p>
    * @param objTree The IR object tree to be validated.
    * @exception ValidatorException if there is a violation.
    */   
    public void _validate (IRObj objTree) throws ValidatorException {
    }
   
}
