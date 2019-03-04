/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.jmppconv.processors.parser;

import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Vector;
import java.util.StringTokenizer;

import com.sun.tgxml.tjtf.tools.BuildProperties;

/**  
 * Jmpp library for test generation in the api/xml/Schema area, Unicode specific.
 *
 * @author Oleg V. Oleinik
 * @version 1.1
 */

public class JmppLibXMLSchemaUnicode extends com.sun.tgxml.tools.jmppconv.processors.parser.JmppLibXMLSchema {

    public static final String JMPP_EXTERNAL_CLASSES = "jmpp.external.classes";
    public String encoding = null;
       
    public String getEncoding(){
        return encoding;
    }

    public void setEncoding(String enc){
        this.encoding = enc;
    }

    public BufferedWriter createOutputWriter() throws IOException {
        String dir2Create = outputDir;
        super.createOutputWriter();
        if (outputDir==null || outputDir.equals(""))
            dir2Create = ".";
            if (createDirs && dirName!=null && dirName.length()>0)
                dir2Create += File.separator+dirName;
            File outputFile = new File(dir2Create+File.separator+currentFileName);
            BufferedWriter bw = (encoding == null || "".equals(encoding)) ? 
                  new BufferedWriter(new FileWriter(outputFile)) :
                  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), getEncoding()));
            setEncoding(null);
            return bw;
        }

    public void generateProlog(PrintWriter out, String intermediateClassName) {
        out.println("package "+templatePackage+";\n");
        out.println(generateTemplateImports());
        out.println("public class "+intermediateClassName+" extends "+getClass().getName()+" {");
    }
	
    public String generateTemplateImports(){
        Vector externalClasses = getExternalClasses();
        String str = "";
        for (int i = 0; i < externalClasses.size(); ++i){
            str += "import " + externalClasses.get(i) + ";" + "\n";
        }
        return str;
    }

    public Vector getExternalClasses() {
        Vector externalClassesV = new Vector();
        String externalClasses = BuildProperties.getString(JMPP_EXTERNAL_CLASSES);
        if (externalClasses != null && externalClasses.trim().length() > 0){
            StringTokenizer st = new StringTokenizer(externalClasses);
            while (st.hasMoreTokens()) {
                externalClassesV.add(st.nextToken());
            }
        }
        return externalClassesV;
    }
}
