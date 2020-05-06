/*
 * Copyright (c) 2006, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.bind.binder;

import javasoft.sqe.tests.bind.binder.Address;
import javasoft.sqe.tests.bind.binder.Items;
import javasoft.sqe.tests.bind.binder.List;
import javasoft.sqe.tests.bind.binder.PurchaseOrder;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the javasoft.sqe.tests.bind.binder package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PurchaseOrder_QNAME = new QName("jck-jaxb-test/Binder", "purchaseOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: javasoft.sqe.tests.bind.binder
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Address }
     *
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link PurchaseOrder }
     *
     */
    public PurchaseOrder createPurchaseOrder() {
        return new PurchaseOrder();
    }

    /**
     * Create an instance of {@link Items }
     *
     */
    public Items createItems() {
        return new Items();
    }

    /**
     * Create an instance of {@link List }
     *
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PurchaseOrder }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "jck-jaxb-test/Binder", name = "purchaseOrder")
    public JAXBElement<PurchaseOrder> createPurchaseOrder(PurchaseOrder value) {
        return new JAXBElement<PurchaseOrder>(_PurchaseOrder_QNAME, PurchaseOrder.class, null, value);
    }

}
