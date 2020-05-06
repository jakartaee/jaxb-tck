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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType005;

import java.util.Map;
import java.util.TreeMap;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.*;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00052 Mapping: XmlAccessorOrder
 * @XmlAccessorOrder annotation allows control over the default ordering of 
 * properties and fields that are mapped to XML elements.
 * @XmlAccessorOrder  annotation can be used only with the following other annotations:
 * @XmlSchemaType, 
 * @XmlSchemaTypes, 
 * @XmlJavaTypeAdapters. 
 * It can also be used with the following annotations at the package level: @XmlJavaTypeAdapter.
 */
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class XmlAccessorOrder005 extends XmlAccessorOrder005a {

	private String location;

	@XmlElement(name = "ID")
	int id;
}

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
class XmlAccessorOrder005a extends XmlAccessorOrder005b {

	protected String getOtherLocation() {
		return String.class.toString();
	}

	protected void setOtherLocation(String value) {
	}

	protected javax.xml.datatype.XMLGregorianCalendar getA() {
		return null;
	}

	protected void setA(javax.xml.datatype.XMLGregorianCalendar value) {
	}
}

@XmlAccessorType(XmlAccessType.NONE)
class XmlAccessorOrder005b {
	@XmlAttribute(name = "atribute")
	javax.xml.namespace.QName atr;
}

