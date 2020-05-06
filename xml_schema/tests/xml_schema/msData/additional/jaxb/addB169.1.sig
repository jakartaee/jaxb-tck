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

CLSS public javasoft.sqe.tests.additional.test93490_16.MapInfoType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MapInfoType", java.lang.String namespace="##default", java.lang.String[] propOrder=["schema"])
cons public MapInfoType()
fld protected java.lang.String hideInactiveListBorder
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="HideInactiveListBorder", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
fld protected java.lang.String selectionNamespaces
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="SelectionNamespaces", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
fld protected java.util.List<javasoft.sqe.tests.additional.test93490_16.SchemaType> schema
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="Schema", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.test93490_16.TruefalseType hideSingleMappedCellBorder
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="HideSingleMappedCellBorder", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
meth public java.lang.String getHideInactiveListBorder()
meth public java.lang.String getSelectionNamespaces()
meth public java.util.List<javasoft.sqe.tests.additional.test93490_16.SchemaType> getSchema()
meth public javasoft.sqe.tests.additional.test93490_16.TruefalseType getHideSingleMappedCellBorder()
meth public void setHideInactiveListBorder(java.lang.String)
meth public void setHideSingleMappedCellBorder(javasoft.sqe.tests.additional.test93490_16.TruefalseType)
meth public void setSelectionNamespaces(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test93490_16.SchemaType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SchemaType", java.lang.String namespace="##default", java.lang.String[] propOrder=["any"])
cons public SchemaType()
fld protected java.lang.String id
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="ID", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
fld protected java.lang.String namespace
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="Namespace", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
fld protected java.lang.String schemaRef
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="SchemaRef", java.lang.String namespace="http://schemas.microsoft.com/office/excel/2003/xml")
fld protected java.util.List<org.w3c.dom.Element> any
 anno 0 jakarta.xml.bind.annotation.XmlAnyElement(boolean lax=false, java.lang.Class<? extends jakarta.xml.bind.annotation.DomHandler> value=class jakarta.xml.bind.annotation.W3CDomHandler)
meth public java.lang.String getID()
meth public java.lang.String getNamespace()
meth public java.lang.String getSchemaRef()
meth public java.util.List<org.w3c.dom.Element> getAny()
meth public void setID(java.lang.String)
meth public void setNamespace(java.lang.String)
meth public void setSchemaRef(java.lang.String)
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.additional.test93490_16.TruefalseType
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="truefalseType", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.additional.test93490_16.TruefalseType FALSE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="false")
fld public final static javasoft.sqe.tests.additional.test93490_16.TruefalseType TRUE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="true")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.additional.test93490_16.TruefalseType fromValue(java.lang.String)
meth public static javasoft.sqe.tests.additional.test93490_16.TruefalseType valueOf(java.lang.String)
meth public static javasoft.sqe.tests.additional.test93490_16.TruefalseType[] values()
supr java.lang.Enum<javasoft.sqe.tests.additional.test93490_16.TruefalseType>
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

