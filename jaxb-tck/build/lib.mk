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

# 
# Makefile for a TCK product


#----------------------------------------------------------------------
#
# EXCLUDE_LIST defined in Defs.mk
#

$(EXCLUDE_LIST:%=$(TCKDIR)/lib/%): $(EXCLUDE_LIST)
	$(RM) $@
	$(CP) $(EXCLUDE_LIST) $@
	CLASSPATH=$(TCKDIR)/classes:$(JAVATEST_JAR_LOC)/javatest.jar \
	    $(GENERAL_JAVA) -Dbackup.ignore=.jtx com.sun.jck.utils.jtxedit.Main $@

ZIP.files += $(EXCLUDE_LIST:%=$(TCKDIR)/lib/%)


#----------------------------------------------------------------------
#
# Misc lib files

LIB_FILES = tck.policy
TCKDIRLIBPOLICY = $(TCKDIR)/lib/$(LIB_FILES)
CONFIGDIRPOLICY = $(TCKDIRLIBPOLICY:$(TCKDIR)/lib/%=$(CONFIGDIR)/%)

$(LIB_FILES:%=$(TCKDIR)/lib/%):$(TCKDIR)/lib/%:$(CONFIGDIR)/%
	@ $(TEST) -d $(@D) || $(MKDIR) -p $(@D)
	@ $(RM) $@
	$(CP) $(@:$(TCKDIR)/lib/%=$(CONFIGDIR)/%) $@
	$(CHMOD) -w $@

ZIP.files += $(LIB_FILES:%=$(TCKDIR)/lib/%)

jaxb.mtl: unzipped.ok
	@ echo "Generating MTL..."
	@JCKUTILS_CLASSPATH=$(UNZIPDIR)/$(TCKVERSION)/classes:$(JAXB_HOME)/mod/jaxb-api.jar \
	JCKUTILS_JAVA=$(PRECOMPILE_JAVA) \
	../jcktestlist  -o $@ -testCases -dirWalk $(UNZIPDIR)/$(TCKVERSION)/tests \
	|| echo "Warning: Generation of MTL failed."

CLEANFILES += jaxb.mtl
