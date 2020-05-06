#Signature file v4.0
#Version 

#
# Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the
# Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
# version 2 with the GNU Classpath Exception, which is available at
# https://www.gnu.org/software/classpath/license.html.
#
# SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
#


CLSS public abstract interface java.io.Serializable

CLSS public abstract interface java.lang.Comparable<%0 extends java.lang.Object>
meth public abstract int compareTo({java.lang.Comparable%0})

CLSS public abstract java.lang.Enum<%0 extends java.lang.Enum<{java.lang.Enum%0}>>
cons protected Enum(java.lang.String,int)
intf java.io.Serializable
intf java.lang.Comparable<{java.lang.Enum%0}>
meth protected final java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected final void finalize()
meth public final boolean equals(java.lang.Object)
meth public final int compareTo({java.lang.Enum%0})
meth public final int hashCode()
meth public final int ordinal()
meth public final java.lang.Class<{java.lang.Enum%0}> getDeclaringClass()
meth public final java.lang.String name()
meth public java.lang.String toString()
meth public static <%0 extends java.lang.Enum<{%%0}>> {%%0} valueOf(java.lang.Class<{%%0}>,java.lang.String)
supr java.lang.Object
hfds name,ordinal

CLSS public java.lang.Object
cons public Object()
meth protected java.lang.Object clone() throws java.lang.CloneNotSupportedException
meth protected void finalize() throws java.lang.Throwable
meth public boolean equals(java.lang.Object)
meth public final java.lang.Class<?> getClass()
meth public final void notify()
meth public final void notifyAll()
meth public final void wait() throws java.lang.InterruptedException
meth public final void wait(long) throws java.lang.InterruptedException
meth public final void wait(long,int) throws java.lang.InterruptedException
meth public int hashCode()
meth public java.lang.String toString()

CLSS public abstract interface java.lang.annotation.Annotation
meth public abstract boolean equals(java.lang.Object)
meth public abstract int hashCode()
meth public abstract java.lang.Class<? extends java.lang.annotation.Annotation> annotationType()
meth public abstract java.lang.String toString()

CLSS public abstract interface !annotation java.lang.annotation.Documented
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation java.lang.annotation.Inherited
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation

CLSS public abstract interface !annotation java.lang.annotation.Retention
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.annotation.RetentionPolicy value()

CLSS public abstract interface !annotation java.lang.annotation.Target
 anno 0 java.lang.annotation.Documented()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[ANNOTATION_TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.annotation.ElementType[] value()

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਓ_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a1300")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਝ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a1d-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਨ_A_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a28a0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਪ_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a2a01")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਭ_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a2d-1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਰ_A_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a30a1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਲ_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a3202")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਲ_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a32-2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਲ਼_A_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a33a2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਵ_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a3503")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਵ_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a35-3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਸ਼_A_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a36a3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਸ_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a3804")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਸ_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a38-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਹ_A_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a39a4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਖ਼_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a5905")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਗ਼_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a5a-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ੜ_A_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a5ca5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ਫ਼_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a5e06")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ੲ_07
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a7207")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ੳ_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a73-7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ੴ_A_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a74a7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S અ_08
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a8508")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ઈ_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a88-8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ઋ_A_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a8ba8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S ઍ_09
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a8d09")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m8.S>
hfds value

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorType
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessType value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlEnum
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class<?> value()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlRootElement
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.String name()
meth public abstract !hasdefault java.lang.String namespace()

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlType
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
innr public final static DEFAULT
intf java.lang.annotation.Annotation
meth public abstract !hasdefault java.lang.Class factoryClass()
meth public abstract !hasdefault java.lang.String factoryMethod()
meth public abstract !hasdefault java.lang.String name()
meth public abstract !hasdefault java.lang.String namespace()
meth public abstract !hasdefault java.lang.String[] propOrder()

