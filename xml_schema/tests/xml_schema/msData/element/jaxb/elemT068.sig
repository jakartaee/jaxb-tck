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

CLSS public final !enum javasoft.sqe.tests.element.elemt068.B
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="B", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.element.elemt068.B A
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="a")
fld public final static javasoft.sqe.tests.element.elemt068.B B
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="b")
fld public final static javasoft.sqe.tests.element.elemt068.B C_123456789
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="c123456789")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.element.elemt068.B fromValue(java.lang.String)
meth public static javasoft.sqe.tests.element.elemt068.B valueOf(java.lang.String)
meth public static javasoft.sqe.tests.element.elemt068.B[] values()
supr java.lang.Enum<javasoft.sqe.tests.element.elemt068.B>
hfds value

CLSS public javasoft.sqe.tests.element.elemt068.CA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.element.elemt068.RCA, class javasoft.sqe.tests.element.elemt068.ECA])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CA", java.lang.String namespace="##default", java.lang.String[] propOrder=["x", "y"])
cons public CA()
fld protected java.lang.Object y
fld protected java.util.List<java.lang.Object> x
meth public java.lang.Object getY()
meth public java.util.List<java.lang.Object> getX()
meth public void setY(java.lang.Object)
supr java.lang.Object

CLSS public javasoft.sqe.tests.element.elemt068.EA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="E-A", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public EA()
fld protected int value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
fld protected java.lang.Integer att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
meth public int getValue()
meth public java.lang.Integer getAtt()
meth public void setAtt(java.lang.Integer)
meth public void setValue(int)
supr java.lang.Object

CLSS public javasoft.sqe.tests.element.elemt068.ECA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="E-CA", java.lang.String namespace="##default", java.lang.String[] propOrder=["z"])
cons public ECA()
fld protected java.lang.Object z
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.Object getZ()
meth public void setZ(java.lang.Object)
supr javasoft.sqe.tests.element.elemt068.CA

CLSS public javasoft.sqe.tests.element.elemt068.RCA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="R-CA", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public RCA()
supr javasoft.sqe.tests.element.elemt068.CA

CLSS public javasoft.sqe.tests.element.elemt068.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["test1", "test2", "test3", "test4", "test5"])
cons public Root()
fld protected java.lang.Integer test2
fld protected java.lang.Integer test3
fld protected java.util.List<java.lang.Integer> test1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class java.lang.Integer, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.element.elemt068.CA test4
fld protected javasoft.sqe.tests.element.elemt068.CA test5
meth public java.lang.Integer getTest2()
meth public java.lang.Integer getTest3()
meth public java.util.List<java.lang.Integer> getTest1()
meth public javasoft.sqe.tests.element.elemt068.CA getTest4()
meth public javasoft.sqe.tests.element.elemt068.CA getTest5()
meth public void setTest2(java.lang.Integer)
meth public void setTest3(java.lang.Integer)
meth public void setTest4(javasoft.sqe.tests.element.elemt068.CA)
meth public void setTest5(javasoft.sqe.tests.element.elemt068.CA)
supr java.lang.Object

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

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlSeeAlso
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[TYPE])
intf java.lang.annotation.Annotation
meth public abstract java.lang.Class[] value()

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

