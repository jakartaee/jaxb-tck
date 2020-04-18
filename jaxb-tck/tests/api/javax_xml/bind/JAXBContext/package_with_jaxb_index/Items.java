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
package javasoft.sqe.tests.api.jakarta.xml.bind.JAXBContext.package_with_jaxb_index;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Items")
public class Items {

    @XmlElement(nillable = true)
    protected List<Item> item;

    protected List<Item> _getItem() {
        if (item == null) {
            item = new ArrayList<Item>();
        }
        return item;
    }

    public List<Item> getItem() {
        return this._getItem();
    }



    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Item {

        protected String productName;
        @XmlElement(type = Integer.class)
        protected int quantity;
        @XmlElement(name = "USPrice", type = Float.class)
        protected float usPrice;
        protected String comment;
        @XmlAttribute(required = true)
        protected String partNum;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String value) {
            this.productName = value;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int value) {
            this.quantity = value;
        }

        public float getUSPrice() {
            return usPrice;
        }
        public void setUSPrice(float value) {
            this.usPrice = value;
        }
        public String getComment() {
            return comment;
        }
        public void setComment(String value) {
            this.comment = value;
        }
        public String getPartNum() {
            return partNum;
        }
        public void setPartNum(String value) {
            this.partNum = value;
        }

    }
}
