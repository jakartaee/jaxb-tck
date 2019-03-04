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

package com.sun.jaxb_tck.lib;

import java.io.PrintWriter;
import java.io.PrintStream;

import com.sun.javatest.Command;
import com.sun.javatest.util.WriterStream;

import com.sun.javatest.Status;

public class JaxbCommand extends Command {

    private static final byte TOOL_NOT_SELECTED = -1;

    private static final Status[] PASSED = {
            Status.passed("Compilation successful"),
            Status.passed("Generation successful") };

    private static final Status[] FAILED = {
            Status.failed("Compilation failed"),
            Status.failed("Generation failed") };

    /**
     * The method that that does the work of the command.
     *
     * @param args
     *            Command-specific options and arguments
     * @param out1
     *            A stream to which to report messages and errors. This stream
     *            was previously called "log".
     * @param out2
     *            An additional stream to which to write output. This stream was
     *            previously called "ref".
     * @return The result of the command
     */
    @Override
    public Status run(String[] args, PrintWriter out1, PrintWriter out2) {

        if (args == null || args.length == 0) {
            return Status.error("No args supplied.");
        }

        int toolIndex = Arguments.toArguments(args).contains("-xjc") ? 0
                : TOOL_NOT_SELECTED;

        if (toolIndex == TOOL_NOT_SELECTED) {
            toolIndex = Arguments.toArguments(args).contains("-jxc") ? 1
                    : TOOL_NOT_SELECTED;
        }

        if (toolIndex == TOOL_NOT_SELECTED) {
            return Status
                    .error(" Either argument '-xjc' or '-jxc' must be specified.");
        }

        int rc;
        PrintStream errStream = new PrintStream(new WriterStream(out1));
        PrintStream outStream = new PrintStream(new WriterStream(out2));

        try {
            rc = toolIndex == 0 ? new Xjc(args).execute(outStream, errStream):
            					            new Jxc(args).execute(outStream, errStream);
            return (rc == 0 ? PASSED[toolIndex] : FAILED[toolIndex]);
        } catch (Invoker.ArgumentException ae) {
            return Status.error(ae.getMessage());
        } catch (Invoker.InvokeException ie) {
            return Status.error(ie.getMessage());
        } catch (InterruptedException ie) {
          ie.printStackTrace(errStream);
          return Status.failed("Test interupted. Increase the time limit in the time factor field.");
        } catch (Exception ex) {
        	ex.printStackTrace(errStream);
        	String msg = ex.getMessage();
        	if(msg == null)
        		msg = "Unexpected exception was thrown " + ex.toString();
            return Status.failed(msg);
        } finally {
            errStream.flush();
            outStream.flush();
        }
    }

    public static void main(String[] args) {
        Status res = new JaxbCommand().run(args, new PrintWriter(System.out),
                new PrintWriter(System.err));
        System.exit(res.getType());
    }
}
