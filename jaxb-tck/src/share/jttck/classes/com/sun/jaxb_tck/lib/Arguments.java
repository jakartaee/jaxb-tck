/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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
package com.sun.jaxb_tck.lib;

import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;

import com.sun.javatest.util.StringArray;

/**
 * Used to configure the launch settings of command line tasks.
 *
 * @author  Leonid Kuskov
 * @version 1.10
 */
public final class Arguments implements Cloneable {

    //Private attribute(s)
    private ArrayList<String> args;

    static Arguments toArguments(String[] args) {
        Arguments lArgs = new Arguments(args);
        return lArgs;
    }

    /**
     * Ctor
     */
    public Arguments(String[] args) {
        this();
        append(args);
    }

    /**
     * Ctor
     */
    public Arguments() {
        args = new ArrayList<String>();
    }

    /**
     * Appends all of the elements in the specified array to the end of this list.
     * @param array the elements to be inserted into this list.
     */
    public void append(String[] array) {
        if((array != null) ) {
            for(int i=0; i < array.length; i++) {
                args.add(array[i]);
            }
        }
    }
    
    public void insert(int index, String[] value){
    	for(int i=value.length-1;i>=0;i--){
    		args.add(index, value[i]);
    	}
   }

    /**
     * Adds the specific element to the end of this list
     */
    public void append(String value) {
        args.add(value);
    }

    /**
     * Removes the element at the specified position in this list if
     *  if index >= 0 && index < size()
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     */
    public void remove(int index) {
        if(index >= 0 && index < size()) {
            args.remove(index);
        }
    }

    /**
     * Returns true if this Arguments contains the specified element.
     *
     * @param elem the element whose presence in this List is to be tested.
     * @return true if the specified element is present; false otherwise.
     */
    public boolean contains(String elem) {
       return args.contains(elem);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     */
    public String get(int index) {
        return args.get(Math.min(index,args.size()-1));
    }
    
    /**
     * Replaces the element at the specified position in this 
     * list with the specified element.
     * 
     */
    public void set(int index, String value) {
        if( index > -1 && index < args.size()) {
            args.set(index, value);
        }
    }    

    /**
     *  Returns the number of elements in this list.
     */
    public int size() {
        return args.size();
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        args.clear();
    }



    /**
     * Appends a list of files that are placed in the sourceDir and have
     * the specified extension recursively.
     */
    public void addFileNames(File sourceDir, final String extension) {
        FileFilter fileFilter = new FileFilter() {
          public boolean accept(File file) {
              return ( file.getName().endsWith(extension) || file.isDirectory());
          }
        };
        findFiles( fileFilter, sourceDir );
    }

    /**
     * Removes the {key, value1,...,valueCount} list for this key from the list if present.
     *
     * @param key the key to search for value
     * @param count the amount of values to delete
     */
    public void removeArgs(String key, int count) {
        int i = args.indexOf(key);
        if( (i > -1) && (count > 0)) {
            for(; count > 0; count--) {
                args.remove(i);
                if( (i == args.size()) || args.get(i).startsWith("-") ) {
                    break;
                }
            }
        }
    }

    /**
     * Removes elements with the specified suffix.
     *
     * @param suffix the suffix.
     */
    public void removeArgs(String suffix) {
        int i = 0;
        while( i < args.size()) {
            if( args.get(i).endsWith(suffix) ) {
                args.remove(i);
            } else {
              i++;
            }
        }
    }

    /**
     * Gets elements with the specified suffix.
     *
     * @param suffix the suffix.
     */
    public ArrayList<String> getArgs(String suffix) {
        int i = 0;
        ArrayList<String> result = new ArrayList<String>();
        while( i < args.size()) {
            String str = args.get(i);
            if( str.endsWith(suffix) ) {
                result.add(str);
            }
            i++;
        }
        return result;
    }

    /**
     * Adds the single key at the first position if list does not contain it
     *
     * @key The key to be inserted
     */
    public void addArg(String key) {
        if (!args.contains(key)) {
            args.add(0, key);
        }
    }

    /**
     * Adds a {key,value} pair at the first position
     * if list does not contain key.
     *
     * @key the key to be inserted if not present
     * @value the value to be inserted if the key is not present
     */
     public void addArgs(String key, String value) {
        if (!args.contains(key)) {
            args.add(0, value);
            args.add(0, key);
        }
    }

    /**
     * Initializes the list.
     *  @param args the elements to be inserted into this list.
     */
    public void setArgs(String[] args) {
        this.args.clear();
        append(args);
    }

    /**
     * Gets the arguments list.
     */
    public String[] getArgs() {
        return (String[])args.toArray(new String[0]);
    }


    public String getArgsAsString(String delimiter) {
        String result = null;
        if ( args.size() > 0 ) {
            result = StringArray.join(getArgs(), delimiter);
        }
        return result;
    }


    /**
     * Gets the {key, value1,...,valueCount} list for this key from the list if present.
     *
     * @param key The key
     * @param count amount of values to get
     */
    public String[] getArg(String key, int count) {
        String[] result = null;
        int i = args.indexOf(key);
        if(i != -1) {
            result = (String[])args.subList( i, Math.min(i+count,args.size()) ).toArray(new String[0]);
        }
        return result;
    }

    /**
      * Gets arguments that begin at specified key end extend to the end.
      *
      * @param key the key to search for
      * @param includeKey If true then the key should be included  to result.
     */
    public String[] getTail(String key, boolean includeKey) {
        String[] result = new String[0];
        int i = args.indexOf(key);
        if( i != -1) {
            i += (includeKey ? 0 : 1);
            int j = args.size();
            if(i<j) {
                result = (String[])args.subList(i,j).toArray(new String[0]);
            }
        }
        return result;
    }

    /**
     * Gets the value that is associated to the key.
     *
     * @param key the key to search for value
     * @param defaultValue the default Vualue that will be return if a value is
     * not found.
     *
     * @return a found string if exists otherwise defaultValue.
    */
    public String getValue(String key, String defaultValue) {
        String result = defaultValue;
        if(args.size() > 0) {
            int i = args.indexOf(key);
            if(( i != -1 ) && (i != args.size() ) ) {
                result = args.get(i+1);
            }
        }
        return result;
    }

    /**
     * Gets the value that is associated to the key.
     *
     * @param key the key to search for value
     * @return a found string if it isn't equal to string that starts with "-"
     * otherwise null.
     */
    public String getValue(String key) {
        String result = getValue(key, null);
        return ( ((result != null ) && !result.startsWith("-"))  ? result : null );
    }

    public void updateValue(String key, String newValue) {
         if(args.size() > 0) {
            int i = args.indexOf(key);
            if((i > -1) && (++i < args.size()) && !args.get(i).startsWith("-")) {
                args.set(i,  newValue);
            }
         }
    }

    /**
     * Deep clone.
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            Arguments result = (Arguments) super.clone();
            result.args      = (ArrayList)args.clone();
            return result;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    private void findFiles(FileFilter fileFilter, File dir) {
        File[] fileList = dir.listFiles(fileFilter);
        if (fileList == null) {
            return;
        }
        for (File file : fileList) {
            if(file.isDirectory()) {
                findFiles(fileFilter, file);
            } else {
                args.add(file.getAbsolutePath());
            }
        }
    }
}
