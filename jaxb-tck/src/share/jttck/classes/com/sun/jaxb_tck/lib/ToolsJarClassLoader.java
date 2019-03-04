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

package com.sun.jaxb_tck.lib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * {@link ClassLoader} that can load classes from JDK tools.jar in sameJVM mode 
 * even if it is not presented in classpath.
 *
 * @version 1.2
 */
public class ToolsJarClassLoader extends URLClassLoader {

	public static class ToolsJarNotFoundException extends Exception implements
			Serializable {
		private static final long serialVersionUID = -4903383150601334662L;

		public ToolsJarNotFoundException(String path) {
			super("JDK''s tools.jar was not found in " + 
			       path + 
			       ". Usually this means you are running JRE, not JDK. Please use the java command in JDK 5.0 or later (not JRE.)");
		}
	}

	/**
	 * List of tools.jar package prefixes 
	 */
	private String[] packagePrefixes = { "com.sun.tools", "com.sun.mirror" };

	public ToolsJarClassLoader(ClassLoader parent) throws ToolsJarClassLoader.ToolsJarNotFoundException {
		super(getToolsJar(parent), parent);
		if (getURLs().length == 0)
			// if tools.jar was found in our classloader, no need to create
			// a parallel classes
			this.packagePrefixes = new String[0];
	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {

		for (String prefix : packagePrefixes) {
			if (className.startsWith(prefix)) {
				// we need to load those classes in this class loader
				// without delegation.
				return findClass(className);
			}
		}
		return super.loadClass(className);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		StringBuilder sb = new StringBuilder(name.length() + 6);
		sb.append(name.replace('.', '/')).append(".class");

		InputStream is = getResourceAsStream(sb.toString());
		if (is == null)
			throw new ClassNotFoundException("Class not found " + sb);

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) >= 0)
				baos.write(buf, 0, len);

			buf = baos.toByteArray();

			// define package if not defined yet
			int i = name.lastIndexOf('.');
			if (i != -1) {
				String pkgname = name.substring(0, i);
				Package pkg = getPackage(pkgname);
				if (pkg == null)
					definePackage(pkgname, null, null, null, null, null, null, null);
			}

			return defineClass(name, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(name, e);
		}
	}

	public static boolean canLoadToolsJar(ClassLoader cl) {
		try {
			Class.forName("com.sun.tools.javac.Main", false, cl);
			return true;
		} catch (ClassNotFoundException e) {
		}
		return false;
	}

	/**
	 * Returns a class loader that can load classes from JDK tools.jar.
	 * @param parent
	 */
	private static URL[] getToolsJar(ClassLoader parent) throws ToolsJarClassLoader.ToolsJarNotFoundException {
		if (canLoadToolsJar(parent)) {
			return new URL[0];
			// we can already load them in the parent class loader.
		}
		// otherwise try to find tools.jar
		File jreHome = new File(System.getProperty("java.home"));
		File toolsJar = new File(jreHome.getParent(), "lib/tools.jar");

		if (!toolsJar.exists()) {
			throw new ToolsJarClassLoader.ToolsJarNotFoundException(toolsJar.getPath());
		}
		try {
			return new URL[] { toolsJar.toURI().toURL() };
		} catch (MalformedURLException e) {
			// impossible
			throw new AssertionError(e);
		}
	}
}

