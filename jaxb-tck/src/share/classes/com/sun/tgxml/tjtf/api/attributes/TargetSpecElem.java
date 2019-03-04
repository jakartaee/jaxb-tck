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

package com.sun.tgxml.tjtf.api.attributes;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.common.NameValuePair;
// </importgen>

/**
 * TargetSpecElem - 
 *
 * <b>TargetSpecElem</b>  is an (extension) element that provides additional (fine-grained) 
 * spec information about what elements of a specification that a test uses
 * TargetSpecElem is a 'name' and 'value' pair that is a requirement of a repository. 
 * 'name' and 'value' attributes are generic strings. 
 *
 * @version   1.1, 10/28/2002
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    TargetSpecElem
 * ============================================================================================
 */


public interface TargetSpecElem extends NameValuePair {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

  /**
    *   Determine if two Repository SpecificAttribute's are equal.
    *  <p>
    * @param      other  the other TargetSpecElem object
    * @return     true if the RepositorySpecificAttributes are equal
    */
    public boolean equals(TargetSpecElem other);

}
