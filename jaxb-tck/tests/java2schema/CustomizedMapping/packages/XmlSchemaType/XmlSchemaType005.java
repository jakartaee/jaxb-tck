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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchemaType005;

import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * JAXB specification assertion JAXB:SPEC:J2S:0054
 * 
 * @XmlAccessorOrder annotation  annotation allows a customized mapping 
 * to a XML Schema built in type.
 * 
 * @XmlAccessorOrder(type)
 * If the annotation @XmlSchemaType is used as a package level annotation or 
 * within @XmlSchemaTypes, value of @XmlSchemaType.type() must be specified 
 * and must be the Java type that is being customized. 
 */
@XmlRootElement(name="root")
class XmlSchemaType005 {
	private byte  xsUnsignedByte;
}

