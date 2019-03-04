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
// DirectoryEntry.java
//
// Author: Kevin A Smith & Rampalli Narasimhan
//
// The Zip List file contains a list of entries. Each entry is nothing but a 
// file or a directory name, the size, date and time stamp. For the purpose 
// of this program we just need the following information:
// 
// The name of the file or directory
// The size of the file or directory (O if its a directory)
// The status - whether this is a file or a directory?
//

package com.sun.jck.utils.jckfilecheck;
import java.io.File;

public class DirectoryEntry implements Comparable {
    private String entry;
    private long size;
    private boolean isDirectory;

    //
    // Constructors for this class
    //
    public DirectoryEntry () {
	entry = null;
	size  = 0;
	isDirectory = false;
    }
    public DirectoryEntry (File fn) {
	entry = fn.getAbsolutePath(); 
	size = fn.length();
	isDirectory = fn.isDirectory();
    }

    public DirectoryEntry (String baseDirectory, File fn) {
	entry = fn.getAbsolutePath().substring (baseDirectory.length());
	this.isDirectory = fn.isDirectory();
	size = fn.length();
    }

    public DirectoryEntry (String fileName, long size) {
	if (size == 0L) {
	    this.isDirectory = true;
	}
	else {
	    this.isDirectory = false;
	}
	this.size = size;
	entry = fileName;
    }

    //
    // Implement the interface Comparable
    //
    public int compareTo (Comparable obj) {
        int retval;
        if (obj instanceof DirectoryEntry) {
	    retval = this.entry.compareTo (((DirectoryEntry) obj).entry);
	}
        else {
	    retval = -1;
	}
        return retval;
    }

    public boolean equals (Object obj) {
        boolean retval;
        if (obj instanceof DirectoryEntry) {
	    retval = this.entry.equals(((DirectoryEntry) obj).entry);
        }
        else {
	    retval = false;
	}
        return retval;
    }

    //
    // This is a set of helper functions that are added to ease
    // the interaction with the DirectoryEntry objects.
    //
    public String toString () {
        return new String (entry + "\t" + size);
    }

    public String getFileName () {
        return entry;
    }

    public long getSize() {
	return isDirectory ? 1L : size;
    }

    public boolean isDirectory() {
	return isDirectory;
    }
  
    public String getEntry() {
	return entry;
    }

    public long getSizeValue() {
	return size;
    }
}






