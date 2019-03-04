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

package com.sun.tgxml.tjtf.api.code;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.code.CodeFactory
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.code.impl.*;
import com.sun.tgxml.tjtf.api.data.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.attributes.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
// </importgen>

/**
 * CodeFactory - 
 *
 * <b>CodeFactory</b> is a static factory class for creating code objects (CodeSet, 
 * InlineSupportClass, ExternalSupportClass, SupportCode, TestCode). 
 *<p>
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    CodeFactory
 * ============================================================================================
 */


public  final class CodeFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private CodeFactory () {

    }

    /*
     * ============================================================================================
     *    Member Fields
     * ============================================================================================
     */
    
    
    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   

    //------------------------------------------------------------------------------
    //  Factories
    //------------------------------------------------------------------------------


  /**
    *   Create an (uninitialized) CodeSet object.
    *  <p>
    * @return   a new CodeSet.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public CodeSet  createCodeSet() {
	return new CodeSetImpl();
    }
     
  /**
    *   Create an (cannon) CodeSet object.
    *  <p>
    * @param dependencies The CodeSet's dependencies
    * @param imports The CodeSet's imports
    * @param baseClassName The CodeSet's base class name
    * @param exports The CodeSet's exports
    * @param supportCode The CodeSet's support code
    * @param supportClasses The CodeSet's support classes
    * @param data The CodeSet's data
    * @param context The CodeSet's context
    * @param executeArgs The CodeSet's executeArgs
    * @return   a new CodeSet.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public CodeSet  createCodeSet (ArrayList dependencies, ArrayList imports, String baseClassName, 
		      ArrayList exports, SupportCode supportCode, ArrayList supportClasses, ArrayList data,
		      String context, String executeArgs ) throws TestFileException {
	CodeSet cs = createCodeSet();
	cs.setDependencies(dependencies);
	cs.setImports(imports);
	cs.setBaseClass(baseClassName);
	cs.setExports(exports);
	cs.setSupportCode(supportCode);
	cs.setSupportClasses(supportClasses);
	cs.setData(data);
	cs.setContext(context);
	cs.setExecuteArgs(executeArgs);

	return cs;
    }

     

  /**
    *   Create an (cannon) CodeSet object.
    *  <p>
    * @param dependencies The CodeSet's dependencies
    * @param imports The CodeSet's imports
    * @param baseClassName The CodeSet's base class name
    * @param exports The CodeSet's exports
    * @param supportCode The CodeSet's support code
    * @param supportClasses The CodeSet's support classes
    * @param data The CodeSet's data
    * @return   a new TestGroup.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public CodeSet  createCodeSet (ArrayList dependencies, ArrayList imports, String baseClassName, 
		      ArrayList exports, SupportCode supportCode, ArrayList supportClasses, ArrayList data ) throws TestFileException {

        return createCodeSet(dependencies, imports, baseClassName, 
                exports, supportCode, supportClasses, data, null, null);

    }

  /**
    *   Create an (cannon) CodeSet object.
    *  <p>
    * @param dependencies The CodeSet's dependencies
    * @param imports The CodeSet's imports
    * @param baseClassName The CodeSet's base class name
    * @param supportCode The CodeSet's support code
    * @param supportClasses The CodeSet's support classes
    * @param data The CodeSet's data
    * @return   a new TestGroup.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public CodeSet  createCodeSet (ArrayList dependencies, ArrayList imports, String baseClassName, 
		      SupportCode supportCode, ArrayList supportClasses, ArrayList data ) throws TestFileException {
        return createCodeSet(dependencies, imports, baseClassName, 
                null, supportCode, supportClasses, data, null, null);
    }
     



  /**
    *   Create an (clone) CodeSet object.
    *  <p>
    * @param origCS The CodeSet to be cloned
    * @return   a new (cloned) CodeSet.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public CodeSet  cloneCodeSet (CodeSet origCS ) 
	throws TestFileException {
	if (origCS == null)
	    return null;

	CodeSet cloneCS = createCodeSet();

	// let sub-classes override their clone fields
	cloneOverrideCodeSet(origCS, cloneCS);

	ArrayList origDeps = origCS.getDependencies();
	if (origDeps != null) {
	    ArrayList cloneDeps = new ArrayList();
	    Iterator it = origDeps.iterator();
	    while (it.hasNext()) {
		CodeDependency dep = (CodeDependency) it.next();
		if (dep instanceof LibraryDependency)
		    cloneDeps.add(cloneLibraryDependency((LibraryDependency) dep));
	    }
	    cloneCS.setDependencies(cloneDeps);
	}


	ArrayList origImports = origCS.getImports();
	if (origImports != null) {
	    ArrayList cloneImports = new ArrayList();
	    Iterator it1 = origImports.iterator();
	    while (it1.hasNext()) {
		cloneImports.add((String)it1.next());
	    }
	    cloneCS.setImports(cloneImports);
	}

	cloneCS.setBaseClass(origCS.getBaseClass());
	cloneCS.setSupportCode(cloneSupportCode(origCS.getSupportCode()));


	ArrayList origSupCls = origCS.getSupportClasses();
	if (origSupCls != null) {
	    ArrayList cloneSupCls = new ArrayList();
	    Iterator it2 = origSupCls.iterator();
	    while (it2.hasNext()) {
		SupportClass oSupCls = (SupportClass) it2.next();
		SupportClass cSupCls = null;
		if (oSupCls instanceof InlineSupportClass)
		    cSupCls = cloneInlineSupportClass((InlineSupportClass)oSupCls);
		else if (oSupCls instanceof ExternalSupportClass)
		    cSupCls = cloneExternalSupportClass((ExternalSupportClass)oSupCls);

		cloneSupCls.add(cSupCls);
	    }
	    cloneCS.setSupportClasses(cloneSupCls);
	}


	ArrayList origDats = origCS.getData();
	if (origDats != null) {
	    ArrayList cloneDats = new ArrayList();
	    Iterator it3 = origDats.iterator();
	    while (it3.hasNext()) {
		Data oData = (Data) it3.next();
		Data cData = null;
		if (oData instanceof InlineData)
		    cData = DataFactory.cloneInlineData((InlineData) oData);
		else if (oData instanceof ExternalData)
		    cData = DataFactory.cloneExternalData((ExternalData) oData);

		cloneDats.add(cData);
	    }
	    cloneCS.setData(cloneDats);
	}

	return cloneCS;
    }
     


  /**
    *   Override this method to set fields for sub-classes of CodeSet.
    *  <p>
    * @param origCS The CodeSet to be cloned
    * @param cloneCS The CodeSet clone
    * @throws TestFileException If there is a problem with one of the parameters.
    * @see com.sun.tgxml.tjtf.api.code.CodeSet
    */
    static public void  cloneOverrideCodeSet (CodeSet origCS, CodeSet cloneCS ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) InlineSupportClass object.
    *  <p>
    * @return   a new InlineSupportClass.
    * @see com.sun.tgxml.tjtf.api.code.InlineSupportClass
    */
    static public InlineSupportClass  createInlineSupportClass() {
	return new InlineSupportClassImpl();
    }
     

  /**
    *   Create an (cannon) InlineSupportClass object.
    *  <p>
    * @param langtype The InlineSupportClass's source language
    * @param source The InlineSupportClass's source code
    * @param classID The InlineSupportClass's class ID
    * @param targetName The InlineSupportClass's target name.
    * @return   a new InlineSupportClass.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.InlineSupportClass
    */
    static public InlineSupportClass  createInlineSupportClass (String langtype, String source,
								String classID, String targetName ) throws TestFileException {
	InlineSupportClass isc = createInlineSupportClass();
	isc.setSourceLang(langtype);
	isc.setSource(source);
	isc.setClassID(classID);
	isc.setTargetName(targetName);

	return isc;
    }
     


  /**
    *   Create an (clone) InlineSupportClass object.
    *  <p>
    * @param origISC The InlineSupportClass to be cloned
    * @return   a new (cloned) InlineSupportClass.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.InlineSupportClass
    */
    static public InlineSupportClass  cloneInlineSupportClass (InlineSupportClass origISC ) 
	throws TestFileException {
	if (origISC == null)
	    return null;

	InlineSupportClass cloneISC = createInlineSupportClass();

	// let sub-classes override their clone fields
	cloneOverrideInlineSupportClass(origISC, cloneISC);

	cloneISC.setSourceLang(origISC.getSourceLang());
	cloneISC.setTargetName(origISC.getTargetName());
	cloneISC.setClassID(origISC.getClassID());
	cloneISC.setSource(origISC.getSource());


	return cloneISC;
    }
     


  /**
    *   Override this method to set fields for sub-classes of InlineSupportClass.
    *  <p>
    * @param origISC The InlineSupportClass to be cloned
    * @param cloneISC The InlineSupportClass clone
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.InlineSupportClass
    */
    static public void  cloneOverrideInlineSupportClass (InlineSupportClass origISC, InlineSupportClass cloneISC ) throws TestFileException {

    }





  /**
    *   Create an (uninitialized) ExternalSupportClass object.
    *  <p>
    * @return   a new ExternalSupportClass.
    * @see com.sun.tgxml.tjtf.api.code.ExternalSupportClass
    */
    static public ExternalSupportClass  createExternalSupportClass() {
	return new ExternalSupportClassImpl();
    }
     

  /**
    *   Create an (cannon) ExternalSupportClass object.
    *  <p>
    * @param langtype The ExternalSupportClass's source language
    * @param classID The ExternalSupportClass's class name
    * @param sourceName The ExternalSupportClass's source name.
    * @return   a new ExternalSupportClass.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.ExternalSupportClass
    */
    static public ExternalSupportClass createExternalSupportClass(String langtype, String classID, 
								  String sourceName ) throws TestFileException {
	ExternalSupportClass esc = createExternalSupportClass();
	esc.setSourceLang(langtype);
	esc.setSourceName(sourceName);
	esc.setClassID(classID);

	return esc;
    }
     



  /**
    *   Create an (clone) ExternalSupportClass object.
    *  <p>
    * @param origESC The ExternalSupportClass to be cloned
    * @return   a new (cloned) ExternalSupportClass.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.ExternalSupportClass
    */
    static public ExternalSupportClass  cloneExternalSupportClass (ExternalSupportClass origESC ) 
	throws TestFileException {
	if (origESC == null)
	    return null;

	ExternalSupportClass cloneESC = createExternalSupportClass();

	// let sub-classes override their clone fields
	cloneOverrideExternalSupportClass(origESC, cloneESC);

	cloneESC.setSourceLang(origESC.getSourceLang());
	cloneESC.setSourceName(origESC.getSourceName());
	cloneESC.setClassID(origESC.getClassID());

	return cloneESC;
    }
     


  /**
    *   Override this method to set fields for sub-classes of ExternalSupportClass.
    *  <p>
    * @param origESC The ExternalSupportClass to be cloned
    * @param cloneESC The ExternalSupportClass clone
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.ExternalSupportClass
    */
    static public void  cloneOverrideExternalSupportClass (ExternalSupportClass origESC, ExternalSupportClass cloneESC ) throws TestFileException {

    }




  /**
    *   Create an (uninitialized) SupportCode object.
    *  <p>
    * @return   a new SupportCode.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    static public SupportCode  createSupportCode() {
	return new SupportCodeImpl();
    }
     

  /**
    *   Create an (cannon) SupportCode object.
    *  <p>
    * @param langtype The SupportCode's source language
    * @param source The SupportCode's source code
    * @return   a new SupportCode.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    static public SupportCode  createSupportCode (String langtype, String source ) throws TestFileException {
	SupportCode sc = createSupportCode();
	sc.setSourceLang(langtype);
	sc.setSource(source);

	return sc;
    }
     



  /**
    *   Create an (clone) SupportCode object.
    *  <p>
    * @param origSC The SupportCode to be cloned
    * @return   a new (cloned) SupportCode.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    static public SupportCode  cloneSupportCode (SupportCode origSC ) 
	throws TestFileException {
	if (origSC == null)
	    return null;

	SupportCode cloneSC = createSupportCode();

	// let sub-classes override their clone fields
	cloneOverrideSupportCode(origSC, cloneSC);

	cloneSC.setSourceLang(origSC.getSourceLang());
	cloneSC.setSource(origSC.getSource());

	return cloneSC;
    }
     


  /**
    *   Override this method to set fields for sub-classes of SupportCode.
    *  <p>
    * @param origSC The SupportCode to be cloned
    * @param cloneSC The SupportCode clone
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.SupportCode
    */
    static public void  cloneOverrideSupportCode (SupportCode origSC, SupportCode cloneSC ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) TestCode object.
    *  <p>
    * @return   a new TestCode.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    */
    static public TestCode  createTestCode() {
	return new TestCodeImpl();
    }
     

  /**
    *   Create an (cannon) TestCode object.
    *  <p>
    * @param langtype The TestCode's source language
    * @param source The TestCode's source code
    * @return   a new TestCode.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    */
    static public TestCode  createTestCode (String langtype, String source ) throws TestFileException {
	TestCode tc = createTestCode();
	tc.setSourceLang(langtype);
	tc.setSource(source);

	return tc;
    }
     


  /**
    *   Create an (clone) TestCode object.
    *  <p>
    * @param origTC The TestCode to be cloned
    * @return   a new (cloned) TestCode.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    */
    static public TestCode  cloneTestCode (TestCode origTC ) 
	throws TestFileException {
	if (origTC == null)
	    return null;

	TestCode cloneTC = createTestCode();

	// let sub-classes override their clone fields
	cloneOverrideTestCode(origTC, cloneTC);

	cloneTC.setSourceLang(origTC.getSourceLang());
	cloneTC.setSource(origTC.getSource());

	return cloneTC;
    }
     


  /**
    *   Override this method to set fields for sub-classes of TestCode.
    *  <p>
    * @param origTC The TestCode to be cloned
    * @param cloneTC The TestCode clone
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.TestCode
    */
    static public void  cloneOverrideTestCode (TestCode origTC, TestCode cloneTC ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) LibraryDependency object.
    *  <p>
    * @return   a new LibraryDependency.
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    */
    static public LibraryDependency  createLibraryDependency() {
	return new LibraryDependencyImpl();
    }
     

  /**
    *   Create an (cannon) LibraryDependency object.
    *  <p>
    * @param id The library's identifier
    * @return   a new LibraryDependency.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    */
    static public LibraryDependency  createLibraryDependency (String id ) throws TestFileException {
	LibraryDependency libdep = createLibraryDependency();
	libdep.setID(id);

	return libdep;
    }
     



  /**
    *   Create an (clone) LibraryDependency object.
    *  <p>
    * @param origLD The LibraryDependency to be cloned
    * @return   a new (cloned) LibraryDependency.
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    */
    static public LibraryDependency  cloneLibraryDependency (LibraryDependency origLD ) 
	throws TestFileException {
	if (origLD == null)
	    return null;

	LibraryDependency cloneLD = createLibraryDependency();

	// let sub-classes override their clone fields
	cloneOverrideLibraryDependency(origLD, cloneLD);

	cloneLD.setID(origLD.getID());

	return cloneLD;
    }
     


  /**
    *   Override this method to set fields for sub-classes of LibraryDependency.
    *  <p>
    * @param origLD The LibraryDependency to be cloned
    * @param cloneLD The LibraryDependency clone
    * @throws TestFileException if there is a problem with one of the paramaters.
    * @see com.sun.tgxml.tjtf.api.code.LibraryDependency
    */
    static public void  cloneOverrideLibraryDependency (LibraryDependency origLD, LibraryDependency cloneLD ) throws TestFileException {

    }



}
