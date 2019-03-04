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

package com.sun.tgxml.tools.testgen.api;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * This class provides the storage of JavaTest TestDescription table where
 * fields are stored in a hashtable.<br>
 * toString() method returns a String which contains the table in html format
 * (it can be put into html file).
 */

public class TestDescriptionIR {
    public final static String TITLE = "title";
    public final static String NAME = "name";
    public final static String SOURCE = "source";
    public final static String CLASS = "executeClass";
    public final static String KEYWORDS = "keywords";
    public final static String CONTEXT = "context";
    public final static String EXECUTE_ARGS = "executeArgs";
    public final static String EXECUTE_NATIVE = "executeNative";
    public final static String REMOTE = "remote";
    public final static String RMIC_CLASSES = "rmicClasses";
    public final static String SELECT_IF = "selectIf";
    public final static String TIMEOUT = "timeout";

    public final static String DEFAULT_FIELDS[] = {
           TITLE, NAME, SOURCE, CLASS, KEYWORDS, CONTEXT, EXECUTE_ARGS,
           EXECUTE_NATIVE, REMOTE, RMIC_CLASSES, SELECT_IF, TIMEOUT
    };

    // map: fieldName --> String fieldValue | String[] fieldValues
    // if the fieldName maps the string array, it means that several
    // entries in TD with such fieldName are allowed.
    private Hashtable table = new Hashtable();

    // list of fields
    private ArrayList fieldSet;


    public final static int DEFAULT_OUTPUT = 0;
    public final static int JMPPLIBAPI_OUTPUT = 1;

    private int outputFormat = DEFAULT_OUTPUT;
    /*
       Note: outputFormat variable is used in the following 
       methods: toString(), fieldToHtmlString(), fileToString(),
                tableHeader(), tableTail()
       It affects only on the cosmetic format of resulting TestDescription
       table (for example, if 'outputFormat' is set to JMPPLIBAPI_OUTPUT, then
       all html tags will be in the upper case, otherwise in the lower case.)

       After fix of 4885777 TestDescriptionIR is used 
       by JmppLibAPI(MultiTestWriter) not only by ExternalEmitter

       JmppLibAPI and ExternalEmitter generate html TestDescription in
       the different format. The usage unified format for both of them
       will lead to a lot of changes in .html files during migration
       process. To avoid such changes 'outputFormat' was added.

       In the future it could be removed if the unified format will be
       accepted.
    */
    
    public TestDescriptionIR() {
        setFields(DEFAULT_FIELDS);
    }
   
   /**
    * Sets td field value.
    * @param key    field name
    * @param value  field value    
    */
    public void set(String key, String value) {
        setFieldValue(key, value);
    }

   /**
    * Sets td field values. 
    * @param key     field name
    * @param values  field value (resulting TestDescription table
    *        will contain as many rows with <code>name</code> as length 
    *        of <code>values</code>)
    */
    public void set(String key, String[] values) {
        setFieldValue(key, values);
    }

    private void setFieldValue(String key, Object value) {
        if (key == null) {
            return;
        } else if (value == null) {
            table.remove(key);
            return;
        }
        table.put(key, value);
        addField(key); // add key to the fieldSet if necessary
    }

   /**
    * The methods behavior depends on field type. If <code>key</code> maps
    * a String array, then <code>value</code> will be added to this array.
    * If <code>key</code> maps a String, then <code>value</code> will be 
    * appended to this string. If <code>key</code> maps nothing, then 
    * set(key, value) method will be invoked.
    *
    * @param key    field name
    * @param value  field value    
    */
    public void add(String key, String value) {
        if(key == null || value == null) {
            return;
        }
         
        Object contents = get(key);
        if( contents == null) {
            set(key, value);
        } else if (contents instanceof String) {
            set(key, contents + " " + value);
        } else {
            // adds value to the array
            String[] arr = (String[])contents;
            String[] newArr = new String[arr.length + 1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            newArr[arr.length] = value;
        }
    }
   

   /**
    * Consequently calls <code>add(key, value)</code> for each element from 
    * the list
    *
    * @param key    field name
    * @param values the list of values
    */
    public void add(String key, ArrayList values) {
        if(values == null) {
            return;
        }
        for (int i = 0; i < values.size();i++) {
            add(key, (String)values.get(i));
        }
    }
   
   /**
    * Calls add(key, value) method, where value is html reference to 
    * the file.
    * 
    * @param key    field name
    * @param file   file name    
    */
    public void addRef(String key, String file) {
        if(key == null || file == null) {
            return;
        }
        add(key, fileToString(file));
    }
   

   /**
    * Consequently calls <code>addRef(key, file)(key, value)</code> for each 
    * element from the list
    * @param key    field name
    * @param files  the list of files
    */
    public void addRef(String key, ArrayList files) {
        if(key == null || files == null) {
            return;
        }
        for (int i = 0; i < files.size();i++) {
            addRef(key, (String)files.get(i));
        }
    }

    /**
     * If <code>key</code> maps a String[] all its elements will be joined
     * with specified <code>delim</code>.
     */
    public void join(String key, String delim) {
        if (key == null)
           return;
        Object value = get(key);
        if (value == null || value instanceof String)
           return;
        String[] array = (String[])value;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
             sb.append(array[i]);
             if (i < array.length - 1) {
                 sb.append(delim);
             }
        }
        set(key, sb.toString());
    }

    /**
     * If <code>key</code> maps a String, this String will be broken
     * into String[].
     */
    public void split(String key, String delim) {
        if (key == null)
           return;
        Object value = get(key);
        if (value == null || value instanceof String[])
           return;

        ArrayList list = new ArrayList();
        StringTokenizer st = new StringTokenizer((String)value, delim);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        set(key, (String[])list.toArray(new String[0]));
    }


   /**
    * Returns the value of the field or null if field is not set.
    *
    * @param key    field name
    * @return returned object is either null, or an instance of String, 
    *          or an instance of String[].
    */
    public Object get(String key) {
        return table.get(key);
    }
   

   /**
    * Returns the formatted TestDescription as a String
    */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(tableHeader());
        for (Iterator it = fieldSet.iterator(); it.hasNext();) {
            String fieldName = (String)(it.next());
            Object value =  get(fieldName);
            if (value == null) {
                // do nothing
            } else if (value instanceof String) {
                sb.append(fieldToHtmlString(fieldName, (String)value));
            } else {
                String[] array = (String[])value;
                for (int i = 0; i < array.length; i++) {
                    sb.append(fieldToHtmlString(fieldName, array[i]));
                }
            }
        }

        sb.append(tableTail());

        return sb.toString();

    }

   /**    
    * @deprecated As of bitools version 1.2,
    * replaced by <code>fieldToHtmlString(String key, String value)</code>.
    *
    * Returns the formatted TestDescription field row as a String.
    * 
    * @param key    field name
    */
    protected String fieldToHtmlString(String key) {
        if(key == null)
            return "";
      
        Object value = get(key);
        if (value == null)
            return "";

        if (value instanceof String)
            return fieldToHtmlString(key, (String)value);

        String[] array = (String[])value;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
             sb.append(array[i]);
             if (i < array.length - 1) {
                 sb.append(" ");
             }
        }
        return fieldToHtmlString(key, sb.toString());
    }


   /**
    * Returns the formatted TestDescription field row as a String
    * @param key    field name
    * @param value  field value
    */
    protected String fieldToHtmlString(String key, String value) {
        if(key == null || value == null) {
            return "";
        }
        if (outputFormat == JMPPLIBAPI_OUTPUT) {
            return "  <TR>\n" + 
                   "    <TD SCOPE=\"row\"> <B>" + key + "</B> </TD>\n" + 
                   "    <TD> " + value + " </TD>\n  </TR>\n";
        } else {
            return "  <tr>\n" + 
                   "      <td scope=\"row\"><b>" + key + "</b></td>\n" + 
                   "      <td>" + value + "</td></tr>\n";
         }
    }
   
    protected String fileToString(String file) {
        if (outputFormat == JMPPLIBAPI_OUTPUT) {
            return "<A HREF=\"" + file + "\">" + file + "</A>";
        } else {
            return "<a href=\"" + file + "\">" + file + "</a>";
        }
    }


    protected String tableHeader() {
        if (outputFormat == JMPPLIBAPI_OUTPUT) {
            return 
            "<P>\n" +
            "<TABLE BORDER=1 SUMMARY=\"Javatest Test Description\"" +
                " CLASS=TestDescription>\n" + 
            "  <THEAD><TR><TH SCOPE=\"col\">Item</TH><TH SCOPE=\"col\">" + 
                "Value</TH></TR></THEAD>\n";
        } else {
            return 
            "<p>\n" +
            "<table border=\"1\" class=\"TestDescription\"" + 
                " summary=\"JavaTest Test Description\">\n" +
            "<thead><tr><th scope=\"col\">Item</th><th scope=\"col\">" + 
                "Value</th></tr></thead>\n";
        }
    }
   
    protected String tableTail() {
        if (outputFormat == JMPPLIBAPI_OUTPUT) {
             return "</TABLE>";
        } else {
             return "</table>\n<p>\n";
        }
    }
   
   /**
    * Access method for the table property.
    *
    * @return   the current value of the table property
    */
    public Hashtable getTable() {
        return table;
    }
   
   /**
    * Sets the value of the table property.
    *
    * @param aTable the new value of the table property
    */
    public void setTable(Hashtable aTable) {
        table = aTable;
    }

    /**
     * Adds new field to the list of TestDescription fields if does not exists
     */
    public void addField(String field) {
       if (field != null && !fieldSet.contains(field)) {
           fieldSet.add(field);
       }
    }


    /**
     * Removes the field from the list of TestDescription fields if exists
     */
    public void removeField(String field) {
       if (field != null && fieldSet.contains(field)) {
           fieldSet.remove(field);
       }
    }

    /**
     * Sets new list of TestDescription fields
     */
    public void setFields(ArrayList newFieldSet) {
       if (newFieldSet != null) {
           fieldSet = newFieldSet;
       }
    }

    /**
     * Sets new list of TestDescription fields
     */
    public void setFields(String[] newFieldSet) {
        if (newFieldSet != null) {
            ArrayList arrList = new ArrayList();
            arrList.addAll(Arrays.asList(newFieldSet));
            fieldSet = arrList;
       }
    }


    /**
     * Returns the list of TestDescription fields
     */
    public ArrayList getFields() {
        return fieldSet;
    }

   
    /**
     * Returns the output format
     */
    public int getOutputFormat() {
        return outputFormat;
    }

    /**
     * Sets the output format. This setting affects on the work of
     * toString() method. 
     */
    public void setOutputFormat(int format) {
        outputFormat = format;
    }

}
