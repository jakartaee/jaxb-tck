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

package javasoft.sqe.tests.java2schema.CustomizedMapping.property_fields.XmlElement.src01;

import java.util.List;
import jakarta.xml.bind.annotation.*;

@XmlType (propOrder={"fld1", "prop1", "fld2"})
@jakarta.xml.bind.annotation.XmlRootElement (name="root")
class src {
  @XmlElement (name="Fld1")
  public int fld1;
  public int getProp1() {
    return 0;
  }
  @XmlElement (name="Prop1")
  public void setProp1(int val) {
  }
  @XmlElements(
    {
      @XmlElement(name="A", type=Integer.class),
      @XmlElement(name="B", type=Float.class)
    }
  )
  public List fld2;
}
