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

CLSS public javasoft.sqe.tests.additional.test74834.Datafile
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="datafile", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["nonstringsection", "stringsection"])
cons public Datafile()
fld protected javasoft.sqe.tests.additional.test74834.Datafile$Nonstringsection nonstringsection
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.test74834.Datafile$Stringsection stringsection
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
innr public static Nonstringsection
innr public static Stringsection
meth public javasoft.sqe.tests.additional.test74834.Datafile$Nonstringsection getNonstringsection()
meth public javasoft.sqe.tests.additional.test74834.Datafile$Stringsection getStringsection()
meth public void setNonstringsection(javasoft.sqe.tests.additional.test74834.Datafile$Nonstringsection)
meth public void setStringsection(javasoft.sqe.tests.additional.test74834.Datafile$Stringsection)
supr java.lang.Object

CLSS public static javasoft.sqe.tests.additional.test74834.Datafile$Nonstringsection
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["bigintOrIntOrSmallint"])
cons public Nonstringsection()
fld protected java.util.List<jakarta.xml.bind.JAXBElement<?>> bigintOrIntOrSmallint
 anno 0 jakarta.xml.bind.annotation.XmlElementRefs(jakarta.xml.bind.annotation.XmlElementRef[] value=[anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="smalldatetime", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="real", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="int", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="tinyint", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="datetime", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="smallint", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="money", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="numeric", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="bigint", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="decimal", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="smallmoney", java.lang.String namespace=""), anno 0 jakarta.xml.bind.annotation.XmlElementRef(java.lang.Class type=class jakarta.xml.bind.JAXBElement, java.lang.String name="float", java.lang.String namespace="")])
meth public java.util.List<jakarta.xml.bind.JAXBElement<?>> getBigintOrIntOrSmallint()
supr java.lang.Object

CLSS public static javasoft.sqe.tests.additional.test74834.Datafile$Stringsection
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["string"])
cons public Stringsection()
fld protected java.util.List<java.lang.String> string
meth public java.util.List<java.lang.String> getString()
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test74834.MyDateTime
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="myDateTime", java.lang.String namespace="##default", java.lang.String[] propOrder=["date", "time", "localizedDt"])
cons public MyDateTime()
fld protected java.lang.String localizedDt
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="localized_dt", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar date
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
fld protected javax.xml.datatype.XMLGregorianCalendar time
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getLocalizedDt()
meth public javax.xml.datatype.XMLGregorianCalendar getDate()
meth public javax.xml.datatype.XMLGregorianCalendar getTime()
meth public void setDate(javax.xml.datatype.XMLGregorianCalendar)
meth public void setLocalizedDt(java.lang.String)
meth public void setTime(javax.xml.datatype.XMLGregorianCalendar)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test74834.MySmallDateTime
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="mySmallDateTime", java.lang.String namespace="##default", java.lang.String[] propOrder=["date", "time", "localizedSdt"])
cons public MySmallDateTime()
fld protected java.lang.String localizedSdt
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="localized_sdt", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar date
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
fld protected javax.xml.datatype.XMLGregorianCalendar time
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getLocalizedSdt()
meth public javax.xml.datatype.XMLGregorianCalendar getDate()
meth public javax.xml.datatype.XMLGregorianCalendar getTime()
meth public void setDate(javax.xml.datatype.XMLGregorianCalendar)
meth public void setLocalizedSdt(java.lang.String)
meth public void setTime(javax.xml.datatype.XMLGregorianCalendar)
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

