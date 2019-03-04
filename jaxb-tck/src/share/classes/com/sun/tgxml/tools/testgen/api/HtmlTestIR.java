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

package com.sun.tgxml.tools.testgen.api;


public interface HtmlTestIR {
    
    /**
     * Sets requirenment of the html anchor generation 
     *
     * @param isNeeded if true the html anchor will be always inserted, 
     *        if false the anchor will be inserted only in case
     *        when TestDescription contains two or more testcases 
     *        or html file contains two or more TestDescriptions.
     *      
     */
    public void setAnchorRequired(boolean isNeeded);

    /**
     * @return true if the html anchor will be always inserted 
     *        false if the anchor will be inserted only in case
     *        when TestDescription contains two or more testcases 
     *        or html file contains two or more TestDescriptions.
     */
    public boolean isAnchorRequired();



    /**
     * Sets the new value of the TestDescriptionIR.
     *
     * @param tdIR the new TestDescriptionIR
     */
    public void setTestDescriptionIR(TestDescriptionIR tdIR);

    /**
     * Returns the TestDescriptionIR.
     */
    public TestDescriptionIR getTestDescriptionIR();

    /**
     * Access method for the test id.
     *
     * @return   the test id
     */
    public String id();

}
