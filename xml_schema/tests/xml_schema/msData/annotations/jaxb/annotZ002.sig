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

CLSS public javasoft.sqe.tests.annotations.annotz002.AD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.LA19CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD", java.lang.String namespace="##default", java.lang.String[] propOrder=["ad1", "ad2", "ad3", "ad4", "ad5", "ad6", "ad7", "ad8"])
cons public AD()
fld protected javasoft.sqe.tests.annotations.annotz002.AD1CONTENT ad1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD2CONTENT ad2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD3CONTENT ad3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD4CONTENT ad4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD5CONTENT ad5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD6CONTENT ad6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD7CONTENT ad7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AD8CONTENT ad8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AD.8", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.AD1CONTENT getAD1()
meth public javasoft.sqe.tests.annotations.annotz002.AD2CONTENT getAD2()
meth public javasoft.sqe.tests.annotations.annotz002.AD3CONTENT getAD3()
meth public javasoft.sqe.tests.annotations.annotz002.AD4CONTENT getAD4()
meth public javasoft.sqe.tests.annotations.annotz002.AD5CONTENT getAD5()
meth public javasoft.sqe.tests.annotations.annotz002.AD6CONTENT getAD6()
meth public javasoft.sqe.tests.annotations.annotz002.AD7CONTENT getAD7()
meth public javasoft.sqe.tests.annotations.annotz002.AD8CONTENT getAD8()
meth public void setAD1(javasoft.sqe.tests.annotations.annotz002.AD1CONTENT)
meth public void setAD2(javasoft.sqe.tests.annotations.annotz002.AD2CONTENT)
meth public void setAD3(javasoft.sqe.tests.annotations.annotz002.AD3CONTENT)
meth public void setAD4(javasoft.sqe.tests.annotations.annotz002.AD4CONTENT)
meth public void setAD5(javasoft.sqe.tests.annotations.annotz002.AD5CONTENT)
meth public void setAD6(javasoft.sqe.tests.annotations.annotz002.AD6CONTENT)
meth public void setAD7(javasoft.sqe.tests.annotations.annotz002.AD7CONTENT)
meth public void setAD8(javasoft.sqe.tests.annotations.annotz002.AD8CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AD8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AD.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AD8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AUI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AUI", java.lang.String namespace="##default", java.lang.String[] propOrder=["aui1", "aui2", "aui3"])
cons public AUI()
fld protected javasoft.sqe.tests.annotations.annotz002.AUI1CONTENT aui1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AUI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AUI2CONTENT aui2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AUI.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.AUI3CONTENT aui3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="AUI.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.AUI1CONTENT getAUI1()
meth public javasoft.sqe.tests.annotations.annotz002.AUI2CONTENT getAUI2()
meth public javasoft.sqe.tests.annotations.annotz002.AUI3CONTENT getAUI3()
meth public void setAUI1(javasoft.sqe.tests.annotations.annotz002.AUI1CONTENT)
meth public void setAUI2(javasoft.sqe.tests.annotations.annotz002.AUI2CONTENT)
meth public void setAUI3(javasoft.sqe.tests.annotations.annotz002.AUI3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AUI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AUI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AUI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AUI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AUI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AUI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.AUI3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="AUI.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public AUI3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCD", java.lang.String namespace="##default", java.lang.String[] propOrder=["ccd1", "ccd2"])
cons public CCD()
fld protected javasoft.sqe.tests.annotations.annotz002.CCD1CONTENT ccd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CCD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CCD2CONTENT ccd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CCD.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CCD1CONTENT getCCD1()
meth public javasoft.sqe.tests.annotations.annotz002.CCD2CONTENT getCCD2()
meth public void setCCD1(javasoft.sqe.tests.annotations.annotz002.CCD1CONTENT)
meth public void setCCD2(javasoft.sqe.tests.annotations.annotz002.CCD2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CCD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CCD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.CCP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CD4CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCP", java.lang.String namespace="##default", java.lang.String[] propOrder=["ccp1", "ccp2", "ccp3"])
cons public CCP()
fld protected javasoft.sqe.tests.annotations.annotz002.CCP1CONTENT ccp1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CCP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CCP2CONTENT ccp2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CCP.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CCP3CONTENT ccp3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CCP.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CCP1CONTENT getCCP1()
meth public javasoft.sqe.tests.annotations.annotz002.CCP2CONTENT getCCP2()
meth public javasoft.sqe.tests.annotations.annotz002.CCP3CONTENT getCCP3()
meth public void setCCP1(javasoft.sqe.tests.annotations.annotz002.CCP1CONTENT)
meth public void setCCP2(javasoft.sqe.tests.annotations.annotz002.CCP2CONTENT)
meth public void setCCP3(javasoft.sqe.tests.annotations.annotz002.CCP3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CCP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CCP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CCP3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CCP.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CCP3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD", java.lang.String namespace="##default", java.lang.String[] propOrder=["cd1", "cd2", "cd3", "cd4", "cd5", "cd6"])
cons public CD()
fld protected javasoft.sqe.tests.annotations.annotz002.CD1CONTENT cd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CD2CONTENT cd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CD3CONTENT cd3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CD4CONTENT cd4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CD5CONTENT cd5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CD6CONTENT cd6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CD.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CD1CONTENT getCD1()
meth public javasoft.sqe.tests.annotations.annotz002.CD2CONTENT getCD2()
meth public javasoft.sqe.tests.annotations.annotz002.CD3CONTENT getCD3()
meth public javasoft.sqe.tests.annotations.annotz002.CD4CONTENT getCD4()
meth public javasoft.sqe.tests.annotations.annotz002.CD5CONTENT getCD5()
meth public javasoft.sqe.tests.annotations.annotz002.CD6CONTENT getCD6()
meth public void setCD1(javasoft.sqe.tests.annotations.annotz002.CD1CONTENT)
meth public void setCD2(javasoft.sqe.tests.annotations.annotz002.CD2CONTENT)
meth public void setCD3(javasoft.sqe.tests.annotations.annotz002.CD3CONTENT)
meth public void setCD4(javasoft.sqe.tests.annotations.annotz002.CD4CONTENT)
meth public void setCD5(javasoft.sqe.tests.annotations.annotz002.CD5CONTENT)
meth public void setCD6(javasoft.sqe.tests.annotations.annotz002.CD6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.WVI

CLSS public javasoft.sqe.tests.annotations.annotz002.CD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.WVS

CLSS public javasoft.sqe.tests.annotations.annotz002.CD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CSU

CLSS public javasoft.sqe.tests.annotations.annotz002.CD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CCP

CLSS public javasoft.sqe.tests.annotations.annotz002.CD5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CD5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CD6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CD6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.NR

CLSS public javasoft.sqe.tests.annotations.annotz002.CE
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.PPN17CONTENT, class javasoft.sqe.tests.annotations.annotz002.XCN16CONTENT, class javasoft.sqe.tests.annotations.annotz002.VID2CONTENT, class javasoft.sqe.tests.annotations.annotz002.VID3CONTENT, class javasoft.sqe.tests.annotations.annotz002.PIP1CONTENT, class javasoft.sqe.tests.annotations.annotz002.PIP2CONTENT, class javasoft.sqe.tests.annotations.annotz002.XPN9CONTENT, class javasoft.sqe.tests.annotations.annotz002.DIN2CONTENT, class javasoft.sqe.tests.annotations.annotz002.CP5CONTENT, class javasoft.sqe.tests.annotations.annotz002.CQ2CONTENT, class javasoft.sqe.tests.annotations.annotz002.TQ11CONTENT, class javasoft.sqe.tests.annotations.annotz002.PRL1CONTENT, class javasoft.sqe.tests.annotations.annotz002.MOC2CONTENT, class javasoft.sqe.tests.annotations.annotz002.SPS1CONTENT, class javasoft.sqe.tests.annotations.annotz002.SPS7CONTENT, class javasoft.sqe.tests.annotations.annotz002.SPS6CONTENT, class javasoft.sqe.tests.annotations.annotz002.SPS5CONTENT, class javasoft.sqe.tests.annotations.annotz002.SPS4CONTENT, class javasoft.sqe.tests.annotations.annotz002.OSP1CONTENT, class javasoft.sqe.tests.annotations.annotz002.ELD4CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE", java.lang.String namespace="##default", java.lang.String[] propOrder=["ce1", "ce2", "ce3", "ce4", "ce5", "ce6"])
cons public CE()
fld protected javasoft.sqe.tests.annotations.annotz002.CE1CONTENT ce1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CE2CONTENT ce2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CE3CONTENT ce3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CE4CONTENT ce4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CE5CONTENT ce5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CE6CONTENT ce6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CE.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CE1CONTENT getCE1()
meth public javasoft.sqe.tests.annotations.annotz002.CE2CONTENT getCE2()
meth public javasoft.sqe.tests.annotations.annotz002.CE3CONTENT getCE3()
meth public javasoft.sqe.tests.annotations.annotz002.CE4CONTENT getCE4()
meth public javasoft.sqe.tests.annotations.annotz002.CE5CONTENT getCE5()
meth public javasoft.sqe.tests.annotations.annotz002.CE6CONTENT getCE6()
meth public void setCE1(javasoft.sqe.tests.annotations.annotz002.CE1CONTENT)
meth public void setCE2(javasoft.sqe.tests.annotations.annotz002.CE2CONTENT)
meth public void setCE3(javasoft.sqe.tests.annotations.annotz002.CE3CONTENT)
meth public void setCE4(javasoft.sqe.tests.annotations.annotz002.CE4CONTENT)
meth public void setCE5(javasoft.sqe.tests.annotations.annotz002.CE5CONTENT)
meth public void setCE6(javasoft.sqe.tests.annotations.annotz002.CE6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CE6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CE.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CE6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF", java.lang.String namespace="##default", java.lang.String[] propOrder=["cf1", "cf2", "cf3", "cf4", "cf5", "cf6"])
cons public CF()
fld protected javasoft.sqe.tests.annotations.annotz002.CF1CONTENT cf1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CF2CONTENT cf2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CF3CONTENT cf3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CF4CONTENT cf4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CF5CONTENT cf5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CF6CONTENT cf6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CF.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CF1CONTENT getCF1()
meth public javasoft.sqe.tests.annotations.annotz002.CF2CONTENT getCF2()
meth public javasoft.sqe.tests.annotations.annotz002.CF3CONTENT getCF3()
meth public javasoft.sqe.tests.annotations.annotz002.CF4CONTENT getCF4()
meth public javasoft.sqe.tests.annotations.annotz002.CF5CONTENT getCF5()
meth public javasoft.sqe.tests.annotations.annotz002.CF6CONTENT getCF6()
meth public void setCF1(javasoft.sqe.tests.annotations.annotz002.CF1CONTENT)
meth public void setCF2(javasoft.sqe.tests.annotations.annotz002.CF2CONTENT)
meth public void setCF3(javasoft.sqe.tests.annotations.annotz002.CF3CONTENT)
meth public void setCF4(javasoft.sqe.tests.annotations.annotz002.CF4CONTENT)
meth public void setCF5(javasoft.sqe.tests.annotations.annotz002.CF5CONTENT)
meth public void setCF6(javasoft.sqe.tests.annotations.annotz002.CF6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CF6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CF.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CF6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CK
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CK", java.lang.String namespace="##default", java.lang.String[] propOrder=["ck1", "ck2", "ck3", "ck4"])
cons public CK()
fld protected javasoft.sqe.tests.annotations.annotz002.CK1CONTENT ck1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CK.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CK2CONTENT ck2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CK.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CK3CONTENT ck3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CK.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CK4CONTENT ck4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CK.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CK1CONTENT getCK1()
meth public javasoft.sqe.tests.annotations.annotz002.CK2CONTENT getCK2()
meth public javasoft.sqe.tests.annotations.annotz002.CK3CONTENT getCK3()
meth public javasoft.sqe.tests.annotations.annotz002.CK4CONTENT getCK4()
meth public void setCK1(javasoft.sqe.tests.annotations.annotz002.CK1CONTENT)
meth public void setCK2(javasoft.sqe.tests.annotations.annotz002.CK2CONTENT)
meth public void setCK3(javasoft.sqe.tests.annotations.annotz002.CK3CONTENT)
meth public void setCK4(javasoft.sqe.tests.annotations.annotz002.CK4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CK1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CK.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CK1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CK2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CK.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CK2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CK3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CK.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CK3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CK4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CK.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CK4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.CN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN", java.lang.String namespace="##default", java.lang.String[] propOrder=["cn1", "cn2", "cn3", "cn4", "cn5", "cn6", "cn7", "cn8", "cn9"])
cons public CN()
fld protected javasoft.sqe.tests.annotations.annotz002.CN1CONTENT cn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN2CONTENT cn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN3CONTENT cn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN4CONTENT cn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN5CONTENT cn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN6CONTENT cn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN7CONTENT cn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN8CONTENT cn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CN9CONTENT cn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CN1CONTENT getCN1()
meth public javasoft.sqe.tests.annotations.annotz002.CN2CONTENT getCN2()
meth public javasoft.sqe.tests.annotations.annotz002.CN3CONTENT getCN3()
meth public javasoft.sqe.tests.annotations.annotz002.CN4CONTENT getCN4()
meth public javasoft.sqe.tests.annotations.annotz002.CN5CONTENT getCN5()
meth public javasoft.sqe.tests.annotations.annotz002.CN6CONTENT getCN6()
meth public javasoft.sqe.tests.annotations.annotz002.CN7CONTENT getCN7()
meth public javasoft.sqe.tests.annotations.annotz002.CN8CONTENT getCN8()
meth public javasoft.sqe.tests.annotations.annotz002.CN9CONTENT getCN9()
meth public void setCN1(javasoft.sqe.tests.annotations.annotz002.CN1CONTENT)
meth public void setCN2(javasoft.sqe.tests.annotations.annotz002.CN2CONTENT)
meth public void setCN3(javasoft.sqe.tests.annotations.annotz002.CN3CONTENT)
meth public void setCN4(javasoft.sqe.tests.annotations.annotz002.CN4CONTENT)
meth public void setCN5(javasoft.sqe.tests.annotations.annotz002.CN5CONTENT)
meth public void setCN6(javasoft.sqe.tests.annotations.annotz002.CN6CONTENT)
meth public void setCN7(javasoft.sqe.tests.annotations.annotz002.CN7CONTENT)
meth public void setCN8(javasoft.sqe.tests.annotations.annotz002.CN8CONTENT)
meth public void setCN9(javasoft.sqe.tests.annotations.annotz002.CN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.CN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE", java.lang.String namespace="##default", java.lang.String[] propOrder=["cne1", "cne2", "cne3", "cne4", "cne5", "cne6", "cne7", "cne8", "cne9"])
cons public CNE()
fld protected javasoft.sqe.tests.annotations.annotz002.CNE1CONTENT cne1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE2CONTENT cne2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE3CONTENT cne3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE4CONTENT cne4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE5CONTENT cne5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE6CONTENT cne6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE7CONTENT cne7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE8CONTENT cne8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNE9CONTENT cne9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNE.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CNE1CONTENT getCNE1()
meth public javasoft.sqe.tests.annotations.annotz002.CNE2CONTENT getCNE2()
meth public javasoft.sqe.tests.annotations.annotz002.CNE3CONTENT getCNE3()
meth public javasoft.sqe.tests.annotations.annotz002.CNE4CONTENT getCNE4()
meth public javasoft.sqe.tests.annotations.annotz002.CNE5CONTENT getCNE5()
meth public javasoft.sqe.tests.annotations.annotz002.CNE6CONTENT getCNE6()
meth public javasoft.sqe.tests.annotations.annotz002.CNE7CONTENT getCNE7()
meth public javasoft.sqe.tests.annotations.annotz002.CNE8CONTENT getCNE8()
meth public javasoft.sqe.tests.annotations.annotz002.CNE9CONTENT getCNE9()
meth public void setCNE1(javasoft.sqe.tests.annotations.annotz002.CNE1CONTENT)
meth public void setCNE2(javasoft.sqe.tests.annotations.annotz002.CNE2CONTENT)
meth public void setCNE3(javasoft.sqe.tests.annotations.annotz002.CNE3CONTENT)
meth public void setCNE4(javasoft.sqe.tests.annotations.annotz002.CNE4CONTENT)
meth public void setCNE5(javasoft.sqe.tests.annotations.annotz002.CNE5CONTENT)
meth public void setCNE6(javasoft.sqe.tests.annotations.annotz002.CNE6CONTENT)
meth public void setCNE7(javasoft.sqe.tests.annotations.annotz002.CNE7CONTENT)
meth public void setCNE8(javasoft.sqe.tests.annotations.annotz002.CNE8CONTENT)
meth public void setCNE9(javasoft.sqe.tests.annotations.annotz002.CNE9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNE9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNE.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNE9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.NDL1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN", java.lang.String namespace="##default", java.lang.String[] propOrder=["cnn1", "cnn2", "cnn3", "cnn4", "cnn5", "cnn6", "cnn7", "cnn8", "cnn9", "cnn10", "cnn11"])
cons public CNN()
fld protected javasoft.sqe.tests.annotations.annotz002.CNN10CONTENT cnn10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN11CONTENT cnn11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN1CONTENT cnn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN2CONTENT cnn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN3CONTENT cnn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN4CONTENT cnn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN5CONTENT cnn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN6CONTENT cnn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN7CONTENT cnn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN8CONTENT cnn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CNN9CONTENT cnn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CNN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CNN10CONTENT getCNN10()
meth public javasoft.sqe.tests.annotations.annotz002.CNN11CONTENT getCNN11()
meth public javasoft.sqe.tests.annotations.annotz002.CNN1CONTENT getCNN1()
meth public javasoft.sqe.tests.annotations.annotz002.CNN2CONTENT getCNN2()
meth public javasoft.sqe.tests.annotations.annotz002.CNN3CONTENT getCNN3()
meth public javasoft.sqe.tests.annotations.annotz002.CNN4CONTENT getCNN4()
meth public javasoft.sqe.tests.annotations.annotz002.CNN5CONTENT getCNN5()
meth public javasoft.sqe.tests.annotations.annotz002.CNN6CONTENT getCNN6()
meth public javasoft.sqe.tests.annotations.annotz002.CNN7CONTENT getCNN7()
meth public javasoft.sqe.tests.annotations.annotz002.CNN8CONTENT getCNN8()
meth public javasoft.sqe.tests.annotations.annotz002.CNN9CONTENT getCNN9()
meth public void setCNN1(javasoft.sqe.tests.annotations.annotz002.CNN1CONTENT)
meth public void setCNN10(javasoft.sqe.tests.annotations.annotz002.CNN10CONTENT)
meth public void setCNN11(javasoft.sqe.tests.annotations.annotz002.CNN11CONTENT)
meth public void setCNN2(javasoft.sqe.tests.annotations.annotz002.CNN2CONTENT)
meth public void setCNN3(javasoft.sqe.tests.annotations.annotz002.CNN3CONTENT)
meth public void setCNN4(javasoft.sqe.tests.annotations.annotz002.CNN4CONTENT)
meth public void setCNN5(javasoft.sqe.tests.annotations.annotz002.CNN5CONTENT)
meth public void setCNN6(javasoft.sqe.tests.annotations.annotz002.CNN6CONTENT)
meth public void setCNN7(javasoft.sqe.tests.annotations.annotz002.CNN7CONTENT)
meth public void setCNN8(javasoft.sqe.tests.annotations.annotz002.CNN8CONTENT)
meth public void setCNN9(javasoft.sqe.tests.annotations.annotz002.CNN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CNN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CNN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CNN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CNN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP", java.lang.String namespace="##default", java.lang.String[] propOrder=["cp1", "cp2", "cp3", "cp4", "cp5", "cp6"])
cons public CP()
fld protected javasoft.sqe.tests.annotations.annotz002.CP1CONTENT cp1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CP2CONTENT cp2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CP3CONTENT cp3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CP4CONTENT cp4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CP5CONTENT cp5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CP6CONTENT cp6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CP.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CP1CONTENT getCP1()
meth public javasoft.sqe.tests.annotations.annotz002.CP2CONTENT getCP2()
meth public javasoft.sqe.tests.annotations.annotz002.CP3CONTENT getCP3()
meth public javasoft.sqe.tests.annotations.annotz002.CP4CONTENT getCP4()
meth public javasoft.sqe.tests.annotations.annotz002.CP5CONTENT getCP5()
meth public javasoft.sqe.tests.annotations.annotz002.CP6CONTENT getCP6()
meth public void setCP1(javasoft.sqe.tests.annotations.annotz002.CP1CONTENT)
meth public void setCP2(javasoft.sqe.tests.annotations.annotz002.CP2CONTENT)
meth public void setCP3(javasoft.sqe.tests.annotations.annotz002.CP3CONTENT)
meth public void setCP4(javasoft.sqe.tests.annotations.annotz002.CP4CONTENT)
meth public void setCP5(javasoft.sqe.tests.annotations.annotz002.CP5CONTENT)
meth public void setCP6(javasoft.sqe.tests.annotations.annotz002.CP6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.MO

CLSS public javasoft.sqe.tests.annotations.annotz002.CP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CP3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CP3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CP4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CP4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CP5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CP5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.CP6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CP.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CP6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CQ
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.TQ1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CQ", java.lang.String namespace="##default", java.lang.String[] propOrder=["cq1", "cq2"])
cons public CQ()
fld protected javasoft.sqe.tests.annotations.annotz002.CQ1CONTENT cq1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CQ.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CQ2CONTENT cq2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CQ.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CQ1CONTENT getCQ1()
meth public javasoft.sqe.tests.annotations.annotz002.CQ2CONTENT getCQ2()
meth public void setCQ1(javasoft.sqe.tests.annotations.annotz002.CQ1CONTENT)
meth public void setCQ2(javasoft.sqe.tests.annotations.annotz002.CQ2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CQ1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CQ.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CQ1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CQ2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CQ.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CQ2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CD3CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU", java.lang.String namespace="##default", java.lang.String[] propOrder=["csu1", "csu2", "csu3", "csu4", "csu5", "csu6", "csu7"])
cons public CSU()
fld protected javasoft.sqe.tests.annotations.annotz002.CSU1CONTENT csu1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU2CONTENT csu2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU3CONTENT csu3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU4CONTENT csu4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU5CONTENT csu5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU6CONTENT csu6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CSU7CONTENT csu7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CSU.7", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CSU1CONTENT getCSU1()
meth public javasoft.sqe.tests.annotations.annotz002.CSU2CONTENT getCSU2()
meth public javasoft.sqe.tests.annotations.annotz002.CSU3CONTENT getCSU3()
meth public javasoft.sqe.tests.annotations.annotz002.CSU4CONTENT getCSU4()
meth public javasoft.sqe.tests.annotations.annotz002.CSU5CONTENT getCSU5()
meth public javasoft.sqe.tests.annotations.annotz002.CSU6CONTENT getCSU6()
meth public javasoft.sqe.tests.annotations.annotz002.CSU7CONTENT getCSU7()
meth public void setCSU1(javasoft.sqe.tests.annotations.annotz002.CSU1CONTENT)
meth public void setCSU2(javasoft.sqe.tests.annotations.annotz002.CSU2CONTENT)
meth public void setCSU3(javasoft.sqe.tests.annotations.annotz002.CSU3CONTENT)
meth public void setCSU4(javasoft.sqe.tests.annotations.annotz002.CSU4CONTENT)
meth public void setCSU5(javasoft.sqe.tests.annotations.annotz002.CSU5CONTENT)
meth public void setCSU6(javasoft.sqe.tests.annotations.annotz002.CSU6CONTENT)
meth public void setCSU7(javasoft.sqe.tests.annotations.annotz002.CSU7CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CSU7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CSU.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CSU7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE", java.lang.String namespace="##default", java.lang.String[] propOrder=["cwe1", "cwe2", "cwe3", "cwe4", "cwe5", "cwe6", "cwe7", "cwe8", "cwe9"])
cons public CWE()
fld protected javasoft.sqe.tests.annotations.annotz002.CWE1CONTENT cwe1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE2CONTENT cwe2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE3CONTENT cwe3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE4CONTENT cwe4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE5CONTENT cwe5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE6CONTENT cwe6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE7CONTENT cwe7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE8CONTENT cwe8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CWE9CONTENT cwe9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CWE.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CWE1CONTENT getCWE1()
meth public javasoft.sqe.tests.annotations.annotz002.CWE2CONTENT getCWE2()
meth public javasoft.sqe.tests.annotations.annotz002.CWE3CONTENT getCWE3()
meth public javasoft.sqe.tests.annotations.annotz002.CWE4CONTENT getCWE4()
meth public javasoft.sqe.tests.annotations.annotz002.CWE5CONTENT getCWE5()
meth public javasoft.sqe.tests.annotations.annotz002.CWE6CONTENT getCWE6()
meth public javasoft.sqe.tests.annotations.annotz002.CWE7CONTENT getCWE7()
meth public javasoft.sqe.tests.annotations.annotz002.CWE8CONTENT getCWE8()
meth public javasoft.sqe.tests.annotations.annotz002.CWE9CONTENT getCWE9()
meth public void setCWE1(javasoft.sqe.tests.annotations.annotz002.CWE1CONTENT)
meth public void setCWE2(javasoft.sqe.tests.annotations.annotz002.CWE2CONTENT)
meth public void setCWE3(javasoft.sqe.tests.annotations.annotz002.CWE3CONTENT)
meth public void setCWE4(javasoft.sqe.tests.annotations.annotz002.CWE4CONTENT)
meth public void setCWE5(javasoft.sqe.tests.annotations.annotz002.CWE5CONTENT)
meth public void setCWE6(javasoft.sqe.tests.annotations.annotz002.CWE6CONTENT)
meth public void setCWE7(javasoft.sqe.tests.annotations.annotz002.CWE7CONTENT)
meth public void setCWE8(javasoft.sqe.tests.annotations.annotz002.CWE8CONTENT)
meth public void setCWE9(javasoft.sqe.tests.annotations.annotz002.CWE9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CWE9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CWE.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CWE9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX", java.lang.String namespace="##default", java.lang.String[] propOrder=["cx1", "cx2", "cx3", "cx4", "cx5", "cx6", "cx7", "cx8"])
cons public CX()
fld protected javasoft.sqe.tests.annotations.annotz002.CX1CONTENT cx1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX2CONTENT cx2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX3CONTENT cx3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX4CONTENT cx4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX5CONTENT cx5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX6CONTENT cx6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX7CONTENT cx7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.CX8CONTENT cx8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="CX.8", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.CX1CONTENT getCX1()
meth public javasoft.sqe.tests.annotations.annotz002.CX2CONTENT getCX2()
meth public javasoft.sqe.tests.annotations.annotz002.CX3CONTENT getCX3()
meth public javasoft.sqe.tests.annotations.annotz002.CX4CONTENT getCX4()
meth public javasoft.sqe.tests.annotations.annotz002.CX5CONTENT getCX5()
meth public javasoft.sqe.tests.annotations.annotz002.CX6CONTENT getCX6()
meth public javasoft.sqe.tests.annotations.annotz002.CX7CONTENT getCX7()
meth public javasoft.sqe.tests.annotations.annotz002.CX8CONTENT getCX8()
meth public void setCX1(javasoft.sqe.tests.annotations.annotz002.CX1CONTENT)
meth public void setCX2(javasoft.sqe.tests.annotations.annotz002.CX2CONTENT)
meth public void setCX3(javasoft.sqe.tests.annotations.annotz002.CX3CONTENT)
meth public void setCX4(javasoft.sqe.tests.annotations.annotz002.CX4CONTENT)
meth public void setCX5(javasoft.sqe.tests.annotations.annotz002.CX5CONTENT)
meth public void setCX6(javasoft.sqe.tests.annotations.annotz002.CX6CONTENT)
meth public void setCX7(javasoft.sqe.tests.annotations.annotz002.CX7CONTENT)
meth public void setCX8(javasoft.sqe.tests.annotations.annotz002.CX8CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CX4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.CX5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public CX6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.CX7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.CX8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CX.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public CX8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DDI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DDI", java.lang.String namespace="##default", java.lang.String[] propOrder=["ddi1", "ddi2", "ddi3"])
cons public DDI()
fld protected javasoft.sqe.tests.annotations.annotz002.DDI1CONTENT ddi1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DDI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DDI2CONTENT ddi2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DDI.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DDI3CONTENT ddi3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DDI.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DDI1CONTENT getDDI1()
meth public javasoft.sqe.tests.annotations.annotz002.DDI2CONTENT getDDI2()
meth public javasoft.sqe.tests.annotations.annotz002.DDI3CONTENT getDDI3()
meth public void setDDI1(javasoft.sqe.tests.annotations.annotz002.DDI1CONTENT)
meth public void setDDI2(javasoft.sqe.tests.annotations.annotz002.DDI2CONTENT)
meth public void setDDI3(javasoft.sqe.tests.annotations.annotz002.DDI3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DDI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DDI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DDI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DDI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DDI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DDI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DDI3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DDI.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DDI3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DIN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DIN", java.lang.String namespace="##default", java.lang.String[] propOrder=["din1", "din2"])
cons public DIN()
fld protected javasoft.sqe.tests.annotations.annotz002.DIN1CONTENT din1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DIN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DIN2CONTENT din2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DIN.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DIN1CONTENT getDIN1()
meth public javasoft.sqe.tests.annotations.annotz002.DIN2CONTENT getDIN2()
meth public void setDIN1(javasoft.sqe.tests.annotations.annotz002.DIN1CONTENT)
meth public void setDIN2(javasoft.sqe.tests.annotations.annotz002.DIN2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DIN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DIN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DIN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.DIN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DIN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DIN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.DLD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLD", java.lang.String namespace="##default", java.lang.String[] propOrder=["dld1", "dld2"])
cons public DLD()
fld protected javasoft.sqe.tests.annotations.annotz002.DLD1CONTENT dld1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLD2CONTENT dld2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLD.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DLD1CONTENT getDLD1()
meth public javasoft.sqe.tests.annotations.annotz002.DLD2CONTENT getDLD2()
meth public void setDLD1(javasoft.sqe.tests.annotations.annotz002.DLD1CONTENT)
meth public void setDLD2(javasoft.sqe.tests.annotations.annotz002.DLD2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DLD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.DLN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLN", java.lang.String namespace="##default", java.lang.String[] propOrder=["dln1", "dln2", "dln3"])
cons public DLN()
fld protected javasoft.sqe.tests.annotations.annotz002.DLN1CONTENT dln1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLN2CONTENT dln2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLN3CONTENT dln3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLN.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DLN1CONTENT getDLN1()
meth public javasoft.sqe.tests.annotations.annotz002.DLN2CONTENT getDLN2()
meth public javasoft.sqe.tests.annotations.annotz002.DLN3CONTENT getDLN3()
meth public void setDLN1(javasoft.sqe.tests.annotations.annotz002.DLN1CONTENT)
meth public void setDLN2(javasoft.sqe.tests.annotations.annotz002.DLN2CONTENT)
meth public void setDLN3(javasoft.sqe.tests.annotations.annotz002.DLN3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLT", java.lang.String namespace="##default", java.lang.String[] propOrder=["dlt1", "dlt2", "dlt3", "dlt4"])
cons public DLT()
fld protected javasoft.sqe.tests.annotations.annotz002.DLT1CONTENT dlt1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLT.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLT2CONTENT dlt2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLT.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLT3CONTENT dlt3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLT.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DLT4CONTENT dlt4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DLT.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DLT1CONTENT getDLT1()
meth public javasoft.sqe.tests.annotations.annotz002.DLT2CONTENT getDLT2()
meth public javasoft.sqe.tests.annotations.annotz002.DLT3CONTENT getDLT3()
meth public javasoft.sqe.tests.annotations.annotz002.DLT4CONTENT getDLT4()
meth public void setDLT1(javasoft.sqe.tests.annotations.annotz002.DLT1CONTENT)
meth public void setDLT2(javasoft.sqe.tests.annotations.annotz002.DLT2CONTENT)
meth public void setDLT3(javasoft.sqe.tests.annotations.annotz002.DLT3CONTENT)
meth public void setDLT4(javasoft.sqe.tests.annotations.annotz002.DLT4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLT1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLT.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DLT1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.NR

CLSS public javasoft.sqe.tests.annotations.annotz002.DLT2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLT.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLT2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLT3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLT.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLT3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DLT4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DLT.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DLT4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DR
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.PPN18CONTENT, class javasoft.sqe.tests.annotations.annotz002.XCN17CONTENT, class javasoft.sqe.tests.annotations.annotz002.XAD12CONTENT, class javasoft.sqe.tests.annotations.annotz002.XPN10CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DR", java.lang.String namespace="##default", java.lang.String[] propOrder=["dr1", "dr2"])
cons public DR()
fld protected javasoft.sqe.tests.annotations.annotz002.DR1CONTENT dr1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DR.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DR2CONTENT dr2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DR.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DR1CONTENT getDR1()
meth public javasoft.sqe.tests.annotations.annotz002.DR2CONTENT getDR2()
meth public void setDR1(javasoft.sqe.tests.annotations.annotz002.DR1CONTENT)
meth public void setDR2(javasoft.sqe.tests.annotations.annotz002.DR2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DR1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DR.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DR1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.DR2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DR.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public DR2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.DTN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DTN", java.lang.String namespace="##default", java.lang.String[] propOrder=["dtn1", "dtn2"])
cons public DTN()
fld protected javasoft.sqe.tests.annotations.annotz002.DTN1CONTENT dtn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DTN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.DTN2CONTENT dtn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="DTN.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.DTN1CONTENT getDTN1()
meth public javasoft.sqe.tests.annotations.annotz002.DTN2CONTENT getDTN2()
meth public void setDTN1(javasoft.sqe.tests.annotations.annotz002.DTN1CONTENT)
meth public void setDTN2(javasoft.sqe.tests.annotations.annotz002.DTN2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DTN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DTN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DTN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.DTN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DTN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public DTN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ED
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED", java.lang.String namespace="##default", java.lang.String[] propOrder=["ed1", "ed2", "ed3", "ed4", "ed5"])
cons public ED()
fld protected javasoft.sqe.tests.annotations.annotz002.ED1CONTENT ed1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ED.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ED2CONTENT ed2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ED.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ED3CONTENT ed3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ED.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ED4CONTENT ed4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ED.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ED5CONTENT ed5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ED.5", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.ED1CONTENT getED1()
meth public javasoft.sqe.tests.annotations.annotz002.ED2CONTENT getED2()
meth public javasoft.sqe.tests.annotations.annotz002.ED3CONTENT getED3()
meth public javasoft.sqe.tests.annotations.annotz002.ED4CONTENT getED4()
meth public javasoft.sqe.tests.annotations.annotz002.ED5CONTENT getED5()
meth public void setED1(javasoft.sqe.tests.annotations.annotz002.ED1CONTENT)
meth public void setED2(javasoft.sqe.tests.annotations.annotz002.ED2CONTENT)
meth public void setED3(javasoft.sqe.tests.annotations.annotz002.ED3CONTENT)
meth public void setED4(javasoft.sqe.tests.annotations.annotz002.ED4CONTENT)
meth public void setED5(javasoft.sqe.tests.annotations.annotz002.ED5CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ED1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public ED1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.ED2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ED2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ED3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ED3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ED4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ED4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ED5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ED.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ED5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.PIP5CONTENT, class javasoft.sqe.tests.annotations.annotz002.EIP1CONTENT, class javasoft.sqe.tests.annotations.annotz002.EIP2CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EI", java.lang.String namespace="##default", java.lang.String[] propOrder=["ei1", "ei2", "ei3", "ei4"])
cons public EI()
fld protected javasoft.sqe.tests.annotations.annotz002.EI1CONTENT ei1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.EI2CONTENT ei2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EI.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.EI3CONTENT ei3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EI.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.EI4CONTENT ei4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EI.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.EI1CONTENT getEI1()
meth public javasoft.sqe.tests.annotations.annotz002.EI2CONTENT getEI2()
meth public javasoft.sqe.tests.annotations.annotz002.EI3CONTENT getEI3()
meth public javasoft.sqe.tests.annotations.annotz002.EI4CONTENT getEI4()
meth public void setEI1(javasoft.sqe.tests.annotations.annotz002.EI1CONTENT)
meth public void setEI2(javasoft.sqe.tests.annotations.annotz002.EI2CONTENT)
meth public void setEI3(javasoft.sqe.tests.annotations.annotz002.EI3CONTENT)
meth public void setEI4(javasoft.sqe.tests.annotations.annotz002.EI4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public EI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public EI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EI3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EI.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public EI3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EI4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EI.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public EI4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EIP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EIP", java.lang.String namespace="##default", java.lang.String[] propOrder=["eip1", "eip2"])
cons public EIP()
fld protected javasoft.sqe.tests.annotations.annotz002.EIP1CONTENT eip1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EIP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.EIP2CONTENT eip2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="EIP.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.EIP1CONTENT getEIP1()
meth public javasoft.sqe.tests.annotations.annotz002.EIP2CONTENT getEIP2()
meth public void setEIP1(javasoft.sqe.tests.annotations.annotz002.EIP1CONTENT)
meth public void setEIP2(javasoft.sqe.tests.annotations.annotz002.EIP2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.EIP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EIP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public EIP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.EI

CLSS public javasoft.sqe.tests.annotations.annotz002.EIP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EIP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public EIP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.EI

CLSS public javasoft.sqe.tests.annotations.annotz002.ELD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ELD", java.lang.String namespace="##default", java.lang.String[] propOrder=["eld1", "eld2", "eld3", "eld4"])
cons public ELD()
fld protected javasoft.sqe.tests.annotations.annotz002.ELD1CONTENT eld1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ELD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ELD2CONTENT eld2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ELD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ELD3CONTENT eld3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ELD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.ELD4CONTENT eld4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="ELD.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.ELD1CONTENT getELD1()
meth public javasoft.sqe.tests.annotations.annotz002.ELD2CONTENT getELD2()
meth public javasoft.sqe.tests.annotations.annotz002.ELD3CONTENT getELD3()
meth public javasoft.sqe.tests.annotations.annotz002.ELD4CONTENT getELD4()
meth public void setELD1(javasoft.sqe.tests.annotations.annotz002.ELD1CONTENT)
meth public void setELD2(javasoft.sqe.tests.annotations.annotz002.ELD2CONTENT)
meth public void setELD3(javasoft.sqe.tests.annotations.annotz002.ELD3CONTENT)
meth public void setELD4(javasoft.sqe.tests.annotations.annotz002.ELD4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ELD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ELD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ELD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ELD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ELD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ELD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ELD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ELD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public ELD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.ELD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ELD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public ELD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.FC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FC", java.lang.String namespace="##default", java.lang.String[] propOrder=["fc1", "fc2"])
cons public FC()
fld protected javasoft.sqe.tests.annotations.annotz002.FC1CONTENT fc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.FC2CONTENT fc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FC.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.FC1CONTENT getFC1()
meth public javasoft.sqe.tests.annotations.annotz002.FC2CONTENT getFC2()
meth public void setFC1(javasoft.sqe.tests.annotations.annotz002.FC1CONTENT)
meth public void setFC2(javasoft.sqe.tests.annotations.annotz002.FC2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public FC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.FN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CN2CONTENT, class javasoft.sqe.tests.annotations.annotz002.XCN2CONTENT, class javasoft.sqe.tests.annotations.annotz002.CNN2CONTENT, class javasoft.sqe.tests.annotations.annotz002.XPN1CONTENT, class javasoft.sqe.tests.annotations.annotz002.PN1CONTENT, class javasoft.sqe.tests.annotations.annotz002.PPN2CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN", java.lang.String namespace="##default", java.lang.String[] propOrder=["fn1", "fn2", "fn3", "fn4", "fn5"])
cons public FN()
fld protected javasoft.sqe.tests.annotations.annotz002.FN1CONTENT fn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.FN2CONTENT fn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.FN3CONTENT fn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.FN4CONTENT fn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.FN5CONTENT fn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="FN.5", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.FN1CONTENT getFN1()
meth public javasoft.sqe.tests.annotations.annotz002.FN2CONTENT getFN2()
meth public javasoft.sqe.tests.annotations.annotz002.FN3CONTENT getFN3()
meth public javasoft.sqe.tests.annotations.annotz002.FN4CONTENT getFN4()
meth public javasoft.sqe.tests.annotations.annotz002.FN5CONTENT getFN5()
meth public void setFN1(javasoft.sqe.tests.annotations.annotz002.FN1CONTENT)
meth public void setFN2(javasoft.sqe.tests.annotations.annotz002.FN2CONTENT)
meth public void setFN3(javasoft.sqe.tests.annotations.annotz002.FN3CONTENT)
meth public void setFN4(javasoft.sqe.tests.annotations.annotz002.FN4CONTENT)
meth public void setFN5(javasoft.sqe.tests.annotations.annotz002.FN5CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.FN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="FN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public FN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.HD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CN9CONTENT, class javasoft.sqe.tests.annotations.annotz002.PPN14CONTENT, class javasoft.sqe.tests.annotations.annotz002.XCN9CONTENT, class javasoft.sqe.tests.annotations.annotz002.XCN14CONTENT, class javasoft.sqe.tests.annotations.annotz002.LA24CONTENT, class javasoft.sqe.tests.annotations.annotz002.PL4CONTENT, class javasoft.sqe.tests.annotations.annotz002.RP2CONTENT, class javasoft.sqe.tests.annotations.annotz002.ED1CONTENT, class javasoft.sqe.tests.annotations.annotz002.LA14CONTENT, class javasoft.sqe.tests.annotations.annotz002.NDL7CONTENT, class javasoft.sqe.tests.annotations.annotz002.CX6CONTENT, class javasoft.sqe.tests.annotations.annotz002.CX4CONTENT, class javasoft.sqe.tests.annotations.annotz002.XON6CONTENT, class javasoft.sqe.tests.annotations.annotz002.XON8CONTENT, class javasoft.sqe.tests.annotations.annotz002.CK4CONTENT, class javasoft.sqe.tests.annotations.annotz002.PPN9CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="HD", java.lang.String namespace="##default", java.lang.String[] propOrder=["hd1", "hd2", "hd3"])
cons public HD()
fld protected javasoft.sqe.tests.annotations.annotz002.HD1CONTENT hd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="HD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.HD2CONTENT hd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="HD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.HD3CONTENT hd3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="HD.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.HD1CONTENT getHD1()
meth public javasoft.sqe.tests.annotations.annotz002.HD2CONTENT getHD2()
meth public javasoft.sqe.tests.annotations.annotz002.HD3CONTENT getHD3()
meth public void setHD1(javasoft.sqe.tests.annotations.annotz002.HD1CONTENT)
meth public void setHD2(javasoft.sqe.tests.annotations.annotz002.HD2CONTENT)
meth public void setHD3(javasoft.sqe.tests.annotations.annotz002.HD3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.HD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="HD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public HD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.HD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="HD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public HD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.HD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="HD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public HD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.JCC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="JCC", java.lang.String namespace="##default", java.lang.String[] propOrder=["jcc1", "jcc2"])
cons public JCC()
fld protected javasoft.sqe.tests.annotations.annotz002.JCC1CONTENT jcc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="JCC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.JCC2CONTENT jcc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="JCC.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.JCC1CONTENT getJCC1()
meth public javasoft.sqe.tests.annotations.annotz002.JCC2CONTENT getJCC2()
meth public void setJCC1(javasoft.sqe.tests.annotations.annotz002.JCC1CONTENT)
meth public void setJCC2(javasoft.sqe.tests.annotations.annotz002.JCC2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.JCC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="JCC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public JCC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.JCC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="JCC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public JCC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1", java.lang.String namespace="##default", java.lang.String[] propOrder=["la11", "la12", "la13", "la14", "la15", "la16", "la17", "la18", "la19"])
cons public LA1()
fld protected javasoft.sqe.tests.annotations.annotz002.LA11CONTENT la11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA12CONTENT la12
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA13CONTENT la13
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA14CONTENT la14
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA15CONTENT la15
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA16CONTENT la16
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA17CONTENT la17
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA18CONTENT la18
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA19CONTENT la19
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA1.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.LA11CONTENT getLA11()
meth public javasoft.sqe.tests.annotations.annotz002.LA12CONTENT getLA12()
meth public javasoft.sqe.tests.annotations.annotz002.LA13CONTENT getLA13()
meth public javasoft.sqe.tests.annotations.annotz002.LA14CONTENT getLA14()
meth public javasoft.sqe.tests.annotations.annotz002.LA15CONTENT getLA15()
meth public javasoft.sqe.tests.annotations.annotz002.LA16CONTENT getLA16()
meth public javasoft.sqe.tests.annotations.annotz002.LA17CONTENT getLA17()
meth public javasoft.sqe.tests.annotations.annotz002.LA18CONTENT getLA18()
meth public javasoft.sqe.tests.annotations.annotz002.LA19CONTENT getLA19()
meth public void setLA11(javasoft.sqe.tests.annotations.annotz002.LA11CONTENT)
meth public void setLA12(javasoft.sqe.tests.annotations.annotz002.LA12CONTENT)
meth public void setLA13(javasoft.sqe.tests.annotations.annotz002.LA13CONTENT)
meth public void setLA14(javasoft.sqe.tests.annotations.annotz002.LA14CONTENT)
meth public void setLA15(javasoft.sqe.tests.annotations.annotz002.LA15CONTENT)
meth public void setLA16(javasoft.sqe.tests.annotations.annotz002.LA16CONTENT)
meth public void setLA17(javasoft.sqe.tests.annotations.annotz002.LA17CONTENT)
meth public void setLA18(javasoft.sqe.tests.annotations.annotz002.LA18CONTENT)
meth public void setLA19(javasoft.sqe.tests.annotations.annotz002.LA19CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA12CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA12CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA13CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA13CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA14CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public LA14CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.LA15CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA15CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA16CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA16CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA17CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA17CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA18CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA18CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA19CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA1.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public LA19CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.AD

CLSS public javasoft.sqe.tests.annotations.annotz002.LA2
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2", java.lang.String namespace="##default", java.lang.String[] propOrder=["la21", "la22", "la23", "la24", "la25", "la26", "la27", "la28", "la29", "la210", "la211", "la212", "la213", "la214", "la215", "la216"])
cons public LA2()
fld protected javasoft.sqe.tests.annotations.annotz002.LA210CONTENT la210
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA211CONTENT la211
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA212CONTENT la212
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.12", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA213CONTENT la213
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.13", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA214CONTENT la214
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.14", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA215CONTENT la215
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.15", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA216CONTENT la216
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.16", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA21CONTENT la21
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA22CONTENT la22
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA23CONTENT la23
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA24CONTENT la24
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA25CONTENT la25
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA26CONTENT la26
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA27CONTENT la27
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA28CONTENT la28
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.LA29CONTENT la29
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="LA2.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.LA210CONTENT getLA210()
meth public javasoft.sqe.tests.annotations.annotz002.LA211CONTENT getLA211()
meth public javasoft.sqe.tests.annotations.annotz002.LA212CONTENT getLA212()
meth public javasoft.sqe.tests.annotations.annotz002.LA213CONTENT getLA213()
meth public javasoft.sqe.tests.annotations.annotz002.LA214CONTENT getLA214()
meth public javasoft.sqe.tests.annotations.annotz002.LA215CONTENT getLA215()
meth public javasoft.sqe.tests.annotations.annotz002.LA216CONTENT getLA216()
meth public javasoft.sqe.tests.annotations.annotz002.LA21CONTENT getLA21()
meth public javasoft.sqe.tests.annotations.annotz002.LA22CONTENT getLA22()
meth public javasoft.sqe.tests.annotations.annotz002.LA23CONTENT getLA23()
meth public javasoft.sqe.tests.annotations.annotz002.LA24CONTENT getLA24()
meth public javasoft.sqe.tests.annotations.annotz002.LA25CONTENT getLA25()
meth public javasoft.sqe.tests.annotations.annotz002.LA26CONTENT getLA26()
meth public javasoft.sqe.tests.annotations.annotz002.LA27CONTENT getLA27()
meth public javasoft.sqe.tests.annotations.annotz002.LA28CONTENT getLA28()
meth public javasoft.sqe.tests.annotations.annotz002.LA29CONTENT getLA29()
meth public void setLA21(javasoft.sqe.tests.annotations.annotz002.LA21CONTENT)
meth public void setLA210(javasoft.sqe.tests.annotations.annotz002.LA210CONTENT)
meth public void setLA211(javasoft.sqe.tests.annotations.annotz002.LA211CONTENT)
meth public void setLA212(javasoft.sqe.tests.annotations.annotz002.LA212CONTENT)
meth public void setLA213(javasoft.sqe.tests.annotations.annotz002.LA213CONTENT)
meth public void setLA214(javasoft.sqe.tests.annotations.annotz002.LA214CONTENT)
meth public void setLA215(javasoft.sqe.tests.annotations.annotz002.LA215CONTENT)
meth public void setLA216(javasoft.sqe.tests.annotations.annotz002.LA216CONTENT)
meth public void setLA22(javasoft.sqe.tests.annotations.annotz002.LA22CONTENT)
meth public void setLA23(javasoft.sqe.tests.annotations.annotz002.LA23CONTENT)
meth public void setLA24(javasoft.sqe.tests.annotations.annotz002.LA24CONTENT)
meth public void setLA25(javasoft.sqe.tests.annotations.annotz002.LA25CONTENT)
meth public void setLA26(javasoft.sqe.tests.annotations.annotz002.LA26CONTENT)
meth public void setLA27(javasoft.sqe.tests.annotations.annotz002.LA27CONTENT)
meth public void setLA28(javasoft.sqe.tests.annotations.annotz002.LA28CONTENT)
meth public void setLA29(javasoft.sqe.tests.annotations.annotz002.LA29CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA210CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA210CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA211CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA211CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA212CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.12.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA212CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA213CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.13.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA213CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA214CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.14.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA214CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA215CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.15.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA215CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA216CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.16.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA216CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA21CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA21CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA22CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA22CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA23CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA23CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA24CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public LA24CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.LA25CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA25CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA26CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA26CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA27CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA27CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA28CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA28CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.LA29CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="LA2.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public LA29CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA", java.lang.String namespace="##default", java.lang.String[] propOrder=["ma1", "ma2", "ma3", "ma4", "ma5", "ma6"])
cons public MA()
fld protected javasoft.sqe.tests.annotations.annotz002.MA1CONTENT ma1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MA2CONTENT ma2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MA3CONTENT ma3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MA4CONTENT ma4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MA5CONTENT ma5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MA6CONTENT ma6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MA.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.MA1CONTENT getMA1()
meth public javasoft.sqe.tests.annotations.annotz002.MA2CONTENT getMA2()
meth public javasoft.sqe.tests.annotations.annotz002.MA3CONTENT getMA3()
meth public javasoft.sqe.tests.annotations.annotz002.MA4CONTENT getMA4()
meth public javasoft.sqe.tests.annotations.annotz002.MA5CONTENT getMA5()
meth public javasoft.sqe.tests.annotations.annotz002.MA6CONTENT getMA6()
meth public void setMA1(javasoft.sqe.tests.annotations.annotz002.MA1CONTENT)
meth public void setMA2(javasoft.sqe.tests.annotations.annotz002.MA2CONTENT)
meth public void setMA3(javasoft.sqe.tests.annotations.annotz002.MA3CONTENT)
meth public void setMA4(javasoft.sqe.tests.annotations.annotz002.MA4CONTENT)
meth public void setMA5(javasoft.sqe.tests.annotations.annotz002.MA5CONTENT)
meth public void setMA6(javasoft.sqe.tests.annotations.annotz002.MA6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MA6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MA.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MA6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MO
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CP1CONTENT, class javasoft.sqe.tests.annotations.annotz002.MOC1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MO", java.lang.String namespace="##default", java.lang.String[] propOrder=["mo1", "mo2"])
cons public MO()
fld protected javasoft.sqe.tests.annotations.annotz002.MO1CONTENT mo1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MO.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MO2CONTENT mo2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MO.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.MO1CONTENT getMO1()
meth public javasoft.sqe.tests.annotations.annotz002.MO2CONTENT getMO2()
meth public void setMO1(javasoft.sqe.tests.annotations.annotz002.MO1CONTENT)
meth public void setMO2(javasoft.sqe.tests.annotations.annotz002.MO2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MO1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MO.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MO1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MO2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MO.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MO2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MOC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOC", java.lang.String namespace="##default", java.lang.String[] propOrder=["moc1", "moc2"])
cons public MOC()
fld protected javasoft.sqe.tests.annotations.annotz002.MOC1CONTENT moc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MOC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MOC2CONTENT moc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MOC.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.MOC1CONTENT getMOC1()
meth public javasoft.sqe.tests.annotations.annotz002.MOC2CONTENT getMOC2()
meth public void setMOC1(javasoft.sqe.tests.annotations.annotz002.MOC1CONTENT)
meth public void setMOC2(javasoft.sqe.tests.annotations.annotz002.MOC2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MOC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public MOC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.MO

CLSS public javasoft.sqe.tests.annotations.annotz002.MOC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public MOC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.MOP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOP", java.lang.String namespace="##default", java.lang.String[] propOrder=["mop1", "mop2"])
cons public MOP()
fld protected javasoft.sqe.tests.annotations.annotz002.MOP1CONTENT mop1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MOP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MOP2CONTENT mop2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MOP.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.MOP1CONTENT getMOP1()
meth public javasoft.sqe.tests.annotations.annotz002.MOP2CONTENT getMOP2()
meth public void setMOP1(javasoft.sqe.tests.annotations.annotz002.MOP1CONTENT)
meth public void setMOP2(javasoft.sqe.tests.annotations.annotz002.MOP2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MOP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MOP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MOP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MOP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MOP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MSG
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MSG", java.lang.String namespace="##default", java.lang.String[] propOrder=["msg1", "msg2", "msg3"])
cons public MSG()
fld protected javasoft.sqe.tests.annotations.annotz002.MSG1CONTENT msg1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MSG.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MSG2CONTENT msg2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MSG.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.MSG3CONTENT msg3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="MSG.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.MSG1CONTENT getMSG1()
meth public javasoft.sqe.tests.annotations.annotz002.MSG2CONTENT getMSG2()
meth public javasoft.sqe.tests.annotations.annotz002.MSG3CONTENT getMSG3()
meth public void setMSG1(javasoft.sqe.tests.annotations.annotz002.MSG1CONTENT)
meth public void setMSG2(javasoft.sqe.tests.annotations.annotz002.MSG2CONTENT)
meth public void setMSG3(javasoft.sqe.tests.annotations.annotz002.MSG3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MSG1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MSG.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MSG1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MSG2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MSG.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MSG2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.MSG3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MSG.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public MSG3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NA", java.lang.String namespace="##default", java.lang.String[] propOrder=["na1", "na2", "na3", "na4"])
cons public NA()
fld protected javasoft.sqe.tests.annotations.annotz002.NA1CONTENT na1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NA.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NA2CONTENT na2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NA.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NA3CONTENT na3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NA.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NA4CONTENT na4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NA.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.NA1CONTENT getNA1()
meth public javasoft.sqe.tests.annotations.annotz002.NA2CONTENT getNA2()
meth public javasoft.sqe.tests.annotations.annotz002.NA3CONTENT getNA3()
meth public javasoft.sqe.tests.annotations.annotz002.NA4CONTENT getNA4()
meth public void setNA1(javasoft.sqe.tests.annotations.annotz002.NA1CONTENT)
meth public void setNA2(javasoft.sqe.tests.annotations.annotz002.NA2CONTENT)
meth public void setNA3(javasoft.sqe.tests.annotations.annotz002.NA3CONTENT)
meth public void setNA4(javasoft.sqe.tests.annotations.annotz002.NA4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NA1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NA.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NA1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NA2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NA.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NA2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NA3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NA.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NA3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NA4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NA.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NA4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL", java.lang.String namespace="##default", java.lang.String[] propOrder=["ndl1", "ndl2", "ndl3", "ndl4", "ndl5", "ndl6", "ndl7", "ndl8", "ndl9", "ndl10", "ndl11"])
cons public NDL()
fld protected javasoft.sqe.tests.annotations.annotz002.NDL10CONTENT ndl10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL11CONTENT ndl11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL1CONTENT ndl1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL2CONTENT ndl2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL3CONTENT ndl3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL4CONTENT ndl4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL5CONTENT ndl5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL6CONTENT ndl6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL7CONTENT ndl7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL8CONTENT ndl8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NDL9CONTENT ndl9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NDL.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.NDL10CONTENT getNDL10()
meth public javasoft.sqe.tests.annotations.annotz002.NDL11CONTENT getNDL11()
meth public javasoft.sqe.tests.annotations.annotz002.NDL1CONTENT getNDL1()
meth public javasoft.sqe.tests.annotations.annotz002.NDL2CONTENT getNDL2()
meth public javasoft.sqe.tests.annotations.annotz002.NDL3CONTENT getNDL3()
meth public javasoft.sqe.tests.annotations.annotz002.NDL4CONTENT getNDL4()
meth public javasoft.sqe.tests.annotations.annotz002.NDL5CONTENT getNDL5()
meth public javasoft.sqe.tests.annotations.annotz002.NDL6CONTENT getNDL6()
meth public javasoft.sqe.tests.annotations.annotz002.NDL7CONTENT getNDL7()
meth public javasoft.sqe.tests.annotations.annotz002.NDL8CONTENT getNDL8()
meth public javasoft.sqe.tests.annotations.annotz002.NDL9CONTENT getNDL9()
meth public void setNDL1(javasoft.sqe.tests.annotations.annotz002.NDL1CONTENT)
meth public void setNDL10(javasoft.sqe.tests.annotations.annotz002.NDL10CONTENT)
meth public void setNDL11(javasoft.sqe.tests.annotations.annotz002.NDL11CONTENT)
meth public void setNDL2(javasoft.sqe.tests.annotations.annotz002.NDL2CONTENT)
meth public void setNDL3(javasoft.sqe.tests.annotations.annotz002.NDL3CONTENT)
meth public void setNDL4(javasoft.sqe.tests.annotations.annotz002.NDL4CONTENT)
meth public void setNDL5(javasoft.sqe.tests.annotations.annotz002.NDL5CONTENT)
meth public void setNDL6(javasoft.sqe.tests.annotations.annotz002.NDL6CONTENT)
meth public void setNDL7(javasoft.sqe.tests.annotations.annotz002.NDL7CONTENT)
meth public void setNDL8(javasoft.sqe.tests.annotations.annotz002.NDL8CONTENT)
meth public void setNDL9(javasoft.sqe.tests.annotations.annotz002.NDL9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public NDL1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CNN

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public NDL2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public NDL3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public NDL7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NDL9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NDL.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NDL9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NR
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.RFR4CONTENT, class javasoft.sqe.tests.annotations.annotz002.RFR1CONTENT, class javasoft.sqe.tests.annotations.annotz002.RFR3CONTENT, class javasoft.sqe.tests.annotations.annotz002.CD6CONTENT, class javasoft.sqe.tests.annotations.annotz002.DLT1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NR", java.lang.String namespace="##default", java.lang.String[] propOrder=["nr1", "nr2"])
cons public NR()
fld protected javasoft.sqe.tests.annotations.annotz002.NR1CONTENT nr1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NR.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.NR2CONTENT nr2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="NR.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.NR1CONTENT getNR1()
meth public javasoft.sqe.tests.annotations.annotz002.NR2CONTENT getNR2()
meth public void setNR1(javasoft.sqe.tests.annotations.annotz002.NR1CONTENT)
meth public void setNR2(javasoft.sqe.tests.annotations.annotz002.NR2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NR1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NR.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NR1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.NR2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NR.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public NR2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OCD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OCD", java.lang.String namespace="##default", java.lang.String[] propOrder=["ocd1", "ocd2"])
cons public OCD()
fld protected javasoft.sqe.tests.annotations.annotz002.OCD1CONTENT ocd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OCD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OCD2CONTENT ocd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OCD.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.OCD1CONTENT getOCD1()
meth public javasoft.sqe.tests.annotations.annotz002.OCD2CONTENT getOCD2()
meth public void setOCD1(javasoft.sqe.tests.annotations.annotz002.OCD1CONTENT)
meth public void setOCD2(javasoft.sqe.tests.annotations.annotz002.OCD2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OCD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OCD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OCD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OCD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OCD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OCD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.TQ10CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD", java.lang.String namespace="##default", java.lang.String[] propOrder=["osd1", "osd2", "osd3", "osd4", "osd5", "osd6", "osd7", "osd8", "osd9", "osd10", "osd11"])
cons public OSD()
fld protected javasoft.sqe.tests.annotations.annotz002.OSD10CONTENT osd10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD11CONTENT osd11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD1CONTENT osd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD2CONTENT osd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD3CONTENT osd3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD4CONTENT osd4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD5CONTENT osd5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD6CONTENT osd6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD7CONTENT osd7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD8CONTENT osd8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSD9CONTENT osd9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSD.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.OSD10CONTENT getOSD10()
meth public javasoft.sqe.tests.annotations.annotz002.OSD11CONTENT getOSD11()
meth public javasoft.sqe.tests.annotations.annotz002.OSD1CONTENT getOSD1()
meth public javasoft.sqe.tests.annotations.annotz002.OSD2CONTENT getOSD2()
meth public javasoft.sqe.tests.annotations.annotz002.OSD3CONTENT getOSD3()
meth public javasoft.sqe.tests.annotations.annotz002.OSD4CONTENT getOSD4()
meth public javasoft.sqe.tests.annotations.annotz002.OSD5CONTENT getOSD5()
meth public javasoft.sqe.tests.annotations.annotz002.OSD6CONTENT getOSD6()
meth public javasoft.sqe.tests.annotations.annotz002.OSD7CONTENT getOSD7()
meth public javasoft.sqe.tests.annotations.annotz002.OSD8CONTENT getOSD8()
meth public javasoft.sqe.tests.annotations.annotz002.OSD9CONTENT getOSD9()
meth public void setOSD1(javasoft.sqe.tests.annotations.annotz002.OSD1CONTENT)
meth public void setOSD10(javasoft.sqe.tests.annotations.annotz002.OSD10CONTENT)
meth public void setOSD11(javasoft.sqe.tests.annotations.annotz002.OSD11CONTENT)
meth public void setOSD2(javasoft.sqe.tests.annotations.annotz002.OSD2CONTENT)
meth public void setOSD3(javasoft.sqe.tests.annotations.annotz002.OSD3CONTENT)
meth public void setOSD4(javasoft.sqe.tests.annotations.annotz002.OSD4CONTENT)
meth public void setOSD5(javasoft.sqe.tests.annotations.annotz002.OSD5CONTENT)
meth public void setOSD6(javasoft.sqe.tests.annotations.annotz002.OSD6CONTENT)
meth public void setOSD7(javasoft.sqe.tests.annotations.annotz002.OSD7CONTENT)
meth public void setOSD8(javasoft.sqe.tests.annotations.annotz002.OSD8CONTENT)
meth public void setOSD9(javasoft.sqe.tests.annotations.annotz002.OSD9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSD9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSD.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSD9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSP", java.lang.String namespace="##default", java.lang.String[] propOrder=["osp1", "osp2", "osp3"])
cons public OSP()
fld protected javasoft.sqe.tests.annotations.annotz002.OSP1CONTENT osp1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSP2CONTENT osp2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSP.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.OSP3CONTENT osp3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="OSP.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.OSP1CONTENT getOSP1()
meth public javasoft.sqe.tests.annotations.annotz002.OSP2CONTENT getOSP2()
meth public javasoft.sqe.tests.annotations.annotz002.OSP3CONTENT getOSP3()
meth public void setOSP1(javasoft.sqe.tests.annotations.annotz002.OSP1CONTENT)
meth public void setOSP2(javasoft.sqe.tests.annotations.annotz002.OSP2CONTENT)
meth public void setOSP3(javasoft.sqe.tests.annotations.annotz002.OSP3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public OSP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.OSP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.OSP3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="OSP.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public OSP3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PCF
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PCF", java.lang.String namespace="##default", java.lang.String[] propOrder=["pcf1", "pcf2", "pcf3"])
cons public PCF()
fld protected javasoft.sqe.tests.annotations.annotz002.PCF1CONTENT pcf1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PCF.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PCF2CONTENT pcf2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PCF.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PCF3CONTENT pcf3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PCF.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PCF1CONTENT getPCF1()
meth public javasoft.sqe.tests.annotations.annotz002.PCF2CONTENT getPCF2()
meth public javasoft.sqe.tests.annotations.annotz002.PCF3CONTENT getPCF3()
meth public void setPCF1(javasoft.sqe.tests.annotations.annotz002.PCF1CONTENT)
meth public void setPCF2(javasoft.sqe.tests.annotations.annotz002.PCF2CONTENT)
meth public void setPCF3(javasoft.sqe.tests.annotations.annotz002.PCF3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PCF1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PCF.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PCF1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PCF2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PCF.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PCF2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PCF3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PCF.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PCF3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.PI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PI", java.lang.String namespace="##default", java.lang.String[] propOrder=["pi1", "pi2", "pi3"])
cons public PI()
fld protected javasoft.sqe.tests.annotations.annotz002.PI1CONTENT pi1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PI2CONTENT pi2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PI.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PI3CONTENT pi3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PI.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PI1CONTENT getPI1()
meth public javasoft.sqe.tests.annotations.annotz002.PI2CONTENT getPI2()
meth public javasoft.sqe.tests.annotations.annotz002.PI3CONTENT getPI3()
meth public void setPI1(javasoft.sqe.tests.annotations.annotz002.PI1CONTENT)
meth public void setPI2(javasoft.sqe.tests.annotations.annotz002.PI2CONTENT)
meth public void setPI3(javasoft.sqe.tests.annotations.annotz002.PI3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PI3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PI.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PI3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP", java.lang.String namespace="##default", java.lang.String[] propOrder=["pip1", "pip2", "pip3", "pip4", "pip5"])
cons public PIP()
fld protected javasoft.sqe.tests.annotations.annotz002.PIP1CONTENT pip1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PIP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PIP2CONTENT pip2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PIP.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PIP3CONTENT pip3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PIP.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PIP4CONTENT pip4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PIP.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PIP5CONTENT pip5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PIP.5", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PIP1CONTENT getPIP1()
meth public javasoft.sqe.tests.annotations.annotz002.PIP2CONTENT getPIP2()
meth public javasoft.sqe.tests.annotations.annotz002.PIP3CONTENT getPIP3()
meth public javasoft.sqe.tests.annotations.annotz002.PIP4CONTENT getPIP4()
meth public javasoft.sqe.tests.annotations.annotz002.PIP5CONTENT getPIP5()
meth public void setPIP1(javasoft.sqe.tests.annotations.annotz002.PIP1CONTENT)
meth public void setPIP2(javasoft.sqe.tests.annotations.annotz002.PIP2CONTENT)
meth public void setPIP3(javasoft.sqe.tests.annotations.annotz002.PIP3CONTENT)
meth public void setPIP4(javasoft.sqe.tests.annotations.annotz002.PIP4CONTENT)
meth public void setPIP5(javasoft.sqe.tests.annotations.annotz002.PIP5CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PIP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PIP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PIP3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PIP4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PIP5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PIP.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PIP5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.EI

CLSS public javasoft.sqe.tests.annotations.annotz002.PL
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL", java.lang.String namespace="##default", java.lang.String[] propOrder=["pl1", "pl2", "pl3", "pl4", "pl5", "pl6", "pl7", "pl8", "pl9"])
cons public PL()
fld protected javasoft.sqe.tests.annotations.annotz002.PL1CONTENT pl1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL2CONTENT pl2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL3CONTENT pl3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL4CONTENT pl4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL5CONTENT pl5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL6CONTENT pl6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL7CONTENT pl7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL8CONTENT pl8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PL9CONTENT pl9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PL.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PL1CONTENT getPL1()
meth public javasoft.sqe.tests.annotations.annotz002.PL2CONTENT getPL2()
meth public javasoft.sqe.tests.annotations.annotz002.PL3CONTENT getPL3()
meth public javasoft.sqe.tests.annotations.annotz002.PL4CONTENT getPL4()
meth public javasoft.sqe.tests.annotations.annotz002.PL5CONTENT getPL5()
meth public javasoft.sqe.tests.annotations.annotz002.PL6CONTENT getPL6()
meth public javasoft.sqe.tests.annotations.annotz002.PL7CONTENT getPL7()
meth public javasoft.sqe.tests.annotations.annotz002.PL8CONTENT getPL8()
meth public javasoft.sqe.tests.annotations.annotz002.PL9CONTENT getPL9()
meth public void setPL1(javasoft.sqe.tests.annotations.annotz002.PL1CONTENT)
meth public void setPL2(javasoft.sqe.tests.annotations.annotz002.PL2CONTENT)
meth public void setPL3(javasoft.sqe.tests.annotations.annotz002.PL3CONTENT)
meth public void setPL4(javasoft.sqe.tests.annotations.annotz002.PL4CONTENT)
meth public void setPL5(javasoft.sqe.tests.annotations.annotz002.PL5CONTENT)
meth public void setPL6(javasoft.sqe.tests.annotations.annotz002.PL6CONTENT)
meth public void setPL7(javasoft.sqe.tests.annotations.annotz002.PL7CONTENT)
meth public void setPL8(javasoft.sqe.tests.annotations.annotz002.PL8CONTENT)
meth public void setPL9(javasoft.sqe.tests.annotations.annotz002.PL9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PL4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.PL5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PL9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PL.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PL9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PLN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PLN", java.lang.String namespace="##default", java.lang.String[] propOrder=["pln1", "pln2", "pln3", "pln4"])
cons public PLN()
fld protected javasoft.sqe.tests.annotations.annotz002.PLN1CONTENT pln1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PLN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PLN2CONTENT pln2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PLN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PLN3CONTENT pln3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PLN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PLN4CONTENT pln4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PLN.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PLN1CONTENT getPLN1()
meth public javasoft.sqe.tests.annotations.annotz002.PLN2CONTENT getPLN2()
meth public javasoft.sqe.tests.annotations.annotz002.PLN3CONTENT getPLN3()
meth public javasoft.sqe.tests.annotations.annotz002.PLN4CONTENT getPLN4()
meth public void setPLN1(javasoft.sqe.tests.annotations.annotz002.PLN1CONTENT)
meth public void setPLN2(javasoft.sqe.tests.annotations.annotz002.PLN2CONTENT)
meth public void setPLN3(javasoft.sqe.tests.annotations.annotz002.PLN3CONTENT)
meth public void setPLN4(javasoft.sqe.tests.annotations.annotz002.PLN4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PLN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PLN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PLN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PLN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PLN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PLN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PLN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PLN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PLN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PLN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PLN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PLN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN", java.lang.String namespace="##default", java.lang.String[] propOrder=["pn1", "pn2", "pn3", "pn4", "pn5", "pn6"])
cons public PN()
fld protected javasoft.sqe.tests.annotations.annotz002.PN1CONTENT pn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PN2CONTENT pn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PN3CONTENT pn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PN4CONTENT pn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PN5CONTENT pn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PN6CONTENT pn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PN.6", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PN1CONTENT getPN1()
meth public javasoft.sqe.tests.annotations.annotz002.PN2CONTENT getPN2()
meth public javasoft.sqe.tests.annotations.annotz002.PN3CONTENT getPN3()
meth public javasoft.sqe.tests.annotations.annotz002.PN4CONTENT getPN4()
meth public javasoft.sqe.tests.annotations.annotz002.PN5CONTENT getPN5()
meth public javasoft.sqe.tests.annotations.annotz002.PN6CONTENT getPN6()
meth public void setPN1(javasoft.sqe.tests.annotations.annotz002.PN1CONTENT)
meth public void setPN2(javasoft.sqe.tests.annotations.annotz002.PN2CONTENT)
meth public void setPN3(javasoft.sqe.tests.annotations.annotz002.PN3CONTENT)
meth public void setPN4(javasoft.sqe.tests.annotations.annotz002.PN4CONTENT)
meth public void setPN5(javasoft.sqe.tests.annotations.annotz002.PN5CONTENT)
meth public void setPN6(javasoft.sqe.tests.annotations.annotz002.PN6CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.PN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN", java.lang.String namespace="##default", java.lang.String[] propOrder=["ppn1", "ppn2", "ppn3", "ppn4", "ppn5", "ppn6", "ppn7", "ppn8", "ppn9", "ppn10", "ppn11", "ppn12", "ppn13", "ppn14", "ppn15", "ppn16", "ppn17", "ppn18", "ppn19"])
cons public PPN()
fld protected javasoft.sqe.tests.annotations.annotz002.PPN10CONTENT ppn10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN11CONTENT ppn11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN12CONTENT ppn12
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.12", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN13CONTENT ppn13
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.13", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN14CONTENT ppn14
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.14", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN15CONTENT ppn15
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.15", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN16CONTENT ppn16
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.16", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN17CONTENT ppn17
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.17", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN18CONTENT ppn18
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.18", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN19CONTENT ppn19
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.19", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN1CONTENT ppn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN2CONTENT ppn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN3CONTENT ppn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN4CONTENT ppn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN5CONTENT ppn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN6CONTENT ppn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN7CONTENT ppn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN8CONTENT ppn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PPN9CONTENT ppn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PPN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PPN10CONTENT getPPN10()
meth public javasoft.sqe.tests.annotations.annotz002.PPN11CONTENT getPPN11()
meth public javasoft.sqe.tests.annotations.annotz002.PPN12CONTENT getPPN12()
meth public javasoft.sqe.tests.annotations.annotz002.PPN13CONTENT getPPN13()
meth public javasoft.sqe.tests.annotations.annotz002.PPN14CONTENT getPPN14()
meth public javasoft.sqe.tests.annotations.annotz002.PPN15CONTENT getPPN15()
meth public javasoft.sqe.tests.annotations.annotz002.PPN16CONTENT getPPN16()
meth public javasoft.sqe.tests.annotations.annotz002.PPN17CONTENT getPPN17()
meth public javasoft.sqe.tests.annotations.annotz002.PPN18CONTENT getPPN18()
meth public javasoft.sqe.tests.annotations.annotz002.PPN19CONTENT getPPN19()
meth public javasoft.sqe.tests.annotations.annotz002.PPN1CONTENT getPPN1()
meth public javasoft.sqe.tests.annotations.annotz002.PPN2CONTENT getPPN2()
meth public javasoft.sqe.tests.annotations.annotz002.PPN3CONTENT getPPN3()
meth public javasoft.sqe.tests.annotations.annotz002.PPN4CONTENT getPPN4()
meth public javasoft.sqe.tests.annotations.annotz002.PPN5CONTENT getPPN5()
meth public javasoft.sqe.tests.annotations.annotz002.PPN6CONTENT getPPN6()
meth public javasoft.sqe.tests.annotations.annotz002.PPN7CONTENT getPPN7()
meth public javasoft.sqe.tests.annotations.annotz002.PPN8CONTENT getPPN8()
meth public javasoft.sqe.tests.annotations.annotz002.PPN9CONTENT getPPN9()
meth public void setPPN1(javasoft.sqe.tests.annotations.annotz002.PPN1CONTENT)
meth public void setPPN10(javasoft.sqe.tests.annotations.annotz002.PPN10CONTENT)
meth public void setPPN11(javasoft.sqe.tests.annotations.annotz002.PPN11CONTENT)
meth public void setPPN12(javasoft.sqe.tests.annotations.annotz002.PPN12CONTENT)
meth public void setPPN13(javasoft.sqe.tests.annotations.annotz002.PPN13CONTENT)
meth public void setPPN14(javasoft.sqe.tests.annotations.annotz002.PPN14CONTENT)
meth public void setPPN15(javasoft.sqe.tests.annotations.annotz002.PPN15CONTENT)
meth public void setPPN16(javasoft.sqe.tests.annotations.annotz002.PPN16CONTENT)
meth public void setPPN17(javasoft.sqe.tests.annotations.annotz002.PPN17CONTENT)
meth public void setPPN18(javasoft.sqe.tests.annotations.annotz002.PPN18CONTENT)
meth public void setPPN19(javasoft.sqe.tests.annotations.annotz002.PPN19CONTENT)
meth public void setPPN2(javasoft.sqe.tests.annotations.annotz002.PPN2CONTENT)
meth public void setPPN3(javasoft.sqe.tests.annotations.annotz002.PPN3CONTENT)
meth public void setPPN4(javasoft.sqe.tests.annotations.annotz002.PPN4CONTENT)
meth public void setPPN5(javasoft.sqe.tests.annotations.annotz002.PPN5CONTENT)
meth public void setPPN6(javasoft.sqe.tests.annotations.annotz002.PPN6CONTENT)
meth public void setPPN7(javasoft.sqe.tests.annotations.annotz002.PPN7CONTENT)
meth public void setPPN8(javasoft.sqe.tests.annotations.annotz002.PPN8CONTENT)
meth public void setPPN9(javasoft.sqe.tests.annotations.annotz002.PPN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN12CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.12.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN12CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN13CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.13.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN13CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN14CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.14.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN14CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN15CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.15.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN15CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN16CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.16.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN16CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN17CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.17.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN17CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN18CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.18.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN18CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.DR

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN19CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.19.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN19CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PPN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PPN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PPN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PPN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.PRL
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PRL", java.lang.String namespace="##default", java.lang.String[] propOrder=["prl1", "prl2", "prl3"])
cons public PRL()
fld protected javasoft.sqe.tests.annotations.annotz002.PRL1CONTENT prl1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PRL.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PRL2CONTENT prl2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PRL.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PRL3CONTENT prl3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PRL.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PRL1CONTENT getPRL1()
meth public javasoft.sqe.tests.annotations.annotz002.PRL2CONTENT getPRL2()
meth public javasoft.sqe.tests.annotations.annotz002.PRL3CONTENT getPRL3()
meth public void setPRL1(javasoft.sqe.tests.annotations.annotz002.PRL1CONTENT)
meth public void setPRL2(javasoft.sqe.tests.annotations.annotz002.PRL2CONTENT)
meth public void setPRL3(javasoft.sqe.tests.annotations.annotz002.PRL3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PRL1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PRL.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public PRL1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.PRL2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PRL.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PRL2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PRL3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PRL.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PRL3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PT", java.lang.String namespace="##default", java.lang.String[] propOrder=["pt1", "pt2"])
cons public PT()
fld protected javasoft.sqe.tests.annotations.annotz002.PT1CONTENT pt1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PT.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PT2CONTENT pt2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PT.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PT1CONTENT getPT1()
meth public javasoft.sqe.tests.annotations.annotz002.PT2CONTENT getPT2()
meth public void setPT1(javasoft.sqe.tests.annotations.annotz002.PT1CONTENT)
meth public void setPT2(javasoft.sqe.tests.annotations.annotz002.PT2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PT1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PT.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PT1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PT2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PT.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PT2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PTA
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PTA", java.lang.String namespace="##default", java.lang.String[] propOrder=["pta1", "pta2", "pta3"])
cons public PTA()
fld protected javasoft.sqe.tests.annotations.annotz002.PTA1CONTENT pta1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PTA.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PTA2CONTENT pta2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PTA.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.PTA3CONTENT pta3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="PTA.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.PTA1CONTENT getPTA1()
meth public javasoft.sqe.tests.annotations.annotz002.PTA2CONTENT getPTA2()
meth public javasoft.sqe.tests.annotations.annotz002.PTA3CONTENT getPTA3()
meth public void setPTA1(javasoft.sqe.tests.annotations.annotz002.PTA1CONTENT)
meth public void setPTA2(javasoft.sqe.tests.annotations.annotz002.PTA2CONTENT)
meth public void setPTA3(javasoft.sqe.tests.annotations.annotz002.PTA3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PTA1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PTA.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PTA1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PTA2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PTA.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PTA2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.PTA3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PTA.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public PTA3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QIP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QIP", java.lang.String namespace="##default", java.lang.String[] propOrder=["qip1", "qip2"])
cons public QIP()
fld protected javasoft.sqe.tests.annotations.annotz002.QIP1CONTENT qip1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QIP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.QIP2CONTENT qip2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QIP.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.QIP1CONTENT getQIP1()
meth public javasoft.sqe.tests.annotations.annotz002.QIP2CONTENT getQIP2()
meth public void setQIP1(javasoft.sqe.tests.annotations.annotz002.QIP1CONTENT)
meth public void setQIP2(javasoft.sqe.tests.annotations.annotz002.QIP2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QIP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QIP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QIP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QIP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QIP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QIP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QSC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QSC", java.lang.String namespace="##default", java.lang.String[] propOrder=["qsc1", "qsc2", "qsc3", "qsc4"])
cons public QSC()
fld protected javasoft.sqe.tests.annotations.annotz002.QSC1CONTENT qsc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QSC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.QSC2CONTENT qsc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QSC.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.QSC3CONTENT qsc3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QSC.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.QSC4CONTENT qsc4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="QSC.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.QSC1CONTENT getQSC1()
meth public javasoft.sqe.tests.annotations.annotz002.QSC2CONTENT getQSC2()
meth public javasoft.sqe.tests.annotations.annotz002.QSC3CONTENT getQSC3()
meth public javasoft.sqe.tests.annotations.annotz002.QSC4CONTENT getQSC4()
meth public void setQSC1(javasoft.sqe.tests.annotations.annotz002.QSC1CONTENT)
meth public void setQSC2(javasoft.sqe.tests.annotations.annotz002.QSC2CONTENT)
meth public void setQSC3(javasoft.sqe.tests.annotations.annotz002.QSC3CONTENT)
meth public void setQSC4(javasoft.sqe.tests.annotations.annotz002.QSC4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QSC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QSC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QSC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QSC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QSC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QSC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QSC3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QSC.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QSC3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.QSC4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="QSC.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public QSC4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RCD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RCD", java.lang.String namespace="##default", java.lang.String[] propOrder=["rcd1", "rcd2", "rcd3"])
cons public RCD()
fld protected javasoft.sqe.tests.annotations.annotz002.RCD1CONTENT rcd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RCD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RCD2CONTENT rcd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RCD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RCD3CONTENT rcd3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RCD.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.RCD1CONTENT getRCD1()
meth public javasoft.sqe.tests.annotations.annotz002.RCD2CONTENT getRCD2()
meth public javasoft.sqe.tests.annotations.annotz002.RCD3CONTENT getRCD3()
meth public void setRCD1(javasoft.sqe.tests.annotations.annotz002.RCD1CONTENT)
meth public void setRCD2(javasoft.sqe.tests.annotations.annotz002.RCD2CONTENT)
meth public void setRCD3(javasoft.sqe.tests.annotations.annotz002.RCD3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RCD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RCD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RCD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RCD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RCD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RCD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RCD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RCD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RCD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR", java.lang.String namespace="##default", java.lang.String[] propOrder=["rfr1", "rfr2", "rfr3", "rfr4", "rfr5", "rfr6", "rfr7"])
cons public RFR()
fld protected javasoft.sqe.tests.annotations.annotz002.RFR1CONTENT rfr1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR2CONTENT rfr2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR3CONTENT rfr3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR4CONTENT rfr4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR5CONTENT rfr5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR6CONTENT rfr6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RFR7CONTENT rfr7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RFR.7", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.RFR1CONTENT getRFR1()
meth public javasoft.sqe.tests.annotations.annotz002.RFR2CONTENT getRFR2()
meth public javasoft.sqe.tests.annotations.annotz002.RFR3CONTENT getRFR3()
meth public javasoft.sqe.tests.annotations.annotz002.RFR4CONTENT getRFR4()
meth public javasoft.sqe.tests.annotations.annotz002.RFR5CONTENT getRFR5()
meth public javasoft.sqe.tests.annotations.annotz002.RFR6CONTENT getRFR6()
meth public javasoft.sqe.tests.annotations.annotz002.RFR7CONTENT getRFR7()
meth public void setRFR1(javasoft.sqe.tests.annotations.annotz002.RFR1CONTENT)
meth public void setRFR2(javasoft.sqe.tests.annotations.annotz002.RFR2CONTENT)
meth public void setRFR3(javasoft.sqe.tests.annotations.annotz002.RFR3CONTENT)
meth public void setRFR4(javasoft.sqe.tests.annotations.annotz002.RFR4CONTENT)
meth public void setRFR5(javasoft.sqe.tests.annotations.annotz002.RFR5CONTENT)
meth public void setRFR6(javasoft.sqe.tests.annotations.annotz002.RFR6CONTENT)
meth public void setRFR7(javasoft.sqe.tests.annotations.annotz002.RFR7CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public RFR1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.NR

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RFR2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public RFR3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.NR

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public RFR4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.NR

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RFR5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RFR6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RFR7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RFR.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RFR7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.TQ2CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RI", java.lang.String namespace="##default", java.lang.String[] propOrder=["ri1", "ri2"])
cons public RI()
fld protected javasoft.sqe.tests.annotations.annotz002.RI1CONTENT ri1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RI2CONTENT ri2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RI.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.RI1CONTENT getRI1()
meth public javasoft.sqe.tests.annotations.annotz002.RI2CONTENT getRI2()
meth public void setRI1(javasoft.sqe.tests.annotations.annotz002.RI1CONTENT)
meth public void setRI2(javasoft.sqe.tests.annotations.annotz002.RI2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RMC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RMC", java.lang.String namespace="##default", java.lang.String[] propOrder=["rmc1", "rmc2", "rmc3"])
cons public RMC()
fld protected javasoft.sqe.tests.annotations.annotz002.RMC1CONTENT rmc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RMC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RMC2CONTENT rmc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RMC.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RMC3CONTENT rmc3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RMC.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.RMC1CONTENT getRMC1()
meth public javasoft.sqe.tests.annotations.annotz002.RMC2CONTENT getRMC2()
meth public javasoft.sqe.tests.annotations.annotz002.RMC3CONTENT getRMC3()
meth public void setRMC1(javasoft.sqe.tests.annotations.annotz002.RMC1CONTENT)
meth public void setRMC2(javasoft.sqe.tests.annotations.annotz002.RMC2CONTENT)
meth public void setRMC3(javasoft.sqe.tests.annotations.annotz002.RMC3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RMC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RMC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RMC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RMC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RMC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RMC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RMC3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RMC.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RMC3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RP
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RP", java.lang.String namespace="##default", java.lang.String[] propOrder=["rp1", "rp2", "rp3", "rp4"])
cons public RP()
fld protected javasoft.sqe.tests.annotations.annotz002.RP1CONTENT rp1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RP.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RP2CONTENT rp2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RP.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RP3CONTENT rp3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RP.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.RP4CONTENT rp4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="RP.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.RP1CONTENT getRP1()
meth public javasoft.sqe.tests.annotations.annotz002.RP2CONTENT getRP2()
meth public javasoft.sqe.tests.annotations.annotz002.RP3CONTENT getRP3()
meth public javasoft.sqe.tests.annotations.annotz002.RP4CONTENT getRP4()
meth public void setRP1(javasoft.sqe.tests.annotations.annotz002.RP1CONTENT)
meth public void setRP2(javasoft.sqe.tests.annotations.annotz002.RP2CONTENT)
meth public void setRP3(javasoft.sqe.tests.annotations.annotz002.RP3CONTENT)
meth public void setRP4(javasoft.sqe.tests.annotations.annotz002.RP4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RP1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RP.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RP1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RP2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RP.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public RP2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.RP3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RP.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RP3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.RP4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="RP.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public RP4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.Record1
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="Record1", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public Record1()
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SAD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.XAD1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SAD", java.lang.String namespace="##default", java.lang.String[] propOrder=["sad1", "sad2", "sad3"])
cons public SAD()
fld protected javasoft.sqe.tests.annotations.annotz002.SAD1CONTENT sad1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SAD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SAD2CONTENT sad2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SAD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SAD3CONTENT sad3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SAD.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SAD1CONTENT getSAD1()
meth public javasoft.sqe.tests.annotations.annotz002.SAD2CONTENT getSAD2()
meth public javasoft.sqe.tests.annotations.annotz002.SAD3CONTENT getSAD3()
meth public void setSAD1(javasoft.sqe.tests.annotations.annotz002.SAD1CONTENT)
meth public void setSAD2(javasoft.sqe.tests.annotations.annotz002.SAD2CONTENT)
meth public void setSAD3(javasoft.sqe.tests.annotations.annotz002.SAD3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SAD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SAD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SAD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SAD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SAD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SAD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SAD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SAD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SAD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SCV
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SCV", java.lang.String namespace="##default", java.lang.String[] propOrder=["scv1", "scv2"])
cons public SCV()
fld protected javasoft.sqe.tests.annotations.annotz002.SCV1CONTENT scv1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SCV.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SCV2CONTENT scv2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SCV.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SCV1CONTENT getSCV1()
meth public javasoft.sqe.tests.annotations.annotz002.SCV2CONTENT getSCV2()
meth public void setSCV1(javasoft.sqe.tests.annotations.annotz002.SCV1CONTENT)
meth public void setSCV2(javasoft.sqe.tests.annotations.annotz002.SCV2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SCV1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SCV.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SCV1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SCV2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SCV.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SCV2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SN", java.lang.String namespace="##default", java.lang.String[] propOrder=["sn1", "sn2", "sn3", "sn4"])
cons public SN()
fld protected javasoft.sqe.tests.annotations.annotz002.SN1CONTENT sn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SN2CONTENT sn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SN3CONTENT sn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SN4CONTENT sn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SN.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SN1CONTENT getSN1()
meth public javasoft.sqe.tests.annotations.annotz002.SN2CONTENT getSN2()
meth public javasoft.sqe.tests.annotations.annotz002.SN3CONTENT getSN3()
meth public javasoft.sqe.tests.annotations.annotz002.SN4CONTENT getSN4()
meth public void setSN1(javasoft.sqe.tests.annotations.annotz002.SN1CONTENT)
meth public void setSN2(javasoft.sqe.tests.annotations.annotz002.SN2CONTENT)
meth public void setSN3(javasoft.sqe.tests.annotations.annotz002.SN3CONTENT)
meth public void setSN4(javasoft.sqe.tests.annotations.annotz002.SN4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPD", java.lang.String namespace="##default", java.lang.String[] propOrder=["spd1", "spd2", "spd3", "spd4"])
cons public SPD()
fld protected javasoft.sqe.tests.annotations.annotz002.SPD1CONTENT spd1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPD2CONTENT spd2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPD3CONTENT spd3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPD4CONTENT spd4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPD.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SPD1CONTENT getSPD1()
meth public javasoft.sqe.tests.annotations.annotz002.SPD2CONTENT getSPD2()
meth public javasoft.sqe.tests.annotations.annotz002.SPD3CONTENT getSPD3()
meth public javasoft.sqe.tests.annotations.annotz002.SPD4CONTENT getSPD4()
meth public void setSPD1(javasoft.sqe.tests.annotations.annotz002.SPD1CONTENT)
meth public void setSPD2(javasoft.sqe.tests.annotations.annotz002.SPD2CONTENT)
meth public void setSPD3(javasoft.sqe.tests.annotations.annotz002.SPD3CONTENT)
meth public void setSPD4(javasoft.sqe.tests.annotations.annotz002.SPD4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS", java.lang.String namespace="##default", java.lang.String[] propOrder=["sps1", "sps2", "sps3", "sps4", "sps5", "sps6", "sps7"])
cons public SPS()
fld protected javasoft.sqe.tests.annotations.annotz002.SPS1CONTENT sps1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS2CONTENT sps2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS3CONTENT sps3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS4CONTENT sps4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS5CONTENT sps5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS6CONTENT sps6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SPS7CONTENT sps7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SPS.7", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SPS1CONTENT getSPS1()
meth public javasoft.sqe.tests.annotations.annotz002.SPS2CONTENT getSPS2()
meth public javasoft.sqe.tests.annotations.annotz002.SPS3CONTENT getSPS3()
meth public javasoft.sqe.tests.annotations.annotz002.SPS4CONTENT getSPS4()
meth public javasoft.sqe.tests.annotations.annotz002.SPS5CONTENT getSPS5()
meth public javasoft.sqe.tests.annotations.annotz002.SPS6CONTENT getSPS6()
meth public javasoft.sqe.tests.annotations.annotz002.SPS7CONTENT getSPS7()
meth public void setSPS1(javasoft.sqe.tests.annotations.annotz002.SPS1CONTENT)
meth public void setSPS2(javasoft.sqe.tests.annotations.annotz002.SPS2CONTENT)
meth public void setSPS3(javasoft.sqe.tests.annotations.annotz002.SPS3CONTENT)
meth public void setSPS4(javasoft.sqe.tests.annotations.annotz002.SPS4CONTENT)
meth public void setSPS5(javasoft.sqe.tests.annotations.annotz002.SPS5CONTENT)
meth public void setSPS6(javasoft.sqe.tests.annotations.annotz002.SPS6CONTENT)
meth public void setSPS7(javasoft.sqe.tests.annotations.annotz002.SPS7CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public SPS1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPS2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SPS3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public SPS4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public SPS5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public SPS6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.SPS7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SPS.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public SPS7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.SRT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SRT", java.lang.String namespace="##default", java.lang.String[] propOrder=["srt1", "srt2"])
cons public SRT()
fld protected javasoft.sqe.tests.annotations.annotz002.SRT1CONTENT srt1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SRT.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.SRT2CONTENT srt2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="SRT.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.SRT1CONTENT getSRT1()
meth public javasoft.sqe.tests.annotations.annotz002.SRT2CONTENT getSRT2()
meth public void setSRT1(javasoft.sqe.tests.annotations.annotz002.SRT1CONTENT)
meth public void setSRT2(javasoft.sqe.tests.annotations.annotz002.SRT2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SRT1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SRT.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SRT1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.SRT2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="SRT.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public SRT2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ", java.lang.String namespace="##default", java.lang.String[] propOrder=["tq1", "tq2", "tq3", "tq4", "tq5", "tq6", "tq7", "tq8", "tq9", "tq10", "tq11", "tq12"])
cons public TQ()
fld protected javasoft.sqe.tests.annotations.annotz002.TQ10CONTENT tq10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ11CONTENT tq11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ12CONTENT tq12
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.12", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ1CONTENT tq1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ2CONTENT tq2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ3CONTENT tq3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ4CONTENT tq4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ5CONTENT tq5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ6CONTENT tq6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ7CONTENT tq7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ8CONTENT tq8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TQ9CONTENT tq9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TQ.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.TQ10CONTENT getTQ10()
meth public javasoft.sqe.tests.annotations.annotz002.TQ11CONTENT getTQ11()
meth public javasoft.sqe.tests.annotations.annotz002.TQ12CONTENT getTQ12()
meth public javasoft.sqe.tests.annotations.annotz002.TQ1CONTENT getTQ1()
meth public javasoft.sqe.tests.annotations.annotz002.TQ2CONTENT getTQ2()
meth public javasoft.sqe.tests.annotations.annotz002.TQ3CONTENT getTQ3()
meth public javasoft.sqe.tests.annotations.annotz002.TQ4CONTENT getTQ4()
meth public javasoft.sqe.tests.annotations.annotz002.TQ5CONTENT getTQ5()
meth public javasoft.sqe.tests.annotations.annotz002.TQ6CONTENT getTQ6()
meth public javasoft.sqe.tests.annotations.annotz002.TQ7CONTENT getTQ7()
meth public javasoft.sqe.tests.annotations.annotz002.TQ8CONTENT getTQ8()
meth public javasoft.sqe.tests.annotations.annotz002.TQ9CONTENT getTQ9()
meth public void setTQ1(javasoft.sqe.tests.annotations.annotz002.TQ1CONTENT)
meth public void setTQ10(javasoft.sqe.tests.annotations.annotz002.TQ10CONTENT)
meth public void setTQ11(javasoft.sqe.tests.annotations.annotz002.TQ11CONTENT)
meth public void setTQ12(javasoft.sqe.tests.annotations.annotz002.TQ12CONTENT)
meth public void setTQ2(javasoft.sqe.tests.annotations.annotz002.TQ2CONTENT)
meth public void setTQ3(javasoft.sqe.tests.annotations.annotz002.TQ3CONTENT)
meth public void setTQ4(javasoft.sqe.tests.annotations.annotz002.TQ4CONTENT)
meth public void setTQ5(javasoft.sqe.tests.annotations.annotz002.TQ5CONTENT)
meth public void setTQ6(javasoft.sqe.tests.annotations.annotz002.TQ6CONTENT)
meth public void setTQ7(javasoft.sqe.tests.annotations.annotz002.TQ7CONTENT)
meth public void setTQ8(javasoft.sqe.tests.annotations.annotz002.TQ8CONTENT)
meth public void setTQ9(javasoft.sqe.tests.annotations.annotz002.TQ9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.OSD

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ12CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.12.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ12CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CQ

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.RI

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public TQ5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.TS

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TQ9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TQ.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TQ9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TS
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.PPN15CONTENT, class javasoft.sqe.tests.annotations.annotz002.DLD2CONTENT, class javasoft.sqe.tests.annotations.annotz002.PCF3CONTENT, class javasoft.sqe.tests.annotations.annotz002.FC2CONTENT, class javasoft.sqe.tests.annotations.annotz002.DIN1CONTENT, class javasoft.sqe.tests.annotations.annotz002.CCD2CONTENT, class javasoft.sqe.tests.annotations.annotz002.DR1CONTENT, class javasoft.sqe.tests.annotations.annotz002.DR2CONTENT, class javasoft.sqe.tests.annotations.annotz002.NDL3CONTENT, class javasoft.sqe.tests.annotations.annotz002.NDL2CONTENT, class javasoft.sqe.tests.annotations.annotz002.TQ5CONTENT, class javasoft.sqe.tests.annotations.annotz002.TQ4CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TS", java.lang.String namespace="##default", java.lang.String[] propOrder=["ts1", "ts2"])
cons public TS()
fld protected javasoft.sqe.tests.annotations.annotz002.TS1CONTENT ts1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TS.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TS2CONTENT ts2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TS.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.TS1CONTENT getTS1()
meth public javasoft.sqe.tests.annotations.annotz002.TS2CONTENT getTS2()
meth public void setTS1(javasoft.sqe.tests.annotations.annotz002.TS1CONTENT)
meth public void setTS2(javasoft.sqe.tests.annotations.annotz002.TS2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TS1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TS.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TS1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TS2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TS.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TS2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TX_CHALLENGE", java.lang.String namespace="##default", java.lang.String[] propOrder=["txchallenge1", "txchallenge2"])
cons public TXCHALLENGE()
fld protected javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE1CONTENT txchallenge1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TX_CHALLENGE.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE2CONTENT txchallenge2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="TX_CHALLENGE.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE1CONTENT getTXCHALLENGE1()
meth public javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE2CONTENT getTXCHALLENGE2()
meth public void setTXCHALLENGE1(javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE1CONTENT)
meth public void setTXCHALLENGE2(javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TX_CHALLENGE.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TXCHALLENGE1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.TXCHALLENGE2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="TX_CHALLENGE.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public TXCHALLENGE2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.UVC
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="UVC", java.lang.String namespace="##default", java.lang.String[] propOrder=["uvc1", "uvc2"])
cons public UVC()
fld protected javasoft.sqe.tests.annotations.annotz002.UVC1CONTENT uvc1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="UVC.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.UVC2CONTENT uvc2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="UVC.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.UVC1CONTENT getUVC1()
meth public javasoft.sqe.tests.annotations.annotz002.UVC2CONTENT getUVC2()
meth public void setUVC1(javasoft.sqe.tests.annotations.annotz002.UVC1CONTENT)
meth public void setUVC2(javasoft.sqe.tests.annotations.annotz002.UVC2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.UVC1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="UVC.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public UVC1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.UVC2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="UVC.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public UVC2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VH
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VH", java.lang.String namespace="##default", java.lang.String[] propOrder=["vh1", "vh2", "vh3", "vh4"])
cons public VH()
fld protected javasoft.sqe.tests.annotations.annotz002.VH1CONTENT vh1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VH.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VH2CONTENT vh2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VH.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VH3CONTENT vh3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VH.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VH4CONTENT vh4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VH.4", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.VH1CONTENT getVH1()
meth public javasoft.sqe.tests.annotations.annotz002.VH2CONTENT getVH2()
meth public javasoft.sqe.tests.annotations.annotz002.VH3CONTENT getVH3()
meth public javasoft.sqe.tests.annotations.annotz002.VH4CONTENT getVH4()
meth public void setVH1(javasoft.sqe.tests.annotations.annotz002.VH1CONTENT)
meth public void setVH2(javasoft.sqe.tests.annotations.annotz002.VH2CONTENT)
meth public void setVH3(javasoft.sqe.tests.annotations.annotz002.VH3CONTENT)
meth public void setVH4(javasoft.sqe.tests.annotations.annotz002.VH4CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VH1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VH.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VH1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VH2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VH.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VH2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VH3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VH.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VH3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VH4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VH.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VH4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VID
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VID", java.lang.String namespace="##default", java.lang.String[] propOrder=["vid1", "vid2", "vid3"])
cons public VID()
fld protected javasoft.sqe.tests.annotations.annotz002.VID1CONTENT vid1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VID.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VID2CONTENT vid2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VID.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VID3CONTENT vid3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VID.3", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.VID1CONTENT getVID1()
meth public javasoft.sqe.tests.annotations.annotz002.VID2CONTENT getVID2()
meth public javasoft.sqe.tests.annotations.annotz002.VID3CONTENT getVID3()
meth public void setVID1(javasoft.sqe.tests.annotations.annotz002.VID1CONTENT)
meth public void setVID2(javasoft.sqe.tests.annotations.annotz002.VID2CONTENT)
meth public void setVID3(javasoft.sqe.tests.annotations.annotz002.VID3CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VID1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VID.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VID1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VID2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VID.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public VID2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.VID3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VID.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public VID3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.VR
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VR", java.lang.String namespace="##default", java.lang.String[] propOrder=["vr1", "vr2"])
cons public VR()
fld protected javasoft.sqe.tests.annotations.annotz002.VR1CONTENT vr1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VR.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.VR2CONTENT vr2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="VR.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.VR1CONTENT getVR1()
meth public javasoft.sqe.tests.annotations.annotz002.VR2CONTENT getVR2()
meth public void setVR1(javasoft.sqe.tests.annotations.annotz002.VR1CONTENT)
meth public void setVR2(javasoft.sqe.tests.annotations.annotz002.VR2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VR1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VR.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VR1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.VR2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="VR.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public VR2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.Varies
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="varies", java.lang.String namespace="##default", java.lang.String[] propOrder=["any"])
cons public Varies()
fld protected java.util.List<org.w3c.dom.Element> any
 anno 0 jakarta.xml.bind.annotation.XmlAnyElement(boolean lax=false, java.lang.Class<? extends jakarta.xml.bind.annotation.DomHandler> value=class jakarta.xml.bind.annotation.W3CDomHandler)
meth public java.util.List<org.w3c.dom.Element> getAny()
meth public java.util.Map<javax.xml.namespace.QName,java.lang.String> getOtherAttributes()
supr java.lang.Object
hfds otherAttributes

CLSS public javasoft.sqe.tests.annotations.annotz002.WVI
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CD1CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVI", java.lang.String namespace="##default", java.lang.String[] propOrder=["wvi1", "wvi2"])
cons public WVI()
fld protected javasoft.sqe.tests.annotations.annotz002.WVI1CONTENT wvi1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="WVI.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.WVI2CONTENT wvi2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="WVI.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.WVI1CONTENT getWVI1()
meth public javasoft.sqe.tests.annotations.annotz002.WVI2CONTENT getWVI2()
meth public void setWVI1(javasoft.sqe.tests.annotations.annotz002.WVI1CONTENT)
meth public void setWVI2(javasoft.sqe.tests.annotations.annotz002.WVI2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.WVI1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVI.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public WVI1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.WVI2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVI.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public WVI2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.WVS
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.annotations.annotz002.CD2CONTENT])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVS", java.lang.String namespace="##default", java.lang.String[] propOrder=["wvs1", "wvs2"])
cons public WVS()
fld protected javasoft.sqe.tests.annotations.annotz002.WVS1CONTENT wvs1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="WVS.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.WVS2CONTENT wvs2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="WVS.2", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.WVS1CONTENT getWVS1()
meth public javasoft.sqe.tests.annotations.annotz002.WVS2CONTENT getWVS2()
meth public void setWVS1(javasoft.sqe.tests.annotations.annotz002.WVS1CONTENT)
meth public void setWVS2(javasoft.sqe.tests.annotations.annotz002.WVS2CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.WVS1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVS.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public WVS1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.WVS2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="WVS.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public WVS2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD", java.lang.String namespace="##default", java.lang.String[] propOrder=["xad1", "xad2", "xad3", "xad4", "xad5", "xad6", "xad7", "xad8", "xad9", "xad10", "xad11", "xad12"])
cons public XAD()
fld protected javasoft.sqe.tests.annotations.annotz002.XAD10CONTENT xad10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD11CONTENT xad11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD12CONTENT xad12
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.12", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD1CONTENT xad1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD2CONTENT xad2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD3CONTENT xad3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD4CONTENT xad4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD5CONTENT xad5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD6CONTENT xad6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD7CONTENT xad7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD8CONTENT xad8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XAD9CONTENT xad9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XAD.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.XAD10CONTENT getXAD10()
meth public javasoft.sqe.tests.annotations.annotz002.XAD11CONTENT getXAD11()
meth public javasoft.sqe.tests.annotations.annotz002.XAD12CONTENT getXAD12()
meth public javasoft.sqe.tests.annotations.annotz002.XAD1CONTENT getXAD1()
meth public javasoft.sqe.tests.annotations.annotz002.XAD2CONTENT getXAD2()
meth public javasoft.sqe.tests.annotations.annotz002.XAD3CONTENT getXAD3()
meth public javasoft.sqe.tests.annotations.annotz002.XAD4CONTENT getXAD4()
meth public javasoft.sqe.tests.annotations.annotz002.XAD5CONTENT getXAD5()
meth public javasoft.sqe.tests.annotations.annotz002.XAD6CONTENT getXAD6()
meth public javasoft.sqe.tests.annotations.annotz002.XAD7CONTENT getXAD7()
meth public javasoft.sqe.tests.annotations.annotz002.XAD8CONTENT getXAD8()
meth public javasoft.sqe.tests.annotations.annotz002.XAD9CONTENT getXAD9()
meth public void setXAD1(javasoft.sqe.tests.annotations.annotz002.XAD1CONTENT)
meth public void setXAD10(javasoft.sqe.tests.annotations.annotz002.XAD10CONTENT)
meth public void setXAD11(javasoft.sqe.tests.annotations.annotz002.XAD11CONTENT)
meth public void setXAD12(javasoft.sqe.tests.annotations.annotz002.XAD12CONTENT)
meth public void setXAD2(javasoft.sqe.tests.annotations.annotz002.XAD2CONTENT)
meth public void setXAD3(javasoft.sqe.tests.annotations.annotz002.XAD3CONTENT)
meth public void setXAD4(javasoft.sqe.tests.annotations.annotz002.XAD4CONTENT)
meth public void setXAD5(javasoft.sqe.tests.annotations.annotz002.XAD5CONTENT)
meth public void setXAD6(javasoft.sqe.tests.annotations.annotz002.XAD6CONTENT)
meth public void setXAD7(javasoft.sqe.tests.annotations.annotz002.XAD7CONTENT)
meth public void setXAD8(javasoft.sqe.tests.annotations.annotz002.XAD8CONTENT)
meth public void setXAD9(javasoft.sqe.tests.annotations.annotz002.XAD9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD12CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.12.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XAD12CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.DR

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XAD1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.SAD

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XAD9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XAD.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XAD9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN", java.lang.String namespace="##default", java.lang.String[] propOrder=["xcn1", "xcn2", "xcn3", "xcn4", "xcn5", "xcn6", "xcn7", "xcn8", "xcn9", "xcn10", "xcn11", "xcn12", "xcn13", "xcn14", "xcn15", "xcn16", "xcn17", "xcn18"])
cons public XCN()
fld protected javasoft.sqe.tests.annotations.annotz002.XCN10CONTENT xcn10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN11CONTENT xcn11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN12CONTENT xcn12
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.12", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN13CONTENT xcn13
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.13", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN14CONTENT xcn14
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.14", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN15CONTENT xcn15
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.15", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN16CONTENT xcn16
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.16", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN17CONTENT xcn17
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.17", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN18CONTENT xcn18
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.18", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN1CONTENT xcn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN2CONTENT xcn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN3CONTENT xcn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN4CONTENT xcn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN5CONTENT xcn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN6CONTENT xcn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN7CONTENT xcn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN8CONTENT xcn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XCN9CONTENT xcn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XCN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.XCN10CONTENT getXCN10()
meth public javasoft.sqe.tests.annotations.annotz002.XCN11CONTENT getXCN11()
meth public javasoft.sqe.tests.annotations.annotz002.XCN12CONTENT getXCN12()
meth public javasoft.sqe.tests.annotations.annotz002.XCN13CONTENT getXCN13()
meth public javasoft.sqe.tests.annotations.annotz002.XCN14CONTENT getXCN14()
meth public javasoft.sqe.tests.annotations.annotz002.XCN15CONTENT getXCN15()
meth public javasoft.sqe.tests.annotations.annotz002.XCN16CONTENT getXCN16()
meth public javasoft.sqe.tests.annotations.annotz002.XCN17CONTENT getXCN17()
meth public javasoft.sqe.tests.annotations.annotz002.XCN18CONTENT getXCN18()
meth public javasoft.sqe.tests.annotations.annotz002.XCN1CONTENT getXCN1()
meth public javasoft.sqe.tests.annotations.annotz002.XCN2CONTENT getXCN2()
meth public javasoft.sqe.tests.annotations.annotz002.XCN3CONTENT getXCN3()
meth public javasoft.sqe.tests.annotations.annotz002.XCN4CONTENT getXCN4()
meth public javasoft.sqe.tests.annotations.annotz002.XCN5CONTENT getXCN5()
meth public javasoft.sqe.tests.annotations.annotz002.XCN6CONTENT getXCN6()
meth public javasoft.sqe.tests.annotations.annotz002.XCN7CONTENT getXCN7()
meth public javasoft.sqe.tests.annotations.annotz002.XCN8CONTENT getXCN8()
meth public javasoft.sqe.tests.annotations.annotz002.XCN9CONTENT getXCN9()
meth public void setXCN1(javasoft.sqe.tests.annotations.annotz002.XCN1CONTENT)
meth public void setXCN10(javasoft.sqe.tests.annotations.annotz002.XCN10CONTENT)
meth public void setXCN11(javasoft.sqe.tests.annotations.annotz002.XCN11CONTENT)
meth public void setXCN12(javasoft.sqe.tests.annotations.annotz002.XCN12CONTENT)
meth public void setXCN13(javasoft.sqe.tests.annotations.annotz002.XCN13CONTENT)
meth public void setXCN14(javasoft.sqe.tests.annotations.annotz002.XCN14CONTENT)
meth public void setXCN15(javasoft.sqe.tests.annotations.annotz002.XCN15CONTENT)
meth public void setXCN16(javasoft.sqe.tests.annotations.annotz002.XCN16CONTENT)
meth public void setXCN17(javasoft.sqe.tests.annotations.annotz002.XCN17CONTENT)
meth public void setXCN18(javasoft.sqe.tests.annotations.annotz002.XCN18CONTENT)
meth public void setXCN2(javasoft.sqe.tests.annotations.annotz002.XCN2CONTENT)
meth public void setXCN3(javasoft.sqe.tests.annotations.annotz002.XCN3CONTENT)
meth public void setXCN4(javasoft.sqe.tests.annotations.annotz002.XCN4CONTENT)
meth public void setXCN5(javasoft.sqe.tests.annotations.annotz002.XCN5CONTENT)
meth public void setXCN6(javasoft.sqe.tests.annotations.annotz002.XCN6CONTENT)
meth public void setXCN7(javasoft.sqe.tests.annotations.annotz002.XCN7CONTENT)
meth public void setXCN8(javasoft.sqe.tests.annotations.annotz002.XCN8CONTENT)
meth public void setXCN9(javasoft.sqe.tests.annotations.annotz002.XCN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN12CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.12.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN12CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN13CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.13.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN13CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN14CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.14.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XCN14CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN15CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.15.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN15CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN16CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.16.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XCN16CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN17CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.17.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XCN17CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.DR

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN18CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.18.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN18CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XCN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XCN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XCN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XCN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XCN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.XON
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON", java.lang.String namespace="##default", java.lang.String[] propOrder=["xon1", "xon2", "xon3", "xon4", "xon5", "xon6", "xon7", "xon8", "xon9"])
cons public XON()
fld protected javasoft.sqe.tests.annotations.annotz002.XON1CONTENT xon1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON2CONTENT xon2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON3CONTENT xon3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON4CONTENT xon4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON5CONTENT xon5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON6CONTENT xon6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON7CONTENT xon7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON8CONTENT xon8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XON9CONTENT xon9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XON.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.XON1CONTENT getXON1()
meth public javasoft.sqe.tests.annotations.annotz002.XON2CONTENT getXON2()
meth public javasoft.sqe.tests.annotations.annotz002.XON3CONTENT getXON3()
meth public javasoft.sqe.tests.annotations.annotz002.XON4CONTENT getXON4()
meth public javasoft.sqe.tests.annotations.annotz002.XON5CONTENT getXON5()
meth public javasoft.sqe.tests.annotations.annotz002.XON6CONTENT getXON6()
meth public javasoft.sqe.tests.annotations.annotz002.XON7CONTENT getXON7()
meth public javasoft.sqe.tests.annotations.annotz002.XON8CONTENT getXON8()
meth public javasoft.sqe.tests.annotations.annotz002.XON9CONTENT getXON9()
meth public void setXON1(javasoft.sqe.tests.annotations.annotz002.XON1CONTENT)
meth public void setXON2(javasoft.sqe.tests.annotations.annotz002.XON2CONTENT)
meth public void setXON3(javasoft.sqe.tests.annotations.annotz002.XON3CONTENT)
meth public void setXON4(javasoft.sqe.tests.annotations.annotz002.XON4CONTENT)
meth public void setXON5(javasoft.sqe.tests.annotations.annotz002.XON5CONTENT)
meth public void setXON6(javasoft.sqe.tests.annotations.annotz002.XON6CONTENT)
meth public void setXON7(javasoft.sqe.tests.annotations.annotz002.XON7CONTENT)
meth public void setXON8(javasoft.sqe.tests.annotations.annotz002.XON8CONTENT)
meth public void setXON9(javasoft.sqe.tests.annotations.annotz002.XON9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XON6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.XON7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XON8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XON8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.HD

CLSS public javasoft.sqe.tests.annotations.annotz002.XON9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XON.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XON9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN", java.lang.String namespace="##default", java.lang.String[] propOrder=["xpn1", "xpn2", "xpn3", "xpn4", "xpn5", "xpn6", "xpn7", "xpn8", "xpn9", "xpn10", "xpn11"])
cons public XPN()
fld protected javasoft.sqe.tests.annotations.annotz002.XPN10CONTENT xpn10
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.10", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN11CONTENT xpn11
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.11", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN1CONTENT xpn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN2CONTENT xpn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN3CONTENT xpn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN4CONTENT xpn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN5CONTENT xpn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN6CONTENT xpn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN7CONTENT xpn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN8CONTENT xpn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XPN9CONTENT xpn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XPN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.XPN10CONTENT getXPN10()
meth public javasoft.sqe.tests.annotations.annotz002.XPN11CONTENT getXPN11()
meth public javasoft.sqe.tests.annotations.annotz002.XPN1CONTENT getXPN1()
meth public javasoft.sqe.tests.annotations.annotz002.XPN2CONTENT getXPN2()
meth public javasoft.sqe.tests.annotations.annotz002.XPN3CONTENT getXPN3()
meth public javasoft.sqe.tests.annotations.annotz002.XPN4CONTENT getXPN4()
meth public javasoft.sqe.tests.annotations.annotz002.XPN5CONTENT getXPN5()
meth public javasoft.sqe.tests.annotations.annotz002.XPN6CONTENT getXPN6()
meth public javasoft.sqe.tests.annotations.annotz002.XPN7CONTENT getXPN7()
meth public javasoft.sqe.tests.annotations.annotz002.XPN8CONTENT getXPN8()
meth public javasoft.sqe.tests.annotations.annotz002.XPN9CONTENT getXPN9()
meth public void setXPN1(javasoft.sqe.tests.annotations.annotz002.XPN1CONTENT)
meth public void setXPN10(javasoft.sqe.tests.annotations.annotz002.XPN10CONTENT)
meth public void setXPN11(javasoft.sqe.tests.annotations.annotz002.XPN11CONTENT)
meth public void setXPN2(javasoft.sqe.tests.annotations.annotz002.XPN2CONTENT)
meth public void setXPN3(javasoft.sqe.tests.annotations.annotz002.XPN3CONTENT)
meth public void setXPN4(javasoft.sqe.tests.annotations.annotz002.XPN4CONTENT)
meth public void setXPN5(javasoft.sqe.tests.annotations.annotz002.XPN5CONTENT)
meth public void setXPN6(javasoft.sqe.tests.annotations.annotz002.XPN6CONTENT)
meth public void setXPN7(javasoft.sqe.tests.annotations.annotz002.XPN7CONTENT)
meth public void setXPN8(javasoft.sqe.tests.annotations.annotz002.XPN8CONTENT)
meth public void setXPN9(javasoft.sqe.tests.annotations.annotz002.XPN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN10CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.10.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XPN10CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.DR

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN11CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.11.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN11CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XPN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.FN

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XPN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XPN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XPN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
cons public XPN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
supr javasoft.sqe.tests.annotations.annotz002.CE

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN", java.lang.String namespace="##default", java.lang.String[] propOrder=["xtn1", "xtn2", "xtn3", "xtn4", "xtn5", "xtn6", "xtn7", "xtn8", "xtn9"])
cons public XTN()
fld protected javasoft.sqe.tests.annotations.annotz002.XTN1CONTENT xtn1
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.1", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN2CONTENT xtn2
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.2", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN3CONTENT xtn3
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.3", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN4CONTENT xtn4
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.4", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN5CONTENT xtn5
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.5", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN6CONTENT xtn6
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.6", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN7CONTENT xtn7
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.7", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN8CONTENT xtn8
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.8", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.annotations.annotz002.XTN9CONTENT xtn9
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="XTN.9", java.lang.String namespace="##default")
meth public javasoft.sqe.tests.annotations.annotz002.XTN1CONTENT getXTN1()
meth public javasoft.sqe.tests.annotations.annotz002.XTN2CONTENT getXTN2()
meth public javasoft.sqe.tests.annotations.annotz002.XTN3CONTENT getXTN3()
meth public javasoft.sqe.tests.annotations.annotz002.XTN4CONTENT getXTN4()
meth public javasoft.sqe.tests.annotations.annotz002.XTN5CONTENT getXTN5()
meth public javasoft.sqe.tests.annotations.annotz002.XTN6CONTENT getXTN6()
meth public javasoft.sqe.tests.annotations.annotz002.XTN7CONTENT getXTN7()
meth public javasoft.sqe.tests.annotations.annotz002.XTN8CONTENT getXTN8()
meth public javasoft.sqe.tests.annotations.annotz002.XTN9CONTENT getXTN9()
meth public void setXTN1(javasoft.sqe.tests.annotations.annotz002.XTN1CONTENT)
meth public void setXTN2(javasoft.sqe.tests.annotations.annotz002.XTN2CONTENT)
meth public void setXTN3(javasoft.sqe.tests.annotations.annotz002.XTN3CONTENT)
meth public void setXTN4(javasoft.sqe.tests.annotations.annotz002.XTN4CONTENT)
meth public void setXTN5(javasoft.sqe.tests.annotations.annotz002.XTN5CONTENT)
meth public void setXTN6(javasoft.sqe.tests.annotations.annotz002.XTN6CONTENT)
meth public void setXTN7(javasoft.sqe.tests.annotations.annotz002.XTN7CONTENT)
meth public void setXTN8(javasoft.sqe.tests.annotations.annotz002.XTN8CONTENT)
meth public void setXTN9(javasoft.sqe.tests.annotations.annotz002.XTN9CONTENT)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN1CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.1.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN1CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN2CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.2.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN2CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN3CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.3.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN3CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String table
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Table", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getTable()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setTable(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN4CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.4.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN4CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN5CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.5.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN5CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN6CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.6.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN6CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN7CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.7.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN7CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN8CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.8.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN8CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.annotations.annotz002.XTN9CONTENT
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="XTN.9.CONTENT", java.lang.String namespace="##default", java.lang.String[] propOrder=["value"])
cons public XTN9CONTENT()
fld protected java.lang.String longName
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="LongName", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="Type", java.lang.String namespace="##default")
fld protected java.lang.String value
 anno 0 jakarta.xml.bind.annotation.XmlValue()
meth public java.lang.String getLongName()
meth public java.lang.String getType()
meth public java.lang.String getValue()
meth public void setLongName(java.lang.String)
meth public void setType(java.lang.String)
meth public void setValue(java.lang.String)
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

