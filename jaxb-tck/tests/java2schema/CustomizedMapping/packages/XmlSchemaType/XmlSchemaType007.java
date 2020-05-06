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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType007;

import jakarta.xml.bind.annotation.*;
import java.util.*;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00056
 * 
 * @XmlSchemaType annotation annotation allows a customized mapping to a XML
 *                Schema built in type.
 * @XmlAccessorOrder.type 
 * If the annotation @XmlSchemaType is used as a package level annotation or within
 * @XmlSchemaTypes, value of @XmlSchemaType.type() must be specified and must 
 * be the Java type that is being customized. 
 * A @XmlSchemaType that is specified as a package level annotation must apply at
 * the point of reference as follows: 
 * 2. a property/field within a class in package, where @XmlSchemaType.type() 
 * is used as a parametric type.
 */

@XmlRootElement(name="root")
class XmlSchemaType007 {
	
	
	public SortedSet<XMLGregorianCalendar> aXsDateTime;
	
	public Vector<java.lang.String>  aXsQName;
	
	public void setAXsUnsignedShort(List<java.lang.Short> aXsUnsignedShort) {
		this.aXsUnsignedShort = aXsUnsignedShort;
	}

	public List<java.lang.Short> getAXsUnsignedShort() {
		return aXsUnsignedShort;
	}

	public void setAXsUnsignedByte(ArrayList<java.lang.Byte> aXsUnsignedByte) {
		this.aXsUnsignedByte = aXsUnsignedByte;
	}

	public ArrayList<java.lang.Byte> getAXsUnsignedByte() {
		return aXsUnsignedByte;
	}

	public void setAXsUnsignedInt(Queue<java.lang.Integer> aXsUnsignedInt) {
		this.aXsUnsignedInt = aXsUnsignedInt;
	}

	public Queue<java.lang.Integer> getAXsUnsignedInt() {
		return aXsUnsignedInt;
	}

	//
	private List<java.lang.Short>     aXsUnsignedShort;
	private ArrayList<java.lang.Byte> aXsUnsignedByte;
	private Queue<java.lang.Integer>  aXsUnsignedInt;
}
