/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jmpp.lib;


/**
 *  Thrown if an error occured during parsing the JmppLibAPI properties file.
 *  This exception is for use with JmppLibAPI and it's subclasses only.
 */
public class InvalidPropertyException extends LibAPIException {

    /**
     * Constructs a <code>InvalidPropertyException</code> with the specified
     * detail message.
     *
     * @param message the detail message.
     */
    public InvalidPropertyException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>InvalidPropertyException</code> using the given
     * property name and reason.
     * @param propertyName The name of a property where error has been detected
     * @param reason the reason of this exception.     ÃÂ
     */
    public InvalidPropertyException(String propertyName, String reason) {
        super("Invalid property found:\n" 
                + propertyName + " : " + reason);   
    }
        
}
