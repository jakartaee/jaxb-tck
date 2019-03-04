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

package javasoft.sqe.jck.lib;

public class ArrayStringValue {
	
	public static class Argument extends MultiTestExt.ArgumentValue<String[]> {
		public int parseValue(String[] argv) {
			int l =argv.length;
			if(l < 1)
			      throw new InvalidValue("arguments array is empty");
            set(argv);
			return l;
		}
	}

    public static class Parameter extends MultiTestExt.ParameterValue<String[]> {
        public Parameter(String name) {
            super(name);
        }

        public int parseValue(String[] argv) {
            return parseValue( argv, new Argument() );
        }
    }
}
