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

package com.sun.tgxml.tools.filter.redundancy.processors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The class appends String objects to a file. It locks the file
 * before appending and allows to appending from different
 * processes. The locking includes creation of a zero length file with
 * the name equals to the name of the modified file followed by the
 * ".lock" extension. The class does not require direct closing of
 * objects for synchronization with files. The synchronization with
 * files is performed in a separate Thread and a special Thread
 * synchronizes all instances prior VM exits.
 */
public class SynchronizedFileAppender {
    private static final int MAX_TIME = 100;
    private static final int MAX_ATTEMPTS = 100;
    private String fileName;
    private String lockName;
    private Appender writer;
    
    private Random rand = new Random(System.currentTimeMillis());
    private List queue = new LinkedList();
    
    private static String id;

    static {
        /*
         * It is required because the Appender is daemon and VM can exit during file writing. 
         */
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                boolean isDone = false;
                for (int i = 0; (i < MAX_ATTEMPTS * 10) && !isDone; i++) {
                    isDone = true;
                    for (Iterator iter = instances.values().iterator(); iter.hasNext();) {
                        SynchronizedFileAppender current = (SynchronizedFileAppender)iter.next();
                        if (current.queue.size() > 0) {
                            isDone = false;
                            synchronized (current.queue) {
                                current.queue.notifyAll();
                            }
                        }
                        isDone = isDone && !current.writer.isFileLocked;
                    }
                    if (isDone) {
                        return;
                    }
                    try {
                        Thread.sleep(MAX_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                throw new RuntimeException("Can not synchronize files.");
            }
        }));
        
    }
    
    private static HashMap instances = new HashMap();

    /**
     * returns a instance associated with the given file name. If the instance
     * does not exist, the method creates the instance prior and returs it.
     */
    public static synchronized SynchronizedFileAppender getInstance(String name) {
        SynchronizedFileAppender retVal = (SynchronizedFileAppender)instances.get(name);
        if (retVal == null) {
            retVal = new SynchronizedFileAppender(name);
            instances.put(name, retVal);
        }
        return retVal;
    }
    
    private SynchronizedFileAppender(String name) {
        this.fileName = name;
        this.lockName = name + ".lock";
        this.writer = new Appender(MAX_ATTEMPTS);
        Thread thread = new Thread(writer);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * appends the file by the given String. The method does not do real appending. 
     * It requests a separate Thread to lock and synchronize a file.
     * @param data String object to be appended to the file.
     */
    public void append(String data) {
        synchronized(queue) {
            queue.add(data);
            queue.notifyAll();
        }
    }
    
    private class Appender implements Runnable {
        int max_count;
        boolean isFileLocked = false;
        
        public Appender(int max_count) {
            this.max_count = max_count;
        }
        
        public synchronized void run() {
            while (true) {
                runSynchronously(this.max_count);
                try {
                    synchronized (queue) {
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        
        public void runSynchronously(int max_count) {
            if (queue.isEmpty()) {
                return;
            }
            File lock = new File(lockName);
            try {
                // obtain a lock
                int count = 0;
                while (!(isFileLocked = lock.createNewFile())) {
                    if ((count++ > max_count) && (max_count != 0)) {
                        throw new RuntimeException();
                    }
                    Thread.sleep(rand.nextInt(MAX_TIME));
                }
                // write all String objects from queue.
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, true));
                synchronized (queue) {
                    while (!queue.isEmpty()) {
                        out.write((String)queue.get(0));
                        queue.remove(0);
                    }
                }
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Can not obtain a lock file: " + lock);
             } finally {
                // release the lock
                if (isFileLocked) {
                    lock.delete();
                    isFileLocked = false;
                }
            }
        }
    }
}
