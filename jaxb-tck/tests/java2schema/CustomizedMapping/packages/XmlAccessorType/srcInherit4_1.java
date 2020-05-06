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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType.srcInherit4_1;


/**
 * JAXB specification assertion: JAXB:SPEC:J2S:2046
 * Mapping: XmlAccessorType annotation allows control over default serialization of fields and properties.
 *
 * The annotation  XmlAccessorType  on a package applies to all classes in the package. 
 * The following inheritance semantics apply:
 * If there is a XmlAccessorType on a class, then it is used.
 * Otherwise, if a XmlAccessorType exists on one of its super classes, then it is inherited.
 * Otherwise, the XmlAccessorType  on a package is inherited.
*/

@jakarta.xml.bind.annotation.XmlAccessorType (jakarta.xml.bind.annotation.XmlAccessType.PUBLIC_MEMBER)
@jakarta.xml.bind.annotation.XmlRootElement (name="rootParent")
public class srcInherit4_1 {
}
