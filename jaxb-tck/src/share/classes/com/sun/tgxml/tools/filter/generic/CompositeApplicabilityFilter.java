/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.generic;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;


/**
 * The <code>CompositeApplicabilityFilter</code> class is subclass of
 * <code>GenericApplicabilityFilter</code>. It defines the methods for
 * registration of the plug-in implementation and delegates acceptance
 * checking to the plug-ins. If the filter is not registered for a
 * given attribute, the attribute is accepted.
 */
public class CompositeApplicabilityFilter extends GenericApplicabilityFilter {

    public CompositeApplicabilityFilter(String name) {
        super(name);
    }

    private TargetSpecFilter targetSpecFilter;

    private APIFilter apiFilter;
    private HashMap filters = new HashMap();
    private KeywordsFilter keywordsFilter;
    private ValueFilter assertionFilter;

    protected static final String TARGETSPECELEM = "TargetSpecElem.";
    protected static final String REQUIREDRESOURCE = "RequiredResource.";

    /**
     * registers the APIFilter that is used for acceptTestedPackage,
     * acceptTestedClass and acceptMemberSig methods.
     * @param filter
     */
    public void registerAPIFilter(APIFilter filter) {
        this.apiFilter = filter;
    }

    /**
     * registers TargetSpec filter that is used for acceptTargetSpec method.
     * @param filter
     */
    public void registerTargetSpecFilter(TargetSpecFilter filter) {
        this.targetSpecFilter = filter;
    }

    /**
     * registers TargetSpec filter that is used for acceptTargetSpecElem
     * method with the given enclosing TargetSpec ID and given name.
     */
    public void registerTargetSpecElemFilter(String id, String name, ValueFilter filter) {
        filters.put(TARGETSPECELEM + name + "." + id, filter);
    }

    /**
     * registers TargetSpec filter that is used for acceptTargetSpecElem method
     * with the given name. This filter is ignored for a TargetSpecElem attribute
     * for which a TargetSpecElem filter is registered using
     * <code>registerTargetSpecElemFilter(String&nbsp;id,&nbsp;String&nbsp;name,&nbsp;ValueFilter&nbsp;filter)</code>
     * attribute name and TargetSpec ID.
     * @param name
     * @param filter
     */
    public void registerTargetSpecElemFilter(String name, ValueFilter filter) {
        filters.put(TARGETSPECELEM + name, filter);
    }

    /**
     * registers the filter for RequiredResource with the give name.
     * @param name
     * @param filter
     */
    public void registerRequiredResourceFilter(String name, ValueFilter filter) {
        filters.put(REQUIREDRESOURCE + name, filter);
    }

    /**
     * registers filter for Keywords attribute.
     * @param filter
     */
    public void registerKeywordsFilter(KeywordsFilter filter) {
        this.keywordsFilter = filter;
    }

    /**
     * registers filter used for the acceptAssertionRef method.
     * @param filter
     */
    public void registerAssertionRefFilter(ValueFilter filter) {
        this.assertionFilter = filter;
    }

    /**
     * delegates the acceptance checking to the registered TargetSpec filter.
     */
    public boolean acceptTargetSpec(TargetSpec spec) throws TestFileException {
        return (targetSpecFilter == null) || (targetSpecFilter.acceptTargetSpec(spec));
    }

    /**
     * delegates the acceptance checking to the registered AssertionRef filter.
     */
    public boolean acceptAssertionRef(String assertion) {
        return (assertionFilter == null) || (assertionFilter.acceptValue(assertion));
    }

    /**
     * delegates the acceptance checking to the registered Keywords filter.
     */
    public boolean acceptKeywords(ArrayList keywords) {
        return (keywordsFilter == null) || (keywordsFilter.acceptKeywords(keywords));
    }

    /**
     * delegates the acceptance checking to the registered APIFilter filter.
     */
    public boolean acceptTestedClass(String testedPack, String testedClass) {
        return (apiFilter == null)
                || apiFilter.acceptTestedClass(testedPack, testedClass);
    }

    /**
     * delegates the acceptance checking to the registered APIFilter filter.
     */
    public boolean acceptTestedPackage(String testedPack) {
        return (apiFilter == null)
                || apiFilter.acceptTestedPackage(testedPack);
    }

    /**
     * delegates the acceptance checking to the registered APIFilter filter.
     */
    public boolean acceptMemberSig(String testedPack, String testedClass, String memberSig) {
        return (apiFilter == null)
                || apiFilter.acceptMemberSig(testedPack, testedClass, memberSig);
    }

    /**
     * delegates the acceptance checking to the ValueFilter registered
     * for a RequiredResource attribute with the given name.
     */
    public boolean acceptRequiredResource(String name, String value) {
        ValueFilter filter = (ValueFilter)filters.get(REQUIREDRESOURCE + name);
        return (filter == null) || (filter.acceptValue(value));
    }

    /**
     * delegates the acceptance checking to the ValueFilter registered
     * for a TargetSpecElem attribute with the given name.
     */
    public boolean acceptTargetSpecElem(TargetSpec spec, String name,
                                         String value) {
        ValueFilter filter = (ValueFilter)filters.get(TARGETSPECELEM + name
                                                      + "." + spec.getID());
        if (filter == null) {
            filter = (ValueFilter)filters.get(TARGETSPECELEM + name);
        }
        return (filter == null) || (filter.acceptValue(value));
    }
}
