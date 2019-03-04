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

package com.sun.tgxml.tools.testgen.processors.emitter;

import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;

/**
 * Processor catches names of classes being compiled
 */
public class J2XProcessor extends javax.annotation.processing.AbstractProcessor {
    
    ArrayList<String> classNames;

    public Set<String> getSupportedAnnotationTypes() {
        Set set = new HashSet();
        set.add("*");
        return set;
    }
    Set<? extends Element> set;
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            set = roundEnv.getRootElements();
            return true;
        }
        
        Iterator<? extends Element> it = set.iterator();
        
        classNames = new ArrayList<String>();
        while(it.hasNext()) {
            printElement(it.next());
        }
        return true;
    }
    
    protected void printElement(Element element) {
        if (! (element instanceof TypeElement)) return;
        TypeElement typeElement = (TypeElement)element;
        
        String elementBinaryName = getBinaryName(typeElement);
        
        classNames.add(elementBinaryName);
        System.out.println("... compiling " + elementBinaryName);
        
        List<? extends Element> list = element.getEnclosedElements();
        Iterator<? extends Element> it = list.iterator();
        while (it.hasNext()) {
            printElement(it.next());
        }
    }
    
    protected String getBinaryName(TypeElement element) {
        if (element.getNestingKind() == NestingKind.TOP_LEVEL) {
            return element.getQualifiedName().toString();
        }
        StringBuffer buffer = new StringBuffer();
        composeBinaryName(element, buffer);
        return buffer.toString();
    }

    // compose binary name from qualified name
    protected void composeBinaryName(TypeElement element, StringBuffer buffer) {
        if (element.getNestingKind() == NestingKind.TOP_LEVEL) {
            buffer.append(element.getQualifiedName());
            return;
        }
        Element cur = element.getEnclosingElement();
        
        // cur is already instance of TypeElement, the next while statement is formal
        // cur cannot be null here, so checking after the while statement is formal
        while ( !(cur == null || cur instanceof TypeElement) )  {
            cur = cur.getEnclosingElement();
        }
        if (cur == null) return;
        
        composeBinaryName((TypeElement)cur, buffer);
        buffer.append("$");
        buffer.append(element.getSimpleName());
    }
    
    public Iterable<String> getClassNames() {
        return classNames;
    }
    
}
