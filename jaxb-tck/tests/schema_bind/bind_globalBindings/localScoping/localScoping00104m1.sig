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

CLSS public javasoft.sqe.tests.localscoping.localscoping00104m1.NestedItem
cons public NestedItem()
cons public NestedItem(java.lang.String)
fld protected final static javax.xml.namespace.QName NAME
supr jakarta.xml.bind.JAXBElement<java.lang.String>

CLSS public javasoft.sqe.tests.localscoping.localscoping00104m1.TopItems
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["nestedItem"])
cons public TopItems()
fld protected java.util.List<javasoft.sqe.tests.localscoping.localscoping00104m1.NestedItem> nestedItem
 anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class javasoft.sqe.tests.localscoping.localscoping00104m1.NestedItem, java.lang.String name="nestedItem", java.lang.String namespace="")
meth public java.util.List<javasoft.sqe.tests.localscoping.localscoping00104m1.NestedItem> getNestedItem()
supr java.lang.Object

CLSS public javasoft.sqe.tests.localscoping.localscoping00104m1.TopType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="topType", java.lang.String namespace="##default", java.lang.String[] propOrder=["topItems"])
cons public TopType()
fld protected javasoft.sqe.tests.localscoping.localscoping00104m1.TopItems topItems
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.localscoping.localscoping00104m1.TopItems getTopItems()
meth public void setTopItems(javasoft.sqe.tests.localscoping.localscoping00104m1.TopItems)
supr java.lang.Object

CLSS public jakarta.xml.bind.JAXBElement<%0 extends java.lang.Object>
cons public JAXBElement(javax.xml.namespace.QName,java.lang.Class<{jakarta.xml.bind.JAXBElement%0}>,java.lang.Class,{jakarta.xml.bind.JAXBElement%0})
cons public JAXBElement(javax.xml.namespace.QName,java.lang.Class<{jakarta.xml.bind.JAXBElement%0}>,{jakarta.xml.bind.JAXBElement%0})
fld protected boolean nil
fld protected final java.lang.Class scope
fld protected final java.lang.Class<{jakarta.xml.bind.JAXBElement%0}> declaredType
fld protected final javax.xml.namespace.QName name
fld protected {jakarta.xml.bind.JAXBElement%0} value
innr public final static GlobalScope
intf java.io.Serializable
meth public boolean isGlobalScope()
meth public boolean isNil()
meth public boolean isTypeSubstituted()
meth public java.lang.Class getScope()
meth public java.lang.Class<{jakarta.xml.bind.JAXBElement%0}> getDeclaredType()
meth public javax.xml.namespace.QName getName()
meth public void setNil(boolean)
meth public void setValue({jakarta.xml.bind.JAXBElement%0})
meth public {jakarta.xml.bind.JAXBElement%0} getValue()
supr java.lang.Object
hfds serialVersionUID

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

