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

@jakarta.xml.bind.annotation.XmlSchemaTypes (
   { 	
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "integer", type=java.math.BigInteger.class),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "int" , type=int.class),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "long", type=long.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "short", type=short.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "decimal", type=java.math.BigDecimal.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "float", 	type=float.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "double", type=double.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "boolean", type=boolean.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "byte", type=byte.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "QName", type=java.lang.StringBuffer.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "base64Binary", type=byte[].class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "dateTime", type=javax.xml.datatype.XMLGregorianCalendar.class ),
	   @jakarta.xml.bind.annotation.XmlSchemaType(name = "anySimpleType", type=java.lang.Object.class )
   }
)		

@jakarta.xml.bind.annotation.XmlSchemaType(name = "duration", type=javax.xml.datatype.Duration.class )
@jakarta.xml.bind.annotation.XmlAccessorOrder(jakarta.xml.bind.annotation.XmlAccessOrder.ALPHABETICAL)
@jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType003;
