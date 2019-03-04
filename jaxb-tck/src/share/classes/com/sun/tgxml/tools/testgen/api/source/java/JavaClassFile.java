/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.testgen.api.source.java;

import java.util.ArrayList;
import java.util.Iterator;
import com.sun.tgxml.tjtf.tools.BuildProperties;

public class JavaClassFile {
    private String name;
    private String packageName;
    private ArrayList imports;
    private ArrayList classes;
    private ArrayList authors;
    private String title;
    private String description;
    private String copyrightNotice;
    private String comment;
    private String id;
    private String varID;
    
    public JavaClassFile() {
        String crn_prop = BuildProperties.getPrefixString(
            "testgen",
            "test.copyrightNotice",
            ""
        );
        Comment copyright = new Comment();
        copyright.setType(Comment.PLAIN);
        copyright.setContent(crn_prop);
        setCopyrightNotice(copyright.toString());
    }
    
    public void setName(String name) {
        if (name != null) {
            this.name = name.trim();
            if (!this.name.endsWith(".java")) this.name += ".java";
        } else name = "";
    }
    
    public String getName() {
        return name == null?"":name;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
       return comment == null?"":comment;
    }
    
    public void setCopyrightNotice(String copyrightNotice) {
        this.copyrightNotice = copyrightNotice;
    }
    
    public String getCopyrightNotice() {
       return copyrightNotice == null?"":copyrightNotice;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
       return description == null?"":description;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
       return title == null?"":title;
    }
   
    public void setLibID(String ID) {
        this.id = ID;
    }
    
    public String getLibID() {
       return (id == null || id.length() == 0) ? null : id;
    }
   
    public void setLibVarID(String ID) {
        this.varID = ID;
    }
    
    public String getLibVarID() {
       return (varID == null || varID.length() == 0) ? null : varID;
    }
   
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getPackageName() {
       return packageName == null?"":packageName;
    }
    
    public void addImport(String value) {
        if (imports == null) imports = new ArrayList();
        if (value != null
                && !imports.contains(value.trim())) {
            if (imports == null) imports = new ArrayList();
            imports.add(value);
        }
    }
    
    public void addImports(ArrayList imports) {
        if (imports != null) {
            Iterator it = imports.iterator();
            while(it.hasNext()) {
                addImport(it.next().toString());
            }
        }
    }
    
    public ArrayList getClasses() {
        return classes == null?new ArrayList():classes;
    }
    
    public void addClass(JavaClass value) {
        if (classes == null) classes = new ArrayList();
        if (value != null
                && !classes.contains(value)) {
            if (classes == null) classes = new ArrayList();
            if (getName() == null
                    || getName().trim().equals("")) {
                setName(value.getName());
            }
            classes.add(value);
        }
    }
    
    public String getAuthors() {
        String result = "";
        if (authors != null) {
            Iterator it = authors.iterator();
            while(it.hasNext()) {
                result += result.equals("")?"":", ";
                result += it.next();
            }
        }
        
        return result;
    }
    
    public void addAuthor(String value) {
        if (authors == null) authors = new ArrayList();
        if (value != null
                && !authors.contains(value.trim())) {
            if (authors == null) authors = new ArrayList();
            authors.add(value);
        }
    }
    
    public void addAuthors(ArrayList authors) {
        if (authors != null) {
            Iterator it = authors.iterator();
            while(it.hasNext()) {
                addAuthor(it.next().toString());
            }
        }
    }
    
    protected String getImports() {
        String result = "";
        if (imports != null) {
            Iterator it = imports.iterator();
            while(it.hasNext()) {
                result += "import " + it.next().toString() + ";\n";
            }
            result += "\n";
        }
        
        return result;
    }
    
    protected String libraryIDvariantID() {
        String libID = getLibID();
        String libVarID = getLibVarID();
        if (libID == null && libVarID == null)
            return "";

        String result = "\n/* --- ";
        if (libID != null) {
            result += "Library ID: " + libID;
        }
        if (libVarID != null) {
            result += "  Variant ID: " + libVarID;
        }
        result += " --- */\n\n";
        
        return result;
    }
    
    public String toString() {
        String result = "";
        
        result += getCopyrightNotice() + "\n";
        
        result += "package " +  getPackageName() +";\n\n";
        
        //imports
        ////
        result+= getImports();

        //Library ID, VarID
        ////
        result+= libraryIDvariantID();
        
        //comments
        ////
        Comment classComment = new Comment(); 
        classComment.setContent(getTitle());
        classComment.addContent(getDescription());
        classComment.addContent("Author: " + getAuthors());
        
        result+= classComment.toString() + "\n";
        
        
        //classes
        ////
        Iterator it = getClasses().iterator();
        while(it.hasNext()) {
            result += it.next().toString();
            result += "\n";
        }
        
        return result;
    }
}
            
