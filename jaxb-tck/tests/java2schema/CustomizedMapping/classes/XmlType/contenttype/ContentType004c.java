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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.contenttype;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Class to Complex Type Definition
 * {content type}
 * element-only content if one or more properties is annotated with
 * @XmlElement;
 */
@XmlType(propOrder={"s01","b01"})
@XmlRootElement
public class ContentType004c {

    @XmlElement(name="BigDecimal01",type=BigDecimal.class)
    protected String s01;

    @XmlElement(name="Boolean01")
    private boolean  b01;
}
