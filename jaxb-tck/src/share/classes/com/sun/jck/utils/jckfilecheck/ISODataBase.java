/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

//
// ISODataBase.java
//

//
// This file creates an ISODataBase class. This class will hold an entry and 
// make note of its ISO-9660 status. A file may violate the ISO specification
// in more than one way. The first violation will be noted. All others will be
// ignored.
//
// status will contain one of the following values:
//
// 0 - Confirms to the ISO-9660 Specificiation
// 1 - The Directory name is Invalid (> 8 chars or contains unacceptable chars)
// 2 - Nesting of sub-directories, greater than 8
// 3 - Filename is NOT valid
// 4 - When filename is reduced to 8.3, there is a clash within that directory
// 5 - Filename is greater than 8.3 format - either name or extn. NOT VERY IMPORTANT
// 6 - Total Directory Name length > 32 - Mac's may pose a problem
// 7 - When the directory name is reduced to 8, then there is a clash
//


package com.sun.jck.utils.jckfilecheck;

import java.io.File;

public class ISODataBase implements Comparable {

    private String entry;
    private int status;

    public static final int OK = 0;
    public static final int INV_DIR = 1;
    public static final int INV_DIR_DEPTH = 2;
    public static final int INV_FILE = 3;
    public static final int CLASH_FILE = 4;
    public static final int LONG_FILE = 5;
    public static final int LONG_DIR = 6;
    public static final int CLASH_DIR = 7;

    public ISODataBase (File fn) {
	entry = fn.getAbsolutePath();
	status = 0;
    }
  
    public ISODataBase (String baseDirectory, File fn) {
	entry = fn.getAbsolutePath().substring (baseDirectory.length());
	status = 0;
    }

    public ISODataBase (String file, int status) {
	entry = file;
	this.status = status;
    }

    public int getStatus () {
	return status;
    }
    public String getEntry() {
	return entry;
    }
    //
    // Implement the comparable interface
    //
    public int compareTo (Comparable obj) {
	int retVal;
	if (obj instanceof ISODataBase) {
	    retVal = this.entry.compareTo (((ISODataBase) obj).entry);
	}
	else {
	    retVal = -1;
	}
	return retVal;
    }

    public boolean equals (Comparable obj) {
	boolean retVal;
	if (obj instanceof ISODataBase) {
	    retVal = this.entry.equals (((ISODataBase) obj).entry);
	}
	else {
	    retVal = false;
	}
	return retVal;
    }
}
