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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S Ῠ_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1fe800")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S Ὺ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1fea-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ῬA_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1feca0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S Ὸ_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1ff801")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S Ό_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1ff9-1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ΏA_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u1ffba1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ↀ_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u218002")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ↁ_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u2181-2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ↂ_A_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u2182a2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ぁ_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u304103")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S な_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u306a-3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ゔ_A_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u3094a3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ァ_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u30a104")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ネ_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u30cd-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ヺ_A_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u30faa4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ㄅ_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u310505")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ㄘ_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u3118-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S ㄬ_A_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u312ca5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S 가_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\uac0006")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S 쇑_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\uc1d1-6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S 힣_A_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\ud7a3a6")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m21.S>
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

