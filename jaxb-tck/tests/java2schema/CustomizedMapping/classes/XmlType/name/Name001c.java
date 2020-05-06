/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.java2schema.CustomizedMapping.classes.XmlType.name.Name001;

import jakarta.xml.bind.annotation.*;

/** 
 * Mapping: Class to Complex Type Definition
 * If class is annotated with @XmlType(name=""), 
 * then class is mapped to an anonymous type.
 */
@XmlRootElement        
@XmlType(name="",propOrder={"b001", "bt01", "d001", "f001", "i001", "l001",
                            "s001", "sh01"})
public class Name001c {
    
    private String  s001;
    private boolean b001;
    private int     i001;
    private byte    bt01;
    private short   sh01;
    private long    l001;
    private float   f001;
    private double  d001;
    
    
    public Name001c() {}

    public String getS001() {
        return s001;
    }

    public void setS001(String s001) {
        this.s001 = s001;
    }

    public boolean isB001() {
        return b001;
    }

    public void setB001(boolean b001) {
        this.b001 = b001;
    }

    public int getI001() {
        return i001;
    }

    public void setI001(int i001) {
        this.i001 = i001;
    }

    public byte getBt01() {
        return bt01;
    }

    public void setBt01(byte bt01) {
        this.bt01 = bt01;
    }

    public short getSh01() {
        return sh01;
    }

    public void setSh01(short sh01) {
        this.sh01 = sh01;
    }

    public long getL001() {
        return l001;
    }

    public void setL001(long l001) {
        this.l001 = l001;
    }

    public float getF001() {
        return f001;
    }

    public void setF001(float f001) {
        this.f001 = f001;
    }
    
   public double getD001() {
        return d001;
    }

    public void setD001(double d001) {
        this.d001 = d001;
    }    
    
}
