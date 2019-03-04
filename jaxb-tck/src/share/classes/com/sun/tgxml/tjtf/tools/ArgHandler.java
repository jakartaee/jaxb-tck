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

package com.sun.tgxml.tjtf.tools;

// <importgen> Generated imports for class: com.sun.tgxml.tjtf.tools.ArgHandler
// </importgen>

/**
 * ArgHandler - 
 *
 * <b>ArgHandler</b> is a generic contract for shell sub-classes, and the components that they
 * contain.  ArgHandler describes a delegation model contract such that these objects may
 * participate in decoding and verifying arguments.
 * <p>
 * 
 * <p>
 *
 * @version 	1.0, 04/26/2001
 * @author  Kevin T. Looney
 */


/*
 * ============================================================================================
 *    ArgHandler
 * ============================================================================================
 */


public  interface ArgHandler  {

    /*
     * ============================================================================================
     *    Methods
     * ============================================================================================
     */


  /**
    * Verify any arguments (realized from command-line).
    * <p>
    * This function returns true if all arguments that the handler
    * is responsible for verify.  The handler is responsible for delegating
    * the verify to any "super" shell sub-classes, as well as any components
    * that this class owns:
    * <p>
   *  <code> <pre>
   *     public boolean verifyArguments() {
   *         boolean retval = true;
   *         //
   *         // verify the localargs:
   *         //   retval = verifyMyArg();
   *         //
   *        if (retval != false) 
   *            retval = myComponent.verifyArguments();
   *        if (retval != false) 
   *            retval = super.verifyArguments();
   *        return retval;
   *     }
   *  </pre> </code> 
    *
    * @return false if the overriden method doesn't verify its args.
    */
    public boolean verifyArguments();


  /**
   * Decode command-line arguments.
   * <p>
   * This contract is for a "consumer" interface. That is,
   * the implementer is responsible for recognizing and consuming
   * the topmost arg(s) that it recognuizes.  If the handler does
   * not recognize the arg, it is responsible for delegating the
   * "decode" to any sub-classes, as well as components that this
   * class owns.
   * <p>
   * Shell Handlers should implement this method by doing the following: <br>
   *  <code> <pre>
   *     public int decodeArguments(String args[], int i) {
   *        if (args[i].equals("-myOption")) {
   *             ...
   *             return 1;  // 1 argument was consumed (the argument args[i]).
   *                        // (if it were 2 args, they would be args[i] & args[i+1])
   *        } else {
   *             int consumed = 0;
   *
   *             // First, pass on the arguments to the components 
   *             // that this shell owns.
   *             consumed = myComponent.decodeArguments(args, i);
   *
   *             // Next, pass on the arguments to the this 
   *             // shell's super.
   *             if (consumed == 0)
   *                consumed = super.decodeArguments(args, i);
   *             return consumed;
   *        }
   *     }
   *  </pre> </code> 
   * <p>
   *  Component handlers should just return only the number of consumed args.
   *
   * @param args The command-line argument array.
   * @param i    The current argument index we are examining.
   * @return An integer descibing how many arguments that were consumed on this pass
   */
   public int decodeArguments(String args[], int i);

  }
