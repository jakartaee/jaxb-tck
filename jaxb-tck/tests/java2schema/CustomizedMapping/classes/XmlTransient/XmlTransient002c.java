/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlTransient002;

import jakarta.xml.bind.annotation.*;

/**
 * JAXB specification assertion: JAXB:SPEC:J2S:01221 
 * Mapping: <b>@XmlTransient</b> is used to prevent the mapping of a class.
 * The class must not be mapped.
 * Any reference to this class from the other XML bound classes will treated as
 * if they are refering to the nearest XML-bound ancestor of this class
 * (which could be java.lang.Object, which guarantees that there always exists
 * such a class.)
 */
@XmlRootElement
public class XmlTransient002c {

	@XmlElement(required=true)
    public Class002c class002c;
}

@XmlTransient
class Class002c extends XmlTransient002d {
    @XmlElement(required=true)
    public byte byte002c;    
}
