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

CLSS public javasoft.sqe.tests.datatypes.id_test64335.Apparel
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="Apparel", java.lang.String namespace="##default", java.lang.String[] propOrder=["size", "style"])
cons public Apparel()
fld protected java.lang.String size
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String style
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getSize()
meth public java.lang.String getStyle()
meth public void setSize(java.lang.String)
meth public void setStyle(java.lang.String)
supr javasoft.sqe.tests.datatypes.id_test64335.Product

CLSS public javasoft.sqe.tests.datatypes.id_test64335.Book
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="Book", java.lang.String namespace="##default", java.lang.String[] propOrder=["author", "publishDate"])
cons public Book()
fld protected java.lang.String author
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar publishDate
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="publish_date", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
meth public java.lang.String getAuthor()
meth public javax.xml.datatype.XMLGregorianCalendar getPublishDate()
meth public void setAuthor(java.lang.String)
meth public void setPublishDate(javax.xml.datatype.XMLGregorianCalendar)
supr javasoft.sqe.tests.datatypes.id_test64335.MediaItem

CLSS public javasoft.sqe.tests.datatypes.id_test64335.CD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="CD", java.lang.String namespace="##default", java.lang.String[] propOrder=["artist", "releaseDate"])
cons public CD()
fld protected java.lang.String artist
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar releaseDate
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="release_date", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
meth public java.lang.String getArtist()
meth public javax.xml.datatype.XMLGregorianCalendar getReleaseDate()
meth public void setArtist(java.lang.String)
meth public void setReleaseDate(javax.xml.datatype.XMLGregorianCalendar)
supr javasoft.sqe.tests.datatypes.id_test64335.MediaItem

CLSS public javasoft.sqe.tests.datatypes.id_test64335.DVD
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="DVD", java.lang.String namespace="##default", java.lang.String[] propOrder=["director", "releaseDate"])
cons public DVD()
fld protected java.lang.String director
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected javax.xml.datatype.XMLGregorianCalendar releaseDate
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="release_date", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="date", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
meth public java.lang.String getDirector()
meth public javax.xml.datatype.XMLGregorianCalendar getReleaseDate()
meth public void setDirector(java.lang.String)
meth public void setReleaseDate(javax.xml.datatype.XMLGregorianCalendar)
supr javasoft.sqe.tests.datatypes.id_test64335.MediaItem

CLSS public abstract javasoft.sqe.tests.datatypes.id_test64335.MediaItem
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.datatypes.id_test64335.Book, class javasoft.sqe.tests.datatypes.id_test64335.CD, class javasoft.sqe.tests.datatypes.id_test64335.DVD])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="MediaItem", java.lang.String namespace="##default", java.lang.String[] propOrder=["title", "category"])
cons public MediaItem()
fld protected java.lang.String category
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String title
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getCategory()
meth public java.lang.String getTitle()
meth public void setCategory(java.lang.String)
meth public void setTitle(java.lang.String)
supr javasoft.sqe.tests.datatypes.id_test64335.Product

CLSS public abstract javasoft.sqe.tests.datatypes.id_test64335.Product
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlSeeAlso(java.lang.Class[] value=[class javasoft.sqe.tests.datatypes.id_test64335.Apparel, class javasoft.sqe.tests.datatypes.id_test64335.MediaItem])
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="Product", java.lang.String namespace="##default", java.lang.String[] propOrder=["price", "description"])
cons public Product()
fld protected java.lang.String description
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
fld protected java.lang.String id
 anno 0 jakarta.xml.bind.annotation.XmlAttribute(boolean required=false, java.lang.String name="##default", java.lang.String namespace="##default")
 anno 0 jakarta.xml.bind.annotation.XmlID()
 anno 0 jakarta.xml.bind.annotation.XmlSchemaType(java.lang.Class type=class jakarta.xml.bind.annotation.XmlSchemaType$DEFAULT, java.lang.String name="ID", java.lang.String namespace="http://www.w3.org/2001/XMLSchema")
 anno 0 jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter(java.lang.Class type=class jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter$DEFAULT, java.lang.Class<? extends jakarta.xml.bind.annotation.adapters.XmlAdapter> value=class jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter)
fld protected java.math.BigDecimal price
 anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=true, java.lang.Class type=class jakarta.xml.bind.annotation.XmlElement$DEFAULT, java.lang.String defaultValue="\u0000", java.lang.String name="##default", java.lang.String namespace="##default")
meth public java.lang.String getDescription()
meth public java.lang.String getId()
meth public java.math.BigDecimal getPrice()
meth public void setDescription(java.lang.String)
meth public void setId(java.lang.String)
meth public void setPrice(java.math.BigDecimal)
supr java.lang.Object

CLSS public javasoft.sqe.tests.datatypes.id_test64335.ProductList
 anno 0 jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType value=FIELD)
 anno 0 jakarta.xml.bind.annotation.XmlType(java.lang.Class factoryClass=class jakarta.xml.bind.annotation.XmlType$DEFAULT, java.lang.String factoryMethod="", java.lang.String name="ProductList", java.lang.String namespace="##default", java.lang.String[] propOrder=["bookOrDvdOrClothing"])
cons public ProductList()
fld protected java.util.List<javasoft.sqe.tests.datatypes.id_test64335.Product> bookOrDvdOrClothing
 anno 0 jakarta.xml.bind.annotation.XmlElements(jakarta.xml.bind.annotation.XmlElement[] value=[anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.datatypes.id_test64335.Apparel, java.lang.String defaultValue="\u0000", java.lang.String name="clothing", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.datatypes.id_test64335.CD, java.lang.String defaultValue="\u0000", java.lang.String name="cd", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.datatypes.id_test64335.DVD, java.lang.String defaultValue="\u0000", java.lang.String name="dvd", java.lang.String namespace="##default"), anno 0 jakarta.xml.bind.annotation.XmlElement(boolean nillable=false, boolean required=false, java.lang.Class type=class javasoft.sqe.tests.datatypes.id_test64335.Book, java.lang.String defaultValue="\u0000", java.lang.String name="book", java.lang.String namespace="##default")])
meth public java.util.List<javasoft.sqe.tests.datatypes.id_test64335.Product> getBookOrDvdOrClothing()
supr java.lang.Object

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

