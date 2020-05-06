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

package javasoft.sqe.tests.java2schema.CustomizedMapping.property_fields.XmlElements.src03;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlType (propOrder={"fld1", "prop1"})
@XmlRootElement (name="root")
class src {
  @XmlElements(
    {
    @XmlElement(name="A", type=Integer.class),
    @XmlElement(name="B", type=Float.class)
    }
  )
  public List fld1;
  @XmlElements(
    {
    @XmlElement(name="AA", type=Integer.class),
    @XmlElement(name="BB", type=Float.class)
    }
  )
  public List getProp1() {
    return null;
  }
  public void setProp1(List list) {
  }
}
