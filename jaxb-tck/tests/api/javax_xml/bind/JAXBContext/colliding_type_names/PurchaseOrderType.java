
/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
     
package javasoft.sqe.tests.api.jakarta.xml.bind.JAXBContext.colliding_type_names;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colliding_name")
@XmlRootElement
public class PurchaseOrderType {

    protected Address shipTo;
    protected Address billTo;
    protected String comment;
    protected Items items;
    @XmlAttribute
    protected XMLGregorianCalendar orderDate;

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address value) {
        this.shipTo = value;
    }
    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address value) {
        this.billTo = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String value) {
        this.comment = value;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items value) {
        this.items = value;
    }

    public XMLGregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(XMLGregorianCalendar value) {
        this.orderDate = value;
    }
}
