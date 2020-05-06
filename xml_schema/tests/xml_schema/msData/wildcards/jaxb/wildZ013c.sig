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

CLSS public javasoft.sqe.tests.wildcards.test328873.Base
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.wildcards.test328873.Derived])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="base", java.lang.String namespace="##default", java.lang.String[] propOrder=["sub", "sub2", "sub3", "sub4", "sub5", "sub6"])
cons public Base()
fld protected javasoft.sqe.tests.wildcards.test328873.Derived2 sub
fld protected javasoft.sqe.tests.wildcards.test328873.Derived3 sub2
fld protected javasoft.sqe.tests.wildcards.test328873.Derived4 sub3
fld protected javasoft.sqe.tests.wildcards.test328873.Derived5 sub4
fld protected javasoft.sqe.tests.wildcards.test328873.Intersection1 sub5
fld protected javasoft.sqe.tests.wildcards.test328873.Intersection2 sub6
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
meth public javasoft.sqe.tests.wildcards.test328873.Derived2 getSub()
meth public javasoft.sqe.tests.wildcards.test328873.Derived3 getSub2()
meth public javasoft.sqe.tests.wildcards.test328873.Derived4 getSub3()
meth public javasoft.sqe.tests.wildcards.test328873.Derived5 getSub4()
meth public javasoft.sqe.tests.wildcards.test328873.Intersection1 getSub5()
meth public javasoft.sqe.tests.wildcards.test328873.Intersection2 getSub6()
meth public void setSub(javasoft.sqe.tests.wildcards.test328873.Derived2)
meth public void setSub2(javasoft.sqe.tests.wildcards.test328873.Derived3)
meth public void setSub3(javasoft.sqe.tests.wildcards.test328873.Derived4)
meth public void setSub4(javasoft.sqe.tests.wildcards.test328873.Derived5)
meth public void setSub5(javasoft.sqe.tests.wildcards.test328873.Intersection1)
meth public void setSub6(javasoft.sqe.tests.wildcards.test328873.Intersection2)
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.wildcards.test328873.Base2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.wildcards.test328873.Derived2])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="base2", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Base2()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.wildcards.test328873.Base3
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.wildcards.test328873.Derived3])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="base3", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Base3()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.wildcards.test328873.Base4
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.wildcards.test328873.Derived5, class javasoft.sqe.tests.wildcards.test328873.Derived4])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="base4", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Base4()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.wildcards.test328873.Derived
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="derived", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Derived()
supr javasoft.sqe.tests.wildcards.test328873.Base

CLSS public javasoft.sqe.tests.wildcards.test328873.Derived2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="derived2", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Derived2()
supr javasoft.sqe.tests.wildcards.test328873.Base2

CLSS public javasoft.sqe.tests.wildcards.test328873.Derived3
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="derived3", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Derived3()
supr javasoft.sqe.tests.wildcards.test328873.Base3

CLSS public javasoft.sqe.tests.wildcards.test328873.Derived4
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="derived4", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Derived4()
supr javasoft.sqe.tests.wildcards.test328873.Base4

CLSS public javasoft.sqe.tests.wildcards.test328873.Derived5
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="derived5", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Derived5()
supr javasoft.sqe.tests.wildcards.test328873.Base4

CLSS public javasoft.sqe.tests.wildcards.test328873.Intersection1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="intersection1", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Intersection1()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.wildcards.test328873.Intersection2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="intersection2", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Intersection2()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public abstract interface !annotation jakarta.xml.bind.annotation.XmlAccessorType
 anno 0 java.lang.annotation.Inherited()
 anno 0 java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy value=RUNTIME)
 anno 0 java.lang.annotation.Target(java.lang.annotation.ElementType[] value=[PACKAGE, TYPE])
intf java.lang.annotation.Annotation
meth public abstract !hasdefault jakarta.xml.bind.annotation.XmlAccessType value()

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

