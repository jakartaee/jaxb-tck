#
# Copyright (c) 1999, 2018 Oracle and/or its affiliates. All rights reserved.
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

# Partial Makefile to create TCK ZIP product
#
#----------------------------------------------------------------------
#
# Build jtt file
TEST_EXECUTE_SCRIPT.sh=echo "script=com.sun.jaxb_tck.lib.JaxbTckScript"
TEST_SUITE_SCRIPT.sh=echo "testsuite=com.sun.jaxb_tck.lib.JaxbTckTestSuite"

test_run_testsuite_jtt.ok: $(ZIP.files)
	@ $(RM) $(TCKDIR)/testsuite.jtt
	echo "name=JAXB TCK testsuite" > $(TCKDIR)/testsuite.jtt
	echo "id=JAXB-TCK-2.3" >> $(TCKDIR)/testsuite.jtt
	echo "classpath=./classes/" >> $(TCKDIR)/testsuite.jtt
	echo "$(shell $(TEST_SUITE_SCRIPT.sh))" >> $(TCKDIR)/testsuite.jtt
	echo "$(shell $(TEST_EXECUTE_SCRIPT.sh))" >> $(TCKDIR)/testsuite.jtt
	echo "$(PWD)"
	echo "finder=com.sun.javatest.finder.BinaryTestFinder -binary $(TCKDIR)/tests/testsuite.jtd" >> $(TCKDIR)/testsuite.jtt
	echo "interview=com.sun.jaxb_tck.interview.JAXBTCKParameters" >> $(TCKDIR)/testsuite.jtt
	echo "initial.jtx=lib/$(EXCLUDE_LIST)" >> $(TCKDIR)/testsuite.jtt
	echo "testsuite.jtt for test run built at `date`" > $@

#----------------------------------------------------------------------
#
# Create a ZIP file from everything in $(TCKDIR)
# The preceding .mk files should each incrementally add dependencies into
# $(ZIP.files) e.g.
#	ZIP.files += dependency ...
#

$(TCKZIP): $(ZIP.files) $(INSTALLER_EXPORT_FILE) test_run_testsuite_jtt.ok check-files.ok
	$(RM) $(TCKZIP)
	$(GENERAL_JAVA) -Xmx192m -cp $(TCKDIR)/classes com.sun.jck.utils.installer.Zipper -q \
			-n 50000 -m com.sun.jck.utils.installer.Installer -e $(INSTALLER_EXPORT_FILE)\
			$(TCKZIP) $(TCKDIR)
	if [ $$TCKZIPEMAIL ]; then \
	    $(PRINTF) "Subject: Zip build completed for $(TCKVERSION)\n\nSee $(TCKZIP)" | \
	    /usr/bin/mail $$TCKZIPEMAIL; \
	fi

$(INSTALLER_EXPORT_FILE):
	$(CLASSDEP) -cp $(TCKDIR)/classes com.sun.jck.utils.installer.Installer >$@


CLEANFILES += $(TCKZIP) $(ZIP.files)

#----------------------------------------------------------------------
#
# Ensure the TCK build directory is available
$(ZIP.files): $(TCKDIR)

$(TCKDIR):
	$(TEST) -d $(TCKDIR) || $(MKDIR) -p $(TCKDIR)

CLEANFILES += $(TCKDIR)

#----------------------------------------------------------------------
#
# put a build ID file in the build directory

$(TCKDIR)/build.txt: $(TCKDIR)
	$(RM) $@
	if [ -z "$(JCK_JAXB)" ]; then \
	    echo "TCK build id: $(BUILD_ID)" > $@ ; \
	    echo "JAXB build id: $(JAXB_ID)" >> $@ ; \
	    echo "Used Java: "`$(PRECOMPILE_JAVA) -fullversion 2>&1 ` >> $@ ; \
	else \
	    echo "JCK_VERSION=$(JCK.MM)" > $@ ; \
	    echo "JCK_MILESTONE=$(JCK_MILESTONE)" >> $@ ; \
	    echo "JCK_BUILD_ID=$(BUILD_ID)" >> $@ ; \
	    echo "JDK_VERSION=$(TEST_JDK_VERSION)" >> $@ ; \
	    echo "JDK_MILESTONE=$(TEST_JDK_MILESTONE)" >> $@ ; \
	    echo "JDK_BUILD_ID=$(TEST_JDK_BUILD)" >> $@ ; \
	    echo "JDK_VERSION_INT=$(TEST_JDK_VERSION_INT)" >> $@ ; \
	    echo `$(PRECOMPILE_JAVA) -fullversion 2>&1 ` >> $@ ; \
	fi
	@echo "**** $@ created at `date` ****"

ZIP.files += $(TCKDIR)/build.txt

#----------------------------------------------------------------------
#
# unzip the product zip file into an area where it can be tested

unzipped.ok: $(TCKZIP)
	$(RM) $(UNZIPDIR)/$(TCKVERSION)
	$(MKDIR) -p $(UNZIPDIR)
	$(GENERAL_JAVA) -jar $(TCKZIP) -o $(UNZIPDIR) > unzip.log
	echo "Unzipped at `date`" > $@

CLEANFILES += $(UNZIPDIR)/$(TCKVERSION) unzipped.ok unzip.log


#----------------------------------------------------------------------
#
# check filenames in TCK: 
# make sure not too long, and no files with case clashes
# docs excluded from length check

check-files.ok: check-files.lst
	$(CAT) check-files.lst | $(NAWK) -F/ \
	 '{ f=tolower($$0); \
	   if (f in files) { \
		if (files[f] != $$0) { print "case clash:", $$0, files[f]; err = 1; } \
	   } else \
		files[f] = $$0; \
	 } \
	 END { exit err; } '
	echo files in $(TCKDIR) checked at `date` > $@

check-files.lst: $(ZIP.files)
	(cd $(TCKDIR) ; $(FIND) . -name doc -prune -o -print) > $@
	(cd $(BUILDCLASSDIR) ;  $(FIND) . -print) >> $@

CLEANFILES += check-files.ok check-files.lst

#----------------------------------------------------------------------
#
# generate a convenience listing of zip file
# (used by stats target)

# tck-zip.lst: $(TCKZIP)
#         $(UNZIP) -l $(TCKZIP) > $@
#         @echo "+++ List of contents of $(TCKZIP): $@"
#
# CLEANFILES += tck-zip.lst

