/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.xml.bind;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiTest;

/**
 * Represents a runtime schema generation and a validation the generated schema
 * against a "golden" xml document
 * 
 * execute Arguments: -TestURL (-t) <TestURL> - optional,<br>
 * Test url used as root directory to get xml document<br>
 * -negative (-n) - optional, the xml document is negative<br>
 * -document (-d) - optional, the xml document<br>
 * -out - directory name where generated schema will be created<br> 
 * -class - fully-qualified name of class to load (for setting up JAXBContext)
 * 
 * @author Dmitry Lepekhin
 * @version 1.14
 */

public class J2XRuntimeTest extends SchemaGenTest {

	/**
	 * directory with runtime generated schema(s)
	 */
	protected String outDirName;

	protected File outDir;

	protected ArrayList<File> schemas;

	protected boolean isNegativeGen;

	protected boolean isEmptyOut;

	protected ArrayList<String> classNameList = new ArrayList<String>();

	/**
	 * Decode the next argument in the argument array. All arguments except of
	 * "-srcdir" are decoded in super.decodeArgs
	 * 
	 * @param args
	 *            The array containing all the arguments
	 * @param index
	 *            The position of the next argument to be decoded.
	 * @return the number of elements in the array were "consumed" by this call.
	 * 
	 * @throws MultiTest.SetupException
	 *             is there is a problem decoding the argument.
	 * 
	 */
	@Override
	protected int decodeArg(String[] args, int index) throws MultiTest.SetupException {
		int count = 2;
		if (args[index].equals("-out")) {
			outDirName = getNextArgument(args, index);
		} else if (args[index].equals("-class")) {
			String cl = getNextArgument(args, index);
			classNameList.add(cl);
		} else if (args[index].equals("-j")) {
			// skip it
		} else if (args[index].equals("-neggen")) {
			isNegativeGen = true;
			count = 1;
		} else if (args[index].equals("-empty_output")) {
			isEmptyOut = true;
			count = 1;

		} else {
			return super.decodeArg(args, index);
		}
		return count;
	}

	/**
	 * A setup method called after argument decoding is complete, and before the
	 * test cases are executed. By default, it does nothing; it may be
	 * overridden to provide additional behavior.
	 * 
	 * @throws MultiTest.SetupException
	 *             if processing should not continue. This may be due to some
	 *             inconsistency in the arguments, or if it is determined the
	 *             test should not execute for some reason.
	 */
	@Override
	protected void init() throws MultiTest.SetupException {
		// Check mandatory option.

		// directory name where generated schema will be created
		if (outDirName == null) {
			throw new MultiTest.SetupException("-out option not specified.");
		}
		try {
			outDir = new File(outDirName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!outDir.isDirectory()) {
			throw new MultiTest.SetupException(outDirName + " is not a directory");
		}

		// Check optional options
		String xmlURL = (xmlName == null) ? null : getDocumentURL(xmlName);

		if (xmlURL != null) {
			xmlSource = new StreamSource(xmlURL);
			if (xmlSource == null) {
				throw new MultiTest.SetupException("Could not instantiate a xml source for " + xmlURL);
			}
		}
	}

	/**
	 * The single testcase that performs an validation a generated schema
	 * against a "golden" xml document if they passed.
	 * 
	 */
	public Status compileSchema() {
		Status status = null;
		
		status = generateSchema();

		if (status.isPassed()) {
			if ( ! isEmptyOut ) {
				try {
					schemaSources = super.getSchemaSources(schemas);
				} catch (MultiTest.SetupException se) {
					return Status.failed(se.getMessage());
				}
				status = super.compileSchema();
			}
		} else {
			if (isNegativeGen) {
				status = Status.passed("Test failed as expected. " + status.getReason());
			}
		}
		return status;
	}

	protected Status generateSchema() {
		try {
			ArrayList<Class<?>> classList = new ArrayList<Class<?>>();
			for(String className : classNameList) {
			    try {
			        classList.add(Class.forName(className));
			    } catch (ClassNotFoundException ex) {
			        return Status.failed("Class not found: " + className);
			    }
			}
			
			if (classList.isEmpty()) {
				return Status.failed("No compiled classes specified ");
			}

			J2XOutputResolver resolver = new J2XOutputResolver();
			resolver.setOutdir(outDir);
			JAXBContext context = JAXBContext.newInstance((Class[]) classList.toArray(new Class[classList.size()]));
			context.generateSchema(resolver);
			schemas = resolver.getSchemas();

			if (schemas.isEmpty()) {
				if (isEmptyOut) {
					return Status.passed("No schemas were generated as expected");
				} else {
					return Status.failed("No schemas were generated");
				}
			} else if (isEmptyOut) {
				return Status.failed("Unexpected schemas were generated");
			}
		} catch (Throwable ex) {
			ex.printStackTrace(ref);
			String msg = ex.getMessage();
			return Status.failed("Test failed. " + msg != null ? msg : "");
		}

		return Status.passed("OK");
	}

	/**
	 * command Line entry point.
	 */
	public static void main(String[] args) {
		J2XRuntimeTest schemaGenTest = new J2XRuntimeTest();
		PrintWriter log = new PrintWriter(System.err, true);
		PrintWriter ref = new PrintWriter(System.out, true);
		Status status = schemaGenTest.run(args, log, ref);
		log.flush();
		ref.flush();
		status.exit();
	}
}

class J2XOutputResolver extends SchemaOutputResolver {
	File outdir;

	private ArrayList<File> schemaFiles;

	public J2XOutputResolver() {
		schemaFiles = new ArrayList<File>();
	}

	public void setOutdir(File dir) {
		outdir = dir;
		if (!outdir.exists()) {
			outdir.mkdirs();
		}
	}

	public ArrayList<File> getSchemas() {
		return schemaFiles;
	}

	public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
		File file;
		if (outdir != null) {
			file = new File(outdir, suggestedFileName);
		} else {
			file = new File(suggestedFileName);
		}
		FileWriter writer = new FileWriter(file);
		StreamResult result = new StreamResult(writer);
		result.setSystemId(file);
		schemaFiles.add(file);
		return result;
	}

}
