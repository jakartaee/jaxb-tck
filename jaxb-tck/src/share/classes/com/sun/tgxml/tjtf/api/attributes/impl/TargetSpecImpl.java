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

package com.sun.tgxml.tjtf.api.attributes.impl;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.impl.TargetSpecImpl
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
// </importgen>

/**
 * TargetSpecImpl - 
 *
 * <b>TargetSpec</b> describes the specification that a TestGroup, TestCase, 
 *  or Library depends on. TargetSpec describes a spec ID
 *  and version, encoded to describe ranges of specs. 
 * <p>
 * A version is encoded using a <b><em>Major</em></b>, <b><em>Minor</em></b> and <b><em>Maint</em></b>
 * version integers, and <b><em>Upper</em></b> and <b><em>Lower</em></b> boolean modifiers.
 * The <b><em>Upper</em></b> modifier encodes all versions greater than the numbers specified
 * (inclusive), while the <b><em>Lower</em></b> modifier encodes all versions less than the numbers specified
 * (inclusive).
 * <p>
 *  For example: <br><br>
 *  -1.3 <br><br>
 *  represents all versions up to and including version 1.3.
 * <p>
 *  For version 1.1, TargetSpec now can contain TargetSpecElems to describe
 *  a finer grain of spec dependencies.
 *
 * @version 	1.1, 10/28/2002
 * @author  Kevin T. Looney
 */



/*
 * ============================================================================================
 *    TargetSpecImpl 
 * ============================================================================================
 */


public  class TargetSpecImpl implements TargetSpec   {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */
    private static final String  str_modifier = "-";
    private static final String  str_dot = ".";
    private static final char  chr_modifier = '-';
    private static final char  chr_dot = '.';

    /** The list of TargetSpecElems */
    private ArrayList m_SpecElems;

    /** The class name */
    private String m_ID;

    /** The specification major version number */
    private int m_major;

    /** The specification minor version number */
    private int m_minor;

    /** The specification maint version number */
    private int m_maint;

    /** The specification */
    private boolean m_lowerModifier;

    /** The specification */
    private boolean m_upperModifier;

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   
    public boolean isValid(String version) {
	return validateString(version);
    }
   
    static public boolean validateString(String version) {
	return true;
    }
   
    static public TargetSpec create(String name, String version) throws TestFileException {
	if (! validateString(version))
	    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));

	TargetSpec spec = new TargetSpecImpl(name, version);
	return spec;

    }

    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------
    public TargetSpecImpl() {
	init();
    }

    public TargetSpecImpl(String id, String version) {
	setID(id);
	try {
	    setVersion(version);
	} catch ( TestFileException e) {
	    // do something if the version doesn't parse
	}

	init();
    }

    public TargetSpecImpl(String id, String version, ArrayList specelems) {
	this(id, version);
	setTargetSpecElems(specelems);
    }


    private void init() {
	if (m_SpecElems == null)
	    m_SpecElems = new ArrayList();
	else
	    m_SpecElems.clear();
    }


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------


  /**
    *   Get the TargetSpecElem attributes associated with this entity.
    *  <p>
    *   The TargetSpecElem is always an ArrayList with zero or
    *   more TargetSpecElem objects.
    *  <p>
    * @return     The list of repository-specific attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    * @see #setTargeSpecElems
    */
    public ArrayList getTargetSpecElems() {
	return m_SpecElems;
    }
     
   /**
    *   Set the TargetSpecElem attributes associated with this entity.
    *  <p>
    *   The TargetSpecElem is always an ArrayList with zero or
    *   more TargetSpecElem objects.
    *  <p>
    * @param     specelems The list of TargetSpecElem attributes.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    * @see #getTargetSpecElems
    */
    public void setTargetSpecElems(ArrayList specelems) {
	m_SpecElems = specelems;
    }



 
  /**
    *   Get the version (constructed as a string).
    *  <p>
    * @return    A String encoded version.
    */
    public String getVersion() {
	StringBuffer sb = new StringBuffer();

	if (m_lowerModifier)
	    sb.append(str_modifier);

	sb.append(Integer.toString(m_major));
	sb.append(str_dot);
	sb.append(Integer.toString(m_minor));
	if (m_maint > 0) {
	    sb.append(str_dot);
	    sb.append(Integer.toString(m_maint));
	}

	if (m_upperModifier)
	    sb.append(str_modifier);

	return sb.toString();
    }

 
  /**
    *   Get the version (constructed as a string).
    *  <p>
    * @return    A String encoded version.
    */
    public void setVersion(String version) throws TestFileException {
	parse(version, this);
    }
 
  /**
    *   Get the version (constructed as a string).
    *  <p>
    * @return    A String encoded version.
    */
    public static boolean valid(String version) {
	boolean ret = true;
	try {
	    parse(version, null);
	} catch (TestFileException e) {
	    ret = false;
	}

	return ret;
    }

  /**
    *   Get the name associated with this TargetSpec.
    *  <p>
    *   This name must be a valid String.
    *  <p>
    * @return     The filename associated with this data.
    * @see #setID
    */
    public String getID() {
	return m_ID;
    }
     
   /**
    *   Set the ID associated with this data.
    *  <p>
    *   This ID must be either a valid String
    *   or NULL.
    *  <p>
    * @param     id The  filename associated with this data.
    * @see #getID
    */
    public void setID(String id) {
	m_ID = id;
    }



  /**
    *   Get the Major version value.
    *  <p>
    * @return     The (int) major version number.
    * @see #setMajor
    */
    public int getMajor() {
	return m_major;
    }
     
   /**
    *   Set the Major version value.
    *  <p>
    * @param     major The (int) major version number.
    * @see #getMajor
    */
    public void setMajor(int major) {
	m_major = major;
    }


  /**
    *   Get the Major version value.
    *  <p>
    * @return     The (int) major version number.
    * @see #setMinor
    */
    public int getMinor() {
	return m_minor;
    }
     
   /**
    *   Set the Major version value.
    *  <p>
    * @param     major The (int) major version number.
    * @see #getMinor
    */
    public void setMinor(int minor) {
	m_minor = minor;
    }


  /**
    *   Get the Maint version value.
    *  <p>
    * @return     The (int) maintenance version number.
    * @see #setMaint
    */
    public int getMaint() {
	return m_maint;
    }
     
   /**
    *   Set the Minor version value.
    *  <p>
    * @param     maint The (int) maintenance version number.
    * @see #getMaint
    */
    public void setMaint(int maint) {
	m_maint = maint;
    }



  /**
    *   predicate - has the "upper" modifier set.
    *  <p>
    *  When the upper modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @return     true if the upper modifier is set.
    * @see #setUpperModifier
    */
    public boolean isUpperModifierSet() {
	return m_upperModifier;
    }
     
   /**
    *   Set the "upper" modifier set.
    *  <p>
    *  When the upper modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @param     upper The (boolean) upper modifier value.
    * @see #isUpperModifierSet
    */
    public void setUpperModifier(boolean upper) {
	m_upperModifier = upper;
    }


  /**
    *   predicate - has the "lower" modifier set.
    *  <p>
    *  When the lower modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @return     true if the lower modifier is set.
    * @see #setLowerModifier
    */
    public boolean isLowerModifierSet() {
	return m_lowerModifier;
    }
     
   /**
    *   Set the "lower" modifier set.
    *  <p>
    *  When the lower modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @param     lower The (boolean) lower modifier value.
    * @see #isLowerModifierSet
    */
    public void setLowerModifier(boolean lower) {
	m_lowerModifier = lower;
    }


    private static boolean validToken(String token) {
	return (token.equals("-") ||
		token.equals(".") ||
		isWhitespace(token) ||
		isInteger(token) );
    }

   private static boolean isInteger(String token) {
       try {
	   Integer.valueOf(token);
	   return true;
       } catch (NumberFormatException e) {
	   return false;
       }
   }

   private static int getInteger(String token) throws TestFileException {
       try {
	   return Integer.parseInt(token);
       } catch (NumberFormatException e) {
	   throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.numb.ill", token));
       }
   }

   private static boolean isWhitespace(String token) {
	return (token.equals(" ") ||
		token.equals("\t") ||
		token.equals("\n") ||
		token.equals("\r")  );
   }


  /**
    *   Get the spec associated with this TargetSpec.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @return     The specification associated with this data.
    * @see #setSpec
    */
    private static void parse(String version, TargetSpecImpl spec) throws TestFileException {
	boolean lowerModifier = false;
	boolean upperModifier = false;

	// state0 - can accept whitespace, lowmodifier, majornumber.
	// state1 - can accept majornumber.
	// state2 - can accept dot.
	// state3 - can accept minornumber.
	// state4 - can accept himodifier, dot, whitespace (terminating).
	// state5 - can accept whitespace (terminating).
	// state6 - can accept maintnumber.
	// state7 - can accept himodifier, whitespace (terminating).

	int state = 0;

	int major = 0;
	int minor = 0;
	int maint = 0;
	int length = version.length();
	String token = "";

	// clear any space before
	StringTokenizer parser = new StringTokenizer(version, "-. \t\n\r", true);

	while (parser.hasMoreTokens()) {
	    token = parser.nextToken();
	    if (! validToken(token))
		throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
	    switch (state) {

	    case 0:   // can accept whitespace, lowmodifier, majornumber
		if (isWhitespace(token))
		    ;  // accept and consume  whitespaces.
		else if (token.equals("-")) {
		    lowerModifier = true;
		    state = 1;
		} else if (isInteger(token)) {
		    major = getInteger(token);
		    state = 2;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;


	    case 1:   // can accept majornumber
		if (isInteger(token)) {
		    major = getInteger(token);
		    state = 2;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;


	    case 2:   // can accept dot
		if (token.equals(".")) {
		    state = 3;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;

	    case 3:   // can accept minornumber
		if (isInteger(token)) {
		    minor = getInteger(token);
		    state = 4;
		} else 
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;


	    case 4:   // can accept dot, whitespace, himodifier
		if (token.equals(".")) {
		    state = 6;
		} else if (isWhitespace(token)) {
		    state = 5;
		} else if (token.equals("-")) {
		    if (lowerModifier)
			throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		    upperModifier = true;
		    state = 5;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;

	    case 5:   // can accept whitespace
		if (isWhitespace(token)) {
		    state = 5;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;


	    case 6:   // can accept maintnumber
		if (isInteger(token)) {
		    maint = getInteger(token);
		    state = 7;
		} else 
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;


	    case 7:   // himodifier, whitespace (terminating).
		if (isWhitespace(token)) {
		    state = 5;
		} else if (token.equals("-")) {
		    if (lowerModifier)
			throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		    upperModifier = true;
		    state = 5;
		} else
		    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));
		break;
	    }

	}

	// Throw an error if the state is non-terminating
	if (state != 4 && state != 5 && state != 7)
	    throw new TestFileException(LibResHandler.getResStr("api.code.targetspec.vers.ill", version));


	// only set the version if the validate flag is false
	if (spec != null) {
	    spec.setLowerModifier(lowerModifier);
	    spec.setUpperModifier(upperModifier);
	    spec.setMaint(maint);
	    spec.setMinor(minor);
	    spec.setMajor(major);
	}

    }


  


    //------------------------------------------------------------------------------
    //  predicates
    //------------------------------------------------------------------------------

 
     
   /**
    *   predicate - return true if the given spec version is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     spec The specification associated with this data.
    * @see #getSpec
    */
    public boolean inSpec(int major, int minor) {
	return inSpec(major, minor, 0);
    }

    
     
 
     
   /**
    *   predicate - return true if the given spec version is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     spec The specification associated with this data.
    * @see #getSpec
    */
    public boolean inSpec(int major, int minor, int maint) {
	if (major == m_major && minor == m_minor && maint == m_maint )
	    return true;

	if (m_lowerModifier) {
	    if (major < m_major)
		return true;
	    if (major == m_major && minor < m_minor)
		return true;
	    if (major == m_major && minor == m_minor && maint < m_maint)
		return true;
	    return false;
	}

	if (m_upperModifier) {
	    if (major > m_major)
		return true;
	    if (major == m_major && minor > m_minor)
		return true;
	    if (major == m_major && minor == m_minor && maint > m_maint)
		return true;
	    return false;
	}

	return false;

    }

    
     
   /**
    *   predicate - return true if the given TargetSpec is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     spec The specification associated with this data.
    * @see #getSpec
    */
    public boolean inSpec(TargetSpec other) {
	int otMajor = other.getMajor();
	int otMinor = other.getMinor();
	int otMaint = other.getMaint();

	boolean inspec = inSpec(otMajor, otMinor, otMaint);

	if (inspec)
	    return true;

	boolean inOtSpec = other.inSpec(m_major, m_minor, m_maint);

	return inOtSpec;
    }

   
     

}
