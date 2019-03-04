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

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import javasoft.sqe.javatest.Status;

/**
 *
 * Compares two Java Beans objects for properties equality.
 *
 * @author Vladimir Sosnin
 *
 */
public class Comparator {

    // Leonid Kuskov: added static keyword  to avoid cyclic references via ID and IDREF.
    static Set<Object> comparedValues = new TreeSet<Object>(new java.util.Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            Integer i1 = System.identityHashCode(o1);
            return i1.compareTo(System.identityHashCode(o2));
        }
    });

    static class ComparableObject implements Comparable {

        Object value;

        ComparableObject(Object value) {
            this.value = value;
        }

        public int compareTo(Object o) {
            return compareBeans(value, o).isPassed() ? 0 : 1;
        }

    }

    static class ComparableElement implements Comparable {

        Element el;

        String namespaceUri, nodeName, textContent;

        ComparableElement(Element element) {
            this.el = element;
            namespaceUri = el.getNamespaceURI();
            nodeName = el.getNodeName();
            textContent = el.getTextContent();
        }

        /**
         * @see Comparable#compareTo(Object)
         */
        public int compareTo(Object element) {
            if (element instanceof Element) {
                Element comparedNode = (Element) element;
                if (nodeName.equals(comparedNode.getNodeName())
                        && textContent.equals(comparedNode.getTextContent())) {
                    if (namespaceUri == null)
                        return comparedNode.getNamespaceURI() == null ? 0 : 1;
                    return namespaceUri.equals(comparedNode.getNamespaceURI()) ? 0: 1;

                } else
                    
                    log("Comapring Element: \n\tnodeName: " + nodeName
                            + " - " + comparedNode.getNodeName()
                            + ", textContent: " + textContent + " - "
                            + comparedNode.getTextContent());
            } else
                throw new IllegalArgumentException("Object isn't Element: "
                        + element);
            return 1;
        }

        protected String elementToString(Element element) {
            return null;
        }

    }

    static class ComparableArray implements Comparable {

        byte[] array;

        ComparableArray(byte[] array) {
            this.array = array;
        }

        /**
         * @see Comparable#compareTo(Object)
         */
        public int compareTo(Object o) {
            if (!(o instanceof byte[]))
                throw new IllegalArgumentException(
                        "The object isn't byte array: " + o);
            byte[] toCompare = (byte[]) o;
            boolean result = Arrays.equals(array, toCompare);
            if (!result) {
                log("Array 1: " + Arrays.toString(array));
                log("Array 2: " + Arrays.toString(toCompare));
            }
            return result ? 0 : 1;
        }

    }

    static class ComparableBean implements Comparable<Object> {

        Object obj = null;

        Method method = null;

        ComparableBean(Object obj, Method m) {
            this.obj = obj;
            method = m;
            if (obj == null) {
                this.obj = this;
                try {
                    method = ComparableBean.class.getDeclaredMethod(
                            "compareNull", new Class[] { Object.class });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * @see Comparable#compareTo(Object)
         */
        public int compareTo(Object o) {
            Object result = null;
            if (method == null)
                throw new UnsupportedOperationException(
                        "Can't compare with objects " + obj
                                + " using method null");
            try {
                result = method.invoke(obj, o);
                if (result instanceof Boolean)
                    result = (Boolean) result ? new Integer(0)
                            : new Integer(-1);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return (Integer) result;
        }

        private int compareNull(Object obj) {
            return obj == null ? 0 : 1;
        }                                             
    }

    /**
     * Compares two JavaBeans
     *
     * @return Status with explanation
     * @throws RuntimeException
     *             if beans traversal errors occurs
     */
    public static Status compareBeans(Object source, Object target) {
        Comparator comp = new Comparator();
        return comp.compareBeansAsTrees(source, target);

    }

    /*
     * Despite the fact that this is recursive method, there are introduced a
     * lot of method variables instead of methods invocations because deep of
     * recursion will be low (2-3)
     */
    protected Status compareBeansAsTrees(Object source, Object target) {

        if (source == null)
            return null;
        BeanInfo sourceBInfo = null;
        BeanInfo targetBInfo = null;
        try {
            // Leonid Kuskov fix:: 
            // The prefix is NOT used in QName.equals(Object) or to compute the QName.hashCode(). 
            // Equality and the hash code are defined using only the Namespace URI and local part.
            if ((source instanceof QName) && (target instanceof QName)) {
                 QName s = (QName)source;
                 QName t = (QName)target;
                 if ( s.equals(t) ) {
                     return Status.passed("Comparison passed.");
                 }
                 return Status.failed("QName objects are not equal:\n" +
                 		              "source.localPart=" + s.getLocalPart() + " target.localPart=" +t.getLocalPart() + "\n" +
                                      "source.namespaceURI=" + s.getNamespaceURI() + " target.namespaceURI=" +t.getNamespaceURI() );
            } 
            
            sourceBInfo = Introspector.getBeanInfo(source.getClass());
            targetBInfo = Introspector.getBeanInfo(target.getClass());
            String sourceBName = sourceBInfo.getBeanDescriptor().getName();
            String targetBName = targetBInfo.getBeanDescriptor().getName();
            log("Source name: " + sourceBName);
            log("Target name: " + targetBName);
            PropertyDescriptor[] sourcePDesc = sourceBInfo.getPropertyDescriptors();
            PropertyDescriptor[] targetPDesc = sourceBInfo.getPropertyDescriptors();
            for (int i = 0; i < sourcePDesc.length; i++) {
                String propName = sourcePDesc[i].getName();
                log("Checking property " + (i + 1) + "/" + sourcePDesc.length + ": " + sourceBName + "."  + propName);
                if (propName.equals("class")) {
                    log("Skipped.");
                    continue;
                } 
                Method sourceReadMethod = getReadMethod(sourcePDesc, propName);
                Method targetReadMethod = getReadMethod(targetPDesc, propName);
                if (!(sourceReadMethod == null || targetReadMethod == null)) {
                    Object sourceValue = sourceReadMethod.invoke(source,(Object[]) null);
                    Object targetValue = targetReadMethod.invoke(target);
                    
                    // Possible skipping of check for two identical objects with
                    // same identityHashCode
                    if (comparedValues.contains(targetValue))
                        continue;
                    comparedValues.add(targetValue);
                    Comparable<Object> sourceComparableValue = getComparable(sourceValue);
                    
                    if (sourceComparableValue.compareTo(targetValue) != 0) {
                        log("Value isn't equal for property '"
                                + sourceBName + "." + propName + "': "
                                + sourceValue + " - " + targetValue);
                        // One of the possible solution is to use certain
                        // logging Handler implementation for tracking all
                        // comparison problem at one time
                        return Status.failed("Value isn't equal for property '"
                                + sourceBName + "." + propName + "': "
                                + sourceValue + " - " + targetValue);

                    }
                    log("Checked '" + sourceBName + "." + propName + " = "
                            + sourceValue + "' equals to master.");
                } else
                    log("Skipped.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Beans error.", e);
        }
        return Status.passed("Comparison passed.");
    }

    private static Method getReadMethod(PropertyDescriptor[] descArray,
            String name) {
        Method readMethod = null;
        for (PropertyDescriptor descriptor : descArray) {
            if (descriptor.getName().compareTo(name) == 0) {
                readMethod = descriptor.getReadMethod();
                // Workaround for generateIsSetMethod binding customization
                if (descriptor.getName().startsWith("set")
                        && descriptor.getWriteMethod() == null)
                    readMethod = null;
                break;
            }
        }
        return readMethod;
    }

    /**
     * Factory method returning <code>Comparable</code> for any
     * <code>Object</code>. It returns object itself if it's
     * <code>Comparable</code> or particular wrapper class.
     *
     * @param value -
     *            object to be <code>Comparable</code>
     * @return <code>Comparable</code> object
     */
    protected static Comparable<Object> getComparable(Object value) {
        try {
            if (value == null) {
                return new ComparableBean(null, null);
            }
            if (value instanceof Comparable) {
                return (Comparable<Object>) value;
            } else if (value instanceof XMLGregorianCalendar) {
                Method compareMethod = XMLGregorianCalendar.class.getDeclaredMethod("compare", new Class[] { XMLGregorianCalendar.class });
                return new ComparableBean(value, compareMethod);
            } else if (value instanceof Duration) {
                Method compareMethod = Duration.class.getDeclaredMethod("compare", new Class[] { Duration.class });
                return new ComparableBean(value, compareMethod);
            } else if (value instanceof Class) {
                Method compareMethod = Class.class.getMethod("equals", new Class[] { Object.class });
                return (Comparable<Object>) new ComparableBean(value, compareMethod);
            } else if (value instanceof Element) {
                return (Comparable) new ComparableElement((Element) value);
            } else if (value instanceof byte[]) {
                return new ComparableArray((byte[]) value);
            } else
                return new ComparableObject(value);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean debug = (Boolean.valueOf(System.getProperty("debug","false"))).booleanValue();

    static void log(String msg) {
        if(debug)
            System.out.println(msg);
    }

}
