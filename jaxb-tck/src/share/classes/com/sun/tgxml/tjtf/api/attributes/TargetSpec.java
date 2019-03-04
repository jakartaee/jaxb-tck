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

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.api.attributes.TargetSpec
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import java.io.PrintStream;
import java.util.ArrayList;
// </importgen>

/**
 * TargetSpec - 
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
 *    TargetSpec 
 * ============================================================================================
 */


public  interface TargetSpec   {

    /*
     * ============================================================================================
     *    Fields
     * ============================================================================================
     */

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */
   
 /**
    *   Determine that a given version string would parse.
    *  <p>
    * @param version    A String encoded version.
    * @return    True if the string is a valid version string.
    * @see #setVersion
    */
    public boolean isValid(String version);


    //------------------------------------------------------------------------------
    //  Getters and Setters
    //------------------------------------------------------------------------------



  /**

    *   Get the (ArrayList) list of TargetSpecElems that this TargetSpec owns.
    *  <p>
    * @return   An (ArrayList) list of TargetSpecElems (or null).
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    * @see java.util.ArrayList
    * @see #setTargetSpecElems
    */
    public ArrayList getTargetSpecElems();
     

     
  /**
    *   Set the (ArrayList) list of TargetSpecElems that this TargetSpec owns.
    *  <p>
    * @param     specelems The (ArrayList) list of TargetSpecElems for this TargetSpec, or NULL.
    * @see com.sun.tgxml.tjtf.api.attributes.TargetSpecElem
    * @see java.util.ArrayList
    * @see #getTargetSpecElems
    */
    public void setTargetSpecElems(ArrayList specelems);


 

  /**
    *   Get the version (constructed as a string).
    *  <p>
    * @return    A String encoded version.
    * @see #setVersion
    */
    public String getVersion();

 
  /**
    *   Set the version.
    *  <p>
    * @param version    A String encoded version.
    * @throws TestFileException if the version doesn't parse
    * @see #getVersion
    * @see #isValid
    */
    public void setVersion(String version) throws TestFileException;
 

  /**
    *   Get the name associated with this TargetSpec.
    *  <p>
    *   This name must be a valid String.
    *  <p>
    * @return     The filename associated with this data.
    * @see #setID
    */
    public String getID();
     
   /**
    *   Set the ID associated with this data.
    *  <p>
    *   This ID must be either a valid String
    *   or NULL.
    *  <p>
    * @param     id The  filename associated with this data.
    * @see #getID
    */
    public void setID(String id);



  /**
    *   Get the Major version value.
    *  <p>
    * @return     The (int) major version number.
    * @see #setMajor
    */
    public int getMajor();
     
   /**
    *   Set the Major version value.
    *  <p>
    * @param     major The (int) major version number.
    * @see #getMajor
    */
    public void setMajor(int major);

  /**
    *   Get the Minor version value.
    *  <p>
    * @return     The (int) minor version number.
    * @see #setMinor
    */
    public int getMinor();
     
   /**
    *   Set the Minor version value.
    *  <p>
    * @param     minor The (int) minor version number.
    * @see #getMinor
    */
    public void setMinor(int minor);


  /**
    *   Get the Maint version value.
    *  <p>
    * @return     The (int) maintenance version number.
    * @see #setMaint
    */
    public int getMaint();
     
   /**
    *   Set the Minor version value.
    *  <p>
    * @param     maint The (int) maintenance version number.
    * @see #getMaint
    */
    public void setMaint(int maint);


  /**
    *   predicate - has the "upper" modifier set.
    *  <p>
    *  When the upper modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @return     true if the upper modifier is set.
    * @see #setUpperModifier
    */
    public boolean isUpperModifierSet();
     
   /**
    *   Set the "upper" modifier set.
    *  <p>
    *  When the upper modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @param     upper The (boolean) upper modifier value.
    * @see #isUpperModifierSet
    */
    public void setUpperModifier(boolean upper);


  /**
    *   predicate - has the "lower" modifier set.
    *  <p>
    *  When the lower modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @return     true if the lower modifier is set.
    * @see #setLowerModifier
    */
    public boolean isLowerModifierSet();
     
   /**
    *   Set the "lower" modifier set.
    *  <p>
    *  When the lower modifier is set, the SpecTarget 
    *  applies to all versions greater than the major.minor version.
    * <p>
    * @param     lower The (boolean) lower modifier value.
    * @see #isLowerModifierSet
    */
    public void setLowerModifier(boolean lower);


    //------------------------------------------------------------------------------
    //  predicates
    //------------------------------------------------------------------------------
  
     
   /**
    *   predicate - return true if the given spec version is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     major The tested major version.
    * @param     minor The tested minor version.
    * @param     maint The tested maint version.
    * @return    true if the (major.minor, maint) version is within this spec.
    */
    public boolean inSpec(int major, int minor, int maint);
    
   /**
    *   predicate - return true if the given spec version is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     major The tested major version.
    * @param     minor The tested minor version.
    * @return    true if the (major.minor) version is within this spec.
    */
    public boolean inSpec(int major, int minor);
    
     
   /**
    *   predicate - return true if the given TargetSpec is within the TargetSpec range.
    *  <p>
    *   This spec must be a valid TargetSpec.
    *  <p>
    * @param     other The other spec to test set overlap.
    * @return    true if the given spec is within this spec.
    */
    public boolean inSpec(TargetSpec other);
}
