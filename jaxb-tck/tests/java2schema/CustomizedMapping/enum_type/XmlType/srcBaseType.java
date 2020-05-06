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

package javasoft.sqe.tests.java2schema.CustomizedMapping.enum_type.XmlType.srcBaseType;

@jakarta.xml.bind.annotation.XmlType
     @jakarta.xml.bind.annotation.XmlEnum(Integer.class)
     enum Coin { 
         @jakarta.xml.bind.annotation.XmlEnumValue("1") PENNY(1),
         @jakarta.xml.bind.annotation.XmlEnumValue("5") NICKEL(5),
         @jakarta.xml.bind.annotation.XmlEnumValue("10") DIME(10),
         @jakarta.xml.bind.annotation.XmlEnumValue("25") QUARTER(25) }
