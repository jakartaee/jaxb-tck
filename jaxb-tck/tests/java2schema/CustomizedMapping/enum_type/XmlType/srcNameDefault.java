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

package javasoft.sqe.tests.java2schema.CustomizedMapping.enum_type.XmlType.srcNameDefault;

import jakarta.xml.bind.annotation.*;

@XmlRootElement (name="root")
@XmlType (propOrder={"theCard1", "theCard2"})
class src {
  
  @XmlType (name="##default")
  @XmlEnum
  enum Card1 { THREE, SEVEN, ACE };

  @XmlType (name="card2")
  @XmlEnum
  enum Card2 { THREE, SEVEN, QUEEN };

  public Card1 theCard1 = Card1.ACE;
  public Card2 theCard2 = Card2.QUEEN;
}
