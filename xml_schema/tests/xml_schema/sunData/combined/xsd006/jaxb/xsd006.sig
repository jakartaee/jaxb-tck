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

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item0To1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item0to1", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item0To1()
fld protected java.lang.Object x
meth public java.lang.Object getX()
meth public void setX(java.lang.Object)
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item0To2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item0to2", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item0To2()
fld protected java.util.List<java.lang.Object> x
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item0ToX
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item0toX", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item0ToX()
fld protected java.util.List<java.lang.Object> x
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item1To2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item1to2", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item1To2()
fld protected java.util.List<java.lang.Object> x
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item1ToX
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item1toX", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item1ToX()
fld protected java.util.List<java.lang.Object> x
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item2To2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item2to2", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item2To2()
fld protected java.util.List<java.lang.Object> x
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item2ToX
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item2toX", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item2ToX()
fld protected java.util.List<java.lang.Object> x
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Item3To7
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="item3to7", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["x"])
cons public Item3To7()
fld protected java.util.List<java.lang.Object> x
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<java.lang.Object> getX()
supr java.lang.Object

CLSS public javasoft.sqe.tests.combined.xsd006.xsd006.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["item0To1OrItem0To2OrItem0ToX"])
cons public Root()
fld protected java.util.List<java.lang.Object> item0To1OrItem0To2OrItem0ToX
 anno 0 jakarta.xml.bind.annotation.XmlElements(jakarta.xml.bind.annotation.XmlElement[] value=[anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item1To2, java.lang.String defaultValue="\u0000", java.lang.String name="item1to2", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item3To7, java.lang.String defaultValue="\u0000", java.lang.String name="item3to7", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item0ToX, java.lang.String defaultValue="\u0000", java.lang.String name="item0toX", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item0To2, java.lang.String defaultValue="\u0000", java.lang.String name="item0to2", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item2To2, java.lang.String defaultValue="\u0000", java.lang.String name="item2to2", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item0To1, java.lang.String defaultValue="\u0000", java.lang.String name="item0to1", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item2ToX, java.lang.String defaultValue="\u0000", java.lang.String name="item2toX", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.combined.xsd006.xsd006.Item1ToX, java.lang.String defaultValue="\u0000", java.lang.String name="item1toX", java.lang.String namespace="##default")])
meth public java.util.List<java.lang.Object> getItem0To1OrItem0To2OrItem0ToX()
supr java.lang.Object

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorType
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessType value()

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

