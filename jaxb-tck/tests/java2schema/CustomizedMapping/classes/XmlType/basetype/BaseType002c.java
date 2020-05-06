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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.basetype;

import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Complex Type Definition
 * {base type definition} Component
 * if the class doesn't contain a mapped property or field
 * annotated with @XmlValue, then the schema type to which 
 * superclass is mapped
 */
@XmlRootElement        
public class BaseType002c extends BaseType002d {
    
    private boolean b001;

    public boolean isB001() {
        return b001;
    }

    public void setB001(boolean b001) {
        this.b001 = b001;
    }    
    
    public BaseType002c() {}
}
