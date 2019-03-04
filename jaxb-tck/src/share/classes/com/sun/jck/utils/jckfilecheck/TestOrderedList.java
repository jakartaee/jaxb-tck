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
 *
 * */

package com.sun.jck.utils.jckfilecheck;

import java.io.PrintStream;
import javasoft.sqe.harness.Status;
import javasoft.sqe.harness.Test;

import java.util.Enumeration;

/* Auxillary class used for testing */
public class TestOrderedList implements Test {

	/* Entry point for a test routine */
	public static void main( String argv[] ) {
		TestOrderedList test = new TestOrderedList( );
		Status status = test.run( argv, System.err, System.out );
		status.exit( );
	}

	public Status run( String argv[], PrintStream log, PrintStream out ) {
		OrderedList ol = new OrderedList( 20 );
		TestString lastTestString = null;
		TestString testString = null;

		ol.setMsgLevel( 2 );
		ol.addNode( new TestString( "Kevin" ) );
		ol.addNode( new TestString( "Paul" ) );
		ol.addNode( new TestString( "george" ) );
		ol.addNode( new TestString( "ABC" ) );
		ol.addNode( new TestString( "efg" ) );

		Enumeration iter = ol.getIterator();
		
		while( iter.hasMoreElements() ) {
			testString = (TestString) iter.nextElement();
			out.println( testString );
			if( ( lastTestString != null ) && 
				( testString.compareTo( lastTestString ) <= 0 ) ){
				return Status.failed( "this is not greater than last" );
			}
			lastTestString = testString;
		}

		return Status.passed( "OKAY" );
	}

}

class TestString implements Comparable {

	public String value;

	public TestString( String val ) {
		value = val;
	}

	public int compareTo( Comparable obj ) {
		if( ! ( obj instanceof TestString ) ){
			return -1;
		}
		else{
			return value.compareTo( ( (TestString)obj ).value );
		}
	}

	public String toString() {
		return value.toString();
	}
}
