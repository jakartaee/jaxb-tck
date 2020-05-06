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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType001;

import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JAXB specification assertion JAXB:SPEC:J2S:0053
 * 
 * @XmlAccessorOrder annotation  annotation allows a customized mapping 
 * to a XML Schema built in type.
 * 
 * @XmlAccessorOrder(name)
 * name() must be an atomic simple type schema type (or a type that derives from it) 
 * to which the type of the property or field can be mapped from XML Schema -> Java 
 * as specified in Section 6.2.2, "Atomic Datatype".
 * @XmlSchemaType annotation can be used with the following program elements:
 * field
 */
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name="root")
class XmlSchemaType001 {
	
	@XmlSchemaType(name = "string" )
	@XmlElement
	java.lang.String xsString;
	
	@XmlSchemaType(name = "integer" )
	java.math.BigInteger xsInteger;

	@XmlSchemaType(name = "int" )
	short xsInt;

	@XmlSchemaType(name = "long" )
	int xsLong;
	
	@XmlSchemaType(name = "short" )
	byte xsShort;
	
	@XmlSchemaType(name = "decimal" )
	java.math.BigDecimal xsDecimal;

	@XmlSchemaType(name = "float" )
	float xsFloat;
	
	@XmlSchemaType(name = "double" )
	double xsDouble;

	@XmlSchemaType(name = "boolean" )
	boolean xsBoolean;
	
	@XmlSchemaType(name = "byte" )
	byte xsByte;
	
	@XmlSchemaType(name = "QName" )
	java.lang.String xsQName;
	
	@XmlSchemaType(name = "dateTime" )
	XMLGregorianCalendar xsDateTime;
	

	@XmlSchemaType(name = "base64Binary" )
	byte[] xsBase64Binary;
	
	@XmlSchemaType(name = "hexBinary" )
	byte[] xsHexBinary;
	     
	
	@XmlSchemaType(name = "unsignedInt" )
	long xsUnsignedInt;
	
	@XmlSchemaType(name = "unsignedShort" )
	int xsUnsignedShort;
	
	@XmlSchemaType(name = "unsignedByte" )
	short xsUnsignedByte;
	
	@XmlSchemaType(name = "time" )
	XMLGregorianCalendar xsTime;
	
	@XmlSchemaType(name = "date"  )
	XMLGregorianCalendar xsDate;
	
	@XmlSchemaType(name = "gYearMonth"  )
	XMLGregorianCalendar  xsgYearMonth;
	
	@XmlSchemaType(name = "gYear"  )
	XMLGregorianCalendar  xsgYear;
	
	@XmlSchemaType(name = "gMonthDay"  )
	XMLGregorianCalendar  xsgMonthDay;
	
	@XmlSchemaType(name = "gDay"  )
	XMLGregorianCalendar  xsgDay;
	
	@XmlSchemaType(name = "anySimpleType"  )
	java.lang.Object xsAnySimpleTypeObject;
	
	@XmlSchemaType(name = "anySimpleType"  )
	java.lang.String xsAnySimpleTypeString;

	@XmlSchemaType(name = "duration")	
	javax.xml.datatype.Duration	xsDuration;
	
}
