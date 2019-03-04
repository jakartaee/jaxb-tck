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
 *  Thrown if an error occured during parsing <code>.jmpp</code> file
 *  by JmppLibAPI library. This exception is for use with JmppLibAPI and it's
 *  subclasses only.
 */
public class LibAPIException extends com.sun.jmpp.JmppException {

    /**
     * Constant used to indicate exception thrown on directory level
     */
    public static final int DIRECTORY_LEVEL = 0;

    /**
     * Constant used to indicate exception thrown on file level
     */
    public static final int FILE_LEVEL = 1;

    /**
     * Constant used to indicate exception thrown on testcase level
     */
    public static final int TESTCASE_LEVEL = 2;

    /**
     * Level of this exception
     */
    private int level = 0;

    /**
     * Refence to JmppLibAPI object to take context from
     */
    private JmppLibAPI libAPI = null;

    /**
     * Constructs a <code>LibAPIException</code> with the specified
     * detail message.
     *
     * @param message the detail message.
     */
    public LibAPIException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>LibAPIException</code> with the specified
     * reason, level using context of libAPI. The level might be either
     * <code>DIRECTORY_LEVEL</code>, <code>FILE_LEVEL</code>, or
     * <code>TESTCASE_LEVEL</code>.
     * @param libAPI <code>JmppLibAPI</code> object to take context from
     * @param level the level where exception occurs
     * @param reason the reason of this exception.     ÃÂ
     * @see JmppLibAPI
     */
    public LibAPIException(JmppLibAPI libAPI, int level, String reason) {
        super(reason);
        this.level = level;
        this.libAPI = libAPI;
    }
    
    /**
     * Returns the detailed message with a preprocessing error description.
     * If exception has been thrown on directory level then just
     * <code>message</code> will be returned. For file level, the conmtents of 
     * JmppLibAPI <code>file</code> and <code>filetitle</code> variables will be
     * included in message as well. For testcase level, the contents of
     * <code>file</code>, <code>testCaseID</code>, <code>method</code>  and
     * <code>values</code> variables will be included.
     * @return the detailed message strin
     * @see #LibAPIException(JmppLibAPI libAPI, int level, String reason)
     */
    public String getMessage() {
    
        String message = super.getMessage();
        if (libAPI == null) {
            return message;
        }        
            
        switch (level) {        
            case 1:   
               return "\n     file : " + libAPI.file + "\n" +
                        "filetitle : " + libAPI.filetitle + "\n"  +
                        "   Reason : " + message;   
            case 2:  
               return "\n      file : " + libAPI.file + "\n" +
                        "testCaseID : " + libAPI.testCaseID + "\n" + 
                        "    method : " + libAPI.method + "\n" +  
                        "    values : " + libAPI.values + "\n" +                            
                        "    Reason : " + message;   
            default:
               return message;  
        
        }
    
    }
    
}
