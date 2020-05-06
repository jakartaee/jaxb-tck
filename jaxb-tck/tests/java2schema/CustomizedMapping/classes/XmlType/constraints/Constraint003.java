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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.constraints;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.*;

/**
 * if the class, subClass, derives from another XML-bound class, baseClass
 * directly or indirectly (other than java.lang.Object), then the subClass
 * must not contain a mapped property or field annotated with @XmlValue
 * annotation.
 * 
 * subClass contains a mapped field annotated with @XmlValue annotation.
 */
@XmlType(propOrder={"key", "value"})
public class Constraint003 {

    @XmlType(name="subClass")
    static class Inner003 extends Constraint003 {

        @XmlValue
        public boolean  state;
    }

    @XmlElement
    public String value;

    @XmlElement
    public int key;            
}
