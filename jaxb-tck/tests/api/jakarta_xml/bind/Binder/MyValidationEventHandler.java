/*
 * Copyright (c) 2003, 2020 Oracle and/or its affiliates. All rights reserved.
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

import java.io.PrintWriter;
import java.util.LinkedList;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;


/**
 * MyValidationEventHandler is an implementation of <code>jakarta.xml.bind.ValidationEventHandler</code>
 * interface that stores validation events in an internal list and always return true.
 */
public class MyValidationEventHandler implements ValidationEventHandler{
    
    /**
     * Validation events handled by <code>handleEvent(jakarta.xml.bind.ValidationEvent)</code> method
     * are stored in this list.
     */
    private LinkedList<ValidationEvent> _validationEvents = new LinkedList<ValidationEvent>(); 
    
    
    
    /**
     * Constructs MyValidationEventHandler.
     */
    public MyValidationEventHandler(){
        super();
    }
    
      
    /**
     * Implements handleEvent method of ValidationEventHandler interface.
     * This method stores event in internal list. Always returns <code>true</code>
     * @see jakarta.xml.bind.ValidationEventHandler#handleEvent(jakarta.xml.bind.ValidationEvent)
     */
    public boolean handleEvent(ValidationEvent event) {
        _validationEvents.add(event);
        return true;
    }
    
    /**
     * Method hasEvents().
     * @return true if at least one validation event is processed with handleEvent.
     */
    public boolean hasEvents(){
       return !_validationEvents.isEmpty(); 
    }
    
    /**
     * Clears state of MyValidationEventHandler by clearing 
     * internal list of validation events.
     */
    public void reset(){
        _validationEvents.clear();
    }
    
    /**
     * Method getValidationEventCount() returns count of validation
     * events contained in MyValidationEventHandler
     */
    public int getValidationEventCount(){
        return _validationEvents.size();
    }
    
    /**
     * Method getValidationEvents returns all validation events contained in
     * MyValidationEventHandler.
     */
    public ValidationEvent[] getValidationEvents(){
        return _validationEvents.toArray(new ValidationEvent[_validationEvents.size()]);
    }
    
    public void printEvents(PrintWriter w){
        for(ValidationEvent e:_validationEvents){
            w.println("Event: "+eventToString(e));
        }
    }
    
    public static String eventToString(ValidationEvent e) {
        StringBuffer sb = new StringBuffer();
        switch (e.getSeverity()) {
            case ValidationEvent.ERROR: sb.append("ERROR"); break;
            case ValidationEvent.FATAL_ERROR: sb.append("FATAL_ERROR"); break;
            case ValidationEvent.WARNING: sb.append("Warning"); break;
            default: sb.append("UNDEFINED");
        }
        ValidationEventLocator locator = e.getLocator();
        sb.append(" at (" + locator.getLineNumber() + ", " + locator.getColumnNumber() + "): "
                + e.getMessage());
        return sb.toString();
    }
}
