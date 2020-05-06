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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType003;

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
@XmlRootElement(name="root")
class XmlSchemaType003 {

	private java.math.BigInteger xsInteger;

	private int xsInt;

	private long xsLong;
	
	private short xsShort;
	
	private java.math.BigDecimal xsDecimal;

	private float xsFloat;
	
	private double xsDouble;

	private boolean xsBoolean;
	
	private byte xsByte;
	
	private java.lang.String xsQName;
	
	private XMLGregorianCalendar xsDateTime;
	
	private byte[] xsBase64Binary;
	
	private java.lang.Object xsAnySimpleTypeObject;
	
	private javax.xml.datatype.Duration xsDuration;
}
