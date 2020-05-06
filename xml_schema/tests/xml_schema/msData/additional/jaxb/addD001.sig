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

CLSS public javasoft.sqe.tests.additional.po1.Items
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="Items", java.lang.String namespace="##default", java.lang.String[] propOrder=["item"])
cons public Items()
fld protected java.util.List<javasoft.sqe.tests.additional.po1.Items$Item> item
innr public static Item
meth public java.util.List<javasoft.sqe.tests.additional.po1.Items$Item> getItem()
supr java.lang.Object

CLSS public static javasoft.sqe.tests.additional.po1.Items$Item
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["productName", "quantity", "usPrice", "comment", "shipDate"])
cons public Item()
fld protected int quantity
fld protected java.lang.String comment
fld protected java.lang.String partNum
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String productName
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.math.BigDecimal usPrice
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="USPrice", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar shipDate
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
meth public int getQuantity()
meth public java.lang.String getComment()
meth public java.lang.String getPartNum()
meth public java.lang.String getProductName()
meth public java.math.BigDecimal getUSPrice()
meth public javax.xml.datatype.XMLGregorianCalendar getShipDate()
meth public void setComment(java.lang.String)
meth public void setPartNum(java.lang.String)
meth public void setProductName(java.lang.String)
meth public void setQuantity(int)
meth public void setShipDate(javax.xml.datatype.XMLGregorianCalendar)
meth public void setUSPrice(java.math.BigDecimal)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.po1.PurchaseOrderType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PurchaseOrderType", java.lang.String namespace="##default", java.lang.String[] propOrder=["shipTo", "billTo", "comment", "items"])
cons public PurchaseOrderType()
fld protected java.lang.String comment
fld protected javasoft.sqe.tests.additional.po1.Items items
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.po1.USAddress billTo
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.po1.USAddress shipTo
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar orderDate
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
meth public java.lang.String getComment()
meth public javasoft.sqe.tests.additional.po1.Items getItems()
meth public javasoft.sqe.tests.additional.po1.USAddress getBillTo()
meth public javasoft.sqe.tests.additional.po1.USAddress getShipTo()
meth public javax.xml.datatype.XMLGregorianCalendar getOrderDate()
meth public void setBillTo(javasoft.sqe.tests.additional.po1.USAddress)
meth public void setComment(java.lang.String)
meth public void setItems(javasoft.sqe.tests.additional.po1.Items)
meth public void setOrderDate(javax.xml.datatype.XMLGregorianCalendar)
meth public void setShipTo(javasoft.sqe.tests.additional.po1.USAddress)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.po1.USAddress
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="USAddress", java.lang.String namespace="##default", java.lang.String[] propOrder=["name", "street", "city", "state", "zip"])
cons public USAddress()
fld protected java.lang.String city
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String country
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="NMTOKEN", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter(java.lang.Class type=class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT, java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value=class jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter)
fld protected java.lang.String name
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String state
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String street
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.math.BigDecimal zip
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getCity()
meth public java.lang.String getCountry()
meth public java.lang.String getName()
meth public java.lang.String getState()
meth public java.lang.String getStreet()
meth public java.math.BigDecimal getZip()
meth public void setCity(java.lang.String)
meth public void setCountry(java.lang.String)
meth public void setName(java.lang.String)
meth public void setState(java.lang.String)
meth public void setStreet(java.lang.String)
meth public void setZip(java.math.BigDecimal)
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

