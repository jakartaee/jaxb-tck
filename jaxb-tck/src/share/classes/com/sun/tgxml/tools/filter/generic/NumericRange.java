/*
 * Copyright (c) 2004, 2018 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.tools.filter.generic;

/**
 * The class implement <code>ValueFilter</code> interface and defines
 * the constructor with String[] parameter. The array should contain a
 * two elements and the both elements should be numeric
 * constants. otherwise the IllegalArgumentException is thrown. A
 * first element of the array is low bound and the second element is
 * upper bound. The filter accepts values which are greater than low
 * bound ad smaller than upper bound. If the value is not numeric
 * literal the filter rejects attribute.
 */
public class NumericRange implements ValueFilter {
    private double low;
    private double high;

    public NumericRange(double low, double high) {
        if (low > high) {
            throw new IllegalArgumentException("The upper bound should not be smallet than lower bound");
        }
        this.low = low;
        this.high = high;
    }

    public NumericRange(String[] args) {
        this(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
    }

    public boolean acceptValue(String value) {
        double number = Double.parseDouble(value);
        return (number > low) && (number < high);
    }

    public String toString() {
        return "NumericRange(" + low + ", " + high + ")";
    }
}
