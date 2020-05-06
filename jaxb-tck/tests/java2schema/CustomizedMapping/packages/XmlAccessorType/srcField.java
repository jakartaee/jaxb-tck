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

package javasoft.sqe.tests.java2schema.CustomizedMapping.packages.XmlAccessorType.srcField;

import jakarta.xml.bind.annotation.*;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:00045
 * Mapping: XmlAccessorType annotation allows control over default serialization of fields and properties.
 * @XmlAccessorType(AccessType.FIELD)
 * FIELD - Every non static, non transient field in a JAXB-bound class will be
 * automatically bound to XML, unless annotated by XmlTransient.
*/
@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name="root")
@XmlType (propOrder={"fldPublic", "fldDefault", "fldProtected", "fldPrivate"})
class src {
  public int fldPublic;
  int fldDefault;
  int fldProtected;
  private int fldPrivate;
  public int getPropPublic() {
    return fldPrivate;
  }
  public void setPropPublic(int val) {
    this.fldPrivate = val;
  }
  @jakarta.xml.bind.annotation.XmlTransient
  public int fldPublicTransient;

}

