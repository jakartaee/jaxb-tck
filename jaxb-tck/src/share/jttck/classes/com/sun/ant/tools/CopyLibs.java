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

import com.sun.tgxml.util.MiscUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Copy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The parsing code in <code>libXmlFromFile</code> and 
 * <code>multiReposFiles</code> methods were just copied from
 * <code>com.sun.tgxml.tools.filter.ExternalLibFilter</code>
 * @see com.sun.tgxml.tools.filter.ExternalLibFilter
 */ 
public class CopyLibs extends org.apache.tools.ant.Task {

    private String fn = null;
    private String repository = null;
    private String destination = null;
    String[] repositories = null;
    String[] relativePaths = null;

    public void execute() throws BuildException {
        try {
            File[] files = libXmlFromFile(fn);

            Copy copy = new Copy();
            copy.setTaskName("copy");
            copy.setProject(getProject());
            for (int i = 0; i < files.length; i++) {
                copy.setFile(files[i]);
                copy.setTofile(new File(destination + "libs" + "/" 
                        + new File(repositories[i]).getName() + "/" + relativePaths[i]));
                copy.execute();
            }
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

    public void setLibxmllist(String fn) {
        this.fn = fn;
    }

    public void setRepository(String repository) {
        if (repository.charAt(repository.length() - 1) == '/') {
            this.repository = repository;
        } else {
            this.repository = repository + '/';
        }
    }
    
    public void setDestination(String destination) {
        if (destination.charAt(destination.length() - 1) == '/') {
            this.destination = destination;
        } else {
            this.destination = destination + '/';
        }
    }

    /**
     * Parses libXmlListFile file that contains list of .lib.xml files.
     * Returns array list of xml file names from the file.
     * @throws BuildException when one of lib.xml file either not found 
     *         or have not .lib.xml suffix
     * @throws java.io.IOException if there is some type of IO problem with reading
     *         libXmlListFile
     */
    protected File[] libXmlFromFile(String libXmlListFile)
            throws IOException, BuildException {

        ArrayList names = MiscUtils.parseTextFile(libXmlListFile, true);

        repositories = new String[names.size()];
        relativePaths = new String[names.size()];
        return multiReposFiles(names);
    }

    protected File[] multiReposFiles(ArrayList names)
            throws IOException, BuildException {

        File[] xmls = new File[names.size()];
        StringBuffer badFiles = new StringBuffer();
        for (int i = 0; i < xmls.length; i++) {
            String name = (String) names.get(i);
            int index = name.indexOf(':');
            
            // Singlerepose entry in multyrepose mode
            // available if repository is specified (from the ant build)
            if (index < 0 && name.startsWith(repository)) {
                index = repository.length() - 1;
            }

            if (index > 0) {
                repositories[i] = name.substring(0, index);
                relativePaths[i] = name.substring(index + 1);
                xmls[i] = new File(repositories[i] + File.separator +
                        relativePaths[i]);
                if (!xmls[i].isFile()
                        || !name.endsWith(".lib.xml")) {
                    badFiles.append("    " + xmls[i] + "\n");
                }
            } else {
                badFiles.append("    " + name + "\n");
            }
        }
        if (badFiles.length() == 0)
            return xmls;
        else
            throw new BuildException("The following files:\n" + badFiles +
                    "either not found " +
                    "or have not .lib.xml suffix " +
                    "or do not belong to any repository");
    }
}
