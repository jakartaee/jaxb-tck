/*
 * Copyright (c) 2008, 2020 Oracle and/or its affiliates. All rights reserved.
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

import java.beans.BeanInfo;
import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.XMLEncoder;
import java.beans.PersistenceDelegate; 
import java.io.File;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;

import jakarta.xml.bind.JAXBElement;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

class TCKJaxbTreeSerializer implements JaxbTreeSerializer {

    /**
     * Standard XMLEncoder
     */
    protected XMLEncoder encoder;

    /**
     * Method for finding classes in certain package and then delegating
     * JavaBeans "persistence delegate" initialization to
     * <code>addPersistenceDelegate</code> method. Only plain file system
     * packages are supported
     * 
     * @param context -
     *            name of JAXB context
     * @param cl -
     *            ClassLader holding classes used in JAXB tree
     * @see #addPersistenceDelegate(String, ClassLoader)
     * @throws RuntimeException
     *             This case realized when classes are in JAR file
     * 
     * 
     */
    protected void initJaxbInfo(Object jaxbTree, ClassLoader cl) {

        initPredefinedDelegates();

        String packageName = jaxbTree.getClass().getPackage().getName();
        
        if (packageName.startsWith("java.") || packageName.startsWith("javax.")) {
            //"Internal" classes like jakarta.xml.bind.JAXBElement
            addPersistenceDelegate(jaxbTree, cl);
        } else {
        	assignPersistenceDelegate(packageName, cl);
        }
    }
    
    private void assignPersistenceDelegate(String packageName, ClassLoader cl) {
        if (packageName.startsWith("java.") || packageName.startsWith("javax.")) {
        	// avoid cases JAXBElement<QName> 
        	return;
        }
    	
        String packagePath = packageName.replace('.', '/');
        URL packageUrl = cl.getResource(packagePath);
        if (packageUrl == null) {
            packageUrl = ClassLoader.getSystemResource(packagePath);
        }
        if (packageUrl == null) {
            return;
        }
        
        try {
            File packFile = new File(packageUrl.toURI());
            if (packFile.isDirectory()) {
                String[] files = packFile.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return !name.endsWith("ObjectFactory.class") && name.endsWith(".class");
                    }
                });
                for (int i = 0; i < files.length; i++) {
                    addPersistenceDelegate(packageName + "." + files[i].substring(0, files[i].indexOf(".")), cl);
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    	
    }
    
    private void addPersistenceDelegate(Object jaxbTree, ClassLoader cl) {
    	Class<?> clazz  = jaxbTree.getClass();
        Class<?> superClass = clazz.getSuperclass();
    	String className = clazz.getName();
        String superClassName = superClass == null ? "" : superClass.getName();
        if (className.endsWith("JAXBElement") || superClassName.endsWith("JAXBElement")) {
        	Class<?> dt = ((JAXBElement<?>)jaxbTree).getDeclaredType();
        	assignPersistenceDelegate(dt.getPackage().getName(), cl);
        } 
        addPersistenceDelegate(className, cl);
    }

    private void addPersistenceDelegate(String className, ClassLoader cl) {
        try {
            Class<?> clazz = Class.forName(className, true, cl);
            Class<?> superClass = clazz.getSuperclass();
            String superClassName = superClass == null ? "" : superClass.getName();

            BeanInfo bi = Introspector.getBeanInfo(clazz);
            if (className.endsWith("JAXBElement") || superClassName.endsWith("JAXBElement")) {
            	
                bi.getBeanDescriptor().setValue("persistenceDelegate", new BeansPersistenceDelegate() {
                    @Override
                    protected Expression instantiate(Object oldInstance, Encoder out) {
                        Constructor<?>[] ctors = oldInstance.getClass().getConstructors();
                        JAXBElement<?> elem = (JAXBElement<?>) oldInstance;
                        int arity = 0;
                        for (Constructor<?> ctor : ctors) {
                            Class<?>[] parms = ctor.getParameterTypes();
                            if (parms.length > arity)
                                arity = parms.length;
                        }
                        switch (arity) {
                            case 1: // JAXBElement wrapper.
                                return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[] { elem.getValue() });
                            case 3:
                                return new Expression(oldInstance, JAXBElement.class, "new", new Object[] { elem.getName(), elem.getDeclaredType(), elem.getValue() });
                            case 4:
                                return new Expression(oldInstance, JAXBElement.class, "new", new Object[] { elem.getName(), elem.getDeclaredType(), elem.getScope(), elem.getValue() });
                            default:
                                throw new RuntimeException("Illegal JAXBElement wrapper:" + oldInstance.toString());
                        }
                    }
                });
            } else {
                bi.getBeanDescriptor().setValue("persistenceDelegate", new BeansPersistenceDelegate());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     */
    private void initPredefinedDelegates() {

        encoder.setPersistenceDelegate(JAXBElement.class, new BeansPersistenceDelegate(new String[] { "name", "declaredType", "scope", "value" }));

        encoder.setPersistenceDelegate(QName.class, new BeansPersistenceDelegate(new String[] { "namespaceURI", "localPart" }));

        encoder.setPersistenceDelegate(BigInteger.class, new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance, Encoder out) {
                return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[] { oldInstance.toString() });
            }
        });

        encoder.setPersistenceDelegate(BigDecimal.class, new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance, Encoder out) {
                return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[] { oldInstance.toString() });
            }
        });

        encoder.setPersistenceDelegate(ObjectsWrapper.createElementNS("test", "testel", "test content").getClass(), 
        								new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance, Encoder out) {
                Element el = (Element) oldInstance;
                return new Expression(oldInstance, ObjectsWrapper.class, "createElementNS", new Object[] { el.getNamespaceURI(), el.getNodeName(), el.getTextContent() });
            }
        });

        // Initializing xml data types
        Duration duration = ObjectsWrapper.createDuration("P1DT10H15M45.678S");
        encoder.setPersistenceDelegate(duration.getClass(), new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance, Encoder out) {
                return new Expression(oldInstance, ObjectsWrapper.class, "createDuration", new Object[] { oldInstance.toString() });
            }
        });

        XMLGregorianCalendar calendar = ObjectsWrapper.createXMLGregorianCalendar("1965-09-05T18:00:00.000+03:00");
        encoder.setPersistenceDelegate(calendar.getClass(), new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance, Encoder out) {
                return new Expression(oldInstance, ObjectsWrapper.class, "createXMLGregorianCalendar", new Object[] { oldInstance.toString() });
            }
        });
    }
    

    public void serialize(Object jaxbTree, OutputStream os, ClassLoader cl) {
        // There is some ClassLoader problem with XMLEncoder so, put it into
        // separate thread

        Thread curThread = Thread.currentThread();
        ClassLoader ccl = curThread.getContextClassLoader();
        try {
            curThread.setContextClassLoader(cl);
            encoder = new XMLEncoder(os);
            initJaxbInfo(jaxbTree, cl);
            encoder.setExceptionListener(new ExceptionListener() {
                public void exceptionThrown(Exception e) {
                    e.printStackTrace();
                }

            });
            encoder.writeObject(jaxbTree);
        } finally {
            if (encoder != null)
                encoder.close();
            curThread.setContextClassLoader(ccl);
        }

    }

}
