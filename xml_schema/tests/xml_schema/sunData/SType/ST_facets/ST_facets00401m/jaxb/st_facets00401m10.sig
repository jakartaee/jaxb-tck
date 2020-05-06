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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ପ_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b2a00")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଭ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b2d-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ର_A_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b30a0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଲ_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b3201")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଲ_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b32-1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଳ_A_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b33a1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଶ_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b3602")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଷ_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b37-2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ହ_A_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b39a2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଽ_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b3d03")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଡ଼_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b5c04")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଡ଼_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b5c-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ଢ଼_A_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b5da4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ୟ_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b5f05")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ୠ_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b60-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ୡ_A_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b61a5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S அ_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b8506")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S இ_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b87-6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ஊ_A_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b8aa6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S எ_07
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b8e07")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ஏ_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b8f-7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ஐ_A_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b90a7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ஒ_08
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b9208")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ஓ_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b93-8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S க_A_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b95a8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ங_09
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b9909")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ங_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b99-9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S ச_A_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0b9aa9")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m10.S>
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

