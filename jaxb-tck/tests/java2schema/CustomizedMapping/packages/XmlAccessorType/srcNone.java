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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType.srcNone;

import jakarta.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00047 Mapping: XmlAccessorType
 * annotation allows control over default serialization of fields and
 * properties.
 * 
 * @XmlAccessorType(AccessType.NONE) NONE - None of the fields or properties is
 *                                   bound to XML unless they are specifically
 *                                   annotated with some of the JAXB
 *                                   annotations.
 */
@XmlAccessorType(XmlAccessType.NONE)
@jakarta.xml.bind.annotation.XmlRootElement(name = "root")
@XmlType(propOrder = { "id", "remarks", "val", "picture" })
class src {

	public int fldPublic;

	int fldDefault;

	@jakarta.xml.bind.annotation.XmlTransient
	public int fldPublicTransient;

	int getPropDefault() {
		return 0;
	}

	void setPropDefault(int val) {
	}

	public int getPropPublic() {
		return 0;
	}

	public void setPropPublic(int val) {
	}

	@jakarta.xml.bind.annotation.XmlTransient
	public int getPropPublicTransient() {
		return 0;
	}

	public void setPropPublicTransient(int val) {
	}

	@XmlList
	private String[] remarks;

	@XmlMimeType(value = "image/jpeg")
	private byte[] picture;

	@XmlAttribute(required = true)
	byte typeID;

	@XmlElement(name = "Value", required = true)
	public int getVal() {
		return 0;
	}

	public void setVal(int val) {
	}

	@XmlID
	@XmlElement(name = "ID", required = true)
	public String id;

}
