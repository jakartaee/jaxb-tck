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

package com.sun.tgxml.tools.testgen.processors.emitter;

import com.sun.tgxml.tjtf.processors.Processor;
import com.sun.tgxml.tjtf.tools.Shell;
import com.sun.tgxml.tjtf.IRObj;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.util.*;
import java.io.IOException;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;

import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.code.InlineSupportClass;
import com.sun.tgxml.tjtf.api.code.SupportCode;
import com.sun.tgxml.tjtf.api.code.CodeSet;
import com.sun.tgxml.tjtf.api.code.Code;

import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestItem;

import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

import com.sun.tgxml.tools.elgen.ExcludeListCollector;
import com.sun.tgxml.tools.elgen.IncorrectAttributesException;
import com.sun.tgxml.tools.indexgen.api.TestSuite;
import com.sun.tgxml.tools.testgen.LibUtils;
import com.sun.tgxml.tools.testgen.processors.ir.IRPFactory;
import com.sun.tgxml.tools.testgen.processors.ir.IRProcessor;

import com.sun.tgxml.tjtf.tools.options.StandardOptionHandler;


public class EmitterManager extends StandardOptionHandler implements ExcludeListCollector, Generator {

    /**
     * Specify "TestSuite" type of IRObj
     */
    public static final int UNKNOWN = -1;

    /**
     * Specify "TestSuite" type of IRObj
     */
    public static final int TESTSUITE = 0;

    /**
     * Specify "Library" type of IRObj
     */
    public static final int LIBRARY = 1;

    /**
     * Specify "MultiTest" type of IRObj
     */
    public static final int TESTGROUP = 2;

    /**
     * Specify name of system property used by Jmpp to detect
     * a link to copyright file is stored
     */
    protected static final String JMPP_COPYRIGHT_LINK_PROP = "jmpp.tck.copyright";
    
    /**
     * Specify name of testgen property used by emitters to detect
     * a link to copyright file is stored
     */
    protected static final String TESTGEN_COPYRIGHT_LINK_PROP = "copyrightLink";


    protected Shell m_shell;
    protected ExcludeListCollector m_elCollector; 
    protected Properties emitterProperties = null;
    protected CodeCopyGenerator copier = new CodeCopyGenerator();

	public static final String GEN_TYPE_PROPERTY = "GenType";


    /**
     * Generates tests and index files from IRObjects
     * It accepts IRObj of the following types:
     * TestGroup, TestSuite, Library<p>
     * This method:
     * <ul>
     *   <li> looks through passed TestGroups, Libraries to detect which
     *        generators should be used to emit TestGroups, Libraries.
     *   <li> invokes all required TestGroup generators with a set of 
     *        TestGroups and all passed TestSuites.
     *   <li> invokes all required Library generators with a set of 
     *        Libraries.
     *   <li> copies external resources for all  IRObjects
     * </ul>
     * <p>
     * To detect what generator should be used to emit TestGroup or Library
     * this method:
     * <ul>
     *   <li> creates a key for the TestGroup or Library
     *   <li> lools through BuildProperties to find properties starting 
     *        with "testgen.emitter."
     *   <li> finds the best property (from the found ones) for the created key
     *   <li> the value of the best property is a generator class name
     * </ul>
     */
    
    public void generate(IRObj[] trees) throws TestFileException, IOException {
        if (trees == null) return;

        // apply IR processing before emitting
        String property = null;
        if (this.emitterProperties != null) {
            property = emitterProperties.get(GEN_TYPE_PROPERTY)
                       + "." + IRPFactory.IRP_FACTORY_SPI_PROP_NAME;
        }
        if (property == null) {
            property = IRPFactory.IRP_FACTORY_SPI_PROP_NAME;
        }
        IRPFactory irpFactory = IRPFactory.getInstance(property);
        IRProcessor irProcessor = irpFactory.getProcessor(emitterProperties);
        trees = irProcessor.process(trees);

        // list of passed TestSuite id
        ArrayList testSuites = new ArrayList();            
   
        // hash : generator <--> TestGroupList 
        Hashtable generatorsScope = new Hashtable();

        // hash : generator <--> LibraryList 
        Hashtable libraryScope = new Hashtable();
		
		//hash: generator -> set of tdFile. for html conflict mode
        Hashtable tdFileScope = new Hashtable();

        for (int i=0; i<trees.length; i++) {
            int type = detectIRType(trees[i]);

            if (type == LIBRARY) {
                  /* processing Library */
                Generator gen = EmitterFactory.getGenerator(trees[i]);
                if (gen == null) {
                    throw new TestFileException(LibResHandler.getResStr(
                      "testgen.error.generatornotfound", trees[i].toString()));
                }
                if (libraryScope.containsKey(gen)) {
                    ArrayList list = (ArrayList)libraryScope.get(gen);
                    list.add(trees[i]);
                } else {
                    ArrayList list = new ArrayList();
                    list.add(trees[i]);
                    libraryScope.put(gen, list);
                }

            } else if (type == TESTSUITE) {
                  /* processing TestSuite */
                testSuites.add(trees[i]);

            } else if (type == TESTGROUP) {
                Generator gen = EmitterFactory.getGenerator(trees[i]);
                if (gen == null) {
                    throw new TestFileException(LibResHandler.getResStr(
                      "testgen.error.generatornotfound", trees[i].toString()));
                }

                  /* processing TestGroup */

                TestGroup tg = (TestGroup)trees[i];

                /* put tg into the testgroup list of appropriate generator */
                if (generatorsScope.containsKey(gen)) {
                    ArrayList tgList = (ArrayList)generatorsScope.get(gen);
                    tgList.add(tg);
                } else {
                    ArrayList tgList = new ArrayList();
                    tgList.add(tg);
                    generatorsScope.put(gen, tgList);
                }
				
				String tdFile = getTdFile(tg, gen);

                /* put tdFile into the tdFileScope list of appropriate generator */
                if (tdFileScope.containsKey(gen)) {
                    HashSet tdSet = (HashSet) tdFileScope.get(gen);
                    tdSet.add(tdFile);
                } else {
                    HashSet tdSet = new HashSet();
                    tdSet.add(tdFile);
                    tdFileScope.put(gen, tdSet);
                }

		
            } else {
                throw new TestFileException(
                    LibResHandler.getResStr("testgen.error.unknownIR", null));
            }
        }
		
        /* Sort genrators. HtmlConflictModeInsert must be first element in the generator list*/
        ArrayList sortedGenerators = new ArrayList(generatorsScope.keySet());
        Collections.sort(sortedGenerators, new Comparator() {
            public int compare(Object o, Object o1) {
                return o instanceof HtmlConflictModeInsert ? -1 : 1;
            }
        });
		
        /* set html conflict mode properties */
        HashSet conflictFiles = getHtmlConflictFiles(sortedGenerators, tdFileScope);

        /* generating TestGroups from sorted generators */
        for (int i = 0; i < sortedGenerators.size(); i++) {
            Generator gen = (Generator) sortedGenerators.get(i);
            gen.setExcludeListCollector(this);
            gen.setProperties(emitterProperties);
			if (gen instanceof HtmlConflictModeIntf)
				//set conflict fiels. 
				((HtmlConflictModeIntf)gen).setConflictFiles(conflictFiles);
            /* calculate all generator arguments: TestGroups and TestSuites */
            IRObj[] trs = generatorArguments(
                (ArrayList)generatorsScope.get(gen),  // list of TestGroups
                testSuites                            // all passed TestSuites
            );
            gen.generate(trs);
        }

        /* generating Libraries */
        Enumeration generators = libraryScope.keys();
        while(generators.hasMoreElements()) {
            Generator gen = (Generator)generators.nextElement();
            ArrayList libs = (ArrayList)libraryScope.get(gen);
            gen.generate((IRObj[])libs.toArray(new IRObj[0]));
        }

        /* copping external support classes and resourses */
        copier.generate(trees);

    }

    /**
     * throws TestFileException if list.size() > size
     */
    protected void checkListSize(ArrayList list, int size, String errMessage) 
            throws TestFileException {
        if (list.size() > size) {
            StringBuffer sb = new StringBuffer();
            sb.append(list.get(0).toString());
            for (int i = 1; i < list.size(); i++) { 
                sb.append(", " + list.get(i));
            }
            throw new TestFileException(errMessage + sb.toString());
        }
    }

    /**
     * Returns array of IRObj for the generator
     * @param tgList list of TestGroup
     * @param suitesList list of TestSuites
     */
    protected IRObj[] generatorArguments(
        ArrayList tgList, ArrayList suitesList) {
        
        ArrayList args = new ArrayList(tgList.size() + suitesList.size());
        args.addAll(tgList);
        args.addAll(suitesList);
        return (IRObj[])args.toArray(new IRObj[0]);
    }

    
    /**
     * returns type of passed IRObj
     */    
    protected int detectIRType(IRObj value) {
        int type = UNKNOWN;
        if (value instanceof Library) {
            type = LIBRARY;
        } else if (value instanceof TestGroup) {
            type = TESTGROUP;
        } else if (value instanceof TestSuite) {
            type = TESTSUITE;
        }
        return type;
    }
            
    public void generate(IRObj tree) throws TestFileException, IOException{
        IRObj[] trees = {tree};
        generate(trees);
    }
    
    
    public void setExcludeListCollector(ExcludeListCollector collector){
        m_elCollector = collector;
    }
    
    public void setProperties(Properties props) {
        emitterProperties = props;
    }
    
    public Shell getShell() throws TestFileException {
        return m_shell;
    }

    public void setShell(Shell shell) {
        m_shell = shell;
    }
        
    public boolean addEntry(TestCase tc) throws IncorrectAttributesException, IOException {
        if (m_elCollector != null) {
            return m_elCollector.addEntry(tc);
        }
        return true;
    }
    
    public boolean addEntry(TestGroup tg) throws IncorrectAttributesException, IOException  {
        if (m_elCollector != null) {
            m_elCollector.addEntry(tg);
        }
        return true;
    }

    public void setExcludeListFileName(String fileName) {
        if (m_elCollector != null) {
            m_elCollector.setExcludeListFileName(fileName);
        }
    }
    
    public String getExcludeListFileName() {
        if (m_elCollector != null) {
            return m_elCollector.getExcludeListFileName();
        } else {
            return null;
        }
    }
	
	/**
	* Generate test descrioption file name from 
	*/
	protected String getTdFile(TestGroup tg, Generator gen) throws TestFileException {
        String tdFile = LibUtils.getTDFile(tg.getTGAttributes());
        if (tdFile != null && !tdFile.trim().equals("")) {
            tdFile = tdFile.trim();
        } else {
            if (gen instanceof ExternalEmitter) {
                tdFile = tg.getID();
            } else {
                tdFile = "index";
            }
        }
        return tdFile;
    }
	
	/**
	* Generate set of conflict html files. Retruns null If no files are found.
	*/	
	protected HashSet getHtmlConflictFiles(ArrayList sortedGenerators, Hashtable tdFileScope) {
        //for conflict mode mtWriter must be instance of MultiTestWriter and HtmlConflictModeIntf
        if (sortedGenerators.size() == 2 &&
			sortedGenerators.get(0) instanceof HtmlConflictModeInsert &&
            sortedGenerators.get(1) instanceof HtmlConflictModeReplace) {
            HashSet tdSet0 = (HashSet) tdFileScope.get(sortedGenerators.get(0));
            HashSet tdSet1 = (HashSet) tdFileScope.get(sortedGenerators.get(1));
            if ((tdSet0 != null) && (tdSet1 != null)) {
             	//intersection of 2 sets
            	tdSet0.retainAll(tdSet1);
                //now tdSet0 contains tgFiles which exsist in tdSet0 and tdSet1
                if (!tdSet0.isEmpty()) 
					return  new HashSet(tdSet0);
            }
		}
		return null;		
	}
         
	/**
	* Interface for html conflict mode
	*/    
    public interface HtmlConflictModeIntf {
		public static String AFTER_TOPLIST = "<DIV ID=\"AFTER_TOPLIST\"/>";
		public static String AFTER_TABLE = "<DIV ID=\"AFTER_TABLE\"/>";
	
    	public void setConflictFiles(HashSet files);
		public boolean isInConflictMode(String file);		
    }
    
    public interface HtmlConflictModeInsert extends HtmlConflictModeIntf {};
    public interface HtmlConflictModeReplace extends HtmlConflictModeIntf {};
                    
}
