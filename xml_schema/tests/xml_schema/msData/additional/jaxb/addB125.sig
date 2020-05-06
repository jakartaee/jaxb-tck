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

CLSS public javasoft.sqe.tests.additional.test78898.Att1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="att1", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Att1()
fld protected java.math.BigInteger att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.math.BigInteger getAtt()
meth public void setAtt(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.Att2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="att2", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Att2()
fld protected java.lang.Boolean att2
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.math.BigInteger att1
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.Boolean isAtt2()
meth public java.math.BigInteger getAtt1()
meth public void setAtt1(java.math.BigInteger)
meth public void setAtt2(java.lang.Boolean)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.Ct1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ct1", java.lang.String namespace="##default", java.lang.String[] propOrder=["e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"])
cons public Ct1()
fld protected java.lang.String e1
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.Att1> e7
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.Att1> e8
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.M3> e3
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.M4> e4
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.M5> e5
fld protected java.util.List<javasoft.sqe.tests.additional.test78898.M6> e6
fld protected javasoft.sqe.tests.additional.test78898.Att1 e2
meth public java.lang.String getE1()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.Att1> getE7()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.Att1> getE8()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.M3> getE3()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.M4> getE4()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.M5> getE5()
meth public java.util.List<javasoft.sqe.tests.additional.test78898.M6> getE6()
meth public javasoft.sqe.tests.additional.test78898.Att1 getE2()
meth public void setE1(java.lang.String)
meth public void setE2(javasoft.sqe.tests.additional.test78898.Att1)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.M3
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="m3", java.lang.String namespace="##default", java.lang.String[] propOrder=["e31"])
cons public M3()
fld protected java.math.BigInteger att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.util.List<java.lang.Object> e31
meth public java.math.BigInteger getAtt()
meth public java.util.List<java.lang.Object> getE31()
meth public void setAtt(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.M4
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="m4", java.lang.String namespace="##default", java.lang.String[] propOrder=["e41AndE3"])
cons public M4()
fld protected java.math.BigInteger att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.util.List<java.lang.Object> e41AndE3
 anno 0 jakarta.xml.bind.annotation.XmlElements(jakarta.xml.bind.annotation.XmlElement[] value=[anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class javasoft.sqe.tests.additional.test78898.Att2, java.lang.String defaultValue="\u0000", java.lang.String name="e41", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class javasoft.sqe.tests.additional.test78898.M3, java.lang.String defaultValue="\u0000", java.lang.String name="e3", java.lang.String namespace="##default")])
meth public java.math.BigInteger getAtt()
meth public java.util.List<java.lang.Object> getE41AndE3()
meth public void setAtt(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.M5
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="m5", java.lang.String namespace="##default", java.lang.String[] propOrder=["e3OrE4OrE5"])
cons public M5()
fld protected java.math.BigInteger att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.util.List<java.lang.Object> e3OrE4OrE5
 anno 0 jakarta.xml.bind.annotation.XmlElements(jakarta.xml.bind.annotation.XmlElement[] value=[anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.additional.test78898.M4, java.lang.String defaultValue="\u0000", java.lang.String name="e4", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.additional.test78898.M5, java.lang.String defaultValue="\u0000", java.lang.String name="e5", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.additional.test78898.M3, java.lang.String defaultValue="\u0000", java.lang.String name="e3", java.lang.String namespace="##default")])
meth public java.math.BigInteger getAtt()
meth public java.util.List<java.lang.Object> getE3OrE4OrE5()
meth public void setAtt(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test78898.M6
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="m6", java.lang.String namespace="##default", java.lang.String[] propOrder=["any"])
cons public M6()
fld protected java.math.BigInteger att
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.util.List<org.w3c.dom.Element> any
 anno 0 jakarta.xml.bind.annotation.XmlAnyElement(boolean lax=false, java.lang.Class<? extends jakarta.xml.bind.annotation.DomHandler> value=class jakarta.xml.bind.annotation.W3CDomHandler)
meth public java.math.BigInteger getAtt()
meth public java.util.List<org.w3c.dom.Element> getAny()
meth public void setAtt(java.math.BigInteger)
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorType
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessType value()

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

