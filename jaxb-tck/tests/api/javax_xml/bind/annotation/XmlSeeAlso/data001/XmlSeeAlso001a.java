/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.xml.bind.annotation.XmlSeeAlso.data001;

import jakarta.xml.bind.annotation.*;

@XmlSeeAlso({XmlSeeAlso001b.class, XmlSeeAlso001c.class} )
@XmlType(namespace = "http://www.example.com/XmlSeeAlso", propOrder={"id", "field001a"}) 
public abstract class XmlSeeAlso001a {
	@XmlElement(required=true, defaultValue="0")
	public boolean id;
	
	@XmlElement(required=true, defaultValue="0")
	public int field001a;
}
