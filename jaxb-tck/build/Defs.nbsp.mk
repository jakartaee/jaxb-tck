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
# Build Definitions for NSK domain

# FCS SQE Tools version and build: used for JavaTest in TCK
FCS_JCTTOOLS = /export/ld52/java/sqe/jct-tools3.1.2-b06/bundles

# Internal SQE Tools: used for JCKUTILS to build TCK
INTERNAL_JCTTOOLS = /export/ld52/java/sqe/jct-tools3.1.2-b06/binaries

# Location of CFREF, used to identify classes to be unpacked from jt.jar
CFREF = /export/ld52/java/sqe/classinfo1.0/CFREF/cfref

#Location of JAXB that should be tested
JAXB_DIR_NAME.sh = \
	 $(LS) /export/ld25/java/dest/Adelard/ | $(GREP) jaxb-$(TEST_JAXB_VERSION)-$(TEST_JAXB_MILESTONE)-$(TEST_JAXB_BUILD)
JAXB_DIR_NAME = $(JAXB_DIR_NAME.sh:sh)
JAXB_HOME = /export/ld25/java/dest/Adelard/$(JAXB_DIR_NAME)
JAXB_ID = $(JAXB_DIR_NAME)

JCTT_HOME=/export/ld52/java/sqe/JCTT/jctt-1_2-alpha2

JCT_TOOLS_WS=/export/ld21/java/sqe/jct-tools3.1.1

SIGTEST_WS=../../jck-sigtest13
JAXB_SIGTEST_WS=../../jaxb-sigtest

#----------------------------------------------------------------------
#
# GENERAL_JAVAxxx are used to run Java programs like JavaTest, Jasm, Jcod, etc
# It is *not* used to run tests
#
GENERAL_JAVAHOME = /export/ld25/java/dest/jdk1.4.1/solaris-sparc

#----------------------------------------------------------------------
#
# PRECOMPILE_JAVAxxx is used to generate any precompiled classes for release
# it is also used as the version of Java to be tested
#
PRECOMPILE_JAVAHOME = /export/ld25/java/dest/jdk1.3.1/solsparc

# SQE Utilities
PKGS_BASEDIR = /export/ld32/tools
CURMUDGEON = $(PKGS_BASEDIR)/htmlcheck/bin/webCurmudgeon
HTML2PS = $(PKGS_BASEDIR)/html2ps/html2ps
HTMLCHECK = $(PKGS_BASEDIR)/htmlcheck/bin/htmlcheck
LINKLINT = $(PERL) $(PKGS_BASEDIR)/linklint/linklint_2.1/linklint
DISTILL = /export/ld10/FrameMaker_6.0/bin/distill
FMBATCH = /export/ld10/FrameMaker_6.0/bin/fmbatch

#location of misc. tools
UNZIP = /export/AB/local/bin/unzip
ZIP = /export/AB/local/bin/zip
PERL = /export/AB/local/bin/perl

#----------------------------------------------------------------------
#
# Model TCK build specific parameters
#

# btools workspace
BTOOLS_WS_MODEL = /export/ld52/java/sqe/TCK_BTools_1.0

# btools-sqe-int workspace
BTOOLS_SQE_INT_WS = /export/ld53/java/sqe/btools-sqe-int

# JAXP libraries directory
JAXPLIBS_DIR=/export/ld51/java/pas/biwg/btools/lib

# workaround for bug in DefaultHtmlGenerator (it needs jdk1.3 to run, not jdk1.4)
JAVA_13 = /export/ld25/java/dest/jdk1.3.0fcsC/solaris/bin/java

# tckbtools.jar produced by btools build
TCKBTOOLS_JAR_MODEL = /export/ld52/java/sqe/TCK_BTools_1.0/lib/tckbtools.jar

JT_MULTIVM_CONCURRENCY_NBSP.sh= \
	if [ -z "$(INITIALURL)" -a -z "$(INITIALURLS)" ] ; then \
	    echo $(JT_MULTIVM_CONCURRENCY.sh:sh) ; \
	else \
	    echo $(PSR_COUNT) ; \
	fi

JT_MULTIVM_CONCURRENCY=$(JT_MULTIVM_CONCURRENCY_NBSP.sh:sh)

PARTIAL_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok \
	test-runtime-batch-multiJVM.ok 

SYSTEM_TESTS.runtime = \
	test-runtime-batch-singleJVM.ok \
	test-runtime-batch-multiJVM.ok 

