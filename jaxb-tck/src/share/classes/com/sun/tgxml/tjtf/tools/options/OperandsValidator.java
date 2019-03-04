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
 */

package com.sun.tgxml.tjtf.tools.options;

import java.util.ArrayList;

/**
 * Interface of methods to validate operands.
 * Subclasses of these should override the methods
 * of this interface in order to implement their own algorithm of
 * validation.
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */
public interface OperandsValidator {
   
    /**
     * validates operands 
     * @throws ParseArgumentException if operands format is incorrect
     */
    public void validate(ArrayList operands) throws ParseArgumentException;

    /**
     * Returns lines with operands descriptions 
     */
    public String[] getOperandsUsageLines();
}
