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

package com.sun.tgxml.tools.dependence.processors;

import java.io.PrintStream;
import java.io.FileInputStream;
import java.util.TreeSet;
import java.util.Stack;
import java.util.Iterator;

import com.sun.tgxml.tjtf.resources.LibResHandler;

import com.sun.tgxml.tools.dependence.LibIDList;
import com.sun.tgxml.tools.dependence.LibraryDependencies;
import com.sun.tgxml.tools.dependence.LibraryDependency;

/**  
 * A processing dependency component. The component is to reveal the whole 
 * set of libraries that given ones depend on.<br>
 * The following inconsistencies are detected and printed as warnings to
 * specified #err:<br>
 * <ul>
 * <li>Duplicate library - if the specified <i>libIDList</i> contains a 
 *                         library identifier twice
 * <li>No starting libraries - if the specified <i>libIDList</i> contains no
 *                         libraries. In this case an empty list becomes the
 *                         result of the dependence analysis.
 * <li>No dependencies - if the specified libraryDependencies contains no
 *                         entries. In this case the method assumes that
 *                         all libraries are independent.
 * <li>Cyclic dependency - if a cyclic dependency is found
 *                         
 * </ul>
 * <br>
 *
 * @author Evgueni Rouban
 * @version @(#)DependenceAnalyzer.java	1.3 02/03/13
 * @see com.sun.tgxml.tools.dependence.DependenceAnalyzerTool
 *      LibraryDependency
 */

public class DependenceAnalyzer {
    
    /**
     * Error stream. 
     */
    protected PrintStream err;

    /**
     * The stack of libraries in depth-first-traversal algorithm.
     */
    protected Stack stack;

    /**
     * The set of libraries found as 'needed'. 
     */
    protected TreeSet resultingSet;

    /**
     * The set defining the direct dependence relation of libraries.
     */
    protected LibraryDependencies libraryDependencies;

    /**
     * Finds identifiers of all libraries those are <i>needed</i> by a given 
     * set of libraries. A library <i>L<sub>x</sub></i>is needed by another
     * library <i>L<sub>y</sub></i> iff there is a sequence of libraries
     * <i>L<sub>1</sub></i>, <i>L<sub>2</sub></i>, ..., <i>L<sub>N</sub></i>
     * where for every <i>i</i>: (1 &lt;= i &lt; N implies that 
     * <i>L<sub>i</sub></i> directly depends on <i>L<sub>i+1</sub></i>) and
     * <i>L<sub>x</sub></i> = <i>L<sub>N</sub></i> and 
     * <i>L<sub>y</sub></i> = <i>L<sub>1</sub></i>. <br>
     * For example: <br>
     *  for libraries <br>
     *      {<i>L<sub>1</sub></i>, <i>L<sub>2</sub></i>}<br>
     *  and dependencies<br>
     *      {<i>L<sub>1</sub></i>, {<i>L<sub>3</sub></i>, <i>L<sub>4</sub></i>}}<br>
     *      {<i>L<sub>2</sub></i>, {<i>L<sub>4</sub></i>}}<br>
     *      {<i>L<sub>4</sub></i>, {<i>L<sub>5</sub></i>}}<br>
     *      {<i>L<sub>7</sub></i>, {<i>L<sub>6</sub></i>}}<br>
     *  the result is <br>
     *      {<i>L<sub>1</sub></i>, <i>L<sub>2</sub></i>, <i>L<sub>3</sub></i>, 
     *       <i>L<sub>4</sub></i>, <i>L<sub>5</sub></i>}<br>
     *
     * @param libIDList
     * @param libraryDependencies 
     *                   this set defines the direct dependence relation
     * @return sorted list of distinct libraries that the given ones need. 
     */
    public LibIDList analyze(LibIDList libIDList, LibraryDependencies libraryDependencies) {
        

        TreeSet startingSet = new TreeSet();
        for(int i = 0; i < libIDList.size(); ++i){
            if (!startingSet.add(libIDList.get(i))) {
                err.println(LibResHandler.getResStr("dependenceanalyzer.warning.library.duplicate") 
                          + libIDList.get(i));
            } 
        }

        if (startingSet.isEmpty()) {
            err.println(LibResHandler.getResStr("dependenceanalyzer.warning.library.no.starting"));
        }

        if (libraryDependencies.isEmpty()) {
            err.println(LibResHandler.getResStr("dependenceanalyzer.warning.dependency.no"));
        } 

        this.libraryDependencies = libraryDependencies;

        for(Iterator it = startingSet.iterator(); it.hasNext(); ) {
            dive((String)(it.next()));
        }
        
        LibIDList result = new LibIDList();
        result.addAll(resultingSet);

        return result;
    }

    /**
     * Constructs a dependence analizer with the specified error stream.
     * Sets <i>stack</i> and <i>resultingSet</i> to be empty. 
     * @param err
     *                 the stream for error messages
     */
    public DependenceAnalyzer(PrintStream err) {
        this.err = err;
        resultingSet = new TreeSet();
        stack = new Stack();
    }

    /**
     * Starts depth-first-traversal with the specified library as a root.
     * After the method returns, <i>resultingSet</i> contains all
     * libraries that the specified one needs. The <i>stack</i> is used 
     * but unchanged. 
     * @param libID
     *                 the dependence relation of libraries.
     */
    protected void dive(String libID) {
        if (stack.search(libID) != -1) {
            err.print(LibResHandler.getResStr("dependenceanalyzer.warning.dependency.cyclic"));
            for(int i = stack.size() - stack.search(libID); i < stack.size(); ++i) {
                err.print(stack.get(i) + " ");
            }
            err.println();
        } else {
            if (resultingSet.add(libID)) {
                stack.push(libID);
                LibraryDependency libraryDependency 
                    = libraryDependencies.get(libID);
                if (libraryDependency != null) {
                    for(int j=0; j < libraryDependency.size(); ++j) {
                        dive(libraryDependency.getString(j));
                    }
                }
                stack.pop();
            }
        }
    }
    
}
