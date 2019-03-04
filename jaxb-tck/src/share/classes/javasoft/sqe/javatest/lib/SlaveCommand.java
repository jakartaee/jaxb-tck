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
 *
 * */

package javasoft.sqe.javatest.lib;

import java.io.PrintWriter;
import com.sun.javatest.Command;
import com.sun.javatest.Status;
import com.sun.javatest.agent.ActiveAgentCommand;
import com.sun.javatest.agent.PassiveAgentCommand;

/**
 * A command to that delegates to a JavaTest Slave, now known as a JavaTest Agent.
 * @deprecated
 * @see com.sun.javatest.agent.ActiveAgentCommand
 * @see com.sun.javatest.agent.PassiveAgentCommand
 */
public class SlaveCommand extends Command {
    public Status run(String[] args, PrintWriter log, PrintWriter ref) {
	// if there is a -h/-host or -p/-port specification, it is a passive
	// command, otherwise, it's an active one.
	for (int i = 0; i < args.length && args[i].startsWith("-"); i++) {
	    if (args[i].equals("-h") || args[i].equals("-host") ||
		args[i].equals("-p") || args[i].equals("-port") ) {
		PassiveAgentCommand pac = new PassiveAgentCommand();
		pac.setClassLoader(getClassLoader());
		return pac.run(args, log, ref);
	    }
	}
	ActiveAgentCommand aac = new ActiveAgentCommand();
	aac.setClassLoader(getClassLoader());
	return aac.run(args, log, ref);
    }
}
