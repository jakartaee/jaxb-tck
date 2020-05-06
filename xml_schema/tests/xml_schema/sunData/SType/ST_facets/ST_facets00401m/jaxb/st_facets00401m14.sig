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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ງ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e87-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ງ_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e8700")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຈ_A_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e88a0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຊ_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e8a01")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຍ_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e8d02")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ດ_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e9403")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຕ_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e95-3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ທ_A_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e97a3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ນ_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e9904")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຜ_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e9c-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຟ_A_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0e9fa4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ມ_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ea105")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຢ_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ea2-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຣ_A_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ea3a5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ລ_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ea506")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ວ_07
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ea707")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ສ_08
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0eaa08")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ສ_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0eaa-8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຫ_A_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0eaba8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ອ_09
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ead09")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ອ_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0ead-9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S ຮ_A_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0eaea9")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m14.S>
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

