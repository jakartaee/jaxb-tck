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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S এ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u098f-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S এ_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u098f00")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ঐ_A_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0990a0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ও_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u099301")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ঝ_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u099d-1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ন_A_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09a8a1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S প_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09aa02")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ভ_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09ad-2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S র_A_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09b0a2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ল_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09b203")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S শ_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09b604")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ষ_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09b7-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S হ_A_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09b9a4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ড়_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09dc05")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ড়_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09dc-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ঢ়_A_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09dda5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S য়_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09df06")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ৠ_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09e0-6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ৡ_A_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09e1a6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ৰ_07
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09f007")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ৰ_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09f0-7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ৱ_A_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u09f1a7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਅ_08
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a0508")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਇ_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a07-8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਊ_A_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a0aa8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਏ_09
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a0f09")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਏ_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a0f-9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S ਐ_A_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0a10a9")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m7.S>
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

