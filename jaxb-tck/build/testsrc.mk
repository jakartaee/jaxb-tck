#
# Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
# 
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# http://www.eclipse.org/legal/epl-2.0.
# 
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the
# Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
# version 2 with the GNU Classpath Exception, which is available at
# https://www.gnu.org/software/classpath/license.html.
# 
# SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
# 

#
# Partial Makefile for TCK tests

$(TCKDIR)/tests $(TESTEXTRACTDIR):
	$(TEST) -d $@ || $(MKDIR) -p $@

split-testsources-dirs.ok: testsources-dirs.lst
	if [ -d xml-bundles ] ; then \
		$(RM) xml-bundles ; \
	fi
	$(MKDIR) -p xml-bundles
	if [ -s testsources-dirs.lst ] ; then \
		$(SPLIT) -$(XML_DIRS_IN_BUNDLE) -a 4 testsources-dirs.lst xml-bundles/; \
		for f in `$(LS) -1 xml-bundles/` ; do \
			$(MV) xml-bundles/$$f xml-bundles/$$f-xml-bundle.lst; \
		done \
	fi
	echo ":-) splitted testsources-dirs.lst at `date`" > $@

CLEANFILES += split-testsources-dirs.ok

XML_BUNDLES.sh= \
	if [ -d xml-bundles ] ; then \
		for f in `ls -1 xml-bundles/ | grep "\.lst" | cat` ; do \
			echo xml-bundles/$$f ; \
		done \
	fi
XML_BUNDLES=$(shell $(XML_BUNDLES.sh))
#XML_BUNDLESOK=$(XML_BUNDLES:%.lst=%.ok)
#XML_BUNOK=$(patsubst %.lst,%.ok,$(XML_BUNDLES))
testsources.ok: ../tck.properties precompile-testfilter-plugin.ok \
		precompile-bundle-testgenfilter.ok tck.jtx split-testsources-dirs.ok \
		$(TCKDIR)/tests $(TESTEXTRACTDIR) xml-bundles-extracted.ok \
		$(SHARED_ENV_MK)
	echo ":-) generated all test sources at `date`" > $@

# workaround for compiler bug: package-info classes that were compiled with Java6 and -target 1.5 are invalid for Java1.5
package_info.ok: testsources.ok package_info.lst
	$(TEST) -s package_info.lst && \
	CLASSPATH=$(TCKDIR)/classes:classes:$(JAVATEST_JAR):$(JAXB_LIBS) \
	$(GENERAL_JAVAC) -d $(TCKDIR)/classes `find $(TCKDIR)/tests/java2schema -name "package-info.java"`; \
	echo "package-info classes recompiled as `date`" > $@
package_info.lst:
	$(TEST) ! -d $(TCKDIR)/tests/java2schema || find $(TCKDIR)/tests/java2schema -name "package-info.java" > $(@)
ZIP.files += package_info.ok
#$(XML_BUNDLESOK):
#	echo I am here in testing
.SECONDEXPANSION:
#XML_BUNDLES.sh= `$(LS) -1 xml-bundles/ | $(GREP) "\.lst" | $(CAT)`
#XML_BUNDLES=$(shell $(XML_BUNDLES.sh))
xml-bundles-extracted.ok: precompile-testemitter-plugin.ok precompile-j2x-testemitter-plugin.ok $(BUILDCLASSDIR) tck.jtx jaxb_test_bug.jtx $(TCKDIR)/tests_j2xrt copy-bundles.ok
	if [ -d xml-bundles ] ; then \
		for f in `ls -1 xml-bundles/ | grep "\.lst" | cat` ; do \
			$(TEST) -d $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/ || $(MKDIR) -p $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/; \
			validator=""; \
        		CLASSPATH=classes:$(JAVATEST_JAR):$(TCKDIR)/classes \
        		$(JAVA_6)  \
        		-Dtck.dest.dir=$(J2XRT_DEST_DIR)/tests \
        		-Dtck.source.dir=$(TEMPTESTSTORAGE)/tests \
        		-Dtck.classes.dir=$(BUILDCLASSDIR) \
        		-Dtck.build.propfile=../tck.properties \
        		-Dtestgen.emitter.TestGroup.XMLSchemaTest.e-lists=jaxb_test_bug.jtx \
        		-Dtestgen.emitter.TestGroup.J2XTest=com.sun.tgxml.tools.testgen.processors.emitter.J2XRuntimeEmitter \
        		-Dtestgen.emitter.TestGroup.J2XTest.executeClass=javasoft.sqe.tests.api.jakarta.xml.bind.J2XRuntimeTest \
        		$$validator \
        		com.sun.tgxml.tools.testgen.BundleTestGenFilter \
            			-plugin $(TESTFILTER_PLUGIN) \
            			-exlist tck.jtx \
            			-liblistOut $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/libID.lst \
            			-outDir $(J2XRT_DEST_DIR) \
            			-el $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/$(EXCLUDE_LIST) \
            			-basedir $(TEMPTESTSTORAGE) \
            			-dirList `$(PWD)`/xml-bundles/`echo $$f` \
            			-copyrightLink "COPYRIGHT-jaxbtck.html"; \
        		# compile j2x tests generating xml schemas from java sources \
        		validator=""; \
        		CLASSPATH=classes:$(JAVATEST_JAR):$(TCKDIR)/classes \
        		$(JAVA_6)  \
        		-Dtck.dest.dir=$(TCKDIR)/tests \
        		-Dtck.source.dir=$(TEMPTESTSTORAGE)/tests \
        		-Dtck.classes.dir=$(BUILDCLASSDIR) \
        		-Dtck.build.propfile=../tck.properties \
        		-Dtestgen.emitter.TestGroup.XMLSchemaTest.e-lists=jaxb_test_bug.jtx \
        		-Dtestgen.emitter.TestGroup.J2XTestWTB.SchemaGenMode=true \
        		$$validator \
        		com.sun.tgxml.tools.testgen.BundleTestGenFilter \
            			-plugin $(TESTFILTER_PLUGIN) \
            			-exlist tck.jtx \
            			-liblistOut $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/libID.lst \
            			-outDir $(TCKDIR) \
            			-el $(TESTEXTRACTDIR)/xml-bundles/`echo $$f | awk -F '-' {'print $$1'}`/$(EXCLUDE_LIST) \
            			-basedir $(TEMPTESTSTORAGE) \
            			-dirList `$(PWD)`/xml-bundles/`echo $$f` \
            			-copyrightLink "COPYRIGHT-jaxbtck.html"; \
		done \
	fi
	JAVA2SCHEMA_RT="$(TCKDIR)/tests/api/javax_xml/bind/JAXBContext"; \
	if [ -d $(J2XRT_DEST_DIR)/tests/java2schema ]; then \
	  if [ ! -d $$JAVA2SCHEMA_RT ]; then $(MKDIR) -p $$JAVA2SCHEMA_RT; fi; \
	  $(MV) $(J2XRT_DEST_DIR)/tests/java2schema $$JAVA2SCHEMA_RT; \
	fi
	$(RM) -rf $(J2XRT_DEST_DIR);
	echo "extracted sources for $(XML_BUNDLES:%.lst=%.ok) bundles, at `date`" > $@

CLEANFILES += xml-bundles-extracted.ok testsources.ok

J2XRT_DEST_DIR=$(TCKDIR)/tests_j2xrt

$(TCKDIR)/tests_j2xrt:
	echo tests_j2xrt
	mkdir -p $(TCKDIR)/tests_j2xrt

copy-bundles.ok: $(TCKDIR)/classes
	$(CP) $(TOPDIR)/src/share/classes/com/sun/tgxml/tjtf/tools/options/resources/ErrorsBundle.properties $(TCKDIR)/classes/com/sun/tgxml/tjtf/tools/options/resources/
	$(CP) -r $(TOPDIR)/src/share/classes/com/sun/tgxml/tjtf/resources/dtd $(TCKDIR)/classes/com/sun/tgxml/tjtf/resources/	

%-xml-bundle.ok: precompile-testemitter-plugin.ok precompile-j2x-testemitter-plugin.ok $(BUILDCLASSDIR) tck.jtx jaxb_test_bug.jtx $(J2XRT_DEST_DIR) copy-bundles.ok
	$(TEST) -d $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/ || $(MKDIR) -p $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/
	validator=""; \
	CLASSPATH=classes:$(JAVATEST_JAR):$(TCKDIR)/classes \
	$(JAVA_6)  \
	-Dtck.dest.dir=$(J2XRT_DEST_DIR)/tests \
	-Dtck.source.dir=$(TEMPTESTSTORAGE)/tests \
	-Dtck.classes.dir=$(BUILDCLASSDIR) \
	-Dtck.build.propfile=../tck.properties \
	-Dtestgen.emitter.TestGroup.XMLSchemaTest.e-lists=jaxb_test_bug.jtx \
	-Dtestgen.emitter.TestGroup.J2XTest=com.sun.tgxml.tools.testgen.processors.emitter.J2XRuntimeEmitter \
	-Dtestgen.emitter.TestGroup.J2XTest.executeClass=javasoft.sqe.tests.api.jakarta.xml.bind.J2XRuntimeTest \
	$$validator \
	com.sun.tgxml.tools.testgen.BundleTestGenFilter \
	    -plugin $(TESTFILTER_PLUGIN) \
	    -exlist tck.jtx \
	    -liblistOut $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/libID.lst \
	    -outDir $(J2XRT_DEST_DIR) \
	    -el $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/$(EXCLUDE_LIST) \
	    -basedir $(TEMPTESTSTORAGE) \
	    -dirList `$(PWD)`/$(@:%.ok=%.lst) \
	    -copyrightLink "COPYRIGHT-jaxbtck.html"
	# compile j2x tests generating xml schemas from java sources
	validator=""; \
	CLASSPATH=classes:$(JAVATEST_JAR):$(TCKDIR)/classes \
	$(JAVA_6)  \
	-Dtck.dest.dir=$(TCKDIR)/tests \
	-Dtck.source.dir=$(TEMPTESTSTORAGE)/tests \
	-Dtck.classes.dir=$(BUILDCLASSDIR) \
	-Dtck.build.propfile=../tck.properties \
	-Dtestgen.emitter.TestGroup.XMLSchemaTest.e-lists=jaxb_test_bug.jtx \
	-Dtestgen.emitter.TestGroup.J2XTestWTB.SchemaGenMode=true \
	$$validator \
	com.sun.tgxml.tools.testgen.BundleTestGenFilter \
	    -plugin $(TESTFILTER_PLUGIN) \
	    -exlist tck.jtx \
	    -liblistOut $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/libID.lst \
	    -outDir $(TCKDIR) \
	    -el $(TESTEXTRACTDIR)/$(@:%-xml-bundle.ok=%)/$(EXCLUDE_LIST) \
	    -basedir $(TEMPTESTSTORAGE) \
	    -dirList `$(PWD)`/$(@:%.ok=%.lst) \
	    -copyrightLink "COPYRIGHT-jaxbtck.html"
	echo ":-) created test sources for bundle: $@, at `date`" > $@

CLEANFILES += $(TESTEXTRACTDIR)

JAXBTEST_JTX_KEYWORD=jaxb_test

TESTBUG_JTX_KEYWORD=test_bug

# Exclude-list for the build
tck.jtx: jtxfiles.lst $(BUILDDIR)/future-fixed.jtx $(BUILDDIR)/precompile.jtx
	@echo "Making exclude-list for filtering..."
	$(CAT) $(BUILDDIR)/future-fixed.jtx $(BUILDDIR)/precompile.jtx `$(CAT) jtxfiles.lst` | $(GREP) -v '^#' | $(GREP) -v '$(JAXBTEST_JTX_KEYWORD)'| $(CAT) > $(@:%=non-converted-%)
	$(PERL) ../EL_convertor.pl $(@:%=non-converted-%) > $@
	if [ -f "$(TOPDIR)/src/share/lib/tck.bi_jtx" ]; then \
	   $(CAT) $(TOPDIR)/src/share/lib/tck.bi_jtx >> $@; \
	fi

#tck.jtx: jtxfiles.lst $(BUILDDIR)/future-fixed.jtx $(BUILDDIR)/precompile.jtx
#	@echo "Making exclude-list for filtering..."
#	cp $(TOPDIR)/src/share/lib/tck.bi_jtx $@

jaxb_test_bug.jtx: tck.jtx
	$(CAT) $(BUILDDIR)/future-fixed.jtx $(BUILDDIR)/precompile.jtx `$(CAT) jtxfiles.lst` | $(GREP) -v '^#' | \
	$(GREP) '$(JAXBTEST_JTX_KEYWORD)' | $(GREP) '$(TESTBUG_JTX_KEYWORD)' | $(CAT) > $@

# Exclude-list: all exludes after test generation.
# cat /dev/null to ensure the following cat invocation won't be blocked.
$(EXCLUDE_LIST): tck.jtx
	$(CAT) $(ABSTOPDIR)/src/share/lib/initial.jtx | $(GREP) -v '^#' | \
	$(GREP) -v '$(JAXBTEST_JTX_KEYWORD) *$(TESTBUG_JTX_KEYWORD)' | $(SORT) -u > $@;

# List of directories containing test.xml files
testsources-dirs.lst: testextract.ok
	(cd $(TEMPTESTSTORAGE) ; $(FIND) tests -name \*.test.xml -type f -print | $(SED) -e 's|^\(.*\)/.*\.test\.xml$$|\1|' | $(SORT) -u) > $@

CLEANFILES += testsources-dirs.lst $(EXCLUDE_LIST) tck.jtx
