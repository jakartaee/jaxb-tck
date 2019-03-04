/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jaxb_tck.lib.persistence;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class for wrapping objects of classes that doesn't follow a JavaBeans
 * conventions.
 * 
 * @author Vladimir Sosnin
 * 
 */
public class ObjectsWrapper {

	static DatatypeFactory dtf;

	static DocumentBuilderFactory dbf;

	static DocumentBuilder db;

	static {
		try {
			dtf = DatatypeFactory.newInstance();
			dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			db = dbf.newDocumentBuilder();
		} catch (DatatypeConfigurationException e) {
		    System.err.println("ObjectsWrapper: Can't get instantiate DatatypeFactory" + e.getMessage());
		} catch (ParserConfigurationException e) {
            System.err.println("ObjectsWrapper: Can't get instantiate DocumentBuilder" + e.getMessage());
		}
	}

	/**
	 * Factory method for <code>org.w3c.dom.Element</code>
	 * 
	 * @return Element configured with provided values
	 */
	public static Element createElementNS(String namespaceURI, String qualifiedName, String textContent) {
		try {
			Element result;
			Document d = db.newDocument();
			result = d.createElementNS(namespaceURI, qualifiedName);
			result.setTextContent(textContent);
			return result;
		} catch (Exception e) {
            System.err.println("ObjectsWrapper: Can't create org.w3c.dom.Element : " + e.getMessage());
		}
		return null;
	}

	/**
	 * Factory method for <code>javax.xml.datatype.Duration</code>
	 * 
	 * @return Duration configured with provided string value
	 */
	public static Duration createDuration(String strRepresentation) {
		Duration result = dtf.newDuration(strRepresentation);
		return result;
	}

	/**
	 * Factory method for <code>javax.xml.datatype.XMLGregorianCalendar</code>
	 * 
	 * @return XMLGregorianCalendar configured with provided string value
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(
			String strRepresentation) {
		XMLGregorianCalendar result = dtf
				.newXMLGregorianCalendar(strRepresentation);
		return result;
	}
	
	public static void addList(Object instance, List l, PropertyDescriptor pd){
		try {
			Method getter = pd.getReadMethod();
			List list = (List) getter.invoke(instance, new Object []{ null});
			list.addAll(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
