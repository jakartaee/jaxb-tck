#
# Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
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

# Top level Makefile for main TCK workspace
#

BUILDDIR = .
include Defs.mk

SAMPLE_PRODUCTS= sampleJAXB

FULL_PRODUCTS = JAXB-TCK

# The normal set of products for all
STD_PRODUCTS = $(FULL_PRODUCTS)

# All products
ALL_PRODUCTS = $(STD_PRODUCTS) $(SAMPLE_PRODUCTS)

#----------------------------------------------------------------------

all: $(STD_PRODUCTS:%=%-build) $(STD_PRODUCTS:%=%-test)

clean: $(ALL_PRODUCTS:%=%-clean)
	$(RM) $(ALL_PRODUCTS) $(BUILDAREA) $(TEMPBUILDAREA) $(CLEANFILES)

nightly: $(FULL_PRODUCTS:%=%-build)

#$(ALL_PRODUCTS): $$(@:%=%-all)

#----------------------------------------------------------------------

INITIALURLS.sh = \
	if [ ! -z "$(INITIALURLS)$(INITIALURL)$(DEFAULTINITIALURLS)" ]; then \
		echo INITIALURLS='"'$(INITIALURLS) $(INITIALURL) $(DEFAULTINITIALURLS)'"' ; \
	fi

#----------------------------------------------------------------------

REPOSITORY_DIR.sh = \
	if [ ! -z "$(REPOSITORY_DIR)" ]; then \
		echo REPOSITORY_DIR='"'$(REPOSITORY_DIR)'"' ; \
	fi

#----------------------------------------------------------------------

REPOSITORIES.sh = \
	if [ ! -z "$(REPOSITORIES)" ]; then \
		if [ "$(REPOSITORIES)" != "$(ABSTOPDIR)" ]; then \
		    echo REPOSITORIES='"'$(ABSTOPDIR) $(REPOSITORIES)'"' ; \
		else \
		    echo REPOSITORIES='"'$(REPOSITORIES)'"' ; \
		fi ; \
	fi

#----------------------------------------------------------------------

%-all:=   TARGET = all
%-build:= TARGET = build
%-clean:= TARGET = clean
%-test:=  TARGET = test
%-srcbundle:=  TARGET = srcbundle

# For most targets, we create the work subdirectory if necessary, 
# and execute make in that directory
$(ALL_PRODUCTS:%=%-build): INIT
	@$(MKDIR) -p $(@:%-build=%)
	@echo '>>> cd $(@:%-build=%) ; $(MAKE) -f ../product.mk $(SHARED_MK) -f ../libs.mk $(shell $(INITIALURLS.sh)) $(shell $(REPOSITORY_DIR.sh)) $(shell $(REPOSITORIES.sh)) build'
	@LC_COLLATE=C; export LC_COLLATE; cd $(@:%-build=%) ; $(MAKE) -f ../product.mk $(SHARED_MK) -f ../libs.mk $(shell $(INITIALURLS.sh)) $(shell $(REPOSITORY_DIR.sh)) $(shell $(REPOSITORIES.sh)) build
	@echo '<<< Done'

# For the clean targets, there is no point in making the subdirectory
# if it does not exist, so only clean in the work subdirectories that do exist
$(ALL_PRODUCTS:%=%-clean): INIT
	@ if $(TEST) -d $(@:%-$(TARGET)=%) ; then \
	    echo '>>> cd $(@:%-$(TARGET)=%) ; $(MAKE) -f ../product.mk $(shell $(INITIALURLS.sh)) $(shell $(REPOSITORY_DIR.sh)) $(shell $(REPOSITORIES.sh)) $(TARGET)' ; \
	    cd $(@:%-$(TARGET)=%) ; $(MAKE) -f ../product.mk $(shell $(INITIALURLS.sh)) $(shell $(REPOSITORY_DIR.sh)) $(shell $(REPOSITORIES.sh)) $(TARGET) ; \
	    echo '<<< Done' ; \
	fi

#----------------------------------------------------------------------
#
# do an upfront check that an X Windows server is running,
# in case it is needed by any tests. 
# By default, the display will be for the machine on which the
# tests are run, and *not* the user's current $DISPLAY.
# If you want to redirect where the tests display output,
# set TCKDISPLAY.
# Please don't change the default without checking with the 
# build team, and RE, who do the nightly builds.
# See run-tck.sh for more details

.NOT-INIT:
	@display=$${TCKDISPLAY:-`uname -n`:0.0} ; \
	if /usr/openwin/bin/xwininfo -root -display $$display 1>/dev/null 2>&1 ; then \
	    true ; \
	else \
	    echo "Cannot access display $$display" ; \
	    echo "Please make sure the window system is running and accessible." ; \
	    exit 1 ; \
	fi

# validate the JDK letter
# validate JDK location
# validate build serial number
# make sure executable log2html is available
INIT:
	@if [ ! -d $(PRECOMPILE_JAVAHOME) ]; then \
	    echo "The following JDK path is invalid, please fix and retry:" ; \
	    echo "  PRECOMPILE_JAVAHOME == $(PRECOMPILE_JAVAHOME)" ; \
	    exit 1 ; \
	fi
	$(RM) -f log2html
	$(CP) $(BUILDDIR)/log2html.ksh $(BUILDDIR)/log2html
	$(CHMOD) +x $(BUILDDIR)/log2html
	$(CHMOD) +x $(BUILDDIR)/run-ant-script

.NO_PARALLEL: clean

CLEANFILES += $(BUILDDIR)/log2html

#.NO_PARALLEL: clean partial-clean

#----------------------------------------------------------------------

include jtxcheck.mk
#include rules.mk
include shared_env.mk
