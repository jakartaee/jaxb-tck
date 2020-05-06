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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType008;

import jakarta.xml.bind.annotation.*;

import java.util.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00057
 * 
 * @XmlSchemaType annotation annotation allows a customized mapping to a XML
 *                Schema built in type.
 * @XmlAccessorOrder.type 
 * If the annotation @XmlSchemaType is used as a package level annotation or within
 * @XmlSchemaTypes, value of @XmlSchemaType.type() must be specified and must 
 * be the Java type that is being customized. 
 * A @XmlSchemaType specified on the property/field maps references to @XmlSchemaType.type() 
 * as follows: 
 * 1. property/field is a single valued.
 */

@XmlRootElement(name="root")
class XmlSchemaType008 {
	
	@XmlSchemaType(name = "unsignedShort")
	java.lang.Short xsUnsignedShort;

	@XmlSchemaType(name = "unsignedByte")
	java.lang.Byte xsUnsignedByte;
	
	@XmlSchemaType(name = "unsignedInt")
	java.lang.Integer  xsUnsignedInt;

	@XmlSchemaType(name = "dateTime")
	XMLGregorianCalendar xsDateTime;
	
	@XmlSchemaType(name = "QName" )   
	private java.lang.String  aXsQName;

	@XmlSchemaType(name = "unsignedShort")
	void setPXsUnsignedShort(java.lang.Short pXsUnsignedShort) {
	}

	java.lang.Short getPXsUnsignedShort() {
		return null;
	}

	@XmlSchemaType(name = "unsignedByte")
	void setPXsUnsignedByte(java.lang.Byte pXsUnsignedByte) {
	}

	java.lang.Byte getPXsUnsignedByte() {
		return null;
	}

	@XmlSchemaType(name = "unsignedInt")
	void setPXsUnsignedInt(java.lang.Integer pXsUnsignedInt) {
	}

	java.lang.Integer getPXsUnsignedInt() {
		return null;
	}

	@XmlSchemaType(name = "dateTime")
	void setPXsDateTime(XMLGregorianCalendar pXsDateTime) {
	}

	XMLGregorianCalendar getPXsDateTime() {
		return null;
	}

	@XmlSchemaType(name = "QName" )   
	private void setPXsQName(java.lang.String pXsQName) {
	}

	private java.lang.String getPXsQName() {
		return null;
	}	

}	
