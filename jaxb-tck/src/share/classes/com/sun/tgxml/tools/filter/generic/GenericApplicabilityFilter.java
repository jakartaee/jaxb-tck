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


import java.util.ArrayList;
import java.util.Stack;

import com.sun.tgxml.tjtf.api.attributes.RequiredResource;
import com.sun.tgxml.tjtf.api.attributes.TargetSpec;
import com.sun.tgxml.tjtf.api.attributes.TargetSpecElem;
import com.sun.tgxml.tjtf.api.documentation.AssertionRef;
import com.sun.tgxml.tjtf.api.documentation.TestGroupDocumentation;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
import com.sun.tgxml.tjtf.api.tests.Library;
import com.sun.tgxml.tjtf.api.tests.TestCase;
import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tjtf.api.tests.TestGroupComponent;
import com.sun.tgxml.tjtf.api.tests.TestItem;
import com.sun.tgxml.tjtf.tools.BuildProperties;
import com.sun.tgxml.tjtf.tools.UTDVisitorBase;
import com.sun.tgxml.tools.filter.redundancy.ApplicabilityFilter;
import com.sun.tgxml.tools.filter.redundancy.TestItemListFilter;

/**
 * This Filter is designed to be compatible with Test Selection
 * Policy. It defines accept method for each Selection Attribute
 * defined in the Test Selection Policy. The user should override
 * these methods if the appropriate Selection Attribute is used for
 * the Test Selection.<p>
 * The Filter parses Test Description (UTD) and
 * invokes accept method if the Selection Attribute is found in the
 * Test Description.  If the accept method returns false or throws
 * Exception the TestItem is rejected. The TestItem is selected only
 * if all accept methods return true.
 * The parameters of the accept methods are alway non-null values.
 * <p>
 * The result of each accept method is logged in the log file if the
 * log file is defined in the build properties.
 * @author Maxim V. Sokolnikov
 */
public class GenericApplicabilityFilter implements ApplicabilityFilter {

    public static final String LOGGING_SYBSYSTEM_NAME = "com.sun.tgxml.tools.filter.generic";
    public static final String LOGGING_LEVEL = "logging.level";

    public static final String FILTER_PREFIX = "filters";

    protected StatusLogger logger;

    protected String name;
    private boolean acceptanceStatus;
    private StringBuffer statusMessage = new StringBuffer();

    private ArrayList keywords = new ArrayList();
    private String log_id;
    private String testedClass;
    private String testedPackage;
    private FilterVisitor visitor = new FilterVisitor();
    private TestItem inProcess;

    /**
     * creates instance with the given name.
     * @param name
     */
    public GenericApplicabilityFilter(String name) {
        this.name = name;
        logger = new StatusLogger(BuildProperties.getString(getFullPropertyName(LOGGING_LEVEL)));
    }

    public String getName() {
        return this.name;
    }

    /**
     * verifies the TargetSpec.
     * @param spec Non null TargetSpec instance.
     * @return true
     */
    public boolean acceptTargetSpec(TargetSpec spec) throws TestFileException {
        return true;
    }

    /**
     * verifies the TargetSpecElem.
     * @param spec Non null TargetSpec instance.
     * @param name Non null TargetSpecElem name.
     * @param value Non null TargetSpecElem value.
     * @return true
     */
    public boolean acceptTargetSpecElem(TargetSpec spec, String name, String value) {
        return true;
    }

    /**
     * verifies the RequiredResource.
     * @param name Non null RequiredResource name.
     * @param value Non null RequiredResource value.
     * @return true
     */
    public boolean acceptRequiredResource(String name, String value) {
        return true;
    }

    /**
     * verifies Keywords test selection attribute.
     * @param keywords non-null Keywords list
     * @return true
     */
    public boolean acceptKeywords(ArrayList keywords) {
        return true;
    }

    /**
     * verifies AssertionRef test selection attribute.
     * @param assertion non-null assertion.
     * @return true
     */
    public boolean acceptAssertionRef(String assertion) {
        return true;
    }

    /**
     * verifies TestedPackage test selection attribute.
     * @param testedPack non-null package name.
     * @return true
     */
    public boolean acceptTestedPackage(String testedPack) {
        return true;
    }

    /**
     * verifies TestedPackage test selection attribute.
     * @param testedPack non-null package name.
     * @param testedClass non-null class name.
     * @return true
     */
    public boolean acceptTestedClass(String testedPack, String testedClass) {
        return true;
    }

    /**
     * verifies MemberSig test selection attribute.
     * @param testedPack non-null package name
     * @param testedClass non-null class name
     * @param memberSig non-null memberSig
     * @return true
     */
    public boolean acceptMemberSig(String testedPack, String testedClass, String memberSig) {
        return true;
    }

    /**
     * verifies TestedClass values correctness. It throws
     * <code>UTDVerificationException</code> if the TestedClass is non-null and
     * TestedPackage is null.
     * @param testedPack
     * @param testedClass
     */
    protected void verifyTestedClass(String testedPack, String testedClass) {
        if ((testedPack == null) && (testedClass != null)) {
            throw new UTDVerificationException("We do not process anonymous packages.");
        }
    }

    /**
     * verifies MemberSig values correctness. It throws
     * <code>UTDVerificationException</code> if the MemberSig is non-null and
     * TestedPackage or TestedClass is null.
     * @param testedPack
     * @param testedClass
     * @param sig
     */
    protected void verifyMemberSig(String testedPack, String testedClass, String sig) {
        if (sig == null) {
            throw new UTDVerificationException("The signature should be non-null");
        } else if ((testedPack == null) || (testedClass == null)) {
            throw new UTDVerificationException("The testedPackage and testedClass"
                                               + " should be non-null if signature is defined.");
        }
    }

    /**
     * the method walks through TestItem IR and invokes acceptance
     * methods for each found test selection attribute. If at least
     * one acceptance method returns false or throws Exception the
     * method returns false. Otherwise the method returns true.
     * @param item TestItem IR
     */
    public boolean accept(TestItem item) {
        synchronized (this) {
            try {
                clear();
                this.log_id = TestItemListFilter.getGlobalID(item, true);
                findTestedPackageAndTestedClass(item);
                inProcess = item;
                visitor.visit(item);
                if (item instanceof TestGroup) {
                    boolean status = acceptKeywords(keywords);
                    logger.reportKeywordsAcceptStatus(log_id, status, keywords);
                    acceptanceStatus = acceptanceStatus && status;
                }
                return acceptanceStatus;
            } catch (Exception tfe) {
                logger.reportVerificationError(log_id, tfe);
                return false;
            }
        }
    }

    /**
     * This method is required, because the TestCase is not complete.
     * The MemberSig from TestCase requires TestedPackage and TestedClass
     * from enclosing TestGroup. Therefore the UTDVisitor using is not enough.
     * @param item
     */
    private void findTestedPackageAndTestedClass(TestItem item) {
        TestGroup tGroup;
        if (item instanceof TestGroup) {
            tGroup = (TestGroup)item;
        } else if (item instanceof TestGroupComponent) {
            tGroup = ((TestGroupComponent)item).getTestGroup();
        } else {
            return;
        }
        TestGroupDocumentation doc = tGroup.getTGDocumentation();
        if (doc != null) {
            testedPackage = doc.getTestedPackage();
            testedClass = doc.getTestedClass();
        }
    }

    /**
     * returns full name of the build property associated with the given
     * short name from namespace of the current GenericApplicabilityFilter.
     */
    protected String getFullPropertyName(String name) {
        return FILTER_PREFIX + "." + this.name + "." + name;
    }

    /**
     * clears all resources before TestItem processing.
     *
     */
    private void clear() {
        this.keywords.clear();
        this.testedClass = null;
        this.testedPackage = null;
        this.acceptanceStatus = true;
    }

    /**
     * This class walks through <code>TestItem IR</code> and invokes
     * acceptance method or stores found keywords in
     * <code>keywords</code> field. The class overrides visit methods
     * for processing of the test selection attributes.
     */
    private class FilterVisitor extends UTDVisitorBase {

        protected void visit_AssertionRef(AssertionRef ar) throws TestFileException {
            String ref = ar.getRef();
            boolean current = acceptAssertionRef(ref);
            logger.reportAssertionRefAcceptStatus(log_id, current, ref);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_AssertionRef(ar);
        }

        protected void visit_Keyword(String keyword) throws TestFileException {
            keywords.add(keyword);
            super.visit_Keyword(keyword);
        }

        protected void visit_MemberSig(String sig) throws TestFileException {
            if (sig != null) {
                verifyMemberSig(testedPackage, testedClass, sig);
                boolean status = acceptMemberSig(testedPackage, testedClass, sig);
                logger.reportMemberSigAcceptStatus(log_id, status, testedPackage, testedClass, sig);
                acceptanceStatus = acceptanceStatus && status;
            }
            super.visit_MemberSig(sig);
        }

        protected void visit_TargetSpec(TargetSpec spec) throws TestFileException {
            boolean current = acceptTargetSpec(spec);
            logger.reportTargetSpecAcceptStatus(log_id, current, spec);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_TargetSpec(spec);
        }

        protected void visit_TargetSpecElem(TargetSpecElem tse) throws TestFileException {
            String name = tse.getName();
            String value = tse.getValue();
            Stack stack = this.getContextStack();
            // last element on stack is TargetSpecElem.
            TargetSpec spec = (TargetSpec)stack.get(stack.size() - 2);
            boolean current = acceptTargetSpecElem(spec, name, value);
            logger.reportTargetSpecElemAcceptStatus(log_id, current, spec, name, value);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_TargetSpecElem(tse);
        }

        protected void visit_TestedClass(String tc) throws TestFileException {
            testedClass = tc;
            verifyTestedClass(testedPackage, testedClass);
            boolean current = acceptTestedClass(testedPackage, testedClass);
            logger.reportTestedClassAcceptStatus(log_id, current,
                    testedPackage, testedClass);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_TestedClass(tc);
        }

        protected void visit_TestedPackage(String tp) throws TestFileException {
            testedPackage = tp;
            boolean current = acceptTestedPackage(testedPackage);
            logger.reportTestedPackageAcceptStatus(log_id, current, testedPackage);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_TestedPackage(tp);
        }

        protected void visit_RequiredResource(RequiredResource rr) throws TestFileException {
            String name = rr.getName();
            String value = rr.getValue();
            boolean current = acceptRequiredResource(name, value);
            logger.reportRequiredResourceAcceptStatus(log_id, current, name, value);
            acceptanceStatus = acceptanceStatus && current;
            super.visit_RequiredResource(rr);
        }

        /**
         * This method is overridden to avoid processing of TestCase attributes,
         * when the TestGroup is processed.
         */
        protected void visit_TestCase(TestCase tCase) throws TestFileException {
            if (inProcess == tCase) {
                super.visit_TestCase(tCase);
            }
        }

        /**
         * This method is overridden to avoid processing of internal Library attributes,
         * when the TestGroup is processed.
         */
        protected void visit_Library(Library lib) throws TestFileException {
           if (inProcess == lib) {
                super.visit_Library(lib);
            }
        }
    }
}
