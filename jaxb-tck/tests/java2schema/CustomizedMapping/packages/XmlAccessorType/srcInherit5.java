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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType.srcInherit5;

import jakarta.xml.bind.annotation.*;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:1047 Mapping: XmlAccessorType
 * annotation allows control over default serialization of fields and
 * properties.
 * @XmlAccessorType(AccessType.NONE)
 *
 * The annotation XmlAccessorType on a package applies to all classes in the
 * package. The following inheritance semantics apply: If there is a
 * XmlAccessorType on a class, then it is used. Otherwise, if a XmlAccessorType
 * exists on one of its super classes, then it is inherited. Otherwise, the
 * XmlAccessorType on a package is inherited.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "rootParent")
@XmlType(propOrder = { "id", "remarks" })
class Parent {

	public int fldPublic;

	@XmlID
	@XmlElement(name = "ID", required = true)
	public String id;

	@XmlList
	private String[] remarks;
}

@XmlRootElement(name = "root")
@XmlType(propOrder = { "val", "picture" })
class Child extends Parent {

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

}
