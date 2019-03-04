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

package javasoft.sqe.jck.lib;

import java.net.URI;
import java.net.URISyntaxException;

public class URIValue {

    /**
     * Implementation of MultiTestExt.ArgumentValue class for parameters of
     * type 'URI'.
     */
    public static class Argument extends MultiTestExt.ArgumentValue<URI> {
        /**
         * reads URI
         */
        public int parseValue(String[] argv) {
            StringValue.Argument str = new StringValue.Argument();
            int count = str.parseValue(argv);
            try {
                set(new URI(str.get()));
            } catch (URISyntaxException e) {
                throw new InvalidValue(e.toString());
            }
            return count;
        }
    }

    /**
     * Implementation of MultiTestExt.ParameterValue class for parameters of
     * type 'URI'.
     */
    public static class Parameter extends MultiTestExt.ParameterValue<URI> {

        public Parameter(String name) {
            super(name);
        }

        public int parseValue(String[] argv) {
            return parseValue(argv, new Argument());
        }
    }
}
