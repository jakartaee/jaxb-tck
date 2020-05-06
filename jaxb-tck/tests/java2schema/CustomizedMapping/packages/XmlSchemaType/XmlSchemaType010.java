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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType010;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00059
 * 
 * @XmlSchemaType annotation annotation allows a customized mapping to a XML
 *                Schema built in type.
 * @XmlSchemaType annotation can only be used with the following other annotations:
 * @XmlElement,
 * @XmlAttribute,
 * @XmlJavaTypeAdapter
 * @XmlJavaTypeAdapters
 */

@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@jakarta.xml.bind.annotation.XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
class XmlSchemaType010 {

	@XmlSchemaType(name = "dateTime")
	@XmlElement
	public SortedSet<XMLGregorianCalendar> aXsDateTime;
	
	@XmlSchemaType(name = "unsignedShort")
	@XmlElement
	public void setAXsUnsignedShort(List<java.lang.Short> aXsUnsignedShort) {
	}
	public List<java.lang.Short> getAXsUnsignedShort() {
		return null;
	}

	@XmlSchemaType(name = "unsignedShort")
	@XmlAttribute
	public java.lang.Short     xsUnsignedShort;
	
	@XmlSchemaType(name = "unsignedByte")
	@XmlAttribute
	public java.lang.Byte xsUnsignedByte;
	
	@XmlSchemaType(name = "QName")
	@XmlJavaTypeAdapter (value=jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter.class,
	                     type=java.lang.String.class ) 
	public java.lang.String xsQName;

}	
