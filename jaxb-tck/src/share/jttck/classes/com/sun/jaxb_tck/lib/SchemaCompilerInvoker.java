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
package com.sun.jaxb_tck.lib;

import java.io.PrintStream;

/**
 * Wrapper to invoke a schema(s) compiler in sameJVM mode.
 * 
 * @author Leonid Kuskov
 * @version 1.7
 */
public class SchemaCompilerInvoker extends Invoker {

  // SUN implementation : "com.sun.jaxb_tck.lib.SchemaCompiler"
  protected String schemaCompilerClassName = null;

  // SchemaCompiler's parameters
  private Arguments xsdFiles = new Arguments();
  private String packageName = null;

  /**
   * ctor
   */
  public SchemaCompilerInvoker(String[] args) {
    super(args);
  }

  /**
   * Process SchemaGen's command line arguments.
   */
  @Override
  public void processArguments() throws Invoker.ArgumentException {

    final String prefix = "The schema compiler invoker failed due to ";
    schemaCompilerClassName = args.getValue("-xjc");
    if (schemaCompilerClassName == null) {
      throw new Invoker.ArgumentException(prefix
          + "no class name specified after the -xjc option.");
    }

    args.removeArgs("-xjc", 2);
    args.setArgs(args.getTail("-", false));
    super.processArguments();
    args.removeArgs("-d", 2);
    packageName = args.getValue("-p");

    if (packageName == null) {
      throw new Invoker.ArgumentException(prefix
          + "no target package specified after the -p option.");
    }

    args.removeArgs("-p", 2);

    int n = args.size();
    if (n == 0) {
      throw new Invoker.ArgumentException(prefix
          + "No schema file(s) specified.");
    }
    // Splits patches of java files that could be merged into one argument.
    for (int i = 0; i < n; i++)
      xsdFiles.append(args.get(i).split("\u0085"));
  }

  /**
   * Invokes SchemaCompiler(xjc) with specific options to perform schema
   * compilation.
   * 
   * @param out
   *          A stream to which to report messages and errors
   * @param err
   *          An additional stream to which to write output.
   * @return The result of the command
   */
  @Override
  public int invoke(PrintStream out, PrintStream err) throws Exception {
    Class<?> schemaCompilerClass = Class.forName(schemaCompilerClassName);
    SchemaCompilerTool schemaCompilerTool = SchemaCompilerTool.class
        .cast(schemaCompilerClass.newInstance());
    return schemaCompilerTool.compile(xsdFiles.getArgs(), packageName, outDir,
        out, err);
  }
}
