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

CLSS public javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.Root
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlRootElement(java.lang.String name="root", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="", java.lang.String namespace="##default", java.lang.String[] propOrder=["val"])
cons public Root()
fld protected java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S> val
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.util.List<javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S> getVal()
supr java.lang.Object

CLSS public final !enum javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S
 anno 0 jakarta.xml.bind.annotation.XmlEnum(java.lang.Class<?> value=class java.lang.String)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="S", java.lang.String namespace="##default", java.lang.String[] propOrder=[""])
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S AAAA
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="aaaa")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S BBB
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="bbb_")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S BBB_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="bbb0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S BBB_B
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="bbbB")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S CC_0_C
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="cc0c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S CC_C
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="cc_c")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S CC_CC
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="ccCc")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S DD
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="dd__")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S DD_00
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="dd00")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S DD_DD
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="ddDD")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S E_0_EE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="e0ee")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S E_EE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="e_ee")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S E_EEE
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="eEee")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S F_0_F_0
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="f0f0")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S F_F
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="f_f_")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S F_FF_F
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="fFfF")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S G
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="g___")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S G_000
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="g000")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S G_GGG
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="gGGG")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S H
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="H---")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S HHHH
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="Hhhh")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S H_111
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="H111")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S III_I
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="IiiI")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S I_11_I
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="I11I")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S I_I
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="I--I")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S JJ_JJ
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="JjJj")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S J_1_J_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="J1J1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S J_J
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="J-J-")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S KK_KK
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="KkKK")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S K_1_KK
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="K1KK")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S K_KK
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="K-KK")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S LL
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="LL--")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S LL_11
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="LL11")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S L_LLL
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="LLll")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S MM_1_M
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="MM1M")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S MM_M
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="MM-M")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S M_MM_M
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="MMmM")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S NNN
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="NNN-")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S NNN_1
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="NNN1")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S NN_NN
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="NNNn")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S OOOO
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S P_00_P
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="p00p")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S P_P
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="p__p")
fld public final static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S P_P_PP
 anno 0 jakarta.xml.bind.annotation.XmlEnumValue(java.lang.String value="pPPp")
meth public java.lang.String value()
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S fromValue(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S valueOf(java.lang.String)
meth public static javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S[] values()
supr java.lang.Enum<javasoft.sqe.tests.stype.st_facets.st_facets00604.st_facets00604.S>
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

