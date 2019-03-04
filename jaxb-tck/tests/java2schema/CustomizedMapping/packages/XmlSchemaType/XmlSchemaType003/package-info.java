/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

@javax.xml.bind.annotation.XmlSchemaTypes (
   { 	
	   @javax.xml.bind.annotation.XmlSchemaType(name = "integer", type=java.math.BigInteger.class),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "int" , type=int.class),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "long", type=long.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "short", type=short.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "decimal", type=java.math.BigDecimal.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "float", 	type=float.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "double", type=double.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "boolean", type=boolean.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "byte", type=byte.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "QName", type=java.lang.StringBuffer.class ), 
	   @javax.xml.bind.annotation.XmlSchemaType(name = "base64Binary", type=byte[].class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "dateTime", type=javax.xml.datatype.XMLGregorianCalendar.class ),
	   @javax.xml.bind.annotation.XmlSchemaType(name = "anySimpleType", type=java.lang.Object.class )
   }
)		

@javax.xml.bind.annotation.XmlSchemaType(name = "duration", type=javax.xml.datatype.Duration.class )
@javax.xml.bind.annotation.XmlAccessorOrder(javax.xml.bind.annotation.XmlAccessOrder.ALPHABETICAL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType003;
