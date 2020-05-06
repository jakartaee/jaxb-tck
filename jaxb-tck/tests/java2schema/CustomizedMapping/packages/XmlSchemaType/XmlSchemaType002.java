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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType002;

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
 * to which the type of the property or field can be mapped from XML Schema to  Java 
 * as specified in Section 6.2.2, Atomic Datatype.
 * @XmlSchemaType annotation can be used with the following program elements:
 * field
 */
@XmlRootElement(name="root")
class XmlSchemaType002 {
	
	private java.math.BigInteger xsInteger;

	private short xsInt;

	private int xsLong;
	
	private byte xsShort;
	
	private java.math.BigDecimal xsDecimal;

	private float xsFloat;
	
	private double xsDouble;

	private boolean xsBoolean;
	
	private byte xsByte;
	
	private java.lang.String xsQName;
	
	private XMLGregorianCalendar xsDateTime;
	
	private byte[] xsBase64Binary;
	
	private byte[] xsHexBinary;
	
	private long xsUnsignedInt;
	
	private int xsUnsignedShort;
	
	private short xsUnsignedByte;
	
	private XMLGregorianCalendar xsTime;
	
	private XMLGregorianCalendar xsDate;
	
	private XMLGregorianCalendar  xsgYearMonth;
	
	private XMLGregorianCalendar  xsgYear;
	
	private XMLGregorianCalendar  xsgMonthDay;
	
	private XMLGregorianCalendar  xsgDay;
	
	private java.lang.Object xsAnySimpleTypeObject;
	
	private java.lang.String xsAnySimpleTypeString;

	private javax.xml.datatype.Duration xsDuration;

	private java.lang.String xsString;

	@XmlSchemaType(name = "dateTime" )
	void setXsDateTime(XMLGregorianCalendar xsDateTime) {
		this.xsDateTime = xsDateTime;
	}

	XMLGregorianCalendar getXsDateTime() {
		return xsDateTime;
	}

	@XmlSchemaType(name = "base64Binary" )
	void setXsBase64Binary(byte[] xsBase64Binary) {
		this.xsBase64Binary = xsBase64Binary;
	}

	byte[] getXsBase64Binary() {
		return xsBase64Binary;
	}

	@XmlSchemaType(name = "hexBinary" )
	void setXsHexBinary(byte[] xsHexBinary) {
		this.xsHexBinary = xsHexBinary;
	}

	byte[] getXsHexBinary() {
		return xsHexBinary;
	}

	@XmlSchemaType(name = "unsignedInt" )
	void setXsUnsignedInt(long xsUnsignedInt) {
		this.xsUnsignedInt = xsUnsignedInt;
	}

	long getXsUnsignedInt() {
		return xsUnsignedInt;
	}

	@XmlSchemaType(name = "unsignedShort" )
	void setXsUnsignedShort(int xsUnsignedShort) {
		this.xsUnsignedShort = xsUnsignedShort;
	}

	int getXsUnsignedShort() {
		return xsUnsignedShort;
	}

	@XmlSchemaType(name = "unsignedByte" )
	void setXsUnsignedByte(short xsUnsignedByte) {
		this.xsUnsignedByte = xsUnsignedByte;
	}

	short getXsUnsignedByte() {
		return xsUnsignedByte;
	}

	@XmlSchemaType(name = "time" )
	void setXsTime(XMLGregorianCalendar xsTime) {
		this.xsTime = xsTime;
	}

	XMLGregorianCalendar getXsTime() {
		return xsTime;
	}

	@XmlSchemaType(name = "date"  )
	void setXsDate(XMLGregorianCalendar xsDate) {
		this.xsDate = xsDate;
	}

	XMLGregorianCalendar getXsDate() {
		return xsDate;
	}

	@XmlSchemaType(name = "gMonthDay"  )
	void setXsgMonthDay(XMLGregorianCalendar xsgMonthDay) {
		this.xsgMonthDay = xsgMonthDay;
	}

	XMLGregorianCalendar getXsgMonthDay() {
		return xsgMonthDay;
	}

	@XmlSchemaType(name = "gYearMonth"  )
	void setXsgYearMonth(XMLGregorianCalendar xsgYearMonth) {
		this.xsgYearMonth = xsgYearMonth;
	}

	XMLGregorianCalendar getXsgYearMonth() {
		return xsgYearMonth;
	}

	@XmlSchemaType(name = "gYear"  )
	void setXsgYear(XMLGregorianCalendar xsgYear) {
		this.xsgYear = xsgYear;
	}

	XMLGregorianCalendar getXsgYear() {
		return xsgYear;
	}

	@XmlSchemaType(name = "gDay"  )
	void setXsgDay(XMLGregorianCalendar xsgDay) {
		this.xsgDay = xsgDay;
	}

	XMLGregorianCalendar getXsgDay() {
		return xsgDay;
	}

	@XmlSchemaType(name = "anySimpleType"  )
	void setXsAnySimpleTypeObject(java.lang.Object xsAnySimpleTypeObject) {
		this.xsAnySimpleTypeObject = xsAnySimpleTypeObject;
	}

	java.lang.Object getXsAnySimpleTypeObject() {
		return xsAnySimpleTypeObject;
	}

	@XmlSchemaType(name = "anySimpleType"  )
	void setXsAnySimpleTypeString(java.lang.String xsAnySimpleTypeString) {
		this.xsAnySimpleTypeString = xsAnySimpleTypeString;
	}

	java.lang.String getXsAnySimpleTypeString() {
		return xsAnySimpleTypeString;
	}

	@XmlSchemaType(name = "string" )
	void setXsString(java.lang.String xsString) {
		this.xsString = xsString;
	}

	java.lang.String getXsString() {
		return xsString;
	}
	
	@XmlSchemaType(name = "duration")
	void setXsDuration(javax.xml.datatype.Duration xsDuration) {
		this.xsDuration = xsDuration;
	}

	javax.xml.datatype.Duration getXsDuration() {
		return xsDuration;
	}

	@XmlSchemaType(name = "QName" )
	void setXsQName(java.lang.String xsQName) {
		this.xsQName = xsQName;
	}

	java.lang.String getXsQName() {
		return xsQName;
	}

	@XmlSchemaType(name = "integer" )
	void setXsInteger(java.math.BigInteger xsInteger) {
		this.xsInteger = xsInteger;
	}

	java.math.BigInteger getXsInteger() {
		return xsInteger;
	}

	@XmlSchemaType(name = "int" )
	void setXsInt(short xsInt) {
		this.xsInt = xsInt;
	}

	short getXsInt() {
		return xsInt;
	}

	@XmlSchemaType(name = "long" )
	void setXsLong(int xsLong) {
		this.xsLong = xsLong;
	}

	int getXsLong() {
		return xsLong;
	}

	@XmlSchemaType(name = "short" )
	void setXsShort(byte xsShort) {
		this.xsShort = xsShort;
	}

	byte getXsShort() {
		return xsShort;
	}

	@XmlSchemaType(name = "decimal" )
	void setXsDecimal(java.math.BigDecimal xsDecimal) {
		this.xsDecimal = xsDecimal;
	}

	java.math.BigDecimal getXsDecimal() {
		return xsDecimal;
	}

	@XmlSchemaType(name = "float" )
	void setXsFloat(float xsFloat) {
		this.xsFloat = xsFloat;
	}

	float getXsFloat() {
		return xsFloat;
	}

	@XmlSchemaType(name = "double" )
	void setXsDouble(double xsDouble) {
		this.xsDouble = xsDouble;
	}

	double getXsDouble() {
		return xsDouble;
	}

	@XmlSchemaType(name = "boolean" )
	void setXsBoolean(boolean xsBoolean) {
		this.xsBoolean = xsBoolean;
	}

	boolean isXsBoolean() {
		return xsBoolean;
	}

	@XmlSchemaType(name = "byte" )
	void setXsByte(byte xsByte) {
		this.xsByte = xsByte;
	}

	byte getXsByte() {
		return xsByte;
	}
}
