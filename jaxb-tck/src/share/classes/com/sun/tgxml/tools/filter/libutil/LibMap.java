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

package com.sun.tgxml.tools.filter.libutil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import com.sun.tgxml.tjtf.api.tests.Library;

/**
 * An object that maps libID to accepted variant IR or null, if library
 * is rejected.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class LibMap implements LibSelectionInfo {

    private HashMap map = null;

    // the flag that signals wether accepted and rejected lists are 
    // up to date or not.
    // method put() set this flag (these lists should be updated)
    // method prepareLists() reset this flag
    private boolean isUpdated = true;

    // list of accepted libIDs
    private ArrayList accList = null;

    // list of rejected libIDs
    protected ArrayList rejList = null;


    /**
     * Creates an empty map.
     */
    public LibMap() {
        map = new HashMap();
    }

    /**
     * Puts libID with specified variant into the map.
     * @throws IllegalArgumentException if libID is null
     */
    public void put(String libID, Library var) {
        if (libID == null) 
            throw new IllegalArgumentException("libID should be null");
        map.put(libID, var);
        isUpdated = true;
    }

    /**
     * Returns accepted library variant by its libID or null if library 
     * rejected.
     * @throws IllegalArgumentException if the map does not contain libID
     */
    public Library get(String libID) {
        if (!map.containsKey(libID)) {
            throw new IllegalArgumentException(
                "LibMap does not contain library: " + libID);
        }
        return (Library)map.get(libID);
    }


    /**
     * Returns the list of accepted libIDs
     */
    public ArrayList accepted() {
        if (isUpdated) {
            prepareLists();
        }
        return accList;
    }

    /**
     * Returns the list of rejected libIDs
     */
    public ArrayList rejected() {
        if (isUpdated) {
            prepareLists();
        }
        return rejList;
    }

    /**
     * Returns Iterator over libIDs
     */
    public Iterator iterator() {
        return map.keySet().iterator();
    }


    // calculates lists of accepted/rejected libraries
    private void prepareLists() {
        accList = new ArrayList();
        rejList = new ArrayList();
        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            String libID = (String)it.next();
            if (map.get(libID) == null) {
                rejList.add(libID);
            } else {
                accList.add(libID);
            }
        }
        isUpdated = false;
    }

    /**
     * Returns the String representation of LibMap if format:
     * libID: + <Selected VarID>  for accepted libs
     * libID: -                   for rejected libs
     */
     public String toString() {
         StringBuffer sb = new StringBuffer();
         for (Iterator it = map.keySet().iterator(); it.hasNext();) {
             String libID = (String)it.next();
             sb.append(libID + ": ");
             Library lib = get(libID);
             if (lib == null) {
                 sb.append("- ");
             } else {
                 sb.append("+ ");
                 String variantID = get(libID).getVarID();
                 sb.append(variantID == null ? "<NoVarID>" : variantID);
             }
             sb.append("\n");
         }
         return sb.toString();
     }

}
