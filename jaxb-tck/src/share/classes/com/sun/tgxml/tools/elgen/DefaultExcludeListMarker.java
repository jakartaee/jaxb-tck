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

package com.sun.tgxml.tools.elgen;

import java.io.IOException;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.attributes.AttrElem;
import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes;
import com.sun.tgxml.tjtf.api.attributes.LibAttributes;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;


/** the default implementation of the ExludeListMarker
  * 
  */
public class DefaultExcludeListMarker implements ExcludeListMarker {

    private ExcludeList el = null;

    public DefaultExcludeListMarker () {
    }


    public DefaultExcludeListMarker (String excludeListFile) 
             throws IOException {
        init(excludeListFile);
    }

    public DefaultExcludeListMarker (ExcludeList excludeList) {
        setExcludeList(excludeList);
    }
    
    /**
     * Does initialization. Parses specified exclude list source file name
     */
    public void init(String excludeListFile) throws IOException {
        setExcludeList(ExcludeListToolFactory.getExcludeList(excludeListFile));
    }

    protected void setExcludeList(ExcludeList exList) {
        el = exList;
    }


    /**  
      * The method mark the given testItem  as excluded
      * by adding "exclude" attrElement with keywords_list as contents.
      * keywords_list is provided for filterring purpose
      * It also add "excludeParams" attribute that will used by exclude list
      * generator 
      *
      * The testItem should have attrElem named "sourceDirectory", that
      * contains a name of test directory relative to the repository test root
      * directory
      * @throws IncorrectAttributesException if "sourceDirectory"  are not defined.      
      * @return true, if the item was marked  
          */

    public boolean markExcluded (TestCase testItem) throws IncorrectAttributesException {
        ExcludeEntry entry = el.find(testItem);
        if(entry == null) {
            return false;
        } else {
            ExcludeListUtils.addExcludedElem(testItem, entry);
            return true;
        }

    }

    /**  
      * The method mark the given testItem  as excluded
      * by adding "exclude" attrElement with keywords_list as contents.
      * keywords_list is provided for filterring purpose
      * It also add "excludeParams" attribute that will used by exclude list
      * generator 
      *
      * The testItem should have attrElem named "sourceDirectory", that
      * contains a name of test directory relative to the repository test root
      * directory
      * @throws IncorrectAttributesException if "sourceDirectory"  are not defined.      
      * @return true, if the item was marked  
          */

    public boolean markExcluded (TestGroup testItem) throws IncorrectAttributesException {
        ExcludeEntry entry = el.find(testItem);
        if(entry == null) {
            return false;
        } else {
            ExcludeListUtils.addExcludedElem(testItem, entry);
            return true;
        }
    
        
    }
} 
