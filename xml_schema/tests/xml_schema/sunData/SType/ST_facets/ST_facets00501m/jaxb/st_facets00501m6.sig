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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_00_ٱ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a00\u0671")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_0_A_ڷ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0A\u06b7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_0_ڔ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a0-\u0694")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_10_ں
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a10\u06ba")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_1_A_ھ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1A\u06be")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_1_ڼ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a1-\u06bc")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_20_ۀ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a20\u06c0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_2_A_ێ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2A\u06ce")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_2_ۇ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a2-\u06c7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_30_ې
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a30\u06d0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_3_A_ۓ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a3A\u06d3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_3_ۑ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a3-\u06d1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_40_ە
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a40\u06d5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_50_ۥ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a50\u06e5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_5_A_ۦ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5A\u06e6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_5_ۥ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a5-\u06e5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_60_अ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a60\u0905")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_6_A_ह
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6A\u0939")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_6_ट
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a6-\u091f")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_70_ऽ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a70\u093d")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_80_क़
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a80\u0958")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_8_A_ॡ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8A\u0961")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_8_ड़
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a8-\u095c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_90_অ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a90\u0985")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_9_A_ঌ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9A\u098c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S A_9_ঈ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a9-\u0988")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00501m.st_facets00501m6.S>
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

