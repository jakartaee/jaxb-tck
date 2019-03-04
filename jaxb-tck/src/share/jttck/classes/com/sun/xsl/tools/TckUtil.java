/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.xsl.tools;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Helper class for providing additional functionality to xslt.
 * @author vs153345
 *
 */
public class TckUtil {

	protected static Logger logger = Logger.getLogger(TckUtil.class.getName());
	
	public static final String PACKAGE_NAME_PREFFIX = "javasoft.sqe.tests";

    /**
     * Java keyword list.
     */
    protected static final String[] javaKeywords = { "abstract", "default",
            "if", "private", "this", "boolean", "do", "implements",
            "protected", "throw", "break", "double", "import", "public",
            "throws", "byte", "else", "instanceof", "return", "transient",
            "case", "extends", "int", "short", "try", "catch", "final",
            "interface", "static", "void", "char", "finally", "long",
            "strictfp", "volatile", "class", "float", "native", "super",
            "while", "const", "for", "new", "switch", "continue", "goto",
            "package", "synchronized", "enum", "assert" };

    protected static String fixJavaKeywords(String pkg) {
		StringTokenizer tokenizer = new StringTokenizer(pkg, ".");
		ArrayList segments = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			String tkn = tokenizer.nextToken();
			if (tkn.length() > 0) {
				// check for Java keyword list
				for (int i = javaKeywords.length; --i >= 0;) {
					if (tkn.equals(javaKeywords[i])) {
						tkn = "_" + tkn;
						break;
					}
				}
				segments.add(tkn);
			}
		}
		StringBuffer result = new StringBuffer();
		for (int i = 2; i < segments.size(); i++)
			result.append("." + segments.get(i));

		return result.length() != 0 ? result.substring(1).toString():result.toString();
	}
	
	public static String getPackage(String path, String schemaPath) {
    	try {
			String preffix =  PACKAGE_NAME_PREFFIX;

			URI testUri = new URI(path);
			URI schemaUri = testUri.resolve(schemaPath);
			
			logger.info(String.format("getPackage: path = '%s', schemaPath = '%s', schemaURI = %s\n", 
					path, schemaPath, schemaUri.toString()));			

			/* 
			 * 1. toLowerCase
			 * 2. Remove .xsd extension
			 * 3. Replace package names started with digits to _digit
			 * 4. Replace all slashes to dots
			 * 5. Replace all non java identifier symbols to '_'
			 */
			String packageName = schemaUri.toString().toLowerCase()
				.replaceAll("\\.xsd$", "")
				.replaceAll("[\\\\/]+", ".")
				.replaceAll("\\.([0-9])", "._$1")
				.replaceAll("[^a-z0-9.]", "_");
			/*
			 * 1. Remove first two parts of the path, 
			 * 2. prepend keyworkds with _,
			 * 3. add prefix 
			 */
			packageName = (preffix + (preffix.length() == 0 ? "" : "."))+fixJavaKeywords(packageName);
			logger.info(String.format("Constructed package: %s", packageName));
			return packageName;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
