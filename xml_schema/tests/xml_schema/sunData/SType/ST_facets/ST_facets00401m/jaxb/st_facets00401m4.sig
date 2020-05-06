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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S> value
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S> getValue()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Β_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03d000")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ΠA_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03d6a0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ΡA_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03f1a1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ϓ_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03d3-0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ϣ_01
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03e201")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ϩ_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u03e9-1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ё_02
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u040102")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ё_04
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u045104")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S І_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0406-2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S І_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0456-4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ЌA_2
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u040ca2")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ЌA_4
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u045ca4")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ў_03
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u040e03")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ў_05
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u045e05")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ю_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u042e-3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ЯA_3
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u044fa3")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ѯ_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u046f-5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ҀA_5
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u0481a5")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ґ_06
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u049006")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ҧ_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04a7-6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ҾA_6
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04bfa6")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӂ_07
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c107")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӂ_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c2-7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ӃA_7
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c4a7")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ӇA_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c8a8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӈ_08
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c708")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӈ_8
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04c7-8")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S ӋA_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04cca9")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӌ_09
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04cb09")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S Ӌ_9
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="\u04cb-9")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00401m.st_facets00401m4.S>
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

