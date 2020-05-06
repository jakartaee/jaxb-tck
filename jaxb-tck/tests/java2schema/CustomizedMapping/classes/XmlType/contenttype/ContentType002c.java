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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.contenttype;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.JAXBElement;
import static jakarta.xml.bind.annotation.XmlAccessType.PUBLIC_MEMBER;
import java.util.*;
import java.math.BigInteger;

/**
 * Mapping: Class to Complex Type Definition
 * {content type}
 * mixed if a property or field is annotated with @XmlMixed
 */
@XmlType
@XmlRootElement
public class ContentType002c {

    @XmlRootElement
    static class ContentType002d {
       @XmlElement(name="i01d") 
       public int additionalField002a;    
    }

    @XmlMixed
    @XmlElementRef(name="ref1", type=ContentType002d.class)
    public JAXBElement<ContentType002d> item01c;                  
    
}
