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

# Makefile for a TCK product

# standard defs
BUILDDIR = ..
CONFIGDIR = ../../src/share/lib
include ../Defs.mk
include ../shared_env.mk

#----------------------------------------------------------------------
#
# standard targets

all: build test


build: $(TCKZIP) unzipped.ok jaxb.mtl

clean: 
	$(RM) $(CLEANFILES)

test: $$(TESTS)

#----------------------------------------------------------------------

# component parts of TCK product
#include ../tools.mk
##include ../doc.mk
#include ../lib.mk
#include ../testextract.mk
#include ../testsrc.mk
#include ../html.mk
#include ../classes.mk
#include ../test.mk
#
## put it all together
##include ../zip.mk
#
## shared rules
#include ../rules.mk
#
## internal build tools
#include ../inttools.mk

EXCLUDEDDOCS.sh = \
	if [ -z "$(EXCLUDEDOCS)" ]; then \
		echo Documents will be built; \
	else \
		echo ****Documents will NOT be built***; \
	fi

.INIT:
	@echo ">>> debug details"
	@echo INITIALURLS           = $(INITIALURLS)
	@echo INITIALURL            = $(INITIALURL)
	@echo TESTSUITEFILES        = $(TESTSUITEFILES)
	@echo SELECT_TESTSUITEFILES = $(SELECT_TESTSUITEFILES) 
	@echo TESTSUITEDIRS         = $(TESTSUITEDIRS)
	@echo SELECTED_DIRS         = $(SELECTED_DIRS)
	@echo TESTS                 = $(TESTS)
	@echo BUILDAREA             = $(BUILDAREA)
	@echo PRJ_DIR               = $(PRJ_DIR)
	@echo TEMPBUILDAREA         = $(TEMPBUILDAREA)
	@echo TCKDIR                = $(TCKDIR)
	@echo TCKZIP                = $(TCKZIP)
	@echo PRODUCT_BASE          = $(PRODUCT_BASE)
	@echo PRODUCT               = $(PRODUCT)
	@echo GENERAL_JAVAHOME      = $(GENERAL_JAVAHOME)
	@echo PRECOMPILE_JAVAHOME   = $(PRECOMPILE_JAVAHOME)
	@echo JAXB_HOME             = $(JAXB_HOME)
	@echo INTERNAL_JCTTOOLS     = $(INTERNAL_JCTTOOLS)
	@echo FCS_JCTTOOLS          = $(FCS_JCTTOOLS)
	@echo CFREF                 = $(CFREF)
	@echo JCTT_HOME             = $(JCTT_HOME)
	@echo BTOOLS_WS_MODEL       = $(BTOOLS_WS_MODEL)
	@echo JAXPLIBS_DIR          = $(JAXPLIBS_DIR)
	@echo TEST_REPOSITORY       = $(TEST_REPOSITORY)
	@echo REPOSITORIES          = $(TEST_REPOSITORIES)
	@echo TCKBTOOLS             = $(TCKBTOOLS)
	@echo TCKMM             = $(TCKMM)
	@echo SHARED_ABSTOPDIR             = $(SHARED_ABSTOPDIR)
	@echo README_NAME             = $(README_NAME)
	@echo $(shell $(EXCLUDEDDOCS.sh))
	@echo "<<< end debug details"

srcbundle:
	SRCBUNDLE_RMDIR=$(SRCBUNDLE_TEMPDIR)/$(TCKVERSION).remove; \
	$(RM) -rf $(SRCBUNDLE_TEMPDIR)/$(TCKVERSION) $$SRCBUNDLE_RMDIR $(SRCBUNDLE_TEMPDIR)/$(TCKVERSION).src.zip; \
	$(MKDIR) -p $(SRCBUNDLE_TEMPDIR); \
	$(MKDIR) -p $$SRCBUNDLE_RMDIR; \
	$(PRECOMPILE_JAVA) -jar $(TCKZIP) -q -o $(SRCBUNDLE_TEMPDIR); \
	for i in $(SRCBUNDLE_RMFILES:%=$(SRCBUNDLE_TEMPDIR)/$(TCKVERSION)/%); do \
	    if [ -f $$i -o -d $$i ]; then \
	        $(MV) $$i $$SRCBUNDLE_RMDIR; \
	    fi \
	done; \
	find $(SRCBUNDLE_TEMPDIR)/$(TCKVERSION)/classes \( -name "*.class" -o -name "*.klass" \) -exec $(MV) -f {} $$SRCBUNDLE_RMDIR \;; \
	if [ ! -z "$(SRCBUNDLE_RELEASE_DOCS)" ]; then \
	    $(CP) $(SRCBUNDLE_RELEASE_DOCS) $(SRCBUNDLE_TEMPDIR)/$(TCKVERSION); \
	fi; \
	(cd $(SRCBUNDLE_TEMPDIR); $(ZIP) -r -q $(TCKVERSION).src.zip $(TCKVERSION)); \
	$(ZIP) -j $(SRCBUNDLE_ZIP) -g $(SRCBUNDLE_TEMPDIR)/$(TCKVERSION).src.zip

