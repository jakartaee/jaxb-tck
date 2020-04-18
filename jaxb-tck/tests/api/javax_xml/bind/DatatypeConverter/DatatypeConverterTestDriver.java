/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
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

package javasoft.sqe.tests.api.jakarta.xml.bind.DatatypeConverter;

import java.io.PrintWriter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import javasoft.sqe.javatest.Status;

public class DatatypeConverterTestDriver extends XmlAdapter<String,String>{

    public static class Test{
        protected PrintWriter _ref;

        public Test(PrintWriter ref){
            _ref = ref;
        }

        public Status test(){
            return Status.passed("Ok");
        }

        PrintWriter getRef(){
            return _ref;
        }
    }

    private static Test __test;
    private static Status __status;
    private static Throwable __throwable;

    public static void init(Test test){
        __test = test;
        __status = null;
        __throwable = null;
    }


    public static Test getTest(){
        return __test;
    }

    public String unmarshal(String value) {
        try{
            __status  = __test.test();
        }catch(Throwable t){
            t.printStackTrace(__test.getRef());
            __throwable = t;
        }
        return value;
    }

    public String marshal(String value) {
        return value.toString();
    }


    public static Status returnStatus(){
        if(__throwable != null){
            return Status.failed("Unexpected exception: "+__throwable);
        }
        return __status;
    }

}
