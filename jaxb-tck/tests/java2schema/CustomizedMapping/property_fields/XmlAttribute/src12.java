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

package javasoft.sqe.tests.java2schema.CustomizedMapping.property_fields.XmlAttribute.src12;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.*;

/**
 * Mapping: Property/field to Attribute Use (no ref attribute)  
 * If @XmlAttribute.namespace() is "##default" then the property or field
 * must be mapped as specified:
 * Value of targetNamespace is equal to  @XmlSchema.namespace()
 */
@XmlType( name = "USAddressType", propOrder={"name", "street", "city", "state", "zip"} )
@XmlRootElement (name="USAddress")
class TestAddress {

    protected String name;
    protected String street;
    protected String city;
    protected String state;
    protected BigDecimal zip;

    @XmlAttribute(name="place", namespace="##default")
    protected String country;
}
