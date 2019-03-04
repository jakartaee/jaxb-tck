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

package com.sun.tgxml.tools.filter.plugins;

import java.util.ArrayList;

import com.sun.tgxml.tjtf.api.attributes.Attributes;
import com.sun.tgxml.tjtf.api.attributes.AttributesFactory;
import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.api.code.SupportClass;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tools.elgen.ExcludeListUtils;
import com.sun.tgxml.tools.filter.processors.FilterExpression;
import com.sun.tgxml.tools.filter.processors.FilterFactory;
import com.sun.tgxml.tools.filter.processors.NodeTRUE;
import com.sun.tgxml.util.IR;

/**
 * Class to be used for filtering XML Schema tests. All tests are filtered out
 * if a target spec name is 'JAXB_FUTURE' or if a value is '0.0'.
 * 
 * @author Evgueni M. Astigueevitch, Evgueni Rouban
 * @version 1.11
 */
public class XMLSchemaTestFilter extends FilterFactory {

	public FilterExpression cfgRead(String configuration) {
		return createAND(new TargetSpecFilterNode(), new TestTypeFilterNode());
	}
}

/**
 * Test types that are not used in JAXB TCK:
 *   - XMLSchemaTest
 */
class TestTypeFilterNode extends NodeTRUE {
	/**
	 * accepts only acceptable TestGroup items.
	 */
	public boolean accept(TestGroup tg) {
            String testType = IR.getAttrElem(TargetSpecFilterNode.ATTR_TEST_TYPE, tg);
            if (testType != null && testType.equals("XMLSchemaTest")) {
                return false;
            }
            return true;
	}
    
}

class TargetSpecFilterNode extends NodeTRUE {

	public static final String JAXB_NOT_REQUIRED = "JAXB_NOT_REQUIRED";

	public static final String JAXB_FUTURE = "JAXB_FUTURE";

	public static final String JAXB = "JAXB";

	public static final String ATTR_TEST_TYPE = "testType";

	public static final String TEST_TYPE_XML_SCHEMA_TEST = "JAXBXMLSchemaTest";

	public static TargetSpec buildSpec;
	
	static {
		String version = BuildProperties.getString("spec.jaxb.version");
		try {
			buildSpec = AttributesFactory.createTargetSpec(JAXB,
					version);
		} catch (TestFileException e) {
			// seems nowhere should be thrown in execution chain
			e.printStackTrace();
		}
	}
	
	public TargetSpecFilterNode() {
	}

	static TargetSpec findSpec(TestItem ti, String specName) {
		if (ti != null) {
			Attributes attrs = ti.getAttributes();
			if (attrs != null) {
				ArrayList specs = attrs.getTargetSpecs();

				for (int j = specs.size() - 1; j >= 0; j--) {
					TargetSpec s = (TargetSpec) specs.get(j);
					String id = s.getID();
					if (id != null && specName.equals(id.toUpperCase()))
						return s;
				}
			}
		}
		return null;
	}

	/**
	 * determines if the target spec is acceptable, i.e. its minor or major is
	 * not zero.
	 */
	static boolean acceptableVersion(TargetSpec s) {
		return buildSpec.inSpec(s);
	}

	/**
	 */
	public static boolean isExcludedWithTestBug(TestItem ti) {
		String exLine = IR.getAttrElem(ExcludeListUtils.ExcludedAttrElemName,
				ti);
		if (exLine != null) {
			return true;//exLine.indexOf("test_bug") != -1;
		} else {
			return false;
		}
	}

	/**
	 * determines if the TestItem is acceptable, i.e. if it has acceptable JAXB
	 * target spec and has no JAXB_FUTURE target spec.
	 */
	static boolean acceptItem(TestItem ti) {

		if (isExcludedWithTestBug(ti)) {
			return false;
		}

		if (null != findSpec(ti, JAXB_FUTURE)) {
			return false;
		}

		TargetSpec s = findSpec(ti, JAXB);
		return (s == null) ? true : acceptableVersion(s);
	}

	/**
	 * accepts only acceptable TestGroup items.
	 */
	public boolean accept(TestGroup tg) {
		return acceptItem(tg);
	}

	/**
	 * accepts only acceptable TestCase items except for the XMLSchemaTest test
	 * groups: in the JAXB_NOT_REQUIRED test group the only testcase needed is
	 * schema one, because the schema becomes negative for the default operation
	 * mode; such tests will be generated with keywords jaxb_not_required,
	 * schema, negative; the shcema test is represented by a testCase with ID
	 * equal to the group ID.
	 */
	public boolean accept(TestCase tc, TestGroup tg) {
		if (acceptItem(tc)) {
			String testType = IR.getAttrElem(ATTR_TEST_TYPE, tg);
			if (testType != null && testType.equals(TEST_TYPE_XML_SCHEMA_TEST)) {
				if (null != findSpec(tg, JAXB_NOT_REQUIRED)) {
					try {
						return tc.getID().equals(tg.getID());
					} catch (TestFileException tfe) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public FilterExpression getRelevant(SupportClass sClass) {
		return null;
	}

	public FilterExpression getRelevant(Library lib) {
		return null;
	}
}
