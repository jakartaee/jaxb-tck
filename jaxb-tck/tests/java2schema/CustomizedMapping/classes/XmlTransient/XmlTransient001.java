/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlTransient001;

import jakarta.xml.bind.annotation.*;

/** 
 * JAXB specification assertion: JAXB:SPEC:J2S:01221
 * Mapping: <b>@XmlTransient</b> is used to prevent the mapping of a class.
 * The class must not be mapped.
 */
@XmlTransient
@XmlRootElement        
@XmlType(name="",propOrder={"s001" , "b001"})
public class XmlTransient001 {
    
    private String   s001;
    private boolean b001;

    public XmlTransient001() {}

    public String getS001() {
        return s001;
    }

    public void setS001(String s001) {
        this.s001 = s001;
    }

    public boolean isB001() {
        return b001;
    }

    public void setB001(boolean b001) {
        this.b001 = b001;
    }
    
}
