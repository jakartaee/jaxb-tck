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

import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.api.code. LibraryDependency;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tools.filter.processors.FilteringException;
import com.sun.tgxml.tools.filter.FilterUtil;
/**
 * Default impementation of LibDependencyFilter
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class DefaultLibDependencyFilter implements LibDependencyFilter {

    /**
     * Creates DefaultLibDependencyFilter instance 
     */
    public DefaultLibDependencyFilter() {
    }

    // list of library id to be rejected
    protected ArrayList toReject;

    // map libID <--> library state
    protected HashMap libState = null;

    // library state names
    protected static final int S_UNPROCESSED = 0;
    protected static final int S_TO_REJECT   = 1;
    protected static final int S_ACCEPTED    = 2;
    protected static final int S_REJECTED    = 3;
    protected static final int S_UNKONW      = 4;

   
    // resulting LibMap
    // libID --> selected variant
    protected LibMap libMap = null;

    // bundle of libs to filtered
    // libID --> variants of libID
    protected VariantsMap bundle = null;

    // libID --> variants dependent on libID
    protected VariantsMap deps = null;


    /**
     * Provides filtering of passed library bundle by dependencies.
     * @return     LibMap with the same number of libID as libBundle has,
     *             where all library depends only on accepted library.
     * @exception  FilteringException if one variants depends on unknown
     *             library, or library variant cannot be selected between
     *             two accepted ones.
     */  
    public LibMap filter(VariantsMap libBundle, LibSelectionInfo filtered)
            throws FilteringException {

        libState = new HashMap();
        toReject = new ArrayList();

        libMap = new LibMap();       // libID --> selected variant
        bundle = new VariantsMap();  // libID --> variants of libID

        ArrayList allVariants = new ArrayList();

        // initial marks:
        for (Iterator it = libBundle.libIDs(); it.hasNext();) {
            String libID = (String)it.next();
            if (libBundle.variants(libID).size() > 0) {
                ArrayList libVars = libBundle.variants(libID);
                for (int i = 0; i < libVars.size(); i++) {
                    bundle.addVariant(libID, (Library)libVars.get(i));
                }
                allVariants.addAll(libVars);
                mark(libID, S_UNPROCESSED);
            } else {
                mark(libID, S_TO_REJECT);
            }
        }

        if (filtered != null) {
            for (Iterator it = filtered.accepted().iterator(); it.hasNext();) {
                String libID = (String)it.next();
                if (state(libID) == S_UNKONW) {
                    mark(libID, S_ACCEPTED);
                }
            }
            for (Iterator it = filtered.rejected().iterator(); it.hasNext();) {
                String libID = (String)it.next();
                if (state(libID) == S_UNKONW) {
                    mark(libID, S_TO_REJECT);
                }
            }            
        }

        createDependenciesMap(allVariants);
        removeRejected();
        selectVariants();
        checkForInlineDependencies();

        return libMap;
    }

    /**
     * Create deps VariantsMap: libID --> variants dependent on libID
     * @param vars  list of all variants of all libraries
     * @exception FilteringException if some variant depends on unknown library
     */
    protected void createDependenciesMap(ArrayList vars)
            throws FilteringException {
        deps = new VariantsMap();
        for (Iterator varIt = vars.iterator(); varIt.hasNext();) {
            Library variant = (Library)varIt.next();
            ArrayList depList;
            try {
                depList = IR.getDependentLibs(variant);
            } catch (TestFileException e) {
                throw new FilteringException("Cannot get dependencies for: " +
                        variant + ", because: " + e);
            }

            for (Iterator depIt = depList.iterator(); depIt.hasNext();) {
                String libID = (String)depIt.next();
                int state = state(libID);
                if (state == S_UNKONW) {
                    throw new FilteringException("Library: [ID='" +
                        IR.getID(variant) + "' VarID='" + variant.getVarID() +
                        "'] depends on unknown libID: " + libID);
                } if (state != S_ACCEPTED) {
                    deps.addVariant(libID, variant);
                }
            }
        }
    }

    /**
     * Removes variants dependent on rejected libraries from the bunlde.
     */
    protected void removeRejected() {
        while (!toReject.isEmpty()) {
            String rejectedLib = (String)toReject.get(0);
            ArrayList rejectedVars = deps.variants(rejectedLib);
            if (rejectedVars != null) {
                for (Iterator it = rejectedVars.iterator(); it.hasNext();) {
                    removeVariant((Library)it.next());
                }
            }
            mark(rejectedLib, S_REJECTED);
            libRejected(rejectedLib);
        }
    }


    /**
     * Removes variant from the bundle.
     * Marks library as TO_REJECT if all its variants are removed
     */
    protected void removeVariant(Library var) {
        String libID = IR.getID(var);
        boolean isRemoved = bundle.removeVariant(libID, var);
        if (isRemoved && bundle.variants(libID).size() == 0) {
            mark(libID, S_TO_REJECT);
        }
    }

    /**
     * Select more appropriate variant for each accepted library 
     * from the bundle.
     * @exception FilteringException if cannot make a selection 
     *            between of two variants.
     */
    protected void selectVariants() throws FilteringException {
        for (Iterator it = bundle.libIDs(); it.hasNext();) {
            String libID = (String)it.next();
            if (state(libID) == S_UNPROCESSED) {
                ArrayList libVars = bundle.variants(libID);
                Library accepted = null;
                for (int i = 0; i < libVars.size(); i++) {
                    Library var = (Library)libVars.get(i);
                    accepted = (accepted == null) ? var  
                             : selectVariant(accepted, var);
                }
                libAccepted(libID, accepted);
                mark(libID, S_ACCEPTED);
            }
        }
    }

     
    /**
     * Returns the more appropriate variant from the passed pair
     */
    protected Library selectVariant(Library lib1, Library lib2)
             throws FilteringException {

        return (Library)FilterUtil.selectVariant(lib1, lib2);
    }

    /**
     * Checks that no one of accepted library does not depneds 
     * on inline libraries.
     * 
     * @exception FilteringException if accepted library depends on
     *            Inline library. 
     */
    protected void checkForInlineDependencies() throws FilteringException {
        for (Iterator it = libMap.accepted().iterator(); it.hasNext();) {
            String libID = (String)it.next();
            Library lib = libMap.get(libID);
            if (lib.isInline()) {
               continue; // inline library may depends on inline
            }
            ArrayList depList;
            try {
                depList = IR.getDependentLibs(lib);
            } catch (TestFileException e) {
                throw new FilteringException("Cannot get dependencies for: " +
                        lib + ", because: " + e);
            }

            for (Iterator depIt = depList.iterator(); depIt.hasNext();) {
                String depLibID = (String)depIt.next();
                Library depLib = libMap.get(depLibID);
                if (depLib.isInline()) {
                    throw new FilteringException("Library: " + libID + 
                        " depends on inline library: " + depLibID);
                }
            }
        }
    }

    /**
     * Processes accepted variant.
     * By default this method just put accepted variant into the libMap.
     * Subclasses may override this method to add an extra processing.
     */
    protected void libAccepted(String libID, Library libVar) {
        libMap.put(libID, libVar);
    }

    /**
     * Processes rejected libID.
     * By default this method just put null for libID into the libMap.
     * Subclasses may override this method to add an extra processing.
     */
    protected void libRejected(String libID) {
        libMap.put(libID, null);
    }

    /**
     * Marks the library with newState.
     * Removes libID from toReject list if previous state is S_TO_REJECT.
     * Adds libID to toReject list if new state is S_TO_REJECT.
     */
    protected void mark(String libID, int newState) {
        int oldState = state(libID);
        if (oldState == newState)
            return;

        if (oldState == S_TO_REJECT) {
            toReject.remove(libID);
        } else if (newState == S_TO_REJECT) {
            toReject.add(libID);
        }
        libState.put(libID, new Integer(newState));
    }

    /**
     * Returns the currect state for library
     */
    protected int state(String libID) {
        Object st = libState.get(libID);
        if (st == null)
            return S_UNKONW;
        else 
            return ((Integer)st).intValue();
    }

}
