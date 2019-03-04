/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.elgen;

import java.io.File;
import java.io.IOException;

/**
  * To support new test monitor exclude list format, a developer
  * should implements this interface, and pass the implementation to the ExcludeListGenerator algorithm
*/
public interface ExcludeListConverter {
   
   /**
    * Implements this method to convert exclude list class to the
    * stream. The stream will be saved in the result file or redirected.
   */
   public void convert(ExcludeList el, File output) throws IOException;
}


