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

CLSS public final !enum javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="NISTSchema-SV-IV-atomic-NMTOKEN-enumeration-3-Type", java.lang.String namespace="NISTSchema-SV-IV-atomic-NMTOKEN-enumeration-3-NS", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type BROWSERS_DOM_BOTH_CHAIN_THE_RECOMMENDING_C
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="browsers:DOM:both.chain:the:recommending.C")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type CRITERIA_MUST_TAR
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="criteria:-must-tar")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type DISCOVER_OASIS_VERSIONS_HAS_COMPATIBILITY_EMBEDDED
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="discover.OASIS-versions.has.compatibility-embedded")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type GROUPS_IN
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="Groups_in_")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type INDUSTRY_ADVANCEMENT_PERMITTING_CONFORMANCE_AND_WILL_PARTICI
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="industry:Advancement.permitting_conformance.and_will-partici")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type MANUAL_THAT_TOOLS_STANDARD
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="manual.that.tools.standard")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type METHODS_PROFILES_ENSURE_MANIPULATE_B
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="methods.profiles.ensure_manipulate.b")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type NEEDED_AS_IS_FURTHERMORE_RETRIEVE_TO_MEANS_FIND
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="needed:as-is.Furthermore_retrieve-to.means.find")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type THAN_BUSINESS_EB_XML_OF_FOR_OF_ELECTRONIC_DIAGNOSTI
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="than:business:ebXML-of:for.of_electronic.diagnosti")
fld public final static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type THE_UNITED_DEVELOPMENT_AND_EACH_DISCO
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="the_United-development-and-each-disco")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type fromValue(java.lang.String)
meth public static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type valueOf(java.lang.String)
meth public static javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type[] values()
supr java.lang.Enum<javasoft.sqe.tests.atomic.nmtoken.schema_instance.nistschema_sv_iv_atomic_nmtoken_enumeration_3.NISTSchemaSVIVAtomicNMTOKENEnumeration3Type>
hfds value

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

