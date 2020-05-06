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

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.AmountType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AmountType", java.lang.String namespace="globalBindings/mapSimpleTypeDef", java.lang.String[] propOrder=["value"])
cons public AmountType()
fld protected int value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
fld protected java.lang.String currency
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
meth public int getValue()
meth public java.lang.String getCurrency()
meth public void setCurrency(java.lang.String)
meth public void setValue(int)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.AppInt
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AppInt", java.lang.String namespace="globalBindings/mapSimpleTypeDef", java.lang.String[] propOrder=["value"])
cons public AppInt()
fld protected int value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public int getValue()
meth public void setValue(int)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Bill
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="bill", java.lang.String namespace="globalBindings/mapSimpleTypeDef")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["product"])
cons public Bill()
fld protected java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Product> product
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="globalBindings/mapSimpleTypeDef")
meth public java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Product> getProduct()
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.ENTITIES
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ENTITIES", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ENTITIES()
fld protected java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.ENTITY> value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="ENTITIES", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.ENTITY> getValue()
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.ENTITY
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ENTITY", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ENTITY()
fld protected javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NCName value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="ENTITY", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NCName getValue()
meth public void setValue(javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NCName)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.IDREFS
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="IDREFS", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public IDREFS()
fld protected java.util.List<java.lang.Object> value
 anno 0 jakarta.xml.bind.annotation.XmlIDREF()
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="IDREFS", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.util.List<java.lang.Object> getValue()
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Language
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="language", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Language()
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="language", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
 anno 0 jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter(java.lang.Class type=class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT, java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value=class jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter)
meth public java.lang.String getValue()
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NCName
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NCName", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NCName()
fld protected javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Name value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="NCName", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Name getValue()
meth public void setValue(javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Name)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NMTOKEN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NMTOKEN", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NMTOKEN()
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="NMTOKEN", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
 anno 0 jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter(java.lang.Class type=class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT, java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value=class jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter)
meth public java.lang.String getValue()
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NMTOKENS
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NMTOKENS", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NMTOKENS()
fld protected java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NMTOKEN> value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="NMTOKENS", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.util.List<javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NMTOKEN> getValue()
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Name
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="Name", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public Name()
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="Name", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
 anno 0 jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter(java.lang.Class type=class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT, java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value=class jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter)
meth public java.lang.String getValue()
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NegativeInteger
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="negativeInteger", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NegativeInteger()
fld protected javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonPositiveInteger value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="negativeInteger", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonPositiveInteger getValue()
meth public void setValue(javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonPositiveInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="nonNegativeInteger", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NonNegativeInteger()
fld protected java.math.BigInteger value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="nonNegativeInteger", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.math.BigInteger getValue()
meth public void setValue(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonPositiveInteger
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="nonPositiveInteger", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NonPositiveInteger()
fld protected java.math.BigInteger value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="nonPositiveInteger", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.math.BigInteger getValue()
meth public void setValue(java.math.BigInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.PositiveInteger
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="positiveInteger", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PositiveInteger()
fld protected javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="positiveInteger", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger getValue()
meth public void setValue(javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.Product
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="product", java.lang.String namespace="globalBindings/mapSimpleTypeDef")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["name", "price"])
cons public Product()
fld protected java.lang.Object price
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class java.lang.Integer, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String name
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.Object getPrice()
meth public java.lang.String getName()
meth public void setName(java.lang.String)
meth public void setPrice(java.lang.Object)
supr java.lang.Object

CLSS public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.UnsignedLong
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="unsignedLong", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public UnsignedLong()
fld protected javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger value
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="unsignedLong", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger getValue()
meth public void setValue(javasoft.sqe.tests.mapsimpletypedef.mapsimpletypedef00101m1.NonNegativeInteger)
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

