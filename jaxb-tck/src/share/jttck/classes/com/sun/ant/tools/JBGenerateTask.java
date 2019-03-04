/*
 * Copyright (c) 2005, 2018 Oracle and/or its affiliates. All rights reserved.
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
import java.util.Arrays;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.sun.jaxb_tck.lib.persistence.JBContentBuild;

/**
 * @author Vladimir Sosnin
 *
 */
public class JBGenerateTask extends Task {
	
	private String packageName;
	private String jbfile;
	private String xmlfile;
	private String compilepath;
	
    public JBGenerateTask() {
		super();
		setTaskName("jbxml-generate");
	}

	/**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

	public String getCompilepath() {
		return compilepath;
	}

	public void setCompilepath(String compilepath) {
		this.compilepath = compilepath;
	}

	public String getJbfile() {
		return jbfile;
	}

	public void setJbfile(String jbfile) {
		this.jbfile = jbfile;
	}

	public String getPackage() {
		return packageName;
	}

	public void setPackage(String packageName) {
		this.packageName = packageName;
	}

	public String getXmlfile() {
		return xmlfile;
	}

	public void setXmlfile(String xmlfile) {
		this.xmlfile = xmlfile;
	}

	@Override
	public void execute() throws BuildException {
		String basedirName = getProject().getProperty("basedir");
		String testGroupName = getProject().getProperty("testgroup");
		String [] args = new String[] {
		        "-package", getPackage(),
		        "-classpath", basedirName + File.separator + getCompilepath(),
		        "-document", getXmlfile(),
		        "-pcontent", getJbfile(),
		        "-TestURL", "file://" + basedirName + File.separator + testGroupName				
		};
		try {
			JBContentBuild.main(args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
