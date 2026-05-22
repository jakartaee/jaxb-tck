/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
package com.sun.jck.lib;

abstract class BinaryExpr extends Expr {
    BinaryExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    Expr order() {
        if (precedence() > left.precedence() && left instanceof BinaryExpr e) {
            left = e.right;
            e.right = order();
            return e;
        } else {
            return this;
        }
    }

    protected Expr left;
    protected Expr right;
}
