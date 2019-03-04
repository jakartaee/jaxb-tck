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

package com.sun.tgxml.tjtf.api.tests.impl;

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.InlineLibrary;

/**
 * Library - 
 *
 * <b>Library</b> is the entity that describes a library that TestCodes depend on.  
 * <p>
 * <b>Library</b>s derive from <b>TestItem</b>. These properties describe
 * the comments, assertions, and descriptions that this class contains.
 * <p>
 *
 * @version 	1.1, 30/11/03
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    InlineLibraryImpl
 * ============================================================================================
 */


public  class InlineLibraryImpl extends LibraryImpl implements InlineLibrary  {

    protected TestGroup parentTestGroup = null;

    public void setTestGroup(TestGroup tg) {
        parentTestGroup = tg;
    }

    public TestGroup getTestGroup() {
        return parentTestGroup;
    }


  /**
    *  Returns true 
    */
    public boolean isInline() {
        return true;
    }
     
  /**
    *  Does nothing
    */
    public void setInline(boolean isInline) {
    }

}
