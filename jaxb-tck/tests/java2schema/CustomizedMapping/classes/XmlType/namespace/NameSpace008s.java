/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.namespace;

import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Simple Type Definition
 * If the class contains a single mapped property or field that is 
 * annotated with @XmlValue then class must be mapped as specified:
 *
 * if @XmlType.namespace() is "##default" && @XmlType.name() is "" 
 * and class is annotated with @XmlRootElement, then the {target
 * namespace} as specified:
 * if @XmlRootElement.namespace() is "##default", then the value of the
 * targetNamespace to which the package containing
 * the class is mapped as specified by @XmlSchema.namespace()
 */
@XmlType(namespace="##default", name="")
@XmlRootElement(namespace="##default")
public class NameSpace008s {
    
    private boolean b008;
    
    public NameSpace008s() {}

    @XmlValue
    public boolean isB008() {
        return b008;
    }

    public void setB008(boolean b008) {
        this.b008 = b008;
    }
}
