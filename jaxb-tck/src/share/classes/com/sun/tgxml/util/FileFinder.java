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

package com.sun.tgxml.util;

import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *  FileFinder a class that provides a file finding from the specified
 *  initinal direcotry that staticfies filtering conditions.
 */
public abstract class FileFinder {

    public FileFinder() {
    }

    /** 
     * This method recursively descends the directory hierarchy for
     * each passed dir. It returns list of all files that match filtering
     * condition (checkFile(file) should return true).
     * It walks through subdir if processDir(subdir) returns true.
     */
    ArrayList found = null;
    public final ArrayList findFrom(File[] dir) {
        found = new ArrayList();
        for (int i = 0; i < dir.length; i++) {
            find(dir[i]);
        }
        return found;
    }

    /**
     * Returns true if specified dir should be processed. 
     * Default implementation returns false only if dir is "SCCS" dir.
     */     
    protected boolean processDir(File dir) {
        return 
           dir != null && dir.isDirectory() && !"SCCS".equals(dir.getName());
    }

    /**
     * Returns true if specified file saticfy the filtering condition.
     * Subclasses should implement this method to provide a filtering
     */    
    protected abstract boolean acceptFile(File file);


    /**
     * Checks all files in the passed dir and class find() method
     * for all appropriate subdirs.
     */    
    private void find(File dir) {
       if (!processDir(dir))
           return;
       if (acceptFile(dir)) {
           found.add(dir);
       }
       File[] list = dir.listFiles();
       if (list == null) 
           return;

       for (int i = 0; i < list.length; i++) {
           if (list[i].isDirectory()) {
               find(list[i]);
           } else if (acceptFile(list[i])) {
               found.add(list[i]);
           }
       }
    }
}
