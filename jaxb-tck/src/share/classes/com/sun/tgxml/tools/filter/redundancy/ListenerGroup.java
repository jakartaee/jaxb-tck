/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.redundancy;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;

/**
 * The class represents TestItemSelectionListener, which dispatches all events
 * to registered TestItemSelectionListener instances.
 * <br>
 * It also provides methods to maintain registered instances list.
 */
public class ListenerGroup implements TestItemSelectionListener {
    
    private ArrayList listeners = new ArrayList();
    
    public ListenerGroup() {
    }
    
    private ListenerGroup(ArrayList list) {
        this.listeners = (ArrayList)list.clone();
    }

    /**
     * registers new listener. When a listener is registered it receives all
     * event received by ListenerGroup.
     */
    public void registerListener(TestItemSelectionListener listener) {
        listeners.add(listener);
    }

    /**
     * returns registered listeners.
     */
    public Iterator getListeners() {
        return listeners.iterator();
    }

    /**
     * unregister given Listner.
     */
    public void unregisterListener(TestItemSelectionListener listener) {
        listeners.remove(listener);
    }
    
    private interface Command {
        public void process(TestItemSelectionListener listener, TestItem item)
            throws TestFileException;
    }

    private void execute(TestItem item, Command command)
        throws TestFileException {
        for (Iterator i = listeners.iterator(); i.hasNext();) {
            TestItemSelectionListener current = (TestItemSelectionListener)i.next();
            try {
                command.process(current, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    public void flush() {
        try {
            this.execute(null, new Command() {
                public void process(TestItemSelectionListener listener, TestItem item) {
                    listener.flush();
                }
            });
        } catch (TestFileException e) {
            // This Exception is never thrown.
        }
    }
    
    public void externalLibrarySelected(Library item) throws TestFileException {
        this.execute(item, new Command() {
            public void process(TestItemSelectionListener listener, TestItem item) 
                throws TestFileException {
                listener.externalLibrarySelected((Library)item);
            }
        });
    }

    public void internalLibrarySelected(Library item) throws TestFileException {
        this.execute(item, new Command() {
            public void process(TestItemSelectionListener listener, TestItem item) 
                throws TestFileException {
                listener.internalLibrarySelected((Library)item);
            }
        });
    }

    public void testCaseSelected(TestCase item) throws TestFileException {
        this.execute(item, new Command() {
            public void process(TestItemSelectionListener listener, TestItem item) 
            throws TestFileException {
                listener.testCaseSelected((TestCase)item);
            }
        });
    }

    public void testGroupSelected(TestGroup tGroup) throws TestFileException {
        this.execute(tGroup, new Command() {
            public void process(TestItemSelectionListener listener, TestItem item) 
            throws TestFileException {
                listener.testGroupSelected((TestGroup)item);
            }
        });   
    }

    public Object clone() {
        return new ListenerGroup(listeners);
    }
}
