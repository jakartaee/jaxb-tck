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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.name.Name005;

import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Simple Type Definition
 * If the class contains a single mapped property that is 
 * annotated with @XmlValue then class must be mapped as specified:
 * If class is annotated with @XmlType(name=""), 
 * then class is mapped to an anonymous type.
 */
@XmlRootElement        
@XmlType(name="")
public class Name005s {

    private String  s001;
    
    public Name005s() {}

    @XmlValue
    public String getS001() {
        return s001;
    }

    public void setS001(String s001) {
        this.s001 = s001;
    }
    
}
