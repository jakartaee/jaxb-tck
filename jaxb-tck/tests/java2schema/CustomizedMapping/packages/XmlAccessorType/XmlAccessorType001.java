/*
 * Copyright (c) 2006, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType001;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.*;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00048 Mapping: XmlAccessorType
 * annotation allows control over default serialization of fields and
 * properties.
 * 
 * @XmlAccessorType annotation can be used only with the following other annotations:
 * @XmlType,
 * @XmlRootElement,
 * @XmlAccessorOrder,
 * @XmlSchema,
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")        
@XmlType(propOrder = { "remarks", "id"})
public class XmlAccessorType001 extends XmlAccessorType001a {

	private String remarks; 
	
	@XmlElement(name="ID")
	int id;
}

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
class XmlAccessorType001a extends XmlAccessorType001b {

	protected String getZ() {
		return String.class.toString();
	}
	
	protected void setZ(String value) {
	}

	
	protected int getA() {
		return 0;
	}
	
	protected void setA(int value) {
	}
}

@XmlAccessorType(XmlAccessType.NONE)
class XmlAccessorType001b {
	@XmlAttribute(name="atribute")
	int atr;
}

