/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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
package com.sun.ant.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.sun.tgxml.tools.jmppconv.JmppConverterTool;

public class MyBundleJmppConverter {

	static String inDir = null;

	static String outDir = null;

	static String workDir = null;
	
	static Logger log = Logger.getLogger(MyBundleJmppConverter.class.getPackage().getName()); 

	public static void main(String[] args) {
		inDir = args[0];
		outDir = args[1];
		workDir = args[2];
		String dirs = args[3];

		// remove trailing quotation marks
		if (dirs.length() > 0 && dirs.charAt(0) == '"') {
			dirs = dirs.substring(1);
		}
		if (dirs.length() > 0 && dirs.charAt(dirs.length() - 1) == '"') {
			dirs = dirs.substring(0, dirs.length() - 1);
		}

		do {
			int idx = dirs.indexOf(' ');
			String dir = "";
			if (idx == -1) {
				dir = dirs;
				dirs = "";
			} else {
				dir = dirs.substring(0, idx);
				dirs = dirs.substring(idx + 1);
			}
			runFor(new File(args[0], dir));
		} while (dirs.length() > 0);
	}

	private static void runFor(File file) {
		if (!file.isDirectory()) {
			return;
		}

		String[] names;
		names = file.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jmpp");
			}
		});
		Arrays.sort(names);
		for (int i = 0; i < names.length; i++) {
			String outputDir;
			outputDir = file.getPath();
			String name = outputDir + File.separatorChar + names[i];
			outputDir = outDir + File.separatorChar
					+ outputDir.substring(inDir.length());
			// , outputDir.lastIndexOf(File.separatorChar));
			String[] newArgs = { "-w", workDir, "-o", outputDir, name };
			if (isUptodate(outputDir
					+ File.separator
					+ name.substring(name.lastIndexOf(File.separatorChar), name
							.indexOf(".jmpp")), name)) {
				log.info("Skipping: Destination is up to date: "
						+ outputDir
						+ File.separator
						+ name.substring(name.lastIndexOf(File.separatorChar),
								name.indexOf(".jmpp")));
				continue;
			}
			log.info("JmppConverter: file " + name + "\n" + "\t to "
					+ outputDir);
			new JmppConverterTool(System.out, System.err).run(newArgs);
		}

		// recursion
		File[] dirs = file.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory()
						&& !pathname.getName().equals("SCCS");
			}
		});
		Arrays.sort(dirs);
		for (int i = 0; i < dirs.length; i++) {
			runFor(dirs[i]);
		}
	}

	protected static boolean isUptodate(String outputDirName,
			String jmppFileName) {
		File destDir = new File(outputDirName);
		File jmppFile = new File(jmppFileName);
		return destDir.exists()
				&& (destDir.lastModified() > jmppFile.lastModified());
	}
}
