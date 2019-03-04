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

import java.util.ArrayList;

/**
 * TestGroupLibsInfo is an utility class to be used by TestFilter
 * to retrieve information about library by libID.
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class TestGroupLibsInfo implements LibSelectionInfo {

    /**
     * Contstructs information about library selection and library type 
     * (inline/external).
     */
    public TestGroupLibsInfo(LibSelectionInfo extLibs, 
            LibSelectionInfo inlineLibs) {

        ArrayList inlineAccList = inlineLibs.accepted();
        ArrayList inlineRejList = inlineLibs.rejected();

        inlineList = new ArrayList(inlineAccList.size() + inlineRejList.size());
        inlineList.addAll(inlineAccList);        
        inlineList.addAll(inlineRejList);        
        accList = (ArrayList)(extLibs.accepted().clone());
        accList.removeAll(inlineAccList); // removes duplicates libIDs
        accList.addAll(inlineAccList);

        rejList = (ArrayList)(extLibs.rejected().clone());
        rejList.removeAll(inlineRejList); // removes duplicates libIDs
        rejList.addAll(inlineRejList);

    }


    /**
     * list of accepted libIDs
     */
    protected ArrayList accList = null;

    /**
     * list of rejected libIDs
     */
    protected ArrayList rejList = null;

    /**
     * list of inline libIDs
     */
    protected ArrayList inlineList = null;



    /**
     * Returns the list of accepted libIDs
     */
    public ArrayList accepted() {
        return accList;
    }

    /**
     * Returns the list of rejected libIDs
     */
    public ArrayList rejected() {
        return rejList;
    }

    /**
     * Returns the list of inline libIDs
     */
    public ArrayList inline() {
        return inlineList;
    }


    /**
     * Returns true if library is accepted (libID belongs to accepted list)
     */
    public boolean isAccepted(String libID) {
       return accList.contains(libID);
    }

    /**
     * Returns true if library is known (libID belongs to accepted 
     * or rejected list)
     */
    public boolean isKnown(String libID) {
       return accList.contains(libID) || rejList.contains(libID);
    }

    /**
     * Returns true if library is inline library (libID belongs to 
     * inline list)
     */
    public boolean isInline(String libID) {
       return inlineList.contains(libID);
    }


}
