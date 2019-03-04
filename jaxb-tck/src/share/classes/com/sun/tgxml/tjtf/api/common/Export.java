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

package com.sun.tgxml.tjtf.api.common;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.common.Export
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
// </importgen>

/**
 * Export - 
 *
 * <b>Export</b> describes that an internal code/data element is to be exported to
 * an external file.
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    Export 
 * ============================================================================================
 */


public  interface Export {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */

  /**
    *   Get the Filename for the exportable.
    *  <p>
    * @return   A string containing the Filename.
    * @see #setTargetName
    */
    public String getTargetName();
     
  /**
    *   Set the Filename for the exportable.
    *  <p>
    * @param     filename The string containing the filename for the exportable.
    * @see #getTargetName
    */
    public void setTargetName(String filename);

     
  /**
    *   Is this item exportable (predicate).
    *  <p>
    * @return true if this object is exportable.
    */
    public boolean isExport();

   

}

