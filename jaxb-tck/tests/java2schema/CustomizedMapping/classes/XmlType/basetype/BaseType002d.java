/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

import javax.xml.bind.annotation.*;

/**
 * Mapping: Class to Complex Type Definition
 * if the class contains a mapped property or field
 * annotated with @XmlValue, ‚Ç£@XmlValue‚Ç¥, then the schema 
 * type to which mapped property or field‚ÇÖs type is mapped.
 */
@XmlType(propOrder={ "d001","f001", "l001"}) 
public class BaseType002d {

    private long    l001;
    private float   f001;
    private double  d001;
    
    public BaseType002d() {}

    public long getL001() {
        return l001;
    }

    public void setL001(long l001) {
        this.l001 = l001;
    }

    public float getF001() {
        return f001;
    }

    public void setF001(float f001) {
        this.f001 = f001;
    }
    
   public double getD001() {
        return d001;
    }

    public void setD001(double d001) {
        this.d001 = d001;
    }    
}
