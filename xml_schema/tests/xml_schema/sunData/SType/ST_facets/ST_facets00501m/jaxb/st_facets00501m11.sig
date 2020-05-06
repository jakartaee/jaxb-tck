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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_00_ஜ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a00\u0b9c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_10_ஞ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a10\u0b9e")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_1_A_ட
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1A\u0b9f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_1_ஞ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1-\u0b9e")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_20_ண
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a20\u0ba3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_2_A_த
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2A\u0ba4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_2_ண
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2-\u0ba3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_30_ந
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a30\u0ba8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_3_A_ப
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a3A\u0baa")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_3_ன
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a3-\u0ba9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_40_ம
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a40\u0bae")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_4_A_வ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4A\u0bb5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_4_ற
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4-\u0bb1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_50_ஷ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a50\u0bb7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_5_A_ஹ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5A\u0bb9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_5_ஸ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5-\u0bb8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_60_అ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a60\u0c05")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_6_A_ఌ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6A\u0c0c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_6_ఈ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6-\u0c08")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_70_ఎ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a70\u0c0e")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_7_A_ఐ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7A\u0c10")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_7_ఏ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7-\u0c0f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_80_ఒ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a80\u0c12")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_8_A_న
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8A\u0c28")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_8_ఝ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8-\u0c1d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_90_ప
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a90\u0c2a")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_9_A_ళ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9A\u0c33")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S A_9_మ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9-\u0c2e")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m11.S>
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

