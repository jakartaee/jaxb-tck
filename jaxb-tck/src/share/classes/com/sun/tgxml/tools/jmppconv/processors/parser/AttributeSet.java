/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

/**
 * This class is an abstraction layer between template parameters
 * and the testcase/test group attribute machinery implementation.
 */
public interface AttributeSet {
    /**
     * Adds new specification to the set of target specs this container holds.
     *
     * @param id spec id
     * @param version spec version
     */
    public void addTargetSpec(String id, String version);

    /**
     * Adds new specification element to given target specification this
     * container holds.
     *
     * @param id      target specification id
     * @param version target specification version
     * @param name    specification element name
     * @param value   specification element value
     */
    public void addTargetSpecElem(String id, String version, String name, String value);
    
    /**
     * Adds new required resource to this container.
     *
     * @param name  resource name
     * @param value resource value (restriction)
     */
    public void addRequiredResource(String name, String value);

    /**
     * Adds arbitrary attribute to this container
     *
     * @param name  attribute name
     * @param value attribute value
     */
    public void addAttrElem(String name, String value);


    /**
     * Adds required template attribute.
     * @param feature name of the attribute.
     * @throws RuntimeException the feature name is unknown.
     */
    public void addTemplateAttribute(String feature);
}
