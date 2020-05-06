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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.namespace.NameSpace009;

import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Simple Type Definition
 * If the class contains a single mapped property or field that is 
 * annotated with @XmlValue then class must be mapped as specified:
 *
 * if @XmlType.namespace() is "##default" && @XmlType.name() is "" 
 * and class is annotated with @XmlRootElement, then the {target
 * namespace} as specified:
 * if @XmlRootElement.namespace() is "##default" && @XmlSchema.namespace() 
 * is "", then targetNamespace is absent.
 */
@XmlType(namespace="##default", name="")
@XmlRootElement(namespace="##default")
public class NameSpace009s {

    private int i008;
    
    public NameSpace009s() {}

    @XmlValue
    public int isi008() {
        return i008;
    }

    public void seti008(int i008) {
        this.i008 = i008;
    }
}
