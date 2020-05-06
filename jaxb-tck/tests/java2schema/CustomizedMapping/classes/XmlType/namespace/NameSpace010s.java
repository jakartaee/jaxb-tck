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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.namespace.NameSpace010;

import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Simple Type Definition
 * If the class contains a single mapped property or field that is 
 * annotated with @XmlValue then class must be mapped as specified:
 *
 * If @XmlType.namespace() is "##default" && @XmlType.name() is "" and
 * class is not annotated with @XmlRootElement, then the {target namespace} 
 * of the attribute or element to which the property or field, 
 * from where this class is referenced, is mapped.
 */
@XmlType(namespace="##default", name="")
@XmlRootElement
public class NameSpace010s {
    
    @XmlValue
    public  NameSpace010d  ns10;
    
}
