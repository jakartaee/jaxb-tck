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

package com.sun.jmpp.lib;

import com.sun.jmpp.lib.jtf.JTF_Slot;
import java.io.PrintWriter;

/**  
 * Jmpp Test Factory (JTF) support class for javasoft.sqe.tests.lang
 * testsuite. Used to generate tests from JTF-based templates.
 *
 * @author Konstantin S. Bobrovsky
 * @version @(#)JmppLibJTF.java	1.9 03/07/23
 */

public class JmppLibJTF extends JmppLibLang {
/** pass number for 2-pass templates */
	protected int passNum = 0;
/** Root slot for a template. */
	public JTF_Slot mainRoot = null;

	public final static int SKIP_NEG_PASS = 0;
	public final static int POS_PASS      = 1;
	public final static int NEG_PASS      = 2;

/*-------->>> 2-pass scheme - related methods --------*/
/** this method should be overloaded in a template
 *  @return the kind of the current combination - positive (true)
 *  or negative (false) */
	public boolean combinationKind() {
		return false;
	}

/** skips negative combinations on the pass when a positive test is generated */
	public void skipNegativeComb() {
		if (passNum == SKIP_NEG_PASS) {
			if (!combinationKind()) {
				skipTest = true;
				generation = true;
			} else {
				passNum = POS_PASS;
				mainRoot.reset();
				mainRoot.genNext();
			}
		}
	}

/** skips positive combinations on the pass when negative tests are generated */
	public void skipPositiveComb() {
		if (passNum == NEG_PASS && combinationKind())
			skipTest = true;
	}

/** @return true if positive test is generated on the current pass,
 *  false otherwise */
	public int pass() {
		return passNum;
	}
/** properly assigns <i>testKind</i> and <i>keywords</i> variables
 *  and resets <i>mainRoot</i> before negative tests generation */
	public void assignKeywords() {
		if (passNum == NEG_PASS) {
			testKind=NEGATIVE;
			keywords = negCompilerKeywords;
		} else if (passNum == POS_PASS) {
			passNum = NEG_PASS;
			mainRoot.reset();
			testKind = POSITIVE;
			keywords = posCompilerKeywords;
		}
	}
/*-----<<< end of 2-pass scheme - related methods ----*/


/** Generates 'prolog' of an intermediate program. */
	public void generateProlog(PrintWriter out, String intermediateClassName) {
		out.println("package "+templatePackage+";\n");
		out.println("import com.sun.jmpp.lib.jtf.*;\n");
		out.println("public class "+intermediateClassName+" extends "+getClass().getName()+" {");
	}


/**
 *   Performs necessary actions before generation of the current test starts.
 *   Among these is calculating the test's name.
 */
	public void composeTest() {
		if (mainRoot == null)
			generationError(log, "mainRoot not initialized.");
		generation = mainRoot.genNext();
		test = className + mainRoot.snapshotSlotTreeState();
		if (!generation)
			skipTest = true;
	}

	public static void main(String[] argv) {
		libMain(argv, new JmppLibJTF());
	}

    /**
     * Substitutes macros in given string with concrete values.
     * @param s the string with macros
     * @param macros macro values
     * @return the string with all macros substituted with given values
     */
    public static String substituteMacro(String s, String[] macros) {
        return JTF_Slot.substituteMacro(s, macros);
    }
}


