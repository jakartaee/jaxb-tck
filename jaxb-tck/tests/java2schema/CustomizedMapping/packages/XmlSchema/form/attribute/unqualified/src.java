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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlSchema.form.attribute.unqualified.src;

/**
 *
 * JAXB specification assertion: JAXB:SPEC:J2S:00042
 * Mapping: Package to XML target namespace
 * attributeFormDefault ::= if the value of @XmlSchema.attributeFormDefault() is @XmlNsForm.UNSET, then absent;
 * otherwise, the value of @XmlSchema.attributeFormDefault()
 */
@jakarta.xml.bind.annotation.XmlRootElement (name="rootElem")
public class src {
  @jakarta.xml.bind.annotation.XmlAttribute
  public int attr;
}

