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

CLSS public javasoft.sqe.tests.additional.test76423.ClassType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ClassType", java.lang.String namespace="##default", java.lang.String[] propOrder=["events", "property"])
cons public ClassType()
fld protected java.lang.String inherits
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String name
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.util.List<javasoft.sqe.tests.additional.test76423.PropertyType> property
fld protected javasoft.sqe.tests.additional.test76423.EventsType events
meth public java.lang.String getInherits()
meth public java.lang.String getName()
meth public java.util.List<javasoft.sqe.tests.additional.test76423.PropertyType> getProperty()
meth public javasoft.sqe.tests.additional.test76423.EventsType getEvents()
meth public void setEvents(javasoft.sqe.tests.additional.test76423.EventsType)
meth public void setInherits(java.lang.String)
meth public void setName(java.lang.String)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test76423.EventType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EventType", java.lang.String namespace="##default", java.lang.String[] propOrder=["desc"])
cons public EventType()
fld protected java.lang.String desc
fld protected java.lang.String name
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.test76423.ScopeType scope
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getDesc()
meth public java.lang.String getName()
meth public javasoft.sqe.tests.additional.test76423.ScopeType getScope()
meth public void setDesc(java.lang.String)
meth public void setName(java.lang.String)
meth public void setScope(javasoft.sqe.tests.additional.test76423.ScopeType)
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test76423.EventsType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="EventsType", java.lang.String namespace="##default", java.lang.String[] propOrder=["event"])
cons public EventsType()
fld protected java.util.List<javasoft.sqe.tests.additional.test76423.EventType> event
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.additional.test76423.EventType> getEvent()
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test76423.JsmlDocumentType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="JsmlDocumentType", java.lang.String namespace="##default", java.lang.String[] propOrder=["clazz"])
cons public JsmlDocumentType()
fld protected java.util.List<javasoft.sqe.tests.additional.test76423.ClassType> clazz
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="class", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.additional.test76423.ClassType> getClazz()
supr java.lang.Object

CLSS public javasoft.sqe.tests.additional.test76423.PropertyType
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="PropertyType", java.lang.String namespace="##default", java.lang.String[] propOrder=["desc"])
cons public PropertyType()
fld protected java.lang.String desc
fld protected java.lang.String name
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String type
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javasoft.sqe.tests.additional.test76423.ScopeType scope
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=true, java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getDesc()
meth public java.lang.String getName()
meth public java.lang.String getType()
meth public javasoft.sqe.tests.additional.test76423.ScopeType getScope()
meth public void setDesc(java.lang.String)
meth public void setName(java.lang.String)
meth public void setScope(javasoft.sqe.tests.additional.test76423.ScopeType)
meth public void setType(java.lang.String)
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.additional.test76423.ScopeType
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ScopeType", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.additional.test76423.ScopeType CLASS
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="class")
fld public final static javasoft.sqe.tests.additional.test76423.ScopeType INSTANCE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="instance")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.additional.test76423.ScopeType fromValue(java.lang.String)
meth public static javasoft.sqe.tests.additional.test76423.ScopeType valueOf(java.lang.String)
meth public static javasoft.sqe.tests.additional.test76423.ScopeType[] values()
supr java.lang.Enum<javasoft.sqe.tests.additional.test76423.ScopeType>
hfds value

CLSS public final !enum javasoft.sqe.tests.additional.test76423.YesNoType
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="YesNoType", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.additional.test76423.YesNoType NO
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="no")
fld public final static javasoft.sqe.tests.additional.test76423.YesNoType YES
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="yes")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.additional.test76423.YesNoType fromValue(java.lang.String)
meth public static javasoft.sqe.tests.additional.test76423.YesNoType valueOf(java.lang.String)
meth public static javasoft.sqe.tests.additional.test76423.YesNoType[] values()
supr java.lang.Enum<javasoft.sqe.tests.additional.test76423.YesNoType>
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

