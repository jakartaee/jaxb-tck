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

package com.sun.tgxml.tjtf.api.attributes;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.AttributesFactory
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.impl.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
// </importgen>

/**
 * AttributesFactory - 
 *
 * <b>AttributesFactory</b> is a static factory class for creating attribute objects (TestGroupAttributes, 
 * TestCaseAttributes, LibAttributes). 
 *<p> <b>AttributesFactory</b> also creates other contained attribute objects 
 * (AttrElem, RequiredResource, TargetSpec).
 *
 * @version 	1.0, 04/17/98
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    AttributesFactory
 * ============================================================================================
 */


public  final class AttributesFactory {

    /*
     * ============================================================================================
     *    constructors
     * ============================================================================================
     */
    
    private AttributesFactory () {

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
    *   Create an (uninitialized) TestGroupAttributes object.
    *  <p>
    * @return   a new TestGroupAttributes.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    */
    static public TestGroupAttributes  createTestGroupAttributes() {
	return new TestGroupAttributesImpl();
    }
     

  /**
    *   Create an (cannon) TestGroupAttributes object.
    *  <p>
    * @param reqResources The TestGroupAttributes's required resources
    * @param attrElems The TestGroupAttributes's AttrElems
    * @param targetSpecs The TestGroupAttributes's target-specs
    * @param keywords The TestGroupAttributes's keywords
    * @param context The TestGroupAttributes's context
    * @param executeClass The TestGroupAttributes's execute-class
    * @param executeNative The TestGroupAttributes's execute-native args
    * @param executeArgs The TestGroupAttributes's execute-args
    * @param remotes The TestGroupAttributes's remotes
    * @param RMICClasses The TestGroupAttributes's RMIC classes
    * @param selectIfs The TestGroupAttributes's selectIfs
    * @param timeout The TestGroupAttributes's timeout
    * @return   a new TestGroupAttributes.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    */
    static public TestGroupAttributes  createTestGroupAttributes (ArrayList reqResources, ArrayList attrElems, 
								  ArrayList targetSpecs, ArrayList keywords, String context, 
								  String executeClass, String executeNative, String executeArgs, 
								  ArrayList remotes, String RMICClasses, 
								  ArrayList selectIfs, String timeout )  throws TestFileException {
	TestGroupAttributes tga = createTestGroupAttributes();
	tga.setRequiredResources(reqResources);
	tga.setAttrElems(attrElems);
	tga.setTargetSpecs(targetSpecs);
	tga.setKeywords(keywords);
	tga.setContext(context);
	tga.setExecuteClass(executeClass);
	tga.setExecuteNative(executeNative);
	tga.setExecuteArgs(executeArgs);
	tga.setRemotes(remotes);
	tga.setRMICClasses(RMICClasses);
	tga.setSelectIfs(selectIfs);
	tga.setTimeout(timeout);

	return tga;
    }
     


  /**
    *   Create an (clone) TestGroupAttributes object.
    *  <p>
    * @param origTGA The TestGroupAttributes to be cloned
    * @return   a new (cloned) TestGroupAttributes.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    */
    static public TestGroupAttributes  cloneTestGroupAttributes (TestGroupAttributes origTGA ) 
	throws TestFileException {
	if (origTGA == null)
	    return null;

	TestGroupAttributes cloneTGA = createTestGroupAttributes();

	// let sub-classes override their clone fields
	cloneOverrideTestGroupAttributes(origTGA, cloneTGA);

	ArrayList origRRs = origTGA.getRequiredResources();
	if (origRRs != null) {
	    ArrayList cloneRRs = new ArrayList();
	    Iterator it = origRRs.iterator();
	    while (it.hasNext()) {
		cloneRRs.add(cloneRequiredResource((RequiredResource) it.next()));
	    }
	    cloneTGA.setRequiredResources(cloneRRs);
	}


	ArrayList origAE = origTGA.getAttrElems();
	if (origAE != null) {
	    ArrayList cloneAE = new ArrayList();
	    Iterator it1 = origAE.iterator();
	    while (it1.hasNext()) {
		cloneAE.add(cloneAttrElem((AttrElem)it1.next()));
	    }
	    cloneTGA.setAttrElems(cloneAE);
	}


	ArrayList origTSs = origTGA.getTargetSpecs();
	if (origTSs != null) {
	    ArrayList cloneTSs = new ArrayList();
	    Iterator it2 = origTSs.iterator();
	    while (it2.hasNext()) {
		cloneTSs.add(cloneTargetSpec((TargetSpec)it2.next()));
	    }
	    cloneTGA.setTargetSpecs(cloneTSs);
	}


	ArrayList origKeywords = origTGA.getKeywords();
	if (origKeywords != null) {
	    ArrayList cloneKeywords = new ArrayList();
	    Iterator it3 = origKeywords.iterator();
	    while (it3.hasNext()) {
		cloneKeywords.add((String) it3.next());
	    }
	    cloneTGA.setKeywords(cloneKeywords);
	}

	cloneTGA.setContext(origTGA.getContext());
	cloneTGA.setExecuteClass(origTGA.getExecuteClass());
	cloneTGA.setExecuteNative(origTGA.getExecuteNative());
	cloneTGA.setExecuteArgs(origTGA.getExecuteArgs());

	ArrayList origRemotes = origTGA.getRemotes();
	if (origRemotes != null) {
	    ArrayList cloneRemotes = new ArrayList();
	    Iterator it4 = origRemotes.iterator();
	    while (it4.hasNext()) {
		cloneRemotes.add((String) it4.next());
	    }
	    cloneTGA.setRemotes(cloneRemotes);
	}

	ArrayList origSelectIfs = origTGA.getSelectIfs();
	if (origSelectIfs != null) {
	    ArrayList cloneSelectIfs = new ArrayList();
	    Iterator it5 = origSelectIfs.iterator();
	    while (it5.hasNext()) {
		cloneSelectIfs.add((String) it5.next());
	    }
	    cloneTGA.setSelectIfs(cloneSelectIfs);
	}

	cloneTGA.setRMICClasses(origTGA.getRMICClasses());
	cloneTGA.setTimeout(origTGA.getTimeout());

	return cloneTGA;
    }
     


  /**
    *   An override method for cloning extra member fields in a sub-class of a TestGroupAttributes.
    *  <p>
    * @param  origTGA the original TestGroupAttributes (sub-class).
    * @param  cloneTGA the cloned TestGroupAttributes (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestGroupAttributes
    */
    static public void  cloneOverrideTestGroupAttributes (TestGroupAttributes origTGA,
							     TestGroupAttributes cloneTGA ) throws TestFileException {

    }




  /**
    *   Create an (uninitialized) TestCaseAttributes object.
    *  <p>
    * @return   a new TestCaseAttributes.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    */
    static public TestCaseAttributes  createTestCaseAttributes() {
	return new TestCaseAttributesImpl();
    }
     

  /**
    *   Create an (cannon) TestCaseAttributes object.
    *  <p>
    * @param reqResources The TestCaseAttributes's required resources
    * @param attrElems The TestCaseAttributes's AttrElems
    * @param targetSpecs The TestCaseAttributes's target-specs
    * @param timeout The TestCaseAttributes's timeout
    * @return   a new TestCaseAttributes.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    */
    static public TestCaseAttributes  createTestCaseAttributes (ArrayList reqResources, ArrayList attrElems, 
								ArrayList targetSpecs, String timeout )  throws TestFileException {
	TestCaseAttributes tca = createTestCaseAttributes();
	tca.setRequiredResources(reqResources);
	tca.setAttrElems(attrElems);
	tca.setTargetSpecs(targetSpecs);
	tca.setTimeout(timeout);

	return tca;
    }
     



  /**
    *   Create an (clone) TestCaseAttributes object.
    *  <p>
    * @param origTCA The TestCaseAttributes to be cloned
    * @return   a new (cloned) TestCaseAttributes.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    */
    static public TestCaseAttributes  cloneTestCaseAttributes (TestCaseAttributes origTCA ) 
	throws TestFileException {
	if (origTCA == null)
	    return null;

	TestCaseAttributes cloneTCA = createTestCaseAttributes();

	// let sub-classes override their clone fields
	cloneOverrideTestCaseAttributes(origTCA, cloneTCA);

	ArrayList origRRs = origTCA.getRequiredResources();
	if (origRRs != null) {
	    ArrayList cloneRRs = new ArrayList();
	    Iterator it = origRRs.iterator();
	    while (it.hasNext()) {
		cloneRRs.add(cloneRequiredResource((RequiredResource) it.next()));
	    }
	    cloneTCA.setRequiredResources(cloneRRs);
	}


	ArrayList origAE = origTCA.getAttrElems();
	if (origAE != null) {
	    ArrayList cloneAE = new ArrayList();
	    Iterator it1 = origAE.iterator();
	    while (it1.hasNext()) {
		cloneAE.add(cloneAttrElem((AttrElem)it1.next()));
	    }
	    cloneTCA.setAttrElems(cloneAE);
	}


	ArrayList origTSs = origTCA.getTargetSpecs();
	if (origTSs != null) {
	    ArrayList cloneTSs = new ArrayList();
	    Iterator it2 = origTSs.iterator();
	    while (it2.hasNext()) {
		cloneTSs.add(cloneTargetSpec((TargetSpec)it2.next()));
	    }
	    cloneTCA.setTargetSpecs(cloneTSs);
	}

	cloneTCA.setTimeout(origTCA.getTimeout());

	return cloneTCA;
    }
     

  /**
    *   An override method for cloning extra member fields in a sub-class of a TestCaseAttributes.
    *  <p>
    * @param  origTCA the original TestCaseAttributes (sub-class).
    * @param  cloneTCA the cloned TestCaseAttributes (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TestCaseAttributes
    */
    static public void  cloneOverrideTestCaseAttributes (TestCaseAttributes origTCA,
							     TestCaseAttributes cloneTCA ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) LibAttributes object.
    *  <p>
    * @return   a new LibAttributes.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    */
    static public LibAttributes  createLibAttributes() {
	return new LibAttributesImpl();
    }
     

  /**
    *   Create an (cannon) LibAttributes object.
    *  <p>
    * @param reqResources The LibAttributes's required resources
    * @param attrElems The LibAttributes's AttrElems
    * @param targetSpecs The LibAttributes's target-specs
    * @return   a new LibAttributes.
     * @throws TestFileException for invalid args.
   * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    */
    static public LibAttributes  createLibAttributes (ArrayList reqResources, ArrayList attrElems, 
						      ArrayList targetSpecs ) throws TestFileException {
	LibAttributes lib = createLibAttributes();
	lib.setRequiredResources(reqResources);
	lib.setAttrElems(attrElems);
	lib.setTargetSpecs(targetSpecs);
	return lib;
    }
     



  /**
    *   Create an (clone) LibAttributes object.
    *  <p>
    * @param origLibA The LibAttributes to be cloned
    * @return   a new (cloned) LibAttributes.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    */
    static public LibAttributes  cloneLibAttributes (LibAttributes origLibA ) 
	throws TestFileException {

	if (origLibA == null)
	    return null;

	LibAttributes cloneLibA = createLibAttributes();

	// let sub-classes override their clone fields
	cloneOverrideLibAttributes(origLibA, cloneLibA);

	ArrayList origRRs = origLibA.getRequiredResources();
	if (origRRs != null) {
	    ArrayList cloneRRs = new ArrayList();
	    Iterator it = origRRs.iterator();
	    while (it.hasNext()) {
		cloneRRs.add(cloneRequiredResource((RequiredResource) it.next()));
	    }
	    cloneLibA.setRequiredResources(cloneRRs);
	}


	ArrayList origAE = origLibA.getAttrElems();
	if (origAE != null) {
	    ArrayList cloneAE = new ArrayList();
	    Iterator it1 = origAE.iterator();
	    while (it1.hasNext()) {
		cloneAE.add(cloneAttrElem((AttrElem)it1.next()));
	    }
	    cloneLibA.setAttrElems(cloneAE);
	}


	ArrayList origTSs = origLibA.getTargetSpecs();
	if (origTSs != null) {
	    ArrayList cloneTSs = new ArrayList();
	    Iterator it2 = origTSs.iterator();
	    while (it2.hasNext()) {
		cloneTSs.add(cloneTargetSpec((TargetSpec)it2.next()));
	    }
	    cloneLibA.setTargetSpecs(cloneTSs);
	}

	return cloneLibA;
    }
     

  /**
    *   An override method for cloning extra member fields in a sub-class of a LibAttributes.
    *  <p>
    * @param  origLibA the original LibAttributes (sub-class).
    * @param  cloneLibA the cloned LibAttributes (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.LibAttributes
    */
    static public void  cloneOverrideLibAttributes (LibAttributes origLibA,
							     LibAttributes cloneLibA ) throws TestFileException {

    }



  /**
    *   Create an (uninitialized) AttrElem object.
    *  <p>
    * @return   a new AttrElem.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    */
    static public AttrElem  createAttrElem() {
	// Fill in with blank name/value (some other code should set name/values)
	try {
	    return new AttrElemImpl(" ", "");
	} catch (TestFileException e) {
	    return null;
	}
    }
     

  /**
    *   Create an (cannon) AttrElem object.
    *  <p>
    * @param name The AttrElem's name
    * @param value The AttrElem's value
    * @return   a new AttrElem.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    */
    static public AttrElem  createAttrElem (String name, String value ) throws TestFileException {
	AttrElem ae = createAttrElem();
	ae.setName(name);
	ae.setValue(value);
	return ae;
    }



  /**
    *   Create an (clone) AttrElem object.
    *  <p>
    * @param origAE The AttrElem to be cloned
    * @return   a new (cloned) AttrElem.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    */
    static public AttrElem  cloneAttrElem (AttrElem origAE ) 
	throws TestFileException {

	if (origAE == null)
	    return null;

	AttrElem cloneAE = createAttrElem();

	cloneAE.setName(origAE.getName());
	cloneAE.setValue(origAE.getValue());

	return cloneAE;
    }
     


  /**
    *   An override method for cloning extra member fields in a sub-class of a AttrElem.
    *  <p>
    * @param  origAE the original AttrElem (sub-class).
    * @param  cloneAE the cloned AttrElem (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.AttrElem
    */
    static public void  cloneOverrideAttrElem (AttrElem origAE,
							     AttrElem cloneAE ) throws TestFileException {

    }

  /**
    *   Create an (uninitialized) RequiredResource object.
    *  <p>
    * @return   a new RequiredResource.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public RequiredResource  createRequiredResource() {
	// Fill in with blank name/value (some other code should set name/values)
	try {
	    return new RequiredResourceImpl(" ", "");
	} catch (TestFileException e) {
	    return null;
	}
    }
     

  /**
    *   Create an (cannon) RequiredResource object.
    *  <p>
    * @param name The RequiredResource's name
    * @param value The RequiredResource's value
    * @return   a new RequiredResource.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public RequiredResource  createRequiredResource (String name, String value ) throws TestFileException {
	RequiredResource rr = createRequiredResource();
	rr.setName(name);
	rr.setValue(value);
	return rr;
    }



  /**
    *   Create an (clone) RequiredResource object.
    *  <p>
    * @param origRR The RequiredResource to be cloned
    * @return   a new (cloned) RequiredResource.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public RequiredResource  cloneRequiredResource (RequiredResource origRR ) 
	throws TestFileException {
	if (origRR == null)
	    return null;

	RequiredResource cloneRR = createRequiredResource();

	cloneRR.setName(origRR.getName());
	cloneRR.setValue(origRR.getValue());

	return cloneRR;
    }
     


  /**
    *   An override method for cloning extra member fields in a sub-class of a RequiredResource.
    *  <p>
    * @param  origRR the original RequiredResource (sub-class).
    * @param  cloneRR the cloned RequiredResource (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public void  cloneOverrideRequiredResource (RequiredResource origRR,
							     RequiredResource cloneRR ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) TargetSpec object.
    *  <p>
    * @return   a new TargetSpec.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    */
    static public TargetSpec  createTargetSpec() {
	return new TargetSpecImpl();
    }
     

  /**
    *   Create an (cannon) TargetSpec object.
    *  <p>
    * @param name The TargetSpec's name
    * @param version The TargetSpec's version
    * @return   a new TargetSpec.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    */
    static public TargetSpec  createTargetSpec(String name, String version ) throws TestFileException {
	TargetSpec ts =  createTargetSpec();
	ts.setID(name);
	ts.setVersion(version);
	return ts;
    }

  /**
    *   Create an (cannon) TargetSpec object.
    *  <p>
    * @param name The TargetSpec's name
    * @param version The TargetSpec's version
    * @return   a new TargetSpec.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    */
    static public TargetSpec  createTargetSpec(String name, String version, ArrayList targetSpecElems ) throws TestFileException {
	TargetSpec ts =  createTargetSpec();
	ts.setID(name);
	ts.setVersion(version);
	ts.setTargetSpecElems(targetSpecElems);
	return ts;
    }

 

  /**
    *   Create an (clone) TargetSpec object.
    *  <p>
    * @param origTS The TargetSpec to be cloned
    * @return   a new (cloned) TargetSpec.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpec
    */
    static public TargetSpec  cloneTargetSpec (TargetSpec origTS )  throws TestFileException {
	if (origTS == null)
	    return null;

	TargetSpec cloneTS = createTargetSpec();

	cloneTS.setID(origTS.getID());
	cloneTS.setVersion(origTS.getVersion());

	ArrayList origTSE = origTS.getTargetSpecElems();
	if (origTSE != null) {
	    ArrayList cloneTSE = new ArrayList();
	    Iterator it1 = origTSE.iterator();
	    while (it1.hasNext()) {
		cloneTSE.add(cloneTargetSpecElem((TargetSpecElem)it1.next()));
	    }
	    cloneTS.setTargetSpecElems(cloneTSE);
	}


	return cloneTS;
    }
     

  /**
    *   An override method for cloning extra member fields in a sub-class of a TargetSpec.
    *  <p>
    * @param  origTS the original TargetSpec (sub-class).
    * @param  cloneTS the cloned TargetSpec (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public void  cloneOverrideTargetSpec (TargetSpec origTS, TargetSpec cloneTS ) throws TestFileException {

    }


  /**
    *   Create an (uninitialized) TargetSpecElem object.
    *  <p>
    * @return   a new TargetSpecElem.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    */
    static public TargetSpecElem  createTargetSpecElem() {
	return new TargetSpecElemImpl();
    }
     

  /**
    *   Create an (cannon) TargetSpecElem object.
    *  <p>
    * @param name The TargetSpecElem's name
    * @param value The TargetSpecElem's version
    * @return   a new TargetSpecElem.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    */
    static public TargetSpecElem  createTargetSpecElem(String name, String value ) throws TestFileException {
	TargetSpecElem ts =  createTargetSpecElem();
	ts.setName(name);
	ts.setValue(value);
	return ts;
    }



  /**
    *   Create an (clone) TargetSpecElem object.
    *  <p>
    * @param origTS The TargetSpecElem to be cloned
    * @return   a new (cloned) TargetSpecElem.
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    */
    static public TargetSpecElem  cloneTargetSpecElem (TargetSpecElem origTS )  throws TestFileException {
	if (origTS == null)
	    return null;

	TargetSpecElem cloneTS = createTargetSpecElem();

	cloneTS.setName(origTS.getName());
	cloneTS.setValue(origTS.getValue());

	return cloneTS;
    }
     

  /**
    *   An override method for cloning extra member fields in a sub-class of a TargetSpecElem.
    *  <p>
    * @param  origTS the original TargetSpecElem (sub-class).
    * @param  cloneTS the cloned TargetSpecElem (sub-class).
    * @throws TestFileException for invalid args.
    * @see com.sun.tgxml.tjtf.api.attributes.RequiredResource
    */
    static public void  cloneOverrideTargetSpecElem (TargetSpecElem origTS, TargetSpecElem cloneTS ) throws TestFileException {

    }


}
