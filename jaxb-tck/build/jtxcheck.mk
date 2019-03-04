#
# Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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
# Partial Makefile for TCK: jtx file checking

#----------------------------------------------------------------------
#
# Master test list

MASTER_TEST_LIST = tck-alltests.lst

$(MASTER_TEST_LIST): $(FULL_PRODUCTS:%=%/tests.lst)
	@echo "+++ master test list: $@"
	cat $(FULL_PRODUCTS:%=%/tests.lst) | sort -u > $@

# Mildly ugly dependency coming up; a remnant of the nested make tree.
# If target does not exist; we go in and build it; if target exists,
# we assume it is OK and up to date. Note we don't want a dependency
# on a pseudo-target like %-build, which will always be executed,
# thus causing this file and its derivatives to be remade every time.
%/tests.lst: $$(@D)
	@echo ">>> cd $(@D); $(MAKE) -f ../product.mk $(@F)"
	cd $(@D); $(MAKE) -f ../product.mk $(@F)
	@echo "<<<"


#----------------------------------------------------------------------
#
# analyze shipping exclude list

nightly := OPT_MASTER_TEST_LIST = $(MASTER_TEST_LIST)

excludeReport.html + excludeReport.txt: $(EXCLUDE_LIST) $$(OPT_MASTER_TEST_LIST)
	@echo "+++ exclude-list report: excludeReport.html"
	@echo "+++ exclude-list report: excludeReport.txt"
	JTX_JAVA=$(GENERAL_JAVA) export JTX_JAVA ; \
	$(JTXCHECK) -stateMode 1 \
		-h excludeReport.html \
		-t excludeReport.txt \
		-c java,java_sqe,jct_tools,java_plugin,hotspot,idl \
		-p solaris-sparc,solaris-x86,win-NT,win-95,win-98 \
		-w waived.txt \
		-s excludeStats.html \
		$(OPT_MASTER_TEST_LIST:%=-m %) \
		-l $(BUGTRAQ_SERVLET) \
		$(EXCLUDE_LIST) 2> excludeReport.log  || \
		( cat excludeReport.log ; \
		  echo ERROR: jtxcheck found errors )

CLEANFILES += \
	$(EXCLUDE_LIST) \
	excludeReport.html \
	excludeReport.txt \
	excludeReport.log 

#----------------------------------------------------------------------
#
# analyze precompile exclude list

precompileExcludeReport.html + precompileExcludeReport.txt: $(BUILDDIR)/precompile.jtx
	@echo "+++ exclude-list report: precompileExcludeReport.html"
	@echo "+++ exclude-list report: precompileExcludeReport.txt"
	JTX_JAVA=$(GENERAL_JAVA) export JTX_JAVA ; \
	$(JTXCHECK) -stateMode 1 \
		-h precompileExcludeReport.html \
		-t precompileExcludeReport.txt \
		-c java,java_sqe,jct_tools,java_plugin,hotspot,idl \
		-p solaris-sparc,solaris-x86,win-NT,win-95,win-98 \
		-l $(BUGTRAQ_SERVLET) \
		$(BUILDDIR)/precompile.jtx 2> precompileExcludeReport.log   ||  \
		( cat precompileExcludeReport.log ; \
		  echo ERROR: jtxcheck found errors )

CLEANFILES += \
	precompileExcludeReport.html \
	precompileExcludeReport.txt \
	precompileExcludeReport.log 

#----------------------------------------------------------------------
#
# Generate bug report list for JDK R&D
#
javaBugs.lst: excludeReport.txt
	@echo "bug report list for JDK R&D: $@"
	$(SED) -e '/java_sqe/d' -e '/Closed/d' -e '/Verified/d' -e '/Integrated/d' excludeReport.txt > $@

CLEANFILES += javaBugs.lst

#----------------------------------------------------------------------
#
# Check if early failures file is non-empty

EARLY_FAILURES_FILE = $(TOPDIR)/config/tck_early_JDK_builds.jtx

earlyFailuresFile.ok: 
	@if [ -r $(EARLY_FAILURES_FILE) -a \
		! -z "`cat $(EARLY_FAILURES_FILE) | egrep -v '(^#)|(^$$)' | true`" ]; then \
	    echo "Warning: $(EARLY_FAILURES_FILE) is non empty"; \
	    echo "+++ early failures file: $(EARLY_FAILURES_FILE)" ; \
	fi
	echo "$(EARLY_FAILURES_FILE) checked at `date`" > $@

CLEANFILES += earlyFailuresFile.ok

#----------------------------------------------------------------------

# Ignore early failures file while running jtxcheck
jtxcheck:= FILTERJTXFILES = | grep -v tck_early_JDK_builds.jtx

jtxcheck: \
	earlyFailuresFile.ok \
	excludeReport.html \
	precompileExcludeReport.html \
	javaBugs.lst
