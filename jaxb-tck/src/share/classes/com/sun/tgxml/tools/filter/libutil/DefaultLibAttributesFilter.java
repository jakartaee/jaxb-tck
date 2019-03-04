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

import java.util.Iterator;
import java.util.ArrayList;

import com.sun.tgxml.util.IR;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tools.filter.processors.NodeTRUE;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.FilterExpression;
import com.sun.tgxml.tools.filter.processors.FilteringException;


/**
 * This class is default implementation of LibAttributesFilter interface
 * 
 * @version  1.0, April 1, 2003
 * @author   Dmitry Fazunenko
 */

public class DefaultLibAttributesFilter implements LibAttributesFilter {

    // filter expression for library filtering
    protected FilterExpression libTree;

    // filter expression for support class filtering
    FilterExpression scTree;


    public DefaultLibAttributesFilter(String pluginName) 
            throws FilteringException {

        FilterFactory filtFact = FilterFactory.newInstance(pluginName);
        FilterExpression tree = filtFact.cfgRead(null);
        libTree = makeNonEmptyTree(tree.getRelevant((Library)null));
        scTree  = makeNonEmptyTree(tree.getRelevant((SupportClass)null));
    }

    /**
     * Provides attribute filtering of passed library array.
     * <p>
     * The method just call filter(Library) method and puts non null
     * values into resulting map.
     */
    public VariantsMap filter(VariantsMap libBundle) throws FilteringException {
        
        VariantsMap filteredMap = new VariantsMap();

        for (Iterator it = libBundle.libIDs(); it.hasNext();) {
            String libID = (String)it.next();
            filteredMap.addLibID(libID);

            ArrayList varList = libBundle.variants(libID);
            for (Iterator varIt = varList.iterator(); varIt.hasNext();) {
                Library var = (Library)varIt.next();
                Library fVar = filter(var);
                if (fVar != null) {
                    filteredMap.addVariant(fVar);
                }
            }
            
        }
        return filteredMap;
    }


    /**
     * Provides attribute filtering of passed library.
     * Methods returns null if library attributes does not satisfy
     * the selection criteria.
     * Otherwise it returns stripped Library (removes irrelevant 
     * support classes from Library CodeSet).
     */
    public Library filter(Library lib) throws FilteringException {
        if (lib != null && libTree.accept(lib)) {
            stripCodeSet(lib, scTree);
            return lib;
        } else {
            return null;
        }
    }

    /**
     * Removes irrelevant support classes from the CodeSet
     */
    public static void stripCodeSet(TestItem ti, FilterExpression scTree) 
            throws FilteringException {
        CodeSet cs = ti.getCodeSet();
        if ( cs == null )
            return;

        ArrayList scList = cs.getSupportClasses();
        if ( scList == null )
            return;

        ArrayList IDset = new ArrayList();

        for (int j = scList.size()-1; j >= 0; j--) {
            SupportClass sc = (SupportClass)scList.get(j);
            if ( !scTree.accept(sc, cs) ) {
                scList.remove(j);
                continue;
            }
            String id = sc.getClassID();
            if (id==null)
                continue;
            if (IDset.contains(id)) {
                throw new FilteringException(LibResHandler.getResStr(
                "filter.error.libfilter.duplicatedSClass", id, IR.getID(ti)));
            }
            IDset.add(id);
        }
    }

    public static FilterExpression makeNonEmptyTree(FilterExpression root) {
	return root == null? new NodeTRUE()
			   : root ;
    }

}
