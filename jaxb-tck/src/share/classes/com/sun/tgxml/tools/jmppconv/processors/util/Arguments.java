/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.util;

import java.util.Hashtable;
import java.util.ArrayList;

public class Arguments extends Hashtable {
    private String m_special = "";
    String[] args;
    ArrayList fileNames = new ArrayList();
    boolean files = false;
    
    public Arguments() {
        super();
    }
    
    public void init(String[] args) throws Exception{
        this.args = args;
        _createArguments(args);
    }
    
    private void setDefaultNameSpace(String value) {
        if (value != null
                && !value.equals("")) {
            m_special = value;
        } else {
            m_special = "";
        }
    }
        
    public Object get(Object value) {
        return _get(value.toString());
    }
        
    
    private Object _get(String value) {
        String key = m_special + (value.startsWith("-")?value.substring(1):value);
        Object temp = super.get(key);
        return temp != null?temp:super.get(value);
    }
    
    public synchronized Arguments clone(String nameSpace) {
        Arguments temp = (Arguments)clone();
        temp.setDefaultNameSpace(nameSpace);
        return temp;
    }
    
    private void _createArguments(String[] args) throws Exception {
        String key = null;
        String file = null;
        for (int i=0; i<args.length; i++) {
            //option names
            ////
            if(args[i].startsWith("-")) {
                if (args[i].equals("-files")) { 
                    files = true;
                } else if (!files) {
                    //option or switch
                    ////
                    if (key != null) {
                        put(key, "true");
                        key = args[i].substring(1);
                    //first option
                    ////
                    } else {
                        key = args[i].substring(1);
                    }
                } else {
                    //option between files
                    ////
                    throw new Exception("incorrect argument");
                }
            //values
            ////
            } else {
                //list of files
                ////
                if (key != null) {
                    put(key, args[i]);
                    key = null;
                } else {
                    files = true;
                    fileNames.add(args[i]);
                } 
            }
        }
        
        if (key != null) {
            put(key, "true");
        }
        
        put("files", fileNames);
    }
}
