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

package com.sun.jmpp.lib;

import java.util.Hashtable;

/**
 * This interface represents a set of properties typically used by the 
 * API tests generation library. Developers who may want to configure
 * JmppLibAPI dynamically via configuration class should implement this
 * interface.
 */
public interface LibAPIProperties {
    
    /**
     * Returns copyright notice to be inserted into test code.
     */
    public String getCopyrightNotice();

    /**
     * Returns the default imports.
     */
    public String getDefaultImports();
    
    /**
     * Returns hastable containing baseClass - execute arguments pairs.
     */
    public Hashtable getBaseClassExecuteArgs();
    
    /**
     * Returns default keywords.
     */
    public String getDefaultKeywords();
    
    /**
     * Returns default rmic keywords.
     */
    public String getDefaultRmicKeywords();
    
    /**
     * Returns hashtable containing baseclass name - full baseclass name paris
     */
    public Hashtable getBaseClasses();
    
    /**
     * Returns package prefix to be appended into a package name
     * of the tests generated.
     */
    public String getPackagePrefix();
    
    /**
     * Returns maximum file name length allowed for output files.
     */
    public int getMaxFileNameLength();

    /**
     * Returns short html copyright notice
     */
    public String getHtmlCopyrightNotice();

}
