/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.indexgen.processors.taghandlers.impl;

import com.sun.tgxml.tjtf.impl.TagsImpl;

/**
 * TagsImpl - 
 *
 * <b>TagsImpl</b> contains the the string names for all XML entity tags, attrib
utes, and
 * enumerated values.  These are used for both parsing and emitting.
 * <p>
 *
 *
 *
 * @author  Artem A. Aliev
 */


/*
 * =============================================================================
===============
 *    TagsImpl
 * =============================================================================
===============
 */


public  class TestSuiteTagsImpl extends TagsImpl {


   /*
    * ==========================================================================
==================
    *    Member Fields
    * ==========================================================================
==================
    */
    
    public static final String  ctStr_tag_testsuite                = "TestSuite";
    public static final String  ctStr_tag_comments                = "Comments";

    public static final String  ctStr_tag_contents                 = "Contents";
    public static final String  ctStr_attr_format                 = "Format";
}
