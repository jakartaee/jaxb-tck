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
SHARED_ENV_MK_DIR=$(BUILDAREA)
SHARED_ENV_MK=$(SHARED_ENV_MK_DIR)/Defs.jaxb.mk
## Following variables are Not Shared!!!!!!!!!!!
## JAXB-TCK fails with JDK6.0
# PRECOMPILE_JAVAHOME
# PRECOMPILE_JAVAC
# PRECOMPILE_JAVA
# PRECOMPILE_RMIC
# PRECOMPILE_IDL
# PRECOMPILE_JAR
# JMPP_JAVA
#############################
SHARED_VARS_NAMES = \
	INTERNAL_JCTTOOLS \
	SIGTEST_DIST \
	CFREF \
	CLASSINFO_HOME \
	JAXB_HOME \
	SIGTESTDEV_JAR_LOC \
	BTOOLS_WS_MODEL

$(SHARED_ENV_MK): $(SHARED_ENV_MK_DIR) $(SHARED_VARS_NAMES:%=SHARE_VARIABLES_%)
	touch $(SHARED_ENV_MK)

$(SHARED_ENV_MK_DIR):
	if [ ! -d $(SHARED_ENV_MK_DIR) ]; then \
	    $(MKDIR) -p $(SHARED_ENV_MK_DIR); \
	fi

$(SHARED_VARS_NAMES:%=SHARE_VARIABLES_%): $(SHARED_ENV_MK_DIR)
	@echo $(@:SHARE_VARIABLES_%=%)=$($(@:SHARE_VARIABLES_%=%)) >> $(SHARED_ENV_MK)

.NO_PARALLEL: $(SHARED_VARS_NAMES:%=SHARE_VARIABLES_%)

