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

import java.util.Vector;

/**  
 * Class to be extend for test generation in section javasoft.sqe.tests.lang.
 *
 * @author Oleg V. Ulyankin
 * @version @(#)JmppLibLang.java	1.10 02/01/15
 */

public class JmppLibLang extends JmppLibTest {

/**  
 *   Deprecated variables for keywords. Please, use ones defined in JmppLibTest.
 */
	public static final String negativeCompilationKeywords="compile negative javaCompiler";
	public static final String negativeSerialKeywords="compile serial negative javaCompiler";

	public static final String positiveExecutionKeywords="compile execute positive simple javaCompiler";
	public static final String positiveSerialKeywords="compile serial execute positive simple javaCompiler";
	
	{
		packageHead+="lang";
	}

	public static void main(String[] argv) {
		libMain(argv, new JmppLibLang());
	}

/**
 * Overriden JmppLibTest's method which initializes tdGenerator
 * with the instance of HTMLTDGenerator.
 */
        protected void initTDGenerator(){
            tdGenerator = (TDGenerator) new HTMLTDGenerator();
        }

/**
 *   For backward compatibility.
 */
	public void makeBody()
	{}
	public void makeTest()
	{ if (packageName != null && packageName.length() > 0) L("package "+packageName+";\n"); makeBody(); }
	public void newSource(String packageName, String fileName) 
	{ newSource(true, fileName, defaultExtension); if (packageName != null && packageName.length() > 0) L("package "+packageName+";\n"); }
}

