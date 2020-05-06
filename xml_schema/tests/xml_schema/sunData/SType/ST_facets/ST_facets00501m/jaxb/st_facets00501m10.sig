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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_00_ପ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a00\u0b2a")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_0_A_ର
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0A\u0b30")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_0_ଭ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0-\u0b2d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_10_ଲ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a10\u0b32")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_1_A_ଳ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1A\u0b33")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_1_ଲ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1-\u0b32")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_20_ଶ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a20\u0b36")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_2_A_ହ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2A\u0b39")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_2_ଷ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2-\u0b37")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_30_ଽ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a30\u0b3d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_40_ଡ଼
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a40\u0b5c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_4_A_ଢ଼
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4A\u0b5d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_4_ଡ଼
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4-\u0b5c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_50_ୟ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a50\u0b5f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_5_A_ୡ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5A\u0b61")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_5_ୠ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5-\u0b60")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_60_அ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a60\u0b85")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_6_A_ஊ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6A\u0b8a")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_6_இ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6-\u0b87")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_70_எ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a70\u0b8e")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_7_A_ஐ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7A\u0b90")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_7_ஏ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7-\u0b8f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_80_ஒ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a80\u0b92")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_8_A_க
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8A\u0b95")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_8_ஓ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8-\u0b93")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_90_ங
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a90\u0b99")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_9_A_ச
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9A\u0b9a")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S A_9_ங
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9-\u0b99")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m10.S>
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

