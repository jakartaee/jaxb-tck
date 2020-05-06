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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_00_এ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a00\u098f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_0_A_ঐ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0A\u0990")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_0_এ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0-\u098f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_10_ও
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a10\u0993")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_1_A_ন
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1A\u09a8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_1_ঝ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1-\u099d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_20_প
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a20\u09aa")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_2_A_র
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2A\u09b0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_2_ভ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2-\u09ad")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_30_ল
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a30\u09b2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_40_শ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a40\u09b6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_4_A_হ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4A\u09b9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_4_ষ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a4-\u09b7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_50_ড়
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a50\u09dc")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_5_A_ঢ়
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5A\u09dd")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_5_ড়
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5-\u09dc")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_60_য়
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a60\u09df")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_6_A_ৡ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6A\u09e1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_6_ৠ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6-\u09e0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_70_ৰ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a70\u09f0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_7_A_ৱ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7A\u09f1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_7_ৰ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a7-\u09f0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_80_ਅ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a80\u0a05")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_8_A_ਊ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8A\u0a0a")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_8_ਇ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8-\u0a07")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_90_ਏ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a90\u0a0f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_9_A_ਐ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9A\u0a10")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S A_9_ਏ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9-\u0a0f")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m7.S>
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

