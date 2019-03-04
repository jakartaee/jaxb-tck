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

package com.sun.tgxml.tools.testgen.processors.ir;

import java.util.Properties;

/**
 * <b>BasicIRProcessor</b> class that defines set/get properties methods. 
 *
 *
 * @version 	1.0, 21/01/2004
 * @author      Dmitry Fazunenko 
 *
 */

public abstract class BasicIRProcessor implements IRProcessor {

    protected Properties props;

    BasicIRProcessor() {
        this(new Properties());
    }

    BasicIRProcessor(Properties props) {
        setProperties(props);
    }

    /**
     * Sets properties for processor. 
     */
    public void setProperties(Properties props) {
        if (props != null) {
            this.props = props;
        } else {
            this.props = new Properties();
        }
    }

    /**
     * Returns processor properties.
     */
    public Properties getProperties() {
        return props;
    }

    /**
     * Sets property for processor.
     * @param name property name
     * @param value property value
     */
    public void setProperty(String name, String value) {
        props.setProperty(name, value);
    }

    /**
     * Returns processor property with specified name.
     * @param name property name
     */
    public String getProperty(String name) {
        return props.getProperty(name);
    }

}
        
